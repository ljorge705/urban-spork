package org.tensorflow.lite;

import java.io.File;
import java.nio.ByteBuffer;
import org.tensorflow.lite.InterpreterApi;

@Deprecated
/* loaded from: classes7.dex */
public class InterpreterFactory {
    public InterpreterApi create(File modelFile, InterpreterApi.Options options) {
        return InterpreterApi.create(modelFile, options);
    }

    public InterpreterApi create(ByteBuffer byteBuffer, InterpreterApi.Options options) {
        return InterpreterApi.create(byteBuffer, options);
    }
}
