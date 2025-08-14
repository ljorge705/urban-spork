package com.clevertap.react;

import android.net.Uri;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.inapp.evaluation.TriggerAdapter;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;

/* compiled from: CleverTapModule.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b'\n\u0002\u0010\u0006\n\u0002\bD\u0018\u0000 »\u00012\u00020\u0001:\u0002»\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0012\u0010\f\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0007J6\u0010\u000f\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000bH\u0007J\u001c\u0010\u0017\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00112\b\u0010\u0019\u001a\u0004\u0018\u00010\u0011H\u0007J@\u0010\u001a\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0016\u001a\u00020\u000bH\u0007JJ\u0010\u001b\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0016\u001a\u00020\u000b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0011H\u0007J@\u0010\u001d\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0011H\u0007J\u001c\u0010\u001e\u001a\u00020\b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00112\b\u0010 \u001a\u0004\u0018\u00010!H\u0007J&\u0010\"\u001a\u00020\b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00112\b\u0010#\u001a\u0004\u0018\u00010\u00112\b\u0010 \u001a\u0004\u0018\u00010!H\u0007J&\u0010$\u001a\u00020\b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00112\b\u0010#\u001a\u0004\u0018\u00010\u00112\b\u0010 \u001a\u0004\u0018\u00010!H\u0007J&\u0010%\u001a\u00020\b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00112\b\u0010#\u001a\u0004\u0018\u00010\u00112\b\u0010 \u001a\u0004\u0018\u00010!H\u0007J&\u0010&\u001a\u00020\b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00112\b\u0010#\u001a\u0004\u0018\u00010\u00112\b\u0010 \u001a\u0004\u0018\u00010!H\u0007J&\u0010'\u001a\u00020\b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00112\b\u0010#\u001a\u0004\u0018\u00010\u00112\b\u0010 \u001a\u0004\u0018\u00010!H\u0007J&\u0010(\u001a\u00020\b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00112\b\u0010#\u001a\u0004\u0018\u00010\u00112\b\u0010 \u001a\u0004\u0018\u00010!H\u0007J\u001c\u0010)\u001a\u00020\b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00112\b\u0010 \u001a\u0004\u0018\u00010!H\u0007J\u001c\u0010*\u001a\u00020\b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00112\b\u0010 \u001a\u0004\u0018\u00010!H\u0007J\u0010\u0010+\u001a\u00020\b2\u0006\u0010,\u001a\u00020\u0011H\u0007J\u0010\u0010-\u001a\u00020\b2\u0006\u0010.\u001a\u00020\u000eH\u0007J\u0012\u0010/\u001a\u00020\b2\b\u00100\u001a\u0004\u0018\u00010\u0011H\u0007J\u0012\u00101\u001a\u00020\b2\b\u00102\u001a\u0004\u0018\u000103H\u0007J\u0012\u00104\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007J\u0012\u00105\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0011H\u0007J\b\u00106\u001a\u00020\bH\u0007J\b\u00107\u001a\u00020\bH\u0007J\b\u00108\u001a\u00020\bH\u0007J\u0010\u00109\u001a\u00020\b2\u0006\u0010:\u001a\u00020\u000bH\u0007J\b\u0010;\u001a\u00020\bH\u0007J\u001c\u0010<\u001a\u00020\b2\b\u0010=\u001a\u0004\u0018\u00010\u00112\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u001c\u0010@\u001a\u00020\b2\b\u0010=\u001a\u0004\u0018\u00010\u00112\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u001c\u0010A\u001a\u00020\b2\b\u0010=\u001a\u0004\u0018\u00010\u00112\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u001c\u0010B\u001a\u00020\b2\b\u0010=\u001a\u0004\u0018\u00010\u00112\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\b\u0010C\u001a\u00020\bH\u0007J\b\u0010D\u001a\u00020\bH\u0007J\u0012\u0010E\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u0012\u0010F\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u0010\u0010G\u001a\u00020\b2\u0006\u0010H\u001a\u00020\u0015H\u0007J\u0012\u0010I\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u0012\u0010J\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u001c\u0010K\u001a\u00020\b2\b\u0010L\u001a\u0004\u0018\u00010\u00112\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u0012\u0010M\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u0014\u0010N\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020P0OH\u0016J\u001c\u0010Q\u001a\u00020\b2\b\u0010R\u001a\u0004\u0018\u00010\u00112\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u001c\u0010S\u001a\u00020\b2\b\u0010L\u001a\u0004\u0018\u00010\u00112\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u0012\u0010T\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J+\u0010U\u001a\u00020\b2\b\u0010,\u001a\u0004\u0018\u00010\u00112\b\u0010V\u001a\u0004\u0018\u00010\u000b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007¢\u0006\u0002\u0010WJ\u0012\u0010X\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u001c\u0010Y\u001a\u00020\b2\b\u00100\u001a\u0004\u0018\u00010\u00112\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u0012\u0010Z\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u0012\u0010[\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u0012\u0010\\\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\b\u0010]\u001a\u00020\u0011H\u0016J\u001c\u0010^\u001a\u00020\b2\b\u0010L\u001a\u0004\u0018\u00010\u00112\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u0012\u0010_\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u0010\u0010`\u001a\u00020\b2\u0006\u0010>\u001a\u00020?H\u0007J\u0018\u0010a\u001a\u00020\b2\u0006\u0010=\u001a\u00020\u00112\u0006\u0010>\u001a\u00020?H\u0007J\u0018\u0010b\u001a\u00020\b2\u0006\u0010=\u001a\u00020\u00112\u0006\u0010>\u001a\u00020?H\u0007J\u0010\u0010c\u001a\u00020\b2\u0006\u0010>\u001a\u00020?H\u0007J\u0010\u0010d\u001a\u00020\b2\u0006\u0010>\u001a\u00020?H\u0007J\u001c\u0010e\u001a\u00020\b2\b\u0010L\u001a\u0004\u0018\u00010\u00112\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u0012\u0010f\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\b\u0010g\u001a\u00020\bH\u0007J\u0012\u0010h\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u0012\u0010i\u001a\u00020\b2\b\u00100\u001a\u0004\u0018\u00010\u0011H\u0007J\u0012\u0010j\u001a\u00020\b2\b\u00102\u001a\u0004\u0018\u000103H\u0007J\u0010\u0010k\u001a\u00020\b2\u0006\u0010=\u001a\u00020\u0011H\u0007J\u0010\u0010l\u001a\u00020\b2\u0006\u0010,\u001a\u00020\u0011H\u0007J\b\u0010m\u001a\u00020\bH\u0007J\u0012\u0010n\u001a\u00020\b2\b\u0010o\u001a\u0004\u0018\u00010\u000eH\u0007J\u0010\u0010p\u001a\u00020\b2\u0006\u0010,\u001a\u00020\u0011H\u0007J\b\u0010q\u001a\u00020\bH\u0007J\b\u0010r\u001a\u00020\bH\u0007J\b\u0010s\u001a\u00020\bH\u0007J\u001c\u0010t\u001a\u00020\b2\b\u0010:\u001a\u0004\u0018\u00010\u00112\b\u0010L\u001a\u0004\u0018\u00010\u0011H\u0007J\u001c\u0010u\u001a\u00020\b2\b\u0010v\u001a\u0004\u0018\u0001032\b\u0010L\u001a\u0004\u0018\u00010\u0011H\u0007J!\u0010w\u001a\u00020\b2\b\u0010:\u001a\u0004\u0018\u00010x2\b\u0010L\u001a\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0002\u0010yJ\u0012\u0010z\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u0012\u0010{\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u001c\u0010|\u001a\u00020\b2\b\u0010}\u001a\u0004\u0018\u00010\u00112\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J!\u0010~\u001a\u00020\b2\b\u0010:\u001a\u0004\u0018\u00010x2\b\u0010L\u001a\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0002\u0010yJ\u001c\u0010\u007f\u001a\u00020\b2\b\u0010:\u001a\u0004\u0018\u00010\u00112\b\u0010L\u001a\u0004\u0018\u00010\u0011H\u0007J\u001d\u0010\u0080\u0001\u001a\u00020\b2\b\u0010v\u001a\u0004\u0018\u0001032\b\u0010L\u001a\u0004\u0018\u00010\u0011H\u0007J\u0013\u0010\u0081\u0001\u001a\u00020\b2\b\u0010L\u001a\u0004\u0018\u00010\u0011H\u0007J\u0013\u0010\u0082\u0001\u001a\u00020\b2\b\u0010o\u001a\u0004\u0018\u00010\u000eH\u0007J\u001d\u0010\u0083\u0001\u001a\u00020\b2\b\u0010v\u001a\u0004\u0018\u0001032\b\u0010L\u001a\u0004\u0018\u00010\u0011H\u0007J\u0012\u0010\u0084\u0001\u001a\u00020\b2\u0007\u0010\u0085\u0001\u001a\u00020\u000bH\u0007J\u0014\u0010\u0086\u0001\u001a\u00020\b2\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u000eH\u0007J\u0013\u0010\u0088\u0001\u001a\u00020\b2\b\u0010R\u001a\u0004\u0018\u00010\u0011H\u0007J\u0013\u0010\u0089\u0001\u001a\u00020\b2\b\u0010R\u001a\u0004\u0018\u00010\u0011H\u0007J\u0013\u0010\u008a\u0001\u001a\u00020\b2\b\u00100\u001a\u0004\u0018\u00010\u0011H\u0007J\u0013\u0010\u008b\u0001\u001a\u00020\b2\b\u00100\u001a\u0004\u0018\u00010\u0011H\u0007J*\u0010\u008c\u0001\u001a\u00020\b2\t\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u00112\t\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u00112\t\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u0011H\u0007J\u001f\u0010\u0090\u0001\u001a\u00020\b2\t\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u000e2\t\u0010\u0092\u0001\u001a\u0004\u0018\u000103H\u0007J\u001e\u0010\u0093\u0001\u001a\u00020\b2\b\u0010=\u001a\u0004\u0018\u00010\u00112\t\u0010\u0094\u0001\u001a\u0004\u0018\u00010\u000eH\u0007J\u0014\u0010\u0095\u0001\u001a\u00020\b2\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0011H\u0007J\t\u0010\u0097\u0001\u001a\u00020\bH\u0007J\t\u0010\u0098\u0001\u001a\u00020\bH\u0007J\t\u0010\u0099\u0001\u001a\u00020\bH\u0007J\u0013\u0010\u009a\u0001\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u0013\u0010\u009b\u0001\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u0013\u0010\u009c\u0001\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u0013\u0010\u009d\u0001\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u0013\u0010\u009e\u0001\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007J\u0012\u0010\u009f\u0001\u001a\u00020\b2\u0007\u0010 \u0001\u001a\u00020\u0015H\u0007J\u0014\u0010¡\u0001\u001a\u00020\b2\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u000eH\u0007J\u0014\u0010£\u0001\u001a\u00020\b2\t\u0010¤\u0001\u001a\u0004\u0018\u00010\u0011H\u0007J\u001d\u0010¥\u0001\u001a\u00020\b2\t\u0010¦\u0001\u001a\u0004\u0018\u00010\u00112\u0007\u0010§\u0001\u001a\u00020\u0015H\u0007J\u0014\u0010¨\u0001\u001a\u00020\b2\t\u0010©\u0001\u001a\u0004\u0018\u00010\u0011H\u0007J\u001b\u0010ª\u0001\u001a\u00020\b2\u0007\u0010«\u0001\u001a\u00020x2\u0007\u0010¬\u0001\u001a\u00020xH\u0007J\u0011\u0010\u00ad\u0001\u001a\u00020\b2\u0006\u0010H\u001a\u00020\u0015H\u0007J\u0011\u0010®\u0001\u001a\u00020\b2\u0006\u0010:\u001a\u00020\u000bH\u0007J\u0011\u0010¯\u0001\u001a\u00020\b2\u0006\u0010:\u001a\u00020\u000bH\u0007J\u001f\u0010°\u0001\u001a\u00020\b2\t\u0010±\u0001\u001a\u0004\u0018\u00010\u00112\t\u0010²\u0001\u001a\u0004\u0018\u00010\u0011H\u0007J\u0014\u0010³\u0001\u001a\u00020\b2\t\u0010´\u0001\u001a\u0004\u0018\u00010\u000eH\u0007J\t\u0010µ\u0001\u001a\u00020\bH\u0007J\t\u0010¶\u0001\u001a\u00020\bH\u0007J\u0012\u0010·\u0001\u001a\u00020\b2\u0007\u0010¸\u0001\u001a\u00020\u000bH\u0007J\t\u0010¹\u0001\u001a\u00020\bH\u0007J\u001c\u0010º\u0001\u001a\u00020\b2\u0007\u0010¸\u0001\u001a\u00020\u000b2\b\u0010>\u001a\u0004\u0018\u00010?H\u0007R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006¼\u0001"}, d2 = {"Lcom/clevertap/react/CleverTapModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "cleverTapModuleImpl", "Lcom/clevertap/react/CleverTapModuleImpl;", RemoteConfigComponent.ACTIVATE_FILE_NAME, "", "clearInAppResources", "expiredOnly", "", "createNotification", "extras", "Lcom/facebook/react/bridge/ReadableMap;", "createNotificationChannel", "channelId", "", "channelName", "channelDescription", "importance", "", "showBadge", "createNotificationChannelGroup", "groupId", "groupName", "createNotificationChannelWithGroupId", "createNotificationChannelWithGroupIdAndSound", "sound", "createNotificationChannelWithSound", "customTemplateContextToString", "templateName", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "customTemplateGetBooleanArg", "argName", "customTemplateGetFileArg", "customTemplateGetNumberArg", "customTemplateGetObjectArg", "customTemplateGetStringArg", "customTemplateRunAction", "customTemplateSetDismissed", "customTemplateSetPresented", "defineFileVariable", "name", "defineVariables", "object", "deleteInboxMessageForId", "messageId", "deleteInboxMessagesForIDs", "messageIDs", "Lcom/facebook/react/bridge/ReadableArray;", "deleteNotificationChannel", "deleteNotificationChannelGroup", "disablePersonalization", "discardInAppNotifications", "dismissInbox", "enableDeviceNetworkInfoReporting", "value", "enablePersonalization", "eventGetDetail", "eventName", "callback", "Lcom/facebook/react/bridge/Callback;", "eventGetFirstTime", "eventGetLastTime", "eventGetOccurrences", RemoteConfigComponent.FETCH_FILE_NAME, "fetchAndActivate", "fetchInApps", "fetchVariables", "fetchWithMinimumFetchIntervalInSeconds", "interval", "getAllDisplayUnits", "getAllInboxMessages", "getBoolean", com.clevertap.android.sdk.Constants.KEY_KEY, "getCleverTapID", "getConstants", "", "", "getDisplayUnitForId", "unitID", "getDouble", "getEventHistory", "getFeatureFlag", "defaultValue", "(Ljava/lang/String;Ljava/lang/Boolean;Lcom/facebook/react/bridge/Callback;)V", "getInboxMessageCount", "getInboxMessageForId", "getInboxMessageUnreadCount", "getInitialUrl", "getLastFetchTimeStampInMillis", "getName", "getString", "getUnreadInboxMessages", "getUserAppLaunchCount", "getUserEventLog", "getUserEventLogCount", "getUserEventLogHistory", "getUserLastVisitTs", "getVariable", "getVariables", "initializeInbox", "isPushPermissionGranted", "markReadInboxMessageForId", "markReadInboxMessagesForIDs", "onEventListenerAdded", "onFileValueChanged", "onOneTimeVariablesChanged", "onUserLogin", "profile", "onValueChanged", "onVariablesChanged", "onVariablesChangedAndNoDownloadsPending", "onceVariablesChangedAndNoDownloadsPending", "profileAddMultiValue", "profileAddMultiValues", "values", "profileDecrementValueForKey", "", "(Ljava/lang/Double;Ljava/lang/String;)V", "profileGetCleverTapAttributionIdentifier", "profileGetCleverTapID", "profileGetProperty", TriggerAdapter.INAPP_PROPERTYNAME, "profileIncrementValueForKey", "profileRemoveMultiValue", "profileRemoveMultiValues", "profileRemoveValueForKey", "profileSet", "profileSetMultiValues", "promptForPushPermission", "showFallbackSettings", "promptPushPrimer", "localInAppConfig", "pushDisplayUnitClickedEventForID", "pushDisplayUnitViewedEventForID", "pushInboxNotificationClickedEventForId", "pushInboxNotificationViewedEventForId", "pushInstallReferrer", "source", "medium", "campaign", "recordChargedEvent", "details", FirebaseAnalytics.Param.ITEMS, "recordEvent", "props", "recordScreenView", "screenName", "registerForPush", "reset", "resumeInAppNotifications", "sessionGetPreviousVisitTime", "sessionGetScreenCount", "sessionGetTimeElapsed", "sessionGetTotalVisits", "sessionGetUTMDetails", "setDebugLevel", "level", "setDefaultsMap", "map", "setInstanceWithAccountId", com.clevertap.android.sdk.Constants.KEY_ACCOUNT_ID, "setLibrary", "libName", "libVersion", "setLocale", "locale", "setLocation", "latitude", "longitude", "setMinimumFetchIntervalInSeconds", "setOffline", "setOptOut", "setPushTokenAsString", "token", "type", "showInbox", "styleConfig", "suspendInAppNotifications", "syncCustomTemplates", "syncCustomTemplatesInProd", "isProduction", "syncVariables", "syncVariablesinProd", "Companion", "clevertap-react-native_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class CleverTapModule extends ReactContextBaseJavaModule {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final CleverTapModuleImpl cleverTapModuleImpl;

    @Deprecated(message = "Use CleverTapRnAPI.setInitialUri(uri) instead", replaceWith = @ReplaceWith(expression = "CleverTapRnAPI.setInitialUri(uri)", imports = {"com.clevertap.react.CleverTapRnAPI"}))
    @JvmStatic
    public static final void setInitialUri(Uri uri) {
        INSTANCE.setInitialUri(uri);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return Constants.REACT_MODULE_NAME;
    }

    public CleverTapModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNull(reactApplicationContext);
        this.cleverTapModuleImpl = new CleverTapModuleImpl(reactApplicationContext);
    }

    /* compiled from: CleverTapModule.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/clevertap/react/CleverTapModule$Companion;", "", "()V", "setInitialUri", "", ReactNativeBlobUtilConst.DATA_ENCODE_URI, "Landroid/net/Uri;", "clevertap-react-native_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Deprecated(message = "Use CleverTapRnAPI.setInitialUri(uri) instead", replaceWith = @ReplaceWith(expression = "CleverTapRnAPI.setInitialUri(uri)", imports = {"com.clevertap.react.CleverTapRnAPI"}))
        @JvmStatic
        public final void setInitialUri(Uri uri) {
            CleverTapModuleImpl.setInitialUri(uri);
        }
    }

    @ReactMethod
    public final void setLibrary(String libName, int libVersion) {
        this.cleverTapModuleImpl.setLibrary(libName, libVersion);
    }

    @ReactMethod
    public final void setLocale(String locale) {
        this.cleverTapModuleImpl.setLocale(locale);
    }

    @ReactMethod
    public final void activate() {
        this.cleverTapModuleImpl.activate();
    }

    @ReactMethod
    public final void createNotification(ReadableMap extras) {
        this.cleverTapModuleImpl.createNotification(extras);
    }

    @ReactMethod
    public final void createNotificationChannel(String channelId, String channelName, String channelDescription, int importance, boolean showBadge) {
        this.cleverTapModuleImpl.createNotificationChannel(channelId, channelName, channelDescription, importance, showBadge);
    }

    @ReactMethod
    public final void createNotificationChannelGroup(String groupId, String groupName) {
        this.cleverTapModuleImpl.createNotificationChannelGroup(groupId, groupName);
    }

    @ReactMethod
    public final void createNotificationChannelWithGroupId(String channelId, String channelName, String channelDescription, int importance, String groupId, boolean showBadge) {
        this.cleverTapModuleImpl.createNotificationChannelWithGroupId(channelId, channelName, channelDescription, importance, groupId, showBadge);
    }

    @ReactMethod
    public final void createNotificationChannelWithGroupIdAndSound(String channelId, String channelName, String channelDescription, int importance, String groupId, boolean showBadge, String sound) {
        this.cleverTapModuleImpl.createNotificationChannelWithGroupIdAndSound(channelId, channelName, channelDescription, importance, groupId, showBadge, sound);
    }

    @ReactMethod
    public final void createNotificationChannelWithSound(String channelId, String channelName, String channelDescription, int importance, boolean showBadge, String sound) {
        this.cleverTapModuleImpl.createNotificationChannelWithSound(channelId, channelName, channelDescription, importance, showBadge, sound);
    }

    @ReactMethod
    public final void deleteNotificationChannel(String channelId) {
        this.cleverTapModuleImpl.deleteNotificationChannel(channelId);
    }

    @ReactMethod
    public final void deleteNotificationChannelGroup(String groupId) {
        this.cleverTapModuleImpl.deleteNotificationChannelGroup(groupId);
    }

    @ReactMethod
    public final void promptForPushPermission(boolean showFallbackSettings) {
        this.cleverTapModuleImpl.promptForPushPermission(showFallbackSettings);
    }

    @ReactMethod
    public final void promptPushPrimer(ReadableMap localInAppConfig) {
        this.cleverTapModuleImpl.promptPushPrimer(localInAppConfig);
    }

    @ReactMethod
    public final void isPushPermissionGranted(Callback callback) {
        this.cleverTapModuleImpl.isPushPermissionGranted(callback);
    }

    @ReactMethod
    public final void disablePersonalization() {
        this.cleverTapModuleImpl.disablePersonalization();
    }

    @ReactMethod
    public final void enableDeviceNetworkInfoReporting(boolean value) {
        this.cleverTapModuleImpl.enableDeviceNetworkInfoReporting(value);
    }

    @ReactMethod
    public final void enablePersonalization() {
        this.cleverTapModuleImpl.enablePersonalization();
    }

    @ReactMethod
    public final void eventGetDetail(String eventName, Callback callback) {
        this.cleverTapModuleImpl.eventGetDetail(eventName, callback);
    }

    @ReactMethod
    public final void eventGetFirstTime(String eventName, Callback callback) {
        this.cleverTapModuleImpl.eventGetFirstTime(eventName, callback);
    }

    @ReactMethod
    public final void eventGetLastTime(String eventName, Callback callback) {
        this.cleverTapModuleImpl.eventGetLastTime(eventName, callback);
    }

    @ReactMethod
    public final void eventGetOccurrences(String eventName, Callback callback) {
        this.cleverTapModuleImpl.eventGetOccurrences(eventName, callback);
    }

    @ReactMethod
    public final void fetch() {
        this.cleverTapModuleImpl.fetch();
    }

    @ReactMethod
    public final void fetchAndActivate() {
        this.cleverTapModuleImpl.fetchAndActivate();
    }

    @ReactMethod
    public final void fetchWithMinimumFetchIntervalInSeconds(int interval) {
        this.cleverTapModuleImpl.fetchWithMinimumFetchIntervalInSeconds(interval);
    }

    @ReactMethod
    public final void getAllDisplayUnits(Callback callback) {
        this.cleverTapModuleImpl.getAllDisplayUnits(callback);
    }

    @ReactMethod
    public final void getBoolean(String key, Callback callback) {
        this.cleverTapModuleImpl.getBoolean(key, callback);
    }

    @ReactMethod
    public final void getDisplayUnitForId(String unitID, Callback callback) {
        this.cleverTapModuleImpl.getDisplayUnitForId(unitID, callback);
    }

    @ReactMethod
    public final void getDouble(String key, Callback callback) {
        this.cleverTapModuleImpl.getDouble(key, callback);
    }

    @ReactMethod
    public final void getEventHistory(Callback callback) {
        this.cleverTapModuleImpl.getEventHistory(callback);
    }

    @ReactMethod
    public final void getFeatureFlag(String name, Boolean defaultValue, Callback callback) {
        this.cleverTapModuleImpl.getFeatureFlag(name, defaultValue, callback);
    }

    @ReactMethod
    public final void getAllInboxMessages(Callback callback) {
        this.cleverTapModuleImpl.getAllInboxMessages(callback);
    }

    @ReactMethod
    public final void getInboxMessageCount(Callback callback) {
        this.cleverTapModuleImpl.getInboxMessageCount(callback);
    }

    @ReactMethod
    public final void getInboxMessageForId(String messageId, Callback callback) {
        this.cleverTapModuleImpl.getInboxMessageForId(messageId, callback);
    }

    @ReactMethod
    public final void getInboxMessageUnreadCount(Callback callback) {
        this.cleverTapModuleImpl.getInboxMessageUnreadCount(callback);
    }

    @ReactMethod
    public final void deleteInboxMessageForId(String messageId) {
        this.cleverTapModuleImpl.deleteInboxMessageForId(messageId);
    }

    @ReactMethod
    public final void getUnreadInboxMessages(Callback callback) {
        this.cleverTapModuleImpl.getUnreadInboxMessages(callback);
    }

    @ReactMethod
    public final void initializeInbox() {
        this.cleverTapModuleImpl.initializeInbox();
    }

    @ReactMethod
    public final void markReadInboxMessageForId(String messageId) {
        this.cleverTapModuleImpl.markReadInboxMessageForId(messageId);
    }

    @ReactMethod
    public final void markReadInboxMessagesForIDs(ReadableArray messageIDs) {
        this.cleverTapModuleImpl.markReadInboxMessagesForIDs(messageIDs);
    }

    @ReactMethod
    public final void deleteInboxMessagesForIDs(ReadableArray messageIDs) {
        this.cleverTapModuleImpl.deleteInboxMessagesForIDs(messageIDs);
    }

    @ReactMethod
    public final void pushInboxNotificationClickedEventForId(String messageId) {
        this.cleverTapModuleImpl.pushInboxNotificationClickedEventForId(messageId);
    }

    @ReactMethod
    public final void pushInboxNotificationViewedEventForId(String messageId) {
        this.cleverTapModuleImpl.pushInboxNotificationViewedEventForId(messageId);
    }

    @ReactMethod
    public final void showInbox(ReadableMap styleConfig) {
        this.cleverTapModuleImpl.showInbox(styleConfig);
    }

    @ReactMethod
    public final void dismissInbox() {
        this.cleverTapModuleImpl.dismissInbox();
    }

    @ReactMethod
    public final void getInitialUrl(Callback callback) {
        this.cleverTapModuleImpl.getInitialUrl(callback);
    }

    @ReactMethod
    public final void getLastFetchTimeStampInMillis(Callback callback) {
        this.cleverTapModuleImpl.getLastFetchTimeStampInMillis(callback);
    }

    @ReactMethod
    public final void getString(String key, Callback callback) {
        this.cleverTapModuleImpl.getString(key, callback);
    }

    @ReactMethod
    public final void onUserLogin(ReadableMap profile) {
        this.cleverTapModuleImpl.onUserLogin(profile);
    }

    @ReactMethod
    public final void profileAddMultiValue(String value, String key) {
        this.cleverTapModuleImpl.profileAddMultiValue(value, key);
    }

    @ReactMethod
    public final void profileAddMultiValues(ReadableArray values, String key) {
        this.cleverTapModuleImpl.profileAddMultiValues(values, key);
    }

    @ReactMethod
    public final void profileGetCleverTapAttributionIdentifier(Callback callback) {
        this.cleverTapModuleImpl.profileGetCleverTapAttributionIdentifier(callback);
    }

    @ReactMethod
    public final void profileGetCleverTapID(Callback callback) {
        this.cleverTapModuleImpl.profileGetCleverTapID(callback);
    }

    @ReactMethod
    public final void getCleverTapID(Callback callback) {
        this.cleverTapModuleImpl.getCleverTapID(callback);
    }

    @ReactMethod
    public final void profileGetProperty(String propertyName, Callback callback) {
        this.cleverTapModuleImpl.profileGetProperty(propertyName, callback);
    }

    @ReactMethod
    public final void profileRemoveMultiValue(String value, String key) {
        this.cleverTapModuleImpl.profileRemoveMultiValue(value, key);
    }

    @ReactMethod
    public final void profileRemoveMultiValues(ReadableArray values, String key) {
        this.cleverTapModuleImpl.profileRemoveMultiValues(values, key);
    }

    @ReactMethod
    public final void profileRemoveValueForKey(String key) {
        this.cleverTapModuleImpl.profileRemoveValueForKey(key);
    }

    @ReactMethod
    public final void profileSet(ReadableMap profile) {
        this.cleverTapModuleImpl.profileSet(profile);
    }

    @ReactMethod
    public final void profileSetMultiValues(ReadableArray values, String key) {
        this.cleverTapModuleImpl.profileSetMultiValues(values, key);
    }

    @ReactMethod
    public final void pushDisplayUnitClickedEventForID(String unitID) {
        this.cleverTapModuleImpl.pushDisplayUnitClickedEventForID(unitID);
    }

    @ReactMethod
    public final void pushDisplayUnitViewedEventForID(String unitID) {
        this.cleverTapModuleImpl.pushDisplayUnitViewedEventForID(unitID);
    }

    @ReactMethod
    public final void pushInstallReferrer(String source, String medium, String campaign) {
        this.cleverTapModuleImpl.pushInstallReferrer(source, medium, campaign);
    }

    @ReactMethod
    public final void recordChargedEvent(ReadableMap details, ReadableArray items) {
        this.cleverTapModuleImpl.recordChargedEvent(details, items);
    }

    @ReactMethod
    public final void recordEvent(String eventName, ReadableMap props) {
        this.cleverTapModuleImpl.recordEvent(eventName, props);
    }

    @ReactMethod
    public final void recordScreenView(String screenName) {
        this.cleverTapModuleImpl.recordScreenView(screenName);
    }

    @ReactMethod
    public final void registerForPush() {
        this.cleverTapModuleImpl.registerForPush();
    }

    @ReactMethod
    public final void reset() {
        this.cleverTapModuleImpl.reset();
    }

    @ReactMethod
    public final void sessionGetPreviousVisitTime(Callback callback) {
        this.cleverTapModuleImpl.sessionGetPreviousVisitTime(callback);
    }

    @ReactMethod
    public final void sessionGetScreenCount(Callback callback) {
        this.cleverTapModuleImpl.sessionGetScreenCount(callback);
    }

    @ReactMethod
    public final void sessionGetTimeElapsed(Callback callback) {
        this.cleverTapModuleImpl.sessionGetTimeElapsed(callback);
    }

    @ReactMethod
    public final void sessionGetTotalVisits(Callback callback) {
        this.cleverTapModuleImpl.sessionGetTotalVisits(callback);
    }

    @ReactMethod
    public final void sessionGetUTMDetails(Callback callback) {
        this.cleverTapModuleImpl.sessionGetUTMDetails(callback);
    }

    @ReactMethod
    public final void setDebugLevel(int level) {
        CleverTapAPI.setDebugLevel(level);
    }

    @ReactMethod
    public final void setDefaultsMap(ReadableMap map) {
        this.cleverTapModuleImpl.setDefaultsMap(map);
    }

    @ReactMethod
    public final void setLocation(double latitude, double longitude) {
        this.cleverTapModuleImpl.setLocation(latitude, longitude);
    }

    @ReactMethod
    public final void setMinimumFetchIntervalInSeconds(int interval) {
        this.cleverTapModuleImpl.setMinimumFetchIntervalInSeconds(interval);
    }

    @ReactMethod
    public final void setOffline(boolean value) {
        this.cleverTapModuleImpl.setOffline(value);
    }

    @ReactMethod
    public final void setOptOut(boolean value) {
        this.cleverTapModuleImpl.setOptOut(value);
    }

    @ReactMethod
    public final void setPushTokenAsString(String token, String type) {
        this.cleverTapModuleImpl.setPushTokenAsString(token, type);
    }

    @ReactMethod
    public final void profileIncrementValueForKey(Double value, String key) {
        this.cleverTapModuleImpl.profileIncrementValueForKey(value, key);
    }

    @ReactMethod
    public final void profileDecrementValueForKey(Double value, String key) {
        this.cleverTapModuleImpl.profileDecrementValueForKey(value, key);
    }

    @ReactMethod
    public final void suspendInAppNotifications() {
        this.cleverTapModuleImpl.suspendInAppNotifications();
    }

    @ReactMethod
    public final void discardInAppNotifications() {
        this.cleverTapModuleImpl.discardInAppNotifications();
    }

    @ReactMethod
    public final void resumeInAppNotifications() {
        this.cleverTapModuleImpl.resumeInAppNotifications();
    }

    @ReactMethod
    public final void setInstanceWithAccountId(String accountId) {
        this.cleverTapModuleImpl.setInstanceWithAccountId(accountId);
    }

    @ReactMethod
    public final void fetchInApps(Callback callback) throws JSONException {
        this.cleverTapModuleImpl.fetchInApps(callback);
    }

    @ReactMethod
    public final void clearInAppResources(boolean expiredOnly) {
        this.cleverTapModuleImpl.clearInAppResources(expiredOnly);
    }

    @ReactMethod
    public final void customTemplateSetDismissed(String templateName, Promise promise) {
        this.cleverTapModuleImpl.customTemplateSetDismissed(templateName, promise);
    }

    @ReactMethod
    public final void customTemplateSetPresented(String templateName, Promise promise) {
        this.cleverTapModuleImpl.customTemplateSetPresented(templateName, promise);
    }

    @ReactMethod
    public final void customTemplateRunAction(String templateName, String argName, Promise promise) {
        this.cleverTapModuleImpl.customTemplateRunAction(templateName, argName, promise);
    }

    @ReactMethod
    public final void customTemplateGetStringArg(String templateName, String argName, Promise promise) {
        this.cleverTapModuleImpl.customTemplateGetStringArg(templateName, argName, promise);
    }

    @ReactMethod
    public final void customTemplateGetNumberArg(String templateName, String argName, Promise promise) {
        this.cleverTapModuleImpl.customTemplateGetNumberArg(templateName, argName, promise);
    }

    @ReactMethod
    public final void customTemplateGetBooleanArg(String templateName, String argName, Promise promise) {
        this.cleverTapModuleImpl.customTemplateGetBooleanArg(templateName, argName, promise);
    }

    @ReactMethod
    public final void customTemplateGetFileArg(String templateName, String argName, Promise promise) {
        this.cleverTapModuleImpl.customTemplateGetFileArg(templateName, argName, promise);
    }

    @ReactMethod
    public final void customTemplateGetObjectArg(String templateName, String argName, Promise promise) {
        this.cleverTapModuleImpl.customTemplateGetObjectArg(templateName, argName, promise);
    }

    @ReactMethod
    public final void customTemplateContextToString(String templateName, Promise promise) {
        this.cleverTapModuleImpl.customTemplateContextToString(templateName, promise);
    }

    @ReactMethod
    public final void syncCustomTemplates() {
        this.cleverTapModuleImpl.syncCustomTemplates();
    }

    @ReactMethod
    public final void syncCustomTemplatesInProd(boolean isProduction) {
        this.cleverTapModuleImpl.syncCustomTemplates();
    }

    @ReactMethod
    public final void syncVariables() {
        this.cleverTapModuleImpl.syncVariables();
    }

    @ReactMethod
    public final void syncVariablesinProd(boolean isProduction, Callback callback) {
        this.cleverTapModuleImpl.syncVariablesinProd(isProduction, callback);
    }

    @ReactMethod
    public final void defineVariables(ReadableMap object) {
        Intrinsics.checkNotNullParameter(object, "object");
        this.cleverTapModuleImpl.defineVariables(object);
    }

    @ReactMethod
    public final void defineFileVariable(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.cleverTapModuleImpl.defineFileVariable(name);
    }

    @ReactMethod
    public final void fetchVariables(Callback callback) throws JSONException {
        this.cleverTapModuleImpl.fetchVariables(callback);
    }

    @ReactMethod
    public final void getVariable(String key, Callback callback) {
        this.cleverTapModuleImpl.getVariable(key, callback);
    }

    @ReactMethod
    public final void getVariables(Callback callback) {
        this.cleverTapModuleImpl.getVariables(callback);
    }

    @ReactMethod
    public final void onVariablesChanged() {
        this.cleverTapModuleImpl.onVariablesChanged();
    }

    @ReactMethod
    public final void onOneTimeVariablesChanged() {
        this.cleverTapModuleImpl.onOneTimeVariablesChanged();
    }

    @ReactMethod
    public final void onValueChanged(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.cleverTapModuleImpl.onValueChanged(name);
    }

    @ReactMethod
    public final void onFileValueChanged(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.cleverTapModuleImpl.onFileValueChanged(name);
    }

    @ReactMethod
    public final void onVariablesChangedAndNoDownloadsPending() {
        this.cleverTapModuleImpl.onVariablesChangedAndNoDownloadsPending();
    }

    @ReactMethod
    public final void onceVariablesChangedAndNoDownloadsPending() {
        this.cleverTapModuleImpl.onceVariablesChangedAndNoDownloadsPending();
    }

    @ReactMethod
    public final void onEventListenerAdded(String eventName) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        this.cleverTapModuleImpl.onEventListenerAdded(eventName);
    }

    @ReactMethod
    public final void getUserEventLog(String eventName, Callback callback) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.cleverTapModuleImpl.getUserEventLog(eventName, callback);
    }

    @ReactMethod
    public final void getUserEventLogCount(String eventName, Callback callback) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.cleverTapModuleImpl.getUserEventLogCount(eventName, callback);
    }

    @ReactMethod
    public final void getUserLastVisitTs(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.cleverTapModuleImpl.getUserLastVisitTs(callback);
    }

    @ReactMethod
    public final void getUserAppLaunchCount(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.cleverTapModuleImpl.getUserAppLaunchCount(callback);
    }

    @ReactMethod
    public final void getUserEventLogHistory(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.cleverTapModuleImpl.getUserEventLogHistory(callback);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        Map<String, Object> clevertapConstants = this.cleverTapModuleImpl.getClevertapConstants();
        Intrinsics.checkNotNullExpressionValue(clevertapConstants, "getClevertapConstants(...)");
        return clevertapConstants;
    }
}
