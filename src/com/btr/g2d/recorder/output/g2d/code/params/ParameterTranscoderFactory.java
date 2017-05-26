package com.btr.g2d.recorder.output.g2d.code.params;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.LinearGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

/*****************************************************************************
 * Creates a transcoder for a given parameter data type.
 *
 * $Id:  $ 
 *
 * @author  BROS
 * @version 1.0
 ****************************************************************************/

public class ParameterTranscoderFactory {
	
	private static class Entry {
		Class<?> effectivity;
		ParameterTranscoder transcoder;
		
		public Entry(Class<?> effectivity, ParameterTranscoder transcoder) {
			this.effectivity = effectivity;
			this.transcoder = transcoder;
		}
		
	}
	
	private static List<Entry> registry = new ArrayList<Entry>();
	static {
		registry.add(new Entry(Color.class, new ColorTranscoder()));
		registry.add(new Entry(Font.class, new FontTranscoder()));
		registry.add(new Entry(AffineTransform.class, new AffineTransformTranscoder()));
		registry.add(new Entry(AlphaComposite.class, new AlphaCompositeTranscoder()));
		registry.add(new Entry(LinearGradientPaint.class, new LinearGradientPaintTranscoder()));
		registry.add(new Entry(RadialGradientPaint.class, new RadialGradientPaintTranscoder()));
		registry.add(new Entry(Number.class, new NumberTranscoder()));
		registry.add(new Entry(String.class, new StringTranscoder()));
	}
	
	/*************************************************************************
	 * @param o object to find a transcoder for.
	 * @return the transcoder parameter.
	 ************************************************************************/
	
	public static ParameterTranscoder getTranscoderForType(Object o) {
		if (o != null) {
			Class<?> c = o.getClass();
			for (Entry e : registry) {
				if (e.effectivity.isAssignableFrom(c)) {
					return e.transcoder;
				}
			}
			System.out.println("No transcoder for type available "+o.getClass());
		}
		return new StringTranscoder();
	}

}
