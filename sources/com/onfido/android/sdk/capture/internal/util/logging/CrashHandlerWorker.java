package com.onfido.android.sdk.capture.internal.util.logging;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker;
import com.onfido.android.sdk.capture.internal.config.DefaultOnfidoRemoteConfig;
import com.onfido.android.sdk.capture.ui.CrashHandlerService;
import com.onfido.android.sdk.capture.ui.options.FlowAction;
import com.onfido.android.sdk.capture.ui.options.FlowStep;
import com.onfido.api.client.JsonParserFactoryKt;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KTypeProjection;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u001e\u0010\u0007\u001a\u00020\b8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u00108\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/logging/CrashHandlerWorker;", "Landroidx/work/Worker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "flowTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/FlowTracker;", "getFlowTracker$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/FlowTracker;", "setFlowTracker$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/FlowTracker;)V", "json", "Lkotlinx/serialization/json/Json;", "remoteLoggerTree", "Lcom/onfido/android/sdk/capture/internal/util/logging/RemoteLoggerTree;", "getRemoteLoggerTree$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/util/logging/RemoteLoggerTree;", "setRemoteLoggerTree$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/util/logging/RemoteLoggerTree;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CrashHandlerWorker extends Worker {

    @Inject
    public FlowTracker flowTracker;
    private final Json json;

    @Inject
    public RemoteLoggerTree remoteLoggerTree;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CrashHandlerWorker(Context appContext, WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.json = JsonParserFactoryKt.getJsonParserInstance();
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        boolean z = getInputData().getBoolean(CrashHandlerService.LOGGER_ENABLED, true);
        boolean z2 = getInputData().getBoolean(CrashHandlerService.ESSENTIAL_ANALYTICS_ENABLED, false);
        String[] stringArray = getInputData().getStringArray(CrashHandlerService.LOGGER_ERROR_LEVELS);
        if (stringArray == null) {
            stringArray = new String[0];
        }
        DefaultOnfidoRemoteConfig defaultOnfidoRemoteConfig = DefaultOnfidoRemoteConfig.INSTANCE;
        defaultOnfidoRemoteConfig.setLoggerConfiguration(new SdkConfiguration.LoggerConfiguration(z, (List) null, ArraysKt.toList(stringArray), 2, (DefaultConstructorMarker) null));
        defaultOnfidoRemoteConfig.setInhouseAnalyticsEnabled(z2);
        String string = getInputData().getString("token");
        if (string == null) {
            string = "";
        }
        Json json = this.json;
        String string2 = getInputData().getString(CrashHandlerService.FLOW_STEPS);
        if (string2 == null) {
            string2 = "[]";
        }
        Iterable iterable = (Iterable) json.decodeFromString(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(List.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(FlowAction.class)))), string2);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(new FlowStep((FlowAction) it.next()));
        }
        String string3 = getInputData().getString(CrashHandlerService.EXCEPTION_MESSAGE);
        String string4 = getInputData().getString(CrashHandlerService.EXCEPTION_STACK_TRACE);
        OnfidoConfig.Companion companion = OnfidoConfig.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        OnfidoConfig onfidoConfigBuild = OnfidoConfig.Builder.withSDKToken$default(companion.builder(applicationContext), string, null, 2, null).withCustomFlow((FlowStep[]) arrayList.toArray(new FlowStep[0])).build();
        SdkController.Companion companion2 = SdkController.INSTANCE;
        SdkController companion3 = companion2.getInstance();
        Context applicationContext2 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext(...)");
        companion3.getSdkComponent(applicationContext2, onfidoConfigBuild).inject$onfido_capture_sdk_core_release(this);
        getRemoteLoggerTree$onfido_capture_sdk_core_release().log(6, CrashHandlerWorker.class.getName(), string3 == null ? "" : string3, new Throwable(string4));
        getFlowTracker$onfido_capture_sdk_core_release().trackFlowInterruptedWithError$onfido_capture_sdk_core_release(string3 != null ? string3 : "");
        companion2.getInstance().onDestroy();
        ListenableWorker.Result resultSuccess = ListenableWorker.Result.success();
        Intrinsics.checkNotNullExpressionValue(resultSuccess, "success(...)");
        return resultSuccess;
    }

    public final FlowTracker getFlowTracker$onfido_capture_sdk_core_release() {
        FlowTracker flowTracker = this.flowTracker;
        if (flowTracker != null) {
            return flowTracker;
        }
        Intrinsics.throwUninitializedPropertyAccessException("flowTracker");
        return null;
    }

    public final RemoteLoggerTree getRemoteLoggerTree$onfido_capture_sdk_core_release() {
        RemoteLoggerTree remoteLoggerTree = this.remoteLoggerTree;
        if (remoteLoggerTree != null) {
            return remoteLoggerTree;
        }
        Intrinsics.throwUninitializedPropertyAccessException("remoteLoggerTree");
        return null;
    }

    public final void setFlowTracker$onfido_capture_sdk_core_release(FlowTracker flowTracker) {
        Intrinsics.checkNotNullParameter(flowTracker, "<set-?>");
        this.flowTracker = flowTracker;
    }

    public final void setRemoteLoggerTree$onfido_capture_sdk_core_release(RemoteLoggerTree remoteLoggerTree) {
        Intrinsics.checkNotNullParameter(remoteLoggerTree, "<set-?>");
        this.remoteLoggerTree = remoteLoggerTree;
    }
}
