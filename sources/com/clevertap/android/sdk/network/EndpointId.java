package com.clevertap.android.sdk.network;

import com.clevertap.android.sdk.events.EventGroup;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: NetworkHeadersListener.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lcom/clevertap/android/sdk/network/EndpointId;", "", ViewHierarchyNode.JsonKeys.IDENTIFIER, "", "(Ljava/lang/String;ILjava/lang/String;)V", "getIdentifier", "()Ljava/lang/String;", "ENDPOINT_SPIKY", "ENDPOINT_A1", "ENDPOINT_HELLO", "ENDPOINT_DEFINE_VARS", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public enum EndpointId {
    ENDPOINT_SPIKY("-spiky"),
    ENDPOINT_A1("/a1"),
    ENDPOINT_HELLO("/hello"),
    ENDPOINT_DEFINE_VARS("/defineVars");


    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String identifier;

    @JvmStatic
    public static final EndpointId fromEventGroup(EventGroup eventGroup) {
        return INSTANCE.fromEventGroup(eventGroup);
    }

    @JvmStatic
    public static final EndpointId fromString(String str) {
        return INSTANCE.fromString(str);
    }

    public final String getIdentifier() {
        return this.identifier;
    }

    EndpointId(String str) {
        this.identifier = str;
    }

    /* compiled from: NetworkHeadersListener.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Lcom/clevertap/android/sdk/network/EndpointId$Companion;", "", "()V", "fromEventGroup", "Lcom/clevertap/android/sdk/network/EndpointId;", "eventGroup", "Lcom/clevertap/android/sdk/events/EventGroup;", "fromString", ViewHierarchyNode.JsonKeys.IDENTIFIER, "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {

        /* compiled from: NetworkHeadersListener.kt */
        @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[EventGroup.values().length];
                try {
                    iArr[EventGroup.PUSH_NOTIFICATION_VIEWED.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[EventGroup.REGULAR.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[EventGroup.VARIABLES.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final EndpointId fromString(String identifier) {
            EndpointId endpointId;
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            EndpointId[] endpointIdArrValues = EndpointId.values();
            int length = endpointIdArrValues.length;
            int i = 0;
            while (true) {
                endpointId = null;
                if (i >= length) {
                    break;
                }
                EndpointId endpointId2 = endpointIdArrValues[i];
                if (StringsKt.contains$default((CharSequence) identifier, (CharSequence) endpointId2.getIdentifier(), false, 2, (Object) null)) {
                    endpointId = endpointId2;
                    break;
                }
                i++;
            }
            return endpointId == null ? EndpointId.ENDPOINT_A1 : endpointId;
        }

        @JvmStatic
        public final EndpointId fromEventGroup(EventGroup eventGroup) {
            Intrinsics.checkNotNullParameter(eventGroup, "eventGroup");
            int i = WhenMappings.$EnumSwitchMapping$0[eventGroup.ordinal()];
            if (i == 1) {
                return EndpointId.ENDPOINT_SPIKY;
            }
            if (i == 2) {
                return EndpointId.ENDPOINT_A1;
            }
            if (i == 3) {
                return EndpointId.ENDPOINT_DEFINE_VARS;
            }
            throw new NoWhenBranchMatchedException();
        }
    }
}
