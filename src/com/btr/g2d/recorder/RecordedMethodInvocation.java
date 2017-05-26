package com.btr.g2d.recorder;

import com.btr.g2d.recorder.output.GraphicsTranscoder;

/*****************************************************************************
 * Small helper class that is used to record method invocations on
 * the Graphics2DRecorder.
 *
 * @author Bernd Rosstauscher
 ****************************************************************************/

public class RecordedMethodInvocation {

	private int methodId;
	private Object[] params;

	/*************************************************************************
	 * Constructor
	 * @param methodId the id of the method invoked.
	 * @param params the list of parameters.
	 ************************************************************************/

	public RecordedMethodInvocation(int methodId, Object...params) {
		super();
		this.methodId = methodId;
		this.params = params;
	}

	/*************************************************************************
	 * @return Returns the methodName.
	 ************************************************************************/

	public int getMethodId() {
		return this.methodId;
	}

	/*************************************************************************
	 * @param methodId The id to set.
	 ************************************************************************/

	public void setMethodId(int methodId) {
		this.methodId = methodId;
	}

	/*************************************************************************
	 * @return Returns the params.
	 ************************************************************************/

	public Object[] getParams() {
		return this.params;
	}

	/*************************************************************************
	 * @param params The params to set.
	 ************************************************************************/

	public void setParams(Object[] params) {
		this.params = params;
	}

	/*************************************************************************
	 * toString
	 * @see java.lang.Object#toString()
	 ************************************************************************/
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.methodId);
		if (this.params != null) {
			for (Object param : this.params) {
				sb.append(" ");
				sb.append(String.valueOf(param));
			}
		}
		return sb.toString();
	}

	/*************************************************************************
	 * The recorded method will stream all recorded data to a transcoder.
	 * @param transcoder to use.
	 ************************************************************************/
	
	public void transcode(GraphicsTranscoder transcoder) {
		transcoder.transcode(this);
	}

}
