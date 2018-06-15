// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:42:23
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   AndroidApplicationRecordEncoder.java

package com.aofei.nfc.ndef.encorder;

import com.aofei.nfc.ndef.NdefMessageEncoder;
import com.aofei.nfc.ndef.NdefRecord;
import com.aofei.nfc.ndef.records.AndroidApplicationRecord;
import com.aofei.nfc.ndef.records.Record;

// Referenced classes of package com.aofei.nfc.ndef.encorder:
//            RecordEncoder

public class AndroidApplicationRecordEncoder
    implements RecordEncoder
{

    public AndroidApplicationRecordEncoder()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #10  <Method void Object()>
    //    2    4:return          
    }

    public boolean canEncode(Record record)
    {
        return record instanceof AndroidApplicationRecord;
    //    0    0:aload_1         
    //    1    1:instanceof      #18  <Class AndroidApplicationRecord>
    //    2    4:ireturn         
    }

    public NdefRecord encodeRecord(Record record, NdefMessageEncoder messageEncoder)
    {
        String packageName = ((AndroidApplicationRecord)record).getPackageName();
    //    0    0:aload_1         
    //    1    1:checkcast       #18  <Class AndroidApplicationRecord>
    //    2    4:invokevirtual   #24  <Method String AndroidApplicationRecord.getPackageName()>
    //    3    7:astore_3        
        byte pkg[] = null;
    //    4    8:aconst_null     
    //    5    9:astore          4
        if(packageName != null)
    //*   6   11:aload_3         
    //*   7   12:ifnull          24
            pkg = packageName.getBytes();
    //    8   15:aload_3         
    //    9   16:invokevirtual   #28  <Method byte[] String.getBytes()>
    //   10   19:astore          4
        else
    //*  11   21:goto            31
            pkg = "com.aofei:pkg".getBytes();
    //   12   24:ldc1            #34  <String "com.aofei:pkg">
    //   13   26:invokevirtual   #28  <Method byte[] String.getBytes()>
    //   14   29:astore          4
        return new NdefRecord((short)4, AndroidApplicationRecord.TYPE, record.getId(), pkg);
    //   15   31:new             #36  <Class NdefRecord>
    //   16   34:dup             
    //   17   35:iconst_4        
    //   18   36:getstatic       #38  <Field byte[] AndroidApplicationRecord.TYPE>
    //   19   39:aload_1         
    //   20   40:invokevirtual   #42  <Method byte[] Record.getId()>
    //   21   43:aload           4
    //   22   45:invokespecial   #47  <Method void NdefRecord(short, byte[], byte[], byte[])>
    //   23   48:areturn         
    }
}