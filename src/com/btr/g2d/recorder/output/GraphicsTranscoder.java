package com.btr.g2d.recorder.output;

import com.btr.g2d.recorder.CreateMethodInvocation;
import com.btr.g2d.recorder.RecordedMethodInvocation;

/*****************************************************************************
 * Simple interface for an transcoder that will produce some output for a stream
 * of RecordedMethodInvocations.
 * 
 * @author  Bernd Rosstauscher
 ****************************************************************************/

public interface GraphicsTranscoder {
	
	/*************************************************************************
	 * Called before the transcoding starts. Can be used for initialization.
	 ************************************************************************/
	
	public void startTranscoding();

	/*************************************************************************
	 * Called in sequence for every recorded Graphics2D method call
	 * @param methodCall information.
	 ************************************************************************/
	
	public void transcode(RecordedMethodInvocation methodCall);

	/*************************************************************************
	 * Called at the end of a successfull transcoding. May not be called if
	 * transcoding ends due to an exception. 
	 ************************************************************************/
	
	public void finishTranscoding();

	/*************************************************************************
	 * Called when a new subcontext needs to be created.
	 * @param createMethodInvocation to process.
	 * @return a transcoder that should be used to transcode all commands in 
	 * the scope of this sub context.
	 ************************************************************************/
	
	public GraphicsTranscoder transcodeCreateSubContext(CreateMethodInvocation createMethodInvocation);

}
