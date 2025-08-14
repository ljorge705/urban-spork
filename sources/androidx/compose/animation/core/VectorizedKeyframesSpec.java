package androidx.compose.animation.core;

import androidx.compose.animation.core.AnimationVector;
import androidx.exifinterface.media.ExifInterface;
import io.sentry.Session;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VectorizedAnimationSpec.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B7\u0012\u001e\u0010\u0004\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u00070\u0005\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006¢\u0006\u0002\u0010\u000bJ-\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0018J-\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0018J\u0015\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u001dR\u0014\u0010\n\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\t\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR&\u0010\u0004\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00028\u0000X\u0082.¢\u0006\u0004\n\u0002\u0010\u0010R\u0010\u0010\u0011\u001a\u00028\u0000X\u0082.¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006\u001e"}, d2 = {"Landroidx/compose/animation/core/VectorizedKeyframesSpec;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/animation/core/VectorizedDurationBasedAnimationSpec;", "keyframes", "", "", "Lkotlin/Pair;", "Landroidx/compose/animation/core/Easing;", "durationMillis", "delayMillis", "(Ljava/util/Map;II)V", "getDelayMillis", "()I", "getDurationMillis", "valueVector", "Landroidx/compose/animation/core/AnimationVector;", "velocityVector", "getValueFromNanos", "playTimeNanos", "", "initialValue", "targetValue", "initialVelocity", "(JLandroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;)Landroidx/compose/animation/core/AnimationVector;", "getVelocityFromNanos", Session.JsonKeys.INIT, "", "value", "(Landroidx/compose/animation/core/AnimationVector;)V", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class VectorizedKeyframesSpec<V extends AnimationVector> implements VectorizedDurationBasedAnimationSpec<V> {
    public static final int $stable = 8;
    private final int delayMillis;
    private final int durationMillis;
    private final Map<Integer, Pair<V, Easing>> keyframes;
    private V valueVector;
    private V velocityVector;

    @Override // androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec
    public int getDelayMillis() {
        return this.delayMillis;
    }

    @Override // androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec
    public int getDurationMillis() {
        return this.durationMillis;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public VectorizedKeyframesSpec(Map<Integer, ? extends Pair<? extends V, ? extends Easing>> keyframes, int i, int i2) {
        Intrinsics.checkNotNullParameter(keyframes, "keyframes");
        this.keyframes = keyframes;
        this.durationMillis = i;
        this.delayMillis = i2;
    }

    public /* synthetic */ VectorizedKeyframesSpec(Map map, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, i, (i3 & 4) != 0 ? 0 : i2);
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public V getValueFromNanos(long playTimeNanos, V initialValue, V targetValue, V initialVelocity) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        Intrinsics.checkNotNullParameter(targetValue, "targetValue");
        Intrinsics.checkNotNullParameter(initialVelocity, "initialVelocity");
        int iClampPlayTime = (int) VectorizedAnimationSpecKt.clampPlayTime(this, playTimeNanos / 1000000);
        if (this.keyframes.containsKey(Integer.valueOf(iClampPlayTime))) {
            return (V) ((Pair) MapsKt.getValue(this.keyframes, Integer.valueOf(iClampPlayTime))).getFirst();
        }
        if (iClampPlayTime >= getDurationMillis()) {
            return targetValue;
        }
        if (iClampPlayTime <= 0) {
            return initialValue;
        }
        int durationMillis = getDurationMillis();
        Easing linearEasing = EasingKt.getLinearEasing();
        int i = 0;
        V first = initialValue;
        int i2 = 0;
        for (Map.Entry<Integer, Pair<V, Easing>> entry : this.keyframes.entrySet()) {
            int iIntValue = entry.getKey().intValue();
            Pair<V, Easing> value = entry.getValue();
            if (iClampPlayTime > iIntValue && iIntValue >= i2) {
                first = value.getFirst();
                linearEasing = value.getSecond();
                i2 = iIntValue;
            } else if (iClampPlayTime < iIntValue && iIntValue <= durationMillis) {
                targetValue = value.getFirst();
                durationMillis = iIntValue;
            }
        }
        float fTransform = linearEasing.transform((iClampPlayTime - i2) / (durationMillis - i2));
        init(initialValue);
        int size = first.getSize();
        while (true) {
            V v = null;
            if (i >= size) {
                break;
            }
            V v2 = this.valueVector;
            if (v2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("valueVector");
            } else {
                v = v2;
            }
            v.set$animation_core_release(i, VectorConvertersKt.lerp(first.get$animation_core_release(i), targetValue.get$animation_core_release(i), fTransform));
            i++;
        }
        V v3 = this.valueVector;
        if (v3 != null) {
            return v3;
        }
        Intrinsics.throwUninitializedPropertyAccessException("valueVector");
        return null;
    }

    private final void init(V value) {
        if (this.valueVector == null) {
            this.valueVector = (V) AnimationVectorsKt.newInstance(value);
            this.velocityVector = (V) AnimationVectorsKt.newInstance(value);
        }
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public V getVelocityFromNanos(long playTimeNanos, V initialValue, V targetValue, V initialVelocity) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        Intrinsics.checkNotNullParameter(targetValue, "targetValue");
        Intrinsics.checkNotNullParameter(initialVelocity, "initialVelocity");
        long jClampPlayTime = VectorizedAnimationSpecKt.clampPlayTime(this, playTimeNanos / 1000000);
        if (jClampPlayTime <= 0) {
            return initialVelocity;
        }
        VectorizedKeyframesSpec<V> vectorizedKeyframesSpec = this;
        AnimationVector valueFromMillis = VectorizedAnimationSpecKt.getValueFromMillis(vectorizedKeyframesSpec, jClampPlayTime - 1, initialValue, targetValue, initialVelocity);
        AnimationVector valueFromMillis2 = VectorizedAnimationSpecKt.getValueFromMillis(vectorizedKeyframesSpec, jClampPlayTime, initialValue, targetValue, initialVelocity);
        init(initialValue);
        int size = valueFromMillis.getSize();
        int i = 0;
        while (true) {
            V v = null;
            if (i >= size) {
                break;
            }
            V v2 = this.velocityVector;
            if (v2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("velocityVector");
            } else {
                v = v2;
            }
            v.set$animation_core_release(i, (valueFromMillis.get$animation_core_release(i) - valueFromMillis2.get$animation_core_release(i)) * 1000.0f);
            i++;
        }
        V v3 = this.velocityVector;
        if (v3 != null) {
            return v3;
        }
        Intrinsics.throwUninitializedPropertyAccessException("velocityVector");
        return null;
    }
}
