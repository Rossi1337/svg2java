package com.btr.g2d.recorder.output.g2d.code.params;

import java.io.Writer;

import com.btr.g2d.recorder.output.g2d.code.CodeContext;

/*****************************************************************************
 * Transcoder for all parameters that can be transcoded inline.
 *
 * @author Bernd Rosstauscher
 ****************************************************************************/

public abstract class InlineParameterTranscoder implements ParameterTranscoder {

	/*************************************************************************
	 * writeParameterBuildingBlock
	 * @see com.btr.g2d.recorder.output.g2d.code.params.ParameterTranscoder#writeParameterBuildingBlock(com.btr.g2d.recorder.output.g2d.code.CodeContext, java.io.Writer, java.lang.Object)
	 ************************************************************************/
	@Override
	public void writeParameterBuildingBlock(CodeContext context,
			Writer initBlockWriter, Object object) {
		// Not used
	}

}