package com.example.test13_1;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> strings = new ArrayList<>();
        aa();
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        while(cursor.moveToNext()){
            int index = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            String contract = cursor.getColumnName(index);
            int id = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            String contract_id = cursor.getString(id);
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"="+contract_id,null,null);
            while(phone.moveToNext()){
                String pNo = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                strings.add(contract + ": " + pNo);
            }
        }
        cursor.close();
        Toast.makeText(this,"asca",Toast.LENGTH_SHORT).show();
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,strings));
    }
    private void aa(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.READ_CONTACTS);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS},REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }
    }
}
