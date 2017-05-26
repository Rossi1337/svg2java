package com.btr.g2d.recorder;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.RenderingHints.Key;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Map;

import com.btr.g2d.recorder.output.GraphicsTranscoder;

/*****************************************************************************
 * This class will record all the graphics operations invoked on it.
 * There is a replay method to execute all the recorded paint commands on
 * another Graphics2D context.
 *
 * We only record the the methods that affect painting. The getters are not needed.
 *
 * @author  Bernd Rosstauscher
 ****************************************************************************/

public class Graphics2DRecorder extends Graphics2D {

	public final static int addRenderingHints = 0;
	public final static int clearRect = 1;
	public final static int clip = 2;
	public final static int clipRect = 3;
	public final static int dispose = 4;
	public final static int draw = 5;
	public final static int draw3DRect = 6;
	public final static int drawArc = 7;
	public final static int drawBytes = 8;
	public final static int drawChars = 9;
	public final static int drawGlyphVector = 10;
	public final static int drawImage1 = 11;
	public final static int drawImage2 = 12;
	public final static int drawImage3 = 13;
	public final static int drawImage4 = 14;
	public final static int drawImage5 = 15;
	public final static int drawImage6 = 16;
	public final static int drawImage7 = 17;
	public final static int drawImage8 = 18;
	public final static int drawLine = 19;
	public final static int drawOval = 20;
	public final static int drawPolygon1 = 21;
	public final static int drawPolygon2 = 22;
	public final static int drawPolyline = 23;
	public final static int drawRect = 24;
	public final static int drawRenderableImage = 25;
	public final static int drawRenderedImage = 26;
	public final static int drawRoundRect = 27;
	public final static int drawString1 = 28;
	public final static int drawString2 = 29;
	public final static int drawString3 = 30;
	public final static int drawString4 = 31;
	public final static int fill = 32;
	public final static int fill3DRect = 33;
	public final static int fillArc = 34;
	public final static int fillOval = 35;
	public final static int fillPolygon1 = 36;
	public final static int fillPolygon2 = 37;
	public final static int fillRect = 38;
	public final static int fillRoundRect = 39;
	public final static int rotate1 = 40;
	public final static int rotate2 = 41;
	public final static int scale = 42;
	public final static int setBackground = 43;
	public final static int setClip1 = 44;
	public final static int setClip2 = 45;
	public final static int setColor = 46;
	public final static int setComposite = 47;
	public final static int setFont = 48;
	public final static int setPaint = 49;
	public final static int setPaintMode = 50;
	public final static int setRenderingHint = 51;
	public final static int setRenderingHints = 52;
	public final static int setStroke = 53;
	public final static int setTransform = 54;
	public final static int setXORMode = 55;
	public final static int shear = 56;
	public final static int transform = 57;
	public final static int translate1 = 58;
	public final static int translate2 = 59;
	public final static int create1 = 60;
	public final static int create2 = 61;

	private ArrayList<RecordedMethodInvocation> methodCalls;
	private Graphics2D delegate;

	/*************************************************************************
	 * Constructor
	 * @param delegate the Graphics2D context to paint against.
	 ************************************************************************/

	public Graphics2DRecorder(Graphics2D delegate) {
		super();
		this.methodCalls = new ArrayList<RecordedMethodInvocation>();
		this.delegate = delegate;
	}

	/*************************************************************************
	 * Records a method invocation.
	 * @param methodName the name of the method.
	 * @param params the invocation parameters.
	 ************************************************************************/

	private void recordMethodInvocation(int methodId, Object...params) {
		this.methodCalls.add(new RecordedMethodInvocation(methodId, params));
	}

	/*************************************************************************
	 * @param transcoder
	 ************************************************************************/
	
	public void transcode(GraphicsTranscoder transcoder) {
		transcoder.startTranscoding();
		for (RecordedMethodInvocation recordedMethodCall : methodCalls) {
			recordedMethodCall.transcode(transcoder);
		}
		transcoder.finishTranscoding();
	}

	/*************************************************************************
	 * Tries to create a copy of an shape object.
	 * @param s the shape to copy.
	 * @return the copied shape, the shape itself if copying is not supported.
	 ************************************************************************/

	private Shape copiedShape(Shape s) {
		Shape shapeCopy = s;
		if (s instanceof Rectangle) {
			shapeCopy = new Rectangle((Rectangle) s);
		}
		return shapeCopy;
	}

	/*************************************************************************
	 * addRenderingHints
	 * @see java.awt.Graphics2D#addRenderingHints(java.util.Map)
	 ************************************************************************/

	@Override
	public void addRenderingHints(Map<?, ?> hints) {
		recordMethodInvocation(addRenderingHints,  hints);
		this.delegate.addRenderingHints(hints);
	}

	/*************************************************************************
	 * clearRect
	 * @see java.awt.Graphics#clearRect(int, int, int, int)
	 ************************************************************************/

	@Override
	public void clearRect(int x, int y, int width, int height) {
		recordMethodInvocation(clearRect, x, y, width, height);
		this.delegate.clearRect(x, y, width, height);
	}

	/*************************************************************************
	 * clip
	 * @see java.awt.Graphics2D#clip(java.awt.Shape)
	 ************************************************************************/
	@Override
	public void clip(Shape s) {
		recordMethodInvocation(clip, copiedShape(s));
		this.delegate.clip(s);
	}

	/*************************************************************************
	 * clipRect
	 * @see java.awt.Graphics#clipRect(int, int, int, int)
	 ************************************************************************/

	@Override
	public void clipRect(int x, int y, int width, int height) {
		this.methodCalls.add(new RecordedMethodInvocation(clipRect, x, y, width, height));
		this.delegate.clipRect(x, y, width, height);
	}

	/*************************************************************************
	 * copyArea
	 * @see java.awt.Graphics#copyArea(int, int, int, int, int, int)
	 ************************************************************************/

	@Override
	public void copyArea(int x, int y, int width, int height, int dx, int dy) {
//		recordMethodInvocation("copyArea", x, y, width, height, dx, dy);
		this.delegate.copyArea(x, y, width, height, dx, dy);
	}

	/*************************************************************************
	 * create
	 * @see java.awt.Graphics#create()
	 ************************************************************************/

	@Override
	public Graphics create() {
		Graphics2DRecorder childRecorder = new Graphics2DRecorder((Graphics2D) this.delegate.create());

		// Special handling for the create method.
		CreateMethodInvocation methodInvocation = new CreateMethodInvocation();
		methodInvocation.setChildRecorder(childRecorder);
		this.methodCalls.add(methodInvocation);

		return childRecorder;
	}

	/*************************************************************************
	 * create
	 * @see java.awt.Graphics#create(int, int, int, int)
	 ************************************************************************/

	@Override
	public Graphics create(int x, int y, int width, int height) {
		Graphics2DRecorder childRecorder = new Graphics2DRecorder((Graphics2D) this.delegate.create(x, y, width, height));

		// Special handling for the create method.
		CreateMethodInvocation methodInvocation = new CreateMethodInvocation(x, y, width, height);
		methodInvocation.setChildRecorder(childRecorder);
		this.methodCalls.add(methodInvocation);

		return childRecorder;
	}

	/*************************************************************************
	 * dispose
	 * @see java.awt.Graphics#dispose()
	 ************************************************************************/

	@Override
	public void dispose() {
		recordMethodInvocation(dispose);
		this.delegate.dispose();
	}

	/*************************************************************************
	 * draw
	 * @see java.awt.Graphics2D#draw(java.awt.Shape)
	 ************************************************************************/

	@Override
	public void draw(Shape s) {
		recordMethodInvocation(draw, copiedShape(s));
		this.delegate.draw(s);
	}

	/*************************************************************************
	 * draw3DRect
	 * @see java.awt.Graphics2D#draw3DRect(int, int, int, int, boolean)
	 ************************************************************************/

	@Override
	public void draw3DRect(int x, int y, int width, int height, boolean raised) {
		recordMethodInvocation(draw3DRect, x, y , width, height, raised);
		this.delegate.draw3DRect(x, y, width, height, raised);
	}

	/*************************************************************************
	 * drawArc
	 * @see java.awt.Graphics#drawArc(int, int, int, int, int, int)
	 ************************************************************************/

	@Override
	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		recordMethodInvocation(drawArc, x, y , width, height, startAngle, arcAngle);
		this.delegate.drawArc(x, y, width, height, startAngle, arcAngle);
	}

	/*************************************************************************
	 * drawBytes
	 * @see java.awt.Graphics#drawBytes(byte[], int, int, int, int)
	 ************************************************************************/

	@Override
	public void drawBytes(byte[] data, int offset, int length, int x, int y) {
		recordMethodInvocation(drawBytes, data, offset, length, x, y);
		this.delegate.drawBytes(data, offset, length, x, y);
	}

	/*************************************************************************
	 * drawChars
	 * @see java.awt.Graphics#drawChars(char[], int, int, int, int)
	 ************************************************************************/

	@Override
	public void drawChars(char[] data, int offset, int length, int x, int y) {
		recordMethodInvocation(drawChars, data, offset, length, x, y);
		this.delegate.drawChars(data, offset, length, x, y);
	}

	/**
	 * @param g
	 * @param x
	 * @param y
	 * @see java.awt.Graphics2D#drawGlyphVector(java.awt.font.GlyphVector, float, float)
	 */
	@Override
	public void drawGlyphVector(GlyphVector g, float x, float y) {
		recordMethodInvocation(drawGlyphVector, g, x, y);
		this.delegate.drawGlyphVector(g, x, y);
	}

	/*************************************************************************
	 * drawImage
	 * @see java.awt.Graphics2D#drawImage(java.awt.image.BufferedImage, java.awt.image.BufferedImageOp, int, int)
	 ************************************************************************/

	@Override
	public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {
		recordMethodInvocation(drawImage1, img, op, x, y);
		this.delegate.drawImage(img, op, x, y);
	}

	/*************************************************************************
	 * drawImage
	 * @see java.awt.Graphics2D#drawImage(java.awt.Image, java.awt.geom.AffineTransform, java.awt.image.ImageObserver)
	 ************************************************************************/

	@Override
	public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {
		recordMethodInvocation(drawImage2, img, xform, obs);
		return this.delegate.drawImage(img, xform, obs);
	}

	/*************************************************************************
	 * drawImage
	 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, java.awt.Color, java.awt.image.ImageObserver)
	 ************************************************************************/

	@Override
	public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
		recordMethodInvocation(drawImage3, img, x, y, bgcolor, observer);
		return this.delegate.drawImage(img, x, y, bgcolor, observer);
	}

	/*************************************************************************
	 * drawImage
	 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, java.awt.image.ImageObserver)
	 ************************************************************************/

	@Override
	public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
		recordMethodInvocation(drawImage4, img, x, y, observer);
		return this.delegate.drawImage(img, x, y, observer);
	}

	/*************************************************************************
	 * drawImage
	 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, int, int, java.awt.Color, java.awt.image.ImageObserver)
	 ************************************************************************/

	@Override
	public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
		recordMethodInvocation(drawImage5, img, x, y, width, height, bgcolor, observer);
		return this.delegate.drawImage(img, x, y, width, height, bgcolor, observer);
	}

	/*************************************************************************
	 * drawImage
	 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, int, int, java.awt.image.ImageObserver)
	 ************************************************************************/

	@Override
	public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
		recordMethodInvocation(drawImage6, img, x, y, width, height, observer);
		return this.delegate.drawImage(img, x, y, width, height, observer);
	}

	/*************************************************************************
	 * drawImage
	 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, int, int, int, int, int, int, java.awt.Color, java.awt.image.ImageObserver)
	 ************************************************************************/

	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
		recordMethodInvocation(drawImage7, img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
		return this.delegate.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
	}

	/*************************************************************************
	 * drawImage
	 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, int, int, int, int, int, int, java.awt.image.ImageObserver)
	 ************************************************************************/

	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
		recordMethodInvocation(drawImage8, img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
		return this.delegate.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
	}

	/*************************************************************************
	 * drawLine
	 * @see java.awt.Graphics#drawLine(int, int, int, int)
	 ************************************************************************/

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		recordMethodInvocation(drawLine, x1, y1, x2, y2);
		this.delegate.drawLine(x1, y1, x2, y2);
	}

	/*************************************************************************
	 * drawOval
	 * @see java.awt.Graphics#drawOval(int, int, int, int)
	 ************************************************************************/

	@Override
	public void drawOval(int x, int y, int width, int height) {
		recordMethodInvocation(drawOval, x, y, width, height);
		this.delegate.drawOval(x, y, width, height);
	}

	/*************************************************************************
	 * drawPolygon
	 * @see java.awt.Graphics#drawPolygon(int[], int[], int)
	 ************************************************************************/

	@Override
	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		recordMethodInvocation(drawPolygon1, xPoints, yPoints, nPoints);
		this.delegate.drawPolygon(xPoints, yPoints, nPoints);
	}

	/*************************************************************************
	 * drawPolygon
	 * @see java.awt.Graphics#drawPolygon(java.awt.Polygon)
	 ************************************************************************/

	@Override
	public void drawPolygon(Polygon p) {
		recordMethodInvocation(drawPolygon2, p);
		this.delegate.drawPolygon(p);
	}

	/*************************************************************************
	 * drawPolyline
	 * @see java.awt.Graphics#drawPolyline(int[], int[], int)
	 ************************************************************************/

	@Override
	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
		recordMethodInvocation(drawPolyline, xPoints, yPoints, nPoints);
		this.delegate.drawPolyline(xPoints, yPoints, nPoints);
	}

	/*************************************************************************
	 * drawRect
	 * @see java.awt.Graphics#drawRect(int, int, int, int)
	 ************************************************************************/

	@Override
	public void drawRect(int x, int y, int width, int height) {
		recordMethodInvocation(drawRect, x, y, width, height);
		this.delegate.drawRect(x, y, width, height);
	}

	/*************************************************************************
	 * drawRenderableImage
	 * @see java.awt.Graphics2D#drawRenderableImage(java.awt.image.renderable.RenderableImage, java.awt.geom.AffineTransform)
	 ************************************************************************/

	@Override
	public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
		recordMethodInvocation(drawRenderableImage, img, xform);
		this.delegate.drawRenderableImage(img, xform);
	}

	/*************************************************************************
	 * drawRenderedImage
	 * @see java.awt.Graphics2D#drawRenderedImage(java.awt.image.RenderedImage, java.awt.geom.AffineTransform)
	 ************************************************************************/

	@Override
	public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
		recordMethodInvocation(drawRenderedImage, img, xform);
		this.delegate.drawRenderedImage(img, xform);
	}

	/*************************************************************************
	 * drawRoundRect
	 * @see java.awt.Graphics#drawRoundRect(int, int, int, int, int, int)
	 ************************************************************************/

	@Override
	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		recordMethodInvocation(drawRoundRect, x, y, width, height, arcWidth, arcHeight);
		this.delegate.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	/*************************************************************************
	 * drawString
	 * @see java.awt.Graphics2D#drawString(java.text.AttributedCharacterIterator, float, float)
	 ************************************************************************/

	@Override
	public void drawString(AttributedCharacterIterator iterator, float x, float y) {
		recordMethodInvocation(drawString1, iterator, x, y);
		this.delegate.drawString(iterator, x, y);
	}

	/*************************************************************************
	 * drawString
	 * @see java.awt.Graphics2D#drawString(java.text.AttributedCharacterIterator, int, int)
	 ************************************************************************/

	@Override
	public void drawString(AttributedCharacterIterator iterator, int x, int y) {
		recordMethodInvocation(drawString2, iterator, x, y);
		this.delegate.drawString(iterator, x, y);
	}

	/*************************************************************************
	 * drawString
	 * @see java.awt.Graphics2D#drawString(java.lang.String, float, float)
	 ************************************************************************/

	@Override
	public void drawString(String s, float x, float y) {
		recordMethodInvocation(drawString3, s, x, y);
		this.delegate.drawString(s, x, y);
	}

	/*************************************************************************
	 * drawString
	 * @see java.awt.Graphics2D#drawString(java.lang.String, int, int)
	 ************************************************************************/

	@Override
	public void drawString(String str, int x, int y) {
		recordMethodInvocation(drawString4, str, x, y);
		this.delegate.drawString(str, x, y);
	}

	/*************************************************************************
	 * equals
	 * @see java.lang.Object#equals(java.lang.Object)
	 ************************************************************************/

	@Override
	public boolean equals(Object obj) {
//		recordMethodInvocation("equals", obj);
		return this.delegate.equals(obj);
	}

	/*************************************************************************
	 * fill
	 * @see java.awt.Graphics2D#fill(java.awt.Shape)
	 ************************************************************************/

	@Override
	public void fill(Shape s) {
		recordMethodInvocation(fill, copiedShape(s));
		this.delegate.fill(s);
	}

	/*************************************************************************
	 * fill3DRect
	 * @see java.awt.Graphics2D#fill3DRect(int, int, int, int, boolean)
	 ************************************************************************/

	@Override
	public void fill3DRect(int x, int y, int width, int height, boolean raised) {
		recordMethodInvocation(fill3DRect, x, y, width, height, raised);
		this.delegate.fill3DRect(x, y, width, height, raised);
	}

	/*************************************************************************
	 * fillArc
	 * @see java.awt.Graphics#fillArc(int, int, int, int, int, int)
	 ************************************************************************/

	@Override
	public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		recordMethodInvocation(fillArc, x, y, width, height, startAngle, arcAngle);
		this.delegate.fillArc(x, y, width, height, startAngle, arcAngle);
	}

	/*************************************************************************
	 * fillOval
	 * @see java.awt.Graphics#fillOval(int, int, int, int)
	 ************************************************************************/

	@Override
	public void fillOval(int x, int y, int width, int height) {
		recordMethodInvocation(fillOval, x, y, width, height);
		this.delegate.fillOval(x, y, width, height);
	}

	/*************************************************************************
	 * fillPolygon
	 * @see java.awt.Graphics#fillPolygon(int[], int[], int)
	 ************************************************************************/

	@Override
	public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		recordMethodInvocation(fillPolygon1, xPoints, yPoints, nPoints);
		this.delegate.fillPolygon(xPoints, yPoints, nPoints);
	}

	/*************************************************************************
	 * fillPolygon
	 * @see java.awt.Graphics#fillPolygon(java.awt.Polygon)
	 ************************************************************************/

	@Override
	public void fillPolygon(Polygon p) {
		recordMethodInvocation(fillPolygon2, p);
		this.delegate.fillPolygon(p);
	}

	/*************************************************************************
	 * fillRect
	 * @see java.awt.Graphics#fillRect(int, int, int, int)
	 ************************************************************************/

	@Override
	public void fillRect(int x, int y, int width, int height) {
		recordMethodInvocation(fillRect, x, y, width, height);
		this.delegate.fillRect(x, y, width, height);
	}

	/*************************************************************************
	 * fillRoundRect
	 * @see java.awt.Graphics#fillRoundRect(int, int, int, int, int, int)
	 ************************************************************************/

	@Override
	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		recordMethodInvocation(fillRoundRect, x, y, width, height, arcWidth, arcHeight);
		this.delegate.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	/*************************************************************************
	 * getBackground
	 * @see java.awt.Graphics2D#getBackground()
	 ************************************************************************/

	@Override
	public Color getBackground() {
//		recordMethodInvocation("getBackground");
		return this.delegate.getBackground();
	}

	/*************************************************************************
	 * getClip
	 * @see java.awt.Graphics#getClip()
	 ************************************************************************/

	@Override
	public Shape getClip() {
//		recordMethodInvocation("getClip");
		return this.delegate.getClip();
	}

	/*************************************************************************
	 * getClipBounds
	 * @see java.awt.Graphics#getClipBounds()
	 ************************************************************************/

	@Override
	public Rectangle getClipBounds() {
//		recordMethodInvocation("getClipBounds1");
		return this.delegate.getClipBounds();
	}

	/*************************************************************************
	 * getClipBounds
	 * @see java.awt.Graphics#getClipBounds(java.awt.Rectangle)
	 ************************************************************************/

	@Override
	public Rectangle getClipBounds(Rectangle r) {
//		recordMethodInvocation("getClipBounds2", r);
		return this.delegate.getClipBounds(r);
	}

	/*************************************************************************
	 * getClipRect
	 * @see java.awt.Graphics#getClipRect()
	 ************************************************************************/

	@Deprecated
	@Override
	public Rectangle getClipRect() {
//		recordMethodInvocation("getClipRect");
		return this.delegate.getClipRect();
	}

	/*************************************************************************
	 * getColor
	 * @see java.awt.Graphics#getColor()
	 ************************************************************************/

	@Override
	public Color getColor() {
//		recordMethodInvocation("getColor");
		return this.delegate.getColor();
	}

	/*************************************************************************
	 * getComposite
	 * @see java.awt.Graphics2D#getComposite()
	 ************************************************************************/

	@Override
	public Composite getComposite() {
//		recordMethodInvocation("getComposite");
		return this.delegate.getComposite();
	}

	/*************************************************************************
	 * getDeviceConfiguration
	 * @see java.awt.Graphics2D#getDeviceConfiguration()
	 ************************************************************************/

	@Override
	public GraphicsConfiguration getDeviceConfiguration() {
//		recordMethodInvocation("getDeviceConfiguration");
		return this.delegate.getDeviceConfiguration();
	}

	/*************************************************************************
	 * getFont
	 * @see java.awt.Graphics#getFont()
	 ************************************************************************/

	@Override
	public Font getFont() {
//		recordMethodInvocation("getFont");
		return this.delegate.getFont();
	}

	/*************************************************************************
	 * getFontMetrics
	 * @see java.awt.Graphics#getFontMetrics()
	 ************************************************************************/

	@Override
	public FontMetrics getFontMetrics() {
//		recordMethodInvocation("getFontMetrics1");
		return this.delegate.getFontMetrics();
	}

	/*************************************************************************
	 * getFontMetrics
	 * @see java.awt.Graphics#getFontMetrics(java.awt.Font)
	 ************************************************************************/

	@Override
	public FontMetrics getFontMetrics(Font f) {
//		recordMethodInvocation("getFontMetrics2", f);
		return this.delegate.getFontMetrics(f);
	}

	/*************************************************************************
	 * getFontRenderContext
	 * @see java.awt.Graphics2D#getFontRenderContext()
	 ************************************************************************/

	@Override
	public FontRenderContext getFontRenderContext() {
//		recordMethodInvocation("getFontRenderContext");
		return this.delegate.getFontRenderContext();
	}

	/*************************************************************************
	 * getPaint
	 * @see java.awt.Graphics2D#getPaint()
	 ************************************************************************/

	@Override
	public Paint getPaint() {
//		recordMethodInvocation("getPaint");
		return this.delegate.getPaint();
	}

	/*************************************************************************
	 * getRenderingHint
	 * @see java.awt.Graphics2D#getRenderingHint(java.awt.RenderingHints.Key)
	 ************************************************************************/

	@Override
	public Object getRenderingHint(Key hintKey) {
//		recordMethodInvocation("getRenderingHint", hintKey);
		return this.delegate.getRenderingHint(hintKey);
	}

	/*************************************************************************
	 * getRenderingHints
	 * @see java.awt.Graphics2D#getRenderingHints()
	 ************************************************************************/

	@Override
	public RenderingHints getRenderingHints() {
//		recordMethodInvocation("getRenderingHints");
		return this.delegate.getRenderingHints();
	}

	/*************************************************************************
	 * getStroke
	 * @see java.awt.Graphics2D#getStroke()
	 ************************************************************************/

	@Override
	public Stroke getStroke() {
//		recordMethodInvocation("getStroke");
		return this.delegate.getStroke();
	}

	/*************************************************************************
	 * getTransform
	 * @see java.awt.Graphics2D#getTransform()
	 ************************************************************************/

	@Override
	public AffineTransform getTransform() {
//		recordMethodInvocation("getTransform");
		return this.delegate.getTransform();
	}

	/*************************************************************************
	 * hashCode
	 * @see java.lang.Object#hashCode()
	 ************************************************************************/

	@Override
	public int hashCode() {
//		recordMethodInvocation("hashCode");
		return this.delegate.hashCode();
	}

	/*************************************************************************
	 * hit
	 * @see java.awt.Graphics2D#hit(java.awt.Rectangle, java.awt.Shape, boolean)
	 ************************************************************************/

	@Override
	public boolean hit(Rectangle rect, Shape s, boolean onStroke) {
//		recordMethodInvocation("hit", rect, s, onStroke);
		return this.delegate.hit(rect, s, onStroke);
	}

	/*************************************************************************
	 * hitClip
	 * @see java.awt.Graphics#hitClip(int, int, int, int)
	 ************************************************************************/

	@Override
	public boolean hitClip(int x, int y, int width, int height) {
//		recordMethodInvocation("hitClip", x, y, width, height);
		return this.delegate.hitClip(x, y, width, height);
	}

	/*************************************************************************
	 * rotate
	 * @see java.awt.Graphics2D#rotate(double, double, double)
	 ************************************************************************/

	@Override
	public void rotate(double theta, double x, double y) {
		recordMethodInvocation(rotate1, theta, x, y);
		this.delegate.rotate(theta, x, y);
	}

	/*************************************************************************
	 * rotate
	 * @see java.awt.Graphics2D#rotate(double)
	 ************************************************************************/

	@Override
	public void rotate(double theta) {
		recordMethodInvocation(rotate2, theta);
		this.delegate.rotate(theta);
	}

	/*************************************************************************
	 * scale
	 * @see java.awt.Graphics2D#scale(double, double)
	 ************************************************************************/

	@Override
	public void scale(double sx, double sy) {
		recordMethodInvocation(scale, sx, sy);
		this.delegate.scale(sx, sy);
	}

	/*************************************************************************
	 * setBackground
	 * @see java.awt.Graphics2D#setBackground(java.awt.Color)
	 ************************************************************************/

	@Override
	public void setBackground(Color color) {
		recordMethodInvocation(setBackground, color);
		this.delegate.setBackground(color);
	}

	/*************************************************************************
	 * setClip
	 * @see java.awt.Graphics#setClip(int, int, int, int)
	 ************************************************************************/

	@Override
	public void setClip(int x, int y, int width, int height) {
		recordMethodInvocation(setClip1, x, y, width, height);
		this.delegate.setClip(x, y, width, height);
	}

	/*************************************************************************
	 * setClip
	 * @see java.awt.Graphics#setClip(java.awt.Shape)
	 ************************************************************************/

	@Override
	public void setClip(Shape clip) {
		recordMethodInvocation(setClip2, copiedShape(clip));
		this.delegate.setClip(clip);
	}

	/*************************************************************************
	 * setColor
	 * @see java.awt.Graphics#setColor(java.awt.Color)
	 ************************************************************************/

	@Override
	public void setColor(Color c) {
		recordMethodInvocation(setColor, c);
		this.delegate.setColor(c);
	}

	/*************************************************************************
	 * setComposite
	 * @see java.awt.Graphics2D#setComposite(java.awt.Composite)
	 ************************************************************************/

	@Override
	public void setComposite(Composite comp) {
		recordMethodInvocation(setComposite, comp);
		this.delegate.setComposite(comp);
	}

	/*************************************************************************
	 * setFont
	 * @see java.awt.Graphics#setFont(java.awt.Font)
	 ************************************************************************/

	@Override
	public void setFont(Font font) {
		recordMethodInvocation(setFont, font);
		this.delegate.setFont(font);
	}

	/*************************************************************************
	 * setPaint
	 * @see java.awt.Graphics2D#setPaint(java.awt.Paint)
	 ************************************************************************/

	@Override
	public void setPaint(Paint paint) {
		recordMethodInvocation(setPaint, paint);
		this.delegate.setPaint(paint);
	}

	/*************************************************************************
	 * setPaintMode
	 * @see java.awt.Graphics#setPaintMode()
	 ************************************************************************/

	@Override
	public void setPaintMode() {
		recordMethodInvocation(setPaintMode);
		this.delegate.setPaintMode();
	}

	/*************************************************************************
	 * setRenderingHint
	 * @see java.awt.Graphics2D#setRenderingHint(java.awt.RenderingHints.Key, java.lang.Object)
	 ************************************************************************/

	@Override
	public void setRenderingHint(Key hintKey, Object hintValue) {
		recordMethodInvocation(setRenderingHint, hintKey, hintValue);
		this.delegate.setRenderingHint(hintKey, hintValue);
	}

	/*************************************************************************
	 * setRenderingHints
	 * @see java.awt.Graphics2D#setRenderingHints(java.util.Map)
	 ************************************************************************/

	@Override
	public void setRenderingHints(Map<?, ?> hints) {
		recordMethodInvocation(setRenderingHints, hints);
		this.delegate.setRenderingHints(hints);
	}

	/*************************************************************************
	 * setStroke
	 * @see java.awt.Graphics2D#setStroke(java.awt.Stroke)
	 ************************************************************************/

	@Override
	public void setStroke(Stroke s) {
		recordMethodInvocation(setStroke, s);
		this.delegate.setStroke(s);
	}

	/*************************************************************************
	 * setTransform
	 * @see java.awt.Graphics2D#setTransform(java.awt.geom.AffineTransform)
	 ************************************************************************/

	@Override
	public void setTransform(AffineTransform Tx) {
		recordMethodInvocation(setTransform, new AffineTransform(Tx));
		this.delegate.setTransform(Tx);
	}

	/*************************************************************************
	 * setXORMode
	 * @see java.awt.Graphics#setXORMode(java.awt.Color)
	 ************************************************************************/

	@Override
	public void setXORMode(Color c1) {
		recordMethodInvocation(setXORMode, c1);
		this.delegate.setXORMode(c1);
	}

	/*************************************************************************
	 * shear
	 * @see java.awt.Graphics2D#shear(double, double)
	 ************************************************************************/

	@Override
	public void shear(double shx, double shy) {
		recordMethodInvocation(shear, shx, shy);
		this.delegate.shear(shx, shy);
	}

	/*************************************************************************
	 * toString
	 * @see java.awt.Graphics#toString()
	 ************************************************************************/

	@Override
	public String toString() {
//		recordMethodInvocation("toString");
		return this.delegate.toString();
	}

	/*************************************************************************
	 * transform
	 * @see java.awt.Graphics2D#transform(java.awt.geom.AffineTransform)
	 ************************************************************************/

	@Override
	public void transform(AffineTransform Tx) {
		recordMethodInvocation(transform, new AffineTransform(Tx));
		this.delegate.transform(Tx);
	}

	/*************************************************************************
	 * translate
	 * @see java.awt.Graphics2D#translate(double, double)
	 ************************************************************************/

	@Override
	public void translate(double tx, double ty) {
		recordMethodInvocation(translate1, tx, ty);
		this.delegate.translate(tx, ty);
	}

	/*************************************************************************
	 * translate
	 * @see java.awt.Graphics2D#translate(int, int)
	 ************************************************************************/

	@Override
	public void translate(int x, int y) {
		recordMethodInvocation(translate2, x, y);
		this.delegate.translate(x, y);
	}

	/*************************************************************************
	 * Counts the total amount of method calls recorded.
	 * @return the number of method calls.
	 ************************************************************************/

	public int countMethodCalls() {
		int counter = 0;
		for (RecordedMethodInvocation meth : this.methodCalls) {
			counter++;
			if (meth instanceof CreateMethodInvocation) {
				counter += ((CreateMethodInvocation)meth).getChildRecorder().countMethodCalls();
			}
		}
		return counter;
	}

}
