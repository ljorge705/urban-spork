package kotlinx.serialization.json.internal;

import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.protocol.SentryThread;
import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: AbstractJsonLexer.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\r\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0004H\u0002J\u0018\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0002J\u0018\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0004H\u0014J\b\u0010\u001d\u001a\u00020\u001eH&J\u0006\u0010\u001f\u001a\u00020\u001eJ\u0010\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u0004H\u0003J\u0006\u0010!\u001a\u00020\u001eJ\u0018\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0004H\u0002J\b\u0010$\u001a\u00020\rH&J\b\u0010%\u001a\u00020&H&J\u000e\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&J\u0010\u0010%\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020(H\u0016J\u0006\u0010)\u001a\u00020*J\u0006\u0010+\u001a\u00020\rJ \u0010+\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0005J\u0006\u0010,\u001a\u00020\rJ\u0006\u0010-\u001a\u00020\rJ\u0018\u0010.\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010/\u001a\u00020\u001aH\u0016J\u0006\u00100\u001a\u00020\u001aJ\u0015\u00101\u001a\u0002022\u0006\u00103\u001a\u00020&H\u0000¢\u0006\u0002\b4J\u0018\u00101\u001a\u0002022\u0006\u00105\u001a\u00020\r2\b\b\u0002\u00106\u001a\u00020\u0004J\u000e\u00107\u001a\u00020\u001a2\u0006\u00108\u001a\u00020\rJ\u0018\u00109\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\u0018\u0010:\u001a\u00020\u00042\u0006\u0010;\u001a\u00020(2\u0006\u0010\u0018\u001a\u00020\u0004H\u0016J\u0006\u0010<\u001a\u00020\u001eJ\u0010\u0010=\u001a\u00020\u001e2\u0006\u0010>\u001a\u00020(H\u0004J\u0006\u0010?\u001a\u00020&J\u0010\u0010@\u001a\u0004\u0018\u00010\r2\u0006\u0010A\u001a\u00020\u001eJ\u0010\u0010B\u001a\u00020\u00042\u0006\u00106\u001a\u00020\u0004H&J1\u0010C\u001a\u00020\u001a2\u0006\u0010D\u001a\u00020\u001e2\b\b\u0002\u00106\u001a\u00020\u00042\f\u00105\u001a\b\u0012\u0004\u0012\u00020\r0EH\u0080\bø\u0001\u0000¢\u0006\u0002\bFJ\u000e\u0010G\u001a\u00020\u001a2\u0006\u0010H\u001a\u00020\u001eJ\b\u0010I\u001a\u00020\u0004H\u0016J\u0018\u0010J\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004H\u0016J\b\u0010L\u001a\u00020\rH\u0002J\b\u0010M\u001a\u00020\rH\u0016J\b\u0010N\u001a\u00020\u001eH&J\u0006\u0010O\u001a\u00020\u001eJ\u0010\u0010P\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020(H\u0004J\b\u0010Q\u001a\u00020\u001eH\u0002R\u0012\u0010\u0003\u001a\u00020\u00048\u0004@\u0004X\u0085\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00020\u000fX¤\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006R"}, d2 = {"Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "", "()V", "currentPosition", "", "escapedString", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getEscapedString", "()Ljava/lang/StringBuilder;", "setEscapedString", "(Ljava/lang/StringBuilder;)V", "peekedString", "", "source", "", "getSource", "()Ljava/lang/CharSequence;", "appendEsc", "startPosition", "appendEscape", "lastPosition", SentryThread.JsonKeys.CURRENT, "appendHex", "startPos", "appendRange", "", "fromIndex", "toIndex", "canConsumeValue", "", "consumeBoolean", ViewProps.START, "consumeBooleanLenient", "consumeBooleanLiteral", "literalSuffix", "consumeKeyString", "consumeNextToken", "", "expected", "", "consumeNumericLiteral", "", "consumeString", "consumeStringLenient", "consumeStringLenientNotNull", "decodedString", "ensureHaveChars", "expectEof", "fail", "", "expectedToken", "fail$kotlinx_serialization_json", "message", ViewProps.POSITION, "failOnUnknownKey", Constants.KEY_KEY, "fromHexChar", "indexOf", "char", "isNotEof", "isValidValueStart", "c", "peekNextToken", "peekString", "isLenient", "prefetchOrEof", "require", "condition", "Lkotlin/Function0;", "require$kotlinx_serialization_json", "skipElement", "allowLenientStrings", "skipWhitespaces", "substring", "endPos", "takePeeked", "toString", "tryConsumeComma", "tryConsumeNotNull", "unexpectedToken", "wasUnquotedString", "kotlinx-serialization-json"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public abstract class AbstractJsonLexer {
    protected int currentPosition;
    private StringBuilder escapedString = new StringBuilder();
    private String peekedString;

    public abstract boolean canConsumeValue();

    public abstract String consumeKeyString();

    public abstract byte consumeNextToken();

    public void ensureHaveChars() {
    }

    protected final StringBuilder getEscapedString() {
        return this.escapedString;
    }

    protected abstract CharSequence getSource();

    protected final boolean isValidValueStart(char c) {
        return !(c == '}' || c == ']' || c == ':' || c == ',');
    }

    public abstract int prefetchOrEof(int position);

    protected final void setEscapedString(StringBuilder sb) {
        Intrinsics.checkNotNullParameter(sb, "<set-?>");
        this.escapedString = sb;
    }

    public abstract boolean tryConsumeComma();

    public final boolean isNotEof() {
        return peekNextToken() != 10;
    }

    public final void expectEof() {
        if (consumeNextToken() == 10) {
            return;
        }
        fail$default(this, "Expected EOF after parsing, but had " + getSource().charAt(this.currentPosition - 1) + " instead", 0, 2, null);
        throw new KotlinNothingValueException();
    }

    public final byte consumeNextToken(byte expected) {
        byte bConsumeNextToken = consumeNextToken();
        if (bConsumeNextToken == expected) {
            return bConsumeNextToken;
        }
        fail$kotlinx_serialization_json(expected);
        throw new KotlinNothingValueException();
    }

    public void consumeNextToken(char expected) {
        ensureHaveChars();
        CharSequence source = getSource();
        int i = this.currentPosition;
        while (true) {
            int iPrefetchOrEof = prefetchOrEof(i);
            if (iPrefetchOrEof != -1) {
                int i2 = iPrefetchOrEof + 1;
                char cCharAt = source.charAt(iPrefetchOrEof);
                if (cCharAt != ' ' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\t') {
                    this.currentPosition = i2;
                    if (cCharAt == expected) {
                        return;
                    } else {
                        unexpectedToken(expected);
                    }
                }
                i = i2;
            } else {
                this.currentPosition = iPrefetchOrEof;
                unexpectedToken(expected);
                return;
            }
        }
    }

    protected final void unexpectedToken(char expected) {
        int i = this.currentPosition - 1;
        this.currentPosition = i;
        if (i >= 0 && expected == '\"' && Intrinsics.areEqual(consumeStringLenient(), "null")) {
            fail("Expected string literal but 'null' literal was found.\nUse 'coerceInputValues = true' in 'Json {}` builder to coerce nulls to default values.", this.currentPosition - 4);
            throw new KotlinNothingValueException();
        }
        fail$kotlinx_serialization_json(AbstractJsonLexerKt.charToTokenClass(expected));
        throw new KotlinNothingValueException();
    }

    public final Void fail$kotlinx_serialization_json(byte expectedToken) {
        fail("Expected " + (expectedToken == 1 ? "quotation mark '\"'" : expectedToken == 4 ? "comma ','" : expectedToken == 5 ? "semicolon ':'" : expectedToken == 6 ? "start of the object '{'" : expectedToken == 7 ? "end of the object '}'" : expectedToken == 8 ? "start of the array '['" : expectedToken == 9 ? "end of the array ']'" : "valid token") + ", but had '" + ((this.currentPosition == getSource().length() || this.currentPosition <= 0) ? "EOF" : String.valueOf(getSource().charAt(this.currentPosition - 1))) + "' instead", this.currentPosition - 1);
        throw new KotlinNothingValueException();
    }

    public final byte peekNextToken() {
        CharSequence source = getSource();
        int i = this.currentPosition;
        while (true) {
            int iPrefetchOrEof = prefetchOrEof(i);
            if (iPrefetchOrEof == -1) {
                this.currentPosition = iPrefetchOrEof;
                return (byte) 10;
            }
            char cCharAt = source.charAt(iPrefetchOrEof);
            if (cCharAt != ' ' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\t') {
                this.currentPosition = iPrefetchOrEof;
                return AbstractJsonLexerKt.charToTokenClass(cCharAt);
            }
            i = iPrefetchOrEof + 1;
        }
    }

    public final boolean tryConsumeNotNull() {
        int iPrefetchOrEof = prefetchOrEof(skipWhitespaces());
        int length = getSource().length() - iPrefetchOrEof;
        if (length < 4 || iPrefetchOrEof == -1) {
            return true;
        }
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if ("null".charAt(i) != getSource().charAt(i + iPrefetchOrEof)) {
                return true;
            }
            if (i2 > 3) {
                if (length > 4 && AbstractJsonLexerKt.charToTokenClass(getSource().charAt(iPrefetchOrEof + 4)) == 0) {
                    return true;
                }
                this.currentPosition = iPrefetchOrEof + 4;
                return false;
            }
            i = i2;
        }
    }

    public int skipWhitespaces() {
        int iPrefetchOrEof;
        char cCharAt;
        int i = this.currentPosition;
        while (true) {
            iPrefetchOrEof = prefetchOrEof(i);
            if (iPrefetchOrEof == -1 || ((cCharAt = getSource().charAt(iPrefetchOrEof)) != ' ' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\t')) {
                break;
            }
            i = iPrefetchOrEof + 1;
        }
        this.currentPosition = iPrefetchOrEof;
        return iPrefetchOrEof;
    }

    public final String peekString(boolean isLenient) {
        String strConsumeString;
        byte bPeekNextToken = peekNextToken();
        if (isLenient) {
            if (bPeekNextToken != 1 && bPeekNextToken != 0) {
                return null;
            }
            strConsumeString = consumeStringLenient();
        } else {
            if (bPeekNextToken != 1) {
                return null;
            }
            strConsumeString = consumeString();
        }
        this.peekedString = strConsumeString;
        return strConsumeString;
    }

    public int indexOf(char c, int startPos) {
        return StringsKt.indexOf$default(getSource(), c, startPos, false, 4, (Object) null);
    }

    public String substring(int startPos, int endPos) {
        return getSource().subSequence(startPos, endPos).toString();
    }

    public final String consumeString() {
        if (this.peekedString != null) {
            return takePeeked();
        }
        return consumeKeyString();
    }

    protected final String consumeString(CharSequence source, int startPosition, int current) {
        String strDecodedString;
        Intrinsics.checkNotNullParameter(source, "source");
        char cCharAt = source.charAt(current);
        boolean z = false;
        while (cCharAt != '\"') {
            if (cCharAt == '\\') {
                startPosition = prefetchOrEof(appendEscape(startPosition, current));
                if (startPosition == -1) {
                    fail("EOF", startPosition);
                    throw new KotlinNothingValueException();
                }
            } else {
                current++;
                if (current >= source.length()) {
                    appendRange(startPosition, current);
                    startPosition = prefetchOrEof(current);
                    if (startPosition == -1) {
                        fail("EOF", startPosition);
                        throw new KotlinNothingValueException();
                    }
                } else {
                    continue;
                    cCharAt = source.charAt(current);
                }
            }
            current = startPosition;
            z = true;
            cCharAt = source.charAt(current);
        }
        if (!z) {
            strDecodedString = substring(startPosition, current);
        } else {
            strDecodedString = decodedString(startPosition, current);
        }
        this.currentPosition = current + 1;
        return strDecodedString;
    }

    private final int appendEscape(int lastPosition, int current) {
        appendRange(lastPosition, current);
        return appendEsc(current + 1);
    }

    private final String decodedString(int lastPosition, int currentPosition) {
        appendRange(lastPosition, currentPosition);
        String string = this.escapedString.toString();
        Intrinsics.checkNotNullExpressionValue(string, "escapedString.toString()");
        this.escapedString.setLength(0);
        return string;
    }

    private final String takePeeked() {
        String str = this.peekedString;
        Intrinsics.checkNotNull(str);
        this.peekedString = null;
        return str;
    }

    public final String consumeStringLenientNotNull() {
        String strConsumeStringLenient = consumeStringLenient();
        if (!Intrinsics.areEqual(strConsumeStringLenient, "null") || !wasUnquotedString()) {
            return strConsumeStringLenient;
        }
        fail$default(this, "Unexpected 'null' value instead of string literal", 0, 2, null);
        throw new KotlinNothingValueException();
    }

    private final boolean wasUnquotedString() {
        return getSource().charAt(this.currentPosition - 1) != '\"';
    }

    public final String consumeStringLenient() {
        String strDecodedString;
        if (this.peekedString != null) {
            return takePeeked();
        }
        int iSkipWhitespaces = skipWhitespaces();
        if (iSkipWhitespaces >= getSource().length() || iSkipWhitespaces == -1) {
            fail("EOF", iSkipWhitespaces);
            throw new KotlinNothingValueException();
        }
        byte bCharToTokenClass = AbstractJsonLexerKt.charToTokenClass(getSource().charAt(iSkipWhitespaces));
        if (bCharToTokenClass == 1) {
            return consumeString();
        }
        if (bCharToTokenClass != 0) {
            fail$default(this, Intrinsics.stringPlus("Expected beginning of the string, but got ", Character.valueOf(getSource().charAt(iSkipWhitespaces))), 0, 2, null);
            throw new KotlinNothingValueException();
        }
        boolean z = false;
        while (AbstractJsonLexerKt.charToTokenClass(getSource().charAt(iSkipWhitespaces)) == 0) {
            iSkipWhitespaces++;
            if (iSkipWhitespaces >= getSource().length()) {
                appendRange(this.currentPosition, iSkipWhitespaces);
                int iPrefetchOrEof = prefetchOrEof(iSkipWhitespaces);
                if (iPrefetchOrEof == -1) {
                    this.currentPosition = iSkipWhitespaces;
                    return decodedString(0, 0);
                }
                iSkipWhitespaces = iPrefetchOrEof;
                z = true;
            }
        }
        if (!z) {
            strDecodedString = substring(this.currentPosition, iSkipWhitespaces);
        } else {
            strDecodedString = decodedString(this.currentPosition, iSkipWhitespaces);
        }
        this.currentPosition = iSkipWhitespaces;
        return strDecodedString;
    }

    protected void appendRange(int fromIndex, int toIndex) {
        this.escapedString.append(getSource(), fromIndex, toIndex);
    }

    private final int appendEsc(int startPosition) {
        int iPrefetchOrEof = prefetchOrEof(startPosition);
        if (iPrefetchOrEof == -1) {
            fail$default(this, "Expected escape sequence to continue, got EOF", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        int i = iPrefetchOrEof + 1;
        char cCharAt = getSource().charAt(iPrefetchOrEof);
        if (cCharAt == 'u') {
            return appendHex(getSource(), i);
        }
        char cEscapeToChar = AbstractJsonLexerKt.escapeToChar(cCharAt);
        if (cEscapeToChar == 0) {
            fail$default(this, "Invalid escaped char '" + cCharAt + '\'', 0, 2, null);
            throw new KotlinNothingValueException();
        }
        this.escapedString.append(cEscapeToChar);
        return i;
    }

    private final int appendHex(CharSequence source, int startPos) {
        int i = startPos + 4;
        if (i >= source.length()) {
            this.currentPosition = startPos;
            ensureHaveChars();
            if (this.currentPosition + 4 >= source.length()) {
                fail$default(this, "Unexpected EOF during unicode escape", 0, 2, null);
                throw new KotlinNothingValueException();
            }
            return appendHex(source, this.currentPosition);
        }
        this.escapedString.append((char) ((fromHexChar(source, startPos) << 12) + (fromHexChar(source, startPos + 1) << 8) + (fromHexChar(source, startPos + 2) << 4) + fromHexChar(source, startPos + 3)));
        return i;
    }

    public static /* synthetic */ void require$kotlinx_serialization_json$default(AbstractJsonLexer abstractJsonLexer, boolean z, int i, Function0 message, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: require");
        }
        if ((i2 & 2) != 0) {
            i = abstractJsonLexer.currentPosition;
        }
        Intrinsics.checkNotNullParameter(message, "message");
        if (z) {
            return;
        }
        abstractJsonLexer.fail((String) message.invoke(), i);
        throw new KotlinNothingValueException();
    }

    public final void require$kotlinx_serialization_json(boolean condition, int position, Function0<String> message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (condition) {
            return;
        }
        fail(message.invoke(), position);
        throw new KotlinNothingValueException();
    }

    private final int fromHexChar(CharSequence source, int currentPosition) {
        char cCharAt = source.charAt(currentPosition);
        if ('0' <= cCharAt && cCharAt <= '9') {
            return cCharAt - '0';
        }
        if ('a' <= cCharAt && cCharAt <= 'f') {
            return cCharAt - 'W';
        }
        if ('A' <= cCharAt && cCharAt <= 'F') {
            return cCharAt - '7';
        }
        fail$default(this, "Invalid toHexChar char '" + cCharAt + "' in unicode escape", 0, 2, null);
        throw new KotlinNothingValueException();
    }

    public final void skipElement(boolean allowLenientStrings) {
        ArrayList arrayList = new ArrayList();
        byte bPeekNextToken = peekNextToken();
        if (bPeekNextToken != 8 && bPeekNextToken != 6) {
            consumeStringLenient();
            return;
        }
        while (true) {
            byte bPeekNextToken2 = peekNextToken();
            if (bPeekNextToken2 != 1) {
                if (bPeekNextToken2 == 8 || bPeekNextToken2 == 6) {
                    arrayList.add(Byte.valueOf(bPeekNextToken2));
                } else if (bPeekNextToken2 == 9) {
                    if (((Number) CollectionsKt.last((List) arrayList)).byteValue() != 8) {
                        throw JsonExceptionsKt.JsonDecodingException(this.currentPosition, "found ] instead of }", getSource());
                    }
                    CollectionsKt.removeLast(arrayList);
                } else if (bPeekNextToken2 == 7) {
                    if (((Number) CollectionsKt.last((List) arrayList)).byteValue() != 6) {
                        throw JsonExceptionsKt.JsonDecodingException(this.currentPosition, "found } instead of ]", getSource());
                    }
                    CollectionsKt.removeLast(arrayList);
                } else if (bPeekNextToken2 == 10) {
                    fail$default(this, "Unexpected end of input due to malformed JSON during ignoring unknown keys", 0, 2, null);
                    throw new KotlinNothingValueException();
                }
                consumeNextToken();
                if (arrayList.size() == 0) {
                    return;
                }
            } else if (allowLenientStrings) {
                consumeStringLenient();
            } else {
                consumeKeyString();
            }
        }
    }

    public String toString() {
        return "JsonReader(source='" + ((Object) getSource()) + "', currentPosition=" + this.currentPosition + ')';
    }

    public final void failOnUnknownKey(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        fail("Encountered an unknown key '" + key + "'.\nUse 'ignoreUnknownKeys = true' in 'Json {}' builder to ignore unknown keys.", StringsKt.lastIndexOf$default((CharSequence) substring(0, this.currentPosition), key, 0, false, 6, (Object) null));
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ Void fail$default(AbstractJsonLexer abstractJsonLexer, String str, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fail");
        }
        if ((i2 & 2) != 0) {
            i = abstractJsonLexer.currentPosition;
        }
        return abstractJsonLexer.fail(str, i);
    }

    public final Void fail(String message, int position) {
        Intrinsics.checkNotNullParameter(message, "message");
        throw JsonExceptionsKt.JsonDecodingException(position, message, getSource());
    }

    public final long consumeNumericLiteral() {
        boolean z;
        int iPrefetchOrEof = prefetchOrEof(skipWhitespaces());
        Object obj = null;
        int i = 2;
        if (iPrefetchOrEof >= getSource().length() || iPrefetchOrEof == -1) {
            fail$default(this, "EOF", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        if (getSource().charAt(iPrefetchOrEof) == '\"') {
            iPrefetchOrEof++;
            if (iPrefetchOrEof == getSource().length()) {
                fail$default(this, "EOF", 0, 2, null);
                throw new KotlinNothingValueException();
            }
            z = true;
        } else {
            z = false;
        }
        int i2 = iPrefetchOrEof;
        boolean z2 = false;
        boolean z3 = true;
        long j = 0;
        while (z3) {
            char cCharAt = getSource().charAt(i2);
            if (cCharAt == '-') {
                if (i2 != iPrefetchOrEof) {
                    fail$default(this, "Unexpected symbol '-' in numeric literal", 0, i, obj);
                    throw new KotlinNothingValueException();
                }
                i2++;
                z2 = true;
            } else {
                if (AbstractJsonLexerKt.charToTokenClass(cCharAt) != 0) {
                    break;
                }
                i2++;
                z3 = i2 != getSource().length();
                int i3 = cCharAt - '0';
                if (i3 < 0 || i3 > 9) {
                    fail$default(this, "Unexpected symbol '" + cCharAt + "' in numeric literal", 0, 2, null);
                    throw new KotlinNothingValueException();
                }
                j = (j * 10) - i3;
                if (j > 0) {
                    fail$default(this, "Numeric value overflow", 0, 2, null);
                    throw new KotlinNothingValueException();
                }
                obj = null;
                i = 2;
            }
        }
        if (iPrefetchOrEof == i2 || (z2 && iPrefetchOrEof == i2 - 1)) {
            fail$default(this, "Expected numeric literal", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        if (z) {
            if (!z3) {
                fail$default(this, "EOF", 0, 2, null);
                throw new KotlinNothingValueException();
            }
            if (getSource().charAt(i2) != '\"') {
                fail$default(this, "Expected closing quotation mark", 0, 2, null);
                throw new KotlinNothingValueException();
            }
            i2++;
        }
        this.currentPosition = i2;
        if (z2) {
            return j;
        }
        if (j != Long.MIN_VALUE) {
            return -j;
        }
        fail$default(this, "Numeric value overflow", 0, 2, null);
        throw new KotlinNothingValueException();
    }

    public final boolean consumeBoolean() {
        return consumeBoolean(skipWhitespaces());
    }

    public final boolean consumeBooleanLenient() {
        boolean z;
        int iSkipWhitespaces = skipWhitespaces();
        if (iSkipWhitespaces == getSource().length()) {
            fail$default(this, "EOF", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        if (getSource().charAt(iSkipWhitespaces) == '\"') {
            iSkipWhitespaces++;
            z = true;
        } else {
            z = false;
        }
        boolean zConsumeBoolean = consumeBoolean(iSkipWhitespaces);
        if (z) {
            if (this.currentPosition == getSource().length()) {
                fail$default(this, "EOF", 0, 2, null);
                throw new KotlinNothingValueException();
            }
            if (getSource().charAt(this.currentPosition) != '\"') {
                fail$default(this, "Expected closing quotation mark", 0, 2, null);
                throw new KotlinNothingValueException();
            }
            this.currentPosition++;
        }
        return zConsumeBoolean;
    }

    private final boolean consumeBoolean(int start) {
        int iPrefetchOrEof = prefetchOrEof(start);
        if (iPrefetchOrEof >= getSource().length() || iPrefetchOrEof == -1) {
            fail$default(this, "EOF", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        int i = iPrefetchOrEof + 1;
        int iCharAt = getSource().charAt(iPrefetchOrEof) | ' ';
        if (iCharAt == 116) {
            consumeBooleanLiteral("rue", i);
            return true;
        }
        if (iCharAt == 102) {
            consumeBooleanLiteral("alse", i);
            return false;
        }
        fail$default(this, "Expected valid boolean literal prefix, but had '" + consumeStringLenient() + '\'', 0, 2, null);
        throw new KotlinNothingValueException();
    }

    private final void consumeBooleanLiteral(String literalSuffix, int current) {
        if (getSource().length() - current < literalSuffix.length()) {
            fail$default(this, "Unexpected end of boolean literal", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        int length = literalSuffix.length() - 1;
        if (length >= 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                if (literalSuffix.charAt(i) != (getSource().charAt(i + current) | ' ')) {
                    fail$default(this, "Expected valid boolean literal prefix, but had '" + consumeStringLenient() + '\'', 0, 2, null);
                    throw new KotlinNothingValueException();
                }
                if (i2 > length) {
                    break;
                } else {
                    i = i2;
                }
            }
        }
        this.currentPosition = current + literalSuffix.length();
    }
}
