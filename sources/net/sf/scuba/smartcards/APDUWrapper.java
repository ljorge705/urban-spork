package net.sf.scuba.smartcards;

/* loaded from: classes4.dex */
public interface APDUWrapper {
    String getType();

    ResponseAPDU unwrap(ResponseAPDU responseAPDU);

    CommandAPDU wrap(CommandAPDU commandAPDU);
}
