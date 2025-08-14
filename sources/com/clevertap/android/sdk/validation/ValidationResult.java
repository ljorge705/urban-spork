package com.clevertap.android.sdk.validation;

/* loaded from: classes5.dex */
public final class ValidationResult {
    private int errorCode;
    private String errorDesc;
    private Object object;

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorDesc() {
        return this.errorDesc;
    }

    public Object getObject() {
        return this.object;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public void setErrorDesc(String str) {
        this.errorDesc = str;
    }

    public void setObject(Object obj) {
        this.object = obj;
    }

    public ValidationResult(int i, String str) {
        this.errorCode = i;
        this.errorDesc = str;
    }

    public ValidationResult() {
        this.errorCode = 0;
    }
}
