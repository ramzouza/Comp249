import java.util.Scanner;
/**
 * main driver of the game battelship
 * @author Ramez
 *
 */
public class battleship
{
	Scanner input= new Scanner(System.in);
	board gameBoard = new board();
	boolean skipUserTurn;
	boolean skipComputerTurn;
	int skippedTurns;
	/**
	 * main method creates a new object of battleship and simply calls it's method play
	 * @param args
	 */
	public static void main(String[] args)
	{
		battleship battle = new battleship();
		battle.play();
	}
	/**
	 * play method, will setup the board and than print it empty (because of the true)
	 * while loop until either the user or the computer looses all of it's ships
	 * will alternate between the user turn method and the computer turn method
	 */
	public void play()
	{
		gameBoard.setupBoard();
		gameBoard.print(true);
		
		while (gameBoard.isStillAlive())
		{
			userTurn();
			computerTurn();
		}

		gameBoard.printWinner();
		System.out.println("The number of skipped turns: "+ skippedTurns);
	}
	/**
	 * method that is used for the user to play his turn, if skipUserTurn is true, it's value is changed to false and the method is 
	 * exited.  
	 * if skipUserTurn is false, the method userShoot is called and it will return true if the user hits a grenade which will make 
	 * him skip his turn  
	 */
	public void userTurn() {
		if (skipUserTurn)
		{
			skipUserTurn = false;
			return;
		}
		
		if (gameBoard.userShoot())
		{
			skippedTurns ++;
			skipUserTurn = true;
		}
		
		gameBoard.print(true);
	}
	/**
	 * method that is used for the computer to play his turn, if skipUserTurn is true, it's value is changed to false and the method is 
	 * exited.  
	 * if skipUserTurn is false, the method userShoot is called and it will return true if the user hits a grenade which will make 
	 * him skip his turn  
	 */
	public void computerTurn() {
		if (skipComputerTurn)
		{
			skipComputerTurn = false;
			return;
		}
		
		if (gameBoard.computerShoot())
		{
			skippedTurns ++;
			skipComputerTurn = true;
		}

		gameBoard.print(true);
	}	

}
