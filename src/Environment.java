/**
 * mine environment
 */
public class Environment 
{
	private int rows;
	private int columns;
	private int difficulty;
	private Square[][] grid;
	
	/*
	 * constructor that saves difficulty and executes methods
	 */
	public Environment(int difficulty)
	{
		this.difficulty = difficulty;
		
		setDifficulty();
		createGrid();
		setsNumber();
	}
	
	/*
	 * method that sets difficulty - i.e. rows and columns based on value passed from WelcomeGui
	 */
	public void setDifficulty()
	{
		if (difficulty == 3)
		{
			rows = 15;
			columns = 15;
		}
		else if (difficulty == 4)
		{
			rows = 10;
			columns = 10;
		}
		else
		{
			rows = 5;
			columns = 5;
		}
	}
	
	/*
	 * creates squares based on rows and columns 
	 */
	public void createGrid()
	{
		grid = new Square[rows][columns];

		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < columns; c++)
			{
				Square temp = new Square();
				grid[r][c] = temp;
			}
		}
	}
	
	/**
	 * returns bomb value of square for Square class
	 */
	public boolean getBomb(int r, int c)
	{
		return grid[r][c].getBomb();
	}
	
	/**
	 * creates number for square by determining where 
	 * bombs are around it
	 */
	public void setsNumber()
	{
		int leftC;
		int rightC;
		int topR;
		int bottomR;

		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < columns; c++)
			{
				// leftC
				if (c != 0)
					leftC = c - 1;
				else
					leftC = 0;
				
				// rightC
				if (c == (columns - 1))
					rightC = columns - 1;
				else
					rightC = c + 1;
				
				// bottomR
				if (r == (rows - 1))
					bottomR = rows - 1;
				else
					bottomR = r + 1;
				
				// topR
				if (r != 0)
					topR = r - 1;
				else
					topR = 0;
					
				// checks the column to the left
				if (grid[r][rightC].getBomb())
					grid[r][c].setNumber();
				
				// checks the column to the right
				if (grid[r][leftC].getBomb())
					grid[r][c].setNumber();
				
				// checks value directly below
				if (grid[bottomR][c].getBomb())
					grid[r][c].setNumber();
				
				// checks the value directly above
				if (grid[topR][c].getBomb())
					grid[r][c].setNumber();
				
				
								
				// checks the top right diagonal
			//	if (grid[topR][rightC].getBomb())
			//		grid[r][c].setNumber();
				
				// checks the top left diagonal
			//	if (grid[topR][leftC].getBomb())
			//		grid[r][c].setNumber();
				
				// checks the bottom left diagonal
			//	if (grid[bottomR][leftC].getBomb())
			//		grid[r][c].setNumber();
				
				// checks the bottom right diagonal
			//	if (grid[bottomR][rightC].getBomb())
			//		grid[r][c].setNumber();
			}
		}
	}
	
	/**
	 * gets number of square
	 */
	public int getNumber(int r, int c)
	{
		return grid[r][c].getNumber();
	}
	
	public int getRows()
	{
		return rows;
	}
	
	public int getColumns()
	{
		return columns;
	}

}
