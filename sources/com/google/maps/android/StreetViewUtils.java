package com.google.maps.android;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.dynamiclinks.DynamicLink;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONObject;

/* compiled from: StreetViewUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/google/maps/android/StreetViewUtils;", "", "()V", "Companion", "library_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class StreetViewUtils {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: StreetViewUtil.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J+\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\rH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lcom/google/maps/android/StreetViewUtils$Companion;", "", "()V", "deserializeResponse", "Lcom/google/maps/android/ResponseStreetView;", "responseString", "", "fetchStreetViewData", "Lcom/google/maps/android/Status;", "latLng", "Lcom/google/android/gms/maps/model/LatLng;", DynamicLink.Builder.KEY_API_KEY, "source", "Lcom/google/maps/android/Source;", "(Lcom/google/android/gms/maps/model/LatLng;Ljava/lang/String;Lcom/google/maps/android/Source;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ Object fetchStreetViewData$default(Companion companion, LatLng latLng, String str, Source source, Continuation continuation, int i, Object obj) {
            if ((i & 4) != 0) {
                source = Source.DEFAULT;
            }
            return companion.fetchStreetViewData(latLng, str, source, continuation);
        }

        public final Object fetchStreetViewData(LatLng latLng, String str, Source source, Continuation<? super Status> continuation) {
            StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/streetview/metadata");
            sb.append("?location=" + latLng.latitude + AbstractJsonLexerKt.COMMA + latLng.longitude);
            sb.append("&key=" + str);
            sb.append("&source=" + source.getValue());
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
            return BuildersKt.withContext(Dispatchers.getIO(), new StreetViewUtils$Companion$fetchStreetViewData$2(string, null), continuation);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ResponseStreetView deserializeResponse(String responseString) {
            String statusString = new JSONObject(responseString).optString("status");
            Intrinsics.checkNotNullExpressionValue(statusString, "statusString");
            return new ResponseStreetView(Status.valueOf(statusString));
        }
    }
}
