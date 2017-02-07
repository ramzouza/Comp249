import java.util.Random;
import java.util.Scanner;

/**
 * 
 * This class takes care of all the interaction with the board itself it will create and initialize the board takes the input
 * from the user, created an input for the computer, shoot the requested tile by the user and computer, and confirms if either 
 * the player or the opponent have lost all of their ships.
 * 
 * @author Ramez Zaid
 *
 */
	/**
	 * The creation of a class board, creates an 2D array with references of type tile
	 */

public class board {
	tile[][] grid = new tile[9][9];
	Random rand = new Random();

	/**
	 * default constructor that will call the method initializeBoard 
	 */
	board() {
		initializeBoard();
	}
	
	Scanner input = new Scanner(System.in);

	/**
	 * creates a tile for every tile reference in the array, and initialize it to it's appropriate initial value
	 */
	private void initializeBoard() {

		grid[0][0] = new tile(' ');

		for (int i = 1; i < 9; i++) {
			grid[0][i] = new tile((char) (64 + i));
			grid[i][0] = new tile((char) (i + 48));

			for (int j = 1; j < 9; j++) {
				grid[i][j] = new tile();
			}
		}
	}

	/**
	 * method that print's out the grid in the appropriate format
	 * @param duringGame variables that decides which toString is being used
	 */
	public void print(boolean duringGame) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(grid[i][j].toString(duringGame) + " ");
			}
			System.out.println();
		}
	}
	/**
	 * method that intakes a string of a given position on the board and will return the equivalent object in the array
	 * @param position that the user or computer inputs 
	 * @return the tile equivalent to the position (such as A1) that was sent to the method 
	 */
	public tile getTile(String position) {
		if (position == null || position.length() != 2) {
			return null;
		}

		int y = position.charAt(0) - 'A' + 1;
		if (y < 1 || y > 8) {
			return null;
		}

		int x = position.charAt(1) - '1' + 1;
		if (x < 0 || x > 8) {
			return null;
		}

		return grid[x][y];
	}

	/**
	 * while loop that will get the user input and verify that it is valid, if not it will ask the user to input  
	 * a new set of position
	 * @param content enum that will decide if the text says ship or grenade	
	 * @param counter int  that displays what ship# or grenade# is being positioned  
	 */
	public void userInput(tileContent content, int counter) {

		tile myTile;

		while (true) {

			System.out.println("Enter the coordinates of your " + tile.getContentName(content) + " #" + counter);
			
			String position = input.next().toUpperCase();
			
			myTile = this.getTile(position);
			if (myTile == null) {
				System.out.println("sorry, coordinates outside the grid. try again.");
				continue;
			}
			if (!myTile.isEmpty()) {
				System.out.println("sorry, coordinates already used. try again.");
				continue;
			}

			break;
		}

		myTile.setContent(true, content);
	}
	
	/**
	 * ask the user a position to shoot a rocket until it gets an acceptable input,
	 * the specific object that has been selected to shoot a rocket on is than sent to the method executeShot
	 * @return 
	 */
	 public boolean userShoot() {

		tile myTile;

		while (true) {

			System.out.println("position of your rocket:");
			
			String position = input.next().toUpperCase();
			
			myTile = this.getTile(position);
			if (myTile == null) {
				System.out.println("sorry, coordinates outside the grid. try again.");
				continue;
			}

			break;
		}

		return executeShot(myTile);
	}
	
	 /**
	  * Randomly generates a position for the computer to enter it's grenades and ships
	  * will call getTile, send a string to it and receive the object equivalent to this position 
	  * @param content that will decide if the position entered will be equivalent to a Ship or a Grenade
	  * @param counterint  that displays what ship# or grenade# is being positioned  
	  */
	public void computerInput(tileContent content, int counter) {
		
		String position = null;
		tile robotTile;

		do {
			int n = rand.nextInt(8) + 1;
			int m = rand.nextInt(8) + 1;
			position = new String(new char[]{ (char)((int)'A' + m - 1)}) + n ;

			robotTile = this.getTile(position);

		} while (!robotTile.isEmpty());

		robotTile.setContent(false, content);
	}

	/**
	 * randomly generates a position for the computer to shoot, than sent the object tile equivalent to the position 
	 * selected to the method executeSHot	
	 * @return the boolean method executeShot
	 */
	public boolean computerShoot() {
		String position = null;
		tile robotTile;

		int n = rand.nextInt(8) + 1;
		int m = rand.nextInt(8) + 1;
		position = new String(new char[]{ (char)((int)'A' + m - 1)}) + n ;

		robotTile = this.getTile(position);

		System.out.println("position of my rocket: " + position);
		
		return executeShot(robotTile);
	}
	
	/**
	 * method that will switch threw different options given if the tile was previously called, a grenade was hit nothing 
	 * was hit or a ship was hit.   
	 * @param tile 
	 * @return true if you hit a grenade 
	 */
	private boolean executeShot(tile tile)
	{
		switch(tile.shoot())
	{
		case AlreadyCalled:
			System.out.println("position already called.");
			break;
		case GrenadeHit:
			System.out.println("boom! grenade.");
			return true;
		case Nothing:
			System.out.println("nothing.");
			break;
		case ShipHit:
			System.out.println("ship hit.");
			break;
	}
	
	return false;
}
	
	/**
	 * 4 for loops that will let the user put 6 ships, 4 grenade, and let's the computer put 6 ships, 4 grenade
	 */
	public void setupBoard()
	{
		for (int i = 1; i <= 6; i++) {
			userInput(tileContent.Ship, i);
		}

		for (int i = 1; i <= 4; i++) {
			userInput(tileContent.Grenade, i);
		}

		for (int i = 1; i <= 6; i++) {
			computerInput(tileContent.Ship, i);
		}

		for (int i = 1; i <= 4; i++) {
			computerInput(tileContent.Grenade, i);
		}
	}
	
	/**
	 * boolean that returns true if the user or computer is still alive
	 * @param isUser boolean that is true if user is calling it and false if it's the computer
	 * @return
	 */
    public boolean isStillAlive(boolean isUser)
    {
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				if (grid[i][j].isStillAlive(isUser))
				{
					return true;
				}
			}
		}

		return false;
    }
    
    /**
     * overloading method of isStillAlive that will return true if both the computer and user is still alive
     * @return boolean
     */
    public boolean isStillAlive()
    {
    	return isStillAlive(true) && isStillAlive(false);
    }
    
    /**
     *  method that will print a message depending on if the user or the computer won
     *  and will print the board in it's initial states (with the grenade and sips of both the user and the computer)
     */
    public void printWinner()
    {
    	if (isStillAlive(true))
    	{
    		System.out.println("You win!");
    	}
    	else
    	{
    		System.out.println("Computer win!");
    	}
    	
    	this.print(false);
    }
}
