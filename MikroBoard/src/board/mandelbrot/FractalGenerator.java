package board.mandelbrot;

import java.awt.Color;

public abstract class FractalGenerator {
	protected int width;
	protected int height;
	protected int maxIterations;
	public Color color;

	public FractalGenerator(int width, int height, int maxIterations, Color color) {
		this.width = width;
		this.height = height;
		this.maxIterations = maxIterations;
		this.color = color;
	}

	public abstract Fractal create(double x1, double x2, double y1, double y2);
}
