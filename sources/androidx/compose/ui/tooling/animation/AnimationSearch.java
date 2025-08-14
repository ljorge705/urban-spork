package androidx.compose.ui.tooling.animation;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector;
import androidx.compose.animation.core.DecayAnimation;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.TargetBasedAnimation;
import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.tooling.PreviewUtilsKt;
import androidx.compose.ui.tooling.data.CallGroup;
import androidx.compose.ui.tooling.data.Group;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClasses;

/* compiled from: AnimationSearch.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0000\u0018\u00002\u00020\u0001:\f!\"#$%&'()*+,B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0002J\u0014\u0010\u001a\u001a\u00020\u00062\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0018J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0011H\u0002J\u0016\u0010\u0014\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00120\u0011H\u0002J\u0006\u0010\u001f\u001a\u00020\u0006J\u0016\u0010 \u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00120\u0018H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch;", "", "clock", "Lkotlin/Function0;", "Landroidx/compose/ui/tooling/animation/PreviewAnimationClock;", "onSeek", "", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "animatedContentSearch", "Landroidx/compose/ui/tooling/animation/AnimationSearch$AnimatedContentSearch;", "animatedVisibilitySearch", "Landroidx/compose/ui/tooling/animation/AnimationSearch$AnimatedVisibilitySearch;", "hasAnimations", "", "getHasAnimations", "()Z", "setToSearch", "", "Landroidx/compose/ui/tooling/animation/AnimationSearch$Search;", "setToTrack", "supportedSearch", "transitionSearch", "Landroidx/compose/ui/tooling/animation/AnimationSearch$TransitionSearch;", "animateXAsStateSearch", "", "Landroidx/compose/ui/tooling/animation/AnimationSearch$AnimateXAsStateSearch;", "findAll", "slotTrees", "Landroidx/compose/ui/tooling/data/Group;", "infiniteTransitionSearch", "Landroidx/compose/ui/tooling/animation/AnimationSearch$InfiniteTransitionSearch;", "trackAll", "unsupportedSearch", "AnimateContentSizeSearch", "AnimateXAsStateSearch", "AnimateXAsStateSearchInfo", "AnimatedContentSearch", "AnimatedVisibilitySearch", "DecaySearch", "InfiniteTransitionSearch", "InfiniteTransitionSearchInfo", "RememberSearch", "Search", "TargetBasedSearch", "TransitionSearch", "ui-tooling_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AnimationSearch {
    private final AnimatedContentSearch animatedContentSearch;
    private final AnimatedVisibilitySearch animatedVisibilitySearch;
    private final Function0<PreviewAnimationClock> clock;
    private final Function0<Unit> onSeek;
    private final Set<Search<? extends Object>> setToSearch;
    private final Set<Search<? extends Object>> setToTrack;
    private final Set<Search<? extends Object>> supportedSearch;
    private final TransitionSearch transitionSearch;

    /* JADX WARN: Multi-variable type inference failed */
    public AnimationSearch(Function0<? extends PreviewAnimationClock> clock, Function0<Unit> onSeek) {
        Intrinsics.checkNotNullParameter(clock, "clock");
        Intrinsics.checkNotNullParameter(onSeek, "onSeek");
        this.clock = clock;
        this.onSeek = onSeek;
        this.transitionSearch = new TransitionSearch(new Function1<Transition<?>, Unit>() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch$transitionSearch$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Transition<?> transition) {
                invoke2(transition);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Transition<?> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ((PreviewAnimationClock) this.this$0.clock.invoke()).trackTransition(it);
            }
        });
        AnimatedContentSearch animatedContentSearch = new AnimatedContentSearch(new Function1<Transition<?>, Unit>() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch$animatedContentSearch$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Transition<?> transition) {
                invoke2(transition);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Transition<?> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ((PreviewAnimationClock) this.this$0.clock.invoke()).trackAnimatedContent(it);
            }
        });
        this.animatedContentSearch = animatedContentSearch;
        this.animatedVisibilitySearch = new AnimatedVisibilitySearch(new Function1<Transition<?>, Unit>() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch$animatedVisibilitySearch$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Transition<?> transition) {
                invoke2(transition);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Transition<?> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ((PreviewAnimationClock) this.this$0.clock.invoke()).trackAnimatedVisibility(it, this.this$0.onSeek);
            }
        });
        Set<Search<? extends Object>> setSupportedSearch = supportedSearch();
        this.supportedSearch = setSupportedSearch;
        Set<Search<? extends Object>> setPlus = SetsKt.plus((Set) setSupportedSearch, (Iterable) unsupportedSearch());
        this.setToTrack = setPlus;
        this.setToSearch = SetsKt.plus((Set) setPlus, (Iterable) SetsKt.setOf(animatedContentSearch));
    }

    private final Collection<AnimateXAsStateSearch> animateXAsStateSearch() {
        if (AnimateXAsStateComposeAnimation.INSTANCE.getApiAvailable()) {
            return SetsKt.setOf(new AnimateXAsStateSearch(new Function1<AnimateXAsStateSearchInfo<?, ?>, Unit>() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch.animateXAsStateSearch.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AnimateXAsStateSearchInfo<?, ?> animateXAsStateSearchInfo) {
                    invoke2(animateXAsStateSearchInfo);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AnimateXAsStateSearchInfo<?, ?> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    ((PreviewAnimationClock) AnimationSearch.this.clock.invoke()).trackAnimateXAsState(it);
                }
            }));
        }
        return CollectionsKt.emptyList();
    }

    private final Set<InfiniteTransitionSearch> infiniteTransitionSearch() {
        if (InfiniteTransitionComposeAnimation.INSTANCE.getApiAvailable()) {
            return SetsKt.setOf(new InfiniteTransitionSearch(new Function1<InfiniteTransitionSearchInfo, Unit>() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch.infiniteTransitionSearch.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(InfiniteTransitionSearchInfo infiniteTransitionSearchInfo) {
                    invoke2(infiniteTransitionSearchInfo);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(InfiniteTransitionSearchInfo it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    ((PreviewAnimationClock) AnimationSearch.this.clock.invoke()).trackInfiniteTransition(it);
                }
            }));
        }
        return SetsKt.emptySet();
    }

    private final Set<Search<? extends Object>> supportedSearch() {
        return SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.setOf((Object[]) new Search[]{this.transitionSearch, this.animatedVisibilitySearch}), (Iterable) animateXAsStateSearch()), (Iterable) infiniteTransitionSearch()), (Iterable) (AnimatedContentComposeAnimation.INSTANCE.getApiAvailable() ? SetsKt.setOf(this.animatedContentSearch) : SetsKt.emptySet()));
    }

    private final Collection<Search<? extends Object>> unsupportedSearch() {
        return UnsupportedComposeAnimation.INSTANCE.getApiAvailable() ? SetsKt.setOf((Object[]) new Search[]{new AnimateContentSizeSearch(new Function1<Object, Unit>() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch.unsupportedSearch.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ((PreviewAnimationClock) AnimationSearch.this.clock.invoke()).trackAnimateContentSize(it);
            }
        }), new TargetBasedSearch(new Function1<TargetBasedAnimation<?, ?>, Unit>() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch.unsupportedSearch.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TargetBasedAnimation<?, ?> targetBasedAnimation) {
                invoke2(targetBasedAnimation);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TargetBasedAnimation<?, ?> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ((PreviewAnimationClock) AnimationSearch.this.clock.invoke()).trackTargetBasedAnimations(it);
            }
        }), new DecaySearch(new Function1<DecayAnimation<?, ?>, Unit>() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch.unsupportedSearch.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DecayAnimation<?, ?> decayAnimation) {
                invoke2(decayAnimation);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DecayAnimation<?, ?> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ((PreviewAnimationClock) AnimationSearch.this.clock.invoke()).trackDecayAnimations(it);
            }
        })}) : CollectionsKt.emptyList();
    }

    public final boolean getHasAnimations() {
        Set<Search<? extends Object>> set = this.supportedSearch;
        if ((set instanceof Collection) && set.isEmpty()) {
            return false;
        }
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            if (((Search) it.next()).hasAnimations()) {
                return true;
            }
        }
        return false;
    }

    public final void findAll(Collection<? extends Group> slotTrees) {
        Intrinsics.checkNotNullParameter(slotTrees, "slotTrees");
        Iterator<T> it = slotTrees.iterator();
        while (it.hasNext()) {
            List<Group> listFindAll = PreviewUtilsKt.findAll((Group) it.next(), new Function1<Group, Boolean>() { // from class: androidx.compose.ui.tooling.animation.AnimationSearch$findAll$1$groupsWithLocation$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(Group it2) {
                    Intrinsics.checkNotNullParameter(it2, "it");
                    return Boolean.valueOf(it2.getLocation() != null);
                }
            });
            Iterator<T> it2 = this.setToSearch.iterator();
            while (it2.hasNext()) {
                ((Search) it2.next()).addAnimations(listFindAll);
            }
            this.transitionSearch.getAnimations().removeAll(this.animatedVisibilitySearch.getAnimations());
            this.transitionSearch.getAnimations().removeAll(this.animatedContentSearch.getAnimations());
        }
    }

    public final void trackAll() {
        if (getHasAnimations()) {
            Iterator<T> it = this.setToTrack.iterator();
            while (it.hasNext()) {
                ((Search) it.next()).track();
            }
        }
    }

    /* compiled from: AnimationSearch.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0017\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0019\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\u0016\u0010\u000b\u001a\u00020\u00052\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0005R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$Search;", ExifInterface.GPS_DIRECTION_TRUE, "", "trackAnimation", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "animations", "", "getAnimations", "()Ljava/util/Set;", "addAnimations", "groupsWithLocation", "", "Landroidx/compose/ui/tooling/data/Group;", "hasAnimations", "", "track", "ui-tooling_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static class Search<T> {
        public static final int $stable = 8;
        private final Set<T> animations;
        private final Function1<T, Unit> trackAnimation;

        public void addAnimations(Collection<? extends Group> groupsWithLocation) {
            Intrinsics.checkNotNullParameter(groupsWithLocation, "groupsWithLocation");
        }

        public final Set<T> getAnimations() {
            return this.animations;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Search(Function1<? super T, Unit> trackAnimation) {
            Intrinsics.checkNotNullParameter(trackAnimation, "trackAnimation");
            this.trackAnimation = trackAnimation;
            this.animations = new LinkedHashSet();
        }

        public final boolean hasAnimations() {
            return !this.animations.isEmpty();
        }

        public final void track() {
            List listReversed = CollectionsKt.reversed(this.animations);
            Function1<T, Unit> function1 = this.trackAnimation;
            Iterator<T> it = listReversed.iterator();
            while (it.hasNext()) {
                function1.invoke(it.next());
            }
        }
    }

    /* compiled from: AnimationSearch.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\b\u0017\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B'\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J0\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00010\u000f\"\b\b\u0001\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0005H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$RememberSearch;", ExifInterface.GPS_DIRECTION_TRUE, "", "Landroidx/compose/ui/tooling/animation/AnimationSearch$Search;", "clazz", "Lkotlin/reflect/KClass;", "trackAnimation", "Lkotlin/Function1;", "", "(Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function1;)V", "addAnimations", "groupsWithLocation", "", "Landroidx/compose/ui/tooling/data/Group;", "findRememberCallWithType", "", "ui-tooling_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static class RememberSearch<T> extends Search<T> {
        public static final int $stable = 8;
        private final KClass<T> clazz;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RememberSearch(KClass<T> clazz, Function1<? super T, Unit> trackAnimation) {
            super(trackAnimation);
            Intrinsics.checkNotNullParameter(clazz, "clazz");
            Intrinsics.checkNotNullParameter(trackAnimation, "trackAnimation");
            this.clazz = clazz;
        }

        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public void addAnimations(Collection<? extends Group> groupsWithLocation) {
            Intrinsics.checkNotNullParameter(groupsWithLocation, "groupsWithLocation");
            getAnimations().addAll(CollectionsKt.toSet(findRememberCallWithType(groupsWithLocation, this.clazz)));
        }

        private final <T> List<T> findRememberCallWithType(Collection<? extends Group> collection, KClass<T> kClass) {
            Object kotlinClass;
            Class<?> cls;
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                Iterator<T> it2 = ((Group) it.next()).getData().iterator();
                while (true) {
                    kotlinClass = null;
                    if (!it2.hasNext()) {
                        break;
                    }
                    T next = it2.next();
                    if (next != null && (cls = next.getClass()) != null) {
                        kotlinClass = JvmClassMappingKt.getKotlinClass(cls);
                    }
                    if (Intrinsics.areEqual(kotlinClass, kClass)) {
                        kotlinClass = next;
                        break;
                    }
                }
                Object objSafeCast = KClasses.safeCast(kClass, kotlinClass);
                if (objSafeCast != null) {
                    arrayList.add(objSafeCast);
                }
            }
            return arrayList;
        }
    }

    /* compiled from: AnimationSearch.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00020\u0001B!\u0012\u001a\u0010\u0003\u001a\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$TargetBasedSearch;", "Landroidx/compose/ui/tooling/animation/AnimationSearch$RememberSearch;", "Landroidx/compose/animation/core/TargetBasedAnimation;", "trackAnimation", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "ui-tooling_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class TargetBasedSearch extends RememberSearch<TargetBasedAnimation<?, ?>> {
        public static final int $stable = 0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TargetBasedSearch(Function1<? super TargetBasedAnimation<?, ?>, Unit> trackAnimation) {
            super(Reflection.getOrCreateKotlinClass(TargetBasedAnimation.class), trackAnimation);
            Intrinsics.checkNotNullParameter(trackAnimation, "trackAnimation");
        }
    }

    /* compiled from: AnimationSearch.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00020\u0001B!\u0012\u001a\u0010\u0003\u001a\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$DecaySearch;", "Landroidx/compose/ui/tooling/animation/AnimationSearch$RememberSearch;", "Landroidx/compose/animation/core/DecayAnimation;", "trackAnimation", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "ui-tooling_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class DecaySearch extends RememberSearch<DecayAnimation<?, ?>> {
        public static final int $stable = 0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DecaySearch(Function1<? super DecayAnimation<?, ?>, Unit> trackAnimation) {
            super(Reflection.getOrCreateKotlinClass(DecayAnimation.class), trackAnimation);
            Intrinsics.checkNotNullParameter(trackAnimation, "trackAnimation");
        }
    }

    /* compiled from: AnimationSearch.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$InfiniteTransitionSearchInfo;", "", "infiniteTransition", "Landroidx/compose/animation/core/InfiniteTransition;", "toolingState", "Landroidx/compose/ui/tooling/animation/ToolingState;", "", "(Landroidx/compose/animation/core/InfiniteTransition;Landroidx/compose/ui/tooling/animation/ToolingState;)V", "getInfiniteTransition", "()Landroidx/compose/animation/core/InfiniteTransition;", "getToolingState", "()Landroidx/compose/ui/tooling/animation/ToolingState;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "", "ui-tooling_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class InfiniteTransitionSearchInfo {
        public static final int $stable = InfiniteTransition.$stable;
        private final InfiniteTransition infiniteTransition;
        private final ToolingState<Long> toolingState;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ InfiniteTransitionSearchInfo copy$default(InfiniteTransitionSearchInfo infiniteTransitionSearchInfo, InfiniteTransition infiniteTransition, ToolingState toolingState, int i, Object obj) {
            if ((i & 1) != 0) {
                infiniteTransition = infiniteTransitionSearchInfo.infiniteTransition;
            }
            if ((i & 2) != 0) {
                toolingState = infiniteTransitionSearchInfo.toolingState;
            }
            return infiniteTransitionSearchInfo.copy(infiniteTransition, toolingState);
        }

        /* renamed from: component1, reason: from getter */
        public final InfiniteTransition getInfiniteTransition() {
            return this.infiniteTransition;
        }

        public final ToolingState<Long> component2() {
            return this.toolingState;
        }

        public final InfiniteTransitionSearchInfo copy(InfiniteTransition infiniteTransition, ToolingState<Long> toolingState) {
            Intrinsics.checkNotNullParameter(infiniteTransition, "infiniteTransition");
            Intrinsics.checkNotNullParameter(toolingState, "toolingState");
            return new InfiniteTransitionSearchInfo(infiniteTransition, toolingState);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InfiniteTransitionSearchInfo)) {
                return false;
            }
            InfiniteTransitionSearchInfo infiniteTransitionSearchInfo = (InfiniteTransitionSearchInfo) other;
            return Intrinsics.areEqual(this.infiniteTransition, infiniteTransitionSearchInfo.infiniteTransition) && Intrinsics.areEqual(this.toolingState, infiniteTransitionSearchInfo.toolingState);
        }

        public final InfiniteTransition getInfiniteTransition() {
            return this.infiniteTransition;
        }

        public final ToolingState<Long> getToolingState() {
            return this.toolingState;
        }

        public int hashCode() {
            return (this.infiniteTransition.hashCode() * 31) + this.toolingState.hashCode();
        }

        public String toString() {
            return "InfiniteTransitionSearchInfo(infiniteTransition=" + this.infiniteTransition + ", toolingState=" + this.toolingState + ')';
        }

        public InfiniteTransitionSearchInfo(InfiniteTransition infiniteTransition, ToolingState<Long> toolingState) {
            Intrinsics.checkNotNullParameter(infiniteTransition, "infiniteTransition");
            Intrinsics.checkNotNullParameter(toolingState, "toolingState");
            this.infiniteTransition = infiniteTransition;
            this.toolingState = toolingState;
        }
    }

    /* compiled from: AnimationSearch.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016J\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\f2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0002¨\u0006\r"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$InfiniteTransitionSearch;", "Landroidx/compose/ui/tooling/animation/AnimationSearch$Search;", "Landroidx/compose/ui/tooling/animation/AnimationSearch$InfiniteTransitionSearchInfo;", "trackAnimation", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "addAnimations", "groupsWithLocation", "", "Landroidx/compose/ui/tooling/data/Group;", "findAnimations", "", "ui-tooling_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class InfiniteTransitionSearch extends Search<InfiniteTransitionSearchInfo> {
        public static final int $stable = 0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public InfiniteTransitionSearch(Function1<? super InfiniteTransitionSearchInfo, Unit> trackAnimation) {
            super(trackAnimation);
            Intrinsics.checkNotNullParameter(trackAnimation, "trackAnimation");
        }

        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public void addAnimations(Collection<? extends Group> groupsWithLocation) {
            Intrinsics.checkNotNullParameter(groupsWithLocation, "groupsWithLocation");
            getAnimations().addAll(findAnimations(groupsWithLocation));
        }

        private final List<InfiniteTransitionSearchInfo> findAnimations(Collection<? extends Group> groupsWithLocation) {
            InfiniteTransitionSearchInfo infiniteTransitionSearchInfo;
            Object next;
            Object next2;
            ArrayList arrayList = new ArrayList();
            for (Object obj : groupsWithLocation) {
                if (Intrinsics.areEqual(((Group) obj).getName(), "rememberInfiniteTransition")) {
                    arrayList.add(obj);
                }
            }
            ArrayList<CallGroup> arrayList2 = new ArrayList();
            for (Object obj2 : arrayList) {
                if (obj2 instanceof CallGroup) {
                    arrayList2.add(obj2);
                }
            }
            ArrayList arrayList3 = new ArrayList();
            for (CallGroup callGroup : arrayList2) {
                Collection<Object> data = callGroup.getData();
                Collection<Group> children = callGroup.getChildren();
                ArrayList arrayList4 = new ArrayList();
                Iterator<T> it = children.iterator();
                while (it.hasNext()) {
                    CollectionsKt.addAll(arrayList4, ((Group) it.next()).getData());
                }
                Iterator it2 = CollectionsKt.plus((Collection) data, (Iterable) arrayList4).iterator();
                while (true) {
                    infiniteTransitionSearchInfo = null;
                    if (!it2.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it2.next();
                    if (next instanceof InfiniteTransition) {
                        break;
                    }
                }
                if (!(next instanceof InfiniteTransition)) {
                    next = null;
                }
                InfiniteTransition infiniteTransition = (InfiniteTransition) next;
                Collection<Object> data2 = callGroup.getData();
                Collection<Group> children2 = callGroup.getChildren();
                ArrayList arrayList5 = new ArrayList();
                Iterator<T> it3 = children2.iterator();
                while (it3.hasNext()) {
                    CollectionsKt.addAll(arrayList5, ((Group) it3.next()).getChildren());
                }
                List listPlus = CollectionsKt.plus((Collection) children2, (Iterable) arrayList5);
                ArrayList arrayList6 = new ArrayList();
                Iterator it4 = listPlus.iterator();
                while (it4.hasNext()) {
                    CollectionsKt.addAll(arrayList6, ((Group) it4.next()).getData());
                }
                Iterator it5 = CollectionsKt.plus((Collection) data2, (Iterable) arrayList6).iterator();
                while (true) {
                    if (!it5.hasNext()) {
                        next2 = null;
                        break;
                    }
                    next2 = it5.next();
                    if (next2 instanceof MutableState) {
                        break;
                    }
                }
                if (!(next2 instanceof MutableState)) {
                    next2 = null;
                }
                MutableState mutableState = (MutableState) next2;
                if (infiniteTransition != null && mutableState != null) {
                    if (mutableState.getValue() == null) {
                        mutableState.setValue(new ToolingState(0L));
                    }
                    Object value = mutableState.getValue();
                    Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.ui.tooling.animation.ToolingState<kotlin.Long>");
                    infiniteTransitionSearchInfo = new InfiniteTransitionSearchInfo(infiniteTransition, (ToolingState) value);
                }
                if (infiniteTransitionSearchInfo != null) {
                    arrayList3.add(infiniteTransitionSearchInfo);
                }
            }
            return arrayList3;
        }
    }

    /* compiled from: AnimationSearch.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u00020\u0004B5\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n¢\u0006\u0002\u0010\u000bJ\u0015\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\bHÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\nHÆ\u0003JK\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$AnimateXAsStateSearchInfo;", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/compose/animation/core/AnimationVector;", "", "animatable", "Landroidx/compose/animation/core/Animatable;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "toolingState", "Landroidx/compose/ui/tooling/animation/ToolingState;", "(Landroidx/compose/animation/core/Animatable;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/ui/tooling/animation/ToolingState;)V", "getAnimatable", "()Landroidx/compose/animation/core/Animatable;", "getAnimationSpec", "()Landroidx/compose/animation/core/AnimationSpec;", "getToolingState", "()Landroidx/compose/ui/tooling/animation/ToolingState;", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "", "ui-tooling_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class AnimateXAsStateSearchInfo<T, V extends AnimationVector> {
        public static final int $stable = 8;
        private final Animatable<T, V> animatable;
        private final AnimationSpec<T> animationSpec;
        private final ToolingState<T> toolingState;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ AnimateXAsStateSearchInfo copy$default(AnimateXAsStateSearchInfo animateXAsStateSearchInfo, Animatable animatable, AnimationSpec animationSpec, ToolingState toolingState, int i, Object obj) {
            if ((i & 1) != 0) {
                animatable = animateXAsStateSearchInfo.animatable;
            }
            if ((i & 2) != 0) {
                animationSpec = animateXAsStateSearchInfo.animationSpec;
            }
            if ((i & 4) != 0) {
                toolingState = animateXAsStateSearchInfo.toolingState;
            }
            return animateXAsStateSearchInfo.copy(animatable, animationSpec, toolingState);
        }

        public final Animatable<T, V> component1() {
            return this.animatable;
        }

        public final AnimationSpec<T> component2() {
            return this.animationSpec;
        }

        public final ToolingState<T> component3() {
            return this.toolingState;
        }

        public final AnimateXAsStateSearchInfo<T, V> copy(Animatable<T, V> animatable, AnimationSpec<T> animationSpec, ToolingState<T> toolingState) {
            Intrinsics.checkNotNullParameter(animatable, "animatable");
            Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
            Intrinsics.checkNotNullParameter(toolingState, "toolingState");
            return new AnimateXAsStateSearchInfo<>(animatable, animationSpec, toolingState);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AnimateXAsStateSearchInfo)) {
                return false;
            }
            AnimateXAsStateSearchInfo animateXAsStateSearchInfo = (AnimateXAsStateSearchInfo) other;
            return Intrinsics.areEqual(this.animatable, animateXAsStateSearchInfo.animatable) && Intrinsics.areEqual(this.animationSpec, animateXAsStateSearchInfo.animationSpec) && Intrinsics.areEqual(this.toolingState, animateXAsStateSearchInfo.toolingState);
        }

        public final Animatable<T, V> getAnimatable() {
            return this.animatable;
        }

        public final AnimationSpec<T> getAnimationSpec() {
            return this.animationSpec;
        }

        public final ToolingState<T> getToolingState() {
            return this.toolingState;
        }

        public int hashCode() {
            return (((this.animatable.hashCode() * 31) + this.animationSpec.hashCode()) * 31) + this.toolingState.hashCode();
        }

        public String toString() {
            return "AnimateXAsStateSearchInfo(animatable=" + this.animatable + ", animationSpec=" + this.animationSpec + ", toolingState=" + this.toolingState + ')';
        }

        public AnimateXAsStateSearchInfo(Animatable<T, V> animatable, AnimationSpec<T> animationSpec, ToolingState<T> toolingState) {
            Intrinsics.checkNotNullParameter(animatable, "animatable");
            Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
            Intrinsics.checkNotNullParameter(toolingState, "toolingState");
            this.animatable = animatable;
            this.animationSpec = animationSpec;
            this.toolingState = toolingState;
        }
    }

    /* compiled from: AnimationSearch.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\b\u0007\u0018\u00002\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00020\u0001B!\u0012\u001a\u0010\u0003\u001a\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016J$\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\f\"\u0004\b\u0000\u0010\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u001e\u0010\u0011\u001a\n\u0012\u0004\u0012\u0002H\r\u0018\u00010\u0012\"\u0004\b\u0000\u0010\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J.\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u00020\u000e0\u00020\u0014\"\u0004\b\u0000\u0010\r2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0002¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$AnimateXAsStateSearch;", "Landroidx/compose/ui/tooling/animation/AnimationSearch$Search;", "Landroidx/compose/ui/tooling/animation/AnimationSearch$AnimateXAsStateSearchInfo;", "trackAnimation", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "addAnimations", "groupsWithLocation", "", "Landroidx/compose/ui/tooling/data/Group;", "findAnimatable", "Landroidx/compose/animation/core/Animatable;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/animation/core/AnimationVector;", "group", "Landroidx/compose/ui/tooling/data/CallGroup;", "findAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "findAnimations", "", "ui-tooling_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class AnimateXAsStateSearch extends Search<AnimateXAsStateSearchInfo<?, ?>> {
        public static final int $stable = 0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnimateXAsStateSearch(Function1<? super AnimateXAsStateSearchInfo<?, ?>, Unit> trackAnimation) {
            super(trackAnimation);
            Intrinsics.checkNotNullParameter(trackAnimation, "trackAnimation");
        }

        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public void addAnimations(Collection<? extends Group> groupsWithLocation) {
            Intrinsics.checkNotNullParameter(groupsWithLocation, "groupsWithLocation");
            getAnimations().addAll(findAnimations(groupsWithLocation));
        }

        private final <T> List<AnimateXAsStateSearchInfo<T, AnimationVector>> findAnimations(Collection<? extends Group> groupsWithLocation) {
            Object animateXAsStateSearchInfo;
            T next;
            Object next2;
            ArrayList arrayList = new ArrayList();
            for (T t : groupsWithLocation) {
                if (Intrinsics.areEqual(((Group) t).getName(), "animateValueAsState")) {
                    arrayList.add(t);
                }
            }
            ArrayList<CallGroup> arrayList2 = new ArrayList();
            for (T t2 : arrayList) {
                if (t2 instanceof CallGroup) {
                    arrayList2.add(t2);
                }
            }
            ArrayList arrayList3 = new ArrayList();
            for (CallGroup callGroup : arrayList2) {
                Animatable<T, AnimationVector> animatableFindAnimatable = findAnimatable(callGroup);
                AnimationSpec<T> animationSpecFindAnimationSpec = findAnimationSpec(callGroup);
                Collection<Group> children = callGroup.getChildren();
                ArrayList arrayList4 = new ArrayList();
                Iterator<T> it = children.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Iterator<T> it2 = ((Group) it.next()).getData().iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            next2 = null;
                            break;
                        }
                        next2 = it2.next();
                        if (next2 instanceof MutableState) {
                            break;
                        }
                    }
                    MutableState mutableState = (MutableState) (next2 instanceof MutableState ? next2 : null);
                    if (mutableState != null) {
                        arrayList4.add(mutableState);
                    }
                }
                ArrayList arrayList5 = arrayList4;
                ArrayList arrayList6 = new ArrayList();
                Iterator<T> it3 = children.iterator();
                while (it3.hasNext()) {
                    Group groupFirstOrNull = PreviewUtilsKt.firstOrNull((Group) it3.next(), AnimationSearchKt$findRememberedData$rememberCalls$1$1.INSTANCE);
                    if (groupFirstOrNull != null) {
                        arrayList6.add(groupFirstOrNull);
                    }
                }
                ArrayList arrayList7 = arrayList5;
                ArrayList arrayList8 = new ArrayList();
                Iterator<T> it4 = arrayList6.iterator();
                while (it4.hasNext()) {
                    Iterator<T> it5 = ((Group) it4.next()).getData().iterator();
                    while (true) {
                        if (!it5.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it5.next();
                        if (next instanceof MutableState) {
                            break;
                        }
                    }
                    if (!(next instanceof MutableState)) {
                        next = null;
                    }
                    MutableState mutableState2 = (MutableState) next;
                    if (mutableState2 != null) {
                        arrayList8.add(mutableState2);
                    }
                }
                MutableState mutableState3 = (MutableState) CollectionsKt.firstOrNull(CollectionsKt.plus((Collection) arrayList7, (Iterable) arrayList8));
                if (animatableFindAnimatable != null && animationSpecFindAnimationSpec != null && mutableState3 != null) {
                    if (mutableState3.getValue() == null) {
                        mutableState3.setValue(new ToolingState(animatableFindAnimatable.getValue()));
                    }
                    Object value = mutableState3.getValue();
                    Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.ui.tooling.animation.ToolingState<T of androidx.compose.ui.tooling.animation.AnimationSearch.AnimateXAsStateSearch.findAnimations$lambda$1>");
                    animateXAsStateSearchInfo = new AnimateXAsStateSearchInfo(animatableFindAnimatable, animationSpecFindAnimationSpec, (ToolingState) value);
                }
                if (animateXAsStateSearchInfo != null) {
                    arrayList3.add(animateXAsStateSearchInfo);
                }
            }
            return arrayList3;
        }

        private final <T> AnimationSpec<T> findAnimationSpec(CallGroup group) {
            Collection<Group> children = group.getChildren();
            ArrayList arrayList = new ArrayList();
            for (T t : children) {
                if (Intrinsics.areEqual(((Group) t).getName(), "rememberUpdatedState")) {
                    arrayList.add(t);
                }
            }
            ArrayList arrayList2 = arrayList;
            ArrayList arrayList3 = arrayList2;
            ArrayList arrayList4 = new ArrayList();
            Iterator<T> it = arrayList2.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList4, ((Group) it.next()).getChildren());
            }
            List listPlus = CollectionsKt.plus((Collection) arrayList3, (Iterable) arrayList4);
            ArrayList arrayList5 = new ArrayList();
            Iterator<T> it2 = listPlus.iterator();
            while (it2.hasNext()) {
                CollectionsKt.addAll(arrayList5, ((Group) it2.next()).getData());
            }
            ArrayList arrayList6 = new ArrayList();
            for (T t2 : arrayList5) {
                if (t2 instanceof State) {
                    arrayList6.add(t2);
                }
            }
            ArrayList arrayList7 = arrayList6;
            ArrayList arrayList8 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList7, 10));
            Iterator<T> it3 = arrayList7.iterator();
            while (it3.hasNext()) {
                arrayList8.add(((State) it3.next()).getValue());
            }
            ArrayList arrayList9 = new ArrayList();
            for (T t3 : arrayList8) {
                if (t3 instanceof AnimationSpec) {
                    arrayList9.add(t3);
                }
            }
            return (AnimationSpec) CollectionsKt.firstOrNull((List) arrayList9);
        }

        private final <T> Animatable<T, AnimationVector> findAnimatable(CallGroup group) {
            T next;
            T next2;
            Collection<Group> children = group.getChildren();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = children.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Iterator<T> it2 = ((Group) it.next()).getData().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        next2 = null;
                        break;
                    }
                    next2 = it2.next();
                    if (next2 instanceof Animatable) {
                        break;
                    }
                }
                Animatable animatable = (Animatable) (next2 instanceof Animatable ? next2 : null);
                if (animatable != null) {
                    arrayList.add(animatable);
                }
            }
            ArrayList arrayList2 = arrayList;
            ArrayList arrayList3 = new ArrayList();
            Iterator<T> it3 = children.iterator();
            while (it3.hasNext()) {
                Group groupFirstOrNull = PreviewUtilsKt.firstOrNull((Group) it3.next(), AnimationSearchKt$findRememberedData$rememberCalls$1$1.INSTANCE);
                if (groupFirstOrNull != null) {
                    arrayList3.add(groupFirstOrNull);
                }
            }
            ArrayList arrayList4 = arrayList2;
            ArrayList arrayList5 = new ArrayList();
            Iterator<T> it4 = arrayList3.iterator();
            while (it4.hasNext()) {
                Iterator<T> it5 = ((Group) it4.next()).getData().iterator();
                while (true) {
                    if (!it5.hasNext()) {
                        next = (T) null;
                        break;
                    }
                    next = it5.next();
                    if (next instanceof Animatable) {
                        break;
                    }
                }
                if (!(next instanceof Animatable)) {
                    next = null;
                }
                Animatable animatable2 = next;
                if (animatable2 != null) {
                    arrayList5.add(animatable2);
                }
            }
            return (Animatable) CollectionsKt.firstOrNull(CollectionsKt.plus((Collection) arrayList4, (Iterable) arrayList5));
        }
    }

    /* compiled from: AnimationSearch.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$AnimateContentSizeSearch;", "Landroidx/compose/ui/tooling/animation/AnimationSearch$Search;", "", "trackAnimation", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "addAnimations", "groupsWithLocation", "", "Landroidx/compose/ui/tooling/data/Group;", "ui-tooling_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class AnimateContentSizeSearch extends Search<Object> {
        public static final int $stable = 0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnimateContentSizeSearch(Function1<Object, Unit> trackAnimation) {
            super(trackAnimation);
            Intrinsics.checkNotNullParameter(trackAnimation, "trackAnimation");
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public void addAnimations(Collection<? extends Group> groupsWithLocation) {
            String name;
            Class<?> cls;
            Intrinsics.checkNotNullParameter(groupsWithLocation, "groupsWithLocation");
            Set<Object> animations = getAnimations();
            ArrayList arrayList = new ArrayList();
            for (Object obj : groupsWithLocation) {
                if (Intrinsics.areEqual(((Group) obj).getName(), "remember")) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Iterator<T> it2 = ((Group) it.next()).getData().iterator();
                while (true) {
                    name = null;
                    if (!it2.hasNext()) {
                        break;
                    }
                    Object next = it2.next();
                    if (next != 0 && (cls = next.getClass()) != null) {
                        name = cls.getName();
                    }
                    if (Intrinsics.areEqual(name, "androidx.compose.animation.SizeAnimationModifier")) {
                        name = next;
                        break;
                    }
                }
                if (name != null) {
                    arrayList2.add(name);
                }
            }
            animations.addAll(CollectionsKt.toSet(arrayList2));
        }
    }

    /* compiled from: AnimationSearch.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u001d\u0012\u0016\u0010\u0003\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$TransitionSearch;", "Landroidx/compose/ui/tooling/animation/AnimationSearch$Search;", "Landroidx/compose/animation/core/Transition;", "trackAnimation", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "addAnimations", "groupsWithLocation", "", "Landroidx/compose/ui/tooling/data/Group;", "ui-tooling_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class TransitionSearch extends Search<Transition<?>> {
        public static final int $stable = 0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TransitionSearch(Function1<? super Transition<?>, Unit> trackAnimation) {
            super(trackAnimation);
            Intrinsics.checkNotNullParameter(trackAnimation, "trackAnimation");
        }

        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public void addAnimations(Collection<? extends Group> groupsWithLocation) {
            Object next;
            Object next2;
            Intrinsics.checkNotNullParameter(groupsWithLocation, "groupsWithLocation");
            Set<Transition<?>> animations = getAnimations();
            ArrayList arrayList = new ArrayList();
            for (Object obj : groupsWithLocation) {
                if (Intrinsics.areEqual(((Group) obj).getName(), "updateTransition")) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = arrayList;
            ArrayList arrayList3 = new ArrayList();
            Iterator it = arrayList2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Iterator<T> it2 = ((Group) it.next()).getData().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        next2 = it2.next();
                        if (next2 instanceof Transition) {
                            break;
                        }
                    } else {
                        next2 = null;
                        break;
                    }
                }
                Transition transition = (Transition) (next2 instanceof Transition ? next2 : null);
                if (transition != null) {
                    arrayList3.add(transition);
                }
            }
            ArrayList arrayList4 = arrayList3;
            ArrayList arrayList5 = new ArrayList();
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                Group groupFirstOrNull = PreviewUtilsKt.firstOrNull((Group) it3.next(), AnimationSearchKt$findRememberedData$rememberCalls$1$1.INSTANCE);
                if (groupFirstOrNull != null) {
                    arrayList5.add(groupFirstOrNull);
                }
            }
            ArrayList arrayList6 = arrayList4;
            ArrayList arrayList7 = new ArrayList();
            Iterator it4 = arrayList5.iterator();
            while (it4.hasNext()) {
                Iterator<T> it5 = ((Group) it4.next()).getData().iterator();
                while (true) {
                    if (it5.hasNext()) {
                        next = it5.next();
                        if (next instanceof Transition) {
                            break;
                        }
                    } else {
                        next = null;
                        break;
                    }
                }
                if (!(next instanceof Transition)) {
                    next = null;
                }
                Transition transition2 = (Transition) next;
                if (transition2 != null) {
                    arrayList7.add(transition2);
                }
            }
            animations.addAll(CollectionsKt.plus((Collection) arrayList6, (Iterable) arrayList7));
        }
    }

    /* compiled from: AnimationSearch.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u001d\u0012\u0016\u0010\u0003\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$AnimatedVisibilitySearch;", "Landroidx/compose/ui/tooling/animation/AnimationSearch$Search;", "Landroidx/compose/animation/core/Transition;", "trackAnimation", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "addAnimations", "groupsWithLocation", "", "Landroidx/compose/ui/tooling/data/Group;", "ui-tooling_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class AnimatedVisibilitySearch extends Search<Transition<?>> {
        public static final int $stable = 0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnimatedVisibilitySearch(Function1<? super Transition<?>, Unit> trackAnimation) {
            super(trackAnimation);
            Intrinsics.checkNotNullParameter(trackAnimation, "trackAnimation");
        }

        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public void addAnimations(Collection<? extends Group> groupsWithLocation) {
            Object next;
            Object next2;
            Intrinsics.checkNotNullParameter(groupsWithLocation, "groupsWithLocation");
            Set<Transition<?>> animations = getAnimations();
            ArrayList arrayList = new ArrayList();
            for (Object obj : groupsWithLocation) {
                if (Intrinsics.areEqual(((Group) obj).getName(), "AnimatedVisibility")) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (true) {
                Object obj2 = null;
                if (!it.hasNext()) {
                    break;
                }
                Iterator<T> it2 = ((Group) it.next()).getChildren().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Object next3 = it2.next();
                    if (Intrinsics.areEqual(((Group) next3).getName(), "updateTransition")) {
                        obj2 = next3;
                        break;
                    }
                }
                Group group = (Group) obj2;
                if (group != null) {
                    arrayList2.add(group);
                }
            }
            ArrayList arrayList3 = arrayList2;
            ArrayList arrayList4 = new ArrayList();
            Iterator it3 = arrayList3.iterator();
            while (it3.hasNext()) {
                Iterator<T> it4 = ((Group) it3.next()).getData().iterator();
                while (true) {
                    if (it4.hasNext()) {
                        next2 = it4.next();
                        if (next2 instanceof Transition) {
                            break;
                        }
                    } else {
                        next2 = null;
                        break;
                    }
                }
                if (!(next2 instanceof Transition)) {
                    next2 = null;
                }
                Transition transition = (Transition) next2;
                if (transition != null) {
                    arrayList4.add(transition);
                }
            }
            ArrayList arrayList5 = arrayList4;
            ArrayList arrayList6 = new ArrayList();
            Iterator it5 = arrayList3.iterator();
            while (it5.hasNext()) {
                Group groupFirstOrNull = PreviewUtilsKt.firstOrNull((Group) it5.next(), AnimationSearchKt$findRememberedData$rememberCalls$1$1.INSTANCE);
                if (groupFirstOrNull != null) {
                    arrayList6.add(groupFirstOrNull);
                }
            }
            ArrayList arrayList7 = arrayList5;
            ArrayList arrayList8 = new ArrayList();
            Iterator it6 = arrayList6.iterator();
            while (it6.hasNext()) {
                Iterator<T> it7 = ((Group) it6.next()).getData().iterator();
                while (true) {
                    if (it7.hasNext()) {
                        next = it7.next();
                        if (next instanceof Transition) {
                            break;
                        }
                    } else {
                        next = null;
                        break;
                    }
                }
                if (!(next instanceof Transition)) {
                    next = null;
                }
                Transition transition2 = (Transition) next;
                if (transition2 != null) {
                    arrayList8.add(transition2);
                }
            }
            animations.addAll(CollectionsKt.plus((Collection) arrayList7, (Iterable) arrayList8));
        }
    }

    /* compiled from: AnimationSearch.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u001d\u0012\u0016\u0010\u0003\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/tooling/animation/AnimationSearch$AnimatedContentSearch;", "Landroidx/compose/ui/tooling/animation/AnimationSearch$Search;", "Landroidx/compose/animation/core/Transition;", "trackAnimation", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "addAnimations", "groupsWithLocation", "", "Landroidx/compose/ui/tooling/data/Group;", "ui-tooling_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class AnimatedContentSearch extends Search<Transition<?>> {
        public static final int $stable = 0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnimatedContentSearch(Function1<? super Transition<?>, Unit> trackAnimation) {
            super(trackAnimation);
            Intrinsics.checkNotNullParameter(trackAnimation, "trackAnimation");
        }

        @Override // androidx.compose.ui.tooling.animation.AnimationSearch.Search
        public void addAnimations(Collection<? extends Group> groupsWithLocation) {
            Object next;
            Object next2;
            Intrinsics.checkNotNullParameter(groupsWithLocation, "groupsWithLocation");
            Set<Transition<?>> animations = getAnimations();
            ArrayList arrayList = new ArrayList();
            for (Object obj : groupsWithLocation) {
                if (Intrinsics.areEqual(((Group) obj).getName(), "AnimatedContent")) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (true) {
                Object obj2 = null;
                if (!it.hasNext()) {
                    break;
                }
                Iterator<T> it2 = ((Group) it.next()).getChildren().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Object next3 = it2.next();
                    if (Intrinsics.areEqual(((Group) next3).getName(), "updateTransition")) {
                        obj2 = next3;
                        break;
                    }
                }
                Group group = (Group) obj2;
                if (group != null) {
                    arrayList2.add(group);
                }
            }
            ArrayList arrayList3 = arrayList2;
            ArrayList arrayList4 = new ArrayList();
            Iterator it3 = arrayList3.iterator();
            while (it3.hasNext()) {
                Iterator<T> it4 = ((Group) it3.next()).getData().iterator();
                while (true) {
                    if (it4.hasNext()) {
                        next2 = it4.next();
                        if (next2 instanceof Transition) {
                            break;
                        }
                    } else {
                        next2 = null;
                        break;
                    }
                }
                if (!(next2 instanceof Transition)) {
                    next2 = null;
                }
                Transition transition = (Transition) next2;
                if (transition != null) {
                    arrayList4.add(transition);
                }
            }
            ArrayList arrayList5 = arrayList4;
            ArrayList arrayList6 = new ArrayList();
            Iterator it5 = arrayList3.iterator();
            while (it5.hasNext()) {
                Group groupFirstOrNull = PreviewUtilsKt.firstOrNull((Group) it5.next(), AnimationSearchKt$findRememberedData$rememberCalls$1$1.INSTANCE);
                if (groupFirstOrNull != null) {
                    arrayList6.add(groupFirstOrNull);
                }
            }
            ArrayList arrayList7 = arrayList5;
            ArrayList arrayList8 = new ArrayList();
            Iterator it6 = arrayList6.iterator();
            while (it6.hasNext()) {
                Iterator<T> it7 = ((Group) it6.next()).getData().iterator();
                while (true) {
                    if (it7.hasNext()) {
                        next = it7.next();
                        if (next instanceof Transition) {
                            break;
                        }
                    } else {
                        next = null;
                        break;
                    }
                }
                if (!(next instanceof Transition)) {
                    next = null;
                }
                Transition transition2 = (Transition) next;
                if (transition2 != null) {
                    arrayList8.add(transition2);
                }
            }
            animations.addAll(CollectionsKt.plus((Collection) arrayList7, (Iterable) arrayList8));
        }
    }
}
