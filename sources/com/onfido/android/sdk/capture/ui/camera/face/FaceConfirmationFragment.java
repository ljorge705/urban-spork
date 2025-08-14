package com.onfido.android.sdk.capture.ui.camera.face;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentFaceConfirmationBinding;
import com.onfido.android.sdk.capture.errors.ErrorDescriptor;
import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import com.onfido.android.sdk.capture.ui.BaseFragment;
import com.onfido.android.sdk.capture.ui.camera.CaptureConfirmationScreen;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.android.sdk.capture.utils.MatrixExtensionsKt;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble;
import com.onfido.javax.inject.Inject;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0013\u001a\u00020\nH\u0002J$\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u001a\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010 \u001a\u00020\u001dH\u0016J\b\u0010!\u001a\u00020\u001dH\u0002J\u0010\u0010\"\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\u001dH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u00020\u000e8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006&"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/face/FaceConfirmationFragment;", "Lcom/onfido/android/sdk/capture/ui/BaseFragment;", "Lcom/onfido/android/sdk/capture/ui/camera/CaptureConfirmationScreen;", "()V", "_binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentFaceConfirmationBinding;", "binding", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentFaceConfirmationBinding;", "fragmentContainer", "Lcom/onfido/android/sdk/capture/ui/camera/face/FaceConfirmationFragmentContainer;", "getFragmentContainer", "()Lcom/onfido/android/sdk/capture/ui/camera/face/FaceConfirmationFragmentContainer;", "imageUtils", "Lcom/onfido/android/sdk/capture/utils/ImageUtils;", "getImageUtils$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/utils/ImageUtils;", "setImageUtils$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/utils/ImageUtils;)V", "getFaceConfirmationFragmentContainer", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "", "onViewCreated", "view", "setForceRetryButton", "setupView", "showError", "descriptor", "Lcom/onfido/android/sdk/capture/errors/ErrorDescriptor;", "showSelfiePreview", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FaceConfirmationFragment extends BaseFragment implements CaptureConfirmationScreen {
    private OnfidoFragmentFaceConfirmationBinding _binding;

    @Inject
    public ImageUtils imageUtils;

    private final OnfidoFragmentFaceConfirmationBinding getBinding() {
        OnfidoFragmentFaceConfirmationBinding onfidoFragmentFaceConfirmationBinding = this._binding;
        if (onfidoFragmentFaceConfirmationBinding != null) {
            return onfidoFragmentFaceConfirmationBinding;
        }
        throw new UninitializedPropertyAccessException("Binding is not initialized yet.");
    }

    private final FaceConfirmationFragmentContainer getFaceConfirmationFragmentContainer() {
        Object objRequireParentFragment;
        if (getActivity() instanceof FaceConfirmationFragmentContainer) {
            objRequireParentFragment = requireActivity();
        } else {
            if (!(getParentFragment() instanceof FaceConfirmationFragmentContainer)) {
                throw new UnsupportedOperationException("Activity/ParentFragment should implement '" + Reflection.getOrCreateKotlinClass(FaceConfirmationFragmentContainer.class) + "'.");
            }
            objRequireParentFragment = requireParentFragment();
        }
        Intrinsics.checkNotNull(objRequireParentFragment, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.camera.face.FaceConfirmationFragmentContainer");
        return (FaceConfirmationFragmentContainer) objRequireParentFragment;
    }

    private final FaceConfirmationFragmentContainer getFragmentContainer() {
        return getFaceConfirmationFragmentContainer();
    }

    private final void setupView() {
        OnfidoFragmentFaceConfirmationBinding binding = getBinding();
        ImageView previewImageView = binding.previewImageView;
        Intrinsics.checkNotNullExpressionValue(previewImageView, "previewImageView");
        if (!ViewCompat.isLaidOut(previewImageView) || previewImageView.isLayoutRequested()) {
            previewImageView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.onfido.android.sdk.capture.ui.camera.face.FaceConfirmationFragment$setupView$lambda$4$$inlined$doOnLayout$1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.removeOnLayoutChangeListener(this);
                    this.this$0.showSelfiePreview();
                }
            });
        } else {
            showSelfiePreview();
        }
        binding.uploadPhotoButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.camera.face.FaceConfirmationFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FaceConfirmationFragment.setupView$lambda$4$lambda$1(this.f$0, view);
            }
        });
        binding.retakePhotoButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.camera.face.FaceConfirmationFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FaceConfirmationFragment.setupView$lambda$4$lambda$2(this.f$0, view);
            }
        });
        binding.forceRetakePhotoButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.camera.face.FaceConfirmationFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FaceConfirmationFragment.setupView$lambda$4$lambda$3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupView$lambda$4$lambda$1(FaceConfirmationFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getFragmentContainer().onUploadSelfieButtonClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupView$lambda$4$lambda$2(FaceConfirmationFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getFragmentContainer().onRetakeSelfieButtonClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupView$lambda$4$lambda$3(FaceConfirmationFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getFragmentContainer().onRetakeSelfieButtonClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSelfiePreview() {
        OnfidoImage previewImage = getFragmentContainer().getPreviewImage();
        if (previewImage == null) {
            return;
        }
        ImageView previewImageView = getBinding().previewImageView;
        Intrinsics.checkNotNullExpressionValue(previewImageView, "previewImageView");
        Bitmap bitmapDecodeScaledResource$default = ImageUtils.decodeScaledResource$default(getImageUtils$onfido_capture_sdk_core_release(), previewImage.getData(), previewImageView.getMeasuredWidth(), previewImageView.getMeasuredHeight(), null, new Function2<Matrix, Bitmap, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.face.FaceConfirmationFragment$showSelfiePreview$previewImageBitmap$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Matrix matrix, Bitmap bitmap) {
                invoke2(matrix, bitmap);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Matrix matrix, Bitmap bitmap) {
                Intrinsics.checkNotNullParameter(matrix, "matrix");
                Intrinsics.checkNotNullParameter(bitmap, "bitmap");
                MatrixExtensionsKt.postHorizontalFlip(matrix, bitmap.getWidth(), bitmap.getHeight());
            }
        }, 8, null);
        ImageUtils imageUtils$onfido_capture_sdk_core_release = getImageUtils$onfido_capture_sdk_core_release();
        Resources resources = getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "getResources(...)");
        previewImageView.setImageBitmap(imageUtils$onfido_capture_sdk_core_release.roundBitmap(bitmapDecodeScaledResource$default, resources, getResources().getDimension(R.dimen.onfido_spacing_1x)));
    }

    public final ImageUtils getImageUtils$onfido_capture_sdk_core_release() {
        ImageUtils imageUtils = this.imageUtils;
        if (imageUtils != null) {
            return imageUtils;
        }
        Intrinsics.throwUninitializedPropertyAccessException("imageUtils");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = OnfidoFragmentFaceConfirmationBinding.inflate(getLayoutInflater(), container, false);
        ConstraintLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this._binding = null;
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        SdkController companion = SdkController.INSTANCE.getInstance();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        SdkController.getSdkComponent$default(companion, contextRequireContext, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        super.onViewCreated(view, savedInstanceState);
        setupView();
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CaptureConfirmationScreen
    public void setForceRetryButton() {
        OnfidoFragmentFaceConfirmationBinding binding = getBinding();
        OnfidoButton uploadPhotoButton = binding.uploadPhotoButton;
        Intrinsics.checkNotNullExpressionValue(uploadPhotoButton, "uploadPhotoButton");
        ViewExtensionsKt.toGone$default(uploadPhotoButton, false, 1, null);
        OnfidoButton retakePhotoButton = binding.retakePhotoButton;
        Intrinsics.checkNotNullExpressionValue(retakePhotoButton, "retakePhotoButton");
        ViewExtensionsKt.toGone$default(retakePhotoButton, false, 1, null);
        OnfidoButton forceRetakePhotoButton = binding.forceRetakePhotoButton;
        Intrinsics.checkNotNullExpressionValue(forceRetakePhotoButton, "forceRetakePhotoButton");
        ViewExtensionsKt.toVisible$default(forceRetakePhotoButton, false, 1, null);
    }

    public final void setImageUtils$onfido_capture_sdk_core_release(ImageUtils imageUtils) {
        Intrinsics.checkNotNullParameter(imageUtils, "<set-?>");
        this.imageUtils = imageUtils;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CaptureConfirmationScreen
    public void showError(ErrorDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        TextView headerText = getBinding().headerText;
        Intrinsics.checkNotNullExpressionValue(headerText, "headerText");
        ViewExtensionsKt.toGone$default(headerText, false, 1, null);
        OnfidoCaptureValidationBubble onfidoCaptureValidationBubble = getBinding().captureValidationBubble;
        Intrinsics.checkNotNull(onfidoCaptureValidationBubble);
        ViewExtensionsKt.toVisible$default(onfidoCaptureValidationBubble, false, 1, null);
        OnfidoCaptureValidationBubble.setContent$onfido_capture_sdk_core_release$default(onfidoCaptureValidationBubble, new OnfidoCaptureValidationBubble.Content.Error(descriptor.getTitle(), descriptor.getSubtitle()), false, 2, null);
        OnfidoCaptureValidationBubble.setVisibilityCommand$onfido_capture_sdk_core_release$default(onfidoCaptureValidationBubble, new OnfidoCaptureValidationBubble.VisibilityCommand.Visible(new OnfidoCaptureValidationBubble.FocusType.AnnounceContent(false, 1, null)), false, 2, null);
    }
}
