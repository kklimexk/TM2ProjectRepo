package board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import board.fractals.Fractal;
import board.fractals.FractalGenerator;
import board.fractals.Julia.JuliaSetGenerator;
import board.fractals.Julia.MultibrotSetGenerator;
import board.gui.pc.FractalPanel;

public class Main {

	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				int height = 900;
				int width = 1440;

				double minRe = -2.4;
				double maxRe = 1.2;
				double minIm = -1.4;
				double maxIm = minIm + (maxRe - minRe) * 3 / 4;

				System.out.println(maxIm);

				Color color = Color.red;
				int maxIterations = 100;
				FractalGenerator gen;

				gen = new MultibrotSetGenerator(width, height, maxIterations,
						color, 2);
				gen = new JuliaSetGenerator(width, height, maxIterations,
				color, -0.75543, 0.08101);
//				 gen = new BurningShipGenerator(width, height,
//				 maxIterations,
//				 color, 2);

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
