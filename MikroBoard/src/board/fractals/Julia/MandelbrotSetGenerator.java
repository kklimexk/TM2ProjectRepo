package board.fractals.Julia;

import java.awt.Color;

import board.fractals.Fractal;
import board.fractals.FractalGenerator;

public class MandelbrotSetGenerator extends FractalGenerator {

	public MandelbrotSetGenerator(int width, int height, int maxIterations,
			Color color) {
		super(width, height, maxIterations, color);
	}

	@Override
	protected Color iterate(double re, double im) {
		double zRe = re;
		double zIm = im;
		int i;
		for (i = 0; i < maxIterations; ++i) {
			double zIm2 = zIm * zIm;
			zIm = 2 * zRe * zIm + im;
			zRe = zRe * zRe - zIm2 +re;
			if (escapesToInfinity(zRe, zIm))
				return scaleColor(i);
		}
		return Color.BLACK;
	}


}
