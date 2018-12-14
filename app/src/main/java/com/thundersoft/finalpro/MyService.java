package com.thundersoft.finalpro;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Random;

import static com.thundersoft.finalpro.MainActivity.PM;

public class MyService extends Service {
    NotificationManager notificationManager;
    Notification notification;
    int i=1;
    int count=0;
    MainActivity mainActivity;
    private PMBinder mBinder=new PMBinder();



    class PMBinder extends Binder{
        public void start(){

        }


    }

    public MyService() {
    }
    @Override
    public void onCreate(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel("fore_service", "前台服务", NotificationManager.IMPORTANCE_HIGH);
//            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//            notificationManager.createNotificationChannel(channel);
//            Intent intentForeSerive = new Intent(this, MainActivity.class);
//            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intentForeSerive, 0);
//            notification = new NotificationCompat.Builder(this, "fore_service")
//                .setContentTitle("空气质量")
//                .setContentText(String.valueOf(i))
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//                .setContentIntent(pendingIntent)
//                .build();
//            startForeground(1, notification);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("fore_service", "前台服务", NotificationManager.IMPORTANCE_HIGH);
            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
            Intent intentForeSerive = new Intent(MyService.this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0, intentForeSerive, 0);
            notification = new NotificationCompat.Builder(MyService.this, "fore_service")
                .setContentTitle("空气质量为")
                .setContentText(new String(String.valueOf(PM)))
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .build();
//            notificationManager.notify(1,notification);
        }
//        }

    }
    @Override

    public int onStartCommand(Intent intent,int flags ,int startId){

        starttime();
//        notificationManager.notify(1,notification);
        return super.onStartCommand(intent,flags,startId);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        stopSelf();
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    void starttime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
               while (true) {
                   i=PM;
                   if (i > 300) {
                       setNotification(i);
                       notificationManager.notify(1, notification);
                       Log.d("123", String.valueOf(i));
                   }
                   break;
               }
            }

        }
        ).start();
    }
    void setNotification(int Air){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        NotificationChannel channel = new NotificationChannel("fore_service", "前台服务", NotificationManager.IMPORTANCE_HIGH);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
        Intent intentForeSerive = new Intent(MyService.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0, intentForeSerive, 0);
        notification = new NotificationCompat.Builder(MyService.this, "fore_service")
            .setContentTitle("空气质量很差")
            .setContentText("当前为"+new String(String.valueOf(Air)))
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
            .setContentIntent(pendingIntent)
            .build();
    }
    }
}
