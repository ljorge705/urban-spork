package com.reactnativegooglesignin;

import android.net.Uri;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes6.dex */
public class Utils {
    static String scopesToString(ReadableArray readableArray) {
        StringBuilder sb = new StringBuilder("oauth2:");
        for (int i = 0; i < readableArray.size(); i++) {
            sb.append(readableArray.getString(i)).append(StringUtils.SPACE);
        }
        return sb.toString().trim();
    }

    static WritableMap getUserProperties(GoogleSignInAccount googleSignInAccount) {
        Uri photoUrl = googleSignInAccount.getPhotoUrl();
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("id", googleSignInAccount.getId());
        writableMapCreateMap.putString("name", googleSignInAccount.getDisplayName());
        writableMapCreateMap.putString("givenName", googleSignInAccount.getGivenName());
        writableMapCreateMap.putString("familyName", googleSignInAccount.getFamilyName());
        writableMapCreateMap.putString("email", googleSignInAccount.getEmail());
        writableMapCreateMap.putString("photo", photoUrl != null ? photoUrl.toString() : null);
        WritableMap writableMapCreateMap2 = Arguments.createMap();
        writableMapCreateMap2.putMap("user", writableMapCreateMap);
        writableMapCreateMap2.putString("idToken", googleSignInAccount.getIdToken());
        writableMapCreateMap2.putString("serverAuthCode", googleSignInAccount.getServerAuthCode());
        WritableArray writableArrayCreateArray = Arguments.createArray();
        Iterator<Scope> it = googleSignInAccount.getGrantedScopes().iterator();
        while (it.hasNext()) {
            String string = it.next().toString();
            if (string.startsWith("http")) {
                writableArrayCreateArray.pushString(string);
            }
        }
        writableMapCreateMap2.putArray("scopes", writableArrayCreateArray);
        return writableMapCreateMap2;
    }

    static GoogleSignInOptions getSignInOptions(Scope[] scopeArr, String str, boolean z, boolean z2, String str2, String str3) {
        GoogleSignInOptions.Builder builderRequestScopes = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestScopes(new Scope("email"), scopeArr);
        if (str != null && !str.isEmpty()) {
            builderRequestScopes.requestIdToken(str);
            if (z) {
                builderRequestScopes.requestServerAuthCode(str, z2);
            }
        }
        if (str2 != null && !str2.isEmpty()) {
            builderRequestScopes.setAccountName(str2);
        }
        if (str3 != null && !str3.isEmpty()) {
            builderRequestScopes.setHostedDomain(str3);
        }
        return builderRequestScopes.build();
    }

    static Scope[] createScopesArray(ReadableArray readableArray) {
        int size = readableArray.size();
        Scope[] scopeArr = new Scope[size];
        for (int i = 0; i < size; i++) {
            scopeArr[i] = new Scope(readableArray.getString(i));
        }
        return scopeArr;
    }

    public static int getExceptionCode(Task<Void> task) {
        Exception exception = task.getException();
        if (exception instanceof ApiException) {
            return ((ApiException) exception).getStatusCode();
        }
        return 8;
    }
}
