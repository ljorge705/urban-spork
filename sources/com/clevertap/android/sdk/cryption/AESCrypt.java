package com.clevertap.android.sdk.cryption;

import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.henninghall.date_picker.props.ModeProp;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: AESCrypt.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0016J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002J\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0014J$\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u000bH\u0002¨\u0006\u0012"}, d2 = {"Lcom/clevertap/android/sdk/cryption/AESCrypt;", "Lcom/clevertap/android/sdk/cryption/Crypt;", "()V", "decryptInternal", "", "cipherText", "accountID", "encryptInternal", "plainText", "generateKeyPassword", "parseCipherText", "", "performCryptOperation", ModeProp.name, "", "password", "text", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class AESCrypt extends Crypt {
    private static final String APP_ID_KEY_PREFIX;
    private static final String APP_ID_KEY_SUFFIX;

    private final String generateKeyPassword(String accountID) {
        return APP_ID_KEY_PREFIX + accountID + APP_ID_KEY_SUFFIX;
    }

    @Override // com.clevertap.android.sdk.cryption.Crypt
    public String encryptInternal(String plainText, String accountID) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        Intrinsics.checkNotNullParameter(plainText, "plainText");
        Intrinsics.checkNotNullParameter(accountID, "accountID");
        String strGenerateKeyPassword = generateKeyPassword(accountID);
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        byte[] bytes = plainText.getBytes(UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        byte[] bArrPerformCryptOperation = performCryptOperation(1, strGenerateKeyPassword, bytes);
        if (bArrPerformCryptOperation == null) {
            return null;
        }
        String string = Arrays.toString(bArrPerformCryptOperation);
        Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
        return string;
    }

    @Override // com.clevertap.android.sdk.cryption.Crypt
    public String decryptInternal(String cipherText, String accountID) {
        byte[] bArrPerformCryptOperation;
        Intrinsics.checkNotNullParameter(cipherText, "cipherText");
        Intrinsics.checkNotNullParameter(accountID, "accountID");
        byte[] cipherText2 = parseCipherText(cipherText);
        if (cipherText2 == null || (bArrPerformCryptOperation = performCryptOperation(2, generateKeyPassword(accountID), cipherText2)) == null) {
            return null;
        }
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        return new String(bArrPerformCryptOperation, UTF_8);
    }

    @Override // com.clevertap.android.sdk.cryption.Crypt
    protected byte[] parseCipherText(String cipherText) {
        Intrinsics.checkNotNullParameter(cipherText, "cipherText");
        try {
            String strSubstring = cipherText.substring(1, cipherText.length() - 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            List<String> listSplit = new Regex("\\s*,\\s*").split(StringsKt.trim((CharSequence) strSubstring).toString(), 0);
            byte[] bArr = new byte[listSplit.size()];
            int size = listSplit.size();
            for (int i = 0; i < size; i++) {
                bArr[i] = Byte.parseByte(listSplit.get(i));
            }
            return bArr;
        } catch (Exception e) {
            Logger.v("Unable to parse cipher text", e);
            return null;
        }
    }

    private final byte[] performCryptOperation(int mode, String password, byte[] text) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        try {
            Charset UTF_8 = StandardCharsets.UTF_8;
            Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
            byte[] bytes = Constants.CRYPTION_SALT.getBytes(UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            Charset UTF_82 = StandardCharsets.UTF_8;
            Intrinsics.checkNotNullExpressionValue(UTF_82, "UTF_8");
            byte[] bytes2 = Constants.CRYPTION_IV.getBytes(UTF_82);
            Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
            char[] charArray = password.toCharArray();
            Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
            SecretKeySpec secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBEWithMD5And128BitAES-CBC-OpenSSL").generateSecret(new PBEKeySpec(charArray, bytes, 1000, 256)).getEncoded(), CipherStorageKeystoreAesCbc.ALGORITHM_AES);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(mode, secretKeySpec, new IvParameterSpec(bytes2));
            return cipher.doFinal(text);
        } catch (Exception e) {
            Logger.v("Unable to perform crypt operation", e);
            return null;
        }
    }

    static {
        Intrinsics.checkNotNullExpressionValue("Lq3fz", "StringBuilder()\n        …\").append(\"z\").toString()");
        APP_ID_KEY_PREFIX = "Lq3fz";
        Intrinsics.checkNotNullExpressionValue("bLti2", "StringBuilder()\n        …\"i\").append(2).toString()");
        APP_ID_KEY_SUFFIX = "bLti2";
    }
}
