package board.mandelbrot;

import java.awt.Color;

public class MandelbrotGenerator extends FractalGenerator {

	public MandelbrotGenerator(int width, int height, int maxIterations, Color color) {
		super(width, height, maxIterations, color);
	}

	private double abs(double re, double im) {
		return re * re + im * im;
	}

	private boolean escapesToInfinity(double re, double im) {
		return abs(re, im) > 4.;
	}

	private Color iterate(double re, double im) {
		double zRe = re;
		double zIm = im;
		int i;
		for (i = 0; i < maxIterations; ++i) {
			double zIm2 = zIm * zIm;
			zIm = 2 * zRe * zIm + im;
			zRe = zRe * zRe - zIm2 + re;
			if (escapesToInfinity(zRe, zIm)) return scaleColor(i);
		}
		return color;
	}

	private Color scaleColor(int i) {
		i = i == 0 ? 1 : i;
		double d = maxIterations / i;
		return new Color((int) (color.getRed() / d), (int) (color.getGreen() / d), (int) (color.getBlue() / d));
	}
	@Override
	public Fractal create(double minRe, double maxRe, double minIm, double maxIm) {
		double dx = Math.abs(minRe - maxRe) / width;
		double dy = Math.abs(minIm - maxIm) / height;
		System.out.println(dx + " " + dy);
		System.out.println(width + " x " + height);
		Fractal fractal = new Fractal(width, height);
		System.out.println(fractal.width + " x " + fractal.height);
		for (int x = 0; x < width; x++) {
			double re = minRe + x * dx;
			for (int y = 0; y < height; y++) {
				double im = maxIm - y * dy;				
				fractal.getImage()[x][y] = iterate(re, im);
			}
		}

		return fractal;
	}

}
