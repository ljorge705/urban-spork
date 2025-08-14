package net.time4j.format.expert;

import java.io.IOException;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.engine.ChronoElement;
import net.time4j.format.Leniency;
import net.time4j.format.NumberSystem;

/* loaded from: classes4.dex */
class NumberProcessor<V> implements FormatProcessor<V> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int[] THRESHOLDS = {9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};
    private final ChronoElement<V> element;
    private final boolean fixedInt;
    private final boolean fixedWidth;
    private final Leniency lenientMode;
    private final int maxDigits;
    private final int minDigits;
    private final NumberSystem numberSystem;
    private final int protectedLength;
    private final boolean protectedMode;
    private final int reserved;
    private final int scaleOfNumsys;
    private final SignPolicy signPolicy;
    private final boolean yearOfEra;
    private final char zeroDigit;

    @Override // net.time4j.format.expert.FormatProcessor
    public ChronoElement<V> getElement() {
        return this.element;
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public boolean isNumerical() {
        return true;
    }

    NumberProcessor(ChronoElement<V> chronoElement, boolean z, int i, int i2, SignPolicy signPolicy, boolean z2) {
        this(chronoElement, z, i, i2, signPolicy, z2, 0, '0', NumberSystem.ARABIC, Leniency.SMART, 0, false);
    }

    private NumberProcessor(ChronoElement<V> chronoElement, boolean z, int i, int i2, SignPolicy signPolicy, boolean z2, int i3, char c, NumberSystem numberSystem, Leniency leniency, int i4, boolean z3) {
        this.element = chronoElement;
        this.fixedWidth = z;
        this.minDigits = i;
        this.maxDigits = i2;
        this.signPolicy = signPolicy;
        this.protectedMode = z2;
        this.fixedInt = z3;
        if (chronoElement == null) {
            throw new NullPointerException("Missing element.");
        }
        if (signPolicy == null) {
            throw new NullPointerException("Missing sign policy.");
        }
        if (i < 1) {
            throw new IllegalArgumentException("Not positive: " + i);
        }
        if (i > i2) {
            throw new IllegalArgumentException("Max smaller than min: " + i2 + " < " + i);
        }
        if (z && i != i2) {
            throw new IllegalArgumentException("Variable width in fixed-width-mode: " + i2 + " != " + i);
        }
        if (z && signPolicy != SignPolicy.SHOW_NEVER) {
            throw new IllegalArgumentException("Sign policy must be SHOW_NEVER in fixed-width-mode.");
        }
        int scale = getScale(numberSystem);
        if (numberSystem.isDecimal()) {
            if (i > scale) {
                throw new IllegalArgumentException("Min digits out of range: " + i);
            }
            if (i2 > scale) {
                throw new IllegalArgumentException("Max digits out of range: " + i2);
            }
        }
        this.yearOfEra = chronoElement.name().equals("YEAR_OF_ERA");
        this.reserved = i3;
        this.zeroDigit = c;
        this.numberSystem = numberSystem;
        this.lenientMode = leniency;
        this.protectedLength = i4;
        this.scaleOfNumsys = scale;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:127:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0138  */
    @Override // net.time4j.format.expert.FormatProcessor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int print(net.time4j.engine.ChronoDisplay r23, java.lang.Appendable r24, net.time4j.engine.AttributeQuery r25, java.util.Set<net.time4j.format.expert.ElementPosition> r26, boolean r27) throws java.io.IOException, net.time4j.engine.ChronoException {
        /*
            Method dump skipped, instructions count: 810
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: net.time4j.format.expert.NumberProcessor.print(net.time4j.engine.ChronoDisplay, java.lang.Appendable, net.time4j.engine.AttributeQuery, java.util.Set, boolean):int");
    }

    /* renamed from: net.time4j.format.expert.NumberProcessor$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$time4j$format$expert$SignPolicy;

        static {
            int[] iArr = new int[SignPolicy.values().length];
            $SwitchMap$net$time4j$format$expert$SignPolicy = iArr;
            try {
                iArr[SignPolicy.SHOW_ALWAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$net$time4j$format$expert$SignPolicy[SignPolicy.SHOW_WHEN_BIG_NUMBER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:192:0x035b  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0364  */
    @Override // net.time4j.format.expert.FormatProcessor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void parse(java.lang.CharSequence r26, net.time4j.format.expert.ParseLog r27, net.time4j.engine.AttributeQuery r28, net.time4j.format.expert.ParsedEntity<?> r29, boolean r30) {
        /*
            Method dump skipped, instructions count: 993
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: net.time4j.format.expert.NumberProcessor.parse(java.lang.CharSequence, net.time4j.format.expert.ParseLog, net.time4j.engine.AttributeQuery, net.time4j.format.expert.ParsedEntity, boolean):void");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NumberProcessor)) {
            return false;
        }
        NumberProcessor numberProcessor = (NumberProcessor) obj;
        return this.element.equals(numberProcessor.element) && this.fixedWidth == numberProcessor.fixedWidth && this.minDigits == numberProcessor.minDigits && this.maxDigits == numberProcessor.maxDigits && this.signPolicy == numberProcessor.signPolicy && this.protectedMode == numberProcessor.protectedMode;
    }

    public int hashCode() {
        return (this.element.hashCode() * 7) + ((this.minDigits + (this.maxDigits * 10)) * 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(getClass().getName());
        sb.append("[element=");
        sb.append(this.element.name());
        sb.append(", fixed-width-mode=");
        sb.append(this.fixedWidth);
        sb.append(", min-digits=");
        sb.append(this.minDigits);
        sb.append(", max-digits=");
        sb.append(this.maxDigits);
        sb.append(", sign-policy=");
        sb.append(this.signPolicy);
        sb.append(", protected-mode=");
        sb.append(this.protectedMode);
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public FormatProcessor<V> withElement(ChronoElement<V> chronoElement) {
        return (this.protectedMode || this.element == chronoElement) ? this : new NumberProcessor(chronoElement, this.fixedWidth, this.minDigits, this.maxDigits, this.signPolicy, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0065  */
    @Override // net.time4j.format.expert.FormatProcessor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public net.time4j.format.expert.FormatProcessor<V> quickPath(net.time4j.format.expert.ChronoFormatter<?> r17, net.time4j.engine.AttributeQuery r18, int r19) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            net.time4j.engine.AttributeKey<net.time4j.format.NumberSystem> r2 = net.time4j.format.Attributes.NUMBER_SYSTEM
            net.time4j.format.NumberSystem r3 = net.time4j.format.NumberSystem.ARABIC
            java.lang.Object r2 = r1.get(r2, r3)
            r12 = r2
            net.time4j.format.NumberSystem r12 = (net.time4j.format.NumberSystem) r12
            net.time4j.engine.AttributeKey<java.lang.Character> r2 = net.time4j.format.Attributes.ZERO_DIGIT
            boolean r2 = r1.contains(r2)
            r3 = 48
            r4 = 0
            if (r2 == 0) goto L28
            net.time4j.engine.AttributeKey<java.lang.Character> r2 = net.time4j.format.Attributes.ZERO_DIGIT
            java.lang.Object r2 = r1.get(r2)
            java.lang.Character r2 = (java.lang.Character) r2
            char r2 = r2.charValue()
        L26:
            r11 = r2
            goto L38
        L28:
            boolean r2 = r12.isDecimal()
            if (r2 == 0) goto L37
            java.lang.String r2 = r12.getDigits()
            char r2 = r2.charAt(r4)
            goto L26
        L37:
            r11 = r3
        L38:
            net.time4j.engine.AttributeKey<java.lang.Integer> r2 = net.time4j.format.Attributes.PROTECTED_CHARACTERS
            java.lang.Integer r5 = java.lang.Integer.valueOf(r4)
            java.lang.Object r2 = r1.get(r2, r5)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r14 = r2.intValue()
            net.time4j.format.NumberSystem r2 = net.time4j.format.NumberSystem.ARABIC
            if (r12 != r2) goto L65
            if (r11 != r3) goto L65
            boolean r2 = r0.fixedWidth
            if (r2 == 0) goto L65
            if (r14 != 0) goto L65
            net.time4j.engine.ChronoElement<V> r2 = r0.element
            java.lang.Class r2 = r2.getType()
            java.lang.Class<java.lang.Integer> r3 = java.lang.Integer.class
            if (r2 != r3) goto L65
            boolean r2 = r0.yearOfEra
            if (r2 != 0) goto L65
            r2 = 1
            r15 = r2
            goto L66
        L65:
            r15 = r4
        L66:
            net.time4j.format.expert.NumberProcessor r2 = new net.time4j.format.expert.NumberProcessor
            net.time4j.engine.ChronoElement<V> r4 = r0.element
            boolean r5 = r0.fixedWidth
            int r6 = r0.minDigits
            int r7 = r0.maxDigits
            net.time4j.format.expert.SignPolicy r8 = r0.signPolicy
            boolean r9 = r0.protectedMode
            net.time4j.engine.AttributeKey<net.time4j.format.Leniency> r3 = net.time4j.format.Attributes.LENIENCY
            net.time4j.format.Leniency r10 = net.time4j.format.Leniency.SMART
            java.lang.Object r1 = r1.get(r3, r10)
            r13 = r1
            net.time4j.format.Leniency r13 = (net.time4j.format.Leniency) r13
            r3 = r2
            r10 = r19
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: net.time4j.format.expert.NumberProcessor.quickPath(net.time4j.format.expert.ChronoFormatter, net.time4j.engine.AttributeQuery, int):net.time4j.format.expert.FormatProcessor");
    }

    private int getScale(NumberSystem numberSystem) {
        if (!numberSystem.isDecimal()) {
            return 100;
        }
        Class<V> type = this.element.getType();
        if (type == Integer.class) {
            return 10;
        }
        return type == Long.class ? 18 : 9;
    }

    private static int length(int i) {
        int i2 = 0;
        while (i > THRESHOLDS[i2]) {
            i2++;
        }
        return i2 + 1;
    }

    private static void appendTwoDigits(int i, Appendable appendable, char c) throws IOException {
        int i2 = (i * 103) >>> 10;
        appendable.append((char) (i2 + c));
        appendable.append((char) ((i - ((i2 << 3) + (i2 << 1))) + c));
    }
}
