package com.btr.svg2java;

/*****************************************************************************
 * Transcoder listener used to monitor the transcoding process.
 * Register it on the SvgTranscoder.
 *
 * @author  Bernd Rosstauscher 
 *         (svg2java(@)rosstauscher.de)
 ****************************************************************************/

public interface TranscoderListener {
	
	/*************************************************************************
	 * Called when the transcoding process begins.
	 ************************************************************************/
	
	public void started();

	/*************************************************************************
	 * Called when the transcoding process is finished.
	 ************************************************************************/
	
	public void finished();

	/*************************************************************************
	 * Called when we encounter an SVG operation that we can not transcode.
	 * @param node that is the cause of the error.
	 * @param message the error message.
	 ************************************************************************/
	
	public void onUnsupportedOperation(Object node, String message);
}