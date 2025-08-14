package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.document.selection.DocumentSelectionButton;
import com.onfido.android.sdk.capture.ui.widget.ShadowedScrollView;
import com.onfido.android.sdk.capture.ui.widget.WatermarkView;

/* loaded from: classes2.dex */
public final class OnfidoFragmentPoaDocumentSelectionBinding implements ViewBinding {
    public final DocumentSelectionButton poaAddressCard;
    public final View poaAddressCardSeparator;
    public final DocumentSelectionButton poaBank;
    public final View poaBankSeparator;
    public final DocumentSelectionButton poaBenefitsLetter;
    public final View poaBenefitsLetterSeparator;
    public final DocumentSelectionButton poaCouncilTaxLetter;
    public final View poaCouncilTaxLetterSeparator;
    public final LinearLayout poaDocTypes;
    public final TextView poaDocumentSubtitle;
    public final TextView poaDocumentTitle;
    public final View poaSeparator;
    public final DocumentSelectionButton poaUtilityBill;
    public final View poaUtilityBillSeparator;
    public final WatermarkView poaWatermark;
    private final ShadowedScrollView rootView;

    private OnfidoFragmentPoaDocumentSelectionBinding(ShadowedScrollView shadowedScrollView, DocumentSelectionButton documentSelectionButton, View view, DocumentSelectionButton documentSelectionButton2, View view2, DocumentSelectionButton documentSelectionButton3, View view3, DocumentSelectionButton documentSelectionButton4, View view4, LinearLayout linearLayout, TextView textView, TextView textView2, View view5, DocumentSelectionButton documentSelectionButton5, View view6, WatermarkView watermarkView) {
        this.rootView = shadowedScrollView;
        this.poaAddressCard = documentSelectionButton;
        this.poaAddressCardSeparator = view;
        this.poaBank = documentSelectionButton2;
        this.poaBankSeparator = view2;
        this.poaBenefitsLetter = documentSelectionButton3;
        this.poaBenefitsLetterSeparator = view3;
        this.poaCouncilTaxLetter = documentSelectionButton4;
        this.poaCouncilTaxLetterSeparator = view4;
        this.poaDocTypes = linearLayout;
        this.poaDocumentSubtitle = textView;
        this.poaDocumentTitle = textView2;
        this.poaSeparator = view5;
        this.poaUtilityBill = documentSelectionButton5;
        this.poaUtilityBillSeparator = view6;
        this.poaWatermark = watermarkView;
    }

    public static OnfidoFragmentPoaDocumentSelectionBinding bind(View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        View viewFindChildViewById3;
        View viewFindChildViewById4;
        View viewFindChildViewById5;
        View viewFindChildViewById6;
        int i = R.id.poaAddressCard;
        DocumentSelectionButton documentSelectionButton = (DocumentSelectionButton) ViewBindings.findChildViewById(view, i);
        if (documentSelectionButton != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.poaAddressCardSeparator))) != null) {
            i = R.id.poaBank;
            DocumentSelectionButton documentSelectionButton2 = (DocumentSelectionButton) ViewBindings.findChildViewById(view, i);
            if (documentSelectionButton2 != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i = R.id.poaBankSeparator))) != null) {
                i = R.id.poaBenefitsLetter;
                DocumentSelectionButton documentSelectionButton3 = (DocumentSelectionButton) ViewBindings.findChildViewById(view, i);
                if (documentSelectionButton3 != null && (viewFindChildViewById3 = ViewBindings.findChildViewById(view, (i = R.id.poaBenefitsLetterSeparator))) != null) {
                    i = R.id.poaCouncilTaxLetter;
                    DocumentSelectionButton documentSelectionButton4 = (DocumentSelectionButton) ViewBindings.findChildViewById(view, i);
                    if (documentSelectionButton4 != null && (viewFindChildViewById4 = ViewBindings.findChildViewById(view, (i = R.id.poaCouncilTaxLetterSeparator))) != null) {
                        i = R.id.poaDocTypes;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                        if (linearLayout != null) {
                            i = R.id.poaDocumentSubtitle;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView != null) {
                                i = R.id.poaDocumentTitle;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView2 != null && (viewFindChildViewById5 = ViewBindings.findChildViewById(view, (i = R.id.poaSeparator))) != null) {
                                    i = R.id.poaUtilityBill;
                                    DocumentSelectionButton documentSelectionButton5 = (DocumentSelectionButton) ViewBindings.findChildViewById(view, i);
                                    if (documentSelectionButton5 != null && (viewFindChildViewById6 = ViewBindings.findChildViewById(view, (i = R.id.poaUtilityBillSeparator))) != null) {
                                        i = R.id.poaWatermark;
                                        WatermarkView watermarkView = (WatermarkView) ViewBindings.findChildViewById(view, i);
                                        if (watermarkView != null) {
                                            return new OnfidoFragmentPoaDocumentSelectionBinding((ShadowedScrollView) view, documentSelectionButton, viewFindChildViewById, documentSelectionButton2, viewFindChildViewById2, documentSelectionButton3, viewFindChildViewById3, documentSelectionButton4, viewFindChildViewById4, linearLayout, textView, textView2, viewFindChildViewById5, documentSelectionButton5, viewFindChildViewById6, watermarkView);
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

    public static OnfidoFragmentPoaDocumentSelectionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public ShadowedScrollView getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentPoaDocumentSelectionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_poa_document_selection, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
