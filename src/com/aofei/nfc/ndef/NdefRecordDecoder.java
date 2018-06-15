// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:31:38
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   NdefRecordDecoder.java

package com.aofei.nfc.ndef;

import com.aofei.nfc.ndef.decorder.RecordDecoder;
import com.aofei.nfc.ndef.records.Record;
import java.util.*;

// Referenced classes of package com.aofei.nfc.ndef:
//            NdefRecord, NdefMessageDecoder

public class NdefRecordDecoder
{

    public NdefRecordDecoder()
    {
        knownRecordDecoders = new ArrayList();
    }

    public Record decode(NdefRecord ndefRecord, NdefMessageDecoder messageDecoder)
    {
        for(Iterator iterator = knownRecordDecoders.iterator(); iterator.hasNext();)
        {
            RecordDecoder decoder = (RecordDecoder)iterator.next();
            if(decoder.canDecode(ndefRecord))
                return decoder.decodeRecord(ndefRecord, messageDecoder);
        }

        throw new RuntimeException((new StringBuilder("unsupported NDEF record [")).append(new String(ndefRecord.getType())).append("]").toString());
    }

    public void addRecordDecoder(RecordDecoder recordDecoder)
    {
        knownRecordDecoders.add(recordDecoder);
    }

    public List knownRecordDecoders;
}