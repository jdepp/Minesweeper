/**
 * Main method for Minesweeper
 */

public class Runner 
{
	public static void main(String[] args)
	{
		WelcomeGui wg = new WelcomeGui();

		// empty loop that waits for user input
		while (!wg.getButtonPressed())
		{
			System.out.print("");
		}
		
		Environment e = new Environment(wg.getDifficulty());
		
		GridGui gg = new GridGui(e);
	}
	
}
