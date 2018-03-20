/**
 * Welcome GUI that takes name and difficulty
 */
public class MockWelcomeGui 
{
	public static void main(String[] args)
	{
		WelcomeGui wg = new WelcomeGui();

		// empty loop that waits for user input
		while (!wg.getButtonPressed())
		{
			System.out.print("");
		}

		System.out.println(wg.getDifficulty());
		
		
	}
}
