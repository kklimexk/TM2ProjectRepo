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
import board.fractals.burningShip.BurningShipGenerator;
import board.gui.pc.FractalPanel;

public class Main {

	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				int width = 1366;
				int height = 768;

				double minRe = -2.0;
				double maxRe = 1.2;
				double minIm = -1.3;
				double maxIm = minIm + (maxRe - minRe) * 3 / 4;

				// minRe = -0.2;
				// maxRe = 0.1;
				// minIm = 0.6;

				System.out.println(maxIm);

				Color color = Color.GREEN;
				int maxIterations = 1000;
				FractalGenerator gen1, gen2, gen3;

				gen1 = new MultibrotSetGenerator(width, height, maxIterations,
						color, 2);
				gen2 = new JuliaSetGenerator(width, height, maxIterations,
						color, -0.835, -0.2321);
				gen3 = new BurningShipGenerator(width, height, maxIterations,
						color, 2);

				Long start = System.currentTimeMillis();

				Fractal fractal = gen3.create(minRe, maxRe, minIm, maxIm);

				Long end = System.currentTimeMillis();
				System.out.println("Finished in " + (end - start));

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
