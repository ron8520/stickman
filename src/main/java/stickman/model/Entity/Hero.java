package stickman.model;

import java.util.List;
import java.util.ArrayList;

public class Hero extends EntityImp implements EntityMovement{

    private double xVel, yVel;
    private boolean isShootable = false;
    private boolean isStand = true;

    public Hero(double xPos, double yPos, double height, double width){
        this.xPos = xPos;
        this.yPos = yPos;
        this.height = height;
        this.width = width;
        this.layer = layer.FOREGROUND;
        this.imagePath = "ch_stand1.png";
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

    public void setShootable(){
        if(!this.isShootable) {
            this.isShootable = true;
        }
    }

    public boolean getShootable(){
        return this.isShootable;
    }

    public void setStandToTrue(){
        this.isStand = true;
    }

    public void setStandToFalse(){
        this.isStand = false;
    }

    public boolean getStand(){
        return this.isStand;
    }

}