/**
 * Welcome GUI that takes name and difficulty
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WelcomeGui
{
	JFrame f = new JFrame();
	private JPanel panel = new JPanel();

	private JButton easy = new JButton("Easy");
	private JButton medium = new JButton("Medium");
	private JButton hard = new JButton("Hard");

	// variable for difficulty
	private int difficulty;

	// variable used to wait until button is pressed
	private boolean buttonPressed = false;

	public WelcomeGui()
	{
		addAction();

		f.setSize(300, 70);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	public void addAction()
	{
		panel.add(easy);
		panel.add(medium);
		panel.add(hard);

		f.add(panel);

		easy.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
					Environment env = new Environment(5);
					GridGui gg = new GridGui(env);
			  }
		});

		medium.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
					Environment env = new Environment(4);
					GridGui gg = new GridGui(env);
			  }
		});

		hard.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
					Environment env = new Environment(3);
					GridGui gg = new GridGui(env);
			  }
		});
	}

	public boolean getButtonPressed()
	{
		return buttonPressed;
	}

	public int getDifficulty()
	{
		return difficulty;
	}

}
