package androidx.compose.material;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import com.clevertap.android.sdk.variables.CTVariableUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Strings.android.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001d\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0006"}, d2 = {"getString", "", CTVariableUtils.STRING, "Landroidx/compose/material/Strings;", "getString-4foXLRw", "(ILandroidx/compose/runtime/Composer;I)Ljava/lang/String;", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class Strings_androidKt {
    /* renamed from: getString-4foXLRw, reason: not valid java name */
    public static final String m1416getString4foXLRw(int i, Composer composer, int i2) {
        String string;
        composer.startReplaceableGroup(-845575816);
        ComposerKt.sourceInformation(composer, "C(getString)P(0:c#material.Strings)25@921L7,26@962L7:Strings.android.kt#jmzs0o");
        ProvidableCompositionLocal<Configuration> localConfiguration = AndroidCompositionLocals_androidKt.getLocalConfiguration();
        ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
        composer.consume(localConfiguration);
        ComposerKt.sourceInformationMarkerEnd(composer);
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd(composer);
        Resources resources = ((Context) objConsume).getResources();
        if (Strings.m1408equalsimpl0(i, Strings.INSTANCE.m1415getNavigationMenuUdPEhr4())) {
            string = resources.getString(androidx.compose.ui.R.string.navigation_menu);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.navigation_menu)");
        } else if (Strings.m1408equalsimpl0(i, Strings.INSTANCE.m1412getCloseDrawerUdPEhr4())) {
            string = resources.getString(androidx.compose.ui.R.string.close_drawer);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.close_drawer)");
        } else if (Strings.m1408equalsimpl0(i, Strings.INSTANCE.m1413getCloseSheetUdPEhr4())) {
            string = resources.getString(androidx.compose.ui.R.string.close_sheet);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.close_sheet)");
        } else if (Strings.m1408equalsimpl0(i, Strings.INSTANCE.m1414getDefaultErrorMessageUdPEhr4())) {
            string = resources.getString(androidx.compose.ui.R.string.default_error_message);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.default_error_message)");
        } else {
            string = "";
        }
        composer.endReplaceableGroup();
        return string;
    }
}
