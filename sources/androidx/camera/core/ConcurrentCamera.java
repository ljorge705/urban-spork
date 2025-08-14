package androidx.camera.core;

import androidx.lifecycle.LifecycleOwner;
import java.util.List;

/* loaded from: classes.dex */
public class ConcurrentCamera {
    private List<Camera> mCameras;

    public List<Camera> getCameras() {
        return this.mCameras;
    }

    public ConcurrentCamera(List<Camera> list) {
        this.mCameras = list;
    }

    public static final class SingleCameraConfig {
        private CameraSelector mCameraSelector;
        private LifecycleOwner mLifecycleOwner;
        private UseCaseGroup mUseCaseGroup;

        public CameraSelector getCameraSelector() {
            return this.mCameraSelector;
        }

        public LifecycleOwner getLifecycleOwner() {
            return this.mLifecycleOwner;
        }

        public UseCaseGroup getUseCaseGroup() {
            return this.mUseCaseGroup;
        }

        public SingleCameraConfig(CameraSelector cameraSelector, UseCaseGroup useCaseGroup, LifecycleOwner lifecycleOwner) {
            this.mCameraSelector = cameraSelector;
            this.mUseCaseGroup = useCaseGroup;
            this.mLifecycleOwner = lifecycleOwner;
        }
    }
}
