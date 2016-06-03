package com.allwinner.presentationdemo;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PresentationActivity extends AppCompatActivity {

    private final String TAG = "PresentationActivity";

    private Button btnPre;
    private Button btnNext;
    private ImageView img;
    private ImageView mFullScreenImage;
    private int position = 0;
    private DisplayManager mDisplayManager;
    private Display[] displays;

    private PresentationDemo mPresentation;

    private int photoId[] = {R.mipmap.test1,R.mipmap.test2,R.mipmap.test3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation_demo);
        mDisplayManager     = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
        initView();
        setDefaultImage();
        displays = mDisplayManager.getDisplays( DisplayManager.DISPLAY_CATEGORY_PRESENTATION );
        show();
    }

    private void initView(){
        btnPre  = (Button) findViewById(R.id.pre);
        btnNext = (Button) findViewById(R.id.next);
        img     = (ImageView) findViewById(R.id.preview_img);
    }

    private void setDefaultImage(){
        img.setImageResource(photoId[position]);
        if(mFullScreenImage != null){
            mFullScreenImage.setImageResource(photoId[position]);
        }
    }

    private void show(){
        Log.e(TAG,"00000000000000000000000>>>"+displays.length);
        for (int i = 0; i < displays.length; i++){
            final Display display = displays[i];
            if (display != null)
                showPresentation(display);
        }
    }

    private void showPresentation(Display display){
        mPresentation = new PresentationDemo(this, display);
        mPresentation.show();
        mFullScreenImage = mPresentation.getImageView();
    }

    private void hidePresentation(Display display){

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.pre:
                if(position > 0) {
                    position--;
                    setDefaultImage();
                }
                break;
            case R.id.next:
                if(position < 2) {
                    position++;
                    setDefaultImage();
                }
                break;
        }
    }
}
