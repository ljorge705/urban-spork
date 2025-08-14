package com.onfido.android.sdk.capture.ui.camera.liveness.challenges;

import android.content.Context;
import android.graphics.RectF;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.fido.u2f.api.common.ClientData;
import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.databinding.OnfidoChallengeDigitsBinding;
import com.onfido.android.sdk.capture.databinding.OnfidoChallengeMovementBinding;
import com.onfido.android.sdk.capture.databinding.OnfidoChallengeReviewDigitsBinding;
import com.onfido.android.sdk.capture.databinding.OnfidoChallengeReviewMovementBinding;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble;
import com.onfido.dagger.assisted.Assisted;
import com.onfido.dagger.assisted.AssistedFactory;
import com.onfido.dagger.assisted.AssistedInject;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.apache.commons.lang3.StringUtils;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 +2\u00020\u0001:\u0002+,B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J%\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0013J \u0010\u0014\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J%\u0010\u0017\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u001aJ \u0010\u001b\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u001e\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u001e\u0010\u001f\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016J\u0010\u0010 \u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0018H\u0002J\u0014\u0010!\u001a\u00020\b*\u00020\"2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J,\u0010#\u001a\u00020\b*\u00020$2\u0006\u0010%\u001a\u00020\u00162\u0006\u0010&\u001a\u00020\"2\u0006\u0010'\u001a\u00020$2\u0006\u0010(\u001a\u00020\"H\u0002J\f\u0010)\u001a\u00020\u0016*\u00020\u000eH\u0002J\u0014\u0010*\u001a\u00020\n*\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesDrawer;", "", "context", "Landroid/content/Context;", "announcementService", "Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "(Landroid/content/Context;Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;)V", "announceReciteChallenge", "", "subtitleString", "", "drawMovementChallengeRecordingMode", "Lcom/onfido/android/sdk/capture/databinding/OnfidoChallengeMovementBinding;", SearchIntents.EXTRA_QUERY, "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/MovementType;", "frameRect", "Landroid/graphics/RectF;", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "drawMovementChallengeRecordingMode$onfido_capture_sdk_core_release", "drawMovementChallengeReviewMode", ViewProps.MAX_HEIGHT, "", "drawReciteChallengeRecordingMode", "", "ovalBounds", "drawReciteChallengeRecordingMode$onfido_capture_sdk_core_release", "drawReciteChallengeReviewMode", "drawRecording", ClientData.KEY_CHALLENGE, "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallenge;", "drawReview", "getFormattedDigitsFromQuery", "alignChallengeLayoutWithContainer", "Landroid/view/View;", "applyTruncationStrategy", "Landroid/widget/TextView;", "viewMaxHeight", "rootView", "title", KeychainModule.AuthPromptOptions.SUBTITLE, "getDrawableRes", "getText", "Companion", "Factory", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LivenessChallengesDrawer {
    private static final String FONT_FAMILY_SANS_SERIF_THIN = "sans-serif-thin";
    private final AnnouncementService announcementService;
    private final Context context;

    @AssistedFactory
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesDrawer$Factory;", "", "create", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesDrawer;", "context", "Landroid/content/Context;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        LivenessChallengesDrawer create(Context context);
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MovementType.values().length];
            try {
                iArr[MovementType.TURN_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MovementType.TURN_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @AssistedInject
    public LivenessChallengesDrawer(@Assisted Context context, AnnouncementService announcementService) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(announcementService, "announcementService");
        this.context = context;
        this.announcementService = announcementService;
    }

    private final void alignChallengeLayoutWithContainer(View view, final RectF rectF) {
        ViewExtensionsKt.changeLayoutParams(view, new Function1<ViewGroup.LayoutParams, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengesDrawer.alignChallengeLayoutWithContainer.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ViewGroup.LayoutParams layoutParams) {
                invoke2(layoutParams);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ViewGroup.LayoutParams it) {
                Intrinsics.checkNotNullParameter(it, "it");
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) it;
                RectF rectF2 = rectF;
                layoutParams.height = (int) rectF2.height();
                layoutParams.topMargin = (int) rectF2.top;
            }
        });
    }

    private final void announceReciteChallenge(String subtitleString) {
        String string = this.context.getString(R.string.onfido_video_capture_header_challenge_digit_instructions);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        AnnouncementService.announceString$default(this.announcementService, new String[]{string + ' ' + subtitleString}, false, 2, (Object) null).blockingAwait();
    }

    private final void applyTruncationStrategy(TextView textView, int i, View view, TextView textView2, View view2) {
        ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
        textView.setMaxLines(((((i - view.getPaddingTop()) - view.getPaddingBottom()) - view2.getHeight()) - ((LinearLayout.LayoutParams) layoutParams).topMargin) / ViewExtensionsKt.getFontLineHeight(textView2));
        textView.setEllipsize(TextUtils.TruncateAt.END);
    }

    private final void drawMovementChallengeReviewMode(MovementType query, ViewGroup container, int maxHeight) {
        container.removeView(container.findViewById(R.id.reviewChallenge));
        OnfidoChallengeReviewMovementBinding onfidoChallengeReviewMovementBindingInflate = OnfidoChallengeReviewMovementBinding.inflate(LayoutInflater.from(this.context), container, true);
        onfidoChallengeReviewMovementBindingInflate.reviewMovementTitle.setText(getText(query, this.context));
        onfidoChallengeReviewMovementBindingInflate.reviewArrow.setImageDrawable(AppCompatResources.getDrawable(this.context, getDrawableRes(query)));
        if (maxHeight > 0) {
            TextView reviewMovementTitle = onfidoChallengeReviewMovementBindingInflate.reviewMovementTitle;
            Intrinsics.checkNotNullExpressionValue(reviewMovementTitle, "reviewMovementTitle");
            View viewFindViewById = onfidoChallengeReviewMovementBindingInflate.getRoot().findViewById(R.id.reviewChallenge);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            TextView reviewMovementTitle2 = onfidoChallengeReviewMovementBindingInflate.reviewMovementTitle;
            Intrinsics.checkNotNullExpressionValue(reviewMovementTitle2, "reviewMovementTitle");
            ImageView reviewArrow = onfidoChallengeReviewMovementBindingInflate.reviewArrow;
            Intrinsics.checkNotNullExpressionValue(reviewArrow, "reviewArrow");
            applyTruncationStrategy(reviewMovementTitle, maxHeight, viewFindViewById, reviewMovementTitle2, reviewArrow);
        }
    }

    private final void drawReciteChallengeReviewMode(int[] query, ViewGroup container, int maxHeight) {
        container.removeView(container.findViewById(R.id.reviewChallenge));
        OnfidoChallengeReviewDigitsBinding onfidoChallengeReviewDigitsBindingInflate = OnfidoChallengeReviewDigitsBinding.inflate(LayoutInflater.from(this.context), container, true);
        onfidoChallengeReviewDigitsBindingInflate.reviewDigitsSubtitle.setText(getFormattedDigitsFromQuery(query));
        if (maxHeight > 0) {
            TextView reviewDigitsTitle = onfidoChallengeReviewDigitsBindingInflate.reviewDigitsTitle;
            Intrinsics.checkNotNullExpressionValue(reviewDigitsTitle, "reviewDigitsTitle");
            View viewFindViewById = onfidoChallengeReviewDigitsBindingInflate.getRoot().findViewById(R.id.reviewChallenge);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            TextView reviewDigitsTitle2 = onfidoChallengeReviewDigitsBindingInflate.reviewDigitsTitle;
            Intrinsics.checkNotNullExpressionValue(reviewDigitsTitle2, "reviewDigitsTitle");
            TextView reviewDigitsSubtitle = onfidoChallengeReviewDigitsBindingInflate.reviewDigitsSubtitle;
            Intrinsics.checkNotNullExpressionValue(reviewDigitsSubtitle, "reviewDigitsSubtitle");
            applyTruncationStrategy(reviewDigitsTitle, maxHeight, viewFindViewById, reviewDigitsTitle2, reviewDigitsSubtitle);
        }
    }

    private final int getDrawableRes(MovementType movementType) {
        int i = WhenMappings.$EnumSwitchMapping$0[movementType.ordinal()];
        if (i == 1) {
            return R.drawable.onfido_liveness_arrow_left;
        }
        if (i == 2) {
            return R.drawable.onfido_liveness_arrow_right;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final String getFormattedDigitsFromQuery(int[] query) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.getDefault();
        Integer[] typedArray = ArraysKt.toTypedArray(query);
        Object[] objArrCopyOf = Arrays.copyOf(typedArray, typedArray.length);
        String str = String.format(locale, "%d - %d - %d", Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private final String getText(MovementType movementType, Context context) {
        int i;
        int i2 = WhenMappings.$EnumSwitchMapping$0[movementType.ordinal()];
        if (i2 == 1) {
            i = R.string.onfido_video_capture_header_challenge_turn_left;
        } else {
            if (i2 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            i = R.string.onfido_video_capture_header_challenge_turn_right;
        }
        String string = context.getString(i);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final OnfidoChallengeMovementBinding drawMovementChallengeRecordingMode$onfido_capture_sdk_core_release(MovementType query, RectF frameRect, ViewGroup container) {
        Context context;
        int i;
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(frameRect, "frameRect");
        Intrinsics.checkNotNullParameter(container, "container");
        container.removeAllViews();
        int i2 = WhenMappings.$EnumSwitchMapping$0[query.ordinal()];
        int i3 = 2;
        if (i2 == 1) {
            context = this.context;
            i = R.string.onfido_video_capture_header_challenge_turn_left;
        } else {
            if (i2 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            context = this.context;
            i = R.string.onfido_video_capture_header_challenge_turn_right;
        }
        String string = context.getString(i);
        Intrinsics.checkNotNull(string);
        String string2 = this.context.getString(R.string.onfido_video_capture_header_challenge_turn_forward);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        OnfidoChallengeMovementBinding onfidoChallengeMovementBindingInflate = OnfidoChallengeMovementBinding.inflate(LayoutInflater.from(this.context), container, true);
        Intrinsics.checkNotNullExpressionValue(onfidoChallengeMovementBindingInflate, "inflate(...)");
        onfidoChallengeMovementBindingInflate.movementTitleFirst.setText(string);
        onfidoChallengeMovementBindingInflate.movementTitleSecond.setText(string2);
        RelativeLayout root = onfidoChallengeMovementBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        alignChallengeLayoutWithContainer(root, frameRect);
        onfidoChallengeMovementBindingInflate.arrow.setMovementType(query);
        OnfidoCaptureValidationBubble livenessErrorsBubble = onfidoChallengeMovementBindingInflate.livenessErrorsBubble;
        Intrinsics.checkNotNullExpressionValue(livenessErrorsBubble, "livenessErrorsBubble");
        OnfidoCaptureValidationBubble.setContent$onfido_capture_sdk_core_release$default(livenessErrorsBubble, new OnfidoCaptureValidationBubble.Content.Error(R.string.onfido_video_capture_alert_wrong_side, null, i3, 0 == true ? 1 : 0), false, 2, null);
        AnnouncementService.announceString$default(this.announcementService, new String[]{string + ' ' + string2}, false, 2, (Object) null).blockingAwait();
        return onfidoChallengeMovementBindingInflate;
    }

    public final void drawReciteChallengeRecordingMode$onfido_capture_sdk_core_release(int[] query, RectF ovalBounds, ViewGroup container) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(ovalBounds, "ovalBounds");
        Intrinsics.checkNotNullParameter(container, "container");
        container.removeAllViews();
        String formattedDigitsFromQuery = getFormattedDigitsFromQuery(query);
        OnfidoChallengeDigitsBinding onfidoChallengeDigitsBindingInflate = OnfidoChallengeDigitsBinding.inflate(LayoutInflater.from(this.context), container, true);
        RelativeLayout root = onfidoChallengeDigitsBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        alignChallengeLayoutWithContainer(root, ovalBounds);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(formattedDigitsFromQuery);
        int i = 0;
        int i2 = 0;
        while (i < formattedDigitsFromQuery.length()) {
            int i3 = i2 + 1;
            if (formattedDigitsFromQuery.charAt(i) == '-') {
                spannableStringBuilder.setSpan(new TypefaceSpan(FONT_FAMILY_SANS_SERIF_THIN), i2, i3, 18);
            }
            i++;
            i2 = i3;
        }
        onfidoChallengeDigitsBindingInflate.subtitle.setText(spannableStringBuilder);
        onfidoChallengeDigitsBindingInflate.subtitle.setContentDescription(ArraysKt.joinToString$default(query, (CharSequence) StringUtils.SPACE, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
        announceReciteChallenge(formattedDigitsFromQuery);
    }

    public final void drawRecording(LivenessChallenge challenge, RectF frameRect, ViewGroup container) {
        Intrinsics.checkNotNullParameter(challenge, "challenge");
        Intrinsics.checkNotNullParameter(frameRect, "frameRect");
        Intrinsics.checkNotNullParameter(container, "container");
        if (challenge instanceof ReciteLivenessChallenge) {
            drawReciteChallengeRecordingMode$onfido_capture_sdk_core_release(((ReciteLivenessChallenge) challenge).getQuery(), frameRect, container);
        } else if (challenge instanceof MovementLivenessChallenge) {
            drawMovementChallengeRecordingMode$onfido_capture_sdk_core_release(((MovementLivenessChallenge) challenge).getQuery(), frameRect, container);
        }
    }

    public final void drawReview(LivenessChallenge challenge, ViewGroup container, int maxHeight) {
        Intrinsics.checkNotNullParameter(challenge, "challenge");
        Intrinsics.checkNotNullParameter(container, "container");
        if (challenge instanceof ReciteLivenessChallenge) {
            drawReciteChallengeReviewMode(((ReciteLivenessChallenge) challenge).getQuery(), container, maxHeight);
        } else if (challenge instanceof MovementLivenessChallenge) {
            drawMovementChallengeReviewMode(((MovementLivenessChallenge) challenge).getQuery(), container, maxHeight);
        }
    }
}
