import java.awt.*; //allows access to the java command libraries
import hsa.Console; //allows access to the Console class files
import java.lang.*; // allows access to Thread class

/*
Alyssa Wang
Ms.Krasteva
January 18,2018
This class creates the rabbit that walks through the wallpaper on the computer.

There class creates the rabbit that walks through the wallpaper on the computer.
There is one method called display which creates the animation of the rabbit.
This class will be called so that it can be ran at the same time as the splash screen method.

Variable Declarations:
Name                        Type               Purpose
c                           Console            Reference variable which allows access to Console

*/

public class RabbitAnimation extends Thread // creates a class called rabbitAnimation that is in the thread class
{
    private Console c; //class that allows access to the ouput window
    /*
    This method creates the animation of a wallpaper on a computer

    Variable Declarations:
    Name                    Type               Purpose
    lightGrey               Color              Stores the color for the bunny
    skyBlue                 Color              Stores the color for the sky
    almostWhite             Color              Stores the color for the rabbit's stomach

    There is a for loop which moves the rabbit from one end of the computer screen to the other.
    Inside this for loop there is also a try block to catch any errors when using thread.sleep(); to delay the animation.
    */
    public void display ()  //method called display
    {
	//local colour variable for the computer and the bunny
	Color lightGrey = new Color (204, 204, 204);
	//local colour variable for the sky
	Color skyBlue = new Color (175, 238, 238);
	//local colour variable for the rabbit's stomach
	Color almostWhite = new Color (252, 249, 244);
	//for loop used to make the rabbits go straight across the wallpaper on the computer
	for (int i = 0 ; i <= 190 ; i++)
	{
	    synchronized (c)  //prevents the colors from switching
	    {
		c.setColor (skyBlue);
		c.fillRect (270 + i, 271, 75, 109); //erase
		c.setColor (Color.black);
		c.drawOval (293 + i, 365, 14, 14); //left foot outline
		c.drawOval (312 + i, 365, 14, 14); //right foot outline
		c.drawOval (278 + i, 333, 14, 13); //left arm outline
		c.drawOval (325 + i, 333, 14, 13); //right arm outline
		c.setColor (lightGrey);
		c.fillOval (294 + i, 366, 13, 13); //left foot
		c.fillOval (313 + i, 366, 13, 13); //right foot
		c.fillOval (279 + i, 334, 13, 12); //left arm
		c.fillOval (326 + i, 334, 13, 12); //right arm
		c.setColor (Color.black);
		c.drawOval (289 + i, 271, 11, 30); //left ear outline
		c.drawOval (319 + i, 271, 11, 31); //right ear outline
		c.drawOval (287 + i, 321, 44, 51); //body outline
		c.setColor (lightGrey);
		c.fillOval (290 + i, 272, 10, 30); //left ear
		c.fillOval (320 + i, 272, 10, 30); //right ear
		c.fillOval (288 + i, 322, 43, 50); //body
		c.setColor (Color.pink);
		c.fillOval (292 + i, 278, 6, 20); //left ear
		c.fillOval (322 + i, 278, 6, 20); //right ear
		c.setColor (Color.black);
		c.drawOval (289 + i, 291, 41, 41); //head outline
		c.setColor (lightGrey);
		c.fillOval (290 + i, 292, 40, 40); //head
		c.fillOval (287 + i, 312, 45, 20); //bottom head nose shape
		c.setColor (almostWhite);
		c.fillOval (304 + i, 312, 12, 12); //snout
		c.fillRect (307 + i, 324, 3, 5); //left tooth
		c.fillRect (310 + i, 324, 3, 5); //right tooth
		c.fillOval (299 + i, 334, 20, 27); //stomach
		c.setColor (Color.pink);
		c.fillOval (307 + i, 313, 6, 6); //nose
		c.setColor (Color.black);
		c.drawOval (287 + i, 312, 45, 20); //bottom head nose shape outline
		c.drawOval (304 + i, 312, 12, 12); //snout outline
		c.drawLine (310 + i, 319, 310 + i, 324); //nose line connnector
		c.drawOval (307 + i, 313, 6, 6); //nose outline
		c.drawRect (307 + i, 324, 3, 5); //left tooth
		c.drawRect (310 + i, 324, 3, 5); //right tooth outline
		c.drawArc (276 + i, 320, 20, 2, 0, 180); //top left whisker
		c.drawArc (276 + i, 323, 20, 2, 0, 180); //middle left whisker
		c.drawArc (276 + i, 326, 20, 2, 0, 180); //left left whisker
		c.drawArc (323 + i, 320, 20, 2, 0, 180); //top right whisker
		c.drawArc (323 + i, 323, 20, 2, 0, 180); //middle right whisker
		c.drawArc (323 + i, 326, 20, 2, 0, 180); //left right whisker
		c.setColor (Color.black);
		c.fillOval (296 + i, 303, 9, 9); //left eye
		c.fillOval (315 + i, 303, 9, 9); //right eye
		c.setColor (Color.white);
		c.fillOval (299 + i, 306, 2, 2); //left eye highlight
		c.fillOval (318 + i, 306, 2, 2); //right eye highlight
	    }
	    //used to delay the animation
	    try
	    {
		Thread.sleep (20);
	    }
	    catch (Exception e)  //catches any exceptions
	    {
	    }
	}
    }


    //This acts as the RabbitAnimation class constructor.
    public RabbitAnimation (Console con)
    {
	c = con;
    }


    //This calls the display method so that it can be outputed in the class ScreenSaver
    public void run ()
    {
	display (); //calls the method called display
    }
}
