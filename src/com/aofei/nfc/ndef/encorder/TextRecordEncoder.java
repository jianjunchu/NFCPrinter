// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:42:23
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   TextRecordEncoder.java

package com.aofei.nfc.ndef.encorder;

import com.aofei.nfc.ndef.NdefMessageEncoder;
import com.aofei.nfc.ndef.NdefRecord;
import com.aofei.nfc.ndef.records.Record;
import com.aofei.nfc.ndef.records.TextRecord;
import java.nio.charset.Charset;
import java.util.Locale;

// Referenced classes of package com.aofei.nfc.ndef.encorder:
//            RecordEncoder

public class TextRecordEncoder
    implements RecordEncoder
{

    public TextRecordEncoder()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #10  <Method void Object()>
    //    2    4:return          
    }

    public boolean canEncode(Record record)
    {
        return record instanceof TextRecord;
    //    0    0:aload_1         
    //    1    1:instanceof      #18  <Class TextRecord>
    //    2    4:ireturn         
    }

    public NdefRecord encodeRecord(Record record, NdefMessageEncoder messageEncoder)
    {
        byte lanuageData[] = ((TextRecord)record).getLocale().getLanguage().getBytes();
    //    0    0:aload_1         
    //    1    1:checkcast       #18  <Class TextRecord>
    //    2    4:invokevirtual   #24  <Method Locale TextRecord.getLocale()>
    //    3    7:invokevirtual   #28  <Method String Locale.getLanguage()>
    //    4   10:invokevirtual   #34  <Method byte[] String.getBytes()>
    //    5   13:astore_3        
        if(lanuageData.length > 31)
    //*   6   14:aload_3         
    //*   7   15:arraylength     
    //*   8   16:bipush          31
    //*   9   18:icmple          31
        {
            throw new IllegalArgumentException("language code length longer than 2^5. this is not supported.");
    //   10   21:new             #40  <Class IllegalArgumentException>
    //   11   24:dup             
    //   12   25:ldc1            #42  <String "language code length longer than 2^5. this is not supported.">
    //   13   27:invokespecial   #44  <Method void IllegalArgumentException(String)>
    //   14   30:athrow          
        } else
        {
            Charset encoding = ((TextRecord)record).getEncoding();
    //   15   31:aload_1         
    //   16   32:checkcast       #18  <Class TextRecord>
    //   17   35:invokevirtual   #47  <Method Charset TextRecord.getEncoding()>
    //   18   38:astore          4
            byte textData[] = ((TextRecord)record).getText().getBytes(encoding);
    //   19   40:aload_1         
    //   20   41:checkcast       #18  <Class TextRecord>
    //   21   44:invokevirtual   #51  <Method String TextRecord.getText()>
    //   22   47:aload           4
    //   23   49:invokevirtual   #54  <Method byte[] String.getBytes(Charset)>
    //   24   52:astore          5
            byte payload[] = new byte[1 + lanuageData.length + textData.length];
    //   25   54:iconst_1        
    //   26   55:aload_3         
    //   27   56:arraylength     
    //   28   57:iadd            
    //   29   58:aload           5
    //   30   60:arraylength     
    //   31   61:iadd            
    //   32   62:newarray        byte[]
    //   33   64:astore          6
            byte status = (byte)(lanuageData.length | (TextRecord.UTF16.equals(encoding) ? 0x80 : 0));
    //   34   66:aload_3         
    //   35   67:arraylength     
    //   36   68:getstatic       #57  <Field Charset TextRecord.UTF16>
    //   37   71:aload           4
    //   38   73:invokevirtual   #61  <Method boolean Charset.equals(Object)>
    //   39   76:ifeq            85
    //   40   79:sipush          128
    //   41   82:goto            86
    //   42   85:iconst_0        
    //   43   86:ior             
    //   44   87:int2byte        
    //   45   88:istore          7
            payload[0] = status;
    //   46   90:aload           6
    //   47   92:iconst_0        
    //   48   93:iload           7
    //   49   95:bastore         
            System.arraycopy(lanuageData, 0, payload, 1, lanuageData.length);
    //   50   96:aload_3         
    //   51   97:iconst_0        
    //   52   98:aload           6
    //   53  100:iconst_1        
    //   54  101:aload_3         
    //   55  102:arraylength     
    //   56  103:invokestatic    #67  <Method void System.arraycopy(Object, int, Object, int, int)>
            System.arraycopy(textData, 0, payload, 1 + lanuageData.length, textData.length);
    //   57  106:aload           5
    //   58  108:iconst_0        
    //   59  109:aload           6
    //   60  111:iconst_1        
    //   61  112:aload_3         
    //   62  113:arraylength     
    //   63  114:iadd            
    //   64  115:aload           5
    //   65  117:arraylength     
    //   66  118:invokestatic    #67  <Method void System.arraycopy(Object, int, Object, int, int)>
            return new NdefRecord((short)1, TextRecord.TYPE, record.getId(), payload);
    //   67  121:new             #73  <Class NdefRecord>
    //   68  124:dup             
    //   69  125:iconst_1        
    //   70  126:getstatic       #75  <Field byte[] TextRecord.TYPE>
    //   71  129:aload_1         
    //   72  130:invokevirtual   #79  <Method byte[] Record.getId()>
    //   73  133:aload           6
    //   74  135:invokespecial   #84  <Method void NdefRecord(short, byte[], byte[], byte[])>
    //   75  138:areturn         
        }
    }
}