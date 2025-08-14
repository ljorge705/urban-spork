package com.onfido.android.sdk.capture.utils;

import android.view.View;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u0004H ¢\u0006\u0002\b\fR\u0012\u0010\u0003\u001a\u00020\u0004X \u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/Visibility;", "", "(Ljava/lang/String;I)V", "isAppearing", "", "isAppearing$onfido_capture_sdk_core_release", "()Z", "changeVisibility", "", "view", "Landroid/view/View;", "withAnimation", "changeVisibility$onfido_capture_sdk_core_release", "VISIBLE", "INVISIBLE", "GONE", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class Visibility {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ Visibility[] $VALUES;
    public static final Visibility VISIBLE = new Visibility("VISIBLE", 0) { // from class: com.onfido.android.sdk.capture.utils.Visibility.VISIBLE
        private final boolean isAppearing = true;

        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }

        @Override // com.onfido.android.sdk.capture.utils.Visibility
        public void changeVisibility$onfido_capture_sdk_core_release(View view, boolean withAnimation) {
            Intrinsics.checkNotNullParameter(view, "view");
            ViewExtensionsKt.toVisible(view, withAnimation);
        }

        @Override // com.onfido.android.sdk.capture.utils.Visibility
        /* renamed from: isAppearing$onfido_capture_sdk_core_release, reason: from getter */
        public boolean getIsAppearing() {
            return this.isAppearing;
        }
    };
    public static final Visibility INVISIBLE = new Visibility("INVISIBLE", 1) { // from class: com.onfido.android.sdk.capture.utils.Visibility.INVISIBLE
        private final boolean isAppearing;

        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }

        @Override // com.onfido.android.sdk.capture.utils.Visibility
        public void changeVisibility$onfido_capture_sdk_core_release(View view, boolean withAnimation) {
            Intrinsics.checkNotNullParameter(view, "view");
            ViewExtensionsKt.toInvisible(view, withAnimation);
        }

        @Override // com.onfido.android.sdk.capture.utils.Visibility
        /* renamed from: isAppearing$onfido_capture_sdk_core_release, reason: from getter */
        public boolean getIsAppearing() {
            return this.isAppearing;
        }
    };
    public static final Visibility GONE = new Visibility("GONE", 2) { // from class: com.onfido.android.sdk.capture.utils.Visibility.GONE
        private final boolean isAppearing;

        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }

        @Override // com.onfido.android.sdk.capture.utils.Visibility
        public void changeVisibility$onfido_capture_sdk_core_release(View view, boolean withAnimation) {
            Intrinsics.checkNotNullParameter(view, "view");
            ViewExtensionsKt.toGone(view, withAnimation);
        }

        @Override // com.onfido.android.sdk.capture.utils.Visibility
        /* renamed from: isAppearing$onfido_capture_sdk_core_release, reason: from getter */
        public boolean getIsAppearing() {
            return this.isAppearing;
        }
    };

    private static final /* synthetic */ Visibility[] $values() {
        return new Visibility[]{VISIBLE, INVISIBLE, GONE};
    }

    static {
        Visibility[] visibilityArr$values = $values();
        $VALUES = visibilityArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(visibilityArr$values);
    }

    private Visibility(String str, int i) {
    }

    public static /* synthetic */ void changeVisibility$onfido_capture_sdk_core_release$default(Visibility visibility, View view, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: changeVisibility");
        }
        if ((i & 2) != 0) {
            z = true;
        }
        visibility.changeVisibility$onfido_capture_sdk_core_release(view, z);
    }

    public static EnumEntries<Visibility> getEntries() {
        return $ENTRIES;
    }

    public static Visibility valueOf(String str) {
        return (Visibility) Enum.valueOf(Visibility.class, str);
    }

    public static Visibility[] values() {
        return (Visibility[]) $VALUES.clone();
    }

    public abstract void changeVisibility$onfido_capture_sdk_core_release(View view, boolean withAnimation);

    /* renamed from: isAppearing$onfido_capture_sdk_core_release */
    public abstract boolean getIsAppearing();

    public /* synthetic */ Visibility(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i);
    }
}
