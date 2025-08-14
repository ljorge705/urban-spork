package com.onfido.workflow.internal.ui;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment;
import com.onfido.android.sdk.capture.internal.navigation.Screen;
import com.onfido.android.sdk.capture.ui.options.MotionCaptureVariantOptions;
import io.sentry.rrweb.RRWebOptionsEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Screens.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\t\u0010\t\u001a\u00020\nHÖ\u0001J\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nHÖ\u0001R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/onfido/workflow/internal/ui/MotionScreen;", "Lcom/onfido/android/sdk/capture/internal/navigation/Screen;", RRWebOptionsEvent.EVENT_TAG, "Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;", "isInWorkflow", "", "(Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;Z)V", "createFragment", "Landroidx/fragment/app/Fragment;", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class MotionScreen implements Screen {
    public static final String KEY_REQUEST = "request_key_motion";
    private final boolean isInWorkflow;
    private final MotionCaptureVariantOptions options;
    public static final Parcelable.Creator<MotionScreen> CREATOR = new Creator();

    /* compiled from: Screens.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<MotionScreen> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final MotionScreen createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new MotionScreen((MotionCaptureVariantOptions) parcel.readSerializable(), parcel.readInt() != 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final MotionScreen[] newArray(int i) {
            return new MotionScreen[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeSerializable(this.options);
        parcel.writeInt(this.isInWorkflow ? 1 : 0);
    }

    public MotionScreen(MotionCaptureVariantOptions options, boolean z) {
        Intrinsics.checkNotNullParameter(options, "options");
        this.options = options;
        this.isInWorkflow = z;
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public String screenKey() {
        return Screen.DefaultImpls.screenKey(this);
    }

    public /* synthetic */ MotionScreen(MotionCaptureVariantOptions motionCaptureVariantOptions, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(motionCaptureVariantOptions, (i & 2) != 0 ? true : z);
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public Fragment createFragment() {
        return MotionHostFragment.INSTANCE.createInstance(KEY_REQUEST, this.options, this.isInWorkflow);
    }
}
