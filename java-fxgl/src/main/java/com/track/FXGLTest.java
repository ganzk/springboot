package com.track;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

public class FXGLTest extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("demo");
        settings.setHeight(720);
        settings.setWidth(1080);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
