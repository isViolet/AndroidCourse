package com.example.test10_2;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PersonService {
    public static List<Person> getPersons(InputStream xml) throws IOException, XmlPullParserException {
        List<Person> persons = null;
        Person person = null;
        XmlPullParser pullParser = Xml.newPullParser();
        try {
            pullParser.setInput(xml, "UTF-8");
            int event = pullParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:
                        persons = new ArrayList<Person>();
                        break;
                    case XmlPullParser.START_TAG:
                        if ("person".equals(pullParser.getName())) {
                            int id = new Integer(pullParser.getAttributeValue(0));
                            person = new Person();
                            person.setId(id);
                        }
                        if ("name".equals(pullParser.getName())) {
                            String name = pullParser.nextText();
                            person.setName(name);
                            if ("age".equals(pullParser.getName())) {
                                String age = pullParser.nextText();
                            }
                        }
                        if("age".equals(pullParser.getName())){
                            String age = pullParser.nextText();
                            person.setAge(age);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("person".equals(pullParser.getName())){
                            persons.add(person);
                            person = null;
                        }
                        break;
                }
                event = pullParser.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return persons;
    }
}