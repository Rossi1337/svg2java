package com.btr.g2d.recorder.output.g2d.code.params;

import java.awt.Font;
import java.io.IOException;
import java.io.Writer;

import com.btr.g2d.recorder.output.g2d.code.CodeContext;

/*****************************************************************************
 * Transcode a color
 *
 * @author Bernd Rosstauscher
 ****************************************************************************/

public class FontTranscoder extends InlineParameterTranscoder {

	/*************************************************************************
	 * writeParameterUsageBlock
	 * @throws IOException 
	 * @see com.btr.g2d.recorder.output.g2d.code.params.ParameterTranscoder#writeParameterUsageBlock(com.btr.g2d.recorder.output.g2d.code.CodeContext, java.io.Writer, java.lang.Object)
	 ************************************************************************/
	@Override
	public void writeParameterUsageBlock(CodeContext context,
			Writer paramBlockWriter, Object object) throws IOException {
		Font f = (Font) object;
		paramBlockWriter.append(" new Font(")
			.append("\""+f.getName()).append("\", ")
			.append(""+f.getStyle()).append(", ")
			.append(""+f.getSize()).append(")");
	}

}
