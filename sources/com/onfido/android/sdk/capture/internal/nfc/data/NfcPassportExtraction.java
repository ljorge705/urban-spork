package com.onfido.android.sdk.capture.internal.nfc.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b(\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B½\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0016J\t\u0010+\u001a\u00020,HÖ\u0001J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u000100H\u0096\u0002J\b\u00101\u001a\u00020,H\u0016J\u0019\u00102\u001a\u0002032\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u00020,HÖ\u0001R\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0016\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0016\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0018R\u0014\u0010\u0004\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0018R\u0016\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0018R\u0016\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0018R\u0016\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0018R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0018R\u0014\u0010\u0013\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0018¨\u00067"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;", "Landroid/os/Parcelable;", "dg1", "", "dg2", "dg3", "dg4", "dg5", "dg6", "dg7", "dg8", "dg9", "dg10", "dg11", "dg12", "dg13", "dg14", "dg15", "dg16", "sod", "aaResponse", "com", "([B[B[B[B[B[B[B[B[B[B[B[B[B[B[B[B[B[B[B)V", "getAaResponse$onfido_capture_sdk_core_release", "()[B", "getCom$onfido_capture_sdk_core_release", "getDg1$onfido_capture_sdk_core_release", "getDg10$onfido_capture_sdk_core_release", "getDg11$onfido_capture_sdk_core_release", "getDg12$onfido_capture_sdk_core_release", "getDg13$onfido_capture_sdk_core_release", "getDg14$onfido_capture_sdk_core_release", "getDg15$onfido_capture_sdk_core_release", "getDg16$onfido_capture_sdk_core_release", "getDg2$onfido_capture_sdk_core_release", "getDg3$onfido_capture_sdk_core_release", "getDg4$onfido_capture_sdk_core_release", "getDg5$onfido_capture_sdk_core_release", "getDg6$onfido_capture_sdk_core_release", "getDg7$onfido_capture_sdk_core_release", "getDg8$onfido_capture_sdk_core_release", "getDg9$onfido_capture_sdk_core_release", "getSod$onfido_capture_sdk_core_release", "describeContents", "", "equals", "", "other", "", "hashCode", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcPassportExtraction implements Parcelable {
    public static final Parcelable.Creator<NfcPassportExtraction> CREATOR = new Creator();
    private final byte[] aaResponse;
    private final byte[] com;
    private final byte[] dg1;
    private final byte[] dg10;
    private final byte[] dg11;
    private final byte[] dg12;
    private final byte[] dg13;
    private final byte[] dg14;
    private final byte[] dg15;
    private final byte[] dg16;
    private final byte[] dg2;
    private final byte[] dg3;
    private final byte[] dg4;
    private final byte[] dg5;
    private final byte[] dg6;
    private final byte[] dg7;
    private final byte[] dg8;
    private final byte[] dg9;
    private final byte[] sod;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<NfcPassportExtraction> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final NfcPassportExtraction createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new NfcPassportExtraction(parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final NfcPassportExtraction[] newArray(int i) {
            return new NfcPassportExtraction[i];
        }
    }

    public NfcPassportExtraction(byte[] dg1, byte[] dg2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7, byte[] bArr8, byte[] bArr9, byte[] bArr10, byte[] bArr11, byte[] bArr12, byte[] bArr13, byte[] bArr14, byte[] sod, byte[] bArr15, byte[] bArr16) {
        Intrinsics.checkNotNullParameter(dg1, "dg1");
        Intrinsics.checkNotNullParameter(dg2, "dg2");
        Intrinsics.checkNotNullParameter(sod, "sod");
        this.dg1 = dg1;
        this.dg2 = dg2;
        this.dg3 = bArr;
        this.dg4 = bArr2;
        this.dg5 = bArr3;
        this.dg6 = bArr4;
        this.dg7 = bArr5;
        this.dg8 = bArr6;
        this.dg9 = bArr7;
        this.dg10 = bArr8;
        this.dg11 = bArr9;
        this.dg12 = bArr10;
        this.dg13 = bArr11;
        this.dg14 = bArr12;
        this.dg15 = bArr13;
        this.dg16 = bArr14;
        this.sod = sod;
        this.aaResponse = bArr15;
        this.com = bArr16;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(NfcPassportExtraction.class, other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.onfido.android.sdk.capture.internal.nfc.data.NfcPassportExtraction");
        NfcPassportExtraction nfcPassportExtraction = (NfcPassportExtraction) other;
        if (!Arrays.equals(this.dg1, nfcPassportExtraction.dg1) || !Arrays.equals(this.dg2, nfcPassportExtraction.dg2)) {
            return false;
        }
        byte[] bArr = this.dg3;
        if (bArr != null) {
            byte[] bArr2 = nfcPassportExtraction.dg3;
            if (bArr2 == null || !Arrays.equals(bArr, bArr2)) {
                return false;
            }
        } else if (nfcPassportExtraction.dg3 != null) {
            return false;
        }
        byte[] bArr3 = this.dg4;
        if (bArr3 != null) {
            byte[] bArr4 = nfcPassportExtraction.dg4;
            if (bArr4 == null || !Arrays.equals(bArr3, bArr4)) {
                return false;
            }
        } else if (nfcPassportExtraction.dg4 != null) {
            return false;
        }
        if (this.dg5 != null) {
            byte[] bArr5 = nfcPassportExtraction.dg5;
            if (bArr5 == null || !Arrays.equals(this.dg3, bArr5)) {
                return false;
            }
        } else if (nfcPassportExtraction.dg5 != null) {
            return false;
        }
        byte[] bArr6 = this.dg6;
        if (bArr6 != null) {
            byte[] bArr7 = nfcPassportExtraction.dg6;
            if (bArr7 == null || !Arrays.equals(bArr6, bArr7)) {
                return false;
            }
        } else if (nfcPassportExtraction.dg6 != null) {
            return false;
        }
        byte[] bArr8 = this.dg7;
        if (bArr8 != null) {
            byte[] bArr9 = nfcPassportExtraction.dg7;
            if (bArr9 == null || !Arrays.equals(bArr8, bArr9)) {
                return false;
            }
        } else if (nfcPassportExtraction.dg7 != null) {
            return false;
        }
        byte[] bArr10 = this.dg8;
        if (bArr10 != null) {
            byte[] bArr11 = nfcPassportExtraction.dg8;
            if (bArr11 == null || !Arrays.equals(bArr10, bArr11)) {
                return false;
            }
        } else if (nfcPassportExtraction.dg8 != null) {
            return false;
        }
        byte[] bArr12 = this.dg9;
        if (bArr12 != null) {
            byte[] bArr13 = nfcPassportExtraction.dg9;
            if (bArr13 == null || !Arrays.equals(bArr12, bArr13)) {
                return false;
            }
        } else if (nfcPassportExtraction.dg9 != null) {
            return false;
        }
        byte[] bArr14 = this.dg10;
        if (bArr14 != null) {
            byte[] bArr15 = nfcPassportExtraction.dg10;
            if (bArr15 == null || !Arrays.equals(bArr14, bArr15)) {
                return false;
            }
        } else if (nfcPassportExtraction.dg10 != null) {
            return false;
        }
        byte[] bArr16 = this.dg11;
        if (bArr16 != null) {
            byte[] bArr17 = nfcPassportExtraction.dg11;
            if (bArr17 == null || !Arrays.equals(bArr16, bArr17)) {
                return false;
            }
        } else if (nfcPassportExtraction.dg11 != null) {
            return false;
        }
        byte[] bArr18 = this.dg12;
        if (bArr18 != null) {
            byte[] bArr19 = nfcPassportExtraction.dg12;
            if (bArr19 == null || !Arrays.equals(bArr18, bArr19)) {
                return false;
            }
        } else if (nfcPassportExtraction.dg12 != null) {
            return false;
        }
        byte[] bArr20 = this.dg13;
        if (bArr20 != null) {
            byte[] bArr21 = nfcPassportExtraction.dg13;
            if (bArr21 == null || !Arrays.equals(bArr20, bArr21)) {
                return false;
            }
        } else if (nfcPassportExtraction.dg13 != null) {
            return false;
        }
        byte[] bArr22 = this.dg14;
        if (bArr22 != null) {
            byte[] bArr23 = nfcPassportExtraction.dg14;
            if (bArr23 == null || !Arrays.equals(bArr22, bArr23)) {
                return false;
            }
        } else if (nfcPassportExtraction.dg14 != null) {
            return false;
        }
        byte[] bArr24 = this.dg15;
        if (bArr24 != null) {
            byte[] bArr25 = nfcPassportExtraction.dg15;
            if (bArr25 == null || !Arrays.equals(bArr24, bArr25)) {
                return false;
            }
        } else if (nfcPassportExtraction.dg15 != null) {
            return false;
        }
        byte[] bArr26 = this.dg16;
        if (bArr26 != null) {
            byte[] bArr27 = nfcPassportExtraction.dg16;
            if (bArr27 == null || !Arrays.equals(bArr26, bArr27)) {
                return false;
            }
        } else if (nfcPassportExtraction.dg16 != null) {
            return false;
        }
        if (!Arrays.equals(this.sod, nfcPassportExtraction.sod)) {
            return false;
        }
        byte[] bArr28 = this.aaResponse;
        byte[] bArr29 = nfcPassportExtraction.aaResponse;
        if (bArr28 != null) {
            if (bArr29 == null || !Arrays.equals(bArr28, bArr29)) {
                return false;
            }
        } else if (bArr29 != null) {
            return false;
        }
        return true;
    }

    /* renamed from: getAaResponse$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getAaResponse() {
        return this.aaResponse;
    }

    /* renamed from: getCom$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getCom() {
        return this.com;
    }

    /* renamed from: getDg1$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getDg1() {
        return this.dg1;
    }

    /* renamed from: getDg10$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getDg10() {
        return this.dg10;
    }

    /* renamed from: getDg11$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getDg11() {
        return this.dg11;
    }

    /* renamed from: getDg12$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getDg12() {
        return this.dg12;
    }

    /* renamed from: getDg13$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getDg13() {
        return this.dg13;
    }

    /* renamed from: getDg14$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getDg14() {
        return this.dg14;
    }

    /* renamed from: getDg15$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getDg15() {
        return this.dg15;
    }

    /* renamed from: getDg16$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getDg16() {
        return this.dg16;
    }

    /* renamed from: getDg2$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getDg2() {
        return this.dg2;
    }

    /* renamed from: getDg3$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getDg3() {
        return this.dg3;
    }

    /* renamed from: getDg4$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getDg4() {
        return this.dg4;
    }

    /* renamed from: getDg5$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getDg5() {
        return this.dg5;
    }

    /* renamed from: getDg6$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getDg6() {
        return this.dg6;
    }

    /* renamed from: getDg7$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getDg7() {
        return this.dg7;
    }

    /* renamed from: getDg8$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getDg8() {
        return this.dg8;
    }

    /* renamed from: getDg9$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getDg9() {
        return this.dg9;
    }

    /* renamed from: getSod$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getSod() {
        return this.sod;
    }

    public int hashCode() {
        int iHashCode = ((Arrays.hashCode(this.dg1) * 31) + Arrays.hashCode(this.dg2)) * 31;
        byte[] bArr = this.dg3;
        int iHashCode2 = (iHashCode + (bArr != null ? Arrays.hashCode(bArr) : 0)) * 31;
        byte[] bArr2 = this.dg4;
        int iHashCode3 = (iHashCode2 + (bArr2 != null ? Arrays.hashCode(bArr2) : 0)) * 31;
        byte[] bArr3 = this.dg5;
        int iHashCode4 = (iHashCode3 + (bArr3 != null ? Arrays.hashCode(bArr3) : 0)) * 31;
        byte[] bArr4 = this.dg6;
        int iHashCode5 = (iHashCode4 + (bArr4 != null ? Arrays.hashCode(bArr4) : 0)) * 31;
        byte[] bArr5 = this.dg7;
        int iHashCode6 = (iHashCode5 + (bArr5 != null ? Arrays.hashCode(bArr5) : 0)) * 31;
        byte[] bArr6 = this.dg8;
        int iHashCode7 = (iHashCode6 + (bArr6 != null ? Arrays.hashCode(bArr6) : 0)) * 31;
        byte[] bArr7 = this.dg9;
        int iHashCode8 = (iHashCode7 + (bArr7 != null ? Arrays.hashCode(bArr7) : 0)) * 31;
        byte[] bArr8 = this.dg10;
        int iHashCode9 = (iHashCode8 + (bArr8 != null ? Arrays.hashCode(bArr8) : 0)) * 31;
        byte[] bArr9 = this.dg11;
        int iHashCode10 = (iHashCode9 + (bArr9 != null ? Arrays.hashCode(bArr9) : 0)) * 31;
        byte[] bArr10 = this.dg12;
        int iHashCode11 = (iHashCode10 + (bArr10 != null ? Arrays.hashCode(bArr10) : 0)) * 31;
        byte[] bArr11 = this.dg13;
        int iHashCode12 = (iHashCode11 + (bArr11 != null ? Arrays.hashCode(bArr11) : 0)) * 31;
        byte[] bArr12 = this.dg14;
        int iHashCode13 = (iHashCode12 + (bArr12 != null ? Arrays.hashCode(bArr12) : 0)) * 31;
        byte[] bArr13 = this.dg15;
        int iHashCode14 = (iHashCode13 + (bArr13 != null ? Arrays.hashCode(bArr13) : 0)) * 31;
        byte[] bArr14 = this.dg16;
        int iHashCode15 = (((iHashCode14 + (bArr14 != null ? Arrays.hashCode(bArr14) : 0)) * 31) + Arrays.hashCode(this.sod)) * 31;
        byte[] bArr15 = this.aaResponse;
        return iHashCode15 + (bArr15 != null ? Arrays.hashCode(bArr15) : 0);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeByteArray(this.dg1);
        parcel.writeByteArray(this.dg2);
        parcel.writeByteArray(this.dg3);
        parcel.writeByteArray(this.dg4);
        parcel.writeByteArray(this.dg5);
        parcel.writeByteArray(this.dg6);
        parcel.writeByteArray(this.dg7);
        parcel.writeByteArray(this.dg8);
        parcel.writeByteArray(this.dg9);
        parcel.writeByteArray(this.dg10);
        parcel.writeByteArray(this.dg11);
        parcel.writeByteArray(this.dg12);
        parcel.writeByteArray(this.dg13);
        parcel.writeByteArray(this.dg14);
        parcel.writeByteArray(this.dg15);
        parcel.writeByteArray(this.dg16);
        parcel.writeByteArray(this.sod);
        parcel.writeByteArray(this.aaResponse);
        parcel.writeByteArray(this.com);
    }
}
