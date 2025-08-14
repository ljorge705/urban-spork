package com.onfido.android.sdk.capture.nfc;

import com.clevertap.android.sdk.Constants;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MRTDModels.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b<\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B¿\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0016J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jé\u0001\u0010>\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010B\u001a\u00020CH\u0016J\t\u0010D\u001a\u00020EHÖ\u0001R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0018R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0018R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0018R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0018R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0018R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0018¨\u0006F"}, d2 = {"Lcom/onfido/android/sdk/capture/nfc/MRTDData;", "", "dg1", "", "dg2", "dg3", "dg4", "dg5", "dg6", "dg7", "dg8", "dg9", "dg10", "dg11", "dg12", "dg13", "dg14", "dg15", "dg16", "com", "sod", "aaResponse", "([B[B[B[B[B[B[B[B[B[B[B[B[B[B[B[B[B[B[B)V", "getAaResponse", "()[B", "getCom", "getDg1", "getDg10", "getDg11", "getDg12", "getDg13", "getDg14", "getDg15", "getDg16", "getDg2", "getDg3", "getDg4", "getDg5", "getDg6", "getDg7", "getDg8", "getDg9", "getSod", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class MRTDData {
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

    /* renamed from: component1, reason: from getter */
    public final byte[] getDg1() {
        return this.dg1;
    }

    /* renamed from: component10, reason: from getter */
    public final byte[] getDg10() {
        return this.dg10;
    }

    /* renamed from: component11, reason: from getter */
    public final byte[] getDg11() {
        return this.dg11;
    }

    /* renamed from: component12, reason: from getter */
    public final byte[] getDg12() {
        return this.dg12;
    }

    /* renamed from: component13, reason: from getter */
    public final byte[] getDg13() {
        return this.dg13;
    }

    /* renamed from: component14, reason: from getter */
    public final byte[] getDg14() {
        return this.dg14;
    }

    /* renamed from: component15, reason: from getter */
    public final byte[] getDg15() {
        return this.dg15;
    }

    /* renamed from: component16, reason: from getter */
    public final byte[] getDg16() {
        return this.dg16;
    }

    /* renamed from: component17, reason: from getter */
    public final byte[] getCom() {
        return this.com;
    }

    /* renamed from: component18, reason: from getter */
    public final byte[] getSod() {
        return this.sod;
    }

    /* renamed from: component19, reason: from getter */
    public final byte[] getAaResponse() {
        return this.aaResponse;
    }

    /* renamed from: component2, reason: from getter */
    public final byte[] getDg2() {
        return this.dg2;
    }

    /* renamed from: component3, reason: from getter */
    public final byte[] getDg3() {
        return this.dg3;
    }

    /* renamed from: component4, reason: from getter */
    public final byte[] getDg4() {
        return this.dg4;
    }

    /* renamed from: component5, reason: from getter */
    public final byte[] getDg5() {
        return this.dg5;
    }

    /* renamed from: component6, reason: from getter */
    public final byte[] getDg6() {
        return this.dg6;
    }

    /* renamed from: component7, reason: from getter */
    public final byte[] getDg7() {
        return this.dg7;
    }

    /* renamed from: component8, reason: from getter */
    public final byte[] getDg8() {
        return this.dg8;
    }

    /* renamed from: component9, reason: from getter */
    public final byte[] getDg9() {
        return this.dg9;
    }

    public final MRTDData copy(byte[] dg1, byte[] dg2, byte[] dg3, byte[] dg4, byte[] dg5, byte[] dg6, byte[] dg7, byte[] dg8, byte[] dg9, byte[] dg10, byte[] dg11, byte[] dg12, byte[] dg13, byte[] dg14, byte[] dg15, byte[] dg16, byte[] com2, byte[] sod, byte[] aaResponse) {
        Intrinsics.checkNotNullParameter(dg1, "dg1");
        Intrinsics.checkNotNullParameter(dg2, "dg2");
        return new MRTDData(dg1, dg2, dg3, dg4, dg5, dg6, dg7, dg8, dg9, dg10, dg11, dg12, dg13, dg14, dg15, dg16, com2, sod, aaResponse);
    }

    public final byte[] getAaResponse() {
        return this.aaResponse;
    }

    public final byte[] getCom() {
        return this.com;
    }

    public final byte[] getDg1() {
        return this.dg1;
    }

    public final byte[] getDg10() {
        return this.dg10;
    }

    public final byte[] getDg11() {
        return this.dg11;
    }

    public final byte[] getDg12() {
        return this.dg12;
    }

    public final byte[] getDg13() {
        return this.dg13;
    }

    public final byte[] getDg14() {
        return this.dg14;
    }

    public final byte[] getDg15() {
        return this.dg15;
    }

    public final byte[] getDg16() {
        return this.dg16;
    }

    public final byte[] getDg2() {
        return this.dg2;
    }

    public final byte[] getDg3() {
        return this.dg3;
    }

    public final byte[] getDg4() {
        return this.dg4;
    }

    public final byte[] getDg5() {
        return this.dg5;
    }

    public final byte[] getDg6() {
        return this.dg6;
    }

    public final byte[] getDg7() {
        return this.dg7;
    }

    public final byte[] getDg8() {
        return this.dg8;
    }

    public final byte[] getDg9() {
        return this.dg9;
    }

    public final byte[] getSod() {
        return this.sod;
    }

    public String toString() {
        return "MRTDData(dg1=" + Arrays.toString(this.dg1) + ", dg2=" + Arrays.toString(this.dg2) + ", dg3=" + Arrays.toString(this.dg3) + ", dg4=" + Arrays.toString(this.dg4) + ", dg5=" + Arrays.toString(this.dg5) + ", dg6=" + Arrays.toString(this.dg6) + ", dg7=" + Arrays.toString(this.dg7) + ", dg8=" + Arrays.toString(this.dg8) + ", dg9=" + Arrays.toString(this.dg9) + ", dg10=" + Arrays.toString(this.dg10) + ", dg11=" + Arrays.toString(this.dg11) + ", dg12=" + Arrays.toString(this.dg12) + ", dg13=" + Arrays.toString(this.dg13) + ", dg14=" + Arrays.toString(this.dg14) + ", dg15=" + Arrays.toString(this.dg15) + ", dg16=" + Arrays.toString(this.dg16) + ", com=" + Arrays.toString(this.com) + ", sod=" + Arrays.toString(this.sod) + ", aaResponse=" + Arrays.toString(this.aaResponse) + ")";
    }

    public MRTDData(byte[] dg1, byte[] dg2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7, byte[] bArr8, byte[] bArr9, byte[] bArr10, byte[] bArr11, byte[] bArr12, byte[] bArr13, byte[] bArr14, byte[] bArr15, byte[] bArr16, byte[] bArr17) {
        Intrinsics.checkNotNullParameter(dg1, "dg1");
        Intrinsics.checkNotNullParameter(dg2, "dg2");
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
        this.com = bArr15;
        this.sod = bArr16;
        this.aaResponse = bArr17;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.onfido.android.sdk.capture.nfc.MRTDData");
        MRTDData mRTDData = (MRTDData) other;
        if (!Arrays.equals(this.dg1, mRTDData.dg1) || !Arrays.equals(this.dg2, mRTDData.dg2)) {
            return false;
        }
        byte[] bArr = this.dg3;
        if (bArr != null) {
            byte[] bArr2 = mRTDData.dg3;
            if (bArr2 == null || !Arrays.equals(bArr, bArr2)) {
                return false;
            }
        } else if (mRTDData.dg3 != null) {
            return false;
        }
        byte[] bArr3 = this.dg4;
        if (bArr3 != null) {
            byte[] bArr4 = mRTDData.dg4;
            if (bArr4 == null || !Arrays.equals(bArr3, bArr4)) {
                return false;
            }
        } else if (mRTDData.dg4 != null) {
            return false;
        }
        if (this.dg5 != null) {
            byte[] bArr5 = mRTDData.dg5;
            if (bArr5 == null || !Arrays.equals(this.dg3, bArr5)) {
                return false;
            }
        } else if (mRTDData.dg5 != null) {
            return false;
        }
        byte[] bArr6 = this.dg6;
        if (bArr6 != null) {
            byte[] bArr7 = mRTDData.dg6;
            if (bArr7 == null || !Arrays.equals(bArr6, bArr7)) {
                return false;
            }
        } else if (mRTDData.dg6 != null) {
            return false;
        }
        byte[] bArr8 = this.dg7;
        if (bArr8 != null) {
            byte[] bArr9 = mRTDData.dg7;
            if (bArr9 == null || !Arrays.equals(bArr8, bArr9)) {
                return false;
            }
        } else if (mRTDData.dg7 != null) {
            return false;
        }
        byte[] bArr10 = this.dg8;
        if (bArr10 != null) {
            byte[] bArr11 = mRTDData.dg8;
            if (bArr11 == null || !Arrays.equals(bArr10, bArr11)) {
                return false;
            }
        } else if (mRTDData.dg8 != null) {
            return false;
        }
        byte[] bArr12 = this.dg9;
        if (bArr12 != null) {
            byte[] bArr13 = mRTDData.dg9;
            if (bArr13 == null || !Arrays.equals(bArr12, bArr13)) {
                return false;
            }
        } else if (mRTDData.dg9 != null) {
            return false;
        }
        byte[] bArr14 = this.dg10;
        if (bArr14 != null) {
            byte[] bArr15 = mRTDData.dg10;
            if (bArr15 == null || !Arrays.equals(bArr14, bArr15)) {
                return false;
            }
        } else if (mRTDData.dg10 != null) {
            return false;
        }
        byte[] bArr16 = this.dg11;
        if (bArr16 != null) {
            byte[] bArr17 = mRTDData.dg11;
            if (bArr17 == null || !Arrays.equals(bArr16, bArr17)) {
                return false;
            }
        } else if (mRTDData.dg11 != null) {
            return false;
        }
        byte[] bArr18 = this.dg12;
        if (bArr18 != null) {
            byte[] bArr19 = mRTDData.dg12;
            if (bArr19 == null || !Arrays.equals(bArr18, bArr19)) {
                return false;
            }
        } else if (mRTDData.dg12 != null) {
            return false;
        }
        byte[] bArr20 = this.dg13;
        if (bArr20 != null) {
            byte[] bArr21 = mRTDData.dg13;
            if (bArr21 == null || !Arrays.equals(bArr20, bArr21)) {
                return false;
            }
        } else if (mRTDData.dg13 != null) {
            return false;
        }
        byte[] bArr22 = this.dg14;
        if (bArr22 != null) {
            byte[] bArr23 = mRTDData.dg14;
            if (bArr23 == null || !Arrays.equals(bArr22, bArr23)) {
                return false;
            }
        } else if (mRTDData.dg14 != null) {
            return false;
        }
        byte[] bArr24 = this.dg15;
        if (bArr24 != null) {
            byte[] bArr25 = mRTDData.dg15;
            if (bArr25 == null || !Arrays.equals(bArr24, bArr25)) {
                return false;
            }
        } else if (mRTDData.dg15 != null) {
            return false;
        }
        byte[] bArr26 = this.dg16;
        if (bArr26 != null) {
            byte[] bArr27 = mRTDData.dg16;
            if (bArr27 == null || !Arrays.equals(bArr26, bArr27)) {
                return false;
            }
        } else if (mRTDData.dg16 != null) {
            return false;
        }
        byte[] bArr28 = this.com;
        if (bArr28 != null) {
            byte[] bArr29 = mRTDData.com;
            if (bArr29 == null || !Arrays.equals(bArr28, bArr29)) {
                return false;
            }
        } else if (mRTDData.com != null) {
            return false;
        }
        byte[] bArr30 = this.sod;
        if (bArr30 != null) {
            byte[] bArr31 = mRTDData.sod;
            if (bArr31 == null || !Arrays.equals(bArr30, bArr31)) {
                return false;
            }
        } else if (mRTDData.sod != null) {
            return false;
        }
        byte[] bArr32 = this.aaResponse;
        if (bArr32 != null) {
            byte[] bArr33 = mRTDData.aaResponse;
            if (bArr33 == null || !Arrays.equals(bArr32, bArr33)) {
                return false;
            }
        } else if (mRTDData.aaResponse != null) {
            return false;
        }
        return true;
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
        int iHashCode15 = (iHashCode14 + (bArr14 != null ? Arrays.hashCode(bArr14) : 0)) * 31;
        byte[] bArr15 = this.com;
        int iHashCode16 = (iHashCode15 + (bArr15 != null ? Arrays.hashCode(bArr15) : 0)) * 31;
        byte[] bArr16 = this.sod;
        int iHashCode17 = (iHashCode16 + (bArr16 != null ? Arrays.hashCode(bArr16) : 0)) * 31;
        byte[] bArr17 = this.aaResponse;
        return iHashCode17 + (bArr17 != null ? Arrays.hashCode(bArr17) : 0);
    }
}
