package com.btr.svg2java;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

/*****************************************************************************
 * Interface for a transcoder that can be used to convert SVG to another
 * output format.
 *
 * @author  Bernd Rosstauscher 
 *         (svg2java(@)rosstauscher.de)
 ****************************************************************************/

public interface SvgTranscoder {

	/*************************************************************************
	 * Registers a listener that is called for different events during the 
	 * transcoding process. Start, end, on error.
	 * @param listener the TranscoderListener to register.
	 ************************************************************************/
	
	public void addListener(TranscoderListener listener);

	/*************************************************************************
	 * Removes a previously registered listener.
	 * @param listener the TranscoderListener to deregister.
	 * @return true if the listener was removed, false if the listener was not
	 * 			previously registered.
	 ************************************************************************/

	public boolean removeListener(TranscoderListener listener);

	/*************************************************************************
	 * Transcodes the SVG image into Java2D code. 
	 * @param source InputStream of the SVG image.
	 * @param javaClassname
	 *            Classname for the generated Java code.
	 * @param destination a writer to send the transcoded result to.
	 * @throws IOException on reading or writing error.
	 ************************************************************************/
	
	public void transcode(InputStream source, Writer destination,
			String javaClassname) throws IOException;

}
