// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:42:23
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   MimeRecordEncoder.java

package com.aofei.nfc.ndef.encorder;

import com.aofei.nfc.ndef.NdefMessageEncoder;
import com.aofei.nfc.ndef.NdefRecord;
import com.aofei.nfc.ndef.records.MimeRecord;
import com.aofei.nfc.ndef.records.Record;

// Referenced classes of package com.aofei.nfc.ndef.encorder:
//            RecordEncoder

public class MimeRecordEncoder
    implements RecordEncoder
{

    public MimeRecordEncoder()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #10  <Method void Object()>
    //    2    4:return          
    }

    public boolean canEncode(Record record)
    {
        return record instanceof MimeRecord;
    //    0    0:aload_1         
    //    1    1:instanceof      #18  <Class MimeRecord>
    //    2    4:ireturn         
    }

    public NdefRecord encodeRecord(Record record, NdefMessageEncoder messageEncoder)
    {
        String type = ((MimeRecord)record).getType();
    //    0    0:aload_1         
    //    1    1:checkcast       #18  <Class MimeRecord>
    //    2    4:invokevirtual   #24  <Method String MimeRecord.getType()>
    //    3    7:astore_3        
        byte mimeType[] = null;
    //    4    8:aconst_null     
    //    5    9:astore          4
        if(type != null)
    //*   6   11:aload_3         
    //*   7   12:ifnull          24
            mimeType = type.getBytes();
    //    8   15:aload_3         
    //    9   16:invokevirtual   #28  <Method byte[] String.getBytes()>
    //   10   19:astore          4
        else
    //*  11   21:goto            31
            mimeType = "application/x-ch-onetouch".getBytes();
    //   12   24:ldc1            #34  <String "application/x-ch-onetouch">
    //   13   26:invokevirtual   #28  <Method byte[] String.getBytes()>
    //   14   29:astore          4
        byte payload[] = ((MimeRecord)record).getContent();
    //   15   31:aload_1         
    //   16   32:checkcast       #18  <Class MimeRecord>
    //   17   35:invokevirtual   #36  <Method byte[] MimeRecord.getContent()>
    //   18   38:astore          5
        return new NdefRecord((short)2, mimeType, record.getId(), payload);
    //   19   40:new             #39  <Class NdefRecord>
    //   20   43:dup             
    //   21   44:iconst_2        
    //   22   45:aload           4
    //   23   47:aload_1         
    //   24   48:invokevirtual   #41  <Method byte[] Record.getId()>
    //   25   51:aload           5
    //   26   53:invokespecial   #46  <Method void NdefRecord(short, byte[], byte[], byte[])>
    //   27   56:areturn         
    }
}