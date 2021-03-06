import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * GUI for the grid
 */
public class GridGui
{
	JFrame f = new JFrame();
	private BorderLayout flow = new BorderLayout();
	private GridLayout grid = new GridLayout(8,0);
	private int rows;
	private int columns;
	private final Environment e;
	private JButton[][] buttons;
	private JPanel mainPanel;
	private int totalButtons;
	private int buttonsClicked;
	private int bombCount;
	private int winningNumber;
	private int score = 0;
	private JLabel scoreLabel = new JLabel();
	private JLabel bombLabel = new JLabel();
	private JLabel flagInfo = new JLabel("Right click to flag");
	private JLabel infoHeader = new JLabel("Information: ");
	private JPanel infoPanel = new JPanel(grid);

	
	public GridGui(Environment e)
	{
		f.setLayout(flow);

		this.e = e;
		rows = e.getRows();
		columns = e.getColumns();

		/* sets gridlayout rows and columns by using environment rows and columns */
		GridLayout textLayout = new GridLayout(rows, columns);
		mainPanel = new JPanel(textLayout);

		createButtons();
		addAction();
		setNumberOfBombs();
		addInfoLabels();
		updateScore();
		updateBombCount();


		/* sets winning number */
		winningNumber = totalButtons - bombCount;


		//f.pack();
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);						// sets frame to full screen
		f.setVisible(true);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * adds info labels to panel
	 */
	public void addInfoLabels()
	{
		infoHeader.setFont(new Font("Arial", Font.PLAIN, 40));
		flagInfo.setFont(new Font("Arial", Font.PLAIN, 40));
		
		infoPanel.add(infoHeader);
		infoPanel.add(flagInfo);
		
		f.add(infoPanel, "East");
		
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	/**
	 * updates frame when score increases
	 */
	public void updateScore()
	{
		scoreLabel.setText("Score: " + Integer.toString(score) + "		");
		scoreLabel.setFont(new Font("Arial", Font.PLAIN, 40));
		infoPanel.add(scoreLabel);
		f.add(infoPanel, "East");
	}
	

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * updates frame when bomb count drops
	 */
	public void updateBombCount()
	{
		bombLabel.setText("Bombs left: \n" + Integer.toString(bombCount) + "		");
		bombLabel.setFont(new Font("Arial", Font.PLAIN, 40));
		infoPanel.add(bombLabel);
		f.add(infoPanel, "East");
	}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * creates buttons
	 */
	public void createButtons()
	{
		buttons = new JButton[rows][columns];

		Graphics g;

		/* loop to add each button to mainPanel */
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < columns; c++)
			{
				buttons[r][c] = new JButton();
				//buttons[r][c].setPreferredSize(new Dimension(30, 30));	// sets dimensions for button

				/* changes background color and border */
				buttons[r][c].setBackground(Color.darkGray);
				buttons[r][c].setBorder(new LineBorder(Color.BLACK));


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

		/* bomb image */
		ImageIcon bomb = new ImageIcon(new ImageIcon("bomb.jpg").getImage().getScaledInstance(150, 100, Image.SCALE_DEFAULT));
		ImageIcon flag = new ImageIcon(new ImageIcon("flag.jpg").getImage().getScaledInstance(250, 150, Image.SCALE_DEFAULT));


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
					buttons[r][c].addMouseListener(new MouseListener()
					{
						  public void mousePressed (MouseEvent m)
						  {

							  /* here if right clicked */
							  if (SwingUtilities.isRightMouseButton(m))
							  {
								  //buttons[row][col].setText("Flagged");
								  buttons[row][col].setIcon(flag);
								  bombCount--;
								  updateScore();
								  updateBombCount();
							  }
							  
							  /* here if user clicks a bomb as first click */
							  else if (buttonsClicked == 0)
							  {
								  e.difuseBomb(row, col);
								  
								  buttons[row][col].setText(Integer.toString(number));
								  buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));

								  /* changes color button and disables it */
								  buttons[row][col].setOpaque(true);
								  buttons[row][col].setEnabled(false);
								  
								 
								  /* updates bomb label */
								  bombCount--;
								  updateBombCount();
								  
								  /* resets winning number */
								  winningNumber = totalButtons - bombCount;
								  
								  buttonsClicked++;
							  }
							  
							  /* here if left clicked */
							  else
							  {
								  /* prints loser message */
								  buttons[row][col].setIcon(bomb);
								 // buttons[row][col].setEnabled(false);
								  JOptionPane.showMessageDialog(null, "Loser!");
									f.dispose();
							  }
						  }


						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub

						}


						@Override
						public void mouseClicked(MouseEvent e) {
							// TODO Auto-generated method stub

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

							  /* changes color button and disables it */
							  buttons[row][col].setOpaque(true);
							  buttons[row][col].setEnabled(false);

							  /* tracks winning */
							  buttonsClicked++;
							  
							  /* adds to score and updates panel */
							  score += 10;
							  updateScore();
							  updateBombCount();

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
