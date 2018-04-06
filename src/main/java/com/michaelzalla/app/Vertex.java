package com.michaelzalla.app;

public class Vertex
{

	private Vector4f _pos;
	
	public Vertex(float x, float y, float z)
	{
		this._pos = new Vector4f(x, y, z, 1);
	}

	public Vertex(Vector4f pos)
	{
		this._pos = pos;
	}

	public Vertex Transform(Matrix4f transform)
	{
		return new Vertex(transform.Transform((this._pos)));
	}

	public float GetX()
	{
		return this._pos.GetX();
	}
	
	public float GetY()
	{
		return this._pos.GetY();
	}
	
	public float GetZ()
	{
		return this._pos.GetY();
	}
	
	public float GetW()
	{
		return this._pos.GetY();
	}

	public Vertex PerspectiveDivide()
	{
		return new Vertex(
			new Vector4f(
				this._pos.GetX() / this._pos.GetW(),
				this._pos.GetY() / this._pos.GetW(),
				this._pos.GetZ() / this._pos.GetW(),
				this._pos.GetW()
			)
		);
	}
	
	public float getTriangleAreaTimesTwo(Vertex b, Vertex c)
	{
		float x1 = b.GetX() - this._pos.GetX();
		float y1 = b.GetY() - this._pos.GetY();
		
		float x2 = c.GetX() - this._pos.GetX();
		float y2 = c.GetY() - this._pos.GetY();

		// Calculate the cross-product of the two vectors;

		return (x1 * y2 - x2 * y1);
	}

}