package com.ReactNativeBlobUtil;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import com.ReactNativeBlobUtil.Utils.FileDescription;
import com.facebook.react.bridge.ReactApplicationContext;
import java.io.File;
import java.io.IOException;

/* loaded from: classes5.dex */
public class ReactNativeBlobUtilMediaCollection {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public enum MediaType {
        Audio,
        Image,
        Video,
        Download
    }

    private static Uri getMediaUri(MediaType mediaType) {
        if (mediaType == MediaType.Audio) {
            if (Build.VERSION.SDK_INT >= 29) {
                return MediaStore.Audio.Media.getContentUri("external_primary");
            }
            return MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        }
        if (mediaType == MediaType.Video) {
            if (Build.VERSION.SDK_INT >= 29) {
                return MediaStore.Video.Media.getContentUri("external_primary");
            }
            return MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        }
        if (mediaType == MediaType.Image) {
            if (Build.VERSION.SDK_INT >= 29) {
                return MediaStore.Images.Media.getContentUri("external_primary");
            }
            return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        if (mediaType != MediaType.Download || Build.VERSION.SDK_INT < 29) {
            return null;
        }
        return MediaStore.Downloads.getContentUri("external_primary");
    }

    private static String getRelativePath(MediaType mediaType, ReactApplicationContext reactApplicationContext) {
        return Build.VERSION.SDK_INT >= 29 ? mediaType == MediaType.Audio ? Environment.DIRECTORY_MUSIC : mediaType == MediaType.Video ? Environment.DIRECTORY_MOVIES : mediaType == MediaType.Image ? Environment.DIRECTORY_PICTURES : mediaType == MediaType.Download ? Environment.DIRECTORY_DOWNLOADS : Environment.DIRECTORY_DOWNLOADS : mediaType == MediaType.Audio ? ReactNativeBlobUtilFS.getLegacySystemfolders(reactApplicationContext).get("LegacyMusicDir").toString() : mediaType == MediaType.Video ? ReactNativeBlobUtilFS.getLegacySystemfolders(reactApplicationContext).get("LegacyMovieDir").toString() : mediaType == MediaType.Image ? ReactNativeBlobUtilFS.getLegacySystemfolders(reactApplicationContext).get("LegacyPictureDir").toString() : mediaType == MediaType.Download ? ReactNativeBlobUtilFS.getLegacySystemfolders(reactApplicationContext).get("LegacyDownloadDir").toString() : ReactNativeBlobUtilFS.getLegacySystemfolders(reactApplicationContext).get("LegacyDownloadDir").toString();
    }

    public static Uri createNewMediaFile(FileDescription fileDescription, MediaType mediaType, ReactApplicationContext reactApplicationContext) {
        ContentResolver contentResolver = ReactNativeBlobUtilImpl.RCTContext.getApplicationContext().getContentResolver();
        ContentValues contentValues = new ContentValues();
        String relativePath = getRelativePath(mediaType, reactApplicationContext);
        String str = fileDescription.mimeType;
        if (Build.VERSION.SDK_INT >= 29) {
            contentValues.put("date_added", Long.valueOf(System.currentTimeMillis() / 1000));
            contentValues.put("date_modified", Long.valueOf(System.currentTimeMillis() / 1000));
            contentValues.put("mime_type", str);
            contentValues.put("_display_name", fileDescription.name);
            contentValues.put("relative_path", relativePath + '/' + fileDescription.partentFolder);
            return contentResolver.insert(getMediaUri(mediaType), contentValues);
        }
        File file = new File(relativePath + fileDescription.getFullPath());
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                return null;
            }
            try {
                file.createNewFile();
                return Uri.fromFile(file);
            } catch (IOException unused) {
                return null;
            }
        }
        return Uri.fromFile(file);
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00d3 A[Catch: IOException -> 0x00d7, TryCatch #1 {IOException -> 0x00d7, blocks: (B:4:0x000b, B:26:0x009e, B:33:0x00ad, B:50:0x00d3, B:51:0x00d6, B:45:0x00cb), top: B:56:0x000b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean writeToMediaFile(android.net.Uri r8, java.lang.String r9, boolean r10, com.facebook.react.bridge.Promise r11, com.facebook.react.bridge.ReactApplicationContext r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 239
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ReactNativeBlobUtil.ReactNativeBlobUtilMediaCollection.writeToMediaFile(android.net.Uri, java.lang.String, boolean, com.facebook.react.bridge.Promise, com.facebook.react.bridge.ReactApplicationContext):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:80:0x00e9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00df A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:? A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x0098 -> B:73:0x00d6). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void copyToInternal(android.net.Uri r6, java.lang.String r7, com.facebook.react.bridge.Promise r8) {
        /*
            Method dump skipped, instructions count: 242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ReactNativeBlobUtil.ReactNativeBlobUtilMediaCollection.copyToInternal(android.net.Uri, java.lang.String, com.facebook.react.bridge.Promise):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void getBlob(android.net.Uri r5, java.lang.String r6, com.facebook.react.bridge.Promise r7) {
        /*
            java.lang.String r0 = "Read only "
            com.facebook.react.bridge.ReactApplicationContext r1 = com.ReactNativeBlobUtil.ReactNativeBlobUtilImpl.RCTContext
            android.content.Context r1 = r1.getApplicationContext()
            android.content.ContentResolver r1 = r1.getContentResolver()
            java.io.InputStream r5 = r1.openInputStream(r5)     // Catch: java.io.IOException -> L8e
            int r1 = r5.available()     // Catch: java.io.IOException -> L8e
            byte[] r2 = new byte[r1]     // Catch: java.io.IOException -> L8e
            int r3 = r5.read(r2)     // Catch: java.io.IOException -> L8e
            r5.close()     // Catch: java.io.IOException -> L8e
            if (r3 >= r1) goto L3c
            java.lang.String r5 = "EUNSPECIFIED"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L8e
            r6.<init>(r0)     // Catch: java.io.IOException -> L8e
            java.lang.StringBuilder r6 = r6.append(r3)     // Catch: java.io.IOException -> L8e
            java.lang.String r0 = " bytes of "
            java.lang.StringBuilder r6 = r6.append(r0)     // Catch: java.io.IOException -> L8e
            java.lang.StringBuilder r6 = r6.append(r1)     // Catch: java.io.IOException -> L8e
            java.lang.String r6 = r6.toString()     // Catch: java.io.IOException -> L8e
            r7.reject(r5, r6)     // Catch: java.io.IOException -> L8e
            return
        L3c:
            java.lang.String r5 = r6.toLowerCase()     // Catch: java.io.IOException -> L8e
            int r6 = r5.hashCode()     // Catch: java.io.IOException -> L8e
            r0 = -1396204209(0xffffffffacc79d4f, float:-5.673385E-12)
            r3 = 0
            r4 = 1
            if (r6 == r0) goto L5b
            r0 = 93106001(0x58caf51, float:1.3229938E-35)
            if (r6 == r0) goto L51
            goto L65
        L51:
            java.lang.String r6 = "ascii"
            boolean r5 = r5.equals(r6)     // Catch: java.io.IOException -> L8e
            if (r5 == 0) goto L65
            r5 = r4
            goto L66
        L5b:
            java.lang.String r6 = "base64"
            boolean r5 = r5.equals(r6)     // Catch: java.io.IOException -> L8e
            if (r5 == 0) goto L65
            r5 = r3
            goto L66
        L65:
            r5 = -1
        L66:
            if (r5 == 0) goto L85
            if (r5 == r4) goto L73
            java.lang.String r5 = new java.lang.String     // Catch: java.io.IOException -> L8e
            r5.<init>(r2)     // Catch: java.io.IOException -> L8e
            r7.resolve(r5)     // Catch: java.io.IOException -> L8e
            goto L92
        L73:
            com.facebook.react.bridge.WritableArray r5 = com.facebook.react.bridge.Arguments.createArray()     // Catch: java.io.IOException -> L8e
        L77:
            if (r3 >= r1) goto L81
            r6 = r2[r3]     // Catch: java.io.IOException -> L8e
            r5.pushInt(r6)     // Catch: java.io.IOException -> L8e
            int r3 = r3 + 1
            goto L77
        L81:
            r7.resolve(r5)     // Catch: java.io.IOException -> L8e
            goto L92
        L85:
            r5 = 2
            java.lang.String r5 = android.util.Base64.encodeToString(r2, r5)     // Catch: java.io.IOException -> L8e
            r7.resolve(r5)     // Catch: java.io.IOException -> L8e
            goto L92
        L8e:
            r5 = move-exception
            r5.printStackTrace()
        L92:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ReactNativeBlobUtil.ReactNativeBlobUtilMediaCollection.getBlob(android.net.Uri, java.lang.String, com.facebook.react.bridge.Promise):void");
    }
}
