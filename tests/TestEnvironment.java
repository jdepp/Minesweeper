import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestEnvironment 
{

	@Test
	void testConstructor() 
	{
		Environment e = new Environment(4);
		
		assertEquals(10, e.getColumns());
		assertEquals(10, e.getRows());
	}
	
	/**
	 * prints the board
	 */
	@Test
	void testCreateGrid()
	{
		Environment e = new Environment(4);

		
		for (int r = 0; r < 8; r++)
		{
			for (int c = 0; c < 8; c++)
			{
				if (e.getBomb(r, c))
					System.out.print(" * ");
				else
					System.out.print(" " + e.getNumber(r, c) + " ");
			}
			
			System.out.println("");
		}

	}
	

	

}
