package pl.agh.fractals;

public class Fractal {
	public int width;
	public int height;
	public final int[][] image;

	public Fractal(int width, int height) {
		this.width = width;
		this.height = height;
		this.image = new int[width][height];
		System.out.println("created fractal: " + width + "x" + height);
	}

	public int[][] getImage() {
		return image;
	}
}
