package app.maikol.catcam.components;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.maikol.catcam.R;
import app.maikol.catcam.SoundMenu;
import app.maikol.catcam.lists.SoundListView;

public class SoundButton extends ImageButton {

	int soundId;

    String songLabel;

	public SoundButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		final Context c = context;
		soundId = R.raw.send;
        songLabel = SoundMenu.SOUNDS.BIP_BIP.getLabel();
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
	
	public void setLongClickListener(final SoundListView listView, final LinearLayout menulayout,final Activity a){
		this.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
                menulayout.setVisibility(View.VISIBLE);
				listView.setSoundButton(SoundButton.this);

                TextView lblCurrentSound = (TextView) a.findViewById(R.id.lblCurrentSound);
                lblCurrentSound.setText("CURRENT: " + SoundButton.this.getSoundLabel());
				return true;
			}
		});
	}

	public int getSoundId() {
		return soundId;
	}

	public void setSound(int soundId, String label) {
		this.soundId = soundId;
        this.songLabel = label;
	}

	public SoundButton(Context context) {
		super(context);
	}

    public String getSoundLabel(){
        return this.songLabel;
    }
}
