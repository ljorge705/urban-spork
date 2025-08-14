package com.onfido.api.client.token.sdk;

import com.onfido.api.client.codec.binary.Base64;

/* loaded from: classes6.dex */
public class InternalSDKTokenExtractor {
    private static final int HEADER_INDEX = 0;
    private static final int PAYLOAD_INDEX = 1;
    private static final String SDK_TOKEN_GROUP_SEPARATOR = "\\.";
    private static final int SIGNATURE_INDEX = 2;
    private static final int TOKEN_TOTAL_PART_COUNT = 3;

    private InternalSDKTokenExtractor() {
    }

    public static String decodeHeader(String str) {
        return getTokenPart(str, 0);
    }

    public static String decodePayload(String str) {
        return getTokenPart(str, 1);
    }

    public static String decodeSignature(String str) {
        return getTokenPart(str, 2);
    }

    private static String getTokenPart(String str, int i) {
        String[] strArrSplit = str != null ? str.split(SDK_TOKEN_GROUP_SEPARATOR) : null;
        if (strArrSplit == null || strArrSplit.length != 3) {
            return null;
        }
        return Base64.decodeBase64String(strArrSplit[i]);
    }
}
