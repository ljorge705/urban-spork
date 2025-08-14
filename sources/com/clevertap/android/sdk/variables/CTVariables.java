package com.clevertap.android.sdk.variables;

import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.variables.callbacks.FetchVariablesCallback;
import com.clevertap.android.sdk.variables.callbacks.VariablesChangedCallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CTVariables {
    private final VarCache varCache;
    private boolean hasVarsRequestCompleted = false;
    private boolean preRegisteredFilesDownloaded = false;
    private final List<VariablesChangedCallback> variablesChangedCallbacks = new ArrayList();
    private final List<VariablesChangedCallback> oneTimeVariablesChangedCallbacks = new ArrayList();
    private final List<VariablesChangedCallback> variablesChangedCallbacksNoDownloadsPending = new ArrayList();
    private final List<VariablesChangedCallback> oneTimeVariablesChangedCallbacksNoDownloadsPending = new ArrayList();

    static /* synthetic */ Unit lambda$init$1() {
        return null;
    }

    VarCache getVarCache() {
        return this.varCache;
    }

    public void setHasVarsRequestCompleted(boolean z) {
        this.hasVarsRequestCompleted = z;
    }

    private static void logD(String str) {
        Logger.d("variables", str);
    }

    public CTVariables(VarCache varCache) {
        this.varCache = varCache;
        varCache.setGlobalCallbacksRunnable(new Runnable() { // from class: com.clevertap.android.sdk.variables.CTVariables$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m4809lambda$new$0$comclevertapandroidsdkvariablesCTVariables();
            }
        });
    }

    /* renamed from: lambda$new$0$com-clevertap-android-sdk-variables-CTVariables, reason: not valid java name */
    /* synthetic */ void m4809lambda$new$0$comclevertapandroidsdkvariablesCTVariables() {
        synchronized (this.variablesChangedCallbacks) {
            Iterator<VariablesChangedCallback> it = this.variablesChangedCallbacks.iterator();
            while (it.hasNext()) {
                Utils.runOnUiThread(it.next());
            }
        }
        synchronized (this.oneTimeVariablesChangedCallbacks) {
            Iterator<VariablesChangedCallback> it2 = this.oneTimeVariablesChangedCallbacks.iterator();
            while (it2.hasNext()) {
                Utils.runOnUiThread(it2.next());
            }
            this.oneTimeVariablesChangedCallbacks.clear();
        }
    }

    public void init() {
        logD("init() called");
        this.varCache.loadDiffs(new Function0() { // from class: com.clevertap.android.sdk.variables.CTVariables$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CTVariables.lambda$init$1();
            }
        });
    }

    public void handleVariableResponse(JSONObject jSONObject, FetchVariablesCallback fetchVariablesCallback) {
        logD("handleVariableResponse() called with: response = [" + jSONObject + "]");
        if (jSONObject == null) {
            handleVariableResponseError(fetchVariablesCallback);
        } else {
            handleVariableResponseSuccess(jSONObject, fetchVariablesCallback);
        }
    }

    public void handleVariableResponseError(FetchVariablesCallback fetchVariablesCallback) {
        if (!hasVarsRequestCompleted().booleanValue()) {
            setHasVarsRequestCompleted(true);
            this.varCache.loadDiffsAndTriggerHandlers(new Function0() { // from class: com.clevertap.android.sdk.variables.CTVariables$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return this.f$0.m4807xa448b5d2();
                }
            });
        }
        if (fetchVariablesCallback != null) {
            fetchVariablesCallback.onVariablesFetched(false);
        }
    }

    /* renamed from: lambda$handleVariableResponseError$2$com-clevertap-android-sdk-variables-CTVariables, reason: not valid java name */
    /* synthetic */ Unit m4807xa448b5d2() {
        triggerGlobalFilesCallbacks();
        this.preRegisteredFilesDownloaded = true;
        return null;
    }

    private void handleVariableResponseSuccess(JSONObject jSONObject, FetchVariablesCallback fetchVariablesCallback) {
        setHasVarsRequestCompleted(true);
        this.varCache.updateDiffsAndTriggerHandlers(CTVariableUtils.convertFlatMapToNestedMaps(JsonUtil.mapFromJson(jSONObject)), new Function0() { // from class: com.clevertap.android.sdk.variables.CTVariables$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.m4808xdd83e18e();
            }
        });
        if (fetchVariablesCallback != null) {
            fetchVariablesCallback.onVariablesFetched(true);
        }
    }

    /* renamed from: lambda$handleVariableResponseSuccess$3$com-clevertap-android-sdk-variables-CTVariables, reason: not valid java name */
    /* synthetic */ Unit m4808xdd83e18e() {
        triggerGlobalFilesCallbacks();
        this.preRegisteredFilesDownloaded = true;
        return null;
    }

    private void triggerGlobalFilesCallbacks() {
        synchronized (this.variablesChangedCallbacksNoDownloadsPending) {
            Iterator<VariablesChangedCallback> it = this.variablesChangedCallbacksNoDownloadsPending.iterator();
            while (it.hasNext()) {
                Utils.runOnUiThread(it.next());
            }
        }
        synchronized (this.oneTimeVariablesChangedCallbacksNoDownloadsPending) {
            Iterator<VariablesChangedCallback> it2 = this.oneTimeVariablesChangedCallbacksNoDownloadsPending.iterator();
            while (it2.hasNext()) {
                Utils.runOnUiThread(it2.next());
            }
            this.oneTimeVariablesChangedCallbacksNoDownloadsPending.clear();
        }
    }

    public void clearUserContent() {
        logD("Clear user content in CTVariables");
        setHasVarsRequestCompleted(false);
        this.preRegisteredFilesDownloaded = false;
        this.varCache.clearUserContent();
    }

    public void addVariablesChangedCallback(VariablesChangedCallback variablesChangedCallback) {
        synchronized (this.variablesChangedCallbacks) {
            this.variablesChangedCallbacks.add(variablesChangedCallback);
        }
        if (this.hasVarsRequestCompleted) {
            variablesChangedCallback.variablesChanged();
        }
    }

    public void addOneTimeVariablesChangedCallback(VariablesChangedCallback variablesChangedCallback) {
        if (this.hasVarsRequestCompleted) {
            variablesChangedCallback.variablesChanged();
            return;
        }
        synchronized (this.oneTimeVariablesChangedCallbacks) {
            this.oneTimeVariablesChangedCallbacks.add(variablesChangedCallback);
        }
    }

    public void onVariablesChangedAndNoDownloadsPending(VariablesChangedCallback variablesChangedCallback) {
        synchronized (this.variablesChangedCallbacksNoDownloadsPending) {
            this.variablesChangedCallbacksNoDownloadsPending.add(variablesChangedCallback);
        }
        if (this.preRegisteredFilesDownloaded) {
            variablesChangedCallback.variablesChanged();
        }
    }

    public void onceVariablesChangedAndNoDownloadsPending(VariablesChangedCallback variablesChangedCallback) {
        if (this.preRegisteredFilesDownloaded) {
            variablesChangedCallback.variablesChanged();
            return;
        }
        synchronized (this.oneTimeVariablesChangedCallbacksNoDownloadsPending) {
            this.oneTimeVariablesChangedCallbacksNoDownloadsPending.add(variablesChangedCallback);
        }
    }

    public void removeVariablesChangedCallback(VariablesChangedCallback variablesChangedCallback) {
        synchronized (this.variablesChangedCallbacks) {
            this.variablesChangedCallbacks.remove(variablesChangedCallback);
        }
    }

    public void removeOneTimeVariablesChangedHandler(VariablesChangedCallback variablesChangedCallback) {
        synchronized (this.oneTimeVariablesChangedCallbacks) {
            this.oneTimeVariablesChangedCallbacks.remove(variablesChangedCallback);
        }
    }

    public void removeAllVariablesChangedCallbacks() {
        synchronized (this.variablesChangedCallbacks) {
            this.variablesChangedCallbacks.clear();
        }
    }

    public void removeAllOneTimeVariablesChangedCallbacks() {
        synchronized (this.oneTimeVariablesChangedCallbacks) {
            this.oneTimeVariablesChangedCallbacks.clear();
        }
    }

    public Boolean hasVarsRequestCompleted() {
        return Boolean.valueOf(this.hasVarsRequestCompleted);
    }
}
