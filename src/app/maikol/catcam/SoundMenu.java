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
	Context c;
	
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
		soundsList.add("Cat sound");
		soundsList.add("Bip Bip");
		
		SoundAdapter adapter = new SoundAdapter(a.getBaseContext(),
				soundsList);
		ArrayAdapter arrayAdapter = new ArrayAdapter(a
				.getApplicationContext(),
				android.R.layout.simple_list_item_single_choice,
				soundsList);
		listView = (SoundListView) a.findViewById(R.id.listViewSound);
		listView.setAdapter(arrayAdapter);
		listView.setVisibility(View.GONE);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0,
					View arg1, int position,
					long arg3) {

				listView.setVisibility(View.GONE);
				if(soundsList.get(position).equals("Cat sound")){
					listView.getSoundButton().setSoundId(R.raw.cat);
				}
				if(soundsList.get(position).equals("Bip Bip")){
					listView.getSoundButton().setSoundId(R.raw.send);
				}
			}
		});
		
		btnSound1.setLongClickListener(listView);
		btnSound2.setLongClickListener(listView);
		btnSound3.setLongClickListener(listView);
		btnSound4.setLongClickListener(listView);
	}
	
	public void hide(){
		this.listView.setVisibility(View.GONE);
	}

}
