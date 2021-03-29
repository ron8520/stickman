package stickman.model;


public class MoveLeft implements MoveStrategy{

    private Slime enemy;
    private Level level;
    private double xVelocity = 0.7;
    private double yVelocity = 1;

    public MoveLeft(Slime enemy, Level level){
        this.enemy = enemy;
        this.level = level;
    }

    public void move(){
        if(this.enemy.getXPos() <= 200) {
            this.enemy.setXPos(this.enemy.getXPos() - xVelocity);
        }

        if(this.enemy.getXPos() <= 0){
            this.enemy.setXPos(0);
            this.xVelocity = 0;
        }

        if (this.enemy.getYPos() + this.enemy.getHeight() >= this.level.getFloorHeight()){
            this.enemy.setYPos(this.level.getFloorHeight() - this.enemy.getHeight() / 2 - 3);
        }else{
            this.enemy.setYPos(this.enemy.getYPos() + yVelocity);
        }

    }
}