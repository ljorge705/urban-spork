package androidx.compose.ui.platform;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.SpannableString;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.collection.ArraySet;
import androidx.collection.SparseArrayCompat;
import androidx.compose.ui.R;
import androidx.compose.ui.TempListUtilsKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.HitTestResult;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.platform.AccessibilityIterators;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.accessibility.CollectionInfoKt;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.CustomAccessibilityAction;
import androidx.compose.ui.semantics.LiveRegionMode;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsNodeKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesAndroid;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.state.ToggleableState;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.platform.AndroidAccessibilitySpannableString_androidKt;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.SentryReplayOptions;
import io.sentry.protocol.ViewHierarchyNode;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
@Metadata(d1 = {"\u0000¼\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 Ù\u00012\u00020\u0001:\u000eÖ\u0001×\u0001Ø\u0001Ù\u0001Ú\u0001Û\u0001Ü\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J*\u0010]\u001a\u00020\u001b2\u0006\u0010^\u001a\u00020\t2\u0006\u0010_\u001a\u00020`2\u0006\u0010a\u001a\u00020\u00062\b\u0010b\u001a\u0004\u0018\u00010cH\u0002J\u0011\u0010d\u001a\u00020\u001bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010eJ-\u0010f\u001a\u00020\u000b2\u0006\u0010g\u001a\u00020\u000b2\u0006\u0010h\u001a\u00020\t2\u0006\u0010i\u001a\u00020jH\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bk\u0010lJ;\u0010f\u001a\u00020\u000b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0m2\u0006\u0010g\u001a\u00020\u000b2\u0006\u0010h\u001a\u00020\t2\u0006\u0010i\u001a\u00020jH\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bn\u0010oJ\b\u0010p\u001a\u00020\u001bH\u0002J\u0010\u0010q\u001a\u00020\u000b2\u0006\u0010^\u001a\u00020\tH\u0002J\u001d\u0010r\u001a\u00020s2\u0006\u0010^\u001a\u00020\t2\u0006\u0010t\u001a\u00020\tH\u0001¢\u0006\u0002\buJ\u0012\u0010v\u001a\u0004\u0018\u00010`2\u0006\u0010^\u001a\u00020\tH\u0002J=\u0010w\u001a\u00020s2\u0006\u0010^\u001a\u00020\t2\b\u0010x\u001a\u0004\u0018\u00010\t2\b\u0010y\u001a\u0004\u0018\u00010\t2\b\u0010z\u001a\u0004\u0018\u00010\t2\b\u0010{\u001a\u0004\u0018\u00010\u0018H\u0002¢\u0006\u0002\u0010|J\u000e\u0010}\u001a\u00020\u000b2\u0006\u0010~\u001a\u00020\u007fJ\u0013\u0010\u0080\u0001\u001a\u00020?2\b\u0010\u0081\u0001\u001a\u00030\u0082\u0001H\u0016J\u0013\u0010\u0083\u0001\u001a\u00020\t2\b\u0010\u0084\u0001\u001a\u00030\u0085\u0001H\u0002J\u0013\u0010\u0086\u0001\u001a\u00020\t2\b\u0010\u0084\u0001\u001a\u00030\u0085\u0001H\u0002J\u0017\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u00062\n\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0085\u0001H\u0002J!\u0010\u0088\u0001\u001a\u0005\u0018\u00010\u0089\u00012\n\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0085\u00012\u0007\u0010\u008a\u0001\u001a\u00020\tH\u0002J#\u0010\u008b\u0001\u001a\u00020\t2\b\u0010\u008c\u0001\u001a\u00030\u008d\u00012\b\u0010\u008e\u0001\u001a\u00030\u008d\u0001H\u0001¢\u0006\u0003\b\u008f\u0001J\u0011\u0010\u0090\u0001\u001a\u00020\u000b2\u0006\u0010^\u001a\u00020\tH\u0002J\u0013\u0010\u0091\u0001\u001a\u00020\u000b2\b\u0010\u0084\u0001\u001a\u00030\u0085\u0001H\u0002J\u0012\u0010\u0092\u0001\u001a\u00020\u001b2\u0007\u0010\u0093\u0001\u001a\u00020UH\u0002J\u0018\u0010\u0094\u0001\u001a\u00020\u001b2\u0007\u0010\u0093\u0001\u001a\u00020UH\u0000¢\u0006\u0003\b\u0095\u0001J\u000f\u0010\u0096\u0001\u001a\u00020\u001bH\u0000¢\u0006\u0003\b\u0097\u0001J$\u0010\u0098\u0001\u001a\u00020\u000b2\u0006\u0010^\u001a\u00020\t2\u0007\u0010\u0099\u0001\u001a\u00020\t2\b\u0010b\u001a\u0004\u0018\u00010cH\u0002J$\u0010\u009a\u0001\u001a\u00020\u001b2\u0006\u0010^\u001a\u00020\t2\u0007\u0010_\u001a\u00030\u009b\u00012\b\u0010\u009c\u0001\u001a\u00030\u0085\u0001H\u0007J!\u0010\u009d\u0001\u001a\u00020\u000b2\u0007\u0010\u009e\u0001\u001a\u00020\t2\r\u0010\u009f\u0001\u001a\b\u0012\u0004\u0012\u00020O0'H\u0002J\u0011\u0010 \u0001\u001a\u00020\u000b2\u0006\u0010^\u001a\u00020\tH\u0002J&\u0010¡\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u0085\u00010¢\u0001j\n\u0012\u0005\u0012\u00030\u0085\u0001`£\u00012\u0007\u0010¤\u0001\u001a\u00020\u000bH\u0002J\u0012\u0010¥\u0001\u001a\u00020\t2\u0007\u0010\u009e\u0001\u001a\u00020\tH\u0002J\u0011\u0010¦\u0001\u001a\u00020\u000b2\u0006\u0010~\u001a\u00020sH\u0002J?\u0010§\u0001\u001a\u00020\u000b2\u0006\u0010^\u001a\u00020\t2\u0006\u0010t\u001a\u00020\t2\u000b\b\u0002\u0010¨\u0001\u001a\u0004\u0018\u00010\t2\u0011\b\u0002\u0010©\u0001\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010'H\u0002¢\u0006\u0003\u0010ª\u0001J&\u0010«\u0001\u001a\u00020\u001b2\u0007\u0010¬\u0001\u001a\u00020\t2\u0007\u0010¨\u0001\u001a\u00020\t2\t\u0010\u00ad\u0001\u001a\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010®\u0001\u001a\u00020\u001b2\u0007\u0010¬\u0001\u001a\u00020\tH\u0002J\u0012\u0010¯\u0001\u001a\u00020\u001b2\u0007\u0010°\u0001\u001a\u00020OH\u0002J$\u0010±\u0001\u001a\u00020\u001b2\u0013\u0010²\u0001\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u001f0\u001eH\u0001¢\u0006\u0003\b³\u0001J\u001c\u0010´\u0001\u001a\u00020\u001b2\b\u0010µ\u0001\u001a\u00030\u0085\u00012\u0007\u0010¶\u0001\u001a\u00020FH\u0002J!\u0010·\u0001\u001a\u00020\u001b2\u0007\u0010\u0093\u0001\u001a\u00020U2\r\u0010¸\u0001\u001a\b\u0012\u0004\u0012\u00020\t0AH\u0002J.\u0010¹\u0001\u001a\u00020\u000b2\b\u0010\u0084\u0001\u001a\u00030\u0085\u00012\u0007\u0010º\u0001\u001a\u00020\t2\u0007\u0010»\u0001\u001a\u00020\t2\u0007\u0010¼\u0001\u001a\u00020\u000bH\u0002J\u001c\u0010½\u0001\u001a\u00020\u001b2\b\u0010\u0084\u0001\u001a\u00030\u0085\u00012\u0007\u0010_\u001a\u00030\u009b\u0001H\u0002J\u001c\u0010¾\u0001\u001a\u00020\u001b2\b\u0010\u0084\u0001\u001a\u00030\u0085\u00012\u0007\u0010_\u001a\u00030\u009b\u0001H\u0002J\t\u0010¿\u0001\u001a\u00020\u001bH\u0002JG\u0010À\u0001\u001a\t\u0012\u0005\u0012\u00030\u0085\u00010$2\u0007\u0010¤\u0001\u001a\u00020\u000b2\u000e\u0010Á\u0001\u001a\t\u0012\u0005\u0012\u00030\u0085\u00010$2\u001c\b\u0002\u0010Â\u0001\u001a\u0015\u0012\u0004\u0012\u00020\t\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0085\u00010$0EH\u0002J)\u0010Ã\u0001\u001a\t\u0012\u0005\u0012\u00030\u0085\u00010$2\u0007\u0010¤\u0001\u001a\u00020\u000b2\u000e\u0010Ä\u0001\u001a\t\u0012\u0005\u0012\u00030\u0085\u00010$H\u0002J\"\u0010Å\u0001\u001a\u0005\u0018\u00010Æ\u00012\n\u0010Ç\u0001\u001a\u0005\u0018\u00010\u0085\u00012\b\u0010È\u0001\u001a\u00030É\u0001H\u0002J.\u0010Ê\u0001\u001a\u00020\u000b2\b\u0010\u0084\u0001\u001a\u00030\u0085\u00012\u0007\u0010\u008a\u0001\u001a\u00020\t2\u0007\u0010Ë\u0001\u001a\u00020\u000b2\u0007\u0010Ì\u0001\u001a\u00020\u000bH\u0002J3\u0010Í\u0001\u001a\u0005\u0018\u0001HÎ\u0001\"\t\b\u0000\u0010Î\u0001*\u00020\u00182\t\u0010{\u001a\u0005\u0018\u0001HÎ\u00012\t\b\u0001\u0010Ï\u0001\u001a\u00020\tH\u0002¢\u0006\u0003\u0010Ð\u0001J\u0011\u0010Ñ\u0001\u001a\u00020\u001b2\u0006\u0010^\u001a\u00020\tH\u0002J\t\u0010Ò\u0001\u001a\u00020\u001bH\u0002J\u0011\u0010Ó\u0001\u001a\u0005\u0018\u00010Ô\u0001*\u00030Õ\u0001H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u00020\u00118\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u001f0\u001e8BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R2\u0010#\u001a&\u0012\f\u0012\n &*\u0004\u0018\u00010%0% &*\u0012\u0012\f\u0012\n &*\u0004\u0018\u00010%0%\u0018\u00010'0$X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010(\u001a\u00020)8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b*\u0010\u0013\u001a\u0004\b+\u0010,R\u000e\u0010-\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00100\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R*\u00105\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t06j\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t`7X\u0082\u000e¢\u0006\u0002\n\u0000R*\u00108\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t06j\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t`7X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00109\u001a\u00020\u000b8@X\u0081\u0004¢\u0006\f\u0012\u0004\b:\u0010\u0013\u001a\u0004\b;\u0010\rR\u0014\u0010<\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b<\u0010\rR \u0010=\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\t0\u001e0\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020?X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010@\u001a\b\u0012\u0004\u0012\u00020\t0AX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010B\u001a\u0004\u0018\u00010CX\u0082\u000e¢\u0006\u0002\n\u0000R0\u0010D\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020F0E8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bG\u0010\u0013\u001a\u0004\bH\u0010!\"\u0004\bI\u0010JR\u000e\u0010K\u001a\u00020FX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010L\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010MR\u0014\u0010N\u001a\b\u0012\u0004\u0012\u00020O0$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020QX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010R\u001a\u000e\u0012\u0004\u0012\u00020O\u0012\u0004\u0012\u00020\u001b0SX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010T\u001a\b\u0012\u0004\u0012\u00020U0AX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010V\u001a\u00020W8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bX\u0010\u0013\u001a\u0004\bY\u0010ZR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b[\u0010\\\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006Ý\u0001"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat;", "Landroidx/core/view/AccessibilityDelegateCompat;", "view", "Landroidx/compose/ui/platform/AndroidComposeView;", "(Landroidx/compose/ui/platform/AndroidComposeView;)V", "EXTRA_DATA_TEST_TRAVERSALAFTER_VAL", "", "EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL", "accessibilityCursorPosition", "", "accessibilityForceEnabledForTesting", "", "getAccessibilityForceEnabledForTesting$ui_release", "()Z", "setAccessibilityForceEnabledForTesting$ui_release", "(Z)V", "accessibilityManager", "Landroid/view/accessibility/AccessibilityManager;", "getAccessibilityManager$ui_release$annotations", "()V", "getAccessibilityManager$ui_release", "()Landroid/view/accessibility/AccessibilityManager;", "actionIdToLabel", "Landroidx/collection/SparseArrayCompat;", "", "boundsUpdateChannel", "Lkotlinx/coroutines/channels/Channel;", "", "checkingForSemanticsChanges", "currentSemanticsNodes", "", "Landroidx/compose/ui/platform/SemanticsNodeWithAdjustedBounds;", "getCurrentSemanticsNodes", "()Ljava/util/Map;", "currentSemanticsNodesInvalidated", "enabledServices", "", "Landroid/accessibilityservice/AccessibilityServiceInfo;", "kotlin.jvm.PlatformType", "", "enabledStateListener", "Landroid/view/accessibility/AccessibilityManager$AccessibilityStateChangeListener;", "getEnabledStateListener$ui_release$annotations", "getEnabledStateListener$ui_release", "()Landroid/view/accessibility/AccessibilityManager$AccessibilityStateChangeListener;", "focusedVirtualViewId", "handler", "Landroid/os/Handler;", "hoveredVirtualViewId", "getHoveredVirtualViewId$ui_release", "()I", "setHoveredVirtualViewId$ui_release", "(I)V", "idToAfterMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "idToBeforeMap", "isEnabled", "isEnabled$ui_release$annotations", "isEnabled$ui_release", "isTouchExplorationEnabled", "labelToActionId", "nodeProvider", "Landroidx/core/view/accessibility/AccessibilityNodeProviderCompat;", "paneDisplayed", "Landroidx/collection/ArraySet;", "pendingTextTraversedEvent", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$PendingTextTraversedEvent;", "previousSemanticsNodes", "", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$SemanticsNodeCopy;", "getPreviousSemanticsNodes$ui_release$annotations", "getPreviousSemanticsNodes$ui_release", "setPreviousSemanticsNodes$ui_release", "(Ljava/util/Map;)V", "previousSemanticsRoot", "previousTraversedNode", "Ljava/lang/Integer;", "scrollObservationScopes", "Landroidx/compose/ui/platform/ScrollObservationScope;", "semanticsChangeChecker", "Ljava/lang/Runnable;", "sendScrollEventIfNeededLambda", "Lkotlin/Function1;", "subtreeChangedLayoutNodes", "Landroidx/compose/ui/node/LayoutNode;", "touchExplorationStateListener", "Landroid/view/accessibility/AccessibilityManager$TouchExplorationStateChangeListener;", "getTouchExplorationStateListener$ui_release$annotations", "getTouchExplorationStateListener$ui_release", "()Landroid/view/accessibility/AccessibilityManager$TouchExplorationStateChangeListener;", "getView", "()Landroidx/compose/ui/platform/AndroidComposeView;", "addExtraDataToAccessibilityNodeInfoHelper", "virtualViewId", "info", "Landroid/view/accessibility/AccessibilityNodeInfo;", "extraDataKey", "arguments", "Landroid/os/Bundle;", "boundsUpdatesEventLoop", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "canScroll", "vertical", "direction", ViewProps.POSITION, "Landroidx/compose/ui/geometry/Offset;", "canScroll-0AR0LA0$ui_release", "(ZIJ)Z", "", "canScroll-moWRBKg$ui_release", "(Ljava/util/Collection;ZIJ)Z", "checkForSemanticsChanges", "clearAccessibilityFocus", "createEvent", "Landroid/view/accessibility/AccessibilityEvent;", "eventType", "createEvent$ui_release", "createNodeInfo", "createTextSelectionChangedEvent", "fromIndex", "toIndex", "itemCount", "text", "(ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/CharSequence;)Landroid/view/accessibility/AccessibilityEvent;", "dispatchHoverEvent", "event", "Landroid/view/MotionEvent;", "getAccessibilityNodeProvider", "host", "Landroid/view/View;", "getAccessibilitySelectionEnd", "node", "Landroidx/compose/ui/semantics/SemanticsNode;", "getAccessibilitySelectionStart", "getIterableTextForAccessibility", "getIteratorForGranularity", "Landroidx/compose/ui/platform/AccessibilityIterators$TextSegmentIterator;", "granularity", "hitTestSemanticsAt", "x", "", "y", "hitTestSemanticsAt$ui_release", "isAccessibilityFocused", "isAccessibilitySelectionExtendable", "notifySubtreeAccessibilityStateChangedIfNeeded", "layoutNode", "onLayoutChange", "onLayoutChange$ui_release", "onSemanticsChange", "onSemanticsChange$ui_release", "performActionHelper", Constants.KEY_ACTION, "populateAccessibilityNodeInfoProperties", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;", "semanticsNode", "registerScrollingId", "id", "oldScrollObservationScopes", "requestAccessibilityFocus", "semanticComparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "layoutIsRtl", "semanticsNodeIdToAccessibilityVirtualNodeId", "sendEvent", "sendEventForVirtualView", "contentChangeType", "contentDescription", "(IILjava/lang/Integer;Ljava/util/List;)Z", "sendPaneChangeEvents", "semanticsNodeId", "title", "sendPendingTextTraversedAtGranularityEvent", "sendScrollEventIfNeeded", "scrollObservationScope", "sendSemanticsPropertyChangeEvents", "newSemanticsNodes", "sendSemanticsPropertyChangeEvents$ui_release", "sendSemanticsStructureChangeEvents", "newNode", "oldNode", "sendSubtreeChangeAccessibilityEvents", "subtreeChangedSemanticsNodesIds", "setAccessibilitySelection", ViewProps.START, ViewProps.END, "traversalMode", "setContentInvalid", "setText", "setTraversalValues", "sortByGeometryGroupings", "parentListToSort", "containerChildrenMapping", "subtreeSortedByGeometryGrouping", "listToSort", "toScreenCoords", "Landroid/graphics/RectF;", "textNode", "bounds", "Landroidx/compose/ui/geometry/Rect;", "traverseAtGranularity", "forward", "extendSelection", "trimToSize", ExifInterface.GPS_DIRECTION_TRUE, RRWebVideoEvent.JsonKeys.SIZE, "(Ljava/lang/CharSequence;I)Ljava/lang/CharSequence;", "updateHoveredVirtualView", "updateSemanticsNodesCopyAndPanes", "getTextForTextField", "Landroidx/compose/ui/text/AnnotatedString;", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "Api24Impl", "Api28Impl", "Api29Impl", "Companion", "MyNodeProvider", "PendingTextTraversedEvent", "SemanticsNodeCopy", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidComposeViewAccessibilityDelegateCompat extends AccessibilityDelegateCompat {
    public static final int AccessibilityCursorPositionUndefined = -1;
    public static final int AccessibilitySliderStepsCount = 20;
    public static final String ClassName = "android.view.View";
    public static final String ExtraDataTestTagKey = "androidx.compose.ui.semantics.testTag";
    public static final int InvalidId = Integer.MIN_VALUE;
    public static final String LogTag = "AccessibilityDelegate";
    public static final int ParcelSafeTextLength = 100000;
    public static final long SendRecurringAccessibilityEventsIntervalMillis = 100;
    public static final String TextClassName = "android.widget.TextView";
    public static final String TextFieldClassName = "android.widget.EditText";
    public static final long TextTraversedEventTimeoutMillis = 1000;
    private final String EXTRA_DATA_TEST_TRAVERSALAFTER_VAL;
    private final String EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL;
    private int accessibilityCursorPosition;
    private boolean accessibilityForceEnabledForTesting;
    private final android.view.accessibility.AccessibilityManager accessibilityManager;
    private SparseArrayCompat<SparseArrayCompat<CharSequence>> actionIdToLabel;
    private final Channel<Unit> boundsUpdateChannel;
    private boolean checkingForSemanticsChanges;
    private Map<Integer, SemanticsNodeWithAdjustedBounds> currentSemanticsNodes;
    private boolean currentSemanticsNodesInvalidated;
    private List<AccessibilityServiceInfo> enabledServices;
    private final AccessibilityManager.AccessibilityStateChangeListener enabledStateListener;
    private int focusedVirtualViewId;
    private final Handler handler;
    private int hoveredVirtualViewId;
    private HashMap<Integer, Integer> idToAfterMap;
    private HashMap<Integer, Integer> idToBeforeMap;
    private SparseArrayCompat<Map<CharSequence, Integer>> labelToActionId;
    private AccessibilityNodeProviderCompat nodeProvider;
    private ArraySet<Integer> paneDisplayed;
    private PendingTextTraversedEvent pendingTextTraversedEvent;
    private Map<Integer, SemanticsNodeCopy> previousSemanticsNodes;
    private SemanticsNodeCopy previousSemanticsRoot;
    private Integer previousTraversedNode;
    private final List<ScrollObservationScope> scrollObservationScopes;
    private final Runnable semanticsChangeChecker;
    private final Function1<ScrollObservationScope, Unit> sendScrollEventIfNeededLambda;
    private final ArraySet<LayoutNode> subtreeChangedLayoutNodes;
    private final AccessibilityManager.TouchExplorationStateChangeListener touchExplorationStateListener;
    private final AndroidComposeView view;
    private static final int[] AccessibilityActionsResourceIds = {R.id.accessibility_custom_action_0, R.id.accessibility_custom_action_1, R.id.accessibility_custom_action_2, R.id.accessibility_custom_action_3, R.id.accessibility_custom_action_4, R.id.accessibility_custom_action_5, R.id.accessibility_custom_action_6, R.id.accessibility_custom_action_7, R.id.accessibility_custom_action_8, R.id.accessibility_custom_action_9, R.id.accessibility_custom_action_10, R.id.accessibility_custom_action_11, R.id.accessibility_custom_action_12, R.id.accessibility_custom_action_13, R.id.accessibility_custom_action_14, R.id.accessibility_custom_action_15, R.id.accessibility_custom_action_16, R.id.accessibility_custom_action_17, R.id.accessibility_custom_action_18, R.id.accessibility_custom_action_19, R.id.accessibility_custom_action_20, R.id.accessibility_custom_action_21, R.id.accessibility_custom_action_22, R.id.accessibility_custom_action_23, R.id.accessibility_custom_action_24, R.id.accessibility_custom_action_25, R.id.accessibility_custom_action_26, R.id.accessibility_custom_action_27, R.id.accessibility_custom_action_28, R.id.accessibility_custom_action_29, R.id.accessibility_custom_action_30, R.id.accessibility_custom_action_31};

    /* compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ToggleableState.values().length];
            try {
                iArr[ToggleableState.On.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ToggleableState.Off.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ToggleableState.Indeterminate.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat", f = "AndroidComposeViewAccessibilityDelegateCompat.android.kt", i = {0, 0, 1, 1}, l = {1999, 2029}, m = "boundsUpdatesEventLoop", n = {"this", "subtreeChangedSemanticsNodesIds", "this", "subtreeChangedSemanticsNodesIds"}, s = {"L$0", "L$1", "L$0", "L$1"})
    /* renamed from: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$boundsUpdatesEventLoop$1, reason: invalid class name and case insensitive filesystem */
    static final class C04521 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C04521(Continuation<? super C04521> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AndroidComposeViewAccessibilityDelegateCompat.this.boundsUpdatesEventLoop(this);
        }
    }

    public static /* synthetic */ void getAccessibilityManager$ui_release$annotations() {
    }

    public static /* synthetic */ void getEnabledStateListener$ui_release$annotations() {
    }

    public static /* synthetic */ void getPreviousSemanticsNodes$ui_release$annotations() {
    }

    public static /* synthetic */ void getTouchExplorationStateListener$ui_release$annotations() {
    }

    private final boolean isAccessibilityFocused(int virtualViewId) {
        return this.focusedVirtualViewId == virtualViewId;
    }

    public static /* synthetic */ void isEnabled$ui_release$annotations() {
    }

    /* renamed from: getAccessibilityForceEnabledForTesting$ui_release, reason: from getter */
    public final boolean getAccessibilityForceEnabledForTesting() {
        return this.accessibilityForceEnabledForTesting;
    }

    /* renamed from: getAccessibilityManager$ui_release, reason: from getter */
    public final android.view.accessibility.AccessibilityManager getAccessibilityManager() {
        return this.accessibilityManager;
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View host) {
        Intrinsics.checkNotNullParameter(host, "host");
        return this.nodeProvider;
    }

    /* renamed from: getEnabledStateListener$ui_release, reason: from getter */
    public final AccessibilityManager.AccessibilityStateChangeListener getEnabledStateListener() {
        return this.enabledStateListener;
    }

    /* renamed from: getHoveredVirtualViewId$ui_release, reason: from getter */
    public final int getHoveredVirtualViewId() {
        return this.hoveredVirtualViewId;
    }

    public final Map<Integer, SemanticsNodeCopy> getPreviousSemanticsNodes$ui_release() {
        return this.previousSemanticsNodes;
    }

    /* renamed from: getTouchExplorationStateListener$ui_release, reason: from getter */
    public final AccessibilityManager.TouchExplorationStateChangeListener getTouchExplorationStateListener() {
        return this.touchExplorationStateListener;
    }

    public final AndroidComposeView getView() {
        return this.view;
    }

    public final void setAccessibilityForceEnabledForTesting$ui_release(boolean z) {
        this.accessibilityForceEnabledForTesting = z;
    }

    public final void setHoveredVirtualViewId$ui_release(int i) {
        this.hoveredVirtualViewId = i;
    }

    public final void setPreviousSemanticsNodes$ui_release(Map<Integer, SemanticsNodeCopy> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.previousSemanticsNodes = map;
    }

    public AndroidComposeViewAccessibilityDelegateCompat(AndroidComposeView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.view = view;
        this.hoveredVirtualViewId = Integer.MIN_VALUE;
        Object systemService = view.getContext().getSystemService("accessibility");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
        android.view.accessibility.AccessibilityManager accessibilityManager = (android.view.accessibility.AccessibilityManager) systemService;
        this.accessibilityManager = accessibilityManager;
        this.enabledStateListener = new AccessibilityManager.AccessibilityStateChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda0
            @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
            public final void onAccessibilityStateChanged(boolean z) {
                AndroidComposeViewAccessibilityDelegateCompat.enabledStateListener$lambda$0(this.f$0, z);
            }
        };
        this.touchExplorationStateListener = new AccessibilityManager.TouchExplorationStateChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda1
            @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
            public final void onTouchExplorationStateChanged(boolean z) {
                AndroidComposeViewAccessibilityDelegateCompat.touchExplorationStateListener$lambda$1(this.f$0, z);
            }
        };
        this.enabledServices = accessibilityManager.getEnabledAccessibilityServiceList(-1);
        this.handler = new Handler(Looper.getMainLooper());
        this.nodeProvider = new AccessibilityNodeProviderCompat(new MyNodeProvider());
        this.focusedVirtualViewId = Integer.MIN_VALUE;
        this.actionIdToLabel = new SparseArrayCompat<>();
        this.labelToActionId = new SparseArrayCompat<>();
        this.accessibilityCursorPosition = -1;
        this.subtreeChangedLayoutNodes = new ArraySet<>();
        this.boundsUpdateChannel = ChannelKt.Channel$default(-1, null, null, 6, null);
        this.currentSemanticsNodesInvalidated = true;
        this.currentSemanticsNodes = MapsKt.emptyMap();
        this.paneDisplayed = new ArraySet<>();
        this.idToBeforeMap = new HashMap<>();
        this.idToAfterMap = new HashMap<>();
        this.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL";
        this.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL";
        this.previousSemanticsNodes = new LinkedHashMap();
        this.previousSemanticsRoot = new SemanticsNodeCopy(view.getSemanticsOwner().getUnmergedRootSemanticsNode(), MapsKt.emptyMap());
        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view2) {
                Intrinsics.checkNotNullParameter(view2, "view");
                AndroidComposeViewAccessibilityDelegateCompat.this.getAccessibilityManager().addAccessibilityStateChangeListener(AndroidComposeViewAccessibilityDelegateCompat.this.getEnabledStateListener());
                AndroidComposeViewAccessibilityDelegateCompat.this.getAccessibilityManager().addTouchExplorationStateChangeListener(AndroidComposeViewAccessibilityDelegateCompat.this.getTouchExplorationStateListener());
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view2) {
                Intrinsics.checkNotNullParameter(view2, "view");
                AndroidComposeViewAccessibilityDelegateCompat.this.handler.removeCallbacks(AndroidComposeViewAccessibilityDelegateCompat.this.semanticsChangeChecker);
                AndroidComposeViewAccessibilityDelegateCompat.this.getAccessibilityManager().removeAccessibilityStateChangeListener(AndroidComposeViewAccessibilityDelegateCompat.this.getEnabledStateListener());
                AndroidComposeViewAccessibilityDelegateCompat.this.getAccessibilityManager().removeTouchExplorationStateChangeListener(AndroidComposeViewAccessibilityDelegateCompat.this.getTouchExplorationStateListener());
            }
        });
        this.semanticsChangeChecker = new Runnable() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AndroidComposeViewAccessibilityDelegateCompat.semanticsChangeChecker$lambda$38(this.f$0);
            }
        };
        this.scrollObservationScopes = new ArrayList();
        this.sendScrollEventIfNeededLambda = new Function1<ScrollObservationScope, Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendScrollEventIfNeededLambda$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ScrollObservationScope scrollObservationScope) {
                invoke2(scrollObservationScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ScrollObservationScope it) {
                Intrinsics.checkNotNullParameter(it, "it");
                this.this$0.sendScrollEventIfNeeded(it);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void enabledStateListener$lambda$0(AndroidComposeViewAccessibilityDelegateCompat this$0, boolean z) {
        List<AccessibilityServiceInfo> listEmptyList;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            listEmptyList = this$0.accessibilityManager.getEnabledAccessibilityServiceList(-1);
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        this$0.enabledServices = listEmptyList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void touchExplorationStateListener$lambda$1(AndroidComposeViewAccessibilityDelegateCompat this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.enabledServices = this$0.accessibilityManager.getEnabledAccessibilityServiceList(-1);
    }

    public final boolean isEnabled$ui_release() {
        if (this.accessibilityForceEnabledForTesting) {
            return true;
        }
        if (this.accessibilityManager.isEnabled()) {
            List<AccessibilityServiceInfo> enabledServices = this.enabledServices;
            Intrinsics.checkNotNullExpressionValue(enabledServices, "enabledServices");
            if (!enabledServices.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private final boolean isTouchExplorationEnabled() {
        return this.accessibilityForceEnabledForTesting || (this.accessibilityManager.isEnabled() && this.accessibilityManager.isTouchExplorationEnabled());
    }

    /* compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$PendingTextTraversedEvent;", "", "node", "Landroidx/compose/ui/semantics/SemanticsNode;", Constants.KEY_ACTION, "", "granularity", "fromIndex", "toIndex", "traverseTime", "", "(Landroidx/compose/ui/semantics/SemanticsNode;IIIIJ)V", "getAction", "()I", "getFromIndex", "getGranularity", "getNode", "()Landroidx/compose/ui/semantics/SemanticsNode;", "getToIndex", "getTraverseTime", "()J", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class PendingTextTraversedEvent {
        private final int action;
        private final int fromIndex;
        private final int granularity;
        private final SemanticsNode node;
        private final int toIndex;
        private final long traverseTime;

        public final int getAction() {
            return this.action;
        }

        public final int getFromIndex() {
            return this.fromIndex;
        }

        public final int getGranularity() {
            return this.granularity;
        }

        public final SemanticsNode getNode() {
            return this.node;
        }

        public final int getToIndex() {
            return this.toIndex;
        }

        public final long getTraverseTime() {
            return this.traverseTime;
        }

        public PendingTextTraversedEvent(SemanticsNode node, int i, int i2, int i3, int i4, long j) {
            Intrinsics.checkNotNullParameter(node, "node");
            this.node = node;
            this.action = i;
            this.granularity = i2;
            this.fromIndex = i3;
            this.toIndex = i4;
            this.traverseTime = j;
        }
    }

    private final Map<Integer, SemanticsNodeWithAdjustedBounds> getCurrentSemanticsNodes() {
        if (this.currentSemanticsNodesInvalidated) {
            this.currentSemanticsNodesInvalidated = false;
            this.currentSemanticsNodes = AndroidComposeViewAccessibilityDelegateCompat_androidKt.getAllUncoveredSemanticsNodesToMap(this.view.getSemanticsOwner());
            setTraversalValues();
        }
        return this.currentSemanticsNodes;
    }

    /* compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0001\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u0006\u0010\u0013\u001a\u00020\u0014R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$SemanticsNodeCopy;", "", "semanticsNode", "Landroidx/compose/ui/semantics/SemanticsNode;", "currentSemanticsNodes", "", "", "Landroidx/compose/ui/platform/SemanticsNodeWithAdjustedBounds;", "(Landroidx/compose/ui/semantics/SemanticsNode;Ljava/util/Map;)V", ViewHierarchyNode.JsonKeys.CHILDREN, "", "getChildren", "()Ljava/util/Set;", "getSemanticsNode", "()Landroidx/compose/ui/semantics/SemanticsNode;", "unmergedConfig", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "getUnmergedConfig", "()Landroidx/compose/ui/semantics/SemanticsConfiguration;", "hasPaneTitle", "", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class SemanticsNodeCopy {
        private final Set<Integer> children;
        private final SemanticsNode semanticsNode;
        private final SemanticsConfiguration unmergedConfig;

        public final Set<Integer> getChildren() {
            return this.children;
        }

        public final SemanticsNode getSemanticsNode() {
            return this.semanticsNode;
        }

        public final SemanticsConfiguration getUnmergedConfig() {
            return this.unmergedConfig;
        }

        public SemanticsNodeCopy(SemanticsNode semanticsNode, Map<Integer, SemanticsNodeWithAdjustedBounds> currentSemanticsNodes) {
            Intrinsics.checkNotNullParameter(semanticsNode, "semanticsNode");
            Intrinsics.checkNotNullParameter(currentSemanticsNodes, "currentSemanticsNodes");
            this.semanticsNode = semanticsNode;
            this.unmergedConfig = semanticsNode.getUnmergedConfig();
            this.children = new LinkedHashSet();
            List<SemanticsNode> replacedChildren$ui_release = semanticsNode.getReplacedChildren$ui_release();
            int size = replacedChildren$ui_release.size();
            for (int i = 0; i < size; i++) {
                SemanticsNode semanticsNode2 = replacedChildren$ui_release.get(i);
                if (currentSemanticsNodes.containsKey(Integer.valueOf(semanticsNode2.getId()))) {
                    this.children.add(Integer.valueOf(semanticsNode2.getId()));
                }
            }
        }

        public final boolean hasPaneTitle() {
            return this.unmergedConfig.contains(SemanticsProperties.INSTANCE.getPaneTitle());
        }
    }

    /* renamed from: canScroll-0AR0LA0$ui_release, reason: not valid java name */
    public final boolean m3672canScroll0AR0LA0$ui_release(boolean vertical, int direction, long position) {
        return m3673canScrollmoWRBKg$ui_release(getCurrentSemanticsNodes().values(), vertical, direction, position);
    }

    /* renamed from: canScroll-moWRBKg$ui_release, reason: not valid java name */
    public final boolean m3673canScrollmoWRBKg$ui_release(Collection<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes, boolean vertical, int direction, long position) {
        SemanticsPropertyKey<ScrollAxisRange> horizontalScrollAxisRange;
        ScrollAxisRange scrollAxisRange;
        Intrinsics.checkNotNullParameter(currentSemanticsNodes, "currentSemanticsNodes");
        if (Offset.m1636equalsimpl0(position, Offset.INSTANCE.m1654getUnspecifiedF1C5BW0()) || !Offset.m1642isValidimpl(position)) {
            return false;
        }
        if (vertical) {
            horizontalScrollAxisRange = SemanticsProperties.INSTANCE.getVerticalScrollAxisRange();
        } else if (!vertical) {
            horizontalScrollAxisRange = SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange();
        } else {
            throw new NoWhenBranchMatchedException();
        }
        Collection<SemanticsNodeWithAdjustedBounds> collection = currentSemanticsNodes;
        if (collection.isEmpty()) {
            return false;
        }
        for (SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds : collection) {
            if (RectHelper_androidKt.toComposeRect(semanticsNodeWithAdjustedBounds.getAdjustedBounds()).m1665containsk4lQ0M(position) && (scrollAxisRange = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(semanticsNodeWithAdjustedBounds.getSemanticsNode().getConfig(), horizontalScrollAxisRange)) != null) {
                int i = scrollAxisRange.getReverseScrolling() ? -direction : direction;
                if (!(direction == 0 && scrollAxisRange.getReverseScrolling()) && i >= 0) {
                    if (scrollAxisRange.getValue().invoke().floatValue() < scrollAxisRange.getMaxValue().invoke().floatValue()) {
                        return true;
                    }
                } else if (scrollAxisRange.getValue().invoke().floatValue() > 0.0f) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AccessibilityNodeInfo createNodeInfo(int virtualViewId) throws Resources.NotFoundException {
        LifecycleOwner lifecycleOwner;
        Lifecycle lifecycleRegistry;
        AndroidComposeView.ViewTreeOwners viewTreeOwners = this.view.getViewTreeOwners();
        if (((viewTreeOwners == null || (lifecycleOwner = viewTreeOwners.getLifecycleOwner()) == null || (lifecycleRegistry = lifecycleOwner.getLifecycleRegistry()) == null) ? null : lifecycleRegistry.getState()) == Lifecycle.State.DESTROYED) {
            return null;
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompatObtain = AccessibilityNodeInfoCompat.obtain();
        Intrinsics.checkNotNullExpressionValue(accessibilityNodeInfoCompatObtain, "obtain()");
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes().get(Integer.valueOf(virtualViewId));
        if (semanticsNodeWithAdjustedBounds == null) {
            return null;
        }
        SemanticsNode semanticsNode = semanticsNodeWithAdjustedBounds.getSemanticsNode();
        if (virtualViewId == -1) {
            Object parentForAccessibility = ViewCompat.getParentForAccessibility(this.view);
            accessibilityNodeInfoCompatObtain.setParent(parentForAccessibility instanceof View ? (View) parentForAccessibility : null);
        } else if (semanticsNode.getParent() != null) {
            SemanticsNode parent = semanticsNode.getParent();
            Intrinsics.checkNotNull(parent);
            int id = parent.getId();
            accessibilityNodeInfoCompatObtain.setParent(this.view, id != this.view.getSemanticsOwner().getUnmergedRootSemanticsNode().getId() ? id : -1);
        } else {
            throw new IllegalStateException("semanticsNode " + virtualViewId + " has null parent");
        }
        accessibilityNodeInfoCompatObtain.setSource(this.view, virtualViewId);
        Rect adjustedBounds = semanticsNodeWithAdjustedBounds.getAdjustedBounds();
        long jMo3358localToScreenMKHz9U = this.view.mo3358localToScreenMKHz9U(OffsetKt.Offset(adjustedBounds.left, adjustedBounds.top));
        long jMo3358localToScreenMKHz9U2 = this.view.mo3358localToScreenMKHz9U(OffsetKt.Offset(adjustedBounds.right, adjustedBounds.bottom));
        accessibilityNodeInfoCompatObtain.setBoundsInScreen(new Rect((int) Math.floor(Offset.m1639getXimpl(jMo3358localToScreenMKHz9U)), (int) Math.floor(Offset.m1640getYimpl(jMo3358localToScreenMKHz9U)), (int) Math.ceil(Offset.m1639getXimpl(jMo3358localToScreenMKHz9U2)), (int) Math.ceil(Offset.m1640getYimpl(jMo3358localToScreenMKHz9U2))));
        populateAccessibilityNodeInfoProperties(virtualViewId, accessibilityNodeInfoCompatObtain, semanticsNode);
        return accessibilityNodeInfoCompatObtain.unwrap();
    }

    private final Comparator<SemanticsNode> semanticComparator(boolean layoutIsRtl) {
        final Comparator comparatorCompareBy = ComparisonsKt.compareBy(new Function1<SemanticsNode, Comparable<?>>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$comparator$1
            @Override // kotlin.jvm.functions.Function1
            public final Comparable<?> invoke(SemanticsNode it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Float.valueOf(LayoutCoordinatesKt.boundsInWindow(it.getLayoutNode().getCoordinates()).getLeft());
            }
        }, new Function1<SemanticsNode, Comparable<?>>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$comparator$2
            @Override // kotlin.jvm.functions.Function1
            public final Comparable<?> invoke(SemanticsNode it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Float.valueOf(LayoutCoordinatesKt.boundsInWindow(it.getLayoutNode().getCoordinates()).getTop());
            }
        }, new Function1<SemanticsNode, Comparable<?>>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$comparator$3
            @Override // kotlin.jvm.functions.Function1
            public final Comparable<?> invoke(SemanticsNode it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Float.valueOf(LayoutCoordinatesKt.boundsInWindow(it.getLayoutNode().getCoordinates()).getBottom());
            }
        }, new Function1<SemanticsNode, Comparable<?>>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$comparator$4
            @Override // kotlin.jvm.functions.Function1
            public final Comparable<?> invoke(SemanticsNode it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Float.valueOf(LayoutCoordinatesKt.boundsInWindow(it.getLayoutNode().getCoordinates()).getRight());
            }
        });
        if (layoutIsRtl) {
            comparatorCompareBy = ComparisonsKt.compareBy(new Function1<SemanticsNode, Comparable<?>>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.semanticComparator.1
                @Override // kotlin.jvm.functions.Function1
                public final Comparable<?> invoke(SemanticsNode it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Float.valueOf(LayoutCoordinatesKt.boundsInWindow(it.getLayoutNode().getCoordinates()).getRight());
                }
            }, new Function1<SemanticsNode, Comparable<?>>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.semanticComparator.2
                @Override // kotlin.jvm.functions.Function1
                public final Comparable<?> invoke(SemanticsNode it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Float.valueOf(LayoutCoordinatesKt.boundsInWindow(it.getLayoutNode().getCoordinates()).getTop());
                }
            }, new Function1<SemanticsNode, Comparable<?>>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.semanticComparator.3
                @Override // kotlin.jvm.functions.Function1
                public final Comparable<?> invoke(SemanticsNode it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Float.valueOf(LayoutCoordinatesKt.boundsInWindow(it.getLayoutNode().getCoordinates()).getBottom());
                }
            }, new Function1<SemanticsNode, Comparable<?>>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.semanticComparator.4
                @Override // kotlin.jvm.functions.Function1
                public final Comparable<?> invoke(SemanticsNode it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Float.valueOf(LayoutCoordinatesKt.boundsInWindow(it.getLayoutNode().getCoordinates()).getLeft());
                }
            });
        }
        final Comparator<LayoutNode> zComparator$ui_release = LayoutNode.INSTANCE.getZComparator$ui_release();
        final Comparator comparator = new Comparator() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$$inlined$thenBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int iCompare = comparatorCompareBy.compare(t, t2);
                return iCompare != 0 ? iCompare : zComparator$ui_release.compare(((SemanticsNode) t).getLayoutNode(), ((SemanticsNode) t2).getLayoutNode());
            }
        };
        return new Comparator() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$$inlined$thenBy$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int iCompare = comparator.compare(t, t2);
                return iCompare != 0 ? iCompare : ComparisonsKt.compareValues(Integer.valueOf(((SemanticsNode) t).getId()), Integer.valueOf(((SemanticsNode) t2).getId()));
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ List sortByGeometryGroupings$default(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat, boolean z, List list, Map map, int i, Object obj) {
        if ((i & 4) != 0) {
            map = new LinkedHashMap();
        }
        return androidComposeViewAccessibilityDelegateCompat.sortByGeometryGroupings(z, list, map);
    }

    private final List<SemanticsNode> sortByGeometryGroupings(boolean layoutIsRtl, List<SemanticsNode> parentListToSort, Map<Integer, List<SemanticsNode>> containerChildrenMapping) {
        ArrayList arrayList = new ArrayList();
        int lastIndex = CollectionsKt.getLastIndex(parentListToSort);
        if (lastIndex >= 0) {
            int i = 0;
            while (true) {
                SemanticsNode semanticsNode = parentListToSort.get(i);
                if (i == 0 || !sortByGeometryGroupings$placedEntryRowOverlaps(arrayList, semanticsNode)) {
                    arrayList.add(new Pair(LayoutCoordinatesKt.boundsInWindow(semanticsNode.getLayoutNode().getCoordinates()), CollectionsKt.mutableListOf(semanticsNode)));
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        CollectionsKt.sortWith(arrayList, ComparisonsKt.compareBy(new Function1<Pair<? extends androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>>, Comparable<?>>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sortByGeometryGroupings.1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Comparable<?> invoke2(Pair<androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Float.valueOf(it.getFirst().getTop());
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Comparable<?> invoke(Pair<? extends androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>> pair) {
                return invoke2((Pair<androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>>) pair);
            }
        }, new Function1<Pair<? extends androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>>, Comparable<?>>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sortByGeometryGroupings.2
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Comparable<?> invoke2(Pair<androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Float.valueOf(it.getFirst().getBottom());
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Comparable<?> invoke(Pair<? extends androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>> pair) {
                return invoke2((Pair<androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>>) pair);
            }
        }));
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            Pair pair = (Pair) arrayList.get(i2);
            CollectionsKt.sortWith((List) pair.getSecond(), semanticComparator(layoutIsRtl));
            List list = (List) pair.getSecond();
            int size2 = list.size();
            for (int i3 = 0; i3 < size2; i3++) {
                SemanticsNode semanticsNode2 = (SemanticsNode) list.get(i3);
                List<SemanticsNode> listMutableListOf = containerChildrenMapping.get(Integer.valueOf(semanticsNode2.getId()));
                if (listMutableListOf == null) {
                    listMutableListOf = CollectionsKt.mutableListOf(semanticsNode2);
                }
                arrayList2.addAll(listMutableListOf);
            }
        }
        return arrayList2;
    }

    private static final boolean sortByGeometryGroupings$placedEntryRowOverlaps(List<Pair<androidx.compose.ui.geometry.Rect, List<SemanticsNode>>> list, SemanticsNode semanticsNode) {
        float top = LayoutCoordinatesKt.boundsInWindow(semanticsNode.getLayoutNode().getCoordinates()).getTop();
        float bottom = LayoutCoordinatesKt.boundsInWindow(semanticsNode.getLayoutNode().getCoordinates()).getBottom();
        OpenEndRange<Float> openEndRangeRangeUntil = AndroidComposeViewAccessibilityDelegateCompat_androidKt.rangeUntil(top, bottom);
        int lastIndex = CollectionsKt.getLastIndex(list);
        if (lastIndex >= 0) {
            int i = 0;
            while (true) {
                androidx.compose.ui.geometry.Rect first = list.get(i).getFirst();
                if (!AndroidComposeViewAccessibilityDelegateCompat_androidKt.overlaps(AndroidComposeViewAccessibilityDelegateCompat_androidKt.rangeUntil(first.getTop(), first.getBottom()), openEndRangeRangeUntil)) {
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                } else {
                    list.set(i, new Pair<>(first.intersect(new androidx.compose.ui.geometry.Rect(0.0f, top, Float.POSITIVE_INFINITY, bottom)), list.get(i).getSecond()));
                    list.get(i).getSecond().add(semanticsNode);
                    return true;
                }
            }
        }
        return false;
    }

    private final List<SemanticsNode> subtreeSortedByGeometryGrouping(boolean layoutIsRtl, List<SemanticsNode> listToSort) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList arrayList = new ArrayList();
        int size = listToSort.size();
        for (int i = 0; i < size; i++) {
            subtreeSortedByGeometryGrouping$depthFirstSearch(arrayList, linkedHashMap, this, layoutIsRtl, listToSort.get(i));
        }
        return sortByGeometryGroupings(layoutIsRtl, arrayList, linkedHashMap);
    }

    private static final void subtreeSortedByGeometryGrouping$depthFirstSearch(List<SemanticsNode> list, Map<Integer, List<SemanticsNode>> map, AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat, boolean z, SemanticsNode semanticsNode) {
        list.add(semanticsNode);
        if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.getSemanticsNodeIsStructurallySignificant(semanticsNode)) {
            map.put(Integer.valueOf(semanticsNode.getId()), androidComposeViewAccessibilityDelegateCompat.subtreeSortedByGeometryGrouping(z, CollectionsKt.toMutableList((Collection) semanticsNode.getChildren())));
            return;
        }
        List<SemanticsNode> children = semanticsNode.getChildren();
        int size = children.size();
        for (int i = 0; i < size; i++) {
            subtreeSortedByGeometryGrouping$depthFirstSearch(list, map, androidComposeViewAccessibilityDelegateCompat, z, children.get(i));
        }
    }

    private final void setTraversalValues() {
        this.idToBeforeMap.clear();
        this.idToAfterMap.clear();
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes().get(-1);
        SemanticsNode semanticsNode = semanticsNodeWithAdjustedBounds != null ? semanticsNodeWithAdjustedBounds.getSemanticsNode() : null;
        Intrinsics.checkNotNull(semanticsNode);
        List<SemanticsNode> listSubtreeSortedByGeometryGrouping = subtreeSortedByGeometryGrouping(AndroidComposeViewAccessibilityDelegateCompat_androidKt.isRtl(semanticsNode), CollectionsKt.toMutableList((Collection) semanticsNode.getChildren()));
        int lastIndex = CollectionsKt.getLastIndex(listSubtreeSortedByGeometryGrouping);
        int i = 1;
        if (1 > lastIndex) {
            return;
        }
        while (true) {
            int id = listSubtreeSortedByGeometryGrouping.get(i - 1).getId();
            int id2 = listSubtreeSortedByGeometryGrouping.get(i).getId();
            this.idToBeforeMap.put(Integer.valueOf(id), Integer.valueOf(id2));
            this.idToAfterMap.put(Integer.valueOf(id2), Integer.valueOf(id));
            if (i == lastIndex) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void populateAccessibilityNodeInfoProperties(int virtualViewId, AccessibilityNodeInfoCompat info, SemanticsNode semanticsNode) throws Resources.NotFoundException {
        NodeCoordinator nodeCoordinatorFindCoordinatorToGetBounds$ui_release;
        Map<CharSequence, Integer> map;
        AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat;
        AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat2;
        int iCoerceIn;
        String string;
        String str;
        Intrinsics.checkNotNullParameter(info, "info");
        Intrinsics.checkNotNullParameter(semanticsNode, "semanticsNode");
        int i = 0;
        boolean z = !semanticsNode.getIsFake() && semanticsNode.getReplacedChildren$ui_release().isEmpty() && AndroidComposeViewAccessibilityDelegateCompat_androidKt.findClosestParentNode(semanticsNode.getLayoutNode(), new Function1<LayoutNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$populateAccessibilityNodeInfoProperties$isUnmergedLeafNode$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(LayoutNode it) {
                SemanticsConfiguration semanticsConfigurationCollapsedSemanticsConfiguration;
                Intrinsics.checkNotNullParameter(it, "it");
                SemanticsModifierNode outerSemantics = SemanticsNodeKt.getOuterSemantics(it);
                boolean z2 = false;
                if (outerSemantics != null && (semanticsConfigurationCollapsedSemanticsConfiguration = SemanticsModifierNodeKt.collapsedSemanticsConfiguration(outerSemantics)) != null && semanticsConfigurationCollapsedSemanticsConfiguration.getIsMergingSemanticsOfDescendants()) {
                    z2 = true;
                }
                return Boolean.valueOf(z2);
            }
        }) == null;
        info.setClassName(ClassName);
        Role role = (Role) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getRole());
        if (role != null) {
            int value = role.getValue();
            if (semanticsNode.getIsFake() || semanticsNode.getReplacedChildren$ui_release().isEmpty()) {
                if (Role.m3748equalsimpl0(role.getValue(), Role.INSTANCE.m3758getTabo7Vup1c())) {
                    info.setRoleDescription(this.view.getContext().getResources().getString(R.string.tab));
                } else if (Role.m3748equalsimpl0(role.getValue(), Role.INSTANCE.m3757getSwitcho7Vup1c())) {
                    info.setRoleDescription(this.view.getContext().getResources().getString(R.string.switch_role));
                } else {
                    if (Role.m3748equalsimpl0(value, Role.INSTANCE.m3752getButtono7Vup1c())) {
                        str = "android.widget.Button";
                    } else if (Role.m3748equalsimpl0(value, Role.INSTANCE.m3753getCheckboxo7Vup1c())) {
                        str = "android.widget.CheckBox";
                    } else if (Role.m3748equalsimpl0(value, Role.INSTANCE.m3756getRadioButtono7Vup1c())) {
                        str = "android.widget.RadioButton";
                    } else if (Role.m3748equalsimpl0(value, Role.INSTANCE.m3755getImageo7Vup1c())) {
                        str = SentryReplayOptions.IMAGE_VIEW_CLASS_NAME;
                    } else {
                        str = Role.m3748equalsimpl0(value, Role.INSTANCE.m3754getDropdownListo7Vup1c()) ? "android.widget.Spinner" : null;
                    }
                    if (!Role.m3748equalsimpl0(role.getValue(), Role.INSTANCE.m3755getImageo7Vup1c()) || z || semanticsNode.getUnmergedConfig().getIsMergingSemanticsOfDescendants()) {
                        info.setClassName(str);
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
            Unit unit2 = Unit.INSTANCE;
        }
        if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.isTextField(semanticsNode)) {
            info.setClassName(TextFieldClassName);
        }
        if (semanticsNode.getConfig().contains(SemanticsProperties.INSTANCE.getText())) {
            info.setClassName("android.widget.TextView");
        }
        info.setPackageName(this.view.getContext().getPackageName());
        info.setImportantForAccessibility(true);
        List<SemanticsNode> replacedChildren$ui_release = semanticsNode.getReplacedChildren$ui_release();
        int size = replacedChildren$ui_release.size();
        for (int i2 = 0; i2 < size; i2++) {
            SemanticsNode semanticsNode2 = replacedChildren$ui_release.get(i2);
            if (getCurrentSemanticsNodes().containsKey(Integer.valueOf(semanticsNode2.getId()))) {
                AndroidViewHolder androidViewHolder = this.view.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().get(semanticsNode2.getLayoutNode());
                if (androidViewHolder != null) {
                    info.addChild(androidViewHolder);
                } else {
                    info.addChild(this.view, semanticsNode2.getId());
                }
            }
        }
        if (this.focusedVirtualViewId == virtualViewId) {
            info.setAccessibilityFocused(true);
            info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        } else {
            info.setAccessibilityFocused(false);
            info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_ACCESSIBILITY_FOCUS);
        }
        setText(semanticsNode, info);
        setContentInvalid(semanticsNode, info);
        info.setStateDescription((CharSequence) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getStateDescription()));
        ToggleableState toggleableState = (ToggleableState) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getToggleableState());
        if (toggleableState != null) {
            info.setCheckable(true);
            int i3 = WhenMappings.$EnumSwitchMapping$0[toggleableState.ordinal()];
            if (i3 == 1) {
                info.setChecked(true);
                int iM3757getSwitcho7Vup1c = Role.INSTANCE.m3757getSwitcho7Vup1c();
                if (role != null && Role.m3748equalsimpl0(role.getValue(), iM3757getSwitcho7Vup1c) && info.getStateDescription() == null) {
                    info.setStateDescription(this.view.getContext().getResources().getString(R.string.on));
                }
            } else if (i3 == 2) {
                info.setChecked(false);
                int iM3757getSwitcho7Vup1c2 = Role.INSTANCE.m3757getSwitcho7Vup1c();
                if (role != null && Role.m3748equalsimpl0(role.getValue(), iM3757getSwitcho7Vup1c2) && info.getStateDescription() == null) {
                    info.setStateDescription(this.view.getContext().getResources().getString(R.string.off));
                }
            } else if (i3 == 3 && info.getStateDescription() == null) {
                info.setStateDescription(this.view.getContext().getResources().getString(R.string.indeterminate));
            }
            Unit unit3 = Unit.INSTANCE;
            Unit unit4 = Unit.INSTANCE;
        }
        Boolean bool = (Boolean) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getSelected());
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            int iM3758getTabo7Vup1c = Role.INSTANCE.m3758getTabo7Vup1c();
            if (role != null && Role.m3748equalsimpl0(role.getValue(), iM3758getTabo7Vup1c)) {
                info.setSelected(zBooleanValue);
            } else {
                info.setCheckable(true);
                info.setChecked(zBooleanValue);
                if (info.getStateDescription() == null) {
                    if (zBooleanValue) {
                        string = this.view.getContext().getResources().getString(R.string.selected);
                    } else {
                        string = this.view.getContext().getResources().getString(R.string.not_selected);
                    }
                    info.setStateDescription(string);
                }
            }
            Unit unit5 = Unit.INSTANCE;
            Unit unit6 = Unit.INSTANCE;
        }
        if (!semanticsNode.getUnmergedConfig().getIsMergingSemanticsOfDescendants() || semanticsNode.getReplacedChildren$ui_release().isEmpty()) {
            List list = (List) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getContentDescription());
            info.setContentDescription(list != null ? (String) CollectionsKt.firstOrNull(list) : null);
        }
        String str2 = (String) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getTestTag());
        if (str2 != null) {
            SemanticsNode parent = semanticsNode;
            while (true) {
                if (parent == null) {
                    break;
                }
                if (parent.getUnmergedConfig().contains(SemanticsPropertiesAndroid.INSTANCE.getTestTagsAsResourceId())) {
                    if (((Boolean) parent.getUnmergedConfig().get(SemanticsPropertiesAndroid.INSTANCE.getTestTagsAsResourceId())).booleanValue()) {
                        info.setViewIdResourceName(str2);
                    }
                } else {
                    parent = parent.getParent();
                }
            }
        }
        if (((Unit) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getHeading())) != null) {
            info.setHeading(true);
            Unit unit7 = Unit.INSTANCE;
            Unit unit8 = Unit.INSTANCE;
        }
        info.setPassword(AndroidComposeViewAccessibilityDelegateCompat_androidKt.isPassword(semanticsNode));
        info.setEditable(AndroidComposeViewAccessibilityDelegateCompat_androidKt.isTextField(semanticsNode));
        info.setEnabled(AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode));
        info.setFocusable(semanticsNode.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getFocused()));
        if (info.isFocusable()) {
            info.setFocused(((Boolean) semanticsNode.getUnmergedConfig().get(SemanticsProperties.INSTANCE.getFocused())).booleanValue());
            if (info.isFocused()) {
                info.addAction(2);
            } else {
                info.addAction(1);
            }
        }
        if (semanticsNode.getIsFake()) {
            SemanticsNode parent2 = semanticsNode.getParent();
            nodeCoordinatorFindCoordinatorToGetBounds$ui_release = parent2 != null ? parent2.findCoordinatorToGetBounds$ui_release() : null;
        } else {
            nodeCoordinatorFindCoordinatorToGetBounds$ui_release = semanticsNode.findCoordinatorToGetBounds$ui_release();
        }
        info.setVisibleToUser((nodeCoordinatorFindCoordinatorToGetBounds$ui_release == null || !nodeCoordinatorFindCoordinatorToGetBounds$ui_release.isTransparent()) && SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getInvisibleToUser()) == null);
        LiveRegionMode liveRegionMode = (LiveRegionMode) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getLiveRegion());
        if (liveRegionMode != null) {
            int value2 = liveRegionMode.getValue();
            info.setLiveRegion((LiveRegionMode.m3739equalsimpl0(value2, LiveRegionMode.INSTANCE.m3744getPolite0phEisY()) || !LiveRegionMode.m3739equalsimpl0(value2, LiveRegionMode.INSTANCE.m3743getAssertive0phEisY())) ? 1 : 2);
            Unit unit9 = Unit.INSTANCE;
            Unit unit10 = Unit.INSTANCE;
        }
        info.setClickable(false);
        AccessibilityAction accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getOnClick());
        if (accessibilityAction != null) {
            boolean zAreEqual = Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getSelected()), (Object) true);
            info.setClickable(!zAreEqual);
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode) && !zAreEqual) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(16, accessibilityAction.getLabel()));
            }
            Unit unit11 = Unit.INSTANCE;
            Unit unit12 = Unit.INSTANCE;
        }
        info.setLongClickable(false);
        AccessibilityAction accessibilityAction2 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getOnLongClick());
        if (accessibilityAction2 != null) {
            info.setLongClickable(true);
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(32, accessibilityAction2.getLabel()));
            }
            Unit unit13 = Unit.INSTANCE;
            Unit unit14 = Unit.INSTANCE;
        }
        AccessibilityAction accessibilityAction3 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getCopyText());
        if (accessibilityAction3 != null) {
            info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(16384, accessibilityAction3.getLabel()));
            Unit unit15 = Unit.INSTANCE;
            Unit unit16 = Unit.INSTANCE;
        }
        if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
            AccessibilityAction accessibilityAction4 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetText());
            if (accessibilityAction4 != null) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(2097152, accessibilityAction4.getLabel()));
                Unit unit17 = Unit.INSTANCE;
                Unit unit18 = Unit.INSTANCE;
            }
            AccessibilityAction accessibilityAction5 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getCutText());
            if (accessibilityAction5 != null) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(65536, accessibilityAction5.getLabel()));
                Unit unit19 = Unit.INSTANCE;
                Unit unit20 = Unit.INSTANCE;
            }
            AccessibilityAction accessibilityAction6 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPasteText());
            if (accessibilityAction6 != null) {
                if (info.isFocused() && this.view.getClipboardManager().hasText()) {
                    info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(32768, accessibilityAction6.getLabel()));
                }
                Unit unit21 = Unit.INSTANCE;
                Unit unit22 = Unit.INSTANCE;
            }
        }
        String iterableTextForAccessibility = getIterableTextForAccessibility(semanticsNode);
        if (iterableTextForAccessibility != null && iterableTextForAccessibility.length() != 0) {
            info.setTextSelection(getAccessibilitySelectionStart(semanticsNode), getAccessibilitySelectionEnd(semanticsNode));
            AccessibilityAction accessibilityAction7 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetSelection());
            info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(131072, accessibilityAction7 != null ? accessibilityAction7.getLabel() : null));
            info.addAction(256);
            info.addAction(512);
            info.setMovementGranularities(11);
            List list2 = (List) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getContentDescription());
            if ((list2 == null || list2.isEmpty()) && semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getGetTextLayoutResult()) && !AndroidComposeViewAccessibilityDelegateCompat_androidKt.excludeLineAndPageGranularities(semanticsNode)) {
                info.setMovementGranularities(info.getMovementGranularities() | 20);
            }
        }
        if (Build.VERSION.SDK_INT >= 26) {
            ArrayList arrayList = new ArrayList();
            CharSequence text = info.getText();
            if (text != null && text.length() != 0 && semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getGetTextLayoutResult())) {
                arrayList.add("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY");
            }
            if (semanticsNode.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getTestTag())) {
                arrayList.add(ExtraDataTestTagKey);
            }
            if (!arrayList.isEmpty()) {
                AccessibilityNodeInfoVerificationHelperMethods accessibilityNodeInfoVerificationHelperMethods = AccessibilityNodeInfoVerificationHelperMethods.INSTANCE;
                AccessibilityNodeInfo accessibilityNodeInfoUnwrap = info.unwrap();
                Intrinsics.checkNotNullExpressionValue(accessibilityNodeInfoUnwrap, "info.unwrap()");
                accessibilityNodeInfoVerificationHelperMethods.setAvailableExtraData(accessibilityNodeInfoUnwrap, arrayList);
            }
        }
        ProgressBarRangeInfo progressBarRangeInfo = (ProgressBarRangeInfo) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getProgressBarRangeInfo());
        if (progressBarRangeInfo != null) {
            if (semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getSetProgress())) {
                info.setClassName("android.widget.SeekBar");
            } else {
                info.setClassName("android.widget.ProgressBar");
            }
            if (progressBarRangeInfo != ProgressBarRangeInfo.INSTANCE.getIndeterminate()) {
                info.setRangeInfo(AccessibilityNodeInfoCompat.RangeInfoCompat.obtain(1, progressBarRangeInfo.getRange().getStart().floatValue(), progressBarRangeInfo.getRange().getEndInclusive().floatValue(), progressBarRangeInfo.getCurrent()));
                if (info.getStateDescription() == null) {
                    ClosedFloatingPointRange<Float> range = progressBarRangeInfo.getRange();
                    float fCoerceIn = RangesKt.coerceIn(range.getEndInclusive().floatValue() - range.getStart().floatValue() == 0.0f ? 0.0f : (progressBarRangeInfo.getCurrent() - range.getStart().floatValue()) / (range.getEndInclusive().floatValue() - range.getStart().floatValue()), 0.0f, 1.0f);
                    if (fCoerceIn == 0.0f) {
                        iCoerceIn = 0;
                    } else {
                        iCoerceIn = 100;
                        if (fCoerceIn != 1.0f) {
                            iCoerceIn = RangesKt.coerceIn(MathKt.roundToInt(fCoerceIn * 100), 1, 99);
                        }
                    }
                    info.setStateDescription(this.view.getContext().getResources().getString(R.string.template_percent, Integer.valueOf(iCoerceIn)));
                }
            } else if (info.getStateDescription() == null) {
                info.setStateDescription(this.view.getContext().getResources().getString(R.string.in_progress));
            }
            if (semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getSetProgress()) && AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
                if (progressBarRangeInfo.getCurrent() < RangesKt.coerceAtLeast(progressBarRangeInfo.getRange().getEndInclusive().floatValue(), progressBarRangeInfo.getRange().getStart().floatValue())) {
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
                }
                if (progressBarRangeInfo.getCurrent() > RangesKt.coerceAtMost(progressBarRangeInfo.getRange().getStart().floatValue(), progressBarRangeInfo.getRange().getEndInclusive().floatValue())) {
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                }
            }
        }
        Api24Impl.addSetProgressAction(info, semanticsNode);
        CollectionInfoKt.setCollectionInfo(semanticsNode, info);
        CollectionInfoKt.setCollectionItemInfo(semanticsNode, info);
        ScrollAxisRange scrollAxisRange = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange());
        AccessibilityAction accessibilityAction8 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getScrollBy());
        if (scrollAxisRange != null && accessibilityAction8 != null) {
            if (!CollectionInfoKt.hasCollectionInfo(semanticsNode)) {
                info.setClassName("android.widget.HorizontalScrollView");
            }
            if (scrollAxisRange.getMaxValue().invoke().floatValue() > 0.0f) {
                info.setScrollable(true);
            }
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
                if (populateAccessibilityNodeInfoProperties$canScrollForward(scrollAxisRange)) {
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
                    if (!AndroidComposeViewAccessibilityDelegateCompat_androidKt.isRtl(semanticsNode)) {
                        accessibilityActionCompat2 = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT;
                    } else {
                        accessibilityActionCompat2 = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT;
                    }
                    info.addAction(accessibilityActionCompat2);
                }
                if (populateAccessibilityNodeInfoProperties$canScrollBackward(scrollAxisRange)) {
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                    if (!AndroidComposeViewAccessibilityDelegateCompat_androidKt.isRtl(semanticsNode)) {
                        accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT;
                    } else {
                        accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT;
                    }
                    info.addAction(accessibilityActionCompat);
                }
            }
        }
        ScrollAxisRange scrollAxisRange2 = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getVerticalScrollAxisRange());
        if (scrollAxisRange2 != null && accessibilityAction8 != null) {
            if (!CollectionInfoKt.hasCollectionInfo(semanticsNode)) {
                info.setClassName("android.widget.ScrollView");
            }
            if (scrollAxisRange2.getMaxValue().invoke().floatValue() > 0.0f) {
                info.setScrollable(true);
            }
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
                if (populateAccessibilityNodeInfoProperties$canScrollForward(scrollAxisRange2)) {
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_DOWN);
                }
                if (populateAccessibilityNodeInfoProperties$canScrollBackward(scrollAxisRange2)) {
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_UP);
                }
            }
        }
        if (Build.VERSION.SDK_INT >= 29) {
            Api29Impl.addPageActions(info, semanticsNode);
        }
        info.setPaneTitle((CharSequence) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getPaneTitle()));
        if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
            AccessibilityAction accessibilityAction9 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getExpand());
            if (accessibilityAction9 != null) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(262144, accessibilityAction9.getLabel()));
                Unit unit23 = Unit.INSTANCE;
                Unit unit24 = Unit.INSTANCE;
            }
            AccessibilityAction accessibilityAction10 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getCollapse());
            if (accessibilityAction10 != null) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(524288, accessibilityAction10.getLabel()));
                Unit unit25 = Unit.INSTANCE;
                Unit unit26 = Unit.INSTANCE;
            }
            AccessibilityAction accessibilityAction11 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getDismiss());
            if (accessibilityAction11 != null) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(1048576, accessibilityAction11.getLabel()));
                Unit unit27 = Unit.INSTANCE;
                Unit unit28 = Unit.INSTANCE;
            }
            if (semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getCustomActions())) {
                List list3 = (List) semanticsNode.getUnmergedConfig().get(SemanticsActions.INSTANCE.getCustomActions());
                int size2 = list3.size();
                int[] iArr = AccessibilityActionsResourceIds;
                if (size2 >= iArr.length) {
                    throw new IllegalStateException("Can't have more than " + iArr.length + " custom actions for one widget");
                }
                SparseArrayCompat<CharSequence> sparseArrayCompat = new SparseArrayCompat<>();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                if (this.labelToActionId.containsKey(virtualViewId)) {
                    Map<CharSequence, Integer> map2 = this.labelToActionId.get(virtualViewId);
                    List<Integer> mutableList = ArraysKt.toMutableList(iArr);
                    ArrayList arrayList2 = new ArrayList();
                    int size3 = list3.size();
                    while (i < size3) {
                        CustomAccessibilityAction customAccessibilityAction = (CustomAccessibilityAction) list3.get(i);
                        Intrinsics.checkNotNull(map2);
                        if (map2.containsKey(customAccessibilityAction.getLabel())) {
                            Integer num = map2.get(customAccessibilityAction.getLabel());
                            Intrinsics.checkNotNull(num);
                            map = map2;
                            sparseArrayCompat.put(num.intValue(), customAccessibilityAction.getLabel());
                            linkedHashMap.put(customAccessibilityAction.getLabel(), num);
                            mutableList.remove(num);
                            info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(num.intValue(), customAccessibilityAction.getLabel()));
                        } else {
                            map = map2;
                            arrayList2.add(customAccessibilityAction);
                        }
                        i++;
                        map2 = map;
                    }
                    int size4 = arrayList2.size();
                    for (int i4 = 0; i4 < size4; i4++) {
                        CustomAccessibilityAction customAccessibilityAction2 = (CustomAccessibilityAction) arrayList2.get(i4);
                        int iIntValue = mutableList.get(i4).intValue();
                        sparseArrayCompat.put(iIntValue, customAccessibilityAction2.getLabel());
                        linkedHashMap.put(customAccessibilityAction2.getLabel(), Integer.valueOf(iIntValue));
                        info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(iIntValue, customAccessibilityAction2.getLabel()));
                    }
                } else {
                    int size5 = list3.size();
                    for (int i5 = 0; i5 < size5; i5++) {
                        CustomAccessibilityAction customAccessibilityAction3 = (CustomAccessibilityAction) list3.get(i5);
                        int i6 = AccessibilityActionsResourceIds[i5];
                        sparseArrayCompat.put(i6, customAccessibilityAction3.getLabel());
                        linkedHashMap.put(customAccessibilityAction3.getLabel(), Integer.valueOf(i6));
                        info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(i6, customAccessibilityAction3.getLabel()));
                    }
                }
                this.actionIdToLabel.put(virtualViewId, sparseArrayCompat);
                this.labelToActionId.put(virtualViewId, linkedHashMap);
            }
        }
        info.setScreenReaderFocusable(semanticsNode.getUnmergedConfig().getIsMergingSemanticsOfDescendants() || (z && (info.getContentDescription() != null || info.getText() != null || info.getHintText() != null || info.getStateDescription() != null || info.isCheckable())));
        if (this.idToBeforeMap.get(Integer.valueOf(virtualViewId)) != null) {
            Integer num2 = this.idToBeforeMap.get(Integer.valueOf(virtualViewId));
            if (num2 != null) {
                info.setTraversalBefore(this.view, num2.intValue());
                Unit unit29 = Unit.INSTANCE;
                Unit unit30 = Unit.INSTANCE;
            }
            AccessibilityNodeInfo accessibilityNodeInfoUnwrap2 = info.unwrap();
            Intrinsics.checkNotNullExpressionValue(accessibilityNodeInfoUnwrap2, "info.unwrap()");
            addExtraDataToAccessibilityNodeInfoHelper(virtualViewId, accessibilityNodeInfoUnwrap2, this.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL, null);
        }
        if (this.idToAfterMap.get(Integer.valueOf(virtualViewId)) != null) {
            Integer num3 = this.idToAfterMap.get(Integer.valueOf(virtualViewId));
            if (num3 != null) {
                info.setTraversalAfter(this.view, num3.intValue());
                Unit unit31 = Unit.INSTANCE;
                Unit unit32 = Unit.INSTANCE;
            }
            AccessibilityNodeInfo accessibilityNodeInfoUnwrap3 = info.unwrap();
            Intrinsics.checkNotNullExpressionValue(accessibilityNodeInfoUnwrap3, "info.unwrap()");
            addExtraDataToAccessibilityNodeInfoHelper(virtualViewId, accessibilityNodeInfoUnwrap3, this.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL, null);
        }
    }

    private static final boolean populateAccessibilityNodeInfoProperties$canScrollForward(ScrollAxisRange scrollAxisRange) {
        return (scrollAxisRange.getValue().invoke().floatValue() < scrollAxisRange.getMaxValue().invoke().floatValue() && !scrollAxisRange.getReverseScrolling()) || (scrollAxisRange.getValue().invoke().floatValue() > 0.0f && scrollAxisRange.getReverseScrolling());
    }

    private static final boolean populateAccessibilityNodeInfoProperties$canScrollBackward(ScrollAxisRange scrollAxisRange) {
        return (scrollAxisRange.getValue().invoke().floatValue() > 0.0f && !scrollAxisRange.getReverseScrolling()) || (scrollAxisRange.getValue().invoke().floatValue() < scrollAxisRange.getMaxValue().invoke().floatValue() && scrollAxisRange.getReverseScrolling());
    }

    private final void setContentInvalid(SemanticsNode node, AccessibilityNodeInfoCompat info) {
        if (node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getError())) {
            info.setContentInvalid(true);
            info.setError((CharSequence) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getError()));
        }
    }

    private final void setText(SemanticsNode node, AccessibilityNodeInfoCompat info) {
        AnnotatedString annotatedString;
        FontFamily.Resolver fontFamilyResolver = this.view.getFontFamilyResolver();
        AnnotatedString textForTextField = getTextForTextField(node.getUnmergedConfig());
        SpannableString accessibilitySpannableString = null;
        SpannableString spannableString = (SpannableString) trimToSize(textForTextField != null ? AndroidAccessibilitySpannableString_androidKt.toAccessibilitySpannableString(textForTextField, this.view.getDensity(), fontFamilyResolver) : null, 100000);
        List list = (List) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getText());
        if (list != null && (annotatedString = (AnnotatedString) CollectionsKt.firstOrNull(list)) != null) {
            accessibilitySpannableString = AndroidAccessibilitySpannableString_androidKt.toAccessibilitySpannableString(annotatedString, this.view.getDensity(), fontFamilyResolver);
        }
        info.setText(spannableString != null ? spannableString : (SpannableString) trimToSize(accessibilitySpannableString, 100000));
    }

    private final boolean requestAccessibilityFocus(int virtualViewId) {
        if (!isTouchExplorationEnabled() || isAccessibilityFocused(virtualViewId)) {
            return false;
        }
        int i = this.focusedVirtualViewId;
        if (i != Integer.MIN_VALUE) {
            sendEventForVirtualView$default(this, i, 65536, null, null, 12, null);
        }
        this.focusedVirtualViewId = virtualViewId;
        this.view.invalidate();
        sendEventForVirtualView$default(this, virtualViewId, 32768, null, null, 12, null);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ boolean sendEventForVirtualView$default(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat, int i, int i2, Integer num, List list, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            num = null;
        }
        if ((i3 & 8) != 0) {
            list = null;
        }
        return androidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView(i, i2, num, list);
    }

    private final boolean sendEventForVirtualView(int virtualViewId, int eventType, Integer contentChangeType, List<String> contentDescription) {
        if (virtualViewId == Integer.MIN_VALUE || !isEnabled$ui_release()) {
            return false;
        }
        AccessibilityEvent accessibilityEventCreateEvent$ui_release = createEvent$ui_release(virtualViewId, eventType);
        if (contentChangeType != null) {
            accessibilityEventCreateEvent$ui_release.setContentChangeTypes(contentChangeType.intValue());
        }
        if (contentDescription != null) {
            accessibilityEventCreateEvent$ui_release.setContentDescription(TempListUtilsKt.fastJoinToString$default(contentDescription, Constants.SEPARATOR_COMMA, null, null, 0, null, null, 62, null));
        }
        return sendEvent(accessibilityEventCreateEvent$ui_release);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean sendEvent(AccessibilityEvent event) {
        if (isEnabled$ui_release()) {
            return this.view.getParent().requestSendAccessibilityEvent(this.view, event);
        }
        return false;
    }

    public final AccessibilityEvent createEvent$ui_release(int virtualViewId, int eventType) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(eventType);
        Intrinsics.checkNotNullExpressionValue(accessibilityEventObtain, "obtain(eventType)");
        accessibilityEventObtain.setEnabled(true);
        accessibilityEventObtain.setClassName(ClassName);
        accessibilityEventObtain.setPackageName(this.view.getContext().getPackageName());
        accessibilityEventObtain.setSource(this.view, virtualViewId);
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes().get(Integer.valueOf(virtualViewId));
        if (semanticsNodeWithAdjustedBounds != null) {
            accessibilityEventObtain.setPassword(AndroidComposeViewAccessibilityDelegateCompat_androidKt.isPassword(semanticsNodeWithAdjustedBounds.getSemanticsNode()));
        }
        return accessibilityEventObtain;
    }

    private final AccessibilityEvent createTextSelectionChangedEvent(int virtualViewId, Integer fromIndex, Integer toIndex, Integer itemCount, CharSequence text) {
        AccessibilityEvent accessibilityEventCreateEvent$ui_release = createEvent$ui_release(virtualViewId, 8192);
        if (fromIndex != null) {
            accessibilityEventCreateEvent$ui_release.setFromIndex(fromIndex.intValue());
        }
        if (toIndex != null) {
            accessibilityEventCreateEvent$ui_release.setToIndex(toIndex.intValue());
        }
        if (itemCount != null) {
            accessibilityEventCreateEvent$ui_release.setItemCount(itemCount.intValue());
        }
        if (text != null) {
            accessibilityEventCreateEvent$ui_release.getText().add(text);
        }
        return accessibilityEventCreateEvent$ui_release;
    }

    private final boolean clearAccessibilityFocus(int virtualViewId) {
        if (!isAccessibilityFocused(virtualViewId)) {
            return false;
        }
        this.focusedVirtualViewId = Integer.MIN_VALUE;
        this.view.invalidate();
        sendEventForVirtualView$default(this, virtualViewId, 65536, null, null, 12, null);
        return true;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:84:0x017e
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:225)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:195)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:62)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:95)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:124)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.processFallThroughCases(SwitchRegionMaker.java:105)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:64)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:95)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:95)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:101)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:95)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:95)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:101)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:95)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:95)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:101)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v38 */
    /* JADX WARN: Type inference failed for: r13v39 */
    /* JADX WARN: Type inference failed for: r13v62 */
    /* JADX WARN: Type inference failed for: r14v21 */
    /* JADX WARN: Type inference failed for: r14v22 */
    /* JADX WARN: Type inference failed for: r14v23 */
    /* JADX WARN: Type inference failed for: r14v24 */
    /* JADX WARN: Type inference failed for: r14v43 */
    /* JADX WARN: Type inference failed for: r14v44 */
    /* JADX WARN: Type inference failed for: r15v13 */
    /* JADX WARN: Type inference failed for: r15v5 */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:83:0x017d -> B:84:0x017e). Please report as a decompilation issue!!! */
    public final boolean performActionHelper(int r13, int r14, android.os.Bundle r15) {
        /*
            Method dump skipped, instructions count: 1606
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.performActionHelper(int, int, android.os.Bundle):boolean");
    }

    private static final boolean performActionHelper$canScroll(ScrollAxisRange scrollAxisRange, float f) {
        return (f < 0.0f && scrollAxisRange.getValue().invoke().floatValue() > 0.0f) || (f > 0.0f && scrollAxisRange.getValue().invoke().floatValue() < scrollAxisRange.getMaxValue().invoke().floatValue());
    }

    private static final float performActionHelper$scrollDelta(float f, float f2) {
        if (Math.signum(f) == Math.signum(f2)) {
            return Math.abs(f) < Math.abs(f2) ? f : f2;
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addExtraDataToAccessibilityNodeInfoHelper(int virtualViewId, AccessibilityNodeInfo info, String extraDataKey, Bundle arguments) {
        SemanticsNode semanticsNode;
        String str;
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes().get(Integer.valueOf(virtualViewId));
        if (semanticsNodeWithAdjustedBounds == null || (semanticsNode = semanticsNodeWithAdjustedBounds.getSemanticsNode()) == null) {
            return;
        }
        String iterableTextForAccessibility = getIterableTextForAccessibility(semanticsNode);
        if (Intrinsics.areEqual(extraDataKey, this.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL)) {
            Integer num = this.idToBeforeMap.get(Integer.valueOf(virtualViewId));
            if (num != null) {
                info.getExtras().putInt(extraDataKey, num.intValue());
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(extraDataKey, this.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL)) {
            Integer num2 = this.idToAfterMap.get(Integer.valueOf(virtualViewId));
            if (num2 != null) {
                info.getExtras().putInt(extraDataKey, num2.intValue());
                return;
            }
            return;
        }
        if (semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getGetTextLayoutResult()) && arguments != null && Intrinsics.areEqual(extraDataKey, "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY")) {
            int i = arguments.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX", -1);
            int i2 = arguments.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH", -1);
            if (i2 > 0 && i >= 0) {
                if (i < (iterableTextForAccessibility != null ? iterableTextForAccessibility.length() : Integer.MAX_VALUE)) {
                    ArrayList arrayList = new ArrayList();
                    Function1 function1 = (Function1) ((AccessibilityAction) semanticsNode.getUnmergedConfig().get(SemanticsActions.INSTANCE.getGetTextLayoutResult())).getAction();
                    if (Intrinsics.areEqual((Object) (function1 != null ? (Boolean) function1.invoke(arrayList) : null), (Object) true)) {
                        TextLayoutResult textLayoutResult = (TextLayoutResult) arrayList.get(0);
                        ArrayList arrayList2 = new ArrayList();
                        for (int i3 = 0; i3 < i2; i3++) {
                            int i4 = i + i3;
                            if (i4 >= textLayoutResult.getLayoutInput().getText().length()) {
                                arrayList2.add(null);
                            } else {
                                arrayList2.add(toScreenCoords(semanticsNode, textLayoutResult.getBoundingBox(i4)));
                            }
                        }
                        info.getExtras().putParcelableArray(extraDataKey, (Parcelable[]) arrayList2.toArray(new RectF[0]));
                        return;
                    }
                    return;
                }
            }
            Log.e(LogTag, "Invalid arguments for accessibility character locations");
            return;
        }
        if (!semanticsNode.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getTestTag()) || arguments == null || !Intrinsics.areEqual(extraDataKey, ExtraDataTestTagKey) || (str = (String) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getTestTag())) == null) {
            return;
        }
        info.getExtras().putCharSequence(extraDataKey, str);
    }

    private final RectF toScreenCoords(SemanticsNode textNode, androidx.compose.ui.geometry.Rect bounds) {
        if (textNode == null) {
            return null;
        }
        androidx.compose.ui.geometry.Rect rectM1676translatek4lQ0M = bounds.m1676translatek4lQ0M(textNode.m3760getPositionInRootF1C5BW0());
        androidx.compose.ui.geometry.Rect boundsInRoot = textNode.getBoundsInRoot();
        androidx.compose.ui.geometry.Rect rectIntersect = rectM1676translatek4lQ0M.overlaps(boundsInRoot) ? rectM1676translatek4lQ0M.intersect(boundsInRoot) : null;
        if (rectIntersect == null) {
            return null;
        }
        long jMo3358localToScreenMKHz9U = this.view.mo3358localToScreenMKHz9U(OffsetKt.Offset(rectIntersect.getLeft(), rectIntersect.getTop()));
        long jMo3358localToScreenMKHz9U2 = this.view.mo3358localToScreenMKHz9U(OffsetKt.Offset(rectIntersect.getRight(), rectIntersect.getBottom()));
        return new RectF(Offset.m1639getXimpl(jMo3358localToScreenMKHz9U), Offset.m1640getYimpl(jMo3358localToScreenMKHz9U), Offset.m1639getXimpl(jMo3358localToScreenMKHz9U2), Offset.m1640getYimpl(jMo3358localToScreenMKHz9U2));
    }

    public final boolean dispatchHoverEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (!isTouchExplorationEnabled()) {
            return false;
        }
        int action = event.getAction();
        if (action == 7 || action == 9) {
            int iHitTestSemanticsAt$ui_release = hitTestSemanticsAt$ui_release(event.getX(), event.getY());
            boolean zDispatchGenericMotionEvent = this.view.getAndroidViewsHandler$ui_release().dispatchGenericMotionEvent(event);
            updateHoveredVirtualView(iHitTestSemanticsAt$ui_release);
            if (iHitTestSemanticsAt$ui_release == Integer.MIN_VALUE) {
                return zDispatchGenericMotionEvent;
            }
            return true;
        }
        if (action != 10) {
            return false;
        }
        if (this.hoveredVirtualViewId != Integer.MIN_VALUE) {
            updateHoveredVirtualView(Integer.MIN_VALUE);
            return true;
        }
        return this.view.getAndroidViewsHandler$ui_release().dispatchGenericMotionEvent(event);
    }

    public final int hitTestSemanticsAt$ui_release(float x, float y) {
        LayoutNode layoutNodeRequireLayoutNode;
        SemanticsModifierNode outerSemantics = null;
        Owner.measureAndLayout$default(this.view, false, 1, null);
        HitTestResult hitTestResult = new HitTestResult();
        this.view.getRoot().m3526hitTestSemanticsM_7yMNQ$ui_release(OffsetKt.Offset(x, y), hitTestResult, (12 & 4) != 0, (12 & 8) != 0);
        SemanticsModifierNode semanticsModifierNode = (SemanticsModifierNode) CollectionsKt.lastOrNull((List) hitTestResult);
        if (semanticsModifierNode != null && (layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(semanticsModifierNode)) != null) {
            outerSemantics = SemanticsNodeKt.getOuterSemantics(layoutNodeRequireLayoutNode);
        }
        if (outerSemantics != null) {
            SemanticsNode semanticsNode = new SemanticsNode(outerSemantics, false, null, 4, null);
            NodeCoordinator nodeCoordinatorFindCoordinatorToGetBounds$ui_release = semanticsNode.findCoordinatorToGetBounds$ui_release();
            if (!semanticsNode.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getInvisibleToUser()) && !nodeCoordinatorFindCoordinatorToGetBounds$ui_release.isTransparent()) {
                LayoutNode layoutNodeRequireLayoutNode2 = DelegatableNodeKt.requireLayoutNode(outerSemantics);
                if (this.view.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().get(layoutNodeRequireLayoutNode2) == null) {
                    return semanticsNodeIdToAccessibilityVirtualNodeId(layoutNodeRequireLayoutNode2.getSemanticsId());
                }
            }
        }
        return Integer.MIN_VALUE;
    }

    private final void updateHoveredVirtualView(int virtualViewId) {
        int i = this.hoveredVirtualViewId;
        if (i == virtualViewId) {
            return;
        }
        this.hoveredVirtualViewId = virtualViewId;
        sendEventForVirtualView$default(this, virtualViewId, 128, null, null, 12, null);
        sendEventForVirtualView$default(this, i, 256, null, null, 12, null);
    }

    private final <T extends CharSequence> T trimToSize(T text, int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (text == null || text.length() == 0 || text.length() <= size) {
            return text;
        }
        int i = size - 1;
        if (Character.isHighSurrogate(text.charAt(i)) && Character.isLowSurrogate(text.charAt(size))) {
            size = i;
        }
        T t = (T) text.subSequence(0, size);
        Intrinsics.checkNotNull(t, "null cannot be cast to non-null type T of androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.trimToSize");
        return t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void semanticsChangeChecker$lambda$38(AndroidComposeViewAccessibilityDelegateCompat this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Owner.measureAndLayout$default(this$0.view, false, 1, null);
        this$0.checkForSemanticsChanges();
        this$0.checkingForSemanticsChanges = false;
    }

    public final void onSemanticsChange$ui_release() {
        this.currentSemanticsNodesInvalidated = true;
        if (!isEnabled$ui_release() || this.checkingForSemanticsChanges) {
            return;
        }
        this.checkingForSemanticsChanges = true;
        this.handler.post(this.semanticsChangeChecker);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0072 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007e A[Catch: all -> 0x0052, TryCatch #0 {all -> 0x0052, blocks: (B:13:0x0035, B:25:0x0064, B:29:0x0076, B:31:0x007e, B:33:0x0087, B:35:0x0090, B:36:0x00a1, B:38:0x00a8, B:39:0x00b1, B:18:0x004e), top: B:48:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x00c4 -> B:14:0x0038). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object boundsUpdatesEventLoop(kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            Method dump skipped, instructions count: 215
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.boundsUpdatesEventLoop(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void onLayoutChange$ui_release(LayoutNode layoutNode) {
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        this.currentSemanticsNodesInvalidated = true;
        if (isEnabled$ui_release()) {
            notifySubtreeAccessibilityStateChangedIfNeeded(layoutNode);
        }
    }

    private final void notifySubtreeAccessibilityStateChangedIfNeeded(LayoutNode layoutNode) {
        if (this.subtreeChangedLayoutNodes.add(layoutNode)) {
            this.boundsUpdateChannel.mo7599trySendJP2dKIU(Unit.INSTANCE);
        }
    }

    private final void sendSubtreeChangeAccessibilityEvents(LayoutNode layoutNode, ArraySet<Integer> subtreeChangedSemanticsNodesIds) {
        LayoutNode layoutNodeFindClosestParentNode;
        SemanticsModifierNode outerSemantics;
        if (layoutNode.isAttached() && !this.view.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().containsKey(layoutNode)) {
            SemanticsModifierNode outerSemantics2 = SemanticsNodeKt.getOuterSemantics(layoutNode);
            if (outerSemantics2 == null) {
                LayoutNode layoutNodeFindClosestParentNode2 = AndroidComposeViewAccessibilityDelegateCompat_androidKt.findClosestParentNode(layoutNode, new Function1<LayoutNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$semanticsWrapper$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(LayoutNode it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return Boolean.valueOf(SemanticsNodeKt.getOuterSemantics(it) != null);
                    }
                });
                outerSemantics2 = layoutNodeFindClosestParentNode2 != null ? SemanticsNodeKt.getOuterSemantics(layoutNodeFindClosestParentNode2) : null;
                if (outerSemantics2 == null) {
                    return;
                }
            }
            if (!SemanticsModifierNodeKt.collapsedSemanticsConfiguration(outerSemantics2).getIsMergingSemanticsOfDescendants() && (layoutNodeFindClosestParentNode = AndroidComposeViewAccessibilityDelegateCompat_androidKt.findClosestParentNode(layoutNode, new Function1<LayoutNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendSubtreeChangeAccessibilityEvents.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(LayoutNode it) {
                    SemanticsConfiguration semanticsConfigurationCollapsedSemanticsConfiguration;
                    Intrinsics.checkNotNullParameter(it, "it");
                    SemanticsModifierNode outerSemantics3 = SemanticsNodeKt.getOuterSemantics(it);
                    boolean z = false;
                    if (outerSemantics3 != null && (semanticsConfigurationCollapsedSemanticsConfiguration = SemanticsModifierNodeKt.collapsedSemanticsConfiguration(outerSemantics3)) != null && semanticsConfigurationCollapsedSemanticsConfiguration.getIsMergingSemanticsOfDescendants()) {
                        z = true;
                    }
                    return Boolean.valueOf(z);
                }
            })) != null && (outerSemantics = SemanticsNodeKt.getOuterSemantics(layoutNodeFindClosestParentNode)) != null) {
                outerSemantics2 = outerSemantics;
            }
            int semanticsId = DelegatableNodeKt.requireLayoutNode(outerSemantics2).getSemanticsId();
            if (subtreeChangedSemanticsNodesIds.add(Integer.valueOf(semanticsId))) {
                sendEventForVirtualView$default(this, semanticsNodeIdToAccessibilityVirtualNodeId(semanticsId), 2048, 1, null, 8, null);
            }
        }
    }

    private final void checkForSemanticsChanges() {
        sendSemanticsStructureChangeEvents(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), this.previousSemanticsRoot);
        sendSemanticsPropertyChangeEvents$ui_release(getCurrentSemanticsNodes());
        updateSemanticsNodesCopyAndPanes();
    }

    private final void updateSemanticsNodesCopyAndPanes() {
        SemanticsConfiguration unmergedConfig;
        ArraySet<? extends Integer> arraySet = new ArraySet<>();
        Iterator<Integer> it = this.paneDisplayed.iterator();
        while (it.hasNext()) {
            Integer id = it.next();
            SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes().get(id);
            String str = null;
            SemanticsNode semanticsNode = semanticsNodeWithAdjustedBounds != null ? semanticsNodeWithAdjustedBounds.getSemanticsNode() : null;
            if (semanticsNode == null || !AndroidComposeViewAccessibilityDelegateCompat_androidKt.hasPaneTitle(semanticsNode)) {
                arraySet.add(id);
                Intrinsics.checkNotNullExpressionValue(id, "id");
                int iIntValue = id.intValue();
                SemanticsNodeCopy semanticsNodeCopy = this.previousSemanticsNodes.get(id);
                if (semanticsNodeCopy != null && (unmergedConfig = semanticsNodeCopy.getUnmergedConfig()) != null) {
                    str = (String) SemanticsConfigurationKt.getOrNull(unmergedConfig, SemanticsProperties.INSTANCE.getPaneTitle());
                }
                sendPaneChangeEvents(iIntValue, 32, str);
            }
        }
        this.paneDisplayed.removeAll(arraySet);
        this.previousSemanticsNodes.clear();
        for (Map.Entry<Integer, SemanticsNodeWithAdjustedBounds> entry : getCurrentSemanticsNodes().entrySet()) {
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.hasPaneTitle(entry.getValue().getSemanticsNode()) && this.paneDisplayed.add(entry.getKey())) {
                sendPaneChangeEvents(entry.getKey().intValue(), 16, (String) entry.getValue().getSemanticsNode().getUnmergedConfig().get(SemanticsProperties.INSTANCE.getPaneTitle()));
            }
            this.previousSemanticsNodes.put(entry.getKey(), new SemanticsNodeCopy(entry.getValue().getSemanticsNode(), getCurrentSemanticsNodes()));
        }
        this.previousSemanticsRoot = new SemanticsNodeCopy(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), getCurrentSemanticsNodes());
    }

    /* JADX WARN: Removed duplicated region for block: B:160:0x0571  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void sendSemanticsPropertyChangeEvents$ui_release(java.util.Map<java.lang.Integer, androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds> r29) {
        /*
            Method dump skipped, instructions count: 1428
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendSemanticsPropertyChangeEvents$ui_release(java.util.Map):void");
    }

    private final boolean registerScrollingId(int id, List<ScrollObservationScope> oldScrollObservationScopes) {
        boolean z;
        ScrollObservationScope scrollObservationScopeFindById = AndroidComposeViewAccessibilityDelegateCompat_androidKt.findById(oldScrollObservationScopes, id);
        if (scrollObservationScopeFindById != null) {
            z = false;
        } else {
            scrollObservationScopeFindById = new ScrollObservationScope(id, this.scrollObservationScopes, null, null, null, null);
            z = true;
        }
        this.scrollObservationScopes.add(scrollObservationScopeFindById);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendScrollEventIfNeeded(final ScrollObservationScope scrollObservationScope) {
        if (scrollObservationScope.isValidOwnerScope()) {
            this.view.getSnapshotObserver().observeReads$ui_release(scrollObservationScope, this.sendScrollEventIfNeededLambda, new Function0<Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendScrollEventIfNeeded.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    ScrollAxisRange horizontalScrollAxisRange = scrollObservationScope.getHorizontalScrollAxisRange();
                    ScrollAxisRange verticalScrollAxisRange = scrollObservationScope.getVerticalScrollAxisRange();
                    Float oldXValue = scrollObservationScope.getOldXValue();
                    Float oldYValue = scrollObservationScope.getOldYValue();
                    float fFloatValue = (horizontalScrollAxisRange == null || oldXValue == null) ? 0.0f : horizontalScrollAxisRange.getValue().invoke().floatValue() - oldXValue.floatValue();
                    float fFloatValue2 = (verticalScrollAxisRange == null || oldYValue == null) ? 0.0f : verticalScrollAxisRange.getValue().invoke().floatValue() - oldYValue.floatValue();
                    if (fFloatValue != 0.0f || fFloatValue2 != 0.0f) {
                        int iSemanticsNodeIdToAccessibilityVirtualNodeId = this.semanticsNodeIdToAccessibilityVirtualNodeId(scrollObservationScope.getSemanticsNodeId());
                        AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(this, iSemanticsNodeIdToAccessibilityVirtualNodeId, 2048, 1, null, 8, null);
                        AccessibilityEvent accessibilityEventCreateEvent$ui_release = this.createEvent$ui_release(iSemanticsNodeIdToAccessibilityVirtualNodeId, 4096);
                        if (horizontalScrollAxisRange != null) {
                            accessibilityEventCreateEvent$ui_release.setScrollX((int) horizontalScrollAxisRange.getValue().invoke().floatValue());
                            accessibilityEventCreateEvent$ui_release.setMaxScrollX((int) horizontalScrollAxisRange.getMaxValue().invoke().floatValue());
                        }
                        if (verticalScrollAxisRange != null) {
                            accessibilityEventCreateEvent$ui_release.setScrollY((int) verticalScrollAxisRange.getValue().invoke().floatValue());
                            accessibilityEventCreateEvent$ui_release.setMaxScrollY((int) verticalScrollAxisRange.getMaxValue().invoke().floatValue());
                        }
                        if (Build.VERSION.SDK_INT >= 28) {
                            Api28Impl.setScrollEventDelta(accessibilityEventCreateEvent$ui_release, (int) fFloatValue, (int) fFloatValue2);
                        }
                        this.sendEvent(accessibilityEventCreateEvent$ui_release);
                    }
                    if (horizontalScrollAxisRange != null) {
                        scrollObservationScope.setOldXValue(horizontalScrollAxisRange.getValue().invoke());
                    }
                    if (verticalScrollAxisRange != null) {
                        scrollObservationScope.setOldYValue(verticalScrollAxisRange.getValue().invoke());
                    }
                }
            });
        }
    }

    private final void sendPaneChangeEvents(int semanticsNodeId, int contentChangeType, String title) {
        AccessibilityEvent accessibilityEventCreateEvent$ui_release = createEvent$ui_release(semanticsNodeIdToAccessibilityVirtualNodeId(semanticsNodeId), 32);
        accessibilityEventCreateEvent$ui_release.setContentChangeTypes(contentChangeType);
        if (title != null) {
            accessibilityEventCreateEvent$ui_release.getText().add(title);
        }
        sendEvent(accessibilityEventCreateEvent$ui_release);
    }

    private final void sendSemanticsStructureChangeEvents(SemanticsNode newNode, SemanticsNodeCopy oldNode) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        List<SemanticsNode> replacedChildren$ui_release = newNode.getReplacedChildren$ui_release();
        int size = replacedChildren$ui_release.size();
        for (int i = 0; i < size; i++) {
            SemanticsNode semanticsNode = replacedChildren$ui_release.get(i);
            if (getCurrentSemanticsNodes().containsKey(Integer.valueOf(semanticsNode.getId()))) {
                if (!oldNode.getChildren().contains(Integer.valueOf(semanticsNode.getId()))) {
                    notifySubtreeAccessibilityStateChangedIfNeeded(newNode.getLayoutNode());
                    return;
                }
                linkedHashSet.add(Integer.valueOf(semanticsNode.getId()));
            }
        }
        Iterator<Integer> it = oldNode.getChildren().iterator();
        while (it.hasNext()) {
            if (!linkedHashSet.contains(Integer.valueOf(it.next().intValue()))) {
                notifySubtreeAccessibilityStateChangedIfNeeded(newNode.getLayoutNode());
                return;
            }
        }
        List<SemanticsNode> replacedChildren$ui_release2 = newNode.getReplacedChildren$ui_release();
        int size2 = replacedChildren$ui_release2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            SemanticsNode semanticsNode2 = replacedChildren$ui_release2.get(i2);
            if (getCurrentSemanticsNodes().containsKey(Integer.valueOf(semanticsNode2.getId()))) {
                SemanticsNodeCopy semanticsNodeCopy = this.previousSemanticsNodes.get(Integer.valueOf(semanticsNode2.getId()));
                Intrinsics.checkNotNull(semanticsNodeCopy);
                sendSemanticsStructureChangeEvents(semanticsNode2, semanticsNodeCopy);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int semanticsNodeIdToAccessibilityVirtualNodeId(int id) {
        if (id == this.view.getSemanticsOwner().getUnmergedRootSemanticsNode().getId()) {
            return -1;
        }
        return id;
    }

    private final boolean traverseAtGranularity(SemanticsNode node, int granularity, boolean forward, boolean extendSelection) {
        AccessibilityIterators.TextSegmentIterator iteratorForGranularity;
        int accessibilitySelectionStart;
        int i;
        int id = node.getId();
        Integer num = this.previousTraversedNode;
        if (num == null || id != num.intValue()) {
            this.accessibilityCursorPosition = -1;
            this.previousTraversedNode = Integer.valueOf(node.getId());
        }
        String iterableTextForAccessibility = getIterableTextForAccessibility(node);
        String str = iterableTextForAccessibility;
        if (str == null || str.length() == 0 || (iteratorForGranularity = getIteratorForGranularity(node, granularity)) == null) {
            return false;
        }
        int accessibilitySelectionEnd = getAccessibilitySelectionEnd(node);
        if (accessibilitySelectionEnd == -1) {
            accessibilitySelectionEnd = forward ? 0 : iterableTextForAccessibility.length();
        }
        int[] iArrFollowing = forward ? iteratorForGranularity.following(accessibilitySelectionEnd) : iteratorForGranularity.preceding(accessibilitySelectionEnd);
        if (iArrFollowing == null) {
            return false;
        }
        int i2 = iArrFollowing[0];
        int i3 = iArrFollowing[1];
        if (extendSelection && isAccessibilitySelectionExtendable(node)) {
            accessibilitySelectionStart = getAccessibilitySelectionStart(node);
            if (accessibilitySelectionStart == -1) {
                accessibilitySelectionStart = forward ? i2 : i3;
            }
            i = forward ? i3 : i2;
        } else {
            accessibilitySelectionStart = forward ? i3 : i2;
            i = accessibilitySelectionStart;
        }
        this.pendingTextTraversedEvent = new PendingTextTraversedEvent(node, forward ? 256 : 512, granularity, i2, i3, SystemClock.uptimeMillis());
        setAccessibilitySelection(node, accessibilitySelectionStart, i, true);
        return true;
    }

    private final void sendPendingTextTraversedAtGranularityEvent(int semanticsNodeId) {
        PendingTextTraversedEvent pendingTextTraversedEvent = this.pendingTextTraversedEvent;
        if (pendingTextTraversedEvent != null) {
            if (semanticsNodeId != pendingTextTraversedEvent.getNode().getId()) {
                return;
            }
            if (SystemClock.uptimeMillis() - pendingTextTraversedEvent.getTraverseTime() <= 1000) {
                AccessibilityEvent accessibilityEventCreateEvent$ui_release = createEvent$ui_release(semanticsNodeIdToAccessibilityVirtualNodeId(pendingTextTraversedEvent.getNode().getId()), 131072);
                accessibilityEventCreateEvent$ui_release.setFromIndex(pendingTextTraversedEvent.getFromIndex());
                accessibilityEventCreateEvent$ui_release.setToIndex(pendingTextTraversedEvent.getToIndex());
                accessibilityEventCreateEvent$ui_release.setAction(pendingTextTraversedEvent.getAction());
                accessibilityEventCreateEvent$ui_release.setMovementGranularity(pendingTextTraversedEvent.getGranularity());
                accessibilityEventCreateEvent$ui_release.getText().add(getIterableTextForAccessibility(pendingTextTraversedEvent.getNode()));
                sendEvent(accessibilityEventCreateEvent$ui_release);
            }
        }
        this.pendingTextTraversedEvent = null;
    }

    private final boolean setAccessibilitySelection(SemanticsNode node, int start, int end, boolean traversalMode) {
        String iterableTextForAccessibility;
        if (node.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getSetSelection()) && AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(node)) {
            Function3 function3 = (Function3) ((AccessibilityAction) node.getUnmergedConfig().get(SemanticsActions.INSTANCE.getSetSelection())).getAction();
            if (function3 != null) {
                return ((Boolean) function3.invoke(Integer.valueOf(start), Integer.valueOf(end), Boolean.valueOf(traversalMode))).booleanValue();
            }
            return false;
        }
        if ((start == end && end == this.accessibilityCursorPosition) || (iterableTextForAccessibility = getIterableTextForAccessibility(node)) == null) {
            return false;
        }
        if (start < 0 || start != end || end > iterableTextForAccessibility.length()) {
            start = -1;
        }
        this.accessibilityCursorPosition = start;
        String str = iterableTextForAccessibility;
        boolean z = str.length() > 0;
        sendEvent(createTextSelectionChangedEvent(semanticsNodeIdToAccessibilityVirtualNodeId(node.getId()), z ? Integer.valueOf(this.accessibilityCursorPosition) : null, z ? Integer.valueOf(this.accessibilityCursorPosition) : null, z ? Integer.valueOf(iterableTextForAccessibility.length()) : null, str));
        sendPendingTextTraversedAtGranularityEvent(node.getId());
        return true;
    }

    private final int getAccessibilitySelectionStart(SemanticsNode node) {
        return (node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getContentDescription()) || !node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getTextSelectionRange())) ? this.accessibilityCursorPosition : TextRange.m3905getStartimpl(((TextRange) node.getUnmergedConfig().get(SemanticsProperties.INSTANCE.getTextSelectionRange())).getPackedValue());
    }

    private final int getAccessibilitySelectionEnd(SemanticsNode node) {
        return (node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getContentDescription()) || !node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getTextSelectionRange())) ? this.accessibilityCursorPosition : TextRange.m3900getEndimpl(((TextRange) node.getUnmergedConfig().get(SemanticsProperties.INSTANCE.getTextSelectionRange())).getPackedValue());
    }

    private final boolean isAccessibilitySelectionExtendable(SemanticsNode node) {
        return !node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getContentDescription()) && node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getEditableText());
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0047 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final androidx.compose.ui.platform.AccessibilityIterators.TextSegmentIterator getIteratorForGranularity(androidx.compose.ui.semantics.SemanticsNode r8, int r9) {
        /*
            r7 = this;
            r0 = 0
            if (r8 != 0) goto L4
            return r0
        L4:
            java.lang.String r1 = r7.getIterableTextForAccessibility(r8)
            r2 = r1
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            if (r2 == 0) goto Le2
            int r2 = r2.length()
            if (r2 != 0) goto L15
            goto Le2
        L15:
            java.lang.String r2 = "view.context.resources.configuration.locale"
            r3 = 1
            if (r9 == r3) goto Lc1
            r4 = 2
            if (r9 == r4) goto La2
            r2 = 4
            if (r9 == r2) goto L37
            r4 = 8
            if (r9 == r4) goto L2a
            r4 = 16
            if (r9 == r4) goto L37
            return r0
        L2a:
            androidx.compose.ui.platform.AccessibilityIterators$ParagraphTextSegmentIterator$Companion r8 = androidx.compose.ui.platform.AccessibilityIterators.ParagraphTextSegmentIterator.INSTANCE
            androidx.compose.ui.platform.AccessibilityIterators$ParagraphTextSegmentIterator r8 = r8.getInstance()
            androidx.compose.ui.platform.AccessibilityIterators$AbstractTextSegmentIterator r8 = (androidx.compose.ui.platform.AccessibilityIterators.AbstractTextSegmentIterator) r8
            r8.initialize(r1)
            goto Ldf
        L37:
            androidx.compose.ui.semantics.SemanticsConfiguration r4 = r8.getUnmergedConfig()
            androidx.compose.ui.semantics.SemanticsActions r5 = androidx.compose.ui.semantics.SemanticsActions.INSTANCE
            androidx.compose.ui.semantics.SemanticsPropertyKey r5 = r5.getGetTextLayoutResult()
            boolean r4 = r4.contains(r5)
            if (r4 != 0) goto L48
            return r0
        L48:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.List r4 = (java.util.List) r4
            androidx.compose.ui.semantics.SemanticsConfiguration r5 = r8.getUnmergedConfig()
            androidx.compose.ui.semantics.SemanticsActions r6 = androidx.compose.ui.semantics.SemanticsActions.INSTANCE
            androidx.compose.ui.semantics.SemanticsPropertyKey r6 = r6.getGetTextLayoutResult()
            java.lang.Object r5 = r5.get(r6)
            androidx.compose.ui.semantics.AccessibilityAction r5 = (androidx.compose.ui.semantics.AccessibilityAction) r5
            kotlin.Function r5 = r5.getAction()
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            if (r5 == 0) goto L6e
            java.lang.Object r5 = r5.invoke(r4)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            goto L6f
        L6e:
            r5 = r0
        L6f:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r3)
            if (r3 == 0) goto La1
            r0 = 0
            java.lang.Object r0 = r4.get(r0)
            androidx.compose.ui.text.TextLayoutResult r0 = (androidx.compose.ui.text.TextLayoutResult) r0
            if (r9 != r2) goto L91
            androidx.compose.ui.platform.AccessibilityIterators$LineTextSegmentIterator$Companion r8 = androidx.compose.ui.platform.AccessibilityIterators.LineTextSegmentIterator.INSTANCE
            androidx.compose.ui.platform.AccessibilityIterators$LineTextSegmentIterator r8 = r8.getInstance()
            androidx.compose.ui.platform.AccessibilityIterators$AbstractTextSegmentIterator r8 = (androidx.compose.ui.platform.AccessibilityIterators.AbstractTextSegmentIterator) r8
            r9 = r8
            androidx.compose.ui.platform.AccessibilityIterators$LineTextSegmentIterator r9 = (androidx.compose.ui.platform.AccessibilityIterators.LineTextSegmentIterator) r9
            r9.initialize(r1, r0)
            goto Ldf
        L91:
            androidx.compose.ui.platform.AccessibilityIterators$PageTextSegmentIterator$Companion r9 = androidx.compose.ui.platform.AccessibilityIterators.PageTextSegmentIterator.INSTANCE
            androidx.compose.ui.platform.AccessibilityIterators$PageTextSegmentIterator r9 = r9.getInstance()
            androidx.compose.ui.platform.AccessibilityIterators$AbstractTextSegmentIterator r9 = (androidx.compose.ui.platform.AccessibilityIterators.AbstractTextSegmentIterator) r9
            r2 = r9
            androidx.compose.ui.platform.AccessibilityIterators$PageTextSegmentIterator r2 = (androidx.compose.ui.platform.AccessibilityIterators.PageTextSegmentIterator) r2
            r2.initialize(r1, r0, r8)
            r8 = r9
            goto Ldf
        La1:
            return r0
        La2:
            androidx.compose.ui.platform.AccessibilityIterators$WordTextSegmentIterator$Companion r8 = androidx.compose.ui.platform.AccessibilityIterators.WordTextSegmentIterator.INSTANCE
            androidx.compose.ui.platform.AndroidComposeView r9 = r7.view
            android.content.Context r9 = r9.getContext()
            android.content.res.Resources r9 = r9.getResources()
            android.content.res.Configuration r9 = r9.getConfiguration()
            java.util.Locale r9 = r9.locale
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r2)
            androidx.compose.ui.platform.AccessibilityIterators$WordTextSegmentIterator r8 = r8.getInstance(r9)
            androidx.compose.ui.platform.AccessibilityIterators$AbstractTextSegmentIterator r8 = (androidx.compose.ui.platform.AccessibilityIterators.AbstractTextSegmentIterator) r8
            r8.initialize(r1)
            goto Ldf
        Lc1:
            androidx.compose.ui.platform.AccessibilityIterators$CharacterTextSegmentIterator$Companion r8 = androidx.compose.ui.platform.AccessibilityIterators.CharacterTextSegmentIterator.INSTANCE
            androidx.compose.ui.platform.AndroidComposeView r9 = r7.view
            android.content.Context r9 = r9.getContext()
            android.content.res.Resources r9 = r9.getResources()
            android.content.res.Configuration r9 = r9.getConfiguration()
            java.util.Locale r9 = r9.locale
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r2)
            androidx.compose.ui.platform.AccessibilityIterators$CharacterTextSegmentIterator r8 = r8.getInstance(r9)
            androidx.compose.ui.platform.AccessibilityIterators$AbstractTextSegmentIterator r8 = (androidx.compose.ui.platform.AccessibilityIterators.AbstractTextSegmentIterator) r8
            r8.initialize(r1)
        Ldf:
            androidx.compose.ui.platform.AccessibilityIterators$TextSegmentIterator r8 = (androidx.compose.ui.platform.AccessibilityIterators.TextSegmentIterator) r8
            return r8
        Le2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.getIteratorForGranularity(androidx.compose.ui.semantics.SemanticsNode, int):androidx.compose.ui.platform.AccessibilityIterators$TextSegmentIterator");
    }

    private final String getIterableTextForAccessibility(SemanticsNode node) {
        AnnotatedString annotatedString;
        if (node == null) {
            return null;
        }
        if (!node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getContentDescription())) {
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.isTextField(node)) {
                AnnotatedString textForTextField = getTextForTextField(node.getUnmergedConfig());
                if (textForTextField != null) {
                    return textForTextField.getText();
                }
                return null;
            }
            List list = (List) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getText());
            if (list == null || (annotatedString = (AnnotatedString) CollectionsKt.firstOrNull(list)) == null) {
                return null;
            }
            return annotatedString.getText();
        }
        return TempListUtilsKt.fastJoinToString$default((List) node.getUnmergedConfig().get(SemanticsProperties.INSTANCE.getContentDescription()), Constants.SEPARATOR_COMMA, null, null, 0, null, null, 62, null);
    }

    private final AnnotatedString getTextForTextField(SemanticsConfiguration semanticsConfiguration) {
        return (AnnotatedString) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsProperties.INSTANCE.getEditableText());
    }

    /* compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\"\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\u0011"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$MyNodeProvider;", "Landroid/view/accessibility/AccessibilityNodeProvider;", "(Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat;)V", "addExtraDataToAccessibilityNodeInfo", "", "virtualViewId", "", "info", "Landroid/view/accessibility/AccessibilityNodeInfo;", "extraDataKey", "", "arguments", "Landroid/os/Bundle;", "createAccessibilityNodeInfo", "performAction", "", Constants.KEY_ACTION, "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class MyNodeProvider extends AccessibilityNodeProvider {
        public MyNodeProvider() {
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int virtualViewId) {
            return AndroidComposeViewAccessibilityDelegateCompat.this.createNodeInfo(virtualViewId);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int virtualViewId, int action, Bundle arguments) {
            return AndroidComposeViewAccessibilityDelegateCompat.this.performActionHelper(virtualViewId, action, arguments);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public void addExtraDataToAccessibilityNodeInfo(int virtualViewId, AccessibilityNodeInfo info, String extraDataKey, Bundle arguments) {
            Intrinsics.checkNotNullParameter(info, "info");
            Intrinsics.checkNotNullParameter(extraDataKey, "extraDataKey");
            AndroidComposeViewAccessibilityDelegateCompat.this.addExtraDataToAccessibilityNodeInfoHelper(virtualViewId, info, extraDataKey, arguments);
        }
    }

    /* compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$Api24Impl;", "", "()V", "addSetProgressAction", "", "info", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;", "semanticsNode", "Landroidx/compose/ui/semantics/SemanticsNode;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class Api24Impl {
        public static final Api24Impl INSTANCE = new Api24Impl();

        private Api24Impl() {
        }

        @JvmStatic
        public static final void addSetProgressAction(AccessibilityNodeInfoCompat info, SemanticsNode semanticsNode) {
            AccessibilityAction accessibilityAction;
            Intrinsics.checkNotNullParameter(info, "info");
            Intrinsics.checkNotNullParameter(semanticsNode, "semanticsNode");
            if (!AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode) || (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetProgress())) == null) {
                return;
            }
            info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionSetProgress, accessibilityAction.getLabel()));
        }
    }

    /* compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$Api28Impl;", "", "()V", "setScrollEventDelta", "", "event", "Landroid/view/accessibility/AccessibilityEvent;", "deltaX", "", "deltaY", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class Api28Impl {
        public static final Api28Impl INSTANCE = new Api28Impl();

        private Api28Impl() {
        }

        @JvmStatic
        public static final void setScrollEventDelta(AccessibilityEvent event, int deltaX, int deltaY) {
            Intrinsics.checkNotNullParameter(event, "event");
            event.setScrollDeltaX(deltaX);
            event.setScrollDeltaY(deltaY);
        }
    }

    /* compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$Api29Impl;", "", "()V", "addPageActions", "", "info", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;", "semanticsNode", "Landroidx/compose/ui/semantics/SemanticsNode;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class Api29Impl {
        public static final Api29Impl INSTANCE = new Api29Impl();

        private Api29Impl() {
        }

        @JvmStatic
        public static final void addPageActions(AccessibilityNodeInfoCompat info, SemanticsNode semanticsNode) {
            Intrinsics.checkNotNullParameter(info, "info");
            Intrinsics.checkNotNullParameter(semanticsNode, "semanticsNode");
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
                AccessibilityAction accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageUp());
                if (accessibilityAction != null) {
                    info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionPageUp, accessibilityAction.getLabel()));
                }
                AccessibilityAction accessibilityAction2 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageDown());
                if (accessibilityAction2 != null) {
                    info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionPageDown, accessibilityAction2.getLabel()));
                }
                AccessibilityAction accessibilityAction3 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageLeft());
                if (accessibilityAction3 != null) {
                    info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionPageLeft, accessibilityAction3.getLabel()));
                }
                AccessibilityAction accessibilityAction4 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageRight());
                if (accessibilityAction4 != null) {
                    info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionPageRight, accessibilityAction4.getLabel()));
                }
            }
        }
    }
}
