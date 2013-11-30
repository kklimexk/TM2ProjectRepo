package pl.agh.bluetooth;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

public class BluetoothConnection {
	private static final int REQUEST_ENABLE_BT = 1;
	private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
			.getDefaultAdapter();

	public BluetoothAdapter getmBluetoothAdapter() {
		return mBluetoothAdapter;
	}

	public void checkBluetoothConnection(Context context) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
		if (mBluetoothAdapter == null) {
			alertDialogBuilder
					.setTitle("Your device does not support Bluetooth!!!")
					.setMessage("Click OK to exit.")
					.setNeutralButton("OK", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();

		} else if (!mBluetoothAdapter.isEnabled()) {
			Intent enableBtIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			((Activity) context).startActivityForResult(enableBtIntent,
					REQUEST_ENABLE_BT);
		}
	}
}
