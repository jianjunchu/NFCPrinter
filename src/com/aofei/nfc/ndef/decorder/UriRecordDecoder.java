// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:52:15
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate_fullnames 
// Source File Name:   UriRecordDecoder.java

package com.aofei.nfc.ndef.decorder;

import com.aofei.nfc.ndef.NdefMessageDecoder;
import com.aofei.nfc.ndef.NdefRecord;
import com.aofei.nfc.ndef.records.Record;
import com.aofei.nfc.ndef.records.UriRecord;
import com.aofei.nfc.utils.NfcUtils;

// Referenced classes of package com.aofei.nfc.ndef.decorder:
//            RecordDecoder

public class UriRecordDecoder
    implements RecordDecoder
{

    public UriRecordDecoder()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #10  <Method void Object()>
    //    2    4:return          
    }

    public boolean canDecode(NdefRecord ndefRecord)
    {
        return NfcUtils.isEqualArray(ndefRecord.getType(), UriRecord.TYPE);
    //    0    0:aload_1         
    //    1    1:invokevirtual   #18  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getType()>
    //    2    4:getstatic       #24  <Field byte[] com.aofei.nfc.ndef.records.UriRecord.TYPE>
    //    3    7:invokestatic    #30  <Method boolean com.aofei.nfc.utils.NfcUtils.isEqualArray(byte[], byte[])>
    //    4   10:ireturn         
    }

    public UriRecord decodeRecord(NdefRecord ndefRecord, NdefMessageDecoder messageDecoder)
    {
        String prefix = "";
    //    0    0:ldc1            #40  <String "">
    //    1    2:astore_3        
        if(ndefRecord.getPayload()[0] >= UriRecord.abbreviableUris.length || ndefRecord.getPayload()[0] < 0)
    //*   2    3:aload_1         
    //*   3    4:invokevirtual   #42  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getPayload()>
    //*   4    7:iconst_0        
    //*   5    8:baload          
    //*   6    9:getstatic       #45  <Field java.lang.String[] com.aofei.nfc.ndef.records.UriRecord.abbreviableUris>
    //*   7   12:arraylength     
    //*   8   13:icmpge          25
    //*   9   16:aload_1         
    //*  10   17:invokevirtual   #42  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getPayload()>
    //*  11   20:iconst_0        
    //*  12   21:baload          
    //*  13   22:ifge            54
        {
            throw new IllegalArgumentException((new StringBuilder("unkown abbreviation index ")).append(ndefRecord.getPayload()[0]).toString());
    //   14   25:new             #49  <Class java.lang.IllegalArgumentException>
    //   15   28:dup             
    //   16   29:new             #51  <Class java.lang.StringBuilder>
    //   17   32:dup             
    //   18   33:ldc1            #53  <String "unkown abbreviation index ">
    //   19   35:invokespecial   #55  <Method void StringBuilder(java.lang.String)>
    //   20   38:aload_1         
    //   21   39:invokevirtual   #42  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getPayload()>
    //   22   42:iconst_0        
    //   23   43:baload          
    //   24   44:invokevirtual   #58  <Method java.lang.StringBuilder java.lang.StringBuilder.append(int)>
    //   25   47:invokevirtual   #62  <Method java.lang.String java.lang.StringBuilder.toString()>
    //   26   50:invokespecial   #66  <Method void IllegalArgumentException(java.lang.String)>
    //   27   53:athrow          
        } else
        {
            prefix = UriRecord.abbreviableUris[ndefRecord.getPayload()[0]];
    //   28   54:getstatic       #45  <Field java.lang.String[] com.aofei.nfc.ndef.records.UriRecord.abbreviableUris>
    //   29   57:aload_1         
    //   30   58:invokevirtual   #42  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getPayload()>
    //   31   61:iconst_0        
    //   32   62:baload          
    //   33   63:aaload          
    //   34   64:astore_3        
            String uri = new String(ndefRecord.getPayload(), 1, ndefRecord.getPayload().length - 1, UriRecord.DEFAULT_URI_CHARSET);
    //   35   65:new             #67  <Class java.lang.String>
    //   36   68:dup             
    //   37   69:aload_1         
    //   38   70:invokevirtual   #42  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getPayload()>
    //   39   73:iconst_1        
    //   40   74:aload_1         
    //   41   75:invokevirtual   #42  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getPayload()>
    //   42   78:arraylength     
    //   43   79:iconst_1        
    //   44   80:isub            
    //   45   81:getstatic       #69  <Field java.nio.charset.Charset com.aofei.nfc.ndef.records.UriRecord.DEFAULT_URI_CHARSET>
    //   46   84:invokespecial   #73  <Method void String(byte[], int, int, java.nio.charset.Charset)>
    //   47   87:astore          4
            return new UriRecord((new StringBuilder(String.valueOf(prefix))).append(uri).toString());
    //   48   89:new             #25  <Class com.aofei.nfc.ndef.records.UriRecord>
    //   49   92:dup             
    //   50   93:new             #51  <Class java.lang.StringBuilder>
    //   51   96:dup             
    //   52   97:aload_3         
    //   53   98:invokestatic    #76  <Method java.lang.String java.lang.String.valueOf(java.lang.Object)>
    //   54  101:invokespecial   #55  <Method void StringBuilder(java.lang.String)>
    //   55  104:aload           4
    //   56  106:invokevirtual   #80  <Method java.lang.StringBuilder java.lang.StringBuilder.append(java.lang.String)>
    //   57  109:invokevirtual   #62  <Method java.lang.String java.lang.StringBuilder.toString()>
    //   58  112:invokespecial   #83  <Method void UriRecord(java.lang.String)>
    //   59  115:areturn         
        }
    }

//    public volatile Record decodeRecord(NdefRecord ndefrecord, NdefMessageDecoder ndefmessagedecoder)
//    {
//        return decodeRecord(ndefrecord, ndefmessagedecoder);
//    //    0    0:aload_0         
//    //    1    1:aload_1         
//    //    2    2:aload_2         
//    //    3    3:invokevirtual   #91  <Method com.aofei.nfc.ndef.records.UriRecord com.aofei.nfc.ndef.decorder.UriRecordDecoder.decodeRecord(com.aofei.nfc.ndef.NdefRecord, com.aofei.nfc.ndef.NdefMessageDecoder)>
//    //    4    6:areturn         
//    }
}