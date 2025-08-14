package com.onfido.android.sdk.capture.internal.util.logging;

import android.os.Build;
import android.util.Log;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.ClassUtils;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/logging/Timber;", "", "()V", "DebugTree", "Forest", "Tree", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Timber {

    /* renamed from: Forest, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ArrayList<Tree> trees = new ArrayList<>();
    private static volatile Tree[] treeArray = new Tree[0];

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\rH\u0014J,\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014R\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00058PX\u0090\u0004¢\u0006\f\u0012\u0004\b\b\u0010\u0002\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/logging/Timber$DebugTree;", "Lcom/onfido/android/sdk/capture/internal/util/logging/Timber$Tree;", "()V", "fqcnIgnore", "", "", "kotlin.jvm.PlatformType", "tag", "getTag$onfido_capture_sdk_core_release$annotations", "getTag$onfido_capture_sdk_core_release", "()Ljava/lang/String;", "createStackElementTag", "element", "Ljava/lang/StackTraceElement;", OnfidoLogMapper.LOG_EVENT_TYPE, "", "priority", "", "message", "t", "", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static class DebugTree extends Tree {
        private static final int MAX_LOG_LENGTH = 4000;
        private static final int MAX_TAG_LENGTH = 23;
        private final List<String> fqcnIgnore = CollectionsKt.listOf((Object[]) new String[]{Timber.class.getName(), Companion.class.getName(), Tree.class.getName(), DebugTree.class.getName()});
        private static final Pattern ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$");

        public static /* synthetic */ void getTag$onfido_capture_sdk_core_release$annotations() {
        }

        protected String createStackElementTag(StackTraceElement element) {
            Intrinsics.checkNotNullParameter(element, "element");
            String className = element.getClassName();
            Intrinsics.checkNotNullExpressionValue(className, "getClassName(...)");
            String strSubstringAfterLast$default = StringsKt.substringAfterLast$default(className, ClassUtils.PACKAGE_SEPARATOR_CHAR, (String) null, 2, (Object) null);
            Matcher matcher = ANONYMOUS_CLASS.matcher(strSubstringAfterLast$default);
            if (matcher.find()) {
                strSubstringAfterLast$default = matcher.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(strSubstringAfterLast$default, "replaceAll(...)");
            }
            if (strSubstringAfterLast$default.length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                return strSubstringAfterLast$default;
            }
            String strSubstring = strSubstringAfterLast$default.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return strSubstring;
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        public String getTag$onfido_capture_sdk_core_release() {
            String tag$onfido_capture_sdk_core_release = super.getTag$onfido_capture_sdk_core_release();
            if (tag$onfido_capture_sdk_core_release != null) {
                return tag$onfido_capture_sdk_core_release;
            }
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "getStackTrace(...)");
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (!this.fqcnIgnore.contains(stackTraceElement.getClassName())) {
                    return createStackElementTag(stackTraceElement);
                }
            }
            throw new NoSuchElementException("Array contains no element matching the predicate.");
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        protected void log(int priority, String tag, String message, Throwable t) {
            int iMin;
            Intrinsics.checkNotNullParameter(message, "message");
            if (message.length() < MAX_LOG_LENGTH) {
                if (priority == 7) {
                    Log.wtf(tag, message);
                    return;
                } else {
                    Log.println(priority, tag, message);
                    return;
                }
            }
            int length = message.length();
            int i = 0;
            while (i < length) {
                int iIndexOf$default = StringsKt.indexOf$default((CharSequence) message, '\n', i, false, 4, (Object) null);
                if (iIndexOf$default == -1) {
                    iIndexOf$default = length;
                }
                while (true) {
                    iMin = Math.min(iIndexOf$default, i + MAX_LOG_LENGTH);
                    String strSubstring = message.substring(i, iMin);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                    if (priority == 7) {
                        Log.wtf(tag, strSubstring);
                    } else {
                        Log.println(priority, tag, strSubstring);
                    }
                    if (iMin >= iIndexOf$default) {
                        break;
                    } else {
                        i = iMin;
                    }
                }
                i = iMin + 1;
            }
        }
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000f\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u000b\u001a\u00020\u0001H\u0097\bJ1\u0010\f\u001a\u00020\r2\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0016\u0010\u0010\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u0004\"\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010\u0012J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0017J;\u0010\f\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0016\u0010\u0010\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u0004\"\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010\u0015J1\u0010\u0016\u001a\u00020\r2\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0016\u0010\u0010\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u0004\"\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010\u0012J\u0012\u0010\u0016\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0017J;\u0010\u0016\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0016\u0010\u0010\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u0004\"\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010\u0015J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0018H\u0007J1\u0010\u0019\u001a\u00020\r2\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0016\u0010\u0010\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u0004\"\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010\u0012J\u0012\u0010\u0019\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0017J;\u0010\u0019\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0016\u0010\u0010\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u0004\"\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010\u0015J9\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u00072\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0016\u0010\u0010\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u0004\"\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010\u001cJ,\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u00072\b\u0010\u001d\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\u001a\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0017JC\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0016\u0010\u0010\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u0004\"\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u0001H\u0007J!\u0010\u001f\u001a\u00020\r2\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0004\"\u00020\u0001H\u0007¢\u0006\u0002\u0010!J\u0010\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u000fH\u0007J\u0010\u0010\"\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u0001H\u0007J\b\u0010#\u001a\u00020\rH\u0007J1\u0010$\u001a\u00020\r2\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0016\u0010\u0010\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u0004\"\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010\u0012J\u0012\u0010$\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0017J;\u0010$\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0016\u0010\u0010\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u0004\"\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010\u0015J1\u0010%\u001a\u00020\r2\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0016\u0010\u0010\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u0004\"\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010\u0012J\u0012\u0010%\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0017J;\u0010%\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0016\u0010\u0010\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u0004\"\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010\u0015J1\u0010&\u001a\u00020\r2\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0016\u0010\u0010\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u0004\"\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010\u0012J\u0012\u0010&\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0017J;\u0010&\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0016\u0010\u0010\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u0004\"\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0002\u0010\u0015R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u00078G¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/logging/Timber$Forest;", "Lcom/onfido/android/sdk/capture/internal/util/logging/Timber$Tree;", "()V", "treeArray", "", "[Lcom/onfido/android/sdk/capture/internal/util/logging/Timber$Tree;", "treeCount", "", "()I", "trees", "Ljava/util/ArrayList;", "asTree", "d", "", "message", "", "args", "", "(Ljava/lang/String;[Ljava/lang/Object;)V", "t", "", "(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V", "e", "forest", "", "i", OnfidoLogMapper.LOG_EVENT_TYPE, "priority", "(ILjava/lang/String;[Ljava/lang/Object;)V", "tag", "(ILjava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V", "plant", "tree", "([Lcom/onfido/android/sdk/capture/internal/util/logging/Timber$Tree;)V", "uproot", "uprootAll", CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, Constants.INAPP_WINDOW, "wtf", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.onfido.android.sdk.capture.internal.util.logging.Timber$Forest, reason: from kotlin metadata */
    public static final class Companion extends Tree {
        private Companion() {
        }

        @JvmStatic
        public Tree asTree() {
            return this;
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void d(String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            for (Tree tree : Timber.treeArray) {
                tree.d(message, Arrays.copyOf(args, args.length));
            }
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void e(String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            for (Tree tree : Timber.treeArray) {
                tree.e(message, Arrays.copyOf(args, args.length));
            }
        }

        @JvmStatic
        public final List<Tree> forest() {
            List<Tree> listUnmodifiableList;
            synchronized (Timber.trees) {
                listUnmodifiableList = Collections.unmodifiableList(CollectionsKt.toList(Timber.trees));
                Intrinsics.checkNotNullExpressionValue(listUnmodifiableList, "unmodifiableList(...)");
            }
            return listUnmodifiableList;
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void i(String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            for (Tree tree : Timber.treeArray) {
                tree.i(message, Arrays.copyOf(args, args.length));
            }
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        protected void log(int priority, String tag, String message, Throwable t) {
            Intrinsics.checkNotNullParameter(message, "message");
            throw new AssertionError();
        }

        @JvmStatic
        public final void plant(Tree tree) {
            Intrinsics.checkNotNullParameter(tree, "tree");
            if (tree == this) {
                throw new IllegalArgumentException("Cannot plant Timber into itself.".toString());
            }
            synchronized (Timber.trees) {
                Timber.trees.add(tree);
                Timber.treeArray = (Tree[]) Timber.trees.toArray(new Tree[0]);
                Unit unit = Unit.INSTANCE;
            }
        }

        @JvmStatic
        public final Tree tag(String tag) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            for (Tree tree : Timber.treeArray) {
                if (tree.getExplicitTag() != null) {
                    tree.getExplicitTag().set(tag);
                }
            }
            return this;
        }

        @JvmStatic
        public final int treeCount() {
            return Timber.treeArray.length;
        }

        @JvmStatic
        public final void uproot(Tree tree) {
            Intrinsics.checkNotNullParameter(tree, "tree");
            synchronized (Timber.trees) {
                if (!Timber.trees.remove(tree)) {
                    throw new IllegalArgumentException(("Cannot uproot tree which is not planted: " + tree).toString());
                }
                Timber.treeArray = (Tree[]) Timber.trees.toArray(new Tree[0]);
                Unit unit = Unit.INSTANCE;
            }
        }

        @JvmStatic
        public final void uprootAll() {
            synchronized (Timber.trees) {
                Timber.trees.clear();
                Timber.treeArray = new Tree[0];
                Unit unit = Unit.INSTANCE;
            }
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void v(String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            for (Tree tree : Timber.treeArray) {
                tree.v(message, Arrays.copyOf(args, args.length));
            }
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void w(String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            for (Tree tree : Timber.treeArray) {
                tree.w(message, Arrays.copyOf(args, args.length));
            }
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void wtf(String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            for (Tree tree : Timber.treeArray) {
                tree.wtf(message, Arrays.copyOf(args, args.length));
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void d(Throwable t) {
            for (Tree tree : Timber.treeArray) {
                tree.d(t);
            }
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void e(Throwable t) {
            for (Tree tree : Timber.treeArray) {
                tree.e(t);
            }
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void i(Throwable t) {
            for (Tree tree : Timber.treeArray) {
                tree.i(t);
            }
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void log(int priority, String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            for (Tree tree : Timber.treeArray) {
                tree.log(priority, message, Arrays.copyOf(args, args.length));
            }
        }

        @JvmStatic
        public final void plant(Tree... trees) {
            Intrinsics.checkNotNullParameter(trees, "trees");
            for (Tree tree : trees) {
                if (tree == null) {
                    throw new IllegalArgumentException("trees contained null".toString());
                }
                if (tree == this) {
                    throw new IllegalArgumentException("Cannot plant Timber into itself.".toString());
                }
            }
            synchronized (Timber.trees) {
                Collections.addAll(Timber.trees, Arrays.copyOf(trees, trees.length));
                Timber.treeArray = (Tree[]) Timber.trees.toArray(new Tree[0]);
                Unit unit = Unit.INSTANCE;
            }
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void v(Throwable t) {
            for (Tree tree : Timber.treeArray) {
                tree.v(t);
            }
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void w(Throwable t) {
            for (Tree tree : Timber.treeArray) {
                tree.w(t);
            }
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void wtf(Throwable t) {
            for (Tree tree : Timber.treeArray) {
                tree.wtf(t);
            }
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void d(Throwable t, String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            for (Tree tree : Timber.treeArray) {
                tree.d(t, message, Arrays.copyOf(args, args.length));
            }
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void e(Throwable t, String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            for (Tree tree : Timber.treeArray) {
                tree.e(t, message, Arrays.copyOf(args, args.length));
            }
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void i(Throwable t, String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            for (Tree tree : Timber.treeArray) {
                tree.i(t, message, Arrays.copyOf(args, args.length));
            }
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void log(int priority, Throwable t) {
            for (Tree tree : Timber.treeArray) {
                tree.log(priority, t);
            }
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void v(Throwable t, String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            for (Tree tree : Timber.treeArray) {
                tree.v(t, message, Arrays.copyOf(args, args.length));
            }
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void w(Throwable t, String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            for (Tree tree : Timber.treeArray) {
                tree.w(t, message, Arrays.copyOf(args, args.length));
            }
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void wtf(Throwable t, String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            for (Tree tree : Timber.treeArray) {
                tree.wtf(t, message, Arrays.copyOf(args, args.length));
            }
        }

        @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
        @JvmStatic
        public void log(int priority, Throwable t, String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            for (Tree tree : Timber.treeArray) {
                tree.log(priority, t, message, Arrays.copyOf(args, args.length));
            }
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J/\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000f\"\u0004\u0018\u00010\u0001H\u0016¢\u0006\u0002\u0010\u0010J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J9\u0010\u000b\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000f\"\u0004\u0018\u00010\u0001H\u0016¢\u0006\u0002\u0010\u0013J/\u0010\u0014\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000f\"\u0004\u0018\u00010\u0001H\u0016¢\u0006\u0002\u0010\u0010J\u0012\u0010\u0014\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J9\u0010\u0014\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000f\"\u0004\u0018\u00010\u0001H\u0016¢\u0006\u0002\u0010\u0013J'\u0010\u0015\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0010\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000fH\u0014¢\u0006\u0002\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J/\u0010\u0018\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000f\"\u0004\u0018\u00010\u0001H\u0016¢\u0006\u0002\u0010\u0010J\u0012\u0010\u0018\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J9\u0010\u0018\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000f\"\u0004\u0018\u00010\u0001H\u0016¢\u0006\u0002\u0010\u0013J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0015J\u001a\u0010\u0019\u001a\u00020\u001a2\b\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001b\u001a\u00020\u001cH\u0014J7\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000f\"\u0004\u0018\u00010\u0001H\u0016¢\u0006\u0002\u0010\u001eJ,\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\r\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H$J\u001a\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016JA\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000f\"\u0004\u0018\u00010\u0001H\u0016¢\u0006\u0002\u0010\u001fJA\u0010 \u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000f\"\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0002\u0010\u001fJ/\u0010!\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000f\"\u0004\u0018\u00010\u0001H\u0016¢\u0006\u0002\u0010\u0010J\u0012\u0010!\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J9\u0010!\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000f\"\u0004\u0018\u00010\u0001H\u0016¢\u0006\u0002\u0010\u0013J/\u0010\"\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000f\"\u0004\u0018\u00010\u0001H\u0016¢\u0006\u0002\u0010\u0010J\u0012\u0010\"\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J9\u0010\"\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000f\"\u0004\u0018\u00010\u0001H\u0016¢\u0006\u0002\u0010\u0013J/\u0010#\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000f\"\u0004\u0018\u00010\u0001H\u0016¢\u0006\u0002\u0010\u0010J\u0012\u0010#\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J9\u0010#\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000f\"\u0004\u0018\u00010\u0001H\u0016¢\u0006\u0002\u0010\u0013R\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048@X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u0004\u0018\u00010\u00058PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006$"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/logging/Timber$Tree;", "", "()V", "explicitTag", "Ljava/lang/ThreadLocal;", "", "getExplicitTag$onfido_capture_sdk_core_release", "()Ljava/lang/ThreadLocal;", "tag", "getTag$onfido_capture_sdk_core_release", "()Ljava/lang/String;", "d", "", "message", "args", "", "(Ljava/lang/String;[Ljava/lang/Object;)V", "t", "", "(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V", "e", "formatMessage", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "getStackTraceString", "i", "isLoggable", "", "priority", "", OnfidoLogMapper.LOG_EVENT_TYPE, "(ILjava/lang/String;[Ljava/lang/Object;)V", "(ILjava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V", "prepareLog", CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, Constants.INAPP_WINDOW, "wtf", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class Tree {
        private final ThreadLocal<String> explicitTag = new ThreadLocal<>();

        private final String getStackTraceString(Throwable t) {
            StringWriter stringWriter = new StringWriter(256);
            PrintWriter printWriter = new PrintWriter((Writer) stringWriter, false);
            t.printStackTrace(printWriter);
            printWriter.flush();
            String string = stringWriter.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }

        private final void prepareLog(int priority, Throwable t, String message, Object... args) {
            String tag$onfido_capture_sdk_core_release = getTag$onfido_capture_sdk_core_release();
            if (isLoggable(tag$onfido_capture_sdk_core_release, priority)) {
                if (message != null && message.length() != 0) {
                    if (!(args.length == 0)) {
                        message = formatMessage(message, args);
                    }
                    if (t != null) {
                        message = message + '\n' + getStackTraceString(t);
                    }
                } else if (t == null) {
                    return;
                } else {
                    message = getStackTraceString(t);
                }
                log(priority, tag$onfido_capture_sdk_core_release, message, t);
            }
        }

        public void d(String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            prepareLog(3, null, message, Arrays.copyOf(args, args.length));
        }

        public void e(String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            prepareLog(6, null, message, Arrays.copyOf(args, args.length));
        }

        protected String formatMessage(String message, Object[] args) {
            Intrinsics.checkNotNullParameter(message, "message");
            Intrinsics.checkNotNullParameter(args, "args");
            Object[] objArrCopyOf = Arrays.copyOf(args, args.length);
            String str = String.format(message, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            return str;
        }

        /* renamed from: getExplicitTag$onfido_capture_sdk_core_release, reason: from getter */
        public final /* synthetic */ ThreadLocal getExplicitTag() {
            return this.explicitTag;
        }

        public /* synthetic */ String getTag$onfido_capture_sdk_core_release() {
            String str = this.explicitTag.get();
            if (str != null) {
                this.explicitTag.remove();
            }
            return str;
        }

        public void i(String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            prepareLog(4, null, message, Arrays.copyOf(args, args.length));
        }

        @Deprecated(message = "Use isLoggable(String, int)", replaceWith = @ReplaceWith(expression = "this.isLoggable(null, priority)", imports = {}))
        protected boolean isLoggable(int priority) {
            return true;
        }

        protected abstract void log(int priority, String tag, String message, Throwable t);

        public void log(int priority, String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            prepareLog(priority, null, message, Arrays.copyOf(args, args.length));
        }

        public void v(String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            prepareLog(2, null, message, Arrays.copyOf(args, args.length));
        }

        public void w(String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            prepareLog(5, null, message, Arrays.copyOf(args, args.length));
        }

        public void wtf(String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            prepareLog(7, null, message, Arrays.copyOf(args, args.length));
        }

        public void d(Throwable t) {
            prepareLog(3, t, null, new Object[0]);
        }

        public void e(Throwable t) {
            prepareLog(6, t, null, new Object[0]);
        }

        public void i(Throwable t) {
            prepareLog(4, t, null, new Object[0]);
        }

        protected boolean isLoggable(String tag, int priority) {
            return isLoggable(priority);
        }

        public void log(int priority, Throwable t) {
            prepareLog(priority, t, null, new Object[0]);
        }

        public void v(Throwable t) {
            prepareLog(2, t, null, new Object[0]);
        }

        public void w(Throwable t) {
            prepareLog(5, t, null, new Object[0]);
        }

        public void wtf(Throwable t) {
            prepareLog(7, t, null, new Object[0]);
        }

        public void d(Throwable t, String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            prepareLog(3, t, message, Arrays.copyOf(args, args.length));
        }

        public void e(Throwable t, String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            prepareLog(6, t, message, Arrays.copyOf(args, args.length));
        }

        public void i(Throwable t, String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            prepareLog(4, t, message, Arrays.copyOf(args, args.length));
        }

        public void log(int priority, Throwable t, String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            prepareLog(priority, t, message, Arrays.copyOf(args, args.length));
        }

        public void v(Throwable t, String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            prepareLog(2, t, message, Arrays.copyOf(args, args.length));
        }

        public void w(Throwable t, String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            prepareLog(5, t, message, Arrays.copyOf(args, args.length));
        }

        public void wtf(Throwable t, String message, Object... args) {
            Intrinsics.checkNotNullParameter(args, "args");
            prepareLog(7, t, message, Arrays.copyOf(args, args.length));
        }
    }

    private Timber() {
        throw new AssertionError();
    }

    @JvmStatic
    public static Tree asTree() {
        return INSTANCE.asTree();
    }

    @JvmStatic
    public static void d(String str, Object... objArr) {
        INSTANCE.d(str, objArr);
    }

    @JvmStatic
    public static void e(String str, Object... objArr) {
        INSTANCE.e(str, objArr);
    }

    @JvmStatic
    public static final List<Tree> forest() {
        return INSTANCE.forest();
    }

    @JvmStatic
    public static void i(String str, Object... objArr) {
        INSTANCE.i(str, objArr);
    }

    @JvmStatic
    public static void log(int i, String str, Object... objArr) {
        INSTANCE.log(i, str, objArr);
    }

    @JvmStatic
    public static final void plant(Tree tree) {
        INSTANCE.plant(tree);
    }

    @JvmStatic
    public static final Tree tag(String str) {
        return INSTANCE.tag(str);
    }

    @JvmStatic
    public static final int treeCount() {
        return INSTANCE.treeCount();
    }

    @JvmStatic
    public static final void uproot(Tree tree) {
        INSTANCE.uproot(tree);
    }

    @JvmStatic
    public static final void uprootAll() {
        INSTANCE.uprootAll();
    }

    @JvmStatic
    public static void v(String str, Object... objArr) {
        INSTANCE.v(str, objArr);
    }

    @JvmStatic
    public static void w(String str, Object... objArr) {
        INSTANCE.w(str, objArr);
    }

    @JvmStatic
    public static void wtf(String str, Object... objArr) {
        INSTANCE.wtf(str, objArr);
    }

    @JvmStatic
    public static void d(Throwable th) {
        INSTANCE.d(th);
    }

    @JvmStatic
    public static void e(Throwable th) {
        INSTANCE.e(th);
    }

    @JvmStatic
    public static void i(Throwable th) {
        INSTANCE.i(th);
    }

    @JvmStatic
    public static void log(int i, Throwable th) {
        INSTANCE.log(i, th);
    }

    @JvmStatic
    public static final void plant(Tree... treeArr) {
        INSTANCE.plant(treeArr);
    }

    @JvmStatic
    public static void v(Throwable th) {
        INSTANCE.v(th);
    }

    @JvmStatic
    public static void w(Throwable th) {
        INSTANCE.w(th);
    }

    @JvmStatic
    public static void wtf(Throwable th) {
        INSTANCE.wtf(th);
    }

    @JvmStatic
    public static void d(Throwable th, String str, Object... objArr) {
        INSTANCE.d(th, str, objArr);
    }

    @JvmStatic
    public static void e(Throwable th, String str, Object... objArr) {
        INSTANCE.e(th, str, objArr);
    }

    @JvmStatic
    public static void i(Throwable th, String str, Object... objArr) {
        INSTANCE.i(th, str, objArr);
    }

    @JvmStatic
    public static void log(int i, Throwable th, String str, Object... objArr) {
        INSTANCE.log(i, th, str, objArr);
    }

    @JvmStatic
    public static void v(Throwable th, String str, Object... objArr) {
        INSTANCE.v(th, str, objArr);
    }

    @JvmStatic
    public static void w(Throwable th, String str, Object... objArr) {
        INSTANCE.w(th, str, objArr);
    }

    @JvmStatic
    public static void wtf(Throwable th, String str, Object... objArr) {
        INSTANCE.wtf(th, str, objArr);
    }
}
