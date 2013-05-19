package app.maikol.catcam;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.hardware.Camera.Parameters;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import app.maikol.catcam.OptionValue.OPTION_NAME;
import app.maikol.catcam.adapters.OptionsMenuAdapter;

public class PopUpWindow {
	private Parameters cameraParameters;

	RadioButton flashOn;
	RadioButton flashOff;
	RadioButton flashAuto;
	RadioGroup rdgFlash;
	Button backButton;
	RadioGroup radioOption;

	Activity activity;
	View view;

	ListView listView;
	final ListView listViewValues;
	
	public PopUpWindow(final Activity a, Parameters p, View popUpView) {
		activity = a;
		cameraParameters = p;
		view = popUpView;

		view.setVisibility(View.GONE);

		// flashOn = (RadioButton) activity.findViewById(R.id.rdbFlashOn);
		// flashOff = (RadioButton) activity.findViewById(R.id.rdbFlashOff);
		// flashAuto = (RadioButton) activity.findViewById(R.id.rdbFlashAuto);
		// rdgFlash = (RadioGroup) activity.findViewById(R.id.rdgFlash);

		final ArrayList<OptionValue> options = new ArrayList<OptionValue>();
		options.add(new OptionValue(OPTION_NAME.FLASH, cameraParameters));
		options.add(new OptionValue(OPTION_NAME.RESOLUTION, cameraParameters));

		final OptionsMenuAdapter adapter = new OptionsMenuAdapter(a.getBaseContext(),
				options);
		listView = (ListView) a.findViewById(R.id.optionList);
		listView.setAdapter(adapter);

		listViewValues = (ListView) a
				.findViewById(R.id.listView2);
		listViewValues.setVisibility(View.GONE);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View v,
					final int position, long id) {

				if (position <= options.size()) {
					// listViewValues.setSelection();
					Log.d("DEBUG", "ACTIVE: "
							+ options.get(position).getActive());
					listViewValues.setVisibility(View.VISIBLE);
					adapter.notifyDataSetChanged();
					listViewValues.post(new Runnable() {
						@Override
						public void run() {
							if (options.get(position) != null && listViewValues.getChildAt(
									options.get(position).getActive())!=null) {
								listViewValues.getChildAt(
										options.get(position).getActive())
										.setBackgroundColor(
												Color.argb(50, 255, 255, 255));
							}
						}
					});

					ArrayAdapter arrayAdapter = new ArrayAdapter(a
							.getApplicationContext(),
							android.R.layout.simple_list_item_1,
							options.get(position).optionList);
					listViewValues.setAdapter(arrayAdapter);
					listViewValues.bringToFront();
					listViewValues
							.setOnItemClickListener(new OnItemClickListener() {

								@Override
								public void onItemClick(AdapterView<?> arg0,
										View arg1, int optionValueParameter,
										long arg3) {

									listViewValues.setVisibility(View.GONE);
									options.get(position).setActive(
											optionValueParameter);
									options.get(position)
											.modifyCameraParameters();
									adapter.notifyDataSetChanged();
								}
							});
				}
			}
		});

	}

	public void show() {
		this.view.setVisibility(View.VISIBLE);
	}

	public void hide() {
		this.view.setVisibility(View.GONE);
		this.listViewValues.setVisibility(View.GONE);
	}

	public Parameters getParameters() {
		return cameraParameters;
	}

	public boolean isVisible() {
		return (this.view.getVisibility() == View.VISIBLE);
	}
}
