package com.btr.svg2java;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.batik.ext.awt.LinearGradientPaint;
import org.apache.batik.ext.awt.RadialGradientPaint;
import org.apache.batik.ext.awt.geom.ExtendedGeneralPath;
import org.apache.batik.gvt.CompositeGraphicsNode;
import org.apache.batik.gvt.CompositeShapePainter;
import org.apache.batik.gvt.FillShapePainter;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.gvt.RasterImageNode;
import org.apache.batik.gvt.ShapeNode;
import org.apache.batik.gvt.ShapePainter;
import org.apache.batik.gvt.StrokeShapePainter;
import org.apache.batik.gvt.TextNode;
import org.apache.batik.gvt.font.AWTGVTGlyphVector;
import org.apache.batik.gvt.font.GVTGlyphVector;
import org.apache.batik.gvt.renderer.StrokingTextPainter;
import org.apache.batik.gvt.renderer.StrokingTextPainter.TextRun;
import org.apache.batik.gvt.text.GVTAttributedCharacterIterator;
import org.apache.batik.gvt.text.TextPaintInfo;
import org.apache.batik.gvt.text.TextSpanLayout;
import org.apache.batik.transcoder.SVGAbstractTranscoder;
import org.apache.batik.transcoder.TranscoderInput;

/*****************************************************************************
 * Abstract transcoder implementing most of the logic to convert an SVG file
 * to java code.
 *
 * @author  Bernd Rosstauscher 
 *         (svg2java(@)rosstauscher.de)
 ****************************************************************************/

public abstract class AbstractSvgTranscoder implements SvgTranscoder {

	// Used for text rendering
    public static final AttributedCharacterIterator.Attribute PAINT_INFO =
        GVTAttributedCharacterIterator.TextAttribute.PAINT_INFO;
	
	private static class RawTranscoder extends SVGAbstractTranscoder {
		public GraphicsNode getGVTRoot() {
			return this.root;
		}
	}
	
	private final static String TOKEN_PACKAGE = "TOKEN_PACKAGE";
	private final static String TOKEN_CLASSNAME = "TOKEN_CLASSNAME";
	private final static String TOKEN_PAINTING_CODE = "TOKEN_PAINTING_CODE";
	private final static String TOKEN_RENDERING_HINTS = "TOKEN_RENDERING_HINTS";
	private final static String TOKEN_METHOD_CODE = "TOKEN_METHOD_CODE";
	private final static String TOKEN_ORIG_X = "TOKEN_ORIG_X";
	private final static String TOKEN_ORIG_Y = "TOKEN_ORIG_Y";
	private final static String TOKEN_ORIG_WIDTH = "TOKEN_ORIG_WIDTH";
	private final static String TOKEN_ORIG_HEIGHT = "TOKEN_ORIG_HEIGHT";
	
	private List<TranscoderListener> listener;
	private PrintWriter externalPrintWriter;
	private String templateFile;
	private StringBuilder methodCodeBuffer;
	private StringBuilder paintingCodeBuffer;
	private String javaClassName;
	private int nodeCounter = 0;

	private AlphaComposite lastComposite;
	private Paint lastPaint;
	private Stroke lastStroke;

	
	/**
	 * Creates a new transcoder.
	 */
	public AbstractSvgTranscoder() {
		super();
	}
	
	/*************************************************************************
	 * Sets the path to the template to use.
	 * @param filePath
	 ************************************************************************/
	
	public final void overrideDefaultTemplateFile(String filePath) {
		this.templateFile = filePath;
	}

	/*************************************************************************
	 * Sets the classname for the generated class 
	 * @param javaClassname to set.
	 ************************************************************************/
	
	private final void setClassName(String javaClassname) {
		this.javaClassName = javaClassname;
	}

	/*************************************************************************
	 * addListener
	 * @see com.btr.svg2java.SvgTranscoder#addListener(com.btr.svg2java.TranscoderListener)
	 ************************************************************************/
	@Override
	public final void addListener(TranscoderListener listener) {
		if (this.listener == null) {
			this.listener = new ArrayList<TranscoderListener>();
		}
		this.listener.add(listener);
	}
	
	/*************************************************************************
	 * removeListener
	 * @see com.btr.svg2java.SvgTranscoder#removeListener(com.btr.svg2java.TranscoderListener)
	 ************************************************************************/
	@Override
	public final boolean removeListener(TranscoderListener listener) {
		if (this.listener != null) {
			return this.listener.remove(listener);
		}
		return false;
	}

	/*************************************************************************
	 * Sets the printwriter to write the generated java source to.
	 * @param printWriter to use for output.
	 ************************************************************************/
	
	private final void setPrintWriter(PrintWriter printWriter) {
		this.externalPrintWriter = printWriter;
	}
	
	/*************************************************************************
	 * Closes the underlying output writer.
	 ************************************************************************/
	
	private void closeWriter() {
		this.externalPrintWriter.close();
	}

	/*************************************************************************
	 * transcode
	 * @see com.btr.svg2java.SvgTranscoder#transcode(java.io.InputStream, java.io.Writer, java.lang.String)
	 ************************************************************************/
	@Override
	public void transcode(InputStream is, Writer destination, String javaClassname) {
		setClassName(javaClassname);
		setPrintWriter(new PrintWriter(destination));
		BufferedInputStream bis = new BufferedInputStream(is);
		TranscoderInput ti = new TranscoderInput(bis);
		try {
			RawTranscoder transcoder = new RawTranscoder();
			transcoder.transcode(ti, null);
			this.transcode(transcoder.getGVTRoot());
		} catch (Exception exc) {
			notifyUnsupportedOperation(null, exc.getMessage());
			exc.printStackTrace();
		} finally {
			closeWriter();
		}
	}

	/*************************************************************************
	 * Transcodes the SVG image into Java2D code.
	 * @param gvtRoot
	 ************************************************************************/
	
	private final void transcode(GraphicsNode gvtRoot) {
		notifyStarted();
		setupBuffers();
		transcodeGraphicsNode(gvtRoot, "_0", this.paintingCodeBuffer);
		String java2DCode = assembleClassFromTemplate(gvtRoot);
		saveAssembledClass(java2DCode);
		teardownBuffers();
		notifyFinished();
	}

	/*************************************************************************
	 * @param gvtRoot
	 * @return
	 ************************************************************************/

	private String assembleClassFromTemplate(GraphicsNode gvtRoot) {
		String templateString = loadTemplateFile();
		templateString = injectClassNameAndPackage(templateString);
		templateString = injectPaintingCode(templateString);
		templateString = injectImageBounds(gvtRoot.getBounds(), templateString);
		return templateString;
	}

	/*************************************************************************
	 * @param gvtRoot 
	 * @param templateString
	 * @return
	 ************************************************************************/
	
	private String injectPaintingCode(String templateString) {
		String result = templateString.replace(TOKEN_PAINTING_CODE,
				this.paintingCodeBuffer.toString());
		result = result.replace(TOKEN_RENDERING_HINTS, 
				generateRenderingHintsCode());
		result = result.replace(TOKEN_METHOD_CODE,
				this.methodCodeBuffer.toString());
		return result;
	}

	/*************************************************************************
	 * @return the generated code
	 ************************************************************************/
	
	protected abstract String generateRenderingHintsCode();

	/*************************************************************************
	 * 
	 ************************************************************************/
	
	private void teardownBuffers() {
		this.methodCodeBuffer = null;
		this.paintingCodeBuffer = null;
	}

	/*************************************************************************
	 * 
	 ************************************************************************/
	
	private void setupBuffers() {
		this.paintingCodeBuffer = new StringBuilder();
		this.methodCodeBuffer = new StringBuilder();
	}

	/*************************************************************************
	 * @param templateString
	 ************************************************************************/
	
	private void saveAssembledClass(String templateString) {
		this.externalPrintWriter.println(templateString);
		this.externalPrintWriter.close();
	}

	/*************************************************************************
	 * @param gvtRoot
	 * @param templateString
	 * @return
	 ************************************************************************/
	
	private String injectImageBounds(Rectangle2D bounds,String templateString) {
		String result = templateString.replace(TOKEN_ORIG_X, ""
				+ (int) Math.ceil(bounds.getX()));
		result = result.replace(TOKEN_ORIG_Y, ""
				+ (int) Math.ceil(bounds.getY()));
		result = result.replace(TOKEN_ORIG_WIDTH, ""
				+ (int) Math.ceil(bounds.getWidth()));
		result = result.replace(TOKEN_ORIG_HEIGHT, ""
				+ (int) Math.ceil(bounds.getHeight()));
		return result;
	}

	/*************************************************************************
	 * @param templateString
	 * @return
	 ************************************************************************/
	
	private String injectClassNameAndPackage(String templateString) {
		String className = this.javaClassName;
		String packageName = "";
		int index = this.javaClassName.lastIndexOf(".");
		if (index != -1) {
			className = this.javaClassName.substring(index+1);
			packageName = this.javaClassName.substring(0, index);
		}
		templateString = templateString.replace(TOKEN_PACKAGE,
				packageName.length() > 0 ? "package " + packageName + ";" : "");
		templateString = templateString.replace(TOKEN_CLASSNAME, className);
		return templateString;
	}

	/*************************************************************************
	 * @return
	 ************************************************************************/
	
	private String loadTemplateFile() {
		InputStream templateStream = getClass().getResourceAsStream(templateFile);
		BufferedReader templateReader = new BufferedReader(
				new InputStreamReader(templateStream));
		StringBuffer templateBuffer = new StringBuffer();
		try {
			String line;
			while ( (line = templateReader.readLine()) != null) {
				templateBuffer.append(line).append("\n");
			}
			templateReader.close();
		} catch (IOException ioe) {
			Logger.getLogger(getClass().getName()).throwing(getClass().getName(), "loadTemplateFile", ioe);
		}
		return templateBuffer.toString();
	}

	/*************************************************************************
	 * 
	 ************************************************************************/
	
	private void notifyStarted() {
		if (listener != null) {
			for (TranscoderListener l : listener) {
				l.started();
			}
		}
	}

	/*************************************************************************
	 * 
	 ************************************************************************/
	
	private void notifyFinished() {
		if (listener != null) {
			for (TranscoderListener l : listener) {
				l.finished();
			}
		}
	}

	/*************************************************************************
	 * 
	 ************************************************************************/
	
	private void notifyUnsupportedOperation(Object node, String message) {
		if (listener != null) {
			for (TranscoderListener l : listener) {
				l.onUnsupportedOperation(node, message);
			}
		}
	}
	
	/*************************************************************************
	 * Transcodes the specified path iterator.
	 * @param pathIterator
	 * @param buffer
	 * @return the generated code
	 ************************************************************************/
	
	protected abstract String generatePathIteratorCode(PathIterator pathIterator, StringBuilder buffer);
	
	/*************************************************************************
	 * Transcodes the specified shape.
	 * @param shape
	 * @param buffer
	 * @return the generated code
	 ************************************************************************/
	
	private String transcodeShape(Shape shape, StringBuilder buffer) {
		String shapeName = null;
		if (shape instanceof ExtendedGeneralPath) {
			shapeName = generatePathIteratorCode(((ExtendedGeneralPath) shape).getPathIterator(null), buffer);
		} else
		if (shape instanceof GeneralPath) {
			shapeName = generatePathIteratorCode(((GeneralPath) shape).getPathIterator(null), buffer);
		} else
		if (shape instanceof Rectangle2D) {
			shapeName = generateRectangle2DCode((Rectangle2D)shape, buffer);
		} else
		if (shape instanceof RoundRectangle2D) {
			shapeName = generateRoundRectangle2DCode((RoundRectangle2D) shape, buffer);
		} else
		if (shape instanceof Ellipse2D) {
			shapeName = generateElipse2DCode((Ellipse2D) shape, buffer);
		} else
		if (shape instanceof Line2D.Float) {
			shapeName = generateLine2DFloatCode((Line2D.Float) shape, buffer);
		} else {
			notifyUnsupportedOperation(shape, "Unsupported shape type "+
					shape.getClass().getCanonicalName());
		}
		return shapeName;
	}

	/*************************************************************************
	 * @param buffer
	 * @param l2df
	 ************************************************************************/
	
	protected abstract String generateLine2DFloatCode(Line2D.Float l2df, StringBuilder buffer);

	/*************************************************************************
	 * @param ell
	 * @param buffer
	 ************************************************************************/
	
	protected abstract String generateElipse2DCode(Ellipse2D ell, StringBuilder buffer);

	/*************************************************************************
	 * @param buffer
	 * @param rRect
	 ************************************************************************/
	
	protected abstract String generateRoundRectangle2DCode(RoundRectangle2D rRect, StringBuilder buffer);

	/*************************************************************************
	 * @param rect
	 * @param buffer
	 * @return the generated code
	 ************************************************************************/
	
	protected abstract String generateRectangle2DCode(Rectangle2D rect, StringBuilder buffer);
	
	/*************************************************************************
	 * Transcodes the specified linear gradient paint.
	 * @param paint
	 *            Linear gradient paint.
	 * @throws IllegalArgumentException
	 *             if the fractions are not strictly increasing.
	 ************************************************************************/
	
	protected abstract void generateLinearGradientPaintCode(LinearGradientPaint paint, StringBuilder buffer)
			throws IllegalArgumentException;

	/*************************************************************************
	 * Transcodes the specified radial gradient paint.
	 * @param paint
	 *            Radial gradient paint.
	 * @throws IllegalArgumentException
	 *             if the fractions are not strictly increasing.
	 ************************************************************************/
	
	protected abstract void generateRadialGradientPaintCode(RadialGradientPaint paint, StringBuilder buffer)
			throws IllegalArgumentException;
	
	/*************************************************************************
	 * Transcodes the specified paint.
	 * 
	 * @param paint
	 *            Paint.
	 ************************************************************************/
	
	private void transcodePaint(Paint paint, StringBuilder buffer) {
		if (isSameAsLastPaint(paint)) {
			return;
		}
		if (paint instanceof RadialGradientPaint) {
			generateRadialGradientPaintCode((RadialGradientPaint) paint, buffer);
		} else
		if (paint instanceof LinearGradientPaint) {
			generateLinearGradientPaintCode((LinearGradientPaint) paint, buffer);
		} else
		if (paint instanceof Color) {
			generateSetColorCode((Color)paint, buffer);
		} else {
			notifyUnsupportedOperation(paint, "Unsupported paint type "+
					paint.getClass().getCanonicalName());
		}
		lastPaint = paint;
	}

	/*************************************************************************
	 * @param paint
	 * @return
	 ************************************************************************/
	
	private boolean isSameAsLastPaint(Paint paint) {
		return (this.lastPaint == null && paint == null) 
			|| (paint != null && paint.equals(lastPaint));
	}

	/*************************************************************************
	 * @param c
	 * @param buffer
	 ************************************************************************/
	
	protected abstract void generateSetColorCode(Color c, StringBuilder buffer);
	
	/*************************************************************************
	 * Transcodes the specified shape painter.
	 * @param painter
	 *            Shape painter.
	 * @param buffer
	 * @param sharedShapeName
	 ************************************************************************/
	
	private void transcodeShapePainter(ShapePainter painter, StringBuilder buffer, String sharedShapeName) {
		if (painter == null) {
			return;
		} else
		if (painter instanceof CompositeShapePainter) {
			transcodeCompositeShapePainter((CompositeShapePainter) painter, buffer, sharedShapeName);
		} else
		if (painter instanceof FillShapePainter) {
			transcodeFillShapePainter((FillShapePainter) painter, buffer, sharedShapeName);
		} else
		if (painter instanceof StrokeShapePainter) {
			transcodeStrokeShapePainter((StrokeShapePainter) painter, buffer, sharedShapeName);
		} else {
			notifyUnsupportedOperation(painter, "Unsupported painter type "+
					painter.getClass().getCanonicalName());
		}
	}

	/*************************************************************************
	 * Transcodes the specified composite shape painter.
	 * @param painter
	 *            Composite shape painter.
	 * @param buffer
	 * @param sharedShapeName
	 ************************************************************************/
	
	private void transcodeCompositeShapePainter(CompositeShapePainter painter, StringBuilder buffer, String sharedShapeName) {
		sharedShapeName = transcodeMultipleUsedShape(painter, buffer, sharedShapeName);
		for (int i = 0; i < painter.getShapePainterCount(); i++) {
			ShapePainter childPainter = painter.getShapePainter(i);
			childPainter.getShape();
			transcodeShapePainter(childPainter, buffer, sharedShapeName);
		}
	}

	/*************************************************************************
	 * Checks if all painters share the same shape.
	 * Should be normally the case for CompositeShapePainter,
	 * @param painter
	 * @param buffer
	 * @param sharedShapeName
	 * @return the name of the shared shape that was generated, null if we have
	 * no common shape.
	 ************************************************************************/
	
	private String transcodeMultipleUsedShape(CompositeShapePainter painter,
			StringBuilder buffer, String sharedShapeName) {
		if (sharedShapeName == null) {
			Shape sp = painter.getShape();
			boolean allSame = true;
			for (int i = 0; i < painter.getShapePainterCount(); i++) {
				ShapePainter childPainter = painter.getShapePainter(i);
				Shape childShape = childPainter.getShape();
				if ( (sp == null || childShape == null) || !sp.equals(childShape)) {
					allSame = false;
					break;
				}
			}
			if (allSame) {
				Shape shape = painter.getShape();
				sharedShapeName = transcodeShape(shape, buffer);
			}
		}
		return sharedShapeName;
	}

	/*************************************************************************
	 * Transcodes the specified fill shape painter.
	 * @param painter
	 *            Fill shape painter.
	 * @param buffer
	 * @param sharedShapeName
	 ************************************************************************/
	
	private void transcodeFillShapePainter(FillShapePainter painter, StringBuilder buffer, String sharedShapeName) {
		Paint paint = painter.getPaint();
		if (paint != null) {
			transcodePaint(paint, buffer);
			if (sharedShapeName == null) {
				Shape shape = painter.getShape();
				sharedShapeName = transcodeShape(shape, buffer);
			}
			generateShapeFillCode(sharedShapeName, buffer);
		}
	}

	/*************************************************************************
	 * @param shapeName
	 * @param buffer
	 ************************************************************************/
	
	protected abstract void generateShapeFillCode(String shapeName, StringBuilder buffer);

	/*************************************************************************
	 * Transcodes the specified stroke shape painter.
	 * 
	 * @param painter
	 *            Stroke shape painter.
	 * @param buffer
	 * @param sharedShapeName
	 ************************************************************************/
	
	private void transcodeStrokeShapePainter(StrokeShapePainter painter, StringBuilder buffer, String sharedShapeName) {
		BasicStroke bStroke = (BasicStroke)painter.getStroke();
		Paint paint = painter.getPaint();
		if (bStroke != null && paint != null) {
			transcodePaint(paint, buffer);
			transcodeStroke(bStroke, buffer);
			if (sharedShapeName == null) {
				Shape shape = painter.getShape();
				sharedShapeName = transcodeShape(shape, buffer);
			}
			generateShapeDrawCode(sharedShapeName, buffer);
		}
	}

	/*************************************************************************
	 * @param bStroke
	 * @param strokeName
	 * @param buffer
	 ************************************************************************/
	
	private void transcodeStroke(BasicStroke bStroke, StringBuilder buffer) {
		if (isStrokeSameAsLast(bStroke)) {
			generateBasicStrokeCode(bStroke, buffer);
			this.lastStroke = bStroke;
		}
	}

	/*************************************************************************
	 * @param bStroke
	 * @return
	 ************************************************************************/
		
	private boolean isStrokeSameAsLast(BasicStroke bStroke) {
		return !bStroke.equals(this.lastStroke);
	}

	/*************************************************************************
	 * @return the next higher counter.
	 ************************************************************************/
	
	protected int getNextID() {
		return (nodeCounter++);
	}

	/*************************************************************************
	 * @param shapeName
	 * @param buffer
	 ************************************************************************/
	
	protected abstract void generateShapeDrawCode(String shapeName, StringBuilder buffer);

	/*************************************************************************
	 * @param bStroke
	 * @param buffer
	 ************************************************************************/
	
	protected abstract void generateBasicStrokeCode(BasicStroke bStroke, StringBuilder buffer);

	/*************************************************************************
	 * Transcodes the specified shape node.
	 * @param node
	 *            Shape node.
	 * @param comment
	 *            Comment (for associating the Java2D section with the
	 *            corresponding SVG section).
	 * @param buffer
	 ************************************************************************/
	
	private void transcodeShapeNode(ShapeNode node, String comment, StringBuilder buffer) {
		ShapePainter sPainter = node.getShapePainter();
		transcodeShapePainter(sPainter, buffer, null);
	}

	/*************************************************************************
	 * Transcodes the specified composite graphics node.
	 * 
	 * @param node
	 *            Composite graphics node.
	 * @param comment
	 *            Comment (for associating the Java2D section with the
	 *            corresponding SVG section).
	 * @param buffer
	 ************************************************************************/
	
	private void transcodeCompositeGraphicsNode(CompositeGraphicsNode node,
			String comment, StringBuilder buffer) {
		int count = 0;
		for (Object obj : node.getChildren()) {
			
			// TODO rossi 08.07.2010 all at same "level" so optimize the transform.
			
			transcodeGraphicsNode((GraphicsNode) obj, comment+"_"+ count, buffer);
			count++;
		}
	}

	/*************************************************************************
	 * @param node 
	 * @param methodPostfix
	 ************************************************************************/
	
	protected abstract void generatePaintMethodSignatureCode(GraphicsNode node, String methodPostfix, StringBuilder buffer);

	/*************************************************************************
	 * @param node 
	 * @param methodPostfix
	 * @param buffer
	 ************************************************************************/
	
	protected abstract void generateMethodInvocationCode(GraphicsNode node, String methodPostfix, StringBuilder buffer);

	/*************************************************************************
	 * Transcodes the specified graphics node.
	 * @param node
	 *            Graphics node.
	 * @param comment
	 *            Comment (for associating the Java2D section with the
	 *            corresponding SVG section).
	 * @param buffer
	 ************************************************************************/
	
	private void transcodeGraphicsNode(GraphicsNode node, String comment, StringBuilder buffer) {
		buffer.append("\t\t// ").append(comment).append("\n");
		transcodeAlphaComposite(node, buffer);
		generateTransformPushCode(comment, buffer);
		generateTransformCode(node, buffer);
		transcodeGraphicsNodePaintcode(node, comment, buffer);
		generateTransformPopCode(comment, buffer);
	}
	
	/*************************************************************************
	 * @param node
	 * @param buffer
	 ************************************************************************/
	
	protected abstract void generateTransformCode(GraphicsNode node, StringBuilder buffer);

	/*************************************************************************
	 * @param node
	 * @param comment
	 * @param buffer
	 ************************************************************************/
	
	private void transcodeGraphicsNodePaintcode(GraphicsNode node,
			String comment, StringBuilder buffer) {
		generateMethodInvocationCode(node, comment, buffer);
		StringBuilder sectionBuffer = new StringBuilder();
		generatePaintMethodSignatureCode(node, comment, sectionBuffer);
		transcodeGraphicsNodeMethodPaintCode(node, comment, sectionBuffer);
		generateMethodClose(sectionBuffer);
		this.methodCodeBuffer.append(sectionBuffer);
	}

	/*************************************************************************
	 * @param buffer
	 ************************************************************************/
	
	protected void generateMethodClose(StringBuilder buffer) {
		buffer.append("\t}\n\n");
	}

	/*************************************************************************
	 * @param node
	 * @param comment
	 * @param buffer
	 ************************************************************************/
	
	private void transcodeGraphicsNodeMethodPaintCode(GraphicsNode node,
			String comment, StringBuilder buffer) {
		if (node instanceof ShapeNode) {
			transcodeShapeNode((ShapeNode) node, comment, buffer);
		} else
		if (node instanceof CompositeGraphicsNode) {
			transcodeCompositeGraphicsNode((CompositeGraphicsNode) node,
					comment, buffer);
		} else 
		if (node instanceof TextNode) {
			transcodeTextNode((TextNode) node, buffer);
		} else 
		if (node instanceof RasterImageNode) {
			// TODO Bros 08.07.2010 perhaps we can embed the image data in an array and paint from there. 
			notifyUnsupportedOperation(node, "Unsupported node type "+
					node.getClass().getCanonicalName());
		} else {
			notifyUnsupportedOperation(node, "Unsupported node type "+
					node.getClass().getCanonicalName());
		}
	}
	
	/*************************************************************************
     * Paints the specified text node using the specified Graphics2D.
     * @param node the text node to paint
     * @param buffer
	 ************************************************************************/
	
	private void transcodeTextNode(TextNode node, StringBuilder buffer) {
        AttributedCharacterIterator aci;
        aci = node.getAttributedCharacterIterator();
        if (aci == null) {
            return;
        }
        StrokingTextPainter strokingPainter = (StrokingTextPainter) node.getTextPainter();
        @SuppressWarnings("unchecked")
		List<TextRun> textRuns = strokingPainter.getTextRuns(node, aci);
        transcodeTextDecorations(textRuns, TextSpanLayout.DECORATION_UNDERLINE, buffer);
        transcodeTextDecorations(textRuns, TextSpanLayout.DECORATION_OVERLINE, buffer);
        transcodeTextRuns(textRuns, buffer);
        transcodeTextDecorations(textRuns,TextSpanLayout.DECORATION_STRIKETHROUGH, buffer);
    }
	
    /*************************************************************************
     * Paints decorations of the specified type.
     * @param textRuns
     * @param decorationType
     * @param buffer
     ************************************************************************/
    
    private void transcodeTextDecorations(List<TextRun> textRuns, int decorationType, StringBuilder buffer) {
        Paint prevPaint = null;
        Paint prevStrokePaint = null;
        Stroke prevStroke = null;
        Rectangle2D decorationRect = null;
        double yLoc = 0, height = 0;

        for (int i = 0; i < textRuns.size(); i++) {
            TextRun textRun = textRuns.get(i);
            AttributedCharacterIterator runaci = textRun.getACI();
            runaci.first();

            TextPaintInfo tpi = (TextPaintInfo)runaci.getAttribute(PAINT_INFO);
            if ((tpi != null) && (tpi.composite != null)) {
            	transcodeComposite(tpi.composite, buffer);
            }

            Paint  paint       = null;
            Stroke stroke      = null;
            Paint  strokePaint = null;
            if (tpi != null) {
                switch (decorationType) {
                case TextSpanLayout.DECORATION_UNDERLINE :
                    paint       = tpi.underlinePaint;
                    stroke      = tpi.underlineStroke;
                    strokePaint = tpi.underlineStrokePaint;
                    break;
                case TextSpanLayout.DECORATION_OVERLINE :
                    paint       = tpi.overlinePaint;
                    stroke      = tpi.overlineStroke;
                    strokePaint = tpi.overlineStrokePaint;
                    break;
                case TextSpanLayout.DECORATION_STRIKETHROUGH :
                    paint       = tpi.strikethroughPaint;
                    stroke      = tpi.strikethroughStroke;
                    strokePaint = tpi.strikethroughStrokePaint;
                    break;
                default:
                    // should never get here
                    return;
                }
            }

            if (textRun.isFirstRunInChunk()) {
                Shape s = textRun.getLayout().getDecorationOutline(decorationType);
                Rectangle2D r2d = s.getBounds2D();
                yLoc   = r2d.getY();
                height = r2d.getHeight();
            }

            if (textRun.isFirstRunInChunk() ||
                (paint != prevPaint) ||
                (stroke != prevStroke) ||
                (strokePaint != prevStrokePaint)) {
                // if there is a current decoration, draw it now
                if (decorationRect != null) {
                	fillAndStrokeShape(decorationRect, prevPaint, prevStroke, prevStrokePaint, buffer);
                    decorationRect = null;
                }
            }

            if ((paint != null || strokePaint != null)
                && !textRun.getLayout().isVertical()
                && !textRun.getLayout().isOnATextPath()) {

                // this text run should be decorated with the
                // specified decoration type
                // NOTE: decorations are only supported for plain
                // horizontal layouts

                Shape decorationShape =
                    textRun.getLayout().getDecorationOutline(decorationType);
                if (decorationRect == null) {
                    // create a new one
                    Rectangle2D r2d = decorationShape.getBounds2D();
                    decorationRect = new Rectangle2D.Double
                        (r2d.getX(), yLoc, r2d.getWidth(), height);
                } else {
                    // extend the current one
                    Rectangle2D bounds = decorationShape.getBounds2D();
                    double minX = Math.min(decorationRect.getX(),
                                           bounds.getX());
                    double maxX = Math.max(decorationRect.getMaxX(),
                                           bounds.getMaxX());
                    decorationRect.setRect(minX, yLoc, maxX-minX, height);
                }
            }
            prevPaint = paint;
            prevStroke = stroke;
            prevStrokePaint = strokePaint;
        }

        // if there is a decoration rect that hasn't been drawn yet, draw it now
        if (decorationRect != null) {
        	fillAndStrokeShape(decorationRect, prevPaint, prevStroke, prevStrokePaint, buffer);
        }
    }

    /*************************************************************************
     * Paints the text in each text run. Decorations are not painted here.
     * @param textRuns
     * @param buffer
     ************************************************************************/
    
    private void transcodeTextRuns(List<TextRun> textRuns, StringBuilder buffer) {
        for (int i = 0; i < textRuns.size(); i++) {
            TextRun textRun = textRuns.get(i);
            AttributedCharacterIterator runaci = textRun.getACI();
            runaci.first();

            TextPaintInfo tpi = (TextPaintInfo)runaci.getAttribute(PAINT_INFO);
            if ((tpi != null) && (tpi.composite != null)) {
            	transcodeComposite(tpi.composite, buffer);
            }
            TextSpanLayout textLayout = textRun.getLayout();
            
            // Call this to make sure that the internal sync method is called.
            // TODO Bros 08.07.2010 or call it per reflection. 
            // textLayout.draw(g2d);
			GVTGlyphVector glyphVector = textLayout.getGlyphVector();
            if (glyphVector instanceof AWTGVTGlyphVector) {
				transcodeGlyphVector((AWTGVTGlyphVector) glyphVector, runaci, buffer);
			} else {
				notifyUnsupportedOperation(glyphVector, "Unsupported GlyphVector type "+
						glyphVector.getClass().getCanonicalName());
			}
        }
    }
    
    /*************************************************************************
     * Draws this glyph vector.
     * @param gv
     * @param aci
     * @param buffer
     ************************************************************************/
    
    private void transcodeGlyphVector(AWTGVTGlyphVector gv,
                     AttributedCharacterIterator aci, StringBuilder buffer) {
        aci.first();
        TextPaintInfo tpi = (TextPaintInfo)aci.getAttribute
            (GVTAttributedCharacterIterator.TextAttribute.PAINT_INFO);
        if (tpi == null || !tpi.visible) {
        	return;
        }
        Paint  fillPaint   = tpi.fillPaint;
        Stroke stroke      = tpi.strokeStroke;
        Paint  strokePaint = tpi.strokePaint;

        if (fillPaint == null && (strokePaint == null || stroke == null)) {
            return;
        }
        
        boolean useHinting = false;
        if (useHinting) {
        	// TODO rossi 08.07.2010 Implement hinting version that gives better results. 
//            int numGlyphs = gv.getNumGlyphs();
//            double sf = gv.scaleFactor;
//            double [] mat = new double[6];
//            for (int i=0; i< numGlyphs; i++) {
//                Point2D         pos = gv.getGlyphPosition(i);
//                double x = pos.getX();
//                double y = pos.getY();
//                AffineTransform at = gv.getGlyphTransform(i);
//                if (at != null) {
//                    // Scale the translate portion of matrix,
//                    // and add it into the position.
//                    at.getMatrix(mat);
//                    x += mat[4];
//                    y += mat[5];
//                    if ((mat[0] != 1) || (mat[1] != 0) ||
//                        (mat[2] != 0) || (mat[3] != 1)) {
//                        // More than just translation.
//                        mat[4] = 0; mat[5] = 0;
//                        at = new AffineTransform(mat);
//                    } else {
//                        at = null;
//                    }
//                }
//                pos = new Point2D.Double(x/sf, y/sf);
//                gv.awtGlyphVector.setGlyphPosition(i, pos);
//                awtGlyphVector.setGlyphTransform(i, at);
//            }
//            graphics2D.scale(sf, sf);
//            graphics2D.setPaint(fillPaint);
//            graphics2D.drawGlyphVector(awtGlyphVector, 0, 0);
//            graphics2D.scale(1/sf, 1/sf);
//
//            for (int i=0; i< numGlyphs; i++) {
//                Point2D         pos = gv.defaultGlyphPositions[i];
//                awtGlyphVector.setGlyphPosition(i, pos);
//                awtGlyphVector.setGlyphTransform(i, null);
//            }

        } else {
            Shape outline = gv.getOutline();
        	fillAndStrokeShape(outline, fillPaint, stroke, strokePaint, buffer);
        }
    }

	/*************************************************************************
	 * @param shape
	 * @param fillPaint
	 * @param stroke
	 * @param strokePaint
	 * @param buffer
	 ************************************************************************/
	
	private void fillAndStrokeShape(Shape shape, Paint fillPaint,
			Stroke stroke, Paint strokePaint, StringBuilder buffer) {
		CompositeShapePainter fp = new CompositeShapePainter(shape);
		if (fillPaint != null) {
			FillShapePainter fillPainter = new FillShapePainter(shape);
			fillPainter.setPaint(fillPaint);
			fp.addShapePainter(fillPainter);
		}
		if (stroke != null && strokePaint != null) {
			StrokeShapePainter strokePainter = new StrokeShapePainter(shape);
			strokePainter.setPaint(strokePaint);
			strokePainter.setStroke(stroke);
			fp.addShapePainter(strokePainter);
		}
		transcodeShapePainter(fp, buffer, null);
	}

//	/*************************************************************************
//	 * @param gv
//	 * @param numGlyphs
//	 * @param fillPaint
//	 * @param stroke
//	 * @param strokePaint
//	 * @return
//	 ************************************************************************/
//	
//	private boolean shouldWeUseHinting(AWTGVTGlyphVector gv, int numGlyphs,
//			Paint fillPaint, Stroke stroke, Paint strokePaint) {
//		String s = System.getProperty("java.specification.version");
//        boolean outlinesPositioned;
//		boolean drawGlyphVectorWorks;
//		boolean glyphVectorTransformWorks;
//		if ("1.4".compareTo(s) <= 0) {
//            outlinesPositioned = true;
//            drawGlyphVectorWorks = true;
//            glyphVectorTransformWorks = true;
//        } else if ("Mac OS X".equals(System.getProperty("os.name"))) {
//            outlinesPositioned = true;
//            drawGlyphVectorWorks = false;
//            glyphVectorTransformWorks = false;
//        } else {
//            outlinesPositioned = false;
//            drawGlyphVectorWorks = true;
//            glyphVectorTransformWorks = false;
//        }
//        
//        boolean useHinting = drawGlyphVectorWorks;
//        if (useHinting && (stroke != null) && (strokePaint != null))
//            // Can't stroke with drawGlyphVector.
//            useHinting = false;
//
//        if (useHinting &&
//            (fillPaint != null) && !(fillPaint instanceof Color))
//            // The coordinate system is different for drawGlyphVector.
//            // So complex paints aren't positioned properly.
//            useHinting = false;
//
//        if (useHinting) {
//        	// rossi 08.07.2010 extract in own method
////            Object v1 = graphics2D.getRenderingHint
////                (RenderingHints.KEY_TEXT_ANTIALIASING);
////            Object v2 = graphics2D.getRenderingHint
////                (RenderingHints.KEY_STROKE_CONTROL);
//            // text-rendering = geometricPrecision so fill shapes.
////            if ((v1 == RenderingHints.VALUE_TEXT_ANTIALIAS_ON) &&
////                (v2 == RenderingHints.VALUE_STROKE_PURE))
////                useHinting = false;
//        }
//
//        final int typeGRot   = AffineTransform.TYPE_GENERAL_ROTATION;
//        final int typeGTrans = AffineTransform.TYPE_GENERAL_TRANSFORM;
//
//        if (useHinting) {
//            // Check if usr->dev transform has general rotation,
//            // or shear..
////            AffineTransform at = graphics2D.getTransform();
////            int type = at.getType();
////            if (((type & typeGTrans) != 0) || ((type & typeGRot)  != 0))
////                useHinting = false;
//        }
//
//        if (useHinting) {
//            for (int i=0; i<numGlyphs; i++) {
//                if (!gv.isGlyphVisible(i)) {
//                    useHinting = false;
//                    break;
//                }
//                AffineTransform at = gv.getGlyphTransform(i);
//                if (at != null) {
//                    int type = at.getType();
//                    if ((type & ~AffineTransform.TYPE_TRANSLATION) == 0) {
//                        // Just translation
//                    } else if (glyphVectorTransformWorks &&
//                               ((type & typeGTrans) == 0) &&
//                               ((type & typeGRot)   == 0)) {
//                        // It's a simple 90Deg rotate, and we can put
//                        // it into the GlyphVector.
//                    } else {
//                        // we can't (or it doesn't make sense
//                        // to use the GlyphVector.
//                        useHinting = false;
//                        break;
//                    }
//                }
//            }
//        }
//		return useHinting;
//	}

	/*************************************************************************
	 * @param composite
	 * @param buffer
	 ************************************************************************/
	
	private void transcodeComposite(Composite composite, StringBuilder buffer) {
    	if (composite instanceof AlphaComposite) {
    		generateAlphaCompositeCode((AlphaComposite) composite, buffer);
    	} else {
    		notifyUnsupportedOperation(composite, "Composite not supported "+
    				composite.getClass().getCanonicalName());
    	}
	}

	/*************************************************************************
	 * @param comment
	 * @param buffer
	 ************************************************************************/
	
	protected abstract void generateTransformPopCode(String comment, StringBuilder buffer);

	/*************************************************************************
	 * @param comment
	 * @param buffer
	 ************************************************************************/
	
	protected abstract void generateTransformPushCode(String comment, StringBuilder buffer);
	
	/*************************************************************************
	 * @param node
	 * @param buffer
	 ************************************************************************/
	
	private void transcodeAlphaComposite(GraphicsNode node, StringBuilder buffer) {
		AlphaComposite composite = (AlphaComposite) node.getComposite();
		if (composite != null && !isSameAsLastComposite(composite)) {
			generateAlphaCompositeCode(composite, buffer);
			this.lastComposite = composite;
		}
	}

	/*************************************************************************
	 * @param composite
	 * @return
	 ************************************************************************/
	
	private boolean isSameAsLastComposite(AlphaComposite composite) {
		return lastComposite != null && lastComposite.getRule() == composite.getRule() && lastComposite.getAlpha() == composite.getAlpha();
	}

	/*************************************************************************
	 * @param composite
	 * @param buffer
	 ************************************************************************/
	
	protected abstract void generateAlphaCompositeCode(AlphaComposite composite, StringBuilder buffer);
	
}
