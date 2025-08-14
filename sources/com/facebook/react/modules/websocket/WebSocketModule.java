package com.facebook.react.modules.websocket;

import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeWebSocketModuleSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.network.CustomClientBuilder;
import com.facebook.react.modules.network.ForwardingCookieHandler;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.OkHttpClient;
import okhttp3.WebSocket;
import okio.ByteString;

@ReactModule(name = NativeWebSocketModuleSpec.NAME)
/* loaded from: classes5.dex */
public final class WebSocketModule extends NativeWebSocketModuleSpec {
    private static CustomClientBuilder customClientBuilder;
    private final Map<Integer, ContentHandler> mContentHandlers;
    private ForwardingCookieHandler mCookieHandler;
    private final Map<Integer, WebSocket> mWebSocketConnections;

    public interface ContentHandler {
        void onMessage(String str, WritableMap writableMap);

        void onMessage(ByteString byteString, WritableMap writableMap);
    }

    public static void setCustomClientBuilder(CustomClientBuilder customClientBuilder2) {
        customClientBuilder = customClientBuilder2;
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void addListener(String str) {
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void removeListeners(double d) {
    }

    public WebSocketModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mWebSocketConnections = new ConcurrentHashMap();
        this.mContentHandlers = new ConcurrentHashMap();
        this.mCookieHandler = new ForwardingCookieHandler(reactApplicationContext);
    }

    private static void applyCustomBuilder(OkHttpClient.Builder builder) {
        CustomClientBuilder customClientBuilder2 = customClientBuilder;
        if (customClientBuilder2 != null) {
            customClientBuilder2.apply(builder);
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void invalidate() {
        Iterator<WebSocket> it = this.mWebSocketConnections.values().iterator();
        while (it.hasNext()) {
            it.next().close(1001, null);
        }
        this.mWebSocketConnections.clear();
        this.mContentHandlers.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(String str, WritableMap writableMap) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        if (reactApplicationContext.hasActiveReactInstance()) {
            reactApplicationContext.emitDeviceEvent(str, writableMap);
        }
    }

    public void setContentHandler(int i, ContentHandler contentHandler) {
        if (contentHandler != null) {
            this.mContentHandlers.put(Integer.valueOf(i), contentHandler);
        } else {
            this.mContentHandlers.remove(Integer.valueOf(i));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00a5  */
    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void connect(java.lang.String r10, com.facebook.react.bridge.ReadableArray r11, com.facebook.react.bridge.ReadableMap r12, double r13) {
        /*
            r9 = this;
            int r13 = (int) r13
            okhttp3.OkHttpClient$Builder r14 = new okhttp3.OkHttpClient$Builder
            r14.<init>()
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS
            r1 = 10
            okhttp3.OkHttpClient$Builder r14 = r14.connectTimeout(r1, r0)
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS
            okhttp3.OkHttpClient$Builder r14 = r14.writeTimeout(r1, r0)
            r0 = 0
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MINUTES
            okhttp3.OkHttpClient$Builder r14 = r14.readTimeout(r0, r2)
            applyCustomBuilder(r14)
            okhttp3.OkHttpClient r14 = r14.build()
            okhttp3.Request$Builder r0 = new okhttp3.Request$Builder
            r0.<init>()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r13)
            okhttp3.Request$Builder r0 = r0.tag(r1)
            okhttp3.Request$Builder r0 = r0.url(r10)
            java.lang.String r1 = r9.getCookie(r10)
            if (r1 == 0) goto L3f
            java.lang.String r2 = "Cookie"
            r0.addHeader(r2, r1)
        L3f:
            java.lang.String r1 = "origin"
            r2 = 0
            r3 = 1
            if (r12 == 0) goto La5
            java.lang.String r4 = "headers"
            boolean r5 = r12.hasKey(r4)
            if (r5 == 0) goto La5
            com.facebook.react.bridge.ReadableType r5 = r12.getType(r4)
            com.facebook.react.bridge.ReadableType r6 = com.facebook.react.bridge.ReadableType.Map
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto La5
            com.facebook.react.bridge.ReadableMap r12 = r12.getMap(r4)
            com.facebook.react.bridge.ReadableMapKeySetIterator r4 = r12.keySetIterator()
            r5 = r2
        L63:
            boolean r6 = r4.hasNextKey()
            if (r6 == 0) goto La3
            java.lang.String r6 = r4.nextKey()
            com.facebook.react.bridge.ReadableType r7 = com.facebook.react.bridge.ReadableType.String
            com.facebook.react.bridge.ReadableType r8 = r12.getType(r6)
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L88
            boolean r7 = r6.equalsIgnoreCase(r1)
            if (r7 == 0) goto L80
            r5 = r3
        L80:
            java.lang.String r7 = r12.getString(r6)
            r0.addHeader(r6, r7)
            goto L63
        L88:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Ignoring: requested "
            r7.<init>(r8)
            java.lang.StringBuilder r6 = r7.append(r6)
            java.lang.String r7 = ", value not a string"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            java.lang.String r7 = "ReactNative"
            com.facebook.common.logging.FLog.w(r7, r6)
            goto L63
        La3:
            if (r5 != 0) goto Lac
        La5:
            java.lang.String r10 = getDefaultOrigin(r10)
            r0.addHeader(r1, r10)
        Lac:
            if (r11 == 0) goto Lfb
            int r10 = r11.size()
            if (r10 <= 0) goto Lfb
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r12 = ""
            r10.<init>(r12)
        Lbb:
            int r1 = r11.size()
            if (r2 >= r1) goto Le0
            java.lang.String r1 = r11.getString(r2)
            java.lang.String r1 = r1.trim()
            boolean r4 = r1.isEmpty()
            if (r4 != 0) goto Ldd
            java.lang.String r4 = ","
            boolean r5 = r1.contains(r4)
            if (r5 != 0) goto Ldd
            r10.append(r1)
            r10.append(r4)
        Ldd:
            int r2 = r2 + 1
            goto Lbb
        Le0:
            int r11 = r10.length()
            if (r11 <= 0) goto Lfb
            int r11 = r10.length()
            int r11 = r11 - r3
            int r1 = r10.length()
            r10.replace(r11, r1, r12)
            java.lang.String r11 = "Sec-WebSocket-Protocol"
            java.lang.String r10 = r10.toString()
            r0.addHeader(r11, r10)
        Lfb:
            okhttp3.Request r10 = r0.build()
            com.facebook.react.modules.websocket.WebSocketModule$1 r11 = new com.facebook.react.modules.websocket.WebSocketModule$1
            r11.<init>()
            r14.newWebSocket(r10, r11)
            okhttp3.Dispatcher r10 = r14.dispatcher()
            java.util.concurrent.ExecutorService r10 = r10.executorService()
            r10.shutdown()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.websocket.WebSocketModule.connect(java.lang.String, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.ReadableMap, double):void");
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void close(double d, String str, double d2) {
        int i = (int) d2;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            return;
        }
        try {
            webSocket.close((int) d, str);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
        } catch (Exception e) {
            FLog.e("ReactNative", "Could not close WebSocket connection for id " + i, e);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void send(String str, double d) {
        int i = (int) d;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putInt("id", i);
            writableMapCreateMap.putString("message", "client is null");
            sendEvent("websocketFailed", writableMapCreateMap);
            WritableMap writableMapCreateMap2 = Arguments.createMap();
            writableMapCreateMap2.putInt("id", i);
            writableMapCreateMap2.putInt("code", 0);
            writableMapCreateMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", writableMapCreateMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(str);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void sendBinary(String str, double d) {
        int i = (int) d;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putInt("id", i);
            writableMapCreateMap.putString("message", "client is null");
            sendEvent("websocketFailed", writableMapCreateMap);
            WritableMap writableMapCreateMap2 = Arguments.createMap();
            writableMapCreateMap2.putInt("id", i);
            writableMapCreateMap2.putInt("code", 0);
            writableMapCreateMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", writableMapCreateMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(ByteString.decodeBase64(str));
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    public void sendBinary(ByteString byteString, int i) {
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putInt("id", i);
            writableMapCreateMap.putString("message", "client is null");
            sendEvent("websocketFailed", writableMapCreateMap);
            WritableMap writableMapCreateMap2 = Arguments.createMap();
            writableMapCreateMap2.putInt("id", i);
            writableMapCreateMap2.putInt("code", 0);
            writableMapCreateMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", writableMapCreateMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(byteString);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void ping(double d) {
        int i = (int) d;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putInt("id", i);
            writableMapCreateMap.putString("message", "client is null");
            sendEvent("websocketFailed", writableMapCreateMap);
            WritableMap writableMapCreateMap2 = Arguments.createMap();
            writableMapCreateMap2.putInt("id", i);
            writableMapCreateMap2.putInt("code", 0);
            writableMapCreateMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", writableMapCreateMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(ByteString.EMPTY);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyWebSocketFailed(int i, String str) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("id", i);
        writableMapCreateMap.putString("message", str);
        sendEvent("websocketFailed", writableMapCreateMap);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String getDefaultOrigin(java.lang.String r11) {
        /*
            java.net.URI r0 = new java.net.URI     // Catch: java.net.URISyntaxException -> La1
            r0.<init>(r11)     // Catch: java.net.URISyntaxException -> La1
            java.lang.String r1 = r0.getScheme()     // Catch: java.net.URISyntaxException -> La1
            int r2 = r1.hashCode()     // Catch: java.net.URISyntaxException -> La1
            r3 = 3804(0xedc, float:5.33E-42)
            java.lang.String r4 = "https"
            java.lang.String r5 = "http"
            r6 = -1
            r7 = 0
            r8 = 3
            r9 = 2
            r10 = 1
            if (r2 == r3) goto L45
            r3 = 118039(0x1cd17, float:1.65408E-40)
            if (r2 == r3) goto L3a
            r3 = 3213448(0x310888, float:4.503E-39)
            if (r2 == r3) goto L32
            r3 = 99617003(0x5f008eb, float:2.2572767E-35)
            if (r2 == r3) goto L2a
            goto L50
        L2a:
            boolean r1 = r1.equals(r4)     // Catch: java.net.URISyntaxException -> La1
            if (r1 == 0) goto L50
            r1 = r8
            goto L51
        L32:
            boolean r1 = r1.equals(r5)     // Catch: java.net.URISyntaxException -> La1
            if (r1 == 0) goto L50
            r1 = r9
            goto L51
        L3a:
            java.lang.String r2 = "wss"
            boolean r1 = r1.equals(r2)     // Catch: java.net.URISyntaxException -> La1
            if (r1 == 0) goto L50
            r1 = r7
            goto L51
        L45:
            java.lang.String r2 = "ws"
            boolean r1 = r1.equals(r2)     // Catch: java.net.URISyntaxException -> La1
            if (r1 == 0) goto L50
            r1 = r10
            goto L51
        L50:
            r1 = r6
        L51:
            if (r1 == 0) goto L6f
            if (r1 == r10) goto L6e
            java.lang.String r4 = ""
            if (r1 == r9) goto L5c
            if (r1 == r8) goto L5c
            goto L6f
        L5c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.net.URISyntaxException -> La1
            r1.<init>(r4)     // Catch: java.net.URISyntaxException -> La1
            java.lang.String r2 = r0.getScheme()     // Catch: java.net.URISyntaxException -> La1
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.net.URISyntaxException -> La1
            java.lang.String r4 = r1.toString()     // Catch: java.net.URISyntaxException -> La1
            goto L6f
        L6e:
            r4 = r5
        L6f:
            int r1 = r0.getPort()     // Catch: java.net.URISyntaxException -> La1
            if (r1 == r6) goto L90
            java.lang.String r1 = "%s://%s:%s"
            java.lang.Object[] r2 = new java.lang.Object[r8]     // Catch: java.net.URISyntaxException -> La1
            r2[r7] = r4     // Catch: java.net.URISyntaxException -> La1
            java.lang.String r3 = r0.getHost()     // Catch: java.net.URISyntaxException -> La1
            r2[r10] = r3     // Catch: java.net.URISyntaxException -> La1
            int r0 = r0.getPort()     // Catch: java.net.URISyntaxException -> La1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.net.URISyntaxException -> La1
            r2[r9] = r0     // Catch: java.net.URISyntaxException -> La1
            java.lang.String r11 = java.lang.String.format(r1, r2)     // Catch: java.net.URISyntaxException -> La1
            goto La0
        L90:
            java.lang.String r1 = "%s://%s"
            java.lang.Object[] r2 = new java.lang.Object[r9]     // Catch: java.net.URISyntaxException -> La1
            r2[r7] = r4     // Catch: java.net.URISyntaxException -> La1
            java.lang.String r0 = r0.getHost()     // Catch: java.net.URISyntaxException -> La1
            r2[r10] = r0     // Catch: java.net.URISyntaxException -> La1
            java.lang.String r11 = java.lang.String.format(r1, r2)     // Catch: java.net.URISyntaxException -> La1
        La0:
            return r11
        La1:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unable to set "
            r1.<init>(r2)
            java.lang.StringBuilder r11 = r1.append(r11)
            java.lang.String r1 = " as default origin header"
            java.lang.StringBuilder r11 = r11.append(r1)
            java.lang.String r11 = r11.toString()
            r0.<init>(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.websocket.WebSocketModule.getDefaultOrigin(java.lang.String):java.lang.String");
    }

    private String getCookie(String str) {
        try {
            List<String> list = this.mCookieHandler.get(new URI(getDefaultOrigin(str)), new HashMap()).get("Cookie");
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
            return null;
        } catch (IOException | URISyntaxException unused) {
            throw new IllegalArgumentException("Unable to get cookie from " + str);
        }
    }
}
