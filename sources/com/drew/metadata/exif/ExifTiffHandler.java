package com.drew.metadata.exif;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.tiff.TiffProcessingException;
import com.drew.imaging.tiff.TiffReader;
import com.drew.lang.BufferBoundsException;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.Charsets;
import com.drew.lang.RandomAccessReader;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.StringValue;
import com.drew.metadata.exif.makernotes.AppleMakernoteDirectory;
import com.drew.metadata.exif.makernotes.CanonMakernoteDirectory;
import com.drew.metadata.exif.makernotes.CasioType1MakernoteDirectory;
import com.drew.metadata.exif.makernotes.CasioType2MakernoteDirectory;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import com.drew.metadata.exif.makernotes.KodakMakernoteDirectory;
import com.drew.metadata.exif.makernotes.KyoceraMakernoteDirectory;
import com.drew.metadata.exif.makernotes.LeicaMakernoteDirectory;
import com.drew.metadata.exif.makernotes.LeicaType5MakernoteDirectory;
import com.drew.metadata.exif.makernotes.NikonType1MakernoteDirectory;
import com.drew.metadata.exif.makernotes.NikonType2MakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusCameraSettingsMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusEquipmentMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusFocusInfoMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusImageProcessingMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusRawDevelopment2MakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusRawDevelopmentMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusRawInfoMakernoteDirectory;
import com.drew.metadata.exif.makernotes.PanasonicMakernoteDirectory;
import com.drew.metadata.exif.makernotes.PentaxMakernoteDirectory;
import com.drew.metadata.exif.makernotes.ReconyxHyperFireMakernoteDirectory;
import com.drew.metadata.exif.makernotes.ReconyxUltraFireMakernoteDirectory;
import com.drew.metadata.exif.makernotes.RicohMakernoteDirectory;
import com.drew.metadata.exif.makernotes.SamsungType2MakernoteDirectory;
import com.drew.metadata.exif.makernotes.SanyoMakernoteDirectory;
import com.drew.metadata.exif.makernotes.SigmaMakernoteDirectory;
import com.drew.metadata.exif.makernotes.SonyType1MakernoteDirectory;
import com.drew.metadata.exif.makernotes.SonyType6MakernoteDirectory;
import com.drew.metadata.icc.IccReader;
import com.drew.metadata.iptc.IptcReader;
import com.drew.metadata.photoshop.PhotoshopReader;
import com.drew.metadata.tiff.DirectoryTiffHandler;
import com.drew.metadata.xmp.XmpReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.jmrtd.cbeff.ISO781611;
import org.jmrtd.lds.LDSFile;

/* loaded from: classes5.dex */
public class ExifTiffHandler extends DirectoryTiffHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public ExifTiffHandler(Metadata metadata, Directory directory) {
        super(metadata, directory);
    }

    @Override // com.drew.imaging.tiff.TiffHandler
    public void setTiffMarker(int i) throws TiffProcessingException {
        if (i != 42) {
            if (i == 85) {
                pushDirectory(PanasonicRawIFD0Directory.class);
                return;
            } else if (i != 20306 && i != 21330) {
                throw new TiffProcessingException(String.format("Unexpected TIFF marker: 0x%X", Integer.valueOf(i)));
            }
        }
        pushDirectory(ExifIFD0Directory.class);
    }

    @Override // com.drew.imaging.tiff.TiffHandler
    public boolean tryEnterSubIfd(int i) {
        if (i == 330) {
            pushDirectory(ExifSubIFDDirectory.class);
            return true;
        }
        if ((this._currentDirectory instanceof ExifIFD0Directory) || (this._currentDirectory instanceof PanasonicRawIFD0Directory)) {
            if (i == 34665) {
                pushDirectory(ExifSubIFDDirectory.class);
                return true;
            }
            if (i == 34853) {
                pushDirectory(GpsDirectory.class);
                return true;
            }
        }
        if ((this._currentDirectory instanceof ExifSubIFDDirectory) && i == 40965) {
            pushDirectory(ExifInteropDirectory.class);
            return true;
        }
        if (!(this._currentDirectory instanceof OlympusMakernoteDirectory)) {
            return false;
        }
        if (i == 8208) {
            pushDirectory(OlympusEquipmentMakernoteDirectory.class);
            return true;
        }
        if (i == 8224) {
            pushDirectory(OlympusCameraSettingsMakernoteDirectory.class);
            return true;
        }
        if (i == 8256) {
            pushDirectory(OlympusImageProcessingMakernoteDirectory.class);
            return true;
        }
        if (i == 8272) {
            pushDirectory(OlympusFocusInfoMakernoteDirectory.class);
            return true;
        }
        if (i == 12288) {
            pushDirectory(OlympusRawInfoMakernoteDirectory.class);
            return true;
        }
        if (i == 16384) {
            pushDirectory(OlympusMakernoteDirectory.class);
            return true;
        }
        if (i == 8240) {
            pushDirectory(OlympusRawDevelopmentMakernoteDirectory.class);
            return true;
        }
        if (i != 8241) {
            return false;
        }
        pushDirectory(OlympusRawDevelopment2MakernoteDirectory.class);
        return true;
    }

    @Override // com.drew.imaging.tiff.TiffHandler
    public boolean hasFollowerIfd() {
        if (!(this._currentDirectory instanceof ExifIFD0Directory) && !(this._currentDirectory instanceof ExifImageDirectory)) {
            return this._currentDirectory instanceof ExifThumbnailDirectory;
        }
        if (this._currentDirectory.containsTag(ExifDirectoryBase.TAG_PAGE_NUMBER)) {
            pushDirectory(ExifImageDirectory.class);
        } else {
            pushDirectory(ExifThumbnailDirectory.class);
        }
        return true;
    }

    @Override // com.drew.imaging.tiff.TiffHandler
    public Long tryCustomProcessFormat(int i, int i2, long j) {
        if (i2 == 13) {
            return Long.valueOf(j * 4);
        }
        return i2 == 0 ? 0L : null;
    }

    @Override // com.drew.imaging.tiff.TiffHandler
    public boolean customProcessTag(int i, Set<Integer> set, int i2, RandomAccessReader randomAccessReader, int i3, int i4) throws Throwable {
        if (i3 == 0) {
            if (this._currentDirectory.containsTag(i3)) {
                return false;
            }
            if (i4 == 0) {
                return true;
            }
        }
        if (i3 == 37500 && (this._currentDirectory instanceof ExifSubIFDDirectory)) {
            return processMakernote(i, set, i2, randomAccessReader);
        }
        if (i3 == 33723 && (this._currentDirectory instanceof ExifIFD0Directory)) {
            if (randomAccessReader.getInt8(i) != 28) {
                return false;
            }
            new IptcReader().extract(new SequentialByteArrayReader(randomAccessReader.getBytes(i, i4)), this._metadata, r0.length, this._currentDirectory);
            return true;
        }
        if (i3 == 34675) {
            new IccReader().extract(new ByteArrayReader(randomAccessReader.getBytes(i, i4)), this._metadata, this._currentDirectory);
            return true;
        }
        if (i3 == 34377 && (this._currentDirectory instanceof ExifIFD0Directory)) {
            new PhotoshopReader().extract(new SequentialByteArrayReader(randomAccessReader.getBytes(i, i4)), i4, this._metadata, this._currentDirectory);
            return true;
        }
        if (i3 == 700 && (this._currentDirectory instanceof ExifIFD0Directory)) {
            new XmpReader().extract(randomAccessReader.getNullTerminatedBytes(i, i4), this._metadata, this._currentDirectory);
            return true;
        }
        if (handlePrintIM(this._currentDirectory, i3)) {
            PrintIMDirectory printIMDirectory = new PrintIMDirectory();
            printIMDirectory.setParent(this._currentDirectory);
            this._metadata.addDirectory(printIMDirectory);
            processPrintIM(printIMDirectory, i, randomAccessReader, i4);
            return true;
        }
        if (this._currentDirectory instanceof OlympusMakernoteDirectory) {
            if (i3 == 8208) {
                pushDirectory(OlympusEquipmentMakernoteDirectory.class);
                TiffReader.processIfd(this, randomAccessReader, set, i, i2);
                return true;
            }
            if (i3 == 8224) {
                pushDirectory(OlympusCameraSettingsMakernoteDirectory.class);
                TiffReader.processIfd(this, randomAccessReader, set, i, i2);
                return true;
            }
            if (i3 == 8256) {
                pushDirectory(OlympusImageProcessingMakernoteDirectory.class);
                TiffReader.processIfd(this, randomAccessReader, set, i, i2);
                return true;
            }
            if (i3 == 8272) {
                pushDirectory(OlympusFocusInfoMakernoteDirectory.class);
                TiffReader.processIfd(this, randomAccessReader, set, i, i2);
                return true;
            }
            if (i3 == 12288) {
                pushDirectory(OlympusRawInfoMakernoteDirectory.class);
                TiffReader.processIfd(this, randomAccessReader, set, i, i2);
                return true;
            }
            if (i3 == 16384) {
                pushDirectory(OlympusMakernoteDirectory.class);
                TiffReader.processIfd(this, randomAccessReader, set, i, i2);
                return true;
            }
            if (i3 == 8240) {
                pushDirectory(OlympusRawDevelopmentMakernoteDirectory.class);
                TiffReader.processIfd(this, randomAccessReader, set, i, i2);
                return true;
            }
            if (i3 == 8241) {
                pushDirectory(OlympusRawDevelopment2MakernoteDirectory.class);
                TiffReader.processIfd(this, randomAccessReader, set, i, i2);
                return true;
            }
        }
        if (this._currentDirectory instanceof PanasonicRawIFD0Directory) {
            if (i3 == 19) {
                PanasonicRawWbInfoDirectory panasonicRawWbInfoDirectory = new PanasonicRawWbInfoDirectory();
                panasonicRawWbInfoDirectory.setParent(this._currentDirectory);
                this._metadata.addDirectory(panasonicRawWbInfoDirectory);
                processBinary(panasonicRawWbInfoDirectory, i, randomAccessReader, i4, false, 2);
                return true;
            }
            if (i3 == 39) {
                PanasonicRawWbInfo2Directory panasonicRawWbInfo2Directory = new PanasonicRawWbInfo2Directory();
                panasonicRawWbInfo2Directory.setParent(this._currentDirectory);
                this._metadata.addDirectory(panasonicRawWbInfo2Directory);
                processBinary(panasonicRawWbInfo2Directory, i, randomAccessReader, i4, false, 3);
                return true;
            }
            if (i3 == 281) {
                PanasonicRawDistortionDirectory panasonicRawDistortionDirectory = new PanasonicRawDistortionDirectory();
                panasonicRawDistortionDirectory.setParent(this._currentDirectory);
                this._metadata.addDirectory(panasonicRawDistortionDirectory);
                processBinary(panasonicRawDistortionDirectory, i, randomAccessReader, i4, true, 1);
                return true;
            }
        }
        if (i3 == 46 && (this._currentDirectory instanceof PanasonicRawIFD0Directory)) {
            try {
                for (Directory directory : JpegMetadataReader.readMetadata(new ByteArrayInputStream(randomAccessReader.getBytes(i, i4))).getDirectories()) {
                    directory.setParent(this._currentDirectory);
                    this._metadata.addDirectory(directory);
                }
                return true;
            } catch (JpegProcessingException e) {
                this._currentDirectory.addError("Error processing JpgFromRaw: " + e.getMessage());
            } catch (IOException e2) {
                this._currentDirectory.addError("Error reading JpgFromRaw: " + e2.getMessage());
            }
        }
        return false;
    }

    private static void processBinary(Directory directory, int i, RandomAccessReader randomAccessReader, int i2, Boolean bool, int i3) throws IOException {
        int i4 = 0;
        while (i4 < i2) {
            if (directory.hasTagName(i4)) {
                if (i4 < i2 - 1 && directory.hasTagName(i4 + 1)) {
                    if (bool.booleanValue()) {
                        directory.setObject(i4, Short.valueOf(randomAccessReader.getInt16((i4 * 2) + i)));
                    } else {
                        directory.setObject(i4, Integer.valueOf(randomAccessReader.getUInt16((i4 * 2) + i)));
                    }
                } else {
                    if (bool.booleanValue()) {
                        short[] sArr = new short[i3];
                        for (int i5 = 0; i5 < i3; i5++) {
                            sArr[i5] = randomAccessReader.getInt16(((i4 + i5) * 2) + i);
                        }
                        directory.setObjectArray(i4, sArr);
                    } else {
                        int[] iArr = new int[i3];
                        for (int i6 = 0; i6 < i3; i6++) {
                            iArr[i6] = randomAccessReader.getUInt16(((i4 + i6) * 2) + i);
                        }
                        directory.setObjectArray(i4, iArr);
                    }
                    i4 += i3 - 1;
                }
            }
            i4++;
        }
    }

    private static String getReaderString(RandomAccessReader randomAccessReader, int i, int i2) throws IOException {
        try {
            return randomAccessReader.getString(i, i2, Charsets.UTF_8);
        } catch (BufferBoundsException unused) {
            return "";
        }
    }

    private boolean processMakernote(int i, Set<Integer> set, int i2, RandomAccessReader randomAccessReader) throws Throwable {
        Directory firstDirectoryOfType = this._metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
        String string = firstDirectoryOfType == null ? null : firstDirectoryOfType.getString(271);
        String readerString = getReaderString(randomAccessReader, i, 2);
        String readerString2 = getReaderString(randomAccessReader, i, 3);
        String readerString3 = getReaderString(randomAccessReader, i, 4);
        String readerString4 = getReaderString(randomAccessReader, i, 5);
        String readerString5 = getReaderString(randomAccessReader, i, 6);
        String readerString6 = getReaderString(randomAccessReader, i, 7);
        String readerString7 = getReaderString(randomAccessReader, i, 8);
        String readerString8 = getReaderString(randomAccessReader, i, 9);
        String readerString9 = getReaderString(randomAccessReader, i, 10);
        String readerString10 = getReaderString(randomAccessReader, i, 12);
        boolean zIsMotorolaByteOrder = randomAccessReader.isMotorolaByteOrder();
        if ("OLYMP\u0000".equals(readerString5) || "EPSON".equals(readerString4) || "AGFA".equals(readerString3)) {
            pushDirectory(OlympusMakernoteDirectory.class);
            TiffReader.processIfd(this, randomAccessReader, set, i + 8, i2);
        } else if ("OLYMPUS\u0000II".equals(readerString9)) {
            pushDirectory(OlympusMakernoteDirectory.class);
            TiffReader.processIfd(this, randomAccessReader, set, i + 12, i);
        } else if (string != null && string.toUpperCase().startsWith("MINOLTA")) {
            pushDirectory(OlympusMakernoteDirectory.class);
            TiffReader.processIfd(this, randomAccessReader, set, i, i2);
        } else if (string != null && string.trim().toUpperCase().startsWith("NIKON")) {
            if ("Nikon".equals(readerString4)) {
                short uInt8 = randomAccessReader.getUInt8(i + 6);
                if (uInt8 == 1) {
                    pushDirectory(NikonType1MakernoteDirectory.class);
                    TiffReader.processIfd(this, randomAccessReader, set, i + 8, i2);
                } else if (uInt8 == 2) {
                    pushDirectory(NikonType2MakernoteDirectory.class);
                    TiffReader.processIfd(this, randomAccessReader, set, i + 18, i + 10);
                } else {
                    this._currentDirectory.addError("Unsupported Nikon makernote data ignored.");
                }
            } else {
                pushDirectory(NikonType2MakernoteDirectory.class);
                TiffReader.processIfd(this, randomAccessReader, set, i, i2);
            }
        } else if ("SONY CAM".equals(readerString7) || "SONY DSC".equals(readerString7)) {
            pushDirectory(SonyType1MakernoteDirectory.class);
            TiffReader.processIfd(this, randomAccessReader, set, i + 12, i2);
        } else if (string != null && string.startsWith("SONY") && !Arrays.equals(randomAccessReader.getBytes(i, 2), new byte[]{1, 0})) {
            pushDirectory(SonyType1MakernoteDirectory.class);
            TiffReader.processIfd(this, randomAccessReader, set, i, i2);
        } else if ("SEMC MS\u0000\u0000\u0000\u0000\u0000".equals(readerString10)) {
            randomAccessReader.setMotorolaByteOrder(true);
            pushDirectory(SonyType6MakernoteDirectory.class);
            TiffReader.processIfd(this, randomAccessReader, set, i + 20, i2);
        } else if ("SIGMA\u0000\u0000\u0000".equals(readerString7) || "FOVEON\u0000\u0000".equals(readerString7)) {
            pushDirectory(SigmaMakernoteDirectory.class);
            TiffReader.processIfd(this, randomAccessReader, set, i + 10, i2);
        } else if ("KDK".equals(readerString2)) {
            randomAccessReader.setMotorolaByteOrder(readerString6.equals("KDK INFO"));
            KodakMakernoteDirectory kodakMakernoteDirectory = new KodakMakernoteDirectory();
            this._metadata.addDirectory(kodakMakernoteDirectory);
            processKodakMakernote(kodakMakernoteDirectory, i, randomAccessReader);
        } else if ("Canon".equalsIgnoreCase(string)) {
            pushDirectory(CanonMakernoteDirectory.class);
            TiffReader.processIfd(this, randomAccessReader, set, i, i2);
        } else if (string != null && string.toUpperCase().startsWith("CASIO")) {
            if ("QVC\u0000\u0000\u0000".equals(readerString5)) {
                pushDirectory(CasioType2MakernoteDirectory.class);
                TiffReader.processIfd(this, randomAccessReader, set, i + 6, i2);
            } else {
                pushDirectory(CasioType1MakernoteDirectory.class);
                TiffReader.processIfd(this, randomAccessReader, set, i, i2);
            }
        } else if ("FUJIFILM".equals(readerString7) || "Fujifilm".equalsIgnoreCase(string)) {
            randomAccessReader.setMotorolaByteOrder(false);
            int int32 = randomAccessReader.getInt32(i + 8) + i;
            pushDirectory(FujifilmMakernoteDirectory.class);
            TiffReader.processIfd(this, randomAccessReader, set, int32, i);
        } else if ("KYOCERA".equals(readerString6)) {
            pushDirectory(KyoceraMakernoteDirectory.class);
            TiffReader.processIfd(this, randomAccessReader, set, i + 22, i2);
        } else if ("LEICA".equals(readerString4)) {
            randomAccessReader.setMotorolaByteOrder(false);
            if ("LEICA\u0000\u0001\u0000".equals(readerString7) || "LEICA\u0000\u0004\u0000".equals(readerString7) || "LEICA\u0000\u0005\u0000".equals(readerString7) || "LEICA\u0000\u0006\u0000".equals(readerString7) || "LEICA\u0000\u0007\u0000".equals(readerString7)) {
                pushDirectory(LeicaType5MakernoteDirectory.class);
                TiffReader.processIfd(this, randomAccessReader, set, i + 8, i);
            } else if ("Leica Camera AG".equals(string)) {
                pushDirectory(LeicaMakernoteDirectory.class);
                TiffReader.processIfd(this, randomAccessReader, set, i + 8, i2);
            } else {
                if (!"LEICA".equals(string)) {
                    return false;
                }
                pushDirectory(PanasonicMakernoteDirectory.class);
                TiffReader.processIfd(this, randomAccessReader, set, i + 8, i2);
            }
        } else if ("Panasonic\u0000\u0000\u0000".equals(readerString10)) {
            pushDirectory(PanasonicMakernoteDirectory.class);
            TiffReader.processIfd(this, randomAccessReader, set, i + 12, i2);
        } else if ("AOC\u0000".equals(readerString3)) {
            pushDirectory(CasioType2MakernoteDirectory.class);
            TiffReader.processIfd(this, randomAccessReader, set, i + 6, i);
        } else if (string != null && (string.toUpperCase().startsWith("PENTAX") || string.toUpperCase().startsWith("ASAHI"))) {
            pushDirectory(PentaxMakernoteDirectory.class);
            TiffReader.processIfd(this, randomAccessReader, set, i, i);
        } else if ("SANYO\u0000\u0001\u0000".equals(readerString7)) {
            pushDirectory(SanyoMakernoteDirectory.class);
            TiffReader.processIfd(this, randomAccessReader, set, i + 8, i);
        } else if (string != null && string.toLowerCase().startsWith("ricoh")) {
            if (readerString.equals("Rv") || readerString2.equals("Rev")) {
                return false;
            }
            if (readerString4.equalsIgnoreCase("Ricoh")) {
                randomAccessReader.setMotorolaByteOrder(true);
                pushDirectory(RicohMakernoteDirectory.class);
                TiffReader.processIfd(this, randomAccessReader, set, i + 8, i);
            }
        } else if (readerString9.equals("Apple iOS\u0000")) {
            boolean zIsMotorolaByteOrder2 = randomAccessReader.isMotorolaByteOrder();
            randomAccessReader.setMotorolaByteOrder(true);
            pushDirectory(AppleMakernoteDirectory.class);
            TiffReader.processIfd(this, randomAccessReader, set, i + 14, i);
            randomAccessReader.setMotorolaByteOrder(zIsMotorolaByteOrder2);
        } else if (randomAccessReader.getUInt16(i) == 61697) {
            ReconyxHyperFireMakernoteDirectory reconyxHyperFireMakernoteDirectory = new ReconyxHyperFireMakernoteDirectory();
            this._metadata.addDirectory(reconyxHyperFireMakernoteDirectory);
            processReconyxHyperFireMakernote(reconyxHyperFireMakernoteDirectory, i, randomAccessReader);
        } else if (readerString8.equalsIgnoreCase("RECONYXUF")) {
            ReconyxUltraFireMakernoteDirectory reconyxUltraFireMakernoteDirectory = new ReconyxUltraFireMakernoteDirectory();
            this._metadata.addDirectory(reconyxUltraFireMakernoteDirectory);
            processReconyxUltraFireMakernote(reconyxUltraFireMakernoteDirectory, i, randomAccessReader);
        } else {
            if (!"SAMSUNG".equals(string)) {
                return false;
            }
            pushDirectory(SamsungType2MakernoteDirectory.class);
            TiffReader.processIfd(this, randomAccessReader, set, i, i2);
        }
        randomAccessReader.setMotorolaByteOrder(zIsMotorolaByteOrder);
        return true;
    }

    private static boolean handlePrintIM(Directory directory, int i) {
        if (i == 50341) {
            return true;
        }
        if (i == 3584) {
            return (directory instanceof CasioType2MakernoteDirectory) || (directory instanceof KyoceraMakernoteDirectory) || (directory instanceof NikonType2MakernoteDirectory) || (directory instanceof OlympusMakernoteDirectory) || (directory instanceof PanasonicMakernoteDirectory) || (directory instanceof PentaxMakernoteDirectory) || (directory instanceof RicohMakernoteDirectory) || (directory instanceof SanyoMakernoteDirectory) || (directory instanceof SonyType1MakernoteDirectory);
        }
        return false;
    }

    private static void processPrintIM(PrintIMDirectory printIMDirectory, int i, RandomAccessReader randomAccessReader, int i2) throws IOException {
        int uInt16;
        Boolean boolValueOf;
        if (i2 == 0) {
            printIMDirectory.addError("Empty PrintIM data");
            return;
        }
        if (i2 <= 15) {
            printIMDirectory.addError("Bad PrintIM data");
            return;
        }
        String string = randomAccessReader.getString(i, 12, Charsets.UTF_8);
        if (!string.startsWith("PrintIM")) {
            printIMDirectory.addError("Invalid PrintIM header");
            return;
        }
        int i3 = i + 14;
        int uInt162 = randomAccessReader.getUInt16(i3);
        if (i2 < (uInt162 * 6) + 16) {
            boolValueOf = Boolean.valueOf(randomAccessReader.isMotorolaByteOrder());
            randomAccessReader.setMotorolaByteOrder(!randomAccessReader.isMotorolaByteOrder());
            uInt16 = randomAccessReader.getUInt16(i3);
            if (i2 < (uInt16 * 6) + 16) {
                printIMDirectory.addError("Bad PrintIM size");
                return;
            }
        } else {
            uInt16 = uInt162;
            boolValueOf = null;
        }
        String strSubstring = string.substring(8, 12);
        printIMDirectory.setObject(0, strSubstring);
        for (int i4 = 0; i4 < uInt16; i4++) {
            int i5 = i + 16 + (i4 * 6);
            printIMDirectory.setObject(randomAccessReader.getUInt16(i5), Long.valueOf(randomAccessReader.getUInt32(i5 + 2)));
        }
        if (boolValueOf != null) {
            randomAccessReader.setMotorolaByteOrder(boolValueOf.booleanValue());
        }
    }

    private static void processKodakMakernote(KodakMakernoteDirectory kodakMakernoteDirectory, int i, RandomAccessReader randomAccessReader) {
        try {
            kodakMakernoteDirectory.setStringValue(0, randomAccessReader.getStringValue(i + 8, 8, Charsets.UTF_8));
            kodakMakernoteDirectory.setInt(9, randomAccessReader.getUInt8(i + 17));
            kodakMakernoteDirectory.setInt(10, randomAccessReader.getUInt8(i + 18));
            kodakMakernoteDirectory.setInt(12, randomAccessReader.getUInt16(i + 20));
            kodakMakernoteDirectory.setInt(14, randomAccessReader.getUInt16(i + 22));
            kodakMakernoteDirectory.setInt(16, randomAccessReader.getUInt16(i + 24));
            kodakMakernoteDirectory.setByteArray(18, randomAccessReader.getBytes(i + 26, 2));
            kodakMakernoteDirectory.setByteArray(20, randomAccessReader.getBytes(i + 28, 4));
            kodakMakernoteDirectory.setInt(24, randomAccessReader.getUInt16(i + 32));
            kodakMakernoteDirectory.setInt(27, randomAccessReader.getUInt8(i + 35));
            kodakMakernoteDirectory.setInt(28, randomAccessReader.getUInt8(i + 36));
            kodakMakernoteDirectory.setInt(29, randomAccessReader.getUInt8(i + 37));
            kodakMakernoteDirectory.setInt(30, randomAccessReader.getUInt16(i + 38));
            kodakMakernoteDirectory.setLong(32, randomAccessReader.getUInt32(i + 40));
            kodakMakernoteDirectory.setInt(36, randomAccessReader.getInt16(i + 44));
            kodakMakernoteDirectory.setInt(56, randomAccessReader.getUInt8(i + 64));
            kodakMakernoteDirectory.setInt(64, randomAccessReader.getUInt8(i + 72));
            kodakMakernoteDirectory.setInt(92, randomAccessReader.getUInt8(i + 100));
            kodakMakernoteDirectory.setInt(93, randomAccessReader.getUInt8(i + 101));
            kodakMakernoteDirectory.setInt(94, randomAccessReader.getUInt16(i + 102));
            kodakMakernoteDirectory.setInt(96, randomAccessReader.getUInt16(i + 104));
            kodakMakernoteDirectory.setInt(98, randomAccessReader.getUInt16(i + 106));
            kodakMakernoteDirectory.setInt(100, randomAccessReader.getUInt16(i + 108));
            kodakMakernoteDirectory.setInt(102, randomAccessReader.getUInt16(i + LDSFile.EF_DG14_TAG));
            kodakMakernoteDirectory.setInt(104, randomAccessReader.getUInt16(i + 112));
            kodakMakernoteDirectory.setInt(107, randomAccessReader.getInt8(i + ISO781611.DISCRETIONARY_DATA_FOR_PAYLOAD_CONSTRUCTED_TAG));
        } catch (IOException e) {
            kodakMakernoteDirectory.addError("Error processing Kodak makernote data: " + e.getMessage());
        }
    }

    private static void processReconyxHyperFireMakernote(ReconyxHyperFireMakernoteDirectory reconyxHyperFireMakernoteDirectory, int i, RandomAccessReader randomAccessReader) throws IOException {
        Integer numValueOf;
        reconyxHyperFireMakernoteDirectory.setObject(0, Integer.valueOf(randomAccessReader.getUInt16(i)));
        int uInt16 = randomAccessReader.getUInt16(i + 2);
        int uInt162 = randomAccessReader.getUInt16(i + 4);
        int uInt163 = randomAccessReader.getUInt16(i + 6);
        String str = String.format("%04X", Integer.valueOf(randomAccessReader.getUInt16(i + 8))) + String.format("%04X", Integer.valueOf(randomAccessReader.getUInt16(i + 10)));
        try {
            numValueOf = Integer.valueOf(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            numValueOf = null;
        }
        if (numValueOf != null) {
            reconyxHyperFireMakernoteDirectory.setString(2, String.format("%d.%d.%d.%s", Integer.valueOf(uInt16), Integer.valueOf(uInt162), Integer.valueOf(uInt163), numValueOf));
        } else {
            reconyxHyperFireMakernoteDirectory.setString(2, String.format("%d.%d.%d", Integer.valueOf(uInt16), Integer.valueOf(uInt162), Integer.valueOf(uInt163)));
            reconyxHyperFireMakernoteDirectory.addError("Error processing Reconyx HyperFire makernote data: build '" + str + "' is not in the expected format and will be omitted from Firmware Version.");
        }
        reconyxHyperFireMakernoteDirectory.setString(12, String.valueOf((char) randomAccessReader.getUInt16(i + 12)));
        reconyxHyperFireMakernoteDirectory.setIntArray(14, new int[]{randomAccessReader.getUInt16(i + 14), randomAccessReader.getUInt16(i + 16)});
        reconyxHyperFireMakernoteDirectory.setInt(18, (randomAccessReader.getUInt16(i + 18) << 16) + randomAccessReader.getUInt16(i + 20));
        int uInt164 = randomAccessReader.getUInt16(i + 22);
        int uInt165 = randomAccessReader.getUInt16(i + 24);
        int uInt166 = randomAccessReader.getUInt16(i + 26);
        int uInt167 = randomAccessReader.getUInt16(i + 28);
        int uInt168 = randomAccessReader.getUInt16(i + 30);
        int uInt169 = randomAccessReader.getUInt16(i + 32);
        if (uInt164 >= 0 && uInt164 < 60 && uInt165 >= 0 && uInt165 < 60 && uInt166 >= 0 && uInt166 < 24 && uInt167 >= 1 && uInt167 < 13 && uInt168 >= 1 && uInt168 < 32 && uInt169 >= 1 && uInt169 <= 9999) {
            reconyxHyperFireMakernoteDirectory.setString(22, String.format("%4d:%2d:%2d %2d:%2d:%2d", Integer.valueOf(uInt169), Integer.valueOf(uInt167), Integer.valueOf(uInt168), Integer.valueOf(uInt166), Integer.valueOf(uInt165), Integer.valueOf(uInt164)));
        } else {
            reconyxHyperFireMakernoteDirectory.addError("Error processing Reconyx HyperFire makernote data: Date/Time Original " + uInt169 + "-" + uInt167 + "-" + uInt168 + StringUtils.SPACE + uInt166 + ":" + uInt165 + ":" + uInt164 + " is not a valid date/time.");
        }
        reconyxHyperFireMakernoteDirectory.setInt(36, randomAccessReader.getUInt16(i + 36));
        reconyxHyperFireMakernoteDirectory.setInt(38, randomAccessReader.getInt16(i + 38));
        reconyxHyperFireMakernoteDirectory.setInt(40, randomAccessReader.getInt16(i + 40));
        reconyxHyperFireMakernoteDirectory.setStringValue(42, new StringValue(randomAccessReader.getBytes(i + 42, 28), Charsets.UTF_16LE));
        reconyxHyperFireMakernoteDirectory.setInt(72, randomAccessReader.getUInt16(i + 72));
        reconyxHyperFireMakernoteDirectory.setInt(74, randomAccessReader.getUInt16(i + 74));
        reconyxHyperFireMakernoteDirectory.setInt(76, randomAccessReader.getUInt16(i + 76));
        reconyxHyperFireMakernoteDirectory.setInt(78, randomAccessReader.getUInt16(i + 78));
        reconyxHyperFireMakernoteDirectory.setInt(80, randomAccessReader.getUInt16(i + 80));
        reconyxHyperFireMakernoteDirectory.setInt(82, randomAccessReader.getUInt16(i + 82));
        reconyxHyperFireMakernoteDirectory.setDouble(84, randomAccessReader.getUInt16(i + 84) / 1000.0d);
        reconyxHyperFireMakernoteDirectory.setString(86, randomAccessReader.getNullTerminatedString(i + 86, 44, Charsets.UTF_8));
    }

    private static void processReconyxUltraFireMakernote(ReconyxUltraFireMakernoteDirectory reconyxUltraFireMakernoteDirectory, int i, RandomAccessReader randomAccessReader) throws IOException {
        reconyxUltraFireMakernoteDirectory.setString(0, randomAccessReader.getString(i, 9, Charsets.UTF_8));
        reconyxUltraFireMakernoteDirectory.setString(52, randomAccessReader.getString(i + 52, 1, Charsets.UTF_8));
        reconyxUltraFireMakernoteDirectory.setIntArray(53, new int[]{randomAccessReader.getByte(i + 53), randomAccessReader.getByte(i + 54)});
        randomAccessReader.getByte(i + 59);
        randomAccessReader.getByte(i + 60);
        randomAccessReader.getByte(i + 61);
        randomAccessReader.getByte(i + 62);
        randomAccessReader.getByte(i + 63);
        reconyxUltraFireMakernoteDirectory.setInt(67, randomAccessReader.getByte(i + 67));
        reconyxUltraFireMakernoteDirectory.setInt(72, randomAccessReader.getByte(i + 72));
        reconyxUltraFireMakernoteDirectory.setStringValue(75, new StringValue(randomAccessReader.getBytes(i + 75, 14), Charsets.UTF_8));
        reconyxUltraFireMakernoteDirectory.setString(80, randomAccessReader.getNullTerminatedString(i + 80, 20, Charsets.UTF_8));
    }
}
