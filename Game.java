package yee;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements ActionListener, MouseListener {

	private int rows_;
	private int cols_;
	private int mines_;
	private int flagCounter_;
	private int correctFlagCounter_;
	private int disabledCellCounter_;
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	private ImageIcon one = new ImageIcon("C:\\Users\\Westin\\Desktop\\MinesweeperImages\\one.png");
	private ImageIcon two = new ImageIcon("C:\\Users\\Westin\\Desktop\\MinesweeperImages\\two.png");
	private ImageIcon three = new ImageIcon("C:\\Users\\Westin\\Desktop\\MinesweeperImages\\three.png");
	private ImageIcon four = new ImageIcon("C:\\Users\\Westin\\Desktop\\MinesweeperImages\\four.png");
	private ImageIcon five = new ImageIcon("C:\\Users\\Westin\\Desktop\\MinesweeperImages\\five.png"); //all icons needed for the game
	private ImageIcon six = new ImageIcon("C:\\Users\\Westin\\Desktop\\MinesweeperImages\\six.png");
	private ImageIcon seven = new ImageIcon("C:\\Users\\Westin\\Desktop\\MinesweeperImages\\seven.png");
	private ImageIcon eight = new ImageIcon("C:\\Users\\Westin\\Desktop\\MinesweeperImages\\eight.png");
	private ImageIcon empty = new ImageIcon("C:\\Users\\Westin\\Desktop\\MinesweeperImages\\empty.png");
	private ImageIcon unpressed = new ImageIcon("C:\\Users\\Westin\\Desktop\\MinesweeperImages\\unpressed.png");
	private ImageIcon flag = new ImageIcon("C:\\Users\\Westin\\Desktop\\MinesweeperImages\\flag.png");
	private ImageIcon mine = new ImageIcon("C:\\Users\\Westin\\Desktop\\MinesweeperImages\\mine.png");
	
	private Image scaledFlag = flag.getImage().getScaledInstance(55, 55,java.awt.Image.SCALE_SMOOTH);
	private Image scaledUnpressed =unpressed.getImage().getScaledInstance(55, 55,java.awt.Image.SCALE_SMOOTH);
	private Image scaledOne = one.getImage().getScaledInstance(55, 55,java.awt.Image.SCALE_SMOOTH);
	private Image scaledTwo = two.getImage().getScaledInstance(55, 55,java.awt.Image.SCALE_SMOOTH);
	private Image scaledThree = three.getImage().getScaledInstance(55, 55,java.awt.Image.SCALE_SMOOTH);
	private Image scaledFour = four.getImage().getScaledInstance(55, 55,java.awt.Image.SCALE_SMOOTH);
	private Image scaledFive = five.getImage().getScaledInstance(55, 55,java.awt.Image.SCALE_SMOOTH);
	private Image scaledSix = six.getImage().getScaledInstance(55, 55,java.awt.Image.SCALE_SMOOTH);
	private Image scaledSeven = seven.getImage().getScaledInstance(55, 55,java.awt.Image.SCALE_SMOOTH);
	private Image scaledEight= eight.getImage().getScaledInstance(55, 55,java.awt.Image.SCALE_SMOOTH);
	private Image scaledEmpty = empty.getImage().getScaledInstance(55, 55,java.awt.Image.SCALE_SMOOTH);
	private Image scaledMine = mine.getImage().getScaledInstance(55, 55,java.awt.Image.SCALE_SMOOTH);
	
	private ImageIcon[][] sol_; //solution for the game
	//private JButton[][] cellArr_;
	private Cell[][] cellArr_;
	private JPanel[][] panelArr_;
	/**
	 * @param rows
	 * @param cols
	 * @param mines
	 */
	public Game(int rows, int cols, int mines)
	{
	super();
	rows_ = rows;
	cols_ = cols;
	mines_ = mines;
	
	flag = new ImageIcon(scaledFlag);
	unpressed = new ImageIcon(scaledUnpressed);
	one = new ImageIcon(scaledOne);
	two = new ImageIcon(scaledTwo);
	three = new ImageIcon(scaledThree);
	four = new ImageIcon(scaledFour);
	five = new ImageIcon(scaledFive); //scales all of the pictures
	six = new ImageIcon(scaledSix);
	seven = new ImageIcon(scaledSeven);
	eight = new ImageIcon(scaledEight);
	empty = new ImageIcon(scaledEmpty);
	mine = new ImageIcon(scaledMine);
	
	sol_ = new ImageIcon[rows_][cols_]; //solution grid
	for(int i = 0; i < rows_; i++)
	{
		for(int j = 0; j < cols_; j++)
		{
			sol_[i][j] = empty; //sets all to blank to start
		}
	}
	
	for(int i = 0; i < mines_; i++) //puts mines in random spots for how many mines the user wants
	{
		int randomX = (int)(Math.random() * rows_);
		int randomY = (int)(Math.random() * cols_);
		while(sol_[randomX][randomY] == mine)
		{
			randomX = (int)(Math.random() * rows_); //checks if mine is already there
			randomY = (int)(Math.random() * cols_);
		}
		sol_[randomX][randomY] = mine; //puts mine in random spot
	}
	
	for(int i = 0; i < rows_; i++) //checks every panel for mines around it and sets image accordingly
	{
		for(int j = 0; j< cols_; j++)
		{
			if(sol_[i][j] != mine)
			{
				int mineCounter = 0;
				if(i == 0 && j == 0) //checks for top left corner
				{
					if(sol_[i+1][j] == mine)
					{
						mineCounter++;
					}
					if(sol_[i+1][j+1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i][j+1] == mine)
					{
						mineCounter++;
					}
				}
				else if(i == 0 && j == cols_ -1) //checks for top right corner
				{
					if(sol_[i+1][j] == mine)
					{
						mineCounter++;
					}
					if(sol_[i+1][j-1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i][j-1] == mine)
					{
						mineCounter++;
					}
				}
				else if(i == rows_-1 && j == 0) //checks for bottom left corner
				{
					if(sol_[i-1][j] == mine)
					{
						mineCounter++;
					}
					if(sol_[i-1][j+1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i][j+1] == mine)
					{
						mineCounter++;
					}
				}
				else if(i == rows_-1 && j == cols_-1) //checks for bottom right corner
				{
					if(sol_[i-1][j] == mine)
					{
						mineCounter++;
					}
					if(sol_[i-1][j-1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i][j-1] == mine)
					{
						mineCounter++;
					}
				}
				else if(i == 0) //case for top row
				{
					if(sol_[i][j-1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i+1][j-1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i+1][j] == mine)
					{
						mineCounter++;
					}
					if(sol_[i+1][j+1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i][j+1] == mine)
					{
						mineCounter++;
					}
				}
				else if(i == rows_-1) //case for bottom row
				{
					if(sol_[i][j-1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i-1][j-1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i-1][j] == mine)
					{
						mineCounter++;
					}
					if(sol_[i-1][j+1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i][j+1] == mine)
					{
						mineCounter++;
					}
				}
				else if(j == 0) //case for leftmost column
				{
					if(sol_[i-1][j] == mine)
					{
						mineCounter++;
					}
					if(sol_[i-1][j+1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i][j+1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i+1][j+1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i+1][j] == mine)
					{
						mineCounter++;
					}
				}
				else if(j == cols_ -1) //case for rightmost side
				{
					if(sol_[i-1][j] == mine)
					{
						mineCounter++;
					}
					if(sol_[i-1][j-1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i][j-1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i+1][j-1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i+1][j] == mine)
					{
						mineCounter++;
					}
				}
				else //other cases where we don't have to worry about bounds issues
				{
					if(sol_[i-1][j] == mine)
					{
						mineCounter++;
					}
					if(sol_[i-1][j-1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i][j-1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i+1][j-1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i+1][j] == mine)
					{
						mineCounter++;
					}
					if(sol_[i-1][j+1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i][j+1] == mine)
					{
						mineCounter++;
					}
					if(sol_[i+1][j+1] == mine)
					{
						mineCounter++;
					}
				}
				
				if(mineCounter == 0)
				{
					sol_[i][j] = empty;
				}
				else if(mineCounter == 1)
				{
					sol_[i][j] = one;
				}
				else if(mineCounter == 2)
				{
					sol_[i][j] = two;
				}
				else if(mineCounter == 3)
				{
					sol_[i][j] = three;
				}
				else if(mineCounter == 4) //sets the actual label corresponding to number of mines around it
				{							//ex if 5 mines are around this cell it will be labeled with a 5
					sol_[i][j] = four;
				}
				else if(mineCounter == 5)
				{
					sol_[i][j] = five;
				}
				else if(mineCounter == 6)
				{
					sol_[i][j] = six;
				}
				else if(mineCounter == 7)
				{
					sol_[i][j] = seven;
				}
				else
				{
					sol_[i][j] = eight;
				}
			}
		}
	}
	
	setLayout(new GridLayout(rows_, cols_, 0,0)); //creates layout of the panel
	cellArr_ = new Cell[rows_][cols_];
	panelArr_ = new JPanel[rows_][cols_]; //sets 2d array to whatever size the user wants
	for(int i = 0; i < rows_; i++) //for however many cells there are create a panel and add a Cell to it.
	{								//then add that panel to the Game Panel
		for(int j =0; j < cols_; j++)
		{
			panelArr_[i][j] = new JPanel();
			panelArr_[i][j].setLayout(new BorderLayout());
			cellArr_[i][j] = new Cell(sol_[i][j]);  //sets cells disabledIcon to the solution
			cellArr_[i][j].x_ = i;
			cellArr_[i][j].y_ = j; //labels the Cells x and y values for use later
			cellArr_[i][j].setIcon(unpressed);
			panelArr_[i][j].add(cellArr_[i][j], BorderLayout.CENTER);
			panelArr_[i][j].validate(); //needed to make sure the picture is right
			panelArr_[i][j].revalidate();

			cellArr_[i][j].addActionListener(this); //adds action listener to later click on it and reveal value
			cellArr_[i][j].addMouseListener(this); //adds mouse listener for the flags
			add(panelArr_[i][j]); //adds all of this panel to the actual game
			validate(); //makes sure it got added correctly
			revalidate();
		}
	}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource(); //gets source of button
		Cell sourceButton = (Cell)source;
		buttonClick(sourceButton, cellArr_, sol_); //RECURSIVE METHOD RAN WHEN A BUTTON IS CLICKED
		if(disabledCellCounter_ == rows_* cols_ - mines_) //if only the mines arent pressed you win
		{
			  for(int i = 0; i < rows_; i++)
			  {
				  for(int j = 0; j < cols_; j++)
				  {
					  cellArr_[i][j].setEnabled(false); //disables all buttons if you win
				  }
			  }
			  JOptionPane.showMessageDialog(null, "You Win!", "Winner!!!", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		  if (SwingUtilities.isRightMouseButton(arg0)) { //check for right click
			  Object source = arg0.getSource(); //gets which button was right clicked
			  JButton sourceButton = (JButton)source; //cast from object to jbutton
			  boolean isButton = false; 
			  for(int i = 0; i < rows_; i++) //makes sure the button is in the cell array
			  {
				  for(int j = 0; j < cols_; j++)
				  {
					  if(source == cellArr_[i][j])
					  {
						  isButton = true;
					  }
				  }
			  }
			  if(isButton)
			  {
				  if(sourceButton.getIcon().equals(unpressed)) //if not already a flag, make it a flag
				  {
					  sourceButton.setIcon(flag);
					  flagCounter_++;
					  if(sourceButton.getDisabledIcon() == mine)
					  {
						  correctFlagCounter_++; // correct flag counter and flag counter needed for
					  }							//checking the win conditions
				  }
				  else 
				  {
					sourceButton.setIcon(unpressed); //if already a flag, make it normal again
					flagCounter_--;
					 if(sourceButton.getDisabledIcon() == mine)
					  {
						 if(correctFlagCounter_ > 0) //respectively alter flag values
						 {
							 correctFlagCounter_--;
						 }
					  }
				  }
			  }
			  if(correctFlagCounter_ == flagCounter_ && flagCounter_ == mines_) //win condition if all mines are flagged
			  {
				  for(int i = 0; i < rows_; i++)
				  {
					  for(int j = 0; j < cols_; j++)
					  {
						  cellArr_[i][j].setEnabled(false); //disables all buttons if you win
					  }
				  }
				  JOptionPane.showMessageDialog(null, "You Win!", "Winner!!!", JOptionPane.INFORMATION_MESSAGE);
			  }
          }
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	private void buttonClick(Cell c, Cell[][] cellArray, ImageIcon[][] iconArray)
	{
		if(c.getIcon() == flag) {return;} //cant click on a flagged cell
		if(c.isEnabled() == false) //do nothing if button is already pressed
		{
			return;
		}
		if(c.getDisabledIcon() == mine)
		{
			for(int i = 0; i < rows_; i++)
			{
				for(int j = 0; j< cols_; j++)
				{
					if(cellArray[i][j].isEnabled())
					{
						cellArray[i][j].setEnabled(false); //if the button clicked is a mine, the game ends
					}
				}
			}
			JOptionPane.showMessageDialog(null, "You Lose!", "MINE!!!", JOptionPane.INFORMATION_MESSAGE);
			//c.setEnabled(false);
		}
		else if(c.getDisabledIcon() != empty) //if the button has a number value it just disables the button
		{
			c.setEnabled(false);
			disabledCellCounter_++;
			return;
		}
		else 
		{
			//the case when the cell has no mines around it
			//if clicked and empty it proceeds to click on every cell around it
			c.setEnabled(false);
			disabledCellCounter_++;
			int cellX = c.x_;
			int cellY = c.y_;
			if(c.x_ == 0 && c.y_ == 0) //top left corner case
			{
				buttonClick(cellArray[cellX][cellY+1], cellArray, iconArray);
				buttonClick(cellArray[cellX+1][cellY+1], cellArray, iconArray);
				buttonClick(cellArray[cellX+1][cellY], cellArray, iconArray);
			}
			else if(cellX == 0 && cellY == cols_-1) //top right corner case
			{
				buttonClick(cellArray[cellX][cellY-1], cellArray, iconArray);
				buttonClick(cellArray[cellX+1][cellY-1], cellArray, iconArray);
				buttonClick(cellArray[cellX+1][cellY], cellArray, iconArray);
			}
			else if(cellX == rows_-1 && cellY == 0) //bottom left corner case
			{
				buttonClick(cellArray[cellX][cellY+1], cellArray, iconArray);
				buttonClick(cellArray[cellX-1][cellY+1], cellArray, iconArray);
				buttonClick(cellArray[cellX-1][cellY], cellArray, iconArray);
			}
			else if(cellX == rows_-1 && cellY == cols_-1) //bottom right corner case
			{
				buttonClick(cellArray[cellX][cellY-1], cellArray, iconArray);
				buttonClick(cellArray[cellX-1][cellY-1], cellArray, iconArray);
				buttonClick(cellArray[cellX-1][cellY], cellArray, iconArray);
			}
			else if(cellX == 0) //cell pressed is on top row
			{
				buttonClick(cellArray[cellX][cellY-1], cellArray, iconArray);
				buttonClick(cellArray[cellX+1][cellY-1], cellArray, iconArray);
				buttonClick(cellArray[cellX+1][cellY], cellArray, iconArray);
				buttonClick(cellArray[cellX+1][cellY+1], cellArray, iconArray);
				buttonClick(cellArray[cellX][cellY+1], cellArray, iconArray);
			}
			else if(cellX == rows_-1) //cell pressed is on bottom row
			{
				buttonClick(cellArray[cellX][cellY-1], cellArray, iconArray);
				buttonClick(cellArray[cellX-1][cellY-1], cellArray, iconArray);
				buttonClick(cellArray[cellX-1][cellY], cellArray, iconArray);
				buttonClick(cellArray[cellX-1][cellY+1], cellArray, iconArray);
				buttonClick(cellArray[cellX][cellY+1], cellArray, iconArray);
			}
			else if(cellY == 0) //cell pressed is on leftmost column
			{
				buttonClick(cellArray[cellX-1][cellY],cellArray,iconArray);
				buttonClick(cellArray[cellX-1][cellY+1],cellArray,iconArray);
				buttonClick(cellArray[cellX][cellY+1],cellArray,iconArray);
				buttonClick(cellArray[cellX+1][cellY+1],cellArray,iconArray);
				buttonClick(cellArray[cellX+1][cellY],cellArray,iconArray);
			}
			else if(cellY == cols_-1) //cell pressed is on rightmost column
			{
				buttonClick(cellArray[cellX-1][cellY],cellArray,iconArray);
				buttonClick(cellArray[cellX-1][cellY-1],cellArray,iconArray);
				buttonClick(cellArray[cellX][cellY-1],cellArray,iconArray);
				buttonClick(cellArray[cellX+1][cellY-1],cellArray,iconArray);
				buttonClick(cellArray[cellX+1][cellY],cellArray,iconArray);
			}
			else //this else case we do not have to worry about bounds issues
			{
				buttonClick(cellArray[cellX-1][cellY],cellArray,iconArray);
				buttonClick(cellArray[cellX-1][cellY-1],cellArray,iconArray);
				buttonClick(cellArray[cellX][cellY-1],cellArray,iconArray);
				buttonClick(cellArray[cellX+1][cellY-1],cellArray,iconArray);
				buttonClick(cellArray[cellX+1][cellY],cellArray,iconArray);
				buttonClick(cellArray[cellX-1][cellY+1],cellArray,iconArray);
				buttonClick(cellArray[cellX][cellY+1],cellArray,iconArray);
				buttonClick(cellArray[cellX+1][cellY+1],cellArray,iconArray);
				
			}
		}
	}
	
}
