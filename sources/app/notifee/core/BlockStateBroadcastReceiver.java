package app.notifee.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import app.notifee.core.event.BlockStateEvent;
import app.notifee.core.interfaces.MethodCallResult;
import java.util.concurrent.TimeUnit;
import n.o.t.i.f.e.e.e;
import n.o.t.i.f.e.e.f;
import n.o.t.i.f.e.e.l;

/* loaded from: classes5.dex */
public class BlockStateBroadcastReceiver extends BroadcastReceiver {
    public static void a(Data data, final CallbackToFutureAdapter.Completer<ListenableWorker.Result> completer) {
        Logger.v("BlockState", "starting background work");
        final boolean z = data.getBoolean("blocked", false);
        final int i = data.getInt("type", 4);
        final l.a aVar = new l.a() { // from class: app.notifee.core.BlockStateBroadcastReceiver$$ExternalSyntheticLambda0
            @Override // n.o.t.i.f.e.e.l.a
            public final void a(Object obj) {
                Bundle bundle = (Bundle) obj;
                f.a(new BlockStateEvent(i, bundle, z, new MethodCallResult() { // from class: app.notifee.core.BlockStateBroadcastReceiver$$ExternalSyntheticLambda2
                    @Override // app.notifee.core.interfaces.MethodCallResult
                    public final void onComplete(Exception exc, Object obj2) {
                        BlockStateBroadcastReceiver.a(completer, exc, (Void) obj2);
                    }
                }));
            }
        };
        if (i == 4) {
            aVar.a(null);
            return;
        }
        MethodCallResult<Bundle> methodCallResult = new MethodCallResult() { // from class: app.notifee.core.BlockStateBroadcastReceiver$$ExternalSyntheticLambda1
            @Override // app.notifee.core.interfaces.MethodCallResult
            public final void onComplete(Exception exc, Object obj) {
                BlockStateBroadcastReceiver.a(completer, aVar, exc, (Bundle) obj);
            }
        };
        String string = data.getString("channelOrGroupId");
        if (i == 5) {
            Notifee.getInstance().getChannel(string, methodCallResult);
        } else if (i == 6) {
            Notifee.getInstance().getChannelGroup(string, methodCallResult);
        } else {
            Logger.e("BlockState", "unknown block state work type");
            completer.set(ListenableWorker.Result.success());
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action;
        Data.Builder builder;
        if (Build.VERSION.SDK_INT >= 28 && (action = intent.getAction()) != null) {
            if (e.f305a == null) {
                e.a(context.getApplicationContext());
            }
            builder = new Data.Builder();
            builder.putString("workType", "app.notifee.core.BlockStateBroadcastReceiver.WORKER");
            action.hashCode();
            switch (action) {
                case "android.app.action.APP_BLOCK_STATE_CHANGED":
                    builder.putInt("type", 4);
                    break;
                case "android.app.action.NOTIFICATION_CHANNEL_GROUP_BLOCK_STATE_CHANGED":
                    builder.putInt("type", 6);
                    String stringExtra = intent.getStringExtra("android.app.extra.NOTIFICATION_CHANNEL_GROUP_ID");
                    builder.putString("channelOrGroupId", stringExtra);
                    action = action + "." + stringExtra;
                    break;
                case "android.app.action.NOTIFICATION_CHANNEL_BLOCK_STATE_CHANGED":
                    builder.putInt("type", 5);
                    String stringExtra2 = intent.getStringExtra("android.app.extra.NOTIFICATION_CHANNEL_ID");
                    builder.putString("channelOrGroupId", stringExtra2);
                    action = action + "." + stringExtra2;
                    break;
                default:
                    Logger.d("BlockState", "unknown intent action received, ignoring.");
                    return;
            }
            builder.putBoolean("blocked", intent.getBooleanExtra("android.app.extra.BLOCKED_STATE", false));
            try {
                WorkManager.getInstance(e.f305a).enqueueUniqueWork(action, ExistingWorkPolicy.REPLACE, new OneTimeWorkRequest.Builder(Worker.class).setInitialDelay(1L, TimeUnit.SECONDS).setInputData(builder.build()).build());
            } catch (IllegalStateException e) {
                Logger.e("BlockState", "Error while calling WorkManager.getInstance", (Exception) e);
                if (e.f305a == null) {
                    Logger.e("BlockState", "Application Context is null");
                }
            }
            Logger.v("BlockState", "scheduled new background work with id " + action);
        }
    }

    public static /* synthetic */ void a(CallbackToFutureAdapter.Completer completer, Exception exc, Void r3) {
        if (exc != null) {
            Logger.e("BlockState", "background work failed with error: ", exc);
            completer.set(ListenableWorker.Result.failure());
        } else {
            Logger.v("BlockState", "background work completed successfully");
            completer.set(ListenableWorker.Result.success());
        }
    }

    public static /* synthetic */ void a(CallbackToFutureAdapter.Completer completer, l.a aVar, Exception exc, Bundle bundle) {
        if (exc != null) {
            Logger.e("BlockState", "Failed getting channel or channel group bundle, received error: ", exc);
            completer.set(ListenableWorker.Result.success());
        } else {
            aVar.a(bundle);
        }
    }
}
