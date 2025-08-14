package com.google.firebase.storage.internal;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.storage.network.NetworkRequest;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes2.dex */
public class Util {
    public static final String ISO_8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    private static final int MAXIMUM_TOKEN_WAIT_TIME_MS = 30000;
    public static final int NETWORK_UNAVAILABLE = -2;
    private static final String TAG = "StorageUtil";

    public static long parseDateTime(String str) {
        if (str == null) {
            return 0L;
        }
        String strReplaceAll = str.replaceAll("Z$", "-0000");
        try {
            return new SimpleDateFormat(ISO_8601_FORMAT, Locale.getDefault()).parse(strReplaceAll).getTime();
        } catch (ParseException e) {
            Log.w(TAG, "unable to parse datetime:" + strReplaceAll, e);
            return 0L;
        }
    }

    public static boolean equals(Object obj, Object obj2) {
        return Objects.equal(obj, obj2);
    }

    public static Uri normalize(FirebaseApp firebaseApp, String str) throws UnsupportedEncodingException {
        String strSubstring;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Uri uri = NetworkRequest.PROD_BASE_URL;
        if (str.toLowerCase().startsWith("gs://")) {
            return Uri.parse("gs://" + Slashes.preserveSlashEncode(Slashes.normalizeSlashes(str.substring(5))));
        }
        Uri uri2 = Uri.parse(str);
        String scheme = uri2.getScheme();
        if (scheme != null && (equals(scheme.toLowerCase(), "http") || equals(scheme.toLowerCase(), "https"))) {
            int iIndexOf = uri2.getAuthority().toLowerCase().indexOf(uri.getAuthority());
            String strSlashize = Slashes.slashize(uri2.getEncodedPath());
            if (iIndexOf == 0 && strSlashize.startsWith("/")) {
                int iIndexOf2 = strSlashize.indexOf("/b/", 0);
                int i = iIndexOf2 + 3;
                int iIndexOf3 = strSlashize.indexOf("/", i);
                int iIndexOf4 = strSlashize.indexOf("/o/", 0);
                if (iIndexOf2 != -1 && iIndexOf3 != -1) {
                    strSubstring = strSlashize.substring(i, iIndexOf3);
                    strSlashize = iIndexOf4 != -1 ? strSlashize.substring(iIndexOf4 + 3) : "";
                } else {
                    Log.w(TAG, "Firebase Storage URLs must point to an object in your Storage Bucket. Please obtain a URL using the Firebase Console or getDownloadUrl().");
                    throw new IllegalArgumentException("Firebase Storage URLs must point to an object in your Storage Bucket. Please obtain a URL using the Firebase Console or getDownloadUrl().");
                }
            } else if (iIndexOf > 1) {
                strSubstring = uri2.getAuthority().substring(0, iIndexOf - 1);
            } else {
                Log.w(TAG, "Firebase Storage URLs must point to an object in your Storage Bucket. Please obtain a URL using the Firebase Console or getDownloadUrl().");
                throw new IllegalArgumentException("Firebase Storage URLs must point to an object in your Storage Bucket. Please obtain a URL using the Firebase Console or getDownloadUrl().");
            }
            Preconditions.checkNotEmpty(strSubstring, "No bucket specified");
            return new Uri.Builder().scheme("gs").authority(strSubstring).encodedPath(strSlashize).build();
        }
        Log.w(TAG, "FirebaseStorage is unable to support the scheme:" + scheme);
        throw new IllegalArgumentException("Uri scheme");
    }

    public static String getCurrentAuthToken(InternalAuthProvider internalAuthProvider) {
        String token;
        if (internalAuthProvider != null) {
            try {
                token = ((GetTokenResult) Tasks.await(internalAuthProvider.getAccessToken(false), 30000L, TimeUnit.MILLISECONDS)).getToken();
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                Log.e(TAG, "error getting token " + e);
            }
        } else {
            token = null;
        }
        if (!TextUtils.isEmpty(token)) {
            return token;
        }
        Log.w(TAG, "no auth token for request");
        return null;
    }

    public static String getCurrentAppCheckToken(InteropAppCheckTokenProvider interopAppCheckTokenProvider) {
        if (interopAppCheckTokenProvider == null) {
            return null;
        }
        try {
            AppCheckTokenResult appCheckTokenResult = (AppCheckTokenResult) Tasks.await(interopAppCheckTokenProvider.getToken(false), 30000L, TimeUnit.MILLISECONDS);
            if (appCheckTokenResult.getError() != null) {
                Log.w(TAG, "Error getting App Check token; using placeholder token instead. Error: " + appCheckTokenResult.getError());
            }
            return appCheckTokenResult.getToken();
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            Log.e(TAG, "Unexpected error getting App Check token: " + e);
            return null;
        }
    }
}
