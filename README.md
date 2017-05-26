
# Introduction
This project is licensed under BSD license.
See license.txt for details.

The purpose of this library is to load SVG files and 
generate java code out of it. Currently I have implemented
a transcoder to convert the SVG files to Java2D code.

What is the benefit of this? When you want to use 
scalable graphics in your code, e.g. icons that you want 
use in different sizes, SVG would be a good way to go.
But to use SVG graphics in java you would have to bundle
a SVG library like Batik or Salamander that have a big
fingerprint and may bloat your product. 

To transcode the graphics may give you the advantage 
of scalable graphics without this dependency. It has 
also the advantage that the painting is no longer 
interpreted but compiled painting code that will be faster.

The size of the generated classes is nearly the same as the 
size of the original SVG files put in a jar or with pac200
you might even save some space there too. 

So transcoding the files gives you all the benefits without the
downsides of using SVG files directly.


The library was tested on the Tango icon set and on the 
Oxygen icon set and can transcode many of the icons without error.

The current implementation as some known problems:

- Embedded images are not supported
- Only basic text rendering support
- SVG files need to be strictly valid 
  (has problems with e.g. some Oxygen icons)
- May generate classes with methods that are to long 
  (Java 64K per method limit)


The idea and the initial code is taken from the Flamingo project 

https://flamingo.dev.java.net/

There the initial version is/was included as an example.
I have taken that code and refactored and restructured most of if.
I fixed some issues and added some more functionality.

Some minor code was taken from the Batik painting code 
(mainly the code for the text node transformation).

The Apache Batik library is needed to read the SVG files. 
It is licensed under Apache License, Version 2.0 
and is included in this project.

The icon and the splash screen are licensed under the 
Creative Commons Attribution ShareAlike 2.5 and are based on work of wikimedia images. 

The GUI test program uses jsyntaxpane for the source code display area.

To use the text program you need to execute the class 
com.btr.svg2java.Svg2Java2D

So to start the GUI test program you have to download and extract the binary distribution,
unzip all jar files to a folder and execute in this folder

java -jar svg2java_20101123.jar  

This will launch the program.

Have fun,
- Bernd Rosstauscher (svg2java@rosstauscher.de) 