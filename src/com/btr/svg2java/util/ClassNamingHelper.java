package com.btr.svg2java.util;

import java.io.File;

/*****************************************************************************
 * Helper class to generate valid Java class names out of a given file name.
 *
 * @author  Bernd Rosstauscher 
 *         (svg2java(@)rosstauscher.de)
 ****************************************************************************/

public class ClassNamingHelper {
	
	/*************************************************************************
	 * Generates a valid class name from a given file name.
	 * This will strip away all characters that are not alphanumeric.
	 * If the name in the end starts with an number then SVG is added as a class
	 * prefix in front of the class name. 
	 * @param filePath any filename. May include a path that is ignored.
	 * @return a valid class name.
	 ************************************************************************/
	
	public static String getClassNameFromFileName(String filePath) {
		String fileName = stripPath(filePath.toLowerCase());
		fileName = stripExtension(fileName);
		StringBuilder result = new StringBuilder();
		boolean nextToUpper = true;
		for (int i = 0; i < fileName.length(); i++) {
			char c = fileName.charAt(i);
			if (Character.isLetterOrDigit(c) == false) {
				nextToUpper = true;
				continue;
			}
			if (nextToUpper) {
				c = Character.toUpperCase(c);
				nextToUpper = false;
			}
			if (i == 0 && Character.isJavaIdentifierStart(c) == false) {
				result.append("Svg");
			}
			result.append(c);
		}
		return result.toString();
	}

	/*************************************************************************
	 * Strips away a file extension from a given file name.
	 * @param fileName to process.
	 * @return the filename without file extension.
	 ************************************************************************/
	
	private static String stripExtension(String fileName) {
		String result = fileName;
		int index = fileName.lastIndexOf(".");
		if (index != -1) {
			result = fileName.substring(0, index);
		}
		return result;
	}

	/*************************************************************************
	 * Strips away a path from a given file name returnig the simple file name
	 * including the file extension.
	 * @param filePath the path and name of a file. 
	 * @return the filename.
	 ************************************************************************/
	
	private static String stripPath(String filePath) {
		String result = filePath;
		int index = filePath.lastIndexOf(File.separator);
		if (index != -1) {
			result = filePath.substring(index);
		}
		return result;
	}

}
