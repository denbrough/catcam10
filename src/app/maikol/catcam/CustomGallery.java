package app.maikol.catcam;

import app.maikol.catcam.model.Photo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import app.maikol.catcam.components.HorizontalListView;

/**
 * Created by Miguel on 19/05/13.
 */
public class CustomGallery extends Activity {

    public HorizontalListView myHorizontalListView;
    ProgressBar mProgressBar;

    List<Photo> photos = new ArrayList<Photo>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.gallery);
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.VISIBLE);

        myHorizontalListView = (HorizontalListView) findViewById(R.id.scrollImages);
//        myHorizontalListView = (CustomHorizontalScroll)findViewById(R.id.scrollImages);

        String ExternalStorageDirectoryPath = Environment
                .getExternalStorageDirectory()
                .getAbsolutePath();

        String targetPath = ExternalStorageDirectoryPath + "/Catcam/";
        File targetDirector = new File(targetPath);

        final File[] filesFromSD = targetDirector.listFiles();

        FileLoaderASync fileLoader = new FileLoaderASync();
        fileLoader.execute(filesFromSD);
    }

    private BaseAdapter mAdapter = new BaseAdapter() {

        @Override
        public int getCount() {
            return photos.size();
        }

        @Override
        public Photo getItem(int position) {
            return photos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final Photo photo = getItem(position);

            View retval = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, null);

            final ImageView imageView = (ImageView)retval.findViewById(R.id.imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//            Bitmap bm = decodeSampledBitmapFromUri(f.getAbsolutePath(), 220, 220);
            imageView.setImageBitmap(photo.bm);
            imageView.setOnLongClickListener(new View.OnLongClickListener() {

                public boolean onLongClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    String ExternalStorageDirectoryPath = Environment
                            .getExternalStorageDirectory()
                            .getAbsolutePath();
                    Log.d("Catcam", photo.file.getName());
                    intent.setDataAndType(Uri.parse("file://" + photo.file.getAbsolutePath()), "image/*");
                    CustomGallery.this.startActivity(intent);

                    return true;
                }
            });

            TextView title = (TextView) retval.findViewById(R.id.textView);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

            title.setText(sdf.format(photo.file.lastModified()));

            return retval;
        }





    };
    public class FileLoaderASync extends AsyncTask<File[], Void, Void> {


        @Override
        protected void onPreExecute() {
            //put a preloder
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(File[]... arg0) {
            // TODO Auto-generated method stub

            for (File file : arg0[0]){
                photos.add(new Photo(file));
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            myHorizontalListView.setAdapter(mAdapter);
            mProgressBar.setVisibility(View.GONE);

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
