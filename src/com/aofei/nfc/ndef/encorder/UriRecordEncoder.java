// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:42:23
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   UriRecordEncoder.java

package com.aofei.nfc.ndef.encorder;

import com.aofei.nfc.ndef.NdefMessageEncoder;
import com.aofei.nfc.ndef.NdefRecord;
import com.aofei.nfc.ndef.records.Record;
import com.aofei.nfc.ndef.records.UriRecord;

// Referenced classes of package com.aofei.nfc.ndef.encorder:
//            RecordEncoder

public class UriRecordEncoder
    implements RecordEncoder
{

    public UriRecordEncoder()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #10  <Method void Object()>
    //    2    4:return          
    }

    public boolean canEncode(Record record)
    {
        return record instanceof UriRecord;
    //    0    0:aload_1         
    //    1    1:instanceof      #18  <Class UriRecord>
    //    2    4:ireturn         
    }

    public NdefRecord encodeRecord(Record record, NdefMessageEncoder messageEncoder)
    {
        int abbreviateIndex = getAbbreviateIndex(((UriRecord)record).getUri().toLowerCase());
    //    0    0:aload_0         
    //    1    1:aload_1         
    //    2    2:checkcast       #18  <Class UriRecord>
    //    3    5:invokevirtual   #24  <Method String UriRecord.getUri()>
    //    4    8:invokevirtual   #28  <Method String String.toLowerCase()>
    //    5   11:invokespecial   #33  <Method int getAbbreviateIndex(String)>
    //    6   14:istore_3        
        int uriCopyOffset = UriRecord.abbreviableUris[abbreviateIndex].length();
    //    7   15:getstatic       #37  <Field String[] UriRecord.abbreviableUris>
    //    8   18:iload_3         
    //    9   19:aaload          
    //   10   20:invokevirtual   #41  <Method int String.length()>
    //   11   23:istore          4
        byte uriAsBytes[] = ((UriRecord)record).getUri().getBytes(UriRecord.DEFAULT_URI_CHARSET);
    //   12   25:aload_1         
    //   13   26:checkcast       #18  <Class UriRecord>
    //   14   29:invokevirtual   #24  <Method String UriRecord.getUri()>
    //   15   32:getstatic       #45  <Field java.nio.charset.Charset UriRecord.DEFAULT_URI_CHARSET>
    //   16   35:invokevirtual   #49  <Method byte[] String.getBytes(java.nio.charset.Charset)>
    //   17   38:astore          5
        byte payload[] = new byte[(uriAsBytes.length + 1) - uriCopyOffset];
    //   18   40:aload           5
    //   19   42:arraylength     
    //   20   43:iconst_1        
    //   21   44:iadd            
    //   22   45:iload           4
    //   23   47:isub            
    //   24   48:newarray        byte[]
    //   25   50:astore          6
        payload[0] = (byte)abbreviateIndex;
    //   26   52:aload           6
    //   27   54:iconst_0        
    //   28   55:iload_3         
    //   29   56:int2byte        
    //   30   57:bastore         
        System.arraycopy(uriAsBytes, uriCopyOffset, payload, 1, uriAsBytes.length - uriCopyOffset);
    //   31   58:aload           5
    //   32   60:iload           4
    //   33   62:aload           6
    //   34   64:iconst_1        
    //   35   65:aload           5
    //   36   67:arraylength     
    //   37   68:iload           4
    //   38   70:isub            
    //   39   71:invokestatic    #53  <Method void System.arraycopy(Object, int, Object, int, int)>
        return new NdefRecord((short)1, UriRecord.TYPE, record.getId(), payload);
    //   40   74:new             #59  <Class NdefRecord>
    //   41   77:dup             
    //   42   78:iconst_1        
    //   43   79:getstatic       #61  <Field byte[] UriRecord.TYPE>
    //   44   82:aload_1         
    //   45   83:invokevirtual   #65  <Method byte[] Record.getId()>
    //   46   86:aload           6
    //   47   88:invokespecial   #71  <Method void NdefRecord(short, byte[], byte[], byte[])>
    //   48   91:areturn         
    }

    private int getAbbreviateIndex(String uri)
    {
        int maxLength = 0;
    //    0    0:iconst_0        
    //    1    1:istore_2        
        int abbreviateIndex = 0;
    //    2    2:iconst_0        
    //    3    3:istore_3        
        for(int x = 1; x < UriRecord.abbreviableUris.length; x++)
    //*   4    4:iconst_1        
    //*   5    5:istore          4
    //*   6    7:goto            48
        {
            String abbreviablePrefix = UriRecord.abbreviableUris[x];
    //    7   10:getstatic       #37  <Field String[] UriRecord.abbreviableUris>
    //    8   13:iload           4
    //    9   15:aaload          
    //   10   16:astore          5
            if(uri.startsWith(abbreviablePrefix) && abbreviablePrefix.length() > maxLength)
    //*  11   18:aload_1         
    //*  12   19:aload           5
    //*  13   21:invokevirtual   #81  <Method boolean String.startsWith(String)>
    //*  14   24:ifeq            45
    //*  15   27:aload           5
    //*  16   29:invokevirtual   #41  <Method int String.length()>
    //*  17   32:iload_2         
    //*  18   33:icmple          45
            {
                abbreviateIndex = x;
    //   19   36:iload           4
    //   20   38:istore_3        
                maxLength = abbreviablePrefix.length();
    //   21   39:aload           5
    //   22   41:invokevirtual   #41  <Method int String.length()>
    //   23   44:istore_2        
            }
        }

    //   24   45:iinc            4  1
    //   25   48:iload           4
    //   26   50:getstatic       #37  <Field String[] UriRecord.abbreviableUris>
    //   27   53:arraylength     
    //   28   54:icmplt          10
        return abbreviateIndex;
    //   29   57:iload_3         
    //   30   58:ireturn         
    }
}