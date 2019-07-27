package yee;

import javax.swing.*;

public class Cell extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 42L;
	public int x_; //public so we can access it easily
	public int y_; //making getter methods also creates a weird formatting bug in grid layout
	
	private ImageIcon unpressed = //unpressed image
			new ImageIcon("C:\\Users\\Westin\\Desktop\\MinesweeperImages\\unpressed.png");
	public Cell(int x, int y, ImageIcon disabledIcon)
	{
		super();
		this.setVisible(true);
		this.setEnabled(true);
		this.setIcon(unpressed);
		this.setDisabledIcon(disabledIcon);
		x_ = x;
		y_ = y;
	}
	public Cell(ImageIcon disabledIcon)
	{
		super();
		this.setVisible(true);
		this.setEnabled(true);
		this.setIcon(unpressed);
		this.setDisabledIcon(disabledIcon);
	}
	
}
