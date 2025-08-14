package io.grpc.internal;

import java.io.InputStream;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
public interface StreamListener {

    public interface MessageProducer {
        @Nullable
        InputStream next();
    }

    void messagesAvailable(MessageProducer messageProducer);

    void onReady();
}
