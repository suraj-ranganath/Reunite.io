package com.gumnur.reuniteio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static com.gumnur.reuniteio.R.id.fabGallery;
import static com.gumnur.reuniteio.R.id.fabPhoto;

public class MainActivity extends AppCompatActivity {

    private boolean fabExpanded = false;
    private FloatingActionButton fabSettings;
    ImageView imageView;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    private LinearLayout layoutFabSave;
    private LinearLayout layoutFabEdit;
    private static final String TAG = "Log";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Log.i(TAG, "onCreate");

        final FloatingActionButton btnCamera =(FloatingActionButton) findViewById(fabPhoto);
        imageView =(ImageView)findViewById(R.id.imgView);
         final FloatingActionButton btnGallery =(FloatingActionButton) findViewById(fabGallery);

        layoutFabSave = (LinearLayout) this.findViewById(R.id.layoutFabSave);
        layoutFabEdit = (LinearLayout) this.findViewById(R.id.layoutFabEdit);
        fabSettings = (FloatingActionButton) this.findViewById(R.id.fabSetting);

        //layoutFabSettings = (LinearLayout) this.findViewById(R.id.layoutFabSettings);

        //When main Fab (Settings) is clicked, it expands if not expanded already.
        //Collapses if main FAB was open already.
        //This gives FAB (Settings) open/close behavior
        fabSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabExpanded == true){
                    closeSubMenusFab();
                } else {
                    openSubMenusFab();
                }
            }
        });


        //Only main FAB is visible in the beginning
        closeSubMenusFab();
    }

    //closes FAB submenus
    private void closeSubMenusFab(){
        layoutFabSave.setVisibility(View.INVISIBLE);
        layoutFabEdit.setVisibility(View.INVISIBLE);
        fabSettings.setImageResource(R.drawable.ic_add_black_24dp);
        fabExpanded = false;
    }

    //Opens FAB submenus
    private void openSubMenusFab(){
        layoutFabSave.setVisibility(View.VISIBLE);
        layoutFabEdit.setVisibility(View.VISIBLE);
        //Change settings icon to 'X' icon
        fabSettings.setImageResource(R.drawable.ic_clear_black_24dp);
        fabExpanded = true;
    }
    //Camera Button
        fabPhoto.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,0);
        }
    });

    //Gallery Button
        fabGallery.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            openGallery();

        }
    });


}
    private void openGallery(){
        Intent Gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(Gallery, PICK_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }

    }


    }
