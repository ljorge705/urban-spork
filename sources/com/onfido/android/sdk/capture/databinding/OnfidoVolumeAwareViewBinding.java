package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.audio.ExpandablePillView;

/* loaded from: classes2.dex */
public final class OnfidoVolumeAwareViewBinding implements ViewBinding {
    public final ExpandablePillView pillView;
    private final RelativeLayout rootView;
    public final RelativeLayout speaker;
    public final TextView turnSoundOn;

    private OnfidoVolumeAwareViewBinding(RelativeLayout relativeLayout, ExpandablePillView expandablePillView, RelativeLayout relativeLayout2, TextView textView) {
        this.rootView = relativeLayout;
        this.pillView = expandablePillView;
        this.speaker = relativeLayout2;
        this.turnSoundOn = textView;
    }

    public static OnfidoVolumeAwareViewBinding bind(View view) {
        int i = R.id.pillView;
        ExpandablePillView expandablePillView = (ExpandablePillView) ViewBindings.findChildViewById(view, i);
        if (expandablePillView != null) {
            i = R.id.speaker;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, i);
            if (relativeLayout != null) {
                i = R.id.turnSoundOn;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    return new OnfidoVolumeAwareViewBinding((RelativeLayout) view, expandablePillView, relativeLayout, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoVolumeAwareViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoVolumeAwareViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_volume_aware_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
