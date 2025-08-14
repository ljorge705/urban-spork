package com.microsoft.codepush.react;

import android.content.Context;
import android.util.Base64;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class CodePushUpdateUtils {
    public static final String NEW_LINE = System.getProperty("line.separator");

    public static boolean isHashIgnored(String str) {
        return str.startsWith("__MACOSX/") || str.equals(".DS_Store") || str.endsWith("/.DS_Store") || str.equals(CodePushConstants.BUNDLE_JWT_FILE) || str.endsWith("/.codepushrelease");
    }

    private static void addContentsOfFolderToManifest(String str, String str2, ArrayList<String> arrayList) {
        for (File file : new File(str).listFiles()) {
            String name = file.getName();
            String absolutePath = file.getAbsolutePath();
            String str3 = (str2.isEmpty() ? "" : str2 + "/") + name;
            if (!isHashIgnored(str3)) {
                if (file.isDirectory()) {
                    addContentsOfFolderToManifest(absolutePath, str3, arrayList);
                } else {
                    try {
                        arrayList.add(str3 + ":" + computeHash(new FileInputStream(file)));
                    } catch (FileNotFoundException e) {
                        throw new CodePushUnknownException("Unable to compute hash of update contents.", e);
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0021, code lost:
    
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0022, code lost:
    
        r5.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005a A[Catch: IOException -> 0x0056, TRY_LEAVE, TryCatch #1 {IOException -> 0x0056, blocks: (B:32:0x0052, B:36:0x005a), top: B:40:0x0052 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0052 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String computeHash(java.io.InputStream r5) throws java.lang.Throwable {
        /*
            r0 = 0
            java.lang.String r1 = "SHA-256"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L42 java.security.NoSuchAlgorithmException -> L44
            java.security.DigestInputStream r2 = new java.security.DigestInputStream     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L42 java.security.NoSuchAlgorithmException -> L44
            r2.<init>(r5, r1)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L42 java.security.NoSuchAlgorithmException -> L44
            r0 = 8192(0x2000, float:1.148E-41)
            byte[] r0 = new byte[r0]     // Catch: java.io.IOException -> L3a java.security.NoSuchAlgorithmException -> L3c java.lang.Throwable -> L4f
        L10:
            int r3 = r2.read(r0)     // Catch: java.io.IOException -> L3a java.security.NoSuchAlgorithmException -> L3c java.lang.Throwable -> L4f
            r4 = -1
            if (r3 == r4) goto L18
            goto L10
        L18:
            r2.close()     // Catch: java.io.IOException -> L21
            if (r5 == 0) goto L25
            r5.close()     // Catch: java.io.IOException -> L21
            goto L25
        L21:
            r5 = move-exception
            r5.printStackTrace()
        L25:
            byte[] r5 = r1.digest()
            java.math.BigInteger r0 = new java.math.BigInteger
            r1 = 1
            r0.<init>(r1, r5)
            java.lang.Object[] r5 = new java.lang.Object[]{r0}
            java.lang.String r0 = "%064x"
            java.lang.String r5 = java.lang.String.format(r0, r5)
            return r5
        L3a:
            r0 = move-exception
            goto L47
        L3c:
            r0 = move-exception
            goto L47
        L3e:
            r1 = move-exception
            r2 = r0
            r0 = r1
            goto L50
        L42:
            r1 = move-exception
            goto L45
        L44:
            r1 = move-exception
        L45:
            r2 = r0
            r0 = r1
        L47:
            com.microsoft.codepush.react.CodePushUnknownException r1 = new com.microsoft.codepush.react.CodePushUnknownException     // Catch: java.lang.Throwable -> L4f
            java.lang.String r3 = "Unable to compute hash of update contents."
            r1.<init>(r3, r0)     // Catch: java.lang.Throwable -> L4f
            throw r1     // Catch: java.lang.Throwable -> L4f
        L4f:
            r0 = move-exception
        L50:
            if (r2 == 0) goto L58
            r2.close()     // Catch: java.io.IOException -> L56
            goto L58
        L56:
            r5 = move-exception
            goto L5e
        L58:
            if (r5 == 0) goto L61
            r5.close()     // Catch: java.io.IOException -> L56
            goto L61
        L5e:
            r5.printStackTrace()
        L61:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.codepush.react.CodePushUpdateUtils.computeHash(java.io.InputStream):java.lang.String");
    }

    public static void copyNecessaryFilesFromCurrentPackage(String str, String str2, String str3) throws Throwable {
        FileUtils.copyDirectoryContents(str2, str3);
        try {
            JSONArray jSONArray = CodePushUtils.getJsonObjectFromFile(str).getJSONArray("deletedFiles");
            for (int i = 0; i < jSONArray.length(); i++) {
                File file = new File(str3, jSONArray.getString(i));
                if (file.exists()) {
                    file.delete();
                }
            }
        } catch (JSONException e) {
            throw new CodePushUnknownException("Unable to copy files from current package during diff update", e);
        }
    }

    public static String findJSBundleInUpdateContents(String str, String str2) {
        for (File file : new File(str).listFiles()) {
            String strAppendPathComponent = CodePushUtils.appendPathComponent(str, file.getName());
            if (file.isDirectory()) {
                String strFindJSBundleInUpdateContents = findJSBundleInUpdateContents(strAppendPathComponent, str2);
                if (strFindJSBundleInUpdateContents != null) {
                    return CodePushUtils.appendPathComponent(file.getName(), strFindJSBundleInUpdateContents);
                }
            } else {
                String name = file.getName();
                if (name.equals(str2)) {
                    return name;
                }
            }
        }
        return null;
    }

    public static String getHashForBinaryContents(Context context, boolean z) {
        try {
            try {
                return CodePushUtils.getStringFromInputStream(context.getAssets().open(CodePushConstants.CODE_PUSH_HASH_FILE_NAME));
            } catch (IOException unused) {
                return CodePushUtils.getStringFromInputStream(context.getAssets().open(CodePushConstants.CODE_PUSH_OLD_HASH_FILE_NAME));
            }
        } catch (IOException unused2) {
            if (z) {
                return null;
            }
            CodePushUtils.log("Unable to get the hash of the binary's bundled resources - \"codepush.gradle\" may have not been added to the build definition.");
            return null;
        }
    }

    public static void verifyFolderHash(String str, String str2) throws Throwable {
        CodePushUtils.log("Verifying hash for folder path: " + str);
        ArrayList arrayList = new ArrayList();
        addContentsOfFolderToManifest(str, "", arrayList);
        Collections.sort(arrayList);
        JSONArray jSONArray = new JSONArray();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            jSONArray.put((String) it.next());
        }
        String strReplace = jSONArray.toString().replace("\\/", "/");
        CodePushUtils.log("Manifest string: " + strReplace);
        String strComputeHash = computeHash(new ByteArrayInputStream(strReplace.getBytes()));
        CodePushUtils.log("Expected hash: " + str2 + ", actual hash: " + strComputeHash);
        if (!str2.equals(strComputeHash)) {
            throw new CodePushInvalidUpdateException("The update contents failed the data integrity check.");
        }
        CodePushUtils.log("The update contents succeeded the data integrity check.");
    }

    public static Map<String, Object> verifyAndDecodeJWT(String str, PublicKey publicKey) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(str);
            if (!signedJWT.verify(new RSASSAVerifier((RSAPublicKey) publicKey))) {
                return null;
            }
            Map<String, Object> claims = signedJWT.getJWTClaimsSet().getClaims();
            CodePushUtils.log("JWT verification succeeded, payload content: " + claims.toString());
            return claims;
        } catch (Exception e) {
            CodePushUtils.log(e.getMessage());
            CodePushUtils.log(e.getStackTrace().toString());
            return null;
        }
    }

    public static PublicKey parsePublicKey(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str.replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "").replace(NEW_LINE, "").getBytes(), 0)));
        } catch (Exception e) {
            CodePushUtils.log(e.getMessage());
            CodePushUtils.log(e.getStackTrace().toString());
            return null;
        }
    }

    public static String getSignatureFilePath(String str) {
        return CodePushUtils.appendPathComponent(CodePushUtils.appendPathComponent(str, "CodePush"), CodePushConstants.BUNDLE_JWT_FILE);
    }

    public static String getSignature(String str) {
        try {
            return FileUtils.readFileToString(getSignatureFilePath(str));
        } catch (IOException e) {
            CodePushUtils.log(e.getMessage());
            CodePushUtils.log(e.getStackTrace().toString());
            return null;
        }
    }

    public static void verifyUpdateSignature(String str, String str2, String str3) throws CodePushInvalidUpdateException {
        CodePushUtils.log("Verifying signature for folder path: " + str);
        PublicKey publicKey = parsePublicKey(str3);
        if (publicKey == null) {
            throw new CodePushInvalidUpdateException("The update could not be verified because no public key was found.");
        }
        String signature = getSignature(str);
        if (signature == null) {
            throw new CodePushInvalidUpdateException("The update could not be verified because no signature was found.");
        }
        Map<String, Object> mapVerifyAndDecodeJWT = verifyAndDecodeJWT(signature, publicKey);
        if (mapVerifyAndDecodeJWT == null) {
            throw new CodePushInvalidUpdateException("The update could not be verified because it was not signed by a trusted party.");
        }
        String str4 = (String) mapVerifyAndDecodeJWT.get("contentHash");
        if (str4 == null) {
            throw new CodePushInvalidUpdateException("The update could not be verified because the signature did not specify a content hash.");
        }
        if (!str4.equals(str2)) {
            throw new CodePushInvalidUpdateException("The update contents failed the code signing check.");
        }
        CodePushUtils.log("The update contents succeeded the code signing check.");
    }
}
