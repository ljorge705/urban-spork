package com.asterinet.react.bgactions;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;

/* loaded from: classes5.dex */
public final class RNBackgroundActionsTask extends HeadlessJsTaskService {
    private static final String CHANNEL_ID = "RN_BACKGROUND_ACTIONS_CHANNEL";
    public static final int SERVICE_NOTIFICATION_ID = 92901;

    public static Notification buildNotification(Context context, BackgroundTaskOptions backgroundTaskOptions) {
        Intent intentAddCategory;
        PendingIntent activity;
        String taskTitle = backgroundTaskOptions.getTaskTitle();
        String taskDesc = backgroundTaskOptions.getTaskDesc();
        int iconInt = backgroundTaskOptions.getIconInt();
        int color = backgroundTaskOptions.getColor();
        String linkingURI = backgroundTaskOptions.getLinkingURI();
        if (linkingURI != null) {
            intentAddCategory = new Intent("android.intent.action.VIEW", Uri.parse(linkingURI));
        } else {
            intentAddCategory = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER");
        }
        if (Build.VERSION.SDK_INT >= 31) {
            activity = PendingIntent.getActivity(context, 0, intentAddCategory, 33554432);
        } else {
            activity = PendingIntent.getActivity(context, 0, intentAddCategory, 201326592);
        }
        NotificationCompat.Builder color2 = new NotificationCompat.Builder(context, CHANNEL_ID).setContentTitle(taskTitle).setContentText(taskDesc).setSmallIcon(iconInt).setContentIntent(activity).setOngoing(true).setPriority(-2).setColor(color);
        Bundle progressBar = backgroundTaskOptions.getProgressBar();
        if (progressBar != null) {
            color2.setProgress((int) Math.floor(progressBar.getDouble("max")), (int) Math.floor(progressBar.getDouble("value")), progressBar.getBoolean("indeterminate"));
        }
        return color2.build();
    }

    @Override // com.facebook.react.HeadlessJsTaskService
    protected HeadlessJsTaskConfig getTaskConfig(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            return new HeadlessJsTaskConfig(extras.getString("taskName"), Arguments.fromBundle(extras), 0L, true);
        }
        return null;
    }

    @Override // com.facebook.react.HeadlessJsTaskService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    private void createNotificationChannel(String str, String str2) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, str, 2);
            notificationChannel.setDescription(str2);
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
    }
}
