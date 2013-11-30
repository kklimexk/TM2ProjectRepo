package pl.agh.fractals.Julia;

import android.graphics.Color;
import pl.agh.fractals.Complex;
import pl.agh.fractals.FractalGenerator;

public class MultibrotSetGenerator extends FractalGenerator {

	private int n;

	public MultibrotSetGenerator(int width, int height, int maxIterations,
			int color, int pow) {
		super(width, height, maxIterations, color);
		this.n = pow;
	}

	public MultibrotSetGenerator(int width, int height, int maxIterations,
			int pow) {
		super(width, height, maxIterations);
		this.n = pow;
	}

	@Override
	protected int iterate(Complex c) {
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
