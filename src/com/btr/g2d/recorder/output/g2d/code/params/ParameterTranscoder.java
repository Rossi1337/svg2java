package com.btr.g2d.recorder.output.g2d.code.params;

import java.io.IOException;
import java.io.Writer;

import com.btr.g2d.recorder.output.g2d.code.CodeContext;

/*****************************************************************************
 * Transcode a given parameter type.
 *
 * @author Bernd Rosstauscher
 * @version 1.0
 ****************************************************************************/

public interface ParameterTranscoder {

	/*************************************************************************
	 * @param context
	 * @param initBlockWriter
	 * @param object
	 * @throws IOException
	 ************************************************************************/
	
	public void writeParameterBuildingBlock(CodeContext context,
			Writer initBlockWriter, Object object) throws IOException;

	/*************************************************************************
	 * @param context
	 * @param paramBlockWriter
	 * @param object
	 * @throws IOException
	 ************************************************************************/
	
	public void writeParameterUsageBlock(CodeContext context,
			Writer paramBlockWriter, Object object) throws IOException;

}
