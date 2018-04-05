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

	public void ScanConvertTriangle(
		Vertex minYVert,
		Vertex midYVert,
		Vertex maxYVert,
		int handedness)
	{
		this.ScanConvertLine(minYVert, maxYVert, 0 + handedness);
		this.ScanConvertLine(minYVert, midYVert, 1 - handedness);
		this.ScanConvertLine(midYVert, maxYVert, 1 - handedness);
	}

	private void ScanConvertLine(
		Vertex minYVert,
		Vertex maxYVert,
		int whichSide)
	{
		int yStart = (int)minYVert.getY();
		int yEnd = (int)maxYVert.getY();
		
		int xStart = (int)minYVert.getX();
		int xEnd = (int)maxYVert.getX();

		int yDist = yEnd - yStart;
		int xDist = xEnd - xStart;

		if(yDist <= 0)
		{
			return;
		}

		float xStep = (float)xDist / (float)yDist;
		float curX = (float)xStart;

		for(int j = yStart; j < yEnd; j++)
		{
			this._scanBuffer[j * 2 + whichSide] = (int)curX;
			curX += xStep;
		}
	}

}
