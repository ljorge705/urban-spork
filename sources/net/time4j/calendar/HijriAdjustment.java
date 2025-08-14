package net.time4j.calendar;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.engine.ChronoException;
import net.time4j.engine.VariantSource;

/* loaded from: classes4.dex */
public final class HijriAdjustment implements VariantSource {
    private final int adjustment;
    private final String baseVariant;

    String getBaseVariant() {
        return this.baseVariant;
    }

    public int getValue() {
        return this.adjustment;
    }

    private HijriAdjustment(String str, int i) {
        if (i < -3 || i > 3) {
            throw new ChronoException("Day adjustment out of range -3 <= x <= 3: " + i);
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Empty variant.");
        }
        this.adjustment = i;
        this.baseVariant = str;
    }

    public static HijriAdjustment ofUmalqura(int i) {
        return new HijriAdjustment(HijriCalendar.VARIANT_UMALQURA, i);
    }

    public static HijriAdjustment of(String str, int i) {
        int iIndexOf = str.indexOf(58);
        if (iIndexOf == -1) {
            return new HijriAdjustment(str, i);
        }
        return new HijriAdjustment(str.substring(0, iIndexOf), i);
    }

    public static HijriAdjustment of(VariantSource variantSource, int i) {
        return of(variantSource.getVariant(), i);
    }

    @Override // net.time4j.engine.VariantSource
    public String getVariant() {
        if (this.adjustment == 0) {
            return this.baseVariant;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.baseVariant);
        sb.append(AbstractJsonLexerKt.COLON);
        if (this.adjustment > 0) {
            sb.append('+');
        }
        sb.append(this.adjustment);
        return sb.toString();
    }

    static HijriAdjustment from(String str) throws NumberFormatException {
        int iIndexOf = str.indexOf(58);
        if (iIndexOf == -1) {
            return new HijriAdjustment(str, 0);
        }
        try {
            return new HijriAdjustment(str.substring(0, iIndexOf), Integer.parseInt(str.substring(iIndexOf + 1)));
        } catch (NumberFormatException unused) {
            throw new ChronoException("Invalid day adjustment: " + str);
        }
    }
}
