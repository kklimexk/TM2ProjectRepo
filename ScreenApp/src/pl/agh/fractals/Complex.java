package pl.agh.fractals;

public class Complex {
	public final double re;
	public final double im;
	public static final double EPSILON = 1e-16;

	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}

	public double arg() {
		return Math.atan2(im, re);
	}

	public double abs() {
		return re * re + im * im;
	}

	public double r() {
		return Math.sqrt(abs());
	}

	public Complex add(Complex z) {
		return new Complex(re + z.re, im + z.im);
	}

	public Complex mul(Complex z) {
		return new Complex(re * z.re - im * z.im, im * z.re + re * z.im);
	}

	public Complex mul(double x) {
		return new Complex(re * x, im * x);
	}

	public Complex div(Complex z) {
		return mul(z.con()).mul(1 / z.abs());
	}

	public Complex con() {
		return new Complex(re, -im);
	}

	public Complex pow(int n) {
		if (n < 0)
			return new Complex(1, 0).div(pow(-n));
		if (n == 0)
			return new Complex(1, 0);

		Complex c = new Complex(re, im);

		for (int i = 1; i < n; ++i) {
			c = c.mul(this);
		}

		return c;

	}

	@Override
	public String toString() {
		return "z = " + re + (im >= 0 ? " + " : " - ") + Math.abs(im) + "i";
	}

	public static void main(String[] args) {
		Long start = System.currentTimeMillis();

		System.out.println("in " + (System.currentTimeMillis() - start));
	}
}
