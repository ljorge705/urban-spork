package com.uxcam.screenshot.viewocclusion;

import android.webkit.WebView;
import com.uxcam.screenshot.model.UXCamOccludeAllTextFields;
import java.lang.ref.WeakReference;

/* loaded from: classes6.dex */
public class WebViewOcclusionImpl implements WebViewOcclusion {
    @Override // com.uxcam.screenshot.viewocclusion.WebViewOcclusion
    public final void a(WeakReference weakReference, UXCamOccludeAllTextFields uXCamOccludeAllTextFields) {
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        int[] iArr = new int[2];
        ((WebView) weakReference.get()).getLocationOnScreen(iArr);
        ((WebView) weakReference.get()).evaluateJavascript("!function(){var e=$$webviewX,t=$$webviewY,n=$$occludeAllTextFields;var i=[];function o(n,o){var a=n.left*o*window.devicePixelRatio+window.screenX+e,c=n.top*o*window.devicePixelRatio+window.screenY+t,r=n.right*o*window.devicePixelRatio+window.screenX+e,w=n.bottom*o*window.devicePixelRatio+window.screenY+t;i.push([parseInt(a),parseInt(c),parseInt(r),parseInt(w)])}!function(){var e,t=document.getElementsByClassName(\"uxcam-occlude\");for(e=0;e<t.length;e++){var n=t[e].getBoundingClientRect(),i=t[e].style.zoom;i||(i=1),o(n,i)}}(),function(){var e,t=document.getElementsByTagName(\"input\");for(e=0;e<t.length;e++){var i=t[e].getBoundingClientRect();if(\"password\"===t[e].type.toLowerCase()||n){var a=t[e].style.zoom;a||(a=1),o(i,a)}}}();try{UXCam.occludeRectsOnNextFrame(JSON.stringify(i))}catch(e){}}();".replace("$$webviewX", String.valueOf(iArr[0])).replace("$$webviewY", String.valueOf(iArr[1])).replace("$$occludeAllTextFields", String.valueOf(uXCamOccludeAllTextFields != null)), null);
    }
}
