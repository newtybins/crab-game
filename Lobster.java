import greenfoot.*;

/**
 * Class to represent a lobster.
 * 
 * @author Jacob Smith
 * @version 18/06/2019
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
    
    /**
     * moveAround - Move the lobster around.
     */
    public void moveAround() {
        // move
        move(4);
        
        // turn randomly
        if (Greenfoot.getRandomNumber(100) < 10) {
            turn(Greenfoot.getRandomNumber(90) -45);
        }
        
        // if the lobster hits the edge of the world, turn 180 degrees
        if ((getX() <= 5 || getX() >= getWorld().getWidth() - 5) || (getY() <= 5 || getY() >= getWorld().getHeight() - 5)) {
            turn(180);
        }
   }
   
   /**
    * eat - Logic for making the lobster eat worms.
    */
   public void eat() {
        // get everything we need
        World world;
        world = getWorld();

        Actor crab;
        crab = getOneObjectAtOffset(0, 0, Crab.class);
        
        // if the crab exists
        if (crab != null) {
            // remove the worm
            world.removeObject(crab);
            
            // remove a life from the crab
            Crab.lives--;
            
            // if there are still more lives, add a new crab
            if (Crab.lives > 0) {
                world.addObject(crab, 282, 278);
            }
            
            // if there are no more lives, remove the world
            if (Crab.lives == 0) {
                Greenfoot.stop();
            }
            
            // play the eating sound
            Greenfoot.playSound("eating.wav");
            
            // update the lives
            world.showText("Lives: " + Crab.lives, 500, 25); 
        }
        
        Actor worm;
        worm = getOneObjectAtOffset(0, 0, Worm.class);
        
        // if the worm exists
        if (worm != null) {
            // remove the worm
            world.removeObject(worm);
            
            // play the eating sound
            Greenfoot.playSound("eating.wav");
            
            // decrease the crab's score
            CrabWorld.score = CrabWorld.score > 0 ? CrabWorld.score - 1 : 0;
            
            // update the score
            world.showText("Score: " + CrabWorld.score, 50, 25);
            
             // if that was the last worm
            if(world.getObjects(Worm.class).size() == 0) {
                // increment the round and update the text
                CrabWorld.round++;
                world.showText("Round: " + CrabWorld.round, 50, 525);
                
                // spawn new worms
                for (int i = 0; i < 10; i++) {
                    Worm newWorm = new Worm();
                    world.addObject(newWorm, Greenfoot.getRandomNumber(540), Greenfoot.getRandomNumber(540));
                }
                
                // add a new lobster to make it harder
                Lobster lobster = new Lobster();
                world.addObject(lobster, 520, 41);
                
                // update the ammo
                Crab.ammo = world.getObjects(Lobster.class).size();
                world.showText("Ammo: " + Crab.ammo, 500, 525);
            }
        }
   }
}
