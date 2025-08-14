package com.clevertap.android.sdk.inapp;

import androidx.fragment.app.FragmentManager;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.Utils;

/* loaded from: classes5.dex */
public abstract class CTInAppBasePartialFragment extends CTInAppBaseFragment {
    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (this.isCleanedUp.get()) {
            cleanup();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.clevertap.android.sdk.inapp.CTInAppBaseFragment
    void cleanup() {
        FragmentManager fragmentManager;
        if (!Utils.isActivityDead(getActivity()) && !this.isCleanedUp.get() && (fragmentManager = getFragmentManager()) != null) {
            try {
                fragmentManager.beginTransaction().remove(this).commit();
            } catch (IllegalStateException unused) {
                fragmentManager.beginTransaction().remove(this).commitAllowingStateLoss();
            }
        }
        this.isCleanedUp.set(true);
    }

    @Override // com.clevertap.android.sdk.inapp.CTInAppBaseFragment
    void generateListener() {
        if (this.config != null) {
            setListener(CleverTapAPI.instanceWithConfig(this.context, this.config).getCoreState().getInAppController());
        }
    }
}
