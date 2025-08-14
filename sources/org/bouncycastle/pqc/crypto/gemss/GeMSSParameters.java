package org.bouncycastle.pqc.crypto.gemss;

import com.drew.metadata.exif.makernotes.OlympusCameraSettingsMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusMakernoteDirectory;
import com.drew.metadata.photoshop.PhotoshopDirectory;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

/* loaded from: classes4.dex */
public class GeMSSParameters {
    public static final GeMSSParameters bluegemss128;
    public static final GeMSSParameters bluegemss192;
    public static final GeMSSParameters bluegemss256;
    private static final Integer bluegemss_128;
    private static final Integer bluegemss_192;
    private static final Integer bluegemss_256;
    public static final GeMSSParameters cyangemss128;
    public static final GeMSSParameters cyangemss192;
    public static final GeMSSParameters cyangemss256;
    private static final Integer cyangemss_128;
    private static final Integer cyangemss_192;
    private static final Integer cyangemss_256;
    public static final GeMSSParameters dualmodems128;
    public static final GeMSSParameters dualmodems192;
    public static final GeMSSParameters dualmodems256;
    private static final Integer dualmodems_128;
    private static final Integer dualmodems_192;
    private static final Integer dualmodems_256;
    public static final GeMSSParameters fgemss128;
    public static final GeMSSParameters fgemss192;
    public static final GeMSSParameters fgemss256;
    private static final Integer fgemss_128;
    private static final Integer fgemss_192;
    private static final Integer fgemss_256;
    public static final GeMSSParameters gemss128;
    public static final GeMSSParameters gemss192;
    public static final GeMSSParameters gemss256;
    private static final Integer gemss_128;
    private static final Integer gemss_192;
    private static final Integer gemss_256;
    public static final GeMSSParameters magentagemss128;
    public static final GeMSSParameters magentagemss192;
    public static final GeMSSParameters magentagemss256;
    private static final Integer magentagemss_128;
    private static final Integer magentagemss_192;
    private static final Integer magentagemss_256;
    private static final Map<Integer, GeMSSParameters> oidToParams;
    private static final Map<GeMSSParameters, Integer> paramsToOid;
    public static final GeMSSParameters redgemss128;
    public static final GeMSSParameters redgemss192;
    public static final GeMSSParameters redgemss256;
    private static final Integer redgemss_128;
    private static final Integer redgemss_192;
    private static final Integer redgemss_256;
    public static final GeMSSParameters whitegemss128;
    public static final GeMSSParameters whitegemss192;
    public static final GeMSSParameters whitegemss256;
    private static final Integer whitegemss_128;
    private static final Integer whitegemss_192;
    private static final Integer whitegemss_256;
    private final GeMSSEngine engine;
    private final String name;

    static {
        GeMSSParameters geMSSParameters = new GeMSSParameters("gemss128", 128, 174, 12, 12, 4, 513, 9, 0);
        gemss128 = geMSSParameters;
        GeMSSParameters geMSSParameters2 = new GeMSSParameters("gemss192", 192, 265, 20, 22, 4, 513, 9, 0);
        gemss192 = geMSSParameters2;
        GeMSSParameters geMSSParameters3 = new GeMSSParameters("gemss256", 256, 354, 33, 30, 4, 513, 9, 0);
        gemss256 = geMSSParameters3;
        GeMSSParameters geMSSParameters4 = new GeMSSParameters("bluegemss128", 128, 175, 14, 13, 4, 129, 7, 0);
        bluegemss128 = geMSSParameters4;
        GeMSSParameters geMSSParameters5 = new GeMSSParameters("bluegemss192", 192, 265, 23, 22, 4, 129, 7, 0);
        bluegemss192 = geMSSParameters5;
        GeMSSParameters geMSSParameters6 = new GeMSSParameters("bluegemss256", 256, 358, 32, 34, 4, 129, 7, 0);
        bluegemss256 = geMSSParameters6;
        GeMSSParameters geMSSParameters7 = new GeMSSParameters("redgemss128", 128, 177, 15, 15, 4, 17, 4, 0);
        redgemss128 = geMSSParameters7;
        GeMSSParameters geMSSParameters8 = new GeMSSParameters("redgemss192", 192, 266, 25, 23, 4, 17, 4, 0);
        redgemss192 = geMSSParameters8;
        GeMSSParameters geMSSParameters9 = new GeMSSParameters("redgemss256", 256, 358, 35, 34, 4, 17, 4, 0);
        redgemss256 = geMSSParameters9;
        GeMSSParameters geMSSParameters10 = new GeMSSParameters("whitegemss128", 128, 175, 12, 12, 3, 513, 9, 0);
        whitegemss128 = geMSSParameters10;
        GeMSSParameters geMSSParameters11 = new GeMSSParameters("whitegemss192", 192, 268, 21, 21, 3, 513, 9, 0);
        whitegemss192 = geMSSParameters11;
        GeMSSParameters geMSSParameters12 = new GeMSSParameters("whitegemss256", 256, 364, 29, 31, 3, 513, 9, 0);
        whitegemss256 = geMSSParameters12;
        GeMSSParameters geMSSParameters13 = new GeMSSParameters("cyangemss128", 128, 177, 13, 14, 3, 129, 7, 0);
        cyangemss128 = geMSSParameters13;
        GeMSSParameters geMSSParameters14 = new GeMSSParameters("cyangemss192", 192, 270, 22, 23, 3, 129, 7, 0);
        cyangemss192 = geMSSParameters14;
        GeMSSParameters geMSSParameters15 = new GeMSSParameters("cyangemss256", 256, 364, 32, 31, 3, 129, 7, 0);
        cyangemss256 = geMSSParameters15;
        GeMSSParameters geMSSParameters16 = new GeMSSParameters("magentagemss128", 128, 178, 15, 15, 3, 17, 4, 0);
        magentagemss128 = geMSSParameters16;
        GeMSSParameters geMSSParameters17 = new GeMSSParameters("magentagemss192", 192, 271, 24, 24, 3, 17, 4, 0);
        magentagemss192 = geMSSParameters17;
        GeMSSParameters geMSSParameters18 = new GeMSSParameters("magentagemss256", 256, 366, 33, 33, 3, 17, 4, 0);
        magentagemss256 = geMSSParameters18;
        GeMSSParameters geMSSParameters19 = new GeMSSParameters("fgemss128", 128, 266, 11, 10, 1, 129, 7, 0);
        fgemss128 = geMSSParameters19;
        GeMSSParameters geMSSParameters20 = new GeMSSParameters("fgemss192", 192, 402, 18, 18, 1, OlympusMakernoteDirectory.TAG_PREVIEW_IMAGE, 9, 7);
        fgemss192 = geMSSParameters20;
        GeMSSParameters geMSSParameters21 = new GeMSSParameters("fgemss256", 256, 537, 26, 25, 1, 1152, 10, 7);
        fgemss256 = geMSSParameters21;
        GeMSSParameters geMSSParameters22 = new GeMSSParameters("dualmodems128", 128, 266, 11, 10, 1, 129, 7, 0);
        dualmodems128 = geMSSParameters22;
        GeMSSParameters geMSSParameters23 = new GeMSSParameters("dualmodems192", 192, 402, 18, 18, 1, 129, 7, 0);
        dualmodems192 = geMSSParameters23;
        GeMSSParameters geMSSParameters24 = new GeMSSParameters("dualmodems256", 256, 544, 32, 32, 1, 129, 7, 0);
        dualmodems256 = geMSSParameters24;
        Integer numValueOf = Integers.valueOf(257);
        gemss_128 = numValueOf;
        Integer numValueOf2 = Integers.valueOf(258);
        gemss_192 = numValueOf2;
        Integer numValueOf3 = Integers.valueOf(259);
        gemss_256 = numValueOf3;
        Integer numValueOf4 = Integers.valueOf(513);
        bluegemss_128 = numValueOf4;
        Integer numValueOf5 = Integers.valueOf(514);
        bluegemss_192 = numValueOf5;
        Integer numValueOf6 = Integers.valueOf(515);
        bluegemss_256 = numValueOf6;
        Integer numValueOf7 = Integers.valueOf(769);
        redgemss_128 = numValueOf7;
        Integer numValueOf8 = Integers.valueOf(770);
        redgemss_192 = numValueOf8;
        Integer numValueOf9 = Integers.valueOf(771);
        redgemss_256 = numValueOf9;
        Integer numValueOf10 = Integers.valueOf(1025);
        whitegemss_128 = numValueOf10;
        Integer numValueOf11 = Integers.valueOf(PhotoshopDirectory.TAG_LAYERS_GROUP_INFORMATION);
        whitegemss_192 = numValueOf11;
        Integer numValueOf12 = Integers.valueOf(1027);
        whitegemss_256 = numValueOf12;
        Integer numValueOf13 = Integers.valueOf(1281);
        cyangemss_128 = numValueOf13;
        Integer numValueOf14 = Integers.valueOf(1282);
        cyangemss_192 = numValueOf14;
        Integer numValueOf15 = Integers.valueOf(1283);
        cyangemss_256 = numValueOf15;
        Integer numValueOf16 = Integers.valueOf(1537);
        magentagemss_128 = numValueOf16;
        Integer numValueOf17 = Integers.valueOf(1538);
        magentagemss_192 = numValueOf17;
        Integer numValueOf18 = Integers.valueOf(OlympusCameraSettingsMakernoteDirectory.TagImageQuality2);
        magentagemss_256 = numValueOf18;
        Integer numValueOf19 = Integers.valueOf(1793);
        fgemss_128 = numValueOf19;
        Integer numValueOf20 = Integers.valueOf(1794);
        fgemss_192 = numValueOf20;
        Integer numValueOf21 = Integers.valueOf(1795);
        fgemss_256 = numValueOf21;
        Integer numValueOf22 = Integers.valueOf(2049);
        dualmodems_128 = numValueOf22;
        Integer numValueOf23 = Integers.valueOf(2050);
        dualmodems_192 = numValueOf23;
        Integer numValueOf24 = Integers.valueOf(2051);
        dualmodems_256 = numValueOf24;
        HashMap map = new HashMap();
        oidToParams = map;
        HashMap map2 = new HashMap();
        paramsToOid = map2;
        map.put(numValueOf, geMSSParameters);
        map.put(numValueOf2, geMSSParameters2);
        map.put(numValueOf3, geMSSParameters3);
        map.put(numValueOf4, geMSSParameters4);
        map.put(numValueOf5, geMSSParameters5);
        map.put(numValueOf6, geMSSParameters6);
        map.put(numValueOf7, geMSSParameters7);
        map.put(numValueOf8, geMSSParameters8);
        map.put(numValueOf9, geMSSParameters9);
        map.put(numValueOf10, geMSSParameters10);
        map.put(numValueOf11, geMSSParameters11);
        map.put(numValueOf12, geMSSParameters12);
        map.put(numValueOf13, geMSSParameters13);
        map.put(numValueOf14, geMSSParameters14);
        map.put(numValueOf15, geMSSParameters15);
        map.put(numValueOf16, geMSSParameters16);
        map.put(numValueOf17, geMSSParameters17);
        map.put(numValueOf18, geMSSParameters18);
        map.put(numValueOf19, geMSSParameters19);
        map.put(numValueOf20, geMSSParameters20);
        map.put(numValueOf21, geMSSParameters21);
        map.put(numValueOf22, geMSSParameters22);
        map.put(numValueOf23, geMSSParameters23);
        map.put(numValueOf24, geMSSParameters24);
        map2.put(geMSSParameters, numValueOf);
        map2.put(geMSSParameters2, numValueOf2);
        map2.put(geMSSParameters3, numValueOf3);
        map2.put(geMSSParameters4, numValueOf4);
        map2.put(geMSSParameters5, numValueOf5);
        map2.put(geMSSParameters6, numValueOf6);
        map2.put(geMSSParameters7, numValueOf7);
        map2.put(geMSSParameters8, numValueOf8);
        map2.put(geMSSParameters9, numValueOf9);
        map2.put(geMSSParameters10, numValueOf10);
        map2.put(geMSSParameters11, numValueOf11);
        map2.put(geMSSParameters12, numValueOf12);
        map2.put(geMSSParameters13, numValueOf13);
        map2.put(geMSSParameters14, numValueOf14);
        map2.put(geMSSParameters15, numValueOf15);
        map2.put(geMSSParameters16, numValueOf16);
        map2.put(geMSSParameters17, numValueOf17);
        map2.put(geMSSParameters18, numValueOf18);
        map2.put(geMSSParameters19, numValueOf19);
        map2.put(geMSSParameters20, numValueOf20);
        map2.put(geMSSParameters21, numValueOf21);
        map2.put(geMSSParameters22, numValueOf22);
        map2.put(geMSSParameters23, numValueOf23);
        map2.put(geMSSParameters24, numValueOf24);
    }

    private GeMSSParameters(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.name = str;
        this.engine = new GeMSSEngine(i, i2, i3, i4, i5, i6, i7, i8);
    }

    public static Integer getID(GeMSSParameters geMSSParameters) {
        return paramsToOid.get(geMSSParameters);
    }

    public static GeMSSParameters getParams(Integer num) {
        return oidToParams.get(num);
    }

    public byte[] getEncoded() {
        return Pack.intToBigEndian(getID(this).intValue());
    }

    public GeMSSEngine getEngine() {
        return this.engine;
    }

    public String getName() {
        return this.name;
    }
}
