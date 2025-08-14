package com.onfido.android.sdk.capture.ui.userconsent.htmlutil;

import android.content.Context;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.style.BulletSpan;
import android.util.TypedValue;
import android.widget.TextView;
import com.nimbusds.jose.jwk.JWKParameterNames;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"dip", "", JWKParameterNames.RSA_FIRST_FACTOR_CRT_EXPONENT, "context", "Landroid/content/Context;", "setTextFromHtml", "", "Landroid/widget/TextView;", "content", "", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TextViewExtensionKt {
    private static final int dip(int i, Context context) {
        return (int) TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
    }

    public static final void setTextFromHtml(TextView textView, String content) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Intrinsics.checkNotNullParameter(content, "content");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(Html.fromHtml(content, 0));
        BulletSpan[] bulletSpanArr = (BulletSpan[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), BulletSpan.class);
        Intrinsics.checkNotNull(bulletSpanArr);
        for (BulletSpan bulletSpan : bulletSpanArr) {
            int spanStart = spannableStringBuilder.getSpanStart(bulletSpan);
            int spanEnd = spannableStringBuilder.getSpanEnd(bulletSpan);
            spannableStringBuilder.removeSpan(bulletSpan);
            Context context = textView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            int iDip = dip(3, context);
            Context context2 = textView.getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
            spannableStringBuilder.setSpan(new ImprovedBulletSpan(iDip, dip(8, context2), 0, 4, null), spanStart, spanEnd, 17);
        }
        textView.setText(spannableStringBuilder);
    }
}
