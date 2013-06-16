package app.maikol.catcam;

import java.util.ArrayList;
import java.util.List;

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
import app.maikol.catcam.adapters.OptionsValueAdapter;

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

    final ArrayList<OptionValue> options = new ArrayList<OptionValue>();

	public PopUpWindow(final Activity a, Parameters p, View popUpView) {
		activity = a;
		cameraParameters = p;
		view = popUpView;

		view.setVisibility(View.GONE);

		// flashOn = (RadioButton) activity.findViewById(R.id.rdbFlashOn);
		// flashOff = (RadioButton) activity.findViewById(R.id.rdbFlashOff);
		// flashAuto = (RadioButton) activity.findViewById(R.id.rdbFlashAuto);
		// rdgFlash = (RadioGroup) activity.findViewById(R.id.rdgFlash);


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

                    OptionsValueAdapter arrayAdapter = new OptionsValueAdapter(a
							.getApplicationContext(),
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

    public int[] getCurrentOptionsIndex(){
        int[] indexes;
        indexes = new int[options.size()];
        for (int i=0; i< options.size();i++){
            Log.e("catcam", options.get(i).toString());
            indexes[i] = options.get(i).getActive();
        }
        Log.e("catcam", indexes.toString());
        return indexes;
    }

    public void setOptionsIndex(List<Integer> indexes){
        Log.e("catcam", indexes.toString());
        for (int i=0; i< indexes.size();i++){
            options.get(i).setActive(indexes.get(i));
        }
    }
}
