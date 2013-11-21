package board.fractals;

import java.awt.Color;

public abstract class FractalGenerator {
	protected int width;
	protected int height;
	protected int maxIterations;
	public Color color;

	public FractalGenerator(int width, int height, int maxIterations,
			Color color) {
		this.width = width;
		this.height = height;
		this.maxIterations = maxIterations;
		this.color = color;
	}

	protected abstract Color iterate(Complex z);

	protected Color scaleColor(int i) {
		i = i == 0 ? 1 : i;
		double d = maxIterations / i;
		return new Color((int) (color.getRed() / d),
				(int) (color.getGreen() / d), (int) (color.getBlue() / d));
	}

	protected boolean escapesToInfinity(Complex z) {
		return z.abs() > 4.;
	}

	public Fractal create(double minRe, double maxRe, double minIm, double maxIm) {
		double dx = Math.abs(minRe - maxRe) / width;
		double dy = Math.abs(minIm - maxIm) / height;
		Fractal fractal = new Fractal(width, height);

		for (int x = 0; x < width; x++) {
			double re = minRe + x * dx;
			for (int y = 0; y < height; y++) {
				double im = maxIm - y * dy;
				fractal.getImage()[x][y] = iterate(new Complex(re, im));
			}
		}

		return fractal;
	}

}
