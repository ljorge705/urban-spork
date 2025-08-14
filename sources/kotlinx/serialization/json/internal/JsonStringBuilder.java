package kotlinx.serialization.json.internal;

import com.clevertap.android.sdk.variables.CTVariableUtils;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: JsonStringBuilder.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0010\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013J \u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J\u0018\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0007H\u0014J\b\u0010\u001d\u001a\u00020\rH\u0016J\b\u0010\u001e\u001a\u00020\u0013H\u0016R\u0012\u0010\u0003\u001a\u00020\u00048\u0004@\u0004X\u0085\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u001f"}, d2 = {"Lkotlinx/serialization/json/internal/JsonStringBuilder;", "", "()V", "array", "", "([C)V", RRWebVideoEvent.JsonKeys.SIZE, "", "getSize", "()I", "setSize", "(I)V", "append", "", "ch", "", "value", "", CTVariableUtils.STRING, "", "appendQuoted", "appendStringSlowPath", "firstEscapedChar", "currentSize", "ensureAdditionalCapacity", "expected", "ensureTotalCapacity", "oldSize", "additional", "release", "toString", "kotlinx-serialization-json"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public class JsonStringBuilder {
    protected char[] array;
    private int size;

    protected final int getSize() {
        return this.size;
    }

    protected final void setSize(int i) {
        this.size = i;
    }

    public JsonStringBuilder(char[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        this.array = array;
    }

    public JsonStringBuilder() {
        this(CharArrayPool.INSTANCE.take());
    }

    public final void append(long value) {
        append(String.valueOf(value));
    }

    public final void append(char ch) {
        ensureAdditionalCapacity(1);
        char[] cArr = this.array;
        int i = this.size;
        this.size = i + 1;
        cArr[i] = ch;
    }

    public final void append(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        int length = string.length();
        ensureAdditionalCapacity(length);
        string.getChars(0, string.length(), this.array, this.size);
        this.size += length;
    }

    public final void appendQuoted(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        ensureAdditionalCapacity(string.length() + 2);
        char[] cArr = this.array;
        int i = this.size;
        int i2 = i + 1;
        cArr[i] = '\"';
        int length = string.length();
        string.getChars(0, length, cArr, i2);
        int i3 = length + i2;
        if (i2 < i3) {
            int i4 = i2;
            while (true) {
                int i5 = i4 + 1;
                char c = cArr[i4];
                if (c < StringOpsKt.getESCAPE_MARKERS().length && StringOpsKt.getESCAPE_MARKERS()[c] != 0) {
                    appendStringSlowPath(i4 - i2, i4, string);
                    return;
                } else if (i5 >= i3) {
                    break;
                } else {
                    i4 = i5;
                }
            }
        }
        cArr[i3] = '\"';
        this.size = i3 + 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0073 A[LOOP:0: B:4:0x0007->B:20:0x0073, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0075 A[EDGE_INSN: B:24:0x0075->B:21:0x0075 BREAK  A[LOOP:0: B:4:0x0007->B:20:0x0073], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void appendStringSlowPath(int r7, int r8, java.lang.String r9) {
        /*
            r6 = this;
            int r0 = r9.length()
            r1 = 1
            if (r7 >= r0) goto L75
        L7:
            int r2 = r7 + 1
            r3 = 2
            int r8 = r6.ensureTotalCapacity(r8, r3)
            char r7 = r9.charAt(r7)
            byte[] r3 = kotlinx.serialization.json.internal.StringOpsKt.getESCAPE_MARKERS()
            int r3 = r3.length
            if (r7 >= r3) goto L68
            byte[] r3 = kotlinx.serialization.json.internal.StringOpsKt.getESCAPE_MARKERS()
            r3 = r3[r7]
            if (r3 != 0) goto L29
            char[] r3 = r6.array
            int r4 = r8 + 1
            char r7 = (char) r7
            r3[r8] = r7
            goto L6f
        L29:
            if (r3 != r1) goto L58
            java.lang.String[] r3 = kotlinx.serialization.json.internal.StringOpsKt.getESCAPE_STRINGS()
            r7 = r3[r7]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            int r3 = r7.length()
            int r8 = r6.ensureTotalCapacity(r8, r3)
            char[] r3 = r6.array
            int r4 = r7.length()
            if (r7 == 0) goto L50
            r5 = 0
            r7.getChars(r5, r4, r3, r8)
            int r7 = r7.length()
            int r8 = r8 + r7
            r6.size = r8
            goto L70
        L50:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            java.lang.String r8 = "null cannot be cast to non-null type java.lang.String"
            r7.<init>(r8)
            throw r7
        L58:
            char[] r7 = r6.array
            r4 = 92
            r7[r8] = r4
            int r4 = r8 + 1
            char r3 = (char) r3
            r7[r4] = r3
            int r8 = r8 + 2
            r6.size = r8
            goto L70
        L68:
            char[] r3 = r6.array
            int r4 = r8 + 1
            char r7 = (char) r7
            r3[r8] = r7
        L6f:
            r8 = r4
        L70:
            if (r2 < r0) goto L73
            goto L75
        L73:
            r7 = r2
            goto L7
        L75:
            int r7 = r6.ensureTotalCapacity(r8, r1)
            char[] r8 = r6.array
            int r9 = r7 + 1
            r0 = 34
            r8[r7] = r0
            r6.size = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.JsonStringBuilder.appendStringSlowPath(int, int, java.lang.String):void");
    }

    public String toString() {
        return new String(this.array, 0, this.size);
    }

    private final void ensureAdditionalCapacity(int expected) {
        ensureTotalCapacity(this.size, expected);
    }

    protected int ensureTotalCapacity(int oldSize, int additional) {
        int i = additional + oldSize;
        char[] cArr = this.array;
        if (cArr.length <= i) {
            char[] cArrCopyOf = Arrays.copyOf(cArr, RangesKt.coerceAtLeast(i, oldSize * 2));
            Intrinsics.checkNotNullExpressionValue(cArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
            this.array = cArrCopyOf;
        }
        return oldSize;
    }

    public void release() {
        CharArrayPool.INSTANCE.release(this.array);
    }
}
