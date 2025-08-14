package androidx.compose.ui.text;

import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.text.android.style.IndentationFixSpan;
import androidx.compose.ui.text.platform.extensions.SpannableExtensions_androidKt;
import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;

/* compiled from: AndroidParagraph.android.kt */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0002\u001a\u001d\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\b\t\u001a\u001d\u0010\n\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\b\r\u001a\u001d\u0010\u000e\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\b\u0011\u001a\u001d\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\b\u0015\u001a\u001d\u0010\u0016\u001a\u00020\u00062\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\b\u0019\u001a\f\u0010\u001a\u001a\u00020\u001b*\u00020\u001bH\u0002\u001a\u0014\u0010\u001c\u001a\u00020\u0006*\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0006H\u0002\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"shouldAttachIndentationFixSpan", "", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "ellipsis", "toLayoutAlign", "", "align", "Landroidx/compose/ui/text/style/TextAlign;", "toLayoutAlign-AMY3VfE", "toLayoutBreakStrategy", "breakStrategy", "Landroidx/compose/ui/text/style/LineBreak$Strategy;", "toLayoutBreakStrategy-u6PBz3U", "toLayoutHyphenationFrequency", "hyphens", "Landroidx/compose/ui/text/style/Hyphens;", "toLayoutHyphenationFrequency-0_XeFpE", "toLayoutLineBreakStyle", "lineBreakStrictness", "Landroidx/compose/ui/text/style/LineBreak$Strictness;", "toLayoutLineBreakStyle-4a2g8L8", "toLayoutLineBreakWordStyle", "lineBreakWordStyle", "Landroidx/compose/ui/text/style/LineBreak$WordBreak;", "toLayoutLineBreakWordStyle-gvcdTPQ", "attachIndentationFixSpan", "", "numberOfLinesThatFitMaxHeight", "Landroidx/compose/ui/text/android/TextLayout;", ViewProps.MAX_HEIGHT, "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidParagraph_androidKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toLayoutAlign-AMY3VfE, reason: not valid java name */
    public static final int m3780toLayoutAlignAMY3VfE(TextAlign textAlign) {
        int iM4265getLefte0LSkKk = TextAlign.INSTANCE.m4265getLefte0LSkKk();
        if (textAlign != null && TextAlign.m4258equalsimpl0(textAlign.getValue(), iM4265getLefte0LSkKk)) {
            return 3;
        }
        int iM4266getRighte0LSkKk = TextAlign.INSTANCE.m4266getRighte0LSkKk();
        if (textAlign != null && TextAlign.m4258equalsimpl0(textAlign.getValue(), iM4266getRighte0LSkKk)) {
            return 4;
        }
        int iM4262getCentere0LSkKk = TextAlign.INSTANCE.m4262getCentere0LSkKk();
        if (textAlign != null && TextAlign.m4258equalsimpl0(textAlign.getValue(), iM4262getCentere0LSkKk)) {
            return 2;
        }
        int iM4267getStarte0LSkKk = TextAlign.INSTANCE.m4267getStarte0LSkKk();
        if (textAlign == null || !TextAlign.m4258equalsimpl0(textAlign.getValue(), iM4267getStarte0LSkKk)) {
            int iM4263getEnde0LSkKk = TextAlign.INSTANCE.m4263getEnde0LSkKk();
            if (textAlign != null && TextAlign.m4258equalsimpl0(textAlign.getValue(), iM4263getEnde0LSkKk)) {
                return 1;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toLayoutHyphenationFrequency-0_XeFpE, reason: not valid java name */
    public static final int m3782toLayoutHyphenationFrequency0_XeFpE(Hyphens hyphens) {
        int iM4177getAutovmbZdU8 = Hyphens.INSTANCE.m4177getAutovmbZdU8();
        if (hyphens != null && Hyphens.m4173equalsimpl0(hyphens.getValue(), iM4177getAutovmbZdU8)) {
            return Build.VERSION.SDK_INT <= 32 ? 1 : 3;
        }
        int iM4178getNonevmbZdU8 = Hyphens.INSTANCE.m4178getNonevmbZdU8();
        if (hyphens != null) {
            Hyphens.m4173equalsimpl0(hyphens.getValue(), iM4178getNonevmbZdU8);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toLayoutBreakStrategy-u6PBz3U, reason: not valid java name */
    public static final int m3781toLayoutBreakStrategyu6PBz3U(LineBreak.Strategy strategy) {
        int iM4204getSimplefcGXIks = LineBreak.Strategy.INSTANCE.m4204getSimplefcGXIks();
        if (strategy != null && LineBreak.Strategy.m4198equalsimpl0(strategy.getValue(), iM4204getSimplefcGXIks)) {
            return 0;
        }
        int iM4203getHighQualityfcGXIks = LineBreak.Strategy.INSTANCE.m4203getHighQualityfcGXIks();
        if (strategy != null && LineBreak.Strategy.m4198equalsimpl0(strategy.getValue(), iM4203getHighQualityfcGXIks)) {
            return 1;
        }
        return (strategy != null && LineBreak.Strategy.m4198equalsimpl0(strategy.getValue(), LineBreak.Strategy.INSTANCE.m4202getBalancedfcGXIks())) ? 2 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toLayoutLineBreakStyle-4a2g8L8, reason: not valid java name */
    public static final int m3783toLayoutLineBreakStyle4a2g8L8(LineBreak.Strictness strictness) {
        int iM4212getDefaultusljTpc = LineBreak.Strictness.INSTANCE.m4212getDefaultusljTpc();
        if (strictness != null && LineBreak.Strictness.m4208equalsimpl0(strictness.getValue(), iM4212getDefaultusljTpc)) {
            return 0;
        }
        int iM4213getLooseusljTpc = LineBreak.Strictness.INSTANCE.m4213getLooseusljTpc();
        if (strictness != null && LineBreak.Strictness.m4208equalsimpl0(strictness.getValue(), iM4213getLooseusljTpc)) {
            return 1;
        }
        int iM4214getNormalusljTpc = LineBreak.Strictness.INSTANCE.m4214getNormalusljTpc();
        if (strictness != null && LineBreak.Strictness.m4208equalsimpl0(strictness.getValue(), iM4214getNormalusljTpc)) {
            return 2;
        }
        return (strictness != null && LineBreak.Strictness.m4208equalsimpl0(strictness.getValue(), LineBreak.Strictness.INSTANCE.m4215getStrictusljTpc())) ? 3 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toLayoutLineBreakWordStyle-gvcdTPQ, reason: not valid java name */
    public static final int m3784toLayoutLineBreakWordStylegvcdTPQ(LineBreak.WordBreak wordBreak) {
        int iM4223getDefaultjp8hJ3c = LineBreak.WordBreak.INSTANCE.m4223getDefaultjp8hJ3c();
        if (wordBreak != null && LineBreak.WordBreak.m4219equalsimpl0(wordBreak.getValue(), iM4223getDefaultjp8hJ3c)) {
            return 0;
        }
        return (wordBreak != null && LineBreak.WordBreak.m4219equalsimpl0(wordBreak.getValue(), LineBreak.WordBreak.INSTANCE.m4224getPhrasejp8hJ3c())) ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int numberOfLinesThatFitMaxHeight(TextLayout textLayout, int i) {
        int lineCount = textLayout.getLineCount();
        for (int i2 = 0; i2 < lineCount; i2++) {
            if (textLayout.getLineBottom(i2) > i) {
                return i2;
            }
        }
        return textLayout.getLineCount();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean shouldAttachIndentationFixSpan(TextStyle textStyle, boolean z) {
        if (!z || TextUnit.m4568equalsimpl0(textStyle.m3930getLetterSpacingXSAIIZE(), TextUnitKt.getSp(0)) || TextUnit.m4568equalsimpl0(textStyle.m3930getLetterSpacingXSAIIZE(), TextUnit.INSTANCE.m4582getUnspecifiedXSAIIZE()) || textStyle.m3933getTextAlignbuA522U() == null) {
            return false;
        }
        TextAlign textAlignM3933getTextAlignbuA522U = textStyle.m3933getTextAlignbuA522U();
        int iM4267getStarte0LSkKk = TextAlign.INSTANCE.m4267getStarte0LSkKk();
        if (textAlignM3933getTextAlignbuA522U != null && TextAlign.m4258equalsimpl0(textAlignM3933getTextAlignbuA522U.getValue(), iM4267getStarte0LSkKk)) {
            return false;
        }
        TextAlign textAlignM3933getTextAlignbuA522U2 = textStyle.m3933getTextAlignbuA522U();
        return textAlignM3933getTextAlignbuA522U2 == null || !TextAlign.m4258equalsimpl0(textAlignM3933getTextAlignbuA522U2.getValue(), TextAlign.INSTANCE.m4264getJustifye0LSkKk());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence attachIndentationFixSpan(CharSequence charSequence) {
        if (charSequence.length() == 0) {
            return charSequence;
        }
        SpannableString spannableString = charSequence instanceof Spannable ? (Spannable) charSequence : new SpannableString(charSequence);
        SpannableExtensions_androidKt.setSpan(spannableString, new IndentationFixSpan(), spannableString.length() - 1, spannableString.length() - 1);
        return spannableString;
    }
}
