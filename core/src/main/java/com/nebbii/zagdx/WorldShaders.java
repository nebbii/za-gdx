package com.nebbii.zagdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public final class WorldShaders {
    private static ShaderProgram enemyHitFlashShader;

    private WorldShaders() {}

    public static void init() {
        if (enemyHitFlashShader != null) return;

        ShaderProgram.pedantic = false;

        enemyHitFlashShader = new ShaderProgram(
            Gdx.files.internal("shaders/default.vert"),
            Gdx.files.internal("shaders/enemy_hit_flash.frag")
        );

        if (!enemyHitFlashShader.isCompiled()) {
            throw new IllegalStateException("Enemy hit flash shader didn't compile right");
        }
    }

    public static ShaderProgram getEnemyHitFlashShader() {
        if (enemyHitFlashShader == null) init();

        return enemyHitFlashShader;
    }

    public static void dispose() {
        enemyHitFlashShader.dispose();
    }
}
