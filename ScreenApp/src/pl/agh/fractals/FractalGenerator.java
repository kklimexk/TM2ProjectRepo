package pl.agh.fractals;

import android.graphics.Color;

public abstract class FractalGenerator {
	protected int width;
	protected int height;
	protected int maxIterations;
	public int color;

	public FractalGenerator(int width, int height, int maxIterations, int color) {
		this.width = width;
		this.height = height;
		this.maxIterations = maxIterations;
		this.color = color;
	}

	public FractalGenerator(int width, int height, int maxIterations) {
		this.width = width;
		this.height = height;
		this.maxIterations = maxIterations;
		this.color = Color.GREEN;
	}

	protected abstract int iterate(Complex z);

	protected int scaleColor(int i) {
		double span = maxIterations / 2;
		i = i == 0 ? 1 : i;
		double step;
		if (i * 2 < maxIterations) {
			step = i / span;
			return new Color().rgb((int) (Color.RED * step),
					(int) (Color.GREEN * step), (int) (Color.BLUE * step));
			// return new Color((int) (color.getRed() * step),(int)
			// (color.getGreen() * step),(int) (color.getBlue() * step));
		} else {
			step = i / span - 1;
			return new Color().rgb((int) (Color.red(Color.WHITE) * step),
					(int) (Color.green(Color.WHITE) * step), (int) (Color.blue(Color.WHITE) * step));
			// return new Color((int) (Color.white.getRed() * step),(int)
			// (Color.white.getGreen() * step),(int) (Color.white.getBlue() *
			// step));
		}

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
