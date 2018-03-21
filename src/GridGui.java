import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * GUI for the grid
 */
public class GridGui 
{
	JFrame f = new JFrame();
	private int rows;
	private int columns;
	private final Environment e;
	private JButton[][] buttons;
	private JPanel mainPanel;
	private int totalButtons;
	private int buttonsClicked;
	private int bombCount;
	private int winningNumber;
	
	public GridGui(Environment e)
	{
		BorderLayout fLayout = new BorderLayout();
		f.setLayout(fLayout);
		
		this.e = e;
		rows = e.getRows();
		columns = e.getColumns();

		/* sets gridlayout rows and columns by using environment rows and columns */
		GridLayout textLayout = new GridLayout(rows, columns);
		mainPanel = new JPanel(textLayout);
		
		createButtons();
		addAction();
		setNumberOfBombs();
		
		/* sets winning number */
		winningNumber = totalButtons - bombCount;
		
		//f.pack();
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);						// sets frame to full screen
		f.setVisible(true);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * creates buttons 
	 */
	public void createButtons()
	{
		buttons = new JButton[rows][columns];
		
		/* loop to add each button to mainPanel */
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < columns; c++)
			{
				buttons[r][c] = new JButton();
				//buttons[r][c].setPreferredSize(new Dimension(30, 30));	// sets dimensions for button
				
				mainPanel.add(buttons[r][c]);
				
				totalButtons++;

			}
		}
		
		f.add(mainPanel, "Center");
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * sets the total number of bombs 
	 */
	public void setNumberOfBombs()
	{
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < columns; c++)
			{
				if (e.getBomb(r, c))
				{
					bombCount++;
				}
			}
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * adds actions to buttons
	 */
	public void addAction()
	{
		boolean temp;
		
		ImageIcon bomb = new ImageIcon(new ImageIcon("bomb.jpg").getImage().getScaledInstance(150, 100, Image.SCALE_DEFAULT));
		
		/* loops all squares to add actions */
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < columns; c++)
			{
				/* temp that holds bomb value of square */
				temp = e.getBomb(r, c);
				
				/* variables must declared final to be used in action events */
		        final int row = r;
		        final int col = c;
		        final int number = e.getNumber(row, col);
		        
				/* here if square contains a bomb */
				if (temp)
				{
					buttons[r][c].addActionListener(new ActionListener() 
					{ 
						  public void actionPerformed(ActionEvent e) 
						  {
							  buttons[row][col].setIcon(bomb);
							  buttons[row][col].setEnabled(false);
							  
							  /* prints loser message */
							  JOptionPane.showMessageDialog(null, "Loser!");
						  }
					});
				}
				
				/* here if square does not contain a bomb */
				else
				{
					e.getNumber(r, c);
					
					buttons[r][c].addActionListener(new ActionListener() 
					{ 
						  public void actionPerformed(ActionEvent e) 
						  {							  
							  buttons[row][col].setText(Integer.toString(number)); 
							  buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));
							  buttons[row][col].setEnabled(false);
							  buttonsClicked++;
							  
							  /* here if all number squares have been clicked */
							  if (buttonsClicked == winningNumber)
								  JOptionPane.showMessageDialog(null, "Winner!");
						  }
					});
				}
				

			}
		}
	}
	
	
}
