package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;
import com.onfido.android.sdk.capture.ui.widget.ShadowedScrollView;
import com.onfido.android.sdk.capture.ui.widget.WatermarkView;

/* loaded from: classes2.dex */
public final class OnfidoFragmentPoaDocumentDetailsBinding implements ViewBinding {
    public final LinearLayout poaButtonsContainer;
    public final TextView poaCaptureBullet1;
    public final TextView poaCaptureBullet2;
    public final TextView poaCaptureBullet3;
    public final TextView poaCaptureBullet4;
    public final TextView poaCaptureTv;
    public final TextView poaDocumentDetailsSubTitle;
    public final TextView poaDocumentDetailsTitle;
    public final ImageView poaInfoIcon;
    public final WatermarkView poaWatermark;
    private final ShadowedScrollView rootView;
    public final OnfidoButton takePhotoButton;
    public final OnfidoButton uploadButton;

    private OnfidoFragmentPoaDocumentDetailsBinding(ShadowedScrollView shadowedScrollView, LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, ImageView imageView, WatermarkView watermarkView, OnfidoButton onfidoButton, OnfidoButton onfidoButton2) {
        this.rootView = shadowedScrollView;
        this.poaButtonsContainer = linearLayout;
        this.poaCaptureBullet1 = textView;
        this.poaCaptureBullet2 = textView2;
        this.poaCaptureBullet3 = textView3;
        this.poaCaptureBullet4 = textView4;
        this.poaCaptureTv = textView5;
        this.poaDocumentDetailsSubTitle = textView6;
        this.poaDocumentDetailsTitle = textView7;
        this.poaInfoIcon = imageView;
        this.poaWatermark = watermarkView;
        this.takePhotoButton = onfidoButton;
        this.uploadButton = onfidoButton2;
    }

    public static OnfidoFragmentPoaDocumentDetailsBinding bind(View view) {
        int i = R.id.poaButtonsContainer;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.poaCaptureBullet1;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.poaCaptureBullet2;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    i = R.id.poaCaptureBullet3;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView3 != null) {
                        i = R.id.poaCaptureBullet4;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView4 != null) {
                            i = R.id.poaCaptureTv;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView5 != null) {
                                i = R.id.poaDocumentDetailsSubTitle;
                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView6 != null) {
                                    i = R.id.poaDocumentDetailsTitle;
                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView7 != null) {
                                        i = R.id.poaInfoIcon;
                                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                                        if (imageView != null) {
                                            i = R.id.poaWatermark;
                                            WatermarkView watermarkView = (WatermarkView) ViewBindings.findChildViewById(view, i);
                                            if (watermarkView != null) {
                                                i = R.id.takePhotoButton;
                                                OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
                                                if (onfidoButton != null) {
                                                    i = R.id.uploadButton;
                                                    OnfidoButton onfidoButton2 = (OnfidoButton) ViewBindings.findChildViewById(view, i);
                                                    if (onfidoButton2 != null) {
                                                        return new OnfidoFragmentPoaDocumentDetailsBinding((ShadowedScrollView) view, linearLayout, textView, textView2, textView3, textView4, textView5, textView6, textView7, imageView, watermarkView, onfidoButton, onfidoButton2);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentPoaDocumentDetailsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public ShadowedScrollView getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentPoaDocumentDetailsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_poa_document_details, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
