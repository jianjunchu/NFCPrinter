// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:31:38
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   NdefRecordEncoder.java

package com.aofei.nfc.ndef;

import com.aofei.nfc.ndef.encorder.RecordEncoder;
import com.aofei.nfc.ndef.records.Record;
import java.util.*;

// Referenced classes of package com.aofei.nfc.ndef:
//            NdefMessageEncoder, NdefRecord

public class NdefRecordEncoder
{

    public NdefRecordEncoder()
    {
        knownRecordEncoders = new ArrayList();
    }

    public NdefRecord encode(Record record, NdefMessageEncoder messageEncoder)
    {
        for(Iterator iterator = knownRecordEncoders.iterator(); iterator.hasNext();)
        {
            RecordEncoder encoder = (RecordEncoder)iterator.next();
            if(encoder.canEncode(record))
                return encoder.encodeRecord(record, messageEncoder);
        }

        throw new RuntimeException((new StringBuilder("unsupported record [")).append(record.getClass().getName()).append("]").toString());
    }

    public void addRecordEncoder(RecordEncoder recordEncoder)
    {
        knownRecordEncoders.add(recordEncoder);
    }

    public List knownRecordEncoders;
}