package board.fractals.Julia;

import java.awt.Color;

import board.fractals.Complex;
import board.fractals.FractalGenerator;

public class MultibrotSetGenerator extends FractalGenerator {

	private int n;

	public MultibrotSetGenerator(int width, int height, int maxIterations,
			Color color, int pow) {
		super(width, height, maxIterations, color);
		this.n = pow;
	}

	@Override
	protected Color iterate(Complex c) {
		Complex z = new Complex(c.re, c.im);
		int i;
		for (i = 0; i < maxIterations; ++i) {
			z = z.pow(n).add(c);
			if (escapesToInfinity(z))
				return scaleColor(i);
		}
		return Color.BLACK;
	}

}
