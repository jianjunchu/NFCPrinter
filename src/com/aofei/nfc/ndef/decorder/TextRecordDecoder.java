// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:52:15
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate_fullnames 
// Source File Name:   TextRecordDecoder.java

package com.aofei.nfc.ndef.decorder;

import com.aofei.nfc.ndef.NdefMessageDecoder;
import com.aofei.nfc.ndef.NdefRecord;
import com.aofei.nfc.ndef.records.Record;
import com.aofei.nfc.ndef.records.TextRecord;
import com.aofei.nfc.utils.NfcUtils;
import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.util.Locale;

// Referenced classes of package com.aofei.nfc.ndef.decorder:
//            RecordDecoder

public class TextRecordDecoder
    implements RecordDecoder
{

    public TextRecordDecoder()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #10  <Method void Object()>
    //    2    4:return          
    }

    public boolean canDecode(NdefRecord ndefRecord)
    {
        return NfcUtils.isEqualArray(ndefRecord.getType(), TextRecord.TYPE);
    //    0    0:aload_1         
    //    1    1:invokevirtual   #18  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getType()>
    //    2    4:getstatic       #24  <Field byte[] com.aofei.nfc.ndef.records.TextRecord.TYPE>
    //    3    7:invokestatic    #30  <Method boolean com.aofei.nfc.utils.NfcUtils.isEqualArray(byte[], byte[])>
    //    4   10:ireturn         
    }

    public TextRecord decodeRecord(NdefRecord ndefRecord, NdefMessageDecoder messageDecoder)
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(ndefRecord.getPayload());
    //    0    0:new             #40  <Class java.io.ByteArrayInputStream>
    //    1    3:dup             
    //    2    4:aload_1         
    //    3    5:invokevirtual   #42  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getPayload()>
    //    4    8:invokespecial   #45  <Method void ByteArrayInputStream(byte[])>
    //    5   11:astore_3        
        int status = bais.read();
    //    6   12:aload_3         
    //    7   13:invokevirtual   #48  <Method int java.io.ByteArrayInputStream.read()>
    //    8   16:istore          4
        byte languageCodeLength = (byte)(status & 0x1f);
    //    9   18:iload           4
    //   10   20:bipush          31
    //   11   22:iand            
    //   12   23:int2byte        
    //   13   24:istore          5
        String languageCode = new String(NfcUtils.getBytesFromStream(languageCodeLength, bais));
    //   14   26:new             #52  <Class java.lang.String>
    //   15   29:dup             
    //   16   30:iload           5
    //   17   32:aload_3         
    //   18   33:invokestatic    #54  <Method byte[] com.aofei.nfc.utils.NfcUtils.getBytesFromStream(int, java.io.ByteArrayInputStream)>
    //   19   36:invokespecial   #58  <Method void String(byte[])>
    //   20   39:astore          6
        byte textData[] = NfcUtils.getBytesFromStream(ndefRecord.getPayload().length - languageCodeLength - 1, bais);
    //   21   41:aload_1         
    //   22   42:invokevirtual   #42  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getPayload()>
    //   23   45:arraylength     
    //   24   46:iload           5
    //   25   48:isub            
    //   26   49:iconst_1        
    //   27   50:isub            
    //   28   51:aload_3         
    //   29   52:invokestatic    #54  <Method byte[] com.aofei.nfc.utils.NfcUtils.getBytesFromStream(int, java.io.ByteArrayInputStream)>
    //   30   55:astore          7
        Charset textEncoding = (status & 0x80) == 0 ? TextRecord.UTF8 : TextRecord.UTF16;
    //   31   57:iload           4
    //   32   59:sipush          128
    //   33   62:iand            
    //   34   63:ifeq            72
    //   35   66:getstatic       #59  <Field java.nio.charset.Charset com.aofei.nfc.ndef.records.TextRecord.UTF16>
    //   36   69:goto            75
    //   37   72:getstatic       #63  <Field java.nio.charset.Charset com.aofei.nfc.ndef.records.TextRecord.UTF8>
    //   38   75:astore          8
        String text = new String(textData, textEncoding);
    //   39   77:new             #52  <Class java.lang.String>
    //   40   80:dup             
    //   41   81:aload           7
    //   42   83:aload           8
    //   43   85:invokespecial   #66  <Method void String(byte[], java.nio.charset.Charset)>
    //   44   88:astore          9
        return new TextRecord(text, textEncoding, new Locale(languageCode));
    //   45   90:new             #25  <Class com.aofei.nfc.ndef.records.TextRecord>
    //   46   93:dup             
    //   47   94:aload           9
    //   48   96:aload           8
    //   49   98:new             #69  <Class java.util.Locale>
    //   50  101:dup             
    //   51  102:aload           6
    //   52  104:invokespecial   #71  <Method void Locale(java.lang.String)>
    //   53  107:invokespecial   #74  <Method void TextRecord(java.lang.String, java.nio.charset.Charset, java.util.Locale)>
    //   54  110:areturn         
    }

//    public volatile Record decodeRecord(NdefRecord ndefrecord, NdefMessageDecoder ndefmessagedecoder)
//    {
//        return decodeRecord(ndefrecord, ndefmessagedecoder);
//    //    0    0:aload_0         
//    //    1    1:aload_1         
//    //    2    2:aload_2         
//    //    3    3:invokevirtual   #97  <Method com.aofei.nfc.ndef.records.TextRecord com.aofei.nfc.ndef.decorder.TextRecordDecoder.decodeRecord(com.aofei.nfc.ndef.NdefRecord, com.aofei.nfc.ndef.NdefMessageDecoder)>
//    //    4    6:areturn         
//    }
}