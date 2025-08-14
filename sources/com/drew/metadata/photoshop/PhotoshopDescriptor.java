package com.drew.metadata.photoshop;

import com.clevertap.android.sdk.Constants;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.Charsets;
import com.drew.metadata.TagDescriptor;
import com.google.android.material.timepicker.TimeModel;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class PhotoshopDescriptor extends TagDescriptor<PhotoshopDirectory> {
    public PhotoshopDescriptor(PhotoshopDirectory photoshopDirectory) {
        super(photoshopDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i != 1002) {
            if (i == 1005) {
                return getResolutionInfoDescription();
            }
            if (i == 1028) {
                return getBinaryDataString(i);
            }
            if (i != 1030) {
                if (i != 1044 && i != 1054) {
                    if (i == 1057) {
                        return getVersionDescription();
                    }
                    if (i == 1062) {
                        return getPrintScaleDescription();
                    }
                    if (i == 1064) {
                        return getPixelAspectRatioString();
                    }
                    if (i == 2999) {
                        return getClippingPathNameString(i);
                    }
                    if (i != 1049) {
                        if (i != 1050) {
                            switch (i) {
                                case PhotoshopDirectory.TAG_THUMBNAIL_OLD /* 1033 */:
                                case PhotoshopDirectory.TAG_THUMBNAIL /* 1036 */:
                                    return getThumbnailDescription(i);
                                case PhotoshopDirectory.TAG_COPYRIGHT /* 1034 */:
                                    return getBooleanString(i);
                                case PhotoshopDirectory.TAG_URL /* 1035 */:
                                    break;
                                case 1037:
                                    break;
                                default:
                                    if (i >= 2000 && i <= 2998) {
                                        return getPathString(i);
                                    }
                                    return super.getDescription(i);
                            }
                        } else {
                            return getSlicesDescription();
                        }
                    }
                }
                return get32BitNumberString(i);
            }
            return getJpegQualityString();
        }
        return getSimpleString(i);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getJpegQualityString() {
        /*
            r11 = this;
            T extends com.drew.metadata.Directory r0 = r11._directory     // Catch: java.io.IOException -> Lab
            com.drew.metadata.photoshop.PhotoshopDirectory r0 = (com.drew.metadata.photoshop.PhotoshopDirectory) r0     // Catch: java.io.IOException -> Lab
            r1 = 1030(0x406, float:1.443E-42)
            byte[] r0 = r0.getByteArray(r1)     // Catch: java.io.IOException -> Lab
            if (r0 != 0) goto L15
            T extends com.drew.metadata.Directory r0 = r11._directory     // Catch: java.io.IOException -> Lab
            com.drew.metadata.photoshop.PhotoshopDirectory r0 = (com.drew.metadata.photoshop.PhotoshopDirectory) r0     // Catch: java.io.IOException -> Lab
            java.lang.String r0 = r0.getString(r1)     // Catch: java.io.IOException -> Lab
            return r0
        L15:
            com.drew.lang.ByteArrayReader r1 = new com.drew.lang.ByteArrayReader     // Catch: java.io.IOException -> Lab
            r1.<init>(r0)     // Catch: java.io.IOException -> Lab
            r0 = 0
            int r2 = r1.getUInt16(r0)     // Catch: java.io.IOException -> Lab
            r3 = 2
            int r4 = r1.getUInt16(r3)     // Catch: java.io.IOException -> Lab
            r5 = 4
            int r1 = r1.getUInt16(r5)     // Catch: java.io.IOException -> Lab
            r6 = 65535(0xffff, float:9.1834E-41)
            if (r2 > r6) goto L39
            r6 = 65533(0xfffd, float:9.1831E-41)
            if (r2 < r6) goto L39
            r6 = 65532(0xfffc, float:9.183E-41)
            int r6 = r2 - r6
            goto L41
        L39:
            r6 = 8
            if (r2 > r6) goto L40
            int r6 = r2 + 4
            goto L41
        L40:
            r6 = r2
        L41:
            switch(r2) {
                case 0: goto L53;
                case 1: goto L50;
                case 2: goto L50;
                case 3: goto L50;
                case 4: goto L4d;
                case 5: goto L4d;
                case 6: goto L4a;
                case 7: goto L4a;
                case 8: goto L4a;
                default: goto L44;
            }     // Catch: java.io.IOException -> Lab
        L44:
            switch(r2) {
                case 65533: goto L53;
                case 65534: goto L53;
                case 65535: goto L53;
                default: goto L47;
            }     // Catch: java.io.IOException -> Lab
        L47:
            java.lang.String r2 = "Unknown"
            goto L55
        L4a:
            java.lang.String r2 = "Maximum"
            goto L55
        L4d:
            java.lang.String r2 = "High"
            goto L55
        L50:
            java.lang.String r2 = "Medium"
            goto L55
        L53:
            java.lang.String r2 = "Low"
        L55:
            java.lang.String r7 = "Unknown 0x%04X"
            r8 = 1
            if (r4 == 0) goto L73
            if (r4 == r8) goto L70
            r9 = 257(0x101, float:3.6E-43)
            if (r4 == r9) goto L6d
            java.lang.Object[] r9 = new java.lang.Object[r8]     // Catch: java.io.IOException -> Lab
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.io.IOException -> Lab
            r9[r0] = r4     // Catch: java.io.IOException -> Lab
            java.lang.String r4 = java.lang.String.format(r7, r9)     // Catch: java.io.IOException -> Lab
            goto L75
        L6d:
            java.lang.String r4 = "Progressive"
            goto L75
        L70:
            java.lang.String r4 = "Optimised"
            goto L75
        L73:
            java.lang.String r4 = "Standard"
        L75:
            r9 = 3
            if (r1 < r8) goto L8a
            if (r1 > r9) goto L8a
            java.lang.String r7 = "%d"
            java.lang.Object[] r10 = new java.lang.Object[r8]     // Catch: java.io.IOException -> Lab
            int r1 = r1 + r3
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.io.IOException -> Lab
            r10[r0] = r1     // Catch: java.io.IOException -> Lab
            java.lang.String r1 = java.lang.String.format(r7, r10)     // Catch: java.io.IOException -> Lab
            goto L96
        L8a:
            java.lang.Object[] r10 = new java.lang.Object[r8]     // Catch: java.io.IOException -> Lab
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.io.IOException -> Lab
            r10[r0] = r1     // Catch: java.io.IOException -> Lab
            java.lang.String r1 = java.lang.String.format(r7, r10)     // Catch: java.io.IOException -> Lab
        L96:
            java.lang.String r7 = "%d (%s), %s format, %s scans"
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.io.IOException -> Lab
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch: java.io.IOException -> Lab
            r5[r0] = r6     // Catch: java.io.IOException -> Lab
            r5[r8] = r2     // Catch: java.io.IOException -> Lab
            r5[r3] = r4     // Catch: java.io.IOException -> Lab
            r5[r9] = r1     // Catch: java.io.IOException -> Lab
            java.lang.String r0 = java.lang.String.format(r7, r5)     // Catch: java.io.IOException -> Lab
            return r0
        Lab:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.metadata.photoshop.PhotoshopDescriptor.getJpegQualityString():java.lang.String");
    }

    public String getPixelAspectRatioString() {
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(PhotoshopDirectory.TAG_PIXEL_ASPECT_RATIO);
            if (byteArray == null) {
                return null;
            }
            return Double.toString(new ByteArrayReader(byteArray).getDouble64(4));
        } catch (Exception unused) {
            return null;
        }
    }

    public String getPrintScaleDescription() {
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(PhotoshopDirectory.TAG_PRINT_SCALE);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
            int int32 = byteArrayReader.getInt32(0);
            float float32 = byteArrayReader.getFloat32(2);
            float float322 = byteArrayReader.getFloat32(6);
            float float323 = byteArrayReader.getFloat32(10);
            if (int32 != 0) {
                return int32 != 1 ? int32 != 2 ? String.format("Unknown %04X, X:%s Y:%s, Scale:%s", Integer.valueOf(int32), Float.valueOf(float32), Float.valueOf(float322), Float.valueOf(float323)) : String.format("User defined, X:%s Y:%s, Scale:%s", Float.valueOf(float32), Float.valueOf(float322), Float.valueOf(float323)) : "Size to fit";
            }
            return "Centered, Scale " + float323;
        } catch (Exception unused) {
            return null;
        }
    }

    public String getResolutionInfoDescription() {
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(1005);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
            float s15Fixed16 = byteArrayReader.getS15Fixed16(0);
            float s15Fixed162 = byteArrayReader.getS15Fixed16(8);
            DecimalFormat decimalFormat = new DecimalFormat("0.##");
            return decimalFormat.format(s15Fixed16) + "x" + decimalFormat.format(s15Fixed162) + " DPI";
        } catch (Exception unused) {
            return null;
        }
    }

    public String getVersionDescription() {
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(PhotoshopDirectory.TAG_VERSION);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
            int int32 = byteArrayReader.getInt32(0);
            int int322 = byteArrayReader.getInt32(5) * 2;
            String string = byteArrayReader.getString(9, int322, "UTF-16");
            int int323 = byteArrayReader.getInt32(9 + int322);
            int i = int322 + 13;
            int i2 = int323 * 2;
            return String.format("%d (%s, %s) %d", Integer.valueOf(int32), string, byteArrayReader.getString(i, i2, "UTF-16"), Integer.valueOf(byteArrayReader.getInt32(i + i2)));
        } catch (IOException unused) {
            return null;
        }
    }

    public String getSlicesDescription() {
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(1050);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
            int int32 = byteArrayReader.getInt32(20) * 2;
            return String.format("%s (%d,%d,%d,%d) %d Slices", byteArrayReader.getString(24, int32, "UTF-16"), Integer.valueOf(byteArrayReader.getInt32(4)), Integer.valueOf(byteArrayReader.getInt32(8)), Integer.valueOf(byteArrayReader.getInt32(12)), Integer.valueOf(byteArrayReader.getInt32(16)), Integer.valueOf(byteArrayReader.getInt32(int32 + 24)));
        } catch (IOException unused) {
            return null;
        }
    }

    public String getThumbnailDescription(int i) {
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
            int int32 = byteArrayReader.getInt32(0);
            int int322 = byteArrayReader.getInt32(4);
            int int323 = byteArrayReader.getInt32(8);
            int int324 = byteArrayReader.getInt32(16);
            int int325 = byteArrayReader.getInt32(20);
            int int326 = byteArrayReader.getInt32(24);
            Object[] objArr = new Object[6];
            objArr[0] = int32 == 1 ? "JpegRGB" : "RawRGB";
            objArr[1] = Integer.valueOf(int322);
            objArr[2] = Integer.valueOf(int323);
            objArr[3] = Integer.valueOf(int324);
            objArr[4] = Integer.valueOf(int326);
            objArr[5] = Integer.valueOf(int325);
            return String.format("%s, %dx%d, Decomp %d bytes, %d bpp, %d bytes", objArr);
        } catch (IOException unused) {
            return null;
        }
    }

    private String getBooleanString(int i) {
        byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
        if (byteArray == null || byteArray.length == 0) {
            return null;
        }
        return byteArray[0] == 0 ? "No" : "Yes";
    }

    private String get32BitNumberString(int i) {
        byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
        if (byteArray == null) {
            return null;
        }
        try {
            return String.format(TimeModel.NUMBER_FORMAT, Integer.valueOf(new ByteArrayReader(byteArray).getInt32(0)));
        } catch (IOException unused) {
            return null;
        }
    }

    private String getSimpleString(int i) {
        byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
        if (byteArray == null) {
            return null;
        }
        return new String(byteArray);
    }

    private String getBinaryDataString(int i) {
        byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
        if (byteArray == null) {
            return null;
        }
        return String.format("%d bytes binary data", Integer.valueOf(byteArray.length));
    }

    public String getClippingPathNameString(int i) {
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
            return new String(byteArrayReader.getBytes(1, byteArrayReader.getByte(0)), "UTF-8");
        } catch (Exception unused) {
            return null;
        }
    }

    public String getPathString(int i) {
        String str;
        String str2;
        int i2;
        int i3;
        ByteArrayReader byteArrayReader;
        Subpath subpath;
        short s;
        Knot knot;
        ByteArrayReader byteArrayReader2;
        Subpath subpath2;
        Knot knot2;
        String str3 = ")";
        String str4 = Constants.SEPARATOR_COMMA;
        try {
            byte[] byteArray = ((PhotoshopDirectory) this._directory).getByteArray(i);
            if (byteArray == null) {
                return null;
            }
            ByteArrayReader byteArrayReader3 = new ByteArrayReader(byteArray);
            short s2 = 1;
            int length = ((int) ((byteArrayReader3.getLength() - byteArrayReader3.getByte(((int) byteArrayReader3.getLength()) - 1)) - 1)) / 26;
            Subpath subpath3 = new Subpath();
            Subpath subpath4 = new Subpath();
            ArrayList arrayList = new ArrayList();
            String str5 = null;
            int i4 = 0;
            while (i4 < length) {
                int i5 = i4 * 26;
                try {
                    short int16 = byteArrayReader3.getInt16(i5);
                    if (int16 == 0) {
                        str = str3;
                        str2 = str4;
                        i2 = length;
                        i3 = i4;
                        if (subpath3.size() != 0) {
                            arrayList.add(subpath3);
                        }
                        subpath3 = new Subpath("Closed Subpath");
                    } else {
                        i3 = i4;
                        if (int16 == s2) {
                            str = str3;
                            str2 = str4;
                            i2 = length;
                            byteArrayReader = byteArrayReader3;
                            subpath = subpath3;
                            s = s2;
                        } else if (int16 != 2) {
                            if (int16 == 3) {
                                str = str3;
                                str2 = str4;
                                i2 = length;
                                byteArrayReader2 = byteArrayReader3;
                                subpath2 = subpath3;
                                if (subpath4.size() != 0) {
                                    arrayList.add(subpath4);
                                }
                                subpath4 = new Subpath("Open Subpath");
                            } else {
                                short s3 = 4;
                                if (int16 != 4) {
                                    if (int16 != 5) {
                                        if (int16 == 8) {
                                            str5 = byteArrayReader3.getInt16(i5 + 2) == 1 ? "with all pixels" : "without all pixels";
                                        }
                                        str = str3;
                                        str2 = str4;
                                        i2 = length;
                                    } else {
                                        s3 = 4;
                                    }
                                }
                                if (int16 == s3) {
                                    knot2 = new Knot("Linked");
                                } else {
                                    knot2 = new Knot("Unlinked");
                                }
                                int i6 = 0;
                                while (i6 < 6) {
                                    int i7 = i6 * 4;
                                    knot2.setPoint(i6, byteArrayReader3.getInt8(i7 + 2 + i5) + (byteArrayReader3.getInt24((i7 + 3) + i5) / Math.pow(2.0d, 24.0d)));
                                    i6++;
                                    length = length;
                                    str3 = str3;
                                    str4 = str4;
                                    byteArrayReader3 = byteArrayReader3;
                                    subpath3 = subpath3;
                                }
                                str = str3;
                                str2 = str4;
                                i2 = length;
                                byteArrayReader2 = byteArrayReader3;
                                subpath2 = subpath3;
                                subpath4.add(knot2);
                            }
                            byteArrayReader3 = byteArrayReader2;
                            subpath3 = subpath2;
                        } else {
                            str = str3;
                            str2 = str4;
                            i2 = length;
                            byteArrayReader = byteArrayReader3;
                            subpath = subpath3;
                            s = 1;
                        }
                        if (int16 == s) {
                            knot = new Knot("Linked");
                        } else {
                            knot = new Knot("Unlinked");
                        }
                        int i8 = 0;
                        while (i8 < 6) {
                            int i9 = i8 * 4;
                            knot.setPoint(i8, r6.getInt8(i9 + 2 + i5) + (r6.getInt24((i9 + 3) + i5) / Math.pow(2.0d, 24.0d)));
                            i8++;
                            i5 = i5;
                            byteArrayReader = byteArrayReader;
                        }
                        byteArrayReader3 = byteArrayReader;
                        subpath3 = subpath;
                        subpath3.add(knot);
                    }
                    i4 = i3 + 1;
                    length = i2;
                    str3 = str;
                    str4 = str2;
                    s2 = 1;
                } catch (Exception unused) {
                    return null;
                }
            }
            String str6 = str3;
            String str7 = str4;
            if (subpath3.size() != 0) {
                arrayList.add(subpath3);
            }
            if (subpath4.size() != 0) {
                arrayList.add(subpath4);
            }
            byte b = byteArrayReader3.getByte(((int) byteArrayReader3.getLength()) - 1);
            String string = byteArrayReader3.getString((((int) byteArrayReader3.getLength()) - b) - 1, b, Charsets.ASCII);
            StringBuilder sb = new StringBuilder();
            sb.append('\"').append(string).append('\"').append(" having ");
            if (str5 != null) {
                sb.append("initial fill rule \"").append(str5).append("\" and ");
            }
            sb.append(arrayList.size()).append(arrayList.size() == 1 ? " subpath:" : " subpaths:");
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Subpath subpath5 = (Subpath) it.next();
                sb.append("\n- ").append(subpath5.getType()).append(" with ").append(arrayList.size()).append(arrayList.size() == 1 ? " knot:" : " knots:");
                for (Knot knot3 : subpath5.getKnots()) {
                    sb.append("\n  - ").append(knot3.getType());
                    String str8 = str7;
                    String str9 = str6;
                    sb.append(" (").append(knot3.getPoint(0)).append(str8).append(knot3.getPoint(1)).append(str9);
                    sb.append(" (").append(knot3.getPoint(2)).append(str8).append(knot3.getPoint(3)).append(str9);
                    str7 = str8;
                    sb.append(" (").append(knot3.getPoint(4)).append(str8).append(knot3.getPoint(5)).append(str9);
                    str6 = str9;
                }
            }
            return sb.toString();
        } catch (Exception unused2) {
            return null;
        }
    }
}
