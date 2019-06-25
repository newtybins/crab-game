import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class to represent the world.
 * 
 * @author Jacob Smith
 * @version 18/06/2019
 */
public class CrabWorld extends World
{
    public static int score = 0;
    public static int round = 1;
    
    // constructor
    public CrabWorld() {   
        // set the size of the world
        super(560, 560, 1); 
        
        // update variables
        score = 0;
        Crab.lives = 3;

        // prepare
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     */
    private void prepare() {
        // spawn the crab and lobster
        Crab crab = new Crab();
        addObject(crab, 150, 50);
        
        Lobster lobster = new Lobster();
        addObject(lobster, 520, 41);
        
        // spawn worms
        for (int i = 0; i < 10; i++) {
            Worm worm = new Worm();
            addObject(worm, Greenfoot.getRandomNumber(540), Greenfoot.getRandomNumber(540));
        }

        // show the text
        showText("Score: 0", 50, 25);
        showText("Round: 1", 50, 525);
        showText("Lives: 3", 500, 25);
        showText("Ammo: 1", 500, 525);
    }
}
