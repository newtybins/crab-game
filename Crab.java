import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class to represent the crab.
 * 
 * @author Jacob Smith
 * @version 18/06/2019
 */
public class Crab extends Actor {    
    public static int lives = 3;
    
    public static int ammo = 1;
    private int charWidth = getImage().getWidth()-45;
    private int wait = 50;
    
    /**
     * Act - do whatever the Crab wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        controls();
        eat();
        
        if (wait > 0) wait --;
    }    
    
    
    /**
     * Controls - Logic for controlling the crab. This is called whenever
     * the 'act' method gets called.
     */
    public void controls() {
        move(4);
        
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) {
            turn(-3);
        }
        
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) {
            turn(3);
        }
        
        if (Greenfoot.isKeyDown("f") || Greenfoot.isKeyDown("space")) {
            if (wait == 0) {
                wait = 50;
                shoot();
            }
        }
    }
    
    /**
     * Eat - Logic for the crab eating worms. This is called whenever the 'act' method
     * gets called.
     */
    public void eat() {
        // get everything we need
        World world;
        world = getWorld();
        
        Actor worm;
        worm = getOneObjectAtOffset(0, 0, Worm.class);
        
        // if the worm exists
        if (worm != null) {
            // remove the worm
            world.removeObject(worm);
            
            // play the eating sound
            Greenfoot.playSound("eating.wav");
            
            // increment the score
            CrabWorld.score++;
            
            // show the score and round
            world.showText("Score: " + CrabWorld.score, 50, 25);
            world.showText("Round: " + CrabWorld.round, 50, 525);
            
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
                ammo = world.getObjects(Lobster.class).size();
                world.showText("Ammo: " + ammo, 500, 525);
            }
        }
    }
    
    /**
     * Shoot - Logic for making the crab shoot bullets.
     */
    public void shoot() {
        // get everything we need
        Bullet bullet;
        bullet = new Bullet(getRotation());
        
        World world;
        world = getWorld();
        
        // if the ammo is greater than 0, decrement the ammo
        if (ammo > 0) ammo--;

        // else if the ammo is at 0, don't let the code run
        else if (ammo == 0) return;
        
        // update the ammo
        world.showText("Ammo: "+ammo, 500, 525);
            
        // add the bullet to the world
        world.addObject(bullet, getX() + charWidth, getY());
    }
}
