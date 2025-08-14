package com.onfido.android.sdk.capture.ui;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.clevertap.android.sdk.leanplum.Constants;
import com.google.android.play.core.splitcompat.SplitCompat;
import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.Onfido;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.android.sdk.capture.common.preferences.StorageKey;
import com.onfido.android.sdk.capture.config.EnterpriseConfig;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.errors.OnfidoException;
import com.onfido.android.sdk.capture.internal.OnfidoConstants;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.config.DefaultOnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.performance.AggregatedPerformanceAnalytics;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.LoadingFragment;
import com.onfido.android.sdk.capture.utils.LocalizationUtil;
import com.onfido.api.client.JsonParserFactoryKt;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.javax.inject.Inject;
import java.lang.Thread;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;

@Metadata(d1 = {"\u0000\u009d\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0014\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H\u0014J\r\u00108\u001a\u000205H\u0000¢\u0006\u0002\b9J\u001f\u0010:\u001a\u0002052\u0006\u0010;\u001a\u00020<2\b\b\u0002\u0010=\u001a\u00020>H\u0010¢\u0006\u0002\b?J\u0012\u0010@\u001a\u0002052\b\u0010A\u001a\u0004\u0018\u00010BH\u0014J\b\u0010C\u001a\u000205H\u0014J\u0010\u0010D\u001a\u00020\n2\u0006\u0010E\u001a\u00020FH\u0016J\b\u0010G\u001a\u000205H\u0014J\b\u0010H\u001a\u000205H\u0014J\u0010\u0010I\u001a\u0002052\u0006\u0010J\u001a\u00020BH\u0014J\b\u0010K\u001a\u000205H\u0014J\r\u0010L\u001a\u000205H ¢\u0006\u0002\bMJ\r\u0010N\u001a\u00020\nH\u0010¢\u0006\u0002\bOJ\u0012\u0010P\u001a\u0002052\b\u0010Q\u001a\u0004\u0018\u00010RH\u0002J\u0012\u0010S\u001a\u0002052\b\u0010T\u001a\u0004\u0018\u00010UH\u0016J\u0012\u0010V\u001a\u0002052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002J\b\u0010W\u001a\u000205H\u0002J\b\u0010X\u001a\u00020\nH\u0002J\u0015\u0010Y\u001a\u0002052\u0006\u0010Z\u001a\u00020[H\u0000¢\u0006\u0002\b\\R\u001e\u0010\u0003\u001a\u00020\u00048\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000b\u0010\u0002\u001a\u0004\b\t\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u001e\u0010\u0016\u001a\u00020\u00178\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001e\u0010\u001c\u001a\u00020\u001d8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001e\u0010\"\u001a\u00020#8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020)X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010.\u001a\u00020/8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103¨\u0006]"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/BaseActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "flowTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/FlowTracker;", "getFlowTracker$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/FlowTracker;", "setFlowTracker$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/FlowTracker;)V", "isDarkModeEnabled", "", "isDarkModeEnabled$annotations", "()Z", "setDarkModeEnabled", "(Z)V", "jsonParser", "Lkotlinx/serialization/json/Json;", "getJsonParser", "()Lkotlinx/serialization/json/Json;", "mainProcessConnection", "com/onfido/android/sdk/capture/ui/BaseActivity$mainProcessConnection$1", "Lcom/onfido/android/sdk/capture/ui/BaseActivity$mainProcessConnection$1;", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "getOnfidoConfig$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/OnfidoConfig;", "setOnfidoConfig$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/OnfidoConfig;)V", "performanceAnalytics", "Lcom/onfido/android/sdk/capture/internal/performance/AggregatedPerformanceAnalytics;", "getPerformanceAnalytics$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/performance/AggregatedPerformanceAnalytics;", "setPerformanceAnalytics$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/performance/AggregatedPerformanceAnalytics;)V", "remoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "getRemoteConfig$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "setRemoteConfig$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "startingNewActivity", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getStartingNewActivity", "()Ljava/util/concurrent/atomic/AtomicBoolean;", KeychainModule.Maps.STORAGE, "Lcom/onfido/android/sdk/capture/common/preferences/SharedPreferencesDataSource;", "waitingScreenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;", "getWaitingScreenTracker$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;", "setWaitingScreenTracker$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;)V", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "dismissLoadingDialog", "dismissLoadingDialog$onfido_capture_sdk_core_release", "finishWithResult", OnfidoLauncher.KEY_RESULT, "", "intent", "Landroid/content/Intent;", "finishWithResult$onfido_capture_sdk_core_release", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onOptionsItemSelected", Constants.IAP_ITEM_PARAM, "Landroid/view/MenuItem;", "onPause", "onResume", "onSaveInstanceState", "outState", "onStop", "onStopDuringExitWhenSentToBackgroundMode", "onStopDuringExitWhenSentToBackgroundMode$onfido_capture_sdk_core_release", "onToolbarBackEvent", "onToolbarBackEvent$onfido_capture_sdk_core_release", "saveSelectedLocale", "locale", "Ljava/util/Locale;", "setSupportActionBar", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "setTheme", "setupCrashHandler", "shouldStartCrashHandlingService", "showLoadingDialog", "dialogMode", "Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode;", "showLoadingDialog$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    public FlowTracker flowTracker;
    private boolean isDarkModeEnabled;

    @Inject
    public OnfidoConfig onfidoConfig;

    @Inject
    public AggregatedPerformanceAnalytics performanceAnalytics;

    @Inject
    public OnfidoRemoteConfig remoteConfig;
    private SharedPreferencesDataSource storage;

    @Inject
    public WaitingScreenTracker waitingScreenTracker;
    private final Json jsonParser = JsonParserFactoryKt.getJsonParserInstance();
    private final AtomicBoolean startingNewActivity = new AtomicBoolean();
    private final BaseActivity$mainProcessConnection$1 mainProcessConnection = new ServiceConnection() { // from class: com.onfido.android.sdk.capture.ui.BaseActivity$mainProcessConnection$1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(service, "service");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            Intrinsics.checkNotNullParameter(name, "name");
        }
    };

    public static /* synthetic */ void finishWithResult$onfido_capture_sdk_core_release$default(BaseActivity baseActivity, int i, Intent intent, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: finishWithResult");
        }
        if ((i2 & 2) != 0) {
            intent = new Intent();
        }
        baseActivity.finishWithResult$onfido_capture_sdk_core_release(i, intent);
    }

    public static /* synthetic */ void isDarkModeEnabled$annotations() {
    }

    private final void saveSelectedLocale(Locale locale) {
        if (locale == null) {
            SharedPreferencesDataSource sharedPreferencesDataSource = this.storage;
            if (sharedPreferencesDataSource != null) {
                sharedPreferencesDataSource.delete$onfido_capture_sdk_core_release(StorageKey.SELECTED_LOCALE);
                return;
            }
            return;
        }
        SharedPreferencesDataSource sharedPreferencesDataSource2 = this.storage;
        if (sharedPreferencesDataSource2 != null) {
            StorageKey storageKey = StorageKey.SELECTED_LOCALE;
            SharedPreferences prefs$onfido_capture_sdk_core_release = sharedPreferencesDataSource2.getPrefs$onfido_capture_sdk_core_release();
            Intrinsics.checkNotNullExpressionValue(prefs$onfido_capture_sdk_core_release, "<get-prefs>(...)");
            sharedPreferencesDataSource2.set(prefs$onfido_capture_sdk_core_release, storageKey.name(), locale);
        }
    }

    private final void setTheme(OnfidoConfig onfidoConfig) {
        boolean zIsDarkModeEnabled = ContextUtilsKt.isDarkModeEnabled(this, onfidoConfig);
        this.isDarkModeEnabled = zIsDarkModeEnabled;
        SharedPreferencesDataSource sharedPreferencesDataSource = this.storage;
        if (sharedPreferencesDataSource != null) {
            StorageKey storageKey = StorageKey.IS_IN_DARK_MODE;
            Boolean boolValueOf = Boolean.valueOf(zIsDarkModeEnabled);
            SharedPreferences prefs$onfido_capture_sdk_core_release = sharedPreferencesDataSource.getPrefs$onfido_capture_sdk_core_release();
            Intrinsics.checkNotNullExpressionValue(prefs$onfido_capture_sdk_core_release, "<get-prefs>(...)");
            sharedPreferencesDataSource.set(prefs$onfido_capture_sdk_core_release, storageKey.name(), boolValueOf);
        }
        Integer lightThemeResId = onfidoConfig != null ? onfidoConfig.getLightThemeResId() : null;
        Integer darkThemeResId = onfidoConfig != null ? onfidoConfig.getDarkThemeResId() : null;
        if (lightThemeResId == null && darkThemeResId == null) {
            lightThemeResId = Integer.valueOf(this.isDarkModeEnabled ? R.style.OnfidoDarkTheme : R.style.OnfidoLightTheme);
        } else if (this.isDarkModeEnabled) {
            lightThemeResId = darkThemeResId;
        }
        if (lightThemeResId != null) {
            setTheme(lightThemeResId.intValue());
        }
    }

    private final void setupCrashHandler() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.onfido.android.sdk.capture.ui.BaseActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public final void uncaughtException(Thread thread, Throwable th) {
                BaseActivity.setupCrashHandler$lambda$3(this.f$0, thread, th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCrashHandler$lambda$3(BaseActivity this$0, Thread thread, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.e("CRASH", "SOMETHING WENT WRONG!!!");
        if (this$0.shouldStartCrashHandlingService()) {
            Log.e("CRASH", "SHOULD START");
            Intent intent = new Intent(this$0, (Class<?>) CrashHandlerService.class);
            intent.putExtra(OnfidoConstants.ONFIDO_CONFIG, this$0.getOnfidoConfig$onfido_capture_sdk_core_release());
            Intrinsics.checkNotNull(th);
            intent.putExtra(CrashHandlerService.EXCEPTION_STACK_TRACE, ExceptionsKt.stackTraceToString(th));
            intent.putExtra(CrashHandlerService.EXCEPTION_MESSAGE, th.getMessage());
            intent.putExtra(CrashHandlerService.LOGGER_ENABLED, this$0.getRemoteConfig$onfido_capture_sdk_core_release().getLoggerConfiguration().isEnabled());
            intent.putExtra(CrashHandlerService.ESSENTIAL_ANALYTICS_ENABLED, this$0.getRemoteConfig$onfido_capture_sdk_core_release().isInhouseAnalyticsEnabled());
            intent.putExtra(CrashHandlerService.LOGGER_ERROR_LEVELS, (String[]) this$0.getRemoteConfig$onfido_capture_sdk_core_release().getLoggerConfiguration().getLevels().toArray(new String[0]));
            this$0.startService(intent);
        }
        Intent intent2 = new Intent();
        String localizedMessage = th.getLocalizedMessage();
        if (localizedMessage == null) {
            localizedMessage = "";
        }
        intent2.putExtra(OnfidoConstants.ONFIDO_EXCEPTION_RESULT, new OnfidoException(localizedMessage));
        SharedPreferencesDataSource sharedPreferencesDataSource = this$0.storage;
        if (sharedPreferencesDataSource != null) {
            sharedPreferencesDataSource.delete$onfido_capture_sdk_core_release(StorageKey.SINGLE_RUN_SESSION_ID);
        }
        this$0.finishWithResult$onfido_capture_sdk_core_release(-2, intent2);
        System.exit(1);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }

    private final boolean shouldStartCrashHandlingService() {
        SdkConfiguration.LoggerConfiguration loggerConfiguration = DefaultOnfidoRemoteConfig.INSTANCE.getLoggerConfiguration();
        if (loggerConfiguration.isEnabled()) {
            List<String> levels = loggerConfiguration.getLevels();
            if (!(levels instanceof Collection) || !levels.isEmpty()) {
                Iterator<T> it = levels.iterator();
                while (it.hasNext()) {
                    if (StringsKt.equals((String) it.next(), "ERROR", true)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        Locale locale;
        Object objValueOf;
        Intrinsics.checkNotNullParameter(newBase, "newBase");
        SharedPreferencesDataSource sharedPreferencesDataSource = new SharedPreferencesDataSource(newBase, this.jsonParser);
        StorageKey storageKey = StorageKey.SELECTED_LOCALE;
        SharedPreferences prefs$onfido_capture_sdk_core_release = sharedPreferencesDataSource.getPrefs$onfido_capture_sdk_core_release();
        Intrinsics.checkNotNullExpressionValue(prefs$onfido_capture_sdk_core_release, "<get-prefs>(...)");
        String strName = storageKey.name();
        if (prefs$onfido_capture_sdk_core_release.contains(strName)) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Locale.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                objValueOf = prefs$onfido_capture_sdk_core_release.getString(strName, "");
                if (objValueOf == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.util.Locale");
                }
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                objValueOf = Integer.valueOf(prefs$onfido_capture_sdk_core_release.getInt(strName, -1));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                objValueOf = Boolean.valueOf(prefs$onfido_capture_sdk_core_release.getBoolean(strName, false));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Float.TYPE))) {
                objValueOf = Float.valueOf(prefs$onfido_capture_sdk_core_release.getFloat(strName, -1.0f));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                objValueOf = Long.valueOf(prefs$onfido_capture_sdk_core_release.getLong(strName, -1L));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Locale.class))) {
                    throw SharedPreferencesDataSource.Companion.getUNSUPPORTED_TYPE_EXCEPTION();
                }
                locale = sharedPreferencesDataSource.getLocale(prefs$onfido_capture_sdk_core_release, strName);
                if (locale == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.util.Locale");
                }
            }
            locale = (Locale) objValueOf;
        } else {
            locale = null;
        }
        LocalizationUtil localizationUtil = LocalizationUtil.INSTANCE;
        if (locale == null) {
            locale = Locale.getDefault();
        }
        super.attachBaseContext(localizationUtil.applyLanguage(newBase, locale));
        SplitCompat.installActivity(this);
    }

    public final void dismissLoadingDialog$onfido_capture_sdk_core_release() {
        Fragment fragmentFindFragmentByTag = getSupportFragmentManager().findFragmentByTag(LoadingFragment.INSTANCE.getTAG());
        DialogFragment dialogFragment = fragmentFindFragmentByTag instanceof DialogFragment ? (DialogFragment) fragmentFindFragmentByTag : null;
        if (dialogFragment != null) {
            dialogFragment.dismissAllowingStateLoss();
        }
    }

    public void finishWithResult$onfido_capture_sdk_core_release(int result, Intent intent) {
        Object serializableExtra;
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (result == -2) {
            if (Build.VERSION.SDK_INT >= 33) {
                serializableExtra = intent.getSerializableExtra(OnfidoConstants.ONFIDO_EXCEPTION_RESULT, OnfidoException.class);
            } else {
                Object serializableExtra2 = intent.getSerializableExtra(OnfidoConstants.ONFIDO_EXCEPTION_RESULT);
                if (!(serializableExtra2 instanceof OnfidoException)) {
                    serializableExtra2 = null;
                }
                serializableExtra = (OnfidoException) serializableExtra2;
            }
            OnfidoException onfidoException = serializableExtra instanceof OnfidoException ? (OnfidoException) serializableExtra : null;
            Timber.INSTANCE.e(onfidoException, "An error occurred onError callback will be called", new Object[0]);
            getFlowTracker$onfido_capture_sdk_core_release().trackFlowInterruptedWithError$onfido_capture_sdk_core_release("An error occurred onError callback will be called with " + (onfidoException != null ? onfidoException.getLocalizedMessage() : null));
        }
        setResult(result, intent);
        finish();
        if (this instanceof OnfidoActivity) {
            SdkController.INSTANCE.getInstance().onDestroy();
        }
    }

    public final FlowTracker getFlowTracker$onfido_capture_sdk_core_release() {
        FlowTracker flowTracker = this.flowTracker;
        if (flowTracker != null) {
            return flowTracker;
        }
        Intrinsics.throwUninitializedPropertyAccessException("flowTracker");
        return null;
    }

    protected final Json getJsonParser() {
        return this.jsonParser;
    }

    public final OnfidoConfig getOnfidoConfig$onfido_capture_sdk_core_release() {
        OnfidoConfig onfidoConfig = this.onfidoConfig;
        if (onfidoConfig != null) {
            return onfidoConfig;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onfidoConfig");
        return null;
    }

    public final AggregatedPerformanceAnalytics getPerformanceAnalytics$onfido_capture_sdk_core_release() {
        AggregatedPerformanceAnalytics aggregatedPerformanceAnalytics = this.performanceAnalytics;
        if (aggregatedPerformanceAnalytics != null) {
            return aggregatedPerformanceAnalytics;
        }
        Intrinsics.throwUninitializedPropertyAccessException("performanceAnalytics");
        return null;
    }

    public final OnfidoRemoteConfig getRemoteConfig$onfido_capture_sdk_core_release() {
        OnfidoRemoteConfig onfidoRemoteConfig = this.remoteConfig;
        if (onfidoRemoteConfig != null) {
            return onfidoRemoteConfig;
        }
        Intrinsics.throwUninitializedPropertyAccessException("remoteConfig");
        return null;
    }

    protected final AtomicBoolean getStartingNewActivity() {
        return this.startingNewActivity;
    }

    public final WaitingScreenTracker getWaitingScreenTracker$onfido_capture_sdk_core_release() {
        WaitingScreenTracker waitingScreenTracker = this.waitingScreenTracker;
        if (waitingScreenTracker != null) {
            return waitingScreenTracker;
        }
        Intrinsics.throwUninitializedPropertyAccessException("waitingScreenTracker");
        return null;
    }

    /* renamed from: isDarkModeEnabled, reason: from getter */
    public final boolean getIsDarkModeEnabled() {
        return this.isDarkModeEnabled;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        Parcelable parcelable;
        OnfidoConfig onfidoConfig;
        String string;
        Parcelable parcelable2;
        this.storage = new SharedPreferencesDataSource(this, this.jsonParser);
        try {
            Intent intent = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
            int i = Build.VERSION.SDK_INT;
            if (i >= 33) {
                parcelable = (Parcelable) intent.getParcelableExtra(OnfidoConstants.ONFIDO_CONFIG, OnfidoConfig.class);
            } else {
                Parcelable parcelableExtra = intent.getParcelableExtra(OnfidoConstants.ONFIDO_CONFIG);
                if (!(parcelableExtra instanceof OnfidoConfig)) {
                    parcelableExtra = null;
                }
                parcelable = (OnfidoConfig) parcelableExtra;
            }
            OnfidoConfig onfidoConfig2 = (OnfidoConfig) parcelable;
            if (savedInstanceState != null) {
                if (i >= 33) {
                    parcelable2 = (Parcelable) savedInstanceState.getParcelable(OnfidoConstants.ONFIDO_CONFIG, OnfidoConfig.class);
                } else {
                    Parcelable parcelable3 = savedInstanceState.getParcelable(OnfidoConstants.ONFIDO_CONFIG);
                    if (!(parcelable3 instanceof OnfidoConfig)) {
                        parcelable3 = null;
                    }
                    parcelable2 = (OnfidoConfig) parcelable3;
                }
                onfidoConfig = (OnfidoConfig) parcelable2;
            } else {
                onfidoConfig = null;
            }
            if (savedInstanceState != null && (string = savedInstanceState.getString(OnfidoConstants.ONFIDO_REMOTE_CONFIG)) != null) {
                DefaultOnfidoRemoteConfig defaultOnfidoRemoteConfig = DefaultOnfidoRemoteConfig.INSTANCE;
                Json jsonParserInstance = JsonParserFactoryKt.getJsonParserInstance();
                defaultOnfidoRemoteConfig.updateFromSdkConfiguration((SdkConfiguration) jsonParserInstance.decodeFromString(SerializersKt.serializer(jsonParserInstance.getSerializersModule(), Reflection.typeOf(SdkConfiguration.class)), string));
            }
            if (onfidoConfig2 != null) {
                EnterpriseConfig.INSTANCE.setEnterpriseFeatures(onfidoConfig2.getEnterpriseFeatures());
                saveSelectedLocale(onfidoConfig2.getLocale());
            }
            if (onfidoConfig != null) {
                EnterpriseConfig.INSTANCE.setEnterpriseFeatures(onfidoConfig.getEnterpriseFeatures());
                saveSelectedLocale(onfidoConfig.getLocale());
            }
            SdkController.getSdkComponent$default(SdkController.INSTANCE.getInstance(), this, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        } catch (RuntimeException unused) {
            finishWithResult$onfido_capture_sdk_core_release$default(this, OnfidoActivity.RESULT_EXIT_MISSING_ONFIDO_CONFIG, null, 2, null);
        }
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        Onfido.Companion companion = Onfido.INSTANCE;
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "getApplication(...)");
        if (companion.isOnfidoProcess(application)) {
            setupCrashHandler();
        }
        setTheme(getOnfidoConfig$onfido_capture_sdk_core_release());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        dismissLoadingDialog$onfido_capture_sdk_core_release();
        this.storage = null;
        unbindService(this.mainProcessConnection);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        if (!onToolbarBackEvent$onfido_capture_sdk_core_release()) {
            finishWithResult$onfido_capture_sdk_core_release$default(this, 0, null, 2, null);
        }
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        dismissLoadingDialog$onfido_capture_sdk_core_release();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        bindService(new Intent(this, (Class<?>) MainProcessStarterService.class), this.mainProcessConnection, 1);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putParcelable(OnfidoConstants.ONFIDO_CONFIG, getOnfidoConfig$onfido_capture_sdk_core_release());
        Json jsonParserInstance = JsonParserFactoryKt.getJsonParserInstance();
        outState.putString(OnfidoConstants.ONFIDO_REMOTE_CONFIG, jsonParserInstance.encodeToString(SerializersKt.serializer(jsonParserInstance.getSerializersModule(), Reflection.typeOf(SdkConfiguration.class)), DefaultOnfidoRemoteConfig.INSTANCE.getSdkConfiguration()));
        super.onSaveInstanceState(outState);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        getPerformanceAnalytics$onfido_capture_sdk_core_release().clearEvents();
        boolean zCompareAndSet = this.startingNewActivity.compareAndSet(true, false);
        boolean zIsFinishing = isFinishing();
        boolean exitWhenSentToBackground = getOnfidoConfig$onfido_capture_sdk_core_release().getExitWhenSentToBackground();
        if (zCompareAndSet || zIsFinishing || !exitWhenSentToBackground) {
            return;
        }
        onStopDuringExitWhenSentToBackgroundMode$onfido_capture_sdk_core_release();
    }

    public abstract void onStopDuringExitWhenSentToBackgroundMode$onfido_capture_sdk_core_release();

    public boolean onToolbarBackEvent$onfido_capture_sdk_core_release() {
        return false;
    }

    public final void setDarkModeEnabled(boolean z) {
        this.isDarkModeEnabled = z;
    }

    public final void setFlowTracker$onfido_capture_sdk_core_release(FlowTracker flowTracker) {
        Intrinsics.checkNotNullParameter(flowTracker, "<set-?>");
        this.flowTracker = flowTracker;
    }

    public final void setOnfidoConfig$onfido_capture_sdk_core_release(OnfidoConfig onfidoConfig) {
        Intrinsics.checkNotNullParameter(onfidoConfig, "<set-?>");
        this.onfidoConfig = onfidoConfig;
    }

    public final void setPerformanceAnalytics$onfido_capture_sdk_core_release(AggregatedPerformanceAnalytics aggregatedPerformanceAnalytics) {
        Intrinsics.checkNotNullParameter(aggregatedPerformanceAnalytics, "<set-?>");
        this.performanceAnalytics = aggregatedPerformanceAnalytics;
    }

    public final void setRemoteConfig$onfido_capture_sdk_core_release(OnfidoRemoteConfig onfidoRemoteConfig) {
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "<set-?>");
        this.remoteConfig = onfidoRemoteConfig;
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public void setSupportActionBar(Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null) {
            return;
        }
        supportActionBar.setTitle("");
    }

    public final void setWaitingScreenTracker$onfido_capture_sdk_core_release(WaitingScreenTracker waitingScreenTracker) {
        Intrinsics.checkNotNullParameter(waitingScreenTracker, "<set-?>");
        this.waitingScreenTracker = waitingScreenTracker;
    }

    public final void showLoadingDialog$onfido_capture_sdk_core_release(LoadingFragment.Companion.DialogMode dialogMode) {
        Intrinsics.checkNotNullParameter(dialogMode, "dialogMode");
        dismissLoadingDialog$onfido_capture_sdk_core_release();
        LoadingFragment.Companion companion = LoadingFragment.INSTANCE;
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        companion.show(dialogMode, supportFragmentManager);
    }
}
