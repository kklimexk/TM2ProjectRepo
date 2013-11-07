package board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import board.gui.pc.FractalPanel;
import board.mandelbrot.Fractal;
import board.mandelbrot.MandelbrotGenerator;

public class Main {

	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				int ImageHeight = 900;
				int ImageWidth = 1440;
				double minRe = -2.0;
				double maxRe = 1.0;
				double minIm = -1.2;
				double maxIm = minIm + (maxRe - minRe) * ImageWidth / ImageHeight;
				System.out.println(maxIm);
				int maxIterations = 2500;
				MandelbrotGenerator gen = new MandelbrotGenerator(ImageWidth, ImageHeight, maxIterations, Color.RED);
				Fractal fractal = gen.create(minRe, maxRe, minIm, 1.2);
				System.out.println(fractal.image.length);
				FractalPanel panel = new FractalPanel(fractal);

				JFrame frame = new JFrame();
				frame.setSize(new Dimension(ImageWidth, ImageHeight));
				frame.setLayout(new BorderLayout());
				frame.add(panel, BorderLayout.CENTER);
				frame.setVisible(true);

			}
		});
	}
}
