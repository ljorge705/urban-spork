package io.invertase.notifee;

import android.os.Bundle;
import app.notifee.core.event.BlockStateEvent;
import app.notifee.core.event.ForegroundServiceEvent;
import app.notifee.core.event.LogEvent;
import app.notifee.core.event.NotificationEvent;
import app.notifee.core.interfaces.EventListener;
import app.notifee.core.model.NotificationModel;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import io.invertase.notifee.NotifeeReactUtils;
import java.util.Objects;

/* loaded from: classes6.dex */
public class NotifeeEventSubscriber implements EventListener {
    static final String FOREGROUND_NOTIFICATION_TASK_KEY = "app.notifee.foreground-service-headless-task";
    private static final String KEY_BLOCKED = "blocked";
    private static final String KEY_DETAIL = "detail";
    private static final String KEY_DETAIL_INPUT = "input";
    private static final String KEY_DETAIL_PRESS_ACTION = "pressAction";
    private static final String KEY_HEADLESS = "headless";
    private static final String KEY_NOTIFICATION = "notification";
    private static final String KEY_TYPE = "type";
    static final String NOTIFICATION_EVENT_KEY = "app.notifee.notification-event";

    @Override // app.notifee.core.interfaces.EventListener
    public void onLogEvent(LogEvent logEvent) {
    }

    @Override // app.notifee.core.interfaces.EventListener
    public void onNotificationEvent(NotificationEvent notificationEvent) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        WritableMap writableMapCreateMap2 = Arguments.createMap();
        writableMapCreateMap.putInt("type", notificationEvent.getType());
        writableMapCreateMap2.putMap(KEY_NOTIFICATION, Arguments.fromBundle(notificationEvent.getNotification().toBundle()));
        Bundle extras = notificationEvent.getExtras();
        if (extras != null) {
            Bundle bundle = extras.getBundle(KEY_DETAIL_PRESS_ACTION);
            if (bundle != null) {
                writableMapCreateMap2.putMap(KEY_DETAIL_PRESS_ACTION, Arguments.fromBundle(bundle));
            }
            String string = extras.getString(KEY_DETAIL_INPUT);
            if (string != null) {
                writableMapCreateMap2.putString(KEY_DETAIL_INPUT, string);
            }
        }
        writableMapCreateMap.putMap(KEY_DETAIL, writableMapCreateMap2);
        if (NotifeeReactUtils.isAppInForeground()) {
            writableMapCreateMap.putBoolean(KEY_HEADLESS, false);
            NotifeeReactUtils.sendEvent(NOTIFICATION_EVENT_KEY, writableMapCreateMap);
        } else {
            writableMapCreateMap.putBoolean(KEY_HEADLESS, true);
            NotifeeReactUtils.startHeadlessTask(NOTIFICATION_EVENT_KEY, writableMapCreateMap, 60000L, null);
        }
    }

    @Override // app.notifee.core.interfaces.EventListener
    public void onBlockStateEvent(final BlockStateEvent blockStateEvent) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        WritableMap writableMapCreateMap2 = Arguments.createMap();
        writableMapCreateMap.putInt("type", blockStateEvent.getType());
        int type = blockStateEvent.getType();
        if (type == 5 || type == 6) {
            String str = type == 5 ? "channel" : "channelGroup";
            if (blockStateEvent.getChannelOrGroupBundle() != null) {
                writableMapCreateMap2.putMap(str, Arguments.fromBundle(blockStateEvent.getChannelOrGroupBundle()));
            }
        }
        if (type == 4) {
            writableMapCreateMap2.putBoolean(KEY_BLOCKED, blockStateEvent.isBlocked());
        }
        writableMapCreateMap.putMap(KEY_DETAIL, writableMapCreateMap2);
        if (NotifeeReactUtils.isAppInForeground()) {
            writableMapCreateMap.putBoolean(KEY_HEADLESS, false);
            NotifeeReactUtils.sendEvent(NOTIFICATION_EVENT_KEY, writableMapCreateMap);
        } else {
            writableMapCreateMap.putBoolean(KEY_HEADLESS, true);
            Objects.requireNonNull(blockStateEvent);
            NotifeeReactUtils.startHeadlessTask(NOTIFICATION_EVENT_KEY, writableMapCreateMap, 0L, new NotifeeReactUtils.GenericCallback() { // from class: io.invertase.notifee.NotifeeEventSubscriber$$ExternalSyntheticLambda1
                @Override // io.invertase.notifee.NotifeeReactUtils.GenericCallback
                public final void call() {
                    blockStateEvent.setCompletionResult();
                }
            });
        }
    }

    @Override // app.notifee.core.interfaces.EventListener
    public void onForegroundServiceEvent(final ForegroundServiceEvent foregroundServiceEvent) {
        NotificationModel notification = foregroundServiceEvent.getNotification();
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putMap(KEY_NOTIFICATION, Arguments.fromBundle(notification.toBundle()));
        Objects.requireNonNull(foregroundServiceEvent);
        NotifeeReactUtils.startHeadlessTask(FOREGROUND_NOTIFICATION_TASK_KEY, writableMapCreateMap, 0L, new NotifeeReactUtils.GenericCallback() { // from class: io.invertase.notifee.NotifeeEventSubscriber$$ExternalSyntheticLambda0
            @Override // io.invertase.notifee.NotifeeReactUtils.GenericCallback
            public final void call() {
                foregroundServiceEvent.setCompletionResult();
            }
        });
    }
}
