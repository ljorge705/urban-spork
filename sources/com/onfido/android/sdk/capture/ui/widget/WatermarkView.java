package com.onfido.android.sdk.capture.ui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import com.onfido.android.sdk.capture.EnterpriseFeatures;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.config.EnterpriseConfig;
import com.onfido.android.sdk.capture.databinding.OnfidoWatermarkViewBinding;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0003J\u0006\u0010\u0011\u001a\u00020\fJ\u0006\u0010\u0012\u001a\u00020\fJ\u0016\u0010\u0013\u001a\u00020\f*\u00020\u00142\b\b\u0001\u0010\u0015\u001a\u00020\u0007H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/widget/WatermarkView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoWatermarkViewBinding;", "configureView", "", "isDarkBackground", "", "getWatermarkBitmap", "Landroid/graphics/Bitmap;", "setDarkBackground", "setLightBackground", "configure", "Landroid/widget/TextView;", "color", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class WatermarkView extends LinearLayout {
    private final OnfidoWatermarkViewBinding binding;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WatermarkView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    private final void configure(TextView textView, int i) {
        textView.setTextColor(i);
        textView.setTypeface(Typeface.SANS_SERIF);
    }

    private final void configureView(boolean isDarkBackground) throws Resources.NotFoundException {
        Bitmap watermarkBitmap = getWatermarkBitmap(isDarkBackground);
        if (watermarkBitmap != null) {
            this.binding.watermarkImageView.setImageBitmap(watermarkBitmap);
        }
        EnterpriseFeatures enterpriseFeatures = EnterpriseConfig.INSTANCE.getEnterpriseFeatures();
        if (enterpriseFeatures == null || !enterpriseFeatures.getHideOnfidoLogo()) {
            return;
        }
        ViewExtensionsKt.toGone$default(this, false, 1, null);
    }

    private final Bitmap getWatermarkBitmap(boolean isDarkBackground) throws Resources.NotFoundException {
        EnterpriseFeatures enterpriseFeatures = EnterpriseConfig.INSTANCE.getEnterpriseFeatures();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        int iColorFromAttr = ContextUtilsKt.colorFromAttr(context, R.attr.onfidoColorWatermark);
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        int iColorFromAttr2 = ContextUtilsKt.colorFromAttr(context2, R.attr.onfidoColorWatermarkDark);
        if (isDarkBackground) {
            iColorFromAttr = iColorFromAttr2;
        }
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.onfido_ic_watermark);
        if (drawable != null) {
            drawable.setTint(iColorFromAttr);
        }
        if (enterpriseFeatures != null) {
            Integer cobrandingLogoLightMode = enterpriseFeatures.getCobrandingLogoLightMode();
            Integer cobrandingLogoDarkMode = enterpriseFeatures.getCobrandingLogoDarkMode();
            if (cobrandingLogoLightMode == null || cobrandingLogoDarkMode == null) {
                String cobrandingText = enterpriseFeatures.getCobrandingText();
                if (cobrandingText != null && cobrandingText.length() != 0) {
                    TextView companyNameTextView = this.binding.companyNameTextView;
                    Intrinsics.checkNotNullExpressionValue(companyNameTextView, "companyNameTextView");
                    configure(companyNameTextView, iColorFromAttr);
                    TextView poweredByTextView = this.binding.poweredByTextView;
                    Intrinsics.checkNotNullExpressionValue(poweredByTextView, "poweredByTextView");
                    configure(poweredByTextView, iColorFromAttr);
                    this.binding.companyNameTextView.setText(enterpriseFeatures.getCobrandingText() + ' ');
                    this.binding.poweredByTextView.setText(getContext().getString(R.string.onfido_powered_by));
                    drawable = ContextCompat.getDrawable(getContext(), R.drawable.onfido_cobrand_logo);
                    if (drawable != null) {
                        drawable.setTint(iColorFromAttr);
                    }
                }
            } else {
                TextView poweredByTextView2 = this.binding.poweredByTextView;
                Intrinsics.checkNotNullExpressionValue(poweredByTextView2, "poweredByTextView");
                configure(poweredByTextView2, iColorFromAttr);
                this.binding.poweredByTextView.setText(getContext().getString(R.string.onfido_powered_by));
                float dimension = getResources().getDimension(R.dimen.onfido_cobranding_logo_left_margin);
                float dimension2 = getResources().getDimension(R.dimen.onfido_cobranding_logo_right_margin);
                int i = (int) (dimension / getResources().getDisplayMetrics().density);
                int i2 = (int) (dimension2 / getResources().getDisplayMetrics().density);
                ViewGroup.LayoutParams layoutParams = this.binding.poweredByTextView.getLayoutParams();
                Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                layoutParams2.setMargins(i, 0, i2, 0);
                this.binding.poweredByTextView.setLayoutParams(layoutParams2);
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.onfido_cobrand_logo);
                if (drawable != null) {
                    drawable.setTint(iColorFromAttr);
                }
                int iIntValue = isDarkBackground ? cobrandingLogoDarkMode.intValue() : cobrandingLogoLightMode.intValue();
                AppCompatImageView appCompatImageView = this.binding.cobrandImageView;
                Drawable drawable2 = ContextCompat.getDrawable(getContext(), iIntValue);
                appCompatImageView.setImageBitmap(drawable2 != null ? ViewExtensionsKt.toBitmap(drawable2) : null);
            }
        }
        if (drawable != null) {
            return ViewExtensionsKt.toBitmap(drawable);
        }
        return null;
    }

    public final void setDarkBackground() throws Resources.NotFoundException {
        configureView(true);
    }

    public final void setLightBackground() throws Resources.NotFoundException {
        configureView(false);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WatermarkView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatermarkView(Context context, AttributeSet attributeSet, int i) throws Resources.NotFoundException {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoWatermarkViewBinding onfidoWatermarkViewBindingInflate = OnfidoWatermarkViewBinding.inflate(LayoutInflater.from(context), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoWatermarkViewBindingInflate, "inflate(...)");
        this.binding = onfidoWatermarkViewBindingInflate;
        setWillNotDraw(false);
        setImportantForAccessibility(2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OnfidoWatermark);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "obtainStyledAttributes(...)");
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R.styleable.OnfidoWatermark_onfidoDarkBackground, false);
        typedArrayObtainStyledAttributes.recycle();
        configureView(z);
    }

    public /* synthetic */ WatermarkView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
