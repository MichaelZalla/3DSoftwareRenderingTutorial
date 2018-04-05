package com.michaelzalla.app;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Dimension;

import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.image.DataBufferByte;

import javax.swing.JFrame;

public class Display extends Canvas
{
	
	static final long serialVersionUID = 0;
	
	private final JFrame _frame;
	private final RenderContext _frameBuffer;
	
	private final BufferedImage _displayImage;
	private final byte[] _displayComponents;
	private final BufferStrategy _bufferStrategy;
	
	private final Graphics _graphics;

	public Display(int width, int height, String title)
	{
		
		Dimension size = new Dimension(width, height);

		this._frameBuffer = new RenderContext(width, height);

		this._displayImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

		this._displayComponents = ((DataBufferByte)this._displayImage.getRaster().getDataBuffer()).getData();

		this._frameBuffer.Clear((byte)0x00);

		this._frameBuffer.DrawPixel(100, 100, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff);

		this.setWindowSize(size);

		// 

		this._frame = new JFrame();
		
		this._frame.add(this);
	
		this._frame.setResizable(false);
		this._frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this._frame.setTitle(title);
	
		this._frame.setVisible(true);

		this._frame.pack();

		// 

		this.createBufferStrategy(1);

		this._bufferStrategy = this.getBufferStrategy();

		this._graphics = this._bufferStrategy.getDrawGraphics();

	}

	public RenderContext GetFrameBuffer()
	{
		return this._frameBuffer;
	}

	public void SwapBuffers()
	{
		this._frameBuffer.CopyToByteArray(this._displayComponents);

		this._graphics.drawImage(
			this._displayImage,
			0, 0, this._frameBuffer.GetWidth(), this._frameBuffer.GetHeight(),
			null);

		this._bufferStrategy.show();
	}

	private void setWindowSize(Dimension size)
	{
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);
		this.setSize(size);
	}

}
