package stickman.model;

import java.util.List;

public class LevelImp implements Level{

    private double height, width, floorheight, herox;
    private double yPos_Velocity = 5;
    private double yPos_Acceleration = 0.1;
    private double xPos_Velocity = 1.5;
    private boolean isVictory = false;

    private List<Entity> entities;
    private Hero hero;

    public LevelImp(double height, double width, double floorheight, double herox, List<Entity> entities){
        this.height = height;
        this.width = width;
        this.floorheight = floorheight;
        this.herox = herox;
        this.entities = entities;
        this.hero = (Hero)entities.get(0);
    }

    public List<Entity> getEntities(){
        return this.entities;
    }

    public double getHeight(){
        return this.height;
    }

    public double getWidth(){
        return this.width;
    }

    public boolean checkCollision(Entity entity1, Entity entity2){
        return (entity1.getXPos() < (entity2.getXPos() + entity2.getWidth())) &&
                ((entity1.getXPos() + entity1.getWidth()) > entity2.getXPos()) &&
                (entity1.getYPos() < (entity2.getYPos() + entity2.getHeight())) &&
                ((entity1.getYPos() + entity1.getHeight()) > entity2.getYPos()) &&
                (entity1.getLayer() == entity2.getLayer());
    }

    public void handleCollision(){
        for(int i = 0; i < this.entities.size(); i++){
            for(int j = 0; j < this.entities.size(); j++){

                if(checkCollision(this.entities.get(i), this.entities.get(j))){

                    if(this.entities.get(i) instanceof Hero &&
                            this.entities.get(j) instanceof BackGroundEntity){
                        BackGroundEntity backEntity = (BackGroundEntity) this.entities.get(j);

                        //if hit on the top of root, hero can stand on it
                        if(backEntity.getType() == EntityType.FOOT){
                           handleHeroAndFoot(hero, backEntity);
                        }

                        //if hit the mushroom, hero is able to shoot bullet
                        //and mushroom will be remove from the entity list
                        if(backEntity.getType() == EntityType.MUSHROOM){
                            handleHeroAndMushroom(hero, backEntity);
                            j--;
                        }

                        //if hit the flag, game finish
                        if(backEntity.getType() == EntityType.FLAG){
                            handleHeroAndFLAG(hero, backEntity);
                        }
                    }

                    //if hero hit the slime, back to original location
                    if(this.entities.get(i) instanceof Hero &&
                            this.entities.get(j) instanceof Slime){
                        Slime slime = (Slime) this.entities.get(j);
                        handleHeroAndSlime(hero, slime);
                    }

                    if(this.entities.get(i) instanceof BackGroundEntity &&
                            this.entities.get(j) instanceof Projectile){
                        BackGroundEntity backEntity = (BackGroundEntity) this.entities.get(i);

                        //bullet hit the foot, then remove
                        if(backEntity.getType() == EntityType.FOOT){
                            this.entities.remove(j);
                            j--;
                        }
                    }

                    //if bullet hit slime, both of them remove from entity list
                    if(this.entities.get(i) instanceof Projectile &&
                            this.entities.get(j) instanceof Slime){

                        this.entities.remove(i);
                        this.entities.remove(j);
                        j--;
                        i -= 2;

                    }
                }

            }
        }
    }

    public void handleHeroAndFoot(Hero hero, BackGroundEntity backentity){

        //let hero stand on the top of the foot
        if(hero.getyVel() > 0 && (hero.getYPos() + hero.getHeight()) >  backentity.getYPos()){
            hero.setStandToTrue();
            hero.setYPos(backentity.getYPos() - hero.getHeight());
        }

        if(hero.getyVel() < 0 &&
                (hero.getYPos() - hero.getHeight()) > (backentity.getYPos() - backentity.getHeight())){
            hero.setYPos(backentity.getYPos() + hero.getHeight());
        }

        hero.setyVel(0);
    }

    public void handleHeroAndMushroom(Hero hero, BackGroundEntity backentity){
        this.hero.setShootable();
        this.entities.remove(backentity);
    }

    public void handleHeroAndFLAG(Hero hero, BackGroundEntity backentity){
        this.isVictory = true;
    }

    public void handleHeroAndSlime(Hero hero, Slime slime){
        this.hero.setXPos(this.herox);
        this.hero.setYPos(this.floorheight);
    }

    public void tick(){

        //update hero xy-axis position
        //hero free fall
        this.hero.setyVel(this.hero.getyVel() + yPos_Acceleration);
        this.hero.setYPos(this.hero.getYPos() + this.hero.getyVel());

        handleCollision();




        //don't go out the left boundary
        if(hero.getXPos() <= 0){
            this.hero.setXPos(0);
            this.hero.setxVel(0);
        }
        //don't go out the right boundary
        else if(hero.getXPos() + this.hero.getWidth() > this.width){
            this.hero.setXPos(this.width - this.hero.getWidth());
            this.hero.setxVel(0);
        }else{
            this.hero.setXPos(this.hero.getXPos() + this.hero.getxVel());
        }

        //to check the hero is out of the y-axis boundary, if yes, then reset to original y-axis
        if(this.hero.getYPos() + this.hero.getHeight() >= this.floorheight){
            this.hero.setYPos(this.floorheight - this.hero.getHeight());
            this.hero.setyVel(0);
            this.hero.setStandToTrue();

        }else if(this.hero.getYPos() - this.hero.getHeight() <= 0){

            //don't go out the top boundary
            this.hero.setYPos(this.hero.getHeight());
        }


        for(int i = 0; i < this.entities.size(); i++){
            //update enemy position
            if(this.entities.get(i) instanceof Slime){
                Slime slime = (Slime) this.entities.get(i);
                slime.getStrategy().move();
            }

            //update bullet position
            if(this.entities.get(i) instanceof Projectile){
                Projectile bullet = (Projectile) this.entities.get(i);
                if(this.entities.get(i).getXPos() >= this.width){
                    this.entities.remove(i);
                    i--;
                }else {
                    bullet.getStrategy().move();
                }
            }

        }

    }


    public double getFloorHeight(){
        return this.floorheight;
    }

    public double getHeroX(){
        return this.hero.getXPos();
    }

    public double getHeroY(){
        return this.hero.getYPos();
    }

    public boolean jump(){
        if (!hero.getStand()){
            return false;
        }
        this.hero.setyVel(-yPos_Velocity);
        this.hero.setStandToFalse();
        return true;
    }

    public boolean shoot(){
        if(!hero.getShootable()){
            return false;
        }

        //add bullet to entities list
        Projectile bullet = new Projectile(hero.getXPos() + 10, hero.getYPos() + hero.getHeight() / 2, 5, 10);
        bullet.setStrategy(new MoveProjectile(bullet, this));
        this.entities.add(bullet);
        return true;
    }


    public boolean moveLeft(){
        this.hero.setxVel(-xPos_Velocity);
        return true;
    }

    public boolean moveRight(){
        this.hero.setxVel(xPos_Velocity);
        return true;
    }

    public boolean stopMoving(){
        hero.setxVel(0);
        return true;
    }

    public boolean getStatus(){
        return this.isVictory;
    }
}