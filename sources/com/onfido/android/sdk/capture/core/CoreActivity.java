package com.onfido.android.sdk.capture.core;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.play.core.splitcompat.SplitCompat;
import com.onfido.android.sdk.FlowConfig;
import com.onfido.android.sdk.capture.EnterpriseFeatures;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.core.config.OnfidoNewConfig;
import java.io.Serializable;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0002J\u0012\u0010\n\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/core/CoreActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "navigateTo", "flow", "Lcom/onfido/android/sdk/FlowConfig;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CoreActivity extends AppCompatActivity {
    private final void navigateTo(FlowConfig flow) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "beginTransaction()");
        fragmentTransactionBeginTransaction.setReorderingAllowed(true);
        fragmentTransactionBeginTransaction.replace(R.id.container, flow.createFragment());
        fragmentTransactionBeginTransaction.commit();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        SplitCompat.installActivity(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onfido_activity_core);
        Serializable serializableExtra = getIntent().getSerializableExtra(OnfidoLauncher.KEY_CONFIG);
        Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.onfido.android.sdk.FlowConfig");
        FlowConfig flowConfig = (FlowConfig) serializableExtra;
        Locale locale = flowConfig.getLocale();
        EnterpriseFeatures enterpriseFeatures = flowConfig.getEnterpriseFeatures();
        boolean backgroundRunDisabled = flowConfig.getBackgroundRunDisabled();
        OnfidoConfig.Builder builderWithSDKToken$default = OnfidoConfig.Builder.withSDKToken$default(OnfidoConfig.INSTANCE.builder(this), flowConfig.getSdkToken(), null, 2, null);
        if (locale != null) {
            builderWithSDKToken$default.withLocale(locale);
        }
        if (enterpriseFeatures != null) {
            builderWithSDKToken$default.withEnterpriseFeatures(enterpriseFeatures);
        }
        if (!backgroundRunDisabled) {
            builderWithSDKToken$default.exitWhenSentToBackground();
        }
        if (!(flowConfig instanceof OnfidoNewConfig)) {
            builderWithSDKToken$default.withWorkflowConfig(flowConfig);
        }
        builderWithSDKToken$default.withTheme(flowConfig.getTheme());
        OnfidoConfig onfidoConfigBuild = builderWithSDKToken$default.build();
        SdkController companion = SdkController.INSTANCE.getInstance();
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        companion.init(applicationContext, onfidoConfigBuild, flowConfig);
        if (savedInstanceState == null) {
            navigateTo(flowConfig);
        }
    }
}
