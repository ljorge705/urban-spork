package com.onfido.android.sdk.capture.utils;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.BulletStepView;
import io.sentry.protocol.Message;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(d1 = {"\u0000\u0092\u0001\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a3\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00150\u0012H\u0002\u001a\f\u0010\u0016\u001a\u00020\u0015*\u00020\u000bH\u0000\u001a(\u0010\u0017\u001a\u00020\u0018*\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u00062\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001bH\u0000\u001a\u001e\u0010\u001d\u001a\u00020\u0018*\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u00062\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0000\u001a\u0014\u0010\u001e\u001a\u00020\u0018*\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0006H\u0000\u001a/\u0010\u001f\u001a\u00020\u0015*\u00020\u000b2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00150\u0012H\u0000\u001a/\u0010\u001f\u001a\u00020\u0015*\u00020 2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00150\u0012H\u0000\u001a\f\u0010!\u001a\u00020\u0015*\u00020\u0002H\u0000\u001a\f\u0010\"\u001a\u00020\u0015*\u00020\u000bH\u0000\u001a\u0016\u0010#\u001a\u0004\u0018\u00010\u000b*\u00020\u000b2\u0006\u0010$\u001a\u00020%H\u0000\u001a(\u0010&\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00012\b\b\u0002\u0010)\u001a\u00020\u0006H\u0000\u001a\u0016\u0010*\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010'\u001a\u00020\u0006H\u0000\u001a \u0010+\u001a\u00020\u0018*\u00020\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\u00062\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0000\u001a\u0014\u0010,\u001a\u00020\u0015*\u00020\u000b2\u0006\u0010-\u001a\u00020.H\u0002\u001a\f\u0010/\u001a\u000200*\u00020\u000bH\u0000\u001a\u0014\u00101\u001a\u000200*\u0002022\u0006\u00103\u001a\u00020\u000bH\u0000\u001a\f\u00104\u001a\u000200*\u00020\u000bH\u0000\u001a\u001a\u00105\u001a\u00020\u0015*\u00020\u000b2\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u001507H\u0000\u001a\u0016\u00108\u001a\u00020\u0015*\u00020\u000b2\b\b\u0001\u00109\u001a\u00020\u0001H\u0000\u001a\u001a\u0010:\u001a\u00020\u0015*\u00020;2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00010=H\u0000\u001a\u0014\u0010>\u001a\u00020\u0015*\u00020\u00022\u0006\u0010?\u001a\u00020\u0001H\u0000\u001a \u0010@\u001a\u00020\u0018*\u00020\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\u00062\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0000\u001a\f\u0010A\u001a\u00020B*\u00020CH\u0000\u001a\u0016\u0010D\u001a\u00020\u0015*\u00020\u000b2\b\b\u0002\u0010E\u001a\u000200H\u0007\u001a\u0016\u0010F\u001a\u00020\u0015*\u00020\u000b2\b\b\u0002\u0010E\u001a\u000200H\u0007\u001a\u0016\u0010G\u001a\u00020\u0015*\u00020\u000b2\b\b\u0002\u0010E\u001a\u000200H\u0007\u001a\u0014\u0010H\u001a\u00020\u0015*\u00020\u000b2\u0006\u0010I\u001a\u000200H\u0000\u001a\u0014\u0010J\u001a\u00020\u0015*\u00020\u000b2\u0006\u0010I\u001a\u000200H\u0007\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0018\u0010\u0005\u001a\u00020\u0006*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u001a\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006K"}, d2 = {"fontLineHeight", "", "Landroid/widget/TextView;", "getFontLineHeight", "(Landroid/widget/TextView;)I", "fontLineHeightFloat", "", "getFontLineHeightFloat", "(Landroid/widget/TextView;)F", "layoutTransition", "Landroid/animation/LayoutTransition;", "Landroid/view/View;", "getLayoutTransition", "(Landroid/view/View;)Landroid/animation/LayoutTransition;", "replaceParams", "Landroid/view/ViewGroup$LayoutParams;", Message.JsonKeys.PARAMS, "closure", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "", RemoteConfigComponent.ACTIVATE_FILE_NAME, "alphaAnimator", "Landroid/view/ViewPropertyAnimator;", ViewHierarchyNode.JsonKeys.ALPHA, "milliseconds", "", "initialDelay", "animateToAlpha", "animateToAlphaFast", "changeLayoutParams", "Landroid/view/ViewGroup;", "clearText", "deactivate", "findChildWithText", "text", "", "getTextPixelsHeightForWidth", "textSizeSp", "width", "lineSpacingAdd", "getTextPixelsWidth", "hideWithAlphaAnim", "instantlyChangeVisibility", ViewHierarchyNode.JsonKeys.VISIBILITY, "Lcom/onfido/android/sdk/capture/utils/Visibility;", "isGone", "", "isPressedAndContainedInView", "Landroid/view/MotionEvent;", "view", "isVisible", "runOnMeasured", "body", "Lkotlin/Function0;", "setContentDescription", "stringId", "setInstructions", "Landroid/widget/LinearLayout;", "instructions", "", "setMaxLinesInHeight", "height", "showWithAlphaAnim", "toBitmap", "Landroid/graphics/Bitmap;", "Landroid/graphics/drawable/Drawable;", "toGone", "withAnimation", "toInvisible", "toVisible", "toVisibleIf", "condition", "toVisibleOrGone", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ViewExtensionsKt {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

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
        }
    }

    public static final void activate(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setActivated(true);
    }

    public static final ViewPropertyAnimator alphaAnimator(View view, float f, long j, long j2) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        ViewPropertyAnimator duration = view.animate().alpha(f).setStartDelay(j2).setDuration(j);
        Intrinsics.checkNotNullExpressionValue(duration, "setDuration(...)");
        return duration;
    }

    public static /* synthetic */ ViewPropertyAnimator alphaAnimator$default(View view, float f, long j, long j2, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 200;
        }
        long j3 = j;
        if ((i & 4) != 0) {
            j2 = 0;
        }
        return alphaAnimator(view, f, j3, j2);
    }

    public static final ViewPropertyAnimator animateToAlpha(View view, float f, long j) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return alphaAnimator$default(view, f, j, 0L, 4, null);
    }

    public static /* synthetic */ ViewPropertyAnimator animateToAlpha$default(View view, float f, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 200;
        }
        return animateToAlpha(view, f, j);
    }

    public static final ViewPropertyAnimator animateToAlphaFast(View view, float f) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return animateToAlpha(view, f, 100L);
    }

    public static final void changeLayoutParams(View view, Function1<? super ViewGroup.LayoutParams, Unit> closure) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(closure, "closure");
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        Intrinsics.checkNotNullExpressionValue(layoutParams, "getLayoutParams(...)");
        view.setLayoutParams(replaceParams(layoutParams, closure));
    }

    public static final void clearText(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setText("");
    }

    public static final void deactivate(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setActivated(false);
    }

    public static final View findChildWithText(View view, String text) {
        View viewFindChildWithText;
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(text, "text");
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            Iterator<Integer> it = new IntRange(0, viewGroup.getChildCount()).iterator();
            while (it.hasNext()) {
                View childAt = viewGroup.getChildAt(((IntIterator) it).nextInt());
                if (childAt != null) {
                    Intrinsics.checkNotNull(childAt);
                    viewFindChildWithText = findChildWithText(childAt, text);
                } else {
                    viewFindChildWithText = null;
                }
                if (viewFindChildWithText != null) {
                    return viewFindChildWithText;
                }
            }
        } else if ((view instanceof TextView) && TextUtils.equals(((TextView) view).getText(), text)) {
            return view;
        }
        return null;
    }

    public static final int getFontLineHeight(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        return (int) Math.ceil(getFontLineHeightFloat(textView));
    }

    private static final float getFontLineHeightFloat(TextView textView) {
        return (textView.getPaint().getFontMetrics().bottom - textView.getPaint().getFontMetrics().top) + textView.getLineSpacingExtra();
    }

    private static final LayoutTransition getLayoutTransition(View view) {
        ViewParent parent = view.getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
        return ((ViewGroup) parent).getLayoutTransition();
    }

    public static final int getTextPixelsHeightForWidth(TextView textView, float f, int i, float f2) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        CharSequence text = textView.getText();
        TextPaint paint = textView.getPaint();
        textView.setTextSize(f);
        return new StaticLayout(text, paint, i, Layout.Alignment.ALIGN_NORMAL, 1.0f, f2, false).getHeight();
    }

    public static /* synthetic */ int getTextPixelsHeightForWidth$default(TextView textView, float f, int i, float f2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            float textSize = textView.getTextSize();
            Context context = textView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            f = textSize / ContextUtilsKt.screenScaledDensity(context);
        }
        if ((i2 & 4) != 0) {
            f2 = textView.getLineSpacingExtra();
        }
        return getTextPixelsHeightForWidth(textView, f, i, f2);
    }

    public static final int getTextPixelsWidth(TextView textView, float f) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        TextPaint paint = textView.getPaint();
        textView.setTextSize(f);
        return (int) paint.measureText(textView.getText().toString());
    }

    public static /* synthetic */ int getTextPixelsWidth$default(TextView textView, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            float textSize = textView.getTextSize();
            Context context = textView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            f = textSize / ContextUtilsKt.screenScaledDensity(context);
        }
        return getTextPixelsWidth(textView, f);
    }

    public static final ViewPropertyAnimator hideWithAlphaAnim(View view, float f, long j) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return alphaAnimator$default(view, f, j, 0L, 4, null);
    }

    public static /* synthetic */ ViewPropertyAnimator hideWithAlphaAnim$default(View view, float f, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 0.0f;
        }
        if ((i & 2) != 0) {
            j = 200;
        }
        return hideWithAlphaAnim(view, f, j);
    }

    private static final void instantlyChangeVisibility(View view, Visibility visibility) {
        LayoutTransition layoutTransition = getLayoutTransition(view);
        if (layoutTransition != null) {
            int i = visibility.getIsAppearing() ? 2 : 3;
            layoutTransition.disableTransitionType(i);
            int i2 = WhenMappings.$EnumSwitchMapping$0[visibility.ordinal()];
            if (i2 == 1) {
                toVisible$default(view, false, 1, null);
            } else if (i2 == 2) {
                toInvisible$default(view, false, 1, null);
            } else if (i2 == 3) {
                toGone$default(view, false, 1, null);
            }
            layoutTransition.enableTransitionType(i);
        }
    }

    public static final boolean isGone(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return view.getVisibility() == 8;
    }

    public static final boolean isPressedAndContainedInView(MotionEvent motionEvent, View view) {
        Intrinsics.checkNotNullParameter(motionEvent, "<this>");
        Intrinsics.checkNotNullParameter(view, "view");
        int action = motionEvent.getAction();
        return (action == 0 || action == 2) && motionEvent.getX() > view.getX() && motionEvent.getX() < view.getX() + ((float) view.getWidth()) && motionEvent.getY() > view.getY() && motionEvent.getY() < view.getY() + ((float) view.getHeight()) && view.isPressed();
    }

    public static final boolean isVisible(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return view.getVisibility() == 0;
    }

    private static final ViewGroup.LayoutParams replaceParams(ViewGroup.LayoutParams layoutParams, Function1<? super ViewGroup.LayoutParams, Unit> function1) {
        function1.invoke(layoutParams);
        return layoutParams;
    }

    public static final void runOnMeasured(final View view, final Function0<Unit> body) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(body, "body");
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.onfido.android.sdk.capture.utils.ViewExtensionsKt.runOnMeasured.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                body.invoke();
            }
        });
    }

    public static final void setContentDescription(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setContentDescription(view.getContext().getString(i));
    }

    public static final void setInstructions(LinearLayout linearLayout, List<Integer> instructions) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(linearLayout, "<this>");
        Intrinsics.checkNotNullParameter(instructions, "instructions");
        linearLayout.removeAllViews();
        int i = 0;
        for (Object obj : instructions) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            int iIntValue = ((Number) obj).intValue();
            Context context = linearLayout.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            BulletStepView bulletStepView = new BulletStepView(context, null, 0, 6, null);
            bulletStepView.setPadding(0, 0, 0, bulletStepView.getContext().getResources().getDimensionPixelSize(R.dimen.onfido_nfc_scan_fail_step_margin_vertical));
            String string = bulletStepView.getResources().getString(iIntValue);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            bulletStepView.setStepInfo(i2, string);
            bulletStepView.hideSeparators();
            linearLayout.addView(bulletStepView);
            i = i2;
        }
    }

    public static final void setMaxLinesInHeight(TextView textView, int i) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setMaxLines((int) ((i + textView.getLineSpacingExtra()) / getFontLineHeightFloat(textView)));
        textView.setEllipsize(TextUtils.TruncateAt.END);
    }

    public static final ViewPropertyAnimator showWithAlphaAnim(View view, float f, long j) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return alphaAnimator$default(view, f, j, 0L, 4, null);
    }

    public static /* synthetic */ ViewPropertyAnimator showWithAlphaAnim$default(View view, float f, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 1.0f;
        }
        if ((i & 2) != 0) {
            j = 200;
        }
        return showWithAlphaAnim(view, f, j);
    }

    public static final Bitmap toBitmap(Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "<this>");
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Intrinsics.checkNotNullExpressionValue(bitmap, "getBitmap(...)");
            return bitmap;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    public static final void toGone(View view, boolean z) {
        Unit unit;
        Intrinsics.checkNotNullParameter(view, "<this>");
        if (z) {
            view.setVisibility(8);
            return;
        }
        if (getLayoutTransition(view) != null) {
            instantlyChangeVisibility(view, Visibility.GONE);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            toGone$default(view, false, 1, null);
        }
    }

    public static /* synthetic */ void toGone$default(View view, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        toGone(view, z);
    }

    public static final void toInvisible(View view, boolean z) {
        Unit unit;
        Intrinsics.checkNotNullParameter(view, "<this>");
        if (z) {
            view.setVisibility(4);
            return;
        }
        if (getLayoutTransition(view) != null) {
            instantlyChangeVisibility(view, Visibility.INVISIBLE);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            toInvisible$default(view, false, 1, null);
        }
    }

    public static /* synthetic */ void toInvisible$default(View view, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        toInvisible(view, z);
    }

    public static final void toVisible(View view, boolean z) {
        Unit unit;
        Intrinsics.checkNotNullParameter(view, "<this>");
        if (z) {
            view.setVisibility(0);
            return;
        }
        if (getLayoutTransition(view) != null) {
            instantlyChangeVisibility(view, Visibility.VISIBLE);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            toVisible$default(view, false, 1, null);
        }
    }

    public static /* synthetic */ void toVisible$default(View view, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        toVisible(view, z);
    }

    public static final void toVisibleIf(View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        if (z) {
            toVisible$default(view, false, 1, null);
        } else {
            toInvisible$default(view, false, 1, null);
        }
    }

    public static final void toVisibleOrGone(View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        if (z) {
            toVisible$default(view, false, 1, null);
        } else {
            toGone$default(view, false, 1, null);
        }
    }

    public static final void changeLayoutParams(ViewGroup viewGroup, Function1<? super ViewGroup.LayoutParams, Unit> closure) {
        Intrinsics.checkNotNullParameter(viewGroup, "<this>");
        Intrinsics.checkNotNullParameter(closure, "closure");
        ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
        Intrinsics.checkNotNullExpressionValue(layoutParams, "getLayoutParams(...)");
        viewGroup.setLayoutParams(replaceParams(layoutParams, closure));
    }
}
