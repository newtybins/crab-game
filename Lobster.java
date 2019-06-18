import greenfoot.*;

/**
 * Write a description of class Lobster here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lobster extends Actor
{   
    /**
     * Act - do whatever the Lobster wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        moveAround();
        eat();
    }   
    
    public void moveAround() {
        move(4);
        
        if (Greenfoot.getRandomNumber(100) < 10) {
            turn(Greenfoot.getRandomNumber(90) -45);
        }
        
        if ((getX() <= 5 || getX() >= getWorld().getWidth() - 5) || (getY() <= 5 || getY() >= getWorld().getHeight() - 5)) {
            turn(180);
        }
   }
   
   public void eat() {
       // get the crab which is next to the crab
        Actor crab;
        crab = getOneObjectAtOffset(0, 0, Crab.class);
        
        // if the crab exists
        if (crab != null) {
            // get the world
            World world;
            world = getWorld();
            
            // remove the worm
            world.removeObject(crab);
            
            // remove a life from the crab
            Crab.lives = Crab.lives - 1;
            
            if (Crab.lives > 0) {
                world.addObject(crab, 282, 278);
            }
            
            if (Crab.lives == 0) {
                world.removeObjects(world.getObjects(null));
            }
            
            // play the eating sound
            Greenfoot.playSound("eating.wav");
            
            // update the lives
            world.showText("Lives: "+Crab.lives, 500, 25); 
        }
        
        Actor worm;
        worm = getOneObjectAtOffset(0, 0, Worm.class);
        
        // if the worm exists
        if (worm != null) {
            // get the world
            World world;
            world = getWorld();
            
            // remove the world
            world.removeObject(worm);
            
            // play the eating sound
            Greenfoot.playSound("eating.wav");
            
            // decrease the crab's score
            Crab.score = Crab.score > 0 ? Crab.score - 1 : 0;
            
            // update the scores
            world.showText("Score: "+Crab.score, 50, 25);
        }
   }
}
