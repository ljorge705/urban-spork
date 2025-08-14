package androidx.camera.core.processing;

/* loaded from: classes.dex */
public interface Node<I, O> {
    void release();

    O transform(I i);
}
