package org.tensorflow.lite.support.model;

import android.content.Context;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.util.Map;
import org.tensorflow.lite.InterpreterApi;
import org.tensorflow.lite.Tensor;
import org.tensorflow.lite.support.common.FileUtil;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;

/* loaded from: classes7.dex */
public class Model {
    private final MappedByteBuffer byteModel;
    private final GpuDelegateProxy gpuDelegateProxy;
    private final InterpreterApi interpreter;
    private final String modelPath;

    public enum Device {
        CPU,
        NNAPI,
        GPU
    }

    public MappedByteBuffer getData() {
        return this.byteModel;
    }

    public String getPath() {
        return this.modelPath;
    }

    public static class Options {
        private final Device device;
        private final int numThreads;
        private final InterpreterApi.Options.TfLiteRuntime tfLiteRuntime;

        /* synthetic */ Options(Builder builder, AnonymousClass1 anonymousClass1) {
            this(builder);
        }

        public static class Builder {
            private Device device = Device.CPU;
            private int numThreads = 1;
            private InterpreterApi.Options.TfLiteRuntime tfLiteRuntime;

            public Builder setDevice(Device device) {
                this.device = device;
                return this;
            }

            public Builder setNumThreads(int numThreads) {
                this.numThreads = numThreads;
                return this;
            }

            public Builder setTfLiteRuntime(InterpreterApi.Options.TfLiteRuntime tfLiteRuntime) {
                this.tfLiteRuntime = tfLiteRuntime;
                return this;
            }

            public Options build() {
                return new Options(this, null);
            }
        }

        private Options(Builder builder) {
            this.device = builder.device;
            this.numThreads = builder.numThreads;
            this.tfLiteRuntime = builder.tfLiteRuntime;
        }
    }

    @Deprecated
    public static class Builder {
        private final MappedByteBuffer byteModel;
        private final String modelPath;
        private Device device = Device.CPU;
        private int numThreads = 1;

        public Builder setDevice(Device device) {
            this.device = device;
            return this;
        }

        public Builder setNumThreads(int numThreads) {
            this.numThreads = numThreads;
            return this;
        }

        public Builder(Context context, String modelPath) throws IOException {
            this.modelPath = modelPath;
            this.byteModel = FileUtil.loadMappedFile(context, modelPath);
        }

        public Model build() {
            return Model.createModel(this.byteModel, this.modelPath, new Options.Builder().setNumThreads(this.numThreads).setDevice(this.device).build());
        }
    }

    public static Model createModel(Context context, String modelPath) throws IOException {
        return createModel(context, modelPath, new Options.Builder().build());
    }

    public static Model createModel(Context context, String modelPath, Options options) throws IOException {
        SupportPreconditions.checkNotEmpty(modelPath, "Model path in the asset folder cannot be empty.");
        return createModel(FileUtil.loadMappedFile(context, modelPath), modelPath, options);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.tensorflow.lite.support.model.Model createModel(java.nio.MappedByteBuffer r4, java.lang.String r5, org.tensorflow.lite.support.model.Model.Options r6) {
        /*
            org.tensorflow.lite.InterpreterApi$Options r0 = new org.tensorflow.lite.InterpreterApi$Options
            r0.<init>()
            int[] r1 = org.tensorflow.lite.support.model.Model.AnonymousClass1.$SwitchMap$org$tensorflow$lite$support$model$Model$Device
            org.tensorflow.lite.support.model.Model$Device r2 = org.tensorflow.lite.support.model.Model.Options.access$400(r6)
            int r2 = r2.ordinal()
            r1 = r1[r2]
            r2 = 1
            if (r1 == r2) goto L29
            r3 = 2
            if (r1 == r3) goto L18
            goto L2c
        L18:
            org.tensorflow.lite.support.model.GpuDelegateProxy r1 = org.tensorflow.lite.support.model.GpuDelegateProxy.maybeNewInstance()
            if (r1 == 0) goto L1f
            goto L20
        L1f:
            r2 = 0
        L20:
            java.lang.String r3 = "Cannot inference with GPU. Did you add \"tensorflow-lite-gpu\" as dependency?"
            org.tensorflow.lite.support.common.internal.SupportPreconditions.checkArgument(r2, r3)
            r0.addDelegate(r1)
            goto L2d
        L29:
            r0.setUseNNAPI(r2)
        L2c:
            r1 = 0
        L2d:
            int r2 = org.tensorflow.lite.support.model.Model.Options.access$500(r6)
            r0.setNumThreads(r2)
            org.tensorflow.lite.InterpreterApi$Options$TfLiteRuntime r2 = org.tensorflow.lite.support.model.Model.Options.access$600(r6)
            if (r2 == 0) goto L41
            org.tensorflow.lite.InterpreterApi$Options$TfLiteRuntime r6 = org.tensorflow.lite.support.model.Model.Options.access$600(r6)
            r0.setRuntime(r6)
        L41:
            org.tensorflow.lite.InterpreterApi r6 = org.tensorflow.lite.InterpreterApi.create(r4, r0)
            org.tensorflow.lite.support.model.Model r0 = new org.tensorflow.lite.support.model.Model
            r0.<init>(r5, r4, r6, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tensorflow.lite.support.model.Model.createModel(java.nio.MappedByteBuffer, java.lang.String, org.tensorflow.lite.support.model.Model$Options):org.tensorflow.lite.support.model.Model");
    }

    /* renamed from: org.tensorflow.lite.support.model.Model$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$tensorflow$lite$support$model$Model$Device;

        static {
            int[] iArr = new int[Device.values().length];
            $SwitchMap$org$tensorflow$lite$support$model$Model$Device = iArr;
            try {
                iArr[Device.NNAPI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$tensorflow$lite$support$model$Model$Device[Device.GPU.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$tensorflow$lite$support$model$Model$Device[Device.CPU.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public Tensor getInputTensor(int inputIndex) {
        return this.interpreter.getInputTensor(inputIndex);
    }

    public Tensor getOutputTensor(int outputIndex) {
        return this.interpreter.getOutputTensor(outputIndex);
    }

    public int[] getOutputTensorShape(int outputIndex) {
        return this.interpreter.getOutputTensor(outputIndex).shape();
    }

    public void run(Object[] inputs, Map<Integer, Object> outputs) {
        this.interpreter.runForMultipleInputsOutputs(inputs, outputs);
    }

    public void close() throws IOException {
        InterpreterApi interpreterApi = this.interpreter;
        if (interpreterApi != null) {
            interpreterApi.close();
        }
        GpuDelegateProxy gpuDelegateProxy = this.gpuDelegateProxy;
        if (gpuDelegateProxy != null) {
            gpuDelegateProxy.close();
        }
    }

    private Model(String modelPath, MappedByteBuffer byteModel, InterpreterApi interpreter, GpuDelegateProxy gpuDelegateProxy) {
        this.modelPath = modelPath;
        this.byteModel = byteModel;
        this.interpreter = interpreter;
        this.gpuDelegateProxy = gpuDelegateProxy;
    }
}
