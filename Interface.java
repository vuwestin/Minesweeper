package yee;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class Interface extends JFrame implements ActionListener {

	private JLabel welcome_;
	private JLabel heading_;
	private JLabel rowLabel_ = new JLabel("Rows: ");
	private JLabel colLabel_ = new JLabel("Columns: "); //basic text labels
	private JLabel mineLabel_ = new JLabel("Mines: ");
	private JLabel warningLabel_ = new JLabel("");
	
	private JTextField rowInput_;
	private JTextField colInput_; //textfields for user input
	private JTextField mineInput_;
	
	private CardLayout cardLayout = new CardLayout(); //card layout to switch from creation to game
	
	private JPanel inputPanel_ = new JPanel();
	private JPanel gameHeader_ = new JPanel(); //Panels needed to help organize
	private JPanel gamePanel = new JPanel();
	
	private JButton gameHeaderButton_ = new JButton("Restart");
	private JButton makeGame_ = new JButton("Create");
	private JButton easy = new JButton("Easy"); //buttons for interface
	private JButton intermed = new JButton("Intermediate");
	private JButton expert = new JButton("Expert");
	private JMenuBar mainBar_= new JMenuBar();
	private JMenu file_ = new JMenu("File"); //menu to switch between making a game and playing one
	private JMenuItem exit_ = new JMenuItem("Exit");
	private JMenuItem createGame_ = new JMenuItem("Create Game");
	private Game g; //game made
	public Interface()
	{
		super("Minesweeper");
		setSize(500,500);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setLayout(cardLayout); //card layout to switch between creation and game
		setResizable(false);
		
		setJMenuBar(mainBar_);
		mainBar_.add(file_); //creates and sets menu for game creation screen switch
		file_.add(createGame_);
		file_.add(exit_);
		
		heading_ = new JLabel("       Please input the number of rows, columns, and mines for your game.                   ");
		welcome_ = new JLabel("Welcome to Minesweeper!");
		rowInput_ = new JTextField(8); //creates data members
		colInput_ = new JTextField(8);
		mineInput_ = new JTextField(8);
		
		inputPanel_.setLayout(new FlowLayout());
		inputPanel_.add(welcome_);
		inputPanel_.add(heading_);
		inputPanel_.add(rowLabel_);
		inputPanel_.add(rowInput_);
		inputPanel_.add(colLabel_);
		inputPanel_.add(colInput_); 
		inputPanel_.add(mineLabel_);
		inputPanel_.add(mineInput_);
		inputPanel_.add(easy);
		inputPanel_.add(intermed);
		inputPanel_.add(expert);
		inputPanel_.add(makeGame_);
		
		inputPanel_.add(warningLabel_);
		
		
		gameHeader_.add(gameHeaderButton_);
		
		gamePanel.setLayout(new BorderLayout());
		gamePanel.add(gameHeader_, BorderLayout.NORTH);
		
		easy.addActionListener(this);
		intermed.addActionListener(this);
		expert.addActionListener(this);
		gameHeaderButton_.addActionListener(this); //makes buttons functional
		makeGame_.addActionListener(this);
		createGame_.addActionListener(this);
		exit_.addActionListener(this);
		
		add(inputPanel_, "Panel1");
		add(gamePanel, "Panel2");
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		int rows;
		int cols;
		int mines;
		if(source == makeGame_) {
			try {
			rows = Integer.parseInt((rowInput_.getText()));
			cols = Integer.parseInt((colInput_.getText())); //checks for any invalid user input
			mines = Integer.parseInt((mineInput_.getText()));
			}
			catch(Exception ex) {
				warningLabel_.setText("Invalid Input: Please Try Again");
				return;
			}
			if(cols == 0 || rows == 0 || mines == 0)
			{
				warningLabel_.setText("Invalid Input: Inputs cannot be 0");
				return;
			}
			if(cols > 30 || rows > 16 || mines >= rows * cols)
			{
				warningLabel_.setText("Invalid Input: Inputs too large"); //cases where the games are not valid
				return;
			}
			if(cols < 3 || rows < 3)
			{
				warningLabel_.setText("Invalid Input: Rows and Columns must be at least 3.");
				return; //have to be at least 3 or else the text overflows with each other on menubar
			}
			g = new Game(rows,cols,mines); //creates game based on user input
			gamePanel.add(g, BorderLayout.CENTER);
			cardLayout.show(getContentPane(), "Panel2");
			setSize(55 * cols,(int)(55*(rows) + 70)); //scales the game accordingly
			gamePanel.revalidate(); //makes sure the game is painted correctly in case of making a new game
			gamePanel.repaint(); //repaints over the old game if the user makes a new game
		}
		else if(source == gameHeaderButton_) //restarts the game with a new game
		{
			rows = Integer.parseInt((rowInput_.getText()));
			cols = Integer.parseInt((colInput_.getText())); //no need to check for errors
			mines = Integer.parseInt((mineInput_.getText()));
			gamePanel.remove(g); //gets rid of the old game
			g = new Game(rows,cols,mines); //adds new one
			gamePanel.add(g, BorderLayout.CENTER);
			gamePanel.revalidate(); //need this to make sure the new game is added 
			gamePanel.repaint(); //repaints over the old game
		}
		else if(source == easy)
		{
			rowInput_.setText("9");
			colInput_.setText("9");
			mineInput_.setText("10");
		}
		else if(source == intermed)
		{
			rowInput_.setText("16");
			colInput_.setText("16");					//easy,intermediate, and expert values taken from original
			mineInput_.setText("40");
		}
		else if(source == expert)
		{
			rowInput_.setText("16");
			colInput_.setText("30");
			mineInput_.setText("99");
		}
		else if(source == createGame_)
		{
			cardLayout.show(getContentPane(), "Panel1");
			setSize(500,500);			//takes user back to create screen and resizes the window
			gamePanel.remove(g);
		}
		else if(source == exit_)
		{
			super.dispose(); //close the game
		}
	}

}
