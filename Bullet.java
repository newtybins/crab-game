import greenfoot.*;

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Actor
{
    private int direction, speed;
    
    public Bullet(int rotation) {
        direction = rotation;
        speed = 15;
    }
    
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        World world;
        world = getWorld();
        
        Actor lobster;
        lobster = getOneObjectAtOffset(0, 0, Lobster.class);
        
        if (lobster != null) {
            world.removeObject(lobster);
            world.removeObject(this);
        }
        
        else if (this.getX() == 559 || this.getX() == 0 || this.getY() == 559 || this.getY() == 0) {
            world.removeObject(this);
        }
        
        setRotation(direction);
        move(speed);
    }    
}
