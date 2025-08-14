package com.drew.tools;

import com.clevertap.android.sdk.Constants;
import com.drew.imaging.FileTypeDetector;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.lang.StringUtil;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifDirectoryBase;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.ExifThumbnailDirectory;
import io.sentry.rrweb.RRWebVideoEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class ProcessAllImagesInFolderUtility {

    interface FileHandler {
        void onBeforeExtraction(File file, PrintStream printStream, String str);

        void onExtractionError(File file, Throwable th, PrintStream printStream);

        void onExtractionSuccess(File file, Metadata metadata, String str, PrintStream printStream);

        void onScanCompleted(PrintStream printStream);

        void onStartingDirectory(File file);

        boolean shouldProcess(File file);
    }

    public static void main(String[] strArr) throws IOException, JpegProcessingException {
        ArrayList arrayList = new ArrayList();
        PrintStream printStream = System.out;
        FileHandler basicFileHandler = null;
        int i = 0;
        while (i < strArr.length) {
            String str = strArr[i];
            if (str.equalsIgnoreCase("--text")) {
                basicFileHandler = new TextFileOutputHandler();
            } else if (str.equalsIgnoreCase("--markdown")) {
                basicFileHandler = new MarkdownTableOutputHandler();
            } else if (str.equalsIgnoreCase("--unknown")) {
                basicFileHandler = new UnknownTagHandler();
            } else if (str.equalsIgnoreCase("--log-file")) {
                if (i == strArr.length - 1) {
                    printUsage();
                    System.exit(1);
                }
                i++;
                printStream = new PrintStream((OutputStream) new FileOutputStream(strArr[i], false), true);
            } else {
                arrayList.add(str);
            }
            i++;
        }
        if (arrayList.isEmpty()) {
            System.err.println("Expects one or more directories as arguments.");
            printUsage();
            System.exit(1);
        }
        if (basicFileHandler == null) {
            basicFileHandler = new BasicFileHandler();
        }
        long jNanoTime = System.nanoTime();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            processDirectory(new File((String) it.next()), basicFileHandler, "", printStream);
        }
        basicFileHandler.onScanCompleted(printStream);
        System.out.println(String.format("Completed in %d ms", Long.valueOf((System.nanoTime() - jNanoTime) / 1000000)));
        if (printStream != System.out) {
            printStream.close();
        }
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println();
        System.out.println("  java com.drew.tools.ProcessAllImagesInFolderUtility [--text|--markdown|--unknown] [--log-file <file-name>]");
    }

    private static void processDirectory(File file, FileHandler fileHandler, String str, PrintStream printStream) {
        fileHandler.onStartingDirectory(file);
        String[] list = file.list();
        if (list == null) {
            return;
        }
        Arrays.sort(list);
        for (String str2 : list) {
            File file2 = new File(file, str2);
            if (file2.isDirectory()) {
                if (str.length() != 0) {
                    str2 = str + "/" + str2;
                }
                processDirectory(file2, fileHandler, str2, printStream);
            } else if (fileHandler.shouldProcess(file2)) {
                fileHandler.onBeforeExtraction(file2, printStream, str);
                try {
                    fileHandler.onExtractionSuccess(file2, ImageMetadataReader.readMetadata(file2), str, printStream);
                } catch (Throwable th) {
                    fileHandler.onExtractionError(file2, th, printStream);
                }
            }
        }
    }

    static abstract class FileHandlerBase implements FileHandler {
        private final Set<String> _supportedExtensions = new HashSet(Arrays.asList("jpg", "jpeg", "png", "gif", "bmp", Constants.NOTIF_ICON, "webp", "pcx", "ai", "eps", "nef", "crw", "cr2", "orf", "arw", "raf", "srw", "x3f", "rw2", "rwl", "tif", "tiff", "psd", "dng", "3g2", "3gp", "m4v", "mov", RRWebVideoEvent.REPLAY_CONTAINER, "pbm", "pnm", "pgm"));
        private int _processedFileCount = 0;
        private int _exceptionCount = 0;
        private int _errorCount = 0;
        private long _processedByteCount = 0;

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onStartingDirectory(File file) {
        }

        FileHandlerBase() {
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public boolean shouldProcess(File file) {
            String extension = getExtension(file);
            return extension != null && this._supportedExtensions.contains(extension.toLowerCase());
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onBeforeExtraction(File file, PrintStream printStream, String str) {
            this._processedFileCount++;
            this._processedByteCount += file.length();
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onExtractionError(File file, Throwable th, PrintStream printStream) {
            this._exceptionCount++;
            printStream.printf("\t[%s] %s\n", th.getClass().getName(), th.getMessage());
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onExtractionSuccess(File file, Metadata metadata, String str, PrintStream printStream) {
            if (metadata.hasErrors()) {
                printStream.print(file);
                printStream.print('\n');
                for (Directory directory : metadata.getDirectories()) {
                    if (directory.hasErrors()) {
                        Iterator<String> it = directory.getErrors().iterator();
                        while (it.hasNext()) {
                            printStream.printf("\t[%s] %s\n", directory.getName(), it.next());
                            this._errorCount++;
                        }
                    }
                }
            }
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onScanCompleted(PrintStream printStream) {
            int i = this._processedFileCount;
            if (i > 0) {
                printStream.print(String.format("Processed %,d files (%,d bytes) with %,d exceptions and %,d file errors\n", Integer.valueOf(i), Long.valueOf(this._processedByteCount), Integer.valueOf(this._exceptionCount), Integer.valueOf(this._errorCount)));
            }
        }

        protected String getExtension(File file) {
            String name = file.getName();
            int iLastIndexOf = name.lastIndexOf(46);
            if (iLastIndexOf == -1 || iLastIndexOf == name.length() - 1) {
                return null;
            }
            return name.substring(iLastIndexOf + 1);
        }
    }

    static class TextFileOutputHandler extends FileHandlerBase {
        private static final String NEW_LINE = "\n";

        TextFileOutputHandler() {
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandlerBase, com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onStartingDirectory(File file) {
            super.onStartingDirectory(file);
            File file2 = new File(file + "/metadata");
            if (file2.exists()) {
                deleteRecursively(file2);
            }
        }

        private static void deleteRecursively(File file) {
            String[] list;
            if (!file.isDirectory()) {
                throw new IllegalArgumentException("Must be a directory.");
            }
            if (file.exists() && (list = file.list()) != null) {
                for (String str : list) {
                    File file2 = new File(str);
                    if (file2.isDirectory()) {
                        deleteRecursively(file2);
                    } else {
                        file2.delete();
                    }
                }
            }
            file.delete();
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandlerBase, com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onBeforeExtraction(File file, PrintStream printStream, String str) {
            super.onBeforeExtraction(file, printStream, str);
            printStream.print(file.getAbsoluteFile());
            printStream.print("\n");
        }

        /* JADX WARN: Removed duplicated region for block: B:112:0x015f A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:76:0x015a A[Catch: all -> 0x016f, TryCatch #7 {all -> 0x016f, blocks: (B:4:0x0007, B:7:0x0013, B:8:0x001b, B:10:0x0021, B:13:0x002e, B:14:0x0036, B:16:0x003c, B:17:0x0054, B:18:0x0057, B:19:0x005f, B:21:0x0065, B:22:0x0078, B:25:0x0081, B:26:0x008c, B:34:0x00af, B:36:0x00b3, B:39:0x00bb, B:30:0x0094, B:40:0x00d2, B:42:0x00d8, B:43:0x00db, B:45:0x00df, B:46:0x00e5, B:48:0x00f3, B:50:0x00f9, B:61:0x0135, B:63:0x0140, B:74:0x0155, B:76:0x015a, B:57:0x0115, B:60:0x011d, B:79:0x0162), top: B:102:0x0007, inners: #6 }] */
        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandlerBase, com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onExtractionSuccess(java.io.File r18, com.drew.metadata.Metadata r19, java.lang.String r20, java.io.PrintStream r21) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 382
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.drew.tools.ProcessAllImagesInFolderUtility.TextFileOutputHandler.onExtractionSuccess(java.io.File, com.drew.metadata.Metadata, java.lang.String, java.io.PrintStream):void");
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x002d A[LOOP:1: B:14:0x0029->B:16:0x002d, LOOP_END] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static void writeHierarchyLevel(com.drew.metadata.Metadata r4, java.io.PrintWriter r5, com.drew.metadata.Directory r6, int r7) {
            /*
                java.lang.Iterable r0 = r4.getDirectories()
                java.util.Iterator r0 = r0.iterator()
            L8:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L4c
                java.lang.Object r1 = r0.next()
                com.drew.metadata.Directory r1 = (com.drew.metadata.Directory) r1
                if (r6 != 0) goto L1d
                com.drew.metadata.Directory r2 = r1.getParent()
                if (r2 == 0) goto L28
                goto L8
            L1d:
                com.drew.metadata.Directory r2 = r1.getParent()
                boolean r2 = r6.equals(r2)
                if (r2 != 0) goto L28
                goto L8
            L28:
                r2 = 0
            L29:
                int r3 = r7 * 4
                if (r2 >= r3) goto L35
                r3 = 32
                r5.write(r3)
                int r2 = r2 + 1
                goto L29
            L35:
                java.lang.String r2 = "- "
                r5.write(r2)
                java.lang.String r2 = r1.getName()
                r5.write(r2)
                java.lang.String r2 = "\n"
                r5.write(r2)
                int r2 = r7 + 1
                writeHierarchyLevel(r4, r5, r1, r2)
                goto L8
            L4c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.drew.tools.ProcessAllImagesInFolderUtility.TextFileOutputHandler.writeHierarchyLevel(com.drew.metadata.Metadata, java.io.PrintWriter, com.drew.metadata.Directory, int):void");
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandlerBase, com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onExtractionError(File file, Throwable th, PrintStream printStream) throws Throwable {
            PrintWriter printWriterOpenWriter;
            super.onExtractionError(file, th, printStream);
            try {
                try {
                    printWriterOpenWriter = openWriter(file);
                    try {
                        printWriterOpenWriter.write("EXCEPTION: " + th.getMessage() + "\n");
                        printWriterOpenWriter.write("\n");
                        closeWriter(printWriterOpenWriter);
                    } catch (Throwable th2) {
                        th = th2;
                        closeWriter(printWriterOpenWriter);
                        throw th;
                    }
                } catch (IOException e) {
                    printStream.printf("IO exception writing metadata file: %s%s", e.getMessage(), "\n");
                }
            } catch (Throwable th3) {
                th = th3;
                printWriterOpenWriter = null;
            }
        }

        private static PrintWriter openWriter(File file) throws Throwable {
            File file2 = new File(String.format("%s/metadata", file.getParent()));
            if (!file2.exists()) {
                file2.mkdir();
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(String.format("%s/metadata/%s.txt", file.getParent(), file.getName())), "UTF-8");
            outputStreamWriter.write("FILE: " + file.getName() + "\n");
            BufferedInputStream bufferedInputStream = null;
            try {
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                try {
                    outputStreamWriter.write(String.format("TYPE: %s\n", FileTypeDetector.detectFileType(bufferedInputStream2).toString().toUpperCase()));
                    outputStreamWriter.write("\n");
                    bufferedInputStream2.close();
                    return new PrintWriter(outputStreamWriter);
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }

        private static void closeWriter(Writer writer) throws IOException {
            if (writer != null) {
                writer.write("Generated using metadata-extractor\n");
                writer.write("https://drewnoakes.com/code/exif/\n");
                writer.flush();
                writer.close();
            }
        }
    }

    static class MarkdownTableOutputHandler extends FileHandlerBase {
        private final Map<String, String> _extensionEquivalence;
        private final Map<String, List<Row>> _rowListByExtension;

        static class Row {
            private String exifVersion;
            final File file;
            private String makernote;
            private String manufacturer;
            final Metadata metadata;
            private String model;
            final String relativePath;
            private String thumbnail;

            Row(File file, Metadata metadata, String str) {
                boolean zContainsTag;
                this.file = file;
                this.metadata = metadata;
                this.relativePath = str;
                ExifIFD0Directory exifIFD0Directory = (ExifIFD0Directory) metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
                ExifSubIFDDirectory exifSubIFDDirectory = (ExifSubIFDDirectory) metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
                ExifThumbnailDirectory exifThumbnailDirectory = (ExifThumbnailDirectory) metadata.getFirstDirectoryOfType(ExifThumbnailDirectory.class);
                if (exifIFD0Directory != null) {
                    this.manufacturer = exifIFD0Directory.getDescription(271);
                    this.model = exifIFD0Directory.getDescription(272);
                }
                if (exifSubIFDDirectory != null) {
                    this.exifVersion = exifSubIFDDirectory.getDescription(ExifDirectoryBase.TAG_EXIF_VERSION);
                    zContainsTag = exifSubIFDDirectory.containsTag(ExifDirectoryBase.TAG_MAKERNOTE);
                } else {
                    zContainsTag = false;
                }
                if (exifThumbnailDirectory != null) {
                    Integer integer = exifThumbnailDirectory.getInteger(256);
                    Integer integer2 = exifThumbnailDirectory.getInteger(257);
                    this.thumbnail = (integer == null || integer2 == null) ? "Yes" : String.format("Yes (%s x %s)", integer, integer2);
                }
                Iterator<Directory> it = metadata.getDirectories().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Directory next = it.next();
                    if (next.getClass().getName().contains("Makernote")) {
                        this.makernote = next.getName().replace("Makernote", "").trim();
                        break;
                    }
                }
                if (this.makernote == null) {
                    this.makernote = zContainsTag ? "(Unknown)" : "N/A";
                }
            }
        }

        public MarkdownTableOutputHandler() {
            HashMap map = new HashMap();
            this._extensionEquivalence = map;
            this._rowListByExtension = new HashMap();
            map.put("jpeg", "jpg");
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandlerBase, com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onExtractionSuccess(File file, Metadata metadata, String str, PrintStream printStream) {
            super.onExtractionSuccess(file, metadata, str, printStream);
            String extension = getExtension(file);
            if (extension == null) {
                return;
            }
            String lowerCase = extension.toLowerCase();
            if (this._extensionEquivalence.containsKey(lowerCase)) {
                lowerCase = this._extensionEquivalence.get(lowerCase);
            }
            List<Row> arrayList = this._rowListByExtension.get(lowerCase);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this._rowListByExtension.put(lowerCase, arrayList);
            }
            arrayList.add(new Row(file, metadata, str));
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0042 -> B:37:0x0045). Please report as a decompilation issue!!! */
        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandlerBase, com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onScanCompleted(PrintStream printStream) throws Throwable {
            PrintStream printStream2;
            FileOutputStream fileOutputStream;
            IOException e;
            super.onScanCompleted(printStream);
            try {
                try {
                    try {
                        fileOutputStream = new FileOutputStream("../wiki/ImageDatabaseSummary.md", false);
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        printStream2 = new PrintStream((OutputStream) fileOutputStream, false);
                        try {
                            writeOutput(printStream2);
                            printStream2.flush();
                            printStream2.close();
                            fileOutputStream.close();
                        } catch (IOException e2) {
                            e = e2;
                            e.printStackTrace();
                            if (printStream2 != null) {
                                printStream2.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        }
                    } catch (IOException e3) {
                        printStream2 = null;
                        e = e3;
                    } catch (Throwable th2) {
                        printStream2 = null;
                        th = th2;
                        if (printStream2 != null) {
                            printStream2.close();
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e5) {
                    printStream2 = null;
                    e = e5;
                    fileOutputStream = null;
                } catch (Throwable th3) {
                    printStream2 = null;
                    th = th3;
                    fileOutputStream = null;
                }
            } catch (IOException e6) {
                e6.printStackTrace();
            }
        }

        private void writeOutput(PrintStream printStream) throws IOException {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(printStream);
            outputStreamWriter.write("# Image Database Summary\n\n");
            for (Map.Entry<String, List<Row>> entry : this._rowListByExtension.entrySet()) {
                outputStreamWriter.write("## " + entry.getKey().toUpperCase() + " Files\n\n");
                outputStreamWriter.write("File|Manufacturer|Model|Dir Count|Exif?|Makernote|Thumbnail|All Data\n");
                outputStreamWriter.write("----|------------|-----|---------|-----|---------|---------|--------\n");
                List<Row> value = entry.getValue();
                Collections.sort(value, new Comparator<Row>() { // from class: com.drew.tools.ProcessAllImagesInFolderUtility.MarkdownTableOutputHandler.1
                    @Override // java.util.Comparator
                    public int compare(Row row, Row row2) {
                        int iCompare = StringUtil.compare(row.manufacturer, row2.manufacturer);
                        return iCompare != 0 ? iCompare : StringUtil.compare(row.model, row2.model);
                    }
                });
                for (Row row : value) {
                    Object[] objArr = new Object[11];
                    objArr[0] = row.file.getName();
                    objArr[1] = row.relativePath;
                    objArr[2] = StringUtil.urlEncode(row.file.getName());
                    String str = "";
                    objArr[3] = row.manufacturer == null ? "" : row.manufacturer;
                    objArr[4] = row.model == null ? "" : row.model;
                    objArr[5] = Integer.valueOf(row.metadata.getDirectoryCount());
                    objArr[6] = row.exifVersion == null ? "" : row.exifVersion;
                    objArr[7] = row.makernote == null ? "" : row.makernote;
                    if (row.thumbnail != null) {
                        str = row.thumbnail;
                    }
                    objArr[8] = str;
                    objArr[9] = row.relativePath;
                    objArr[10] = StringUtil.urlEncode(row.file.getName()).toLowerCase();
                    outputStreamWriter.write(String.format("[%s](https://raw.githubusercontent.com/drewnoakes/metadata-extractor-images/master/%s/%s)|%s|%s|%d|%s|%s|%s|[metadata](https://raw.githubusercontent.com/drewnoakes/metadata-extractor-images/master/%s/metadata/%s.txt)\n", objArr));
                }
                outputStreamWriter.write(10);
            }
            outputStreamWriter.flush();
        }
    }

    static class UnknownTagHandler extends FileHandlerBase {
        private HashMap<String, HashMap<Integer, Integer>> _occurrenceCountByTagByDirectory = new HashMap<>();

        UnknownTagHandler() {
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandlerBase, com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onExtractionSuccess(File file, Metadata metadata, String str, PrintStream printStream) {
            super.onExtractionSuccess(file, metadata, str, printStream);
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    if (!tag.hasTagName()) {
                        HashMap<Integer, Integer> map = this._occurrenceCountByTagByDirectory.get(directory.getName());
                        if (map == null) {
                            map = new HashMap<>();
                            this._occurrenceCountByTagByDirectory.put(directory.getName(), map);
                        }
                        Integer num = map.get(Integer.valueOf(tag.getTagType()));
                        if (num == null) {
                            map.put(Integer.valueOf(tag.getTagType()), 0);
                            num = 0;
                        }
                        map.put(Integer.valueOf(tag.getTagType()), Integer.valueOf(num.intValue() + 1));
                    }
                }
            }
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandlerBase, com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onScanCompleted(PrintStream printStream) {
            super.onScanCompleted(printStream);
            for (Map.Entry<String, HashMap<Integer, Integer>> entry : this._occurrenceCountByTagByDirectory.entrySet()) {
                String key = entry.getKey();
                ArrayList<Map.Entry> arrayList = new ArrayList(entry.getValue().entrySet());
                Collections.sort(arrayList, new Comparator<Map.Entry<Integer, Integer>>() { // from class: com.drew.tools.ProcessAllImagesInFolderUtility.UnknownTagHandler.1
                    @Override // java.util.Comparator
                    public int compare(Map.Entry<Integer, Integer> entry2, Map.Entry<Integer, Integer> entry3) {
                        return entry3.getValue().compareTo(entry2.getValue());
                    }
                });
                for (Map.Entry entry2 : arrayList) {
                    printStream.format("%s, 0x%04X, %d\n", key, (Integer) entry2.getKey(), (Integer) entry2.getValue());
                }
            }
        }
    }

    static class BasicFileHandler extends FileHandlerBase {
        BasicFileHandler() {
        }

        @Override // com.drew.tools.ProcessAllImagesInFolderUtility.FileHandlerBase, com.drew.tools.ProcessAllImagesInFolderUtility.FileHandler
        public void onExtractionSuccess(File file, Metadata metadata, String str, PrintStream printStream) {
            super.onExtractionSuccess(file, metadata, str, printStream);
            for (Directory directory : metadata.getDirectories()) {
                directory.getName();
                for (Tag tag : directory.getTags()) {
                    tag.getTagName();
                    tag.getDescription();
                }
            }
        }
    }
}
