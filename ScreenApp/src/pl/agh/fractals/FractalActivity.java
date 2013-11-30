package pl.agh.fractals;

import pl.agh.bluetooth.R;
import pl.agh.bluetooth.R.layout;
import pl.agh.bluetooth.R.menu;
import pl.agh.fractals.Julia.MultibrotSetGenerator;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class FractalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_fractal);
		generateFractal();
	}

	public void initComponents() {
	}

	public void generateFractal() {
		int width = 480;
		int height = 320;

		double minRe = -2.0;
		double maxRe = 1.2;
		double minIm = -1.3;
		double maxIm = minIm + (maxRe - minRe) * 3 / 4;

		// minRe = -0.2;
		// maxRe = 0.1;
		// minIm = 0.6;

		System.out.println(maxIm);

		int color = Color.BLUE;
		int maxIterations = 100;
		FractalGenerator gen1, gen2, gen3;
		gen1 = new MultibrotSetGenerator(width, height, maxIterations, 2);
		Fractal fractal = gen1.create(minRe, maxRe, minIm, maxIm);
		Bitmap fractalBitmap = getImage(fractal);
		BitmapDrawable bd = new BitmapDrawable(fractalBitmap);
		ImageView fractalView = (ImageView) findViewById(R.id.fractalView);
		fractalView.setBackgroundDrawable(bd);
	}

	private Bitmap getImage(Fractal fractal) {
		// BufferedImage.TYPE_INT_RGB
		Bitmap image = Bitmap.createBitmap(fractal.width, fractal.height,
				Bitmap.Config.ARGB_8888);
		for (int x = 0; x < fractal.width; x++) {
			for (int y = 0; y < fractal.height; y++) {
				image.setPixel(x, y, fractal.image[x][y]);
			}
		}
		return image;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fractal, menu);
		return true;
	}

}
