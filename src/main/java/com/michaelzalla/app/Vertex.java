package com.michaelzalla.app;

public class Vertex
{
	
	private float _x;
	private float _y;

	Vertex(float x, float y)
	{
		this._x = x;
		this._y = y;
	}

	public float getX()
	{
		return this._x;
	}
	
	public void setX(float x)
	{
		this._x = x;
	}
	
	public float getY()
	{
		return this._y;
	}
	
	public void setY(float y)
	{
		this._y = y;
	}

	public float getTriangleAreaTimesTwo(Vertex b, Vertex c)
	{
		float x1 = b.getX() - this._x;
		float y1 = b.getY() - this._y;
		
		float x2 = c.getX() - this._x;
		float y2 = c.getY() - this._y;

		// Calculate the cross-product of the two vectors;

		return (x1 * y2 - x2 * y1);
	}

}