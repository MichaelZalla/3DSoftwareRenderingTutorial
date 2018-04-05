package com.michaelzalla.app;

public class Stars3D
{
	private final float _spread;
	private final float _speed;

	private final float _starsX[];
	private final float _starsY[];
	private final float _starsZ[];

	public Stars3D(int numStars, float spread, float speed)
	{
		this._spread = spread;
		
		this._speed = speed;

		this._starsX = new float[numStars];
		this._starsY = new float[numStars];
		this._starsZ = new float[numStars];

		for(int i = 0; i < this._starsX.length; i++)
		{
			this.InitStar(i);
		}
	}

	private void InitStar(int i)
	{
		this._starsX[i] = ((float)Math.random() * 2.0f - 1.0f) * this._spread;
		this._starsY[i] = ((float)Math.random() * 2.0f - 1.0f) * this._spread;
		this._starsZ[i] = ((float)Math.random() + 0.00001f) * this._spread;
	}

	public void UpdateAndRender(Bitmap target, float delta)
	{
		final float tanHalfFOV = (float) Math.tan(Math.toRadians(70.0f) / 2.0f);

		target.Clear((byte)0x00);

		float halfWidth = target.GetWidth() / 2.0f;
		float halfHeight = target.GetHeight() / 2.0f;
		
		for(int i = 0; i < this._starsX.length; i++)
		{

			this._starsZ[i] -= delta * this._speed;

			if(this._starsZ[i] <= 0)
			{
				// Respawn this star

				InitStar(i);
			}

			int x = (int)((this._starsX[i] / (tanHalfFOV * this._starsZ[i])) * halfWidth + halfWidth);
			int y = (int)((this._starsY[i] / (tanHalfFOV * this._starsZ[i]))  * halfHeight + halfHeight);

			if(
				(x < 0 || x >= target.GetWidth()) ||
				(y < 0 || y >= target.GetHeight())
			)
			{
				this.InitStar(i);
			}
			else
			{
				target.DrawPixel(
					x, y,
					(byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff
				);
			}

		}

	}

}
