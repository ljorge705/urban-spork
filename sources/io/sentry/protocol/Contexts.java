package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.SpanContext;
import io.sentry.protocol.App;
import io.sentry.protocol.Browser;
import io.sentry.protocol.Device;
import io.sentry.protocol.Gpu;
import io.sentry.protocol.OperatingSystem;
import io.sentry.protocol.Response;
import io.sentry.protocol.SentryRuntime;
import io.sentry.util.HintUtils;
import io.sentry.util.Objects;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public final class Contexts extends ConcurrentHashMap<String, Object> implements JsonSerializable {
    public static final String REPLAY_ID = "replay_id";
    private static final long serialVersionUID = 252445813254943011L;
    private final Object responseLock = new Object();

    public Contexts() {
    }

    public Contexts(Contexts contexts) {
        for (Map.Entry<String, Object> entry : contexts.entrySet()) {
            if (entry != null) {
                Object value = entry.getValue();
                if (App.TYPE.equals(entry.getKey()) && (value instanceof App)) {
                    setApp(new App((App) value));
                } else if (Browser.TYPE.equals(entry.getKey()) && (value instanceof Browser)) {
                    setBrowser(new Browser((Browser) value));
                } else if (Device.TYPE.equals(entry.getKey()) && (value instanceof Device)) {
                    setDevice(new Device((Device) value));
                } else if (OperatingSystem.TYPE.equals(entry.getKey()) && (value instanceof OperatingSystem)) {
                    setOperatingSystem(new OperatingSystem((OperatingSystem) value));
                } else if (SentryRuntime.TYPE.equals(entry.getKey()) && (value instanceof SentryRuntime)) {
                    setRuntime(new SentryRuntime((SentryRuntime) value));
                } else if (Gpu.TYPE.equals(entry.getKey()) && (value instanceof Gpu)) {
                    setGpu(new Gpu((Gpu) value));
                } else if ("trace".equals(entry.getKey()) && (value instanceof SpanContext)) {
                    setTrace(new SpanContext((SpanContext) value));
                } else if (Response.TYPE.equals(entry.getKey()) && (value instanceof Response)) {
                    setResponse(new Response((Response) value));
                } else {
                    put(entry.getKey(), value);
                }
            }
        }
    }

    private <T> T toContextType(String str, Class<T> cls) {
        Object obj = get(str);
        if (cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        return null;
    }

    public SpanContext getTrace() {
        return (SpanContext) toContextType("trace", SpanContext.class);
    }

    public void setTrace(SpanContext spanContext) {
        Objects.requireNonNull(spanContext, "traceContext is required");
        put("trace", spanContext);
    }

    public App getApp() {
        return (App) toContextType(App.TYPE, App.class);
    }

    public void setApp(App app2) {
        put(App.TYPE, app2);
    }

    public Browser getBrowser() {
        return (Browser) toContextType(Browser.TYPE, Browser.class);
    }

    public void setBrowser(Browser browser) {
        put(Browser.TYPE, browser);
    }

    public Device getDevice() {
        return (Device) toContextType(Device.TYPE, Device.class);
    }

    public void setDevice(Device device) {
        put(Device.TYPE, device);
    }

    public OperatingSystem getOperatingSystem() {
        return (OperatingSystem) toContextType(OperatingSystem.TYPE, OperatingSystem.class);
    }

    public void setOperatingSystem(OperatingSystem operatingSystem) {
        put(OperatingSystem.TYPE, operatingSystem);
    }

    public SentryRuntime getRuntime() {
        return (SentryRuntime) toContextType(SentryRuntime.TYPE, SentryRuntime.class);
    }

    public void setRuntime(SentryRuntime sentryRuntime) {
        put(SentryRuntime.TYPE, sentryRuntime);
    }

    public Gpu getGpu() {
        return (Gpu) toContextType(Gpu.TYPE, Gpu.class);
    }

    public void setGpu(Gpu gpu) {
        put(Gpu.TYPE, gpu);
    }

    public Response getResponse() {
        return (Response) toContextType(Response.TYPE, Response.class);
    }

    public void withResponse(HintUtils.SentryConsumer<Response> sentryConsumer) {
        synchronized (this.responseLock) {
            Response response = getResponse();
            if (response != null) {
                sentryConsumer.accept(response);
            } else {
                Response response2 = new Response();
                setResponse(response2);
                sentryConsumer.accept(response2);
            }
        }
    }

    public void setResponse(Response response) {
        synchronized (this.responseLock) {
            put(Response.TYPE, response);
        }
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        ArrayList<String> list = Collections.list(keys());
        Collections.sort(list);
        for (String str : list) {
            Object obj = get(str);
            if (obj != null) {
                objectWriter.name(str).value(iLogger, obj);
            }
        }
        objectWriter.endObject();
    }

    public static final class Deserializer implements JsonDeserializer<Contexts> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public Contexts deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            String strNextName;
            Contexts contexts = new Contexts();
            objectReader.beginObject();
            while (objectReader.peek() == JsonToken.NAME) {
                strNextName = objectReader.nextName();
                strNextName.hashCode();
                switch (strNextName) {
                    case "device":
                        contexts.setDevice(new Device.Deserializer().deserialize(objectReader, iLogger));
                        break;
                    case "response":
                        contexts.setResponse(new Response.Deserializer().deserialize(objectReader, iLogger));
                        break;
                    case "os":
                        contexts.setOperatingSystem(new OperatingSystem.Deserializer().deserialize(objectReader, iLogger));
                        break;
                    case "app":
                        contexts.setApp(new App.Deserializer().deserialize(objectReader, iLogger));
                        break;
                    case "gpu":
                        contexts.setGpu(new Gpu.Deserializer().deserialize(objectReader, iLogger));
                        break;
                    case "trace":
                        contexts.setTrace(new SpanContext.Deserializer().deserialize(objectReader, iLogger));
                        break;
                    case "browser":
                        contexts.setBrowser(new Browser.Deserializer().deserialize(objectReader, iLogger));
                        break;
                    case "runtime":
                        contexts.setRuntime(new SentryRuntime.Deserializer().deserialize(objectReader, iLogger));
                        break;
                    default:
                        Object objNextObjectOrNull = objectReader.nextObjectOrNull();
                        if (objNextObjectOrNull == null) {
                            break;
                        } else {
                            contexts.put(strNextName, objNextObjectOrNull);
                            break;
                        }
                }
            }
            objectReader.endObject();
            return contexts;
        }
    }
}
