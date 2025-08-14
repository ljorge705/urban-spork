package net.sf.scuba.tlv;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/* loaded from: classes4.dex */
class TLVInputState {
    private boolean isAtStartOfLength;
    private boolean isAtStartOfTag;
    private boolean isReadingValue;
    private Deque<TLStruct> state;

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

    public TLVInputState() {
        this(new ArrayDeque(), true, false, false);
    }

    public TLVInputState(TLVInputState tLVInputState) {
        this(tLVInputState.getDeepCopyOfState(), tLVInputState.isAtStartOfTag, tLVInputState.isAtStartOfLength, tLVInputState.isReadingValue);
    }

    private TLVInputState(Deque<TLStruct> deque, boolean z, boolean z2, boolean z3) {
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
        return this.state.peek().getLength();
    }

    public int getValueBytesProcessed() {
        return this.state.peek().getValueBytesProcessed();
    }

    public int getValueBytesLeft() {
        if (this.state.isEmpty()) {
            throw new IllegalStateException("Length of value is unknown.");
        }
        TLStruct tLStructPeek = this.state.peek();
        return tLStructPeek.getLength() - tLStructPeek.getValueBytesProcessed();
    }

    public void setTagProcessed(int i, int i2) {
        TLStruct tLStruct = new TLStruct(this, i);
        if (!this.state.isEmpty()) {
            this.state.peek().updateValueBytesProcessed(i2);
        }
        this.state.push(tLStruct);
        this.isAtStartOfTag = false;
        this.isAtStartOfLength = true;
        this.isReadingValue = false;
    }

    public void setLengthProcessed(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("Cannot set negative length (length = " + i + ", 0x" + Integer.toHexString(i) + " for tag " + Integer.toHexString(getTag()) + ").");
        }
        TLStruct tLStructPop = this.state.pop();
        if (!this.state.isEmpty()) {
            this.state.peek().updateValueBytesProcessed(i2);
        }
        tLStructPop.setLength(i);
        this.state.push(tLStructPop);
        this.isAtStartOfTag = false;
        this.isAtStartOfLength = false;
        this.isReadingValue = true;
    }

    public void updateValueBytesProcessed(int i) {
        if (this.state.isEmpty()) {
            return;
        }
        TLStruct tLStructPeek = this.state.peek();
        int length = tLStructPeek.getLength() - tLStructPeek.getValueBytesProcessed();
        if (i > length) {
            throw new IllegalArgumentException("Cannot process " + i + " bytes! Only " + length + " bytes left in this TLV object " + tLStructPeek);
        }
        tLStructPeek.updateValueBytesProcessed(i);
        int length2 = tLStructPeek.getLength();
        if (tLStructPeek.getValueBytesProcessed() != length2) {
            this.isAtStartOfTag = false;
            this.isAtStartOfLength = false;
            this.isReadingValue = true;
        } else {
            this.state.pop();
            updateValueBytesProcessed(length2);
            this.isAtStartOfTag = true;
            this.isAtStartOfLength = false;
            this.isReadingValue = false;
        }
    }

    public String toString() {
        return this.state.toString();
    }

    private Deque<TLStruct> getDeepCopyOfState() {
        ArrayDeque arrayDeque = new ArrayDeque(this.state.size());
        Iterator<TLStruct> it = this.state.iterator();
        while (it.hasNext()) {
            arrayDeque.addLast(new TLStruct(this, it.next()));
        }
        return arrayDeque;
    }

    private class TLStruct {
        private int length;
        private int tag;
        private int valueBytesRead;

        public int getLength() {
            return this.length;
        }

        public int getTag() {
            return this.tag;
        }

        public int getValueBytesProcessed() {
            return this.valueBytesRead;
        }

        public void setLength(int i) {
            this.length = i;
        }

        public void updateValueBytesProcessed(int i) {
            this.valueBytesRead += i;
        }

        public TLStruct(TLVInputState tLVInputState, int i) {
            this(i, Integer.MAX_VALUE, 0);
        }

        public TLStruct(TLVInputState tLVInputState, TLStruct tLStruct) {
            this(tLStruct.tag, tLStruct.length, tLStruct.valueBytesRead);
        }

        public TLStruct(int i, int i2, int i3) {
            this.tag = i;
            this.length = i2;
            this.valueBytesRead = i3;
        }

        public String toString() {
            return "[TLStruct " + Integer.toHexString(this.tag) + ", " + this.length + ", " + this.valueBytesRead + "]";
        }
    }
}
