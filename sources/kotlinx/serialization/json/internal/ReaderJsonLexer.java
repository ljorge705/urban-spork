package kotlinx.serialization.json.internal;

import com.facebook.react.uimanager.ViewProps;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Arrays;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: JsonLexerJvm.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0014H\u0014J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0016H\u0016J\u0018\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0014H\u0016J\u0010\u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020\u0014H\u0016J\u0010\u0010&\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u0014H\u0002J\u0018\u0010(\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u00142\u0006\u0010)\u001a\u00020\u0014H\u0016J\b\u0010*\u001a\u00020\u001aH\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@VX\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lkotlinx/serialization/json/internal/ReaderJsonLexer;", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "i", "Ljava/io/InputStream;", "charset", "Ljava/nio/charset/Charset;", "(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V", "reader", "Ljava/io/Reader;", "_source", "", "(Ljava/io/Reader;[C)V", "<set-?>", "", "source", "getSource", "()Ljava/lang/CharSequence;", "setSource", "(Ljava/lang/CharSequence;)V", "threshold", "", "appendRange", "", "fromIndex", "toIndex", "canConsumeValue", "", "consumeKeyString", "", "consumeNextToken", "", "ensureHaveChars", "indexOf", "char", "", "startPos", "prefetchOrEof", ViewProps.POSITION, "preload", "spaceLeft", "substring", "endPos", "tryConsumeComma", "kotlinx-serialization-json"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class ReaderJsonLexer extends AbstractJsonLexer {
    private char[] _source;
    private final Reader reader;
    private CharSequence source;
    private int threshold;

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    protected CharSequence getSource() {
        return this.source;
    }

    public void setSource(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<set-?>");
        this.source = charSequence;
    }

    public /* synthetic */ ReaderJsonLexer(Reader reader, char[] cArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(reader, (i & 2) != 0 ? new char[16384] : cArr);
    }

    public ReaderJsonLexer(Reader reader, char[] _source) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(_source, "_source");
        this.reader = reader;
        this._source = _source;
        this.threshold = 128;
        this.source = new ArrayAsSequence(this._source);
        preload(0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ReaderJsonLexer(InputStream i, Charset charset) {
        Intrinsics.checkNotNullParameter(i, "i");
        Intrinsics.checkNotNullParameter(charset, "charset");
        InputStreamReader inputStreamReader = new InputStreamReader(i, charset);
        this((Reader) (inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 262144)), (char[]) null, 2, (DefaultConstructorMarker) (0 == true ? 1 : 0));
    }

    public /* synthetic */ ReaderJsonLexer(InputStream inputStream, Charset charset, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(inputStream, (i & 2) != 0 ? Charsets.UTF_8 : charset);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public boolean tryConsumeComma() {
        int iSkipWhitespaces = skipWhitespaces();
        if (iSkipWhitespaces >= getSource().length() || iSkipWhitespaces == -1 || getSource().charAt(iSkipWhitespaces) != ',') {
            return false;
        }
        this.currentPosition++;
        int i = this.currentPosition;
        return true;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public boolean canConsumeValue() throws IOException {
        ensureHaveChars();
        int i = this.currentPosition;
        while (true) {
            int iPrefetchOrEof = prefetchOrEof(i);
            if (iPrefetchOrEof != -1) {
                char cCharAt = getSource().charAt(iPrefetchOrEof);
                if (cCharAt != ' ' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\t') {
                    this.currentPosition = iPrefetchOrEof;
                    return isValidValueStart(cCharAt);
                }
                i = iPrefetchOrEof + 1;
            } else {
                this.currentPosition = iPrefetchOrEof;
                return false;
            }
        }
    }

    private final void preload(int spaceLeft) throws IOException {
        char[] cArr = this._source;
        System.arraycopy(cArr, this.currentPosition, cArr, 0, spaceLeft);
        int length = this._source.length;
        while (true) {
            if (spaceLeft == length) {
                break;
            }
            int i = this.reader.read(cArr, spaceLeft, length - spaceLeft);
            if (i == -1) {
                char[] cArrCopyOf = Arrays.copyOf(this._source, spaceLeft);
                Intrinsics.checkNotNullExpressionValue(cArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
                this._source = cArrCopyOf;
                setSource(new ArrayAsSequence(this._source));
                this.threshold = -1;
                break;
            }
            spaceLeft += i;
        }
        this.currentPosition = 0;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public int prefetchOrEof(int position) throws IOException {
        if (position < getSource().length()) {
            return position;
        }
        this.currentPosition = position;
        ensureHaveChars();
        return (this.currentPosition != 0 || getSource().length() == 0) ? -1 : 0;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public byte consumeNextToken() throws IOException {
        ensureHaveChars();
        CharSequence source = getSource();
        int i = this.currentPosition;
        while (true) {
            int iPrefetchOrEof = prefetchOrEof(i);
            if (iPrefetchOrEof != -1) {
                int i2 = iPrefetchOrEof + 1;
                byte bCharToTokenClass = AbstractJsonLexerKt.charToTokenClass(source.charAt(iPrefetchOrEof));
                if (bCharToTokenClass != 3) {
                    this.currentPosition = i2;
                    return bCharToTokenClass;
                }
                i = i2;
            } else {
                this.currentPosition = iPrefetchOrEof;
                return (byte) 10;
            }
        }
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public void ensureHaveChars() throws IOException {
        int length = this._source.length - this.currentPosition;
        if (length > this.threshold) {
            return;
        }
        preload(length);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public String consumeKeyString() throws IOException {
        consumeNextToken('\"');
        int i = this.currentPosition;
        int iIndexOf = indexOf('\"', i);
        if (iIndexOf == -1) {
            int iPrefetchOrEof = prefetchOrEof(i);
            if (iPrefetchOrEof == -1) {
                fail$kotlinx_serialization_json((byte) 1);
                throw new KotlinNothingValueException();
            }
            return consumeString(getSource(), this.currentPosition, iPrefetchOrEof);
        }
        if (i < iIndexOf) {
            int i2 = i;
            while (true) {
                int i3 = i2 + 1;
                if (getSource().charAt(i2) == '\\') {
                    return consumeString(getSource(), this.currentPosition, i2);
                }
                if (i3 >= iIndexOf) {
                    break;
                }
                i2 = i3;
            }
        }
        this.currentPosition = iIndexOf + 1;
        return substring(i, iIndexOf);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public int indexOf(char c, int startPos) {
        char[] cArr = this._source;
        int length = cArr.length;
        if (startPos >= length) {
            return -1;
        }
        while (true) {
            int i = startPos + 1;
            if (cArr[startPos] == c) {
                return startPos;
            }
            if (i >= length) {
                return -1;
            }
            startPos = i;
        }
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public String substring(int startPos, int endPos) {
        return new String(this._source, startPos, endPos - startPos);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    protected void appendRange(int fromIndex, int toIndex) {
        getEscapedString().append(this._source, fromIndex, toIndex - fromIndex);
    }
}
