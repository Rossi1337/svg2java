package com.btr.svg2java.test.swing.util;

import java.awt.Color;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/*****************************************************************************
 * A paint for "transparent background". This will generate a checker board
 * background as seen in many painting programs.
 * 
 * @author Bernd Rosstauscher (svg2java(@)rosstauscher.de)
 ****************************************************************************/

public final class TransparentBackgroundPaint extends Color implements java.awt.Paint {

	private Color c1;
	private Color c2;
	private int tileSize;

	/*************************************************************************
	 * Constructor
	 * @param c1 checker board color 1
	 * @param c2 checker board color 2
	 * @param tileSize of the checker board.
	 * @throws IllegalArgumentException
	 ************************************************************************/
	
	public TransparentBackgroundPaint(Color c1, Color c2, int tileSize) {
		super(0, 0, 0, 0);
		this.tileSize = tileSize; 
		this.c1 = c1;
		this.c2 = c2;
	}

	/*************************************************************************
	 * createContext
	 * @see java.awt.Paint#createContext(java.awt.image.ColorModel, java.awt.Rectangle, java.awt.geom.Rectangle2D, java.awt.geom.AffineTransform, java.awt.RenderingHints)
	 ************************************************************************/
	@Override
	public synchronized PaintContext createContext(ColorModel cm, Rectangle deviceBounds,
			Rectangle2D userBounds, AffineTransform xform, RenderingHints hints) {
		return new CheckerPaintContext();
	}

	/*************************************************************************
	 * getTransparency
	 * @see java.awt.Transparency#getTransparency()
	 ************************************************************************/
	@Override
	public int getTransparency() {
		return Transparency.TRANSLUCENT;
	}

	/*****************************************************************************
	 * Paint context is always in RGB
	 * @author  Bernd Rosstauscher 
	 *         (svg2java(@)rosstauscher.de)
	 ****************************************************************************/
	
	private final class CheckerPaintContext implements java.awt.PaintContext {

		private static final int COMPONENTS_PER_PIXEL = 4; // RGBA

		/*************************************************************************
		 * Constructor
		 ************************************************************************/
		
		public CheckerPaintContext() {
			super();
		}

		/*************************************************************************
		 * dispose
		 * @see java.awt.PaintContext#dispose()
		 ************************************************************************/
		@Override
		public void dispose() {
			// Not used
		}

		/*************************************************************************
		 * getColorModel
		 * @see java.awt.PaintContext#getColorModel()
		 ************************************************************************/
		@Override
		public java.awt.image.ColorModel getColorModel() {
			return java.awt.image.ColorModel.getRGBdefault();
		}

		/*************************************************************************
		 * getRaster
		 * @see java.awt.PaintContext#getRaster(int, int, int, int)
		 ************************************************************************/
		@Override
		public java.awt.image.Raster getRaster(int x, int y, int width, int height) {
			WritableRaster raster = getColorModel().createCompatibleWritableRaster(width, height);

			int[] data = new int[(width * height * COMPONENTS_PER_PIXEL)];

			for (int posY = 0; posY < height; posY++) {
				for (int posX = 0; posX < width; posX++) {
					
					int rowX = (x+posX) / tileSize;
					int rowY = (y+posY) / tileSize;
					Color c = (rowX+rowY) % 2 == 0? c1 : c2;
					int dataIndex = (posY*width+posX)*COMPONENTS_PER_PIXEL;
					data[dataIndex+0] = c.getRed(); 
					data[dataIndex+1] = c.getGreen(); 
					data[dataIndex+2] = c.getBlue(); 
					data[dataIndex+3] = c.getAlpha(); 
				}
			}

			raster.setPixels(0, 0, width, height, data);
			return raster;
		}
	}
}