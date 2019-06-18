import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CrabWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CrabWorld extends World
{

    /**
     * Constructor for objects of class CrabWorld.
     * 
     */
    public CrabWorld()
    {    
        super(560, 560, 1); 

        prepare();
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        // add crab and lobster
        Crab crab = new Crab();
        addObject(crab, 150, 50);
        
        Lobster lobster = new Lobster();
        addObject(lobster, 520, 41);
        
        // spawn worms
        for (int i = 0; i < 10; i++) {
            Worm worm = new Worm();
            addObject(worm, Greenfoot.getRandomNumber(540), Greenfoot.getRandomNumber(540));
        }

        // text
        showText("Score: 0", 50, 25);
        showText("Round: 1", 500, 25);
        showText("Lives: 3", 50, 525);
        showText("Ammo: 1", 500, 525);
    }
}
