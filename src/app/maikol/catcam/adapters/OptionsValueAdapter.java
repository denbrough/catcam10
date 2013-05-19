package app.maikol.catcam.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import app.maikol.catcam.OptionValue;
import app.maikol.catcam.R;
import app.maikol.catcam.R.id;
import app.maikol.catcam.R.layout;

public class OptionsValueAdapter extends BaseAdapter{

	private Context context;
	private LayoutInflater li;
	private ArrayList<String> optionsList = new ArrayList<String>();
	
	final String TAG = "OptionsValueAdapter";
	
	public OptionsValueAdapter(Context context, ArrayList<String> list) {
		super();
		Log.d(TAG, "Adapter value constructor");
		this.context = context;
		this.optionsList = list;
		li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return optionsList.size();
	}

	@Override
	public Object getItem(int position) {
		return optionsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return (long) position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d(TAG, "getView size"+optionsList.size());
		View v;
		
		if (convertView != null) {
			v = convertView;
		} else {
			v = li.inflate(R.layout.options_menu_item, null);
		}

		return v;
	}

}
