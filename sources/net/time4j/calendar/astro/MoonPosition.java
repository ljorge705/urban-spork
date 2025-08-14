package net.time4j.calendar.astro;

import com.drew.metadata.exif.ExifDirectoryBase;
import com.drew.metadata.exif.makernotes.OlympusMakernoteDirectory;
import com.drew.metadata.iptc.IptcDirectory;
import com.drew.metadata.mp4.media.Mp4SoundDirectory;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.Moment;
import net.time4j.PlainDate;
import net.time4j.calendar.astro.Zodiac;
import net.time4j.engine.ChronoElement;
import net.time4j.scale.UniversalTime;
import net.time4j.tz.ZonalOffset;
import okhttp3.internal.http.StatusLine;
import org.jmrtd.PassportService;
import org.jmrtd.cbeff.ISO781611;

/* loaded from: classes4.dex */
public class MoonPosition implements EquatorialCoordinates, Serializable {
    private static final int MIO = 1000000;
    private static final long serialVersionUID = 5736859564589473324L;
    private final double azimuth;
    private final double declination;
    private final double distance;
    private final double elevation;
    private final double rightAscension;
    private static final int[] A_D = {0, 2, 2, 0, 0, 0, 2, 2, 2, 2, 0, 1, 0, 2, 0, 0, 4, 0, 4, 2, 2, 1, 1, 2, 2, 4, 2, 0, 2, 2, 1, 2, 0, 0, 2, 2, 2, 4, 0, 3, 2, 4, 0, 2, 2, 2, 4, 0, 4, 1, 2, 0, 1, 3, 4, 2, 0, 1, 2, 2};
    private static final int[] A_M = {0, 0, 0, 0, 1, 0, 0, -1, 0, -1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, -1, 0, 0, 0, 1, 0, -1, 0, -2, 1, 2, -2, 0, 0, -1, 0, 0, 1, -1, 2, 2, 1, -1, 0, 0, -1, 0, 1, 0, 1, 0, 0, -1, 2, 1, 0, 0};
    private static final int[] A_M2 = {1, -1, 0, 2, 0, 0, -2, -1, 1, 0, -1, 0, 1, 0, 1, 1, -1, 3, -2, -1, 0, -1, 0, 1, 2, 0, -3, -2, -1, -2, 1, 0, 2, 0, -1, 1, 0, -1, 2, -1, 1, -2, -1, -1, -2, 0, 1, 4, 0, -2, 0, 2, 1, -2, -3, 2, 1, -1, 3, -1};
    private static final int[] A_F = {0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, -2, 2, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, -2, 2, 0, 2, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0, -2, -2, 0, 0, 0, 0, 0, 0, 0, -2};
    private static final int[] COEFF_L = {6288774, 1274027, 658314, 213618, -185116, -114332, 58793, 57066, 53322, 45758, -40923, -34720, -30383, 15327, -12528, 10980, 10675, 10034, 8548, -7888, -6766, -5163, 4987, 4036, 3994, 3861, 3665, -2689, -2602, 2390, -2348, 2236, -2120, -2069, 2048, -1773, -1595, 1215, -1110, -892, -810, 759, -713, -700, 691, 596, 549, 537, 520, -487, -399, -381, 351, -340, ExifDirectoryBase.TAG_SUB_IFD_OFFSET, 327, -323, 299, 294, 0};
    private static final int[] COEFF_R = {-20905355, -3699111, -2955968, -569925, 48888, -3149, 246158, -152138, -170733, -204586, -129620, 108743, 104755, 10321, 0, 79661, -34782, -23210, -21636, 24208, 30824, -8379, -16675, -12831, -10445, -11650, 14403, -7003, 0, 10056, 6322, -9884, 5751, 0, -4950, OlympusMakernoteDirectory.TAG_INTERNAL_FLASH_AE2, 0, -3958, 0, 3258, 2616, -1897, -2117, 2354, 0, 0, -1423, -1117, -1571, -1739, 0, -4421, 0, 0, 0, 0, 1165, 0, 0, 8752};
    private static final int[] B_D = {0, 0, 0, 2, 2, 2, 2, 0, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0, 4, 0, 0, 0, 1, 0, 0, 0, 1, 0, 4, 4, 0, 4, 2, 2, 2, 2, 0, 2, 2, 2, 2, 4, 2, 2, 0, 2, 1, 1, 0, 2, 1, 2, 0, 4, 4, 1, 4, 1, 4, 2};
    private static final int[] B_M = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 1, -1, -1, -1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 1, 1, 0, -1, -2, 0, 1, 1, 1, 1, 1, 0, -1, 1, 0, -1, 0, 0, 0, -1, -2};
    private static final int[] B_M2 = {0, 1, 1, 0, -1, -1, 0, 2, 1, 2, 0, -2, 1, 0, -1, 0, -1, -1, -1, 0, 0, -1, 0, 1, 1, 0, 0, 3, 0, -1, 1, -2, 0, 2, 1, -2, 3, 2, -3, -1, 0, 0, 1, 0, 1, 1, 0, 0, -2, -1, 1, -2, 2, -2, -1, 1, 1, -1, 0, 0};
    private static final int[] B_F = {1, 1, -1, -1, 1, -1, 1, 1, -1, -1, -1, -1, 1, -1, 1, 1, -1, -1, -1, 1, 3, 1, 1, 1, -1, -1, -1, 1, -1, 1, -3, 1, -3, -1, -1, 1, -1, 1, -1, 1, 1, 1, 1, -1, 3, -1, -1, 1, -1, -1, 1, -1, 1, -1, -1, -1, -1, -1, -1, 1};
    private static final int[] COEFF_B = {5128122, 280602, 277693, 173237, 55413, 46271, 32573, 17198, 9266, 8822, 8216, 4324, 4200, -3359, 2463, 2211, 2065, -1870, 1828, -1794, -1749, -1565, -1491, -1475, -1410, -1344, -1335, 1107, 1021, 833, 777, 671, IptcDirectory.TAG_PROVINCE_OR_STATE, 596, 491, -451, 439, 422, StatusLine.HTTP_MISDIRECTED_REQUEST, -366, -351, 331, ExifDirectoryBase.TAG_ARTIST, Mp4SoundDirectory.TAG_NUMBER_OF_CHANNELS, -283, -229, PassportService.DEFAULT_MAX_BLOCKSIZE, PassportService.DEFAULT_MAX_BLOCKSIZE, -220, -220, -185, 181, -177, 176, 166, -164, 132, -119, ISO781611.DISCRETIONARY_DATA_FOR_PAYLOAD_CONSTRUCTED_TAG, 107};
    private static final int[] PERIGEE_D = {2, 4, 6, 8, 2, 0, 10, 4, 6, 12, 1, 8, 14, 0, 3, 10, 16, 12, 5, 2, 18, 14, 7, 2, 20, 1, 16, 4, 9, 4, 2, 4, 6, 22, 18, 6, 11, 8, 4, 6, 3, 5, 13, 20, 3, 4, 1, 22, 0, 6, 2, 0, 0, 2, 0, 2, 24, 4, 2, 1};
    private static final int[] PERIGEE_F = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, -2, 2, 0, 0, 0, 0, 0, 2, 0, 0, 4, -2, -2, 0, 2, 4, 2, -2, 0, -4, 0, 0};
    private static final int[] PERIGEE_M = {0, 0, 0, 0, -1, 1, 0, -1, -1, 0, 0, -1, 0, 0, 0, -1, 0, -1, 0, 0, 0, -1, 0, 1, 0, 1, -1, 1, 0, 0, -2, -2, -2, 0, -1, 1, 0, 1, 0, 0, 1, 1, 0, -1, 2, -2, 2, -1, 0, 0, 1, 2, -1, 0, -2, 2, 0, 0, 2, -1};
    private static final double[] PERIGEE_COEFF = {-1.6769d, 0.4589d, -0.1856d, 0.0883d, -0.0773d, 0.0502d, -0.046d, 0.0422d, -0.0256d, 0.0253d, 0.0237d, 0.0162d, -0.0145d, 0.0129d, -0.0112d, -0.0104d, 0.0086d, 0.0069d, 0.0066d, -0.0053d, -0.0052d, -0.0046d, -0.0041d, 0.004d, 0.0032d, -0.0032d, 0.0031d, -0.0029d, 0.0027d, 0.0027d, -0.0027d, 0.0024d, -0.0021d, -0.0021d, -0.0021d, 0.0019d, -0.0018d, -0.0014d, -0.0014d, -0.0014d, 0.0014d, -0.0014d, 0.0013d, 0.0013d, 0.0011d, -0.0011d, -0.001d, -9.0E-4d, -8.0E-4d, 8.0E-4d, 8.0E-4d, 7.0E-4d, 7.0E-4d, 7.0E-4d, -6.0E-4d, -6.0E-4d, 6.0E-4d, 5.0E-4d, 5.0E-4d, -4.0E-4d};
    private static final double[] PERIGEE_COEFF_T = {0.0d, 0.0d, 0.0d, 0.0d, 1.9E-4d, -1.3E-4d, 0.0d, -1.1E-4d};
    private static final int[] APOGEE_D = {2, 4, 0, 2, 0, 1, 6, 4, 2, 1, 8, 6, 2, 2, 3, 4, 8, 4, 10, 3, 0, 2, 2, 6, 6, 10, 5, 4, 0, 12, 2, 1};
    private static final int[] APOGEE_F = {0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, -2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, -2, 2, 0, 2, 0};
    private static final int[] APOGEE_M = {0, 0, 1, -1, 0, 0, 0, -1, 0, 1, 0, -1, 0, -2, 0, 0, -1, -2, 0, 1, 2, 1, 2, 0, -2, -1, 0, 0, 1, 0, -1, -1};
    private static final double[] APOGEE_COEFF = {0.4392d, 0.0684d, 0.0456d, 0.0426d, 0.0212d, -0.0189d, 0.0144d, 0.0113d, 0.0047d, 0.0036d, 0.0035d, 0.0034d, -0.0034d, 0.0022d, -0.0017d, 0.0013d, 0.0011d, 0.001d, 9.0E-4d, 7.0E-4d, 6.0E-4d, 5.0E-4d, 5.0E-4d, 4.0E-4d, 4.0E-4d, 4.0E-4d, -4.0E-4d, -4.0E-4d, 3.0E-4d, 3.0E-4d, 3.0E-4d, -3.0E-4d};
    private static final double[] APOGEE_COEFF_T = {0.0d, 0.0d, -1.1E-4d, -1.1E-4d};

    public double getAzimuth() {
        return this.azimuth;
    }

    @Override // net.time4j.calendar.astro.EquatorialCoordinates
    public double getDeclination() {
        return this.declination;
    }

    public double getDistance() {
        return this.distance;
    }

    public double getElevation() {
        return this.elevation;
    }

    @Override // net.time4j.calendar.astro.EquatorialCoordinates
    public double getRightAscension() {
        return this.rightAscension;
    }

    private MoonPosition(double d, double d2, double d3, double d4, double d5) {
        this.rightAscension = d;
        this.declination = d2;
        this.azimuth = d3;
        this.elevation = d4;
        this.distance = d5;
    }

    public static MoonPosition at(Moment moment, GeoLocation geoLocation) {
        double[] dArrCalculateMeeus47 = calculateMeeus47(JulianDay.ofEphemerisTime(moment).getCenturyJ2000());
        double radians = Math.toRadians(dArrCalculateMeeus47[2]);
        double radians2 = Math.toRadians(dArrCalculateMeeus47[3]);
        double d = dArrCalculateMeeus47[4];
        double radians3 = Math.toRadians(geoLocation.getLatitude());
        double radians4 = Math.toRadians(geoLocation.getLongitude());
        double dCos = Math.cos(radians3);
        double dSin = Math.sin(radians3);
        int altitude = geoLocation.getAltitude();
        double dGmst = ((AstroUtils.gmst(JulianDay.ofMeanSolarTime(moment).getMJD()) + Math.toRadians(dArrCalculateMeeus47[0] * Math.cos(Math.toRadians(dArrCalculateMeeus47[1])))) + radians4) - radians;
        double degrees = Math.toDegrees(Math.asin((Math.sin(radians2) * dSin) + (Math.cos(radians2) * dCos * Math.cos(dGmst))));
        if (degrees >= (-0.5d) - StdSolarCalculator.TIME4J.getGeodeticAngle(geoLocation.getLatitude(), altitude)) {
            double degrees2 = Math.toDegrees(Math.asin(6378.14d / d));
            degrees = (degrees - degrees2) + ((AstroUtils.refractionFactorOfStdAtmosphere(altitude) * AstroUtils.getRefraction(degrees)) / 60.0d);
        }
        return new MoonPosition(dArrCalculateMeeus47[2], dArrCalculateMeeus47[3], Math.toDegrees(Math.atan2(Math.sin(dGmst), (Math.cos(dGmst) * dSin) - (Math.tan(radians2) * dCos))) + 180.0d, degrees, d);
    }

    public static Zodiac.Event inConstellationOf(Zodiac zodiac) {
        return Zodiac.Event.ofConstellation('L', zodiac);
    }

    public static Zodiac.Event inSignOf(Zodiac zodiac) {
        return Zodiac.Event.ofSign('L', zodiac);
    }

    public static Moment inNextApogeeAfter(Moment moment) {
        return anomalistic(moment, true);
    }

    public static Moment inNextPerigeeAfter(Moment moment) {
        return anomalistic(moment, false);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MoonPosition)) {
            return false;
        }
        MoonPosition moonPosition = (MoonPosition) obj;
        return this.rightAscension == moonPosition.rightAscension && this.declination == moonPosition.declination && this.azimuth == moonPosition.azimuth && this.elevation == moonPosition.elevation && this.distance == moonPosition.distance;
    }

    public int hashCode() {
        return hashCode(this.rightAscension) + (hashCode(this.declination) * 31) + (hashCode(this.distance) * 37);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append("moon-position[ra=");
        sb.append(this.rightAscension);
        sb.append(",decl=");
        sb.append(this.declination);
        sb.append(",azimuth=");
        sb.append(this.azimuth);
        sb.append(",elevation=");
        sb.append(this.elevation);
        sb.append(",distance=");
        sb.append(this.distance);
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00b1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static double[] calculateMeeus47(double r33) {
        /*
            Method dump skipped, instructions count: 541
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: net.time4j.calendar.astro.MoonPosition.calculateMeeus47(double):double[]");
    }

    static double lunarLongitude(double d) {
        double d2;
        double d3 = (d - 2451545.0d) / 36525.0d;
        double meanLongitude = getMeanLongitude(d3);
        double meanElongation = getMeanElongation(d3);
        double meanAnomalyOfSun = getMeanAnomalyOfSun(d3);
        double meanAnomalyOfMoon = getMeanAnomalyOfMoon(d3);
        double meanDistanceOfMoon = getMeanDistanceOfMoon(d3);
        double d4 = 1.0d - (((7.4E-6d * d3) + 0.002516d) * d3);
        double d5 = d4 * d4;
        int length = A_D.length - 1;
        double dSin = 0.0d;
        while (length >= 0) {
            int i = A_M[length];
            double d6 = d4;
            if (i != -2) {
                if (i == -1 || i == 1) {
                    d2 = d6;
                } else if (i != 2) {
                    d2 = 1.0d;
                }
                dSin += COEFF_L[length] * d2 * Math.sin(Math.toRadians((A_D[length] * meanElongation) + (i * meanAnomalyOfSun) + (A_M2[length] * meanAnomalyOfMoon) + (A_F[length] * meanDistanceOfMoon)));
                length--;
                d4 = d6;
                meanElongation = meanElongation;
            }
            d2 = d5;
            dSin += COEFF_L[length] * d2 * Math.sin(Math.toRadians((A_D[length] * meanElongation) + (i * meanAnomalyOfSun) + (A_M2[length] * meanAnomalyOfMoon) + (A_F[length] * meanDistanceOfMoon)));
            length--;
            d4 = d6;
            meanElongation = meanElongation;
        }
        double dSin2 = dSin + (Math.sin(Math.toRadians((131.849d * d3) + 119.75d)) * 3958.0d) + (Math.sin(Math.toRadians(meanLongitude - meanDistanceOfMoon)) * 1962.0d) + (Math.sin(Math.toRadians((479264.29d * d3) + 53.09d)) * 318.0d);
        double[] dArr = new double[5];
        StdSolarCalculator.nutations(d3, dArr);
        return AstroUtils.toRange_0_360(meanLongitude + (dSin2 / 1000000.0d) + dArr[0]);
    }

    static double getMeanLongitude(double d) {
        return normalize(((((((((-1.5338834862103876E-8d) * d) + 1.855835023689734E-6d) * d) - 0.0015786d) * d) + 481267.88123421d) * d) + 218.3164477d);
    }

    static double getMeanElongation(double d) {
        return normalize(((((((1.8319447192361523E-6d - (8.844469995135542E-9d * d)) * d) - 0.0018819d) * d) + 445267.1114034d) * d) + 297.8501921d);
    }

    static double getMeanAnomalyOfSun(double d) {
        return normalize((((((4.083299305839118E-8d * d) - 1.536E-4d) * d) + 35999.0502909d) * d) + 357.5291092d);
    }

    static double getMeanAnomalyOfMoon(double d) {
        return normalize(((((((1.4347408140719379E-5d - (6.797172376291463E-8d * d)) * d) + 0.0087414d) * d) + 477198.8675055d) * d) + 134.9633964d);
    }

    static double getMeanDistanceOfMoon(double d) {
        return normalize((((((((1.1583324645839848E-9d * d) - 2.8360748723766307E-7d) * d) - 0.0036539d) * d) + 483202.0175233d) * d) + 93.272095d);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static Moment calculateMeeus50(int i, boolean z) {
        double d = i + (z ? -0.5d : 0.0d);
        double d2 = d / 1325.55d;
        double d3 = d2 * d2;
        double d4 = (27.55454989d * d) + 2451534.6698d + (((((5.2E-9d * d2) - 1.098E-6d) * d2) - 6.691E-4d) * d3);
        double dNormalize = normalize((335.9106046d * d) + 171.9179d + (((((5.5E-8d * d2) - 1.156E-5d) * d2) - 0.0100383d) * d3));
        double dNormalize2 = normalize((27.1577721d * d) + 347.3477d + (((-8.13E-4d) - (1.0E-6d * d2)) * d3));
        double dNormalize3 = normalize((d * 364.5287911d) + 316.6109d + (((-0.0125053d) - (1.48E-5d * d2)) * d3));
        int[] iArr = z ? APOGEE_D : PERIGEE_D;
        int[] iArr2 = z ? APOGEE_M : PERIGEE_M;
        int[] iArr3 = z ? APOGEE_F : PERIGEE_F;
        double[] dArr = z ? APOGEE_COEFF : PERIGEE_COEFF;
        double[] dArr2 = z ? APOGEE_COEFF_T : PERIGEE_COEFF_T;
        int length = iArr.length - 1;
        double dSin = 0.0d;
        while (length >= 0) {
            double d5 = d4;
            int[] iArr4 = iArr;
            int[] iArr5 = iArr2;
            double d6 = (iArr[length] * dNormalize) + (iArr2[length] * dNormalize2) + (iArr3[length] * dNormalize3);
            double d7 = dArr[length];
            double d8 = dNormalize3;
            if (length < dArr2.length) {
                d7 += dArr2[length] * d2;
            }
            dSin += d7 * Math.sin(Math.toRadians(d6));
            length--;
            iArr2 = iArr5;
            iArr = iArr4;
            d4 = d5;
            dNormalize3 = d8;
        }
        return (Moment) JulianDay.ofEphemerisTime(d4 + dSin).toMoment().with((ChronoElement<ChronoElement<TimeUnit>>) Moment.PRECISION, (ChronoElement<TimeUnit>) TimeUnit.MINUTES);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static Moment anomalistic(Moment moment, boolean z) {
        Moment moment2 = (Moment) moment.with((ChronoElement<ChronoElement<TimeUnit>>) Moment.PRECISION, (ChronoElement<TimeUnit>) TimeUnit.MINUTES);
        PlainDate date = moment2.toZonalTimestamp(ZonalOffset.UTC).toDate();
        int iFloor = (int) Math.floor(((date.getYear() + (date.getDayOfYear() / date.lengthOfYear())) - 1999.97d) * 13.2555d);
        Moment momentCalculateMeeus50 = calculateMeeus50(iFloor, z);
        while (!momentCalculateMeeus50.isAfter((UniversalTime) moment2)) {
            iFloor++;
            momentCalculateMeeus50 = calculateMeeus50(iFloor, z);
        }
        return momentCalculateMeeus50;
    }

    private static double normalize(double d) {
        return d - (Math.floor(d / 360.0d) * 360.0d);
    }

    private static int hashCode(double d) {
        long jDoubleToLongBits = Double.doubleToLongBits(d);
        return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
    }
}
