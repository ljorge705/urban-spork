package io.invertase.firebase.messaging;

import com.clevertap.android.sdk.Constants;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.messaging.RemoteMessage;
import io.invertase.firebase.common.ReactNativeFirebaseEvent;
import io.invertase.firebase.common.SharedUtils;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.Map;

/* loaded from: classes6.dex */
public class ReactNativeFirebaseMessagingSerializer {
    private static final String EVENT_MESSAGES_DELETED = "messaging_message_deleted";
    private static final String EVENT_MESSAGE_RECEIVED = "messaging_message_received";
    private static final String EVENT_MESSAGE_SEND_ERROR = "messaging_message_send_error";
    private static final String EVENT_MESSAGE_SENT = "messaging_message_sent";
    private static final String EVENT_NEW_TOKEN = "messaging_token_refresh";
    private static final String EVENT_NOTIFICATION_OPENED = "messaging_notification_opened";
    private static final String KEY_COLLAPSE_KEY = "collapseKey";
    private static final String KEY_DATA = "data";
    private static final String KEY_ERROR = "error";
    private static final String KEY_FROM = "from";
    private static final String KEY_MESSAGE_ID = "messageId";
    private static final String KEY_MESSAGE_TYPE = "messageType";
    private static final String KEY_SENT_TIME = "sentTime";
    private static final String KEY_TO = "to";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_TTL = "ttl";

    public static ReactNativeFirebaseEvent messagesDeletedToEvent() {
        return new ReactNativeFirebaseEvent(EVENT_MESSAGES_DELETED, Arguments.createMap());
    }

    public static ReactNativeFirebaseEvent messageSentToEvent(String str) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString(KEY_MESSAGE_ID, str);
        return new ReactNativeFirebaseEvent(EVENT_MESSAGE_SENT, writableMapCreateMap);
    }

    public static ReactNativeFirebaseEvent messageSendErrorToEvent(String str, Exception exc) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString(KEY_MESSAGE_ID, str);
        writableMapCreateMap.putMap("error", SharedUtils.getExceptionMap(exc));
        return new ReactNativeFirebaseEvent(EVENT_MESSAGE_SEND_ERROR, writableMapCreateMap);
    }

    public static ReactNativeFirebaseEvent remoteMessageToEvent(RemoteMessage remoteMessage, Boolean bool) {
        return new ReactNativeFirebaseEvent(bool.booleanValue() ? EVENT_NOTIFICATION_OPENED : EVENT_MESSAGE_RECEIVED, remoteMessageToWritableMap(remoteMessage));
    }

    public static ReactNativeFirebaseEvent remoteMessageMapToEvent(WritableMap writableMap, Boolean bool) {
        return new ReactNativeFirebaseEvent(bool.booleanValue() ? EVENT_NOTIFICATION_OPENED : EVENT_MESSAGE_RECEIVED, writableMap);
    }

    public static ReactNativeFirebaseEvent newTokenToTokenEvent(String str) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("token", str);
        return new ReactNativeFirebaseEvent(EVENT_NEW_TOKEN, writableMapCreateMap);
    }

    static WritableMap remoteMessageToWritableMap(RemoteMessage remoteMessage) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        WritableMap writableMapCreateMap2 = Arguments.createMap();
        if (remoteMessage.getCollapseKey() != null) {
            writableMapCreateMap.putString(KEY_COLLAPSE_KEY, remoteMessage.getCollapseKey());
        }
        if (remoteMessage.getFrom() != null) {
            writableMapCreateMap.putString("from", remoteMessage.getFrom());
        }
        if (remoteMessage.getTo() != null) {
            writableMapCreateMap.putString(KEY_TO, remoteMessage.getTo());
        }
        if (remoteMessage.getMessageId() != null) {
            writableMapCreateMap.putString(KEY_MESSAGE_ID, remoteMessage.getMessageId());
        }
        if (remoteMessage.getMessageType() != null) {
            writableMapCreateMap.putString(KEY_MESSAGE_TYPE, remoteMessage.getMessageType());
        }
        if (remoteMessage.getData().size() > 0) {
            for (Map.Entry<String, String> entry : remoteMessage.getData().entrySet()) {
                writableMapCreateMap2.putString(entry.getKey(), entry.getValue());
            }
        }
        writableMapCreateMap.putMap("data", writableMapCreateMap2);
        writableMapCreateMap.putDouble(KEY_TTL, remoteMessage.getTtl());
        writableMapCreateMap.putDouble(KEY_SENT_TIME, remoteMessage.getSentTime());
        if (remoteMessage.getNotification() != null) {
            writableMapCreateMap.putMap("notification", remoteMessageNotificationToWritableMap(remoteMessage.getNotification()));
        }
        return writableMapCreateMap;
    }

    static WritableMap remoteMessageNotificationToWritableMap(RemoteMessage.Notification notification) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        WritableMap writableMapCreateMap2 = Arguments.createMap();
        if (notification.getTitle() != null) {
            writableMapCreateMap.putString("title", notification.getTitle());
        }
        if (notification.getTitleLocalizationKey() != null) {
            writableMapCreateMap.putString("titleLocKey", notification.getTitleLocalizationKey());
        }
        if (notification.getTitleLocalizationArgs() != null) {
            writableMapCreateMap.putArray("titleLocArgs", Arguments.fromJavaArgs(notification.getTitleLocalizationArgs()));
        }
        if (notification.getBody() != null) {
            writableMapCreateMap.putString("body", notification.getBody());
        }
        if (notification.getBodyLocalizationKey() != null) {
            writableMapCreateMap.putString("bodyLocKey", notification.getBodyLocalizationKey());
        }
        if (notification.getBodyLocalizationArgs() != null) {
            writableMapCreateMap.putArray("bodyLocArgs", Arguments.fromJavaArgs(notification.getBodyLocalizationArgs()));
        }
        if (notification.getChannelId() != null) {
            writableMapCreateMap2.putString("channelId", notification.getChannelId());
        }
        if (notification.getClickAction() != null) {
            writableMapCreateMap2.putString("clickAction", notification.getClickAction());
        }
        if (notification.getColor() != null) {
            writableMapCreateMap2.putString("color", notification.getColor());
        }
        if (notification.getIcon() != null) {
            writableMapCreateMap2.putString("smallIcon", notification.getIcon());
        }
        if (notification.getImageUrl() != null) {
            writableMapCreateMap2.putString("imageUrl", notification.getImageUrl().toString());
        }
        if (notification.getLink() != null) {
            writableMapCreateMap2.putString(DynamicLink.Builder.KEY_LINK, notification.getLink().toString());
        }
        if (notification.getNotificationCount() != null) {
            writableMapCreateMap2.putInt("count", notification.getNotificationCount().intValue());
        }
        if (notification.getNotificationPriority() != null) {
            writableMapCreateMap2.putInt("priority", notification.getNotificationPriority().intValue());
        }
        if (notification.getSound() != null) {
            writableMapCreateMap2.putString("sound", notification.getSound());
        }
        if (notification.getTicker() != null) {
            writableMapCreateMap2.putString("ticker", notification.getTicker());
        }
        if (notification.getVisibility() != null) {
            writableMapCreateMap2.putInt(ViewHierarchyNode.JsonKeys.VISIBILITY, notification.getVisibility().intValue());
        }
        writableMapCreateMap.putMap(Constants.KEY_ANDROID, writableMapCreateMap2);
        return writableMapCreateMap;
    }

    static RemoteMessage remoteMessageFromReadableMap(ReadableMap readableMap) {
        RemoteMessage.Builder builder = new RemoteMessage.Builder(readableMap.getString(KEY_TO));
        if (readableMap.hasKey(KEY_TTL)) {
            builder.setTtl(readableMap.getInt(KEY_TTL));
        }
        if (readableMap.hasKey(KEY_MESSAGE_ID)) {
            builder.setMessageId(readableMap.getString(KEY_MESSAGE_ID));
        }
        if (readableMap.hasKey(KEY_MESSAGE_TYPE)) {
            builder.setMessageType(readableMap.getString(KEY_MESSAGE_TYPE));
        }
        if (readableMap.hasKey(KEY_COLLAPSE_KEY)) {
            builder.setCollapseKey(readableMap.getString(KEY_COLLAPSE_KEY));
        }
        if (readableMap.hasKey("data")) {
            ReadableMap map = readableMap.getMap("data");
            ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = map.keySetIterator();
            while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
                String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
                builder.addData(strNextKey, map.getString(strNextKey));
            }
        }
        return builder.build();
    }
}
