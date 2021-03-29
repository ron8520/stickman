package stickman.model;

import java.util.List;

public interface LevelBuilder{

    void setHeight(double height);
    void setWidth(double width);
    void setEntities(List<Entity> entities);
    void setFloorHeight(double floorHeight);
    void setHeroX(double herox);
    void addEntity(Entity entity);
    List<Level> getLevels(String configure_file);
    Level getResult();

}