package com.onfido.android.sdk.capture.validation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import com.clevertap.android.sdk.Constants;
import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.databinding.OnfidoCaptureValidationBubbleBinding;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.StringExtensionsKt;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.android.sdk.capture.utils.Visibility;
import com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.sentry.Session;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 W2\u00020\u00012\u00020\u0002:\u0006VWXYZ[B%\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ$\u0010.\u001a\u00020/2\b\b\u0001\u00100\u001a\u00020\b2\u0006\u00101\u001a\u0002022\b\b\u0001\u00103\u001a\u00020\bH\u0002J\b\u00104\u001a\u00020\u001dH\u0002J\b\u00105\u001a\u00020\u000bH\u0002J\r\u00106\u001a\u00020\u000bH\u0000¢\u0006\u0002\b7J\b\u00108\u001a\u00020/H\u0014J\b\u00109\u001a\u00020/H\u0014J\r\u0010:\u001a\u00020;H\u0000¢\u0006\u0002\b<J\u0010\u0010=\u001a\u00020/2\u0006\u0010>\u001a\u00020?H\u0016J\u0010\u0010@\u001a\u00020/2\u0006\u0010A\u001a\u00020BH\u0002J\u0012\u0010C\u001a\u00020/2\b\b\u0001\u00100\u001a\u00020\bH\u0002J\u001f\u0010D\u001a\u00020/2\u0006\u0010E\u001a\u00020F2\b\b\u0002\u0010G\u001a\u00020\u000bH\u0000¢\u0006\u0002\bHJ\u0015\u0010I\u001a\u00020/2\u0006\u0010'\u001a\u00020(H\u0000¢\u0006\u0002\bJJ#\u0010K\u001a\u00020/2\b\b\u0001\u0010L\u001a\u00020\b2\n\b\u0003\u0010M\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0002\u0010NJ\u001f\u0010O\u001a\u00020/2\u0006\u0010P\u001a\u00020+2\b\b\u0002\u0010G\u001a\u00020\u000bH\u0000¢\u0006\u0002\bQJ\u001c\u0010R\u001a\u00020/2\b\b\u0001\u00103\u001a\u00020\b2\b\b\u0001\u0010S\u001a\u00020\bH\u0002J\u000e\u0010T\u001a\b\u0012\u0004\u0012\u00020+0UH\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u00020\r8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u00020\u00178\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010!\u001a\u00020\"8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b#\u0010$R\u000e\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010)\u001a\u0015\u0012\f\u0012\n ,*\u0004\u0018\u00010+0+0*¢\u0006\u0002\b-X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\\"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble;", "Landroid/widget/RelativeLayout;", "Lcom/onfido/android/sdk/capture/validation/CaptureValidationBubblePresenter$View;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "announceIcon", "", "announcementService", "Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "getAnnouncementService$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "setAnnouncementService$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;)V", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoCaptureValidationBubbleBinding;", "bubbleMode", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$BubbleMode;", "captureValidationBubblePresenterFactory", "Lcom/onfido/android/sdk/capture/validation/CaptureValidationBubblePresenter$Factory;", "getCaptureValidationBubblePresenterFactory$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/validation/CaptureValidationBubblePresenter$Factory;", "setCaptureValidationBubblePresenterFactory$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/validation/CaptureValidationBubblePresenter$Factory;)V", "displayedText", "", "getDisplayedText", "()Ljava/lang/String;", "isTightMode", "presenter", "Lcom/onfido/android/sdk/capture/validation/CaptureValidationBubblePresenter;", "getPresenter", "()Lcom/onfido/android/sdk/capture/validation/CaptureValidationBubblePresenter;", "presenter$delegate", "Lkotlin/Lazy;", "state", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$State;", "visibilityCommandBehaviorSubject", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$VisibilityCommand;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "changeBackground", "", "color", "view", "Landroid/view/View;", "drawable", "getDisplayedTextInternal", "isLocked", "isVisible", "isVisible$onfido_capture_sdk_core_release", "onAttachedToWindow", "onDetachedFromWindow", "readingTimeMilliseconds", "", "readingTimeMilliseconds$onfido_capture_sdk_core_release", "renderVisibility", ViewHierarchyNode.JsonKeys.VISIBILITY, "Lcom/onfido/android/sdk/capture/utils/Visibility;", "setBubbleTitleTypeface", "titleTextView", "Landroid/widget/TextView;", "setColor", "setContent", "content", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$Content;", "shouldIgnoreLock", "setContent$onfido_capture_sdk_core_release", "setState", "setState$onfido_capture_sdk_core_release", "setText", "title", KeychainModule.AuthPromptOptions.SUBTITLE, "(ILjava/lang/Integer;)V", "setVisibilityCommand", "visibilityCommand", "setVisibilityCommand$onfido_capture_sdk_core_release", "setWarningIcon", "contentDescription", "visibilityChange", "Lio/reactivex/rxjava3/core/Observable;", "BubbleMode", "Companion", "Content", "FocusType", "State", "VisibilityCommand", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoCaptureValidationBubble extends RelativeLayout implements CaptureValidationBubblePresenter.View {
    private static final float ALPHA_INVISIBLE = 0.0f;
    private static final float ALPHA_VISIBLE = 1.0f;
    private static final Companion Companion = new Companion(null);
    private static final String FONT_FAMILY_SANS_SERIF_MEDIUM = "sans-serif-medium";
    private static final int POST_CAPTURE_BUBBLE_MODE = 1;
    private static final int REALTIME_BUBBLE_MODE = 0;
    private final boolean announceIcon;

    @Inject
    public AnnouncementService announcementService;
    private final OnfidoCaptureValidationBubbleBinding binding;
    private final BubbleMode bubbleMode;

    @Inject
    public CaptureValidationBubblePresenter.Factory captureValidationBubblePresenterFactory;
    private final boolean isTightMode;

    /* renamed from: presenter$delegate, reason: from kotlin metadata */
    private final Lazy presenter;
    private State state;
    private final BehaviorSubject<VisibilityCommand> visibilityCommandBehaviorSubject;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$BubbleMode;", "", "(Ljava/lang/String;I)V", "LIVE_CAPTURE", "POST_CAPTURE", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class BubbleMode {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ BubbleMode[] $VALUES;
        public static final BubbleMode LIVE_CAPTURE = new BubbleMode("LIVE_CAPTURE", 0);
        public static final BubbleMode POST_CAPTURE = new BubbleMode("POST_CAPTURE", 1);

        private static final /* synthetic */ BubbleMode[] $values() {
            return new BubbleMode[]{LIVE_CAPTURE, POST_CAPTURE};
        }

        static {
            BubbleMode[] bubbleModeArr$values = $values();
            $VALUES = bubbleModeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(bubbleModeArr$values);
        }

        private BubbleMode(String str, int i) {
        }

        public static EnumEntries<BubbleMode> getEntries() {
            return $ENTRIES;
        }

        public static BubbleMode valueOf(String str) {
            return (BubbleMode) Enum.valueOf(BubbleMode.class, str);
        }

        public static BubbleMode[] values() {
            return (BubbleMode[]) $VALUES.clone();
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$Companion;", "", "()V", "ALPHA_INVISIBLE", "", "ALPHA_VISIBLE", "FONT_FAMILY_SANS_SERIF_MEDIUM", "", "POST_CAPTURE_BUBBLE_MODE", "", "REALTIME_BUBBLE_MODE", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0002\n\u000bB\u0007\b\u0004¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00048aX \u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048aX \u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t\u0082\u0001\u0002\f\r¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$Content;", "", "()V", KeychainModule.AuthPromptOptions.SUBTITLE, "", "getSubtitle$onfido_capture_sdk_core_release", "()Ljava/lang/Integer;", "title", "getTitle$onfido_capture_sdk_core_release", "()I", "Error", "Info", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$Content$Error;", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$Content$Info;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class Content {

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000e\u0010\u000b\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b\fJ\u0012\u0010\r\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0004\b\u000e\u0010\u0007J$\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0090\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$Content$Error;", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$Content;", "title", "", KeychainModule.AuthPromptOptions.SUBTITLE, "(ILjava/lang/Integer;)V", "getSubtitle$onfido_capture_sdk_core_release", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTitle$onfido_capture_sdk_core_release", "()I", "component1", "component1$onfido_capture_sdk_core_release", "component2", "component2$onfido_capture_sdk_core_release", Constants.COPY_TYPE, "(ILjava/lang/Integer;)Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$Content$Error;", "equals", "", "other", "", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Error extends Content {
            private final Integer subtitle;
            private final int title;

            public Error(int i, Integer num) {
                super(null);
                this.title = i;
                this.subtitle = num;
            }

            public static /* synthetic */ Error copy$default(Error error, int i, Integer num, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    i = error.title;
                }
                if ((i2 & 2) != 0) {
                    num = error.subtitle;
                }
                return error.copy(i, num);
            }

            /* renamed from: component1$onfido_capture_sdk_core_release, reason: from getter */
            public final int getTitle() {
                return this.title;
            }

            /* renamed from: component2$onfido_capture_sdk_core_release, reason: from getter */
            public final Integer getSubtitle() {
                return this.subtitle;
            }

            public final Error copy(int title, Integer subtitle) {
                return new Error(title, subtitle);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Error)) {
                    return false;
                }
                Error error = (Error) other;
                return this.title == error.title && Intrinsics.areEqual(this.subtitle, error.subtitle);
            }

            @Override // com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble.Content
            public Integer getSubtitle$onfido_capture_sdk_core_release() {
                return this.subtitle;
            }

            @Override // com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble.Content
            public int getTitle$onfido_capture_sdk_core_release() {
                return this.title;
            }

            public int hashCode() {
                int iHashCode = Integer.hashCode(this.title) * 31;
                Integer num = this.subtitle;
                return iHashCode + (num == null ? 0 : num.hashCode());
            }

            public String toString() {
                return "Error(title=" + this.title + ", subtitle=" + this.subtitle + ')';
            }

            public /* synthetic */ Error(int i, Integer num, int i2, DefaultConstructorMarker defaultConstructorMarker) {
                this(i, (i2 & 2) != 0 ? null : num);
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000e\u0010\u000b\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b\fJ\u0012\u0010\r\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0004\b\u000e\u0010\u0007J$\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0090\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$Content$Info;", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$Content;", "title", "", KeychainModule.AuthPromptOptions.SUBTITLE, "(ILjava/lang/Integer;)V", "getSubtitle$onfido_capture_sdk_core_release", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTitle$onfido_capture_sdk_core_release", "()I", "component1", "component1$onfido_capture_sdk_core_release", "component2", "component2$onfido_capture_sdk_core_release", Constants.COPY_TYPE, "(ILjava/lang/Integer;)Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$Content$Info;", "equals", "", "other", "", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Info extends Content {
            private final Integer subtitle;
            private final int title;

            public Info(int i, Integer num) {
                super(null);
                this.title = i;
                this.subtitle = num;
            }

            public static /* synthetic */ Info copy$default(Info info, int i, Integer num, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    i = info.title;
                }
                if ((i2 & 2) != 0) {
                    num = info.subtitle;
                }
                return info.copy(i, num);
            }

            /* renamed from: component1$onfido_capture_sdk_core_release, reason: from getter */
            public final int getTitle() {
                return this.title;
            }

            /* renamed from: component2$onfido_capture_sdk_core_release, reason: from getter */
            public final Integer getSubtitle() {
                return this.subtitle;
            }

            public final Info copy(int title, Integer subtitle) {
                return new Info(title, subtitle);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Info)) {
                    return false;
                }
                Info info = (Info) other;
                return this.title == info.title && Intrinsics.areEqual(this.subtitle, info.subtitle);
            }

            @Override // com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble.Content
            public Integer getSubtitle$onfido_capture_sdk_core_release() {
                return this.subtitle;
            }

            @Override // com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble.Content
            public int getTitle$onfido_capture_sdk_core_release() {
                return this.title;
            }

            public int hashCode() {
                int iHashCode = Integer.hashCode(this.title) * 31;
                Integer num = this.subtitle;
                return iHashCode + (num == null ? 0 : num.hashCode());
            }

            public String toString() {
                return "Info(title=" + this.title + ", subtitle=" + this.subtitle + ')';
            }

            public /* synthetic */ Info(int i, Integer num, int i2, DefaultConstructorMarker defaultConstructorMarker) {
                this(i, (i2 & 2) != 0 ? null : num);
            }
        }

        private Content() {
        }

        public abstract Integer getSubtitle$onfido_capture_sdk_core_release();

        public abstract int getTitle$onfido_capture_sdk_core_release();

        public /* synthetic */ Content(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0001\u0004¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$FocusType;", "", "()V", "AnnounceContent", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$FocusType$AnnounceContent;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class FocusType {

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$FocusType$AnnounceContent;", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$FocusType;", "shouldInterrupt", "", "(Z)V", "getShouldInterrupt", "()Z", "component1", Constants.COPY_TYPE, "equals", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class AnnounceContent extends FocusType {
            private final boolean shouldInterrupt;

            public AnnounceContent() {
                this(false, 1, null);
            }

            public static /* synthetic */ AnnounceContent copy$default(AnnounceContent announceContent, boolean z, int i, Object obj) {
                if ((i & 1) != 0) {
                    z = announceContent.shouldInterrupt;
                }
                return announceContent.copy(z);
            }

            /* renamed from: component1, reason: from getter */
            public final boolean getShouldInterrupt() {
                return this.shouldInterrupt;
            }

            public final AnnounceContent copy(boolean shouldInterrupt) {
                return new AnnounceContent(shouldInterrupt);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof AnnounceContent) && this.shouldInterrupt == ((AnnounceContent) other).shouldInterrupt;
            }

            public final boolean getShouldInterrupt() {
                return this.shouldInterrupt;
            }

            public int hashCode() {
                return Boolean.hashCode(this.shouldInterrupt);
            }

            public String toString() {
                return "AnnounceContent(shouldInterrupt=" + this.shouldInterrupt + ')';
            }

            public AnnounceContent(boolean z) {
                super(null);
                this.shouldInterrupt = z;
            }

            public /* synthetic */ AnnounceContent(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? false : z);
            }
        }

        private FocusType() {
        }

        public /* synthetic */ FocusType(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$State;", "", "(Ljava/lang/String;I)V", "UNLOCKED", "SOFT_LOCK", "HARD_LOCK", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class State {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ State[] $VALUES;
        public static final State UNLOCKED = new State("UNLOCKED", 0);
        public static final State SOFT_LOCK = new State("SOFT_LOCK", 1);
        public static final State HARD_LOCK = new State("HARD_LOCK", 2);

        private static final /* synthetic */ State[] $values() {
            return new State[]{UNLOCKED, SOFT_LOCK, HARD_LOCK};
        }

        static {
            State[] stateArr$values = $values();
            $VALUES = stateArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(stateArr$values);
        }

        private State(String str, int i) {
        }

        public static EnumEntries<State> getEntries() {
            return $ENTRIES;
        }

        public static State valueOf(String str) {
            return (State) Enum.valueOf(State.class, str);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0003\u0007\b\tB\u0007\b\u0004¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X \u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0003\n\u000b\f¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$VisibilityCommand;", "", "()V", ViewHierarchyNode.JsonKeys.VISIBILITY, "Lcom/onfido/android/sdk/capture/utils/Visibility;", "getVisibility$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/utils/Visibility;", "Gone", "Invisible", "Visible", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$VisibilityCommand$Gone;", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$VisibilityCommand$Invisible;", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$VisibilityCommand$Visible;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class VisibilityCommand {

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$VisibilityCommand$Gone;", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$VisibilityCommand;", "()V", ViewHierarchyNode.JsonKeys.VISIBILITY, "Lcom/onfido/android/sdk/capture/utils/Visibility;", "getVisibility$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/utils/Visibility;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Gone extends VisibilityCommand {
            public static final Gone INSTANCE = new Gone();
            private static final Visibility visibility = Visibility.GONE;

            private Gone() {
                super(null);
            }

            @Override // com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble.VisibilityCommand
            /* renamed from: getVisibility$onfido_capture_sdk_core_release */
            public Visibility getVisibility() {
                return visibility;
            }
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$VisibilityCommand$Invisible;", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$VisibilityCommand;", "()V", ViewHierarchyNode.JsonKeys.VISIBILITY, "Lcom/onfido/android/sdk/capture/utils/Visibility;", "getVisibility$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/utils/Visibility;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Invisible extends VisibilityCommand {
            public static final Invisible INSTANCE = new Invisible();
            private static final Visibility visibility = Visibility.INVISIBLE;

            private Invisible() {
                super(null);
            }

            @Override // com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble.VisibilityCommand
            /* renamed from: getVisibility$onfido_capture_sdk_core_release */
            public Visibility getVisibility() {
                return visibility;
            }
        }

        @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$VisibilityCommand$Visible;", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$VisibilityCommand;", "focusType", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$FocusType;", "(Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$FocusType;)V", "getFocusType", "()Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$FocusType;", ViewHierarchyNode.JsonKeys.VISIBILITY, "Lcom/onfido/android/sdk/capture/utils/Visibility;", "getVisibility$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/utils/Visibility;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Visible extends VisibilityCommand {
            private final FocusType focusType;
            private final Visibility visibility;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Visible(FocusType focusType) {
                super(null);
                Intrinsics.checkNotNullParameter(focusType, "focusType");
                this.focusType = focusType;
                this.visibility = Visibility.VISIBLE;
            }

            public static /* synthetic */ Visible copy$default(Visible visible, FocusType focusType, int i, Object obj) {
                if ((i & 1) != 0) {
                    focusType = visible.focusType;
                }
                return visible.copy(focusType);
            }

            /* renamed from: component1, reason: from getter */
            public final FocusType getFocusType() {
                return this.focusType;
            }

            public final Visible copy(FocusType focusType) {
                Intrinsics.checkNotNullParameter(focusType, "focusType");
                return new Visible(focusType);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Visible) && Intrinsics.areEqual(this.focusType, ((Visible) other).focusType);
            }

            public final FocusType getFocusType() {
                return this.focusType;
            }

            @Override // com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble.VisibilityCommand
            /* renamed from: getVisibility$onfido_capture_sdk_core_release, reason: from getter */
            public Visibility getVisibility() {
                return this.visibility;
            }

            public int hashCode() {
                return this.focusType.hashCode();
            }

            public String toString() {
                return "Visible(focusType=" + this.focusType + ')';
            }
        }

        private VisibilityCommand() {
        }

        /* renamed from: getVisibility$onfido_capture_sdk_core_release */
        public abstract Visibility getVisibility();

        public /* synthetic */ VisibilityCommand(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public OnfidoCaptureValidationBubble(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    private final void changeBackground(int color, View view, int drawable) {
        Drawable drawable2 = AppCompatResources.getDrawable(getContext(), drawable);
        if (drawable2 != null) {
            Drawable drawableMutate = DrawableCompat.wrap(drawable2).mutate();
            Intrinsics.checkNotNullExpressionValue(drawableMutate, "mutate(...)");
            DrawableCompat.setTint(drawableMutate, color);
            if (view instanceof ImageView) {
                ((ImageView) view).setImageDrawable(drawableMutate);
            } else {
                view.setBackground(drawableMutate);
            }
        }
    }

    private final String getDisplayedTextInternal() {
        StringBuilder sb = new StringBuilder();
        CharSequence contentDescription = this.binding.warningIcon.getContentDescription();
        if (contentDescription != null) {
            Intrinsics.checkNotNull(contentDescription);
            sb.append(this.binding.warningIcon.getContentDescription());
            sb.append(' ');
        }
        sb.append(this.binding.bubbleTitle.getText().toString());
        sb.append(' ');
        sb.append(this.binding.bubbleSubtitle.getText().toString());
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return StringsKt.trim((CharSequence) string).toString();
    }

    private final CaptureValidationBubblePresenter getPresenter() {
        return (CaptureValidationBubblePresenter) this.presenter.getValue();
    }

    private final boolean isLocked() {
        State state = this.state;
        return state == State.HARD_LOCK || state == State.SOFT_LOCK;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void renderVisibility$lambda$1(Visibility visibility, OnfidoCaptureValidationBubble this$0) {
        Intrinsics.checkNotNullParameter(visibility, "$visibility");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        visibility.changeVisibility$onfido_capture_sdk_core_release(this$0, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void renderVisibility$lambda$2(Visibility visibility, OnfidoCaptureValidationBubble this$0) {
        Intrinsics.checkNotNullParameter(visibility, "$visibility");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        visibility.changeVisibility$onfido_capture_sdk_core_release(this$0, false);
    }

    private final void setBubbleTitleTypeface(TextView titleTextView) {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        if (ContextUtilsKt.resolveTypedValueFromAttr$default(context, R.attr.onfidoFontFamilySubtitle, null, false, 6, null) == null) {
            titleTextView.setTypeface(Typeface.create(FONT_FAMILY_SANS_SERIF_MEDIUM, 0));
        }
    }

    private final void setColor(int color) {
        FrameLayout bubbleIconBackground = this.binding.bubbleIconBackground;
        Intrinsics.checkNotNullExpressionValue(bubbleIconBackground, "bubbleIconBackground");
        changeBackground(color, bubbleIconBackground, R.drawable.onfido_circle_validation_error);
        if (this.bubbleMode == BubbleMode.POST_CAPTURE) {
            Drawable drawableMutate = this.binding.bubbleRoot.getBackground().mutate();
            Intrinsics.checkNotNull(drawableMutate, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
            GradientDrawable gradientDrawable = (GradientDrawable) drawableMutate;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            gradientDrawable.setStroke(ContextUtilsKt.dimen(context, R.dimen.onfido_post_capture_validation_bubble_stroke_width), color);
            Context context2 = getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
            gradientDrawable.setColor(ContextUtilsKt.colorFromAttr(context2, android.R.attr.colorBackground));
            Context context3 = getContext();
            Intrinsics.checkNotNullExpressionValue(context3, "getContext(...)");
            int iColorFromAttr = ContextUtilsKt.colorFromAttr(context3, R.attr.onfidoColorContentMain);
            this.binding.bubbleTitle.setTextColor(iColorFromAttr);
            this.binding.bubbleSubtitle.setTextColor(iColorFromAttr);
        }
    }

    public static /* synthetic */ void setContent$onfido_capture_sdk_core_release$default(OnfidoCaptureValidationBubble onfidoCaptureValidationBubble, Content content, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        onfidoCaptureValidationBubble.setContent$onfido_capture_sdk_core_release(content, z);
    }

    private final void setText(int title, Integer subtitle) {
        this.binding.bubbleTitle.setText(getContext().getString(title));
        if (subtitle == null) {
            this.binding.bubbleSubtitle.setText((CharSequence) null);
            TextView bubbleSubtitle = this.binding.bubbleSubtitle;
            Intrinsics.checkNotNullExpressionValue(bubbleSubtitle, "bubbleSubtitle");
            ViewExtensionsKt.toGone$default(bubbleSubtitle, false, 1, null);
            return;
        }
        this.binding.bubbleSubtitle.setText(getContext().getString(subtitle.intValue()));
        TextView bubbleSubtitle2 = this.binding.bubbleSubtitle;
        Intrinsics.checkNotNullExpressionValue(bubbleSubtitle2, "bubbleSubtitle");
        ViewExtensionsKt.toVisible$default(bubbleSubtitle2, false, 1, null);
    }

    static /* synthetic */ void setText$default(OnfidoCaptureValidationBubble onfidoCaptureValidationBubble, int i, Integer num, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            num = null;
        }
        onfidoCaptureValidationBubble.setText(i, num);
    }

    public static /* synthetic */ void setVisibilityCommand$onfido_capture_sdk_core_release$default(OnfidoCaptureValidationBubble onfidoCaptureValidationBubble, VisibilityCommand visibilityCommand, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        onfidoCaptureValidationBubble.setVisibilityCommand$onfido_capture_sdk_core_release(visibilityCommand, z);
    }

    private final void setWarningIcon(int drawable, int contentDescription) {
        if (getAnnouncementService$onfido_capture_sdk_core_release().isEnabled() && this.announceIcon) {
            String string = getContext().getString(contentDescription);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            this.binding.warningIcon.announceForAccessibility(string);
        }
        this.binding.warningIcon.setImageDrawable(AppCompatResources.getDrawable(getContext(), drawable));
        this.binding.warningIcon.setContentDescription(getContext().getString(contentDescription));
    }

    public final AnnouncementService getAnnouncementService$onfido_capture_sdk_core_release() {
        AnnouncementService announcementService = this.announcementService;
        if (announcementService != null) {
            return announcementService;
        }
        Intrinsics.throwUninitializedPropertyAccessException("announcementService");
        return null;
    }

    public final CaptureValidationBubblePresenter.Factory getCaptureValidationBubblePresenterFactory$onfido_capture_sdk_core_release() {
        CaptureValidationBubblePresenter.Factory factory = this.captureValidationBubblePresenterFactory;
        if (factory != null) {
            return factory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("captureValidationBubblePresenterFactory");
        return null;
    }

    @Override // com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter.View
    public String getDisplayedText() {
        return getDisplayedTextInternal();
    }

    public final boolean isVisible$onfido_capture_sdk_core_release() {
        return getAlpha() > 0.0f;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getPresenter().onAttach();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getPresenter().onDetach();
    }

    public final long readingTimeMilliseconds$onfido_capture_sdk_core_release() {
        return StringExtensionsKt.readingTimeMilliseconds(String.valueOf(this.binding.bubbleTitle.getText())) + StringExtensionsKt.readingTimeMilliseconds(String.valueOf(this.binding.bubbleSubtitle.getText()));
    }

    @Override // com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter.View
    public void renderVisibility(final Visibility visibility) {
        Intrinsics.checkNotNullParameter(visibility, "visibility");
        if (visibility.getIsAppearing()) {
            ViewExtensionsKt.animateToAlphaFast(this, 1.0f).withStartAction(new Runnable() { // from class: com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OnfidoCaptureValidationBubble.renderVisibility$lambda$1(visibility, this);
                }
            });
        } else {
            ViewExtensionsKt.animateToAlphaFast(this, 0.0f).withEndAction(new Runnable() { // from class: com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    OnfidoCaptureValidationBubble.renderVisibility$lambda$2(visibility, this);
                }
            });
        }
    }

    public final void setAnnouncementService$onfido_capture_sdk_core_release(AnnouncementService announcementService) {
        Intrinsics.checkNotNullParameter(announcementService, "<set-?>");
        this.announcementService = announcementService;
    }

    public final void setCaptureValidationBubblePresenterFactory$onfido_capture_sdk_core_release(CaptureValidationBubblePresenter.Factory factory) {
        Intrinsics.checkNotNullParameter(factory, "<set-?>");
        this.captureValidationBubblePresenterFactory = factory;
    }

    public final void setContent$onfido_capture_sdk_core_release(Content content, boolean shouldIgnoreLock) {
        Integer numValueOf;
        int i;
        Context context;
        int i2;
        Intrinsics.checkNotNullParameter(content, "content");
        if (!isLocked() || shouldIgnoreLock) {
            setText(content.getTitle$onfido_capture_sdk_core_release(), content.getSubtitle$onfido_capture_sdk_core_release());
            boolean z = content instanceof Content.Error;
            if (z) {
                numValueOf = Integer.valueOf(R.drawable.onfido_error_red);
                i = R.string.onfido_generic_icon_error_accessibility;
            } else {
                if (!(content instanceof Content.Info)) {
                    throw new NoWhenBranchMatchedException();
                }
                numValueOf = Integer.valueOf(R.drawable.onfido_ic_info_hallow_16);
                i = R.string.onfido_generic_icon_info_accessibility;
            }
            Pair pair = TuplesKt.to(numValueOf, Integer.valueOf(i));
            setWarningIcon(((Number) pair.component1()).intValue(), ((Number) pair.component2()).intValue());
            if (z) {
                context = getContext();
                Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
                i2 = R.attr.onfidoColorIconStrokeNegative;
            } else {
                if (!(content instanceof Content.Info)) {
                    throw new NoWhenBranchMatchedException();
                }
                context = getContext();
                Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
                i2 = R.attr.onfidoColorIconAccent;
            }
            setColor(ContextUtilsKt.colorFromAttr(context, i2));
        }
    }

    public final void setState$onfido_capture_sdk_core_release(State state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.state = state;
    }

    public final void setVisibilityCommand$onfido_capture_sdk_core_release(VisibilityCommand visibilityCommand, boolean shouldIgnoreLock) {
        Intrinsics.checkNotNullParameter(visibilityCommand, "visibilityCommand");
        if (!isLocked() || shouldIgnoreLock) {
            setState$onfido_capture_sdk_core_release(State.UNLOCKED);
            this.visibilityCommandBehaviorSubject.onNext(visibilityCommand);
        }
    }

    @Override // com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter.View
    public Observable<VisibilityCommand> visibilityChange() {
        Observable<VisibilityCommand> observableHide = this.visibilityCommandBehaviorSubject.hide();
        Intrinsics.checkNotNullExpressionValue(observableHide, "hide(...)");
        return observableHide;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public OnfidoCaptureValidationBubble(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OnfidoCaptureValidationBubble(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.state = State.UNLOCKED;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OnfidoCaptureValidationBubble);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "obtainStyledAttributes(...)");
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R.styleable.OnfidoCaptureValidationBubble_onfidoTightMode, false);
        this.isTightMode = z;
        this.announceIcon = typedArrayObtainStyledAttributes.getBoolean(R.styleable.OnfidoCaptureValidationBubble_onfidoAnnounceIcon, false);
        this.bubbleMode = typedArrayObtainStyledAttributes.getInt(R.styleable.OnfidoCaptureValidationBubble_onfidoMode, 0) == 1 ? BubbleMode.POST_CAPTURE : BubbleMode.LIVE_CAPTURE;
        typedArrayObtainStyledAttributes.recycle();
        OnfidoCaptureValidationBubbleBinding onfidoCaptureValidationBubbleBindingInflate = OnfidoCaptureValidationBubbleBinding.inflate(LayoutInflater.from(context), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoCaptureValidationBubbleBindingInflate, "inflate(...)");
        this.binding = onfidoCaptureValidationBubbleBindingInflate;
        if (z) {
            onfidoCaptureValidationBubbleBindingInflate.bubbleRoot.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
            TextView bubbleSubtitle = onfidoCaptureValidationBubbleBindingInflate.bubbleSubtitle;
            Intrinsics.checkNotNullExpressionValue(bubbleSubtitle, "bubbleSubtitle");
            ViewExtensionsKt.toGone$default(bubbleSubtitle, false, 1, null);
        }
        TextView bubbleTitle = onfidoCaptureValidationBubbleBindingInflate.bubbleTitle;
        Intrinsics.checkNotNullExpressionValue(bubbleTitle, "bubbleTitle");
        setBubbleTitleTypeface(bubbleTitle);
        setAlpha(0.0f);
        setFocusable(true);
        SdkController.getSdkComponent$default(SdkController.INSTANCE.getInstance(), context, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        BehaviorSubject<VisibilityCommand> behaviorSubjectCreate = BehaviorSubject.create();
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreate, "create(...)");
        this.visibilityCommandBehaviorSubject = behaviorSubjectCreate;
        this.presenter = LazyKt.lazy(new Function0<CaptureValidationBubblePresenter>() { // from class: com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble$presenter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CaptureValidationBubblePresenter invoke() {
                return this.this$0.getCaptureValidationBubblePresenterFactory$onfido_capture_sdk_core_release().create(this.this$0.bubbleMode, this.this$0);
            }
        });
    }

    public /* synthetic */ OnfidoCaptureValidationBubble(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
