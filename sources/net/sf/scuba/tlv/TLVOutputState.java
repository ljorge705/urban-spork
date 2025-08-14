package net.sf.scuba.tlv;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.scuba.util.Hex;

/* loaded from: classes4.dex */
class TLVOutputState {
    private static final Logger LOGGER = Logger.getLogger("net.sf.scuba.tlv");
    private boolean isAtStartOfLength;
    private boolean isAtStartOfTag;
    private boolean isReadingValue;
    private Deque<TLVStruct> state;

    public boolean isAtStartOfLength() {
        return this.isAtStartOfLength;
    }

    public boolean isAtStartOfTag() {
        return this.isAtStartOfTag;
    }

    public boolean isProcessingValue() {
        return this.isReadingValue;
    }

    public void setDummyLengthProcessed() {
        this.isAtStartOfTag = false;
        this.isAtStartOfLength = false;
        this.isReadingValue = true;
    }

    public TLVOutputState() {
        this(new ArrayDeque(), true, false, false);
    }

    public TLVOutputState(TLVOutputState tLVOutputState) {
        this(tLVOutputState.getDeepCopyOfState(), tLVOutputState.isAtStartOfTag, tLVOutputState.isAtStartOfLength, tLVOutputState.isReadingValue);
    }

    private TLVOutputState(Deque<TLVStruct> deque, boolean z, boolean z2, boolean z3) {
        this.state = deque;
        this.isAtStartOfTag = z;
        this.isAtStartOfLength = z2;
        this.isReadingValue = z3;
    }

    public int getTag() {
        if (this.state.isEmpty()) {
            throw new IllegalStateException("Tag not yet read.");
        }
        return this.state.peek().getTag();
    }

    public int getLength() {
        if (this.state.isEmpty()) {
            throw new IllegalStateException("Length not yet known.");
        }
        int length = this.state.peek().getLength();
        if (length >= 0) {
            return length;
        }
        throw new IllegalStateException("Length not yet knwon.");
    }

    public int getValueBytesProcessed() {
        return this.state.peek().getValueBytesProcessed();
    }

    public int getValueBytesLeft() {
        if (this.state.isEmpty()) {
            throw new IllegalStateException("Length of value is unknown.");
        }
        TLVStruct tLVStructPeek = this.state.peek();
        return tLVStructPeek.getLength() - tLVStructPeek.getValueBytesProcessed();
    }

    public void setTagProcessed(int i) {
        TLVStruct tLVStruct = new TLVStruct(this, i);
        if (!this.state.isEmpty()) {
            TLVStruct tLVStructPeek = this.state.peek();
            byte[] tagAsBytes = TLVUtil.getTagAsBytes(i);
            tLVStructPeek.write(tagAsBytes, 0, tagAsBytes.length);
        }
        this.state.push(tLVStruct);
        this.isAtStartOfTag = false;
        this.isAtStartOfLength = true;
        this.isReadingValue = false;
    }

    public boolean isDummyLengthSet() {
        if (this.state.isEmpty()) {
            return false;
        }
        return !this.state.peek().isLengthSet();
    }

    public void setLengthProcessed(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Cannot set negative length (length = " + i + ").");
        }
        TLVStruct tLVStructPop = this.state.pop();
        if (!this.state.isEmpty()) {
            TLVStruct tLVStructPeek = this.state.peek();
            byte[] lengthAsBytes = TLVUtil.getLengthAsBytes(i);
            tLVStructPeek.write(lengthAsBytes, 0, lengthAsBytes.length);
        }
        tLVStructPop.setLength(i);
        this.state.push(tLVStructPop);
        this.isAtStartOfTag = false;
        this.isAtStartOfLength = false;
        this.isReadingValue = true;
    }

    public void updatePreviousLength(int i) {
        if (this.state.isEmpty()) {
            return;
        }
        TLVStruct tLVStructPeek = this.state.peek();
        if (tLVStructPeek.isLengthSet && tLVStructPeek.getLength() == i) {
            return;
        }
        tLVStructPeek.setLength(i);
        if (tLVStructPeek.getValueBytesProcessed() == tLVStructPeek.getLength()) {
            this.state.pop();
            byte[] lengthAsBytes = TLVUtil.getLengthAsBytes(i);
            byte[] value = tLVStructPeek.getValue();
            updateValueBytesProcessed(lengthAsBytes, 0, lengthAsBytes.length);
            updateValueBytesProcessed(value, 0, value.length);
            this.isAtStartOfTag = true;
            this.isAtStartOfLength = false;
            this.isReadingValue = false;
        }
    }

    public void updateValueBytesProcessed(byte[] bArr, int i, int i2) {
        if (this.state.isEmpty()) {
            return;
        }
        TLVStruct tLVStructPeek = this.state.peek();
        int length = tLVStructPeek.getLength() - tLVStructPeek.getValueBytesProcessed();
        if (i2 > length) {
            throw new IllegalArgumentException("Cannot process " + i2 + " bytes! Only " + length + " bytes left in this TLV object " + tLVStructPeek);
        }
        tLVStructPeek.write(bArr, i, i2);
        if (tLVStructPeek.getValueBytesProcessed() != tLVStructPeek.getLength()) {
            this.isAtStartOfTag = false;
            this.isAtStartOfLength = false;
            this.isReadingValue = true;
        } else {
            this.state.pop();
            updateValueBytesProcessed(tLVStructPeek.getValue(), 0, tLVStructPeek.getLength());
            this.isAtStartOfTag = true;
            this.isAtStartOfLength = false;
            this.isReadingValue = false;
        }
    }

    public byte[] getValue() {
        if (this.state.isEmpty()) {
            throw new IllegalStateException("Cannot get value yet.");
        }
        return this.state.peek().getValue();
    }

    public String toString() {
        return this.state.toString();
    }

    public boolean canBeWritten() {
        Iterator<TLVStruct> it = this.state.iterator();
        while (it.hasNext()) {
            if (!it.next().isLengthSet()) {
                return false;
            }
        }
        return true;
    }

    private Deque<TLVStruct> getDeepCopyOfState() {
        ArrayDeque arrayDeque = new ArrayDeque(this.state.size());
        Iterator<TLVStruct> it = this.state.iterator();
        while (it.hasNext()) {
            arrayDeque.add(new TLVStruct(this, it.next()));
        }
        return arrayDeque;
    }

    private class TLVStruct {
        private boolean isLengthSet;
        private int length;
        private int tag;
        private ByteArrayOutputStream value;

        public int getLength() {
            return this.length;
        }

        public int getTag() {
            return this.tag;
        }

        public boolean isLengthSet() {
            return this.isLengthSet;
        }

        public void setLength(int i) {
            this.length = i;
            this.isLengthSet = true;
        }

        public TLVStruct(TLVOutputState tLVOutputState, TLVStruct tLVStruct) {
            this(tLVStruct.tag, tLVStruct.length, tLVStruct.isLengthSet, tLVStruct.getValue());
        }

        public TLVStruct(TLVOutputState tLVOutputState, int i) {
            this(i, Integer.MAX_VALUE, false, null);
        }

        public TLVStruct(int i, int i2, boolean z, byte[] bArr) {
            this.tag = i;
            this.length = i2;
            this.isLengthSet = z;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            this.value = byteArrayOutputStream;
            if (bArr != null) {
                try {
                    byteArrayOutputStream.write(bArr);
                } catch (IOException e) {
                    TLVOutputState.LOGGER.log(Level.FINE, "Exception writing bytes in memory", (Throwable) e);
                }
            }
        }

        public int getValueBytesProcessed() {
            return this.value.size();
        }

        public byte[] getValue() {
            return this.value.toByteArray();
        }

        public void write(byte[] bArr, int i, int i2) {
            this.value.write(bArr, i, i2);
        }

        public String toString() {
            byte[] byteArray = this.value.toByteArray();
            return "[TLVStruct " + Integer.toHexString(this.tag) + ", " + (this.isLengthSet ? Integer.valueOf(this.length) : "UNDEFINED") + ", " + Hex.bytesToHexString(byteArray) + "(" + byteArray.length + ") ]";
        }
    }
}
