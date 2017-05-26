package com.btr.svg2java.test.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.zip.GZIPInputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import jsyntaxpane.DefaultSyntaxKit;
import jsyntaxpane.syntaxkits.JavaSyntaxKit;

import org.apache.batik.swing.JSVGCanvas;

import com.btr.svg2java.java2d.SvgToJava2DTranscoder;
import com.btr.svg2java.test.swing.icons.AppIcon;
import com.btr.svg2java.test.swing.icons.DocumentOpen;
import com.btr.svg2java.test.swing.util.FileDnDSupport;
import com.btr.svg2java.test.swing.util.FileDnDSupport.FileDropListener;
import com.btr.svg2java.test.swing.util.SvgFileFilter;
import com.btr.svg2java.test.swing.util.TransparentBackgroundPaint;
import com.btr.svg2java.util.ClassNamingHelper;
import com.btr.svg2java.util.TranscodeListenerAdapter;

/*****************************************************************************
 * Test program to generate java code from a given SVG
 *
 * @author  Bernd Rosstauscher 
 *         (svg2java(@)rosstauscher.de)
 ****************************************************************************/

public class Svg2Java2D extends JFrame implements FileDropListener {

	private static final String DEFAULT_PACKAGE = "amos.share.icons.svg.";
	
	private JSVGCanvas canvas;
	private File file;
	private JEditorPane sourceArea;
	private JTextArea logArea;
	private JProgressBar progressBar;
	
	/*************************************************************************
	 * Constructor
	 ************************************************************************/
	
	public Svg2Java2D() {
		super("SVG to Java2D");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		AppIcon appIcon = new AppIcon();
		setIconImage(getImage(appIcon));
		
		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.setBorder(BorderFactory.createTitledBorder("Svg Viewer"));
		
		this.canvas = new JSVGCanvas();
		canvas.setDoubleBufferedRendering(true);
		canvas.setEnableZoomInteractor(true);
		canvas.setEnablePanInteractor(true);
		canvas.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		TransparentBackgroundPaint bgPaint = new TransparentBackgroundPaint(Color.WHITE, Color.GRAY, 25);
		canvas.setBackground(bgPaint);
		
		FileDnDSupport fileDnDSupport = new FileDnDSupport();
		fileDnDSupport.addDropTarget(canvas);
		fileDnDSupport.addFileFilter(new SvgFileFilter());
		fileDnDSupport.addDropListener(this);
		
		
		JPanel canvasBG = new JPanel(new GridBagLayout());
		canvasBG.setBackground(bgPaint);
		canvasBG.setOpaque(true);
		canvasBG.add(canvas);
		JScrollPane csc = new JScrollPane(canvasBG);
		leftPanel.add(csc, BorderLayout.CENTER);

		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setBorder(BorderFactory.createTitledBorder("Java Code"));

		DefaultSyntaxKit.initKit();
		
		this.sourceArea = new JEditorPane();
		
		JavaSyntaxKit kit = new JavaSyntaxKit();
		
		JScrollPane sc = new JScrollPane(sourceArea);

		// This action uses a java script that is not compatible with new Nashorn JS engine
		// We deactivate this action.	
		kit.getConfig().remove("Action.insert-date");
		kit.getConfig().remove("Action.insert-date.Function");
		kit.getConfig().remove("Script.insert-date.URL");
		sourceArea.setEditorKit(kit);

		sourceArea.setContentType("text/java");
		sourceArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		sourceArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		rightPanel.add(sc, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JButton openButton = new JButton("Open...");
		DocumentOpen applicIcon = new DocumentOpen();
		applicIcon.setDimension(new Dimension(16, 16));
		openButton.setIcon(applicIcon);
		openButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		buttonPanel.add(openButton);

		this.progressBar = new JProgressBar();
		progressBar.setVisible(false);
		progressBar.setIndeterminate(true);
		buttonPanel.add(progressBar);
		
		JSplitPane splitArea = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, leftPanel, rightPanel);

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(splitArea, BorderLayout.CENTER);
		
		this.logArea = new JTextArea();
		logArea.setEditable(false);
		logArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		sc = new JScrollPane(logArea);
		sc.setPreferredSize(new Dimension(500, 100));
		
		JPanel panLog = new JPanel(new BorderLayout());
		panLog.add(sc, BorderLayout.CENTER);
		panLog.setBorder(BorderFactory.createTitledBorder("Processing Log"));
		
		
		mainPanel.add(panLog, BorderLayout.SOUTH);
		mainPanel.add(buttonPanel, BorderLayout.NORTH);
		
		setContentPane(mainPanel);
		pack();
		setLocationRelativeTo(null);
	}
	
	/*************************************************************************
	 * @param appIcon
	 * @return
	 ************************************************************************/
	
	private Image getImage(AppIcon appIcon) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
		GraphicsDevice gs = ge.getDefaultScreenDevice(); 
		GraphicsConfiguration gc = gs.getDefaultConfiguration(); 
		BufferedImage img = gc.createCompatibleImage(
				appIcon.getIconWidth(), 
				appIcon.getIconHeight(), 
				Transparency.TRANSLUCENT); 
		Graphics2D gr = (Graphics2D) img.getGraphics();
		appIcon.paint(gr);
		return img;
	}

	/*************************************************************************
	 * Converts the given file to Java2D code
	 ************************************************************************/
	
	private void convertFile() {
		SwingWorker<String, String> worker = new ConvertWorker();
		worker.execute();
	}
	
	/*************************************************************************
	 * Shows a file chooser to load an SVG image file
	 ************************************************************************/
	
	private void openFile() {
		JFileChooser chooser = new JFileChooser();
		chooser.setAcceptAllFileFilterUsed(true);
		chooser.setFileFilter(new SvgFileFilter());
		chooser.setSelectedFile(this.file);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			this.file = chooser.getSelectedFile();
			updateView();
		}
	}

	/*************************************************************************
	 * Updates the UI when a file is opened.
	 ************************************************************************/
	
	private void updateView() {
		this.progressBar.setVisible(true);
		canvas.setURI(file.toURI().toString());
		convertFile();
	}

	/*************************************************************************
	 * filesDropped
	 * @see com.btr.svg2java.FileDnDSupport.FileDropListener#filesDropped(java.util.List)
	 ************************************************************************/
	@Override
	public void filesDropped(List<File> fileList) {
		if (fileList.size() > 0) {
			this.file = fileList.get(0);
			updateView();
		}
	}

	/*************************************************************************
	 * Main entry point of the application.
	 * @param args command line arguments.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 ************************************************************************/
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Svg2Java2D().setVisible(true);
			}
		});
	}
	
	/*************************************************************************
	 * Helper method to log exceptions to the logging area.
	 * @param e exception that occurred.
	 ************************************************************************/
	
	private void logException(Throwable e) {
		StringWriter s = new StringWriter();
		e.printStackTrace(new PrintWriter(s));
		logArea.append(s.toString()+"\n");
	}
	
	/*****************************************************************************
	 * Worker to do the heavy stuff in the background.
	 ****************************************************************************/
	
	private final class ConvertWorker extends SwingWorker<String, String> {
		@Override
		protected String doInBackground() throws Exception {
			StringWriter buffer = new StringWriter();
			publish("Transcoding file: "+file);
			SvgToJava2DTranscoder trans = new SvgToJava2DTranscoder();
			trans.addListener(new TranscodeListenerAdapter() {
				@Override
				public void onUnsupportedOperation(Object node, String message) {
					publish("Unsupported feature: Node "+node+" Message: "+message);
				}
			});
			String className = ClassNamingHelper.getClassNameFromFileName(file.getName());
			InputStream source = getSvgSource();
			trans.transcode(source, new PrintWriter(buffer), DEFAULT_PACKAGE+className);
			
			publish("Finished");

			return buffer.toString();
		}

		/*************************************************************************
		 * Builds the correct input stream to read the SVG content.
		 * @return the input stream.
		 * @throws FileNotFoundException
		 ************************************************************************/
		
		private InputStream getSvgSource() {
			try {
				InputStream source = new FileInputStream(file);
				if (file.getName().toLowerCase().endsWith(SvgFileFilter.SVGZ_FILE_EXTENSION)) {
					source = new GZIPInputStream(source);
				}
				return source;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		/*************************************************************************
		 * done
		 * @see javax.swing.SwingWorker#done()
		 ************************************************************************/
		@Override
		protected void done() {
			try {
				sourceArea.setText(get());
			} catch (InterruptedException e) {
				logException(e);
			} catch (ExecutionException e) {
				logException(e);
			}
			progressBar.setVisible(false);
		}

		/*************************************************************************
		 * process
		 * @see javax.swing.SwingWorker#process(java.util.List)
		 ************************************************************************/
		@Override
		protected void process(List<String> chunks) {
			for (String string : chunks) {
				logArea.append(string+"\n");
			}
		}
	}

}
