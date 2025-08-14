package net.time4j.format;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.engine.TimeSpan;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes4.dex */
public abstract class TimeSpanFormatter<U, S extends TimeSpan<U>> {
    private static final Object SIGN_KEY = new Object();
    private final List<FormatItem<U>> items;
    private final String pattern;
    private final Class<U> type;

    private static boolean isSymbol(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    protected abstract S convert(Map<U, Long> map, boolean z);

    public String getPattern() {
        return this.pattern;
    }

    public Class<U> getType() {
        return this.type;
    }

    protected abstract U getUnit(char c);

    protected TimeSpanFormatter(Class<U> cls, String str) {
        int i;
        if (cls == null) {
            throw new NullPointerException("Missing unit type.");
        }
        int length = str.length();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ArrayList());
        boolean z = false;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            boolean z2 = true;
            if (i2 < length) {
                char cCharAt = str.charAt(i2);
                if (cCharAt == '#') {
                    i3++;
                } else if (isSymbol(cCharAt)) {
                    int i4 = i2 + 1;
                    while (i4 < length && str.charAt(i4) == cCharAt) {
                        i4++;
                    }
                    addSymbol(cCharAt, i4 - i2, i3, arrayList);
                    i2 = i4 - 1;
                    i3 = 0;
                } else {
                    if (i3 > 0) {
                        throw new IllegalArgumentException("Char # must be followed by unit symbol.");
                    }
                    if (cCharAt == '\'') {
                        int i5 = i2 + 1;
                        i = i5;
                        while (i < length) {
                            if (str.charAt(i) == '\'') {
                                int i6 = i + 1;
                                if (i6 >= length || str.charAt(i6) != '\'') {
                                    break;
                                } else {
                                    i = i6;
                                }
                            }
                            i++;
                        }
                        if (i >= length) {
                            throw new IllegalArgumentException("String literal in pattern not closed: " + str);
                        }
                        if (i5 == i) {
                            addLiteral('\'', (List) arrayList);
                        } else {
                            addLiteral(str.substring(i5, i).replace("''", "'"), arrayList);
                        }
                    } else if (cCharAt == '[') {
                        startOptionalSection(arrayList);
                    } else if (cCharAt == ']') {
                        endOptionalSection(arrayList);
                    } else {
                        char c = AbstractJsonLexerKt.COMMA;
                        char c2 = ClassUtils.PACKAGE_SEPARATOR_CHAR;
                        if (cCharAt == '.') {
                            lastOn(arrayList).add(new SeparatorItem(c2, c));
                        } else if (cCharAt == ',') {
                            lastOn(arrayList).add(new SeparatorItem(c, c2));
                        } else if (cCharAt == '-') {
                            lastOn(arrayList).add(new SignItem(z));
                        } else if (cCharAt == '+') {
                            lastOn(arrayList).add(new SignItem(z2));
                        } else if (cCharAt == '{') {
                            int i7 = i2 + 1;
                            i = i7;
                            while (i < length && str.charAt(i) != '}') {
                                i++;
                            }
                            addPluralItem(str.substring(i7, i), arrayList);
                        } else if (cCharAt == '|') {
                            lastOn(arrayList).add(OrItem.getInstance());
                        } else {
                            addLiteral(cCharAt, arrayList);
                        }
                    }
                    i2 = i;
                }
                i2++;
            } else {
                if (arrayList.size() > 1) {
                    throw new IllegalArgumentException("Open square bracket without closing one.");
                }
                if (arrayList.isEmpty()) {
                    throw new IllegalArgumentException("Empty or invalid pattern.");
                }
                List<FormatItem<U>> list = arrayList.get(0);
                if (list.isEmpty()) {
                    throw new IllegalArgumentException("Missing format pattern.");
                }
                if (list.get(0) == OrItem.INSTANCE || list.get(list.size() - 1) == OrItem.INSTANCE) {
                    throw new IllegalArgumentException("Pattern must not start or end with an or-operator.");
                }
                int size = list.size();
                int minWidth = list.get(size - 1).getMinWidth();
                for (int i8 = size - 2; i8 >= 0; i8--) {
                    FormatItem<U> formatItem = list.get(i8);
                    if (formatItem == OrItem.INSTANCE) {
                        minWidth = 0;
                    } else {
                        list.set(i8, formatItem.update(minWidth));
                        minWidth += formatItem.getMinWidth();
                    }
                }
                this.type = cls;
                this.items = Collections.unmodifiableList(list);
                this.pattern = str;
                return;
            }
        }
    }

    public String format(TimeSpan<? super U> timeSpan) {
        StringBuilder sb = new StringBuilder();
        try {
            print(timeSpan, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public void print(TimeSpan<? super U> timeSpan, Appendable appendable) throws IOException {
        FormatItem<U> next;
        Iterator<FormatItem<U>> it = this.items.iterator();
        while (it.hasNext() && (next = it.next()) != OrItem.INSTANCE) {
            next.print(timeSpan, appendable);
        }
    }

    public S parse(CharSequence charSequence) throws ParseException {
        return (S) parse(charSequence, 0);
    }

    public S parse(CharSequence charSequence, int i) throws ParseException {
        FormatItem<U> formatItem;
        HashMap map = new HashMap();
        int size = this.items.size();
        boolean z = false;
        int i2 = 0;
        while (i2 < size && (formatItem = this.items.get(i2)) != OrItem.INSTANCE) {
            int i3 = formatItem.parse(map, charSequence, i);
            if (i3 < 0) {
                while (true) {
                    i2++;
                    if (i2 >= size) {
                        i2 = -1;
                        break;
                    }
                    if (this.items.get(i2) == OrItem.INSTANCE) {
                        break;
                    }
                }
                if (i2 == -1) {
                    throw new ParseException("Cannot parse: " + ((Object) charSequence), ~i3);
                }
                map.clear();
            } else {
                i = i3;
            }
            i2++;
        }
        int length = charSequence.length();
        if (i < length) {
            throw new ParseException("Unparsed trailing characters found: \"" + ((Object) charSequence.subSequence(i, length)) + "\" in \"" + ((Object) charSequence), i);
        }
        Long l = (Long) map.remove(SIGN_KEY);
        if (l != null && l.longValue() < 0) {
            z = true;
        }
        HashMap map2 = new HashMap();
        for (Object obj : map.keySet()) {
            if (this.type.isInstance(obj)) {
                map2.put(this.type.cast(obj), map.get(obj));
            } else {
                throw new ParseException("Duration type mismatched: " + map, i);
            }
        }
        return (S) convert(map2, z);
    }

    private void addSymbol(char c, int i, int i2, List<List<FormatItem<U>>> list) {
        U unit = getUnit(c);
        List<FormatItem<U>> list2 = list.get(list.size() - 1);
        if (c != 'f') {
            list2.add(new NumberItem(0, i, i + i2, unit));
        } else {
            if (i2 > 0) {
                throw new IllegalArgumentException("Combination of # and f-symbol not allowed.");
            }
            list2.add(new FractionItem(0, i, getUnit(c)));
        }
    }

    private void addLiteral(char c, List<List<FormatItem<U>>> list) {
        addLiteral(String.valueOf(c), list);
    }

    private void addLiteral(String str, List<List<FormatItem<U>>> list) {
        lastOn(list).add(new LiteralItem(str));
    }

    private void addPluralItem(String str, List<List<FormatItem<U>>> list) {
        Locale locale;
        String[] strArrSplit = str.split(":");
        if (strArrSplit.length > 9 || strArrSplit.length < 4) {
            throw new IllegalArgumentException("Plural information has wrong format: " + str);
        }
        if (strArrSplit[0].length() == 1) {
            U unit = getUnit(strArrSplit[0].charAt(0));
            String[] strArrSplit2 = strArrSplit[2].split("-|_");
            String str2 = strArrSplit2[0];
            if (strArrSplit2.length > 1) {
                String str3 = strArrSplit2[1];
                if (strArrSplit2.length > 2) {
                    String str4 = strArrSplit2[2];
                    if (strArrSplit2.length > 3) {
                        throw new IllegalArgumentException("Plural information has wrong locale: " + str);
                    }
                    locale = new Locale(str2, str3, str4);
                } else {
                    locale = new Locale(str2, str3);
                }
            } else {
                locale = new Locale(str2);
            }
            EnumMap enumMap = new EnumMap(PluralCategory.class);
            PluralRules pluralRulesOf = PluralRules.of(locale, NumberType.CARDINALS);
            for (int i = 3; i < strArrSplit.length; i++) {
                String[] strArrSplit3 = strArrSplit[i].split("=");
                if (strArrSplit3.length == 2) {
                    enumMap.put((EnumMap) PluralCategory.valueOf(strArrSplit3[0]), (PluralCategory) strArrSplit3[1]);
                } else {
                    throw new IllegalArgumentException("Plural information has wrong format: " + str);
                }
            }
            if (enumMap.isEmpty()) {
                throw new IllegalArgumentException("Missing plural forms: " + str);
            }
            if (!enumMap.containsKey(PluralCategory.OTHER)) {
                throw new IllegalArgumentException("Missing plural category OTHER: " + str);
            }
            lastOn(list).add(new PluralItem(unit, strArrSplit[1], pluralRulesOf, enumMap));
            return;
        }
        throw new IllegalArgumentException("Plural information has wrong symbol: " + str);
    }

    private static <U> void startOptionalSection(List<List<FormatItem<U>>> list) {
        list.add(new ArrayList());
    }

    private static <U> void endOptionalSection(List<List<FormatItem<U>>> list) {
        int size = list.size();
        int i = size - 1;
        if (i < 1) {
            throw new IllegalArgumentException("Closing square bracket without open one.");
        }
        list.get(size - 2).add(new OptionalSectionItem(list.remove(i)));
    }

    private static <U> List<FormatItem<U>> lastOn(List<List<FormatItem<U>>> list) {
        return list.get(list.size() - 1);
    }

    private static abstract class FormatItem<U> {
        private final int reserved;

        abstract int getMinWidth();

        int getReserved() {
            return this.reserved;
        }

        boolean isZero(TimeSpan<? super U> timeSpan) {
            return true;
        }

        abstract int parse(Map<Object, Long> map, CharSequence charSequence, int i);

        abstract void print(TimeSpan<? super U> timeSpan, Appendable appendable) throws IOException;

        abstract FormatItem<U> update(int i);

        FormatItem(int i) {
            this.reserved = i;
        }
    }

    private static class NumberItem<U> extends FormatItem<U> {
        private final int maxWidth;
        private final int minWidth;
        private final U unit;

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        int getMinWidth() {
            return this.minWidth;
        }

        U getUnit() {
            return this.unit;
        }

        private NumberItem(int i, int i2, int i3, U u) {
            super(i);
            if (i2 < 1 || i2 > 18) {
                throw new IllegalArgumentException("Min width out of bounds: " + i2);
            }
            if (i3 < i2) {
                throw new IllegalArgumentException("Max width smaller than min width.");
            }
            if (i3 > 18) {
                throw new IllegalArgumentException("Max width out of bounds: " + i3);
            }
            if (u == null) {
                throw new NullPointerException("Missing unit.");
            }
            this.minWidth = i2;
            this.maxWidth = i3;
            this.unit = u;
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        void print(TimeSpan<? super U> timeSpan, Appendable appendable) throws IOException {
            String strValueOf = String.valueOf(getAmount(timeSpan));
            if (strValueOf.length() > this.maxWidth) {
                throw new IllegalArgumentException("Too many digits for: " + this.unit + " [" + timeSpan + "]");
            }
            for (int length = this.minWidth - strValueOf.length(); length > 0; length--) {
                appendable.append('0');
            }
            appendable.append(strValueOf);
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        int parse(Map<Object, Long> map, CharSequence charSequence, int i) {
            int length = charSequence.length() - getReserved();
            long j = 0;
            int i2 = i;
            int i3 = i2;
            while (i2 < length) {
                char cCharAt = charSequence.charAt(i2);
                if (cCharAt < '0' || cCharAt > '9' || i2 - i >= this.maxWidth) {
                    break;
                }
                j = (j * 10) + (cCharAt - '0');
                i3++;
                i2++;
            }
            if (i3 == i) {
                return ~i;
            }
            Long lValueOf = Long.valueOf(j);
            Long lPut = map.put(this.unit, lValueOf);
            return (lPut == null || lPut.equals(lValueOf)) ? i3 : ~i;
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        FormatItem<U> update(int i) {
            return new NumberItem(i, this.minWidth, this.maxWidth, this.unit);
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        boolean isZero(TimeSpan<? super U> timeSpan) {
            return getAmount(timeSpan) == 0;
        }

        long getAmount(TimeSpan<? super U> timeSpan) {
            return timeSpan.getPartialAmount(this.unit);
        }
    }

    private static class FractionItem<U> extends FormatItem<U> {
        private final U nanosecond;
        private final int width;

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        int getMinWidth() {
            return this.width;
        }

        private FractionItem(int i, int i2, U u) {
            super(i);
            if (i2 < 1 || i2 > 9) {
                throw new IllegalArgumentException("Fraction width out of bounds: " + i2);
            }
            this.width = i2;
            this.nanosecond = u;
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        void print(TimeSpan<? super U> timeSpan, Appendable appendable) throws IOException {
            String strValueOf = String.valueOf(timeSpan.getPartialAmount(this.nanosecond));
            int length = strValueOf.length();
            if (length > 9) {
                throw new IllegalArgumentException("Too many nanoseconds, consider normalization: " + timeSpan);
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9 - length; i++) {
                sb.append('0');
            }
            sb.append(strValueOf);
            appendable.append(sb.toString().substring(0, this.width));
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        int parse(Map<Object, Long> map, CharSequence charSequence, int i) {
            char cCharAt;
            StringBuilder sb = new StringBuilder();
            int iMin = Math.min(charSequence.length() - getReserved(), this.width + i);
            int i2 = i;
            int i3 = i2;
            while (i2 < iMin && (cCharAt = charSequence.charAt(i2)) >= '0' && cCharAt <= '9') {
                sb.append(cCharAt);
                i3++;
                i2++;
            }
            if (i3 == i) {
                return ~i;
            }
            int i4 = i3 - i;
            for (int i5 = 0; i5 < 9 - i4; i5++) {
                sb.append('0');
            }
            Long lValueOf = Long.valueOf(Long.parseLong(sb.toString()));
            Long lPut = map.put(this.nanosecond, lValueOf);
            return (lPut == null || lPut.equals(lValueOf)) ? i3 : ~i;
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        FormatItem<U> update(int i) {
            return new FractionItem(i, this.width, this.nanosecond);
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        boolean isZero(TimeSpan<? super U> timeSpan) {
            return timeSpan.getPartialAmount(this.nanosecond) == 0;
        }
    }

    private static class PluralItem<U> extends FormatItem<U> {
        private final int minWidth;
        private final NumberItem<U> numItem;
        private final Map<PluralCategory, String> pluralForms;
        private final PluralRules rules;
        private final FormatItem<U> sepItem;

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        int getMinWidth() {
            return this.minWidth;
        }

        private PluralItem(U u, String str, PluralRules pluralRules, Map<PluralCategory, String> map) {
            super(0);
            this.numItem = new NumberItem<>(0, 1, 18, u);
            this.sepItem = new LiteralItem(str, true);
            this.rules = pluralRules;
            this.pluralForms = map;
            int length = Integer.MAX_VALUE;
            for (String str2 : map.values()) {
                if (str2.length() < length) {
                    length = str2.length();
                }
            }
            this.minWidth = length;
        }

        private PluralItem(int i, NumberItem<U> numberItem, FormatItem<U> formatItem, PluralRules pluralRules, Map<PluralCategory, String> map, int i2) {
            super(i);
            this.numItem = numberItem;
            this.sepItem = formatItem;
            this.rules = pluralRules;
            this.pluralForms = map;
            this.minWidth = i2;
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        void print(TimeSpan<? super U> timeSpan, Appendable appendable) throws IOException {
            this.numItem.print(timeSpan, appendable);
            this.sepItem.print(timeSpan, appendable);
            appendable.append(this.pluralForms.get(this.rules.getCategory(this.numItem.getAmount(timeSpan))));
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        int parse(Map<Object, Long> map, CharSequence charSequence, int i) {
            int i2 = this.numItem.parse(map, charSequence, i);
            if (i2 < 0) {
                return i2;
            }
            int i3 = this.sepItem.parse(map, charSequence, i2);
            if (i3 < 0) {
                return i3;
            }
            String str = this.pluralForms.get(this.rules.getCategory(map.get(this.numItem.getUnit()).longValue()));
            int length = str.length();
            int i4 = i3 + length;
            if (i4 > charSequence.length() - getReserved()) {
                return ~i;
            }
            for (int i5 = 0; i5 < length; i5++) {
                if (str.charAt(i5) != charSequence.charAt(i3 + i5)) {
                    return ~i;
                }
            }
            return i4;
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        FormatItem<U> update(int i) {
            return new PluralItem(i, this.numItem, this.sepItem, this.rules, this.pluralForms, this.minWidth);
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        boolean isZero(TimeSpan<? super U> timeSpan) {
            return this.numItem.isZero(timeSpan);
        }
    }

    private static class SeparatorItem<U> extends FormatItem<U> {
        private final char alt;
        private final char separator;

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        int getMinWidth() {
            return 1;
        }

        private SeparatorItem(char c, char c2) {
            this(0, c, c2);
        }

        private SeparatorItem(int i, char c, char c2) {
            super(i);
            this.separator = c;
            this.alt = c2;
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        void print(TimeSpan<? super U> timeSpan, Appendable appendable) throws IOException {
            appendable.append(this.separator);
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        int parse(Map<Object, Long> map, CharSequence charSequence, int i) {
            if (i >= charSequence.length() - getReserved()) {
                return ~i;
            }
            char cCharAt = charSequence.charAt(i);
            return (cCharAt == this.separator || cCharAt == this.alt) ? i + 1 : ~i;
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        FormatItem<U> update(int i) {
            return new SeparatorItem(i, this.separator, this.alt);
        }
    }

    private static class OrItem<U> extends FormatItem<U> {
        static final OrItem INSTANCE = new OrItem();

        static <U> FormatItem<U> getInstance() {
            return INSTANCE;
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        int getMinWidth() {
            return 0;
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        int parse(Map<Object, Long> map, CharSequence charSequence, int i) {
            return i;
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        void print(TimeSpan<? super U> timeSpan, Appendable appendable) throws IOException {
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        FormatItem<U> update(int i) {
            return this;
        }

        private OrItem() {
            super(0);
        }
    }

    private static class LiteralItem<U> extends FormatItem<U> {
        private final String literal;

        private LiteralItem(String str) {
            this(str, false);
        }

        private LiteralItem(String str, boolean z) {
            super(0);
            if (!z && str.isEmpty()) {
                throw new IllegalArgumentException("Literal is empty.");
            }
            this.literal = str;
        }

        private LiteralItem(int i, String str) {
            super(i);
            this.literal = str;
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        void print(TimeSpan<? super U> timeSpan, Appendable appendable) throws IOException {
            appendable.append(this.literal);
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        int parse(Map<Object, Long> map, CharSequence charSequence, int i) {
            int length = this.literal.length() + i;
            if (length > charSequence.length() - getReserved()) {
                return ~i;
            }
            for (int i2 = i; i2 < length; i2++) {
                if (charSequence.charAt(i2) != this.literal.charAt(i2 - i)) {
                    return ~i;
                }
            }
            return length;
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        int getMinWidth() {
            return this.literal.length();
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        FormatItem<U> update(int i) {
            return new LiteralItem(i, this.literal);
        }
    }

    private static class SignItem<U> extends FormatItem<U> {
        private final boolean always;

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        int getMinWidth() {
            return this.always ? 1 : 0;
        }

        private SignItem(boolean z) {
            super(0);
            this.always = z;
        }

        private SignItem(int i, boolean z) {
            super(i);
            this.always = z;
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        void print(TimeSpan<? super U> timeSpan, Appendable appendable) throws IOException {
            if (this.always) {
                appendable.append(timeSpan.isNegative() ? '-' : '+');
            } else if (timeSpan.isNegative()) {
                appendable.append('-');
            }
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        int parse(Map<Object, Long> map, CharSequence charSequence, int i) {
            int i2;
            Long l = 1L;
            if (i >= charSequence.length() - getReserved()) {
                if (this.always) {
                    return ~i;
                }
                Long lPut = map.put(TimeSpanFormatter.SIGN_KEY, l);
                return (lPut == null || lPut.longValue() == 1) ? i : ~i;
            }
            char cCharAt = charSequence.charAt(i);
            if (this.always) {
                if (cCharAt != '+') {
                    if (cCharAt != '-') {
                        return ~i;
                    }
                    l = -1L;
                }
            } else {
                if (cCharAt == '+') {
                    return ~i;
                }
                if (cCharAt == '-') {
                    l = -1L;
                } else {
                    i2 = i;
                    Long lPut2 = map.put(TimeSpanFormatter.SIGN_KEY, l);
                    return (lPut2 != null || lPut2.longValue() == l.longValue()) ? i2 : ~i;
                }
            }
            i2 = i + 1;
            Long lPut22 = map.put(TimeSpanFormatter.SIGN_KEY, l);
            if (lPut22 != null) {
            }
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        FormatItem<U> update(int i) {
            return new SignItem(i, this.always);
        }
    }

    private static class OptionalSectionItem<U> extends FormatItem<U> {
        private final List<FormatItem<U>> items;

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        int getMinWidth() {
            return 0;
        }

        private OptionalSectionItem(List<FormatItem<U>> list) {
            super(0);
            if (list.isEmpty()) {
                throw new IllegalArgumentException("Optional section is empty.");
            }
            if (list.get(0) == OrItem.INSTANCE || list.get(list.size() - 1) == OrItem.INSTANCE) {
                throw new IllegalArgumentException("Optional section must not start or end with an or-operator.");
            }
            this.items = Collections.unmodifiableList(list);
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        void print(TimeSpan<? super U> timeSpan, Appendable appendable) throws IOException {
            FormatItem<U> next;
            if (isZero(timeSpan)) {
                return;
            }
            Iterator<FormatItem<U>> it = this.items.iterator();
            while (it.hasNext() && (next = it.next()) != OrItem.INSTANCE) {
                next.print(timeSpan, appendable);
            }
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        int parse(Map<Object, Long> map, CharSequence charSequence, int i) {
            HashMap map2 = new HashMap();
            int size = this.items.size();
            int i2 = 0;
            int i3 = i;
            while (i2 < size) {
                FormatItem<U> formatItem = this.items.get(i2);
                if (formatItem == OrItem.INSTANCE) {
                    break;
                }
                int i4 = formatItem.parse(map2, charSequence, i3);
                if (i4 < 0) {
                    while (true) {
                        i2++;
                        if (i2 >= size) {
                            i2 = -1;
                            break;
                        }
                        if (this.items.get(i2) == OrItem.INSTANCE) {
                            break;
                        }
                    }
                    if (i2 == -1) {
                        return i;
                    }
                    map2.clear();
                } else {
                    i3 = i4;
                }
                i2++;
            }
            map.putAll(map2);
            return i3;
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        FormatItem<U> update(int i) {
            ArrayList arrayList = new ArrayList(this.items);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                FormatItem formatItem = (FormatItem) arrayList.get(size);
                arrayList.set(size, formatItem.update(i));
                i += formatItem.getMinWidth();
            }
            return new OptionalSectionItem(arrayList);
        }

        @Override // net.time4j.format.TimeSpanFormatter.FormatItem
        boolean isZero(TimeSpan<? super U> timeSpan) {
            Iterator<FormatItem<U>> it = this.items.iterator();
            while (it.hasNext()) {
                if (!it.next().isZero(timeSpan)) {
                    return false;
                }
            }
            return true;
        }
    }
}
