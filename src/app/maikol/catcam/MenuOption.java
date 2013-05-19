package app.maikol.catcam;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Camera;

public class MenuOption {

	public enum options { FLASH, RESOLUTION };
	
	private options name;
	
	private List<OptionValue> values;
	
	private OptionValue currentValue;
	
	public MenuOption(){
		
	}
	
	public MenuOption(Camera camera,options option){
		this.name = option;
		
		values = new ArrayList<OptionValue>();
		
//		switch(option){
//		case FLASH: values.add("ON"));
//					values.add("OFF"));
//					values.add(new OptionValue("AUTO"));
//					break;
//		case RESOLUTION: 
		
//		}
	}
}
