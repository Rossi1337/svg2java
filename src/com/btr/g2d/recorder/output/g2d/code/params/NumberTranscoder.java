package com.btr.g2d.recorder.output.g2d.code.params;

import java.io.IOException;
import java.io.Writer;

import com.btr.g2d.recorder.output.g2d.code.CodeContext;

/*****************************************************************************
 * Transcode a Number: int, float, double, ...
 *
 * @author Bernd Rosstauscher
 ****************************************************************************/

public class NumberTranscoder extends InlineParameterTranscoder{

	/*************************************************************************
	 * writeParameterUsageBlock
	 * @throws IOException 
	 * @see com.btr.g2d.recorder.output.g2d.code.params.ParameterTranscoder#writeParameterUsageBlock(com.btr.g2d.recorder.output.g2d.code.CodeContext, java.io.Writer, java.lang.Object)
	 ************************************************************************/
	@Override
	public void writeParameterUsageBlock(CodeContext context,
			Writer paramBlockWriter, Object object) throws IOException {
		Number n = (Number) object;
		paramBlockWriter.append(n.toString());
		if (n instanceof Float) {
			paramBlockWriter.append("f");
		}
	}

}
