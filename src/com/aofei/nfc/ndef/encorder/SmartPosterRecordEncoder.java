// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:42:23
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   SmartPosterRecordEncoder.java

package com.aofei.nfc.ndef.encorder;

import com.aofei.nfc.ndef.NdefMessageEncoder;
import com.aofei.nfc.ndef.NdefRecord;
import com.aofei.nfc.ndef.records.Record;
import com.aofei.nfc.ndef.records.SmartPosterRecord;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.aofei.nfc.ndef.encorder:
//            RecordEncoder

public class SmartPosterRecordEncoder
    implements RecordEncoder
{

    public SmartPosterRecordEncoder()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #10  <Method void Object()>
    //    2    4:return          
    }

    public boolean canEncode(Record record)
    {
        return record instanceof SmartPosterRecord;
    //    0    0:aload_1         
    //    1    1:instanceof      #18  <Class SmartPosterRecord>
    //    2    4:ireturn         
    }

    public NdefRecord encodeRecord(Record record, NdefMessageEncoder messageEncoder)
    {
        SmartPosterRecord myRecord = (SmartPosterRecord)record;
    //    0    0:aload_1         
    //    1    1:checkcast       #18  <Class SmartPosterRecord>
    //    2    4:astore_3        
        List records = new ArrayList();
    //    3    5:new             #24  <Class ArrayList>
    //    4    8:dup             
    //    5    9:invokespecial   #26  <Method void ArrayList()>
    //    6   12:astore          4
        if(myRecord.getTitle() != null)
    //*   7   14:aload_3         
    //*   8   15:invokevirtual   #27  <Method com.aofei.nfc.ndef.records.TextRecord SmartPosterRecord.getTitle()>
    //*   9   18:ifnull          33
            records.add(myRecord.getTitle());
    //   10   21:aload           4
    //   11   23:aload_3         
    //   12   24:invokevirtual   #27  <Method com.aofei.nfc.ndef.records.TextRecord SmartPosterRecord.getTitle()>
    //   13   27:invokeinterface #31  <Method boolean List.add(Object)>
    //   14   32:pop             
        if(myRecord.getUri() != null)
    //*  15   33:aload_3         
    //*  16   34:invokevirtual   #37  <Method com.aofei.nfc.ndef.records.UriRecord SmartPosterRecord.getUri()>
    //*  17   37:ifnull          52
            records.add(myRecord.getUri());
    //   18   40:aload           4
    //   19   42:aload_3         
    //   20   43:invokevirtual   #37  <Method com.aofei.nfc.ndef.records.UriRecord SmartPosterRecord.getUri()>
    //   21   46:invokeinterface #31  <Method boolean List.add(Object)>
    //   22   51:pop             
        if(myRecord.getAction() != null)
    //*  23   52:aload_3         
    //*  24   53:invokevirtual   #41  <Method com.aofei.nfc.ndef.records.ActionRecord SmartPosterRecord.getAction()>
    //*  25   56:ifnull          71
            records.add(myRecord.getAction());
    //   26   59:aload           4
    //   27   61:aload_3         
    //   28   62:invokevirtual   #41  <Method com.aofei.nfc.ndef.records.ActionRecord SmartPosterRecord.getAction()>
    //   29   65:invokeinterface #31  <Method boolean List.add(Object)>
    //   30   70:pop             
        byte payload[] = messageEncoder.encode(records);
    //   31   71:aload_2         
    //   32   72:aload           4
    //   33   74:invokevirtual   #45  <Method byte[] NdefMessageEncoder.encode(List)>
    //   34   77:astore          5
        return new NdefRecord((short)1, SmartPosterRecord.TYPE, record.getId(), payload);
    //   35   79:new             #51  <Class NdefRecord>
    //   36   82:dup             
    //   37   83:iconst_1        
    //   38   84:getstatic       #53  <Field byte[] SmartPosterRecord.TYPE>
    //   39   87:aload_1         
    //   40   88:invokevirtual   #57  <Method byte[] Record.getId()>
    //   41   91:aload           5
    //   42   93:invokespecial   #63  <Method void NdefRecord(short, byte[], byte[], byte[])>
    //   43   96:areturn         
    }
}