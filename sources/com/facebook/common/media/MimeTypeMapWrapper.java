package com.facebook.common.media;

import android.webkit.MimeTypeMap;
import com.facebook.common.internal.ImmutableMap;
import com.reactnativecommunity.clipboard.ClipboardModule;
import java.util.Map;

/* loaded from: classes5.dex */
public class MimeTypeMapWrapper {
    private static final MimeTypeMap sMimeTypeMap = MimeTypeMap.getSingleton();
    private static final Map<String, String> sMimeTypeToExtensionMap = ImmutableMap.of(ClipboardModule.MIMETYPE_HEIF, "heif", ClipboardModule.MIMETYPE_HEIC, "heic");
    private static final Map<String, String> sExtensionToMimeTypeMap = ImmutableMap.of("heif", ClipboardModule.MIMETYPE_HEIF, "heic", ClipboardModule.MIMETYPE_HEIC);

    public static String getExtensionFromMimeType(String str) {
        String str2 = sMimeTypeToExtensionMap.get(str);
        return str2 != null ? str2 : sMimeTypeMap.getExtensionFromMimeType(str);
    }

    public static String getMimeTypeFromExtension(String str) {
        String str2 = sExtensionToMimeTypeMap.get(str);
        return str2 != null ? str2 : sMimeTypeMap.getMimeTypeFromExtension(str);
    }

    public static boolean hasExtension(String str) {
        return sExtensionToMimeTypeMap.containsKey(str) || sMimeTypeMap.hasExtension(str);
    }

    public static boolean hasMimeType(String str) {
        return sMimeTypeToExtensionMap.containsKey(str) || sMimeTypeMap.hasMimeType(str);
    }
}
