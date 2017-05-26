package com.btr.g2d.recorder.output.g2d.code.params;

import java.awt.LinearGradientPaint;
import java.io.IOException;
import java.io.Writer;

import com.btr.g2d.recorder.output.g2d.code.CodeContext;

/*****************************************************************************
 * Transcode a LinearGradientPaint
 *
 * @author Bernd Rosstauscher
 ****************************************************************************/

public class LinearGradientPaintTranscoder extends InlineParameterTranscoder {

	/*************************************************************************
	 * writeParameterUsageBlock
	 * @throws IOException 
	 * @see com.btr.g2d.recorder.output.g2d.code.params.ParameterTranscoder#writeParameterUsageBlock(com.btr.g2d.recorder.output.g2d.code.CodeContext, java.io.Writer, java.lang.Object)
	 ************************************************************************/
	@Override
	public void writeParameterUsageBlock(CodeContext context,
			Writer paramBlockWriter, Object object) throws IOException {
		LinearGradientPaint p = (LinearGradientPaint) object;
		// TODO rossi 11.07.2010 encode parameters correctly.
		paramBlockWriter.append(" new LinearGradientPaint(")
			.append(""+ p.getStartPoint()).append(", ")
			.append(""+p.getEndPoint()).append(", ")
			.append(""+p.getFractions()).append(", ")
			.append(""+p.getColors()).append(", ")
			.append(""+p.getCycleMethod()).append(", ")
			.append(""+p.getColorSpace()).append(", ")
			.append(""+p.getTransform())
			.append(")");
	}

}
