package com.example.software_engine.utils;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import static android.app.Activity.RESULT_OK;

public class SelectMediaUtil {

    private final int REQUEST_CODE_SELECT = 112;

    private String path;
    private Activity activity;

    private SelectType selectType = SelectType.video;

    public enum SelectType {
        audio(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, MediaStore.Audio.Media.DATA),
        video(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, MediaStore.Video.Media.DATA),
        image(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaStore.Images.Media.DATA);

        Uri externalContentUri;
        String data;

        SelectType(Uri externalContentUri, String data) {
            this.externalContentUri = externalContentUri;
            this.data = data;
        }
    }

    public void select(Activity activity, SelectType selectType) {
        this.activity = activity;
        this.selectType = selectType;
        Intent i = new Intent(Intent.ACTION_PICK, selectType.externalContentUri);
        activity.startActivityForResult(i, REQUEST_CODE_SELECT);
    }

    public String onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_SELECT && resultCode == RESULT_OK && null != data) {
            Uri uri = data.getData();
            String[] filePathColumn = {selectType.data};

            Cursor cursor = activity.getContentResolver().query(uri,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            path = cursor.getString(columnIndex);
            cursor.close();
        }

        return path;
    }

}


