package app.maikol.catcam.components;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import app.maikol.catcam.R;

/**
 * Created by Miguel on 19/05/13.
 */
    public class CustomHorizontalScroll extends HorizontalScrollView {

        Context myContext;
        ArrayList<File> itemList = new ArrayList<File>();
        LinearLayout internalWrapper;

        public CustomHorizontalScroll(Context context) {
            super(context);
            myContext = context;

            internalWrapper = new LinearLayout(getContext());
            internalWrapper.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            internalWrapper.setOrientation(LinearLayout.HORIZONTAL);
            addView(internalWrapper);
        }

        public CustomHorizontalScroll(Context context, AttributeSet attrs) {
            super(context, attrs);
            myContext = context;

            internalWrapper = new LinearLayout(getContext());
            internalWrapper.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            internalWrapper.setOrientation(LinearLayout.HORIZONTAL);
            addView(internalWrapper);
        }




        public void add(File file){
            int newIdx = itemList.size();
            itemList.add(file);
            getImageView(newIdx);
//            internalWrapper.addView(getImageView(newIdx));
        }

        ImageView getImageView(int i){
            Bitmap bm = null;
            if (i < itemList.size()){
                bm = decodeSampledBitmapFromUri(itemList.get(i).getAbsolutePath(), 220, 220);
            }

            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            inflater.inflate(R.layout.gallery_item,internalWrapper);

            final ImageView photoImageView = (ImageView) this.findViewById(R.id.imageView);
            final TextView labelView= (TextView) this.findViewById(R.id.textView);
            labelView.setText("PRUEBA");


            final String imagePath= itemList.get(i).getAbsolutePath();


//            final ImageView imageView = new ImageView(myContext);
//            imageView.setLayoutParams(new LayoutParams(220, 220));
            photoImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LayoutParams layoutParams = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10,0,10,0);
            photoImageView.setLayoutParams(layoutParams);
            photoImageView.setImageBitmap(bm);

//            internalWrapper.addView(photoImageView);
//            internalWrapper.addView(labelView);



            photoImageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    String ExternalStorageDirectoryPath = Environment
                            .getExternalStorageDirectory()
                            .getAbsolutePath();
                    Log.d("Catcam", imagePath);
                    intent.setDataAndType(Uri.parse("file://" +imagePath), "image/*");
                    CustomHorizontalScroll.this.myContext.startActivity(intent);
                }
            });

            return photoImageView;
        }

        public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth, int reqHeight) {
            Bitmap bm = null;

            // First decode with inJustDecodeBounds=true to check dimensions
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            bm = BitmapFactory.decodeFile(path, options);

            return bm;
        }

        public int calculateInSampleSize(

                BitmapFactory.Options options, int reqWidth, int reqHeight) {
            // Raw height and width of image
            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;

            if (height > reqHeight || width > reqWidth) {
                if (width > height) {
                    inSampleSize = Math.round((float)height / (float)reqHeight);
                } else {
                    inSampleSize = Math.round((float)width / (float)reqWidth);
                }
            }

            return inSampleSize;
        }

    }

