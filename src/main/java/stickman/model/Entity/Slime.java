package stickman.model;

public class Slime extends EntityImp implements EntityMovement{

    private double xVel, yVel;
    private MoveStrategy strategy;

    public Slime(double xPos, double yPos, double height, double width){
        this.xPos = xPos;
        this.yPos = yPos;
        this.height = height;
        this.width = width;
        this.layer = layer.FOREGROUND;
    }

    public void setStrategy(MoveStrategy strategy){
        this.strategy = strategy;
    }

    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }

    public void setxVel(double xVel){
        this.xVel = xVel;
    }

    public void setyVel(double yVel){
        this.yVel = yVel;
    }

    public double getxVel(){
        return this.xVel;
    }

    public double getyVel(){
        return this.yVel;
    }

    public MoveStrategy getStrategy(){
        return this.strategy;
    }

}