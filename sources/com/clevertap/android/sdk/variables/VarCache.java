package com.clevertap.android.sdk.variables;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.StorageHelper;
import com.clevertap.android.sdk.inapp.data.CtCacheType;
import com.clevertap.android.sdk.inapp.images.FileResourceProvider;
import com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepoImpl;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenStorageFlowProcessor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.apache.commons.lang3.ClassUtils;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class VarCache {
    private final FileResourceProvider fileResourceProvider;
    private final FileResourcesRepoImpl fileResourcesRepoImpl;
    private final CleverTapInstanceConfig instanceConfig;
    private final Context variablesCtx;
    private final Map<String, Object> valuesFromClient = new HashMap();
    private final Map<String, Var<?>> vars = new ConcurrentHashMap();
    private final Map<String, String> defaultKinds = new HashMap();
    private Runnable globalCallbacksRunnable = null;
    private Map<String, Object> diffs = new HashMap();
    public Object merged = null;

    private static void log(String str) {
        Logger.d("variables", str);
    }

    private static void log(String str, Throwable th) {
        Logger.d("variables", str, th);
    }

    public VarCache(CleverTapInstanceConfig cleverTapInstanceConfig, Context context, FileResourcesRepoImpl fileResourcesRepoImpl, FileResourceProvider fileResourceProvider) {
        this.variablesCtx = context;
        this.instanceConfig = cleverTapInstanceConfig;
        this.fileResourcesRepoImpl = fileResourcesRepoImpl;
        this.fileResourceProvider = fileResourceProvider;
    }

    private void storeDataInCache(String str) {
        log("storeDataInCache() called with: data = [" + str + "]");
        try {
            StorageHelper.putString(this.variablesCtx, StorageHelper.storageKeyWithSuffix(this.instanceConfig, Constants.CACHED_VARIABLES_KEY), str);
        } catch (Throwable th) {
            log("storeDataInCache failed", th);
        }
    }

    private String loadDataFromCache() {
        String string = StorageHelper.getString(this.variablesCtx, StorageHelper.storageKeyWithSuffix(this.instanceConfig, Constants.CACHED_VARIABLES_KEY), BiometricTokenStorageFlowProcessor.EMPTY_REQUEST_OBJECT);
        log("VarCache loaded cache data:\n" + string);
        return string;
    }

    void mergeVariable(Var<?> var) {
        Object obj = this.merged;
        if (obj == null) {
            log("mergeVariable() called, but `merged` member is null.");
            return;
        }
        if (!(obj instanceof Map)) {
            log("mergeVariable() called, but `merged` member is not of Map type.");
            return;
        }
        String str = var.nameComponents()[0];
        Object obj2 = this.valuesFromClient.get(str);
        Map map = (Map) JsonUtil.uncheckedCast(this.merged);
        Object obj3 = map.get(str);
        if ("file".equals(var.kind())) {
            if (obj2 != null || obj3 == null) {
                return;
            }
        } else if (obj2 == null || obj2.equals(obj3)) {
            return;
        }
        map.put(str, CTVariableUtils.mergeHelper(obj2, obj3));
        StringBuilder sb = new StringBuilder(str);
        for (int i = 1; i < var.nameComponents().length; i++) {
            Var<?> var2 = this.vars.get(sb.toString());
            if (var2 != null) {
                var2.update();
            }
            sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR).append(var.nameComponents()[i]);
        }
    }

    public synchronized void registerVariable(Var<?> var) {
        log("registerVariable() called with: var = [" + var + "]");
        this.vars.put(var.name(), var);
        Object objDefaultValue = var.defaultValue();
        if (objDefaultValue instanceof Map) {
            objDefaultValue = CTVariableUtils.deepCopyMap((Map) JsonUtil.uncheckedCast(objDefaultValue));
        }
        CTVariableUtils.updateValuesAndKinds(var.name(), var.nameComponents(), objDefaultValue, var.kind(), this.valuesFromClient, this.defaultKinds);
        mergeVariable(var);
    }

    public synchronized Object getMergedValue(String str) {
        Var<?> var = this.vars.get(str);
        if (var != null && "file".equals(var.kind())) {
            return filePathFromDisk(var.stringValue);
        }
        Object mergedValueFromComponentArray = getMergedValueFromComponentArray(CTVariableUtils.getNameComponents(str));
        if (!(mergedValueFromComponentArray instanceof Map)) {
            return mergedValueFromComponentArray;
        }
        return CTVariableUtils.deepCopyMap((Map) JsonUtil.uncheckedCast(mergedValueFromComponentArray));
    }

    public synchronized <T> T getMergedValueFromComponentArray(Object[] objArr) {
        Object obj;
        obj = this.merged;
        if (obj == null) {
            obj = this.valuesFromClient;
        }
        return (T) getMergedValueFromComponentArray(objArr, obj);
    }

    public synchronized <T> T getMergedValueFromComponentArray(Object[] objArr, Object obj) {
        for (Object obj2 : objArr) {
            obj = CTVariableUtils.traverse(obj, obj2, false);
        }
        return (T) JsonUtil.uncheckedCast(obj);
    }

    public synchronized void loadDiffs(Function0<Unit> function0) {
        try {
            Map<String, Object> mapFromJson = JsonUtil.fromJson(loadDataFromCache());
            HashMap<String, Var<?>> map = new HashMap<>(this.vars);
            applyVariableDiffs(mapFromJson, map);
            startFilesDownload(map, function0);
        } catch (Exception e) {
            log("Could not load variable diffs.\n", e);
        }
    }

    public synchronized void loadDiffsAndTriggerHandlers(Function0<Unit> function0) {
        loadDiffs(function0);
        triggerGlobalCallbacks();
    }

    public synchronized void updateDiffsAndTriggerHandlers(Map<String, Object> map, Function0<Unit> function0) {
        HashMap<String, Var<?>> map2 = new HashMap<>(this.vars);
        applyVariableDiffs(map, map2);
        startFilesDownload(map2, function0);
        saveDiffsAsync();
        triggerGlobalCallbacks();
    }

    private void saveDiffsAsync() {
        CTExecutorFactory.executors(this.instanceConfig).postAsyncSafelyTask().execute("VarCache#saveDiffsAsync", new Callable() { // from class: com.clevertap.android.sdk.variables.VarCache$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.m4810x72eee58f();
            }
        });
    }

    /* renamed from: lambda$saveDiffsAsync$0$com-clevertap-android-sdk-variables-VarCache, reason: not valid java name */
    /* synthetic */ Void m4810x72eee58f() throws Exception {
        saveDiffs();
        return null;
    }

    private void saveDiffs() {
        log("saveDiffs() called");
        storeDataInCache(JsonUtil.toJson(this.diffs));
    }

    private void applyVariableDiffs(Map<String, Object> map, HashMap<String, Var<?>> map2) {
        log("applyVariableDiffs() called with: diffs = [" + map + "]");
        if (map != null) {
            this.diffs = map;
            this.merged = CTVariableUtils.mergeHelper(this.valuesFromClient, map);
            log("applyVariableDiffs: updated value of merged=[" + this.merged + "]");
            Iterator<Map.Entry<String, Var<?>>> it = map2.entrySet().iterator();
            while (it.hasNext()) {
                Var<?> var = this.vars.get(it.next().getKey());
                if (var != null) {
                    var.update();
                }
            }
        }
    }

    private void startFilesDownload(HashMap<String, Var<?>> map, final Function0<Unit> function0) {
        if (map.isEmpty()) {
            log("There are no variables registered by the client. Not downloading files & posting global callbacks");
            return;
        }
        StringBuilder sb = new StringBuilder("Skipped these file vars cause urls are not present :\n");
        StringBuilder sb2 = new StringBuilder("Adding these files to download :\n");
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<String, Var<?>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            String key = it.next().getKey();
            Var<?> var = this.vars.get(key);
            if (var != null && var.kind().equals("file")) {
                String strRawFileValue = var.rawFileValue();
                if (strRawFileValue != null) {
                    if (!this.fileResourceProvider.isFileCached(strRawFileValue)) {
                        arrayList.add(new Pair(strRawFileValue, CtCacheType.FILES));
                        sb2.append(key).append(" : ").append(strRawFileValue);
                        sb2.append("\n");
                    }
                } else {
                    sb.append(key);
                    sb.append("\n");
                }
            }
        }
        log(sb.toString());
        log(sb2.toString());
        if (arrayList.isEmpty()) {
            function0.invoke();
        } else {
            this.fileResourcesRepoImpl.preloadFilesAndCache(arrayList, new Function1() { // from class: com.clevertap.android.sdk.variables.VarCache$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VarCache.lambda$startFilesDownload$1(function0, (Map) obj);
                }
            });
        }
    }

    static /* synthetic */ Unit lambda$startFilesDownload$1(Function0 function0, Map map) {
        function0.invoke();
        return Unit.INSTANCE;
    }

    private synchronized void triggerGlobalCallbacks() {
        Runnable runnable = this.globalCallbacksRunnable;
        if (runnable != null) {
            runnable.run();
        }
    }

    public JSONObject getDefineVarsData() {
        return CTVariableUtils.getFlatVarsJson(this.valuesFromClient, this.defaultKinds);
    }

    public synchronized void clearUserContent() {
        log("Clear user content in VarCache");
        HashMap<String, Var<?>> map = new HashMap<>(this.vars);
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            Var<?> var = this.vars.get(it.next());
            if (var != null) {
                var.clearStartFlag();
            }
        }
        applyVariableDiffs(new HashMap(), map);
        saveDiffsAsync();
    }

    public synchronized <T> Var<T> getVariable(String str) {
        return (Var) JsonUtil.uncheckedCast(this.vars.get(str));
    }

    int getVariablesCount() {
        return this.vars.size();
    }

    public synchronized void setGlobalCallbacksRunnable(Runnable runnable) {
        this.globalCallbacksRunnable = runnable;
    }

    public String filePathFromDisk(String str) {
        return this.fileResourceProvider.cachedFilePath(str);
    }

    public void fileVarUpdated(final Var<String> var) {
        String strRawFileValue = var.rawFileValue();
        if (strRawFileValue == null || this.fileResourceProvider.isFileCached(strRawFileValue)) {
            var.triggerFileIsReady();
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair(strRawFileValue, CtCacheType.FILES));
        this.fileResourcesRepoImpl.preloadFilesAndCache(arrayList, new Function1() { // from class: com.clevertap.android.sdk.variables.VarCache$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VarCache.lambda$fileVarUpdated$2(var, (Map) obj);
            }
        });
    }

    static /* synthetic */ Unit lambda$fileVarUpdated$2(Var var, Map map) {
        var.triggerFileIsReady();
        return Unit.INSTANCE;
    }
}
