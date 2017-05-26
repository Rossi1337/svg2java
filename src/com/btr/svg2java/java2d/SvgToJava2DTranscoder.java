package com.btr.svg2java.java2d;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

import org.apache.batik.ext.awt.LinearGradientPaint;
import org.apache.batik.ext.awt.MultipleGradientPaint;
import org.apache.batik.ext.awt.RadialGradientPaint;
import org.apache.batik.ext.awt.MultipleGradientPaint.ColorSpaceEnum;
import org.apache.batik.ext.awt.MultipleGradientPaint.CycleMethodEnum;
import org.apache.batik.gvt.GraphicsNode;

import com.btr.svg2java.AbstractSvgTranscoder;

/*****************************************************************************
 * Transcoder that will generate Java Graphics2D painting code out of a svg file.
 *
 * @author  Bernd Rosstauscher 
 *         (svg2java(@)rosstauscher.de)
 ****************************************************************************/

public class SvgToJava2DTranscoder extends AbstractSvgTranscoder {
	
	private static final String TEMPLATE_FILE = "SvgToJava2DTemplate.templ";
	private boolean isAntialiasingOn = true;
	private boolean isStrokePure = false;
	
	/*************************************************************************
	 * Constructor
	 ************************************************************************/
	
	public SvgToJava2DTranscoder() {
		super();
		overrideDefaultTemplateFile(TEMPLATE_FILE);
	}

	/*************************************************************************
	 * @return Returns the isAntialiasingOn.
	 ************************************************************************/
	
	public final boolean isAntialiasingOn() {
		return isAntialiasingOn;
	}

	/*************************************************************************
	 * @param isAntialiasingOn The isAntialiasingOn to set.
	 ************************************************************************/
	
	public final void setAntialiasingOn(boolean isAntialiasingOn) {
		this.isAntialiasingOn = isAntialiasingOn;
	}

	/*************************************************************************
	 * @return Returns the isStrokePure.
	 ************************************************************************/
	
	public final boolean isStrokePure() {
		return isStrokePure;
	}

	/*************************************************************************
	 * @param isStrokePure The isStrokePure to set.
	 ************************************************************************/
	
	public final void setStrokePure(boolean isStrokePure) {
		this.isStrokePure = isStrokePure;
	}
	
	/*************************************************************************
	 * @param methodPostfix
	 ************************************************************************/
	@Override
	protected void generatePaintMethodSignatureCode(GraphicsNode node, String methodPostfix, StringBuilder buffer) {
		buffer.append("\tprivate void paint")
			.append(node.getClass().getSimpleName())
			.append(methodPostfix)
			.append("(Graphics2D g) {\n");
	}

	/*************************************************************************
	 * @param methodPostfix
	 * @param buffer
	 ************************************************************************/
	@Override
	protected void generateMethodInvocationCode(GraphicsNode node, String methodPostfix,
			StringBuilder buffer) {
		buffer.append("\t\tpaint")
			.append(node.getClass().getSimpleName())
			.append(methodPostfix)
			.append("(g);\n");
	}
	
	/*************************************************************************
	 * @return the generated code
	 ************************************************************************/
	@Override
	protected String generateRenderingHintsCode() {
		StringBuffer buffer = new StringBuffer();
		if (this.isAntialiasingOn) {
			buffer.append("\t\tg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);");
		}
		if (this.isStrokePure) {
			buffer.append("\t\tg.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);");
		}
		return buffer.toString();
	}

	/*************************************************************************
	 * @param node
	 * @param buffer
	 ************************************************************************/
	@Override
	protected void generateTransformCode(GraphicsNode node, StringBuilder buffer) {
		AffineTransform transform = node.getTransform();
		if (transform != null) {
			double[] transfMatrix = getTransformMatrixForGradient(transform);
			buffer.append("\t\tg.transform(new AffineTransform(")
					.append(transfMatrix[0]).append("f, ")
					.append(transfMatrix[1]).append("f, ")
					.append(transfMatrix[2]).append("f, ")
					.append(transfMatrix[3]).append("f, ")
					.append(transfMatrix[4]).append("f, ")
					.append(transfMatrix[5]).append("f));\n");
		}
	}

	/*************************************************************************
	 * @param comment
	 * @param buffer
	 ************************************************************************/
	@Override
	protected void generateTransformPopCode(String comment, StringBuilder buffer) {
		buffer.append("\t\tg.setTransform(trans").append(comment)
				.append(");\n");
	}

	/*************************************************************************
	 * @param comment
	 * @param buffer
	 ************************************************************************/
	@Override
	protected void generateTransformPushCode(String comment, StringBuilder buffer) {
		buffer.append("\t\tAffineTransform trans").append(comment)
				.append(" = g.getTransform();\n");
	}
	
	/*************************************************************************
	 * @param composite
	 * @param buffer
	 ************************************************************************/
	@Override
	protected void generateAlphaCompositeCode(AlphaComposite composite, StringBuilder buffer) {
		buffer.append("\t\tg.setComposite(AlphaComposite.getInstance(")
				.append(composite.getRule()).append(", ")
				.append(composite.getAlpha()).append("f * origAlpha));\n");
	}

	/*************************************************************************
	 * @param bStroke
	 * @param buffer
	 ************************************************************************/
	@Override
	protected void generateBasicStrokeCode(BasicStroke bStroke,
			StringBuilder buffer) {
		String dashRep = getDashRepresentation(bStroke.getDashArray());
		buffer.append("\t\tg.setStroke(new BasicStroke(")
			.append(bStroke.getLineWidth()).append("f,")
			.append(bStroke.getEndCap()).append(",")
			.append(bStroke.getLineJoin()).append(",")
			.append(bStroke.getMiterLimit()).append("f,")
			.append(dashRep).append(",")
			.append(bStroke.getDashPhase()).append("f));\n");
	}

	/*************************************************************************
	 * @param dash
	 * @return
	 ************************************************************************/
	
	private String getDashRepresentation(float[] dash) {
		StringBuffer dashRep = new StringBuffer();
		if (dash == null) {
			dashRep.append("null");
		} else {
			String sep = "";
			dashRep.append("new float[] {");
			for (float _dash : dash) {
				dashRep.append(sep);
				dashRep.append(_dash + "f");
				sep = ",";
			}
			dashRep.append("}");
		}
		return dashRep.toString();
	}
	
	/*************************************************************************
	 * @param shapeName
	 * @param buffer
	 ************************************************************************/
	@Override
	protected void generateShapeDrawCode(String shapeName, StringBuilder buffer) {
		buffer.append("\t\tg.draw("+shapeName+");\n");
	}

	/*************************************************************************
	 * @param shapeName
	 * @param buffer
	 ************************************************************************/
	@Override
	protected void generateShapeFillCode(String shapeName, StringBuilder buffer) {
		buffer.append("\t\tg.fill("+shapeName+");\n");
	}

	/*************************************************************************
	 * @param c
	 * @param buffer
	 ************************************************************************/
	@Override
	protected void generateSetColorCode(Color c, StringBuilder buffer) {
		buffer.append("\t\tg.setPaint(new Color(")
			.append(c.getRed()).append(", ")
			.append(c.getGreen()).append(", ")
			.append(c.getBlue()).append(", ")
			.append(c.getAlpha()).append("));\n");
	}
	
	/*************************************************************************
	 * generateRadialGradientPaintCode
	 * @see com.btr.svg2java.AbstractSvgTranscoder#generateRadialGradientPaintCode(org.apache.batik.ext.awt.RadialGradientPaint, java.lang.StringBuilder)
	 ************************************************************************/
	@Override
	protected void generateRadialGradientPaintCode(RadialGradientPaint paint, StringBuilder buffer)
			throws IllegalArgumentException {
		Point2D centerPoint = paint.getCenterPoint();
		Point2D focusPoint = paint.getFocusPoint();
		buffer.append("\t\tg.setPaint(new RadialGradientPaint(new Point2D.Double(")
			.append(centerPoint.getX()).append(", ")
			.append(centerPoint.getY()).append("), ")
			.append(paint.getRadius())
			.append("f, new Point2D.Double(")
			.append(focusPoint.getX()).append(", ")
			.append(focusPoint.getY()).append("), ");
		generateCommonPaintParameterCode(paint, buffer);
		buffer.append("));\n");
	}

	/*************************************************************************
	 * @param paint
	 * @param buffer
	 * @throws IllegalArgumentException
	 ************************************************************************/

	private void generateCommonPaintParameterCode(MultipleGradientPaint paint,
			StringBuilder buffer) throws IllegalArgumentException {
		float[] fractions = paint.getFractions();
		checkFractionsValidity(fractions);
		Color[] colors = paint.getColors();

		// TODO Bros 04.07.2010  make me nicer.
		markInvalidValues(fractions, colors);  
		List<Float> validFractions = new ArrayList<Float>();
		List<Color> validColors = new ArrayList<Color>();
		for (int i = 0; i < colors.length; i++) {
			if (colors[i] != null) {
				validFractions.add(fractions[i]);
				validColors.add(colors[i]);
			}
		}
		fractions = new float[validFractions.size()];
		colors = new Color[validColors.size()];
		for (int i = 0; i < colors.length; i++) {
			fractions[i] = validFractions.get(i);
			colors[i] = validColors.get(i);
		}
		String fractionsRep = getFractionsRepresentation(fractions);
		String colorsRep = getColorsRepresentation(colors);
		String cycleMethodRep = getCyclicMethodRepresentation(paint.getCycleMethod());
		String colorSpaceRep = getColorSpaceRepresentation(paint.getColorSpace());
		buffer.append(fractionsRep).append(", ")
			.append(colorsRep).append(", ")
			.append(cycleMethodRep).append(", ")
			.append(colorSpaceRep).append(", ");
		buffer.append(generateAffineTransformCode(paint.getTransform()));
	}

	/*************************************************************************
	 * @param fractions 
	 * @param fractions
	 * @param colors
	 ************************************************************************/

	private void markInvalidValues(float[] fractions, Color[] colors) {
		Color lastColor = null;
		float lastFraction = -1;
		for (int i = 0; i < colors.length; i++) {
			if (colors[i].equals(lastColor) && lastFraction == fractions[i]) {
				colors[i] = null;
			} else { 
				lastColor = colors[i];
				lastFraction = fractions[i];
			}
		}
	}

	/*************************************************************************
	 * generateLinearGradientPaintCode
	 * @see com.btr.svg2java.AbstractSvgTranscoder#generateLinearGradientPaintCode(org.apache.batik.ext.awt.LinearGradientPaint, java.lang.StringBuilder)
	 ************************************************************************/
	@Override
	protected void generateLinearGradientPaintCode(LinearGradientPaint paint, StringBuilder buffer)
			throws IllegalArgumentException {
		Point2D startPoint = paint.getStartPoint();
		Point2D endPoint = paint.getEndPoint();
		buffer.append("\t\tg.setPaint(new LinearGradientPaint(new Point2D.Double(")
			.append(startPoint.getX()).append(", ")
			.append(startPoint.getY())
			.append("), new Point2D.Double(")
			.append(endPoint.getX()).append(", ")
			.append(endPoint.getY()).append("), ");
		generateCommonPaintParameterCode(paint, buffer);
		buffer.append("));\n");
	}

	/*************************************************************************
	 * @param rect
	 * @param buffer
	 ************************************************************************/
	@Override
	protected String generateRectangle2DCode(Rectangle2D rect, StringBuilder buffer) {
		String shapeName = "shape"+getNextID();
		buffer.append("\t\tRectangle2D.Double ")
			.append(shapeName)
			.append(" = new Rectangle2D.Double(")
			.append(rect.getX()).append(", ")
			.append(rect.getY()).append(", ")
			.append(rect.getWidth()).append(", ")
			.append(rect.getHeight()).append(");\n");
		return shapeName;
	}
	
	/*************************************************************************
	 * @param buffer
	 * @param rRect
	 ************************************************************************/
	@Override
	protected String generateRoundRectangle2DCode(RoundRectangle2D rRect, StringBuilder buffer) {
		String shapeName = "shape"+getNextID();
		buffer.append("\t\tRoundRectangle2D.Double ")
			.append(shapeName)
			.append(" = new RoundRectangle2D.Double(")
			.append(rRect.getX()).append(", ")
			.append(rRect.getY()).append(", ")
			.append(rRect.getWidth()).append(", ")
			.append(rRect.getHeight()).append(", ")
			.append(rRect.getArcWidth()).append(", ")
			.append(rRect.getArcHeight()).append(");\n");
		return shapeName;
	}

	/*************************************************************************
	 * @param ell
	 * @param buffer
	 ************************************************************************/
	@Override
	protected String generateElipse2DCode(Ellipse2D ell, StringBuilder buffer) {
		String shapeName = "shape"+getNextID();
		buffer.append("\t\tEllipse2D.Double ")
			.append(shapeName)
			.append(" = new Ellipse2D.Double(")
			.append(ell.getX()).append(", ")
			.append(ell.getY()).append(", ")
			.append(ell.getWidth()).append(", ")
			.append(ell.getHeight()).append(");\n");
		return shapeName;
	}

	/*************************************************************************
	 * @param buffer
	 * @param l2df
	 ************************************************************************/
	@Override
	protected String generateLine2DFloatCode(Line2D.Float l2df, StringBuilder buffer) {
		String shapeName = "shape"+getNextID();
		buffer.append("\t\tLine2D.Float ")
			.append(shapeName)
			.append(" = new Line2D.Float(")
			.append(l2df.x1).append("f,")
			.append(l2df.y1).append("f,")
			.append(l2df.x2).append("f,")
			.append(l2df.y2).append("f,")
			.append(");\n");
		return shapeName;
	}
	
	/*************************************************************************
	 * generatePathIteratorCode
	 * @see com.btr.svg2java.AbstractSvgTranscoder#generatePathIteratorCode(java.awt.geom.PathIterator, java.lang.StringBuilder)
	 ************************************************************************/
	@Override
	protected String generatePathIteratorCode(PathIterator pathIterator, StringBuilder buffer) {
		String shapeName = "shape"+getNextID();
		buffer.append("\t\tGeneralPath ")
			.append(shapeName)
			.append(" = new GeneralPath();\n");

		float[] coords = new float[6];
		for (; !pathIterator.isDone(); pathIterator.next()) {
			int type = pathIterator.currentSegment(coords);
			switch (type) {
			case PathIterator.SEG_CUBICTO:
				generatePathCubicToCode(shapeName, coords, buffer);
				break;
			case PathIterator.SEG_QUADTO:
				generatePathQuadToCode(shapeName, coords, buffer);
				break;
			case PathIterator.SEG_MOVETO:
				generatePathMoveToCode(shapeName, coords, buffer);
				break;
			case PathIterator.SEG_LINETO:
				generatePathLineToCode(shapeName, coords, buffer);
				break;
			case PathIterator.SEG_CLOSE:
				generatePathCloseCode(shapeName, buffer);
				break;
			}
		}
		return shapeName;
	}

	/*************************************************************************
	 * @param shapeName
	 * @param buffer
	 ************************************************************************/
	
	private void generatePathCloseCode(String shapeName, StringBuilder buffer) {
		buffer.append("\t\t").append(shapeName).append(".closePath();\n");
	}

	/*************************************************************************
	 * @param buffer
	 * @param coords
	 * @param shapeName
	 ************************************************************************/
	
	private void generatePathLineToCode(String shapeName, float[] coords,
			StringBuilder buffer) {
		buffer.append("\t\t").append(shapeName).append(".lineTo(")
			.append(coords[0]).append(", ")
			.append(coords[1]).append(");\n");
	}

	/*************************************************************************
	 * @param shapeName
	 * @param coords
	 * @param buffer
	 ************************************************************************/
	
	
	private void generatePathMoveToCode(String shapeName, float[] coords,
			StringBuilder buffer) {
		buffer.append("\t\t").append(shapeName).append(".moveTo(")
			.append(coords[0]).append(", ")
			.append(coords[1]).append(");\n");
	}

	/*************************************************************************
	 * @param shapeName
	 * @param coords
	 * @param buffer
	 ************************************************************************/
	
	
	private void generatePathQuadToCode(String shapeName, float[] coords,
			StringBuilder buffer) {
		buffer.append("\t\t").append(shapeName).append(".quadTo(")
			.append(coords[0]).append(", ")
			.append(coords[1]).append(", ")
			.append(coords[2]).append(", ")
			.append(coords[3]).append(");\n");
	}

	/*************************************************************************
	 * @param shapeName
	 * @param coords
	 * @param buffer
	 ************************************************************************/
	
	
	private void generatePathCubicToCode(String shapeName, float[] coords,
			StringBuilder buffer) {
		buffer.append("\t\t").append(shapeName).append(".curveTo(")
			.append(coords[0]).append(", ")
			.append(coords[1]).append(", ")
			.append(coords[2]).append(", ")
			.append(coords[3]).append(", ")
			.append(coords[4]).append(", ")
			.append(coords[5]).append(");\n");
	}
	
	/*************************************************************************
	 * @param paint
	 * @param buffer
	 * @return 
	 ************************************************************************/
	
	private String generateAffineTransformCode(AffineTransform trans) {
		double[] transfMatrix = getTransformMatrixForGradient(trans);
		StringBuilder buffer = new StringBuilder();
		buffer.append("new AffineTransform(")
			.append(transfMatrix[0]).append("f, ")
			.append(transfMatrix[1]).append("f, ")
			.append(transfMatrix[2]).append("f, ")
			.append(transfMatrix[3]).append("f, ")
			.append(transfMatrix[4]).append("f, ")
			.append(transfMatrix[5]).append("f)");
		return buffer.toString();
	}
	
	/*************************************************************************
	 * @param transform
	 * @return
	 ************************************************************************/
	
	private double[] getTransformMatrixForGradient(AffineTransform transform) {
		double[] transfMatrix = new double[6];
		transform.getMatrix(transfMatrix);
		return transfMatrix;
	}

	/*************************************************************************
	 * @param fractions
	 * @throws IllegalArgumentException
	 ************************************************************************/
	
	private void checkFractionsValidity(float[] fractions) throws IllegalArgumentException {
		float previousFraction = -1.0f;
		for (float currentFraction : fractions) {
			if (currentFraction < 0f || currentFraction > 1f) {
				throw new IllegalArgumentException("Fraction values must "
						+ "be in the range 0 to 1: " + currentFraction);
			}
			if (currentFraction < previousFraction) {
				throw new IllegalArgumentException("Keyframe fractions "
						+ "must be non-decreasing: " + currentFraction);
			}
			previousFraction = currentFraction;
		}
	}

	/*************************************************************************
	 * @param fractions
	 * @param colors
	 * @return
	 ************************************************************************/
	
	private String getColorsRepresentation(Color[] colors) {
		StringBuffer colorsRep = new StringBuffer();
		String sep = "";
		colorsRep.append("new Color[] {");
		for (Color color : colors) {
			colorsRep.append(sep);
			colorsRep.append("new Color(")
				.append(color.getRed()).append(", ")
				.append(color.getGreen()).append(", ")
				.append(color.getBlue()).append(", ")
				.append(color.getAlpha()).append(")");
			sep = ",";
		}
		colorsRep.append("}");
		return colorsRep.toString();
	}

	/*************************************************************************
	 * @param colorSpace
	 * @return
	 ************************************************************************/
	
	private String getColorSpaceRepresentation(ColorSpaceEnum colorSpace) {
		String colorSpaceRep = null;
		if (colorSpace == MultipleGradientPaint.SRGB) {
			colorSpaceRep = "MultipleGradientPaint.ColorSpaceType.SRGB";
		}
		if (colorSpace == MultipleGradientPaint.LINEAR_RGB) {
			colorSpaceRep = "MultipleGradientPaint.ColorSpaceType.LINEAR_RGB";
		}
		return colorSpaceRep;
	}

	/*************************************************************************
	 * @param fractions
	 * @return
	 ************************************************************************/
	
	private String getFractionsRepresentation(float[] fractions) {
		float previousFraction;
		StringBuffer fractionsRep = new StringBuffer();
		if (fractions == null) {
			fractionsRep.append("null");
		} else {
			String sep = "";
			fractionsRep.append("new float[] {");
			previousFraction = -1.0f;
			for (float currentFraction : fractions) {
				fractionsRep.append(sep);   
				if (currentFraction == previousFraction) {
					// TODO Bros 04.07.2010 instead of this hack we skip all but keep the last one?
					currentFraction += 0.000001f; 
				}
				fractionsRep.append(currentFraction + "f");
				sep = ",";
				previousFraction = currentFraction;
			}
			fractionsRep.append("}");
		}
		return fractionsRep.toString();
	}

	/*************************************************************************
	 * @param cycleMethod
	 * @param cycleMethodRep
	 * @return
	 ************************************************************************/
	
	private String getCyclicMethodRepresentation(CycleMethodEnum cycleMethod) {
		String cycleMethodRep = null;
		if (cycleMethod == MultipleGradientPaint.NO_CYCLE) {
			cycleMethodRep = "MultipleGradientPaint.CycleMethod.NO_CYCLE";
		}
		if (cycleMethod == MultipleGradientPaint.REFLECT) {
			cycleMethodRep = "MultipleGradientPaint.CycleMethod.REFLECT";
		}
		if (cycleMethod == MultipleGradientPaint.REPEAT) {
			cycleMethodRep = "MultipleGradientPaint.CycleMethod.REPEAT";
		}
		return cycleMethodRep;
	}

}
