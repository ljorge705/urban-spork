package io.perfmark;

import javax.annotation.Nullable;

/* loaded from: classes6.dex */
public class Impl {
    private static final long NO_LINK_ID = Long.MIN_VALUE;
    static final long NO_TAG_ID = Long.MIN_VALUE;
    static final String NO_TAG_NAME = "";
    static final Tag NO_TAG = new Tag("", Long.MIN_VALUE);
    static final Link NO_LINK = new Link(Long.MIN_VALUE);

    protected void attachTag(Tag tag) {
    }

    protected void attachTag(String str, long j) {
    }

    protected void attachTag(String str, long j, long j2) {
    }

    protected <T> void attachTag(String str, T t, StringFunction<? super T> stringFunction) {
    }

    protected void attachTag(String str, String str2) {
    }

    protected Tag createTag(@Nullable String str, long j) {
        return NO_TAG;
    }

    protected void event(String str) {
    }

    protected void event(String str, Tag tag) {
    }

    protected void event(String str, String str2) {
    }

    protected void linkIn(Link link) {
    }

    protected Link linkOut() {
        return NO_LINK;
    }

    protected void setEnabled(boolean z) {
    }

    protected <T> void startTask(T t, StringFunction<? super T> stringFunction) {
    }

    protected void startTask(String str) {
    }

    protected void startTask(String str, Tag tag) {
    }

    protected void startTask(String str, String str2) {
    }

    protected void stopTask() {
    }

    protected void stopTask(String str) {
    }

    protected void stopTask(String str, Tag tag) {
    }

    protected void stopTask(String str, String str2) {
    }

    protected Impl(Tag tag) {
        if (tag != NO_TAG) {
            throw new AssertionError("nope");
        }
    }

    @Nullable
    protected static String unpackTagName(Tag tag) {
        return tag.tagName;
    }

    protected static long unpackTagId(Tag tag) {
        return tag.tagId;
    }

    protected static long unpackLinkId(Link link) {
        return link.linkId;
    }

    protected static Tag packTag(@Nullable String str, long j) {
        return new Tag(str, j);
    }

    protected static Link packLink(long j) {
        return new Link(j);
    }
}
