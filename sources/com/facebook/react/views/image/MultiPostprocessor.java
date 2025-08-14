package com.facebook.react.views.image;

import android.graphics.Bitmap;
import com.clevertap.android.sdk.Constants;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.MultiCacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.request.Postprocessor;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes5.dex */
public class MultiPostprocessor implements Postprocessor {
    private final List<Postprocessor> mPostprocessors;

    public static Postprocessor from(List<Postprocessor> list) {
        int size = list.size();
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            return list.get(0);
        }
        return new MultiPostprocessor(list);
    }

    private MultiPostprocessor(List<Postprocessor> list) {
        this.mPostprocessors = new LinkedList(list);
    }

    @Override // com.facebook.imagepipeline.request.Postprocessor
    public String getName() {
        StringBuilder sb = new StringBuilder();
        for (Postprocessor postprocessor : this.mPostprocessors) {
            if (sb.length() > 0) {
                sb.append(Constants.SEPARATOR_COMMA);
            }
            sb.append(postprocessor.getName());
        }
        sb.insert(0, "MultiPostProcessor (");
        sb.append(")");
        return sb.toString();
    }

    @Override // com.facebook.imagepipeline.request.Postprocessor
    public CacheKey getPostprocessorCacheKey() {
        LinkedList linkedList = new LinkedList();
        Iterator<Postprocessor> it = this.mPostprocessors.iterator();
        while (it.hasNext()) {
            linkedList.push(it.next().getPostprocessorCacheKey());
        }
        return new MultiCacheKey(linkedList);
    }

    @Override // com.facebook.imagepipeline.request.Postprocessor
    public CloseableReference<Bitmap> process(Bitmap bitmap, PlatformBitmapFactory platformBitmapFactory) {
        CloseableReference<Bitmap> closeableReferenceProcess = null;
        try {
            Iterator<Postprocessor> it = this.mPostprocessors.iterator();
            CloseableReference<Bitmap> closeableReferenceMo4815clone = null;
            while (it.hasNext()) {
                closeableReferenceProcess = it.next().process(closeableReferenceMo4815clone != null ? closeableReferenceMo4815clone.get() : bitmap, platformBitmapFactory);
                CloseableReference.closeSafely(closeableReferenceMo4815clone);
                closeableReferenceMo4815clone = closeableReferenceProcess.mo4815clone();
            }
            return closeableReferenceProcess.mo4815clone();
        } finally {
            CloseableReference.closeSafely(closeableReferenceProcess);
        }
    }
}
