package board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import board.fractals.Fractal;
import board.fractals.FractalGenerator;
import board.fractals.Julia.MandelbrotSetGenerator;
import board.gui.pc.FractalPanel;

public class Main {

	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				int height = 900;
				int width = 1440;

				double minRe = -2.0;
				double maxRe = 1.0;
				double minIm = -1.2;
				double maxIm = minIm + (maxRe - minRe) * 3 / 4;

				System.out.println(maxIm);

				Color color = Color.blue;
				int maxIterations = 100;
				FractalGenerator gen;

				 gen = new MandelbrotSetGenerator(width,
				 height, maxIterations, color);
//				// gen = new JuliaSetGenerator(width, height, maxIterations,
//				// color, -0.75543, 0.08101);
//				gen = new BurningShipSetGenerator(width, height, maxIterations,
//						color);

				Fractal fractal = gen.create(minRe, maxRe, minIm, maxIm);

				FractalPanel panel = new FractalPanel(fractal);
				JFrame frame = new JFrame();
				frame.setSize(new Dimension(width, height));
				frame.setLayout(new BorderLayout());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(panel, BorderLayout.CENTER);
				frame.setVisible(true);

			}
		});
	}
}
