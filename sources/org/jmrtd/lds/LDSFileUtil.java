package org.jmrtd.lds;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jmrtd.PassportService;
import org.jmrtd.cbeff.ISO781611;
import org.jmrtd.lds.icao.COMFile;
import org.jmrtd.lds.icao.DG11File;
import org.jmrtd.lds.icao.DG12File;
import org.jmrtd.lds.icao.DG14File;
import org.jmrtd.lds.icao.DG15File;
import org.jmrtd.lds.icao.DG1File;
import org.jmrtd.lds.icao.DG2File;
import org.jmrtd.lds.icao.DG3File;
import org.jmrtd.lds.icao.DG4File;
import org.jmrtd.lds.icao.DG5File;
import org.jmrtd.lds.icao.DG6File;
import org.jmrtd.lds.icao.DG7File;
import org.spongycastle.crypto.tls.AlertDescription;

/* loaded from: classes4.dex */
public final class LDSFileUtil {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    public static final Map<Short, Byte> FID_TO_SFI = createFIDToSFIMap();

    private LDSFileUtil() {
    }

    public static AbstractLDSFile getLDSFile(short s, InputStream inputStream) throws IOException {
        switch (s) {
            case 257:
                return new DG1File(inputStream);
            case 258:
                return new DG2File(inputStream);
            case 259:
                return new DG3File(inputStream);
            case 260:
                return new DG4File(inputStream);
            case 261:
                return new DG5File(inputStream);
            case 262:
                return new DG6File(inputStream);
            case 263:
                return new DG7File(inputStream);
            case 264:
                throw new IllegalArgumentException("DG8 files are not yet supported");
            case 265:
                throw new IllegalArgumentException("DG9 files are not yet supported");
            case 266:
                throw new IllegalArgumentException("DG10 files are not yet supported");
            case 267:
                return new DG11File(inputStream);
            case 268:
                return new DG12File(inputStream);
            case 269:
                throw new IllegalArgumentException("DG13 files are not yet supported");
            case 270:
                return new DG14File(inputStream);
            case 271:
                return new DG15File(inputStream);
            case 272:
                throw new IllegalArgumentException("DG16 files are not yet supported");
            default:
                switch (s) {
                    case 284:
                        return new CVCAFile(inputStream);
                    case 285:
                        return new SODFile(inputStream);
                    case 286:
                        return new COMFile(inputStream);
                    default:
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 37);
                        try {
                            bufferedInputStream.mark(37);
                            return new CVCAFile(s, bufferedInputStream);
                        } catch (Exception e) {
                            LOGGER.log(Level.WARNING, "Unknown file " + Integer.toHexString(s), (Throwable) e);
                            bufferedInputStream.reset();
                            throw new NumberFormatException("Unknown file " + Integer.toHexString(s));
                        }
                }
        }
    }

    public static short lookupFIDByTag(int i) {
        switch (i) {
            case 96:
                return PassportService.EF_COM;
            case 97:
                return PassportService.EF_DG1;
            case 98:
            case 100:
            case 113:
            case 114:
            case ISO781611.DISCRETIONARY_DATA_FOR_PAYLOAD_CONSTRUCTED_TAG /* 115 */:
            case 116:
            default:
                throw new NumberFormatException("Unknown tag " + Integer.toHexString(i));
            case 99:
                return PassportService.EF_DG3;
            case 101:
                return PassportService.EF_DG5;
            case 102:
                return PassportService.EF_DG6;
            case 103:
                return PassportService.EF_DG7;
            case 104:
                return PassportService.EF_DG8;
            case 105:
                return PassportService.EF_DG9;
            case 106:
                return PassportService.EF_DG10;
            case 107:
                return PassportService.EF_DG11;
            case 108:
                return PassportService.EF_DG12;
            case 109:
                return PassportService.EF_DG13;
            case LDSFile.EF_DG14_TAG /* 110 */:
                return PassportService.EF_DG14;
            case 111:
                return PassportService.EF_DG15;
            case 112:
                return PassportService.EF_DG16;
            case LDSFile.EF_DG2_TAG /* 117 */:
                return PassportService.EF_DG2;
            case LDSFile.EF_DG4_TAG /* 118 */:
                return PassportService.EF_DG4;
            case 119:
                return (short) 285;
        }
    }

    public static int lookupDataGroupNumberByTag(int i) {
        if (i == 97) {
            return 1;
        }
        if (i == 99) {
            return 3;
        }
        if (i == 117) {
            return 2;
        }
        if (i == 118) {
            return 4;
        }
        switch (i) {
            case 101:
                return 5;
            case 102:
                return 6;
            case 103:
                return 7;
            case 104:
                return 8;
            case 105:
                return 9;
            case 106:
                return 10;
            case 107:
                return 11;
            case 108:
                return 12;
            case 109:
                return 13;
            case LDSFile.EF_DG14_TAG /* 110 */:
                return 14;
            case 111:
                return 15;
            case 112:
                return 16;
            default:
                throw new NumberFormatException("Unknown tag " + Integer.toHexString(i));
        }
    }

    public static int lookupTagByDataGroupNumber(int i) {
        switch (i) {
            case 1:
                return 97;
            case 2:
                return LDSFile.EF_DG2_TAG;
            case 3:
                return 99;
            case 4:
                return LDSFile.EF_DG4_TAG;
            case 5:
                return 101;
            case 6:
                return 102;
            case 7:
                return 103;
            case 8:
                return 104;
            case 9:
                return 105;
            case 10:
                return 106;
            case 11:
                return 107;
            case 12:
                return 108;
            case 13:
                return 109;
            case 14:
                return LDSFile.EF_DG14_TAG;
            case 15:
                return 111;
            case 16:
                return 112;
            default:
                throw new NumberFormatException("Unknown number " + i);
        }
    }

    public static short lookupFIDByDataGroupNumber(int i) {
        switch (i) {
            case 1:
                return PassportService.EF_DG1;
            case 2:
                return PassportService.EF_DG2;
            case 3:
                return PassportService.EF_DG3;
            case 4:
                return PassportService.EF_DG4;
            case 5:
                return PassportService.EF_DG5;
            case 6:
                return PassportService.EF_DG6;
            case 7:
                return PassportService.EF_DG7;
            case 8:
                return PassportService.EF_DG8;
            case 9:
                return PassportService.EF_DG9;
            case 10:
                return PassportService.EF_DG10;
            case 11:
                return PassportService.EF_DG11;
            case 12:
                return PassportService.EF_DG12;
            case 13:
                return PassportService.EF_DG13;
            case 14:
                return PassportService.EF_DG14;
            case 15:
                return PassportService.EF_DG15;
            case 16:
                return PassportService.EF_DG16;
            default:
                throw new NumberFormatException("Unknown number " + i);
        }
    }

    public static short lookupTagByFID(short s) {
        if (s == 285) {
            return (short) 119;
        }
        if (s == 286) {
            return (short) 96;
        }
        switch (s) {
            case 257:
                return (short) 97;
            case 258:
                return (short) 117;
            case 259:
                return (short) 99;
            case 260:
                return (short) 118;
            case 261:
                return (short) 101;
            case 262:
                return (short) 102;
            case 263:
                return (short) 103;
            case 264:
                return (short) 104;
            case 265:
                return (short) 105;
            case 266:
                return (short) 106;
            case 267:
                return (short) 107;
            case 268:
                return (short) 108;
            case 269:
                return (short) 109;
            case 270:
                return AlertDescription.unsupported_extension;
            case 271:
                return AlertDescription.certificate_unobtainable;
            case 272:
                return AlertDescription.unrecognized_name;
            default:
                throw new NumberFormatException("Unknown fid " + Integer.toHexString(s));
        }
    }

    public static int lookupDataGroupNumberByFID(short s) {
        switch (s) {
            case 257:
                return 1;
            case 258:
                return 2;
            case 259:
                return 3;
            case 260:
                return 4;
            case 261:
                return 5;
            case 262:
                return 6;
            case 263:
                return 7;
            case 264:
                return 8;
            case 265:
                return 9;
            case 266:
                return 10;
            case 267:
                return 11;
            case 268:
                return 12;
            case 269:
                return 13;
            case 270:
                return 14;
            case 271:
                return 15;
            case 272:
                return 16;
            default:
                throw new NumberFormatException("Unknown fid " + Integer.toHexString(s));
        }
    }

    public static String lookupFileNameByTag(int i) {
        switch (i) {
            case 96:
                return "EF_COM";
            case 97:
                return "EF_DG1";
            case 98:
            case 100:
            case 113:
            case 114:
            case ISO781611.DISCRETIONARY_DATA_FOR_PAYLOAD_CONSTRUCTED_TAG /* 115 */:
            case 116:
            default:
                return "File with tag 0x" + Integer.toHexString(i);
            case 99:
                return "EF_DG3";
            case 101:
                return "EF_DG5";
            case 102:
                return "EF_DG6";
            case 103:
                return "EF_DG7";
            case 104:
                return "EF_DG8";
            case 105:
                return "EF_DG9";
            case 106:
                return "EF_DG10";
            case 107:
                return "EF_DG11";
            case 108:
                return "EF_DG12";
            case 109:
                return "EF_DG13";
            case LDSFile.EF_DG14_TAG /* 110 */:
                return "EF_DG14";
            case 111:
                return "EF_DG15";
            case 112:
                return "EF_DG16";
            case LDSFile.EF_DG2_TAG /* 117 */:
                return "EF_DG2";
            case LDSFile.EF_DG4_TAG /* 118 */:
                return "EF_DG4";
            case 119:
                return "EF_SOD";
        }
    }

    public static String lookupFileNameByFID(int i) {
        if (i == 285) {
            return "EF_SOD";
        }
        if (i == 286) {
            return "EF_COM";
        }
        switch (i) {
            case 257:
                return "EF_DG1";
            case 258:
                return "EF_DG2";
            case 259:
                return "EF_DG3";
            case 260:
                return "EF_DG4";
            case 261:
                return "EF_DG5";
            case 262:
                return "EF_DG6";
            case 263:
                return "EF_DG7";
            case 264:
                return "EF_DG8";
            case 265:
                return "EF_DG9";
            case 266:
                return "EF_DG10";
            case 267:
                return "EF_DG11";
            case 268:
                return "EF_DG12";
            case 269:
                return "EF_DG13";
            case 270:
                return "EF_DG14";
            case 271:
                return "EF_DG15";
            case 272:
                return "EF_DG16";
            default:
                return "File with FID 0x" + Integer.toHexString(i);
        }
    }

    public static int lookupSFIByFID(short s) {
        Byte b = FID_TO_SFI.get(Short.valueOf(s));
        if (b == null) {
            throw new NumberFormatException("Unknown FID " + Integer.toHexString(s));
        }
        return b.byteValue() & 255;
    }

    public static short lookupFIDBySFI(byte b) {
        switch (b) {
            case 1:
                return PassportService.EF_DG1;
            case 2:
                return PassportService.EF_DG2;
            case 3:
                return PassportService.EF_DG3;
            case 4:
                return PassportService.EF_DG4;
            case 5:
                return PassportService.EF_DG5;
            case 6:
                return PassportService.EF_DG6;
            case 7:
                return PassportService.EF_DG7;
            case 8:
                return PassportService.EF_DG8;
            case 9:
                return PassportService.EF_DG9;
            case 10:
                return PassportService.EF_DG10;
            case 11:
                return PassportService.EF_DG11;
            case 12:
                return PassportService.EF_DG12;
            case 13:
                return PassportService.EF_DG13;
            case 14:
                return PassportService.EF_DG14;
            case 15:
                return PassportService.EF_DG15;
            case 16:
                return PassportService.EF_DG16;
            default:
                switch (b) {
                    case 28:
                        return (short) 284;
                    case 29:
                        return (short) 285;
                    case 30:
                        return PassportService.EF_COM;
                    default:
                        throw new NumberFormatException("Unknown SFI " + Integer.toHexString(b));
                }
        }
    }

    public static List<Integer> getDataGroupNumbers(SODFile sODFile) {
        ArrayList arrayList = new ArrayList();
        if (sODFile == null) {
            return arrayList;
        }
        arrayList.addAll(sODFile.getDataGroupHashes().keySet());
        Collections.sort(arrayList);
        return arrayList;
    }

    public static List<Integer> getDataGroupNumbers(COMFile cOMFile) {
        ArrayList arrayList = new ArrayList();
        if (cOMFile == null) {
            return arrayList;
        }
        arrayList.addAll(toDataGroupList(cOMFile.getTagList()));
        Collections.sort(arrayList);
        return arrayList;
    }

    private static List<Integer> toDataGroupList(int[] iArr) {
        if (iArr == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            try {
                arrayList.add(Integer.valueOf(lookupDataGroupNumberByTag(i)));
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "Could not find DG number for tag: " + Integer.toHexString(i), (Throwable) e);
            }
        }
        return arrayList;
    }

    private static Map<Short, Byte> createFIDToSFIMap() {
        HashMap map = new HashMap(20);
        map.put(Short.valueOf(PassportService.EF_COM), (byte) 30);
        map.put(Short.valueOf(PassportService.EF_DG1), (byte) 1);
        map.put(Short.valueOf(PassportService.EF_DG2), (byte) 2);
        map.put(Short.valueOf(PassportService.EF_DG3), (byte) 3);
        map.put(Short.valueOf(PassportService.EF_DG4), (byte) 4);
        map.put(Short.valueOf(PassportService.EF_DG5), (byte) 5);
        map.put(Short.valueOf(PassportService.EF_DG6), (byte) 6);
        map.put(Short.valueOf(PassportService.EF_DG7), (byte) 7);
        map.put(Short.valueOf(PassportService.EF_DG8), (byte) 8);
        map.put(Short.valueOf(PassportService.EF_DG9), (byte) 9);
        map.put(Short.valueOf(PassportService.EF_DG10), (byte) 10);
        map.put(Short.valueOf(PassportService.EF_DG11), (byte) 11);
        map.put(Short.valueOf(PassportService.EF_DG12), (byte) 12);
        map.put(Short.valueOf(PassportService.EF_DG13), (byte) 13);
        map.put(Short.valueOf(PassportService.EF_DG14), (byte) 14);
        map.put(Short.valueOf(PassportService.EF_DG15), (byte) 15);
        map.put(Short.valueOf(PassportService.EF_DG16), (byte) 16);
        map.put((short) 285, (byte) 29);
        map.put((short) 284, (byte) 28);
        return Collections.unmodifiableMap(map);
    }
}
