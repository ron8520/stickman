package stickman.model;

public class MoveProjectile implements MoveStrategy {

    private Level level;
    private Projectile bullet;
    private final double xVelocity = 1.5;

    public MoveProjectile(Projectile bullet, Level level) {
        this.bullet = bullet;
        this.level = level;
    }

    public void move(){
        this.bullet.setXPos(this.bullet.getXPos() + this.xVelocity);
    }
}