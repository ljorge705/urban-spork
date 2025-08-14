package io.invertase.firebase.messaging;

import com.clevertap.android.sdk.Constants;
import com.facebook.react.bridge.WritableMap;
import com.google.firebase.messaging.RemoteMessage;
import io.invertase.firebase.common.UniversalFirebasePreferences;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ReactNativeFirebaseMessagingStoreImpl implements ReactNativeFirebaseMessagingStore {
    private static final int MAX_SIZE_NOTIFICATIONS = 100;
    private static final String S_KEY_ALL_NOTIFICATION_IDS = "all_notification_ids";
    private final String DELIMITER = Constants.SEPARATOR_COMMA;

    @Override // io.invertase.firebase.messaging.ReactNativeFirebaseMessagingStore
    public void storeFirebaseMessage(RemoteMessage remoteMessage) {
        try {
            String string = JsonConvert.reactToJSON(ReactNativeFirebaseMessagingSerializer.remoteMessageToWritableMap(remoteMessage)).toString();
            UniversalFirebasePreferences sharedInstance = UniversalFirebasePreferences.getSharedInstance();
            sharedInstance.setStringValue(remoteMessage.getMessageId(), string);
            String strRemoveRemoteMessage = sharedInstance.getStringValue(S_KEY_ALL_NOTIFICATION_IDS, "") + remoteMessage.getMessageId() + Constants.SEPARATOR_COMMA;
            List<String> listConvertToArray = convertToArray(strRemoveRemoteMessage);
            if (listConvertToArray.size() > 100) {
                String str = listConvertToArray.get(0);
                sharedInstance.remove(str);
                strRemoveRemoteMessage = removeRemoteMessage(str, strRemoveRemoteMessage);
            }
            sharedInstance.setStringValue(S_KEY_ALL_NOTIFICATION_IDS, strRemoveRemoteMessage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // io.invertase.firebase.messaging.ReactNativeFirebaseMessagingStore
    @Deprecated
    public RemoteMessage getFirebaseMessage(String str) {
        WritableMap firebaseMessageMap = getFirebaseMessageMap(str);
        if (firebaseMessageMap != null) {
            return ReactNativeFirebaseMessagingSerializer.remoteMessageFromReadableMap(firebaseMessageMap);
        }
        return null;
    }

    @Override // io.invertase.firebase.messaging.ReactNativeFirebaseMessagingStore
    public WritableMap getFirebaseMessageMap(String str) {
        String stringValue = UniversalFirebasePreferences.getSharedInstance().getStringValue(str, null);
        if (stringValue != null) {
            try {
                WritableMap writableMapJsonToReact = JsonConvert.jsonToReact(new JSONObject(stringValue));
                writableMapJsonToReact.putString("to", str);
                return writableMapJsonToReact;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override // io.invertase.firebase.messaging.ReactNativeFirebaseMessagingStore
    public void clearFirebaseMessage(String str) {
        UniversalFirebasePreferences sharedInstance = UniversalFirebasePreferences.getSharedInstance();
        sharedInstance.remove(str);
        String stringValue = sharedInstance.getStringValue(S_KEY_ALL_NOTIFICATION_IDS, "");
        if (stringValue.isEmpty()) {
            return;
        }
        sharedInstance.setStringValue(S_KEY_ALL_NOTIFICATION_IDS, removeRemoteMessage(str, stringValue));
    }

    private String removeRemoteMessage(String str, String str2) {
        return str2.replace(str + Constants.SEPARATOR_COMMA, "");
    }

    private List<String> convertToArray(String str) {
        return new ArrayList(Arrays.asList(str.split(Constants.SEPARATOR_COMMA)));
    }
}
