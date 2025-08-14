package net.time4j.calendar.service;

import java.io.IOException;
import java.text.ParsePosition;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoException;
import net.time4j.format.Attributes;
import net.time4j.format.Leniency;
import net.time4j.format.NumberSystem;
import net.time4j.format.TextWidth;
import net.time4j.format.internal.DualFormatElement;

/* loaded from: classes4.dex */
public abstract class DualYearOfEraElement<T extends ChronoEntity<T>> extends StdIntegerDateElement<T> implements DualFormatElement {
    protected abstract NumberSystem getNumberSystem(AttributeQuery attributeQuery);

    protected DualYearOfEraElement(Class<T> cls, int i, int i2, char c) {
        super("YEAR_OF_ERA", cls, i, i2, c, null, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0022 A[PHI: r1
      0x0022: PHI (r1v3 int) = (r1v2 int), (r1v6 int), (r1v7 int) binds: [B:3:0x0017, B:5:0x001a, B:7:0x001d] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // net.time4j.format.TextElement
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void print(net.time4j.engine.ChronoDisplay r9, java.lang.Appendable r10, net.time4j.engine.AttributeQuery r11) throws java.io.IOException, net.time4j.engine.ChronoException {
        /*
            r8 = this;
            net.time4j.format.NumberSystem r4 = r8.getNumberSystem(r11)
            net.time4j.engine.AttributeKey<net.time4j.format.TextWidth> r0 = net.time4j.format.Attributes.TEXT_WIDTH
            net.time4j.format.TextWidth r1 = net.time4j.format.TextWidth.NARROW
            java.lang.Object r0 = r11.get(r0, r1)
            net.time4j.format.TextWidth r0 = (net.time4j.format.TextWidth) r0
            int[] r1 = net.time4j.calendar.service.DualYearOfEraElement.AnonymousClass1.$SwitchMap$net$time4j$format$TextWidth
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 1
            if (r0 == r1) goto L22
            r1 = 2
            if (r0 == r1) goto L22
            r1 = 3
            if (r0 == r1) goto L22
            r0 = 4
            r6 = r0
            goto L23
        L22:
            r6 = r1
        L23:
            net.time4j.engine.AttributeKey<java.lang.Character> r0 = net.time4j.format.Attributes.ZERO_DIGIT
            boolean r0 = r11.contains(r0)
            if (r0 == 0) goto L39
            net.time4j.engine.AttributeKey<java.lang.Character> r0 = net.time4j.format.Attributes.ZERO_DIGIT
            java.lang.Object r0 = r11.get(r0)
            java.lang.Character r0 = (java.lang.Character) r0
            char r0 = r0.charValue()
        L37:
            r5 = r0
            goto L4c
        L39:
            boolean r0 = r4.isDecimal()
            if (r0 == 0) goto L49
            java.lang.String r0 = r4.getDigits()
            r1 = 0
            char r0 = r0.charAt(r1)
            goto L37
        L49:
            r0 = 48
            goto L37
        L4c:
            r7 = 10
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r11
            r0.print(r1, r2, r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.time4j.calendar.service.DualYearOfEraElement.print(net.time4j.engine.ChronoDisplay, java.lang.Appendable, net.time4j.engine.AttributeQuery):void");
    }

    /* renamed from: net.time4j.calendar.service.DualYearOfEraElement$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$time4j$format$TextWidth;

        static {
            int[] iArr = new int[TextWidth.values().length];
            $SwitchMap$net$time4j$format$TextWidth = iArr;
            try {
                iArr[TextWidth.NARROW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$net$time4j$format$TextWidth[TextWidth.SHORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$net$time4j$format$TextWidth[TextWidth.ABBREVIATED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // net.time4j.format.internal.DualFormatElement
    public void print(ChronoDisplay chronoDisplay, Appendable appendable, AttributeQuery attributeQuery, NumberSystem numberSystem, char c, int i, int i2) throws IOException, ChronoException {
        String numeral = numberSystem.toNumeral(chronoDisplay.getInt(this));
        if (numberSystem.isDecimal()) {
            int length = i - numeral.length();
            for (int i3 = 0; i3 < length; i3++) {
                appendable.append(c);
            }
        }
        appendable.append(numeral);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // net.time4j.format.TextElement
    public Integer parse(CharSequence charSequence, ParsePosition parsePosition, AttributeQuery attributeQuery) {
        char cCharAt;
        int i;
        NumberSystem numberSystem = getNumberSystem(attributeQuery);
        int index = parsePosition.getIndex();
        int i2 = 0;
        if (attributeQuery.contains(Attributes.ZERO_DIGIT)) {
            cCharAt = ((Character) attributeQuery.get(Attributes.ZERO_DIGIT)).charValue();
        } else {
            cCharAt = numberSystem.isDecimal() ? numberSystem.getDigits().charAt(0) : '0';
        }
        Leniency leniency = numberSystem.isDecimal() ? Leniency.SMART : (Leniency) attributeQuery.get(Attributes.LENIENCY, Leniency.SMART);
        long integer = 0;
        if (numberSystem.isDecimal()) {
            int iMin = Math.min(index + 9, charSequence.length());
            int i3 = index;
            i = i3;
            while (i3 < iMin) {
                int iCharAt = charSequence.charAt(i3) - cCharAt;
                if (iCharAt < 0 || iCharAt > 9) {
                    break;
                }
                integer = (integer * 10) + iCharAt;
                i++;
                i3++;
            }
        } else {
            int length = charSequence.length();
            for (int i4 = index; i4 < length && numberSystem.contains(charSequence.charAt(i4)); i4++) {
                i2++;
            }
            if (i2 > 0) {
                i = i2 + index;
                integer = numberSystem.toInteger(charSequence.subSequence(index, i).toString(), leniency);
            } else {
                i = index;
            }
        }
        if (i == index || integer > 2147483647L) {
            parsePosition.setErrorIndex(index);
            return null;
        }
        parsePosition.setIndex(i);
        return Integer.valueOf((int) integer);
    }

    @Override // net.time4j.format.internal.DualFormatElement
    public Integer parse(CharSequence charSequence, ParsePosition parsePosition, AttributeQuery attributeQuery, ChronoEntity<?> chronoEntity) {
        return parse(charSequence, parsePosition, attributeQuery);
    }
}
