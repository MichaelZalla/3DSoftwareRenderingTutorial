package com.michaelzalla.app;

public class RenderContext extends Bitmap
{
	
	private final int[] _scanBuffer;

	public RenderContext(int width, int height)
	{
		super(width, height);

		this._scanBuffer = new int[height * 2];
	}

	public void DrawScanBuffer(int yCoord, int xMin, int xMax)
	{
		this._scanBuffer[yCoord * 2    ] = xMin;
		this._scanBuffer[yCoord * 2 + 1] = xMax;
	}

	public void FillShape(int yMin, int yMax)
	{
		for(int scanIndex = yMin; scanIndex < yMax; scanIndex++)
		{
			int xMin = this._scanBuffer[scanIndex * 2    ];
			int xMax = this._scanBuffer[scanIndex * 2 + 1];

			for(int xIndex = xMin; xIndex <= xMax; xIndex++)
			{
				this.DrawPixel(
					xIndex, scanIndex,
					(byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff
				);
			}

		}
	}

}
