// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:52:15
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate_fullnames 
// Source File Name:   SmartPosterDecoder.java

package com.aofei.nfc.ndef.decorder;

import com.aofei.nfc.ndef.NdefMessageDecoder;
import com.aofei.nfc.ndef.NdefRecord;
import com.aofei.nfc.ndef.records.*;
import com.aofei.nfc.utils.NfcUtils;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.aofei.nfc.ndef.decorder:
//            RecordDecoder

public class SmartPosterDecoder
    implements RecordDecoder
{

    public SmartPosterDecoder()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #10  <Method void Object()>
    //    2    4:return          
    }

    public boolean canDecode(NdefRecord ndefRecord)
    {
        return NfcUtils.isEqualArray(ndefRecord.getType(), SmartPosterRecord.TYPE);
    //    0    0:aload_1         
    //    1    1:invokevirtual   #18  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getType()>
    //    2    4:getstatic       #24  <Field byte[] com.aofei.nfc.ndef.records.SmartPosterRecord.TYPE>
    //    3    7:invokestatic    #30  <Method boolean com.aofei.nfc.utils.NfcUtils.isEqualArray(byte[], byte[])>
    //    4   10:ireturn         
    }

    public SmartPosterRecord decodeRecord(NdefRecord ndefRecord, NdefMessageDecoder messageDecoder)
    {
        SmartPosterRecord smartPosterRecord = new SmartPosterRecord();
    //    0    0:new             #25  <Class com.aofei.nfc.ndef.records.SmartPosterRecord>
    //    1    3:dup             
    //    2    4:invokespecial   #40  <Method void SmartPosterRecord()>
    //    3    7:astore_3        
        List records = messageDecoder.decodeToRecords(messageDecoder.decode(ndefRecord.getPayload()));
    //    4    8:aload_2         
    //    5    9:aload_2         
    //    6   10:aload_1         
    //    7   11:invokevirtual   #41  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getPayload()>
    //    8   14:invokevirtual   #44  <Method com.aofei.nfc.ndef.NdefMessage com.aofei.nfc.ndef.NdefMessageDecoder.decode(byte[])>
    //    9   17:invokevirtual   #50  <Method java.util.List com.aofei.nfc.ndef.NdefMessageDecoder.decodeToRecords(com.aofei.nfc.ndef.NdefMessage)>
    //   10   20:astore          4
        for(Iterator iterator = records.iterator(); iterator.hasNext();)
    //*  11   22:aload           4
    //*  12   24:invokeinterface #54  <Method java.util.Iterator java.util.List.iterator()>
    //*  13   29:astore          6
    //*  14   31:goto            103
        {
            Record record = (Record)iterator.next();
    //   15   34:aload           6
    //   16   36:invokeinterface #60  <Method java.lang.Object java.util.Iterator.next()>
    //   17   41:checkcast       #66  <Class com.aofei.nfc.ndef.records.Record>
    //   18   44:astore          5
            if(record instanceof UriRecord)
    //*  19   46:aload           5
    //*  20   48:instanceof      #68  <Class com.aofei.nfc.ndef.records.UriRecord>
    //*  21   51:ifeq            66
                smartPosterRecord.setUri((UriRecord)record);
    //   22   54:aload_3         
    //   23   55:aload           5
    //   24   57:checkcast       #68  <Class com.aofei.nfc.ndef.records.UriRecord>
    //   25   60:invokevirtual   #70  <Method void com.aofei.nfc.ndef.records.SmartPosterRecord.setUri(com.aofei.nfc.ndef.records.UriRecord)>
            else
    //*  26   63:goto            103
            if(record instanceof TextRecord)
    //*  27   66:aload           5
    //*  28   68:instanceof      #74  <Class com.aofei.nfc.ndef.records.TextRecord>
    //*  29   71:ifeq            86
                smartPosterRecord.setTitle((TextRecord)record);
    //   30   74:aload_3         
    //   31   75:aload           5
    //   32   77:checkcast       #74  <Class com.aofei.nfc.ndef.records.TextRecord>
    //   33   80:invokevirtual   #76  <Method void com.aofei.nfc.ndef.records.SmartPosterRecord.setTitle(com.aofei.nfc.ndef.records.TextRecord)>
            else
    //*  34   83:goto            103
            if(record instanceof ActionRecord)
    //*  35   86:aload           5
    //*  36   88:instanceof      #80  <Class com.aofei.nfc.ndef.records.ActionRecord>
    //*  37   91:ifeq            103
                smartPosterRecord.setAction((ActionRecord)record);
    //   38   94:aload_3         
    //   39   95:aload           5
    //   40   97:checkcast       #80  <Class com.aofei.nfc.ndef.records.ActionRecord>
    //   41  100:invokevirtual   #82  <Method void com.aofei.nfc.ndef.records.SmartPosterRecord.setAction(com.aofei.nfc.ndef.records.ActionRecord)>
        }

    //   42  103:aload           6
    //   43  105:invokeinterface #86  <Method boolean java.util.Iterator.hasNext()>
    //   44  110:ifne            34
        return smartPosterRecord;
    //   45  113:aload_3         
    //   46  114:areturn         
    }

//    public volatile Record decodeRecord(NdefRecord ndefrecord, NdefMessageDecoder ndefmessagedecoder)
//    {
//        return decodeRecord(ndefrecord, ndefmessagedecoder);
//    //    0    0:aload_0         
//    //    1    1:aload_1         
//    //    2    2:aload_2         
//    //    3    3:invokevirtual   #102 <Method com.aofei.nfc.ndef.records.SmartPosterRecord com.aofei.nfc.ndef.decorder.SmartPosterDecoder.decodeRecord(com.aofei.nfc.ndef.NdefRecord, com.aofei.nfc.ndef.NdefMessageDecoder)>
//    //    4    6:areturn         
//    }
}