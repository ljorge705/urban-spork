package com.drew.imaging;

import com.drew.imaging.avi.AviMetadataReader;
import com.drew.imaging.bmp.BmpMetadataReader;
import com.drew.imaging.eps.EpsMetadataReader;
import com.drew.imaging.gif.GifMetadataReader;
import com.drew.imaging.heif.HeifMetadataReader;
import com.drew.imaging.ico.IcoMetadataReader;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.mp3.Mp3MetadataReader;
import com.drew.imaging.mp4.Mp4MetadataReader;
import com.drew.imaging.pcx.PcxMetadataReader;
import com.drew.imaging.png.PngMetadataReader;
import com.drew.imaging.psd.PsdMetadataReader;
import com.drew.imaging.quicktime.QuickTimeMetadataReader;
import com.drew.imaging.raf.RafMetadataReader;
import com.drew.imaging.tiff.TiffMetadataReader;
import com.drew.imaging.wav.WavMetadataReader;
import com.drew.imaging.webp.WebpMetadataReader;
import com.drew.lang.RandomAccessStreamReader;
import com.drew.lang.StringUtil;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.file.FileSystemMetadataReader;
import com.drew.metadata.file.FileTypeDirectory;
import com.drew.metadata.xmp.XmpDirectory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
public class ImageMetadataReader {
    public static Metadata readMetadata(InputStream inputStream) throws ImageProcessingException, IOException {
        return readMetadata(inputStream, -1L);
    }

    public static Metadata readMetadata(InputStream inputStream, long j) throws ImageProcessingException, IOException {
        BufferedInputStream bufferedInputStream = inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream);
        FileType fileTypeDetectFileType = FileTypeDetector.detectFileType(bufferedInputStream);
        Metadata metadata = readMetadata(bufferedInputStream, j, fileTypeDetectFileType);
        metadata.addDirectory(new FileTypeDirectory(fileTypeDetectFileType));
        return metadata;
    }

    /* renamed from: com.drew.imaging.ImageMetadataReader$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$drew$imaging$FileType;

        static {
            int[] iArr = new int[FileType.values().length];
            $SwitchMap$com$drew$imaging$FileType = iArr;
            try {
                iArr[FileType.Jpeg.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Tiff.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Arw.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Cr2.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Nef.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Orf.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Rw2.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Psd.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Png.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Bmp.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Gif.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Ico.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Pcx.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.WebP.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Raf.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Avi.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Wav.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Mov.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Mp4.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Mp3.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Eps.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Heif.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$drew$imaging$FileType[FileType.Unknown.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
        }
    }

    public static Metadata readMetadata(InputStream inputStream, long j, FileType fileType) throws ImageProcessingException, IOException {
        switch (AnonymousClass1.$SwitchMap$com$drew$imaging$FileType[fileType.ordinal()]) {
            case 1:
                return JpegMetadataReader.readMetadata(inputStream);
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return TiffMetadataReader.readMetadata(new RandomAccessStreamReader(inputStream, 2048, j));
            case 8:
                return PsdMetadataReader.readMetadata(inputStream);
            case 9:
                return PngMetadataReader.readMetadata(inputStream);
            case 10:
                return BmpMetadataReader.readMetadata(inputStream);
            case 11:
                return GifMetadataReader.readMetadata(inputStream);
            case 12:
                return IcoMetadataReader.readMetadata(inputStream);
            case 13:
                return PcxMetadataReader.readMetadata(inputStream);
            case 14:
                return WebpMetadataReader.readMetadata(inputStream);
            case 15:
                return RafMetadataReader.readMetadata(inputStream);
            case 16:
                return AviMetadataReader.readMetadata(inputStream);
            case 17:
                return WavMetadataReader.readMetadata(inputStream);
            case 18:
                return QuickTimeMetadataReader.readMetadata(inputStream);
            case 19:
                return Mp4MetadataReader.readMetadata(inputStream);
            case 20:
                return Mp3MetadataReader.readMetadata(inputStream);
            case 21:
                return EpsMetadataReader.readMetadata(inputStream);
            case 22:
                return HeifMetadataReader.readMetadata(inputStream);
            case 23:
                throw new ImageProcessingException("File format could not be determined");
            default:
                return new Metadata();
        }
    }

    public static Metadata readMetadata(File file) throws ImageProcessingException, IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            Metadata metadata = readMetadata(fileInputStream, file.length());
            fileInputStream.close();
            new FileSystemMetadataReader().read(file, metadata);
            return metadata;
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    private ImageMetadataReader() throws Exception {
        throw new Exception("Not intended for instantiation");
    }

    public static void main(String[] strArr) throws MetadataException, IOException {
        Metadata metadata;
        ArrayList<String> arrayList = new ArrayList(Arrays.asList(strArr));
        boolean zRemove = arrayList.remove("-markdown");
        boolean zRemove2 = arrayList.remove("-hex");
        int i = 1;
        if (arrayList.size() < 1) {
            String implementationVersion = ImageMetadataReader.class.getPackage().getImplementationVersion();
            System.out.println("metadata-extractor version " + implementationVersion);
            System.out.println();
            PrintStream printStream = System.out;
            Object[] objArr = new Object[1];
            if (implementationVersion == null) {
                implementationVersion = "a.b.c";
            }
            objArr[0] = implementationVersion;
            printStream.println(String.format("Usage: java -jar metadata-extractor-%s.jar <filename> [<filename>] [-thumb] [-markdown] [-hex]", objArr));
            System.exit(1);
        }
        for (String str : arrayList) {
            long jNanoTime = System.nanoTime();
            File file = new File(str);
            if (!zRemove && arrayList.size() > i) {
                System.out.printf("\n***** PROCESSING: %s%n%n", str);
            }
            try {
                metadata = readMetadata(file);
            } catch (Exception e) {
                e.printStackTrace(System.err);
                System.exit(i);
                metadata = null;
            }
            long jNanoTime2 = System.nanoTime() - jNanoTime;
            if (!zRemove) {
                System.out.printf("Processed %.3f MB file in %.2f ms%n%n", Double.valueOf(file.length() / 1048576.0d), Double.valueOf(jNanoTime2 / 1000000.0d));
            }
            if (zRemove) {
                String name = file.getName();
                String strUrlEncode = StringUtil.urlEncode(str);
                ExifIFD0Directory exifIFD0Directory = (ExifIFD0Directory) metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
                String string = exifIFD0Directory == null ? "" : exifIFD0Directory.getString(271);
                String string2 = exifIFD0Directory != null ? exifIFD0Directory.getString(272) : "";
                System.out.println();
                System.out.println("---");
                System.out.println();
                System.out.printf("# %s - %s%n", string, string2);
                System.out.println();
                System.out.printf("<a href=\"https://raw.githubusercontent.com/drewnoakes/metadata-extractor-images/master/%s\">%n", strUrlEncode);
                System.out.printf("<img src=\"https://raw.githubusercontent.com/drewnoakes/metadata-extractor-images/master/%s\" width=\"300\"/><br/>%n", strUrlEncode);
                System.out.println(name);
                System.out.println("</a>");
                System.out.println();
                System.out.println("Directory | Tag Id | Tag Name | Extracted Value");
                System.out.println(":--------:|-------:|----------|----------------");
            }
            for (Directory directory : metadata.getDirectories()) {
                String name2 = directory.getName();
                for (Tag tag : directory.getTags()) {
                    String tagName = tag.getTagName();
                    String description = tag.getDescription();
                    if (description != null && description.length() > 1024) {
                        description = description.substring(0, 1024) + "...";
                    }
                    if (zRemove) {
                        System.out.printf("%s|0x%s|%s|%s%n", name2, Integer.toHexString(tag.getTagType()), tagName, description);
                    } else if (zRemove2) {
                        System.out.printf("[%s - %s] %s = %s%n", name2, tag.getTagTypeHex(), tagName, description);
                    } else {
                        System.out.printf("[%s] %s = %s%n", name2, tagName, description);
                    }
                }
                if (directory instanceof XmpDirectory) {
                    for (Map.Entry<String, String> entry : ((XmpDirectory) directory).getXmpProperties().entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (value != null && value.length() > 1024) {
                            value = value.substring(0, 1024) + "...";
                        }
                        if (zRemove) {
                            System.out.printf("%s||%s|%s%n", name2, key, value);
                        } else {
                            System.out.printf("[%s] %s = %s%n", name2, key, value);
                        }
                    }
                }
                Iterator<String> it = directory.getErrors().iterator();
                while (it.hasNext()) {
                    System.err.println("ERROR: " + it.next());
                }
                i = 1;
            }
        }
    }
}
