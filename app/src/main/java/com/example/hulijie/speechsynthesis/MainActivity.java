package com.example.hulijie.speechsynthesis;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import junit.framework.Assert;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        try {
            mp = new MediaPlayer();
            AssetFileDescriptor fileDescriptor = getAssets().openFd("hhwq.mp3");
            mp.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(),
                    fileDescriptor.getLength());
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

//开始播放
//        mp.start();

        try {
            Context context = getApplicationContext();

            InputStream fileDescriptor = context.getResources().getAssets().open("hhwq.mp3");//从外部assets获取MP3文件
            //存放于Data里的hhwq.mp3
//            FileInputStream fis = openFileInput("hhwq.mp3");//从data里获取mp3文件
            BufferedInputStream bis =new BufferedInputStream(fileDescriptor,10000);//转换缓冲流
            FileInputStream fis1 = openFileInput("mq.mp3");//从data里取mq.mp3文件
            BufferedInputStream bis1 =new BufferedInputStream(fis1,10000);//转换缓存流
        String fileName  = "j2222j.mp3";//输出文件名j2222j.mp3

            FileOutputStream fos = this.openFileOutput(fileName,
                    Context.MODE_APPEND);// 添加在文件后面，存放位置data里
            BufferedOutputStream bos = new BufferedOutputStream(fos,10000);//缓冲刘
            byte input[] = new byte[10000];
            int count = 0;
            while (  bis.read(input) != -1)
            {
                bos.write(input);
                Log.i("tag", String.valueOf(input[1]));
                count++;
            }
            while (  bis1.read(input) != -1)
            {
                bos.write(input);
                Log.i("tag", String.valueOf(input[1]));
                count++;
            }


            System.out.println("读取了" + fileName);
            bis.close();
            fileDescriptor.close();
            bos.close();
            fos.close();
            System.out.println("读取了" + count);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
