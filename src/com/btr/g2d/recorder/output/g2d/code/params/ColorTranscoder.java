package com.btr.g2d.recorder.output.g2d.code.params;

import java.awt.Color;
import java.io.IOException;
import java.io.Writer;

import com.btr.g2d.recorder.output.g2d.code.CodeContext;

/*****************************************************************************
 * Transcode a color
 *
 * @author Bernd Rosstauscher
 ****************************************************************************/

public class ColorTranscoder extends InlineParameterTranscoder {

	/*************************************************************************
	 * writeParameterUsageBlock
	 * @throws IOException 
	 * @see com.btr.g2d.recorder.output.g2d.code.params.ParameterTranscoder#writeParameterUsageBlock(com.btr.g2d.recorder.output.g2d.code.CodeContext, java.io.Writer, java.lang.Object)
	 ************************************************************************/
	@Override
	public void writeParameterUsageBlock(CodeContext context,
			Writer paramBlockWriter, Object object) throws IOException {
		Color c = (Color) object;
		paramBlockWriter.append(" new Color(")
			.append(""+c.getRed()).append(", ")
			.append(""+c.getGreen()).append(", ")
			.append(""+c.getBlue()).append(", ")
			.append(""+c.getAlpha()).append(")");
	}

}
