import java.awt.*; //allows access to the java command libraries
import hsa.Console; //allows access to the Console class files
import hsa.Message; //allows access to the message class
import java.io.*; //allows access to the java io class
import javax.imageio.ImageIO; //allows access to the java image class
import java.awt.image.*; //allows access to the java image class in the java command libraries

/*
Alyssa Wang
ScreenSaver
Ms.Krasteva
January 18, 2019

There will first be an introduction which is the splashScreen. It has an animation of a animated wallpaper that consists of a bunny moving across the computer screen.
Next, there will be a main menu where you can choose to set a password, go to wallpapers, go to instructions, view your passwords, or quit the program.
You must choose one of these or else you will get an error message.
If you choose instructions, then the program will go to the instructions and then back to the actual program.
If you choose exit, then you will see who wrote the program and then it will exit the program.
If you chose the first option, you will be able to set a password and then confirm it. If the confirmed password does not match with the password entered, there will be an error message.
The password will also be saved in a file called ScreensaverPassword. If you want to change your password simply re-enter a new password and that password will be saved.
However all the passwords that you have entered will be saved into the file called ScreensaverPassword. You will have the chance to clear this file if you choose option 4 from the main menu.
If you choose to go to wallpapers, you will have the option to go back to main menu or continue looking at wallpapers.
If you continue to look at wallpapers, you can choose 1 out of the 5 to use. You will also be able to adjust the speed of the animation and how long the animation will run for.
If an incorrect value is inputed, an error message will appear and you will be able to try again.
Once the wallpaper runs, the program will continue to ask you to choose wallpapers until you want to exit by pressing 1 and returning to the main menu.
If you choose to go to view passwords, the password you have set will appear along with the ones you have set prior.
After you are finished viewing your previous passwords, you then have the option to clear the file as I mentioned before. It will ask you to enter yes or no.
If you enter yes, it will clear the file, if you enter no, it won't, and if you entered neither, you will get an error message.

Variable Declarations:
Name                        Type               Purpose
choice                      String             Stores the number the user enters during mainMenu
preSetPassword              String             Stores the password the user enters in setPassword
wallpaperDisplayChoice      String             Stores the choice for in wallpaperDisplay for whether the user would like to return to the mainMenu or continue to use wallpapers
animationSpeed              int                Stores the number the user inputs in wallpaperDisplay when asked for the animation speed which will later be used for the black box return method changeSpeed
executionTime               int                Stores the number the user inputs in wallpaperDisplay when asked for the execution time which will later be used for the black box return method changeTime
FILE_NAME                   String             Stores the name of the file that the passwords will be saved to
counter                     int                Counts the number of lines in the file
c                           Console            Reference variable which allows access to Console
*/
public class ScreenSaver // creates a class called UpperCaseConverter
{
    Console c; //class that allows the ouput window to be created
    //global variable declarations
    String choice, preSetPassword, wallpaperDisplayChoice;
    int animationSpeed, executionTime;
    int counter = 0;
    final String FILE_NAME = "ScreensaverPassword";

    /* This is the class constructor for the class ScreenSaver.
    It creates a new console and names it Screensaver.
    */
    public ScreenSaver ()  //class constructor
    {
	c = new Console (16, "Screensaver"); //creates a new console and names it Screensaver
    }


    //This method creates the title which is Screensaver
    private void title ()  //method called title
    {
	c.clear (); //clears the screen
	c.print (' ', 34);
	c.println ("Screensaver");
	c.println ();
    }


    //This method gets the user to enter a character which will then be taken by the program
    private void pauseProgram ()  // method called pauseProgram
    {
	c.println ("Press any key to continue...");
	c.getChar (); //gets the user input
    }


    /*
    This method adds the RabbitAnimation thread to ScreenSaver

    Variable Declarations:
    Name                    Type               Purpose
    r                       instance           //creates an instance variable and allows a copy of RabbitAnimation

    There is a try block which allows the method to join with the method ScreenSaver. It catches any exceptions.
    */
    public void rabbitAnimation ()  //creates an instance variable and a copy of RabbitAnimation
    {
	RabbitAnimation r = new RabbitAnimation (c); //creates a copy of the thread
	//starts the thread
	r.start ();
	//joins with the ScreenSaver method so that it executes at the same time
	try
	{
	    r.join ();
	}
	catch (InterruptedException e)  //catches any exceptions
	{
	}
    }


    /*This method tells the user what the program does so it acts as an introduction.
    It draws out the background graphics for the bunny in RabbitAnimation.

      Variable Declarations:
      Name                        Type               Purpose
      lightPink                   Color              Stores the color for the background of the Console
      lightGrey                   Color              Stores the color for the computer
      skyBlue                     Color              Stores the color for the sky
      grassGreen                  Color              Stores the color for the grass
    */
    public void splashScreen ()  //method called splashScreen
    {
	//local colour variable for the background
	Color lightPink = new Color (255, 229, 237);
	//local colour variable for the computer and the bunny
	Color lightGrey = new Color (204, 204, 204);
	//local colour variable for the sky
	Color skyBlue = new Color (175, 238, 238);
	//local colour variable for the grass
	Color grassGreen = new Color (102, 204, 0);
	//background
	c.setColor (lightPink);
	c.fillRect (0, 0, 800, 600);
	//sets the color of the background behind the text the same as the rest of the screen
	c.setTextBackgroundColor (lightPink);
	c.print (' ', 34);
	c.println ("Screensaver");
	c.println ();
	//description of program
	c.println ("Welcome to screensaver! This program will allow you to view 5 unique animated");
	c.println ("wallpapers! You will even be able to change the speed of the animation in your  wallpapers!");
	c.println ();
	c.setColor (lightGrey);
	c.fillRect (230, 200, 350, 220); //the actual screen
	c.fillRect (380, 420, 50, 70); //vertical part of the stand
	c.fillRect (320, 480, 170, 45); //horizontal base
	c.setColor (skyBlue);
	c.fillRect (250, 220, 310, 180); //wallpaper sky on the desktop
	c.setColor (grassGreen);
	c.fillRect (250, 380, 310, 20); //grass on the wallpaper on the desktop

    }


    /*
    This method creates the menu so that the user can chose what they would like to do.
    It will get their input and store it so that it can be user later.
    There is a while loop in this method which is used for error trapping the input.
    It continues running and getting the input until the user enters a correct value which is when the program will break from the while loop.
    There is also an if statement in the while loop to check if the user has entered 1,2,3,4,or 5. If it has not then the method will continue to run.
    */
    public void mainMenu ()  //method called mainMenu
    {
	c.setColor (Color.black);
	title (); //calls the title method
	c.println ();
	c.println ();
	c.println ();
	c.print ("", 35);
	c.println ("Main Menu");
	c.println ();
	c.print ("", 30);
	c.println ("1. Set a password");
	c.println ();
	c.print ("", 30);
	c.println ("2. Wallpapers");
	c.println ();
	c.print ("", 30);
	c.println ("3. Instructions");
	c.println ();
	c.print ("", 30);
	c.println ("4. View passwords");
	c.println ();
	c.print ("", 30);
	c.println ("5. Quit");
	c.println ();
	//while loop continues to loop until it breaks when a correct input is entered
	while (true)
	{
	    c.setCursor (18, 27);
	    c.println ("Please enter what you would");
	    c.print ("", 27);
	    c.println ("like to do (1,2,3,4,or 5):");
	    c.println ();
	    c.setCursor (20, 40);
	    c.drawRect (200, 80, 400, 400); //draws the rectangle
	    choice = c.readString (); //gets the user input
	    c.drawRect (200, 80, 400, 400); //draws the rectangle
	    //error traps the input if the user did not enter 1,2,3,4,or 5
	    if (!(choice.equals ("1") || choice.equals ("2") || choice.equals ("3") || choice.equals ("4") || choice.equals ("5")))
	    {
		new Message ("You must enter 1, 2, 3, 4, or 5.");
	    }
	    else
	    {
		break; //breaks from the loop if they entered a valid input
	    }
	}
	c.drawRect (200, 80, 400, 400); //draws the rectangle
    }


    /*
    This method akss the user to set a password and then confirm it.
    It will then save the password into a file called ScreensaverPassword.

    Variable Declarations:
    Name                        Type               Purpose
    confirmPassword             String             Stores the user input for the confirmed password
    output                      PrintWriter        Creates a new writer so that the passwords can be written into a file
    line                        String             Stores the lines in the file
    array                       String             Stores the passwords as elements of the array so that they can be written into the file
    input                       BufferedReader     Creates a new reader so that the program can read what's already in the file
    input2                      BufferedReader     Creates a new reader so that the program can read the file after the file is read by the BufferedReader input

    There is a while loop which helps with error trapping.
    It will continue to run until the user inputs the same confirmed password as the actual password. This is when the program will break from the while loop.
    An if statement is embedded into the while loop because it checks for whether or not the confirmed password matches with the password they just set.
    If the two passwords do match, then the program will continue to write to the file. To do this, there is a try and catch block to catch any errors that might occur when accessing a file.
    Inside this first try block, the program will access the file through a BufferedReader.
    There is a while loop after which will continue to add one to the variable counter as long as it does not hit an empty line in the file.
    There is a second try block because I wanted to read from the file again. The purpose of having this try block is the same as the previous one - to catch any exceptions.
    In the try block, I read from the file again so that I can have a for loop running through 0 to the amount of lines in the file (represented by the variable counter).
    Everytime, the program will store one line from the file into an element in the array.
    There is then another try block which does the same thing as the previous ones since it will error trap the program.
    Inside this try catch block, there is a PrintWriter so that I can write the passwords to the file.
    To do this, there is another for loop which runs from 0 to the length of the array. It will print out each password onto the file.
    */
    public void setPassword ()  //method called setPassword
    {
	//local variables
	String confirmPassword, line = "";
	BufferedReader input, input2;
	PrintWriter output;
	counter = 0;
	title (); //calls the title method
	//while loop continues to loop until it breaks when a correct input is entered
	while (true)
	{
	    c.setCursor (1, 1);
	    c.println ("Enter password:");
	    c.println ();
	    c.setCursor (2, 1);
	    preSetPassword = c.readString (); //gets the password the user enters
	    c.setCursor (3, 1);
	    c.println ("Confirm password");
	    c.println ();
	    c.setCursor (4, 1);
	    confirmPassword = c.readString (); //gets the confirmed password the user enters
	    //error traps if the confirmed password does not match with the first password the user entered
	    if (!(preSetPassword.equals (confirmPassword)))
	    {
		new Message ("Your confirmed password does not match with the password you entered. Please re-enter your password.");
	    }
	    else
	    {
		break; //breaks from the if statement
	    }
	}
	//try catch block to catch any errors such as not being able to find the file
	try
	{
	    input = new BufferedReader (new FileReader (FILE_NAME)); //creates a new BufferedReader to read the file name stored in the variable FILE_NAME
	    //while loop adds one to counter everytime there is something on a new line in the file
	    while ((line = input.readLine ()) != null)
	    {
		counter++;
	    }
	    input.close (); //closes the BufferedReader
	}
	catch (IOException e)
	{
	    c.println ("File not found"); //error traps if the file is not found
	}
	String array[] = new String [counter + 1]; //creates a string array and sets the size of it to the number of lines in the file and adds an additional space for the password the user just inputted
	//try catch block to catch any errors such as not being able to find the file
	try
	{
	    input2 = new BufferedReader (new FileReader (FILE_NAME)); //creates another BufferedReader to read the file name stored in the variable FILE_NAME
	    //for loop which runs from 0 to the number of lines in the file
	    for (int i = 0 ; i < counter ; i++)
	    {
		array [i] = input2.readLine (); //stores each line into a seperate element in the array
	    }
	    input2.close (); //closes the BufferedReader
	}
	catch (IOException e)
	{
	    c.println ("File not found"); //error traps if the file is not found
	}
	array [counter] = preSetPassword; //sets the final slot of the array to the password the user just set
	//try catch block to catch any errors such as not being able to find the file
	try
	{
	    output = new PrintWriter (new FileWriter (FILE_NAME)); //creates a new PrintWriter called output so that the program can write to the file name stored in the variable FILE_NAME
	    //for loop running from 0 to the size of the array
	    for (int i = 0 ; i < array.length ; i++)
	    {
		output.println (array [i]); //writes each element of the array into the file called ScreensaverPasswords
	    }
	    output.close (); //closes the PrintWriter
	}
	catch (IOException e)
	{
	    c.println ("File not found"); //error traps if the file is not found
	}
	c.println ("Your password has been set and it has also been saved to a file called");
	c.println ("'" + FILE_NAME + "'.");
	c.println ();
	pauseProgram (); //calls the pauseProgram method
    }


    /*
    This method asks the user to enter their password if they have set one so that they can access the wallpapers.
    If they haven't set a wallpaper it will tell the user that they will be viewing the wallpapers without a password.

    Variable Declarations:
    Name                    Type               Purpose
    matchPassword           String             Stores the password they are entering in this method and compares it with the password the user had already set

    There is an if statement searching for if the user has set a password prior.
    If they have the program will ask them to enter the password. A while loop is then used to help error trap if the password entered does not match with the password set.
    The while loop will continue running until the two passwords match.
    Inside the while loop, there is another if statement which compares the two passwords.
    If the user has not set a password prior which is the else, it will inform the user that they are continuing without a password.
    */
    public void wallpaperDisplayPassword ()  //method called wallpaperDisplayPassword
    {
	title (); //calls the title method
	String matchPassword = ""; //local variable declaration
	//if the the user has already set a password the program will ask for the user to enter the password
	if (preSetPassword != null)
	{
	    c.drawRect (200, 150, 400, 250); //draws the rectangle
	    //while loop continues to loop until it breaks when a correct input is entered
	    while (true)
	    {
		c.setCursor (13, 27);
		c.println ("Please enter your password:");
		c.println ();
		c.setCursor (14, 27);
		c.drawRect (200, 150, 400, 250); //draws the rectangle
		matchPassword = c.readString (); //gets the user input
		//if the password does not match the one they already set, then it will give an error message
		if (!(matchPassword.equals (preSetPassword)))
		{
		    new Message ("Your password is incorrect. Please try again.");
		}
		else
		{
		    break; //breaks from the if statement
		}
	    }
	}
	//if the user has not set a password yet, the program will remind the user that they are continuing without a password
	else
	{
	    c.println ("You are viewing the following wallpapers without a password.");
	    pauseProgram (); //calls the pauseProgram method
	}
    }


    /*
    This method asks the user for which wallpaper they would like to use, how fast they want the animation to run and for the length of the animation's execution.
    It then displays the wallpaper according to the user inputs.

    Variable Declarations:
    Name                        Type               Purpose
    wallpaperChoice             String             gets the user input for which wallpaper they want to use
    strAnimationSpeed           String             gets the user input for how fast the user wants the animation to run. This is a string so that it can be easily error trapped. It will then be converted into an int called animationSpeed so that it can be used in a return method later on.
    strExecutionTime            String             gets the user input for how long the user wants the animation to execute. This is a string so that it can be easily error trapped. It will then be converted into an int called executionTime so that it can be used in a return method later on.
    desertSand                  Color              Stores the color for the ground
    cactusGreen                 Color              Stores the color for the cacti
    lightBlue                   Color              Stores the color for the sky
    navyBlue                    Color              Stores the color for the train cargo boxes
    golden                      Color              Stores the color for the bottom of the cargo boxes
    windowPane                  Color              Stores the color for the windows
    trainRed                    Color              Stores the color for the train head
    grey                        Color              Stores the color for the parts of the train head
    background                  Color              Stores the color for the background of the second wallpaper
    sun                         Color              Stores the color for the sun
    lightGrey                   Color              Stores the color for the moon's base
    darkGrey                    Color              Stores the color for the crater's on the moon
    fuchsia                     Color              Stores the color for the colorful planet
    mintBlue                    Color              Stores the color for the colorful planet strips
    glass                       Color              Stores the color for the top part of the UFO
    red                         Color              Stores the color for the the bottom of the UFO
    yellow                      Color              Stores the color for the the legs of the UFO
    lightYellow                 Color              Stores the color for the stars
    pink                        Color              Stores the color for the pink tail of the stars
    green                       Color              Stores the color for the green tail of the stars
    backgroundMint              Color              Stores the color for the background of the third wallpaper
    dough                       Color              Stores the color for the dough of the cake
    chocolate                   Color              Stores the color for the decorations on the cake
    icingPink                   Color              Stores the color for the icing on the top layer of the cake
    icingWhite                  Color              Stores the color for the icing on the middle layer of the cake
    icingBrown                  Color              Stores the color for the icing on the top layer of the cake
    candlePink                  Color              Stores the color for the body of the candle
    flame                       Color              Stores the color for the fire on the candle
    balloonGreen                Color              Stores the color for the green balloons
    balloonPurple               Color              Stores the color for the periwinkle balloon
    balloonPurple               Color              Stores the color for the pink balloon
    backgroundFall              Color              Stores the color for the background of the fourth wallpaper
    grassGreen                  Color              Stores the color for the grass
    bark                        Color              Stores the color for the tree stumps
    amber                       Color              Stores the color for the leaves on the trees
    darkFur                     Color              Stores the color for the fur on the deer
    mediumFur                   Color              Stores the color for the fur on the head and the hind legs of the deer
    deerParts                   Color              Stores the color for the darkest accents of the deer such as the hooves or the antlers
    lightFur                    Color              Stores the color for the tail and the ear on the deer
    birdBody                    Color              Stores the color for the main body of the bird
    birdAccents                 Color              Stores the color for the wings and tail of the bird
    birdBeak                    Color              Stores the color for the beak of the bird
    night                       Color              Stores the color for the background on the fifth wallpaper
    nightGround                 Color              Stores the color for the hills
    groundOutline               Color              Stores the color for the outlines on the hills
    pond                        Color              Stores the color for the pond
    darkWood                    Color              Stores the color for the buildings and the tree stumps
    window                      Color              Stores the color for the window on the house
    treeGreen                   Color              Stores the color for the leaves on the trees
    moon                        Color              Stores the color for the moon
    cowWhite                    Color              Stores the color for the main body of the cow
    cowSpots                    Color              Stores the color for the spots on the cow
    cowAccents                  Color              Stores the color for the nose and the udder on the cow
    cowNostrils                 Color              Stores the color for the cow nostrils
    cowYellow                   Color              Stores the color for the cow horns

    There is a while loop which encases the entire code as the method will continue running until the user enters 1 to return back to the mainMenu.
    There is also a try and catch statement for uploading the images which error traps it in case the program is unable to find the files.
    Next there is a while loop which helps error trap the first question. It will continue running until the user enters 1 or 2 which is when it will break from the loop.
    An if statement is in the while loop to check if the user has entered 1 or 2. If it hasn't then the program will output an error message.
    Another if statement is outside of the while loop which will return to main menu if wallpaperDisplayChoice is 1.
    There is another while loop which error traps the next set of user input. Inside there is an if statement checking to see if the user entered 1,2,3,4,or 5 for the wallpaperChoice.
    If they did, the program will exit from the loop but if they didn't then the program will output an error message.
    There is another while loop which error traps the next set of user input. Inside there is an if statement checking to see if the user entered 1,2,3,4,or 5 for the animationSpeed.
    If they did, the program will exit from the loop but if they didn't then the program will output an error message.
    There is also a try catch statement in this loop to error trap any negatives.
    There is another while loop which error traps the next set of user input. Inside there is an if statement checking to see if the user entered 1,2,3,4,or 5 for the executionTime.
    If they did, the program will exit from the loop but if they didn't then the program will output an error message.
    There is also a try catch statement in this loop to error trap any negatives.
    Next there is a big if statement which has different commands for each if or else if statement.
    The first portion of this if statement is if the user chose to run the first wallpaper.
    There is a for loop in this which will run the animation for a certain amount of time that was calculated in the return method changeTime.
    Inside this for loop, is another for loop which actually makes the graphics on the wallpapers move.
    Finally, inside the first portion of this if statement is a try block to delay the animation to whatever speed was calculated in the return method changeSpeed.
    The catch will catch any exceptions so that the program won't crash.
    The next parts of the if statement are just the else if statements which do the same thing as the first portion except for different wallpapers.
    */
    public void wallpaperDisplay ()  //wallpaperDisplay method
    {
	//local variable declarations
	String strAnimationSpeed = "", strExecutionTime = "", wallpaperChoice = "";
	//color variables for the first wallpaper
	//local colour variable for the ground
	Color desertSand = new Color (244, 219, 146);
	//local colour variable for the cacti
	Color cactusGreen = new Color (147, 196, 125);
	//local colour variable for the sky
	Color lightBlue = new Color (201, 250, 255);
	//local colour variable for the train cargo boxes
	Color navyBlue = new Color (11, 83, 148);
	//local colour variable for the bottom of the cargo boxes
	Color golden = new Color (241, 194, 50);
	//local colour variable for the windows
	Color windowPane = new Color (207, 226, 243);
	//local colour variable for the train head body
	Color trainRed = new Color (204, 0, 0);
	//local colour variable for the grey parts of the train head
	Color grey = new Color (102, 102, 102);
	//color variables for the second wallpaper
	//local colour variable for the background
	Color background = new Color (7, 55, 99);
	//local colour variable for the sun
	Color sun = new Color (255, 196, 10);
	//local colour variable for the moon's base
	Color lightGrey = new Color (204, 204, 204);
	//local colour variable for the craters on the moon
	Color darkGrey = new Color (153, 153, 153);
	//local colour variable for the colorful planet
	Color fuchsia = new Color (207, 102, 162);
	//local colour variable for the colorful planet's strips and the tails of the stars
	Color mintBlue = new Color (163, 243, 237);
	//local colour variable for the top part of the UFO
	Color glass = new Color (204, 244, 255);
	//local colour variable for the bottom of the UFO
	Color red = new Color (255, 0, 0);
	//local colour variable for the legs of the UFO
	Color yellow = new Color (255, 217, 102);
	//local colour variable for the stars
	Color lightYellow = new Color (255, 252, 196);
	//local colour variable for the tails of the stars
	Color pink = new Color (255, 177, 211);
	//local colour variable for the tails of the stars
	Color green = new Color (175, 236, 144);
	//color variables for the third wallpaper
	//local colour variable for the background
	Color backgroundMint = new Color (197, 253, 255);
	//local colour variable for the cake base
	Color dough = new Color (255, 231, 171);
	//local colour variable for the chocolate on the cake
	Color chocolate = new Color (132, 69, 4);
	//local colour variable for the icing on the bottom layer of the cake
	Color icingPink = new Color (255, 183, 191);
	//local colour variable for the icing on the middle layer of cake
	Color icingWhite = new Color (255, 250, 230);
	//local colour variable for the icing on the top layer of cake
	Color icingBrown = new Color (162, 86, 1);
	//local colour variable for the body of the candle
	Color candlePink = new Color (255, 136, 150);
	//local colour variable for the fire on the candle
	Color flame = new Color (255, 217, 0);
	//local colour variable for the green balloons
	Color balloonGreen = new Color (176, 236, 126);
	//local colour variable for the periwinkle balloon
	Color balloonPurple = new Color (204, 204, 255);
	//local colour variable for the pink balloon
	Color balloonPink = new Color (255, 161, 210);
	//color variables for the fourth wallpaper
	//local colour variable for the background
	Color backgroundFall = new Color (255, 247, 183);
	//local colour variable for the grass
	Color grassGreen = new Color (151, 213, 61);
	//local colour variable for the tree stumps
	Color bark = new Color (120, 63, 4);
	//local colour variable for the leaves on the trees
	Color amber = new Color (241, 194, 50);
	//local colour variable for the fur on the deer
	Color darkFur = new Color (150, 101, 22);
	//local colour variable for the fur on the head and the hind legs of the deer
	Color mediumFur = new Color (166, 121, 60);
	//local colour variable for the darkest accents of the deer such as the hooves or the antlers
	Color deerParts = new Color (115, 77, 16);
	//local colour variable for the tail and the ear on the deer
	Color lightFur = new Color (255, 236, 175);
	//local colour variable for the main body of the bird
	Color birdBody = new Color (163, 233, 243);
	//local colour variable for the wings and tail of the bird
	Color birdAccents = new Color (70, 199, 243);
	//local colour variable for the beak of the bird
	Color birdBeak = new Color (255, 153, 0);
	//color variables for the fifth wallpaper
	//local colour variable for the background
	Color night = new Color (63, 118, 130);
	//local colour variable for the hills
	Color nightGround = new Color (136, 103, 78);
	//local colour variable for the outlines of the hills
	Color groundOutline = new Color (136, 64, 26);
	//local colour variable for the pond
	Color pond = new Color (124, 153, 181);
	//local colour variable for the buildings and the tree stumps
	Color darkWood = new Color (83, 53, 29);
	//local colour variable for the window on the house
	Color window = new Color (105, 67, 37);
	//local colour variable for the leaves on the trees
	Color treeGreen = new Color (39, 78, 19);
	//local colour variable for the moon
	Color moon = new Color (252, 255, 238);
	//local colour variable for the main body of the cow
	Color cowWhite = new Color (243, 243, 243);
	//local colour variable for the spots on the cow
	Color cowSpots = new Color (67, 67, 67);
	//local colour variable for the nose and the udder of the cow
	Color cowAccents = new Color (243, 185, 203);
	//local colour variable for the cow nostrils
	Color cowNostrils = new Color (243, 137, 149);
	//local colour variable for the cow horns
	Color cowYellow = new Color (255, 217, 102);
	while (true) //loops the entire wallpaper display until the user enters 1 to return to main menu
	{
	    title (); //calls the title method
	    c.println ();
	    c.println ();
	    c.println ();
	    c.println ();
	    c.println ();
	    c.println ();
	    c.print ("", 8);
	    c.print ("1", 16);
	    c.print ("2", 16);
	    c.print ("3", 16);
	    c.print ("4", 16);
	    c.print ("5", 16);
	    c.println ();
	    c.setColor (Color.black);
	    c.drawLine (0, 40, 800, 40); //horizontal line at the top
	    c.setColor (Color.black);
	    c.drawLine (0, 210, 800, 210); //horizontal second line at the top
	    c.setColor (Color.black);
	    c.drawRect (16, 60, 140, 110); //first outline for the first wallpaper
	    c.setColor (Color.black);
	    c.drawRect (174, 60, 140, 110); //second outline for the first wallpaper
	    c.setColor (Color.black);
	    c.drawRect (331, 60, 140, 110); //third outline for the first wallpaper
	    c.setColor (Color.black);
	    c.drawRect (488, 60, 140, 110); //fourth outline for the first wallpaper
	    c.setColor (Color.black);
	    c.drawRect (645, 60, 140, 110); //fifth outline for the first wallpaper
	    try
	    {
		//picture of the first wallpaper
		BufferedImage image1 = ImageIO.read (new File ("WallpaperOneOfficial.png"));
		c.drawImage (image1, 21, 70, null);
		//picture of the second wallpaper
		BufferedImage image2 = ImageIO.read (new File ("WallpaperTwoOfficial.png"));
		c.drawImage (image2, 179, 70, null);
		//picture of the third wallpaper
		BufferedImage image3 = ImageIO.read (new File ("WallpaperThreeOfficial.png"));
		c.drawImage (image3, 335, 70, null);
		//picture of the fourth wallpaper
		BufferedImage image4 = ImageIO.read (new File ("WallpaperFourOfficial.png"));
		c.drawImage (image4, 492, 70, null);
		//picture of the fifth wallpaper
		BufferedImage image5 = ImageIO.read (new File ("WallpaperFiveOfficial.png"));
		c.drawImage (image5, 649, 70, null);
	    }
	    //error traps if the picture is not found
	    catch (IOException e)
	    {
		new Message ("File not found :I", "hmm");
	    }

	    c.println ("1.Return to main menu");
	    c.println ("2.Continue to preview wallpaper");
	    //while loop continues to loop until it breaks when a correct input is entered
	    while (true)
	    {
		c.setCursor (13, 1);
		c.println ("Please enter 1 or 2:");
		c.println ();
		c.setCursor (14, 1);
		wallpaperDisplayChoice = c.readString (); //gets the user input
		//error traps if the user does not enter 1 or 2
		if (!(wallpaperDisplayChoice.equals ("1") || wallpaperDisplayChoice.equals ("2")))
		{
		    new Message ("You must enter 1 or 2.");
		}
		else
		{
		    break; //breaks if the user enters 1 or 2
		}
	    }
	    if (wallpaperDisplayChoice.equals ("1")) //if the user enters 1 it will return to the main menu
	    {
		return;
	    }
	    //while loop continues to loop until it breaks when a correct input is entered
	    while (true)
	    {
		c.setCursor (15, 1);
		c.println ("Which wallpaper would you like to choose (1,2,3,4,or 5):");
		c.println ();
		c.setCursor (16, 1);
		wallpaperChoice = c.readString (); //gets the user input
		//error traps if the user doesn't enter 1,2,3,4,or 5
		if (!(wallpaperChoice.equals ("1") || wallpaperChoice.equals ("2") || wallpaperChoice.equals ("3") || wallpaperChoice.equals ("4") || wallpaperChoice.equals ("5")))
		{
		    new Message ("You must enter 1, 2, 3, 4, or 5.");
		}
		else
		{
		    break; //breaks if they enter a valid number
		}
	    }
	    //while loop continues to loop until it breaks when a correct input is entered
	    while (true)
	    {
		try
		{
		    c.setCursor (17, 1);
		    c.println ("Please choose a speed to run your animation at from 1 to 5 (1 is the slowest");
		    c.println ("while 5 is the fastest):");
		    c.println ();
		    c.setCursor (19, 1);
		    strAnimationSpeed = c.readString (); //gets the user input
		    animationSpeed = Integer.parseInt (strAnimationSpeed); //parses strAnimation into an integer and calls it animationSpeed
		    //error traps if the user doesn't enter 1,2,3,4,or 5
		    if (!(strAnimationSpeed.equals ("1") || strAnimationSpeed.equals ("2") || strAnimationSpeed.equals ("3") || strAnimationSpeed.equals ("4") || strAnimationSpeed.equals ("5"))) //animationSpeed <1 && animationSpeed >5)
		    {
			new Message ("You must enter 1, 2, 3, 4, or 5.");
		    }
		    else
		    {
			break; //breaks if they enter a valid number
		    }
		}
		catch (NumberFormatException e)  //error trap if they don't enter 1,2,3,4,or 5
		{
		    new Message ("You must enter 1, 2, 3, 4, or 5.");
		}
	    }
	    //while loop continues to loop until it breaks when a correct input is entered
	    while (true)
	    {
		try
		{
		    c.setCursor (20, 1);
		    c.println ("How long would you like to run the wallpaper for from 1 to 5 (1 being the");
		    c.println ("shortest time and 5 being the longest:");
		    c.println ();
		    c.setCursor (22, 1);
		    strExecutionTime = c.readString (); //gets the user input
		    executionTime = Integer.parseInt (strExecutionTime); //parses strExecutionTime (string) into an integer and calls it executionTime
		    //error traps if the user does not enter 1,2,3,4,or 5
		    if (!(strExecutionTime.equals ("1") || strExecutionTime.equals ("2") || strExecutionTime.equals ("3") || strExecutionTime.equals ("4") || strExecutionTime.equals ("5")))
		    {
			new Message ("You must enter 1,2,3,4, or 5.");
		    }
		    else
		    {
			break; //breaks if the user enters 1,2,3,4,or 5
		    }
		}
		catch (NumberFormatException e)  //error traps if the user does not enter 1,2,3,4,or 5
		{
		    new Message ("You must enter 1,2,3,4, or 5.");
		}
	    }
	    c.println ();
	    if (wallpaperChoice.equals ("1")) //executes the first wallpaper if the user inputs 1 for wallpaperChoice
	    {
		//sky
		c.setColor (lightBlue);
		c.fillRect (0, 0, 800, 600);
		//sandy ground
		c.setColor (desertSand);
		c.fillRect (0, 435, 800, 165);
		//clouds
		c.setColor (Color.white);
		//first cloud (most left)
		c.fillOval (80, 50, 90, 50); //most left oval bottom
		c.fillOval (130, 50, 70, 60); //right circle bottom
		c.fillOval (110, 20, 45, 45); //left top small circle
		c.fillOval (150, 23, 40, 40); //right top small circle
		//second cloud (middle)
		c.fillOval (340, 67, 90, 50); //long oval at the bottom
		c.fillOval (380, 43, 30, 30); //small circle
		c.fillOval (405, 47, 45, 45); //most right circle
		//third cloud (most right)
		c.fillOval (600, 45, 90, 80); //largest circle
		c.fillOval (590, 37, 43, 43); //smallest circle
		c.fillOval (660, 33, 60, 60); //medium circle
		//cacti
		c.setColor (cactusGreen);
		//left cactus
		c.fillOval (50, 460, 87, 87); //largest bottom circle
		c.fillOval (105, 445, 30, 30); //small circle
		//right cactus
		c.fillOval (690, 455, 25, 95); //long oval
		c.fillOval (670, 500, 30, 17); //left oval on the body
		c.fillOval (700, 469, 40, 16); //right oval on the body
		for (int h = 0 ; h <= (changeTime (executionTime)) ; h++) //for loop looping the animation for the number of times the return method changeTime calculated based on the user input
		{
		    for (int i = 0 ; i < 1400 ; i++) //for loop making the train move across the screen
		    {
			synchronized (c)
			{
			    //train
			    c.setColor (lightBlue);
			    c.fillRect (-601 + i, 236, 594, 199); //erase
			    //train head
			    c.setColor (trainRed);
			    c.fillRect (-175 + i, 245, 48, 150); //long rectangle left part
			    c.setColor (grey);
			    c.fillRect (-127 + i, 324, 107, 95); //two strips between red parts of the train
			    c.fillOval (-181 + i, 236, 60, 25); //top oval part
			    c.fillRect (-68 + i, 294, 15, 30); //chimney vertical part
			    c.fillRect (-75 + i, 286, 29, 15); //chimney horizontal part
			    c.setColor (trainRed);
			    c.fillRect (-113 + i, 324, 93, 84); //right red part of main body
			    //blue cargo
			    c.setColor (navyBlue);
			    c.fillRect (-591 + i, 245, 159, 138); //left
			    c.fillRect (-391 + i, 245, 159, 138); //right
			    //windows
			    c.setColor (windowPane);
			    //left cargo
			    c.fillRect (-577 + i, 260, 35, 30); //left window
			    c.fillRect (-529 + i, 260, 35, 30); //middle window
			    c.fillRect (-481 + i, 260, 35, 30); //right window
			    //right cargo
			    c.fillRect (-377 + i, 260, 35, 30); //left window
			    c.fillRect (-329 + i, 260, 35, 30); //middle window
			    c.fillRect (-281 + i, 260, 35, 30); //right window
			    //yellow bottom
			    c.setColor (golden);
			    c.fillRect (-594 + i, 383, 165, 15); //left
			    c.fillRect (-394 + i, 383, 165, 15); //right
			    //wheels
			    //left most cargo train
			    c.setColor (Color.black);
			    c.fillOval (-581 + i, 395, 40, 40); //left wheel
			    c.fillOval (-531 + i, 395, 40, 40); //middle wheel
			    c.fillOval (-481 + i, 395, 40, 40); //right wheel
			    //right most cargo train
			    c.fillOval (-381 + i, 395, 40, 40); //left wheel
			    c.fillOval (-331 + i, 395, 40, 40); //middle wheel
			    c.fillOval (-281 + i, 395, 40, 40); //right wheel
			    //train head
			    c.fillOval (-181 + i, 375, 60, 60); //big wheel
			    c.fillOval (-116 + i, 395, 40, 40); //left wheel
			    c.fillOval (-71 + i, 395, 40, 40); //right wheel
			    //connecting pieces
			    c.fillRect (-432 + i, 369, 41, 10); //in between cargo boxes
			    c.fillRect (-232 + i, 369, 57, 10); //in between right cargo box and train head
			    //triangle piece at the front of the train
			    int x[] = { - 20 + i, -20 + i, 0 + i}; //list of all x-coordintes
			    int y[] = {395, 419, 419}; // list of all y-coordinates
			    c.fillPolygon (x, y, 3);
			}
			//used to delay the animation
			try
			{
			    Thread.sleep (changeSpeed (animationSpeed)); //delays the animation with the number calculated in the return method changeSpeed based on the user input
			}
			catch (Exception e)  //catches any exceptions
			{
			}
		    }
		}
	    }
	    else if (wallpaperChoice.equals ("2")) //executes the second wallpaper if the user inputs 2 for wallpaperChoice
	    {
		synchronized (c)
		{
		    //space background
		    c.setColor (background);
		    c.fillRect (0, 0, 800, 600);
		    //sun
		    c.setColor (sun);
		    c.fillOval (635, 390, 250, 250);
		    //moon
		    c.setColor (lightGrey);
		    c.fillOval (30, 380, 150, 150); //base
		    c.setColor (darkGrey);
		    c.fillOval (43, 417, 50, 35); //top left crater
		    c.fillOval (43, 477, 60, 30); //bottom left crater
		    c.fillOval (120, 408, 30, 30); //top right crater
		    c.fillOval (130, 467, 30, 30); //bottom right crater
		    //colorful planet
		    c.setColor (fuchsia);
		    c.fillOval (605, 45, 165, 165); //base
		    c.setColor (mintBlue);
		    c.fillRect (633, 62, 109, 10); //bottom strip
		    c.fillRect (611, 92, 152, 10); //top of the middle strip
		    c.fillRect (605, 122, 165, 10); //middle strip
		    c.fillRect (611, 152, 153, 10); //bottom of the middle strip
		    c.fillRect (633, 182, 109, 10); //bottom strip
		    c.setColor (background);
		    c.drawOval (604, 44, 166, 166); //outline
		    c.drawOval (604, 43, 167, 167); //outline
		    c.drawOval (603, 42, 168, 168); //outline
		    c.drawOval (602, 41, 169, 169); //outline
		    c.drawOval (603, 40, 170, 170); //outline
		    c.drawOval (601, 39, 171, 171); //outline
		    c.drawOval (604, 38, 172, 172); //outline
		    //UFO
		    c.setColor (glass);
		    c.fillArc (65, 55, 70, 90, 0, 180); //top part
		    c.setColor (red);
		    c.fillRect (65, 95, 70, 13); //rectangular part of the red base
		    //right traingle piece
		    int x[] = {135, 135, 145}; //list of all x-coordintes
		    int y[] = {95, 108, 108}; // list of all y-coordinates
		    c.fillPolygon (x, y, 3);
		    //left traingle piece
		    int a[] = {65, 65, 55}; //list of all x-coordintes
		    int b[] = {95, 108, 108}; // list of all y-coordinates
		    c.fillPolygon (a, b, 3);
		    //UFO legs
		    c.setColor (yellow);
		    c.fillRect (69, 108, 7, 10);
		    c.fillRect (88, 108, 7, 10);
		    c.fillRect (107, 108, 7, 10);
		    c.fillRect (124, 108, 7, 10);
		    //white stars
		    c.setColor (Color.white);
		    c.fillOval (50, 30, 10, 10); //top left
		    c.fillOval (260, 50, 10, 10); //top middle
		    c.fillOval (700, 20, 10, 10); //top right
		    c.fillOval (16, 397, 10, 10); //bottom left
		    c.fillOval (270, 410, 10, 10); //bottom middle
		    c.fillOval (663, 400, 10, 10); //bottom right
		}
		for (int h = 0 ; h <= (changeTime (executionTime)) ; h++) //for loop looping the animation for the number of times the return method changeTime calculated based on the user input
		{
		    //shooting stars
		    //first round of animations
		    for (int i = 0 ; i < 935 ; i++)
		    {
			synchronized (c)
			{
			    c.setColor (background);
			    c.fillRect (-128 + i, 330 - (i / 2 + 50), 122, 76); //erase for the most left star going diagonally
			    c.fillRect (811 - i, 250, 122, 75); //erase for the most right star going horizontally left
			    c.setColor (mintBlue);
			    c.fillOval (-127 + i, 380 - (i / 2 + 50), 80, 15); //most left star's mint blue streak
			    c.fillOval (852 - i, 300, 80, 15); //most right star's mint blue streak
			    c.setColor (pink);
			    c.fillOval (-127 + i, 365 - (i / 2 + 50), 80, 15); //most left star's pink streak
			    c.fillOval (852 - i, 285, 80, 15); //most right star's pink streak
			    c.setColor (green);
			    c.fillOval (-127 + i, 350 - (i / 2 + 50), 80, 15); //most left star's green strip
			    c.fillOval (852 - i, 270, 80, 15); //most right star's green streak
			    c.setColor (lightYellow);
			    c.fillStar (-80 + i, 330 - (i / 2 + 50), 75, 75); //most left star
			    c.fillStar (810 - i, 250, 75, 75); //most right star
			}
			//used to delay the animation
			try
			{
			    Thread.sleep (changeSpeed (animationSpeed)); //delays the animation with the number calculated in the return method changeSpeed based on the user input
			}
			catch (Exception e)  //catches any exceptions
			{
			}
		    }
		    //second round with the star that goes straight up
		    for (int i2 = 0 ; i2 < 760 ; i2++)
		    {
			synchronized (c)
			{
			    c.setColor (background);
			    c.fillRect (353, 648 - i2, 122, 75); //erase
			    c.setColor (mintBlue);
			    c.fillOval (353, 697 - i2, 80, 15); //mint streak
			    c.setColor (pink);
			    c.fillOval (353, 682 - i2, 80, 15); //pink streak
			    c.setColor (green);
			    c.fillOval (353, 667 - i2, 80, 15); //green streak
			    c.setColor (lightYellow);
			    c.fillStar (400, 647 - i2, 75, 75); //bottom star
			}
			//used to delay the animation
			try
			{
			    Thread.sleep (changeSpeed (animationSpeed)); //delays the animation with the number calculated in the return method changeSpeed based on the user input
			}
			catch (Exception e)  //catches any exceptions
			{
			}
		    }
		}

	    }

	    else if (wallpaperChoice.equals ("3")) //executes the third wallpaper if the user inputs 3 for wallpaperChoice
	    {
		//background
		c.setColor (backgroundMint);
		c.fillRect (0, 0, 800, 600);
		//cake
		c.setColor (dough);
		c.fillRect (267, 385, 266, 125); //bottom layer
		c.setColor (chocolate);
		c.fillOval (267, 365, 266, 40); //bottom layer chocolate frosting
		c.setColor (dough);
		c.fillRect (302, 285, 196, 100); //middle layer
		c.setColor (chocolate);
		c.fillOval (302, 270, 196, 30); //middle layer chocolate frosting
		c.setColor (dough);
		c.fillRect (332, 215, 136, 70); //top layer
		c.setColor (chocolate);
		c.fillOval (332, 202, 136, 26); //middle layer chocolate frosting
		//decorations on the cake
		c.setColor (icingPink);
		c.fillRect (287, 410, 50, 95); //left most pink rectangle
		c.fillRect (374, 410, 50, 95); //middle pink rectangle
		c.fillRect (461, 410, 50, 95); //right most pink rectangle
		c.setColor (icingWhite);
		c.fillRect (302, 325, 196, 30); //white filling in the middle layer
		c.setColor (icingBrown);
		c.fillOval (347, 240, 25, 30); //left most circle on the top layer of cake
		c.fillOval (389, 240, 25, 30); //middle circle on the top layer of cake
		c.fillOval (431, 240, 25, 30); //right most circle on the top layer of cake
		c.setColor (candlePink);
		c.fillRect (391, 172, 20, 40); //candle
		c.setColor (flame);
		c.fillOval (391, 155, 20, 27); //flame on the candle
		for (int o = 0 ; o <= (changeTime (executionTime)) ; o++) //for loop looping the animation for the number of times the return method changeTime calculated based on the user input
		{
		    //first round of animations
		    for (int i = 0 ; i < 935 ; i++)
		    {
			synchronized (c)
			{
			    c.setColor (backgroundMint);
			    c.fillRect (60, 602 - i, 90, 530); //erase for the left green balloon
			    c.fillRect (791 - i / 3, 603 - i, 130, 330); //erase for the pink balloon
			    //left green balloon
			    c.setColor (balloonGreen);
			    c.fillOval (60, 601 - i, 90, 120); //round part
			    //triangle piece at the bottom of the balloon
			    int x[] = {105, 115, 95}; //list of all x-coordintes
			    int y[] = {711 - i, 731 - i, 731 - i}; // list of all y-coordinates
			    c.fillPolygon (x, y, 3);
			    c.setColor (Color.black);
			    c.drawLine (105, 731 - i, 105, 881 - i); //string of the balloon
			    //pink balloon
			    c.setColor (balloonPink);
			    c.fillOval (790 - i / 3, 601 - i, 130, 130); //actual balloon part
			    //triangle piece at the bottom of the balloon
			    int a[] = {856 - i / 3, 866 - i / 3, 846 - i / 3}; //list of all x-coordintes
			    int h[] = {722 - i, 742 - i, 742 - i}; // list of all y-coordinates
			    c.fillPolygon (a, h, 3);
			    c.setColor (Color.black);
			    c.drawLine (856 - i / 3, 742 - i, 906 - i / 3, 931 - i); //string of the balloon
			}
			//delays the animation
			try
			{
			    Thread.sleep (changeSpeed (animationSpeed)); //delays the animation with the number calculated in the return method changeSpeed based on the user input
			}
			catch (Exception e)  //catches any exceptions
			{
			}
		    }
		    //second batch of animations
		    for (int i = 0 ; i < 900 ; i++)
		    {
			synchronized (c)
			{
			    c.setColor (backgroundMint);
			    c.fillRect (-136 + (i / 2 - 20), 602 - i, 120, 327);
			    c.fillRect (660, 502 - i, 90, 275);
			    //purple balloon
			    c.setColor (balloonPurple);
			    c.fillStar (-135 + (i / 2 - 20), 601 - i, 120, 120); //actual balloon part
			    //triangle piece at the bottom of the balloon
			    int a[] = { - 75 + (i / 2 - 20), -65 + (i / 2 - 20), -85 + (i / 2 - 20) }; //list of all x-coordintes
			    int b[] = {688 - i, 708 - i, 708 - i}; // list of all y-coordinates
			    c.fillPolygon (a, b, 3);
			    //string of the balloon
			    c.setColor (Color.black);
			    c.drawArc (-100 + (i / 2 - 20), 708 - i, 50, 70, 270, 180); //first arc
			    c.drawArc (-100 + (i / 2 - 20), 778 - i, 60, 100, 90, 180); //second arc
			    //right green balloon
			    c.setColor (balloonGreen);
			    c.fillOval (660, 501 - i, 90, 110); //actual balloon part
			    //triangle piece at the bottom of the balloon
			    int t[] = {705, 715, 695}; //list of all x-coordintes
			    int z[] = {601 - i, 621 - i, 621 - i}; // list of all y-coordinates
			    c.fillPolygon (t, z, 3);
			    //string
			    c.setColor (Color.black);
			    c.drawArc (680, 621 - i, 50, 50, 270, 180); //first arc
			    c.drawArc (690, 671 - i, 30, 50, 90, 180); //second arc
			    c.drawArc (700, 721 - i, 5, 10, 270, 180); //third mini arc
			    c.drawLine (703, 731 - i, 680, 771 - i); //line at the bottom
			}
			//delays the animation
			try
			{
			    Thread.sleep (changeSpeed (animationSpeed)); //delays the animation with the number calculated in the return method changeSpeed based on the user input
			}
			catch (Exception e)  //catches any exceptions
			{
			}
		    }
		}
	    }

	    else if (wallpaperChoice.equals ("4")) //executes the fourth wallpaper if the user inputs 4 for wallpaperChoice
	    {
		//background
		c.setColor (backgroundFall);
		c.fillRect (0, 0, 800, 600);
		//grass
		c.setColor (grassGreen);
		c.fillRect (0, 465, 800, 135);
		//bark
		c.setColor (bark);
		c.fillRect (0, 270, 100, 195); //left tree
		c.fillRect (700, 270, 100, 195); //right tree
		//leaves on the tree
		c.setColor (amber);
		//left tree
		c.fillOval (7, 220, 100, 105); //middle circle on the bottom
		c.fillOval (80, 240, 100, 100); //most right one on the bottom
		c.fillOval (-80, 230, 100, 100); //most left one on the bottom
		c.fillOval (-30, 158, 100, 100); //left top one
		c.fillOval (60, 170, 100, 100); //right top one
		//right tree
		c.fillOval (730, 230, 100, 100); //bottom right circle
		c.fillOval (630, 245, 110, 100); //bottom left circle
		c.fillOval (585, 190, 100, 100); //top left most circle
		c.fillOval (640, 157, 100, 100); //middle top circle
		c.fillOval (720, 160, 100, 100); //right most top circle
		c.fillOval (670, 200, 100, 100); //filler circle in the middle
		//clouds
		c.setColor (Color.white);
		//left cloud
		c.fillOval (100, 53, 120, 60); //large oval at the bottom
		c.fillOval (65, 53, 60, 60); //left bottom circle
		c.fillOval (180, 53, 60, 60); //right bottom circle
		c.fillOval (100, 22, 60, 60); //top left circle
		c.fillOval (155, 3, 70, 70); //top right circle
		//middle cloud
		c.fillOval (345, 53, 100, 60); //big oval most left
		c.fillOval (415, 53, 60, 60); //middle small circle
		c.fillOval (425, 40, 100, 50); //right most large oval
		c.fillOval (417, 22, 70, 70); //top circle
		//right cloud
		c.fillOval (625, 20, 80, 80); //big middle cloud
		c.fillOval (610, 36, 40, 40); //small top circle on the left
		c.fillOval (615, 64, 40, 40); //small bottom circle on the left
		c.fillOval (585, 50, 60, 45); //most left circle
		c.fillOval (675, 50, 60, 50); //second most right circle
		c.fillOval (717, 53, 35, 35); //most right circle
		//deer
		c.setColor (darkFur);
		c.fillOval (277, 365, 256, 100); //main body
		c.fillOval (480, 395, 60, 80); //front part of body
		c.fillRect (410, 455, 40, 10); //filler piece in the center of body
		c.fillRoundRect (445, 450, 100, 30, 7, 7); //front legs
		c.setColor (mediumFur);
		c.fillOval (267, 375, 120, 90); //lighter part of the body(butt)
		c.fillRoundRect (270, 450, 130, 30, 7, 7); //hind legs
		c.setColor (deerParts);
		c.fillArc (365, 448, 50, 65, 0, 180); //hind leg hoof
		c.fillArc (440, 448, 50, 65, 0, 180); //front leg hoof
		//left antler
		c.fillRect (475, 372, 7, 40); //bottom part
		c.fillRect (445, 372, 30, 7); //bottom horizontal part
		c.fillRect (445, 322, 7, 50); //long vertical part
		c.fillRect (445, 342, 35, 7); //horizontal top part
		//right antler
		c.fillRect (500, 372, 7, 40); //bottom part
		c.fillRect (500, 372, 35, 7); //bottom horizontal part
		c.fillRect (528, 322, 7, 50); //long vertical part
		c.fillRect (500, 342, 35, 7); //horizontal top part
		c.setColor (mediumFur);
		c.fillOval (457, 400, 65, 65); //head
		c.fillOval (437, 440, 40, 25); //snout
		c.fillOval (510, 410, 30, 17); //ear
		c.setColor (deerParts);
		c.fillOval (433, 447, 13, 9); //nose
		c.fillArc (477, 425, 14, 9, 210, 180); //eye
		c.setColor (mediumFur);
		c.fillArc (478, 425, 12, 7, 210, 180); //eraser part of eye
		c.setColor (lightFur);
		c.fillOval (520, 416, 14, 8); //ear accent
		c.fillOval (250, 413, 35, 22); //tail
		for (int o = 0 ; o <= (changeTime (executionTime)) ; o++) //for loop looping the animation for the number of times the return method changeTime calculated based on the user input
		{
		    //first batch of animations
		    for (int i = 0 ; i < 870 ; i++)
		    {
			synchronized (c)
			{
			    //bird
			    c.setColor (backgroundFall);
			    c.fillRect (-64 + i, 112, 62, 45); //erase for the bird
			    c.setColor (birdBody);
			    c.fillOval (-58 + i, 122, 50, 35); //main body
			    c.setColor (birdAccents);
			    c.fillOval (-42 + i, 132, 16, 11); //base of wings
			    //triangle piece for the left wing
			    int x[] = { - 49 + i, -27 + i, -42 + i}; //list of all x-coordintes
			    int y[] = {112, 138, 139}; // list of all y-coordinates
			    c.fillPolygon (x, y, 3);
			    //triangle piece for the right wing
			    int a[] = { - 39 + i, -26 + i, -40 + i}; //list of all x-coordintes
			    int b[] = {113, 138, 139}; // list of all y-coordinates
			    c.fillPolygon (a, b, 3);
			    //triangle piece for the tail
			    int q[] = { - 63 + i, -63 + i, -53 + i}; //list of all x-coordintes
			    int f[] = {133, 145, 139}; // list of all y-coordinates
			    c.fillPolygon (q, f, 3);
			    c.setColor (Color.black);
			    c.fillOval (-22 + i, 136, 8, 8); //eye
			    c.setColor (birdBeak);
			    //triangle piece for the beak
			    int h[] = { - 9 + i, -9 + i, -1 + i}; //list of all x-coordintes
			    int k[] = {135, 143, 139}; // list of all y-coordinates
			    c.fillPolygon (h, k, 3);
			    //fall leaves
			    c.setColor (bark);
			    //right tree
			    c.fillRect (30, 323 + i / 9, 40, 40); //erase for leaf on the trunk
			    c.fillOval (75, 317 + i / 7, 15, 20); //erase for the oval on the bark
			    //left tree
			    c.fillMapleLeaf (710, 327 + i / 9, 40, 40); //erase for leaf on the trunk
			    c.setColor (backgroundFall);
			    //left tree
			    c.fillMapleLeaf (128, 334 + i / 9, 40, 40); //erase for second most right leaf
			    c.fillMapleLeaf (162 + i / 30, 307 + i / 8 + 9, 43, 42); //most right leaf
			    //right tree
			    c.fillMapleLeaf (579, 275 + i / 6 + 7, 40, 40); //erase for highest leaf
			    c.fillOval (670, 343 + i / 9 + 5, 15, 20); //erase for oval closest to the trunk
			    //falling leaves from left tree
			    c.setColor (amber);
			    //maple leaves
			    c.fillMapleLeaf (30, 323 + i / 9, 40, 40); //leaf on the trunk
			    c.fillMapleLeaf (128, 335 + i / 9, 40, 40); //second most right leaf
			    c.fillMapleLeaf (164 + i / 30, 310 + i / 8 + 9, 40, 40); //most right leaf
			    //ovals
			    c.fillOval (75, 318 + i / 7, 15, 20); //oval on the trunk
			    //falling leaves from the right tree
			    //maple leaves
			    c.fillMapleLeaf (710, 328 + i / 9, 40, 40); //leaf on the trunk
			    c.fillMapleLeaf (579, 276 + i / 6 + 7, 40, 40); //highest leaf
			    //ovals
			    c.fillOval (670, 344 + i / 9 + 5, 15, 20); //oval closest to the trunk
			}
			//delays the animation
			try
			{
			    Thread.sleep (changeSpeed (animationSpeed)); //delays the animation with the number calculated in the return method changeSpeed based on the user input
			}
			catch (Exception e)  //catches any exceptions
			{
			}
		    }
		    c.setColor (bark);
		    //left tree
		    c.fillMapleLeaf (30, 419, 40, 40); //leaf on the trunk
		    c.fillOval (75, 442, 15, 20); //oval on the trunk
		    //right tree
		    c.fillMapleLeaf (710, 424, 40, 40); //leaf on the trunk
		    c.setColor (backgroundFall);
		    //left tree
		    c.fillMapleLeaf (128, 431, 40, 40); //second most right leaf
		    c.fillMapleLeaf (192, 427, 40, 40); //most right leaf
		    //right tree
		    c.fillOval (670, 445, 15, 20); //oval closest to the trunk
		    c.fillMapleLeaf (579, 427, 40, 40); //highest leaf
		    c.setColor (grassGreen);
		    //left tree
		    c.fillRect (120, 465, 100, 20);
		    c.fillRect (590, 465, 50, 20);
		    for (int i = 0 ; i < 108 ; i++)
		    {
			synchronized (c)
			{
			    //left tree leaves
			    c.setColor (backgroundFall);
			    c.fillMapleLeaf (157 + i / 3, 205 + i / 2, 42, 42); //highest leaf
			    c.fillOval (113, 338 + i, 15, 20); //oval closest to the tree
			    c.fillOval (154, 328 + i + 10, 15, 20); //highest oval
			    c.setColor (amber);
			    c.fillMapleLeaf (161 + i / 3, 210 + i / 2, 40, 40); //highest leaf
			    c.fillOval (113, 339 + i, 15, 20); //oval closest to the tree
			    c.fillOval (154, 329 + i + 10, 15, 20); //highest oval
			}
			//delays animation
			try
			{
			    Thread.sleep (changeSpeed (animationSpeed)); //delays the animation with the number calculated in the return method changeSpeed based on the user input
			}
			catch (Exception e)  //catches any exceptions
			{
			}
		    }
		    c.setColor (backgroundFall);
		    c.fillOval (113, 446, 15, 20); //oval closest to the tree
		    c.fillOval (154, 446, 15, 20); //highest oval
		    for (int i = 0 ; i < 170 ; i++)
		    {
			synchronized (c)
			{
			    c.setColor (backgroundFall);
			    //left tree erase
			    c.fillMapleLeaf (197, 260 + i, 40, 40); //highest leaf
			    //right tree erase
			    c.fillMapleLeaf (625, 330 + i / 2 + 10, 40, 40); //leaf
			    c.fillOval (600, 281 + i / 2 + 79, 15, 20); //highest oval
			    c.setColor (bark);
			    c.fillOval (770, 329 + i / 2 + 31, 15, 20); //oval on the trunk
			    c.setColor (amber);
			    //left tree
			    c.fillMapleLeaf (197, 261 + i, 40, 40); //highest leaf
			    //right tree
			    c.fillOval (770, 330 + i / 2 + 31, 15, 20); //oval on the trunk
			    c.fillMapleLeaf (625, 331 + i / 2 + 10, 40, 40); //leaf
			    c.fillOval (600, 282 + i / 2 + 79, 15, 20); //highest oval
			}
			//delays animation
			try
			{
			    Thread.sleep (changeSpeed (animationSpeed)); //delays the animation with the number calculated in the return method changeSpeed based on the user input
			}
			catch (Exception e)  //catches any exceptions
			{
			}
		    }
		    c.setColor (backgroundFall);
		    c.fillMapleLeaf (197, 430, 40, 40); //highest leaf
		    c.fillMapleLeaf (625, 425, 40, 40); //leaf
		    c.fillOval (600, 445, 15, 20); //highest oval
		    c.setColor (bark);
		    c.fillOval (770, 445, 15, 20); //oval on the trunk
		    c.setColor (grassGreen);
		    c.fillRect (200, 465, 50, 10);
		}
	    }

	    else if (wallpaperChoice.equals ("5")) //executes the fifth wallpaper if the user inputs 5 for wallpaperChoice
	    {
		//background
		c.setColor (night);
		c.fillRect (0, 0, 800, 600);
		c.setColor (nightGround);
		c.fillOval (0, 360, 380, 300); //hill with the 4 trees
		c.setColor (groundOutline);
		c.drawOval (0, 360, 380, 300); //outline of hill with the 4 trees
		c.setColor (nightGround);
		c.fillOval (-130, 385, 360, 300); //most left hill
		c.setColor (groundOutline);
		c.drawOval (-130, 385, 360, 300); //outline of the most left hill
		c.setColor (nightGround);
		c.fillOval (130, 420, 450, 300); //hill next to the hill on the most right
		c.setColor (groundOutline);
		c.drawOval (130, 420, 450, 300); //outline of hill next to the hill on the most right
		c.setColor (nightGround);
		c.fillOval (400, 380, 600, 400); //hill on the most right
		c.setColor (groundOutline);
		c.drawOval (400, 380, 600, 400); //outline of hill on the most right
		//pond
		c.setColor (pond);
		c.fillOval (650, 475, 210, 130);
		//cabin
		c.setColor (darkWood);
		c.fillRect (590, 350, 140, 90); //base of cabin
		c.setColor (Color.black);
		c.drawRect (590, 350, 140, 90); //outline of base of cabin
		//roof of cabin
		c.setColor (darkWood);
		c.fillRect (580, 305, 160, 50); //rectangle portrion
		//left triangle
		int x[] = {580, 580, 560}; //list of all x-coordintes
		int y[] = {305, 355, 355}; // list of all y-coordinates
		c.fillPolygon (x, y, 3);
		//left triangle
		int a[] = {740, 740, 760}; //list of all x-coordintes
		int b[] = {305, 355, 355}; // list of all y-coordinates
		c.fillPolygon (a, b, 3);
		//outline of the roof
		c.setColor (Color.black);
		c.drawLine (560, 355, 760, 355); //bottom outline
		c.drawLine (580, 305, 740, 305); //top outline
		c.drawLine (560, 355, 580, 305); //left slant outline
		c.drawLine (740, 305, 760, 355); //right slant outline
		//windows
		c.setColor (window);
		c.fillRect (613, 370, 32, 25); //left window
		c.fillRect (676, 370, 32, 25); //right window
		c.setColor (Color.black);
		c.drawRect (613, 370, 32, 25); //outline for the left window
		c.drawRect (676, 370, 32, 25); //outline for the right window
		//tall silo
		c.setColor (darkWood);
		c.fillRect (40, 280, 70, 150); //base of silo
		c.setColor (Color.black);
		c.drawRect (40, 280, 70, 150); //outline of the base of silo
		//roof of the silo
		c.setColor (darkWood);
		int e2[] = {75, 20, 130}; //list of all x-coordintes
		int f[] = {220, 295, 295}; // list of all y-coordinates
		c.fillPolygon (e2, f, 3);
		c.setColor (Color.black);
		int g[] = {75, 20, 130}; //list of all x-coordintes
		int h[] = {220, 295, 295}; // list of all y-coordinates
		c.drawPolygon (g, h, 3);
		//most left pine tree
		//highest triangle
		c.setColor (treeGreen);
		int a1[] = {375, 355, 395}; //list of all x-coordintes
		int j[] = {470, 500, 500}; // list of all y-coordinates
		c.fillPolygon (a1, j, 3);
		//middle triangle
		int k[] = {375, 350, 400}; //list of all x-coordintes
		int l[] = {485, 525, 525}; // list of all y-coordinates
		c.fillPolygon (k, l, 3);
		//bottom triangle
		int m[] = {375, 313, 437}; //list of all x-coordintes
		int n[] = {505, 600, 600}; // list of all y-coordinates
		c.fillPolygon (m, n, 3);
		//middle pine tree
		//highest triangle
		int o[] = {420, 395, 445}; //list of all x-coordintes
		int p[] = {455, 495, 495}; // list of all y-coordinates
		c.fillPolygon (o, p, 3);
		//middle triangle
		int q[] = {420, 390, 450}; //list of all x-coordintes
		int r[] = {475, 525, 525}; // list of all y-coordinates
		c.fillPolygon (q, r, 3);
		//bottom triangle
		int s[] = {420, 345, 495}; //list of all x-coordintes
		int t[] = {500, 600, 600}; // list of all y-coordinates
		c.fillPolygon (s, t, 3);
		//most right pine tree
		//highest triangle
		int u[] = {465, 430, 500}; //list of all x-coordintes
		int v[] = {462, 517, 517}; // list of all y-coordinates
		c.fillPolygon (u, v, 3);
		//bottom triangle
		int w[] = {465, 380, 550}; //list of all x-coordintes
		int z[] = {505, 600, 600}; // list of all y-coordinates
		c.fillPolygon (w, z, 3);
		//round trees in the back
		//trunks
		c.setColor (darkWood);
		c.fillRect (158, 352, 7, 18); //trunk for the most left tree
		c.fillRect (179, 356, 7, 20); //trunk for the second most left tree
		c.fillRect (199, 350, 7, 18); //trunk for the second most right tree
		c.fillRect (218, 360, 8, 15);
		//leaves
		c.setColor (treeGreen);
		c.fillOval (150, 330, 23, 25); //most left tree
		c.fillOval (171, 336, 23, 25); //second most left tree
		c.fillOval (191, 328, 23, 25); //second most right tree
		c.fillOval (210, 340, 23, 25); //most right tree
		//moon
		c.setColor (moon);
		c.fillOval (414, 195, 105, 100);
		for (int H = 0 ; H <= (changeTime (executionTime)) ; H++) //for loop looping the animation for the number of times the return method changeTime calculated based on the user input
		{
		    //cow
		    for (int i = 0 ; i < 1100 ; i++)
		    {
			synchronized (c)
			{
			    c.setColor (night);
			    c.fillRect (-278 + i, 18, 276, 153); //erase
			    c.setColor (cowAccents);
			    c.fillOval (-189 + i, 113, 65, 50); //udder
			    c.fillOval (-181 + i, 154, 10, 15); //most left teat
			    c.fillOval (-160 + i, 156, 10, 15); //middle teat
			    c.fillOval (-143 + i, 154, 10, 15); //most right teat
			    c.setColor (cowYellow);
			    //most left horn
			    int g1[] = { - 56 + i, -46 + i, -64 + i}; //list of all x-coordintes
			    int h1[] = {18, 50, 50}; // list of all y-coordinates
			    c.fillPolygon (g1, h1, 3);
			    //most left horn
			    int i1[] = { - 33 + i, -23 + i, -41 + i}; //list of all x-coordintes
			    int j1[] = {18, 50, 50}; // list of all y-coordinates
			    c.fillPolygon (i1, j1, 3);
			    c.setColor (cowWhite);
			    c.fillOval (-251 + i, 50, 200, 90); //main body
			    c.fillOval (-76 + i, 30, 62, 80); //head
			    c.fillOval (-88 + i, 40, 27, 13); //left ear
			    c.fillOval (-28 + i, 40, 27, 13); //right ear
			    c.fillRect (-271 + i, 79, 30, 10); //white part of tail
			    //most left leg of the cow
			    int z1[] = { - 194 + i, -185 + i, -255 + i, -263 + i}; //list of all x-coordintes
			    int b1[] = {95, 108, 150, 137}; // list of all y-coordinates
			    c.fillPolygon (z1, b1, 4);
			    //second most left leg on the cow
			    int c1[] = { - 197 + i, -185 + i, -223 + i, -235 + i}; //list of all x-coordintes
			    int d1[] = {90, 100, 167, 157}; // list of all y-coordinates
			    c.fillPolygon (c1, d1, 4);
			    c.fillRect (-101 + i, 120, 15, 50); //second most right leg on cow
			    //most right leg on the cow
			    int e1[] = { - 101 + i, -86 + i, -51 + i, -66 + i}; //list of all x-coordintes
			    int f1[] = {120, 112, 153, 161}; // list of all y-coordinates
			    c.fillPolygon (e1, f1, 4);
			    c.setColor (cowSpots);
			    c.fillOval (-277 + i, 78, 17, 12); //black part of tail
			    c.fillOval (-246 + i, 85, 25, 17); //most left circle
			    c.fillOval (-212 + i, 62, 37, 30); //highest large circle
			    c.fillOval (-191 + i, 100, 37, 30); //lowest large circle
			    c.fillOval (-147 + i, 75, 37, 30); //most right large circle
			    c.fillOval (-103 + i, 67, 22, 16); //tallest small circle
			    c.fillOval (-106 + i, 106, 22, 16); //lowest small circle
			    c.setColor (cowAccents);
			    c.fillOval (-76 + i, 70, 65, 50); //cow nose
			    c.setColor (cowNostrils);
			    c.fillOval (-61 + i, 87, 9, 13); //left nostril
			    c.fillOval (-35 + i, 87, 9, 13); //right nostril
			    c.setColor (Color.black);
			    c.drawArc (-57 + i, 100, 30, 12, 180, 180); //mouth
			    c.fillOval (-64 + i, 55, 10, 12); //left eye
			    c.fillOval (-35 + i, 55, 10, 12); //right eye
			}
			//delays the animation
			try
			{
			    Thread.sleep (changeSpeed (animationSpeed)); //delays the animation with the number calculated in the return method changeSpeed based on the user input
			}
			catch (Exception e)  //catches any exceptions
			{
			}
		    }
		}
	    }
	    displayPassword ();
	}

    }


    /*
    This is a return method which will calculate the speed that the user has inputed for the animation to run at and then return it.
    The return method is returning an integer and it is private.

    Variable Declarations:
    Name                        Type               Purpose
    time                        int                Stores the calculated value of animationSpeed
    animationSpeed              int                Acts as the parameter for the return method and is the variable storing the user input

    There is an if statement which sets a different value to time with every if or else if.
    */
    private int changeSpeed (int animationSpeed)  //method called changeSpeed
    {
	int time = 0; //local variable declaration
	if (animationSpeed == 1)
	{
	    time = 50; //sets time to 100
	}
	else if (animationSpeed == 2)
	{
	    time = 30; //sets time to 60
	}
	else if (animationSpeed == 3)
	{
	    time = 20; //sets time to 30
	}
	else if (animationSpeed == 4)
	{
	    time = 13; //sets time to 15
	}
	else if (animationSpeed == 5)
	{
	    time = 5; //sets time to 7
	}
	return (time); //returns the time variable
    }


    /*
    This is a return method which will calculate the length of time that the user has inputed for the animation to run and then the method will return it.
    The return method is returning an integer and it is private.

    Variable Declarations:
    Name                        Type               Purpose
    length                      int                Stores the calculated value of executionTime
    executionTime               int                Acts as the parameter for the return method and is the variable storing the user input

    There is an if statement which sets a different value to length with every if or else if.
    */
    private int changeTime (int executionTime)  //method called changeTime
    {
	int length = 0; //local variable declaration
	if (executionTime == 1)
	{
	    length = 1; //sets length to 1
	}
	else if (executionTime == 2)
	{
	    length = 2; //sets length to 2
	}
	else if (executionTime == 3)
	{
	    length = 3; //sets length to 3
	}
	else if (executionTime == 4)
	{
	    length = 4; //sets length to 4
	}
	else if (executionTime == 5)
	{
	    length = 5; //sets length to 5
	}
	return (length); //returns the variable length
    }


    /*
    This method displays the passwords that the user has set and saved to the file ScreensaverPassword.

    Variable Declarations:
    Name                        Type               Purpose
    line                        String             Stores the lines in the file
    array                       String             Stores the passwords as elements of the array so that they can be outputted onto the screen
    reader                      BufferedReader     Creates a new reader so that the program can read what's already in the file
    reader2                     BufferedReader     Creates a new reader so that the program can read the file after the file is read by the BufferedReader reader

    First, there is a try and catch block to catch any errors that might occur when accessing a file.
    Inside this first try block, the program will access the file through a BufferedReader.
    There is a while loop after which will continue to add one to the variable counter as long as it does not hit an empty line in the file.
    There is an if statement that will output the passwords if the counter is greater than 1 and if there is nothing in the file, it will give a message that their are no passwords in the file.
    Inside the if statement, there is a second try block because I wanted to read from the file again. The purpose of having this try block is the same as the previous one - to catch any exceptions.
    In the try block, I read from the file again so that I can have a for loop running through 0 to the amount of lines in the file (represented by the variable counter).
    Everytime, the program will store one line from the file into an element in the array.
    There is another for loop which runs from 0 to the length of the array. I will then be able to print out each password onto the screen through the array.
    */
    public void displayPassword ()  //method called displayPassword
    {
	title (); //calls the title method
	//local variable declarations
	String line = "";
	counter = 0;
	BufferedReader reader, reader2;
	//try catch block to catch any errors such as not being able to find the file
	try
	{
	    reader = new BufferedReader (new FileReader (FILE_NAME)); //creates a new BufferedReader called reader to read the file name stored in the variable FILE_NAME
	    //while loop adds one to counter everytime there is something on a new line in the file
	    while ((line = reader.readLine ()) != null)
	    {
		counter++;
	    }
	    reader.close (); //closes the BufferedReader
	}
	catch (IOException e)
	{
	    c.println ("File not found"); //error traps if the file is not found
	}
	//program tells the user that they have no passwords stored if they have no passwords stored
	if (counter == 1)
	{
	    c.println ("You have not set a password.");
	}
	//if there are passwords in the file, it will output them
	else
	{
	    c.println ("Passwords set:");

	    String array[] = new String [counter]; //creates a string array and sets the size of it to the number of lines in the file and adds an additional space for the password the user just inputted
	    //try catch block to catch any errors such as not being able to find the file
	    try
	    {
		reader2 = new BufferedReader (new FileReader (FILE_NAME)); //creates another BufferedReader called reader2 to read the file name stored in the variable FILE_NAME
		//for loop which runs from 0 to the number of lines in the file
		reader2.readLine ();
		for (int i = 0 ; i < counter ; i++)
		{
		    array [i] = reader2.readLine (); //stores each line into a seperate element in the array
		}
		reader2.close (); //closes the BufferedReader
	    }
	    catch (IOException e)
	    {
		c.println ("File not found"); //error traps if the file is not found
	    }
	    array [counter - 1] = preSetPassword; //sets the final slot of the array to the password the user just set
	    //for loop running from 0 to the length of the array
	    for (int i = 0 ; i < array.length - 1 ; i++)
	    {
		c.println (array [i]); //writes the password to the file
	    }
	}
	c.println ();
	pauseProgram (); //calls the pauseProgram method
    }


    /*
    This method asks the user if they would like to clear the file called ScreensaverPasswords.
    If they choose yes, it will clear the file and if they choose no, the program will continue without clearing the file.

    Variable Declarations:
    Name                        Type               Purpose
    answer                      String             Stores the user input when they get asked if they would like to clear the file or not
    input                       PrintWriter        Creates a new writer so that the file can be cleared

    There is first a while loop which helps with the error trapping when the user is asked if they would like to clear the file.
    If they user enters yes, then there will be a try block to help catch any errors when accessing the file to clear it.
    The program will just write a space into the file to clear it.
    If the user enters no, then the program will break from the while loop and continue on with the pauseProgram and the rest of the program.
    If the user doesn't enter no or yes, then it will give them an error message.
    To do the above, an if statement is used so that the program can sort through the information the user entered through different conditions.
    */
    public void clearFile ()
    {
	title (); //calls the title method
	//local variable declarations
	String answer = "";
	PrintWriter input;
	c.println ();
	//while loop continues to loop until it breaks when a correct input is entered
	while (true)
	{
	    c.setCursor (3, 1);
	    c.println ("Would you like to clear the file (Please enter yes or no)?");
	    c.println ();
	    c.setCursor (4, 1);
	    answer = c.readString (); //gets the user input
	    //if the user enters yes then the file will be cleared
	    if (answer.equalsIgnoreCase ("yes"))
	    {
		//try catch block to catch any errors such as not being able to find the file
		try
		{
		    input = new PrintWriter (new FileWriter (FILE_NAME)); //creates a new PrintWriter called output so that the program can write to the file named the name that is stored in the variable FILE_NAME
		    input.println (""); //writes a space to the file
		    input.close (); //closes the PrintWriter
		}
		catch (IOException e)
		{
		    c.println ("File not found."); //error traps if the file is not found
		}
		c.println ("The file '" + FILE_NAME + "' was cleared.");
		break; //breaks from the while loop
	    }
	    //if the user enters no, the program will continue without doing anything
	    else if (answer.equalsIgnoreCase ("no"))
	    {
		break; //breaks from the while loop
	    }
	    //if the user enters neither yes nor no, the program will give an error message
	    else
	    {
		new Message ("You must enter yes or no. Please try again.");
	    }
	}
	pauseProgram (); //calls the pauseProgram method
    }


    //This method creates the goodbye message if the user wants to exit the program.
    //When the user wants to exit the program completely, they just have to enter any character.
    public void goodbye ()  //goodbye method
    {
	title (); //calls the title method
	c.println ("Thank you for using Screensaver! Have a great day!");
	c.println ("This program was written by Alyssa Wang.");
	c.println ();
	pauseProgram (); //calls the pauseProgram method
	c.close (); //closes the program after the user enters anything
    }


    //This method creates the instructions explaining the program and how to use it.
    public void instructions ()
    {
	title (); //calls the title method
	c.println ("This program will allow you to use 5 unique wallpapers. In main menu, you have");
	c.println ("the choice to set a password to protect the access to the program. Once you");
	c.println ("enter your password you will be allowed to view the wallpapers. You will also");
	c.println ("be able to change your password in the main menu if you wish to. These passwordswill all be saved into a file called ScreensaverPassword. You can view the file if you choose option 4 in the main menu. After viewing the passwords that you");
	c.println ("have set, you can also choose to clear the file. If you don't want to protect");
	c.println ("your program access, you can also preview the wallpapers without a password.");
	c.println ("While previewing your wallpapers, you will be allowed to change the animation");
	c.println ("speed and also how long your animation runs.While you are previewing your");
	c.println ("wallpapers, you will also be allowed to return back to main menu. After you set your wallpaper, the passwords in the file will be displayed. Enjoy Screensaver!");
	c.println ();
	pauseProgram (); //calls the pauseProgram method
    }


    /*This is the main method which creates an instance variable of the program and a copy of it.
    All of the methods are called here so that they can be exectued.
    There is a while loop which will run until the user enters 5 where it will break and output the goodbye method.
    Within this while loop, there is an if structure which tells the program which methods to call based on what the user enters in main method.
    If the user enters 1 , it will continue to the setPassword method.
    If the user enters 2, it will execute the wallpaperDisplayPassword and the wallpaperDisplay method.
    If the user enters 3, it will execute the instructions method.
    If the user enters 4, it will execute the displayPassword method.
    If there are passwords in the file, it will display the clearFile method allowing the user to clear the file.
    If there are no passwords in the file, it will continue with the rest of the program.
    If the user enters 5, them it will break the while loop which will go to the goodbye method which is executed outside of the loop.
    Then there is an else, which will display an error message if the user does not enter a valid answer. Valid answers include 1,2,or 3.
    Variable Declarations:
    Name                        Type               Purpose
    s                           instance           Creates an instance variable for the Screensaver class
    */
    public static void main (String[] args)  //main method
    {
	ScreenSaver s = new ScreenSaver (); //creates an instance variable and a copy of ScreenSaver
	s.splashScreen (); //executes the splashScreen method
	s.rabbitAnimation (); //executes the RabbitAnimation method
	while (true)
	{
	    s.mainMenu (); //executes the mainMenu method
	    if (s.choice.equals ("1"))
	    {
		s.setPassword ();  //executes the setPassword method
	    }
	    else if (s.choice.equals ("2"))
	    {
		s.wallpaperDisplayPassword (); //executes the wallpaperDisplayPassword method
		s.wallpaperDisplay ();  //executes the wallpaperDisplay method
	    }
	    else if (s.choice.equals ("3"))
	    {
		s.instructions ();  //executes the instructions method
	    }
	    else if (s.choice.equals ("4"))
	    {
		s.displayPassword ();  //executes the displayPassword method
		if (s.counter > 1)
		{
		    s.clearFile (); //executes the clearFile method
		}
	    }
	    else if (s.choice.equals ("5"))
	    {
		break; //allows program to exit from while loop
	    }
	    else
	    {
		//gives the user an error message if they don't enter 1,2,3,4, or 5
		new Message ("Error! You must enter 1,2,3,4, or 5.");
	    }
	}
	s.goodbye (); //executes the goodbye method after the program breaks from the while loop
    }
}

/*Sources:
keeps threads from overlapping each other and switching up the colours
I got the synchronized (c) code from the MCPT website.
Here is the link:https://docs.google.com/document/d/1mgR09oneNIJhgefgkHK2vpaSQ5gZ8xDqB7rtPDQ7YHM/edit?fbclid=IwAR2HP_Ybu0Q2RsCELSHossdkBnrBlsZtf1YJuwAdcCU6sF4cdklMLKnDhMs)
*/





