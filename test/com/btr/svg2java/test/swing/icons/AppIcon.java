package com.btr.svg2java.test.swing.icons;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.Icon; 

/**
 * This class has been automatically generated using svg2java
 * 
 */
public class AppIcon implements Icon {
	
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
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        origAlpha = 1.0f;
        Composite origComposite = g.getComposite();
        if (origComposite instanceof AlphaComposite) {
            AlphaComposite origAlphaComposite = 
                (AlphaComposite)origComposite;
            if (origAlphaComposite.getRule() == AlphaComposite.SRC_OVER) {
                origAlpha = origAlphaComposite.getAlpha();
            }
        }
        
		// _0
		AffineTransform trans_0 = g.getTransform();
		paintRootGraphicsNode_0(g);
		g.setTransform(trans_0);

	}

	private void paintShapeNode_0_0_0_0(Graphics2D g) {
		Rectangle2D.Double shape0 = new Rectangle2D.Double(0.0, 6.512463442049921E-4, 63.999813079833984, 63.99934768676758);
		g.setPaint(new Color(255, 153, 0, 255));
		g.fill(shape0);
	}

	private void paintShapeNode_0_0_0_1(Graphics2D g) {
		Rectangle2D.Double shape1 = new Rectangle2D.Double(31.998966217041016, 32.000816345214844, 32.001033782958984, 31.901582717895508);
		g.setPaint(new Color(222, 133, 0, 255));
		g.fill(shape1);
	}

	private void paintShapeNode_0_0_0_2(Graphics2D g) {
		Rectangle2D.Double shape2 = new Rectangle2D.Double(4.721748409792781E-4, 1.895750465337187E-4, 32.001033782958984, 32.00080108642578);
		g.setPaint(new Color(255, 177, 59, 255));
		g.fill(shape2);
	}

	private void paintShapeNode_0_0_0_3_0(Graphics2D g) {
		Ellipse2D.Double shape3 = new Ellipse2D.Double(315.693603515625, 224.0355987548828, 50.126800537109375, 50.126800537109375);
		g.setPaint(new Color(255, 153, 0, 255));
		g.fill(shape3);
	}

	private void paintShapeNode_0_0_0_3_1(Graphics2D g) {
		Ellipse2D.Double shape4 = new Ellipse2D.Double(-1658.510009765625, -1658.510009765625, 3317.02001953125, 3317.02001953125);
		g.fill(shape4);
	}

	private void paintShapeNode_0_0_0_3_2(Graphics2D g) {
		Ellipse2D.Double shape5 = new Ellipse2D.Double(-1658.510009765625, -1658.510009765625, 3317.02001953125, 3317.02001953125);
		g.fill(shape5);
	}

	private void paintShapeNode_0_0_0_3_3(Graphics2D g) {
		Ellipse2D.Double shape6 = new Ellipse2D.Double(-1658.510009765625, -1658.510009765625, 3317.02001953125, 3317.02001953125);
		g.fill(shape6);
	}

	private void paintShapeNode_0_0_0_3_4(Graphics2D g) {
		Ellipse2D.Double shape7 = new Ellipse2D.Double(401.78558349609375, 224.0355987548828, 50.126800537109375, 50.126800537109375);
		g.fill(shape7);
	}

	private void paintShapeNode_0_0_0_3_5(Graphics2D g) {
		Ellipse2D.Double shape8 = new Ellipse2D.Double(-1658.510009765625, -1658.510009765625, 3317.02001953125, 3317.02001953125);
		g.fill(shape8);
	}

	private void paintShapeNode_0_0_0_3_6(Graphics2D g) {
		Ellipse2D.Double shape9 = new Ellipse2D.Double(-1658.510009765625, -1658.510009765625, 3317.02001953125, 3317.02001953125);
		g.fill(shape9);
	}

	private void paintShapeNode_0_0_0_3_7(Graphics2D g) {
		Ellipse2D.Double shape10 = new Ellipse2D.Double(-1658.510009765625, -1658.510009765625, 3317.02001953125, 3317.02001953125);
		g.fill(shape10);
	}

	private void paintCompositeGraphicsNode_0_0_0_3(Graphics2D g) {
		// _0_0_0_3_0
		AffineTransform trans_0_0_0_3_0 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_3_0(g);
		g.setTransform(trans_0_0_0_3_0);
		// _0_0_0_3_1
		AffineTransform trans_0_0_0_3_1 = g.getTransform();
		g.transform(new AffineTransform(0.010685799643397331f, 0.010685799643397331f, -0.010685799643397331f, 0.010685799643397331f, 353.364990234375f, 218.66099548339844f));
		paintShapeNode_0_0_0_3_1(g);
		g.setTransform(trans_0_0_0_3_1);
		// _0_0_0_3_2
		AffineTransform trans_0_0_0_3_2 = g.getTransform();
		g.transform(new AffineTransform(0.0f, 0.015111999586224556f, -0.015111999586224556f, 0.0f, 383.8030090332031f, 206.05299377441406f));
		paintShapeNode_0_0_0_3_2(g);
		g.setTransform(trans_0_0_0_3_2);
		// _0_0_0_3_3
		AffineTransform trans_0_0_0_3_3 = g.getTransform();
		g.transform(new AffineTransform(-0.010685799643397331f, 0.010685799643397331f, -0.010685799643397331f, -0.010685799643397331f, 414.2409973144531f, 218.66099548339844f));
		paintShapeNode_0_0_0_3_3(g);
		g.setTransform(trans_0_0_0_3_3);
		// _0_0_0_3_4
		AffineTransform trans_0_0_0_3_4 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_3_4(g);
		g.setTransform(trans_0_0_0_3_4);
		// _0_0_0_3_5
		AffineTransform trans_0_0_0_3_5 = g.getTransform();
		g.transform(new AffineTransform(-0.010685799643397331f, -0.010685799643397331f, 0.010685799643397331f, -0.010685799643397331f, 414.2409973144531f, 279.5379943847656f));
		paintShapeNode_0_0_0_3_5(g);
		g.setTransform(trans_0_0_0_3_5);
		// _0_0_0_3_6
		AffineTransform trans_0_0_0_3_6 = g.getTransform();
		g.transform(new AffineTransform(0.0f, -0.015111999586224556f, 0.015111999586224556f, 0.0f, 383.8030090332031f, 292.14599609375f));
		paintShapeNode_0_0_0_3_6(g);
		g.setTransform(trans_0_0_0_3_6);
		// _0_0_0_3_7
		AffineTransform trans_0_0_0_3_7 = g.getTransform();
		g.transform(new AffineTransform(0.010685799643397331f, -0.010685799643397331f, 0.010685799643397331f, 0.010685799643397331f, 353.364990234375f, 279.5379943847656f));
		paintShapeNode_0_0_0_3_7(g);
		g.setTransform(trans_0_0_0_3_7);
	}

	private void paintShapeNode_0_0_0_4(Graphics2D g) {
		GeneralPath shape11 = new GeneralPath();
		shape11.moveTo(40.413532, 11.756659);
		shape11.curveTo(40.413532, 7.1385403, 36.66704, 3.3920746, 32.04889, 3.3920746);
		shape11.curveTo(27.430737, 3.3920746, 23.684244, 7.1385336, 23.684244, 11.7566595);
		shape11.curveTo(20.417286, 8.492091, 15.121161, 8.492091, 11.854224, 11.7566595);
		shape11.curveTo(8.589632, 15.023595, 8.589632, 20.319681, 11.854224, 23.58659);
		shape11.curveTo(7.2360787, 23.58659, 3.4895792, 27.333055, 3.4895792, 31.951174);
		shape11.curveTo(3.4895792, 36.569286, 7.2360725, 40.315758, 11.854224, 40.315758);
		shape11.curveTo(8.589632, 43.58269, 8.589632, 48.878777, 11.854224, 52.145695);
		shape11.curveTo(15.121182, 55.410263, 20.417307, 55.410263, 23.684244, 52.145695);
		shape11.curveTo(23.684244, 56.763805, 27.43073, 60.510277, 32.04889, 60.510277);
		shape11.curveTo(36.667034, 60.510277, 40.413532, 56.763813, 40.413532, 52.14569);
		shape11.curveTo(43.680492, 55.41026, 48.976616, 55.41026, 52.243546, 52.14569);
		shape11.curveTo(55.508137, 48.878757, 55.508137, 43.582672, 52.243546, 40.315754);
		shape11.curveTo(56.86169, 40.315754, 60.605797, 36.569298, 60.605797, 31.95117);
		shape11.curveTo(60.605797, 27.33306, 56.861675, 23.586586, 52.243546, 23.586586);
		shape11.curveTo(55.508137, 20.31965, 55.508137, 15.023564, 52.243546, 11.756657);
		shape11.curveTo(48.976585, 8.492088, 43.68046, 8.492088, 40.413532, 11.756657);
		shape11.closePath();
		g.setPaint(new Color(0, 0, 0, 255));
		g.fill(shape11);
	}

	private void paintShapeNode_0_0_0_5(Graphics2D g) {
		GeneralPath shape12 = new GeneralPath();
		shape12.moveTo(35.24949, 14.959766);
		shape12.lineTo(35.24949, 24.222004);
		shape12.lineTo(41.799915, 17.671625);
		shape12.curveTo(41.799915, 16.514141, 42.24165, 15.354296, 43.125122, 14.470837);
		shape12.curveTo(44.89443, 12.701543, 47.762173, 12.701543, 49.52909, 14.470837);
		shape12.curveTo(51.298397, 16.23777, 51.298397, 19.105488, 49.52909, 20.87476);
		shape12.curveTo(48.645622, 21.75822, 47.485764, 22.199957, 46.328278, 22.199957);
		shape12.lineTo(39.77785, 28.750338);
		shape12.lineTo(49.040154, 28.750338);
		shape12.curveTo(49.859848, 27.93065, 50.99135, 27.422781, 52.243332, 27.422781);
		shape12.curveTo(54.742565, 27.422781, 56.769352, 29.451912, 56.769352, 31.951126);
		shape12.curveTo(56.769352, 34.45034, 54.742565, 36.47948, 52.243332, 36.47948);
		shape12.curveTo(50.99135, 36.47948, 49.859848, 35.971603, 49.040154, 35.151917);
		shape12.lineTo(39.777847, 35.151917);
		shape12.lineTo(46.328274, 41.702297);
		shape12.curveTo(47.485767, 41.702297, 48.64562, 42.144035, 49.529087, 43.0275);
		shape12.curveTo(51.298393, 44.796795, 51.298393, 47.664516, 49.529087, 49.431423);
		shape12.curveTo(47.762146, 51.200718, 44.894405, 51.200718, 43.12512, 49.431423);
		shape12.curveTo(42.24165, 48.547955, 41.79991, 47.38811, 41.79991, 46.230633);
		shape12.lineTo(35.249485, 39.680252);
		shape12.lineTo(35.249485, 48.942482);
		shape12.curveTo(36.06918, 49.76217, 36.577057, 50.893665, 36.577057, 52.145638);
		shape12.curveTo(36.577057, 54.64485, 34.5479, 56.671623, 32.04867, 56.671623);
		shape12.curveTo(29.549438, 56.671623, 27.520292, 54.64485, 27.520292, 52.145638);
		shape12.curveTo(27.520292, 50.89367, 28.028166, 49.76217, 28.847858, 48.942482);
		shape12.lineTo(28.847858, 39.680252);
		shape12.lineTo(22.29743, 46.230633);
		shape12.curveTo(22.29743, 47.38811, 21.855698, 48.547955, 20.972223, 49.431423);
		shape12.curveTo(19.202917, 51.200718, 16.335175, 51.200718, 14.568255, 49.431423);
		shape12.curveTo(12.798948, 47.66449, 12.798948, 44.796776, 14.568255, 43.0275);
		shape12.curveTo(15.451721, 42.14403, 16.61158, 41.702297, 17.769066, 41.702297);
		shape12.lineTo(24.319494, 35.151917);
		shape12.lineTo(15.057189, 35.151917);
		shape12.curveTo(14.237496, 35.971603, 13.105996, 36.47948, 11.854012, 36.47948);
		shape12.curveTo(9.35478, 36.47948, 7.327993, 34.45034, 7.327993, 31.951128);
		shape12.curveTo(7.327993, 29.451916, 9.35478, 27.422783, 11.854012, 27.422783);
		shape12.curveTo(13.105996, 27.422783, 14.237496, 27.930653, 15.05719, 28.75034);
		shape12.lineTo(24.319496, 28.75034);
		shape12.lineTo(17.769068, 22.199959);
		shape12.curveTo(16.611576, 22.199959, 15.451723, 21.758228, 14.568256, 20.874762);
		shape12.curveTo(12.798949, 19.105469, 12.798949, 16.237745, 14.568256, 14.4708395);
		shape12.curveTo(16.335196, 12.701546, 19.20294, 12.701546, 20.972225, 14.4708395);
		shape12.curveTo(21.855692, 15.3543, 22.297432, 16.514149, 22.297432, 17.671629);
		shape12.lineTo(28.84786, 24.222008);
		shape12.lineTo(28.84786, 14.959771);
		shape12.curveTo(28.028166, 14.140084, 27.520294, 13.008593, 27.520294, 11.756624);
		shape12.curveTo(27.520294, 9.257402, 29.54944, 7.23063, 32.04867, 7.23063);
		shape12.curveTo(34.5479, 7.23063, 36.577057, 9.257402, 36.577057, 11.756624);
		shape12.curveTo(36.577057, 13.008593, 36.069176, 14.140084, 35.249485, 14.959771);
		shape12.closePath();
		g.setPaint(new Color(255, 255, 255, 255));
		g.fill(shape12);
	}

	private void paintShapeNode_0_0_0_6(Graphics2D g) {
		Rectangle2D.Double shape13 = new Rectangle2D.Double(3.4916346073150635, 31.99987030029297, 57.11129379272461, 28.508249282836914);
		g.setPaint(new Color(0, 0, 0, 255));
		g.fill(shape13);
	}

	private void paintShapeNode_0_0_0_7(Graphics2D g) {
		Rectangle2D.Double shape14 = new Rectangle2D.Double(3.4916346073150635, 47.99759292602539, 57.11129379272461, 12.51075553894043);
		g.setPaint(new Color(63, 63, 63, 255));
		g.fill(shape14);
	}

	private void paintShapeNode_0_0_0_8(Graphics2D g) {
		GeneralPath shape15 = new GeneralPath();
		shape15.moveTo(9.477582, 34.644264);
		shape15.lineTo(55.082695, 34.644264);
		shape15.curveTo(56.95121, 34.644264, 58.477207, 36.92005, 58.477207, 39.706642);
		shape15.lineTo(58.477207, 56.782074);
		shape15.curveTo(40.852654, 53.53046, 23.379293, 49.03524, 6.0828896, 49.45096);
		shape15.lineTo(6.0828896, 39.706646);
		shape15.curveTo(6.0828896, 36.92005, 7.6088877, 34.644264, 9.477403, 34.644264);
		shape15.lineTo(9.477581, 34.644264);
		shape15.closePath();
		g.fill(shape15);
	}

	private void paintShapeNode_0_0_0_9_0_0(Graphics2D g) {
		GeneralPath shape16 = new GeneralPath();
		shape16.moveTo(458.259, 307.339);
		shape16.curveTo(468.798, 300.92398, 476.217, 289.98898, 475.618, 280.24298);
		shape16.lineTo(531.94104, 216.71198);
		shape16.curveTo(540.91205, 217.49597, 549.67804, 213.97197, 553.95306, 214.19098);
		shape16.curveTo(562.53503, 214.94897, 561.97205, 212.80898, 571.4961, 215.54898);
		shape16.curveTo(581.0491, 218.46498, 579.5061, 220.04298, 584.7581, 220.79999);
		shape16.curveTo(590.2221, 221.58398, 598.2141, 217.874, 599.37714, 214.385);
		shape16.curveTo(602.4871, 204.82399, 600.9451, 207.93599, 598.02716, 202.692);
		shape16.curveTo(605.4122, 202.692, 612.63715, 195.079, 611.2632, 187.095);
		shape16.curveTo(609.71216, 178.892, 606.7872, 175.209, 599.7542, 171.693);
		shape16.curveTo(602.89124, 169.146, 610.28424, 161.163, 603.8692, 151.82199);
		shape16.curveTo(596.2652, 141.10799, 590.4142, 148.11299, 579.5052, 145.19598);
		shape16.curveTo(568.19116, 142.27098, 568.19116, 145.19598, 565.45917, 136.01398);
		shape16.curveTo(562.9382, 126.86798, 570.51715, 130.38298, 567.9972, 119.06998);
		shape16.curveTo(561.3722, 89.45398, 536.8222, 94.89898, 529.80817, 100.55698);
		shape16.curveTo(526.8832, 103.101974, 528.2402, 106.39898, 530.1872, 109.516975);
		shape16.curveTo(533.7032, 115.95798, 537.9862, 139.91698, 536.8232, 150.44698);
		shape16.curveTo(535.4392, 162.73097, 531.35016, 160.59798, 525.69415, 173.45398);
		shape16.curveTo(520.0632, 186.49498, 522.5831, 199.76498, 521.79016, 208.12698);
		shape16.lineTo(475.01917, 268.95297);
		shape16.curveTo(473.07217, 259.99298, 457.69617, 259.58698, 444.61917, 259.39297);
		shape16.lineTo(458.2592, 307.339);
		g.setPaint(new Color(0, 0, 0, 255));
		g.fill(shape16);
	}

	private void paintShapeNode_0_0_0_9_0_1(Graphics2D g) {
		GeneralPath shape17 = new GeneralPath();
		shape17.moveTo(182.483, 233.868);
		shape17.curveTo(162.613, 230.758, 155.604, 235.41899, 153.84601, 241.86);
		shape17.curveTo(140.77, 240.098, 99.46101, 252.769, 92.046005, 258.426);
		shape17.curveTo(84.657005, 264.082, 81.54301, 288.44598, 80.353004, 294.862);
		shape17.curveTo(79.189, 301.303, 77.428, 290.579, 70.41801, 297.593);
		shape17.curveTo(63.598007, 304.607, 71.98601, 303.849, 67.68201, 307.524);
		shape17.curveTo(63.598007, 311.233, 64.57101, 304.819, 57.372005, 310.44998);
		shape17.curveTo(49.952003, 315.922, 54.446007, 314.75897, 48.411003, 321.17297);
		shape17.curveTo(42.159004, 327.8, 10.786003, 313.77997, 5.534004, 314.75897);
		shape17.curveTo(0.445004, 315.54196, -3.6439962, 332.11, 5.128004, 335.41296);
		shape17.curveTo(14.085004, 338.70895, 35.744003, 343.01697, 43.132004, 344.36496);
		shape17.curveTo(37.096004, 349.64294, 17.226004, 356.25296, 13.112003, 361.33698);
		shape17.curveTo(8.834003, 366.589, 4.7490034, 374.201, 7.075003, 376.93396);
		shape17.curveTo(9.212004, 379.85895, 18.389004, 382.18497, 30.082003, 382.18497);
		shape17.curveTo(41.779003, 382.18497, 49.952003, 363.85797, 58.345, 368.16595);
		shape17.curveTo(66.707, 372.24594, 60.292, 399.72797, 60.103, 405.76495);
		shape17.curveTo(60.103, 411.80096, 58.724, 417.64294, 68.47, 417.64294);
		shape17.curveTo(78.4, 417.64294, 78.022, 415.91595, 82.3, 404.97995);
		shape17.curveTo(86.415, 394.07196, 80.758, 370.87195, 89.341, 364.64194);
		shape17.curveTo(97.703, 358.60495, 95.37701, 357.23093, 102.981, 360.55194);
		shape17.curveTo(110.374, 363.67194, 103.955, 366.78195, 112.917, 371.27695);
		shape17.curveTo(136.113, 382.96893, 145.264, 364.64194, 145.643, 348.26895);
		shape17.curveTo(145.643, 340.27795, 138.254, 339.68695, 135.32901, 339.68695);
		shape17.curveTo(132.598, 339.68695, 130.26701, 343.98795, 125.58301, 343.98795);
		shape17.curveTo(120.71501, 343.98795, 116.031006, 340.27795, 109.017006, 333.65094);
		shape17.curveTo(102.19701, 327.01593, 98.677, 324.68994, 98.677, 321.17294);
		shape17.curveTo(98.677, 317.86896, 100.629005, 322.14294, 101.034004, 314.15994);
		shape17.curveTo(101.412, 306.17593, 95.37701, 308.12296, 95.94501, 299.54193);
		shape17.curveTo(96.351006, 290.95892, 103.739006, 268.35593, 107.07001, 262.13693);
		shape17.curveTo(113.701004, 258.80493, 145.85901, 246.53894, 153.65701, 246.91794);
		shape17.curveTo(155.60402, 251.79094, 156.95702, 255.50095, 172.55002, 264.08295);
		shape17.lineTo(182.48302, 233.86795);
		g.fill(shape17);
	}

	private void paintCompositeGraphicsNode_0_0_0_9_0(Graphics2D g) {
		// _0_0_0_9_0_0
		AffineTransform trans_0_0_0_9_0_0 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_9_0_0(g);
		g.setTransform(trans_0_0_0_9_0_0);
		// _0_0_0_9_0_1
		AffineTransform trans_0_0_0_9_0_1 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_9_0_1(g);
		g.setTransform(trans_0_0_0_9_0_1);
	}

	private void paintShapeNode_0_0_0_9_1(Graphics2D g) {
		GeneralPath shape18 = new GeneralPath();
		shape18.moveTo(339.24396, 236.76106);
		shape18.curveTo(305.32297, 268.91406, 253.29596, 336.56107, 233.21497, 374.75906);
		shape18.curveTo(212.96497, 412.94806, 183.51196, 477.26407, 171.43997, 520.9341);
		shape18.curveTo(159.55898, 564.78107, 143.56097, 623.44006, 142.58698, 651.51306);
		shape18.curveTo(141.60898, 679.58704, 142.58698, 699.64307, 162.66898, 698.66406);
		shape18.curveTo(182.94498, 697.6941, 178.26198, 687.57007, 226.38998, 663.99005);
		shape18.curveTo(274.55, 640.19806, 300.451, 633.56305, 352.88498, 645.8561);
		shape18.curveTo(405.322, 658.1391, 418.17798, 704.1351, 437.85397, 702.1891);
		shape18.curveTo(457.55698, 700.2321, 470.42197, 661.6371, 470.22897, 594.20306);
		shape18.curveTo(470.22897, 526.77606, 421.09497, 419.76807, 409.81598, 391.51007);
		shape18.curveTo(398.68597, 363.44608, 359.326, 274.95108, 339.244, 236.76108);
		g.setPaint(new Color(255, 255, 255, 255));
		g.fill(shape18);
	}

	private void paintShapeNode_0_0_0_9_2(Graphics2D g) {
		GeneralPath shape19 = new GeneralPath();
		shape19.moveTo(339.24396, 236.76106);
		shape19.curveTo(305.32297, 268.91406, 253.29596, 336.56107, 233.21497, 374.75906);
		shape19.curveTo(212.96497, 412.94806, 183.51196, 477.26407, 171.43997, 520.9341);
		shape19.curveTo(159.55898, 564.78107, 143.56097, 623.44006, 142.58698, 651.51306);
		shape19.curveTo(141.60898, 679.58704, 142.58698, 699.64307, 162.66898, 698.66406);
		shape19.curveTo(182.94498, 697.6941, 178.26198, 687.57007, 226.38998, 663.99005);
		shape19.curveTo(274.55, 640.19806, 300.451, 633.56305, 352.88498, 645.8561);
		shape19.curveTo(405.322, 658.1391, 418.17798, 704.1351, 437.85397, 702.1891);
		shape19.curveTo(457.55698, 700.2321, 470.42197, 661.6371, 470.22897, 594.20306);
		shape19.curveTo(470.22897, 526.77606, 421.09497, 419.76807, 409.81598, 391.51007);
		shape19.curveTo(398.68597, 363.44608, 359.326, 274.95108, 339.244, 236.76108);
		shape19.closePath();
		g.setPaint(new Color(0, 0, 0, 255));
		g.setStroke(new BasicStroke(1.239f,0,0,2.613f,null,0.0f));
		g.draw(shape19);
	}

	private void paintShapeNode_0_0_0_9_3(Graphics2D g) {
		GeneralPath shape20 = new GeneralPath();
		shape20.moveTo(339.24396, 241.06207);
		shape20.curveTo(271.81296, 296.79507, 209.63297, 404.96606, 180.59096, 490.5351);
		shape20.curveTo(237.70796, 456.80508, 406.48495, 468.11908, 453.45093, 498.5181);
		shape20.curveTo(444.48892, 459.1301, 364.38293, 276.7141, 339.24393, 241.0621);
		g.fill(shape20);
	}

	private void paintShapeNode_0_0_0_9_4_0(Graphics2D g) {
		GeneralPath shape21 = new GeneralPath();
		shape21.moveTo(306.841, 165.059);
		shape21.curveTo(258.492, 164.09001, 226.14401, 196.43701, 226.334, 239.314);
		shape21.curveTo(226.334, 282.192, 261.039, 306.767, 302.348, 304.608);
		shape21.curveTo(343.682, 302.466, 388.508, 275.371, 386.34, 234.847);
		shape21.curveTo(384.013, 194.295, 355.164, 166.037, 306.841, 165.05899);
		g.setPaint(new Color(169, 5, 51, 255));
		g.fill(shape21);
	}

	private void paintShapeNode_0_0_0_9_4_1(Graphics2D g) {
		GeneralPath shape22 = new GeneralPath();
		shape22.moveTo(274.855, 206.896);
		shape22.lineTo(297.545, 206.896);
		shape22.curveTo(297.545, 194.356, 287.384, 184.18799, 274.855, 184.18799);
		shape22.curveTo(262.33002, 184.18799, 252.16202, 194.35599, 252.16202, 206.896);
		shape22.curveTo(252.16202, 219.41699, 262.33002, 229.58499, 274.855, 229.58499);
		shape22.curveTo(287.385, 229.58499, 297.545, 219.41699, 297.545, 206.896);
		g.setPaint(new Color(255, 255, 255, 255));
		g.fill(shape22);
	}

	private void paintCompositeGraphicsNode_0_0_0_9_4(Graphics2D g) {
		// _0_0_0_9_4_0
		AffineTransform trans_0_0_0_9_4_0 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_9_4_0(g);
		g.setTransform(trans_0_0_0_9_4_0);
		// _0_0_0_9_4_1
		AffineTransform trans_0_0_0_9_4_1 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_9_4_1(g);
		g.setTransform(trans_0_0_0_9_4_1);
	}

	private void paintShapeNode_0_0_0_9_5(Graphics2D g) {
		GeneralPath shape23 = new GeneralPath();
		shape23.moveTo(312.55295, 392.67407);
		shape23.curveTo(264.20395, 391.70508, 231.85596, 424.05206, 232.04594, 466.92908);
		shape23.curveTo(232.04594, 509.80707, 266.75095, 534.3821, 308.05994, 532.2231);
		shape23.curveTo(349.39392, 530.08105, 394.21994, 502.98608, 392.05194, 462.4621);
		shape23.curveTo(389.72495, 421.9101, 360.87595, 393.6521, 312.55295, 392.6741);
		shape23.closePath();
		g.setPaint(new Color(0, 0, 0, 255));
		g.setStroke(new BasicStroke(1.745f,0,1,2.613f,null,0.0f));
		g.draw(shape23);
	}

	private void paintShapeNode_0_0_0_9_6(Graphics2D g) {
		GeneralPath shape24 = new GeneralPath();
		shape24.moveTo(600.41, 375.32306);
		shape24.curveTo(590.276, 376.10706, 559.66595, 376.70605, 567.46295, 395.03506);
		g.setPaint(new Color(128, 130, 133, 255));
		g.setStroke(new BasicStroke(0.498f,1,1,2.613f,null,0.0f));
		g.draw(shape24);
	}

	private void paintShapeNode_0_0_0_9_7(Graphics2D g) {
		GeneralPath shape25 = new GeneralPath();
		shape25.moveTo(603.92596, 399.30908);
		shape25.curveTo(572.33496, 396.7621, 558.907, 405.1601, 569.81696, 418.2021);
		g.setStroke(new BasicStroke(0.389f,1,1,2.613f,null,0.0f));
		g.draw(shape25);
	}

	private void paintShapeNode_0_0_0_9_8(Graphics2D g) {
		GeneralPath shape26 = new GeneralPath();
		shape26.moveTo(601.785, 429.51508);
		shape26.curveTo(591.06995, 424.8361, 562.397, 418.01608, 565.923, 437.31308);
		g.setStroke(new BasicStroke(0.444f,1,1,2.613f,null,0.0f));
		g.draw(shape26);
	}

	private void paintCompositeGraphicsNode_0_0_0_9(Graphics2D g) {
		// _0_0_0_9_0
		AffineTransform trans_0_0_0_9_0 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 5.7119526863098145f, 227.6150665283203f));
		paintCompositeGraphicsNode_0_0_0_9_0(g);
		g.setTransform(trans_0_0_0_9_0);
		// _0_0_0_9_1
		AffineTransform trans_0_0_0_9_1 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_9_1(g);
		g.setTransform(trans_0_0_0_9_1);
		// _0_0_0_9_2
		AffineTransform trans_0_0_0_9_2 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_9_2(g);
		g.setTransform(trans_0_0_0_9_2);
		// _0_0_0_9_3
		AffineTransform trans_0_0_0_9_3 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_9_3(g);
		g.setTransform(trans_0_0_0_9_3);
		// _0_0_0_9_4
		AffineTransform trans_0_0_0_9_4 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 5.7119526863098145f, 227.6150665283203f));
		paintCompositeGraphicsNode_0_0_0_9_4(g);
		g.setTransform(trans_0_0_0_9_4);
		// _0_0_0_9_5
		AffineTransform trans_0_0_0_9_5 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_9_5(g);
		g.setTransform(trans_0_0_0_9_5);
		// _0_0_0_9_6
		AffineTransform trans_0_0_0_9_6 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_9_6(g);
		g.setTransform(trans_0_0_0_9_6);
		// _0_0_0_9_7
		AffineTransform trans_0_0_0_9_7 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_9_7(g);
		g.setTransform(trans_0_0_0_9_7);
		// _0_0_0_9_8
		AffineTransform trans_0_0_0_9_8 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_9_8(g);
		g.setTransform(trans_0_0_0_9_8);
	}

	private void paintCompositeGraphicsNode_0_0_0(Graphics2D g) {
		// _0_0_0_0
		AffineTransform trans_0_0_0_0 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_0(g);
		g.setTransform(trans_0_0_0_0);
		// _0_0_0_1
		AffineTransform trans_0_0_0_1 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_1(g);
		g.setTransform(trans_0_0_0_1);
		// _0_0_0_2
		AffineTransform trans_0_0_0_2 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_2(g);
		g.setTransform(trans_0_0_0_2);
		// _0_0_0_3
		AffineTransform trans_0_0_0_3 = g.getTransform();
		g.transform(new AffineTransform(0.4691174328327179f, 0.0f, 0.0f, 0.4691140651702881f, -147.99998474121094f, -84.9047622680664f));
		paintCompositeGraphicsNode_0_0_0_3(g);
		g.setTransform(trans_0_0_0_3);
		// _0_0_0_4
		AffineTransform trans_0_0_0_4 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_4(g);
		g.setTransform(trans_0_0_0_4);
		// _0_0_0_5
		AffineTransform trans_0_0_0_5 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_5(g);
		g.setTransform(trans_0_0_0_5);
		// _0_0_0_6
		AffineTransform trans_0_0_0_6 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_6(g);
		g.setTransform(trans_0_0_0_6);
		// _0_0_0_7
		AffineTransform trans_0_0_0_7 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_7(g);
		g.setTransform(trans_0_0_0_7);
		// _0_0_0_8
		AffineTransform trans_0_0_0_8 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintShapeNode_0_0_0_8(g);
		g.setTransform(trans_0_0_0_8);
		// _0_0_0_9
		AffineTransform trans_0_0_0_9 = g.getTransform();
		g.transform(new AffineTransform(0.07447020709514618f, 0.0f, 0.0f, 0.0744696781039238f, 7.571465015411377f, 6.798445701599121f));
		paintCompositeGraphicsNode_0_0_0_9(g);
		g.setTransform(trans_0_0_0_9);
	}

	private void paintCanvasGraphicsNode_0_0(Graphics2D g) {
		// _0_0_0
		AffineTransform trans_0_0_0 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		paintCompositeGraphicsNode_0_0_0(g);
		g.setTransform(trans_0_0_0);
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
     * @return The X of the bounding box of the original SVG image.
     */
    public int getOrigX() {
        return 0;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * @return The Y of the bounding box of the original SVG image.
     */
    public int getOrigY() {
        return 1;
    }

    /**
     * Returns the width of the bounding box of the original SVG image.
     * @return The width of the bounding box of the original SVG image.
     */
    public int getOrigWidth() {
        return 64;
    }

    /**
     * Returns the height of the bounding box of the original SVG image.
     * @return The height of the bounding box of the original SVG image.
     */
    public int getOrigHeight() {
        return 64;
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
	public AppIcon() {
        this.width = getOrigWidth();
        this.height = getOrigHeight();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.Icon#getIconHeight()
	 */
    @Override
	public int getIconHeight() {
		return height;
	}

	/*
	 * (non-Javadoc)
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
	 * @see javax.swing.Icon#paintIcon(java.awt.Component, java.awt.Graphics, int, int)
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

