package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;
import com.onfido.android.sdk.capture.ui.widget.ShadowedScrollView;
import com.onfido.android.sdk.capture.ui.widget.WatermarkView;

/* loaded from: classes2.dex */
public final class OnfidoFragmentPoaDocumentSubmissionBinding implements ViewBinding {
    public final LinearLayout poaButtonsContainer;
    public final ConstraintLayout poaCloseButton;
    public final ImageView poaCollapseIcon;
    public final ImageView poaDocumentImage;
    public final PoaZoomClass poaDocumentImageLarge;
    public final TextView poaDocumentSubmissionTv;
    public final TextView poaDocumentZoomIndications;
    public final ConstraintLayout poaEnlargeButton;
    public final ImageView poaEnlargeIcon;
    public final TextView poaEnlargeText;
    public final Group poaImageLargeGroup;
    public final ShadowedScrollView poaImageSmallGroup;
    public final OnfidoButton poaRepeatButton;
    public final TextView poaStopText;
    public final OnfidoButton poaSubmitDocumentButton;
    public final WatermarkView poaWatermark;
    private final ConstraintLayout rootView;

    private OnfidoFragmentPoaDocumentSubmissionBinding(ConstraintLayout constraintLayout, LinearLayout linearLayout, ConstraintLayout constraintLayout2, ImageView imageView, ImageView imageView2, PoaZoomClass poaZoomClass, TextView textView, TextView textView2, ConstraintLayout constraintLayout3, ImageView imageView3, TextView textView3, Group group, ShadowedScrollView shadowedScrollView, OnfidoButton onfidoButton, TextView textView4, OnfidoButton onfidoButton2, WatermarkView watermarkView) {
        this.rootView = constraintLayout;
        this.poaButtonsContainer = linearLayout;
        this.poaCloseButton = constraintLayout2;
        this.poaCollapseIcon = imageView;
        this.poaDocumentImage = imageView2;
        this.poaDocumentImageLarge = poaZoomClass;
        this.poaDocumentSubmissionTv = textView;
        this.poaDocumentZoomIndications = textView2;
        this.poaEnlargeButton = constraintLayout3;
        this.poaEnlargeIcon = imageView3;
        this.poaEnlargeText = textView3;
        this.poaImageLargeGroup = group;
        this.poaImageSmallGroup = shadowedScrollView;
        this.poaRepeatButton = onfidoButton;
        this.poaStopText = textView4;
        this.poaSubmitDocumentButton = onfidoButton2;
        this.poaWatermark = watermarkView;
    }

    public static OnfidoFragmentPoaDocumentSubmissionBinding bind(View view) {
        int i = R.id.poaButtonsContainer;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.poaCloseButton;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
            if (constraintLayout != null) {
                i = R.id.poaCollapseIcon;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R.id.poaDocumentImage;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView2 != null) {
                        i = R.id.poaDocumentImageLarge;
                        PoaZoomClass poaZoomClass = (PoaZoomClass) ViewBindings.findChildViewById(view, i);
                        if (poaZoomClass != null) {
                            i = R.id.poaDocumentSubmissionTv;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView != null) {
                                i = R.id.poaDocumentZoomIndications;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView2 != null) {
                                    i = R.id.poaEnlargeButton;
                                    ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                    if (constraintLayout2 != null) {
                                        i = R.id.poaEnlargeIcon;
                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                                        if (imageView3 != null) {
                                            i = R.id.poaEnlargeText;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView3 != null) {
                                                i = R.id.poaImageLargeGroup;
                                                Group group = (Group) ViewBindings.findChildViewById(view, i);
                                                if (group != null) {
                                                    i = R.id.poa_image_small_group;
                                                    ShadowedScrollView shadowedScrollView = (ShadowedScrollView) ViewBindings.findChildViewById(view, i);
                                                    if (shadowedScrollView != null) {
                                                        i = R.id.poaRepeatButton;
                                                        OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
                                                        if (onfidoButton != null) {
                                                            i = R.id.poaStopText;
                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                                            if (textView4 != null) {
                                                                i = R.id.poaSubmitDocumentButton;
                                                                OnfidoButton onfidoButton2 = (OnfidoButton) ViewBindings.findChildViewById(view, i);
                                                                if (onfidoButton2 != null) {
                                                                    i = R.id.poaWatermark;
                                                                    WatermarkView watermarkView = (WatermarkView) ViewBindings.findChildViewById(view, i);
                                                                    if (watermarkView != null) {
                                                                        return new OnfidoFragmentPoaDocumentSubmissionBinding((ConstraintLayout) view, linearLayout, constraintLayout, imageView, imageView2, poaZoomClass, textView, textView2, constraintLayout2, imageView3, textView3, group, shadowedScrollView, onfidoButton, textView4, onfidoButton2, watermarkView);
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
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentPoaDocumentSubmissionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentPoaDocumentSubmissionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_poa_document_submission, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
