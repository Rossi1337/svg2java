package com.btr.g2d.recorder;

import com.btr.g2d.recorder.output.GraphicsTranscoder;

/*****************************************************************************
 * Special recorded method invocation for the create method.
 * This will hold a own recorder for the created child context.
 *
 * @author  Bernd Rosstauscher
 ****************************************************************************/

public class CreateMethodInvocation extends RecordedMethodInvocation {

	private Graphics2DRecorder childRecorder;

	/*************************************************************************
	 * Constructor
	 * Will always invoke the create method.
	 * @param params
	 ************************************************************************/

	public CreateMethodInvocation(Object... params) {
		super(params.length == 0?Graphics2DRecorder.create1:Graphics2DRecorder.create2, params);
		this.childRecorder = null;
	}

	/*************************************************************************
	 * setMethodName
	 * Will do nothing. This class is hardcoded to the "create" method.
	 * @see RecordedMethodInvocation#setMethodId(int)
	 ************************************************************************/
	@Override
	public void setMethodId(int methodId) {
		// Do nothing
	}

	/*************************************************************************
	 * Gets the recorder that is used for the created child context.
	 * @return Returns the childRecorder.
	 ************************************************************************/

	public Graphics2DRecorder getChildRecorder() {
		return this.childRecorder;
	}

	/*************************************************************************
	 * Sets the recorder that is used for the created child context.
	 * @param childRecorder The childRecorder to set.
	 ************************************************************************/

	public void setChildRecorder(Graphics2DRecorder childRecorder) {
		this.childRecorder = childRecorder;
	}

	/*************************************************************************
	 * transcode
	 * @see com.btr.g2d.recorder.RecordedMethodInvocation#transcode(com.btr.g2d.recorder.output.GraphicsTranscoder)
	 ************************************************************************/
	@Override
	public void transcode(GraphicsTranscoder transcoder) {
		GraphicsTranscoder childTranscoder = transcoder.transcodeCreateSubContext(this);
		if (this.childRecorder != null) {
			this.childRecorder.transcode(childTranscoder);
		}
	}

}
