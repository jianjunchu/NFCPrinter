// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:52:15
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate_fullnames 
// Source File Name:   BluetoothOOBDataRecordDecoder.java

package com.aofei.nfc.ndef.decorder;

import com.aofei.nfc.ndef.NdefMessageDecoder;
import com.aofei.nfc.ndef.NdefRecord;
import com.aofei.nfc.ndef.records.BluetoothOOBDataRecord;
import com.aofei.nfc.ndef.records.Record;

// Referenced classes of package com.aofei.nfc.ndef.decorder:
//            RecordDecoder

public class BluetoothOOBDataRecordDecoder
    implements RecordDecoder
{

    public BluetoothOOBDataRecordDecoder()
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
        return "application/vnd.bluetooth.ep.oob".equals(t);
    //    8   14:ldc1            #29  <String "application/vnd.bluetooth.ep.oob">
    //    9   16:aload_3         
    //   10   17:invokevirtual   #31  <Method boolean java.lang.String.equals(java.lang.Object)>
    //   11   20:ireturn         
    }

    public BluetoothOOBDataRecord decodeRecord(NdefRecord ndefRecord, NdefMessageDecoder messageDecoder)
    {
        byte data[] = ndefRecord.getPayload();
    //    0    0:aload_1         
    //    1    1:invokevirtual   #43  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getPayload()>
    //    2    4:astore_3        
        return BluetoothOOBDataRecord.decode(data);
    //    3    5:aload_3         
    //    4    6:invokestatic    #46  <Method com.aofei.nfc.ndef.records.BluetoothOOBDataRecord com.aofei.nfc.ndef.records.BluetoothOOBDataRecord.decode(byte[])>
    //    5    9:areturn         
    }

//    public volatile Record decodeRecord(NdefRecord ndefrecord, NdefMessageDecoder ndefmessagedecoder)
//    {
//        return decodeRecord(ndefrecord, ndefmessagedecoder);
//    //    0    0:aload_0         
//    //    1    1:aload_1         
//    //    2    2:aload_2         
//    //    3    3:invokevirtual   #56  <Method com.aofei.nfc.ndef.records.BluetoothOOBDataRecord com.aofei.nfc.ndef.decorder.BluetoothOOBDataRecordDecoder.decodeRecord(com.aofei.nfc.ndef.NdefRecord, com.aofei.nfc.ndef.NdefMessageDecoder)>
//    //    4    6:areturn         
//    }
}