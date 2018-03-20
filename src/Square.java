/**
 * Generates the value of a square 
 */

import java.util.*;

public class Square 
{
	private boolean bomb;
	private Random rand = new Random();
	private int  n = rand.nextInt(3) + 1;
	private int number = 0;

	/**
	 * constructor determines bomb value
	 */
	public Square()
	{
		if (n <= 2)
			bomb = false;
		else
			bomb = true;
	}
	
	/**
	 * sets the number value
	 */
	public void setNumber()
	{
		this.number++;;
	}
	
	/**
	 * returns the number value
	 */
	public int getNumber()
	{
		return number;
	}
	
	/**
	 *  returns bomb
	 */
	public boolean getBomb()
	{
		return bomb;
	}
}
