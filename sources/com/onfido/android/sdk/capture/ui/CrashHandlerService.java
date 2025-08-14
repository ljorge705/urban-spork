package com.onfido.android.sdk.capture.ui;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.internal.OnfidoConstants;
import com.onfido.android.sdk.capture.internal.util.logging.CrashHandlerWorker;
import com.onfido.android.sdk.capture.ui.options.FlowAction;
import com.onfido.android.sdk.capture.ui.options.FlowStep;
import com.onfido.api.client.JsonParserFactoryKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KTypeProjection;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0000\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\"\u0010\u0007\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/CrashHandlerService;", "Landroid/app/Service;", "()V", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onStartCommand", "", "flags", "startId", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CrashHandlerService extends Service {
    public static final String ESSENTIAL_ANALYTICS_ENABLED = "essential_analytics_enabled";
    public static final String EXCEPTION_MESSAGE = "exception_message";
    public static final String EXCEPTION_STACK_TRACE = "stack_trace";
    public static final String FLOW_STEPS = "flow_steps";
    public static final String LOGGER_ENABLED = "logger_enabled";
    public static final String LOGGER_ERROR_LEVELS = "logger_error_levels";
    public static final String TOKEN = "token";

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) throws Throwable {
        OnfidoConfig onfidoConfig = intent != null ? (OnfidoConfig) intent.getParcelableExtra(OnfidoConstants.ONFIDO_CONFIG) : null;
        OnfidoConfig onfidoConfig2 = onfidoConfig instanceof OnfidoConfig ? onfidoConfig : null;
        if (onfidoConfig2 == null) {
            return super.onStartCommand(intent, flags, startId);
        }
        String stringExtra = intent.getStringExtra(EXCEPTION_MESSAGE);
        String stringExtra2 = intent.getStringExtra(EXCEPTION_STACK_TRACE);
        boolean booleanExtra = intent.getBooleanExtra(ESSENTIAL_ANALYTICS_ENABLED, false);
        boolean booleanExtra2 = intent.getBooleanExtra(LOGGER_ENABLED, false);
        String[] stringArrayExtra = intent.getStringArrayExtra(LOGGER_ERROR_LEVELS);
        if (stringArrayExtra == null) {
            stringArrayExtra = new String[0];
        }
        String tokenValue = onfidoConfig2.getToken().getTokenValue();
        Json jsonParserInstance = JsonParserFactoryKt.getJsonParserInstance();
        List<FlowStep> flowSteps = onfidoConfig2.getFlowSteps();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(flowSteps, 10));
        Iterator<T> it = flowSteps.iterator();
        while (it.hasNext()) {
            arrayList.add(((FlowStep) it.next()).getAction());
        }
        Data dataBuild = new Data.Builder().putString("token", tokenValue).putString(FLOW_STEPS, jsonParserInstance.encodeToString(SerializersKt.serializer(jsonParserInstance.getSerializersModule(), Reflection.typeOf(List.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(FlowAction.class)))), arrayList)).putString(EXCEPTION_MESSAGE, stringExtra).putString(EXCEPTION_STACK_TRACE, stringExtra2).putBoolean(ESSENTIAL_ANALYTICS_ENABLED, booleanExtra).putBoolean(LOGGER_ENABLED, booleanExtra2).putStringArray(LOGGER_ERROR_LEVELS, stringArrayExtra).build();
        Intrinsics.checkNotNullExpressionValue(dataBuild, "build(...)");
        WorkManager.getInstance(getApplicationContext()).enqueue(new OneTimeWorkRequest.Builder(CrashHandlerWorker.class).setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()).setInputData(dataBuild).build());
        return super.onStartCommand(intent, flags, startId);
    }
}
