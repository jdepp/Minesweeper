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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * returns bomb value of square for Square class
	 */
	public boolean getBomb(int r, int c)
	{
		return grid[r][c].getBomb();
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * creates number for square by determining where
	 * bombs are around it
	 */
	public void setsNumber() {
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				if(checkRight(r,c))							grid[r][c].setNumber();
				if(checkBottomRight(r,c))				grid[r][c].setNumber();
				if(checkBottom(r,c))						grid[r][c].setNumber();
				if(checkBottomLeft(r,c))				grid[r][c].setNumber();
				if(checkLeft(r,c))							grid[r][c].setNumber();
				if(checkTopLeft(r,c))						grid[r][c].setNumber();
				if(checkTop(r,c))								grid[r][c].setNumber();
				if(checkTopRight(r,c))					grid[r][c].setNumber();
			}
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public boolean checkRight(int row, int col) {
		if(col+1 < columns && grid[row][col+1].getBomb())  return true;
		else   return false;
	}

  public boolean checkBottomRight(int row, int col) {
		if(row+1 < rows && col+1 < columns && grid[row+1][col+1].getBomb())   return true;
		else   return false;
	}

	public boolean checkBottom(int row, int col) {
		if(row+1 < rows && grid[row+1][col].getBomb())   return true;
		else   return false;
	}

  public boolean checkBottomLeft(int row, int col) {
		if(row+1 < rows && col-1 >= 0 && grid[row+1][col-1].getBomb())   return true;
		else   return false;
	}

	public boolean checkLeft(int row, int col) {
		if(col-1 >= 0 && grid[row][col-1].getBomb())   return true;
		else   return false;
	}

	public boolean checkTopLeft(int row, int col) {
		if(row-1 >= 0 && col-1 >= 0 && grid[row-1][col-1].getBomb())   return true;
		else   return false;
	}

	public boolean checkTop(int row, int col) {
		if(row-1 >= 0 && grid[row-1][col].getBomb())   return true;
		else   return false;
	}

	public boolean checkTopRight(int row, int col) {
		if(row-1 >= 0 && col+1 < columns && grid[row-1][col+1].getBomb())   return true;
		else   return false;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/* calls difuse bomb in square class */
	public void difuseBomb(int r, int c)
	{
		grid[r][c].difuseBomb();
		createGrid();
		setsNumber();
	}

}
