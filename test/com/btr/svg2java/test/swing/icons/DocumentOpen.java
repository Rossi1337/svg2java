package com.btr.svg2java.test.swing.icons;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;

/**
 * This class has been automatically generated using svg2java
 * 
 */
public class DocumentOpen implements Icon {

	private float origAlpha = 1.0f;

	/**
	 * Paints the transcoded SVG image on the specified graphics context. You
	 * can install a custom transformation on the graphics context to scale the
	 * image.
	 * 
	 * @param g
	 *            Graphics context.
	 */
	public void paint(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		origAlpha = 1.0f;
		Composite origComposite = g.getComposite();
		if (origComposite instanceof AlphaComposite) {
			AlphaComposite origAlphaComposite = (AlphaComposite) origComposite;
			if (origAlphaComposite.getRule() == AlphaComposite.SRC_OVER) {
				origAlpha = origAlphaComposite.getAlpha();
			}
		}

		// _0
		AffineTransform trans_0 = g.getTransform();
		paintRootGraphicsNode_0(g);
		g.setTransform(trans_0);

	}

	private void paintShapeNode_0_0_1_0(Graphics2D g) {
		GeneralPath shape0 = new GeneralPath();
		shape0.moveTo(4.6200285, 38.651016);
		shape0.curveTo(4.6618366, 39.07147, 5.117414, 39.491924, 5.5311837,
				39.491924);
		shape0.lineTo(36.667347, 39.491924);
		shape0.curveTo(37.081116, 39.491924, 37.45308, 39.07147, 37.41127,
				38.651016);
		shape0.lineTo(34.714653, 11.531728);
		shape0.curveTo(34.672844, 11.111274, 34.217266, 10.69082, 33.803497,
				10.69082);
		shape0.lineTo(21.080082, 10.69082);
		shape0.curveTo(20.489536, 10.69082, 19.870998, 10.311268, 19.677221,
				9.730485);
		shape0.lineTo(18.574219, 6.4246087);
		shape0.curveTo(18.404966, 5.9173307, 18.02707, 5.6888137, 17.259747,
				5.6888137);
		shape0.lineTo(2.3224187, 5.6888137);
		shape0.curveTo(1.9086492, 5.6888137, 1.5366876, 6.109268, 1.5784956,
				6.529722);
		shape0.lineTo(4.6200285, 38.651016);
		shape0.closePath();
		g.setPaint(new RadialGradientPaint(new Point2D.Double(
				26.10677719116211, 38.19511413574219), 32.25977f,
				new Point2D.Double(26.10677719116211, 38.19511413574219),
				new float[] { 0.0f, 1.0f }, new Color[] {
						new Color(160, 160, 160, 255),
						new Color(168, 168, 168, 255) },
				MultipleGradientPaint.CycleMethod.NO_CYCLE,
				MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(
						1.0156350135803223f, 0.0f, 0.10310500115156174f,
						1.0005120038986206f, 0.0f, -0.08369457721710205f)));
		g.fill(shape0);
		g.setPaint(new Color(90, 90, 90, 255));
		g.setStroke(new BasicStroke(1.0f, 1, 1, 4.0f, null, 0.0f));
		g.draw(shape0);
	}

	private void paintShapeNode_0_0_1_1(Graphics2D g) {
		GeneralPath shape1 = new GeneralPath();
		shape1.moveTo(3.3386018, 17.533487);
		shape1.lineTo(34.48846, 17.533487);
		g.setPaint(new Color(114, 159, 207, 255));
		g.fill(shape1);
		g.setPaint(new Color(0, 0, 0, 255));
		g.setStroke(new BasicStroke(1.0000004f, 1, 1, 4.0f, null, 0.0f));
		g.draw(shape1);
	}

	private void paintShapeNode_0_0_1_2(Graphics2D g) {
		GeneralPath shape2 = new GeneralPath();
		shape2.moveTo(5.3301525, 37.533485);
		shape2.lineTo(35.317905, 37.533485);
		g.setPaint(new Color(114, 159, 207, 255));
		g.fill(shape2);
		g.setPaint(new Color(0, 0, 0, 255));
		g.setStroke(new BasicStroke(1.0000002f, 1, 1, 4.0f, null, 0.0f));
		g.draw(shape2);
	}

	private void paintShapeNode_0_0_1_3(Graphics2D g) {
		GeneralPath shape3 = new GeneralPath();
		shape3.moveTo(5.3301525, 35.533485);
		shape3.lineTo(35.317905, 35.533485);
		g.setPaint(new Color(114, 159, 207, 255));
		g.fill(shape3);
		g.setPaint(new Color(0, 0, 0, 255));
		g.draw(shape3);
	}

	private void paintShapeNode_0_0_1_4_0(Graphics2D g) {
		Rectangle2D.Double shape4 = new Rectangle2D.Double(-1559.2523193359375,
				-150.6968536376953, 1339.633544921875, 478.357177734375);
		g.setPaint(new LinearGradientPaint(new Point2D.Double(
				302.8571472167969, 366.64788818359375), new Point2D.Double(
				302.8571472167969, 609.5050659179688), new float[] { 0.0f,
				0.5f, 1.0f }, new Color[] { new Color(0, 0, 0, 0),
				new Color(0, 0, 0, 255), new Color(0, 0, 0, 0) },
				MultipleGradientPaint.CycleMethod.NO_CYCLE,
				MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(
						2.7743890285491943f, 0.0f, 0.0f, 1.9697060585021973f,
						-1892.178955078125f, -872.8853759765625f)));
		g.fill(shape4);
	}

	private void paintShapeNode_0_0_1_4_1(Graphics2D g) {
		GeneralPath shape5 = new GeneralPath();
		shape5.moveTo(-219.61876, -150.68037);
		shape5.curveTo(-219.61876, -150.68037, -219.61876, 327.65042,
				-219.61876, 327.65042);
		shape5.curveTo(-76.74459, 328.55087, 125.78146, 220.48074, 125.78138,
				88.45424);
		shape5.curveTo(125.78138, -43.572304, -33.655437, -150.68036,
				-219.61876, -150.68037);
		shape5.closePath();
		g.setPaint(new RadialGradientPaint(new Point2D.Double(
				605.7142944335938, 486.64788818359375), 117.14286f,
				new Point2D.Double(605.7142944335938, 486.64788818359375),
				new float[] { 0.0f, 1.0f }, new Color[] {
						new Color(0, 0, 0, 255), new Color(0, 0, 0, 0) },
				MultipleGradientPaint.CycleMethod.NO_CYCLE,
				MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(
						2.7743890285491943f, 0.0f, 0.0f, 1.9697060585021973f,
						-1891.633056640625f, -872.8853759765625f)));
		g.fill(shape5);
	}

	private void paintShapeNode_0_0_1_4_2(Graphics2D g) {
		GeneralPath shape6 = new GeneralPath();
		shape6.moveTo(-1559.2523, -150.68037);
		shape6.curveTo(-1559.2523, -150.68037, -1559.2523, 327.65042,
				-1559.2523, 327.65042);
		shape6.curveTo(-1702.1265, 328.55087, -1904.6525, 220.48074,
				-1904.6525, 88.45424);
		shape6.curveTo(-1904.6525, -43.572304, -1745.2157, -150.68036,
				-1559.2523, -150.68037);
		shape6.closePath();
		g.setPaint(new RadialGradientPaint(new Point2D.Double(
				605.7142944335938, 486.64788818359375), 117.14286f,
				new Point2D.Double(605.7142944335938, 486.64788818359375),
				new float[] { 0.0f, 1.0f }, new Color[] {
						new Color(0, 0, 0, 255), new Color(0, 0, 0, 0) },
				MultipleGradientPaint.CycleMethod.NO_CYCLE,
				MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(
						-2.7743890285491943f, 0.0f, 0.0f, 1.9697060585021973f,
						112.76229858398438f, -872.8853759765625f)));
		g.fill(shape6);
	}

	private void paintCompositeGraphicsNode_0_0_1_4(Graphics2D g) {
		// _0_0_1_4_0
		g.setComposite(AlphaComposite.getInstance(3, 0.40206185f * origAlpha));
		AffineTransform trans_0_0_1_4_0 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_4_0(g);
		g.setTransform(trans_0_0_1_4_0);
		// _0_0_1_4_1
		AffineTransform trans_0_0_1_4_1 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_4_1(g);
		g.setTransform(trans_0_0_1_4_1);
		// _0_0_1_4_2
		AffineTransform trans_0_0_1_4_2 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_4_2(g);
		g.setTransform(trans_0_0_1_4_2);
	}

	private void paintShapeNode_0_0_1_5(Graphics2D g) {
		GeneralPath shape7 = new GeneralPath();
		shape7.moveTo(6.171752, 38.418674);
		shape7.curveTo(6.203108, 38.729, 6.017127, 38.935886, 5.6963477,
				38.832443);
		shape7.lineTo(5.6963477, 38.832443);
		shape7.curveTo(5.3755684, 38.729, 5.14778, 38.522118, 5.1164236,
				38.21179);
		shape7.lineTo(2.0868573, 6.8445945);
		shape7.curveTo(2.0555012, 6.534267, 2.243451, 6.346871, 2.5537784,
				6.346871);
		shape7.lineTo(17.303532, 6.255425);
		shape7.curveTo(17.834814, 6.2521315, 18.04296, 6.308731, 18.18333,
				6.7726374);
		shape7.curveTo(18.18333, 6.7726374, 19.268703, 9.885435, 19.429564,
				10.470742);
		shape7.lineTo(17.873968, 7.553706);
		shape7.curveTo(17.608788, 7.0564437, 17.275225, 7.1399364, 16.901178,
				7.1399364);
		shape7.lineTo(3.7717774, 7.1399364);
		shape7.curveTo(3.4614503, 7.1399364, 3.2754695, 7.3468213, 3.3068254,
				7.657149);
		shape7.lineTo(6.285646, 38.522118);
		shape7.lineTo(6.171752, 38.418674);
		shape7.closePath();
		g.setPaint(new LinearGradientPaint(new Point2D.Double(
				5.265791416168213, 18.725862503051758), new Point2D.Double(
				8.212224006652832, 52.625850677490234), new float[] { 0.0f,
				1.0f }, new Color[] { new Color(255, 255, 255, 179),
				new Color(255, 255, 255, 0) },
				MultipleGradientPaint.CycleMethod.NO_CYCLE,
				MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(
						1.4626959562301636f, 0.0f, 0.06907907873392105f,
						0.6836689710617065f, 0.0f, 0.0f)));
		g.fill(shape7);
	}

	private void paintShapeNode_0_0_1_6(Graphics2D g) {
		GeneralPath shape8 = new GeneralPath();
		shape8.moveTo(2.3052332, 7.533487);
		shape8.lineTo(17.088966, 7.533487);
		g.setPaint(new Color(114, 159, 207, 255));
		g.fill(shape8);
		g.setPaint(new Color(0, 0, 0, 255));
		g.setStroke(new BasicStroke(0.9999998f, 1, 1, 4.0f, null, 0.0f));
		g.draw(shape8);
	}

	private void paintShapeNode_0_0_1_7(Graphics2D g) {
		GeneralPath shape9 = new GeneralPath();
		shape9.moveTo(2.7573333, 11.533487);
		shape9.lineTo(33.496216, 11.533487);
		g.setPaint(new Color(114, 159, 207, 255));
		g.fill(shape9);
		g.setPaint(new Color(0, 0, 0, 255));
		g.setStroke(new BasicStroke(1.0000001f, 1, 1, 4.0f, null, 0.0f));
		g.draw(shape9);
	}

	private void paintShapeNode_0_0_1_8_0(Graphics2D g) {
		GeneralPath shape10 = new GeneralPath();
		shape10.moveTo(41.785744, 9.0363865);
		shape10.curveTo(41.79537, 8.561804, 41.800934, 8.311881, 41.36235,
				8.312183);
		shape10.lineTo(28.80653, 8.32084);
		shape10.curveTo(28.50653, 8.32084, 28.481916, 8.177634, 28.80653,
				8.32084);
		shape10.curveTo(29.131144, 8.4640465, 30.053629, 8.979112, 30.989227,
				9.021835);
		shape10.curveTo(30.989227, 9.021835, 41.785706, 9.038299, 41.785744,
				9.0363865);
		shape10.closePath();
		g.setPaint(new Color(255, 255, 255, 148));
		g.fill(shape10);
	}

	private void paintCompositeGraphicsNode_0_0_1_8(Graphics2D g) {
		// _0_0_1_8_0
		AffineTransform trans_0_0_1_8_0 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_8_0(g);
		g.setTransform(trans_0_0_1_8_0);
	}

	private void paintShapeNode_0_0_1_9(Graphics2D g) {
		GeneralPath shape11 = new GeneralPath();
		shape11.moveTo(3.1628954, 15.533487);
		shape11.lineTo(33.99345, 15.533487);
		g.setPaint(new Color(114, 159, 207, 255));
		g.fill(shape11);
		g.setPaint(new Color(0, 0, 0, 255));
		g.setStroke(new BasicStroke(0.99999994f, 1, 1, 4.0f, null, 0.0f));
		g.draw(shape11);
	}

	private void paintShapeNode_0_0_1_10(Graphics2D g) {
		GeneralPath shape12 = new GeneralPath();
		shape12.moveTo(5.1594715, 33.533485);
		shape12.lineTo(35.147224, 33.533485);
		g.setPaint(new Color(114, 159, 207, 255));
		g.fill(shape12);
		g.setPaint(new Color(0, 0, 0, 255));
		g.setStroke(new BasicStroke(1.0000002f, 1, 1, 4.0f, null, 0.0f));
		g.draw(shape12);
	}

	private void paintShapeNode_0_0_1_11(Graphics2D g) {
		GeneralPath shape13 = new GeneralPath();
		shape13.moveTo(4.8658085, 31.533487);
		shape13.lineTo(34.974533, 31.533487);
		g.setPaint(new Color(114, 159, 207, 255));
		g.fill(shape13);
		g.setPaint(new Color(0, 0, 0, 255));
		g.setStroke(new BasicStroke(1.0000004f, 1, 1, 4.0f, null, 0.0f));
		g.draw(shape13);
	}

	private void paintShapeNode_0_0_1_12(Graphics2D g) {
		GeneralPath shape14 = new GeneralPath();
		shape14.moveTo(4.6336365, 29.533487);
		shape14.lineTo(34.80285, 29.533487);
		g.setPaint(new Color(114, 159, 207, 255));
		g.fill(shape14);
		g.setPaint(new Color(0, 0, 0, 255));
		g.draw(shape14);
	}

	private void paintShapeNode_0_0_1_13(Graphics2D g) {
		GeneralPath shape15 = new GeneralPath();
		shape15.moveTo(4.4629555, 27.533487);
		shape15.lineTo(34.632168, 27.533487);
		g.setPaint(new Color(114, 159, 207, 255));
		g.fill(shape15);
		g.setPaint(new Color(0, 0, 0, 255));
		g.draw(shape15);
	}

	private void paintShapeNode_0_0_1_14(Graphics2D g) {
		GeneralPath shape16 = new GeneralPath();
		shape16.moveTo(4.255672, 25.533487);
		shape16.lineTo(34.460793, 25.533487);
		g.setPaint(new Color(114, 159, 207, 255));
		g.fill(shape16);
		g.setPaint(new Color(0, 0, 0, 255));
		g.setStroke(new BasicStroke(1.0f, 1, 1, 4.0f, null, 0.0f));
		g.draw(shape16);
	}

	private void paintShapeNode_0_0_1_15(Graphics2D g) {
		GeneralPath shape17 = new GeneralPath();
		shape17.moveTo(4.02352, 23.533487);
		shape17.lineTo(34.2891, 23.533487);
		g.setPaint(new Color(114, 159, 207, 255));
		g.fill(shape17);
		g.setPaint(new Color(0, 0, 0, 255));
		g.setStroke(new BasicStroke(1.0000002f, 1, 1, 4.0f, null, 0.0f));
		g.draw(shape17);
	}

	private void paintShapeNode_0_0_1_16(Graphics2D g) {
		GeneralPath shape18 = new GeneralPath();
		shape18.moveTo(3.852839, 21.533487);
		shape18.lineTo(34.11842, 21.533487);
		g.setPaint(new Color(114, 159, 207, 255));
		g.fill(shape18);
		g.setPaint(new Color(0, 0, 0, 255));
		g.draw(shape18);
	}

	private void paintShapeNode_0_0_1_17_0(Graphics2D g) {
		GeneralPath shape19 = new GeneralPath();
		shape19.moveTo(41.785744, 9.0363865);
		shape19.curveTo(41.79537, 8.561804, 41.800934, 8.311881, 41.36235,
				8.312183);
		shape19.lineTo(28.80653, 8.32084);
		shape19.curveTo(28.50653, 8.32084, 28.481916, 8.177634, 28.80653,
				8.32084);
		shape19.curveTo(29.131144, 8.4640465, 30.053629, 8.979112, 30.989227,
				9.021835);
		shape19.curveTo(30.989227, 9.021835, 41.785706, 9.038299, 41.785744,
				9.0363865);
		shape19.closePath();
		g.setPaint(new Color(255, 255, 255, 148));
		g.fill(shape19);
	}

	private void paintCompositeGraphicsNode_0_0_1_17(Graphics2D g) {
		// _0_0_1_17_0
		AffineTransform trans_0_0_1_17_0 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_17_0(g);
		g.setTransform(trans_0_0_1_17_0);
	}

	private void paintShapeNode_0_0_1_18(Graphics2D g) {
		GeneralPath shape20 = new GeneralPath();
		shape20.moveTo(2.9642313, 13.533487);
		shape20.lineTo(33.990734, 13.533487);
		g.setPaint(new Color(114, 159, 207, 255));
		g.fill(shape20);
		g.setPaint(new Color(0, 0, 0, 255));
		g.setStroke(new BasicStroke(1.0000004f, 1, 1, 4.0f, null, 0.0f));
		g.draw(shape20);
	}

	private void paintShapeNode_0_0_1_19(Graphics2D g) {
		GeneralPath shape21 = new GeneralPath();
		shape21.moveTo(3.651419, 19.533487);
		shape21.lineTo(33.947216, 19.533487);
		g.setPaint(new Color(114, 159, 207, 255));
		g.fill(shape21);
		g.setPaint(new Color(0, 0, 0, 255));
		g.setStroke(new BasicStroke(1.0000001f, 1, 1, 4.0f, null, 0.0f));
		g.draw(shape21);
	}

	private void paintShapeNode_0_0_1_20(Graphics2D g) {
		GeneralPath shape22 = new GeneralPath();
		shape22.moveTo(2.5242572, 9.533487);
		shape22.lineTo(17.805073, 9.533487);
		g.setPaint(new Color(114, 159, 207, 255));
		g.fill(shape22);
		g.setPaint(new Color(0, 0, 0, 255));
		g.setStroke(new BasicStroke(1.0000002f, 1, 1, 4.0f, null, 0.0f));
		g.draw(shape22);
	}

	private void paintShapeNode_0_0_1_21(Graphics2D g) {
		GeneralPath shape23 = new GeneralPath();
		shape23.moveTo(34.375, 14.125);
		shape23.lineTo(37.0, 38.75);
		shape23.lineTo(6.0, 38.875);
		shape23.curveTo(6.0, 38.875, 4.125, 14.125, 4.125, 14.125);
		shape23.curveTo(4.125, 14.125, 34.5, 14.125, 34.375, 14.125);
		shape23.closePath();
		g.setPaint(new LinearGradientPaint(new Point2D.Double(22.25, 37.625),
				new Point2D.Double(19.75, 14.875), new float[] { 0.0f, 1.0f },
				new Color[] { new Color(0, 0, 0, 255), new Color(0, 0, 0, 0) },
				MultipleGradientPaint.CycleMethod.NO_CYCLE,
				MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(
						1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f)));
		g.fill(shape23);
	}

	private void paintShapeNode_0_0_1_22(Graphics2D g) {
		GeneralPath shape24 = new GeneralPath();
		shape24.moveTo(43.375, 2.4944034);
		shape24.curveTo(43.875, 19.373135, 34.29994, 21.022879, 37.36244,
				31.494661);
		shape24.curveTo(37.36244, 31.494661, 5.875, 32.380596, 5.875, 32.380596);
		shape24.curveTo(4.0, 19.527987, 14.25, 11.166045, 11.25, 2.649254);
		shape24.lineTo(43.375, 2.4944034);
		shape24.closePath();
		g.setPaint(new LinearGradientPaint(new Point2D.Double(25.875, 10.625),
				new Point2D.Double(25.25, 30.875), new float[] { 0.0f, 0.5f,
						1.0f }, new Color[] { new Color(250, 250, 250, 255),
						new Color(168, 168, 168, 255),
						new Color(205, 205, 205, 255) },
				MultipleGradientPaint.CycleMethod.NO_CYCLE,
				MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(
						1.0f, 0.0f, 0.0f, 1.2388060092926025f, 0.0f,
						-7.880597114562988f)));
		g.fill(shape24);
		g.setPaint(new RadialGradientPaint(new Point2D.Double(
				8.824419021606445, 3.7561285495758057), 37.751713f,
				new Point2D.Double(8.824419021606445, 3.7561285495758057),
				new float[] { 0.0f, 1.0f }, new Color[] {
						new Color(163, 163, 163, 255),
						new Color(76, 76, 76, 255) },
				MultipleGradientPaint.CycleMethod.NO_CYCLE,
				MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(
						0.9682729840278625f, 0.0f, 0.0f, 1.046686053276062f,
						44.36452865600586f, -17.007169723510742f)));
		g.setStroke(new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f));
		g.draw(shape24);
	}

	private void paintShapeNode_0_0_1_23(Graphics2D g) {
		GeneralPath shape25 = new GeneralPath();
		shape25.moveTo(15.4375, 6.5625);
		shape25.lineTo(39.0, 6.5625);
		g.setPaint(new Color(161, 161, 161, 255));
		g.draw(shape25);
	}

	private void paintShapeNode_0_0_1_24(Graphics2D g) {
		GeneralPath shape26 = new GeneralPath();
		shape26.moveTo(5.7785654, 39.065998);
		shape26.curveTo(5.8820076, 39.277466, 6.0888915, 39.488926, 6.399217,
				39.488926);
		shape26.lineTo(39.70767, 39.488926);
		shape26.curveTo(39.914562, 39.488926, 40.228832, 39.36262, 40.415844,
				39.224575);
		shape26.curveTo(40.946247, 38.83304, 41.070705, 38.61219, 41.308624,
				38.251106);
		shape26.curveTo(43.756752, 34.53565, 47.113766, 18.974215, 47.113766,
				18.974215);
		shape26.curveTo(47.21721, 18.762754, 47.010326, 18.551294, 46.7,
				18.551294);
		shape26.lineTo(11.776358, 18.551294);
		shape26.curveTo(11.466032, 18.551294, 10.120393, 34.658623, 6.913359,
				37.838318);
		shape26.lineTo(5.6751237, 39.065998);
		shape26.lineTo(5.7785654, 39.065998);
		shape26.closePath();
		g.setPaint(new LinearGradientPaint(new Point2D.Double(
				22.175975799560547, 36.987998962402344), new Point2D.Double(
				22.065330505371094, 32.050498962402344), new float[] { 0.0f,
				1.0f }, new Color[] { new Color(97, 148, 203, 255),
				new Color(114, 159, 207, 255) },
				MultipleGradientPaint.CycleMethod.NO_CYCLE,
				MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(
						1.0f, 0.0f, 0.0f, 1.0221179723739624f,
						52.05693817138672f, -1.3230259418487549f)));
		g.fill(shape26);
		g.setPaint(new Color(52, 101, 164, 255));
		g.setStroke(new BasicStroke(0.9999998f, 0, 1, 4.0f, null, 0.0f));
		g.draw(shape26);
	}

	private void paintShapeNode_0_0_1_25(Graphics2D g) {
		GeneralPath shape27 = new GeneralPath();
		shape27.moveTo(15.356073, 8.5625);
		shape27.lineTo(35.08142, 8.5625);
		g.setPaint(new Color(161, 161, 161, 255));
		g.setStroke(new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f));
		g.draw(shape27);
	}

	private void paintShapeNode_0_0_1_26(Graphics2D g) {
		GeneralPath shape28 = new GeneralPath();
		shape28.moveTo(13.134476, 20.138641);
		shape28.curveTo(12.361729, 25.129398, 11.633175, 29.147884, 10.418486,
				33.652504);
		shape28.curveTo(12.804971, 32.945396, 17.534601, 30.448, 27.534601,
				30.448);
		shape28.curveTo(37.534603, 30.448, 44.258175, 21.1993, 45.186253,
				20.094446);
		shape28.lineTo(13.134476, 20.138641);
		shape28.closePath();
		g.setPaint(new LinearGradientPaint(new Point2D.Double(
				14.899378776550293, 27.059642791748047), new Point2D.Double(
				22.71544647216797, 41.83689498901367),
				new float[] { 0.0f, 1.0f }, new Color[] {
						new Color(255, 255, 255, 34),
						new Color(255, 255, 255, 13) },
				MultipleGradientPaint.CycleMethod.NO_CYCLE,
				MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(
						1.5352989435195923f, 0.0f, 0.0f, 0.6513389945030212f,
						3.451417922973633f, 2.447999954223633f)));
		g.fill(shape28);
	}

	private void paintShapeNode_0_0_1_27(Graphics2D g) {
		GeneralPath shape29 = new GeneralPath();
		shape29.moveTo(15.143007, 10.5625);
		shape29.lineTo(39.457832, 10.5625);
		g.setPaint(new Color(161, 161, 161, 255));
		g.setStroke(new BasicStroke(1.0000001f, 0, 0, 4.0f, null, 0.0f));
		g.draw(shape29);
	}

	private void paintShapeNode_0_0_1_28(Graphics2D g) {
		GeneralPath shape30 = new GeneralPath();
		shape30.moveTo(45.820084, 19.6875);
		shape30.lineTo(12.661612, 19.6875);
		shape30.curveTo(12.661612, 19.6875, 10.513864, 35.707108, 7.93934,
				37.928078);
		shape30.curveTo(16.060417, 37.928078, 39.51051, 37.87944, 39.53033,
				37.87944);
		shape30.curveTo(41.28199, 37.87944, 44.43797, 25.243248, 45.820084,
				19.6875);
		shape30.closePath();
		g.setPaint(new LinearGradientPaint(new Point2D.Double(
				19.11611557006836, 28.946041107177734), new Point2D.Double(
				19.426923751831055, 51.91269302368164), new float[] { 0.0f,
				1.0f }, new Color[] { new Color(255, 255, 255, 255),
				new Color(255, 255, 255, 0) },
				MultipleGradientPaint.CycleMethod.NO_CYCLE,
				MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(
						1.4215370416641235f, 0.0f, 0.0f, 0.7034639716148376f,
						0.0f, 0.0f)));
		g.setStroke(new BasicStroke(1.0f, 1, 0, 4.0f, null, 0.0f));
		g.draw(shape30);
	}

	private void paintShapeNode_0_0_1_29(Graphics2D g) {
		GeneralPath shape31 = new GeneralPath();
		shape31.moveTo(14.398767, 12.5625);
		shape31.lineTo(38.25216, 12.5625);
		g.setPaint(new Color(161, 161, 161, 255));
		g.setStroke(new BasicStroke(1.0000002f, 0, 0, 4.0f, null, 0.0f));
		g.draw(shape31);
	}

	private void paintShapeNode_0_0_1_30(Graphics2D g) {
		GeneralPath shape32 = new GeneralPath();
		shape32.moveTo(13.629028, 14.5625);
		shape32.lineTo(36.97533, 14.5625);
		g.setStroke(new BasicStroke(1.0000005f, 0, 0, 4.0f, null, 0.0f));
		g.draw(shape32);
	}

	private void paintShapeNode_0_0_1_31(Graphics2D g) {
		GeneralPath shape33 = new GeneralPath();
		shape33.moveTo(12.520679, 16.5625);
		shape33.lineTo(31.16684, 16.5625);
		g.setStroke(new BasicStroke(1.0000002f, 0, 0, 4.0f, null, 0.0f));
		g.draw(shape33);
	}

	private void paintShapeNode_0_0_1_32(Graphics2D g) {
		GeneralPath shape34 = new GeneralPath();
		shape34.moveTo(6.375, 31.75);
		shape34.curveTo(5.1336346, 19.511961, 13.5625, 12.6875, 12.0, 3.0);
		shape34.lineTo(42.875, 3.0);
		shape34.lineTo(12.875, 3.625);
		shape34.curveTo(14.125, 13.1875, 6.6786165, 18.271446, 6.375, 31.75);
		shape34.closePath();
		g.setPaint(new Color(255, 255, 255, 255));
		g.fill(shape34);
	}

	private void paintCompositeGraphicsNode_0_0_1(Graphics2D g) {
		// _0_0_1_0
		AffineTransform trans_0_0_1_0 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_0(g);
		g.setTransform(trans_0_0_1_0);
		// _0_0_1_1
		g.setComposite(AlphaComposite.getInstance(3, 0.11363633f * origAlpha));
		AffineTransform trans_0_0_1_1 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_1(g);
		g.setTransform(trans_0_0_1_1);
		// _0_0_1_2
		AffineTransform trans_0_0_1_2 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_2(g);
		g.setTransform(trans_0_0_1_2);
		// _0_0_1_3
		AffineTransform trans_0_0_1_3 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_3(g);
		g.setTransform(trans_0_0_1_3);
		// _0_0_1_4
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
		AffineTransform trans_0_0_1_4 = g.getTransform();
		g.transform(new AffineTransform(0.02165151946246624f, 0.0f, 0.0f,
				0.019038410857319832f, 42.41537857055664f, 36.933719635009766f));
		paintCompositeGraphicsNode_0_0_1_4(g);
		g.setTransform(trans_0_0_1_4);
		// _0_0_1_5
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
		AffineTransform trans_0_0_1_5 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_5(g);
		g.setTransform(trans_0_0_1_5);
		// _0_0_1_6
		g.setComposite(AlphaComposite.getInstance(3, 0.11363633f * origAlpha));
		AffineTransform trans_0_0_1_6 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_6(g);
		g.setTransform(trans_0_0_1_6);
		// _0_0_1_7
		AffineTransform trans_0_0_1_7 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_7(g);
		g.setTransform(trans_0_0_1_7);
		// _0_0_1_8
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
		AffineTransform trans_0_0_1_8 = g.getTransform();
		g.transform(new AffineTransform(1.0344239473342896f, 0.0f,
				0.10452000051736832f, 1.0344239473342896f,
				-10.032480239868164f, 2.631913900375366f));
		paintCompositeGraphicsNode_0_0_1_8(g);
		g.setTransform(trans_0_0_1_8);
		// _0_0_1_9
		g.setComposite(AlphaComposite.getInstance(3, 0.11363633f * origAlpha));
		AffineTransform trans_0_0_1_9 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_9(g);
		g.setTransform(trans_0_0_1_9);
		// _0_0_1_10
		AffineTransform trans_0_0_1_10 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_10(g);
		g.setTransform(trans_0_0_1_10);
		// _0_0_1_11
		AffineTransform trans_0_0_1_11 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_11(g);
		g.setTransform(trans_0_0_1_11);
		// _0_0_1_12
		AffineTransform trans_0_0_1_12 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_12(g);
		g.setTransform(trans_0_0_1_12);
		// _0_0_1_13
		AffineTransform trans_0_0_1_13 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_13(g);
		g.setTransform(trans_0_0_1_13);
		// _0_0_1_14
		AffineTransform trans_0_0_1_14 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_14(g);
		g.setTransform(trans_0_0_1_14);
		// _0_0_1_15
		AffineTransform trans_0_0_1_15 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_15(g);
		g.setTransform(trans_0_0_1_15);
		// _0_0_1_16
		AffineTransform trans_0_0_1_16 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_16(g);
		g.setTransform(trans_0_0_1_16);
		// _0_0_1_17
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
		AffineTransform trans_0_0_1_17 = g.getTransform();
		g.transform(new AffineTransform(1.0344239473342896f, 0.0f,
				0.10452000051736832f, 1.0344239473342896f,
				-10.032480239868164f, 2.631913900375366f));
		paintCompositeGraphicsNode_0_0_1_17(g);
		g.setTransform(trans_0_0_1_17);
		// _0_0_1_18
		g.setComposite(AlphaComposite.getInstance(3, 0.11363633f * origAlpha));
		AffineTransform trans_0_0_1_18 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_18(g);
		g.setTransform(trans_0_0_1_18);
		// _0_0_1_19
		AffineTransform trans_0_0_1_19 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_19(g);
		g.setTransform(trans_0_0_1_19);
		// _0_0_1_20
		AffineTransform trans_0_0_1_20 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_20(g);
		g.setTransform(trans_0_0_1_20);
		// _0_0_1_21
		g.setComposite(AlphaComposite.getInstance(3, 0.39204544f * origAlpha));
		AffineTransform trans_0_0_1_21 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_21(g);
		g.setTransform(trans_0_0_1_21);
		// _0_0_1_22
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
		AffineTransform trans_0_0_1_22 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_22(g);
		g.setTransform(trans_0_0_1_22);
		// _0_0_1_23
		AffineTransform trans_0_0_1_23 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_23(g);
		g.setTransform(trans_0_0_1_23);
		// _0_0_1_24
		AffineTransform trans_0_0_1_24 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_24(g);
		g.setTransform(trans_0_0_1_24);
		// _0_0_1_25
		AffineTransform trans_0_0_1_25 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_25(g);
		g.setTransform(trans_0_0_1_25);
		// _0_0_1_26
		AffineTransform trans_0_0_1_26 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_26(g);
		g.setTransform(trans_0_0_1_26);
		// _0_0_1_27
		AffineTransform trans_0_0_1_27 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_27(g);
		g.setTransform(trans_0_0_1_27);
		// _0_0_1_28
		g.setComposite(AlphaComposite.getInstance(3, 0.52272725f * origAlpha));
		AffineTransform trans_0_0_1_28 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_28(g);
		g.setTransform(trans_0_0_1_28);
		// _0_0_1_29
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
		AffineTransform trans_0_0_1_29 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_29(g);
		g.setTransform(trans_0_0_1_29);
		// _0_0_1_30
		AffineTransform trans_0_0_1_30 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_30(g);
		g.setTransform(trans_0_0_1_30);
		// _0_0_1_31
		AffineTransform trans_0_0_1_31 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_31(g);
		g.setTransform(trans_0_0_1_31);
		// _0_0_1_32
		AffineTransform trans_0_0_1_32 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_1_32(g);
		g.setTransform(trans_0_0_1_32);
	}

	private void paintCanvasGraphicsNode_0_0(Graphics2D g) {
		// _0_0_0
		AffineTransform trans_0_0_0 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		g.setTransform(trans_0_0_0);
		// _0_0_1
		AffineTransform trans_0_0_1 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintCompositeGraphicsNode_0_0_1(g);
		g.setTransform(trans_0_0_1);
		// _0_0_2
		AffineTransform trans_0_0_2 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		g.setTransform(trans_0_0_2);
	}

	private void paintRootGraphicsNode_0(Graphics2D g) {
		// _0_0
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
		AffineTransform trans_0_0 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintCanvasGraphicsNode_0_0(g);
		g.setTransform(trans_0_0);
	}

	/**
	 * Returns the X of the bounding box of the original SVG image.
	 * 
	 * @return The X of the bounding box of the original SVG image.
	 */
	public int getOrigX() {
		return 2;
	}

	/**
	 * Returns the Y of the bounding box of the original SVG image.
	 * 
	 * @return The Y of the bounding box of the original SVG image.
	 */
	public int getOrigY() {
		return 2;
	}

	/**
	 * Returns the width of the bounding box of the original SVG image.
	 * 
	 * @return The width of the bounding box of the original SVG image.
	 */
	public int getOrigWidth() {
		return 47;
	}

	/**
	 * Returns the height of the bounding box of the original SVG image.
	 * 
	 * @return The height of the bounding box of the original SVG image.
	 */
	public int getOrigHeight() {
		return 42;
	}

	/**
	 * The current width of this resizable icon.
	 */
	int width;

	/**
	 * The current height of this resizable icon.
	 */
	int height;

	/**
	 * Creates a new transcoded SVG image.
	 */
	public DocumentOpen() {
		this.width = getOrigWidth();
		this.height = getOrigHeight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.Icon#getIconHeight()
	 */
	@Override
	public int getIconHeight() {
		return height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.Icon#getIconWidth()
	 */
	@Override
	public int getIconWidth() {
		return width;
	}

	/*
	 * Set the dimension of the icon.
	 */

	public void setDimension(Dimension newDimension) {
		this.width = newDimension.width;
		this.height = newDimension.height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.Icon#paintIcon(java.awt.Component, java.awt.Graphics,
	 * int, int)
	 */
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.translate(x, y);

		double coef1 = (double) this.width / (double) getOrigWidth();
		double coef2 = (double) this.height / (double) getOrigHeight();
		double coef = Math.min(coef1, coef2);
		g2d.scale(coef, coef);
		paint(g2d);
		g2d.dispose();
	}
}
