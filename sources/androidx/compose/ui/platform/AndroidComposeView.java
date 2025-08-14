package androidx.compose.ui.platform;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.os.SystemClock;
import android.os.Trace;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStructure;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.autofill.AndroidAutofill;
import androidx.compose.ui.autofill.AndroidAutofill_androidKt;
import androidx.compose.ui.autofill.Autofill;
import androidx.compose.ui.autofill.AutofillCallback;
import androidx.compose.ui.autofill.AutofillTree;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusOwner;
import androidx.compose.ui.focus.FocusOwnerImpl;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.PlatformHapticFeedback;
import androidx.compose.ui.input.InputMode;
import androidx.compose.ui.input.InputModeManager;
import androidx.compose.ui.input.InputModeManagerImpl;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.MotionEventAdapter;
import androidx.compose.ui.input.pointer.PointerIcon;
import androidx.compose.ui.input.pointer.PointerIconService;
import androidx.compose.ui.input.pointer.PointerInputEvent;
import androidx.compose.ui.input.pointer.PointerInputEventData;
import androidx.compose.ui.input.pointer.PointerInputEventProcessor;
import androidx.compose.ui.input.pointer.PointerInputEventProcessorKt;
import androidx.compose.ui.input.pointer.PointerKeyboardModifiers;
import androidx.compose.ui.input.pointer.PositionCalculator;
import androidx.compose.ui.input.pointer.ProcessResult;
import androidx.compose.ui.input.rotary.RotaryInputModifierKt;
import androidx.compose.ui.input.rotary.RotaryScrollEvent;
import androidx.compose.ui.layout.RootMeasurePolicy;
import androidx.compose.ui.modifier.ModifierLocalManager;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.node.MeasureAndLayoutDelegate;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.OwnerSnapshotObserver;
import androidx.compose.ui.node.RootForTest;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.semantics.SemanticsModifierCore;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsNodeKt;
import androidx.compose.ui.semantics.SemanticsOwner;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontFamilyResolver_androidKt;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputServiceAndroid;
import androidx.compose.ui.unit.AndroidDensity_androidKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.math.MathKt;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: AndroidComposeView.android.kt */
@Metadata(d1 = {"\u0000¾\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0019*\u0002\u0099\u0001\b\u0001\u0018\u0000 ÿ\u00022\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0004ÿ\u0002\u0080\u0003B\r\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001b\u0010è\u0001\u001a\u00020+2\b\u0010Ë\u0001\u001a\u00030é\u00012\b\u0010ê\u0001\u001a\u00030\u009c\u0001J\u0019\u0010\u001a\u001a\u00020+2\u000f\u0010ë\u0001\u001a\n\u0012\u0005\u0012\u00030í\u00010ì\u0001H\u0016J\t\u0010î\u0001\u001a\u00020RH\u0002J\u0013\u0010ï\u0001\u001a\u00020+H\u0086@ø\u0001\u0000¢\u0006\u0003\u0010ð\u0001J!\u0010ñ\u0001\u001a\u00020k2\u0007\u0010ò\u0001\u001a\u00020kH\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\bó\u0001\u0010ô\u0001J!\u0010õ\u0001\u001a\u00020k2\u0007\u0010ö\u0001\u001a\u00020kH\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b÷\u0001\u0010ô\u0001J\u0012\u0010ø\u0001\u001a\u00020R2\u0007\u0010ù\u0001\u001a\u000201H\u0016J\u0012\u0010ú\u0001\u001a\u00020R2\u0007\u0010ù\u0001\u001a\u000201H\u0016J\u0012\u0010û\u0001\u001a\u00020+2\u0007\u0010ü\u0001\u001a\u00020\u0001H\u0002J\u001f\u0010ý\u0001\u001a\u000f\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u0002010þ\u00012\u0007\u0010ÿ\u0001\u001a\u000201H\u0002J.\u0010\u0080\u0002\u001a\u00020;2\u0014\u0010\u0081\u0002\u001a\u000f\u0012\u0005\u0012\u00030\u0082\u0002\u0012\u0004\u0012\u00020+0)2\r\u0010\u0083\u0002\u001a\b\u0012\u0004\u0012\u00020+0>H\u0016J\u0013\u0010\u0084\u0002\u001a\u00020+2\b\u0010\u0085\u0002\u001a\u00030\u0086\u0002H\u0014J\u0013\u0010\u0087\u0002\u001a\u00020R2\b\u0010\u0088\u0002\u001a\u00030\u0095\u0001H\u0016J\u0013\u0010\u0089\u0002\u001a\u00020R2\b\u0010\u0088\u0002\u001a\u00030\u0095\u0001H\u0016J\u0013\u0010\u008a\u0002\u001a\u00020R2\b\u0010\u0088\u0002\u001a\u00030\u008b\u0002H\u0016J\u0013\u0010\u008c\u0002\u001a\u00020R2\b\u0010\u008d\u0002\u001a\u00030\u0095\u0001H\u0016J\u001b\u0010\u008e\u0002\u001a\u00020+2\b\u0010Ë\u0001\u001a\u00030é\u00012\b\u0010\u0085\u0002\u001a\u00030\u0086\u0002J\u001f\u0010\u008f\u0002\u001a\u0005\u0018\u00010Ì\u00012\u0007\u0010\u0090\u0002\u001a\u0002012\b\u0010\u0091\u0002\u001a\u00030Ì\u0001H\u0002J\u0013\u0010\u0092\u0002\u001a\u0005\u0018\u00010Ì\u00012\u0007\u0010\u0090\u0002\u001a\u000201J\u0013\u0010\u0093\u0002\u001a\u00020+2\b\u0010ê\u0001\u001a\u00030\u009c\u0001H\u0016J%\u0010\u0094\u0002\u001a\u0005\u0018\u00010\u0095\u00022\b\u0010\u0096\u0002\u001a\u00030\u0097\u0002H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u0098\u0002\u0010\u0099\u0002J\u0013\u0010\u009a\u0002\u001a\u00020+2\b\u0010\u009b\u0002\u001a\u00030\u009c\u0002H\u0016J&\u0010\u009d\u0002\u001a\u00030\u009e\u00022\b\u0010\u008d\u0002\u001a\u00030\u0095\u0001H\u0002ø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u009f\u0002\u0010 \u0002J\u0013\u0010¡\u0002\u001a\u00020R2\b\u0010\u0088\u0002\u001a\u00030\u0095\u0001H\u0002J\u001d\u0010¢\u0002\u001a\u00020R2\b\u0010\u0088\u0002\u001a\u00030\u0095\u00012\b\u0010£\u0002\u001a\u00030\u0095\u0001H\u0002J\t\u0010¤\u0002\u001a\u00020+H\u0016J\u0013\u0010¥\u0002\u001a\u00020+2\b\u0010¦\u0002\u001a\u00030\u009c\u0001H\u0002J\u0013\u0010§\u0002\u001a\u00020+2\b\u0010¦\u0002\u001a\u00030\u009c\u0001H\u0002J\u0013\u0010¨\u0002\u001a\u00020R2\b\u0010\u0088\u0002\u001a\u00030\u0095\u0001H\u0002J\u0013\u0010©\u0002\u001a\u00020R2\b\u0010\u0088\u0002\u001a\u00030\u0095\u0001H\u0002J\u0013\u0010ª\u0002\u001a\u00020R2\b\u0010\u008d\u0002\u001a\u00030\u0095\u0001H\u0002J\u0013\u0010«\u0002\u001a\u00020R2\b\u0010\u0088\u0002\u001a\u00030\u0095\u0001H\u0002J!\u0010¬\u0002\u001a\u00020k2\u0007\u0010ö\u0001\u001a\u00020kH\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u00ad\u0002\u0010ô\u0001J,\u0010®\u0002\u001a\u00020+2\b\u0010ê\u0001\u001a\u00030\u009c\u00012\b\u0010¯\u0002\u001a\u00030\u008a\u0001H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b°\u0002\u0010±\u0002J\u0012\u0010®\u0002\u001a\u00020+2\u0007\u0010²\u0002\u001a\u00020RH\u0016J\t\u0010³\u0002\u001a\u00020+H\u0016J!\u0010´\u0002\u001a\u00020+2\u0007\u0010µ\u0002\u001a\u00020;2\u0007\u0010¶\u0002\u001a\u00020RH\u0000¢\u0006\u0003\b·\u0002J\u0013\u0010¸\u0002\u001a\u00020+2\b\u0010¦\u0002\u001a\u00030\u009c\u0001H\u0016J\t\u0010¹\u0002\u001a\u00020+H\u0014J\t\u0010º\u0002\u001a\u00020RH\u0016J\u0012\u0010»\u0002\u001a\u00020+2\u0007\u0010¼\u0002\u001a\u00020*H\u0014J\u0016\u0010½\u0002\u001a\u0005\u0018\u00010¾\u00022\b\u0010¿\u0002\u001a\u00030À\u0002H\u0016J\u0013\u0010Á\u0002\u001a\u00020+2\b\u0010¦\u0002\u001a\u00030\u009c\u0001H\u0016J\t\u0010Â\u0002\u001a\u00020+H\u0014J\u0013\u0010Ã\u0002\u001a\u00020+2\b\u0010\u0085\u0002\u001a\u00030\u0086\u0002H\u0014J\t\u0010Ä\u0002\u001a\u00020+H\u0016J'\u0010Å\u0002\u001a\u00020+2\u0007\u0010Æ\u0002\u001a\u00020R2\u0007\u0010ù\u0001\u001a\u0002012\n\u0010Ç\u0002\u001a\u0005\u0018\u00010\u009c\u0002H\u0014J6\u0010È\u0002\u001a\u00020+2\u0007\u0010É\u0002\u001a\u00020R2\u0007\u0010Ê\u0002\u001a\u0002012\u0007\u0010Ë\u0002\u001a\u0002012\u0007\u0010Ì\u0002\u001a\u0002012\u0007\u0010Í\u0002\u001a\u000201H\u0014J\u0013\u0010Î\u0002\u001a\u00020+2\b\u0010ê\u0001\u001a\u00030\u009c\u0001H\u0016J\u001b\u0010Ï\u0002\u001a\u00020+2\u0007\u0010Ð\u0002\u001a\u0002012\u0007\u0010Ñ\u0002\u001a\u000201H\u0014J\u001e\u0010Ò\u0002\u001a\u00020+2\n\u0010Ó\u0002\u001a\u0005\u0018\u00010Ô\u00022\u0007\u0010Õ\u0002\u001a\u000201H\u0016J%\u0010Ö\u0002\u001a\u00020+2\b\u0010ê\u0001\u001a\u00030\u009c\u00012\u0007\u0010×\u0002\u001a\u00020R2\u0007\u0010Ø\u0002\u001a\u00020RH\u0016J%\u0010Ù\u0002\u001a\u00020+2\b\u0010ê\u0001\u001a\u00030\u009c\u00012\u0007\u0010×\u0002\u001a\u00020R2\u0007\u0010Ø\u0002\u001a\u00020RH\u0016J\u0013\u0010Ú\u0002\u001a\u00020+2\b\u0010Û\u0002\u001a\u00030Ü\u0002H\u0016J\u0011\u0010Ý\u0002\u001a\u00020+2\u0006\u0010v\u001a\u000201H\u0016J\t\u0010Þ\u0002\u001a\u00020+H\u0016J\u0012\u0010ß\u0002\u001a\u00020+2\u0007\u0010à\u0002\u001a\u00020RH\u0016J\t\u0010á\u0002\u001a\u00020+H\u0002J\u0013\u0010á\u0002\u001a\u00020+2\b\u0010\u008d\u0002\u001a\u00030\u0095\u0001H\u0002J\t\u0010â\u0002\u001a\u00020+H\u0002J\u0018\u0010ã\u0002\u001a\u00020R2\u0007\u0010µ\u0002\u001a\u00020;H\u0000¢\u0006\u0003\bä\u0002J\u0018\u0010å\u0002\u001a\u00020+2\r\u0010æ\u0002\u001a\b\u0012\u0004\u0012\u00020+0>H\u0016J\u0013\u0010ç\u0002\u001a\u00020+2\b\u0010æ\u0002\u001a\u00030è\u0002H\u0016J\u0011\u0010é\u0002\u001a\u00020+2\b\u0010Ë\u0001\u001a\u00030é\u0001J\u0007\u0010ê\u0002\u001a\u00020+J\u0013\u0010ë\u0002\u001a\u00020+2\b\u0010ê\u0001\u001a\u00030\u009c\u0001H\u0016J\u0017\u0010ì\u0002\u001a\u00020+2\f\b\u0002\u0010í\u0002\u001a\u0005\u0018\u00010\u009c\u0001H\u0002J!\u0010î\u0002\u001a\u00020k2\u0007\u0010ï\u0002\u001a\u00020kH\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\bð\u0002\u0010ô\u0001J\"\u0010ñ\u0002\u001a\u00020R2\b\u0010\u0096\u0002\u001a\u00030\u0097\u0002H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\bò\u0002\u0010ó\u0002J&\u0010ô\u0002\u001a\u00030\u009e\u00022\b\u0010\u008d\u0002\u001a\u00030\u0095\u0001H\u0002ø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\bõ\u0002\u0010 \u0002J0\u0010ö\u0002\u001a\u00020+2\b\u0010\u008d\u0002\u001a\u00030\u0095\u00012\u0007\u0010÷\u0002\u001a\u0002012\u0007\u0010ø\u0002\u001a\u00020m2\t\b\u0002\u0010ù\u0002\u001a\u00020RH\u0002J\u001d\u0010ú\u0002\u001a\u00020+2\u0014\u0010û\u0002\u001a\u000f\u0012\u0005\u0012\u00030\u008c\u0001\u0012\u0004\u0012\u00020+0)J\t\u0010ü\u0002\u001a\u00020RH\u0016J\t\u0010ý\u0002\u001a\u00020+H\u0002J\u000e\u0010þ\u0002\u001a\u00020R*\u00030\u009c\u0001H\u0002R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u001fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010$\u001a\u00020%X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R&\u0010(\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020+0)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u000e\u00100\u001a\u000201X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u00104\u001a\u0002032\u0006\u00102\u001a\u000203@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0010\u00107\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00109\u001a\b\u0012\u0004\u0012\u00020;0:X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010<\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020+\u0018\u00010>0=X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010?\u001a\u00020@X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010BR+\u0010D\u001a\u00020C2\u0006\u00102\u001a\u00020C8V@RX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\bI\u0010J\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u001c\u0010K\u001a\u00020L8\u0016X\u0097\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bM\u0010N\u001a\u0004\bO\u0010PR\u000e\u0010Q\u001a\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020TX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010U\u001a\u00020VX\u0082\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010WR\u0014\u0010X\u001a\u00020YX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010[R\u0014\u0010\\\u001a\u00020R8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b]\u0010^R\u000e\u0010_\u001a\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010`\u001a\u00020a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bb\u0010cR\u000e\u0010d\u001a\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010e\u001a\u00020R8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\be\u0010^R\u000e\u0010f\u001a\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010g\u001a\u00020hX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010i\u001a\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000R\u0019\u0010j\u001a\u00020kX\u0082\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010WR$\u0010l\u001a\u00020m8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bn\u0010N\u001a\u0004\bo\u0010p\"\u0004\bq\u0010rR\u0014\u0010s\u001a\b\u0012\u0004\u0012\u00020;0tX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010v\u001a\u00020u2\u0006\u00102\u001a\u00020u8V@RX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b{\u0010J\u001a\u0004\bw\u0010x\"\u0004\by\u0010zR\u000e\u0010|\u001a\u00020}X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010~\u001a\u00020\u007fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0080\u0001\u001a\u00020m8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0081\u0001\u0010pR\u0018\u0010\u0082\u0001\u001a\u00030\u0083\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0084\u0001\u0010\u0085\u0001R\u0010\u0010\u0086\u0001\u001a\u00030\u0087\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010\u0088\u0001\u001a\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0089\u0001\u001a\u0005\u0018\u00010\u008a\u0001X\u0082\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0002\n\u0000R\u001e\u0010\u008b\u0001\u001a\u0011\u0012\u0005\u0012\u00030\u008c\u0001\u0012\u0004\u0012\u00020+\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u008d\u0001\u001a\u00030\u008e\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001R\u0010\u0010\u0091\u0001\u001a\u00030\u0092\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0093\u0001\u001a\n\u0012\u0004\u0012\u00020;\u0018\u00010:X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0096\u0001\u001a\u00020mX\u0082\u000e¢\u0006\u0002\n\u0000R\u0015\u0010\u0097\u0001\u001a\b\u0012\u0004\u0012\u00020+0>X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0098\u0001\u001a\u00030\u0099\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u009a\u0001R\u0018\u0010\u009b\u0001\u001a\u00030\u009c\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001R\u0018\u0010\u009f\u0001\u001a\u00030 \u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b¡\u0001\u0010¢\u0001R\u000f\u0010£\u0001\u001a\u00020hX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010¤\u0001\u001a\u00030¥\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010¦\u0001\u001a\u00030§\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010¨\u0001\u001a\u00030©\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\bª\u0001\u0010«\u0001R\u0010\u0010¬\u0001\u001a\u00030\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010®\u0001\u001a\u00030¯\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b°\u0001\u0010±\u0001R%\u0010²\u0001\u001a\u00020RX\u0096\u000e¢\u0006\u0018\n\u0000\u0012\u0005\b³\u0001\u0010N\u001a\u0005\b´\u0001\u0010^\"\u0006\bµ\u0001\u0010¶\u0001R\u0018\u0010·\u0001\u001a\u00030¸\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b¹\u0001\u0010º\u0001R\u000f\u0010»\u0001\u001a\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u0010¼\u0001\u001a\u00030½\u0001X\u0096\u0004¢\u0006\u0011\n\u0000\u0012\u0005\b¾\u0001\u0010N\u001a\u0006\b¿\u0001\u0010À\u0001R\u0010\u0010Á\u0001\u001a\u00030Â\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010Ã\u0001\u001a\u00030Ä\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\bÅ\u0001\u0010Æ\u0001R\u0010\u0010Ç\u0001\u001a\u00030È\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010É\u0001\u001a\u00030Ê\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010Ë\u0001\u001a\u00030Ì\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bÍ\u0001\u0010Î\u0001R\u0018\u0010Ï\u0001\u001a\u00030Ð\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\bÑ\u0001\u0010Ò\u0001R\u0012\u0010Ó\u0001\u001a\u0005\u0018\u00010Ô\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010Õ\u0001\u001a\u00030Ö\u0001X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0005\n\u0003\u0010×\u0001R7\u0010Ø\u0001\u001a\u0005\u0018\u00010\u008c\u00012\t\u00102\u001a\u0005\u0018\u00010\u008c\u00018F@BX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\bÝ\u0001\u0010J\u001a\u0006\bÙ\u0001\u0010Ú\u0001\"\u0006\bÛ\u0001\u0010Ü\u0001R\u000f\u0010Þ\u0001\u001a\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010ß\u0001\u001a\u00030à\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bá\u0001\u0010â\u0001R\u001a\u0010ã\u0001\u001a\u00020kX\u0082\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010WR\u001c\u0010ä\u0001\u001a\u00030Ö\u0001X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0005\n\u0003\u0010×\u0001R\u001b\u0010å\u0001\u001a\u000201*\u00020*8BX\u0082\u0004¢\u0006\b\u001a\u0006\bæ\u0001\u0010ç\u0001\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0081\u0003"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeView;", "Landroid/view/ViewGroup;", "Landroidx/compose/ui/node/Owner;", "Landroidx/compose/ui/platform/ViewRootForTest;", "Landroidx/compose/ui/input/pointer/PositionCalculator;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_androidViewsHandler", "Landroidx/compose/ui/platform/AndroidViewsHandler;", "_autofill", "Landroidx/compose/ui/autofill/AndroidAutofill;", "_inputModeManager", "Landroidx/compose/ui/input/InputModeManagerImpl;", "_windowInfo", "Landroidx/compose/ui/platform/WindowInfoImpl;", "accessibilityDelegate", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat;", "accessibilityManager", "Landroidx/compose/ui/platform/AndroidAccessibilityManager;", "getAccessibilityManager", "()Landroidx/compose/ui/platform/AndroidAccessibilityManager;", "androidViewsHandler", "getAndroidViewsHandler$ui_release", "()Landroidx/compose/ui/platform/AndroidViewsHandler;", "autofill", "Landroidx/compose/ui/autofill/Autofill;", "getAutofill", "()Landroidx/compose/ui/autofill/Autofill;", "autofillTree", "Landroidx/compose/ui/autofill/AutofillTree;", "getAutofillTree", "()Landroidx/compose/ui/autofill/AutofillTree;", "canvasHolder", "Landroidx/compose/ui/graphics/CanvasHolder;", "clipboardManager", "Landroidx/compose/ui/platform/AndroidClipboardManager;", "getClipboardManager", "()Landroidx/compose/ui/platform/AndroidClipboardManager;", "configurationChangeObserver", "Lkotlin/Function1;", "Landroid/content/res/Configuration;", "", "getConfigurationChangeObserver", "()Lkotlin/jvm/functions/Function1;", "setConfigurationChangeObserver", "(Lkotlin/jvm/functions/Function1;)V", "currentFontWeightAdjustment", "", "<set-?>", "Landroidx/compose/ui/unit/Density;", "density", "getDensity", "()Landroidx/compose/ui/unit/Density;", "desiredPointerIcon", "Landroidx/compose/ui/input/pointer/PointerIcon;", "dirtyLayers", "", "Landroidx/compose/ui/node/OwnedLayer;", "endApplyChangesListeners", "Landroidx/compose/runtime/collection/MutableVector;", "Lkotlin/Function0;", "focusOwner", "Landroidx/compose/ui/focus/FocusOwner;", "getFocusOwner", "()Landroidx/compose/ui/focus/FocusOwner;", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "fontFamilyResolver", "getFontFamilyResolver", "()Landroidx/compose/ui/text/font/FontFamily$Resolver;", "setFontFamilyResolver", "(Landroidx/compose/ui/text/font/FontFamily$Resolver;)V", "fontFamilyResolver$delegate", "Landroidx/compose/runtime/MutableState;", "fontLoader", "Landroidx/compose/ui/text/font/Font$ResourceLoader;", "getFontLoader$annotations", "()V", "getFontLoader", "()Landroidx/compose/ui/text/font/Font$ResourceLoader;", "forceUseMatrixCache", "", "globalLayoutListener", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "globalPosition", "Landroidx/compose/ui/unit/IntOffset;", "J", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "getHapticFeedBack", "()Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "hasPendingMeasureOrLayout", "getHasPendingMeasureOrLayout", "()Z", "hoverExitReceived", "inputModeManager", "Landroidx/compose/ui/input/InputModeManager;", "getInputModeManager", "()Landroidx/compose/ui/input/InputModeManager;", "isDrawingContent", "isLifecycleInResumedState", "isRenderNodeCompatible", "keyInputModifier", "Landroidx/compose/ui/Modifier;", "keyboardModifiersRequireUpdate", "lastDownPointerPosition", "Landroidx/compose/ui/geometry/Offset;", "lastMatrixRecalculationAnimationTime", "", "getLastMatrixRecalculationAnimationTime$ui_release$annotations", "getLastMatrixRecalculationAnimationTime$ui_release", "()J", "setLastMatrixRecalculationAnimationTime$ui_release", "(J)V", "layerCache", "Landroidx/compose/ui/platform/WeakCache;", "Landroidx/compose/ui/unit/LayoutDirection;", ViewProps.LAYOUT_DIRECTION, "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "layoutDirection$delegate", "matrixToWindow", "Landroidx/compose/ui/platform/CalculateMatrixToWindow;", "measureAndLayoutDelegate", "Landroidx/compose/ui/node/MeasureAndLayoutDelegate;", "measureIteration", "getMeasureIteration", "modifierLocalManager", "Landroidx/compose/ui/modifier/ModifierLocalManager;", "getModifierLocalManager", "()Landroidx/compose/ui/modifier/ModifierLocalManager;", "motionEventAdapter", "Landroidx/compose/ui/input/pointer/MotionEventAdapter;", "observationClearRequested", "onMeasureConstraints", "Landroidx/compose/ui/unit/Constraints;", "onViewTreeOwnersAvailable", "Landroidx/compose/ui/platform/AndroidComposeView$ViewTreeOwners;", "pointerIconService", "Landroidx/compose/ui/input/pointer/PointerIconService;", "getPointerIconService", "()Landroidx/compose/ui/input/pointer/PointerIconService;", "pointerInputEventProcessor", "Landroidx/compose/ui/input/pointer/PointerInputEventProcessor;", "postponedDirtyLayers", "previousMotionEvent", "Landroid/view/MotionEvent;", "relayoutTime", "resendMotionEventOnLayout", "resendMotionEventRunnable", "androidx/compose/ui/platform/AndroidComposeView$resendMotionEventRunnable$1", "Landroidx/compose/ui/platform/AndroidComposeView$resendMotionEventRunnable$1;", "root", "Landroidx/compose/ui/node/LayoutNode;", "getRoot", "()Landroidx/compose/ui/node/LayoutNode;", "rootForTest", "Landroidx/compose/ui/node/RootForTest;", "getRootForTest", "()Landroidx/compose/ui/node/RootForTest;", "rotaryInputModifier", "scrollChangedListener", "Landroid/view/ViewTreeObserver$OnScrollChangedListener;", "semanticsModifier", "Landroidx/compose/ui/semantics/SemanticsModifierCore;", "semanticsOwner", "Landroidx/compose/ui/semantics/SemanticsOwner;", "getSemanticsOwner", "()Landroidx/compose/ui/semantics/SemanticsOwner;", "sendHoverExitEvent", "Ljava/lang/Runnable;", "sharedDrawScope", "Landroidx/compose/ui/node/LayoutNodeDrawScope;", "getSharedDrawScope", "()Landroidx/compose/ui/node/LayoutNodeDrawScope;", "showLayoutBounds", "getShowLayoutBounds$annotations", "getShowLayoutBounds", "setShowLayoutBounds", "(Z)V", "snapshotObserver", "Landroidx/compose/ui/node/OwnerSnapshotObserver;", "getSnapshotObserver", "()Landroidx/compose/ui/node/OwnerSnapshotObserver;", "superclassInitComplete", "textInputService", "Landroidx/compose/ui/text/input/TextInputService;", "getTextInputService$annotations", "getTextInputService", "()Landroidx/compose/ui/text/input/TextInputService;", "textInputServiceAndroid", "Landroidx/compose/ui/text/input/TextInputServiceAndroid;", "textToolbar", "Landroidx/compose/ui/platform/TextToolbar;", "getTextToolbar", "()Landroidx/compose/ui/platform/TextToolbar;", "tmpPositionArray", "", "touchModeChangeListener", "Landroid/view/ViewTreeObserver$OnTouchModeChangeListener;", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "viewLayersContainer", "Landroidx/compose/ui/platform/DrawChildContainer;", "viewToWindowMatrix", "Landroidx/compose/ui/graphics/Matrix;", "[F", "viewTreeOwners", "getViewTreeOwners", "()Landroidx/compose/ui/platform/AndroidComposeView$ViewTreeOwners;", "setViewTreeOwners", "(Landroidx/compose/ui/platform/AndroidComposeView$ViewTreeOwners;)V", "viewTreeOwners$delegate", "wasMeasuredWithMultipleConstraints", "windowInfo", "Landroidx/compose/ui/platform/WindowInfo;", "getWindowInfo", "()Landroidx/compose/ui/platform/WindowInfo;", "windowPosition", "windowToViewMatrix", "fontWeightAdjustmentCompat", "getFontWeightAdjustmentCompat", "(Landroid/content/res/Configuration;)I", "addAndroidView", "Landroidx/compose/ui/viewinterop/AndroidViewHolder;", "layoutNode", "values", "Landroid/util/SparseArray;", "Landroid/view/autofill/AutofillValue;", "autofillSupported", "boundsUpdatesEventLoop", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calculateLocalPosition", "positionInWindow", "calculateLocalPosition-MK-Hz9U", "(J)J", "calculatePositionInWindow", "localPosition", "calculatePositionInWindow-MK-Hz9U", "canScrollHorizontally", "direction", "canScrollVertically", "clearChildInvalidObservations", "viewGroup", "convertMeasureSpec", "Lkotlin/Pair;", "measureSpec", "createLayer", "drawBlock", "Landroidx/compose/ui/graphics/Canvas;", "invalidateParentLayer", "dispatchDraw", "canvas", "Landroid/graphics/Canvas;", "dispatchGenericMotionEvent", "event", "dispatchHoverEvent", "dispatchKeyEvent", "Landroid/view/KeyEvent;", "dispatchTouchEvent", "motionEvent", "drawAndroidView", "findViewByAccessibilityIdRootedAtCurrentView", "accessibilityId", "currentView", "findViewByAccessibilityIdTraversal", "forceMeasureTheSubtree", "getFocusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "keyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "getFocusDirection-P8AzH3I", "(Landroid/view/KeyEvent;)Landroidx/compose/ui/focus/FocusDirection;", "getFocusedRect", "rect", "Landroid/graphics/Rect;", "handleMotionEvent", "Landroidx/compose/ui/input/pointer/ProcessResult;", "handleMotionEvent-8iAsVTc", "(Landroid/view/MotionEvent;)I", "handleRotaryEvent", "hasChangedDevices", "lastEvent", "invalidateDescendants", "invalidateLayers", "node", "invalidateLayoutNodeMeasurement", "isBadMotionEvent", "isDevicePressEvent", "isInBounds", "isPositionChanged", "localToScreen", "localToScreen-MK-Hz9U", "measureAndLayout", CryptoServicesPermission.CONSTRAINTS, "measureAndLayout-0kLqBqw", "(Landroidx/compose/ui/node/LayoutNode;J)V", "sendPointerUpdate", "measureAndLayoutForTest", "notifyLayerIsDirty", "layer", "isDirty", "notifyLayerIsDirty$ui_release", "onAttach", "onAttachedToWindow", "onCheckIsTextEditor", "onConfigurationChanged", "newConfig", "onCreateInputConnection", "Landroid/view/inputmethod/InputConnection;", "outAttrs", "Landroid/view/inputmethod/EditorInfo;", "onDetach", "onDetachedFromWindow", "onDraw", "onEndApplyChanges", "onFocusChanged", "gainFocus", "previouslyFocusedRect", ViewProps.ON_LAYOUT, "changed", "l", "t", "r", "b", "onLayoutChange", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onProvideAutofillVirtualStructure", "structure", "Landroid/view/ViewStructure;", "flags", "onRequestMeasure", "affectsLookahead", "forceRequest", "onRequestRelayout", "onResume", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onRtlPropertiesChanged", "onSemanticsChange", "onWindowFocusChanged", "hasWindowFocus", "recalculateWindowPosition", "recalculateWindowViewTransforms", "recycle", "recycle$ui_release", "registerOnEndApplyChangesListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "registerOnLayoutCompletedListener", "Landroidx/compose/ui/node/Owner$OnLayoutCompletedListener;", "removeAndroidView", "requestClearInvalidObservations", "requestOnPositionedCallback", "scheduleMeasureAndLayout", "nodeToRemeasure", "screenToLocal", "positionOnScreen", "screenToLocal-MK-Hz9U", "sendKeyEvent", "sendKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "sendMotionEvent", "sendMotionEvent-8iAsVTc", "sendSimulatedEvent", Constants.KEY_ACTION, "eventTime", "forceHover", "setOnViewTreeOwnersAvailable", "callback", "shouldDelayChildPressedState", "updatePositionCacheAndDispatch", "childSizeCanAffectParentSize", "Companion", "ViewTreeOwners", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidComposeView extends ViewGroup implements Owner, ViewRootForTest, PositionCalculator, DefaultLifecycleObserver {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FocusTag = "Compose Focus";
    private static final int MaximumLayerCacheSize = 10;
    private static Method getBooleanMethod;
    private static Class<?> systemPropertiesClass;
    private AndroidViewsHandler _androidViewsHandler;
    private final AndroidAutofill _autofill;
    private final InputModeManagerImpl _inputModeManager;
    private final WindowInfoImpl _windowInfo;
    private final AndroidComposeViewAccessibilityDelegateCompat accessibilityDelegate;
    private final AndroidAccessibilityManager accessibilityManager;
    private final AutofillTree autofillTree;
    private final CanvasHolder canvasHolder;
    private final AndroidClipboardManager clipboardManager;
    private Function1<? super Configuration, Unit> configurationChangeObserver;
    private int currentFontWeightAdjustment;
    private Density density;
    private PointerIcon desiredPointerIcon;
    private final List<OwnedLayer> dirtyLayers;
    private final MutableVector<Function0<Unit>> endApplyChangesListeners;
    private final FocusOwner focusOwner;

    /* renamed from: fontFamilyResolver$delegate, reason: from kotlin metadata */
    private final MutableState fontFamilyResolver;
    private final Font.ResourceLoader fontLoader;
    private boolean forceUseMatrixCache;
    private final ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener;
    private long globalPosition;
    private final HapticFeedback hapticFeedBack;
    private boolean hoverExitReceived;
    private boolean isDrawingContent;
    private boolean isRenderNodeCompatible;
    private final Modifier keyInputModifier;
    private boolean keyboardModifiersRequireUpdate;
    private long lastDownPointerPosition;
    private long lastMatrixRecalculationAnimationTime;
    private final WeakCache<OwnedLayer> layerCache;

    /* renamed from: layoutDirection$delegate, reason: from kotlin metadata */
    private final MutableState layoutDirection;
    private final CalculateMatrixToWindow matrixToWindow;
    private final MeasureAndLayoutDelegate measureAndLayoutDelegate;
    private final ModifierLocalManager modifierLocalManager;
    private final MotionEventAdapter motionEventAdapter;
    private boolean observationClearRequested;
    private Constraints onMeasureConstraints;
    private Function1<? super ViewTreeOwners, Unit> onViewTreeOwnersAvailable;
    private final PointerIconService pointerIconService;
    private final PointerInputEventProcessor pointerInputEventProcessor;
    private List<OwnedLayer> postponedDirtyLayers;
    private MotionEvent previousMotionEvent;
    private long relayoutTime;
    private final Function0<Unit> resendMotionEventOnLayout;
    private final AndroidComposeView$resendMotionEventRunnable$1 resendMotionEventRunnable;
    private final LayoutNode root;
    private final RootForTest rootForTest;
    private final Modifier rotaryInputModifier;
    private final ViewTreeObserver.OnScrollChangedListener scrollChangedListener;
    private final SemanticsModifierCore semanticsModifier;
    private final SemanticsOwner semanticsOwner;
    private final Runnable sendHoverExitEvent;
    private final LayoutNodeDrawScope sharedDrawScope;
    private boolean showLayoutBounds;
    private final OwnerSnapshotObserver snapshotObserver;
    private boolean superclassInitComplete;
    private final TextInputService textInputService;
    private final TextInputServiceAndroid textInputServiceAndroid;
    private final TextToolbar textToolbar;
    private final int[] tmpPositionArray;
    private final ViewTreeObserver.OnTouchModeChangeListener touchModeChangeListener;
    private final ViewConfiguration viewConfiguration;
    private DrawChildContainer viewLayersContainer;
    private final float[] viewToWindowMatrix;

    /* renamed from: viewTreeOwners$delegate, reason: from kotlin metadata */
    private final MutableState viewTreeOwners;
    private boolean wasMeasuredWithMultipleConstraints;
    private long windowPosition;
    private final float[] windowToViewMatrix;

    private final boolean autofillSupported() {
        return Build.VERSION.SDK_INT >= 26;
    }

    @Deprecated(message = "fontLoader is deprecated, use fontFamilyResolver", replaceWith = @ReplaceWith(expression = "fontFamilyResolver", imports = {}))
    public static /* synthetic */ void getFontLoader$annotations() {
    }

    public static /* synthetic */ void getLastMatrixRecalculationAnimationTime$ui_release$annotations() {
    }

    public static /* synthetic */ void getShowLayoutBounds$annotations() {
    }

    public static /* synthetic */ void getTextInputService$annotations() {
    }

    @Override // androidx.compose.ui.node.Owner
    public AndroidAccessibilityManager getAccessibilityManager() {
        return this.accessibilityManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public AutofillTree getAutofillTree() {
        return this.autofillTree;
    }

    @Override // androidx.compose.ui.node.Owner
    public AndroidClipboardManager getClipboardManager() {
        return this.clipboardManager;
    }

    public final Function1<Configuration, Unit> getConfigurationChangeObserver() {
        return this.configurationChangeObserver;
    }

    @Override // androidx.compose.ui.node.Owner, androidx.compose.ui.node.RootForTest
    public Density getDensity() {
        return this.density;
    }

    @Override // androidx.compose.ui.node.Owner
    public FocusOwner getFocusOwner() {
        return this.focusOwner;
    }

    @Override // androidx.compose.ui.node.Owner
    public Font.ResourceLoader getFontLoader() {
        return this.fontLoader;
    }

    @Override // androidx.compose.ui.node.Owner
    public HapticFeedback getHapticFeedBack() {
        return this.hapticFeedBack;
    }

    /* renamed from: getLastMatrixRecalculationAnimationTime$ui_release, reason: from getter */
    public final long getLastMatrixRecalculationAnimationTime() {
        return this.lastMatrixRecalculationAnimationTime;
    }

    @Override // androidx.compose.ui.node.Owner
    public ModifierLocalManager getModifierLocalManager() {
        return this.modifierLocalManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public PointerIconService getPointerIconService() {
        return this.pointerIconService;
    }

    @Override // androidx.compose.ui.node.Owner
    public LayoutNode getRoot() {
        return this.root;
    }

    @Override // androidx.compose.ui.node.Owner
    public RootForTest getRootForTest() {
        return this.rootForTest;
    }

    @Override // androidx.compose.ui.node.RootForTest
    public SemanticsOwner getSemanticsOwner() {
        return this.semanticsOwner;
    }

    @Override // androidx.compose.ui.node.Owner
    public LayoutNodeDrawScope getSharedDrawScope() {
        return this.sharedDrawScope;
    }

    @Override // androidx.compose.ui.node.Owner
    public boolean getShowLayoutBounds() {
        return this.showLayoutBounds;
    }

    @Override // androidx.compose.ui.node.Owner
    public OwnerSnapshotObserver getSnapshotObserver() {
        return this.snapshotObserver;
    }

    @Override // androidx.compose.ui.node.Owner, androidx.compose.ui.node.RootForTest
    public TextInputService getTextInputService() {
        return this.textInputService;
    }

    @Override // androidx.compose.ui.node.Owner
    public TextToolbar getTextToolbar() {
        return this.textToolbar;
    }

    @Override // androidx.compose.ui.node.Owner
    public ViewConfiguration getViewConfiguration() {
        return this.viewConfiguration;
    }

    @Override // androidx.compose.ui.node.Owner
    public void onAttach(LayoutNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
    }

    public final void requestClearInvalidObservations() {
        this.observationClearRequested = true;
    }

    public final void setConfigurationChangeObserver(Function1<? super Configuration, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.configurationChangeObserver = function1;
    }

    public final void setLastMatrixRecalculationAnimationTime$ui_release(long j) {
        this.lastMatrixRecalculationAnimationTime = j;
    }

    @Override // androidx.compose.ui.node.Owner
    public void setShowLayoutBounds(boolean z) {
        this.showLayoutBounds = z;
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v13, types: [androidx.compose.ui.platform.AndroidComposeView$resendMotionEventRunnable$1] */
    public AndroidComposeView(Context context) {
        CalculateMatrixToWindowApi21 calculateMatrixToWindowApi21;
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.lastDownPointerPosition = Offset.INSTANCE.m1654getUnspecifiedF1C5BW0();
        this.superclassInitComplete = true;
        this.sharedDrawScope = new LayoutNodeDrawScope(null, 1, 0 == true ? 1 : 0);
        this.density = AndroidDensity_androidKt.Density(context);
        SemanticsModifierCore semanticsModifierCore = new SemanticsModifierCore(false, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeView$semanticsModifier$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver $receiver) {
                Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }
        }, null, 8, null);
        this.semanticsModifier = semanticsModifierCore;
        this.focusOwner = new FocusOwnerImpl(new Function1<Function0<? extends Unit>, Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeView$focusOwner$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Function0<? extends Unit> function0) {
                invoke2((Function0<Unit>) function0);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function0<Unit> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                this.this$0.registerOnEndApplyChangesListener(it);
            }
        });
        this._windowInfo = new WindowInfoImpl();
        Modifier modifierOnKeyEvent = KeyInputModifierKt.onKeyEvent(Modifier.INSTANCE, new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$keyInputModifier$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                return m3670invokeZmokQxo(keyEvent.m3134unboximpl());
            }

            /* renamed from: invoke-ZmokQxo, reason: not valid java name */
            public final Boolean m3670invokeZmokQxo(android.view.KeyEvent it) {
                Intrinsics.checkNotNullParameter(it, "it");
                FocusDirection focusDirectionMo3648getFocusDirectionP8AzH3I = this.this$0.mo3648getFocusDirectionP8AzH3I(it);
                if (focusDirectionMo3648getFocusDirectionP8AzH3I == null || !KeyEventType.m3138equalsimpl0(KeyEvent_androidKt.m3146getTypeZmokQxo(it), KeyEventType.INSTANCE.m3142getKeyDownCS__XNY())) {
                    return false;
                }
                return Boolean.valueOf(this.this$0.getFocusOwner().mo1583moveFocus3ESFkO8(focusDirectionMo3648getFocusDirectionP8AzH3I.getValue()));
            }
        });
        this.keyInputModifier = modifierOnKeyEvent;
        Modifier modifierOnRotaryScrollEvent = RotaryInputModifierKt.onRotaryScrollEvent(Modifier.INSTANCE, new Function1<RotaryScrollEvent, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$rotaryInputModifier$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(RotaryScrollEvent it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return false;
            }
        });
        this.rotaryInputModifier = modifierOnRotaryScrollEvent;
        this.canvasHolder = new CanvasHolder();
        LayoutNode layoutNode = new LayoutNode(false, 0, 3, null);
        layoutNode.setMeasurePolicy(RootMeasurePolicy.INSTANCE);
        layoutNode.setDensity(getDensity());
        layoutNode.setModifier(Modifier.INSTANCE.then(semanticsModifierCore).then(modifierOnRotaryScrollEvent).then(getFocusOwner().getModifier()).then(modifierOnKeyEvent));
        this.root = layoutNode;
        this.rootForTest = this;
        this.semanticsOwner = new SemanticsOwner(getRoot());
        AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = new AndroidComposeViewAccessibilityDelegateCompat(this);
        this.accessibilityDelegate = androidComposeViewAccessibilityDelegateCompat;
        this.autofillTree = new AutofillTree();
        this.dirtyLayers = new ArrayList();
        this.motionEventAdapter = new MotionEventAdapter();
        this.pointerInputEventProcessor = new PointerInputEventProcessor(getRoot());
        this.configurationChangeObserver = new Function1<Configuration, Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeView$configurationChangeObserver$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Configuration it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Configuration configuration) {
                invoke2(configuration);
                return Unit.INSTANCE;
            }
        };
        this._autofill = autofillSupported() ? new AndroidAutofill(this, getAutofillTree()) : null;
        this.clipboardManager = new AndroidClipboardManager(context);
        this.accessibilityManager = new AndroidAccessibilityManager(context);
        this.snapshotObserver = new OwnerSnapshotObserver(new AndroidComposeView$snapshotObserver$1(this));
        this.measureAndLayoutDelegate = new MeasureAndLayoutDelegate(getRoot());
        android.view.ViewConfiguration viewConfiguration = android.view.ViewConfiguration.get(context);
        Intrinsics.checkNotNullExpressionValue(viewConfiguration, "get(context)");
        this.viewConfiguration = new AndroidViewConfiguration(viewConfiguration);
        this.globalPosition = IntOffsetKt.IntOffset(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.tmpPositionArray = new int[]{0, 0};
        this.viewToWindowMatrix = Matrix.m2101constructorimpl$default(null, 1, null);
        this.windowToViewMatrix = Matrix.m2101constructorimpl$default(null, 1, null);
        this.lastMatrixRecalculationAnimationTime = -1L;
        this.windowPosition = Offset.INSTANCE.m1653getInfiniteF1C5BW0();
        this.isRenderNodeCompatible = true;
        this.viewTreeOwners = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                AndroidComposeView.globalLayoutListener$lambda$1(this.f$0);
            }
        };
        this.scrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() { // from class: androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda1
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public final void onScrollChanged() {
                AndroidComposeView.scrollChangedListener$lambda$2(this.f$0);
            }
        };
        this.touchModeChangeListener = new ViewTreeObserver.OnTouchModeChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda2
            @Override // android.view.ViewTreeObserver.OnTouchModeChangeListener
            public final void onTouchModeChanged(boolean z) {
                AndroidComposeView.touchModeChangeListener$lambda$3(this.f$0, z);
            }
        };
        AndroidComposeView androidComposeView = this;
        TextInputServiceAndroid textInputServiceAndroid = new TextInputServiceAndroid(androidComposeView);
        this.textInputServiceAndroid = textInputServiceAndroid;
        this.textInputService = AndroidComposeView_androidKt.getTextInputServiceFactory().invoke(textInputServiceAndroid);
        this.fontLoader = new AndroidFontResourceLoader(context);
        this.fontFamilyResolver = SnapshotStateKt.mutableStateOf(FontFamilyResolver_androidKt.createFontFamilyResolver(context), SnapshotStateKt.referentialEqualityPolicy());
        Configuration configuration = context.getResources().getConfiguration();
        Intrinsics.checkNotNullExpressionValue(configuration, "context.resources.configuration");
        this.currentFontWeightAdjustment = getFontWeightAdjustmentCompat(configuration);
        Configuration configuration2 = context.getResources().getConfiguration();
        Intrinsics.checkNotNullExpressionValue(configuration2, "context.resources.configuration");
        this.layoutDirection = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AndroidComposeView_androidKt.getLocaleLayoutDirection(configuration2), null, 2, null);
        this.hapticFeedBack = new PlatformHapticFeedback(androidComposeView);
        this._inputModeManager = new InputModeManagerImpl(isInTouchMode() ? InputMode.INSTANCE.m2543getTouchaOaMEAU() : InputMode.INSTANCE.m2542getKeyboardaOaMEAU(), new Function1<InputMode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$_inputModeManager$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(InputMode inputMode) {
                return m3669invokeiuPiT84(inputMode.getValue());
            }

            /* renamed from: invoke-iuPiT84, reason: not valid java name */
            public final Boolean m3669invokeiuPiT84(int i) {
                boolean zRequestFocusFromTouch;
                if (InputMode.m2538equalsimpl0(i, InputMode.INSTANCE.m2543getTouchaOaMEAU())) {
                    zRequestFocusFromTouch = this.this$0.isInTouchMode();
                } else {
                    zRequestFocusFromTouch = InputMode.m2538equalsimpl0(i, InputMode.INSTANCE.m2542getKeyboardaOaMEAU()) ? this.this$0.isInTouchMode() ? this.this$0.requestFocusFromTouch() : true : false;
                }
                return Boolean.valueOf(zRequestFocusFromTouch);
            }
        }, null);
        AndroidComposeView androidComposeView2 = this;
        this.modifierLocalManager = new ModifierLocalManager(androidComposeView2);
        this.textToolbar = new AndroidTextToolbar(androidComposeView);
        this.layerCache = new WeakCache<>();
        this.endApplyChangesListeners = new MutableVector<>(new Function0[16], 0);
        this.resendMotionEventRunnable = new Runnable() { // from class: androidx.compose.ui.platform.AndroidComposeView$resendMotionEventRunnable$1
            @Override // java.lang.Runnable
            public void run() {
                this.this$0.removeCallbacks(this);
                MotionEvent motionEvent = this.this$0.previousMotionEvent;
                if (motionEvent != null) {
                    boolean z = motionEvent.getToolType(0) == 3;
                    int actionMasked = motionEvent.getActionMasked();
                    if (z) {
                        if (actionMasked == 10 || actionMasked == 1) {
                            return;
                        }
                    } else if (actionMasked == 1) {
                        return;
                    }
                    int i = 7;
                    if (actionMasked != 7 && actionMasked != 9) {
                        i = 2;
                    }
                    AndroidComposeView androidComposeView3 = this.this$0;
                    androidComposeView3.sendSimulatedEvent(motionEvent, i, androidComposeView3.relayoutTime, false);
                }
            }
        };
        this.sendHoverExitEvent = new Runnable() { // from class: androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                AndroidComposeView.sendHoverExitEvent$lambda$5(this.f$0);
            }
        };
        this.resendMotionEventOnLayout = new Function0<Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeView$resendMotionEventOnLayout$1
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
                MotionEvent motionEvent = this.this$0.previousMotionEvent;
                if (motionEvent != null) {
                    int actionMasked = motionEvent.getActionMasked();
                    if (actionMasked == 7 || actionMasked == 9) {
                        this.this$0.relayoutTime = SystemClock.uptimeMillis();
                        AndroidComposeView androidComposeView3 = this.this$0;
                        androidComposeView3.post(androidComposeView3.resendMotionEventRunnable);
                    }
                }
            }
        };
        if (Build.VERSION.SDK_INT >= 29) {
            calculateMatrixToWindowApi21 = new CalculateMatrixToWindowApi29();
        } else {
            calculateMatrixToWindowApi21 = new CalculateMatrixToWindowApi21();
        }
        this.matrixToWindow = calculateMatrixToWindowApi21;
        setWillNotDraw(false);
        setFocusable(true);
        if (Build.VERSION.SDK_INT >= 26) {
            AndroidComposeViewVerificationHelperMethodsO.INSTANCE.focusable(androidComposeView, 1, false);
        }
        setFocusableInTouchMode(true);
        setClipChildren(false);
        ViewCompat.setAccessibilityDelegate(androidComposeView, androidComposeViewAccessibilityDelegateCompat);
        Function1<ViewRootForTest, Unit> onViewCreatedCallback = ViewRootForTest.INSTANCE.getOnViewCreatedCallback();
        if (onViewCreatedCallback != null) {
            onViewCreatedCallback.invoke(this);
        }
        getRoot().attach$ui_release(androidComposeView2);
        if (Build.VERSION.SDK_INT >= 29) {
            AndroidComposeViewForceDarkModeQ.INSTANCE.disallowForceDark(androidComposeView);
        }
        this.pointerIconService = new PointerIconService() { // from class: androidx.compose.ui.platform.AndroidComposeView$pointerIconService$1
            @Override // androidx.compose.ui.input.pointer.PointerIconService
            public PointerIcon getCurrent() {
                PointerIcon pointerIcon = this.this$0.desiredPointerIcon;
                return pointerIcon == null ? PointerIcon.INSTANCE.getDefault() : pointerIcon;
            }

            @Override // androidx.compose.ui.input.pointer.PointerIconService
            public void setCurrent(PointerIcon value) {
                Intrinsics.checkNotNullParameter(value, "value");
                this.this$0.desiredPointerIcon = value;
            }
        };
    }

    @Override // androidx.compose.ui.platform.ViewRootForTest
    public View getView() {
        return this;
    }

    @Override // androidx.compose.ui.node.Owner
    public WindowInfo getWindowInfo() {
        return this._windowInfo;
    }

    @Override // androidx.compose.ui.node.Owner
    public Autofill getAutofill() {
        return this._autofill;
    }

    public final AndroidViewsHandler getAndroidViewsHandler$ui_release() {
        if (this._androidViewsHandler == null) {
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            AndroidViewsHandler androidViewsHandler = new AndroidViewsHandler(context);
            this._androidViewsHandler = androidViewsHandler;
            addView(androidViewsHandler);
        }
        AndroidViewsHandler androidViewsHandler2 = this._androidViewsHandler;
        Intrinsics.checkNotNull(androidViewsHandler2);
        return androidViewsHandler2;
    }

    @Override // androidx.compose.ui.node.Owner
    public long getMeasureIteration() {
        return this.measureAndLayoutDelegate.getMeasureIteration();
    }

    @Override // androidx.compose.ui.platform.ViewRootForTest
    public boolean getHasPendingMeasureOrLayout() {
        return this.measureAndLayoutDelegate.getHasPendingMeasureOrLayout();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ViewTreeOwners getViewTreeOwners() {
        return (ViewTreeOwners) this.viewTreeOwners.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void globalLayoutListener$lambda$1(AndroidComposeView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.updatePositionCacheAndDispatch();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void scrollChangedListener$lambda$2(AndroidComposeView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.updatePositionCacheAndDispatch();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void touchModeChangeListener$lambda$3(AndroidComposeView this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0._inputModeManager.m2546setInputModeiuPiT84(z ? InputMode.INSTANCE.m2543getTouchaOaMEAU() : InputMode.INSTANCE.m2542getKeyboardaOaMEAU());
    }

    @Override // androidx.compose.ui.node.Owner
    public FontFamily.Resolver getFontFamilyResolver() {
        return (FontFamily.Resolver) this.fontFamilyResolver.getValue();
    }

    private final int getFontWeightAdjustmentCompat(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 31) {
            return configuration.fontWeightAdjustment;
        }
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View, android.view.ViewParent, androidx.compose.ui.node.Owner
    public LayoutDirection getLayoutDirection() {
        return (LayoutDirection) this.layoutDirection.getValue();
    }

    @Override // androidx.compose.ui.node.Owner
    public InputModeManager getInputModeManager() {
        return this._inputModeManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendHoverExitEvent$lambda$5(AndroidComposeView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.hoverExitReceived = false;
        MotionEvent motionEvent = this$0.previousMotionEvent;
        Intrinsics.checkNotNull(motionEvent);
        if (motionEvent.getActionMasked() != 10) {
            throw new IllegalStateException("The ACTION_HOVER_EXIT event was not cleared.".toString());
        }
        this$0.m3668sendMotionEvent8iAsVTc(motionEvent);
    }

    @Override // android.view.View
    public void getFocusedRect(Rect rect) {
        Unit unit;
        Intrinsics.checkNotNullParameter(rect, "rect");
        androidx.compose.ui.geometry.Rect focusRect = getFocusOwner().getFocusRect();
        if (focusRect != null) {
            rect.left = MathKt.roundToInt(focusRect.getLeft());
            rect.top = MathKt.roundToInt(focusRect.getTop());
            rect.right = MathKt.roundToInt(focusRect.getRight());
            rect.bottom = MathKt.roundToInt(focusRect.getBottom());
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            super.getFocusedRect(rect);
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onResume(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        setShowLayoutBounds(INSTANCE.getIsShowingLayoutBounds());
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        Log.d(FocusTag, "Owner FocusChanged(" + gainFocus + ')');
        if (gainFocus) {
            getFocusOwner().takeFocus();
        } else {
            getFocusOwner().releaseFocus();
        }
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        boolean isShowingLayoutBounds;
        this._windowInfo.setWindowFocused(hasWindowFocus);
        this.keyboardModifiersRequireUpdate = true;
        super.onWindowFocusChanged(hasWindowFocus);
        if (!hasWindowFocus || getShowLayoutBounds() == (isShowingLayoutBounds = INSTANCE.getIsShowingLayoutBounds())) {
            return;
        }
        setShowLayoutBounds(isShowingLayoutBounds);
        invalidateDescendants();
    }

    @Override // androidx.compose.ui.node.RootForTest
    /* renamed from: sendKeyEvent-ZmokQxo */
    public boolean mo3650sendKeyEventZmokQxo(android.view.KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(keyEvent, "keyEvent");
        return getFocusOwner().mo1584dispatchKeyEventZmokQxo(keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(android.view.KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (isFocused()) {
            this._windowInfo.m3734setKeyboardModifiers5xRPYO0(PointerKeyboardModifiers.m3340constructorimpl(event.getMetaState()));
            return mo3650sendKeyEventZmokQxo(KeyEvent.m3129constructorimpl(event));
        }
        return super.dispatchKeyEvent(event);
    }

    @Override // androidx.compose.ui.node.Owner
    public void onDetach(LayoutNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        this.measureAndLayoutDelegate.onNodeDetached(node);
        requestClearInvalidObservations();
    }

    @Override // androidx.compose.ui.node.Owner
    public void onEndApplyChanges() {
        if (this.observationClearRequested) {
            getSnapshotObserver().clearInvalidObservations$ui_release();
            this.observationClearRequested = false;
        }
        AndroidViewsHandler androidViewsHandler = this._androidViewsHandler;
        if (androidViewsHandler != null) {
            clearChildInvalidObservations(androidViewsHandler);
        }
        while (this.endApplyChangesListeners.isNotEmpty()) {
            int size = this.endApplyChangesListeners.getSize();
            for (int i = 0; i < size; i++) {
                Function0<Unit> function0 = this.endApplyChangesListeners.getContent()[i];
                this.endApplyChangesListeners.set(i, null);
                if (function0 != null) {
                    function0.invoke();
                }
            }
            this.endApplyChangesListeners.removeRange(0, size);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void registerOnEndApplyChangesListener(Function0<Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.endApplyChangesListeners.contains(listener)) {
            return;
        }
        this.endApplyChangesListeners.add(listener);
    }

    private final void clearChildInvalidObservations(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof AndroidComposeView) {
                ((AndroidComposeView) childAt).onEndApplyChanges();
            } else if (childAt instanceof ViewGroup) {
                clearChildInvalidObservations((ViewGroup) childAt);
            }
        }
    }

    public final void addAndroidView(AndroidViewHolder view, final LayoutNode layoutNode) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        getAndroidViewsHandler$ui_release().getHolderToLayoutNode().put(view, layoutNode);
        AndroidViewHolder androidViewHolder = view;
        getAndroidViewsHandler$ui_release().addView(androidViewHolder);
        getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().put(layoutNode, view);
        ViewCompat.setImportantForAccessibility(androidViewHolder, 1);
        ViewCompat.setAccessibilityDelegate(androidViewHolder, new AccessibilityDelegateCompat() { // from class: androidx.compose.ui.platform.AndroidComposeView.addAndroidView.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                Intrinsics.checkNotNullParameter(host, "host");
                Intrinsics.checkNotNullParameter(info, "info");
                super.onInitializeAccessibilityNodeInfo(host, info);
                SemanticsModifierNode outerSemantics = SemanticsNodeKt.getOuterSemantics(layoutNode);
                Intrinsics.checkNotNull(outerSemantics);
                SemanticsNode parent = new SemanticsNode(outerSemantics, false, null, 4, null).getParent();
                Intrinsics.checkNotNull(parent);
                int id = parent.getId();
                if (id == this.getSemanticsOwner().getUnmergedRootSemanticsNode().getId()) {
                    id = -1;
                }
                info.setParent(this, id);
            }
        });
    }

    public final void removeAndroidView(final AndroidViewHolder view) {
        Intrinsics.checkNotNullParameter(view, "view");
        registerOnEndApplyChangesListener(new Function0<Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeView.removeAndroidView.1
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
                AndroidComposeView.this.getAndroidViewsHandler$ui_release().removeViewInLayout(view);
                HashMap<LayoutNode, AndroidViewHolder> layoutNodeToHolder = AndroidComposeView.this.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder();
                TypeIntrinsics.asMutableMap(layoutNodeToHolder).remove(AndroidComposeView.this.getAndroidViewsHandler$ui_release().getHolderToLayoutNode().remove(view));
                ViewCompat.setImportantForAccessibility(view, 0);
            }
        });
    }

    public final void drawAndroidView(AndroidViewHolder view, Canvas canvas) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        getAndroidViewsHandler$ui_release().drawView(view, canvas);
    }

    static /* synthetic */ void scheduleMeasureAndLayout$default(AndroidComposeView androidComposeView, LayoutNode layoutNode, int i, Object obj) {
        if ((i & 1) != 0) {
            layoutNode = null;
        }
        androidComposeView.scheduleMeasureAndLayout(layoutNode);
    }

    private final void scheduleMeasureAndLayout(LayoutNode nodeToRemeasure) {
        if (isLayoutRequested() || !isAttachedToWindow()) {
            return;
        }
        if (nodeToRemeasure != null) {
            while (nodeToRemeasure != null && nodeToRemeasure.getMeasuredByParent() == LayoutNode.UsageByParent.InMeasureBlock && childSizeCanAffectParentSize(nodeToRemeasure)) {
                nodeToRemeasure = nodeToRemeasure.getParent$ui_release();
            }
            if (nodeToRemeasure == getRoot()) {
                requestLayout();
                return;
            }
        }
        if (getWidth() == 0 || getHeight() == 0) {
            requestLayout();
        } else {
            invalidate();
        }
    }

    private final boolean childSizeCanAffectParentSize(LayoutNode layoutNode) {
        LayoutNode parent$ui_release;
        return this.wasMeasuredWithMultipleConstraints || !((parent$ui_release = layoutNode.getParent$ui_release()) == null || parent$ui_release.getHasFixedInnerContentConstraints$ui_release());
    }

    @Override // androidx.compose.ui.node.Owner
    public void forceMeasureTheSubtree(LayoutNode layoutNode) {
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        this.measureAndLayoutDelegate.forceMeasureTheSubtree(layoutNode);
    }

    @Override // androidx.compose.ui.node.Owner
    public void onRequestMeasure(LayoutNode layoutNode, boolean affectsLookahead, boolean forceRequest) {
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        if (affectsLookahead) {
            if (this.measureAndLayoutDelegate.requestLookaheadRemeasure(layoutNode, forceRequest)) {
                scheduleMeasureAndLayout(layoutNode);
            }
        } else if (this.measureAndLayoutDelegate.requestRemeasure(layoutNode, forceRequest)) {
            scheduleMeasureAndLayout(layoutNode);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void onRequestRelayout(LayoutNode layoutNode, boolean affectsLookahead, boolean forceRequest) {
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        if (affectsLookahead) {
            if (this.measureAndLayoutDelegate.requestLookaheadRelayout(layoutNode, forceRequest)) {
                scheduleMeasureAndLayout$default(this, null, 1, null);
            }
        } else if (this.measureAndLayoutDelegate.requestRelayout(layoutNode, forceRequest)) {
            scheduleMeasureAndLayout$default(this, null, 1, null);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void requestOnPositionedCallback(LayoutNode layoutNode) {
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        this.measureAndLayoutDelegate.requestOnPositionedCallback(layoutNode);
        scheduleMeasureAndLayout$default(this, null, 1, null);
    }

    @Override // androidx.compose.ui.node.RootForTest
    public void measureAndLayoutForTest() {
        Owner.measureAndLayout$default(this, false, 1, null);
    }

    private final Pair<Integer, Integer> convertMeasureSpec(int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        int size = View.MeasureSpec.getSize(measureSpec);
        if (mode == Integer.MIN_VALUE) {
            return TuplesKt.to(0, Integer.valueOf(size));
        }
        if (mode == 0) {
            return TuplesKt.to(0, Integer.MAX_VALUE);
        }
        if (mode == 1073741824) {
            return TuplesKt.to(Integer.valueOf(size), Integer.valueOf(size));
        }
        throw new IllegalStateException();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        this.measureAndLayoutDelegate.measureAndLayout(this.resendMotionEventOnLayout);
        this.onMeasureConstraints = null;
        updatePositionCacheAndDispatch();
        if (this._androidViewsHandler != null) {
            getAndroidViewsHandler$ui_release().layout(0, 0, r - l, b - t);
        }
    }

    private final void updatePositionCacheAndDispatch() {
        getLocationOnScreen(this.tmpPositionArray);
        long j = this.globalPosition;
        int iM4500component1impl = IntOffset.m4500component1impl(j);
        int iM4501component2impl = IntOffset.m4501component2impl(j);
        int[] iArr = this.tmpPositionArray;
        boolean z = false;
        int i = iArr[0];
        if (iM4500component1impl != i || iM4501component2impl != iArr[1]) {
            this.globalPosition = IntOffsetKt.IntOffset(i, iArr[1]);
            if (iM4500component1impl != Integer.MAX_VALUE && iM4501component2impl != Integer.MAX_VALUE) {
                getRoot().getLayoutDelegate().getMeasurePassDelegate().notifyChildrenUsingCoordinatesWhilePlacing();
                z = true;
            }
        }
        this.measureAndLayoutDelegate.dispatchOnPositionedCallbacks(z);
    }

    @Override // androidx.compose.ui.node.Owner
    public OwnedLayer createLayer(Function1<? super androidx.compose.ui.graphics.Canvas, Unit> drawBlock, Function0<Unit> invalidateParentLayer) {
        ViewLayerContainer viewLayerContainer;
        Intrinsics.checkNotNullParameter(drawBlock, "drawBlock");
        Intrinsics.checkNotNullParameter(invalidateParentLayer, "invalidateParentLayer");
        OwnedLayer ownedLayerPop = this.layerCache.pop();
        if (ownedLayerPop != null) {
            ownedLayerPop.reuseLayer(drawBlock, invalidateParentLayer);
            return ownedLayerPop;
        }
        if (isHardwareAccelerated() && this.isRenderNodeCompatible) {
            try {
                return new RenderNodeLayer(this, drawBlock, invalidateParentLayer);
            } catch (Throwable unused) {
                this.isRenderNodeCompatible = false;
            }
        }
        if (this.viewLayersContainer == null) {
            if (!ViewLayer.INSTANCE.getHasRetrievedMethod()) {
                ViewLayer.INSTANCE.updateDisplayList(new View(getContext()));
            }
            if (ViewLayer.INSTANCE.getShouldUseDispatchDraw()) {
                Context context = getContext();
                Intrinsics.checkNotNullExpressionValue(context, "context");
                viewLayerContainer = new DrawChildContainer(context);
            } else {
                Context context2 = getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "context");
                viewLayerContainer = new ViewLayerContainer(context2);
            }
            this.viewLayersContainer = viewLayerContainer;
            addView(viewLayerContainer);
        }
        DrawChildContainer drawChildContainer = this.viewLayersContainer;
        Intrinsics.checkNotNull(drawChildContainer);
        return new ViewLayer(this, drawChildContainer, drawBlock, invalidateParentLayer);
    }

    public final boolean recycle$ui_release(OwnedLayer layer) {
        Intrinsics.checkNotNullParameter(layer, "layer");
        if (this.viewLayersContainer != null) {
            ViewLayer.INSTANCE.getShouldUseDispatchDraw();
        }
        this.layerCache.push(layer);
        return true;
    }

    @Override // androidx.compose.ui.node.Owner
    public void onSemanticsChange() {
        this.accessibilityDelegate.onSemanticsChange$ui_release();
    }

    @Override // androidx.compose.ui.node.Owner
    public void onLayoutChange(LayoutNode layoutNode) {
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        this.accessibilityDelegate.onLayoutChange$ui_release(layoutNode);
    }

    @Override // androidx.compose.ui.node.Owner
    public void registerOnLayoutCompletedListener(Owner.OnLayoutCompletedListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.measureAndLayoutDelegate.registerOnLayoutCompletedListener(listener);
        scheduleMeasureAndLayout$default(this, null, 1, null);
    }

    @Override // androidx.compose.ui.node.Owner
    /* renamed from: getFocusDirection-P8AzH3I */
    public FocusDirection mo3648getFocusDirectionP8AzH3I(android.view.KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(keyEvent, "keyEvent");
        long jM3145getKeyZmokQxo = KeyEvent_androidKt.m3145getKeyZmokQxo(keyEvent);
        if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo, Key.INSTANCE.m3073getTabEK5gGoQ())) {
            return FocusDirection.m1562boximpl(KeyEvent_androidKt.m3151isShiftPressedZmokQxo(keyEvent) ? FocusDirection.INSTANCE.m1580getPreviousdhqQ8s() : FocusDirection.INSTANCE.m1578getNextdhqQ8s());
        }
        if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo, Key.INSTANCE.m2914getDirectionRightEK5gGoQ())) {
            return FocusDirection.m1562boximpl(FocusDirection.INSTANCE.m1581getRightdhqQ8s());
        }
        if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo, Key.INSTANCE.m2913getDirectionLeftEK5gGoQ())) {
            return FocusDirection.m1562boximpl(FocusDirection.INSTANCE.m1577getLeftdhqQ8s());
        }
        if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo, Key.INSTANCE.m2915getDirectionUpEK5gGoQ())) {
            return FocusDirection.m1562boximpl(FocusDirection.INSTANCE.m1582getUpdhqQ8s());
        }
        if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo, Key.INSTANCE.m2910getDirectionDownEK5gGoQ())) {
            return FocusDirection.m1562boximpl(FocusDirection.INSTANCE.m1573getDowndhqQ8s());
        }
        if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo, Key.INSTANCE.m2909getDirectionCenterEK5gGoQ()) || Key.m2550equalsimpl0(jM3145getKeyZmokQxo, Key.INSTANCE.m2923getEnterEK5gGoQ()) || Key.m2550equalsimpl0(jM3145getKeyZmokQxo, Key.INSTANCE.m3015getNumPadEnterEK5gGoQ())) {
            return FocusDirection.m1562boximpl(FocusDirection.INSTANCE.m1574getEnterdhqQ8s());
        }
        if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo, Key.INSTANCE.m2852getBackEK5gGoQ()) || Key.m2550equalsimpl0(jM3145getKeyZmokQxo, Key.INSTANCE.m2926getEscapeEK5gGoQ())) {
            return FocusDirection.m1562boximpl(FocusDirection.INSTANCE.m1575getExitdhqQ8s());
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (!isAttachedToWindow()) {
            invalidateLayers(getRoot());
        }
        Owner.measureAndLayout$default(this, false, 1, null);
        this.isDrawingContent = true;
        CanvasHolder canvasHolder = this.canvasHolder;
        Canvas internalCanvas = canvasHolder.getAndroidCanvas().getInternalCanvas();
        canvasHolder.getAndroidCanvas().setInternalCanvas(canvas);
        getRoot().draw$ui_release(canvasHolder.getAndroidCanvas());
        canvasHolder.getAndroidCanvas().setInternalCanvas(internalCanvas);
        if (!this.dirtyLayers.isEmpty()) {
            int size = this.dirtyLayers.size();
            for (int i = 0; i < size; i++) {
                this.dirtyLayers.get(i).updateDisplayList();
            }
        }
        if (ViewLayer.INSTANCE.getShouldUseDispatchDraw()) {
            int iSave = canvas.save();
            canvas.clipRect(0.0f, 0.0f, 0.0f, 0.0f);
            super.dispatchDraw(canvas);
            canvas.restoreToCount(iSave);
        }
        this.dirtyLayers.clear();
        this.isDrawingContent = false;
        List<OwnedLayer> list = this.postponedDirtyLayers;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            this.dirtyLayers.addAll(list);
            list.clear();
        }
    }

    public final void notifyLayerIsDirty$ui_release(OwnedLayer layer, boolean isDirty) {
        Intrinsics.checkNotNullParameter(layer, "layer");
        if (!isDirty) {
            if (this.isDrawingContent) {
                return;
            }
            this.dirtyLayers.remove(layer);
            List<OwnedLayer> list = this.postponedDirtyLayers;
            if (list != null) {
                list.remove(layer);
                return;
            }
            return;
        }
        if (!this.isDrawingContent) {
            this.dirtyLayers.add(layer);
            return;
        }
        ArrayList arrayList = this.postponedDirtyLayers;
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.postponedDirtyLayers = arrayList;
        }
        arrayList.add(layer);
    }

    public final void setOnViewTreeOwnersAvailable(Function1<? super ViewTreeOwners, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        if (viewTreeOwners != null) {
            callback.invoke(viewTreeOwners);
        }
        if (isAttachedToWindow()) {
            return;
        }
        this.onViewTreeOwnersAvailable = callback;
    }

    public final Object boundsUpdatesEventLoop(Continuation<? super Unit> continuation) {
        Object objBoundsUpdatesEventLoop = this.accessibilityDelegate.boundsUpdatesEventLoop(continuation);
        return objBoundsUpdatesEventLoop == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objBoundsUpdatesEventLoop : Unit.INSTANCE;
    }

    private final void invalidateLayoutNodeMeasurement(LayoutNode node) {
        int i = 0;
        MeasureAndLayoutDelegate.requestRemeasure$default(this.measureAndLayoutDelegate, node, false, 2, null);
        MutableVector<LayoutNode> mutableVector = node.get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            LayoutNode[] content = mutableVector.getContent();
            do {
                invalidateLayoutNodeMeasurement(content[i]);
                i++;
            } while (i < size);
        }
    }

    private final void invalidateLayers(LayoutNode node) {
        node.invalidateLayers$ui_release();
        MutableVector<LayoutNode> mutableVector = node.get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            LayoutNode[] content = mutableVector.getContent();
            int i = 0;
            do {
                invalidateLayers(content[i]);
                i++;
            } while (i < size);
        }
    }

    @Override // androidx.compose.ui.platform.ViewRootForTest
    public void invalidateDescendants() {
        invalidateLayers(getRoot());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        LifecycleOwner lifecycleOwner;
        Lifecycle lifecycleRegistry;
        AndroidAutofill androidAutofill;
        super.onAttachedToWindow();
        invalidateLayoutNodeMeasurement(getRoot());
        invalidateLayers(getRoot());
        getSnapshotObserver().startObserving$ui_release();
        if (autofillSupported() && (androidAutofill = this._autofill) != null) {
            AutofillCallback.INSTANCE.register(androidAutofill);
        }
        AndroidComposeView androidComposeView = this;
        LifecycleOwner lifecycleOwner2 = ViewTreeLifecycleOwner.get(androidComposeView);
        SavedStateRegistryOwner savedStateRegistryOwner = ViewTreeSavedStateRegistryOwner.get(androidComposeView);
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        if (viewTreeOwners == null || (lifecycleOwner2 != null && savedStateRegistryOwner != null && (lifecycleOwner2 != viewTreeOwners.getLifecycleOwner() || savedStateRegistryOwner != viewTreeOwners.getLifecycleOwner()))) {
            if (lifecycleOwner2 == null) {
                throw new IllegalStateException("Composed into the View which doesn't propagate ViewTreeLifecycleOwner!");
            }
            if (savedStateRegistryOwner == null) {
                throw new IllegalStateException("Composed into the View which doesn't propagateViewTreeSavedStateRegistryOwner!");
            }
            if (viewTreeOwners != null && (lifecycleOwner = viewTreeOwners.getLifecycleOwner()) != null && (lifecycleRegistry = lifecycleOwner.getLifecycleRegistry()) != null) {
                lifecycleRegistry.removeObserver(this);
            }
            lifecycleOwner2.getLifecycleRegistry().addObserver(this);
            ViewTreeOwners viewTreeOwners2 = new ViewTreeOwners(lifecycleOwner2, savedStateRegistryOwner);
            setViewTreeOwners(viewTreeOwners2);
            Function1<? super ViewTreeOwners, Unit> function1 = this.onViewTreeOwnersAvailable;
            if (function1 != null) {
                function1.invoke(viewTreeOwners2);
            }
            this.onViewTreeOwnersAvailable = null;
        }
        this._inputModeManager.m2546setInputModeiuPiT84(isInTouchMode() ? InputMode.INSTANCE.m2543getTouchaOaMEAU() : InputMode.INSTANCE.m2542getKeyboardaOaMEAU());
        ViewTreeOwners viewTreeOwners3 = getViewTreeOwners();
        Intrinsics.checkNotNull(viewTreeOwners3);
        viewTreeOwners3.getLifecycleOwner().getLifecycleRegistry().addObserver(this);
        getViewTreeObserver().addOnGlobalLayoutListener(this.globalLayoutListener);
        getViewTreeObserver().addOnScrollChangedListener(this.scrollChangedListener);
        getViewTreeObserver().addOnTouchModeChangeListener(this.touchModeChangeListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        AndroidAutofill androidAutofill;
        LifecycleOwner lifecycleOwner;
        Lifecycle lifecycleRegistry;
        super.onDetachedFromWindow();
        getSnapshotObserver().stopObserving$ui_release();
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        if (viewTreeOwners != null && (lifecycleOwner = viewTreeOwners.getLifecycleOwner()) != null && (lifecycleRegistry = lifecycleOwner.getLifecycleRegistry()) != null) {
            lifecycleRegistry.removeObserver(this);
        }
        if (autofillSupported() && (androidAutofill = this._autofill) != null) {
            AutofillCallback.INSTANCE.unregister(androidAutofill);
        }
        getViewTreeObserver().removeOnGlobalLayoutListener(this.globalLayoutListener);
        getViewTreeObserver().removeOnScrollChangedListener(this.scrollChangedListener);
        getViewTreeObserver().removeOnTouchModeChangeListener(this.touchModeChangeListener);
    }

    @Override // android.view.View
    public void onProvideAutofillVirtualStructure(ViewStructure structure, int flags) {
        AndroidAutofill androidAutofill;
        if (!autofillSupported() || structure == null || (androidAutofill = this._autofill) == null) {
            return;
        }
        AndroidAutofill_androidKt.populateViewStructure(androidAutofill, structure);
    }

    @Override // android.view.View
    public void autofill(SparseArray<AutofillValue> values) {
        AndroidAutofill androidAutofill;
        Intrinsics.checkNotNullParameter(values, "values");
        if (!autofillSupported() || (androidAutofill = this._autofill) == null) {
            return;
        }
        AndroidAutofill_androidKt.performAutofill(androidAutofill, values);
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getActionMasked() == 8) {
            if (event.isFromSource(4194304)) {
                return handleRotaryEvent(event);
            }
            if (isBadMotionEvent(event) || !isAttachedToWindow()) {
                return super.dispatchGenericMotionEvent(event);
            }
            return ProcessResult.m3365getDispatchedToAPointerInputModifierimpl(m3667handleMotionEvent8iAsVTc(event));
        }
        return super.dispatchGenericMotionEvent(event);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "motionEvent");
        if (this.hoverExitReceived) {
            removeCallbacks(this.sendHoverExitEvent);
            MotionEvent motionEvent2 = this.previousMotionEvent;
            Intrinsics.checkNotNull(motionEvent2);
            if (motionEvent.getActionMasked() != 0 || hasChangedDevices(motionEvent, motionEvent2)) {
                this.sendHoverExitEvent.run();
            } else {
                this.hoverExitReceived = false;
            }
        }
        if (isBadMotionEvent(motionEvent) || !isAttachedToWindow()) {
            return false;
        }
        if (motionEvent.getActionMasked() == 2 && !isPositionChanged(motionEvent)) {
            return false;
        }
        int iM3667handleMotionEvent8iAsVTc = m3667handleMotionEvent8iAsVTc(motionEvent);
        if (ProcessResult.m3364getAnyMovementConsumedimpl(iM3667handleMotionEvent8iAsVTc)) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return ProcessResult.m3365getDispatchedToAPointerInputModifierimpl(iM3667handleMotionEvent8iAsVTc);
    }

    private final boolean handleRotaryEvent(MotionEvent event) {
        android.view.ViewConfiguration viewConfiguration = android.view.ViewConfiguration.get(getContext());
        float f = -event.getAxisValue(26);
        return getFocusOwner().dispatchRotaryEvent(new RotaryScrollEvent(ViewConfigurationCompat.getScaledVerticalScrollFactor(viewConfiguration, getContext()) * f, f * ViewConfigurationCompat.getScaledHorizontalScrollFactor(viewConfiguration, getContext()), event.getEventTime()));
    }

    /* renamed from: handleMotionEvent-8iAsVTc, reason: not valid java name */
    private final int m3667handleMotionEvent8iAsVTc(MotionEvent motionEvent) {
        removeCallbacks(this.resendMotionEventRunnable);
        try {
            recalculateWindowPosition(motionEvent);
            boolean z = true;
            this.forceUseMatrixCache = true;
            measureAndLayout(false);
            this.desiredPointerIcon = null;
            Trace.beginSection("AndroidOwner:onTouch");
            try {
                int actionMasked = motionEvent.getActionMasked();
                MotionEvent motionEvent2 = this.previousMotionEvent;
                boolean z2 = motionEvent2 != null && motionEvent2.getToolType(0) == 3;
                if (motionEvent2 != null && hasChangedDevices(motionEvent, motionEvent2)) {
                    if (isDevicePressEvent(motionEvent2)) {
                        this.pointerInputEventProcessor.processCancel();
                    } else if (motionEvent2.getActionMasked() != 10 && z2) {
                        sendSimulatedEvent$default(this, motionEvent2, 10, motionEvent2.getEventTime(), false, 8, null);
                    }
                }
                if (motionEvent.getToolType(0) != 3) {
                    z = false;
                }
                if (!z2 && z && actionMasked != 3 && actionMasked != 9 && isInBounds(motionEvent)) {
                    sendSimulatedEvent$default(this, motionEvent, 9, motionEvent.getEventTime(), false, 8, null);
                }
                if (motionEvent2 != null) {
                    motionEvent2.recycle();
                }
                this.previousMotionEvent = MotionEvent.obtainNoHistory(motionEvent);
                int iM3668sendMotionEvent8iAsVTc = m3668sendMotionEvent8iAsVTc(motionEvent);
                Trace.endSection();
                AndroidComposeViewVerificationHelperMethodsN.INSTANCE.setPointerIcon(this, this.desiredPointerIcon);
                return iM3668sendMotionEvent8iAsVTc;
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        } finally {
            this.forceUseMatrixCache = false;
        }
    }

    private final boolean hasChangedDevices(MotionEvent event, MotionEvent lastEvent) {
        return (lastEvent.getSource() == event.getSource() && lastEvent.getToolType(0) == event.getToolType(0)) ? false : true;
    }

    private final boolean isDevicePressEvent(MotionEvent event) {
        int actionMasked;
        return event.getButtonState() != 0 || (actionMasked = event.getActionMasked()) == 0 || actionMasked == 2 || actionMasked == 6;
    }

    /* renamed from: sendMotionEvent-8iAsVTc, reason: not valid java name */
    private final int m3668sendMotionEvent8iAsVTc(MotionEvent motionEvent) {
        PointerInputEventData pointerInputEventDataPrevious;
        if (this.keyboardModifiersRequireUpdate) {
            this.keyboardModifiersRequireUpdate = false;
            this._windowInfo.m3734setKeyboardModifiers5xRPYO0(PointerKeyboardModifiers.m3340constructorimpl(motionEvent.getMetaState()));
        }
        AndroidComposeView androidComposeView = this;
        PointerInputEvent pointerInputEventConvertToPointerInputEvent$ui_release = this.motionEventAdapter.convertToPointerInputEvent$ui_release(motionEvent, androidComposeView);
        if (pointerInputEventConvertToPointerInputEvent$ui_release != null) {
            List<PointerInputEventData> pointers = pointerInputEventConvertToPointerInputEvent$ui_release.getPointers();
            ListIterator<PointerInputEventData> listIterator = pointers.listIterator(pointers.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    pointerInputEventDataPrevious = null;
                    break;
                }
                pointerInputEventDataPrevious = listIterator.previous();
                if (pointerInputEventDataPrevious.getDown()) {
                    break;
                }
            }
            PointerInputEventData pointerInputEventData = pointerInputEventDataPrevious;
            if (pointerInputEventData != null) {
                this.lastDownPointerPosition = pointerInputEventData.m3292getPositionF1C5BW0();
            }
            int iM3297processBIzXfog = this.pointerInputEventProcessor.m3297processBIzXfog(pointerInputEventConvertToPointerInputEvent$ui_release, androidComposeView, isInBounds(motionEvent));
            int actionMasked = motionEvent.getActionMasked();
            if ((actionMasked != 0 && actionMasked != 5) || ProcessResult.m3365getDispatchedToAPointerInputModifierimpl(iM3297processBIzXfog)) {
                return iM3297processBIzXfog;
            }
            this.motionEventAdapter.endStream(motionEvent.getPointerId(motionEvent.getActionIndex()));
            return iM3297processBIzXfog;
        }
        this.pointerInputEventProcessor.processCancel();
        return PointerInputEventProcessorKt.ProcessResult(false, false);
    }

    static /* synthetic */ void sendSimulatedEvent$default(AndroidComposeView androidComposeView, MotionEvent motionEvent, int i, long j, boolean z, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            z = true;
        }
        androidComposeView.sendSimulatedEvent(motionEvent, i, j, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendSimulatedEvent(MotionEvent motionEvent, int action, long eventTime, boolean forceHover) {
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = -1;
        if (actionMasked != 1) {
            if (actionMasked == 6) {
                actionIndex = motionEvent.getActionIndex();
            }
        } else if (action != 9 && action != 10) {
            actionIndex = 0;
        }
        int pointerCount = motionEvent.getPointerCount() - (actionIndex >= 0 ? 1 : 0);
        if (pointerCount == 0) {
            return;
        }
        MotionEvent.PointerProperties[] pointerPropertiesArr = new MotionEvent.PointerProperties[pointerCount];
        for (int i = 0; i < pointerCount; i++) {
            pointerPropertiesArr[i] = new MotionEvent.PointerProperties();
        }
        MotionEvent.PointerCoords[] pointerCoordsArr = new MotionEvent.PointerCoords[pointerCount];
        for (int i2 = 0; i2 < pointerCount; i2++) {
            pointerCoordsArr[i2] = new MotionEvent.PointerCoords();
        }
        int i3 = 0;
        while (i3 < pointerCount) {
            int i4 = ((actionIndex < 0 || i3 < actionIndex) ? 0 : 1) + i3;
            motionEvent.getPointerProperties(i4, pointerPropertiesArr[i3]);
            MotionEvent.PointerCoords pointerCoords = pointerCoordsArr[i3];
            motionEvent.getPointerCoords(i4, pointerCoords);
            long jMo3358localToScreenMKHz9U = mo3358localToScreenMKHz9U(OffsetKt.Offset(pointerCoords.x, pointerCoords.y));
            pointerCoords.x = Offset.m1639getXimpl(jMo3358localToScreenMKHz9U);
            pointerCoords.y = Offset.m1640getYimpl(jMo3358localToScreenMKHz9U);
            i3++;
        }
        MotionEvent event = MotionEvent.obtain(motionEvent.getDownTime() == motionEvent.getEventTime() ? eventTime : motionEvent.getDownTime(), eventTime, action, pointerCount, pointerPropertiesArr, pointerCoordsArr, motionEvent.getMetaState(), forceHover ? 0 : motionEvent.getButtonState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags());
        MotionEventAdapter motionEventAdapter = this.motionEventAdapter;
        Intrinsics.checkNotNullExpressionValue(event, "event");
        AndroidComposeView androidComposeView = this;
        PointerInputEvent pointerInputEventConvertToPointerInputEvent$ui_release = motionEventAdapter.convertToPointerInputEvent$ui_release(event, androidComposeView);
        Intrinsics.checkNotNull(pointerInputEventConvertToPointerInputEvent$ui_release);
        this.pointerInputEventProcessor.m3297processBIzXfog(pointerInputEventConvertToPointerInputEvent$ui_release, androidComposeView, true);
        event.recycle();
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int direction) {
        return this.accessibilityDelegate.m3672canScroll0AR0LA0$ui_release(false, direction, this.lastDownPointerPosition);
    }

    @Override // android.view.View
    public boolean canScrollVertically(int direction) {
        return this.accessibilityDelegate.m3672canScroll0AR0LA0$ui_release(true, direction, this.lastDownPointerPosition);
    }

    private final boolean isInBounds(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        return 0.0f <= x && x <= ((float) getWidth()) && 0.0f <= y && y <= ((float) getHeight());
    }

    @Override // androidx.compose.ui.input.pointer.PositionCalculator
    /* renamed from: localToScreen-MK-Hz9U */
    public long mo3358localToScreenMKHz9U(long localPosition) {
        recalculateWindowPosition();
        long jM2107mapMKHz9U = Matrix.m2107mapMKHz9U(this.viewToWindowMatrix, localPosition);
        return OffsetKt.Offset(Offset.m1639getXimpl(jM2107mapMKHz9U) + Offset.m1639getXimpl(this.windowPosition), Offset.m1640getYimpl(jM2107mapMKHz9U) + Offset.m1640getYimpl(this.windowPosition));
    }

    @Override // androidx.compose.ui.input.pointer.PositionCalculator
    /* renamed from: screenToLocal-MK-Hz9U */
    public long mo3359screenToLocalMKHz9U(long positionOnScreen) {
        recalculateWindowPosition();
        return Matrix.m2107mapMKHz9U(this.windowToViewMatrix, OffsetKt.Offset(Offset.m1639getXimpl(positionOnScreen) - Offset.m1639getXimpl(this.windowPosition), Offset.m1640getYimpl(positionOnScreen) - Offset.m1640getYimpl(this.windowPosition)));
    }

    private final void recalculateWindowPosition() {
        if (this.forceUseMatrixCache) {
            return;
        }
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        if (jCurrentAnimationTimeMillis != this.lastMatrixRecalculationAnimationTime) {
            this.lastMatrixRecalculationAnimationTime = jCurrentAnimationTimeMillis;
            recalculateWindowViewTransforms();
            ViewParent parent = getParent();
            AndroidComposeView androidComposeView = this;
            while (parent instanceof ViewGroup) {
                androidComposeView = (View) parent;
                parent = ((ViewGroup) androidComposeView).getParent();
            }
            androidComposeView.getLocationOnScreen(this.tmpPositionArray);
            int[] iArr = this.tmpPositionArray;
            float f = iArr[0];
            float f2 = iArr[1];
            androidComposeView.getLocationInWindow(iArr);
            int[] iArr2 = this.tmpPositionArray;
            this.windowPosition = OffsetKt.Offset(f - iArr2[0], f2 - iArr2[1]);
        }
    }

    private final void recalculateWindowPosition(MotionEvent motionEvent) {
        this.lastMatrixRecalculationAnimationTime = AnimationUtils.currentAnimationTimeMillis();
        recalculateWindowViewTransforms();
        long jM2107mapMKHz9U = Matrix.m2107mapMKHz9U(this.viewToWindowMatrix, OffsetKt.Offset(motionEvent.getX(), motionEvent.getY()));
        this.windowPosition = OffsetKt.Offset(motionEvent.getRawX() - Offset.m1639getXimpl(jM2107mapMKHz9U), motionEvent.getRawY() - Offset.m1640getYimpl(jM2107mapMKHz9U));
    }

    private final void recalculateWindowViewTransforms() {
        this.matrixToWindow.mo3677calculateMatrixToWindowEL8BTi8(this, this.viewToWindowMatrix);
        InvertMatrixKt.m3701invertToJiSxe2E(this.viewToWindowMatrix, this.windowToViewMatrix);
    }

    @Override // android.view.View
    public boolean onCheckIsTextEditor() {
        return this.textInputServiceAndroid.getEditorHasFocus();
    }

    @Override // android.view.View
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        Intrinsics.checkNotNullParameter(outAttrs, "outAttrs");
        return this.textInputServiceAndroid.createInputConnection(outAttrs);
    }

    @Override // androidx.compose.ui.node.Owner
    /* renamed from: calculateLocalPosition-MK-Hz9U */
    public long mo3646calculateLocalPositionMKHz9U(long positionInWindow) {
        recalculateWindowPosition();
        return Matrix.m2107mapMKHz9U(this.windowToViewMatrix, positionInWindow);
    }

    @Override // androidx.compose.ui.node.Owner
    /* renamed from: calculatePositionInWindow-MK-Hz9U */
    public long mo3647calculatePositionInWindowMKHz9U(long localPosition) {
        recalculateWindowPosition();
        return Matrix.m2107mapMKHz9U(this.viewToWindowMatrix, localPosition);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        this.density = AndroidDensity_androidKt.Density(context);
        if (getFontWeightAdjustmentCompat(newConfig) != this.currentFontWeightAdjustment) {
            this.currentFontWeightAdjustment = getFontWeightAdjustmentCompat(newConfig);
            Context context2 = getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "context");
            setFontFamilyResolver(FontFamilyResolver_androidKt.createFontFamilyResolver(context2));
        }
        this.configurationChangeObserver.invoke(newConfig);
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int layoutDirection) {
        if (this.superclassInitComplete) {
            LayoutDirection layoutDirectionLayoutDirectionFromInt = AndroidComposeView_androidKt.layoutDirectionFromInt(layoutDirection);
            setLayoutDirection(layoutDirectionLayoutDirectionFromInt);
            getFocusOwner().setLayoutDirection(layoutDirectionLayoutDirectionFromInt);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchHoverEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (this.hoverExitReceived) {
            removeCallbacks(this.sendHoverExitEvent);
            this.sendHoverExitEvent.run();
        }
        if (isBadMotionEvent(event) || !isAttachedToWindow()) {
            return false;
        }
        if (event.isFromSource(4098) && event.getToolType(0) == 1) {
            return this.accessibilityDelegate.dispatchHoverEvent(event);
        }
        int actionMasked = event.getActionMasked();
        if (actionMasked != 7) {
            if (actionMasked == 10 && isInBounds(event)) {
                if (event.getToolType(0) != 3) {
                    MotionEvent motionEvent = this.previousMotionEvent;
                    if (motionEvent != null) {
                        motionEvent.recycle();
                    }
                    this.previousMotionEvent = MotionEvent.obtainNoHistory(event);
                    this.hoverExitReceived = true;
                    post(this.sendHoverExitEvent);
                    return false;
                }
                if (event.getButtonState() != 0) {
                    return false;
                }
            }
        } else if (!isPositionChanged(event)) {
            return false;
        }
        return ProcessResult.m3365getDispatchedToAPointerInputModifierimpl(m3667handleMotionEvent8iAsVTc(event));
    }

    private final boolean isBadMotionEvent(MotionEvent event) {
        float x = event.getX();
        if (!Float.isInfinite(x) && !Float.isNaN(x)) {
            float y = event.getY();
            if (!Float.isInfinite(y) && !Float.isNaN(y)) {
                float rawX = event.getRawX();
                if (!Float.isInfinite(rawX) && !Float.isNaN(rawX)) {
                    float rawY = event.getRawY();
                    if (!Float.isInfinite(rawY) && !Float.isNaN(rawY)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private final boolean isPositionChanged(MotionEvent event) {
        MotionEvent motionEvent;
        return (event.getPointerCount() == 1 && (motionEvent = this.previousMotionEvent) != null && event.getRawX() == motionEvent.getRawX() && event.getRawY() == motionEvent.getRawY()) ? false : true;
    }

    private final View findViewByAccessibilityIdRootedAtCurrentView(int accessibilityId, View currentView) throws NoSuchMethodException, SecurityException {
        if (Build.VERSION.SDK_INT >= 29) {
            return null;
        }
        Method declaredMethod = View.class.getDeclaredMethod("getAccessibilityViewId", new Class[0]);
        declaredMethod.setAccessible(true);
        if (Intrinsics.areEqual(declaredMethod.invoke(currentView, new Object[0]), Integer.valueOf(accessibilityId))) {
            return currentView;
        }
        if (!(currentView instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) currentView;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            Intrinsics.checkNotNullExpressionValue(childAt, "currentView.getChildAt(i)");
            View viewFindViewByAccessibilityIdRootedAtCurrentView = findViewByAccessibilityIdRootedAtCurrentView(accessibilityId, childAt);
            if (viewFindViewByAccessibilityIdRootedAtCurrentView != null) {
                return viewFindViewByAccessibilityIdRootedAtCurrentView;
            }
        }
        return null;
    }

    public final View findViewByAccessibilityIdTraversal(int accessibilityId) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        View viewFindViewByAccessibilityIdRootedAtCurrentView = null;
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                Method declaredMethod = View.class.getDeclaredMethod("findViewByAccessibilityIdTraversal", Integer.TYPE);
                declaredMethod.setAccessible(true);
                Object objInvoke = declaredMethod.invoke(this, Integer.valueOf(accessibilityId));
                if (objInvoke instanceof View) {
                    viewFindViewByAccessibilityIdRootedAtCurrentView = (View) objInvoke;
                }
            } else {
                viewFindViewByAccessibilityIdRootedAtCurrentView = findViewByAccessibilityIdRootedAtCurrentView(accessibilityId, this);
            }
        } catch (NoSuchMethodException unused) {
        }
        return viewFindViewByAccessibilityIdRootedAtCurrentView;
    }

    @Override // androidx.compose.ui.platform.ViewRootForTest
    public boolean isLifecycleInResumedState() {
        LifecycleOwner lifecycleOwner;
        Lifecycle lifecycleRegistry;
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        return ((viewTreeOwners == null || (lifecycleOwner = viewTreeOwners.getLifecycleOwner()) == null || (lifecycleRegistry = lifecycleOwner.getLifecycleRegistry()) == null) ? null : lifecycleRegistry.getState()) == Lifecycle.State.RESUMED;
    }

    /* compiled from: AndroidComposeView.android.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeView$Companion;", "", "()V", "FocusTag", "", "MaximumLayerCacheSize", "", "getBooleanMethod", "Ljava/lang/reflect/Method;", "systemPropertiesClass", "Ljava/lang/Class;", "getIsShowingLayoutBounds", "", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean getIsShowingLayoutBounds() {
            try {
                if (AndroidComposeView.systemPropertiesClass == null) {
                    AndroidComposeView.systemPropertiesClass = Class.forName("android.os.SystemProperties");
                    Class cls = AndroidComposeView.systemPropertiesClass;
                    AndroidComposeView.getBooleanMethod = cls != null ? cls.getDeclaredMethod("getBoolean", String.class, Boolean.TYPE) : null;
                }
                Method method = AndroidComposeView.getBooleanMethod;
                Object objInvoke = method != null ? method.invoke(null, "debug.layout", false) : null;
                Boolean bool = objInvoke instanceof Boolean ? (Boolean) objInvoke : null;
                if (bool != null) {
                    return bool.booleanValue();
                }
                return false;
            } catch (Exception unused) {
                return false;
            }
        }
    }

    /* compiled from: AndroidComposeView.android.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeView$ViewTreeOwners;", "", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "savedStateRegistryOwner", "Landroidx/savedstate/SavedStateRegistryOwner;", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/savedstate/SavedStateRegistryOwner;)V", "getLifecycleOwner", "()Landroidx/lifecycle/LifecycleOwner;", "getSavedStateRegistryOwner", "()Landroidx/savedstate/SavedStateRegistryOwner;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ViewTreeOwners {
        public static final int $stable = 8;
        private final LifecycleOwner lifecycleOwner;
        private final SavedStateRegistryOwner savedStateRegistryOwner;

        public final LifecycleOwner getLifecycleOwner() {
            return this.lifecycleOwner;
        }

        public final SavedStateRegistryOwner getSavedStateRegistryOwner() {
            return this.savedStateRegistryOwner;
        }

        public ViewTreeOwners(LifecycleOwner lifecycleOwner, SavedStateRegistryOwner savedStateRegistryOwner) {
            Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
            Intrinsics.checkNotNullParameter(savedStateRegistryOwner, "savedStateRegistryOwner");
            this.lifecycleOwner = lifecycleOwner;
            this.savedStateRegistryOwner = savedStateRegistryOwner;
        }
    }

    private final void setViewTreeOwners(ViewTreeOwners viewTreeOwners) {
        this.viewTreeOwners.setValue(viewTreeOwners);
    }

    private void setFontFamilyResolver(FontFamily.Resolver resolver) {
        this.fontFamilyResolver.setValue(resolver);
    }

    private void setLayoutDirection(LayoutDirection layoutDirection) {
        this.layoutDirection.setValue(layoutDirection);
    }

    @Override // androidx.compose.ui.node.Owner
    public void measureAndLayout(boolean sendPointerUpdate) {
        Function0<Unit> function0;
        Trace.beginSection("AndroidOwner:measureAndLayout");
        if (sendPointerUpdate) {
            try {
                function0 = this.resendMotionEventOnLayout;
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        } else {
            function0 = null;
        }
        if (this.measureAndLayoutDelegate.measureAndLayout(function0)) {
            requestLayout();
        }
        MeasureAndLayoutDelegate.dispatchOnPositionedCallbacks$default(this.measureAndLayoutDelegate, false, 1, null);
        Unit unit = Unit.INSTANCE;
        Trace.endSection();
    }

    @Override // androidx.compose.ui.node.Owner
    /* renamed from: measureAndLayout-0kLqBqw */
    public void mo3649measureAndLayout0kLqBqw(LayoutNode layoutNode, long constraints) {
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        Trace.beginSection("AndroidOwner:measureAndLayout");
        try {
            this.measureAndLayoutDelegate.m3554measureAndLayout0kLqBqw(layoutNode, constraints);
            MeasureAndLayoutDelegate.dispatchOnPositionedCallbacks$default(this.measureAndLayoutDelegate, false, 1, null);
            Unit unit = Unit.INSTANCE;
        } finally {
            Trace.endSection();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Trace.beginSection("AndroidOwner:onMeasure");
        try {
            if (!isAttachedToWindow()) {
                invalidateLayoutNodeMeasurement(getRoot());
            }
            Pair<Integer, Integer> pairConvertMeasureSpec = convertMeasureSpec(widthMeasureSpec);
            int iIntValue = pairConvertMeasureSpec.component1().intValue();
            int iIntValue2 = pairConvertMeasureSpec.component2().intValue();
            Pair<Integer, Integer> pairConvertMeasureSpec2 = convertMeasureSpec(heightMeasureSpec);
            long jConstraints = ConstraintsKt.Constraints(iIntValue, iIntValue2, pairConvertMeasureSpec2.component1().intValue(), pairConvertMeasureSpec2.component2().intValue());
            Constraints constraints = this.onMeasureConstraints;
            if (constraints == null) {
                this.onMeasureConstraints = Constraints.m4334boximpl(jConstraints);
                this.wasMeasuredWithMultipleConstraints = false;
            } else if (constraints == null || !Constraints.m4339equalsimpl0(constraints.getValue(), jConstraints)) {
                this.wasMeasuredWithMultipleConstraints = true;
            }
            this.measureAndLayoutDelegate.m3555updateRootConstraintsBRTryo0(jConstraints);
            this.measureAndLayoutDelegate.measureOnly();
            setMeasuredDimension(getRoot().getWidth(), getRoot().getHeight());
            if (this._androidViewsHandler != null) {
                getAndroidViewsHandler$ui_release().measure(View.MeasureSpec.makeMeasureSpec(getRoot().getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getRoot().getHeight(), 1073741824));
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            Trace.endSection();
        }
    }
}
