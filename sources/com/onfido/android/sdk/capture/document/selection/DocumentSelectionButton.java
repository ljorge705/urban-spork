package com.onfido.android.sdk.capture.document.selection;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.databinding.OnfidoDocumentSelectionButtonBinding;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0014"}, d2 = {"Lcom/onfido/android/sdk/capture/document/selection/DocumentSelectionButton;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoDocumentSelectionButtonBinding;", "documentName", "Landroid/widget/TextView;", "getDocumentName$onfido_capture_sdk_core_release", "()Landroid/widget/TextView;", "onInitializeAccessibilityNodeInfo", "", "info", "Landroid/view/accessibility/AccessibilityNodeInfo;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentSelectionButton extends RelativeLayout {
    private static final Companion Companion = new Companion(null);
    private static final int ICON_RESOURCE_DEFAULT_VALUE = -1;
    private final OnfidoDocumentSelectionButtonBinding binding;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/document/selection/DocumentSelectionButton$Companion;", "", "()V", "ICON_RESOURCE_DEFAULT_VALUE", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DocumentSelectionButton(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final TextView getDocumentName$onfido_capture_sdk_core_release() {
        TextView documentName = this.binding.documentName;
        Intrinsics.checkNotNullExpressionValue(documentName, "documentName");
        return documentName;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        if (info != null) {
            info.setClassName(Button.class.getName());
        }
        if (info == null) {
            return;
        }
        info.setContentDescription(this.binding.documentName.getText());
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DocumentSelectionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DocumentSelectionButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OnfidoDocumentSelectionButton);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "obtainStyledAttributes(...)");
        String string = typedArrayObtainStyledAttributes.getString(R.styleable.OnfidoDocumentSelectionButton_onfidoTitle);
        String string2 = typedArrayObtainStyledAttributes.getString(R.styleable.OnfidoDocumentSelectionButton_onfidoDescription);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.OnfidoDocumentSelectionButton_onfidoIcon, -1);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R.styleable.OnfidoDocumentSelectionButton_onfidoTitleBold, false);
        OnfidoDocumentSelectionButtonBinding onfidoDocumentSelectionButtonBindingInflate = OnfidoDocumentSelectionButtonBinding.inflate(LayoutInflater.from(context), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoDocumentSelectionButtonBindingInflate, "inflate(...)");
        this.binding = onfidoDocumentSelectionButtonBindingInflate;
        onfidoDocumentSelectionButtonBindingInflate.documentName.setText(string);
        onfidoDocumentSelectionButtonBindingInflate.documentDescription.setText(string2);
        if (resourceId != -1) {
            onfidoDocumentSelectionButtonBindingInflate.icon.setImageDrawable(ContextCompat.getDrawable(context, resourceId));
        }
        CharSequence text = onfidoDocumentSelectionButtonBindingInflate.documentDescription.getText();
        Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
        if (text.length() == 0) {
            TextView documentDescription = onfidoDocumentSelectionButtonBindingInflate.documentDescription;
            Intrinsics.checkNotNullExpressionValue(documentDescription, "documentDescription");
            ViewExtensionsKt.toGone$default(documentDescription, false, 1, null);
        }
        if (z) {
            onfidoDocumentSelectionButtonBindingInflate.documentName.setTypeface(null, 1);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public /* synthetic */ DocumentSelectionButton(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
