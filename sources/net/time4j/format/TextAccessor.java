package net.time4j.format;

import java.text.ParsePosition;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.engine.AttributeQuery;

/* loaded from: classes4.dex */
public final class TextAccessor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final char PROTECTED_SPACE = 160;
    private final List<String> textForms;

    public List<String> getTextForms() {
        return this.textForms;
    }

    TextAccessor(String[] strArr) {
        this.textForms = Collections.unmodifiableList(Arrays.asList(strArr));
    }

    public String print(Enum<?> r3) {
        int iOrdinal = r3.ordinal();
        if (this.textForms.size() <= iOrdinal) {
            return r3.name();
        }
        return this.textForms.get(iOrdinal);
    }

    public <V extends Enum<V>> V parse(CharSequence charSequence, ParsePosition parsePosition, Class<V> cls) {
        return (V) parse(charSequence, parsePosition, cls, true, false, true);
    }

    public <V extends Enum<V>> V parse(CharSequence charSequence, ParsePosition parsePosition, Class<V> cls, Leniency leniency) {
        boolean z;
        boolean z2;
        boolean z3;
        if (leniency == Leniency.STRICT) {
            z2 = false;
        } else {
            if (leniency != Leniency.LAX) {
                z = false;
                z2 = true;
                z3 = true;
                return (V) parse(charSequence, parsePosition, cls, z2, z, z3);
            }
            z2 = true;
        }
        z = z2;
        z3 = z;
        return (V) parse(charSequence, parsePosition, cls, z2, z, z3);
    }

    public <V extends Enum<V>> V parse(CharSequence charSequence, ParsePosition parsePosition, Class<V> cls, AttributeQuery attributeQuery) {
        return (V) parse(charSequence, parsePosition, cls, ((Boolean) attributeQuery.get(Attributes.PARSE_CASE_INSENSITIVE, Boolean.TRUE)).booleanValue(), ((Boolean) attributeQuery.get(Attributes.PARSE_PARTIAL_COMPARE, Boolean.FALSE)).booleanValue(), ((Boolean) attributeQuery.get(Attributes.PARSE_MULTIPLE_CONTEXT, Boolean.TRUE)).booleanValue());
    }

    public String toString() {
        int size = this.textForms.size();
        StringBuilder sb = new StringBuilder((size * 16) + 2);
        sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
        boolean z = true;
        for (int i = 0; i < size; i++) {
            if (z) {
                z = false;
            } else {
                sb.append(AbstractJsonLexerKt.COMMA);
            }
            sb.append(this.textForms.get(i));
        }
        sb.append(AbstractJsonLexerKt.END_OBJ);
        return sb.toString();
    }

    private <V extends Enum<V>> V parse(CharSequence charSequence, ParsePosition parsePosition, Class<V> cls, boolean z, boolean z2, boolean z3) {
        int i;
        int i2;
        String str;
        V v;
        V[] enumConstants = cls.getEnumConstants();
        int size = this.textForms.size();
        int index = parsePosition.getIndex();
        int length = charSequence.length();
        String str2 = "";
        String strName = "";
        int i3 = 0;
        V v2 = null;
        int i4 = 0;
        while (i3 < enumConstants.length) {
            boolean zIsEmpty = strName.isEmpty();
            if (zIsEmpty) {
                strName = i3 >= size ? enumConstants[i3].name() : this.textForms.get(i3);
            }
            int length2 = strName.length();
            int i5 = index;
            int i6 = 0;
            boolean z4 = true;
            while (z4 && i6 < length2) {
                int i7 = size;
                int i8 = index + i6;
                if (i8 >= length) {
                    str = str2;
                    v = v2;
                    z4 = false;
                } else {
                    char cCharAt = charSequence.charAt(i8);
                    str = str2;
                    char cCharAt2 = strName.charAt(i6);
                    if (z3) {
                        v = v2;
                        if (cCharAt == 160) {
                            cCharAt = ' ';
                        }
                        if (cCharAt2 == 160) {
                            cCharAt2 = ' ';
                        }
                    } else {
                        v = v2;
                    }
                    boolean z5 = !z ? cCharAt != cCharAt2 : !(cCharAt == cCharAt2 || compareIgnoreCase(cCharAt, cCharAt2));
                    if (z5) {
                        i5++;
                    }
                    z4 = z5;
                }
                i6++;
                size = i7;
                str2 = str;
                v2 = v;
            }
            int i9 = size;
            String str3 = str2;
            V v3 = v2;
            if (z3 && zIsEmpty && length2 == 5 && strName.charAt(4) == '.' && i5 == (i2 = index + 3) && i2 < length && charSequence.charAt(i2) == '.') {
                i3--;
                strName = ((Object) strName.subSequence(index, i2)) + ".";
            } else {
                if (z2 || length2 == 1) {
                    int i10 = i5 - index;
                    if (i4 < i10) {
                        v2 = enumConstants[i3];
                        i4 = i10;
                        strName = str3;
                        i = 1;
                        i3 += i;
                        size = i9;
                        str2 = str3;
                    } else if (i4 == i10) {
                        strName = str3;
                        i = 1;
                        v2 = null;
                        i3 += i;
                        size = i9;
                        str2 = str3;
                    }
                } else if (z4) {
                    parsePosition.setIndex(i5);
                    return enumConstants[i3];
                }
                strName = str3;
            }
            v2 = v3;
            i = 1;
            i3 += i;
            size = i9;
            str2 = str3;
        }
        V v4 = v2;
        if (v4 == null) {
            parsePosition.setErrorIndex(index);
        } else {
            parsePosition.setIndex(index + i4);
        }
        return v4;
    }

    private boolean compareIgnoreCase(char c, char c2) {
        if (c >= 'a' && c <= 'z') {
            if (c2 >= 'A' && c2 <= 'Z') {
                c2 = (char) (c2 + ' ');
            }
            return c == c2;
        }
        if (c < 'A' || c > 'Z') {
            return Character.toUpperCase(c) == Character.toUpperCase(c2) || Character.toLowerCase(c) == Character.toLowerCase(c2);
        }
        char c3 = (char) (c + ' ');
        if (c2 >= 'A' && c2 <= 'Z') {
            c2 = (char) (c2 + ' ');
        }
        return c3 == c2;
    }
}
