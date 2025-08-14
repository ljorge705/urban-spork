package net.time4j.format.internal;

import net.time4j.format.NumberSystem;

/* loaded from: classes4.dex */
public class DualFormatHelper {
    public static String toNumeral(NumberSystem numberSystem, char c, int i) {
        if (numberSystem.isDecimal()) {
            int i2 = c - '0';
            String string = Integer.toString(i);
            if (i2 == 0) {
                return string;
            }
            StringBuilder sb = new StringBuilder();
            int length = string.length();
            for (int i3 = 0; i3 < length; i3++) {
                sb.append((char) (string.charAt(i3) + i2));
            }
            return sb.toString();
        }
        return numberSystem.toNumeral(i);
    }
}
