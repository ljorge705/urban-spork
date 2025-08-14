package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoVideoViewBinding implements ViewBinding {
    public final ImageButton play;
    private final View rootView;
    public final TextureView videoView;

    private OnfidoVideoViewBinding(View view, ImageButton imageButton, TextureView textureView) {
        this.rootView = view;
        this.play = imageButton;
        this.videoView = textureView;
    }

    public static OnfidoVideoViewBinding bind(View view) {
        int i = R.id.play;
        ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(view, i);
        if (imageButton != null) {
            i = R.id.videoView;
            TextureView textureView = (TextureView) ViewBindings.findChildViewById(view, i);
            if (textureView != null) {
                return new OnfidoVideoViewBinding(view, imageButton, textureView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoVideoViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (viewGroup == null) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.onfido_video_view, viewGroup);
        return bind(viewGroup);
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }
}
