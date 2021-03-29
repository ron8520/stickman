package stickman.model;


public class Projectile extends EntityImp implements EntityMovement{

    private double xVel;
    private MoveStrategy strategy;
    private boolean deleteable = false;

    public Projectile(double xPos, double yPos, double height, double width){
        this.xPos = xPos;
        this.yPos = yPos;
        this.height = height;
        this.width = width;
        this.layer = layer.FOREGROUND;
        this.imagePath = "bullet.png";
    }

    public void setxVel(double xVel){
        this.xVel = xVel;
    }

    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }

    //no y-axis movement;
    public void setyVel(double yVel){
    }

    public double getxVel(){
        return this.xVel;
    }

    //no y-axis movement
    public double getyVel(){
        return 0;
    }

    public void setStrategy(MoveStrategy strategy){
        this.strategy = strategy;
    }

    public MoveStrategy getStrategy() {
        return strategy;
    }

}