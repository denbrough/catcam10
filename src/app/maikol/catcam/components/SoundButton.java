package app.maikol.catcam.components;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import app.maikol.catcam.R;
import app.maikol.catcam.lists.SoundListView;

public class SoundButton extends ImageButton {

	int soundId;

	public SoundButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		final Context c = context;
		soundId = R.raw.send;
		this.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (SoundButton.this != null) {
					try {
						MediaPlayer mp = MediaPlayer.create(c,
								SoundButton.this.getSoundId());
						if (mp != null) {

							mp.start();

						}
						mp.setOnCompletionListener(new OnCompletionListener() {

							@Override
							public void onCompletion(MediaPlayer mp) {
								 mp.release();
							}

						});
					} catch (Exception e) {
						
					}
				}

			}
		});

	}
	
	public void setLongClickListener(final SoundListView listView){
		this.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				listView.setVisibility(View.VISIBLE);
				listView.setSoundButton(SoundButton.this);
				return true;
			}
		});
	}

	public int getSoundId() {
		return soundId;
	}

	public void setSoundId(int soundId) {
		this.soundId = soundId;
	}

	public SoundButton(Context context) {
		super(context);
	}

}
