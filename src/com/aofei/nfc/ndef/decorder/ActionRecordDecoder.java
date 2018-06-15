// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:52:15
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate_fullnames 
// Source File Name:   ActionRecordDecoder.java

package com.aofei.nfc.ndef.decorder;

import com.aofei.nfc.ndef.NdefMessageDecoder;
import com.aofei.nfc.ndef.NdefRecord;
import com.aofei.nfc.ndef.records.ActionRecord;
import com.aofei.nfc.ndef.records.Record;
import com.aofei.nfc.utils.NfcUtils;

// Referenced classes of package com.aofei.nfc.ndef.decorder:
//            RecordDecoder

public class ActionRecordDecoder
    implements RecordDecoder
{

    public ActionRecordDecoder()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #10  <Method void Object()>
    //    2    4:return          
    }

    public boolean canDecode(NdefRecord ndefRecord)
    {
        return NfcUtils.isEqualArray(ndefRecord.getType(), ActionRecord.TYPE);
    //    0    0:aload_1         
    //    1    1:invokevirtual   #18  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getType()>
    //    2    4:getstatic       #24  <Field byte[] com.aofei.nfc.ndef.records.ActionRecord.TYPE>
    //    3    7:invokestatic    #30  <Method boolean com.aofei.nfc.utils.NfcUtils.isEqualArray(byte[], byte[])>
    //    4   10:ireturn         
    }

    public ActionRecord decodeRecord(NdefRecord ndefRecord, NdefMessageDecoder messageDecoder)
    {
        com.aofei.nfc.ndef.records.ActionRecord.Action action = null;
    //    0    0:aconst_null     
    //    1    1:astore_3        
        int actionId = ndefRecord.getPayload()[0];
    //    2    2:aload_1         
    //    3    3:invokevirtual   #40  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getPayload()>
    //    4    6:iconst_0        
    //    5    7:baload          
    //    6    8:istore          4
        com.aofei.nfc.ndef.records.ActionRecord.Action aaction[];
        int j = (aaction = com.aofei.nfc.ndef.records.ActionRecord.Action.values()).length;
    //    7   10:invokestatic    #43  <Method com.aofei.nfc.ndef.records.ActionRecord$Action[] com.aofei.nfc.ndef.records.ActionRecord$Action.values()>
    //    8   13:dup             
    //    9   14:astore          8
    //   10   16:arraylength     
    //   11   17:istore          7
        for(int i = 0; i < j; i++)
    //*  12   19:iconst_0        
    //*  13   20:istore          6
    //*  14   22:goto            51
        {
            com.aofei.nfc.ndef.records.ActionRecord.Action possibleAction = aaction[i];
    //   15   25:aload           8
    //   16   27:iload           6
    //   17   29:aaload          
    //   18   30:astore          5
            if(actionId != possibleAction.getValue())
                continue;
    //   19   32:iload           4
    //   20   34:aload           5
    //   21   36:invokevirtual   #49  <Method int com.aofei.nfc.ndef.records.ActionRecord$Action.getValue()>
    //   22   39:icmpne          48
            action = possibleAction;
    //   23   42:aload           5
    //   24   44:astore_3        
            break;
    //   25   45:goto            58
        }

    //   26   48:iinc            6  1
    //   27   51:iload           6
    //   28   53:iload           7
    //   29   55:icmplt          25
        return new ActionRecord(action);
    //   30   58:new             #25  <Class com.aofei.nfc.ndef.records.ActionRecord>
    //   31   61:dup             
    //   32   62:aload_3         
    //   33   63:invokespecial   #53  <Method void ActionRecord(com.aofei.nfc.ndef.records.ActionRecord$Action)>
    //   34   66:areturn         
    }

//    public volatile Record decodeRecord(NdefRecord ndefrecord, NdefMessageDecoder ndefmessagedecoder)
//    {
//        return decodeRecord(ndefrecord, ndefmessagedecoder);
//    //    0    0:aload_0         
//    //    1    1:aload_1         
//    //    2    2:aload_2         
//    //    3    3:invokevirtual   #69  <Method com.aofei.nfc.ndef.records.ActionRecord com.aofei.nfc.ndef.decorder.ActionRecordDecoder.decodeRecord(com.aofei.nfc.ndef.NdefRecord, com.aofei.nfc.ndef.NdefMessageDecoder)>
//    //    4    6:areturn         
//    }
}