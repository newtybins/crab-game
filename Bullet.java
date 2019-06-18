import greenfoot.*;

/**
 * Class to represent a bullet.
 * 
 * @author Jacob Smith
 * @version 18/06/2019
 */
public class Bullet extends Actor {
    private int direction;
    private int speed;
    
    // constructor
    public Bullet(int rotation) {
        direction = rotation;
        speed = 15;
    }
    
    /**
     * Act - do whatever the Crab wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // get everything we need
        World world;
        world = getWorld();
        
        Actor lobster;
        lobster = getOneObjectAtOffset(0, 0, Lobster.class);
        
        // if the lobster exists
        if (lobster != null) {
            // delete the lobster and the bullet that collided
            world.removeObject(lobster);
            world.removeObject(this);
        }
        
        // else if the bullet hits any of the edges of the world
        else if (this.getX() == 559 || this.getX() == 0 || this.getY() == 559 || this.getY() == 0) {
            world.removeObject(this);
        }
        
        // moving
        setRotation(direction);
        move(speed);
    }    
}
