package app.maikol.catcam;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import java.io.File;

import app.maikol.catcam.components.CustomHorizontalScroll;

/**
 * Created by Miguel on 19/05/13.
 */
public class CustomGallery extends Activity {

    public CustomHorizontalScroll myHorizontalLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);

        myHorizontalLayout = (CustomHorizontalScroll)findViewById(R.id.scrollImages);

        String ExternalStorageDirectoryPath = Environment
                .getExternalStorageDirectory()
                .getAbsolutePath();

        String targetPath = ExternalStorageDirectoryPath + "/Catcam/";

        Toast.makeText(getApplicationContext(), targetPath, Toast.LENGTH_LONG).show();
        File targetDirector = new File(targetPath);

        File[] files = targetDirector.listFiles();
        for (File file : files){
            myHorizontalLayout.add(file.getAbsolutePath());
        }
    }
}
