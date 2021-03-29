package stickman.model;

public abstract class EntityImp implements Entity{

    public double xPos, yPos, height, width;
    public String imagePath;
    public Layer layer;

    public String getImagePath(){
        return this.imagePath;
    }

    public Layer getLayer(){
        return this.layer;
    }

    public double getXPos(){
        return this.xPos;
    }

    public double getYPos(){
        return this.yPos;
    }

    public double getHeight(){
        return this.height;
    }

    public double getWidth(){
        return this.width;
    }

    public void setXPos(double xpos){
        this.xPos = xpos;
    }

    public void setYPos(double ypos){
        this.yPos = ypos;
    }

}