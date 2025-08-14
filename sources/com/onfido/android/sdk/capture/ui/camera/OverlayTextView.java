package com.onfido.android.sdk.capture.ui.camera;

import android.content.Context;
import android.content.res.Resources;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.databinding.OnfidoOverlayTextViewBinding;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.model.DocumentTypeUIModel;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.android.sdk.capture.utils.Visibility;
import com.onfido.api.client.data.DocSide;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import io.sentry.Session;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 ;2\u00020\u0001:\u0001;B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0010\u001a\u00020\u0011H\u0002J(\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007H\u0014J'\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0000¢\u0006\u0002\b\u001eJ\u0016\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\u001f\u0010 \u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0000¢\u0006\u0002\b!J\r\u0010\"\u001a\u00020\u0011H\u0000¢\u0006\u0002\b#J+\u0010$\u001a\u00020\u00112\b\b\u0001\u0010%\u001a\u00020\u00072\b\b\u0003\u0010&\u001a\u00020\u00072\b\b\u0002\u0010'\u001a\u00020\u000eH\u0000¢\u0006\u0002\b(J&\u0010)\u001a\u00020\u00112\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020\u000e2\b\b\u0002\u0010-\u001a\u00020\u000eH\u0002J\u001c\u0010.\u001a\u00020\u00112\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u00101\u001a\u000200H\u0002J\u0017\u00102\u001a\u00020\u00112\b\b\u0001\u00103\u001a\u00020\u0007H\u0000¢\u0006\u0002\b4J)\u00105\u001a\u00020\u00112\u0006\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020\u000e2\b\b\u0002\u0010-\u001a\u00020\u000eH\u0000¢\u0006\u0002\b6J\r\u00107\u001a\u00020\u0011H\u0000¢\u0006\u0002\b8J(\u00109\u001a\u00020\u0011*\u00020:2\u0006\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020\u000e2\b\b\u0002\u0010-\u001a\u00020\u000eH\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/OverlayTextView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoOverlayTextViewBinding;", "primaryTextColor", "primaryTextColorDark", "secondaryTextTruncatable", "", "sideMargin", "applyTruncationStrategy", "", "onSizeChanged", Constants.INAPP_WINDOW, "h", "oldw", "oldh", "setCaptureOverlayText", ReactNativeBridgeUtiles.KEY_CAPTURE_TYPE, "Lcom/onfido/android/sdk/capture/ui/CaptureType;", "documentTypeUIModel", "Lcom/onfido/android/sdk/capture/ui/model/DocumentTypeUIModel;", "documentSide", "Lcom/onfido/api/client/data/DocSide;", "setCaptureOverlayText$onfido_capture_sdk_core_release", "setConfirmationOverlayText", "setDocumentOverlayText", "setDocumentOverlayText$onfido_capture_sdk_core_release", "setLivenessOverlayText", "setLivenessOverlayText$onfido_capture_sdk_core_release", "setMainText", "mainTextResId", "contentDescription", "readAccessibility", "setMainText$onfido_capture_sdk_core_release", "setMainTextVisibility", ViewHierarchyNode.JsonKeys.VISIBILITY, "Lcom/onfido/android/sdk/capture/utils/Visibility;", "withAnimation", "withMargin", "setOverlayText", "mainText", "", "secondaryText", "setSecondaryText", "secondaryTextResID", "setSecondaryText$onfido_capture_sdk_core_release", "setSecondaryTextVisibility", "setSecondaryTextVisibility$onfido_capture_sdk_core_release", "setSelfieCaptureOverlayText", "setSelfieCaptureOverlayText$onfido_capture_sdk_core_release", "setVisibilityWithMargin", "Landroid/widget/TextView;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OverlayTextView extends RelativeLayout {
    private static final Companion Companion = new Companion(null);
    private final OnfidoOverlayTextViewBinding binding;
    private final int primaryTextColor;
    private final int primaryTextColorDark;
    private boolean secondaryTextTruncatable;
    private final int sideMargin;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0002J\f\u0010\u0006\u001a\u00020\u0004*\u00020\u0005H\u0002¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/OverlayTextView$Companion;", "", "()V", "disableScrolling", "", "Landroid/widget/TextView;", "enableScrolling", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void disableScrolling(TextView textView) {
            textView.setMovementMethod(null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void enableScrolling(TextView textView) {
            textView.setMovementMethod(new ScrollingMovementMethod());
            textView.setMaxLines(Integer.MAX_VALUE);
            textView.setEllipsize(null);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Visibility.values().length];
            try {
                iArr[Visibility.VISIBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Visibility.INVISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Visibility.GONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[CaptureType.values().length];
            try {
                iArr2[CaptureType.DOCUMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[CaptureType.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public OverlayTextView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    private final void applyTruncationStrategy() {
        int height = getHeight() - this.binding.mainText.getHeight();
        ViewGroup.LayoutParams layoutParams = this.binding.secondaryText.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        int i = height - ((RelativeLayout.LayoutParams) layoutParams).topMargin;
        TextView secondaryText = this.binding.secondaryText;
        Intrinsics.checkNotNullExpressionValue(secondaryText, "secondaryText");
        ViewExtensionsKt.setMaxLinesInHeight(secondaryText, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onSizeChanged$lambda$9(OverlayTextView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.applyTruncationStrategy();
    }

    public static /* synthetic */ void setMainText$onfido_capture_sdk_core_release$default(OverlayTextView overlayTextView, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = i;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        overlayTextView.setMainText$onfido_capture_sdk_core_release(i, i2, z);
    }

    private final void setMainTextVisibility(Visibility visibility, boolean withAnimation, boolean withMargin) {
        TextView mainText = this.binding.mainText;
        Intrinsics.checkNotNullExpressionValue(mainText, "mainText");
        setVisibilityWithMargin(mainText, visibility, withAnimation, withMargin);
    }

    static /* synthetic */ void setMainTextVisibility$default(OverlayTextView overlayTextView, Visibility visibility, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            visibility = Visibility.VISIBLE;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        overlayTextView.setMainTextVisibility(visibility, z, z2);
    }

    private final void setOverlayText(String mainText, String secondaryText) {
        this.binding.mainText.setText(mainText);
        this.binding.secondaryText.setText(secondaryText);
    }

    static /* synthetic */ void setOverlayText$default(OverlayTextView overlayTextView, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        if ((i & 2) != 0) {
            str2 = "";
        }
        overlayTextView.setOverlayText(str, str2);
    }

    public static /* synthetic */ void setSecondaryTextVisibility$onfido_capture_sdk_core_release$default(OverlayTextView overlayTextView, Visibility visibility, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        overlayTextView.setSecondaryTextVisibility$onfido_capture_sdk_core_release(visibility, z, z2);
    }

    private final void setVisibilityWithMargin(TextView textView, Visibility visibility, boolean z, boolean z2) {
        int i = WhenMappings.$EnumSwitchMapping$0[visibility.ordinal()];
        if (i == 1) {
            ViewExtensionsKt.toVisible(textView, z);
        } else if (i == 2) {
            ViewExtensionsKt.toInvisible(textView, z);
        } else if (i == 3) {
            ViewExtensionsKt.toGone(textView, z);
        }
        if (z2) {
            ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.setMarginStart(this.sideMargin);
            layoutParams2.setMarginEnd(this.sideMargin);
        }
    }

    static /* synthetic */ void setVisibilityWithMargin$default(OverlayTextView overlayTextView, TextView textView, Visibility visibility, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        overlayTextView.setVisibilityWithMargin(textView, visibility, z, z2);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (this.secondaryTextTruncatable) {
            post(new Runnable() { // from class: com.onfido.android.sdk.capture.ui.camera.OverlayTextView$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OverlayTextView.onSizeChanged$lambda$9(this.f$0);
                }
            });
        }
    }

    public final void setCaptureOverlayText$onfido_capture_sdk_core_release(CaptureType captureType, DocumentTypeUIModel documentTypeUIModel, DocSide documentSide) throws Resources.NotFoundException {
        Resources resources;
        int i;
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        Intrinsics.checkNotNullParameter(documentTypeUIModel, "documentTypeUIModel");
        this.secondaryTextTruncatable = false;
        Companion companion = Companion;
        TextView secondaryText = this.binding.secondaryText;
        Intrinsics.checkNotNullExpressionValue(secondaryText, "secondaryText");
        companion.disableScrolling(secondaryText);
        int i2 = WhenMappings.$EnumSwitchMapping$1[captureType.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                resources = getResources();
                i = R.string.onfido_selfie_capture_body;
            } else {
                ViewGroup.LayoutParams layoutParams = this.binding.mainText.getLayoutParams();
                Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                int i3 = this.sideMargin;
                layoutParams2.setMargins(i3, layoutParams2.topMargin, i3, layoutParams2.bottomMargin);
                resources = getResources();
                i = R.string.onfido_video_capture_body;
            }
            String string = resources.getString(i);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            setOverlayText$default(this, null, string, 1, null);
            TextView mainText = this.binding.mainText;
            Intrinsics.checkNotNullExpressionValue(mainText, "mainText");
            ViewExtensionsKt.toGone$default(mainText, false, 1, null);
        } else {
            DocSide docSide = DocSide.FRONT;
            int captureFrontPrimaryLabel = documentSide == docSide ? documentTypeUIModel.getCaptureFrontPrimaryLabel() : documentTypeUIModel.getCaptureBackPrimaryLabel();
            int captureFrontSecondaryLabel = documentSide == docSide ? documentTypeUIModel.getCaptureFrontSecondaryLabel() : documentTypeUIModel.getCaptureBackSecondaryLabel();
            String string2 = getResources().getString(captureFrontPrimaryLabel);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            String string3 = getResources().getString(captureFrontSecondaryLabel);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            setOverlayText(string2, string3);
            ViewGroup.LayoutParams layoutParams3 = this.binding.secondaryText.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams3, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) layoutParams3;
            int i4 = this.sideMargin;
            layoutParams4.setMargins(i4, layoutParams4.topMargin, i4, layoutParams4.bottomMargin);
            TextView mainText2 = this.binding.mainText;
            Intrinsics.checkNotNullExpressionValue(mainText2, "mainText");
            ViewExtensionsKt.toVisible$default(mainText2, false, 1, null);
        }
        TextView secondaryText2 = this.binding.secondaryText;
        Intrinsics.checkNotNullExpressionValue(secondaryText2, "secondaryText");
        ViewExtensionsKt.toVisible$default(secondaryText2, false, 1, null);
        this.binding.mainText.setTextColor(this.primaryTextColorDark);
        this.binding.secondaryText.setTextColor(this.primaryTextColorDark);
        requestLayout();
    }

    public final void setConfirmationOverlayText(CaptureType captureType, DocumentTypeUIModel documentTypeUIModel) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        Intrinsics.checkNotNullParameter(documentTypeUIModel, "documentTypeUIModel");
        String string = getResources().getString(captureType == CaptureType.DOCUMENT ? documentTypeUIModel.getReadabilityCheckLabel() : R.string.onfido_selfie_confirmation_body);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        setOverlayText$default(this, null, string, 1, null);
        ViewGroup.LayoutParams layoutParams = this.binding.secondaryText.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        int i = this.sideMargin;
        layoutParams2.setMargins(i, layoutParams2.topMargin, i, layoutParams2.bottomMargin);
        this.binding.mainText.setTextColor(this.primaryTextColor);
        this.binding.secondaryText.setTextColor(this.primaryTextColor);
        TextView mainText = this.binding.mainText;
        Intrinsics.checkNotNullExpressionValue(mainText, "mainText");
        ViewExtensionsKt.toGone$default(mainText, false, 1, null);
        TextView secondaryText = this.binding.secondaryText;
        Intrinsics.checkNotNullExpressionValue(secondaryText, "secondaryText");
        ViewExtensionsKt.toVisible$default(secondaryText, false, 1, null);
        Companion companion = Companion;
        TextView secondaryText2 = this.binding.secondaryText;
        Intrinsics.checkNotNullExpressionValue(secondaryText2, "secondaryText");
        companion.enableScrolling(secondaryText2);
        this.secondaryTextTruncatable = false;
    }

    public final void setDocumentOverlayText$onfido_capture_sdk_core_release(DocumentTypeUIModel documentTypeUIModel, DocSide documentSide) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(documentTypeUIModel, "documentTypeUIModel");
        this.secondaryTextTruncatable = false;
        Companion companion = Companion;
        TextView secondaryText = this.binding.secondaryText;
        Intrinsics.checkNotNullExpressionValue(secondaryText, "secondaryText");
        companion.disableScrolling(secondaryText);
        DocSide docSide = DocSide.FRONT;
        int captureFrontPrimaryLabel = documentSide == docSide ? documentTypeUIModel.getCaptureFrontPrimaryLabel() : documentTypeUIModel.getCaptureBackPrimaryLabel();
        int captureFrontSecondaryLabel = documentSide == docSide ? documentTypeUIModel.getCaptureFrontSecondaryLabel() : documentTypeUIModel.getCaptureBackSecondaryLabel();
        String string = getResources().getString(captureFrontPrimaryLabel);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = getResources().getString(captureFrontSecondaryLabel);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        setOverlayText(string, string2);
        ViewGroup.LayoutParams layoutParams = this.binding.secondaryText.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        int i = this.sideMargin;
        layoutParams2.setMargins(i, layoutParams2.topMargin, i, layoutParams2.bottomMargin);
        TextView mainText = this.binding.mainText;
        Intrinsics.checkNotNullExpressionValue(mainText, "mainText");
        ViewExtensionsKt.toVisible$default(mainText, false, 1, null);
        TextView secondaryText2 = this.binding.secondaryText;
        Intrinsics.checkNotNullExpressionValue(secondaryText2, "secondaryText");
        ViewExtensionsKt.toVisible$default(secondaryText2, false, 1, null);
        this.binding.mainText.setTextColor(this.primaryTextColorDark);
        this.binding.secondaryText.setTextColor(this.primaryTextColorDark);
        requestLayout();
    }

    public final void setLivenessOverlayText$onfido_capture_sdk_core_release() throws Resources.NotFoundException {
        this.secondaryTextTruncatable = false;
        Companion companion = Companion;
        TextView secondaryText = this.binding.secondaryText;
        Intrinsics.checkNotNullExpressionValue(secondaryText, "secondaryText");
        companion.disableScrolling(secondaryText);
        ViewGroup.LayoutParams layoutParams = this.binding.mainText.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        int i = this.sideMargin;
        layoutParams2.setMargins(i, layoutParams2.topMargin, i, layoutParams2.bottomMargin);
        String string = getResources().getString(R.string.onfido_video_capture_body);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        setOverlayText$default(this, null, string, 1, null);
        TextView mainText = this.binding.mainText;
        Intrinsics.checkNotNullExpressionValue(mainText, "mainText");
        ViewExtensionsKt.toGone$default(mainText, false, 1, null);
        TextView secondaryText2 = this.binding.secondaryText;
        Intrinsics.checkNotNullExpressionValue(secondaryText2, "secondaryText");
        ViewExtensionsKt.toVisible$default(secondaryText2, false, 1, null);
        this.binding.secondaryText.setTextColor(this.primaryTextColorDark);
        requestLayout();
    }

    public final void setMainText$onfido_capture_sdk_core_release(int mainTextResId, int contentDescription, boolean readAccessibility) {
        String string = getContext().getString(mainTextResId);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this.binding.mainText.setText(string);
        this.binding.mainText.setContentDescription(getContext().getString(contentDescription));
        if (readAccessibility) {
            this.binding.mainText.performAccessibilityAction(64, null);
            this.binding.mainText.sendAccessibilityEvent(4);
        }
        this.binding.mainText.setTextColor(this.primaryTextColorDark);
        setMainTextVisibility$default(this, null, false, false, 7, null);
        requestLayout();
    }

    public final void setSecondaryText$onfido_capture_sdk_core_release(int secondaryTextResID) {
        String string = getContext().getString(secondaryTextResID);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        setOverlayText$default(this, null, string, 1, null);
        this.binding.secondaryText.setTextColor(this.primaryTextColorDark);
        setSecondaryTextVisibility$onfido_capture_sdk_core_release(Visibility.VISIBLE, false, false);
        requestLayout();
    }

    public final void setSecondaryTextVisibility$onfido_capture_sdk_core_release(Visibility visibility, boolean withAnimation, boolean withMargin) {
        Intrinsics.checkNotNullParameter(visibility, "visibility");
        TextView secondaryText = this.binding.secondaryText;
        Intrinsics.checkNotNullExpressionValue(secondaryText, "secondaryText");
        setVisibilityWithMargin(secondaryText, visibility, withAnimation, withMargin);
    }

    public final void setSelfieCaptureOverlayText$onfido_capture_sdk_core_release() throws Resources.NotFoundException {
        this.secondaryTextTruncatable = false;
        Companion companion = Companion;
        TextView secondaryText = this.binding.secondaryText;
        Intrinsics.checkNotNullExpressionValue(secondaryText, "secondaryText");
        companion.disableScrolling(secondaryText);
        String string = getResources().getString(R.string.onfido_selfie_capture_body);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        setOverlayText$default(this, null, string, 1, null);
        TextView mainText = this.binding.mainText;
        Intrinsics.checkNotNullExpressionValue(mainText, "mainText");
        ViewExtensionsKt.toGone$default(mainText, false, 1, null);
        TextView secondaryText2 = this.binding.secondaryText;
        Intrinsics.checkNotNullExpressionValue(secondaryText2, "secondaryText");
        ViewExtensionsKt.toVisible$default(secondaryText2, false, 1, null);
        this.binding.mainText.setTextColor(this.primaryTextColorDark);
        this.binding.secondaryText.setTextColor(this.primaryTextColorDark);
        requestLayout();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public OverlayTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OverlayTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.secondaryTextTruncatable = true;
        this.primaryTextColor = ContextUtilsKt.colorFromAttr(context, R.attr.onfidoColorContentMain);
        this.primaryTextColorDark = ContextUtilsKt.colorFromAttr(context, R.attr.onfidoColorContentMainDark);
        OnfidoOverlayTextViewBinding onfidoOverlayTextViewBindingInflate = OnfidoOverlayTextViewBinding.inflate(LayoutInflater.from(context), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoOverlayTextViewBindingInflate, "inflate(...)");
        this.binding = onfidoOverlayTextViewBindingInflate;
        TextView textView = onfidoOverlayTextViewBindingInflate.secondaryText;
        textView.setTextSize(0, Math.min(textView.getTextSize(), onfidoOverlayTextViewBindingInflate.mainText.getTextSize()));
        this.sideMargin = getResources().getDimensionPixelOffset(R.dimen.onfido_document_capture_text_side_margin);
    }

    public /* synthetic */ OverlayTextView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
