package com.btr.g2d.recorder.output.g2d.replay;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
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
import java.text.AttributedCharacterIterator;
import java.util.Map;

import com.btr.g2d.recorder.CreateMethodInvocation;
import com.btr.g2d.recorder.Graphics2DRecorder;
import com.btr.g2d.recorder.RecordedMethodInvocation;
import com.btr.g2d.recorder.output.GraphicsTranscoder;

/*****************************************************************************
 * A simple transcoder that will replay the recorded method calls against a 
 * given Graphics2D painting canvas.
 *
 * @author  Bernd Rosstauscher
 ****************************************************************************/

public class ReplayTranscoder implements GraphicsTranscoder {
	
	private Graphics2D destination;
	
	/*************************************************************************
	 * Constructor
	 * @param destination to paint to.
	 ************************************************************************/
	
	public ReplayTranscoder(Graphics2D destination) {
		super();
		this.destination = destination;
	}
	

	/*************************************************************************
	 * finishTranscoding
	 * @see com.btr.g2d.recorder.output.GraphicsTranscoder#finishTranscoding()
	 ************************************************************************/
	@Override
	public void finishTranscoding() {
		// Not used.
	}

	/*************************************************************************
	 * startTranscoding
	 * @see com.btr.g2d.recorder.output.GraphicsTranscoder#startTranscoding()
	 ************************************************************************/
	@Override
	public void startTranscoding() {
		// Not used.
	}

	/*************************************************************************
	 * transcode
	 * @see com.btr.g2d.recorder.output.GraphicsTranscoder#transcode(com.btr.g2d.recorder.RecordedMethodInvocation)
	 ************************************************************************/
	@Override
	public void transcode(RecordedMethodInvocation methodCall) {
		int methodId = methodCall.getMethodId();
		Object[] params = methodCall.getParams();
		switch (methodId) {
			case Graphics2DRecorder.addRenderingHints:
				destination.addRenderingHints((Map<?, ?>) params[0]);
				break;
			case Graphics2DRecorder.clearRect:
				destination.clearRect(((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue() );
				break;
			case Graphics2DRecorder.clip:
				destination.clip((Shape)params[0]);
				break;
			case Graphics2DRecorder.clipRect:
				destination.clipRect(((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue() );
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
				destination.dispose();
				break;
			case Graphics2DRecorder.draw:
				destination.draw((Shape)params[0]);
				break;
			case Graphics2DRecorder.draw3DRect:
				destination.draw3DRect(((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Boolean)params[4]).booleanValue() );
				break;
			case Graphics2DRecorder.drawArc:
				destination.drawArc(((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue() , ((Integer)params[5]).intValue());
				break;
			case Graphics2DRecorder.drawBytes:
				destination.drawBytes((byte[])params[0], ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue());
				break;
			case Graphics2DRecorder.drawChars:
				destination.drawChars((char[])params[0], ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue());
				break;
			case Graphics2DRecorder.drawGlyphVector:
				destination.drawGlyphVector((GlyphVector)params[0], ((Float)params[1]).floatValue(), ((Float)params[2]).floatValue());
				break;
			case Graphics2DRecorder.drawImage1:
				destination.drawImage((BufferedImage)params[0], (BufferedImageOp)params[1], ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue());
				break;
			case Graphics2DRecorder.drawImage2:
				destination.drawImage((Image)params[0], (AffineTransform)params[1], (ImageObserver)params[2]);
				break;
			case Graphics2DRecorder.drawImage3:
				destination.drawImage((Image)params[0], ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), (Color)params[3], (ImageObserver)params[4]);
				break;
			case Graphics2DRecorder.drawImage4:
				destination.drawImage((Image)params[0], ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), (ImageObserver)params[3]);
				break;
			case Graphics2DRecorder.drawImage5:
				destination.drawImage((Image)params[0], ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue(), (Color)params[5], (ImageObserver)params[6]);
				break;
			case Graphics2DRecorder.drawImage6:
				destination.drawImage((Image)params[0], ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue(), (ImageObserver)params[5]);
				break;
			case Graphics2DRecorder.drawImage7:
				destination.drawImage((Image)params[0], ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue(), ((Integer)params[5]).intValue(), ((Integer)params[6]).intValue(), ((Integer)params[7]).intValue(), ((Integer)params[8]).intValue(), (Color)params[9], (ImageObserver)params[10]);
				break;
			case Graphics2DRecorder.drawImage8:
				destination.drawImage((Image)params[0], ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue(), ((Integer)params[5]).intValue(), ((Integer)params[6]).intValue(), ((Integer)params[7]).intValue(), ((Integer)params[8]).intValue(), (ImageObserver)params[9]);
				break;
			case Graphics2DRecorder.drawLine:
				destination.drawLine(((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue() );
				break;
			case Graphics2DRecorder.drawOval:
				destination.drawOval(((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue() );
				break;
			case Graphics2DRecorder.drawPolygon1:
				destination.drawPolygon((int[])params[0], (int[])params[1], ((Integer)params[2]).intValue());
				break;
			case Graphics2DRecorder.drawPolygon2:
				destination.drawPolygon((Polygon)params[0]);
				break;
			case Graphics2DRecorder.drawPolyline:
				destination.drawPolyline((int[])params[0], (int[])params[1], ((Integer)params[2]).intValue());
				break;
			case Graphics2DRecorder.drawRect:
				destination.drawRect(((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue() );
				break;
			case Graphics2DRecorder.drawRenderableImage:
				destination.drawRenderableImage((RenderableImage)params[0], (AffineTransform)params[1]);
				break;
			case Graphics2DRecorder.drawRenderedImage:
				destination.drawRenderedImage((RenderedImage)params[0], (AffineTransform)params[1]);
				break;
			case Graphics2DRecorder.drawRoundRect:
				destination.drawRoundRect(((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue(), ((Integer)params[5]).intValue() );
				break;
			case Graphics2DRecorder.drawString1:
				destination.drawString((AttributedCharacterIterator)params[0], ((Float)params[1]).floatValue(), ((Float)params[2]).floatValue());
				break;
			case Graphics2DRecorder.drawString2:
				destination.drawString((AttributedCharacterIterator)params[0], ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue());
				break;
			case Graphics2DRecorder.drawString3:
				destination.drawString((String)params[0], ((Float)params[1]).floatValue(), ((Float)params[2]).floatValue());
				break;
			case Graphics2DRecorder.drawString4:
				destination.drawString((String)params[0], ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue());
				break;
			/*case Graphics2DRecorder.equals:
				destination.equals(params[0]);
				break;*/
			case Graphics2DRecorder.fill:
				destination.fill((Shape) params[0]);
				break;
			case Graphics2DRecorder.fill3DRect:
				destination.fill3DRect(((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Boolean)params[4]).booleanValue() );
				break;
			case Graphics2DRecorder.fillArc:
				destination.fillArc(((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue(), ((Integer)params[5]).intValue() );
				break;
			case Graphics2DRecorder.fillOval:
				destination.fillOval(((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue());
				break;
			case Graphics2DRecorder.fillPolygon1:
				destination.fillPolygon((int[])params[0], (int[])params[1], ((Integer)params[2]).intValue());
				break;
			case Graphics2DRecorder.fillPolygon2:
				destination.fillPolygon((Polygon)params[0]);
				break;
			case Graphics2DRecorder.fillRect:
				destination.fillRect(((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue());
				break;
			case Graphics2DRecorder.fillRoundRect:
				destination.fillRoundRect(((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue(), ((Integer)params[4]).intValue(), ((Integer)params[5]).intValue() );
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
				destination.rotate(((Double)params[0]).doubleValue(), ((Double)params[1]).doubleValue(), ((Double)params[2]).doubleValue());
				break;
			case Graphics2DRecorder.rotate2:
				destination.rotate(((Double)params[0]).doubleValue());
				break;
			case Graphics2DRecorder.scale:
				destination.scale(((Double)params[0]).doubleValue(), ((Double)params[1]).doubleValue());
				break;
			case Graphics2DRecorder.setBackground:
				destination.setBackground((Color)params[0]);
				break;
			case Graphics2DRecorder.setClip1:
				destination.setClip(((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue());
				break;
			case Graphics2DRecorder.setClip2:
				destination.setClip((Shape)params[0]);
				break;
			case Graphics2DRecorder.setColor:
				destination.setColor((Color)params[0]);
				break;
			case Graphics2DRecorder.setComposite:
				destination.setComposite((Composite)params[0]);
				break;
			case Graphics2DRecorder.setFont:
				destination.setFont((Font)params[0]);
				break;
			case Graphics2DRecorder.setPaint:
				destination.setPaint((Paint)params[0]);
				break;
			case Graphics2DRecorder.setPaintMode:
				destination.setPaintMode();
				break;
			case Graphics2DRecorder.setRenderingHint:
				destination.setRenderingHint((Key)params[0], params[1]);
				break;
			case Graphics2DRecorder.setRenderingHints:
				destination.setRenderingHints((Map<?,?>)params[0]);
				break;
			case Graphics2DRecorder.setStroke:
				destination.setStroke((Stroke)params[0]);
				break;
			case Graphics2DRecorder.setTransform:
				destination.setTransform((AffineTransform)params[0]);
				break;
			case Graphics2DRecorder.setXORMode:
				destination.setXORMode((Color)params[0]);
				break;
			case Graphics2DRecorder.shear:
				destination.shear(((Double)params[0]).doubleValue(), ((Double)params[1]).doubleValue());
				break;
			case Graphics2DRecorder.transform:
				destination.transform((AffineTransform)params[0]);
				break;
			case Graphics2DRecorder.translate1:
				destination.translate(((Double)params[0]).doubleValue(), ((Double)params[1]).doubleValue());
				break;
			case Graphics2DRecorder.translate2:
				destination.translate(((Integer)params[0]).intValue(), ((Integer)params[1]).intValue());
				break;
			default:
				throw new UnsupportedOperationException("Error executing recorded paint operation "+methodId);
		}
	}

	/*************************************************************************
	 * transcodeCreateSubContext
	 * @see com.btr.g2d.recorder.output.GraphicsTranscoder#transcodeCreateSubContext(com.btr.g2d.recorder.CreateMethodInvocation)
	 ************************************************************************/
	@Override
	public GraphicsTranscoder transcodeCreateSubContext(CreateMethodInvocation methodCall) {
		Graphics2D subContext = null;
		int methodId = methodCall.getMethodId();
		Object[] params = methodCall.getParams();
		switch (methodId) {
			case Graphics2DRecorder.create1:
				subContext = (Graphics2D) destination.create();
				break;
			case Graphics2DRecorder.create2:
				subContext = (Graphics2D) destination.create(((Integer)params[0]).intValue(), ((Integer)params[1]).intValue(), ((Integer)params[2]).intValue(), ((Integer)params[3]).intValue());
				break;
			default :
				throw new UnsupportedOperationException("Create method not supported "+methodId);
		}
		return new ReplayTranscoder(subContext);
	}

}
