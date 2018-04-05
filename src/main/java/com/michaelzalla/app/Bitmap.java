package com.michaelzalla.app;

import java.util.Arrays;

public class Bitmap
{

	private static int NUM_COMPONENTS = 4;

	private final int _width;
	private final int _height;

	private byte _buffer[];

	Bitmap(int width, int height)
	{
		this._width = width;
		this._height = height;

		this._buffer = new byte[width * height * NUM_COMPONENTS];
	}

	public int GetWidth()
	{
		return _width;
	}
	
	public int GetHeight()
	{
		return _height;
	}

	public void Clear(byte shade)
	{
		Arrays.fill(this._buffer, shade);
	}

	public void DrawPixel(
		int x, int y,
		byte a,	byte b,	byte g,	byte r)
	{
		int index = (x + y * this._width) * NUM_COMPONENTS;

		this._buffer[index    ] = a;
		this._buffer[index + 1] = b;
		this._buffer[index + 2] = g;
		this._buffer[index + 3] = r;
	}

	public void CopyToByteArray(byte[] destination)
	{
		// Drops the alpha value when copying

		for(int i = 0; i < this._width * this._height; i++)
		{
			destination[i * 3    ] = this._buffer[NUM_COMPONENTS * i    ];
			destination[i * 3 + 1] = this._buffer[NUM_COMPONENTS * i + 1];
			destination[i * 3 + 2] = this._buffer[NUM_COMPONENTS * i + 2];
		}
	}

	// public void CopyToIntArray(int[] destination)
	// {
	// 	for(int i = 0; i < this._width * this._height; i++)
	// 	{
	// 		int pixelData =
	// 			this._buffer[NUM_COMPONENTS * i    ] << 24 | // a
	// 			this._buffer[NUM_COMPONENTS * i + 1] << 16 | // r
	// 			this._buffer[NUM_COMPONENTS * i + 2] << 8 |  // g
	// 			this._buffer[NUM_COMPONENTS * i + 3]; 		 // b

	// 		destination[i] = pixelData;
	// 	}
	// }

}
