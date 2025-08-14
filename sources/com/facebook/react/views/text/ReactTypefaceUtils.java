package com.facebook.react.views.text;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.TextUtils;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.assets.ReactFontManager;
import java.util.ArrayList;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes5.dex */
public class ReactTypefaceUtils {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:6:0x000d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int parseFontWeight(java.lang.String r2) {
        /*
            r0 = -1
            if (r2 == 0) goto Lae
            r2.hashCode()
            int r1 = r2.hashCode()
            switch(r1) {
                case -1039745817: goto L84;
                case 48625: goto L79;
                case 49586: goto L6e;
                case 50547: goto L63;
                case 51508: goto L58;
                case 52469: goto L4d;
                case 53430: goto L42;
                case 54391: goto L37;
                case 55352: goto L2a;
                case 56313: goto L1d;
                case 3029637: goto L10;
                default: goto Ld;
            }
        Ld:
            r2 = r0
            goto L8f
        L10:
            java.lang.String r1 = "bold"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L19
            goto Ld
        L19:
            r2 = 10
            goto L8f
        L1d:
            java.lang.String r1 = "900"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L26
            goto Ld
        L26:
            r2 = 9
            goto L8f
        L2a:
            java.lang.String r1 = "800"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L33
            goto Ld
        L33:
            r2 = 8
            goto L8f
        L37:
            java.lang.String r1 = "700"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L40
            goto Ld
        L40:
            r2 = 7
            goto L8f
        L42:
            java.lang.String r1 = "600"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L4b
            goto Ld
        L4b:
            r2 = 6
            goto L8f
        L4d:
            java.lang.String r1 = "500"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L56
            goto Ld
        L56:
            r2 = 5
            goto L8f
        L58:
            java.lang.String r1 = "400"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L61
            goto Ld
        L61:
            r2 = 4
            goto L8f
        L63:
            java.lang.String r1 = "300"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L6c
            goto Ld
        L6c:
            r2 = 3
            goto L8f
        L6e:
            java.lang.String r1 = "200"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L77
            goto Ld
        L77:
            r2 = 2
            goto L8f
        L79:
            java.lang.String r1 = "100"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L82
            goto Ld
        L82:
            r2 = 1
            goto L8f
        L84:
            java.lang.String r1 = "normal"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L8e
            goto Ld
        L8e:
            r2 = 0
        L8f:
            switch(r2) {
                case 0: goto Lab;
                case 1: goto La8;
                case 2: goto La5;
                case 3: goto La2;
                case 4: goto Lab;
                case 5: goto L9f;
                case 6: goto L9c;
                case 7: goto L99;
                case 8: goto L96;
                case 9: goto L93;
                case 10: goto L99;
                default: goto L92;
            }
        L92:
            goto Lae
        L93:
            r2 = 900(0x384, float:1.261E-42)
            return r2
        L96:
            r2 = 800(0x320, float:1.121E-42)
            return r2
        L99:
            r2 = 700(0x2bc, float:9.81E-43)
            return r2
        L9c:
            r2 = 600(0x258, float:8.41E-43)
            return r2
        L9f:
            r2 = 500(0x1f4, float:7.0E-43)
            return r2
        La2:
            r2 = 300(0x12c, float:4.2E-43)
            return r2
        La5:
            r2 = 200(0xc8, float:2.8E-43)
            return r2
        La8:
            r2 = 100
            return r2
        Lab:
            r2 = 400(0x190, float:5.6E-43)
            return r2
        Lae:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactTypefaceUtils.parseFontWeight(java.lang.String):int");
    }

    public static int parseFontStyle(String str) {
        if (str == null) {
            return -1;
        }
        if ("italic".equals(str)) {
            return 2;
        }
        return "normal".equals(str) ? 0 : -1;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static String parseFontVariant(ReadableArray readableArray) {
        if (readableArray == null || readableArray.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readableArray.size(); i++) {
            String string = readableArray.getString(i);
            if (string != null) {
                string.hashCode();
                char c = 65535;
                switch (string.hashCode()) {
                    case -1983120972:
                        if (string.equals("stylistic-thirteen")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -1933522176:
                        if (string.equals("stylistic-fifteen")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -1534462052:
                        if (string.equals("stylistic-eighteen")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -1195362251:
                        if (string.equals("proportional-nums")) {
                            c = 3;
                            break;
                        }
                        break;
                    case -1061392823:
                        if (string.equals("lining-nums")) {
                            c = 4;
                            break;
                        }
                        break;
                    case -899039099:
                        if (string.equals("historical-ligatures")) {
                            c = 5;
                            break;
                        }
                        break;
                    case -771984547:
                        if (string.equals("tabular-nums")) {
                            c = 6;
                            break;
                        }
                        break;
                    case -672279417:
                        if (string.equals("discretionary-ligatures")) {
                            c = 7;
                            break;
                        }
                        break;
                    case -659678800:
                        if (string.equals("oldstyle-nums")) {
                            c = '\b';
                            break;
                        }
                        break;
                    case 249095901:
                        if (string.equals("no-contextual")) {
                            c = '\t';
                            break;
                        }
                        break;
                    case 273808209:
                        if (string.equals("contextual")) {
                            c = '\n';
                            break;
                        }
                        break;
                    case 289909490:
                        if (string.equals("no-common-ligatures")) {
                            c = 11;
                            break;
                        }
                        break;
                    case 296506098:
                        if (string.equals("stylistic-eight")) {
                            c = '\f';
                            break;
                        }
                        break;
                    case 309330544:
                        if (string.equals("stylistic-seven")) {
                            c = CharUtils.CR;
                            break;
                        }
                        break;
                    case 310339585:
                        if (string.equals("stylistic-three")) {
                            c = 14;
                            break;
                        }
                        break;
                    case 604478526:
                        if (string.equals("stylistic-eleven")) {
                            c = 15;
                            break;
                        }
                        break;
                    case 915975441:
                        if (string.equals("no-historical-ligatures")) {
                            c = 16;
                            break;
                        }
                        break;
                    case 979426287:
                        if (string.equals("stylistic-five")) {
                            c = 17;
                            break;
                        }
                        break;
                    case 979432035:
                        if (string.equals("stylistic-four")) {
                            c = 18;
                            break;
                        }
                        break;
                    case 979664367:
                        if (string.equals("stylistic-nine")) {
                            c = 19;
                            break;
                        }
                        break;
                    case 1001434505:
                        if (string.equals("stylistic-one")) {
                            c = 20;
                            break;
                        }
                        break;
                    case 1001438213:
                        if (string.equals("stylistic-six")) {
                            c = 21;
                            break;
                        }
                        break;
                    case 1001439040:
                        if (string.equals("stylistic-ten")) {
                            c = 22;
                            break;
                        }
                        break;
                    case 1001439599:
                        if (string.equals("stylistic-two")) {
                            c = 23;
                            break;
                        }
                        break;
                    case 1030714463:
                        if (string.equals("stylistic-sixteen")) {
                            c = 24;
                            break;
                        }
                        break;
                    case 1044065430:
                        if (string.equals("stylistic-twelve")) {
                            c = 25;
                            break;
                        }
                        break;
                    case 1044067310:
                        if (string.equals("stylistic-twenty")) {
                            c = 26;
                            break;
                        }
                        break;
                    case 1082592379:
                        if (string.equals("no-discretionary-ligatures")) {
                            c = 27;
                            break;
                        }
                        break;
                    case 1183323111:
                        if (string.equals("small-caps")) {
                            c = 28;
                            break;
                        }
                        break;
                    case 1223989350:
                        if (string.equals("common-ligatures")) {
                            c = 29;
                            break;
                        }
                        break;
                    case 1463562569:
                        if (string.equals("stylistic-nineteen")) {
                            c = 30;
                            break;
                        }
                        break;
                    case 1648446397:
                        if (string.equals("stylistic-fourteen")) {
                            c = 31;
                            break;
                        }
                        break;
                    case 2097122634:
                        if (string.equals("stylistic-seventeen")) {
                            c = ' ';
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        arrayList.add("'ss13'");
                        break;
                    case 1:
                        arrayList.add("'ss15'");
                        break;
                    case 2:
                        arrayList.add("'ss18'");
                        break;
                    case 3:
                        arrayList.add("'pnum'");
                        break;
                    case 4:
                        arrayList.add("'lnum'");
                        break;
                    case 5:
                        arrayList.add("'hlig'");
                        break;
                    case 6:
                        arrayList.add("'tnum'");
                        break;
                    case 7:
                        arrayList.add("'dlig'");
                        break;
                    case '\b':
                        arrayList.add("'onum'");
                        break;
                    case '\t':
                        arrayList.add("'calt' off");
                        break;
                    case '\n':
                        arrayList.add("'calt'");
                        break;
                    case 11:
                        arrayList.add("'liga' off");
                        arrayList.add("'clig' off");
                        break;
                    case '\f':
                        arrayList.add("'ss08'");
                        break;
                    case '\r':
                        arrayList.add("'ss07'");
                        break;
                    case 14:
                        arrayList.add("'ss03'");
                        break;
                    case 15:
                        arrayList.add("'ss11'");
                        break;
                    case 16:
                        arrayList.add("'hlig' off");
                        break;
                    case 17:
                        arrayList.add("'ss05'");
                        break;
                    case 18:
                        arrayList.add("'ss04'");
                        break;
                    case 19:
                        arrayList.add("'ss09'");
                        break;
                    case 20:
                        arrayList.add("'ss01'");
                        break;
                    case 21:
                        arrayList.add("'ss06'");
                        break;
                    case 22:
                        arrayList.add("'ss10'");
                        break;
                    case 23:
                        arrayList.add("'ss02'");
                        break;
                    case 24:
                        arrayList.add("'ss16'");
                        break;
                    case 25:
                        arrayList.add("'ss12'");
                        break;
                    case 26:
                        arrayList.add("'ss20'");
                        break;
                    case 27:
                        arrayList.add("'dlig' off");
                        break;
                    case 28:
                        arrayList.add("'smcp'");
                        break;
                    case 29:
                        arrayList.add("'liga'");
                        arrayList.add("'clig'");
                        break;
                    case 30:
                        arrayList.add("'ss19'");
                        break;
                    case 31:
                        arrayList.add("'ss14'");
                        break;
                    case ' ':
                        arrayList.add("'ss17'");
                        break;
                }
            }
        }
        return TextUtils.join(", ", arrayList);
    }

    public static Typeface applyStyles(Typeface typeface, int i, int i2, String str, AssetManager assetManager) {
        ReactFontManager.TypefaceStyle typefaceStyle = new ReactFontManager.TypefaceStyle(i, i2);
        if (str != null) {
            return com.facebook.react.common.assets.ReactFontManager.getInstance().getTypeface(str, typefaceStyle, assetManager);
        }
        if (typeface == null) {
            typeface = Typeface.DEFAULT;
        }
        return typefaceStyle.apply(typeface);
    }
}
