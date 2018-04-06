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
import com.michaelzalla.app.Stars3D;

public class Main {
	
	public static void main(String[] args) {
		
		Display display = new Display(800, 600, "Good news, everyone!");

		ScanBufferBitmap target = display.GetFrameBuffer();

		Stars3D stars = new Stars3D(1800, 48.0f, 6.0f);

		Vertex minYVert = new Vertex(100, 100);
		Vertex midYVert = new Vertex(0, 200);
		Vertex maxYVert = new Vertex(80, 300);

		long previousTime = System.nanoTime();

		while (true) {
	
			long currentTime = System.nanoTime();

			float timeDelta = (float)((currentTime - previousTime) / 1000000000.0);

			// stars.UpdateAndRender(target, timeDelta);

			target.Clear((byte)0x00);

			target.FillTriangle(maxYVert, midYVert, minYVert);

			display.SwapBuffers();

			previousTime = currentTime;

		}
	
	}

}
