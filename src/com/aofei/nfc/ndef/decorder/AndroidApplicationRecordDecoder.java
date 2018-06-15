// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:52:15
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate_fullnames 
// Source File Name:   AndroidApplicationRecordDecoder.java

package com.aofei.nfc.ndef.decorder;

import com.aofei.nfc.ndef.NdefMessageDecoder;
import com.aofei.nfc.ndef.NdefRecord;
import com.aofei.nfc.ndef.records.AndroidApplicationRecord;
import com.aofei.nfc.ndef.records.Record;
import com.aofei.nfc.utils.NfcUtils;

// Referenced classes of package com.aofei.nfc.ndef.decorder:
//            RecordDecoder

public class AndroidApplicationRecordDecoder
    implements RecordDecoder
{

    public AndroidApplicationRecordDecoder()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #10  <Method void Object()>
    //    2    4:return          
    }

    public boolean canDecode(NdefRecord ndefRecord)
    {
        return NfcUtils.isEqualArray(ndefRecord.getType(), AndroidApplicationRecord.TYPE);
    //    0    0:aload_1         
    //    1    1:invokevirtual   #18  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getType()>
    //    2    4:getstatic       #24  <Field byte[] com.aofei.nfc.ndef.records.AndroidApplicationRecord.TYPE>
    //    3    7:invokestatic    #30  <Method boolean com.aofei.nfc.utils.NfcUtils.isEqualArray(byte[], byte[])>
    //    4   10:ireturn         
    }

    public AndroidApplicationRecord decodeRecord(NdefRecord ndefRecord, NdefMessageDecoder messageDecoder)
    {
        byte payload[] = ndefRecord.getPayload();
    //    0    0:aload_1         
    //    1    1:invokevirtual   #40  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getPayload()>
    //    2    4:astore_3        
        String packageName = new String(payload);
    //    3    5:new             #43  <Class java.lang.String>
    //    4    8:dup             
    //    5    9:aload_3         
    //    6   10:invokespecial   #45  <Method void String(byte[])>
    //    7   13:astore          4
        return new AndroidApplicationRecord(packageName);
    //    8   15:new             #25  <Class com.aofei.nfc.ndef.records.AndroidApplicationRecord>
    //    9   18:dup             
    //   10   19:aload           4
    //   11   21:invokespecial   #48  <Method void AndroidApplicationRecord(java.lang.String)>
    //   12   24:areturn         
    }

//    public volatile Record decodeRecord(NdefRecord ndefrecord, NdefMessageDecoder ndefmessagedecoder)
//    {
//        return decodeRecord(ndefrecord, ndefmessagedecoder);
//    //    0    0:aload_0         
//    //    1    1:aload_1         
//    //    2    2:aload_2         
//    //    3    3:invokevirtual   #57  <Method com.aofei.nfc.ndef.records.AndroidApplicationRecord com.aofei.nfc.ndef.decorder.AndroidApplicationRecordDecoder.decodeRecord(com.aofei.nfc.ndef.NdefRecord, com.aofei.nfc.ndef.NdefMessageDecoder)>
//    //    4    6:areturn         
//    }
}