package board.fractals.burningShip;

import java.awt.Color;

import board.fractals.Complex;
import board.fractals.FractalGenerator;

public class BurningShipGenerator extends FractalGenerator {

	private int n;

	public BurningShipGenerator(int width, int height,
			int maxIterations, Color color, int n) {
		super(width, height, maxIterations, color);

		this.n = n;
	}

	@Override
	protected Color iterate(Complex c) {
		Complex z = new Complex(c.re, c.im);

		int i;
		for (i = 0; i < maxIterations; ++i) {
			z = new Complex(Math.abs(z.re), -Math.abs(z.im)).pow(n).add(c);

			if (escapesToInfinity(z))
				return scaleColor(i);
		}
		return Color.BLACK;
	}

}
