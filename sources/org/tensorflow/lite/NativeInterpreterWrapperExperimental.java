package org.tensorflow.lite;

import java.nio.ByteBuffer;
import org.tensorflow.lite.InterpreterImpl;

/* loaded from: classes7.dex */
final class NativeInterpreterWrapperExperimental extends NativeInterpreterWrapper {
    private static native void resetVariableTensors(long interpreterHandle, long errorHandle);

    NativeInterpreterWrapperExperimental(String modelPath, InterpreterImpl.Options options) {
        super(modelPath, options);
    }

    NativeInterpreterWrapperExperimental(ByteBuffer buffer, InterpreterImpl.Options options) {
        super(buffer, options);
    }

    void resetVariableTensors() {
        resetVariableTensors(this.interpreterHandle, this.errorHandle);
    }
}
