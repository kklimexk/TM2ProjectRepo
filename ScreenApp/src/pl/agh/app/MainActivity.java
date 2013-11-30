package pl.agh.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import pl.agh.bluetooth.BluetoothConnection;
import pl.agh.bluetooth.R;
import pl.agh.fractals.FractalActivity;
import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	protected static final int DISCOVERY_REQUEST = 1;
	private BluetoothConnection bluetoothConnection;
	private ArrayAdapter<String> btArrayAdapter;
	private ListView devicesList;
	private Button searchBtn;
	private Button generateBtn;

	private void initBluetoothConnection() {
		bluetoothConnection = new BluetoothConnection();
		bluetoothConnection.checkBluetoothConnection(this);
	}

	private void initComponents() {
		searchBtn = (Button) findViewById(R.id.searchBtn);
		generateBtn = (Button) findViewById(R.id.generateBtn);
		devicesList = (ListView) findViewById(R.id.listOfDevices);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initBluetoothConnection();
		initComponents();
		findLocalDevices();
		discoverDevices(this);
		displayFractal();
	}

	public void findLocalDevices() {
		List<String> namesList = new ArrayList<String>();
		namesList.clear();
		btArrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, namesList);

		Set<BluetoothDevice> pairedDevices = bluetoothConnection
				.getmBluetoothAdapter().getBondedDevices();
		if (pairedDevices.size() > 0) {
			for (BluetoothDevice device : pairedDevices) {
				String deviceBTName = device.getName();
				btArrayAdapter.add(deviceBTName);
			}
		}
		devicesList.setAdapter(btArrayAdapter);
	}

	public void discoverDevices(final Activity activity) {
		searchBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
						.getDefaultAdapter();
				List<String> namesList = new ArrayList<String>();
				namesList.clear();
				btArrayAdapter = new ArrayAdapter<String>(activity,
						android.R.layout.simple_list_item_1, namesList);

				final BroadcastReceiver mReceiver = new BroadcastReceiver() {
					public void onReceive(Context context, Intent intent) {
						String action = intent.getAction();
						// When discovery finds a device
						if (BluetoothDevice.ACTION_FOUND.equals(action)) {
							// Get the BluetoothDevice object from the Intent
							BluetoothDevice device = intent
									.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
							btArrayAdapter.add(device.getName());
						}
					}
				};

				// String aDiscoverable =
				// BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE;
				// startActivityForResult(new Intent(aDiscoverable),
				// DISCOVERY_REQUEST);
				IntentFilter filter = new IntentFilter(
						BluetoothDevice.ACTION_FOUND);
				registerReceiver(mReceiver, filter);
				mBluetoothAdapter.startDiscovery();
				devicesList.setAdapter(btArrayAdapter);
			}
		});
	}

	public void displayFractal() {
		generateBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent chartIntent = new Intent(MainActivity.this, FractalActivity.class);
				startActivity(chartIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
