package com.onfido.android.sdk.capture.utils;

import com.clevertap.android.sdk.inapp.store.preference.InAppStore;
import com.drew.metadata.exif.makernotes.PanasonicMakernoteDirectory;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;
import com.facebook.imageutils.JfifUtil;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKit;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import okhttp3.internal.ws.WebSocketProtocol;
import org.jmrtd.PassportService;
import org.jmrtd.cbeff.ISO781611;
import org.jmrtd.lds.LDSFile;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.jce.provider.BouncyCastleProvider;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: CountryCode.kt */
@Metadata(d1 = {"\u0000\u0013\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0003\b\u008a\u0002\b\u0086\u0081\u0002\u0018\u0000 \u008c\u00022\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\u008c\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R&\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\fR&\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000e\u0010\t\u001a\u0004\b\u000f\u0010\u0006\"\u0004\b\u0010\u0010\fj\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>j\u0002\b?j\u0002\b@j\u0002\bAj\u0002\bBj\u0002\bCj\u0002\bDj\u0002\bEj\u0002\bFj\u0002\bGj\u0002\bHj\u0002\bIj\u0002\bJj\u0002\bKj\u0002\bLj\u0002\bMj\u0002\bNj\u0002\bOj\u0002\bPj\u0002\bQj\u0002\bRj\u0002\bSj\u0002\bTj\u0002\bUj\u0002\bVj\u0002\bWj\u0002\bXj\u0002\bYj\u0002\bZj\u0002\b[j\u0002\b\\j\u0002\b]j\u0002\b^j\u0002\b_j\u0002\b`j\u0002\baj\u0002\bbj\u0002\bcj\u0002\bdj\u0002\bej\u0002\bfj\u0002\bgj\u0002\bhj\u0002\bij\u0002\bjj\u0002\bkj\u0002\blj\u0002\bmj\u0002\bnj\u0002\boj\u0002\bpj\u0002\bqj\u0002\brj\u0002\bsj\u0002\btj\u0002\buj\u0002\bvj\u0002\bwj\u0002\bxj\u0002\byj\u0002\bzj\u0002\b{j\u0002\b|j\u0002\b}j\u0002\b~j\u0002\b\u007fj\u0003\b\u0080\u0001j\u0003\b\u0081\u0001j\u0003\b\u0082\u0001j\u0003\b\u0083\u0001j\u0003\b\u0084\u0001j\u0003\b\u0085\u0001j\u0003\b\u0086\u0001j\u0003\b\u0087\u0001j\u0003\b\u0088\u0001j\u0003\b\u0089\u0001j\u0003\b\u008a\u0001j\u0003\b\u008b\u0001j\u0003\b\u008c\u0001j\u0003\b\u008d\u0001j\u0003\b\u008e\u0001j\u0003\b\u008f\u0001j\u0003\b\u0090\u0001j\u0003\b\u0091\u0001j\u0003\b\u0092\u0001j\u0003\b\u0093\u0001j\u0003\b\u0094\u0001j\u0003\b\u0095\u0001j\u0003\b\u0096\u0001j\u0003\b\u0097\u0001j\u0003\b\u0098\u0001j\u0003\b\u0099\u0001j\u0003\b\u009a\u0001j\u0003\b\u009b\u0001j\u0003\b\u009c\u0001j\u0003\b\u009d\u0001j\u0003\b\u009e\u0001j\u0003\b\u009f\u0001j\u0003\b \u0001j\u0003\b¡\u0001j\u0003\b¢\u0001j\u0003\b£\u0001j\u0003\b¤\u0001j\u0003\b¥\u0001j\u0003\b¦\u0001j\u0003\b§\u0001j\u0003\b¨\u0001j\u0003\b©\u0001j\u0003\bª\u0001j\u0003\b«\u0001j\u0003\b¬\u0001j\u0003\b\u00ad\u0001j\u0003\b®\u0001j\u0003\b¯\u0001j\u0003\b°\u0001j\u0003\b±\u0001j\u0003\b²\u0001j\u0003\b³\u0001j\u0003\b´\u0001j\u0003\bµ\u0001j\u0003\b¶\u0001j\u0003\b·\u0001j\u0003\b¸\u0001j\u0003\b¹\u0001j\u0003\bº\u0001j\u0003\b»\u0001j\u0003\b¼\u0001j\u0003\b½\u0001j\u0003\b¾\u0001j\u0003\b¿\u0001j\u0003\bÀ\u0001j\u0003\bÁ\u0001j\u0003\bÂ\u0001j\u0003\bÃ\u0001j\u0003\bÄ\u0001j\u0003\bÅ\u0001j\u0003\bÆ\u0001j\u0003\bÇ\u0001j\u0003\bÈ\u0001j\u0003\bÉ\u0001j\u0003\bÊ\u0001j\u0003\bË\u0001j\u0003\bÌ\u0001j\u0003\bÍ\u0001j\u0003\bÎ\u0001j\u0003\bÏ\u0001j\u0003\bÐ\u0001j\u0003\bÑ\u0001j\u0003\bÒ\u0001j\u0003\bÓ\u0001j\u0003\bÔ\u0001j\u0003\bÕ\u0001j\u0003\bÖ\u0001j\u0003\b×\u0001j\u0003\bØ\u0001j\u0003\bÙ\u0001j\u0003\bÚ\u0001j\u0003\bÛ\u0001j\u0003\bÜ\u0001j\u0003\bÝ\u0001j\u0003\bÞ\u0001j\u0003\bß\u0001j\u0003\bà\u0001j\u0003\bá\u0001j\u0003\bâ\u0001j\u0003\bã\u0001j\u0003\bä\u0001j\u0003\bå\u0001j\u0003\bæ\u0001j\u0003\bç\u0001j\u0003\bè\u0001j\u0003\bé\u0001j\u0003\bê\u0001j\u0003\bë\u0001j\u0003\bì\u0001j\u0003\bí\u0001j\u0003\bî\u0001j\u0003\bï\u0001j\u0003\bð\u0001j\u0003\bñ\u0001j\u0003\bò\u0001j\u0003\bó\u0001j\u0003\bô\u0001j\u0003\bõ\u0001j\u0003\bö\u0001j\u0003\b÷\u0001j\u0003\bø\u0001j\u0003\bù\u0001j\u0003\bú\u0001j\u0003\bû\u0001j\u0003\bü\u0001j\u0003\bý\u0001j\u0003\bþ\u0001j\u0003\bÿ\u0001j\u0003\b\u0080\u0002j\u0003\b\u0081\u0002j\u0003\b\u0082\u0002j\u0003\b\u0083\u0002j\u0003\b\u0084\u0002j\u0003\b\u0085\u0002j\u0003\b\u0086\u0002j\u0003\b\u0087\u0002j\u0003\b\u0088\u0002j\u0003\b\u0089\u0002j\u0003\b\u008a\u0002j\u0003\b\u008b\u0002¨\u0006\u008d\u0002"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/CountryCode;", "", "alpha3", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getAlpha3", "()Ljava/lang/String;", "localeName", "getLocaleName$annotations", "()V", "getLocaleName", "setLocaleName", "(Ljava/lang/String;)V", "nativeName", "getNativeName$annotations", "getNativeName", "setNativeName", "AD", "AE", "AF", "AG", "AI", "AL", "AM", "AN", "AO", "AQ", "AR", "AS", "AT", "AU", "AW", "AX", "AZ", "BA", "BB", "BD", "BE", "BF", "BG", "BH", "BI", "BJ", "BL", "BM", "BN", "BO", "BQ", "BR", "BS", "BT", "BV", "BW", "BY", "BZ", "CA", "CC", "CD", "CF", "CG", "CH", "CI", "CK", "CL", "CM", "CN", "CO", "CR", "CU", "CV", "CW", "CX", "CY", "CZ", "DE", "DJ", "DK", "DM", "DO", "DZ", "EC", "EE", "EG", "EH", "ER", "ES", "ET", "FI", "FJ", "FK", "FM", "FO", "FR", "GA", "GB", "GD", "GE", "GF", "GG", "GH", "GI", "GL", "GM", "GN", "GP", "GQ", "GR", "GS", "GT", "GU", "GW", "GY", "HK", "HM", "HN", "HR", "HT", "HU", "ID", "IE", "IL", "IM", "IN", "IO", "IQ", "IR", "IS", "IT", "JE", "JM", "JO", "JP", "KE", "KG", "KH", "KI", "KM", "KN", "KP", "KR", "KW", "KY", "KZ", "LA", "LB", "LC", "LI", "LK", "LR", "LS", "LT", "LU", "LV", "LY", "MA", "MC", "MD", "ME", "MF", "MG", "MH", "MK", "ML", "MM", "MN", "MO", "MP", "MQ", "MR", "MS", "MT", "MU", "MV", "MW", "MX", "MY", "MZ", "NA", "NC", "NE", "NF", "NG", "NI", "NL", "NO", "NP", "NR", "NU", "NZ", "OM", "PA", "PE", "PF", "PG", "PH", "PK", "PL", "PM", "PN", "PR", "PS", "PT", "PW", "PY", "QA", "RE", "RO", "RS", "RU", "RW", "SA", "SB", BouncyCastleProvider.PROVIDER_NAME, "SD", "SE", "SG", "SH", "SI", "SJ", "SK", "SL", "SM", "SN", "SO", "SR", InAppStore.SERVER_SIDE_MODE, "ST", "SV", "SX", "SY", "SZ", "TC", "TD", "TF", "TG", "TH", "TJ", "TK", "TL", "TM", "TN", "TO", "TR", "TT", "TV", "TW", "TZ", "UA", "UG", "UM", "US", "UY", "UZ", "VA", "VC", "VE", "VG", "VI", "VN", "VU", "WF", "WS", "XK", "YE", "YT", "ZA", "ZM", "ZW", "Companion", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CountryCode {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ CountryCode[] $VALUES;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final Map<String, CountryCode> map;
    private final String alpha3;
    private String localeName;
    private String nativeName;
    public static final CountryCode AD = new CountryCode("AD", 0, "AND");
    public static final CountryCode AE = new CountryCode("AE", 1, "ARE");
    public static final CountryCode AF = new CountryCode("AF", 2, "AFG");
    public static final CountryCode AG = new CountryCode("AG", 3, "ATG");
    public static final CountryCode AI = new CountryCode("AI", 4, "AIA");
    public static final CountryCode AL = new CountryCode("AL", 5, "ALB");
    public static final CountryCode AM = new CountryCode("AM", 6, "ARM");
    public static final CountryCode AN = new CountryCode("AN", 7, "ANT");
    public static final CountryCode AO = new CountryCode("AO", 8, "AGO");
    public static final CountryCode AQ = new CountryCode("AQ", 9, "ATA");
    public static final CountryCode AR = new CountryCode("AR", 10, "ARG");
    public static final CountryCode AS = new CountryCode("AS", 11, "ASM");
    public static final CountryCode AT = new CountryCode("AT", 12, "AUT");
    public static final CountryCode AU = new CountryCode("AU", 13, "AUS");
    public static final CountryCode AW = new CountryCode("AW", 14, "ABW");
    public static final CountryCode AX = new CountryCode("AX", 15, "ALA");
    public static final CountryCode AZ = new CountryCode("AZ", 16, "AZE");
    public static final CountryCode BA = new CountryCode("BA", 17, "BIH");
    public static final CountryCode BB = new CountryCode("BB", 18, "BRB");
    public static final CountryCode BD = new CountryCode("BD", 19, "BGD");
    public static final CountryCode BE = new CountryCode("BE", 20, "BEL");
    public static final CountryCode BF = new CountryCode("BF", 21, "BFA");
    public static final CountryCode BG = new CountryCode("BG", 22, "BGR");
    public static final CountryCode BH = new CountryCode("BH", 23, "BHR");
    public static final CountryCode BI = new CountryCode("BI", 24, "BDI");
    public static final CountryCode BJ = new CountryCode("BJ", 25, "BEN");
    public static final CountryCode BL = new CountryCode("BL", 26, "BLM");
    public static final CountryCode BM = new CountryCode("BM", 27, "BMU");
    public static final CountryCode BN = new CountryCode("BN", 28, "BRN");
    public static final CountryCode BO = new CountryCode("BO", 29, "BOL");
    public static final CountryCode BQ = new CountryCode("BQ", 30, "BES");
    public static final CountryCode BR = new CountryCode("BR", 31, "BRA");
    public static final CountryCode BS = new CountryCode("BS", 32, "BHS");
    public static final CountryCode BT = new CountryCode("BT", 33, "BTN");
    public static final CountryCode BV = new CountryCode("BV", 34, "BVT");
    public static final CountryCode BW = new CountryCode("BW", 35, "BWA");
    public static final CountryCode BY = new CountryCode("BY", 36, "BLR");
    public static final CountryCode BZ = new CountryCode("BZ", 37, "BLZ");
    public static final CountryCode CA = new CountryCode("CA", 38, "CAN");
    public static final CountryCode CC = new CountryCode("CC", 39, "CCK");
    public static final CountryCode CD = new CountryCode("CD", 40, "COD");
    public static final CountryCode CF = new CountryCode("CF", 41, "CAF");
    public static final CountryCode CG = new CountryCode("CG", 42, "COG");
    public static final CountryCode CH = new CountryCode("CH", 43, "CHE");
    public static final CountryCode CI = new CountryCode("CI", 44, "CIV");
    public static final CountryCode CK = new CountryCode("CK", 45, "COK");
    public static final CountryCode CL = new CountryCode("CL", 46, "CHL");
    public static final CountryCode CM = new CountryCode("CM", 47, "CMR");
    public static final CountryCode CN = new CountryCode("CN", 48, "CHN");
    public static final CountryCode CO = new CountryCode("CO", 49, "COL");
    public static final CountryCode CR = new CountryCode("CR", 50, "CRI");
    public static final CountryCode CU = new CountryCode("CU", 51, "CUB");
    public static final CountryCode CV = new CountryCode("CV", 52, "CPV");
    public static final CountryCode CW = new CountryCode("CW", 53, "CUW");
    public static final CountryCode CX = new CountryCode("CX", 54, "CXR");
    public static final CountryCode CY = new CountryCode("CY", 55, "CYP");
    public static final CountryCode CZ = new CountryCode("CZ", 56, "CZE");
    public static final CountryCode DE = new CountryCode("DE", 57, "DEU");
    public static final CountryCode DJ = new CountryCode("DJ", 58, "DJI");
    public static final CountryCode DK = new CountryCode("DK", 59, "DNK");
    public static final CountryCode DM = new CountryCode("DM", 60, "DMA");
    public static final CountryCode DO = new CountryCode("DO", 61, "DOM");
    public static final CountryCode DZ = new CountryCode("DZ", 62, "DZA");
    public static final CountryCode EC = new CountryCode("EC", 63, "ECU");
    public static final CountryCode EE = new CountryCode("EE", 64, "EST");
    public static final CountryCode EG = new CountryCode("EG", 65, "EGY");
    public static final CountryCode EH = new CountryCode("EH", 66, "ESH");
    public static final CountryCode ER = new CountryCode("ER", 67, "ERI");
    public static final CountryCode ES = new CountryCode("ES", 68, "ESP");
    public static final CountryCode ET = new CountryCode("ET", 69, "ETH");
    public static final CountryCode FI = new CountryCode("FI", 70, "FIN");
    public static final CountryCode FJ = new CountryCode("FJ", 71, "FJI");
    public static final CountryCode FK = new CountryCode("FK", 72, "FLK");
    public static final CountryCode FM = new CountryCode("FM", 73, "FSM");
    public static final CountryCode FO = new CountryCode("FO", 74, "FRO");
    public static final CountryCode FR = new CountryCode("FR", 75, "FRA");
    public static final CountryCode GA = new CountryCode("GA", 76, "GAB");
    public static final CountryCode GB = new CountryCode("GB", 77, "GBR");
    public static final CountryCode GD = new CountryCode("GD", 78, "GRD");
    public static final CountryCode GE = new CountryCode("GE", 79, "GEO");
    public static final CountryCode GF = new CountryCode("GF", 80, "GUF");
    public static final CountryCode GG = new CountryCode("GG", 81, "GGY");
    public static final CountryCode GH = new CountryCode("GH", 82, "GHA");
    public static final CountryCode GI = new CountryCode("GI", 83, "GIB");
    public static final CountryCode GL = new CountryCode("GL", 84, "GRL");
    public static final CountryCode GM = new CountryCode("GM", 85, "GMB");
    public static final CountryCode GN = new CountryCode("GN", 86, "GIN");
    public static final CountryCode GP = new CountryCode("GP", 87, "GLP");
    public static final CountryCode GQ = new CountryCode("GQ", 88, "GNQ");
    public static final CountryCode GR = new CountryCode("GR", 89, "GRC");
    public static final CountryCode GS = new CountryCode("GS", 90, "SGS");
    public static final CountryCode GT = new CountryCode("GT", 91, "GTM");
    public static final CountryCode GU = new CountryCode("GU", 92, "GUM");
    public static final CountryCode GW = new CountryCode("GW", 93, "GNB");
    public static final CountryCode GY = new CountryCode("GY", 94, "GUY");
    public static final CountryCode HK = new CountryCode("HK", 95, "HKG");
    public static final CountryCode HM = new CountryCode("HM", 96, "HMD");
    public static final CountryCode HN = new CountryCode("HN", 97, "HND");
    public static final CountryCode HR = new CountryCode("HR", 98, "HRV");
    public static final CountryCode HT = new CountryCode("HT", 99, "HTI");
    public static final CountryCode HU = new CountryCode("HU", 100, "HUN");
    public static final CountryCode ID = new CountryCode("ID", 101, "IDN");
    public static final CountryCode IE = new CountryCode("IE", 102, "IRL");
    public static final CountryCode IL = new CountryCode("IL", 103, "ISR");
    public static final CountryCode IM = new CountryCode("IM", 104, "IMN");
    public static final CountryCode IN = new CountryCode("IN", 105, "IND");
    public static final CountryCode IO = new CountryCode("IO", 106, "IOT");
    public static final CountryCode IQ = new CountryCode("IQ", 107, "IRQ");
    public static final CountryCode IR = new CountryCode("IR", 108, "IRN");
    public static final CountryCode IS = new CountryCode("IS", 109, "ISL");
    public static final CountryCode IT = new CountryCode("IT", LDSFile.EF_DG14_TAG, "ITA");
    public static final CountryCode JE = new CountryCode("JE", 111, "JEY");
    public static final CountryCode JM = new CountryCode("JM", 112, "JAM");
    public static final CountryCode JO = new CountryCode("JO", 113, "JOR");
    public static final CountryCode JP = new CountryCode("JP", 114, "JPN");
    public static final CountryCode KE = new CountryCode("KE", ISO781611.DISCRETIONARY_DATA_FOR_PAYLOAD_CONSTRUCTED_TAG, "KEN");
    public static final CountryCode KG = new CountryCode("KG", 116, "KGZ");
    public static final CountryCode KH = new CountryCode("KH", LDSFile.EF_DG2_TAG, "KHM");
    public static final CountryCode KI = new CountryCode("KI", LDSFile.EF_DG4_TAG, "KIR");
    public static final CountryCode KM = new CountryCode("KM", 119, "COM");
    public static final CountryCode KN = new CountryCode("KN", 120, "KNA");
    public static final CountryCode KP = new CountryCode("KP", PanasonicMakernoteDirectory.TAG_INTELLIGENT_D_RANGE, "PRK");
    public static final CountryCode KR = new CountryCode("KR", 122, "KOR");
    public static final CountryCode KW = new CountryCode("KW", 123, "KWT");
    public static final CountryCode KY = new CountryCode("KY", PanasonicMakernoteDirectory.TAG_CLEAR_RETOUCH, "CYM");
    public static final CountryCode KZ = new CountryCode("KZ", ISO781611.SMT_TAG, "KAZ");
    public static final CountryCode LA = new CountryCode("LA", WebSocketProtocol.PAYLOAD_SHORT, "LAO");
    public static final CountryCode LB = new CountryCode("LB", 127, "LBN");
    public static final CountryCode LC = new CountryCode("LC", 128, "LCA");
    public static final CountryCode LI = new CountryCode("LI", 129, "LIE");
    public static final CountryCode LK = new CountryCode("LK", 130, "LKA");
    public static final CountryCode LR = new CountryCode("LR", 131, "LBR");
    public static final CountryCode LS = new CountryCode("LS", 132, "LSO");
    public static final CountryCode LT = new CountryCode("LT", 133, "LTU");
    public static final CountryCode LU = new CountryCode("LU", 134, "LUX");
    public static final CountryCode LV = new CountryCode("LV", 135, "LVA");
    public static final CountryCode LY = new CountryCode("LY", 136, "LBY");
    public static final CountryCode MA = new CountryCode("MA", 137, "MAR");
    public static final CountryCode MC = new CountryCode("MC", 138, "MCO");
    public static final CountryCode MD = new CountryCode("MD", 139, "MDA");
    public static final CountryCode ME = new CountryCode("ME", 140, "MNE");
    public static final CountryCode MF = new CountryCode("MF", 141, "MAF");
    public static final CountryCode MG = new CountryCode("MG", 142, "MDG");
    public static final CountryCode MH = new CountryCode("MH", 143, "MHL");
    public static final CountryCode MK = new CountryCode("MK", 144, "MKD");
    public static final CountryCode ML = new CountryCode("ML", 145, "MLI");
    public static final CountryCode MM = new CountryCode("MM", 146, "MMR");
    public static final CountryCode MN = new CountryCode("MN", 147, "MNG");
    public static final CountryCode MO = new CountryCode("MO", 148, "MAC");
    public static final CountryCode MP = new CountryCode("MP", 149, "MNP");
    public static final CountryCode MQ = new CountryCode("MQ", 150, "MTQ");
    public static final CountryCode MR = new CountryCode("MR", 151, "MRT");
    public static final CountryCode MS = new CountryCode("MS", 152, "MSR");
    public static final CountryCode MT = new CountryCode("MT", 153, "MLT");
    public static final CountryCode MU = new CountryCode("MU", 154, "MUS");
    public static final CountryCode MV = new CountryCode("MV", 155, "MDV");
    public static final CountryCode MW = new CountryCode("MW", 156, "MWI");
    public static final CountryCode MX = new CountryCode("MX", 157, "MEX");
    public static final CountryCode MY = new CountryCode("MY", 158, "MYS");
    public static final CountryCode MZ = new CountryCode("MZ", 159, "MOZ");
    public static final CountryCode NA = new CountryCode("NA", 160, "NAM");
    public static final CountryCode NC = new CountryCode("NC", 161, "NCL");
    public static final CountryCode NE = new CountryCode("NE", 162, "NER");
    public static final CountryCode NF = new CountryCode("NF", 163, "NFK");
    public static final CountryCode NG = new CountryCode("NG", 164, "NGA");
    public static final CountryCode NI = new CountryCode("NI", 165, "NIC");
    public static final CountryCode NL = new CountryCode("NL", 166, "NLD");
    public static final CountryCode NO = new CountryCode("NO", 167, "NOR");
    public static final CountryCode NP = new CountryCode("NP", 168, "NPL");
    public static final CountryCode NR = new CountryCode("NR", 169, "NRU");
    public static final CountryCode NU = new CountryCode("NU", 170, "NIU");
    public static final CountryCode NZ = new CountryCode("NZ", 171, "NZL");
    public static final CountryCode OM = new CountryCode("OM", 172, "OMN");
    public static final CountryCode PA = new CountryCode("PA", 173, "PAN");
    public static final CountryCode PE = new CountryCode("PE", 174, "PER");
    public static final CountryCode PF = new CountryCode("PF", 175, "PYF");
    public static final CountryCode PG = new CountryCode("PG", 176, "PNG");
    public static final CountryCode PH = new CountryCode("PH", 177, "PHL");
    public static final CountryCode PK = new CountryCode("PK", 178, "PAK");
    public static final CountryCode PL = new CountryCode("PL", 179, "POL");
    public static final CountryCode PM = new CountryCode("PM", 180, "SPM");
    public static final CountryCode PN = new CountryCode("PN", 181, "PCN");
    public static final CountryCode PR = new CountryCode("PR", 182, "PRI");
    public static final CountryCode PS = new CountryCode("PS", 183, "PSE");
    public static final CountryCode PT = new CountryCode("PT", 184, "PRT");
    public static final CountryCode PW = new CountryCode("PW", 185, "PLW");
    public static final CountryCode PY = new CountryCode("PY", CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256, "PRY");
    public static final CountryCode QA = new CountryCode("QA", 187, "QAT");
    public static final CountryCode RE = new CountryCode("RE", 188, "REU");
    public static final CountryCode RO = new CountryCode("RO", 189, "ROU");
    public static final CountryCode RS = new CountryCode("RS", CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA256, "SRB");
    public static final CountryCode RU = new CountryCode("RU", CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256, "RUS");
    public static final CountryCode RW = new CountryCode("RW", 192, "RWA");
    public static final CountryCode SA = new CountryCode("SA", CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, "SAU");
    public static final CountryCode SB = new CountryCode("SB", CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256, "SLB");
    public static final CountryCode SC = new CountryCode(BouncyCastleProvider.PROVIDER_NAME, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256, "SYC");
    public static final CountryCode SD = new CountryCode("SD", CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256, "SDN");
    public static final CountryCode SE = new CountryCode("SE", CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, "SWE");
    public static final CountryCode SG = new CountryCode("SG", 198, "SGP");
    public static final CountryCode SH = new CountryCode("SH", 199, "SHN");
    public static final CountryCode SI = new CountryCode("SI", 200, "SVN");
    public static final CountryCode SJ = new CountryCode("SJ", 201, "SJM");
    public static final CountryCode SK = new CountryCode("SK", 202, "SVK");
    public static final CountryCode SL = new CountryCode("SL", 203, "SLE");
    public static final CountryCode SM = new CountryCode("SM", 204, "SMR");
    public static final CountryCode SN = new CountryCode("SN", 205, "SEN");
    public static final CountryCode SO = new CountryCode("SO", 206, "SOM");
    public static final CountryCode SR = new CountryCode("SR", 207, "SUR");
    public static final CountryCode SS = new CountryCode(InAppStore.SERVER_SIDE_MODE, 208, "SSD");
    public static final CountryCode ST = new CountryCode("ST", Mp4VideoDirectory.TAG_DEPTH, "STP");
    public static final CountryCode SV = new CountryCode("SV", Mp4VideoDirectory.TAG_COMPRESSION_TYPE, "SLV");
    public static final CountryCode SX = new CountryCode("SX", 211, "SXM");
    public static final CountryCode SY = new CountryCode("SY", Mp4VideoDirectory.TAG_OPCOLOR, "SYR");
    public static final CountryCode SZ = new CountryCode("SZ", Mp4VideoDirectory.TAG_COLOR_TABLE, "SWZ");
    public static final CountryCode TC = new CountryCode("TC", Mp4VideoDirectory.TAG_FRAME_RATE, "TCA");
    public static final CountryCode TD = new CountryCode("TD", JfifUtil.MARKER_RST7, "TCD");
    public static final CountryCode TF = new CountryCode("TF", JfifUtil.MARKER_SOI, "ATF");
    public static final CountryCode TG = new CountryCode("TG", JfifUtil.MARKER_EOI, "TGO");
    public static final CountryCode TH = new CountryCode("TH", JfifUtil.MARKER_SOS, "THA");
    public static final CountryCode TJ = new CountryCode("TJ", 219, "TJK");
    public static final CountryCode TK = new CountryCode("TK", 220, "TKL");
    public static final CountryCode TL = new CountryCode("TL", 221, "TLS");
    public static final CountryCode TM = new CountryCode("TM", 222, "TKM");
    public static final CountryCode TN = new CountryCode("TN", PassportService.DEFAULT_MAX_BLOCKSIZE, "TUN");
    public static final CountryCode TO = new CountryCode("TO", 224, "TON");
    public static final CountryCode TR = new CountryCode("TR", JfifUtil.MARKER_APP1, "TUR");
    public static final CountryCode TT = new CountryCode("TT", 226, "TTO");
    public static final CountryCode TV = new CountryCode("TV", 227, "TUV");
    public static final CountryCode TW = new CountryCode("TW", 228, "TWN");
    public static final CountryCode TZ = new CountryCode("TZ", 229, "TZA");
    public static final CountryCode UA = new CountryCode("UA", 230, "UKR");
    public static final CountryCode UG = new CountryCode("UG", 231, "UGA");
    public static final CountryCode UM = new CountryCode("UM", 232, "UMI");
    public static final CountryCode US = new CountryCode("US", 233, "USA");
    public static final CountryCode UY = new CountryCode("UY", 234, "URY");
    public static final CountryCode UZ = new CountryCode("UZ", 235, "UZB");
    public static final CountryCode VA = new CountryCode("VA", 236, "VAT");
    public static final CountryCode VC = new CountryCode("VC", 237, "VCT");
    public static final CountryCode VE = new CountryCode("VE", 238, "VEN");
    public static final CountryCode VG = new CountryCode("VG", 239, "VGB");
    public static final CountryCode VI = new CountryCode("VI", FaceDetectorAvcMLKit.FACE_DETECTION_FRAME_WIDTH, "VIR");
    public static final CountryCode VN = new CountryCode("VN", 241, "VNM");
    public static final CountryCode VU = new CountryCode("VU", 242, "VUT");
    public static final CountryCode WF = new CountryCode("WF", 243, "WLF");
    public static final CountryCode WS = new CountryCode("WS", 244, "WSM");
    public static final CountryCode XK = new CountryCode("XK", 245, "RKS");
    public static final CountryCode YE = new CountryCode("YE", 246, "YEM");
    public static final CountryCode YT = new CountryCode("YT", 247, "MYT");
    public static final CountryCode ZA = new CountryCode("ZA", 248, "ZAF");
    public static final CountryCode ZM = new CountryCode("ZM", 249, "ZMB");
    public static final CountryCode ZW = new CountryCode("ZW", 250, "ZWE");

    private static final /* synthetic */ CountryCode[] $values() {
        return new CountryCode[]{AD, AE, AF, AG, AI, AL, AM, AN, AO, AQ, AR, AS, AT, AU, AW, AX, AZ, BA, BB, BD, BE, BF, BG, BH, BI, BJ, BL, BM, BN, BO, BQ, BR, BS, BT, BV, BW, BY, BZ, CA, CC, CD, CF, CG, CH, CI, CK, CL, CM, CN, CO, CR, CU, CV, CW, CX, CY, CZ, DE, DJ, DK, DM, DO, DZ, EC, EE, EG, EH, ER, ES, ET, FI, FJ, FK, FM, FO, FR, GA, GB, GD, GE, GF, GG, GH, GI, GL, GM, GN, GP, GQ, GR, GS, GT, GU, GW, GY, HK, HM, HN, HR, HT, HU, ID, IE, IL, IM, IN, IO, IQ, IR, IS, IT, JE, JM, JO, JP, KE, KG, KH, KI, KM, KN, KP, KR, KW, KY, KZ, LA, LB, LC, LI, LK, LR, LS, LT, LU, LV, LY, MA, MC, MD, ME, MF, MG, MH, MK, ML, MM, MN, MO, MP, MQ, MR, MS, MT, MU, MV, MW, MX, MY, MZ, NA, NC, NE, NF, NG, NI, NL, NO, NP, NR, NU, NZ, OM, PA, PE, PF, PG, PH, PK, PL, PM, PN, PR, PS, PT, PW, PY, QA, RE, RO, RS, RU, RW, SA, SB, SC, SD, SE, SG, SH, SI, SJ, SK, SL, SM, SN, SO, SR, SS, ST, SV, SX, SY, SZ, TC, TD, TF, TG, TH, TJ, TK, TL, TM, TN, TO, TR, TT, TV, TW, TZ, UA, UG, UM, US, UY, UZ, VA, VC, VE, VG, VI, VN, VU, WF, WS, XK, YE, YT, ZA, ZM, ZW};
    }

    public static EnumEntries<CountryCode> getEntries() {
        return $ENTRIES;
    }

    public static /* synthetic */ void getLocaleName$annotations() {
    }

    public static /* synthetic */ void getNativeName$annotations() {
    }

    public static CountryCode valueOf(String str) {
        return (CountryCode) Enum.valueOf(CountryCode.class, str);
    }

    public static CountryCode[] values() {
        return (CountryCode[]) $VALUES.clone();
    }

    public final String getAlpha3() {
        return this.alpha3;
    }

    public final String getLocaleName() {
        return this.localeName;
    }

    public final String getNativeName() {
        return this.nativeName;
    }

    public final void setLocaleName(String str) {
        this.localeName = str;
    }

    public final void setNativeName(String str) {
        this.nativeName = str;
    }

    private CountryCode(String str, int i, String str2) {
        this.alpha3 = str2;
    }

    static {
        CountryCode[] countryCodeArr$values = $values();
        $VALUES = countryCodeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(countryCodeArr$values);
        INSTANCE = new Companion(null);
        CountryCode[] countryCodeArrValues = values();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(countryCodeArrValues.length), 16));
        for (CountryCode countryCode : countryCodeArrValues) {
            linkedHashMap.put(countryCode.alpha3, countryCode);
        }
        map = linkedHashMap;
    }

    /* compiled from: CountryCode.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/CountryCode$Companion;", "", "()V", "map", "", "", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "fromAlpha3", "alpha3", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CountryCode fromAlpha3(String alpha3) {
            Intrinsics.checkNotNullParameter(alpha3, "alpha3");
            return (CountryCode) CountryCode.map.get(alpha3);
        }
    }
}
