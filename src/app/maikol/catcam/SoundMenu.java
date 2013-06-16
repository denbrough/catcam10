package app.maikol.catcam;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.maikol.catcam.adapters.SoundAdapter;
import app.maikol.catcam.components.SoundButton;
import app.maikol.catcam.lists.SoundListView;

public class SoundMenu {

	private SoundButton btnSound1;
	private SoundButton btnSound2;
	private SoundButton btnSound3;
	private SoundButton btnSound4;
	private SoundButton btnSound5;
	private SoundButton btnSound6;

	SoundListView listView;
    LinearLayout menulayout;
	Context c;
    public enum SOUNDS {
        CAT_SOUND("Cat sound"),

        BIP_BIP("Bip Bip");

        private String name;

        private SOUNDS(String name) {
            this.name = name;

        }

        public String getLabel() {
            return name;
        }
    }
	public SoundMenu(Activity a) {
		c = a.getBaseContext();

		btnSound1 = (SoundButton) a.findViewById(R.id.btnSound1);
		btnSound2 = (SoundButton) a.findViewById(R.id.btnSound2);
		btnSound3 = (SoundButton) a.findViewById(R.id.btnSound3);
		btnSound4 = (SoundButton) a.findViewById(R.id.btnSound4);

		final AudioManager mAudioManager = (AudioManager) c
				.getSystemService(Context.AUDIO_SERVICE);

		final MediaPlayer mp = MediaPlayer.create(c, R.raw.send);
		
		
		final ArrayList<String> soundsList = new ArrayList<String> ();
        for(SOUNDS sound: SOUNDS.values()){
            soundsList.add(sound.getLabel());
        }
		
		SoundAdapter adapter = new SoundAdapter(a.getBaseContext(),
				soundsList);
		ArrayAdapter arrayAdapter = new ArrayAdapter(a
				.getApplicationContext(),
				android.R.layout.simple_list_item_single_choice,
				soundsList);
        menulayout = (LinearLayout)a.findViewById(R.id.soundmenu);
        menulayout.setVisibility(View.GONE);
		listView = (SoundListView) a.findViewById(R.id.listViewSound);
		listView.setAdapter(arrayAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0,
					View arg1, int position,
					long arg3) {
                listView.setSelection(position);
                menulayout.setVisibility(View.GONE);
				if(soundsList.get(position).equals(SOUNDS.CAT_SOUND.getLabel())){
					listView.getSoundButton().setSound(R.raw.cat, SOUNDS.CAT_SOUND.getLabel());
				}
				if(soundsList.get(position).equals(SOUNDS.BIP_BIP.getLabel())){
					listView.getSoundButton().setSound(R.raw.send, SOUNDS.BIP_BIP.getLabel());
				}
			}
		});
		
		btnSound1.setLongClickListener(listView,menulayout,a);
		btnSound2.setLongClickListener(listView,menulayout,a);
		btnSound3.setLongClickListener(listView,menulayout,a);
		btnSound4.setLongClickListener(listView,menulayout,a);
	}
	
	public void hide(){
		this.menulayout.setVisibility(View.GONE);
	}

}
