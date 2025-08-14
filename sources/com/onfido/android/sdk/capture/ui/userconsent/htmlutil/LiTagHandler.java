package com.onfido.android.sdk.capture.ui.userconsent.htmlutil;

import android.text.Editable;
import android.text.Html;
import android.text.style.BulletSpan;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.xml.sax.XMLReader;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/userconsent/htmlutil/LiTagHandler;", "Landroid/text/Html$TagHandler;", "()V", "handleTag", "", "opening", "", "tag", "", "output", "Landroid/text/Editable;", "xmlReader", "Lorg/xml/sax/XMLReader;", "Bullet", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LiTagHandler implements Html.TagHandler {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/userconsent/htmlutil/LiTagHandler$Bullet;", "", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Bullet {
    }

    @Override // android.text.Html.TagHandler
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(xmlReader, "xmlReader");
        if (Intrinsics.areEqual(tag, "li") && opening) {
            output.setSpan(new Bullet(), output.length(), output.length(), 17);
        }
        if (!Intrinsics.areEqual(tag, "li") || opening) {
            return;
        }
        output.append("\n\n");
        Object[] spans = output.getSpans(0, output.length(), Bullet.class);
        Intrinsics.checkNotNullExpressionValue(spans, "getSpans(...)");
        Bullet bullet = (Bullet) ArraysKt.lastOrNull(spans);
        if (bullet != null) {
            int spanStart = output.getSpanStart(bullet);
            output.removeSpan(bullet);
            if (spanStart != output.length()) {
                output.setSpan(new BulletSpan(), spanStart, output.length(), 17);
            }
        }
    }
}
