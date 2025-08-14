package com.onfido.api.client.token.sdk;

import android.util.Base64;

/* loaded from: classes6.dex */
class SDKTokenExtractor {
    private static final int HEADER_INDEX = 0;
    private static final int PAYLOAD_INDEX = 1;
    private static final String SDK_TOKEN_GROUP_SEPARATOR = "\\.";
    private static final int TOKEN_TOTAL_PART_COUNT = 3;

    private SDKTokenExtractor() {
    }

    static String decodeHeader(String str) {
        return getTokenPart(str, 0);
    }

    static String decodePayload(String str) {
        return getTokenPart(str, 1);
    }

    private static String getTokenPart(String str, int i) {
        String[] strArrSplit = str != null ? str.split(SDK_TOKEN_GROUP_SEPARATOR) : null;
        if (strArrSplit == null || strArrSplit.length != 3) {
            return null;
        }
        return convertToString(Base64.decode(strArrSplit[i], 0));
    }

    private static String convertToString(byte[] bArr) {
        return bArr != null ? new String(bArr) : "";
    }
}
