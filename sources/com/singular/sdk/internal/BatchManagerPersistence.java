package com.singular.sdk.internal;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/* loaded from: classes6.dex */
public interface BatchManagerPersistence {
    boolean addEvent(String str, String str2) throws IOException;

    boolean deleteEvent(String str) throws IOException;

    Set<Map.Entry<String, String>> getAllEvents();

    long getSendId();

    long incSendId();

    boolean updateEvent(String str, String str2) throws IOException;
}
