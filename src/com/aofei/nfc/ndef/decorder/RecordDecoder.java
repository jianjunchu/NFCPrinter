// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:52:15
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate_fullnames 
// Source File Name:   RecordDecoder.java

package com.aofei.nfc.ndef.decorder;

import com.aofei.nfc.ndef.NdefMessageDecoder;
import com.aofei.nfc.ndef.NdefRecord;
import com.aofei.nfc.ndef.records.Record;

public interface RecordDecoder
{

    public abstract boolean canDecode(NdefRecord ndefrecord);

    public abstract Record decodeRecord(NdefRecord ndefrecord, NdefMessageDecoder ndefmessagedecoder);
}