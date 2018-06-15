// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:42:22
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   ActionRecordEncoder.java

package com.aofei.nfc.ndef.encorder;

import com.aofei.nfc.ndef.NdefMessageEncoder;
import com.aofei.nfc.ndef.NdefRecord;
import com.aofei.nfc.ndef.records.ActionRecord;
import com.aofei.nfc.ndef.records.Record;

// Referenced classes of package com.aofei.nfc.ndef.encorder:
//            RecordEncoder

public class ActionRecordEncoder
    implements RecordEncoder
{

    public ActionRecordEncoder()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #10  <Method void Object()>
    //    2    4:return          
    }

    public boolean canEncode(Record record)
    {
        return record instanceof ActionRecord;
    //    0    0:aload_1         
    //    1    1:instanceof      #18  <Class ActionRecord>
    //    2    4:ireturn         
    }

    public NdefRecord encodeRecord(Record record, NdefMessageEncoder messageEncoder)
    {
        byte payload[] = new byte[1];
    //    0    0:iconst_1        
    //    1    1:newarray        byte[]
    //    2    3:astore_3        
        payload[0] = (byte)((ActionRecord)record).getAction().getValue();

    //    3    4:aload_3         
    //    4    5:iconst_0        
    //    5    6:aload_1         
    //    6    7:checkcast       #18  <Class ActionRecord>
    //    7   10:invokevirtual   #24  <Method com.aofei.nfc.ndef.records.ActionRecord$Action ActionRecord.getAction()>
    //    8   13:invokevirtual   #28  <Method int com.aofei.nfc.ndef.records.ActionRecord$Action.getValue()>
    //    9   16:int2byte        
    //   10   17:bastore         
        return new NdefRecord((short)1, ActionRecord.TYPE, record.getId(), payload);
    //   11   18:new             #34  <Class NdefRecord>
    //   12   21:dup             
    //   13   22:iconst_1        
    //   14   23:getstatic       #36  <Field byte[] ActionRecord.TYPE>
    //   15   26:aload_1         
    //   16   27:invokevirtual   #40  <Method byte[] Record.getId()>
    //   17   30:aload_3         
    //   18   31:invokespecial   #46  <Method void NdefRecord(short, byte[], byte[], byte[])>
    //   19   34:areturn         
    }
}