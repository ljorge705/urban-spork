package com.google.common.collect;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
class EmptyImmutableListMultimap extends ImmutableListMultimap<Object, Object> {
    static final EmptyImmutableListMultimap INSTANCE = new EmptyImmutableListMultimap();
    private static final long serialVersionUID = 0;

    private Object readResolve() {
        return INSTANCE;
    }

    private EmptyImmutableListMultimap() {
        super(ImmutableMap.of(), 0);
    }
}
