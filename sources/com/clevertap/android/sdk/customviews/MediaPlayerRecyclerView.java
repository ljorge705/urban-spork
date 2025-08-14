package com.clevertap.android.sdk.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.inbox.CTInboxBaseMessageViewHolder;
import com.clevertap.android.sdk.video.InboxVideoPlayerHandle;
import com.clevertap.android.sdk.video.VideoLibChecker;
import com.clevertap.android.sdk.video.VideoLibraryIntegrated;
import com.clevertap.android.sdk.video.inbox.ExoplayerHandle;
import com.clevertap.android.sdk.video.inbox.Media3Handle;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaPlayerRecyclerView.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\n\u0010\u0019\u001a\u0004\u0018\u00010\u0012H\u0002J\b\u0010\u001a\u001a\u00020\u0018H\u0002J\u0006\u0010\u001b\u001a\u00020\u0018J\u0006\u0010\u001c\u001a\u00020\u0018J\u0006\u0010\u001d\u001a\u00020\u0018J\b\u0010\u001e\u001a\u00020\u0018H\u0002J\b\u0010\u001f\u001a\u00020\u0018H\u0002J\b\u0010 \u001a\u00020\u0018H\u0002J\u0006\u0010!\u001a\u00020\u0018R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/clevertap/android/sdk/customviews/MediaPlayerRecyclerView;", "Landroidx/recyclerview/widget/RecyclerView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "handle", "Lcom/clevertap/android/sdk/video/InboxVideoPlayerHandle;", "onChildAttachStateChangeListener", "Landroidx/recyclerview/widget/RecyclerView$OnChildAttachStateChangeListener;", "onScrollListener", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "playingHolder", "Lcom/clevertap/android/sdk/inbox/CTInboxBaseMessageViewHolder;", "rect", "Landroid/graphics/Rect;", "artworkAsset", "Landroid/graphics/drawable/Drawable;", "bufferingStarted", "", "findBestVisibleMediaHolder", "initialize", "onPausePlayer", "onRestartPlayer", "playVideo", "playerReady", "recyclerViewListeners", "removeVideoView", "stop", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class MediaPlayerRecyclerView extends RecyclerView {
    private final InboxVideoPlayerHandle handle;
    private final RecyclerView.OnChildAttachStateChangeListener onChildAttachStateChangeListener;
    private final RecyclerView.OnScrollListener onScrollListener;
    private CTInboxBaseMessageViewHolder playingHolder;
    private final Rect rect;

    /* compiled from: MediaPlayerRecyclerView.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VideoLibraryIntegrated.values().length];
            try {
                iArr[VideoLibraryIntegrated.MEDIA3.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaPlayerRecyclerView(Context context) {
        ExoplayerHandle exoplayerHandle;
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        if (WhenMappings.$EnumSwitchMapping$0[VideoLibChecker.mediaLibType.ordinal()] == 1) {
            exoplayerHandle = new Media3Handle();
        } else {
            exoplayerHandle = new ExoplayerHandle();
        }
        this.handle = exoplayerHandle;
        this.rect = new Rect();
        this.onScrollListener = new RecyclerView.OnScrollListener() { // from class: com.clevertap.android.sdk.customviews.MediaPlayerRecyclerView$onScrollListener$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 0) {
                    this.this$0.playVideo();
                }
            }
        };
        this.onChildAttachStateChangeListener = new RecyclerView.OnChildAttachStateChangeListener() { // from class: com.clevertap.android.sdk.customviews.MediaPlayerRecyclerView$onChildAttachStateChangeListener$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewAttachedToWindow(View view) {
                Intrinsics.checkNotNullParameter(view, "view");
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewDetachedFromWindow(View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                CTInboxBaseMessageViewHolder cTInboxBaseMessageViewHolder = this.this$0.playingHolder;
                if (cTInboxBaseMessageViewHolder != null) {
                    MediaPlayerRecyclerView mediaPlayerRecyclerView = this.this$0;
                    if (Intrinsics.areEqual(cTInboxBaseMessageViewHolder.itemView, view)) {
                        mediaPlayerRecyclerView.stop();
                    }
                }
            }
        };
        initialize();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaPlayerRecyclerView(Context context, AttributeSet attrs) {
        ExoplayerHandle exoplayerHandle;
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        if (WhenMappings.$EnumSwitchMapping$0[VideoLibChecker.mediaLibType.ordinal()] == 1) {
            exoplayerHandle = new Media3Handle();
        } else {
            exoplayerHandle = new ExoplayerHandle();
        }
        this.handle = exoplayerHandle;
        this.rect = new Rect();
        this.onScrollListener = new RecyclerView.OnScrollListener() { // from class: com.clevertap.android.sdk.customviews.MediaPlayerRecyclerView$onScrollListener$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 0) {
                    this.this$0.playVideo();
                }
            }
        };
        this.onChildAttachStateChangeListener = new RecyclerView.OnChildAttachStateChangeListener() { // from class: com.clevertap.android.sdk.customviews.MediaPlayerRecyclerView$onChildAttachStateChangeListener$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewAttachedToWindow(View view) {
                Intrinsics.checkNotNullParameter(view, "view");
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewDetachedFromWindow(View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                CTInboxBaseMessageViewHolder cTInboxBaseMessageViewHolder = this.this$0.playingHolder;
                if (cTInboxBaseMessageViewHolder != null) {
                    MediaPlayerRecyclerView mediaPlayerRecyclerView = this.this$0;
                    if (Intrinsics.areEqual(cTInboxBaseMessageViewHolder.itemView, view)) {
                        mediaPlayerRecyclerView.stop();
                    }
                }
            }
        };
        initialize();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaPlayerRecyclerView(Context context, AttributeSet attrs, int i) {
        ExoplayerHandle exoplayerHandle;
        super(context, attrs, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        if (WhenMappings.$EnumSwitchMapping$0[VideoLibChecker.mediaLibType.ordinal()] == 1) {
            exoplayerHandle = new Media3Handle();
        } else {
            exoplayerHandle = new ExoplayerHandle();
        }
        this.handle = exoplayerHandle;
        this.rect = new Rect();
        this.onScrollListener = new RecyclerView.OnScrollListener() { // from class: com.clevertap.android.sdk.customviews.MediaPlayerRecyclerView$onScrollListener$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 0) {
                    this.this$0.playVideo();
                }
            }
        };
        this.onChildAttachStateChangeListener = new RecyclerView.OnChildAttachStateChangeListener() { // from class: com.clevertap.android.sdk.customviews.MediaPlayerRecyclerView$onChildAttachStateChangeListener$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewAttachedToWindow(View view) {
                Intrinsics.checkNotNullParameter(view, "view");
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewDetachedFromWindow(View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                CTInboxBaseMessageViewHolder cTInboxBaseMessageViewHolder = this.this$0.playingHolder;
                if (cTInboxBaseMessageViewHolder != null) {
                    MediaPlayerRecyclerView mediaPlayerRecyclerView = this.this$0;
                    if (Intrinsics.areEqual(cTInboxBaseMessageViewHolder.itemView, view)) {
                        mediaPlayerRecyclerView.stop();
                    }
                }
            }
        };
        initialize();
    }

    public final void onPausePlayer() {
        this.handle.setPlayWhenReady(false);
    }

    public final void onRestartPlayer() {
        initialize();
        playVideo();
    }

    public final void playVideo() {
        CTInboxBaseMessageViewHolder cTInboxBaseMessageViewHolderFindBestVisibleMediaHolder = findBestVisibleMediaHolder();
        if (cTInboxBaseMessageViewHolderFindBestVisibleMediaHolder == null) {
            removeVideoView();
            return;
        }
        CTInboxBaseMessageViewHolder cTInboxBaseMessageViewHolder = this.playingHolder;
        if (cTInboxBaseMessageViewHolder != null && Intrinsics.areEqual(cTInboxBaseMessageViewHolder.itemView, cTInboxBaseMessageViewHolderFindBestVisibleMediaHolder.itemView)) {
            if (cTInboxBaseMessageViewHolder.itemView.getGlobalVisibleRect(this.rect) && this.rect.height() >= 400 && cTInboxBaseMessageViewHolder.shouldAutoPlay()) {
                this.handle.setPlayWhenReady(true);
                return;
            } else {
                this.handle.setPlayWhenReady(false);
                return;
            }
        }
        removeVideoView();
        initialize();
        if (cTInboxBaseMessageViewHolderFindBestVisibleMediaHolder.addMediaPlayer(this.handle.playerVolume(), new Function0<Float>() { // from class: com.clevertap.android.sdk.customviews.MediaPlayerRecyclerView$playVideo$addedVideo$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                this.this$0.handle.handleMute();
                return Float.valueOf(this.this$0.handle.playerVolume());
            }
        }, new Function3<String, Boolean, Boolean, Void>() { // from class: com.clevertap.android.sdk.customviews.MediaPlayerRecyclerView$playVideo$addedVideo$2
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Void invoke(String str, Boolean bool, Boolean bool2) {
                return invoke(str, bool.booleanValue(), bool2.booleanValue());
            }

            public final Void invoke(String uri, boolean z, boolean z2) {
                Intrinsics.checkNotNullParameter(uri, "uri");
                InboxVideoPlayerHandle inboxVideoPlayerHandle = this.this$0.handle;
                Context context = this.this$0.getContext();
                Intrinsics.checkNotNullExpressionValue(context, "context");
                inboxVideoPlayerHandle.startPlaying(context, uri, z, z2);
                return null;
            }
        }, this.handle.videoSurface())) {
            this.playingHolder = cTInboxBaseMessageViewHolderFindBestVisibleMediaHolder;
        }
    }

    public final void stop() {
        this.handle.pause();
        this.playingHolder = null;
    }

    private final CTInboxBaseMessageViewHolder findBestVisibleMediaHolder() {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getLayoutManager();
        int iFindFirstVisibleItemPosition = linearLayoutManager != null ? linearLayoutManager.findFirstVisibleItemPosition() : 0;
        LinearLayoutManager linearLayoutManager2 = (LinearLayoutManager) getLayoutManager();
        int iFindLastVisibleItemPosition = linearLayoutManager2 != null ? linearLayoutManager2.findLastVisibleItemPosition() : 0;
        if (iFindFirstVisibleItemPosition > iFindLastVisibleItemPosition) {
            return null;
        }
        int i = iFindFirstVisibleItemPosition;
        int i2 = 0;
        CTInboxBaseMessageViewHolder cTInboxBaseMessageViewHolder = null;
        while (true) {
            View childAt = getChildAt(i - iFindFirstVisibleItemPosition);
            if (childAt != null) {
                Object tag = childAt.getTag();
                CTInboxBaseMessageViewHolder cTInboxBaseMessageViewHolder2 = tag instanceof CTInboxBaseMessageViewHolder ? (CTInboxBaseMessageViewHolder) tag : null;
                if (cTInboxBaseMessageViewHolder2 != null && cTInboxBaseMessageViewHolder2.needsMediaPlayer()) {
                    int iHeight = cTInboxBaseMessageViewHolder2.itemView.getGlobalVisibleRect(this.rect) ? this.rect.height() : 0;
                    if (iHeight > i2) {
                        cTInboxBaseMessageViewHolder = cTInboxBaseMessageViewHolder2;
                        i2 = iHeight;
                    }
                }
            }
            if (i == iFindLastVisibleItemPosition) {
                return cTInboxBaseMessageViewHolder;
            }
            i++;
        }
    }

    /* compiled from: MediaPlayerRecyclerView.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    /* renamed from: com.clevertap.android.sdk.customviews.MediaPlayerRecyclerView$initialize$1, reason: invalid class name */
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0<Unit> {
        AnonymousClass1(Object obj) {
            super(0, obj, MediaPlayerRecyclerView.class, "bufferingStarted", "bufferingStarted()V", 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            ((MediaPlayerRecyclerView) this.receiver).bufferingStarted();
        }
    }

    private final void initialize() {
        InboxVideoPlayerHandle inboxVideoPlayerHandle = this.handle;
        Context applicationContext = getContext().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
        inboxVideoPlayerHandle.initExoplayer(applicationContext, new AnonymousClass1(this), new AnonymousClass2(this));
        InboxVideoPlayerHandle inboxVideoPlayerHandle2 = this.handle;
        Context applicationContext2 = getContext().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "context.applicationContext");
        inboxVideoPlayerHandle2.initPlayerView(applicationContext2, new AnonymousClass3(this));
        recyclerViewListeners();
    }

    /* compiled from: MediaPlayerRecyclerView.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    /* renamed from: com.clevertap.android.sdk.customviews.MediaPlayerRecyclerView$initialize$2, reason: invalid class name */
    /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function0<Unit> {
        AnonymousClass2(Object obj) {
            super(0, obj, MediaPlayerRecyclerView.class, "playerReady", "playerReady()V", 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            ((MediaPlayerRecyclerView) this.receiver).playerReady();
        }
    }

    /* compiled from: MediaPlayerRecyclerView.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    /* renamed from: com.clevertap.android.sdk.customviews.MediaPlayerRecyclerView$initialize$3, reason: invalid class name */
    /* synthetic */ class AnonymousClass3 extends FunctionReferenceImpl implements Function0<Drawable> {
        AnonymousClass3(Object obj) {
            super(0, obj, MediaPlayerRecyclerView.class, "artworkAsset", "artworkAsset()Landroid/graphics/drawable/Drawable;", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Drawable invoke() {
            return ((MediaPlayerRecyclerView) this.receiver).artworkAsset();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bufferingStarted() {
        CTInboxBaseMessageViewHolder cTInboxBaseMessageViewHolder = this.playingHolder;
        if (cTInboxBaseMessageViewHolder != null) {
            cTInboxBaseMessageViewHolder.playerBuffering();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playerReady() {
        CTInboxBaseMessageViewHolder cTInboxBaseMessageViewHolder = this.playingHolder;
        if (cTInboxBaseMessageViewHolder != null) {
            cTInboxBaseMessageViewHolder.playerReady();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Drawable artworkAsset() throws Resources.NotFoundException {
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ct_audio, null);
        Intrinsics.checkNotNull(drawable);
        return drawable;
    }

    private final void recyclerViewListeners() {
        removeOnScrollListener(this.onScrollListener);
        removeOnChildAttachStateChangeListener(this.onChildAttachStateChangeListener);
        addOnScrollListener(this.onScrollListener);
        addOnChildAttachStateChangeListener(this.onChildAttachStateChangeListener);
    }

    private final void removeVideoView() {
        this.handle.pause();
        CTInboxBaseMessageViewHolder cTInboxBaseMessageViewHolder = this.playingHolder;
        if (cTInboxBaseMessageViewHolder != null) {
            cTInboxBaseMessageViewHolder.playerRemoved();
        }
    }
}
