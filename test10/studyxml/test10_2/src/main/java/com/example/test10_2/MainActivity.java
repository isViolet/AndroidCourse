package com.example.test10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView show_person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show_person = findViewById(R.id.tv);
        button=(Button)findViewById(R.id.bt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream xml = this.getClass().getClassLoader().getResourceAsStream("assets/person.xml");
                List<Person> persons = null;
                try {
                    persons = PersonService.getPersons(xml);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
                for (Person person : persons) {
                    Log.v("tes", person.toString());
                    show_person.setText(show_person.getText().toString() + "\n" + person.toString());
                }
            }
        });
    }
}