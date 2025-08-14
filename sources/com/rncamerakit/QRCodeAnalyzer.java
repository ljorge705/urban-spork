package com.rncamerakit;

import android.media.Image;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.media3.common.MimeTypes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QRCodeAnalyzer.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B.\u0012'\u0010\u0002\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0017R/\u0010\u0002\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/rncamerakit/QRCodeAnalyzer;", "Landroidx/camera/core/ImageAnalysis$Analyzer;", "onQRCodesDetected", "Lkotlin/Function1;", "", "", "Lkotlin/ParameterName;", "name", "qrCodes", "", "(Lkotlin/jvm/functions/Function1;)V", "analyze", MimeTypes.BASE_TYPE_IMAGE, "Landroidx/camera/core/ImageProxy;", "react-native-camera-kit_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class QRCodeAnalyzer implements ImageAnalysis.Analyzer {
    private final Function1<List<String>, Unit> onQRCodesDetected;

    /* JADX WARN: Multi-variable type inference failed */
    public QRCodeAnalyzer(Function1<? super List<String>, Unit> onQRCodesDetected) {
        Intrinsics.checkNotNullParameter(onQRCodesDetected, "onQRCodesDetected");
        this.onQRCodesDetected = onQRCodesDetected;
    }

    @Override // androidx.camera.core.ImageAnalysis.Analyzer
    public void analyze(final ImageProxy image) {
        Intrinsics.checkNotNullParameter(image, "image");
        Image image2 = image.getImage();
        Intrinsics.checkNotNull(image2);
        InputImage inputImageFromMediaImage = InputImage.fromMediaImage(image2, image.getImageInfo().getRotationDegrees());
        Intrinsics.checkNotNullExpressionValue(inputImageFromMediaImage, "fromMediaImage(...)");
        BarcodeScanner client = BarcodeScanning.getClient();
        Intrinsics.checkNotNullExpressionValue(client, "getClient(...)");
        Task<List<Barcode>> taskProcess = client.process(inputImageFromMediaImage);
        final Function1<List<Barcode>, Unit> function1 = new Function1<List<Barcode>, Unit>() { // from class: com.rncamerakit.QRCodeAnalyzer.analyze.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<Barcode> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<Barcode> list) {
                ArrayList arrayList = new ArrayList();
                Intrinsics.checkNotNull(list);
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    String rawValue = ((Barcode) it.next()).getRawValue();
                    if (rawValue != null) {
                        Intrinsics.checkNotNull(rawValue);
                        arrayList.add(rawValue);
                    }
                }
                QRCodeAnalyzer.this.onQRCodesDetected.invoke(arrayList);
            }
        };
        taskProcess.addOnSuccessListener(new OnSuccessListener() { // from class: com.rncamerakit.QRCodeAnalyzer$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                QRCodeAnalyzer.analyze$lambda$0(function1, obj);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: com.rncamerakit.QRCodeAnalyzer$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                QRCodeAnalyzer.analyze$lambda$1(image, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void analyze$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void analyze$lambda$1(ImageProxy image, Task it) {
        Intrinsics.checkNotNullParameter(image, "$image");
        Intrinsics.checkNotNullParameter(it, "it");
        image.close();
    }
}
