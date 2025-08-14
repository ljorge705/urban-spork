package com.onfido.android.sdk.capture.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.material.color.MaterialColors;
import com.nimbusds.jose.jwk.JWKParameterNames;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.OnfidoTheme;
import com.onfido.android.sdk.capture.ui.options.Orientation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u001a\u0016\u0010\u0007\u001a\u00020\u0006*\u00020\b2\b\b\u0001\u0010\u0007\u001a\u00020\u0006H\u0001\u001a\u0016\u0010\t\u001a\u00020\u0006*\u00020\b2\b\b\u0001\u0010\n\u001a\u00020\u0006H\u0007\u001a\u0016\u0010\u000b\u001a\u00020\u0006*\u00020\b2\b\b\u0001\u0010\u000b\u001a\u00020\u0006H\u0000\u001a\u0016\u0010\f\u001a\u00020\u0006*\u00020\b2\b\b\u0001\u0010\u000b\u001a\u00020\u0006H\u0000\u001a\u0014\u0010\r\u001a\u00020\u000e*\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0006H\u0001\u001a\u000e\u0010\u0010\u001a\u00020\u0011*\u0004\u0018\u00010\bH\u0000\u001a\u0016\u0010\u0012\u001a\u00020\u0001*\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0000\u001a\n\u0010\u0015\u001a\u00020\u0001*\u00020\b\u001a\f\u0010\u0016\u001a\u00020\u0001*\u00020\bH\u0000\u001a\f\u0010\u0017\u001a\u00020\u0018*\u00020\u0019H\u0000\u001a\f\u0010\u0017\u001a\u00020\u0018*\u00020\u001aH\u0000\u001a1\u0010\u001b\u001a\u0004\u0018\u00010\u0006*\u00020\b2\b\b\u0001\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\u001d\u001a\u00020\u00022\b\b\u0002\u0010\u001e\u001a\u00020\u0001H\u0001¢\u0006\u0002\u0010\u001f\u001a\u0018\u0010 \u001a\u0004\u0018\u00010!*\u00020\b2\b\b\u0001\u0010\"\u001a\u00020\u0006H\u0007\u001a,\u0010#\u001a\u0004\u0018\u00010\u0002*\u00020\b2\b\b\u0001\u0010\"\u001a\u00020\u00062\b\b\u0002\u0010\u001d\u001a\u00020\u00022\b\b\u0002\u0010\u001e\u001a\u00020\u0001H\u0007\u001a\f\u0010$\u001a\u00020\u0006*\u00020\bH\u0000\u001a\f\u0010%\u001a\u00020\u000e*\u00020\bH\u0000\u001a\f\u0010&\u001a\u00020\u0006*\u00020\bH\u0000\u001a\f\u0010'\u001a\u00020(*\u00020\bH\u0001\u001a\f\u0010)\u001a\u00020(*\u00020\bH\u0001\u001a\f\u0010*\u001a\u00020+*\u00020\bH\u0002\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003¨\u0006,"}, d2 = {"isEmpty", "", "Landroid/util/TypedValue;", "(Landroid/util/TypedValue;)Z", "apilevelAtLeast", "apiLevel", "", "color", "Landroid/content/Context;", "colorFromAttr", "colorAttributeResId", "dimen", "dimenInt", "dpToPixel", "", JWKParameterNames.RSA_FIRST_FACTOR_CRT_EXPONENT, "getScreenOrientation", "Lcom/onfido/android/sdk/capture/ui/options/Orientation;", "isDarkModeEnabled", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "isGooglePlayServiceAvailable", "isSystemDarkModeEnabled", "requireToolbarHost", "Lcom/onfido/android/sdk/capture/utils/ToolbarHost;", "Landroid/app/Activity;", "Landroidx/fragment/app/Fragment;", "resolveDimensionFromAttr", "dimensionAttr", "typedValue", "resolveRefs", "(Landroid/content/Context;ILandroid/util/TypedValue;Z)Ljava/lang/Integer;", "resolveFontFromAttr", "Lcom/onfido/android/sdk/capture/utils/FontInfo;", "attr", "resolveTypedValueFromAttr", "screenHeight", "screenScaledDensity", "screenWidth", "vibrateForError", "", "vibrateForSuccess", "vibrator", "Landroid/os/Vibrator;", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ContextUtilsKt {
    public static final boolean apilevelAtLeast(int i) {
        return Build.VERSION.SDK_INT >= i;
    }

    public static final int color(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return ContextCompat.getColor(context, i);
    }

    public static final int colorFromAttr(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return MaterialColors.getColor(context, i, "No attribute with name " + i + " was found in the theme");
    }

    public static final int dimen(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return context.getResources().getDimensionPixelSize(i);
    }

    public static final int dimenInt(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return context.getResources().getDimensionPixelOffset(i);
    }

    public static final float dpToPixel(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
    }

    public static final Orientation getScreenOrientation(Context context) {
        if (context != null) {
            Orientation orientation = context.getResources().getConfiguration().orientation == 2 ? Orientation.LANDSCAPE : Orientation.PORTRAIT;
            if (orientation != null) {
                return orientation;
            }
        }
        return Orientation.PORTRAIT;
    }

    public static final boolean isDarkModeEnabled(Context context, OnfidoConfig onfidoConfig) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        if ((onfidoConfig != null ? onfidoConfig.getTheme() : null) != OnfidoTheme.DARK) {
            if ((onfidoConfig != null ? onfidoConfig.getTheme() : null) != OnfidoTheme.AUTOMATIC || !isSystemDarkModeEnabled(context)) {
                return false;
            }
        }
        return true;
    }

    private static final boolean isEmpty(TypedValue typedValue) {
        return typedValue.resourceId == 0 || typedValue.type == 0;
    }

    public static final boolean isGooglePlayServiceAvailable(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        Intrinsics.checkNotNullExpressionValue(googleApiAvailability, "getInstance(...)");
        return googleApiAvailability.isGooglePlayServicesAvailable(context) == 0;
    }

    public static final boolean isSystemDarkModeEnabled(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ToolbarHost requireToolbarHost(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        ToolbarHost toolbarHost = activity instanceof ToolbarHost ? (ToolbarHost) activity : null;
        if (toolbarHost != null) {
            return toolbarHost;
        }
        throw new IllegalStateException(("this Activity= " + activity + " is not a ToolbarHost").toString());
    }

    public static final Integer resolveDimensionFromAttr(Context context, int i, TypedValue typedValue, boolean z) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(typedValue, "typedValue");
        try {
            context.getTheme().resolveAttribute(i, typedValue, z);
            if (!isEmpty(typedValue)) {
                return Integer.valueOf(context.getResources().getDimensionPixelSize(typedValue.resourceId));
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static /* synthetic */ Integer resolveDimensionFromAttr$default(Context context, int i, TypedValue typedValue, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            typedValue = new TypedValue();
        }
        if ((i2 & 4) != 0) {
            z = true;
        }
        return resolveDimensionFromAttr(context, i, typedValue, z);
    }

    public static final FontInfo resolveFontFromAttr(Context context, int i) {
        FontInfo fontInfo;
        Intrinsics.checkNotNullParameter(context, "<this>");
        TypedValue typedValueResolveTypedValueFromAttr$default = resolveTypedValueFromAttr$default(context, i, null, false, 6, null);
        Integer numValueOf = typedValueResolveTypedValueFromAttr$default != null ? Integer.valueOf(typedValueResolveTypedValueFromAttr$default.type) : null;
        if (numValueOf != null && numValueOf.intValue() == 3) {
            CharSequence charSequence = typedValueResolveTypedValueFromAttr$default.string;
            String str = charSequence instanceof String ? (String) charSequence : null;
            fontInfo = new FontInfo(Typeface.create(str, 0), str);
        } else {
            if (numValueOf == null || numValueOf.intValue() != 1) {
                return null;
            }
            fontInfo = new FontInfo(ResourcesCompat.getFont(context, typedValueResolveTypedValueFromAttr$default.data), context.getResources().getResourceEntryName(typedValueResolveTypedValueFromAttr$default.resourceId));
        }
        return fontInfo;
    }

    public static final TypedValue resolveTypedValueFromAttr(Context context, int i, TypedValue typedValue, boolean z) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(typedValue, "typedValue");
        try {
            context.getTheme().resolveAttribute(i, typedValue, z);
        } catch (Throwable unused) {
        }
        if (isEmpty(typedValue)) {
            return null;
        }
        return typedValue;
    }

    public static /* synthetic */ TypedValue resolveTypedValueFromAttr$default(Context context, int i, TypedValue typedValue, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            typedValue = new TypedValue();
        }
        if ((i2 & 4) != 0) {
            z = true;
        }
        return resolveTypedValueFromAttr(context, i, typedValue, z);
    }

    public static final int screenHeight(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Object systemService = context.getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        ((WindowManager) systemService).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static final float screenScaledDensity(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static final int screenWidth(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Object systemService = context.getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        ((WindowManager) systemService).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static final void vibrateForError(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        boolean zApilevelAtLeast = apilevelAtLeast(26);
        Vibrator vibrator = vibrator(context);
        if (zApilevelAtLeast) {
            vibrator.vibrate(VibrationEffect.createWaveform(new long[]{75, 75, 75, 75}, new int[]{255, 0, 255, 0}, -1));
        } else {
            vibrator.vibrate(new long[]{75, 75, 75, 75}, -1);
        }
    }

    public static final void vibrateForSuccess(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        boolean zApilevelAtLeast = apilevelAtLeast(26);
        Vibrator vibrator = vibrator(context);
        if (zApilevelAtLeast) {
            vibrator.vibrate(VibrationEffect.createOneShot(100L, -1));
        } else {
            vibrator.vibrate(100L);
        }
    }

    private static final Vibrator vibrator(Context context) {
        Object systemService = context.getSystemService("vibrator");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.Vibrator");
        return (Vibrator) systemService;
    }

    public static final ToolbarHost requireToolbarHost(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        FragmentActivity fragmentActivityRequireActivity = fragment.requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
        return requireToolbarHost(fragmentActivityRequireActivity);
    }
}
