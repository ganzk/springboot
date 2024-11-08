package com.track;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.track.enums.EntityType;
import javafx.scene.input.KeyCode;

public class FXGLTest extends GameApplication {

    @Override
    protected void initGame() {
        // 添加组件
        FXGL.getGameWorld().addEntity(CustomerEntityFactory.createEntity(EntityType.PLANE));
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("demo");
        settings.setHeight(720);
        settings.setWidth(1080);
    }

    public static void main(String[] args) {
        launch(args);
    }


    /**
     * 输入
     */
    @Override
    protected void initInput() {
        FXGL.onKey(KeyCode.D, () -> {

        });
    }

    

}
