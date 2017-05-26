package com.btr.g2d.recorder.output.g2d.code;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.RenderingHints.Key;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import com.btr.g2d.recorder.CreateMethodInvocation;
import com.btr.g2d.recorder.Graphics2DRecorder;
import com.btr.g2d.recorder.RecordedMethodInvocation;
import com.btr.g2d.recorder.output.GraphicsTranscoder;
import com.btr.g2d.recorder.output.g2d.code.params.ParameterTranscoder;
import com.btr.g2d.recorder.output.g2d.code.params.ParameterTranscoderFactory;

/*****************************************************************************
 * Generates Graphics2D paining code from the recorded painting calls.
 *
 * @author  Bernd Rosstauscher
 ****************************************************************************/

public class G2dCodeGenerator implements GraphicsTranscoder {
	
	private CodeContext context;
	private Writer destination;
	
	/*************************************************************************
	 * Constructor
	 * @param destination
	 ************************************************************************/
	
	public G2dCodeGenerator(Writer destination) {
		super();
		this.destination = destination;
	}

	/*************************************************************************
	 * startTranscoding
	 * @see com.btr.g2d.recorder.output.GraphicsTranscoder#startTranscoding()
	 ************************************************************************/
	@Override
	public void startTranscoding() {
		this.context = new CodeContext();
	}
	
	/*************************************************************************
	 * finishTranscoding
	 * @see com.btr.g2d.recorder.output.GraphicsTranscoder#finishTranscoding()
	 ************************************************************************/
	@Override
	public void finishTranscoding() {
		this.context = null;
		try {
			this.destination.close();
		} catch (IOException e) {
			// TODO rossi 11.07.2010 Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*************************************************************************
	 * @param context
	 * @param methodName
	 * @param params
	 * @throws IOException
	 ************************************************************************/
	
	private void writeMethod(CodeContext context, String methodName, Object ... params) throws IOException {
		String grName = "g2"; // TODO Bros 11.07.2010  
		Writer paramBlockWriter = new StringWriter();
		for (int i = 0; i < params.length; i++) {
			Object param = params[i];
			ParameterTranscoder paramTranscoder = findTranscoderForType(param);
			paramTranscoder.writeParameterBuildingBlock(context, destination, param);
			paramTranscoder.writeParameterUsageBlock(context, paramBlockWriter, param);
			if (i < params.length-1) {
				paramBlockWriter.append(", ");
			}
		}
		destination.append(grName).append(".").append(methodName).append("(");
		destination.append(paramBlockWriter.toString());
		destination.append(");\n");
	}

	/*************************************************************************
	 * @param object
	 * @return
	 ************************************************************************/
	
	private ParameterTranscoder findTranscoderForType(Object parameter) {
		return ParameterTranscoderFactory.getTranscoderForType(parameter);
	}

	/*************************************************************************
	 * transcode
	 * @see com.btr.g2d.recorder.output.GraphicsTranscoder#transcode(com.btr.g2d.recorder.RecordedMethodInvocation)
	 ************************************************************************/
	@Override
	public void transcode(RecordedMethodInvocation methodCall) {
		try {
			int methodId = methodCall.getMethodId();
			Object[] params = methodCall.getParams();
			switch (methodId) {
				case Graphics2DRecorder.addRenderingHints:
					writeMethod(context, "addRenderingHints", params[0]);
					break;
				case Graphics2DRecorder.clearRect:
					writeMethod(context, "clearRect", ((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue() );
					break;
				case Graphics2DRecorder.clip:
					writeMethod(context, "clip", (Shape)params[0]);
					break;
				case Graphics2DRecorder.clipRect:
					writeMethod(context, "clipRect", ((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue() );
					break;
				/*case Graphics2DRecorder.copyArea:
					destination.copyArea(((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue(), ((Integer)params[5]).intValue());
					break;*/
				case Graphics2DRecorder.create1:
					// Handled in transcodeCreateSubContext
					break;
				case Graphics2DRecorder.create2:
					// Handled in transcodeCreateSubContext
					break;
				case Graphics2DRecorder.dispose:
					writeMethod(context, "dispose");
					break;
				case Graphics2DRecorder.draw:
					writeMethod(context, "draw", params[0]);
					break;
				case Graphics2DRecorder.draw3DRect:
					writeMethod(context, "draw3DRect", ((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Boolean)params[4]).booleanValue() );
					break;
				case Graphics2DRecorder.drawArc:
					writeMethod(context, "drawArc", ((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue() , ((Integer)params[5]).intValue());
					break;
				case Graphics2DRecorder.drawBytes:
					writeMethod(context, "drawBytes", (byte[])params[0], ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue());
					break;
				case Graphics2DRecorder.drawChars:
					writeMethod(context, "drawChars", (char[])params[0], ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue());
					break;
				case Graphics2DRecorder.drawGlyphVector:
					writeMethod(context, "drawGlyphVector", (GlyphVector)params[0], ((Float)params[1]).floatValue(), ((Float)params[2]).floatValue());
					break;
				case Graphics2DRecorder.drawImage1:
					writeMethod(context, "drawImage", (BufferedImage)params[0], (BufferedImageOp)params[1], ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue());
					break;
				case Graphics2DRecorder.drawImage2:
					writeMethod(context, "drawImage", (Image)params[0], (AffineTransform)params[1], (ImageObserver)params[2]);
					break;
				case Graphics2DRecorder.drawImage3:
					writeMethod(context, "drawImage", (Image)params[0], ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), (Color)params[3], (ImageObserver)params[4]);
					break;
				case Graphics2DRecorder.drawImage4:
					writeMethod(context, "drawImage", (Image)params[0], ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), (ImageObserver)params[3]);
					break;
				case Graphics2DRecorder.drawImage5:
					writeMethod(context, "drawImage", (Image)params[0], ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue(), (Color)params[5], (ImageObserver)params[6]);
					break;
				case Graphics2DRecorder.drawImage6:
					writeMethod(context, "drawImage", (Image)params[0], ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue(), (ImageObserver)params[5]);
					break;
				case Graphics2DRecorder.drawImage7:
					writeMethod(context, "drawImage", (Image)params[0], ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue(), ((Integer)params[5]).intValue(), ((Integer)params[6]).intValue(), ((Integer)params[7]).intValue(), ((Integer)params[8]).intValue(), (Color)params[9], (ImageObserver)params[10]);
					break;
				case Graphics2DRecorder.drawImage8:
					writeMethod(context, "drawImage", (Image)params[0], ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue(), ((Integer)params[5]).intValue(), ((Integer)params[6]).intValue(), ((Integer)params[7]).intValue(), ((Integer)params[8]).intValue(), (ImageObserver)params[9]);
					break;
				case Graphics2DRecorder.drawLine:
					writeMethod(context, "drawLine", ((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue() );
					break;
				case Graphics2DRecorder.drawOval:
					writeMethod(context, "drawOval", ((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue() );
					break;
				case Graphics2DRecorder.drawPolygon1:
					writeMethod(context, "drawPolygon", (int[])params[0], (int[])params[1], ((Integer)params[2]).intValue());
					break;
				case Graphics2DRecorder.drawPolygon2:
					writeMethod(context, "drawPolygon", (Polygon)params[0]);
					break;
				case Graphics2DRecorder.drawPolyline:
					writeMethod(context, "drawPolyline", (int[])params[0], (int[])params[1], ((Integer)params[2]).intValue());
					break;
				case Graphics2DRecorder.drawRect:
					writeMethod(context, "drawRect", ((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue() );
					break;
				case Graphics2DRecorder.drawRenderableImage:
					writeMethod(context, "drawRenderableImage", (RenderableImage)params[0], (AffineTransform)params[1]);
					break;
				case Graphics2DRecorder.drawRenderedImage:
					writeMethod(context, "drawRenderedImage", (RenderedImage)params[0], (AffineTransform)params[1]);
					break;
				case Graphics2DRecorder.drawRoundRect:
					writeMethod(context, "drawRoundRect", ((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue(), ((Integer)params[5]).intValue() );
					break;
				case Graphics2DRecorder.drawString1:
					writeMethod(context, "drawString", (AttributedCharacterIterator)params[0], ((Float)params[1]).floatValue(), ((Float)params[2]).floatValue());
					break;
				case Graphics2DRecorder.drawString2:
					writeMethod(context, "drawString", (AttributedCharacterIterator)params[0], ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue());
					break;
				case Graphics2DRecorder.drawString3:
					writeMethod(context, "drawString", (String)params[0], ((Float)params[1]).floatValue(), ((Float)params[2]).floatValue());
					break;
				case Graphics2DRecorder.drawString4:
					writeMethod(context, "drawString", (String)params[0], ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue());
					break;
				/*case Graphics2DRecorder.equals:
					destination.equals(params[0]);
					break;*/
				case Graphics2DRecorder.fill:
					writeMethod(context, "fill", (Shape) params[0]);
					break;
				case Graphics2DRecorder.fill3DRect:
					writeMethod(context, "fill3DRect", ((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Boolean)params[4]).booleanValue() );
					break;
				case Graphics2DRecorder.fillArc:
					writeMethod(context, "fillArc", ((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue(), ((Integer)params[5]).intValue() );
					break;
				case Graphics2DRecorder.fillOval:
					writeMethod(context, "fillOval", ((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue());
					break;
				case Graphics2DRecorder.fillPolygon1:
					writeMethod(context, "fillPolygon", (int[])params[0], (int[])params[1], ((Integer)params[2]).intValue());
					break;
				case Graphics2DRecorder.fillPolygon2:
					writeMethod(context, "fillPolygon", (Polygon)params[0]);
					break;
				case Graphics2DRecorder.fillRect:
					writeMethod(context, "fillRect", ((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue());
					break;
				case Graphics2DRecorder.fillRoundRect:
					writeMethod(context, "fillRoundRect", ((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue(), ((Integer)params[5]).intValue() );
					break;
				/* getter methods are not recorded
				case Graphics2DRecorder.getBackground:
					return destination.getBackground();
				case Graphics2DRecorder.getClip:
					return destination.getClip();
				case Graphics2DRecorder.getClipBounds1:
					return destination.getClipBounds();
				case Graphics2DRecorder.getClipBounds2:
					return destination.getClipBounds((Rectangle) params[0]);
				case Graphics2DRecorder.getClipRect:
					return destination.getClipRect();
				case Graphics2DRecorder.getColor:
					return destination.getColor();
				case Graphics2DRecorder.getComposite:
					return destination.getComposite();
				case Graphics2DRecorder.getDeviceConfiguration:
					return destination.getDeviceConfiguration();
				case Graphics2DRecorder.getFont:
					return destination.getFont();
				case Graphics2DRecorder.getFontMetrics1:
					return destination.getFontMetrics();
				case Graphics2DRecorder.getFontMetrics2:
					return destination.getFontMetrics((Font) params[0]);
				case Graphics2DRecorder.getFontRenderContext:
					return destination.getFontRenderContext();
				case Graphics2DRecorder.getPaint:
					return destination.getPaint();
				case Graphics2DRecorder.getRenderingHint:
					return destination.getRenderingHint((Key) params[0]);
				case Graphics2DRecorder.getRenderingHints:
					return destination.getRenderingHints();
				case Graphics2DRecorder.getStroke:
					return destination.getStroke();
				case Graphics2DRecorder.getTransform:
					return destination.getTransform();
				case Graphics2DRecorder.hashCode:
					return destination.hashCode();
				case Graphics2DRecorder.hit:
					return destination.hit((Rectangle)params[0], (Shape)params[1], ((Boolean)params[2]).booleanValue());
				case Graphics2DRecorder.hitClip:
					return destination.hitClip(((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue());*/
				case Graphics2DRecorder.rotate1:
					writeMethod(context, "rotate", ((Double)params[0]).doubleValue(), ((Double)params[1]).doubleValue(), ((Double)params[2]).doubleValue());
					break;
				case Graphics2DRecorder.rotate2:
					writeMethod(context, "rotate", ((Double)params[0]).doubleValue());
					break;
				case Graphics2DRecorder.scale:
					writeMethod(context, "scale", ((Double)params[0]).doubleValue(), ((Double)params[1]).doubleValue());
					break;
				case Graphics2DRecorder.setBackground:
					writeMethod(context, "setBackground", (Color)params[0]);
					break;
				case Graphics2DRecorder.setClip1:
					writeMethod(context, "setClip", ((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue());
					break;
				case Graphics2DRecorder.setClip2:
					writeMethod(context, "setClip", (Shape)params[0]);
					break;
				case Graphics2DRecorder.setColor:
					writeMethod(context, "setColor", (Color)params[0]);
					break;
				case Graphics2DRecorder.setComposite:
					writeMethod(context, "setComposite", (Composite)params[0]);
					break;
				case Graphics2DRecorder.setFont:
					writeMethod(context, "setFont", (Font)params[0]);
					break;
				case Graphics2DRecorder.setPaint:
					writeMethod(context, "setPaint", (Paint)params[0]);
					break;
				case Graphics2DRecorder.setPaintMode:
					writeMethod(context, "setPaintMode");
					break;
				case Graphics2DRecorder.setRenderingHint:
					writeMethod(context, "setRenderingHint", (Key)params[0], params[1]);
					break;
				case Graphics2DRecorder.setRenderingHints:
					writeMethod(context, "setRenderingHints", (Map<?,?>)params[0]);
					break;
				case Graphics2DRecorder.setStroke:
					writeMethod(context, "setStroke", (Stroke)params[0]);
					break;
				case Graphics2DRecorder.setTransform:
					writeMethod(context, "setTransform", (AffineTransform)params[0]);
					break;
				case Graphics2DRecorder.setXORMode:
					writeMethod(context, "setXORMode", (Color)params[0]);
					break;
				case Graphics2DRecorder.shear:
					writeMethod(context, "setShear", ((Double)params[0]).doubleValue(), ((Double)params[1]).doubleValue());
					break;
				case Graphics2DRecorder.transform:
					writeMethod(context, "transform", (AffineTransform)params[0]);
					break;
				case Graphics2DRecorder.translate1:
					writeMethod(context, "translate", ((Double)params[0]).doubleValue(), ((Double)params[1]).doubleValue());
					break;
				case Graphics2DRecorder.translate2:
					writeMethod(context, "translate", ((Integer)params[0]).intValue(), ((Integer)params[1]).intValue());
					break;
				default:
					throw new UnsupportedOperationException("Error executing recorded paint operation "+methodId);
			}
		} catch (IOException e) {
			// TODO rossi 11.07.2010 Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*************************************************************************
	 * transcodeCreateSubContext
	 * @see com.btr.g2d.recorder.output.GraphicsTranscoder#transcodeCreateSubContext(com.btr.g2d.recorder.CreateMethodInvocation)
	 ************************************************************************/
	@Override
	public GraphicsTranscoder transcodeCreateSubContext(
			CreateMethodInvocation createMethodInvocation) {
		// TODO rossi 11.07.2010
		return this;
	}

}
