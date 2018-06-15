// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:42:23
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   RecordEncoder.java

package com.aofei.nfc.ndef.encorder;

import com.aofei.nfc.ndef.NdefMessageEncoder;
import com.aofei.nfc.ndef.NdefRecord;
import com.aofei.nfc.ndef.records.Record;

public interface RecordEncoder
{

    public abstract boolean canEncode(Record record);

    public abstract NdefRecord encodeRecord(Record record, NdefMessageEncoder ndefmessageencoder);
}