package com.asterinet.react.bgactions;

import android.graphics.Color;
import android.os.Bundle;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import io.sentry.protocol.SentryStackFrame;

/* loaded from: classes5.dex */
public final class BackgroundTaskOptions {
    private final Bundle extras;

    public Bundle getExtras() {
        return this.extras;
    }

    public BackgroundTaskOptions(Bundle bundle) {
        this.extras = bundle;
    }

    public BackgroundTaskOptions(ReactContext reactContext, ReadableMap readableMap) {
        String packageName;
        Bundle bundle = Arguments.toBundle(readableMap);
        this.extras = bundle;
        if (bundle == null) {
            throw new IllegalArgumentException("Could not convert arguments to bundle");
        }
        try {
            if (readableMap.getString("taskTitle") == null) {
                throw new IllegalArgumentException();
            }
            try {
                if (readableMap.getString("taskDesc") == null) {
                    throw new IllegalArgumentException();
                }
                try {
                    ReadableMap map = readableMap.getMap("taskIcon");
                    if (map == null) {
                        throw new IllegalArgumentException();
                    }
                    String string = map.getString("name");
                    String string2 = map.getString("type");
                    try {
                        packageName = map.getString(SentryStackFrame.JsonKeys.PACKAGE);
                    } catch (Exception unused) {
                        packageName = reactContext.getPackageName();
                    }
                    if (packageName == null) {
                        throw new IllegalArgumentException();
                    }
                    int identifier = reactContext.getResources().getIdentifier(string, string2, packageName);
                    this.extras.putInt("iconInt", identifier);
                    if (identifier == 0) {
                        throw new IllegalArgumentException();
                    }
                    try {
                        this.extras.putInt("color", Color.parseColor(readableMap.getString("color")));
                    } catch (Exception unused2) {
                        this.extras.putInt("color", Color.parseColor("#ffffff"));
                    }
                } catch (Exception unused3) {
                    throw new IllegalArgumentException("Task icon not found");
                }
            } catch (Exception unused4) {
                throw new IllegalArgumentException("Task description cannot be null");
            }
        } catch (Exception unused5) {
            throw new IllegalArgumentException("Task title cannot be null");
        }
    }

    public String getTaskTitle() {
        return this.extras.getString("taskTitle", "");
    }

    public String getTaskDesc() {
        return this.extras.getString("taskDesc", "");
    }

    public int getIconInt() {
        return this.extras.getInt("iconInt");
    }

    public int getColor() {
        return this.extras.getInt("color");
    }

    public String getLinkingURI() {
        return this.extras.getString("linkingURI");
    }

    public Bundle getProgressBar() {
        return this.extras.getBundle("progressBar");
    }
}
