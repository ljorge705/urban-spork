package com.onfido.android.sdk.capture.internal.nfc;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001Bq\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b\u0012\u0010\b\u0002\u0010\t\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b\u0012\u0010\b\u0002\u0010\n\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u001d\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\bHÆ\u0003J\u0011\u0010\u001e\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\bHÆ\u0003J\u0011\u0010\u001f\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\bHÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\fHÆ\u0003Ju\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u0010\b\u0002\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b2\u0010\b\u0002\u0010\t\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b2\u0010\b\u0002\u0010\n\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fHÆ\u0001J\u0013\u0010#\u001a\u00020\u00032\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020\fHÖ\u0001R\u0019\u0010\t\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0019\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0019\u0010\n\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0013\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012¨\u0006("}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/PassportAuthAccess;", "", "hasAccess", "", "hasPaceAuthSucceeded", "hasBacAuthSucceeded", "paceException", "Ljava/lang/Exception;", "Lkotlin/Exception;", "bacException", "selectAppletException", "cardAccessFileString", "", "usedSecurityInfoString", "(ZZZLjava/lang/Exception;Ljava/lang/Exception;Ljava/lang/Exception;Ljava/lang/String;Ljava/lang/String;)V", "getBacException", "()Ljava/lang/Exception;", "getCardAccessFileString", "()Ljava/lang/String;", "getHasAccess", "()Z", "getHasBacAuthSucceeded", "getHasPaceAuthSucceeded", "getPaceException", "getSelectAppletException", "getUsedSecurityInfoString", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", Constants.COPY_TYPE, "equals", "other", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class PassportAuthAccess {
    private final Exception bacException;
    private final String cardAccessFileString;
    private final boolean hasAccess;
    private final boolean hasBacAuthSucceeded;
    private final boolean hasPaceAuthSucceeded;
    private final Exception paceException;
    private final Exception selectAppletException;
    private final String usedSecurityInfoString;

    public PassportAuthAccess() {
        this(false, false, false, null, null, null, null, null, 255, null);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getHasAccess() {
        return this.hasAccess;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getHasPaceAuthSucceeded() {
        return this.hasPaceAuthSucceeded;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getHasBacAuthSucceeded() {
        return this.hasBacAuthSucceeded;
    }

    /* renamed from: component4, reason: from getter */
    public final Exception getPaceException() {
        return this.paceException;
    }

    /* renamed from: component5, reason: from getter */
    public final Exception getBacException() {
        return this.bacException;
    }

    /* renamed from: component6, reason: from getter */
    public final Exception getSelectAppletException() {
        return this.selectAppletException;
    }

    /* renamed from: component7, reason: from getter */
    public final String getCardAccessFileString() {
        return this.cardAccessFileString;
    }

    /* renamed from: component8, reason: from getter */
    public final String getUsedSecurityInfoString() {
        return this.usedSecurityInfoString;
    }

    public final PassportAuthAccess copy(boolean hasAccess, boolean hasPaceAuthSucceeded, boolean hasBacAuthSucceeded, Exception paceException, Exception bacException, Exception selectAppletException, String cardAccessFileString, String usedSecurityInfoString) {
        return new PassportAuthAccess(hasAccess, hasPaceAuthSucceeded, hasBacAuthSucceeded, paceException, bacException, selectAppletException, cardAccessFileString, usedSecurityInfoString);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PassportAuthAccess)) {
            return false;
        }
        PassportAuthAccess passportAuthAccess = (PassportAuthAccess) other;
        return this.hasAccess == passportAuthAccess.hasAccess && this.hasPaceAuthSucceeded == passportAuthAccess.hasPaceAuthSucceeded && this.hasBacAuthSucceeded == passportAuthAccess.hasBacAuthSucceeded && Intrinsics.areEqual(this.paceException, passportAuthAccess.paceException) && Intrinsics.areEqual(this.bacException, passportAuthAccess.bacException) && Intrinsics.areEqual(this.selectAppletException, passportAuthAccess.selectAppletException) && Intrinsics.areEqual(this.cardAccessFileString, passportAuthAccess.cardAccessFileString) && Intrinsics.areEqual(this.usedSecurityInfoString, passportAuthAccess.usedSecurityInfoString);
    }

    public final Exception getBacException() {
        return this.bacException;
    }

    public final String getCardAccessFileString() {
        return this.cardAccessFileString;
    }

    public final boolean getHasAccess() {
        return this.hasAccess;
    }

    public final boolean getHasBacAuthSucceeded() {
        return this.hasBacAuthSucceeded;
    }

    public final boolean getHasPaceAuthSucceeded() {
        return this.hasPaceAuthSucceeded;
    }

    public final Exception getPaceException() {
        return this.paceException;
    }

    public final Exception getSelectAppletException() {
        return this.selectAppletException;
    }

    public final String getUsedSecurityInfoString() {
        return this.usedSecurityInfoString;
    }

    public int hashCode() {
        int iHashCode = ((((Boolean.hashCode(this.hasAccess) * 31) + Boolean.hashCode(this.hasPaceAuthSucceeded)) * 31) + Boolean.hashCode(this.hasBacAuthSucceeded)) * 31;
        Exception exc = this.paceException;
        int iHashCode2 = (iHashCode + (exc == null ? 0 : exc.hashCode())) * 31;
        Exception exc2 = this.bacException;
        int iHashCode3 = (iHashCode2 + (exc2 == null ? 0 : exc2.hashCode())) * 31;
        Exception exc3 = this.selectAppletException;
        int iHashCode4 = (iHashCode3 + (exc3 == null ? 0 : exc3.hashCode())) * 31;
        String str = this.cardAccessFileString;
        int iHashCode5 = (iHashCode4 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.usedSecurityInfoString;
        return iHashCode5 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "PassportAuthAccess(hasAccess=" + this.hasAccess + ", hasPaceAuthSucceeded=" + this.hasPaceAuthSucceeded + ", hasBacAuthSucceeded=" + this.hasBacAuthSucceeded + ", paceException=" + this.paceException + ", bacException=" + this.bacException + ", selectAppletException=" + this.selectAppletException + ", cardAccessFileString=" + this.cardAccessFileString + ", usedSecurityInfoString=" + this.usedSecurityInfoString + ')';
    }

    public PassportAuthAccess(boolean z, boolean z2, boolean z3, Exception exc, Exception exc2, Exception exc3, String str, String str2) {
        this.hasAccess = z;
        this.hasPaceAuthSucceeded = z2;
        this.hasBacAuthSucceeded = z3;
        this.paceException = exc;
        this.bacException = exc2;
        this.selectAppletException = exc3;
        this.cardAccessFileString = str;
        this.usedSecurityInfoString = str2;
    }

    public /* synthetic */ PassportAuthAccess(boolean z, boolean z2, boolean z3, Exception exc, Exception exc2, Exception exc3, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) == 0 ? z3 : false, (i & 8) != 0 ? null : exc, (i & 16) != 0 ? null : exc2, (i & 32) != 0 ? null : exc3, (i & 64) != 0 ? null : str, (i & 128) == 0 ? str2 : null);
    }
}
