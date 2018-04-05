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
	
}