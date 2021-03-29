package stickman.model;


public class MoveRight implements MoveStrategy{

    private Slime enemy;
    private Level level;
    private final double xVelocity = 0.7;
    private double yVelocity = 1;

    public MoveRight(Slime enemy, Level level){
        this.enemy = enemy;
        this.level = level;
    }

    public void move(){

        if(this.enemy.getXPos() > 50) {
            this.enemy.setXPos(this.enemy.getXPos() + xVelocity);
        }

        if(this.enemy.getXPos() >= this.level.getWidth()){
            this.enemy.setXPos(this.level.getWidth());
        }

        if (this.enemy.getYPos() + this.enemy.getHeight() >= this.level.getFloorHeight()){
            this.enemy.setYPos(this.level.getFloorHeight() - this.enemy.getHeight() / 2 - 3);
        }else{
            this.enemy.setYPos(this.enemy.getYPos() + yVelocity);
        }
    }
}