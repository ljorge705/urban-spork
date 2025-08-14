package cl.json;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.core.content.FileProvider;
import androidx.media3.common.MimeTypes;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.facebook.react.bridge.ReactContext;
import java.io.File;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class RNSharePathUtil {
    private static final ArrayList<String> authorities = new ArrayList<>();

    public static void compileAuthorities(ReactContext reactContext) {
        ArrayList<String> arrayList = authorities;
        if (arrayList.size() == 0) {
            ComponentCallbacks2 componentCallbacks2 = (Application) reactContext.getApplicationContext();
            if (componentCallbacks2 instanceof ShareApplication) {
                arrayList.add(((ShareApplication) componentCallbacks2).getFileProviderAuthority());
            }
            arrayList.add(reactContext.getPackageName() + ".rnshare.fileprovider");
        }
    }

    public static Uri compatUriFromFile(ReactContext reactContext, File file) {
        compileAuthorities(reactContext);
        String authority = Uri.fromFile(file).getAuthority();
        if (!TextUtils.isEmpty(authority) && authorities.contains(authority)) {
            return Uri.fromFile(file);
        }
        if (file.getAbsolutePath().startsWith(ReactNativeBlobUtilConst.FILE_PREFIX_CONTENT)) {
            return Uri.fromFile(file);
        }
        Uri uriForFile = null;
        int i = 0;
        while (true) {
            ArrayList<String> arrayList = authorities;
            if (i >= arrayList.size()) {
                break;
            }
            try {
                uriForFile = FileProvider.getUriForFile(reactContext, arrayList.get(i), file);
            } catch (Exception e) {
                System.out.println("RNSharePathUtil::compatUriFromFile ERROR " + e.getMessage());
            }
            if (uriForFile != null) {
                break;
            }
            i++;
        }
        return uriForFile;
    }

    public static String getRealPathFromURI(Context context, Uri uri, Boolean bool) {
        Uri uri2 = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] strArrSplit = DocumentsContract.getDocumentId(uri).split(":");
                String str = strArrSplit[0];
                if ("primary".equalsIgnoreCase(str) || "0".equalsIgnoreCase(str)) {
                    return "" + (bool.booleanValue() ? context.getCacheDir() : context.getExternalCacheDir()) + "/" + strArrSplit[1];
                }
                if ("raw".equalsIgnoreCase(str)) {
                    return "" + strArrSplit[1];
                }
                if (!TextUtils.isEmpty(str)) {
                    return "/storage/" + str + "/" + strArrSplit[1];
                }
            } else {
                if (isDownloadsDocument(uri)) {
                    String documentId = DocumentsContract.getDocumentId(uri);
                    if (documentId.startsWith("raw:")) {
                        return "" + documentId.replaceFirst("raw:", "");
                    }
                    return "" + getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId).longValue()), null, null);
                }
                if (isMediaDocument(uri)) {
                    String[] strArrSplit2 = DocumentsContract.getDocumentId(uri).split(":");
                    String str2 = strArrSplit2[0];
                    if (MimeTypes.BASE_TYPE_IMAGE.equals(str2)) {
                        uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(str2)) {
                        uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if (MimeTypes.BASE_TYPE_AUDIO.equals(str2)) {
                        uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    } else if ("raw".equalsIgnoreCase(str2)) {
                        return "" + strArrSplit2[1];
                    }
                    return "" + getDataColumn(context, uri2, "_id=?", new String[]{strArrSplit2[1]});
                }
            }
        } else {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                if (isGooglePhotosUri(uri)) {
                    return uri.getLastPathSegment();
                }
                return "" + getDataColumn(context, uri, null, null);
            }
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0045  */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getDataColumn(android.content.Context r10, android.net.Uri r11, java.lang.String r12, java.lang.String[] r13) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "_data"
            java.lang.String[] r4 = new java.lang.String[]{r0}
            r8 = 0
            androidx.loader.content.CursorLoader r9 = new androidx.loader.content.CursorLoader     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
            r7 = 0
            r1 = r9
            r2 = r10
            r3 = r11
            r5 = r12
            r6 = r13
            r1.<init>(r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
            android.database.Cursor r10 = r9.loadInBackground()     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
            if (r10 == 0) goto L2e
            boolean r11 = r10.moveToFirst()     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L41
            if (r11 == 0) goto L2e
            int r11 = r10.getColumnIndexOrThrow(r0)     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L41
            java.lang.String r11 = r10.getString(r11)     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L41
            if (r10 == 0) goto L2b
            r10.close()
        L2b:
            return r11
        L2c:
            r11 = move-exception
            goto L38
        L2e:
            if (r10 == 0) goto L33
            r10.close()
        L33:
            return r8
        L34:
            r11 = move-exception
            goto L43
        L36:
            r11 = move-exception
            r10 = r8
        L38:
            r11.printStackTrace()     // Catch: java.lang.Throwable -> L41
            if (r10 == 0) goto L40
            r10.close()
        L40:
            return r8
        L41:
            r11 = move-exception
            r8 = r10
        L43:
            if (r8 == 0) goto L48
            r8.close()
        L48:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: cl.json.RNSharePathUtil.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
