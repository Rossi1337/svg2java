package com.btr.g2d.recorder.output.g2d.code.params;

import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.Writer;

import com.btr.g2d.recorder.output.g2d.code.CodeContext;

/*****************************************************************************
 * Transcode a affine transformation.
 *
 * @author Bernd Rosstauscher
 ****************************************************************************/

public class AffineTransformTranscoder extends InlineParameterTranscoder {

	/*************************************************************************
	 * writeParameterUsageBlock
	 * @throws IOException 
	 * @see com.btr.g2d.recorder.output.g2d.code.params.ParameterTranscoder#writeParameterUsageBlock(com.btr.g2d.recorder.output.g2d.code.CodeContext, java.io.Writer, java.lang.Object)
	 ************************************************************************/
	@Override
	public void writeParameterUsageBlock(CodeContext context,
			Writer paramBlockWriter, Object object) throws IOException {
		AffineTransform af = (AffineTransform) object;
		paramBlockWriter.append(" new AffineTransform(")
			.append(""+af.getScaleX()).append(", ")
			.append(""+af.getShearY()).append(", ")
			.append(""+af.getShearX()).append(", ")
			.append(""+af.getScaleY()).append(", ")
			.append(""+af.getTranslateX()).append(", ")
			.append(""+af.getTranslateY()).append(")");
	}

}
