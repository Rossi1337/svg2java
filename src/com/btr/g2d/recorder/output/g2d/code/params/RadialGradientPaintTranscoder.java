package com.btr.g2d.recorder.output.g2d.code.params;

import java.awt.RadialGradientPaint;
import java.io.IOException;
import java.io.Writer;

import com.btr.g2d.recorder.output.g2d.code.CodeContext;

/*****************************************************************************
 * Transcode a RadialGradientPaint
 *
 * @author Bernd Rosstauscher
 ****************************************************************************/

public class RadialGradientPaintTranscoder extends InlineParameterTranscoder {

	/*************************************************************************
	 * writeParameterUsageBlock
	 * @throws IOException 
	 * @see com.btr.g2d.recorder.output.g2d.code.params.ParameterTranscoder#writeParameterUsageBlock(com.btr.g2d.recorder.output.g2d.code.CodeContext, java.io.Writer, java.lang.Object)
	 ************************************************************************/
	@Override
	public void writeParameterUsageBlock(CodeContext context,
			Writer paramBlockWriter, Object object) throws IOException {
		RadialGradientPaint p = (RadialGradientPaint) object;
		// TODO rossi 11.07.2010 encode parameters correctly.
		paramBlockWriter.append(" new RadialGradientPaint(")
			.append(""+ p.getCenterPoint()).append(", ")
			.append(""+p.getRadius()).append(", ")
			.append(""+p.getFocusPoint()).append(", ")
			.append(""+p.getFractions()).append(", ")
			.append(""+p.getColors()).append(", ")
			.append(""+p.getCycleMethod()).append(", ")
			.append(""+p.getColorSpace()).append(", ")
			.append(""+p.getTransform())
			.append(")");
	}

}
