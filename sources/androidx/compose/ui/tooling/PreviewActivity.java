package androidx.compose.ui.tooling;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.ComponentActivity;
import androidx.activity.compose.ComponentActivityKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material.FloatingActionButtonKt;
import androidx.compose.material.ScaffoldKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import com.facebook.react.util.JSStackTrace;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: PreviewActivity.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0004H\u0002J \u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/tooling/PreviewActivity;", "Landroidx/activity/ComponentActivity;", "()V", "TAG", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setComposableContent", "composableFqn", "setParameterizedContent", "className", JSStackTrace.METHOD_NAME_KEY, "parameterProvider", "ui-tooling_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PreviewActivity extends ComponentActivity {
    public static final int $stable = 0;
    private final String TAG = "PreviewActivity";

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws IllegalAccessException, InstantiationException, SecurityException, IllegalArgumentException, InvocationTargetException {
        String stringExtra;
        super.onCreate(savedInstanceState);
        if ((getApplicationInfo().flags & 2) == 0) {
            Log.d(this.TAG, "Application is not debuggable. Compose Preview not allowed.");
            finish();
            return;
        }
        Intent intent = getIntent();
        if (intent == null || (stringExtra = intent.getStringExtra("composable")) == null) {
            return;
        }
        setComposableContent(stringExtra);
    }

    private final void setComposableContent(String composableFqn) throws IllegalAccessException, InstantiationException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Log.d(this.TAG, "PreviewActivity has composable " + composableFqn);
        final String strSubstringBeforeLast$default = StringsKt.substringBeforeLast$default(composableFqn, ClassUtils.PACKAGE_SEPARATOR_CHAR, (String) null, 2, (Object) null);
        final String strSubstringAfterLast$default = StringsKt.substringAfterLast$default(composableFqn, ClassUtils.PACKAGE_SEPARATOR_CHAR, (String) null, 2, (Object) null);
        String stringExtra = getIntent().getStringExtra("parameterProviderClassName");
        if (stringExtra != null) {
            setParameterizedContent(strSubstringBeforeLast$default, strSubstringAfterLast$default, stringExtra);
        } else {
            Log.d(this.TAG, "Previewing '" + strSubstringAfterLast$default + "' without a parameter provider.");
            ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(-161032931, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.tooling.PreviewActivity.setComposableContent.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) throws SecurityException, ReflectiveOperationException {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) throws SecurityException, ReflectiveOperationException {
                    ComposerKt.sourceInformation(composer, "C:PreviewActivity.kt#hevd2p");
                    if ((i & 11) != 2 || !composer.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-161032931, i, -1, "androidx.compose.ui.tooling.PreviewActivity.setComposableContent.<anonymous> (PreviewActivity.kt:75)");
                        }
                        ComposableInvoker.INSTANCE.invokeComposable(strSubstringBeforeLast$default, strSubstringAfterLast$default, composer, new Object[0]);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer.skipToGroupEnd();
                }
            }), 1, null);
        }
    }

    private final void setParameterizedContent(final String className, final String methodName, String parameterProvider) throws IllegalAccessException, InstantiationException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Log.d(this.TAG, "Previewing '" + methodName + "' with parameter provider: '" + parameterProvider + '\'');
        final Object[] previewProviderParameters = PreviewUtilsKt.getPreviewProviderParameters(PreviewUtilsKt.asPreviewProviderClass(parameterProvider), getIntent().getIntExtra("parameterProviderIndex", -1));
        if (previewProviderParameters.length > 1) {
            ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(-1735847170, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.tooling.PreviewActivity.setParameterizedContent.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    ComposerKt.sourceInformation(composer, "C109@4590L30,111@4638L768:PreviewActivity.kt#hevd2p");
                    if ((i & 11) == 2 && composer.getSkipping()) {
                        composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1735847170, i, -1, "androidx.compose.ui.tooling.PreviewActivity.setParameterizedContent.<anonymous> (PreviewActivity.kt:108)");
                    }
                    composer.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                    Object objRememberedValue = composer.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(0, null, 2, null);
                        composer.updateRememberedValue(objRememberedValue);
                    }
                    composer.endReplaceableGroup();
                    final MutableState mutableState = (MutableState) objRememberedValue;
                    final Object[] objArr = previewProviderParameters;
                    ComposableLambda composableLambda = ComposableLambdaKt.composableLambda(composer, 2137630662, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.tooling.PreviewActivity.setParameterizedContent.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i2) {
                            ComposerKt.sourceInformation(composer2, "C123@5159L207:PreviewActivity.kt#hevd2p");
                            if ((i2 & 11) != 2 || !composer2.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(2137630662, i2, -1, "androidx.compose.ui.tooling.PreviewActivity.setParameterizedContent.<anonymous>.<anonymous> (PreviewActivity.kt:122)");
                                }
                                Function2<Composer, Integer, Unit> function2M4315getLambda1$ui_tooling_release = ComposableSingletons$PreviewActivityKt.INSTANCE.m4315getLambda1$ui_tooling_release();
                                final MutableState<Integer> mutableState2 = mutableState;
                                final Object[] objArr2 = objArr;
                                FloatingActionButtonKt.m1318ExtendedFloatingActionButtonwqdebIU(function2M4315getLambda1$ui_tooling_release, new Function0<Unit>() { // from class: androidx.compose.ui.tooling.PreviewActivity.setParameterizedContent.1.1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public /* bridge */ /* synthetic */ Unit invoke() {
                                        invoke2();
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        MutableState<Integer> mutableState3 = mutableState2;
                                        mutableState3.setValue(Integer.valueOf((mutableState3.getValue().intValue() + 1) % objArr2.length));
                                    }
                                }, null, null, null, null, 0L, 0L, null, composer2, 6, 508);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer2.skipToGroupEnd();
                        }
                    });
                    final String str = className;
                    final String str2 = methodName;
                    final Object[] objArr2 = previewProviderParameters;
                    ScaffoldKt.m1393Scaffold27mzLpw(null, null, null, null, null, composableLambda, 0, false, null, false, null, 0.0f, 0L, 0L, 0L, 0L, 0L, ComposableLambdaKt.composableLambda(composer, -1578412612, true, new Function3<PaddingValues, Composer, Integer, Unit>() { // from class: androidx.compose.ui.tooling.PreviewActivity.setParameterizedContent.1.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(PaddingValues paddingValues, Composer composer2, Integer num) throws SecurityException, ReflectiveOperationException {
                            invoke(paddingValues, composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(PaddingValues padding, Composer composer2, int i2) throws SecurityException, ReflectiveOperationException {
                            int i3;
                            Intrinsics.checkNotNullParameter(padding, "padding");
                            ComposerKt.sourceInformation(composer2, "C113@4715L351:PreviewActivity.kt#hevd2p");
                            if ((i2 & 14) == 0) {
                                i3 = (composer2.changed(padding) ? 4 : 2) | i2;
                            } else {
                                i3 = i2;
                            }
                            if ((i3 & 91) != 18 || !composer2.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1578412612, i2, -1, "androidx.compose.ui.tooling.PreviewActivity.setParameterizedContent.<anonymous>.<anonymous> (PreviewActivity.kt:112)");
                                }
                                Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, padding);
                                String str3 = str;
                                String str4 = str2;
                                Object[] objArr3 = objArr2;
                                MutableState<Integer> mutableState2 = mutableState;
                                composer2.startReplaceableGroup(733328855);
                                ComposerKt.sourceInformation(composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composer2, 0);
                                composer2.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation(composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart(composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume = composer2.consume(localDensity);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                Density density = (Density) objConsume;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart(composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume2 = composer2.consume(localLayoutDirection);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                LayoutDirection layoutDirection = (LayoutDirection) objConsume2;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart(composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume3 = composer2.consume(localViewConfiguration);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ViewConfiguration viewConfiguration = (ViewConfiguration) objConsume3;
                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifierPadding);
                                if (!(composer2.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer2.startReusableNode();
                                if (composer2.getInserting()) {
                                    composer2.createNode(constructor);
                                } else {
                                    composer2.useNode();
                                }
                                composer2.disableReusing();
                                Composer composerM1518constructorimpl = Updater.m1518constructorimpl(composer2);
                                Updater.m1525setimpl(composerM1518constructorimpl, measurePolicyRememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m1525setimpl(composerM1518constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
                                Updater.m1525setimpl(composerM1518constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                Updater.m1525setimpl(composerM1518constructorimpl, viewConfiguration, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                                composer2.enableReusing();
                                function3MaterializerOf.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composer2)), composer2, 0);
                                composer2.startReplaceableGroup(2058660585);
                                ComposerKt.sourceInformationMarkerStart(composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart(composer2, -513396387, "C:PreviewActivity.kt#hevd2p");
                                ComposableInvoker.INSTANCE.invokeComposable(str3, str4, composer2, objArr3[mutableState2.getValue().intValue()]);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                composer2.endReplaceableGroup();
                                composer2.endNode();
                                composer2.endReplaceableGroup();
                                composer2.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer2.skipToGroupEnd();
                        }
                    }), composer, 196608, 12582912, 131039);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), 1, null);
        } else {
            ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(1507674311, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.tooling.PreviewActivity.setParameterizedContent.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) throws SecurityException, ReflectiveOperationException {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) throws SecurityException, ReflectiveOperationException {
                    ComposerKt.sourceInformation(composer, "C:PreviewActivity.kt#hevd2p");
                    if ((i & 11) != 2 || !composer.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1507674311, i, -1, "androidx.compose.ui.tooling.PreviewActivity.setParameterizedContent.<anonymous> (PreviewActivity.kt:131)");
                        }
                        ComposableInvoker composableInvoker = ComposableInvoker.INSTANCE;
                        String str = className;
                        String str2 = methodName;
                        Object[] objArr = previewProviderParameters;
                        composableInvoker.invokeComposable(str, str2, composer, Arrays.copyOf(objArr, objArr.length));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer.skipToGroupEnd();
                }
            }), 1, null);
        }
    }
}
