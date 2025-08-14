package net.time4j.calendar;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.text.Typography;
import net.time4j.PlainDate;
import net.time4j.engine.AttributeKey;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.CalendarEra;
import net.time4j.engine.ChronoCondition;
import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoException;
import net.time4j.engine.EpochDays;
import net.time4j.format.Attributes;
import net.time4j.format.CalendarText;
import net.time4j.format.TextElement;
import net.time4j.format.TextWidth;

/* loaded from: classes4.dex */
public final class Nengo implements CalendarEra, Serializable {
    private static final Map<String, Nengo> CHINESE_TO_NENGO;
    private static final byte COURT_NORTHERN = 1;
    private static final byte COURT_SOUTHERN = -1;
    private static final byte COURT_STANDARD = 0;
    public static final Nengo HEISEI;
    private static final Map<String, Nengo> KANJI_TO_NENGO;
    private static final TST KOREAN_TO_NENGO;
    public static final Nengo MEIJI;
    private static final String[] MODERN_KEYS;
    private static final Nengo[] MODERN_NENGOS;
    private static final Nengo NENGO_KENMU;
    private static final Nengo NENGO_OEI;
    public static final Nengo NEWEST;
    private static final String NEW_ERA_PROPERTY = "net.time4j.calendar.japanese.supplemental.era";
    private static final Nengo[] NORTHERN_NENGOS;
    private static final Nengo[] OFFICIAL_NENGOS;
    public static final Nengo REIWA;
    private static final TST ROMAJI_TO_NENGO;
    private static final TST RUSSIAN_TO_NENGO;
    public static final AttributeKey<Selector> SELECTOR;
    public static final Nengo SHOWA;
    public static final Nengo TAISHO;
    private static final long serialVersionUID = 5696395761628504723L;
    private final transient String chinese;
    private final byte court;
    private final int index;
    private final transient String kanji;
    private final transient String korean;
    private final transient int relgregyear;
    private final transient String romaji;
    private final transient String russian;
    private final transient long start;

    public enum Selector implements ChronoCondition<Nengo> {
        OFFICIAL { // from class: net.time4j.calendar.Nengo.Selector.1
            @Override // net.time4j.engine.ChronoCondition
            public boolean test(Nengo nengo) {
                return nengo.court != 1;
            }
        },
        MODERN { // from class: net.time4j.calendar.Nengo.Selector.2
            @Override // net.time4j.engine.ChronoCondition
            public boolean test(Nengo nengo) {
                return nengo.index >= Nengo.MEIJI.index;
            }
        },
        EDO_PERIOD { // from class: net.time4j.calendar.Nengo.Selector.3
            @Override // net.time4j.engine.ChronoCondition
            public boolean test(Nengo nengo) {
                return nengo.relgregyear >= 1603 && nengo.relgregyear < 1868;
            }
        },
        AZUCHI_MOMOYAMA_PERIOD { // from class: net.time4j.calendar.Nengo.Selector.4
            @Override // net.time4j.engine.ChronoCondition
            public boolean test(Nengo nengo) {
                return nengo.relgregyear >= 1573 && nengo.relgregyear < 1603;
            }
        },
        MUROMACHI_PERIOD { // from class: net.time4j.calendar.Nengo.Selector.5
            @Override // net.time4j.engine.ChronoCondition
            public boolean test(Nengo nengo) {
                return nengo.relgregyear >= 1336 && nengo.relgregyear < 1573 && nengo.court != 1;
            }
        },
        NORTHERN_COURT { // from class: net.time4j.calendar.Nengo.Selector.6
            @Override // net.time4j.engine.ChronoCondition
            public boolean test(Nengo nengo) {
                return nengo.court == 1;
            }
        },
        SOUTHERN_COURT { // from class: net.time4j.calendar.Nengo.Selector.7
            @Override // net.time4j.engine.ChronoCondition
            public boolean test(Nengo nengo) {
                return nengo.court == -1;
            }
        },
        KAMAKURA_PERIOD { // from class: net.time4j.calendar.Nengo.Selector.8
            @Override // net.time4j.engine.ChronoCondition
            public boolean test(Nengo nengo) {
                return nengo.relgregyear >= 1185 && nengo.relgregyear < 1332;
            }
        },
        HEIAN_PERIOD { // from class: net.time4j.calendar.Nengo.Selector.9
            @Override // net.time4j.engine.ChronoCondition
            public boolean test(Nengo nengo) {
                return nengo.relgregyear >= 794 && nengo.relgregyear < 1185;
            }
        },
        NARA_PERIOD { // from class: net.time4j.calendar.Nengo.Selector.10
            @Override // net.time4j.engine.ChronoCondition
            public boolean test(Nengo nengo) {
                return nengo.relgregyear >= 710 && nengo.relgregyear < 794;
            }
        },
        ASUKA_PERIOD { // from class: net.time4j.calendar.Nengo.Selector.11
            @Override // net.time4j.engine.ChronoCondition
            public boolean test(Nengo nengo) {
                return nengo.relgregyear >= 538 && nengo.relgregyear < 710;
            }
        }
    }

    public int getFirstRelatedGregorianYear() {
        return this.relgregyear;
    }

    int getIndexOfficial() {
        return this.index;
    }

    long getStartAsDaysSinceEpochUTC() {
        return this.start;
    }

    public int hashCode() {
        long j = this.start;
        return (int) (j ^ (j >>> 32));
    }

    /* JADX WARN: Removed duplicated region for block: B:128:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x010e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00ab A[Catch: EOFException -> 0x0095, IOException -> 0x018e, TRY_ENTER, TRY_LEAVE, TryCatch #4 {IOException -> 0x018e, blocks: (B:4:0x0041, B:7:0x0050, B:9:0x0057, B:11:0x0071, B:13:0x0077, B:15:0x007d, B:19:0x0089, B:24:0x00a0, B:27:0x00ab, B:41:0x0109, B:44:0x010e, B:50:0x011b, B:52:0x0124, B:53:0x0130, B:55:0x0136, B:57:0x013e, B:59:0x014e, B:60:0x0170, B:30:0x00da, B:32:0x00f1), top: B:138:0x0041 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01a3  */
    static {
        /*
            Method dump skipped, instructions count: 874
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: net.time4j.calendar.Nengo.<clinit>():void");
    }

    private Nengo(int i, long j, String str, String str2, String str3, String str4, String str5, byte b, int i2) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Missing kanji.");
        }
        if (str5.isEmpty()) {
            throw new IllegalArgumentException("Missing latin transcription.");
        }
        if (b > 1 || b < -1) {
            throw new IllegalArgumentException("Undefined court byte: " + ((int) b));
        }
        this.relgregyear = i;
        this.start = j;
        this.kanji = str;
        this.chinese = str2;
        this.korean = str3;
        this.russian = str4;
        this.romaji = str5;
        this.court = b;
        this.index = i2;
    }

    public static Nengo ofRelatedGregorianYear(int i) {
        return ofRelatedGregorianYear(i, Selector.OFFICIAL);
    }

    public static Nengo ofRelatedGregorianYear(int i, Selector selector) {
        Nengo nengo;
        Nengo nengo2;
        Nengo nengo3 = null;
        if (i >= 701) {
            int i2 = AnonymousClass1.$SwitchMap$net$time4j$calendar$Nengo$Selector[selector.ordinal()];
            if (i2 == 1) {
                if (i >= 1873) {
                    return ofRelatedGregorianYear(i, Selector.MODERN);
                }
                int length = OFFICIAL_NENGOS.length - 1;
                int i3 = 0;
                while (i3 <= length) {
                    int i4 = (i3 + length) >> 1;
                    if (OFFICIAL_NENGOS[i4].getFirstRelatedGregorianYear() <= i) {
                        i3 = i4 + 1;
                    } else {
                        length = i4 - 1;
                    }
                }
                if (i3 != 0) {
                    return OFFICIAL_NENGOS[i3 - 1];
                }
            } else if (i2 == 2) {
                int lowerBound = getLowerBound(selector);
                for (int length2 = OFFICIAL_NENGOS.length - 1; length2 >= lowerBound; length2--) {
                    nengo = OFFICIAL_NENGOS[length2];
                    if (nengo.relgregyear <= i) {
                        nengo3 = nengo;
                        break;
                    }
                }
            } else if (i2 != 3) {
                if (i2 != 4) {
                    int lowerBound2 = getLowerBound(selector);
                    int upperBound = getUpperBound(selector);
                    Nengo[] nengoArr = OFFICIAL_NENGOS;
                    if (i >= nengoArr[lowerBound2].relgregyear && i <= nengoArr[upperBound + 1].relgregyear) {
                        while (upperBound >= lowerBound2) {
                            nengo = OFFICIAL_NENGOS[upperBound];
                            if (nengo.relgregyear <= i) {
                                nengo3 = nengo;
                                break;
                            }
                            upperBound--;
                        }
                    }
                } else if (i >= 1334 && i <= 1393) {
                    int i5 = NENGO_OEI.index - 1;
                    while (true) {
                        nengo2 = OFFICIAL_NENGOS[i5];
                        if (nengo2.court != -1) {
                            break;
                        }
                        if (nengo2.relgregyear <= i) {
                            break;
                        }
                        i5--;
                    }
                    nengo3 = nengo2;
                }
            } else if (i >= 1332 && i <= 1394) {
                for (int length3 = NORTHERN_NENGOS.length - 1; length3 >= 0; length3--) {
                    nengo2 = NORTHERN_NENGOS[length3];
                    if (nengo2.relgregyear <= i) {
                        nengo3 = nengo2;
                    }
                }
            }
        }
        if (nengo3 != null) {
            return nengo3;
        }
        throw new IllegalArgumentException("Could not find nengo for year=" + i + ", selector=" + selector + ".");
    }

    public static Nengo ofKanji(String str) {
        Nengo nengo = KANJI_TO_NENGO.get(str);
        if (nengo != null) {
            return nengo;
        }
        throw new IllegalArgumentException("Could not find any nengo for Japanese kanji: " + str);
    }

    public static List<Nengo> parseRomaji(String str) {
        String strHepburn = hepburn(str, 0);
        TST tst = ROMAJI_TO_NENGO;
        return tst.find(tst.longestPrefixOf(strHepburn, 0));
    }

    public static List<Nengo> list() {
        return list(Selector.OFFICIAL);
    }

    public static List<Nengo> list(Selector selector) {
        List listAsList;
        int i = AnonymousClass1.$SwitchMap$net$time4j$calendar$Nengo$Selector[selector.ordinal()];
        if (i == 1) {
            listAsList = Arrays.asList(OFFICIAL_NENGOS);
        } else if (i == 3) {
            listAsList = Arrays.asList(NORTHERN_NENGOS);
        } else {
            int lowerBound = getLowerBound(selector);
            int upperBound = getUpperBound(selector);
            listAsList = new ArrayList((upperBound - lowerBound) + 1);
            while (lowerBound <= upperBound) {
                listAsList.add(OFFICIAL_NENGOS[lowerBound]);
                lowerBound++;
            }
        }
        return Collections.unmodifiableList(listAsList);
    }

    public boolean matches(Selector selector) {
        return selector.test(this);
    }

    public PlainDate getStart() {
        return PlainDate.of(this.start, EpochDays.UTC);
    }

    public boolean isModern() {
        return this.index >= MEIJI.index;
    }

    public String getDisplayName(Locale locale) {
        return getDisplayName(locale, TextWidth.WIDE);
    }

    public String getDisplayName(Locale locale, TextWidth textWidth) {
        String str;
        if (locale.getLanguage().isEmpty()) {
            return this.romaji;
        }
        int i = this.index;
        if (i < MEIJI.index || i > NEWEST.index || locale.getLanguage().equals("ru")) {
            return locale.getLanguage().equals("ja") ? this.kanji : locale.getLanguage().equals("zh") ? this.chinese : locale.getLanguage().equals("ko") ? this.korean : locale.getLanguage().equals("ru") ? "Период " + this.russian : this.romaji;
        }
        int i2 = 0;
        while (true) {
            Nengo[] nengoArr = MODERN_NENGOS;
            if (i2 >= nengoArr.length) {
                str = null;
                break;
            }
            if (equals(nengoArr[i2])) {
                str = MODERN_KEYS[i2];
                break;
            }
            i2++;
        }
        if (str == null) {
            throw new IllegalStateException("Modern nengos need an update.");
        }
        if (textWidth == TextWidth.NARROW) {
            str = str + "_n";
        }
        return CalendarText.getInstance("japanese", locale).getTextForms().get(str);
    }

    public Nengo findNext() {
        if (this.court == 1) {
            int i = this.index;
            Nengo[] nengoArr = NORTHERN_NENGOS;
            return i == nengoArr.length - 1 ? NENGO_OEI : nengoArr[i + 1];
        }
        int i2 = this.index;
        Nengo[] nengoArr2 = OFFICIAL_NENGOS;
        if (i2 == nengoArr2.length - 1) {
            return null;
        }
        return nengoArr2[i2 + 1];
    }

    public Nengo findPrevious() {
        if (this.court == 1) {
            int i = this.index;
            if (i == 0) {
                return OFFICIAL_NENGOS[NENGO_KENMU.index - 1];
            }
            return NORTHERN_NENGOS[i - 1];
        }
        int i2 = this.index;
        if (i2 == 0) {
            return null;
        }
        return OFFICIAL_NENGOS[i2 - 1];
    }

    @Override // net.time4j.engine.CalendarEra
    public String name() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.romaji);
        sb.append(" (");
        Nengo nengoFindNext = findNext();
        if (nengoFindNext != null) {
            sb.append(this.relgregyear);
            sb.append('-');
            sb.append(nengoFindNext.relgregyear);
        } else {
            sb.append("since ");
            sb.append(this.relgregyear);
        }
        sb.append(')');
        return sb.toString();
    }

    int getValue() {
        int length;
        int i;
        if (matches(Selector.NORTHERN_COURT)) {
            length = (this.index - NORTHERN_NENGOS.length) + NENGO_OEI.index;
            i = SHOWA.index;
        } else {
            length = this.index;
            i = SHOWA.index;
        }
        return (length - i) + 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Nengo)) {
            return false;
        }
        Nengo nengo = (Nengo) obj;
        return this.relgregyear == nengo.relgregyear && this.start == nengo.start && this.kanji.equals(nengo.kanji) && this.romaji.equals(nengo.romaji) && this.court == nengo.court;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.romaji);
        sb.append(' ');
        sb.append(this.kanji);
        sb.append(' ');
        Nengo nengoFindNext = findNext();
        if (nengoFindNext != null) {
            sb.append(this.relgregyear);
            sb.append('-');
            sb.append(nengoFindNext.relgregyear);
        } else {
            sb.append("since ");
            sb.append(this.relgregyear);
        }
        if (this.court != 0) {
            sb.append(" (");
            sb.append(this.court == 1 ? 'N' : 'S');
            sb.append(')');
        }
        return sb.toString();
    }

    static Nengo ofIndexOfficial(int i) {
        return OFFICIAL_NENGOS[i];
    }

    static String hepburn(CharSequence charSequence, int i) {
        int iMin = Math.min(charSequence.length(), i + 32);
        StringBuilder sb = null;
        for (int i2 = i; i2 < iMin; i2++) {
            char cCharAt = charSequence.charAt(i2);
            char c = 362;
            char c2 = 363;
            char upperCase = 332;
            char lowerCase = 333;
            if (i2 == i) {
                if (cCharAt != 212 && cCharAt != 244 && cCharAt != 333) {
                    upperCase = Character.toUpperCase(cCharAt);
                }
                if (cCharAt != 219 && cCharAt != 251 && cCharAt != 363) {
                    c = upperCase;
                }
            } else {
                if (cCharAt != 212 && cCharAt != 244 && cCharAt != 332) {
                    lowerCase = Character.toLowerCase(cCharAt);
                }
                if (cCharAt != 219 && cCharAt != 251 && cCharAt != 362) {
                    c2 = lowerCase;
                }
                c = c2;
            }
            if (cCharAt == '\'') {
                c = Typography.rightSingleQuote;
            }
            if (cCharAt == ' ') {
                c = '-';
            }
            if (sb != null || c != cCharAt) {
                if (sb == null) {
                    sb = new StringBuilder(32);
                    sb.append(charSequence.subSequence(i, i2));
                }
                sb.append(c);
            }
        }
        return sb == null ? charSequence.subSequence(i, iMin).toString() : sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String capitalize(CharSequence charSequence, int i) {
        int iMin = Math.min(charSequence.length(), i + 32);
        StringBuilder sb = null;
        int i2 = i;
        boolean z = true;
        while (i2 < iMin) {
            char cCharAt = charSequence.charAt(i2);
            char upperCase = z ? Character.toUpperCase(cCharAt) : Character.toLowerCase(cCharAt);
            boolean z2 = cCharAt == ' ';
            if (sb != null || upperCase != cCharAt) {
                if (sb == null) {
                    sb = new StringBuilder(32);
                    sb.append(charSequence.subSequence(i, i2));
                }
                sb.append(upperCase);
            }
            i2++;
            z = z2;
        }
        return sb == null ? charSequence.subSequence(i, iMin).toString() : sb.toString();
    }

    private static int getUpperBound(Selector selector) {
        switch (selector) {
            case NORTHERN_COURT:
                return NORTHERN_NENGOS.length - 1;
            case SOUTHERN_COURT:
                return NENGO_KENMU.index + 8;
            case EDO_PERIOD:
                return MEIJI.index - 1;
            case AZUCHI_MOMOYAMA_PERIOD:
                return 187;
            case MUROMACHI_PERIOD:
                return 184;
            case KAMAKURA_PERIOD:
                return NENGO_KENMU.index - 1;
            case HEIAN_PERIOD:
                return 102;
            case NARA_PERIOD:
                return 14;
            case ASUKA_PERIOD:
                return 2;
            default:
                return OFFICIAL_NENGOS.length - 1;
        }
    }

    private static int getLowerBound(Selector selector) {
        switch (selector) {
            case MODERN:
                return MEIJI.index;
            case NORTHERN_COURT:
            default:
                return 0;
            case SOUTHERN_COURT:
                return NENGO_KENMU.index;
            case EDO_PERIOD:
                return 188;
            case AZUCHI_MOMOYAMA_PERIOD:
                return 185;
            case MUROMACHI_PERIOD:
                return NENGO_KENMU.index + 1;
            case KAMAKURA_PERIOD:
                return 103;
            case HEIAN_PERIOD:
                return 15;
            case NARA_PERIOD:
                return 3;
        }
    }

    private static Nengo of(int i, boolean z) {
        return z ? NORTHERN_NENGOS[i] : OFFICIAL_NENGOS[i];
    }

    private Object readResolve() throws ObjectStreamException {
        try {
            int i = this.index;
            boolean z = true;
            if (this.court != 1) {
                z = false;
            }
            return of(i, z);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new StreamCorruptedException();
        }
    }

    static class Element implements TextElement<Nengo>, Serializable {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        static final Element SINGLETON = new Element();
        private static final long serialVersionUID = -1099321098836107792L;

        private Object readResolve() throws ObjectStreamException {
            return SINGLETON;
        }

        @Override // net.time4j.engine.ChronoElement
        public char getSymbol() {
            return 'G';
        }

        @Override // net.time4j.engine.ChronoElement
        public boolean isDateElement() {
            return true;
        }

        @Override // net.time4j.engine.ChronoElement
        public boolean isLenient() {
            return false;
        }

        @Override // net.time4j.engine.ChronoElement
        public boolean isTimeElement() {
            return false;
        }

        @Override // net.time4j.engine.ChronoElement
        public String name() {
            return "ERA";
        }

        private Element() {
        }

        @Override // net.time4j.format.TextElement
        public void print(ChronoDisplay chronoDisplay, Appendable appendable, AttributeQuery attributeQuery) throws IOException, ChronoException {
            appendable.append(((Nengo) chronoDisplay.get(this)).getDisplayName((Locale) attributeQuery.get(Attributes.LANGUAGE, Locale.ROOT), (TextWidth) attributeQuery.get(Attributes.TEXT_WIDTH, TextWidth.WIDE)));
        }

        /* JADX WARN: Removed duplicated region for block: B:45:0x00ea  */
        @Override // net.time4j.format.TextElement
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public net.time4j.calendar.Nengo parse(java.lang.CharSequence r17, java.text.ParsePosition r18, net.time4j.engine.AttributeQuery r19) {
            /*
                Method dump skipped, instructions count: 555
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: net.time4j.calendar.Nengo.Element.parse(java.lang.CharSequence, java.text.ParsePosition, net.time4j.engine.AttributeQuery):net.time4j.calendar.Nengo");
        }

        @Override // net.time4j.engine.ChronoElement
        public Class<Nengo> getType() {
            return Nengo.class;
        }

        @Override // java.util.Comparator
        public int compare(ChronoDisplay chronoDisplay, ChronoDisplay chronoDisplay2) {
            Nengo nengo = (Nengo) chronoDisplay.get(this);
            Nengo nengo2 = (Nengo) chronoDisplay2.get(this);
            if (nengo.start < nengo2.start) {
                return -1;
            }
            if (nengo.start > nengo2.start) {
                return 1;
            }
            return nengo.court == 1 ? nengo2.court == 1 ? 0 : 1 : nengo2.court == 1 ? -1 : 0;
        }

        @Override // net.time4j.engine.ChronoElement
        public Nengo getDefaultMinimum() {
            return Nengo.OFFICIAL_NENGOS[0];
        }

        @Override // net.time4j.engine.ChronoElement
        public Nengo getDefaultMaximum() {
            return Nengo.OFFICIAL_NENGOS[Nengo.OFFICIAL_NENGOS.length - 1];
        }

        @Override // net.time4j.engine.ChronoElement
        public String getDisplayName(Locale locale) {
            String str = CalendarText.getIsoInstance(locale).getTextForms().get("L_era");
            return str == null ? name() : str;
        }
    }

    private static class TST {
        private Node root;

        private TST() {
            this.root = null;
        }

        List<Nengo> find(String str) {
            if (str == null || str.length() == 0) {
                return Collections.emptyList();
            }
            Node nodeFind = find(this.root, str, 0);
            if (nodeFind == null) {
                return Collections.emptyList();
            }
            return Collections.unmodifiableList(nodeFind.nengos);
        }

        private static Node find(Node node, String str, int i) {
            if (node == null) {
                return null;
            }
            char cCharAt = str.charAt(i);
            if (cCharAt < node.c) {
                return find(node.left, str, i);
            }
            if (cCharAt > node.c) {
                return find(node.right, str, i);
            }
            return i < str.length() + (-1) ? find(node.mid, str, i + 1) : node;
        }

        void insert(String str, Nengo nengo) {
            if (str.isEmpty()) {
                throw new IllegalArgumentException("Empty key cannot be inserted.");
            }
            this.root = insert(this.root, str, nengo, 0);
        }

        private static Node insert(Node node, String str, Nengo nengo, int i) {
            char cCharAt = str.charAt(i);
            if (node == null) {
                node = new Node();
                node.c = cCharAt;
            }
            if (cCharAt < node.c) {
                node.left = insert(node.left, str, nengo, i);
            } else if (cCharAt <= node.c) {
                if (i < str.length() - 1) {
                    node.mid = insert(node.mid, str, nengo, i + 1);
                } else {
                    if (node.nengos == null) {
                        node.nengos = new ArrayList();
                    }
                    node.nengos.add(nengo);
                }
            } else {
                node.right = insert(node.right, str, nengo, i);
            }
            return node;
        }

        String longestPrefixOf(CharSequence charSequence, int i) {
            Node node = this.root;
            int length = charSequence.length();
            int i2 = i;
            int i3 = i2;
            while (node != null && i2 < length) {
                char cCharAt = charSequence.charAt(i2);
                if (cCharAt < node.c) {
                    node = node.left;
                } else if (cCharAt > node.c) {
                    node = node.right;
                } else {
                    i2++;
                    if (node.nengos != null) {
                        i3 = i2;
                    }
                    node = node.mid;
                }
            }
            if (i >= i3) {
                return null;
            }
            return charSequence.subSequence(i, i3).toString();
        }
    }

    private static class Node {
        private char c;
        private Node left;
        private Node mid;
        private List<Nengo> nengos;
        private Node right;

        private Node() {
            this.c = (char) 0;
            this.left = null;
            this.mid = null;
            this.right = null;
            this.nengos = null;
        }
    }
}
