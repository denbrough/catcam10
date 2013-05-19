package app.maikol.catcam.lists;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
import app.maikol.catcam.components.SoundButton;

public class SoundListView extends ListView{

	SoundButton soundButton;
	
	public SoundButton getSoundButton() {
		return soundButton;
	}

	public void setSoundButton(SoundButton soundButton) {
		this.soundButton = soundButton;
	}

	public SoundListView(Context context) {
		super(context);
	}
	
	public SoundListView(Context context,AttributeSet attr) {
		super(context,attr);
	}

}
