package net.time4j.tz;

import com.drew.metadata.exif.makernotes.NikonType2MakernoteDirectory;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.time.TimeZones;

/* loaded from: classes4.dex */
public final class ZonalOffset implements Comparable<ZonalOffset>, TZID, Serializable {
    private static final BigDecimal DECIMAL_240;
    private static final BigDecimal DECIMAL_3600;
    private static final BigDecimal DECIMAL_60;
    private static final BigDecimal DECIMAL_NEG_180;
    private static final BigDecimal DECIMAL_POS_180;
    private static final BigDecimal MRD;
    private static final ConcurrentMap<Integer, ZonalOffset> OFFSET_CACHE;
    public static final ZonalOffset UTC;
    private static final long serialVersionUID = -1410512619471503090L;
    private final transient int fraction;
    private final transient String name;
    private final transient int total;

    public int getFractionalAmount() {
        return this.fraction;
    }

    public int getIntegralAmount() {
        return this.total;
    }

    public String toString() {
        return this.name;
    }

    static {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        OFFSET_CACHE = concurrentHashMap;
        DECIMAL_60 = new BigDecimal(60);
        DECIMAL_3600 = new BigDecimal(NikonType2MakernoteDirectory.TAG_NIKON_SCAN);
        DECIMAL_NEG_180 = new BigDecimal(-180);
        DECIMAL_POS_180 = new BigDecimal(180);
        DECIMAL_240 = new BigDecimal(FaceDetectorAvcMLKit.FACE_DETECTION_FRAME_WIDTH);
        MRD = new BigDecimal(1000000000);
        ZonalOffset zonalOffset = new ZonalOffset(0, 0);
        UTC = zonalOffset;
        concurrentHashMap.put(0, zonalOffset);
    }

    private ZonalOffset(int i, int i2) {
        if (i2 != 0) {
            if (Math.abs(i2) > 999999999) {
                throw new IllegalArgumentException("Fraction out of range: " + i2);
            }
            if (i < -39600 || i > 39600) {
                throw new IllegalArgumentException("Total seconds out of range while fraction is non-zero: " + i);
            }
            if ((i < 0 && i2 > 0) || (i > 0 && i2 < 0)) {
                throw new IllegalArgumentException("Different signs: offset=" + i + ", fraction=" + i2);
            }
        } else if (i < -64800 || i > 64800) {
            throw new IllegalArgumentException("Total seconds out of range: " + i);
        }
        boolean z = i < 0 || i2 < 0;
        StringBuilder sb = new StringBuilder();
        sb.append(z ? '-' : '+');
        int iAbs = Math.abs(i);
        int i3 = iAbs / NikonType2MakernoteDirectory.TAG_NIKON_SCAN;
        int i4 = (iAbs / 60) % 60;
        int i5 = iAbs % 60;
        if (i3 < 10) {
            sb.append('0');
        }
        sb.append(i3);
        sb.append(AbstractJsonLexerKt.COLON);
        if (i4 < 10) {
            sb.append('0');
        }
        sb.append(i4);
        if (i5 != 0 || i2 != 0) {
            sb.append(AbstractJsonLexerKt.COLON);
            if (i5 < 10) {
                sb.append('0');
            }
            sb.append(i5);
            if (i2 != 0) {
                sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
                String strValueOf = String.valueOf(Math.abs(i2));
                int length = 9 - strValueOf.length();
                for (int i6 = 0; i6 < length; i6++) {
                    sb.append('0');
                }
                sb.append(strValueOf);
            }
        }
        this.name = sb.toString();
        this.total = i;
        this.fraction = i2;
    }

    public static ZonalOffset atLongitude(BigDecimal bigDecimal) {
        if (bigDecimal.compareTo(DECIMAL_POS_180) > 0 || bigDecimal.compareTo(DECIMAL_NEG_180) < 0) {
            throw new IllegalArgumentException("Out of range: " + bigDecimal);
        }
        BigDecimal bigDecimalMultiply = bigDecimal.multiply(DECIMAL_240);
        BigDecimal scale = bigDecimalMultiply.setScale(0, RoundingMode.DOWN);
        BigDecimal bigDecimalMultiply2 = bigDecimalMultiply.subtract(scale).setScale(9, RoundingMode.HALF_UP).multiply(MRD);
        int iIntValueExact = scale.intValueExact();
        int iIntValueExact2 = bigDecimalMultiply2.intValueExact();
        if (iIntValueExact2 == 0) {
            return ofTotalSeconds(iIntValueExact);
        }
        if (iIntValueExact2 == 1000000000) {
            return ofTotalSeconds(iIntValueExact + 1);
        }
        if (iIntValueExact2 == -1000000000) {
            return ofTotalSeconds(iIntValueExact - 1);
        }
        return new ZonalOffset(iIntValueExact, iIntValueExact2);
    }

    public static ZonalOffset atLongitude(OffsetSign offsetSign, int i, int i2, double d) {
        if (offsetSign == null) {
            throw new NullPointerException("Missing sign.");
        }
        if (i < 0 || i > 180) {
            throw new IllegalArgumentException("Degrees of longitude out of range (0 <= degrees <= 180).");
        }
        if (i2 < 0 || i2 > 59) {
            throw new IllegalArgumentException("Arc minute out of range (0 <= arcMinutes <= 59).");
        }
        if (Double.compare(d, 0.0d) < 0 || Double.compare(d, 60.0d) >= 0) {
            throw new IllegalArgumentException("Arc second out of range (0.0 <= arcSeconds < 60.0).");
        }
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(i);
        if (i2 != 0) {
            bigDecimalValueOf = bigDecimalValueOf.add(BigDecimal.valueOf(i2).setScale(15, RoundingMode.UNNECESSARY).divide(DECIMAL_60, RoundingMode.HALF_UP));
        }
        if (d != 0.0d) {
            bigDecimalValueOf = bigDecimalValueOf.add(BigDecimal.valueOf(d).setScale(15, RoundingMode.FLOOR).divide(DECIMAL_3600, RoundingMode.HALF_UP));
        }
        if (offsetSign == OffsetSign.BEHIND_UTC) {
            bigDecimalValueOf = bigDecimalValueOf.negate();
        }
        return atLongitude(bigDecimalValueOf);
    }

    public static ZonalOffset ofHours(OffsetSign offsetSign, int i) {
        return ofHoursMinutes(offsetSign, i, 0);
    }

    public static ZonalOffset ofHoursMinutes(OffsetSign offsetSign, int i, int i2) {
        if (offsetSign == null) {
            throw new NullPointerException("Missing sign.");
        }
        if (i < 0 || i > 18) {
            throw new IllegalArgumentException("Hour part out of range (0 <= hours <= 18) in: " + format(i, i2));
        }
        if (i2 < 0 || i2 > 59) {
            throw new IllegalArgumentException("Minute part out of range (0 <= minutes <= 59) in: " + format(i, i2));
        }
        if (i == 18 && i2 != 0) {
            throw new IllegalArgumentException("Time zone offset out of range (-18:00:00 <= offset <= 18:00:00) in: " + format(i, i2));
        }
        int i3 = (i * NikonType2MakernoteDirectory.TAG_NIKON_SCAN) + (i2 * 60);
        if (offsetSign == OffsetSign.BEHIND_UTC) {
            i3 = -i3;
        }
        return ofTotalSeconds(i3);
    }

    public static ZonalOffset ofTotalSeconds(int i) {
        return ofTotalSeconds(i, 0);
    }

    public static ZonalOffset ofTotalSeconds(int i, int i2) {
        if (i2 != 0) {
            return new ZonalOffset(i, i2);
        }
        if (i == 0) {
            return UTC;
        }
        if (i % SQLitePersistence.MAX_ARGS == 0) {
            Integer numValueOf = Integer.valueOf(i);
            ConcurrentMap<Integer, ZonalOffset> concurrentMap = OFFSET_CACHE;
            ZonalOffset zonalOffset = concurrentMap.get(numValueOf);
            if (zonalOffset != null) {
                return zonalOffset;
            }
            concurrentMap.putIfAbsent(numValueOf, new ZonalOffset(i, 0));
            return concurrentMap.get(numValueOf);
        }
        return new ZonalOffset(i, 0);
    }

    public OffsetSign getSign() {
        return (this.total < 0 || this.fraction < 0) ? OffsetSign.BEHIND_UTC : OffsetSign.AHEAD_OF_UTC;
    }

    public int getAbsoluteHours() {
        return Math.abs(this.total) / NikonType2MakernoteDirectory.TAG_NIKON_SCAN;
    }

    public int getAbsoluteMinutes() {
        return (Math.abs(this.total) / 60) % 60;
    }

    public int getAbsoluteSeconds() {
        return Math.abs(this.total) % 60;
    }

    @Override // java.lang.Comparable
    public int compareTo(ZonalOffset zonalOffset) {
        int i = this.total;
        int i2 = zonalOffset.total;
        if (i < i2) {
            return -1;
        }
        if (i > i2) {
            return 1;
        }
        int i3 = this.fraction - zonalOffset.fraction;
        if (i3 < 0) {
            return -1;
        }
        return i3 == 0 ? 0 : 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ZonalOffset)) {
            return false;
        }
        ZonalOffset zonalOffset = (ZonalOffset) obj;
        return this.total == zonalOffset.total && this.fraction == zonalOffset.fraction;
    }

    public int hashCode() {
        return (~this.total) + (this.fraction % 64000);
    }

    @Override // net.time4j.tz.TZID
    public String canonical() {
        return (this.total == 0 && this.fraction == 0) ? "Z" : "UTC" + this.name;
    }

    public static ZonalOffset parse(String str) {
        return parse(str, true);
    }

    public String getStdFormatPattern(Locale locale) {
        boolean z = this.total == 0 && this.fraction == 0;
        try {
            return Timezone.NAME_PROVIDER.getStdFormatPattern(z, locale);
        } catch (Throwable unused) {
            return z ? TimeZones.GMT_ID : "GMT±hh:mm";
        }
    }

    static ZonalOffset parse(String str, boolean z) {
        String strSubstring;
        OffsetSign offsetSign;
        int i;
        int i2;
        if (str.equals("Z")) {
            return UTC;
        }
        int length = str.length();
        if (length < 3) {
            strSubstring = str;
        } else if (str.startsWith("UTC")) {
            strSubstring = str.substring(3);
            length -= 3;
        } else {
            if (str.startsWith(TimeZones.GMT_ID)) {
                if (z) {
                    throw new IllegalArgumentException("Use UTC-prefix for canonical offset instead: " + str);
                }
                return null;
            }
            strSubstring = str;
        }
        if (length >= 2) {
            if (strSubstring.charAt(0) == '-') {
                offsetSign = OffsetSign.BEHIND_UTC;
            } else {
                offsetSign = strSubstring.charAt(0) == '+' ? OffsetSign.AHEAD_OF_UTC : null;
            }
            int i3 = parse(strSubstring, 1, 2);
            if (i3 >= 0) {
                if (length <= 3) {
                    return ofHours(offsetSign, i3);
                }
                int i4 = strSubstring.charAt(2) != ':' ? 4 : 3;
                int i5 = parse(strSubstring, i4, 2);
                if (strSubstring.charAt(i4 - 1) == ':' && i5 >= 0) {
                    int i6 = i4 + 2;
                    if (length == i6) {
                        return ofHoursMinutes(offsetSign, i3, i5);
                    }
                    int i7 = i4 + 5;
                    if (length >= i7 && strSubstring.charAt(i6) == ':' && (i = parse(strSubstring, i4 + 3, 2)) >= 0) {
                        int i8 = (i3 * NikonType2MakernoteDirectory.TAG_NIKON_SCAN) + (i5 * 60) + i;
                        if (offsetSign == OffsetSign.BEHIND_UTC) {
                            i8 = -i8;
                        }
                        if (length == i7) {
                            return ofTotalSeconds(i8);
                        }
                        if (length == i4 + 15 && strSubstring.charAt(i7) == '.' && (i2 = parse(strSubstring, i4 + 6, 9)) >= 0) {
                            if (offsetSign == OffsetSign.BEHIND_UTC) {
                                i2 = -i2;
                            }
                            return ofTotalSeconds(i8, i2);
                        }
                    }
                }
            }
        }
        if (z) {
            throw new IllegalArgumentException("No canonical zonal offset: " + str);
        }
        return null;
    }

    SingleOffsetTimezone getModel() {
        return SingleOffsetTimezone.of(this);
    }

    private static int parse(String str, int i, int i2) {
        int iMin = Math.min(str.length() - i, i2);
        int i3 = -1;
        for (int i4 = 0; i4 < iMin; i4++) {
            char cCharAt = str.charAt(i + i4);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            i3 = i3 == -1 ? cCharAt - '0' : (i3 * 10) + (cCharAt - '0');
        }
        return i3;
    }

    private static String format(int i, int i2) {
        return "[hours=" + i + ",minutes=" + i2 + AbstractJsonLexerKt.END_LIST;
    }

    private Object writeReplace() {
        return new SPX(this, 15);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Serialization proxy required.");
    }
}
