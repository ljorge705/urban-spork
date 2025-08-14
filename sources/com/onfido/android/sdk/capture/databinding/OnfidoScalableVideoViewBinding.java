package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoScalableVideoViewBinding implements ViewBinding {
    public final ImageButton play;
    private final FrameLayout rootView;
    public final TextureView videoView;

    private OnfidoScalableVideoViewBinding(FrameLayout frameLayout, ImageButton imageButton, TextureView textureView) {
        this.rootView = frameLayout;
        this.play = imageButton;
        this.videoView = textureView;
    }

    public static OnfidoScalableVideoViewBinding bind(View view) {
        int i = R.id.play;
        ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(view, i);
        if (imageButton != null) {
            i = R.id.videoView;
            TextureView textureView = (TextureView) ViewBindings.findChildViewById(view, i);
            if (textureView != null) {
                return new OnfidoScalableVideoViewBinding((FrameLayout) view, imageButton, textureView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoScalableVideoViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoScalableVideoViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_scalable_video_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
