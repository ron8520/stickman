package stickman.model;

import java.util.List;

public interface Level {
    List<Entity> getEntities();
    double getHeight();
    double getWidth();
    boolean getStatus();

    void tick();

    double getFloorHeight();
    double getHeroX();
    double getHeroY();

    boolean jump();
    boolean moveLeft();
    boolean moveRight();
    boolean stopMoving();
    boolean shoot();
}
