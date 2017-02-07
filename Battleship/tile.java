/**
 * tile class that manipulates the different tile Object, contains methods such as shot, setContent and so on to manipulate 
 * different tile object
 * @author Ramez
 *
 */
public class tile 
{
	/**
	 * creation of instance variables		
	 */
	private tileContent content;
	private char charContent;
	private boolean isUser;
	private boolean isHit;
	
	tile()
	{
		this.content = tileContent.Empty;
	}
	
	tile(char typeChar)
	{
		this.content = tileContent.Character;
		this.charContent = typeChar;
	}

	/*
	 * method of type shootResult that will decide the shootResult of the selected tile
	 * shootResult is affected by if the tile has been hit or not by a rocket and it's initial tileContent value (ship,grenade etc)  
	 */
	public shootResult shoot()
	{
		if (this.content == tileContent.Ship)
		{
			if (this.isHit)
			{
				return shootResult.AlreadyCalled;
			}
			else
			{
				this.isHit = true;
				return shootResult.ShipHit;
			}
		}
		else if (this.content == tileContent.Grenade)
		{
			if (this.isHit)
			{
				return shootResult.AlreadyCalled;
			}
			else
			{
				this.isHit = true;
				return shootResult.GrenadeHit;
			}
		}
		else
		{
			this.content = tileContent.Rocket;
			return shootResult.Nothing;
		}
	}
	
	/**
	 * setter for the tileContent variable, this method will set the tileContent for both the user and the computer
	 * @param isUser isUser boolean that is true if user is calling it and false if it's the computer
	 * @param content
	 */
	public void setContent(boolean isUser, tileContent content)
	{
		this.isUser = isUser;
		this.content = content;
	}

	/**
	 * toString method that is called to print each tile, contains a duringGame boolean that will alter what is being printed
	 * is duringGame is true, the ship, grenade will only be displayed if the isHIt boolean is true.
	 * if duringGame is false, ships and grenades will all the be displayed 
	 * @param duringGame
	 * @return
	 */
	public String toString(boolean duringGame)
	{
		if (duringGame)
		{
			switch(this.content)
			{
				case Ship:
					if (isHit)
					{
						return  this.isUser ? "s" : "S";
					}
					break;
				case Grenade:
					if (isHit)
					{
						return this.isUser ? "g" : "G";
					}
					break;
				case Rocket:
					return "*";
				case Character:
					return new String(new char[]{this.charContent});
			}
			
			return "_";
		}

		switch(this.content)
		{
			case Empty:
				return "_";
			case Ship:
				return this.isUser ? "s" : "S";
			case Grenade:
				return this.isUser ? "g" : "G";
			case Rocket:
				return "_";
			case Character:
				return new String(new char[]{this.charContent});
			default:
				return "=";
		}
	}
	
	/**
	 * boolean that check if the tile is empty
	 * @return
	 */
	public boolean isEmpty()
	{
		return this.content == tileContent.Empty;
	}
	/**
	 * boolean that will return true if the tile belong to whoever called the method, the content of the tile is a ship and the 
	 * ship has not been hit
	 * @param isUser
	 * @return
	 */
	public boolean isStillAlive(boolean isUser)
	{
		return (isUser == this.isUser && content == tileContent.Ship && !isHit);
	}
	
	/**
	 * depending on the value of content will return an equivalent string 
	 * @param content
	 * @return
	 */
	public static String getContentName (tileContent content)
	{
		switch(content)
		{
			case Ship:
				return "ship";
			case Grenade:
				return "grenade";
			case Rocket:
				return "rocket";
			default:
				return "";
		}
	}
}
