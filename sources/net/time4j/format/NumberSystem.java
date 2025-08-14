package net.time4j.format;

import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.firestore.local.SQLitePersistence;
import java.io.IOException;
import net.time4j.base.MathUtils;

/* loaded from: classes4.dex */
public enum NumberSystem {
    ARABIC("latn") { // from class: net.time4j.format.NumberSystem.1
        @Override // net.time4j.format.NumberSystem
        public boolean contains(char c) {
            return c >= '0' && c <= '9';
        }

        @Override // net.time4j.format.NumberSystem
        public String getDigits() {
            return "0123456789";
        }

        @Override // net.time4j.format.NumberSystem
        public boolean isDecimal() {
            return true;
        }

        @Override // net.time4j.format.NumberSystem
        public String toNumeral(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Cannot convert: " + i);
            }
            return Integer.toString(i);
        }

        @Override // net.time4j.format.NumberSystem
        public int toInteger(String str, Leniency leniency) throws NumberFormatException {
            int i = Integer.parseInt(str);
            if (i >= 0) {
                return i;
            }
            throw new NumberFormatException("Cannot convert negative number: " + str);
        }
    },
    ARABIC_INDIC("arab") { // from class: net.time4j.format.NumberSystem.2
        @Override // net.time4j.format.NumberSystem
        public String getDigits() {
            return "٠١٢٣٤٥٦٧٨٩";
        }

        @Override // net.time4j.format.NumberSystem
        public boolean isDecimal() {
            return true;
        }
    },
    ARABIC_INDIC_EXT("arabext") { // from class: net.time4j.format.NumberSystem.3
        @Override // net.time4j.format.NumberSystem
        public String getDigits() {
            return "۰۱۲۳۴۵۶۷۸۹";
        }

        @Override // net.time4j.format.NumberSystem
        public boolean isDecimal() {
            return true;
        }
    },
    BENGALI("beng") { // from class: net.time4j.format.NumberSystem.4
        @Override // net.time4j.format.NumberSystem
        public String getDigits() {
            return "০১২৩৪৫৬৭৮৯";
        }

        @Override // net.time4j.format.NumberSystem
        public boolean isDecimal() {
            return true;
        }
    },
    DEVANAGARI("deva") { // from class: net.time4j.format.NumberSystem.5
        @Override // net.time4j.format.NumberSystem
        public String getDigits() {
            return "०१२३४५६७८९";
        }

        @Override // net.time4j.format.NumberSystem
        public boolean isDecimal() {
            return true;
        }
    },
    DOZENAL("dozenal") { // from class: net.time4j.format.NumberSystem.6
        @Override // net.time4j.format.NumberSystem
        public boolean contains(char c) {
            return (c >= '0' && c <= '9') || c == 8586 || c == 8587;
        }

        @Override // net.time4j.format.NumberSystem
        public String getDigits() {
            return "0123456789↊↋";
        }

        @Override // net.time4j.format.NumberSystem
        public boolean isDecimal() {
            return false;
        }

        @Override // net.time4j.format.NumberSystem
        public String toNumeral(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Cannot convert: " + i);
            }
            return Integer.toString(i, 12).replace('a', (char) 8586).replace(Constants.INAPP_POSITION_BOTTOM, (char) 8587);
        }

        @Override // net.time4j.format.NumberSystem
        public int toNumeral(int i, Appendable appendable) throws IOException {
            if (i >= 0) {
                int i2 = 1;
                while (true) {
                    if (i2 > 4) {
                        i2 = 0;
                        break;
                    }
                    if (i < NumberSystem.D_FACTORS[i2]) {
                        break;
                    }
                    i2++;
                }
                if (i2 > 0) {
                    int i3 = i2 - 1;
                    do {
                        int i4 = i / NumberSystem.D_FACTORS[i3];
                        appendable.append(i4 == 11 ? (char) 8587 : i4 == 10 ? (char) 8586 : (char) (i4 + 48));
                        i -= i4 * NumberSystem.D_FACTORS[i3];
                        i3--;
                    } while (i3 >= 0);
                    return i2;
                }
            }
            return super.toNumeral(i, appendable);
        }

        @Override // net.time4j.format.NumberSystem
        public int toInteger(String str, Leniency leniency) throws NumberFormatException {
            int i = Integer.parseInt(str.replace((char) 8586, 'a').replace((char) 8587, Constants.INAPP_POSITION_BOTTOM), 12);
            if (i >= 0) {
                return i;
            }
            throw new NumberFormatException("Cannot convert negative number: " + str);
        }
    },
    ETHIOPIC("ethiopic") { // from class: net.time4j.format.NumberSystem.7
        @Override // net.time4j.format.NumberSystem
        public boolean contains(char c) {
            return c >= 4969 && c <= 4988;
        }

        @Override // net.time4j.format.NumberSystem
        public String getDigits() {
            return "፩፪፫፬፭፮፯፰፱፲፳፴፵፶፷፸፹፺፻፼";
        }

        @Override // net.time4j.format.NumberSystem
        public boolean isDecimal() {
            return false;
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x005c  */
        @Override // net.time4j.format.NumberSystem
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.String toNumeral(int r11) {
            /*
                r10 = this;
                r0 = 1
                if (r11 < r0) goto L82
                java.lang.String r11 = java.lang.String.valueOf(r11)
                int r1 = r11.length()
                int r2 = r1 + (-1)
                int r3 = r2 % 2
                if (r3 != 0) goto L21
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                java.lang.String r3 = "0"
                r2.<init>(r3)
                java.lang.StringBuilder r11 = r2.append(r11)
                java.lang.String r11 = r11.toString()
                goto L22
            L21:
                r1 = r2
            L22:
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                r3 = r1
            L28:
                if (r3 < 0) goto L7d
                int r4 = r1 - r3
                char r4 = r11.charAt(r4)
                int r5 = r3 + (-1)
                int r6 = r1 - r5
                char r6 = r11.charAt(r6)
                r7 = 48
                r8 = 0
                if (r6 == r7) goto L41
                int r6 = r6 + 4920
                char r6 = (char) r6
                goto L42
            L41:
                r6 = r8
            L42:
                if (r4 == r7) goto L48
                int r4 = r4 + 4929
                char r4 = (char) r4
                goto L49
            L48:
                r4 = r8
            L49:
                int r7 = r5 % 4
                int r7 = r7 / 2
                r9 = 4987(0x137b, float:6.988E-42)
                if (r5 == 0) goto L5c
                if (r7 == 0) goto L59
                if (r6 != 0) goto L57
                if (r4 == 0) goto L5c
            L57:
                r5 = r9
                goto L5d
            L59:
                r5 = 4988(0x137c, float:6.99E-42)
                goto L5d
            L5c:
                r5 = r8
            L5d:
                r7 = 4969(0x1369, float:6.963E-42)
                if (r6 != r7) goto L6a
                if (r4 != 0) goto L6a
                if (r1 <= r0) goto L6a
                if (r5 == r9) goto L6b
                if (r3 != r1) goto L6a
                goto L6b
            L6a:
                r8 = r6
            L6b:
                if (r4 == 0) goto L70
                r2.append(r4)
            L70:
                if (r8 == 0) goto L75
                r2.append(r8)
            L75:
                if (r5 == 0) goto L7a
                r2.append(r5)
            L7a:
                int r3 = r3 + (-2)
                goto L28
            L7d:
                java.lang.String r11 = r2.toString()
                return r11
            L82:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "Can only convert positive numbers: "
                r1.<init>(r2)
                java.lang.StringBuilder r11 = r1.append(r11)
                java.lang.String r11 = r11.toString()
                r0.<init>(r11)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: net.time4j.format.NumberSystem.AnonymousClass7.toNumeral(int):java.lang.String");
        }

        @Override // net.time4j.format.NumberSystem
        public int toInteger(String str, Leniency leniency) {
            int i;
            int i2 = 1;
            boolean z = false;
            boolean z2 = false;
            int iAddEthiopic = 0;
            int i3 = 0;
            for (int length = str.length() - 1; length >= 0; length--) {
                char cCharAt = str.charAt(length);
                if (cCharAt >= 4969 && cCharAt < 4978) {
                    i = cCharAt - 4968;
                } else if (cCharAt < 4978 || cCharAt >= 4987) {
                    if (cCharAt == 4988) {
                        if (z && i3 == 0) {
                            i3 = 1;
                        }
                        iAddEthiopic = NumberSystem.addEthiopic(iAddEthiopic, i3, i2);
                        i2 = z ? i2 * 100 : i2 * 10000;
                        z2 = true;
                        z = false;
                        i3 = 0;
                    } else if (cCharAt == 4987) {
                        iAddEthiopic = NumberSystem.addEthiopic(iAddEthiopic, i3, i2);
                        i2 *= 100;
                        z = true;
                        z2 = false;
                        i3 = 0;
                    }
                } else {
                    i = (cCharAt - 4977) * 10;
                }
                i3 += i;
            }
            return NumberSystem.addEthiopic(iAddEthiopic, ((z || z2) && i3 == 0) ? 1 : i3, i2);
        }
    },
    GUJARATI("gujr") { // from class: net.time4j.format.NumberSystem.8
        @Override // net.time4j.format.NumberSystem
        public String getDigits() {
            return "૦૧૨૩૪૫૬૭૮૯";
        }

        @Override // net.time4j.format.NumberSystem
        public boolean isDecimal() {
            return true;
        }
    },
    JAPANESE("jpan") { // from class: net.time4j.format.NumberSystem.9
        @Override // net.time4j.format.NumberSystem
        public String getDigits() {
            return "一二三四五六七八九十百千";
        }

        @Override // net.time4j.format.NumberSystem
        public boolean isDecimal() {
            return false;
        }

        @Override // net.time4j.format.NumberSystem
        public String toNumeral(int i) {
            if (i < 1 || i > 9999) {
                throw new IllegalArgumentException("Cannot convert: " + i);
            }
            String digits = getDigits();
            int i2 = i / 1000;
            int i3 = i % 1000;
            int i4 = i3 / 100;
            int i5 = i3 % 100;
            int i6 = i5 / 10;
            int i7 = i5 % 10;
            StringBuilder sb = new StringBuilder();
            if (i2 >= 1) {
                if (i2 > 1) {
                    sb.append(digits.charAt(i2 - 1));
                }
                sb.append((char) 21315);
            }
            if (i4 >= 1) {
                if (i4 > 1) {
                    sb.append(digits.charAt(i4 - 1));
                }
                sb.append((char) 30334);
            }
            if (i6 >= 1) {
                if (i6 > 1) {
                    sb.append(digits.charAt(i6 - 1));
                }
                sb.append((char) 21313);
            }
            if (i7 > 0) {
                sb.append(digits.charAt(i7 - 1));
            }
            return sb.toString();
        }

        @Override // net.time4j.format.NumberSystem
        public int toInteger(String str, Leniency leniency) {
            boolean z;
            String digits = getDigits();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            for (int length = str.length() - 1; length >= 0; length--) {
                char cCharAt = str.charAt(length);
                if (cCharAt == 21313) {
                    if (i != 0 || i3 != 0 || i4 != 0) {
                        throw new IllegalArgumentException("Invalid Japanese numeral: " + str);
                    }
                    i++;
                } else if (cCharAt == 21315) {
                    if (i4 != 0) {
                        throw new IllegalArgumentException("Invalid Japanese numeral: " + str);
                    }
                    i4++;
                } else if (cCharAt != 30334) {
                    int i5 = 0;
                    while (true) {
                        if (i5 >= 9) {
                            z = false;
                            break;
                        }
                        if (digits.charAt(i5) == cCharAt) {
                            int i6 = i5 + 1;
                            if (i4 == 1) {
                                i2 += i6 * 1000;
                                i4 = -1;
                            } else if (i3 == 1) {
                                i2 += i6 * 100;
                                i3 = -1;
                            } else if (i == 1) {
                                i2 += i6 * 10;
                                i = -1;
                            } else {
                                i2 += i6;
                            }
                            z = true;
                        } else {
                            i5++;
                        }
                    }
                    if (!z) {
                        throw new IllegalArgumentException("Invalid Japanese numeral: " + str);
                    }
                } else {
                    if (i3 != 0 || i4 != 0) {
                        throw new IllegalArgumentException("Invalid Japanese numeral: " + str);
                    }
                    i3++;
                }
            }
            if (i == 1) {
                i2 += 10;
            }
            if (i3 == 1) {
                i2 += 100;
            }
            return i4 == 1 ? i2 + 1000 : i2;
        }
    },
    KHMER("khmr") { // from class: net.time4j.format.NumberSystem.10
        @Override // net.time4j.format.NumberSystem
        public String getDigits() {
            return "០១២៣៤៥៦៧៨៩";
        }

        @Override // net.time4j.format.NumberSystem
        public boolean isDecimal() {
            return true;
        }
    },
    MYANMAR("mymr") { // from class: net.time4j.format.NumberSystem.11
        @Override // net.time4j.format.NumberSystem
        public String getDigits() {
            return "၀၁၂၃၄၅၆၇၈၉";
        }

        @Override // net.time4j.format.NumberSystem
        public boolean isDecimal() {
            return true;
        }
    },
    ORYA("orya") { // from class: net.time4j.format.NumberSystem.12
        @Override // net.time4j.format.NumberSystem
        public String getDigits() {
            return "୦୧୨୩୪୫୬୭୮୯";
        }

        @Override // net.time4j.format.NumberSystem
        public boolean isDecimal() {
            return true;
        }
    },
    ROMAN("roman") { // from class: net.time4j.format.NumberSystem.13
        @Override // net.time4j.format.NumberSystem
        public String getDigits() {
            return "IVXLCDM";
        }

        @Override // net.time4j.format.NumberSystem
        public boolean isDecimal() {
            return false;
        }

        @Override // net.time4j.format.NumberSystem
        public String toNumeral(int i) {
            if (i < 1 || i > 3999) {
                throw new IllegalArgumentException("Out of range (1-3999): " + i);
            }
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < NumberSystem.NUMBERS.length; i2++) {
                while (i >= NumberSystem.NUMBERS[i2]) {
                    sb.append(NumberSystem.LETTERS[i2]);
                    i -= NumberSystem.NUMBERS[i2];
                }
            }
            return sb.toString();
        }

        /* JADX WARN: Code restructure failed: missing block: B:71:0x0016, code lost:
        
            continue;
         */
        @Override // net.time4j.format.NumberSystem
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int toInteger(java.lang.String r12, net.time4j.format.Leniency r13) {
            /*
                Method dump skipped, instructions count: 217
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: net.time4j.format.NumberSystem.AnonymousClass13.toInteger(java.lang.String, net.time4j.format.Leniency):int");
        }

        @Override // net.time4j.format.NumberSystem
        public boolean contains(char c) {
            char upperCase = Character.toUpperCase(c);
            return upperCase == 'I' || upperCase == 'V' || upperCase == 'X' || upperCase == 'L' || upperCase == 'C' || upperCase == 'D' || upperCase == 'M';
        }
    },
    TELUGU("telu") { // from class: net.time4j.format.NumberSystem.14
        @Override // net.time4j.format.NumberSystem
        public String getDigits() {
            return "౦౧౨౩౪౫౬౭౮౯";
        }

        @Override // net.time4j.format.NumberSystem
        public boolean isDecimal() {
            return true;
        }
    },
    THAI("thai") { // from class: net.time4j.format.NumberSystem.15
        @Override // net.time4j.format.NumberSystem
        public String getDigits() {
            return "๐๑๒๓๔๕๖๗๘๙";
        }

        @Override // net.time4j.format.NumberSystem
        public boolean isDecimal() {
            return true;
        }
    };

    private static final char ETHIOPIC_HUNDRED = 4987;
    private static final char ETHIOPIC_ONE = 4969;
    private static final char ETHIOPIC_TEN = 4978;
    private static final char ETHIOPIC_TEN_THOUSAND = 4988;
    private final String code;
    private static final int[] NUMBERS = {1000, SQLitePersistence.MAX_ARGS, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] LETTERS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "IV", "I"};
    private static final int[] D_FACTORS = {1, 12, 144, 1728, 20736};

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isValidRomanCombination(char c, char c2) {
        if (c == 'C') {
            return c2 == 'M' || c2 == 'D';
        }
        if (c == 'I') {
            return c2 == 'X' || c2 == 'V';
        }
        if (c != 'X') {
            return false;
        }
        return c2 == 'C' || c2 == 'L';
    }

    public String getCode() {
        return this.code;
    }

    NumberSystem(String str) {
        this.code = str;
    }

    public String toNumeral(int i) {
        if (isDecimal() && i >= 0) {
            int iCharAt = getDigits().charAt(0) - '0';
            String string = Integer.toString(i);
            StringBuilder sb = new StringBuilder();
            int length = string.length();
            for (int i2 = 0; i2 < length; i2++) {
                sb.append((char) (string.charAt(i2) + iCharAt));
            }
            return sb.toString();
        }
        throw new IllegalArgumentException("Cannot convert: " + i);
    }

    public int toNumeral(int i, Appendable appendable) throws IOException {
        String numeral = toNumeral(i);
        appendable.append(numeral);
        return numeral.length();
    }

    public final int toInteger(String str) {
        return toInteger(str, Leniency.SMART);
    }

    public int toInteger(String str, Leniency leniency) throws NumberFormatException {
        if (isDecimal()) {
            int iCharAt = getDigits().charAt(0) - '0';
            StringBuilder sb = new StringBuilder();
            int length = str.length();
            for (int i = 0; i < length; i++) {
                sb.append((char) (str.charAt(i) - iCharAt));
            }
            int i2 = Integer.parseInt(sb.toString());
            if (i2 >= 0) {
                return i2;
            }
            throw new NumberFormatException("Cannot convert negative number: " + str);
        }
        throw new NumberFormatException("Cannot convert: " + str);
    }

    public boolean contains(char c) {
        String digits = getDigits();
        int length = digits.length();
        for (int i = 0; i < length; i++) {
            if (digits.charAt(i) == c) {
                return true;
            }
        }
        return false;
    }

    public String getDigits() {
        throw new AbstractMethodError();
    }

    public boolean isDecimal() {
        throw new AbstractMethodError();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int addEthiopic(int i, int i2, int i3) {
        return MathUtils.safeAdd(i, MathUtils.safeMultiply(i2, i3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getValue(char c) {
        if (c == 'C') {
            return 100;
        }
        if (c == 'D') {
            return 500;
        }
        if (c == 'I') {
            return 1;
        }
        if (c == 'V') {
            return 5;
        }
        if (c == 'X') {
            return 10;
        }
        if (c == 'L') {
            return 50;
        }
        if (c == 'M') {
            return 1000;
        }
        throw new NumberFormatException("Invalid Roman digit: " + c);
    }
}
