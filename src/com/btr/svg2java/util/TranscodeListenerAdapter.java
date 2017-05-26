package com.btr.svg2java.util;

import com.btr.svg2java.TranscoderListener;

/*****************************************************************************
 * Adapter class for the TranscodeListener.
 *
 * @author  Bernd Rosstauscher 
 *         (svg2java(@)rosstauscher.de)
 ****************************************************************************/

public class TranscodeListenerAdapter implements TranscoderListener {

	/*************************************************************************
	 * finished
	 * @see com.btr.svg2java.TranscoderListener#finished()
	 ************************************************************************/
	@Override
	public void finished() {
		// Override to add functionality
	}

	/*************************************************************************
	 * onUnsupportedOperation
	 * @see com.btr.svg2java.TranscoderListener#onUnsupportedOperation(java.lang.Object, java.lang.String)
	 ************************************************************************/
	@Override
	public void onUnsupportedOperation(Object node, String message) {
		// Override to add functionality
	}

	/*************************************************************************
	 * started
	 * @see com.btr.svg2java.TranscoderListener#started()
	 ************************************************************************/
	@Override
	public void started() {
		// Override to add functionality
	}
}