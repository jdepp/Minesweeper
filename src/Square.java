/**
 * Generates the value of a square
 */

import java.util.*;

public class Square
{
	private boolean bomb;
	private Random rand = new Random();
	private int  n = rand.nextInt(5) + 1;
	private int number = 0;

	/**
	 * constructor determines bomb value
	 */
	public Square()
	{
		if (n == 5)
			bomb = true;
		else
			bomb = false;
	}

	/**
	 * sets the number value
	 */
	public void setNumber()
	{
		this.number++;
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
