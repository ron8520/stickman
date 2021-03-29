package stickman.model;


public class BackGroundEntity extends EntityImp{

    private EntityType type;

    public BackGroundEntity(double xpos, double ypos, double height, double width){
        this.layer = layer.FOREGROUND;
        this.xPos = xpos;
        this.yPos = ypos;
        this.height = height;
        this.width = width;
    }

    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }

    public void setEntityType(EntityType type){
        this.type = type;
    }

    public EntityType getType(){
        return this.type;
    }
}