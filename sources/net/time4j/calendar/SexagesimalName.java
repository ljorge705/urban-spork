package net.time4j.calendar;

import com.google.firebase.dynamiclinks.DynamicLink;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.base.MathUtils;
import net.time4j.format.CalendarText;

/* loaded from: classes4.dex */
public class SexagesimalName implements Comparable<SexagesimalName>, Serializable {
    private static final SexagesimalName[] INSTANCES;
    private static final Set<String> LANGS_WITHOUT_SEP;
    private static final Map<String, String[]> LANG_2_BRANCH;
    private static final Map<String, String[]> LANG_2_STEM;
    private static final long serialVersionUID = -4556668597489844917L;
    private final int number;
    private static final String[] STEMS_SIMPLE = {"jia", "yi", "bing", "ding", "wu", "ji", "geng", "xin", "ren", "gui"};
    private static final String[] STEMS_PINYIN = {"jiǎ", "yǐ", "bǐng", "dīng", "wù", "jǐ", "gēng", "xīn", "rén", "guǐ"};
    private static final String[] STEMS_CHINESE = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    private static final String[] STEMS_KOREAN = {"갑", "을", "병", "정", "무", "기", "경", "신", "임", "계"};
    private static final String[] STEMS_VIETNAMESE = {"giáp", "ất", "bính", "đinh", "mậu", "kỷ", "canh", "tân", "nhâm", "quý"};
    private static final String[] STEMS_RUSSIAN = {"Цзя", "И", "Бин", "Дин", "У", "Цзи", "Гэн", "Синь", "Жэнь", "Гуй"};
    private static final String[] BRANCHES_SIMPLE = {"zi", "chou", "yin", "mao", "chen", DynamicLink.SocialMetaTagParameters.KEY_SOCIAL_IMAGE_LINK, "wu", "wei", "shen", "you", "xu", "hai"};
    private static final String[] BRANCHES_PINYIN = {"zǐ", "chǒu", "yín", "mǎo", "chén", "sì", "wǔ", "wèi", "shēn", "yǒu", "xū", "hài"};
    private static final String[] BRANCHES_CHINESE = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    private static final String[] BRANCHES_KOREAN = {"자", "축", "인", "묘", "진", "사", "오", "미", "신", "유", "술", "해"};
    private static final String[] BRANCHES_VIETNAMESE = {"tí", "sửu", "dần", "mão", "thìn", "tị", "ngọ", "mùi", "thân", "dậu", "tuất", "hợi"};
    private static final String[] BRANCHES_RUSSIAN = {"Цзы", "Чоу", "Инь", "Мао", "Чэнь", "Сы", "У", "Вэй", "Шэнь", "Ю", "Сюй", "Хай"};

    private static char toASCII(char c) {
        if (c == 224) {
            return 'a';
        }
        if (c == 249) {
            return AbstractJsonLexerKt.UNICODE_ESC;
        }
        if (c == 275) {
            return 'e';
        }
        if (c == 299) {
            return 'i';
        }
        if (c == 363) {
            return AbstractJsonLexerKt.UNICODE_ESC;
        }
        if (c == 462) {
            return 'a';
        }
        if (c == 464) {
            return 'i';
        }
        if (c == 466) {
            return 'o';
        }
        if (c == 232 || c == 233) {
            return 'e';
        }
        if (c == 236 || c == 237) {
            return 'i';
        }
        return c;
    }

    public int getNumber() {
        return this.number;
    }

    public int hashCode() {
        return this.number;
    }

    static {
        SexagesimalName[] sexagesimalNameArr = new SexagesimalName[60];
        int i = 0;
        while (i < 60) {
            int i2 = i + 1;
            sexagesimalNameArr[i] = new SexagesimalName(i2);
            i = i2;
        }
        INSTANCES = sexagesimalNameArr;
        HashMap map = new HashMap();
        map.put("root", STEMS_SIMPLE);
        String[] strArr = STEMS_CHINESE;
        map.put("zh", strArr);
        map.put("ja", strArr);
        map.put("ko", STEMS_KOREAN);
        map.put("vi", STEMS_VIETNAMESE);
        map.put("ru", STEMS_RUSSIAN);
        LANG_2_STEM = Collections.unmodifiableMap(map);
        HashMap map2 = new HashMap();
        map2.put("root", BRANCHES_SIMPLE);
        String[] strArr2 = BRANCHES_CHINESE;
        map2.put("zh", strArr2);
        map2.put("ja", strArr2);
        map2.put("ko", BRANCHES_KOREAN);
        map2.put("vi", BRANCHES_VIETNAMESE);
        map2.put("ru", BRANCHES_RUSSIAN);
        LANG_2_BRANCH = Collections.unmodifiableMap(map2);
        HashSet hashSet = new HashSet();
        hashSet.add("zh");
        hashSet.add("ja");
        hashSet.add("ko");
        LANGS_WITHOUT_SEP = Collections.unmodifiableSet(hashSet);
    }

    SexagesimalName(int i) {
        this.number = i;
    }

    public static SexagesimalName of(int i) {
        if (i < 1 || i > 60) {
            throw new IllegalArgumentException("Out of range: " + i);
        }
        return INSTANCES[i - 1];
    }

    public static SexagesimalName of(Stem stem, Branch branch) {
        int iOrdinal = stem.ordinal();
        SexagesimalName sexagesimalNameOf = of(iOrdinal + 1 + MathUtils.floorModulo((branch.ordinal() - iOrdinal) * 25, 60));
        if (sexagesimalNameOf.getStem() == stem && sexagesimalNameOf.getBranch() == branch) {
            return sexagesimalNameOf;
        }
        throw new IllegalArgumentException("Invalid combination of stem and branch.");
    }

    public Stem getStem() {
        return Stem.values()[(this.number % 10 != 0 ? r0 : 10) - 1];
    }

    public Branch getBranch() {
        return Branch.values()[(this.number % 12 != 0 ? r0 : 12) - 1];
    }

    public static SexagesimalName parse(String str, Locale locale) throws ParseException {
        SexagesimalName sexagesimalName = parse(str, new ParsePosition(0), locale, true);
        if (sexagesimalName != null) {
            return sexagesimalName;
        }
        throw new ParseException(str, 0);
    }

    public String getDisplayName(Locale locale) {
        return getStem().getDisplayName(locale) + (LANGS_WITHOUT_SEP.contains(locale.getLanguage()) ? "" : "-") + getBranch().getDisplayName(locale);
    }

    public String getZodiac(Locale locale) {
        return getBranch().getZodiac(locale);
    }

    public SexagesimalName roll(int i) {
        return i == 0 ? this : of(MathUtils.floorModulo(MathUtils.safeAdd(this.number - 1, i), 60) + 1);
    }

    @Override // java.lang.Comparable
    public int compareTo(SexagesimalName sexagesimalName) {
        if (getClass().equals(sexagesimalName.getClass())) {
            return this.number - ((SexagesimalName) SexagesimalName.class.cast(sexagesimalName)).number;
        }
        throw new ClassCastException("Cannot compare different types.");
    }

    public boolean equals(Object obj) {
        return getClass().equals(obj.getClass()) && this.number == ((SexagesimalName) obj).number;
    }

    public String toString() {
        return getDisplayName(Locale.ROOT) + "(" + String.valueOf(this.number) + ")";
    }

    static SexagesimalName parse(CharSequence charSequence, ParsePosition parsePosition, Locale locale, boolean z) {
        Stem stem;
        Branch branch;
        int i;
        int i2;
        Stem[] stemArr;
        Locale locale2 = locale;
        int index = parsePosition.getIndex();
        int length = charSequence.length();
        boolean zIsEmpty = locale.getLanguage().isEmpty();
        int i3 = index + 1;
        if (i3 >= length || index < 0) {
            parsePosition.setErrorIndex(index);
            return null;
        }
        if (LANGS_WITHOUT_SEP.contains(locale.getLanguage())) {
            Stem[] stemArrValues = Stem.values();
            int length2 = stemArrValues.length;
            int i4 = 0;
            while (true) {
                if (i4 >= length2) {
                    stem = null;
                    break;
                }
                stem = stemArrValues[i4];
                if (stem.getDisplayName(locale2).charAt(0) == charSequence.charAt(index)) {
                    break;
                }
                i4++;
            }
            if (stem != null) {
                Branch[] branchArrValues = Branch.values();
                int length3 = branchArrValues.length;
                for (int i5 = 0; i5 < length3; i5++) {
                    branch = branchArrValues[i5];
                    if (branch.getDisplayName(locale2).charAt(0) == charSequence.charAt(i3)) {
                        index += 2;
                        break;
                    }
                }
                branch = null;
            } else {
                branch = null;
            }
        } else {
            while (true) {
                if (i3 >= length) {
                    i3 = -1;
                    break;
                }
                if (charSequence.charAt(i3) == '-') {
                    break;
                }
                i3++;
            }
            if (i3 == -1) {
                parsePosition.setErrorIndex(index);
                return null;
            }
            Stem[] stemArrValues2 = Stem.values();
            int length4 = stemArrValues2.length;
            stem = null;
            int i6 = 0;
            while (i6 < length4) {
                Stem stem2 = stemArrValues2[i6];
                String displayName = stem2.getDisplayName(locale2);
                int i7 = index;
                while (true) {
                    if (i7 >= i3) {
                        stemArr = stemArrValues2;
                        break;
                    }
                    int i8 = i7 - index;
                    char cCharAt = charSequence.charAt(i7);
                    if (zIsEmpty) {
                        cCharAt = toASCII(cCharAt);
                    }
                    char c = cCharAt;
                    stemArr = stemArrValues2;
                    if (i8 >= displayName.length() || displayName.charAt(i8) != c) {
                        break;
                    }
                    if (i8 + 1 == displayName.length()) {
                        stem = stem2;
                        break;
                    }
                    i7++;
                    stemArrValues2 = stemArr;
                }
                i6++;
                stemArrValues2 = stemArr;
            }
            if (stem == null) {
                if (z && !zIsEmpty && i3 + 1 < length) {
                    return parse(charSequence, parsePosition, Locale.ROOT, true);
                }
                parsePosition.setErrorIndex(index);
                return null;
            }
            Branch[] branchArrValues2 = Branch.values();
            int length5 = branchArrValues2.length;
            int i9 = 0;
            branch = null;
            while (i9 < length5) {
                Branch branch2 = branchArrValues2[i9];
                String displayName2 = branch2.getDisplayName(locale2);
                int i10 = i3 + 1;
                while (true) {
                    if (i10 >= length) {
                        i = index;
                        i2 = length;
                        break;
                    }
                    int i11 = i10 - i3;
                    int i12 = i11 - 1;
                    char cCharAt2 = charSequence.charAt(i10);
                    if (zIsEmpty) {
                        cCharAt2 = toASCII(cCharAt2);
                    }
                    i = index;
                    char c2 = cCharAt2;
                    i2 = length;
                    if (i12 >= displayName2.length() || displayName2.charAt(i12) != c2) {
                        break;
                    }
                    if (i11 == displayName2.length()) {
                        index = i10 + 1;
                        branch = branch2;
                        break;
                    }
                    i10++;
                    length = i2;
                    index = i;
                }
                index = i;
                i9++;
                locale2 = locale;
                length = i2;
            }
        }
        if (stem != null && branch != null) {
            parsePosition.setIndex(index);
            return of(stem, branch);
        }
        if (z && !zIsEmpty) {
            return parse(charSequence, parsePosition, Locale.ROOT, true);
        }
        parsePosition.setErrorIndex(index);
        return null;
    }

    Object readResolve() throws ObjectStreamException {
        return of(this.number);
    }

    public enum Stem {
        JIA_1_WOOD_YANG,
        YI_2_WOOD_YIN,
        BING_3_FIRE_YANG,
        DING_4_FIRE_YIN,
        WU_5_EARTH_YANG,
        JI_6_EARTH_YIN,
        GENG_7_METAL_YANG,
        XIN_8_METAL_YIN,
        REN_9_WATER_YANG,
        GUI_10_WATER_YIN;

        public String getDisplayName(Locale locale) {
            String language = locale.getLanguage();
            Map map = SexagesimalName.LANG_2_STEM;
            if (language.isEmpty()) {
                language = "root";
            }
            String[] strArr = (String[]) map.get(language);
            if (strArr == null) {
                strArr = SexagesimalName.STEMS_PINYIN;
            }
            return strArr[ordinal()];
        }
    }

    public enum Branch {
        ZI_1_RAT,
        CHOU_2_OX,
        YIN_3_TIGER,
        MAO_4_HARE,
        CHEN_5_DRAGON,
        SI_6_SNAKE,
        WU_7_HORSE,
        WEI_8_SHEEP,
        SHEN_9_MONKEY,
        YOU_10_FOWL,
        XU_11_DOG,
        HAI_12_PIG;

        public String getDisplayName(Locale locale) {
            String language = locale.getLanguage();
            Map map = SexagesimalName.LANG_2_BRANCH;
            if (language.isEmpty()) {
                language = "root";
            }
            String[] strArr = (String[]) map.get(language);
            if (strArr == null) {
                strArr = SexagesimalName.BRANCHES_PINYIN;
            }
            return strArr[ordinal()];
        }

        public String getZodiac(Locale locale) {
            return CalendarText.getInstance("chinese", locale).getTextForms().get("zodiac-" + String.valueOf(ordinal() + 1));
        }
    }
}
