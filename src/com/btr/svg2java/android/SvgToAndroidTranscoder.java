package com.btr.svg2java.android;

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

import org.apache.batik.ext.awt.LinearGradientPaint;
import org.apache.batik.ext.awt.MultipleGradientPaint;
import org.apache.batik.ext.awt.RadialGradientPaint;
import org.apache.batik.ext.awt.MultipleGradientPaint.ColorSpaceEnum;
import org.apache.batik.ext.awt.MultipleGradientPaint.CycleMethodEnum;
import org.apache.batik.gvt.GraphicsNode;

import com.btr.svg2java.AbstractSvgTranscoder;

/*****************************************************************************
 * Transcoder that will generate Android canvas painting code out of a svg file.
 * 
 * Work in progress.
 * 
 * @author  Bernd Rosstauscher 
 *         (svg2java(@)rosstauscher.de)
 ****************************************************************************/

public class SvgToAndroidTranscoder extends AbstractSvgTranscoder {
	
	private static final String TEMPLATE_FILE = "SvgToAndroidTemplate.templ";
	
	/*************************************************************************
	 * Constructor
	 ************************************************************************/
	
	public SvgToAndroidTranscoder() {
		super();
		overrideDefaultTemplateFile(TEMPLATE_FILE);
	}
	
	/*************************************************************************
	 * @param methodPostfix
	 ************************************************************************/

	protected void generatePaintMethodSignatureCode(String methodPostfix, StringBuilder buffer) {
		buffer.append("private void paint").append(methodPostfix).append("(Canvas g) {\n");
	}

	/*************************************************************************
	 * @param methodPostfix
	 * @param buffer
	 ************************************************************************/
	
	protected void generateMethodInvocationCode(String methodPostfix,
			StringBuilder buffer) {
		buffer.append("paint").append(methodPostfix).append("(g);\n");
	}
	
	/*************************************************************************
	 * @return the generated code
	 ************************************************************************/
	@Override
	protected String generateRenderingHintsCode() {
		StringBuffer buffer = new StringBuffer();
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
			String matrixName = "m"+getNextID();
			buffer.append("Matrix "+matrixName+" = new Matrix();\n");
			buffer.append(matrixName).append(".setValues(new float[] {")
					.append(transfMatrix[0]).append("f, ").append(transfMatrix[1]).append("f, ")
					.append(transfMatrix[2]).append("f, ").append(transfMatrix[3]).append("f, ")
					.append(transfMatrix[4]).append("f, ").append(transfMatrix[5]).append("f, " +
					"0f, 0f, 1f});\n");
			buffer.append("g.concat(").append(matrixName).append(");\n");
		}
	}

	/*************************************************************************
	 * @param comment
	 * @param buffer
	 ************************************************************************/
	@Override
	protected void generateTransformPopCode(String comment, StringBuilder buffer) {
		buffer.append("g.setMatrix(m").append(comment).append(");\n");
	}

	/*************************************************************************
	 * @param comment
	 * @param buffer
	 ************************************************************************/
	@Override
	protected void generateTransformPushCode(String comment, StringBuilder buffer) {
		buffer.append("Matrix m").append(comment).append(" = g.getMatrix();\n");
	}
	
	/*************************************************************************
	 * @param composite
	 * @param buffer
	 ************************************************************************/
	@Override
	protected void generateAlphaCompositeCode(AlphaComposite composite, StringBuilder buffer) {
		int rule = composite.getRule();
		float alpha = composite.getAlpha();
		// TODO rossi 04.07.2010
		buffer.append("g.setComposite(AlphaComposite.getInstance(")
				.append(rule).append(", ").append(alpha).append("f * origAlpha));\n");
	}

	/*************************************************************************
	 * @param bStroke
	 * @param buffer
	 ************************************************************************/
	@Override
	protected void generateBasicStrokeCode(BasicStroke bStroke,
			StringBuilder buffer) {
		float width = bStroke.getLineWidth();
		int cap = bStroke.getEndCap();
		int join = bStroke.getLineJoin();
		float miterlimit = bStroke.getMiterLimit();
		float[] dash = bStroke.getDashArray();
		float dash_phase = bStroke.getDashPhase();

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
		buffer.append("g.setStroke(new BasicStroke(").append(width).append("f,")
				.append(cap).append(",").append(join).append(",").append(miterlimit).append("f,").append(dashRep)
				.append(",").append(dash_phase).append("f));\n");
	}
	
	/*************************************************************************
	 * @param shapeName
	 * @param buffer
	 ************************************************************************/
	@Override
	protected void generateShapeDrawCode(String shapeName, StringBuilder buffer) {
		String paintName = ""; // TODO rossi 04.07.2010 merge paint with fill?
		buffer.append(shapeName).append("draw(g, ").append(paintName).append(");\n");
	}

	/*************************************************************************
	 * @param shapeName
	 * @param buffer
	 ************************************************************************/
	@Override
	protected void generateShapeFillCode(String shapeName, StringBuilder buffer) {
		String paintName = ""; // TODO rossi 04.07.2010  merge paint with draw?
		buffer.append(shapeName).append("draw(g, ").append(paintName).append(");\n");
	}

	/*************************************************************************
	 * @param c
	 * @param buffer
	 ************************************************************************/
	@Override
	protected void generateSetColorCode(Color c, StringBuilder buffer) {
		// TODO rossi 04.07.2010 store it and make it available as lastPaint;
//		buffer.append("g.setPaint(new Color(").append(c.getRed()).append(", ")
//				.append(c.getGreen()).append(", ").append(c.getBlue()).append(", ").append(c.getAlpha())
//				.append("));\n");
	}
	
	/**
	 * Transcodes the specified radial gradient paint.
	 * 
	 * @param paint
	 *            Radial gradient paint.
	 * @throws IllegalArgumentException
	 *             if the fractions are not strictly increasing.
	 */
	@Override
	protected void generateRadialGradientPaintCode(RadialGradientPaint paint, StringBuilder buffer)
			throws IllegalArgumentException {
		Point2D centerPoint = paint.getCenterPoint();
		float radius = paint.getRadius();
		Point2D focusPoint = paint.getFocusPoint();
		buffer.append("g.setPaint(new RadialGradientPaint(new Point2D.Double(")
			.append(centerPoint.getX()).append(", ").append(centerPoint.getY())
			.append("), ").append(radius).append("f, new Point2D.Double(")
			.append(focusPoint.getX()).append(", ").append(focusPoint.getY()).append("), ");
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
		checkFractionsValidity(paint.getFractions());
		String fractionsRep = getFractionsRepresentation(paint.getFractions());
		String colorsRep = getColorsRepresentation(paint.getFractions(), paint.getColors());
		String cycleMethodRep = getCyclicMethodRepresentation(paint.getCycleMethod());
		String colorSpaceRep = getColorSpaceRepresentation(paint.getColorSpace());
		buffer.append(fractionsRep).append(", ").append(colorsRep)
						.append(", ").append(cycleMethodRep).append(", ").append(colorSpaceRep)
						.append(", ");
		buffer.append(generateAffineTransformCode(paint.getTransform()));
	}

	/**
	 * Transcodes the specified linear gradient paint.
	 * 
	 * @param paint
	 *            Linear gradient paint.
	 * @throws IllegalArgumentException
	 *             if the fractions are not strictly increasing.
	 */
	@Override
	protected void generateLinearGradientPaintCode(LinearGradientPaint paint, StringBuilder buffer)
			throws IllegalArgumentException {
		Point2D startPoint = paint.getStartPoint();
		Point2D endPoint = paint.getEndPoint();
		buffer.append("g.setPaint(new LinearGradientPaint(new Point2D.Double(")
					.append(startPoint.getX()).append(", ").append(startPoint.getY())
					.append("), new Point2D.Double(").append(endPoint.getX()).append(", ")
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
		buffer.append("Rectangle2D.Double "+shapeName+" = new Rectangle2D.Double(").append(rect.getX())
				.append(", ").append(rect.getY()).append(", ").append(rect.getWidth()).append(", ")
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
		buffer.append("RoundRectangle2D.Double "+shapeName+" = new RoundRectangle2D.Double(")
				.append(rRect.getX()).append(", ").append(rRect.getY()).append(", ")
				.append(rRect.getWidth()).append(", ").append(rRect.getHeight()).append(", ")
				.append(rRect.getArcWidth()).append(", ").append(rRect.getArcHeight()).append(");\n");
		return shapeName;
	}

	/*************************************************************************
	 * @param ell
	 * @param buffer
	 ************************************************************************/
	@Override
	protected String generateElipse2DCode(Ellipse2D ell, StringBuilder buffer) {
		String shapeName = "shape"+getNextID();
		buffer.append("Ellipse2D.Double "+shapeName+" = new Ellipse2D.Double(").append(ell.getX())
				.append(", ").append(ell.getY()).append(", ").append(ell.getWidth()).append(", ")
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
		buffer.append("Line2D.Float "+shapeName+" = new Line2D.Float(")
				.append(l2df.x1).append("f,")
				.append(l2df.y1).append("f,")
				.append(l2df.x2).append("f,")
				.append(l2df.y2).append("f,")
				.append(");\n");
		return shapeName;
	}
	
	/**
	 * Transcodes the specified path iterator.
	 * 
	 * @param pathIterator
	 *            Path iterator.
	 */
	@Override
	protected String generatePathIteratorCode(PathIterator pathIterator, StringBuilder buffer) {
		float[] coords = new float[6];
		String shapeName = "shape"+getNextID();
		buffer.append("GeneralPath "+shapeName+" = new GeneralPath();\n");
		for (; !pathIterator.isDone(); pathIterator.next()) {
			int type = pathIterator.currentSegment(coords);
			switch (type) {
			case PathIterator.SEG_CUBICTO:
				buffer.append(shapeName+".curveTo(").append(coords[0])
						.append(", ").append(coords[1]).append(", ").append(coords[2]).append(", ")
						.append(coords[3]).append(", ").append(coords[4]).append(", ").append(coords[5])
						.append(");\n");
				break;
			case PathIterator.SEG_QUADTO:
				buffer.append(shapeName+".quadTo(").append(coords[0])
						.append(", ").append(coords[1]).append(", ").append(coords[2]).append(", ")
						.append(coords[3]).append(");\n");
				break;
			case PathIterator.SEG_MOVETO:
				buffer.append(shapeName+".moveTo(").append(coords[0])
						.append(", ").append(coords[1]).append(");\n");
				break;
			case PathIterator.SEG_LINETO:
				buffer.append(shapeName+".lineTo(").append(coords[0])
						.append(", ").append(coords[1]).append(");\n");
				break;
			case PathIterator.SEG_CLOSE:
				buffer.append(shapeName+".closePath();\n");
				break;
			}
		}
		return shapeName;
	}
	
	/*************************************************************************
	 * @param paint
	 * @param buffer
	 * @return 
	 ************************************************************************/
	
	private String generateAffineTransformCode(AffineTransform trans) {
		double[] transfMatrix = getTransformMatrixForGradient(trans);
		StringBuilder buffer = new StringBuilder();
		buffer.append("new AffineTransform(").append(transfMatrix[0]).append("f, ")
						.append(transfMatrix[1]).append("f, ").append(transfMatrix[2]).append("f, ")
						.append(transfMatrix[3]).append("f, ").append(transfMatrix[4]).append("f, ")
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
	
	private String getColorsRepresentation(float[] fractions,
			Color[] colors) {
		StringBuffer colorsRep = new StringBuffer();
		if (fractions == null) {
			colorsRep.append("null");
		} else {
			String sep = "";
			colorsRep.append("new Color[] {");
			for (Color color : colors) {
				colorsRep.append(sep);
				colorsRep.append("new Color(" + color.getRed() + ", "
						+ color.getGreen() + ", " + color.getBlue() + ", "
						+ color.getAlpha() + ")");
				sep = ",";
			}
			colorsRep.append("}");
		}
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
				if (currentFraction == previousFraction)
					currentFraction += 0.000000001f;
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

	@Override
	protected void generateMethodInvocationCode(GraphicsNode node,
			String methodPostfix, StringBuilder buffer) {
		// TODO rossi 13.07.2010 Auto-generated method stub
		
	}

	@Override
	protected void generatePaintMethodSignatureCode(GraphicsNode node,
			String methodPostfix, StringBuilder buffer) {
		// TODO rossi 13.07.2010 Auto-generated method stub
		
	}


	
	
	

}
