// Unlike the first command executed (archetype:generate) you may notice the second is simply a single word - package. Rather than a goal, this is a phase. A phase is a step in the build lifecycle, which is an ordered sequence of phases. When a phase is given, Maven will execute every phase in the sequence up to and including the one defined. For example, if we execute the compile phase, the phases that actually get executed are:
// 
// - validate
// - generate-sources
// - process-sources
// - generate-resources
// - process-resources
// - compile

package com.michaelzalla.app;

import com.michaelzalla.app.Display;
import com.michaelzalla.app.ScanBufferBitmap;

public class Main {
	
	public static void main(String[] args) {
		
		Display display = new Display(800, 600, "Good news, everyone!");

		ScanBufferBitmap target = display.GetFrameBuffer();

		Vertex minYVert = new Vertex(-1, -1, 0);
		Vertex midYVert = new Vertex(0, 1, 0);
		Vertex maxYVert = new Vertex(1, -1, 0);

		Matrix4f projection = new Matrix4f().InitPerspective(
			(float)Math.toRadians(70.0f),
			(float)target.GetWidth()/(float)target.GetHeight(),
			0.1f,
			1000.0f);

		float rotationCounter = 0.0f;

		long previousTime = System.nanoTime();

		while (true) {
	
			long currentTime = System.nanoTime();

			float timeDelta = (float)((currentTime - previousTime) / 1000000000.0);

			previousTime = currentTime;

			rotationCounter += timeDelta;

			Matrix4f translation = new Matrix4f().InitTranslation(0.0f, 0.0f, 3.0f);
			Matrix4f rotation = new Matrix4f().InitRotation(0.0f, rotationCounter, 0.0f);
			Matrix4f transform = projection.Mul(translation.Mul(rotation));
			
			target.Clear((byte)0x00);

			target.FillTriangle(
				maxYVert.Transform(transform),
				midYVert.Transform(transform),
				minYVert.Transform(transform)
			);

			display.SwapBuffers();

		}
	
	}

}
