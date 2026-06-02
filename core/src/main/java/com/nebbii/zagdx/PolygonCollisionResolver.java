package com.nebbii.zagdx;

import java.util.List;

import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ShortArray;

public class PolygonCollisionResolver {
    private static final int MAX_PUSH_ITERATIONS = 8; // how many polygon nudges per tick
    private static final float MIN_PUSH_LEN2 = 0.000001f;
    private static final float CONVEX_EPSILON = 0.00001f;

    private final EarClippingTriangulator triangulator = new EarClippingTriangulator();

    public boolean rectangleOverlapsPolygon(Rectangle rectangle, Polygon polygon) {
        if (!rectangle.overlaps(polygon.getBoundingRectangle())) {
            return false;
        }

        float[] polyVertices = polygon.getTransformedVertices();

        if (polyVertices.length < 6) {
            return false;
        }

        if (isConvex(polyVertices)) {
            return rectangleOverlapsConvexVertices(rectangle, polyVertices);
        }

        ShortArray triangleIndices = triangulator.computeTriangles(polyVertices);

        for (int i = 0; i < triangleIndices.size; i += 3) {
            float[] triangleVertices = getTriangleVertices(polyVertices, triangleIndices, i);

            if (rectangleOverlapsConvexVertices(rectangle, triangleVertices)) {
                return true;
            }
        }

        return false;
    }

    public boolean rectangleOverlapsAnyPolygon(Rectangle rectangle, List<PolygonMapObject> polygonObjects) {
        for (PolygonMapObject polygonObject : polygonObjects) {
            Polygon polygon = polygonObject.getPolygon();

            if (!rectangle.overlaps(polygon.getBoundingRectangle())) {
                continue;
            }

            if (rectangleOverlapsPolygon(rectangle, polygon)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @nebbii: Resolve rectangle against a polygon that may be convex or concave.
     * Concave polygons are triangulated into convex pieces, then all overlapping
     * triangles are resolved in repeated passes.
     */
    public boolean resolveRectangleVsPolygon(Rectangle movingRectangle, Polygon solidPolygon) {
        if (!movingRectangle.overlaps(solidPolygon.getBoundingRectangle())) {
            return false;
        }

        float[] polyVertices = solidPolygon.getTransformedVertices();

        if (polyVertices.length < 6) {
            return false;
        }

        if (isConvex(polyVertices)) {
            return resolveRectangleVsConvexVertices(movingRectangle, polyVertices);
        }

        boolean movedAtAll = false;
        ShortArray triangleIndices = triangulator.computeTriangles(polyVertices);

        for (int pass = 0; pass < MAX_PUSH_ITERATIONS; pass++) {
            boolean movedThisPass = false;

            for (int i = 0; i < triangleIndices.size; i += 3) {
                float[] triangleVertices = getTriangleVertices(polyVertices, triangleIndices, i);

                Vector2 push = computePushOutVectorConvex(movingRectangle, triangleVertices);

                if (push.len2() > MIN_PUSH_LEN2) {
                    movingRectangle.x += push.x;
                    movingRectangle.y += push.y;
                    movedThisPass = true;
                    movedAtAll = true;
                }
            }

            if (!movedThisPass) {
                break;
            }
        }

        return movedAtAll;
    }

    boolean resolveRectangleVsConvexVertices(Rectangle movingRectangle, float[] convexVertices) {
        boolean movedAtAll = false;

        for (int pass = 0; pass < MAX_PUSH_ITERATIONS; pass++) {
            Vector2 push = computePushOutVectorConvex(movingRectangle, convexVertices);

            if (push.len2() <= MIN_PUSH_LEN2) {
                break;
            }

            movingRectangle.x += push.x;
            movingRectangle.y += push.y;
            movedAtAll = true;
        }

        return movedAtAll;
    }

    /**
     * SAT push-out for rectangle vs convex shapes
     */
    static Vector2 computePushOutVectorConvex(Rectangle movingRectangle, float[] polyVertices) {
        Vector2 mtv = new Vector2();

        if (polyVertices.length < 6) {
            return mtv.set(0f, 0f);
        }

        float[] rectangleVertices = getRectangleVertices(movingRectangle);

        float smallestOverlap = Float.POSITIVE_INFINITY;
        Vector2 smallestAxis = new Vector2();

        // Polygon edge normals
        for (int i = 0; i < polyVertices.length; i += 2) {
            int next = (i + 2) % polyVertices.length;

            float x1 = polyVertices[i];
            float y1 = polyVertices[i + 1];
            float x2 = polyVertices[next];
            float y2 = polyVertices[next + 1];

            float edgeX = x2 - x1;
            float edgeY = y2 - y1;

            if (edgeX == 0f && edgeY == 0f) continue;

            Vector2 axis = new Vector2(-edgeY, edgeX).nor();

            float overlap = getOverlapOnAxis(rectangleVertices, polyVertices, axis);

            if (overlap <= 0f) {
                return mtv.set(0f, 0f);
            }

            if (overlap < smallestOverlap) {
                smallestOverlap = overlap;
                smallestAxis.set(axis);
            }
        }

        // Rectangle axes
        Vector2[] rectangleAxes = {
            new Vector2(1f, 0f),
            new Vector2(0f, 1f)
        };

        for (Vector2 axis : rectangleAxes) {
            float overlap = getOverlapOnAxis(rectangleVertices, polyVertices, axis);

            if (overlap <= 0f) {
                return mtv.set(0f, 0f);
            }

            if (overlap < smallestOverlap) {
                smallestOverlap = overlap;
                smallestAxis.set(axis);
            }
        }

        if (smallestOverlap == Float.POSITIVE_INFINITY) {
            return mtv.set(0f, 0f);
        }

        // Make sure push points from polygon outward toward the rectangle
        Vector2 rectangleCenter = new Vector2(
            movingRectangle.x + movingRectangle.width * 0.5f,
            movingRectangle.y + movingRectangle.height * 0.5f
        );

        Vector2 polygonCenter = computePolygonCenter(polyVertices);
        Vector2 centerDelta = new Vector2(rectangleCenter).sub(polygonCenter);

        if (centerDelta.dot(smallestAxis) < 0f) {
            smallestAxis.scl(-1f);
        }

        return mtv.set(smallestAxis).scl(smallestOverlap);
    }

    static boolean rectangleOverlapsConvexVertices(Rectangle rectangle, float[] convexVertices) {
        if (convexVertices.length < 6) {
            return false;
        }

        float[] rectangleVertices = getRectangleVertices(rectangle);

        if (hasSeparatingAxis(rectangleVertices, rectangleVertices, convexVertices)) {
            return false;
        }

        if (hasSeparatingAxis(convexVertices, rectangleVertices, convexVertices)) {
            return false;
        }

        return true;
    }

    static boolean hasSeparatingAxis(float[] axisSourceVertices, float[] verticesA, float[] verticesB) {
        for (int i = 0; i < axisSourceVertices.length; i += 2) {
            int next = (i + 2) % axisSourceVertices.length;

            float x1 = axisSourceVertices[i];
            float y1 = axisSourceVertices[i + 1];
            float x2 = axisSourceVertices[next];
            float y2 = axisSourceVertices[next + 1];

            float edgeX = x2 - x1;
            float edgeY = y2 - y1;

            if (edgeX == 0f && edgeY == 0f) {
                continue;
            }

            Vector2 axis = new Vector2(-edgeY, edgeX).nor();
            float overlap = getOverlapOnAxis(verticesA, verticesB, axis);

            if (overlap <= 0f) {
                return true;
            }
        }

        return false;
    }

    static float getOverlapOnAxis(float[] verticesA, float[] verticesB, Vector2 axis) {
        float[] projectionA = projectVertices(verticesA, axis);
        float[] projectionB = projectVertices(verticesB, axis);

        float minA = projectionA[0];
        float maxA = projectionA[1];
        float minB = projectionB[0];
        float maxB = projectionB[1];

        return Math.min(maxA, maxB) - Math.max(minA, minB);
    }

    static float[] projectVertices(float[] vertices, Vector2 axis) {
        float min = Float.POSITIVE_INFINITY;
        float max = Float.NEGATIVE_INFINITY;

        for (int i = 0; i < vertices.length; i += 2) {
            float projection = vertices[i] * axis.x + vertices[i + 1] * axis.y;
            if (projection < min) min = projection;
            if (projection > max) max = projection;
        }

        return new float[] { min, max };
    }

    static float[] getRectangleVertices(Rectangle rectangle) {
        return new float[] {
            rectangle.x, rectangle.y,
            rectangle.x + rectangle.width, rectangle.y,
            rectangle.x + rectangle.width, rectangle.y + rectangle.height,
            rectangle.x, rectangle.y + rectangle.height
        };
    }

    static float[] getTriangleVertices(float[] vertices, ShortArray triangleIndices, int triangleOffset) {
        int i0 = triangleIndices.get(triangleOffset) * 2;
        int i1 = triangleIndices.get(triangleOffset + 1) * 2;
        int i2 = triangleIndices.get(triangleOffset + 2) * 2;

        return new float[] {
            vertices[i0], vertices[i0 + 1],
            vertices[i1], vertices[i1 + 1],
            vertices[i2], vertices[i2 + 1]
        };
    }

    static Vector2 computePolygonCenter(float[] vertices) {
        float sumX = 0f;
        float sumY = 0f;
        int count = vertices.length / 2;

        for (int i = 0; i < vertices.length; i += 2) {
            sumX += vertices[i];
            sumY += vertices[i + 1];
        }

        return new Vector2(sumX / count, sumY / count);
    }

    static boolean isConvex(float[] vertices) {
        int vertexCount = vertices.length / 2;

        if (vertexCount < 3) {
            return false;
        }

        boolean hasPositiveCross = false;
        boolean hasNegativeCross = false;
        boolean hasNonZeroCross = false;

        for (int i = 0; i < vertexCount; i++) {
            int current = i * 2;
            int next = ((i + 1) % vertexCount) * 2;
            int afterNext = ((i + 2) % vertexCount) * 2;

            float currentX = vertices[current];
            float currentY = vertices[current + 1];

            float nextX = vertices[next];
            float nextY = vertices[next + 1];

            float afterNextX = vertices[afterNext];
            float afterNextY = vertices[afterNext + 1];

            float edgeAX = nextX - currentX;
            float edgeAY = nextY - currentY;

            float edgeBX = afterNextX - nextX;
            float edgeBY = afterNextY - nextY;

            float cross = edgeAX * edgeBY - edgeAY * edgeBX;

            if (cross > CONVEX_EPSILON) {
                hasPositiveCross = true;
                hasNonZeroCross = true;
            }
            else if (cross < -CONVEX_EPSILON) {
                hasNegativeCross = true;
                hasNonZeroCross = true;
            }

            if (hasPositiveCross && hasNegativeCross) {
                return false;
            }
        }

        return hasNonZeroCross;
    }
}
