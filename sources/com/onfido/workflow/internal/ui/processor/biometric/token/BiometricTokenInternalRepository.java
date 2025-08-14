package com.onfido.workflow.internal.ui.processor.biometric.token;

import android.content.Context;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Action;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KTypeProjection;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;

/* compiled from: BiometricTokenInternalRepository.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenInternalRepository;", "Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenRepository;", "appContext", "Landroid/content/Context;", "parser", "Lkotlinx/serialization/json/Json;", "(Landroid/content/Context;Lkotlinx/serialization/json/Json;)V", "getType", "Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenRepositoryType;", "retrieveToken", "Lio/reactivex/rxjava3/core/Observable;", "", "customerUserHash", "storeToken", "Lio/reactivex/rxjava3/core/Completable;", "biometricToken", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class BiometricTokenInternalRepository implements BiometricTokenRepository {
    public static final String BIOMETRIC_TOKEN_STORAGE_FILE_NAME = "ebt_storage.json";
    private final Context appContext;
    private final Json parser;

    @Inject
    public BiometricTokenInternalRepository(Context appContext, Json parser) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(parser, "parser");
        this.appContext = appContext;
        this.parser = parser;
    }

    @Override // com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenRepository
    public Completable storeToken(final String customerUserHash, final String biometricToken) {
        Intrinsics.checkNotNullParameter(customerUserHash, "customerUserHash");
        Intrinsics.checkNotNullParameter(biometricToken, "biometricToken");
        Completable completableFromAction = Completable.fromAction(new Action() { // from class: com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenInternalRepository$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                BiometricTokenInternalRepository.storeToken$lambda$0(this.f$0, customerUserHash, biometricToken);
            }
        });
        Intrinsics.checkNotNullExpressionValue(completableFromAction, "fromAction(...)");
        return completableFromAction;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void storeToken$lambda$0(BiometricTokenInternalRepository this$0, String customerUserHash, String biometricToken) {
        LinkedHashMap linkedHashMap;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(customerUserHash, "$customerUserHash");
        Intrinsics.checkNotNullParameter(biometricToken, "$biometricToken");
        File file = new File(this$0.appContext.getFilesDir(), BIOMETRIC_TOKEN_STORAGE_FILE_NAME);
        if (file.exists()) {
            Json json = this$0.parser;
            linkedHashMap = MapsKt.toMutableMap((Map) json.decodeFromString(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(Map.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class)), KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class)))), FilesKt.readText$default(file, null, 1, null)));
        } else {
            linkedHashMap = new LinkedHashMap();
        }
        linkedHashMap.put(customerUserHash, biometricToken);
        Json json2 = this$0.parser;
        FilesKt.writeText$default(file, json2.encodeToString(SerializersKt.serializer(json2.getSerializersModule(), Reflection.mutableCollectionType(Reflection.typeOf(Map.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class)), KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class))))), linkedHashMap), null, 2, null);
    }

    @Override // com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenRepository
    public Observable<String> retrieveToken(final String customerUserHash) {
        Intrinsics.checkNotNullParameter(customerUserHash, "customerUserHash");
        Observable<String> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenInternalRepository$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                BiometricTokenInternalRepository.retrieveToken$lambda$1(this.f$0, customerUserHash, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create(...)");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void retrieveToken$lambda$1(BiometricTokenInternalRepository this$0, String customerUserHash, ObservableEmitter emitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(customerUserHash, "$customerUserHash");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        File file = new File(this$0.appContext.getFilesDir(), BIOMETRIC_TOKEN_STORAGE_FILE_NAME);
        if (file.exists()) {
            Json json = this$0.parser;
            String str = (String) ((Map) json.decodeFromString(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(Map.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class)), KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class)))), FilesKt.readText$default(file, null, 1, null))).get(customerUserHash);
            if (str != null) {
                emitter.onNext(str);
            } else {
                emitter.onError(new NoSuchElementException("No biometric token found for the given user hash in internal storage"));
            }
        } else {
            emitter.onError(new FileNotFoundException("Internal biometric token storage file not found"));
        }
        emitter.onComplete();
    }

    @Override // com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenRepository
    public BiometricTokenRepositoryType getType() {
        return BiometricTokenRepositoryType.INTERNAL;
    }
}
