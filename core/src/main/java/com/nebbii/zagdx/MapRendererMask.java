package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.ShortArray;

/**
 * Masking for drawing the map over world entities
 */
public class MapRendererMask {
    private final ShapeRenderer shapes = new ShapeRenderer();
    private final EarClippingTriangulator triangulator = new EarClippingTriangulator();

    public void beginMask(OrthographicCamera camera) {
        Gdx.gl.glEnable(GL20.GL_STENCIL_TEST);

        Gdx.gl.glStencilMask(0xFF);
        Gdx.gl.glClearStencil(0);

        // clear full stencil
        Gdx.gl.glClear(GL20.GL_STENCIL_BUFFER_BIT);

        Gdx.gl.glStencilFunc(GL20.GL_ALWAYS, 1, 0xFF);
        Gdx.gl.glStencilOp(GL20.GL_KEEP, GL20.GL_KEEP, GL20.GL_REPLACE);

        // disable color while setting mask
        Gdx.gl.glColorMask(false, false, false, false);

        shapes.setProjectionMatrix(camera.combined);
        shapes.begin(ShapeRenderer.ShapeType.Filled);
    }

    // LLM witchcraft for polygon filling
    public void maskPolygon(Polygon poly) {
        float[] verts = poly.getTransformedVertices();

        ShortArray indices = triangulator.computeTriangles(verts);

        for (int i = 0; i < indices.size; i += 3) {
            int i1 = indices.get(i) * 2;
            int i2 = indices.get(i + 1) * 2;
            int i3 = indices.get(i + 2) * 2;

            shapes.triangle(
                verts[i1],     verts[i1 + 1],
                verts[i2],     verts[i2 + 1],
                verts[i3],     verts[i3 + 1]
            );
        }
    }

    public void endMask() {
        shapes.end();

        // re-enable colors
        Gdx.gl.glColorMask(true, true, true, true);

        Gdx.gl.glStencilMask(0x00);
        Gdx.gl.glStencilFunc(GL20.GL_NOTEQUAL, 1, 0xFF);
        Gdx.gl.glStencilOp(GL20.GL_KEEP, GL20.GL_KEEP, GL20.GL_KEEP);
    }

    public void disable() {
        Gdx.gl.glStencilMask(0xFF);
        Gdx.gl.glDisable(GL20.GL_STENCIL_TEST);
    }
}
