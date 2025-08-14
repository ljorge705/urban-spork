package androidx.camera.viewfinder;

import android.view.Surface;
import androidx.camera.viewfinder.ViewfinderSurfaceRequest;

/* loaded from: classes.dex */
final class AutoValue_ViewfinderSurfaceRequest_Result extends ViewfinderSurfaceRequest.Result {
    private final int resultCode;
    private final Surface surface;

    @Override // androidx.camera.viewfinder.ViewfinderSurfaceRequest.Result
    public int getResultCode() {
        return this.resultCode;
    }

    @Override // androidx.camera.viewfinder.ViewfinderSurfaceRequest.Result
    public Surface getSurface() {
        return this.surface;
    }

    AutoValue_ViewfinderSurfaceRequest_Result(int i, Surface surface) {
        this.resultCode = i;
        if (surface == null) {
            throw new NullPointerException("Null surface");
        }
        this.surface = surface;
    }

    public String toString() {
        return "Result{resultCode=" + this.resultCode + ", surface=" + this.surface + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ViewfinderSurfaceRequest.Result)) {
            return false;
        }
        ViewfinderSurfaceRequest.Result result = (ViewfinderSurfaceRequest.Result) obj;
        return this.resultCode == result.getResultCode() && this.surface.equals(result.getSurface());
    }

    public int hashCode() {
        return ((this.resultCode ^ 1000003) * 1000003) ^ this.surface.hashCode();
    }
}
