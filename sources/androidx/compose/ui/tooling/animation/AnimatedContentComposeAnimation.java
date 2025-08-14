package androidx.compose.ui.tooling.animation;

import androidx.compose.animation.core.Transition;
import androidx.compose.animation.tooling.ComposeAnimation;
import androidx.compose.animation.tooling.ComposeAnimationType;
import androidx.exifinterface.media.ExifInterface;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnimatedContentComposeAnimation.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u0016*\u0004\b\u0000\u0010\u00012\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u0016B-\b\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\t\u001a\u0004\u0018\u00010\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimatedContentComposeAnimation;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/animation/tooling/ComposeAnimation;", "Landroidx/compose/ui/tooling/animation/TransitionBasedAnimation;", "animationObject", "Landroidx/compose/animation/core/Transition;", "states", "", "", "label", "", "(Landroidx/compose/animation/core/Transition;Ljava/util/Set;Ljava/lang/String;)V", "getAnimationObject", "()Landroidx/compose/animation/core/Transition;", "getLabel", "()Ljava/lang/String;", "getStates", "()Ljava/util/Set;", "type", "Landroidx/compose/animation/tooling/ComposeAnimationType;", "getType", "()Landroidx/compose/animation/tooling/ComposeAnimationType;", "Companion", "ui-tooling_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AnimatedContentComposeAnimation<T> implements ComposeAnimation, TransitionBasedAnimation<T> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static boolean apiAvailable;
    private final Transition<T> animationObject;
    private final String label;
    private final Set<Object> states;
    private final ComposeAnimationType type;

    public /* synthetic */ AnimatedContentComposeAnimation(Transition transition, Set set, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(transition, set, str);
    }

    @Override // androidx.compose.ui.tooling.animation.TransitionBasedAnimation
    /* renamed from: getAnimationObject, reason: merged with bridge method [inline-methods] */
    public Transition<T> m4317getAnimationObject() {
        return this.animationObject;
    }

    public String getLabel() {
        return this.label;
    }

    public Set<Object> getStates() {
        return this.states;
    }

    public ComposeAnimationType getType() {
        return this.type;
    }

    private AnimatedContentComposeAnimation(Transition<T> transition, Set<? extends Object> set, String str) {
        this.animationObject = transition;
        this.states = set;
        this.label = str;
        this.type = ComposeAnimationType.ANIMATED_CONTENT;
    }

    /* compiled from: AnimatedContentComposeAnimation.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004J\u0014\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\f*\u0006\u0012\u0002\b\u00030\rR\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimatedContentComposeAnimation$Companion;", "", "()V", "<set-?>", "", "apiAvailable", "getApiAvailable", "()Z", "testOverrideAvailability", "", "override", "parseAnimatedContent", "Landroidx/compose/ui/tooling/animation/AnimatedContentComposeAnimation;", "Landroidx/compose/animation/core/Transition;", "ui-tooling_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean getApiAvailable() {
            return AnimatedContentComposeAnimation.apiAvailable;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0028  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final androidx.compose.ui.tooling.animation.AnimatedContentComposeAnimation<?> parseAnimatedContent(androidx.compose.animation.core.Transition<?> r6) {
            /*
                r5 = this;
                java.lang.String r0 = "<this>"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                boolean r0 = r5.getApiAvailable()
                r1 = 0
                if (r0 != 0) goto Ld
                return r1
            Ld:
                java.lang.Object r0 = r6.getCurrentState()
                if (r0 == 0) goto L44
                java.lang.Class r2 = r0.getClass()
                java.lang.Object[] r2 = r2.getEnumConstants()
                if (r2 == 0) goto L28
                java.lang.String r3 = "enumConstants"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
                java.util.Set r2 = kotlin.collections.ArraysKt.toSet(r2)
                if (r2 != 0) goto L2c
            L28:
                java.util.Set r2 = kotlin.collections.SetsKt.setOf(r0)
            L2c:
                androidx.compose.ui.tooling.animation.AnimatedContentComposeAnimation r3 = new androidx.compose.ui.tooling.animation.AnimatedContentComposeAnimation
                java.lang.String r4 = r6.getLabel()
                if (r4 != 0) goto L40
                java.lang.Class r0 = r0.getClass()
                kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
                java.lang.String r4 = r0.getSimpleName()
            L40:
                r3.<init>(r6, r2, r4, r1)
                r1 = r3
            L44:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.tooling.animation.AnimatedContentComposeAnimation.Companion.parseAnimatedContent(androidx.compose.animation.core.Transition):androidx.compose.ui.tooling.animation.AnimatedContentComposeAnimation");
        }

        public final void testOverrideAvailability(boolean override) {
            AnimatedContentComposeAnimation.apiAvailable = override;
        }
    }

    static {
        ComposeAnimationType[] composeAnimationTypeArrValues = ComposeAnimationType.values();
        int length = composeAnimationTypeArrValues.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            if (Intrinsics.areEqual(composeAnimationTypeArrValues[i].name(), "ANIMATED_CONTENT")) {
                z = true;
                break;
            }
            i++;
        }
        apiAvailable = z;
    }
}
