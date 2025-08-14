package com.facebook.imagepipeline.core;

import android.net.Uri;
import bolts.Continuation;
import bolts.Task;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.facebook.cache.common.CacheKey;
import com.facebook.callercontext.CallerContextVerifier;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Predicate;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSources;
import com.facebook.datasource.SimpleDataSource;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.datasource.CloseableProducerToDataSourceAdapter;
import com.facebook.imagepipeline.datasource.ProducerToDataSourceAdapter;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import com.facebook.imagepipeline.listener.ForwardingRequestListener2;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestListener2;
import com.facebook.imagepipeline.producers.InternalRequestListener;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

/* loaded from: classes5.dex */
public class ImagePipeline {
    private static final CancellationException PREFETCH_EXCEPTION = new CancellationException("Prefetching is not enabled");
    private final MemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache;
    private final CacheKeyFactory mCacheKeyFactory;

    @Nullable
    private final CallerContextVerifier mCallerContextVerifier;
    private final ImagePipelineConfigInterface mConfig;
    private final MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;
    private AtomicLong mIdCounter = new AtomicLong();
    private final Supplier<Boolean> mIsPrefetchEnabledSupplier;
    private final Supplier<Boolean> mLazyDataSource;
    private final BufferedDiskCache mMainBufferedDiskCache;
    private final ProducerSequenceFactory mProducerSequenceFactory;
    private final RequestListener mRequestListener;
    private final RequestListener2 mRequestListener2;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;
    private final Supplier<Boolean> mSuppressBitmapPrefetchingSupplier;
    private final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;

    public MemoryCache<CacheKey, CloseableImage> getBitmapMemoryCache() {
        return this.mBitmapMemoryCache;
    }

    public CacheKeyFactory getCacheKeyFactory() {
        return this.mCacheKeyFactory;
    }

    public ImagePipelineConfigInterface getConfig() {
        return this.mConfig;
    }

    public ProducerSequenceFactory getProducerSequenceFactory() {
        return this.mProducerSequenceFactory;
    }

    public void init() {
    }

    public Supplier<Boolean> isLazyDataSource() {
        return this.mLazyDataSource;
    }

    public ImagePipeline(ProducerSequenceFactory producerSequenceFactory, Set<RequestListener> set, Set<RequestListener2> set2, Supplier<Boolean> supplier, MemoryCache<CacheKey, CloseableImage> memoryCache, MemoryCache<CacheKey, PooledByteBuffer> memoryCache2, BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory, ThreadHandoffProducerQueue threadHandoffProducerQueue, Supplier<Boolean> supplier2, Supplier<Boolean> supplier3, @Nullable CallerContextVerifier callerContextVerifier, ImagePipelineConfigInterface imagePipelineConfigInterface) {
        this.mProducerSequenceFactory = producerSequenceFactory;
        this.mRequestListener = new ForwardingRequestListener(set);
        this.mRequestListener2 = new ForwardingRequestListener2(set2);
        this.mIsPrefetchEnabledSupplier = supplier;
        this.mBitmapMemoryCache = memoryCache;
        this.mEncodedMemoryCache = memoryCache2;
        this.mMainBufferedDiskCache = bufferedDiskCache;
        this.mSmallImageBufferedDiskCache = bufferedDiskCache2;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mThreadHandoffProducerQueue = threadHandoffProducerQueue;
        this.mSuppressBitmapPrefetchingSupplier = supplier2;
        this.mLazyDataSource = supplier3;
        this.mCallerContextVerifier = callerContextVerifier;
        this.mConfig = imagePipelineConfigInterface;
    }

    public String generateUniqueFutureId() {
        return String.valueOf(this.mIdCounter.getAndIncrement());
    }

    public Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(final ImageRequest imageRequest, @Nullable final Object obj, final ImageRequest.RequestLevel requestLevel) {
        return new Supplier<DataSource<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<CloseableImage>> get() {
                return ImagePipeline.this.fetchDecodedImage(imageRequest, obj, requestLevel);
            }

            public String toString() {
                return Objects.toStringHelper(this).add(ReactNativeBlobUtilConst.DATA_ENCODE_URI, imageRequest.getSourceUri()).toString();
            }
        };
    }

    public Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(final ImageRequest imageRequest, @Nullable final Object obj, final ImageRequest.RequestLevel requestLevel, @Nullable final RequestListener requestListener) {
        return new Supplier<DataSource<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<CloseableImage>> get() {
                return ImagePipeline.this.fetchDecodedImage(imageRequest, obj, requestLevel, requestListener);
            }

            public String toString() {
                return Objects.toStringHelper(this).add(ReactNativeBlobUtilConst.DATA_ENCODE_URI, imageRequest.getSourceUri()).toString();
            }
        };
    }

    public Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(final ImageRequest imageRequest, @Nullable final Object obj, final ImageRequest.RequestLevel requestLevel, @Nullable final RequestListener requestListener, @Nullable final String str) {
        return new Supplier<DataSource<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<CloseableImage>> get() {
                return ImagePipeline.this.fetchDecodedImage(imageRequest, obj, requestLevel, requestListener, str);
            }

            public String toString() {
                return Objects.toStringHelper(this).add(ReactNativeBlobUtilConst.DATA_ENCODE_URI, imageRequest.getSourceUri()).toString();
            }
        };
    }

    public Supplier<DataSource<CloseableReference<PooledByteBuffer>>> getEncodedImageDataSourceSupplier(final ImageRequest imageRequest, @Nullable final Object obj) {
        return new Supplier<DataSource<CloseableReference<PooledByteBuffer>>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<PooledByteBuffer>> get() {
                return ImagePipeline.this.fetchEncodedImage(imageRequest, obj);
            }

            public String toString() {
                return Objects.toStringHelper(this).add(ReactNativeBlobUtilConst.DATA_ENCODE_URI, imageRequest.getSourceUri()).toString();
            }
        };
    }

    public DataSource<CloseableReference<CloseableImage>> fetchImageFromBitmapCache(ImageRequest imageRequest, @Nullable Object obj) {
        return fetchDecodedImage(imageRequest, obj, ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(@Nullable ImageRequest imageRequest, @Nullable Object obj) {
        return fetchDecodedImage(imageRequest, obj, ImageRequest.RequestLevel.FULL_FETCH);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, @Nullable Object obj, @Nullable RequestListener requestListener) {
        return fetchDecodedImage(imageRequest, obj, ImageRequest.RequestLevel.FULL_FETCH, requestListener);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(@Nullable ImageRequest imageRequest, @Nullable Object obj, ImageRequest.RequestLevel requestLevel) {
        return fetchDecodedImage(imageRequest, obj, requestLevel, null);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(@Nullable ImageRequest imageRequest, @Nullable Object obj, ImageRequest.RequestLevel requestLevel, @Nullable RequestListener requestListener) {
        return fetchDecodedImage(imageRequest, obj, requestLevel, requestListener, null);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(@Nullable ImageRequest imageRequest, @Nullable Object obj, ImageRequest.RequestLevel requestLevel, @Nullable RequestListener requestListener, @Nullable String str) {
        try {
            Preconditions.checkNotNull(imageRequest);
            return submitFetchRequest(this.mProducerSequenceFactory.getDecodedImageProducerSequence(imageRequest), imageRequest, requestLevel, obj, requestListener, str);
        } catch (Exception e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, @Nullable Object obj, ImageRequest.RequestLevel requestLevel, @Nullable RequestListener requestListener, @Nullable String str, @Nullable Map<String, ?> map) {
        try {
            return submitFetchRequest(this.mProducerSequenceFactory.getDecodedImageProducerSequence(imageRequest), imageRequest, requestLevel, obj, requestListener, str, map);
        } catch (Exception e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public DataSource<CloseableReference<PooledByteBuffer>> fetchEncodedImage(ImageRequest imageRequest, @Nullable Object obj) {
        return fetchEncodedImage(imageRequest, obj, null);
    }

    public DataSource<CloseableReference<PooledByteBuffer>> fetchEncodedImage(ImageRequest imageRequest, @Nullable Object obj, @Nullable RequestListener requestListener) {
        Preconditions.checkNotNull(imageRequest.getSourceUri());
        try {
            Producer<CloseableReference<PooledByteBuffer>> encodedImageProducerSequence = this.mProducerSequenceFactory.getEncodedImageProducerSequence(imageRequest);
            if (imageRequest.getResizeOptions() != null) {
                imageRequest = ImageRequestBuilder.fromRequest(imageRequest).setResizeOptions(null).build();
            }
            return submitFetchRequest(encodedImageProducerSequence, imageRequest, ImageRequest.RequestLevel.FULL_FETCH, obj, requestListener, null, null);
        } catch (Exception e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public DataSource<Void> prefetchToBitmapCache(ImageRequest imageRequest, @Nullable Object obj) {
        return prefetchToBitmapCache(imageRequest, obj, null);
    }

    public DataSource<Void> prefetchToBitmapCache(ImageRequest imageRequest, @Nullable Object obj, @Nullable RequestListener requestListener) {
        Producer<Void> encodedImagePrefetchProducerSequence;
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("ImagePipeline#prefetchToBitmapCache");
            }
            if (!this.mIsPrefetchEnabledSupplier.get().booleanValue()) {
                DataSource<Void> dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return dataSourceImmediateFailedDataSource;
            }
            try {
                if (this.mConfig.getExperiments() != null && this.mConfig.getExperiments().getPrefetchShortcutEnabled() && isInBitmapMemoryCache(imageRequest)) {
                    DataSource<Void> dataSourceImmediateSuccessfulDataSource = DataSources.immediateSuccessfulDataSource();
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                    return dataSourceImmediateSuccessfulDataSource;
                }
                Boolean boolShouldDecodePrefetches = imageRequest.shouldDecodePrefetches();
                if (boolShouldDecodePrefetches != null) {
                    if (!boolShouldDecodePrefetches.booleanValue()) {
                    }
                } else {
                    encodedImagePrefetchProducerSequence = this.mSuppressBitmapPrefetchingSupplier.get().booleanValue() ? this.mProducerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest) : this.mProducerSequenceFactory.getDecodedImagePrefetchProducerSequence(imageRequest);
                }
                DataSource<Void> dataSourceSubmitPrefetchRequest = submitPrefetchRequest(encodedImagePrefetchProducerSequence, imageRequest, ImageRequest.RequestLevel.FULL_FETCH, obj, Priority.MEDIUM, requestListener);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return dataSourceSubmitPrefetchRequest;
            } catch (Exception e) {
                DataSource<Void> dataSourceImmediateFailedDataSource2 = DataSources.immediateFailedDataSource(e);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return dataSourceImmediateFailedDataSource2;
            }
        } catch (Throwable th) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th;
        }
    }

    public DataSource<Void> prefetchToDiskCache(@Nullable ImageRequest imageRequest, @Nullable Object obj) {
        return prefetchToDiskCache(imageRequest, obj, Priority.MEDIUM);
    }

    public DataSource<Void> prefetchToDiskCache(@Nullable ImageRequest imageRequest, @Nullable Object obj, @Nullable RequestListener requestListener) {
        return prefetchToDiskCache(imageRequest, obj, Priority.MEDIUM, requestListener);
    }

    public DataSource<Void> prefetchToDiskCache(@Nullable ImageRequest imageRequest, @Nullable Object obj, Priority priority) {
        return prefetchToDiskCache(imageRequest, obj, priority, null);
    }

    public DataSource<Void> prefetchToDiskCache(@Nullable ImageRequest imageRequest, @Nullable Object obj, Priority priority, @Nullable RequestListener requestListener) {
        if (!this.mIsPrefetchEnabledSupplier.get().booleanValue()) {
            return DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
        }
        if (imageRequest == null) {
            return DataSources.immediateFailedDataSource(new NullPointerException("imageRequest is null"));
        }
        try {
            return submitPrefetchRequest(this.mProducerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest.RequestLevel.FULL_FETCH, obj, priority, requestListener);
        } catch (Exception e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, @Nullable Object obj) {
        return prefetchToEncodedCache(imageRequest, obj, Priority.MEDIUM);
    }

    public DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, @Nullable Object obj, @Nullable RequestListener requestListener) {
        return prefetchToEncodedCache(imageRequest, obj, Priority.MEDIUM, requestListener);
    }

    public DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, @Nullable Object obj, Priority priority) {
        return prefetchToEncodedCache(imageRequest, obj, priority, null);
    }

    public DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, @Nullable Object obj, Priority priority, @Nullable RequestListener requestListener) {
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("ImagePipeline#prefetchToEncodedCache");
            }
            if (!this.mIsPrefetchEnabledSupplier.get().booleanValue()) {
                DataSource<Void> dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return dataSourceImmediateFailedDataSource;
            }
            try {
                if (this.mConfig.getExperiments() != null && this.mConfig.getExperiments().getPrefetchShortcutEnabled() && isInEncodedMemoryCache(imageRequest)) {
                    DataSource<Void> dataSourceImmediateSuccessfulDataSource = DataSources.immediateSuccessfulDataSource();
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                    return dataSourceImmediateSuccessfulDataSource;
                }
                DataSource<Void> dataSourceSubmitPrefetchRequest = submitPrefetchRequest(this.mProducerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest.RequestLevel.FULL_FETCH, obj, priority, requestListener);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return dataSourceSubmitPrefetchRequest;
            } catch (Exception e) {
                DataSource<Void> dataSourceImmediateFailedDataSource2 = DataSources.immediateFailedDataSource(e);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return dataSourceImmediateFailedDataSource2;
            }
        } catch (Throwable th) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th;
        }
    }

    public void evictFromMemoryCache(Uri uri) {
        Predicate<CacheKey> predicatePredicateForUri = predicateForUri(uri);
        this.mBitmapMemoryCache.removeAll(predicatePredicateForUri);
        this.mEncodedMemoryCache.removeAll(predicatePredicateForUri);
    }

    public void evictFromDiskCache(Uri uri) {
        evictFromDiskCache((ImageRequest) Preconditions.checkNotNull(ImageRequest.fromUri(uri)));
    }

    public void evictFromDiskCache(@Nullable ImageRequest imageRequest) {
        if (imageRequest == null) {
            return;
        }
        CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, null);
        this.mMainBufferedDiskCache.remove(encodedCacheKey);
        this.mSmallImageBufferedDiskCache.remove(encodedCacheKey);
    }

    public void evictFromCache(Uri uri) {
        evictFromMemoryCache(uri);
        evictFromDiskCache(uri);
    }

    public void clearMemoryCaches() {
        Predicate<CacheKey> predicate = new Predicate<CacheKey>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.5
            @Override // com.facebook.common.internal.Predicate
            public boolean apply(CacheKey cacheKey) {
                return true;
            }
        };
        this.mBitmapMemoryCache.removeAll(predicate);
        this.mEncodedMemoryCache.removeAll(predicate);
    }

    public void clearDiskCaches() {
        this.mMainBufferedDiskCache.clearAll();
        this.mSmallImageBufferedDiskCache.clearAll();
    }

    public long getUsedDiskCacheSize() {
        return this.mMainBufferedDiskCache.getSize() + this.mSmallImageBufferedDiskCache.getSize();
    }

    public void clearCaches() {
        clearMemoryCaches();
        clearDiskCaches();
    }

    public boolean isInBitmapMemoryCache(Uri uri) {
        if (uri == null) {
            return false;
        }
        return this.mBitmapMemoryCache.contains(predicateForUri(uri));
    }

    public boolean isInBitmapMemoryCache(ImageRequest imageRequest) {
        if (imageRequest == null) {
            return false;
        }
        CloseableReference<CloseableImage> closeableReference = this.mBitmapMemoryCache.get(this.mCacheKeyFactory.getBitmapCacheKey(imageRequest, null));
        try {
            return CloseableReference.isValid(closeableReference);
        } finally {
            CloseableReference.closeSafely(closeableReference);
        }
    }

    public boolean isInEncodedMemoryCache(Uri uri) {
        if (uri == null) {
            return false;
        }
        return this.mEncodedMemoryCache.contains(predicateForUri(uri));
    }

    public boolean isInEncodedMemoryCache(ImageRequest imageRequest) {
        if (imageRequest == null) {
            return false;
        }
        CloseableReference<PooledByteBuffer> closeableReference = this.mEncodedMemoryCache.get(this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, null));
        try {
            return CloseableReference.isValid(closeableReference);
        } finally {
            CloseableReference.closeSafely(closeableReference);
        }
    }

    public boolean isInDiskCacheSync(Uri uri) {
        return isInDiskCacheSync(uri, ImageRequest.CacheChoice.SMALL) || isInDiskCacheSync(uri, ImageRequest.CacheChoice.DEFAULT);
    }

    public boolean isInDiskCacheSync(Uri uri, ImageRequest.CacheChoice cacheChoice) {
        return isInDiskCacheSync(ImageRequestBuilder.newBuilderWithSource(uri).setCacheChoice(cacheChoice).build());
    }

    public boolean isInDiskCacheSync(ImageRequest imageRequest) {
        CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, null);
        int i = AnonymousClass9.$SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice[imageRequest.getCacheChoice().ordinal()];
        if (i == 1) {
            return this.mMainBufferedDiskCache.diskCheckSync(encodedCacheKey);
        }
        if (i != 2) {
            return false;
        }
        return this.mSmallImageBufferedDiskCache.diskCheckSync(encodedCacheKey);
    }

    /* renamed from: com.facebook.imagepipeline.core.ImagePipeline$9, reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice;

        static {
            int[] iArr = new int[ImageRequest.CacheChoice.values().length];
            $SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice = iArr;
            try {
                iArr[ImageRequest.CacheChoice.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice[ImageRequest.CacheChoice.SMALL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public DataSource<Boolean> isInDiskCache(Uri uri) {
        return isInDiskCache((ImageRequest) Preconditions.checkNotNull(ImageRequest.fromUri(uri)));
    }

    public DataSource<Boolean> isInDiskCache(ImageRequest imageRequest) {
        final CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, null);
        final SimpleDataSource simpleDataSourceCreate = SimpleDataSource.create();
        this.mMainBufferedDiskCache.contains(encodedCacheKey).continueWithTask(new Continuation<Boolean, Task<Boolean>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.7
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public Task<Boolean> then(Task<Boolean> task) throws Exception {
                if (task.isCancelled() || task.isFaulted() || !task.getResult().booleanValue()) {
                    return ImagePipeline.this.mSmallImageBufferedDiskCache.contains(encodedCacheKey);
                }
                return Task.forResult(true);
            }
        }).continueWith(new Continuation<Boolean, Void>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.6
            @Override // bolts.Continuation
            public Void then(Task<Boolean> task) throws Exception {
                simpleDataSourceCreate.setResult(Boolean.valueOf((task.isCancelled() || task.isFaulted() || !task.getResult().booleanValue()) ? false : true));
                return null;
            }
        });
        return simpleDataSourceCreate;
    }

    @Nullable
    public CacheKey getCacheKey(@Nullable ImageRequest imageRequest, @Nullable Object obj) {
        CacheKey bitmapCacheKey;
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("ImagePipeline#getCacheKey");
        }
        CacheKeyFactory cacheKeyFactory = this.mCacheKeyFactory;
        if (cacheKeyFactory == null || imageRequest == null) {
            bitmapCacheKey = null;
        } else if (imageRequest.getPostprocessor() != null) {
            bitmapCacheKey = cacheKeyFactory.getPostprocessedBitmapCacheKey(imageRequest, obj);
        } else {
            bitmapCacheKey = cacheKeyFactory.getBitmapCacheKey(imageRequest, obj);
        }
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        return bitmapCacheKey;
    }

    @Nullable
    public CloseableReference<CloseableImage> getCachedImage(@Nullable CacheKey cacheKey) {
        MemoryCache<CacheKey, CloseableImage> memoryCache = this.mBitmapMemoryCache;
        if (memoryCache == null || cacheKey == null) {
            return null;
        }
        CloseableReference<CloseableImage> closeableReference = memoryCache.get(cacheKey);
        if (closeableReference == null || closeableReference.get().getQualityInfo().isOfFullQuality()) {
            return closeableReference;
        }
        closeableReference.close();
        return null;
    }

    public boolean hasCachedImage(@Nullable CacheKey cacheKey) {
        MemoryCache<CacheKey, CloseableImage> memoryCache = this.mBitmapMemoryCache;
        if (memoryCache == null || cacheKey == null) {
            return false;
        }
        return memoryCache.contains((MemoryCache<CacheKey, CloseableImage>) cacheKey);
    }

    private <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producer, ImageRequest imageRequest, ImageRequest.RequestLevel requestLevel, @Nullable Object obj, @Nullable RequestListener requestListener, @Nullable String str) {
        return submitFetchRequest(producer, imageRequest, requestLevel, obj, requestListener, str, null);
    }

    private <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producer, ImageRequest imageRequest, ImageRequest.RequestLevel requestLevel, @Nullable Object obj, @Nullable RequestListener requestListener, @Nullable String str, @Nullable Map<String, ?> map) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("ImagePipeline#submitFetchRequest");
        }
        InternalRequestListener internalRequestListener = new InternalRequestListener(getRequestListenerForRequest(imageRequest, requestListener), this.mRequestListener2);
        CallerContextVerifier callerContextVerifier = this.mCallerContextVerifier;
        if (callerContextVerifier != null) {
            callerContextVerifier.verifyCallerContext(obj, false);
        }
        try {
            try {
                SettableProducerContext settableProducerContext = new SettableProducerContext(imageRequest, generateUniqueFutureId(), str, internalRequestListener, obj, ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), requestLevel), false, imageRequest.getProgressiveRenderingEnabled() || !UriUtil.isNetworkUri(imageRequest.getSourceUri()), imageRequest.getPriority(), this.mConfig);
                settableProducerContext.putExtras(map);
                DataSource<CloseableReference<T>> dataSourceCreate = CloseableProducerToDataSourceAdapter.create(producer, settableProducerContext, internalRequestListener);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return dataSourceCreate;
            } catch (Exception e) {
                DataSource<CloseableReference<T>> dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(e);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return dataSourceImmediateFailedDataSource;
            }
        } catch (Throwable th) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th;
        }
    }

    private <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producer, ImageRequest imageRequest, ImageRequest.RequestLevel requestLevel, @Nullable Object obj, @Nullable RequestListener requestListener, @Nullable Map<String, ?> map) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("ImagePipeline#submitFetchRequest");
        }
        InternalRequestListener internalRequestListener = new InternalRequestListener(getRequestListenerForRequest(imageRequest, requestListener), this.mRequestListener2);
        CallerContextVerifier callerContextVerifier = this.mCallerContextVerifier;
        if (callerContextVerifier != null) {
            callerContextVerifier.verifyCallerContext(obj, false);
        }
        try {
            try {
                DataSource<CloseableReference<T>> dataSourceCreate = CloseableProducerToDataSourceAdapter.create(producer, new SettableProducerContext(imageRequest, generateUniqueFutureId(), null, internalRequestListener, obj, ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), requestLevel), false, imageRequest.getProgressiveRenderingEnabled() || !UriUtil.isNetworkUri(imageRequest.getSourceUri()), imageRequest.getPriority(), this.mConfig), internalRequestListener);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return dataSourceCreate;
            } catch (Exception e) {
                DataSource<CloseableReference<T>> dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(e);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return dataSourceImmediateFailedDataSource;
            }
        } catch (Throwable th) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th;
        }
    }

    public <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producer, SettableProducerContext settableProducerContext, RequestListener requestListener) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("ImagePipeline#submitFetchRequest");
        }
        try {
            try {
                DataSource<CloseableReference<T>> dataSourceCreate = CloseableProducerToDataSourceAdapter.create(producer, settableProducerContext, new InternalRequestListener(requestListener, this.mRequestListener2));
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return dataSourceCreate;
            } catch (Exception e) {
                DataSource<CloseableReference<T>> dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(e);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return dataSourceImmediateFailedDataSource;
            }
        } catch (Throwable th) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th;
        }
    }

    private DataSource<Void> submitPrefetchRequest(Producer<Void> producer, ImageRequest imageRequest, ImageRequest.RequestLevel requestLevel, @Nullable Object obj, Priority priority, @Nullable RequestListener requestListener) {
        InternalRequestListener internalRequestListener = new InternalRequestListener(getRequestListenerForRequest(imageRequest, requestListener), this.mRequestListener2);
        CallerContextVerifier callerContextVerifier = this.mCallerContextVerifier;
        if (callerContextVerifier != null) {
            callerContextVerifier.verifyCallerContext(obj, true);
        }
        try {
            return ProducerToDataSourceAdapter.create(producer, new SettableProducerContext(imageRequest, generateUniqueFutureId(), internalRequestListener, obj, ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), requestLevel), true, this.mConfig.getExperiments() != null && this.mConfig.getExperiments().getAllowProgressiveOnPrefetch() && imageRequest.getProgressiveRenderingEnabled(), priority, this.mConfig), internalRequestListener);
        } catch (Exception e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public RequestListener getRequestListenerForRequest(ImageRequest imageRequest, @Nullable RequestListener requestListener) {
        if (requestListener == null) {
            return imageRequest.getRequestListener() == null ? this.mRequestListener : new ForwardingRequestListener(this.mRequestListener, imageRequest.getRequestListener());
        }
        if (imageRequest.getRequestListener() == null) {
            return new ForwardingRequestListener(this.mRequestListener, requestListener);
        }
        return new ForwardingRequestListener(this.mRequestListener, requestListener, imageRequest.getRequestListener());
    }

    public RequestListener getCombinedRequestListener(@Nullable RequestListener requestListener) {
        return requestListener == null ? this.mRequestListener : new ForwardingRequestListener(this.mRequestListener, requestListener);
    }

    private Predicate<CacheKey> predicateForUri(final Uri uri) {
        return new Predicate<CacheKey>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.8
            @Override // com.facebook.common.internal.Predicate
            public boolean apply(CacheKey cacheKey) {
                return cacheKey.containsUri(uri);
            }
        };
    }

    public void pause() {
        this.mThreadHandoffProducerQueue.startQueueing();
    }

    public void resume() {
        this.mThreadHandoffProducerQueue.stopQueuing();
    }

    public boolean isPaused() {
        return this.mThreadHandoffProducerQueue.isQueueing();
    }
}
