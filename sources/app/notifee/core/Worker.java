package app.notifee.core;

import android.content.Context;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes5.dex */
public class Worker extends ListenableWorker {

    /* renamed from: a, reason: collision with root package name */
    public CallbackToFutureAdapter.Completer<ListenableWorker.Result> f42a;

    public Worker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    @Override // androidx.work.ListenableWorker
    public void onStopped() {
        CallbackToFutureAdapter.Completer<ListenableWorker.Result> completer = this.f42a;
        if (completer != null) {
            completer.set(ListenableWorker.Result.failure());
        }
        this.f42a = null;
    }

    @Override // androidx.work.ListenableWorker
    public ListenableFuture<ListenableWorker.Result> startWork() {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: app.notifee.core.Worker$$ExternalSyntheticLambda0
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return this.f$0.a(completer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object a(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.f42a = completer;
        String string = getInputData().getString("workType");
        if (string == null) {
            Logger.d("Worker", "received task with no input key type.");
            completer.set(ListenableWorker.Result.success());
            return "Worker.startWork operation cancelled - no input.";
        }
        if (string.equals("app.notifee.core.BlockStateBroadcastReceiver.WORKER")) {
            Logger.d("Worker", "received task with type " + string);
            BlockStateBroadcastReceiver.a(getInputData(), completer);
            return "Worker.startWork operation created successfully.";
        }
        if (string.equals("app.notifee.core.NotificationManager.TRIGGER")) {
            c.a(getInputData(), (CallbackToFutureAdapter.Completer<ListenableWorker.Result>) completer);
            return "Worker.startWork operation created successfully.";
        }
        Logger.d("Worker", "unknown work type received: " + string);
        completer.set(ListenableWorker.Result.success());
        return "Worker.startWork operation cancelled - unknown work type.";
    }
}
