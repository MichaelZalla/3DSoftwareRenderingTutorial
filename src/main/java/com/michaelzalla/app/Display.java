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
	
	private final ScanBufferBitmap _frameBuffer;
	
	private final BufferedImage _displayImage;
	private final byte[] _displayComponents;
	private final BufferStrategy _bufferStrategy;
	private final Graphics _graphics;

	public Display(int width, int height, String title)
	{
		
		Dimension size = new Dimension(width, height);

		// Instantiate a new ScanBufferBitmap (Bitmap) to holds a bitmap byte array
		// as well as a scan buffer for rendering; we set the dimensions of the
		// bitmap buffer (and by extension the scan buffer) at instantiation;

		// @NOTE(mzalla) This is the original frame buffer that will be copied
		// by our drawing context to the display buffer;

		this._frameBuffer = new ScanBufferBitmap(width, height);

		// In order to pass our image data to the graphics drawing context, we
		// have to provide it in a contextually clear format (i.e.,
		// BufferedImage instance), which the drawing context can query for
		// metadata (e.g., num channels, byte order, etc);

		this._displayImage = new BufferedImage(
			width, height,
			BufferedImage.TYPE_3BYTE_BGR);

		// We'll get a DataBufferByte handle to the region in memory where
		// _displayImage is actually storing the image data;

		this._displayComponents =
			((DataBufferByte)this._displayImage
				.getRaster()
				.getDataBuffer()
			).getData();

		// Start with a black background;

		this._frameBuffer.Clear((byte)0x00);

		// Draw a test (red) pixel

		// this._frameBuffer.DrawPixel(
		// 	100, 100,
		// 	(byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff);

		// Set size properties inherited from java.awt.Component;

		this.setWindowSize(size);

		// Instantiate a new JFrame, passing it this Canvas instance;

		this._frame = new JFrame();
		
		this._frame.add(this);
	
		this._frame.setResizable(false);
		this._frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this._frame.setTitle(title);
		this._frame.setVisible(true);

		// Resize the JFrame to snugly fit our Canvas dimensions;

		this._frame.pack();

		// Configure a new multi-buffer strategy based on the number of intended
		// buffers (in this case, 1); Canvas should default to a page-flipping
		// strategy; this means that when we draw to our Canvas instance, it may
		// write image data to a second buffer in memory;

		// Later, when we call this._bufferStrategy.show(), our Canvas should
		// update its current buffer reference to point to the 'new' buffer,
		// bringing about an instantaneous image update;

		this.createBufferStrategy(1);

		// Get a handle to the BufferStrategy so we can call .show() in our
		// SwapBuffers routine;

		this._bufferStrategy = this.getBufferStrategy();

		// Also get a handle to our 2D drawing context that operates on the
		// Canvas;

		this._graphics = this._bufferStrategy.getDrawGraphics();

	}

	public ScanBufferBitmap GetFrameBuffer()
	{
		// Expose the frame buffer to clients who wish to write data to it;

		return this._frameBuffer;
	}

	public void SwapBuffers()
	{
		// Copies frame buffer into the buffer memory set up by the DisplayImage
		// instance;

		this._frameBuffer.CopyToByteArray(this._displayComponents);

		// Use our 2D drawing context to draw the DisplayImage onto the Canvas;

		this._graphics.drawImage(
			this._displayImage,
			0, 0, this._frameBuffer.GetWidth(), this._frameBuffer.GetHeight(),
			null);

		// Page-flip routine;

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
