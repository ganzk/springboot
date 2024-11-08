package com.track;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.track.enums.EntityType;

public class CustomerEntityFactory {

    //
    public static Entity createEntity(EntityType type) {
        switch (type) {
            case PLANE:
                Entity entity = FXGL.entityBuilder().view("player.png").build();
                return entity;
            default:
                return null;
        }
    }


}
