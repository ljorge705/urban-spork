package io.invertase.firebase.storage;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import io.invertase.firebase.interfaces.NativeEvent;

/* loaded from: classes6.dex */
public class ReactNativeFirebaseStorageEvent implements NativeEvent {
    private static final String EVENT_DEFAULT = "storage_event";
    static final String EVENT_DOWNLOAD_FAILURE = "download_failure";
    static final String EVENT_DOWNLOAD_SUCCESS = "download_success";
    static final String EVENT_STATE_CHANGED = "state_changed";
    static final String EVENT_UPLOAD_FAILURE = "upload_failure";
    static final String EVENT_UPLOAD_SUCCESS = "upload_success";
    private static final String KEY_APP_NAME = "appName";
    private static final String KEY_BODY = "body";
    private static final String KEY_EVENT_NAME = "eventName";
    private static final String KEY_ID = "taskId";
    private String appName;
    private WritableMap eventBody;
    private String internalEventName;
    private int taskId;

    @Override // io.invertase.firebase.interfaces.NativeEvent
    public String getEventName() {
        return EVENT_DEFAULT;
    }

    @Override // io.invertase.firebase.interfaces.NativeEvent
    public String getFirebaseAppName() {
        return this.appName;
    }

    ReactNativeFirebaseStorageEvent(WritableMap writableMap, String str, String str2, int i) {
        this.eventBody = writableMap;
        this.internalEventName = str;
        this.appName = str2;
        this.taskId = i;
    }

    @Override // io.invertase.firebase.interfaces.NativeEvent
    public WritableMap getEventBody() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt(KEY_ID, this.taskId);
        writableMapCreateMap.putMap(KEY_BODY, this.eventBody);
        writableMapCreateMap.putString(KEY_APP_NAME, this.appName);
        writableMapCreateMap.putString("eventName", this.internalEventName);
        return writableMapCreateMap;
    }
}
