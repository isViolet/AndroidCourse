package com.example.test13_2;

import android.content.Context;
import android.os.Environment;
import android.util.Xml;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

class Sms_Xml {
    //将短信保存在sd卡下的mes.xml文件下.
    public static void beifen_sms(List<SmsInfo> list, Context context) {
        try {
            XmlSerializer serial = Xml.newSerializer();
            File file = new File(Environment.getExternalStorageDirectory(), "mes.xml");
            FileOutputStream fi_out = new FileOutputStream(file);
//初始化序刊号器，指定xml数据写入到哪个文件以及骗码
            serial.setOutput(fi_out, "utf-8");
            serial.startDocument("utf-8", true);
            serial.startTag(null, "smss");
            for (SmsInfo info : list) {
                serial.startTag(null, "sms");
                serial.attribute(null, "id", info.getId() + "");

                serial.startTag(null, "body");
                serial.text(info.getBody());
                serial.endTag(null, "body");

                serial.startTag(null, "address");
                serial.text(info.getAddress());
                serial.endTag(null, "address");

                serial.startTag(null, "type");
                serial.text(info.getType()+"");
                serial.endTag(null, "type");

                serial.startTag(null, "date");
                serial.text(info.getDate()+"");
                serial.endTag(null, "date");

                serial.endTag(null,"sms");
            }
            serial.endTag(null,"smss");
            serial.endDocument();
            fi_out.close();
            Toast.makeText(context,"备份成功",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context,"文件不存在",Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(context,"备份失败",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context,"备份失败",Toast.LENGTH_SHORT).show();
        }
    }
}

