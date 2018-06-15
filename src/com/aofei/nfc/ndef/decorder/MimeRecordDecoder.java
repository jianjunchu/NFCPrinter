// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:52:15
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate_fullnames 
// Source File Name:   MimeRecordDecoder.java

package com.aofei.nfc.ndef.decorder;

import com.aofei.nfc.ndef.NdefMessageDecoder;
import com.aofei.nfc.ndef.NdefRecord;
import com.aofei.nfc.ndef.records.MimeRecord;
import com.aofei.nfc.ndef.records.Record;

// Referenced classes of package com.aofei.nfc.ndef.decorder:
//            RecordDecoder

public class MimeRecordDecoder
    implements RecordDecoder
{

    public MimeRecordDecoder()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #10  <Method void Object()>
    //    2    4:return          
    }

    public boolean canDecode(NdefRecord ndefRecord)
    {
        byte type[] = ndefRecord.getType();
    //    0    0:aload_1         
    //    1    1:invokevirtual   #18  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getType()>
    //    2    4:astore_2        
        String t = new String(type);
    //    3    5:new             #24  <Class java.lang.String>
    //    4    8:dup             
    //    5    9:aload_2         
    //    6   10:invokespecial   #26  <Method void String(byte[])>
    //    7   13:astore_3        
        return true;
    //    8   14:iconst_1        
    //    9   15:ireturn         
    }

    public MimeRecord decodeRecord(NdefRecord ndefRecord, NdefMessageDecoder messageDecoder)
    {
        byte type[] = ndefRecord.getType();
    //    0    0:aload_1         
    //    1    1:invokevirtual   #18  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getType()>
    //    2    4:astore_3        
        String t = new String(type);
    //    3    5:new             #24  <Class java.lang.String>
    //    4    8:dup             
    //    5    9:aload_3         
    //    6   10:invokespecial   #26  <Method void String(byte[])>
    //    7   13:astore          4
        byte data[] = ndefRecord.getPayload();
    //    8   15:aload_1         
    //    9   16:invokevirtual   #37  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getPayload()>
    //   10   19:astore          5
        return new MimeRecord(t, data);
    //   11   21:new             #40  <Class com.aofei.nfc.ndef.records.MimeRecord>
    //   12   24:dup             
    //   13   25:aload           4
    //   14   27:aload           5
    //   15   29:invokespecial   #42  <Method void MimeRecord(java.lang.String, byte[])>
    //   16   32:areturn         
    }

//    public volatile Record decodeRecord(NdefRecord ndefrecord, NdefMessageDecoder ndefmessagedecoder)
//    {
//        return decodeRecord(ndefrecord, ndefmessagedecoder);
//    //    0    0:aload_0         
//    //    1    1:aload_1         
//    //    2    2:aload_2         
//    //    3    3:invokevirtual   #49  <Method com.aofei.nfc.ndef.records.MimeRecord com.aofei.nfc.ndef.decorder.MimeRecordDecoder.decodeRecord(com.aofei.nfc.ndef.NdefRecord, com.aofei.nfc.ndef.NdefMessageDecoder)>
//    //    4    6:areturn         
//    }
}