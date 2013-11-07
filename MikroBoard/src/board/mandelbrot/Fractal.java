package board.mandelbrot;

import java.awt.Color;

public class Fractal {
	public int width;
	public int height;
	public final Color[][] image;

	public Fractal(int width, int height) {
		this.width = width;
		this.height = height;
		this.image = new Color[width][height];
		System.out.println("created fractal: " + width + "x" + height);
	}

	public Color[][] getImage() {
		return image;
	}
}
