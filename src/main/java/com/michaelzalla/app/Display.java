package com.michaelzalla.app;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Display extends Canvas
{
	
	static final long serialVersionUID = 0;
	
	private final JFrame _frame;

	public Display(int width, int height, String title)
	{
		
		Dimension size = new Dimension(width, height);
		
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);

		this._frame = new JFrame();

		this._frame.add(this);

		this._frame.pack();

		this._frame.setResizable(false);
		this._frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this._frame.setTitle(title);

		this._frame.setVisible(true);

	}
}
