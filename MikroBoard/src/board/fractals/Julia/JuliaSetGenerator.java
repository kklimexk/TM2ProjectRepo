package board.fractals.Julia;

import java.awt.Color;

import board.fractals.Complex;
import board.fractals.FractalGenerator;

public class JuliaSetGenerator extends FractalGenerator {

	protected Complex c;

	public JuliaSetGenerator(int width, int height, int maxIterations,
			Color color, double re, double im) {
		super(width, height, maxIterations, color);

		c = new Complex(re, im);
	}

	@Override
	protected Color iterate(Complex zOld) {
		Complex z = new Complex(zOld.re, zOld.im);

		for (int i = 0; i < maxIterations; ++i) {
			z = z.pow(2).add(c);

			if (escapesToInfinity(z))
				return scaleColor(i);
		}
		return Color.BLACK;
	}

}
