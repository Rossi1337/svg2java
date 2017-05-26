package com.btr.svg2java.test.swing.util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/*****************************************************************************
 * File filter for svg files.
 *
 * @author  Bernd Rosstauscher 
 *         (svg2java(@)rosstauscher.de)
 ****************************************************************************/

public final class SvgFileFilter extends FileFilter implements java.io.FileFilter {

	public static final String SVGZ_FILE_EXTENSION = ".svgz";
	public static final String SVG_FILE_EXTENSION = ".svg";

	/*************************************************************************
	 * Constructor
	 * @param testViewer
	 ************************************************************************/
	
	public SvgFileFilter() {
		super();
	}

	/*************************************************************************
	 * getDescription
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 ************************************************************************/
	
	@Override
	public String getDescription() {
		return "SVG Files";
	}

	/*************************************************************************
	 * accept
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 ************************************************************************/
	
	@Override
	public boolean accept(File f) {
		String fileName = f.getName().toLowerCase();
		return f.isDirectory() || (
				fileName.endsWith(SVG_FILE_EXTENSION) || 
				fileName.endsWith(SVGZ_FILE_EXTENSION));
	}
}

