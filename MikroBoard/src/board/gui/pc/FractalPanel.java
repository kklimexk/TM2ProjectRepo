package board.gui.pc;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import board.mandelbrot.Fractal;

public class FractalPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final Fractal fractal;
	private final BufferedImage image;

	public FractalPanel(Fractal fractal) {
		this.fractal = fractal;
		image = getImage();
		JLabel pic = new JLabel(new ImageIcon(image));
		add(pic);
	}

	private BufferedImage getImage() {
		final BufferedImage image = new BufferedImage(fractal.width, fractal.height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < fractal.width; x++) {
			for (int y = 0; y < fractal.height; y++) {
//				System.out.println(x + "x " + y + " -> " + fractal.image[x][y]);
				image.setRGB(x, y, fractal.image[x][y].getRGB());
			}

		}
		return image;
	}

//	@Override
//	public void paintComponents(Graphics g) {
//		super.paintComponents(g);
//		g.drawImage(image, 0, 0, null);
//	}

}
