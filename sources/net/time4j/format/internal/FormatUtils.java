package net.time4j.format.internal;

import com.clevertap.android.sdk.Constants;

/* loaded from: classes4.dex */
public class FormatUtils {
    private FormatUtils() {
    }

    public static String removeZones(String str) {
        int i;
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        int i2 = 0;
        boolean z = false;
        while (i2 < length) {
            char cCharAt = str.charAt(i2);
            if (cCharAt == '\'') {
                int i3 = i2 + 1;
                if (i3 >= length || str.charAt(i3) != '\'') {
                    z = !z;
                } else {
                    sb.append(cCharAt);
                    i2 = i3;
                }
                sb.append(cCharAt);
            } else if (z) {
                sb.append(cCharAt);
            } else if (cCharAt != 'z' && cCharAt != 'Z' && cCharAt != 'v' && cCharAt != 'V' && cCharAt != 'x' && cCharAt != 'X') {
                sb.append(cCharAt);
            }
            i2++;
        }
        int i4 = 0;
        while (i4 < sb.length()) {
            char cCharAt2 = sb.charAt(i4);
            if ((cCharAt2 == ' ' && (i = i4 + 1) < sb.length() && sb.charAt(i) == ' ') || cCharAt2 == '[' || cCharAt2 == ']' || cCharAt2 == '(' || cCharAt2 == ')') {
                sb.deleteCharAt(i4);
                i4--;
                i4++;
            } else {
                i4++;
            }
        }
        String strTrim = sb.toString().trim();
        if (strTrim.endsWith(" '")) {
            return strTrim.substring(0, strTrim.length() - 2) + "'";
        }
        return strTrim.endsWith(Constants.SEPARATOR_COMMA) ? strTrim.substring(0, strTrim.length() - 1) : strTrim;
    }
}
