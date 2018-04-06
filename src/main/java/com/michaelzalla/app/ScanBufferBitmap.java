package com.michaelzalla.app;

/**
 * Extend the Bitmap class to additionally hold a scan buffer, to quickly draw
 * filled triangles to the buffer;
 */
public class ScanBufferBitmap extends Bitmap
{

	private final int[] _scanBuffer;

	public ScanBufferBitmap(int width, int height)
	{
		super(width, height);

		// The scan buffer will hold 2 coordinates (xMin and xMax for each
		// possibly y-coordinate; note that this implementation restricts us to
		// only expressing convex shapes in the scan buffer; 

		this._scanBuffer = new int[height * 2];
	}

	/**
	 * Marks the min and max x-coordinates for a given y position in the scan
	 * buffer;
	 */
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
