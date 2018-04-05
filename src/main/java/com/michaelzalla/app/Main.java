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

public class Main
{
	public static void main(String[] args)
	{
		
		Display display = new Display(800, 600, "Good news, everyone!");

	}
}
