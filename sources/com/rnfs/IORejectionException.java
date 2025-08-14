package com.rnfs;

/* loaded from: classes6.dex */
class IORejectionException extends Exception {
    private String code;

    public String getCode() {
        return this.code;
    }

    public IORejectionException(String str, String str2) {
        super(str2);
        this.code = str;
    }
}
