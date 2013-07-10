package app.maikol.catcam;

import java.util.ArrayList;
import java.util.List;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.text.style.LineHeightSpan.WithDensity;

public class OptionValue {

	private OPTION_NAME type;
	private String name;
	private String value;
	List<String> optionList;
	Parameters cameraParameters;
	Camera camera;

	private int active = -1;

	public enum OPTION_NAME {
		FLASH, RESOLUTION, FOCUS
	};

	public OptionValue() {
		optionList = new ArrayList<String>();

	}

	public OptionValue(OPTION_NAME name, Parameters parameters) {
		optionList = new ArrayList<String>();
		cameraParameters = parameters;
		type = name;
		switch (name) {
		case FLASH:
			optionList.add("On");
			optionList.add("Off");
			optionList.add("Auto");
			this.name = "Flash";
			active = 0;
			try {
				if (parameters.getFlashMode().equalsIgnoreCase(
						Parameters.FLASH_MODE_OFF)) {
					active = 1;
				}
				if (parameters.getFlashMode().equalsIgnoreCase(
						Parameters.FLASH_MODE_AUTO)) {
					active = 2;
				}
			} catch (Exception e) {
				optionList.clear();
				optionList.add("Off");
			}

			break;
		case RESOLUTION:
			this.name = "Resolution";
			for (Size size : cameraParameters.getSupportedPictureSizes()) {
				if (size.equals(cameraParameters.getPictureSize())) {
					active = cameraParameters.getSupportedPictureSizes()
							.indexOf(size);
				}
				optionList.add(size.width + "x" + size.height);
			}
			break;

		case FOCUS:
			this.name = "Focus";
			for (String focusMode : cameraParameters.getSupportedFocusModes()) {
				if (focusMode.equals(cameraParameters.getFocusMode())) {
					active = cameraParameters.getSupportedFocusModes().indexOf(
							focusMode);
				}
				if (focusMode.length() > 1) {
					focusMode = focusMode.substring(0, 1).toUpperCase()
							+ focusMode.substring(1);
				}
				optionList.add(focusMode);
			}
			break;
		}
	}

	public boolean isActive() {
		return active != -1;
	}

	public void modifyCameraParameters() {
		switch (this.type) {
		case FLASH:
			String s = "off";
			if (isActive()) {
				s = optionList.get(active);
			}
			cameraParameters.setFlashMode(Parameters.FLASH_MODE_OFF);
			if (s.equalsIgnoreCase("on")) {
				cameraParameters.setFlashMode(Parameters.FLASH_MODE_ON);
			}
			if (s.equalsIgnoreCase("auto")) {
				cameraParameters.setFlashMode(Parameters.FLASH_MODE_AUTO);
			}

			break;
		case RESOLUTION:
			int height;
			int width;
			if (isActive()) {

				s = optionList.get(active);
				String[] temp = s.split("x");
				width = Integer.parseInt(temp[0]);
				height = Integer.parseInt(temp[1]);

				cameraParameters.setPictureSize(width, height);
				if (cameraParameters.getSupportedPreviewSizes()
						.contains(
								cameraParameters.getSupportedPictureSizes()
										.get(active))) {
					cameraParameters.setPreviewSize(width, height);
				}
			}
			break;
		}
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCurrentValue() {
		return this.optionList.get(active);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getActive() {
		return active;
	}
}
