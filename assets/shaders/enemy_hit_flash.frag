#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform vec4 u_flashColor;
uniform float u_mix;

void main() {
    vec4 texColor = texture2D(u_texture, v_texCoords);

    if (texColor.a < 0.01) {
        discard;
    }

    vec3 finalRgb = mix(texColor.rgb, u_flashColor.rgb, u_mix);
    gl_FragColor = vec4(finalRgb, texColor.a);
}