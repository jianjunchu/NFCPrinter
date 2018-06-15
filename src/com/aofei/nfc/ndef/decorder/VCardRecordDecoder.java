// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:52:15
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate_fullnames 
// Source File Name:   VCardRecordDecoder.java

package com.aofei.nfc.ndef.decorder;

import com.aofei.nfc.ndef.NdefMessageDecoder;
import com.aofei.nfc.ndef.NdefRecord;
import com.aofei.nfc.ndef.records.*;
import com.aofei.nfc.utils.NfcUtils;
import info.ineighborhood.cardme.engine.VCardEngine;
import info.ineighborhood.cardme.vcard.VCard;
import java.io.*;
import java.nio.charset.Charset;

// Referenced classes of package com.aofei.nfc.ndef.decorder:
//            RecordDecoder

public class VCardRecordDecoder
    implements RecordDecoder
{

    public VCardRecordDecoder()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #10  <Method void Object()>
    //    2    4:return          
    }

    public boolean canDecode(NdefRecord ndefRecord)
    {
        return NfcUtils.isEqualArray(ndefRecord.getType(), VCardRecord.TYPE) || NfcUtils.isEqualArray(ndefRecord.getType(), VCardRecord.TYPE_P2P);
    //    0    0:aload_1         
    //    1    1:invokevirtual   #18  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getType()>
    //    2    4:getstatic       #24  <Field byte[] com.aofei.nfc.ndef.records.VCardRecord.TYPE>
    //    3    7:invokestatic    #30  <Method boolean com.aofei.nfc.utils.NfcUtils.isEqualArray(byte[], byte[])>
    //    4   10:ifne            28
    //    5   13:aload_1         
    //    6   14:invokevirtual   #18  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getType()>
    //    7   17:getstatic       #36  <Field byte[] com.aofei.nfc.ndef.records.VCardRecord.TYPE_P2P>
    //    8   20:invokestatic    #30  <Method boolean com.aofei.nfc.utils.NfcUtils.isEqualArray(byte[], byte[])>
    //    9   23:ifne            28
    //   10   26:iconst_0        
    //   11   27:ireturn         
    //   12   28:iconst_1        
    //   13   29:ireturn         
    }

    public VCardRecord decodeRecord(NdefRecord ndefRecord, NdefMessageDecoder messageDecoder)
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(ndefRecord.getPayload());
    //    0    0:new             #44  <Class java.io.ByteArrayInputStream>
    //    1    3:dup             
    //    2    4:aload_1         
    //    3    5:invokevirtual   #46  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getPayload()>
    //    4    8:invokespecial   #49  <Method void ByteArrayInputStream(byte[])>
    //    5   11:astore_3        
        int status = bais.read();
    //    6   12:aload_3         
    //    7   13:invokevirtual   #52  <Method int java.io.ByteArrayInputStream.read()>
    //    8   16:istore          4
        byte languageCodeLength = (byte)(status & 0x1f);
    //    9   18:iload           4
    //   10   20:bipush          31
    //   11   22:iand            
    //   12   23:int2byte        
    //   13   24:istore          5
        String languageCode = new String(NfcUtils.getBytesFromStream(languageCodeLength, bais));
    //   14   26:new             #56  <Class java.lang.String>
    //   15   29:dup             
    //   16   30:iload           5
    //   17   32:aload_3         
    //   18   33:invokestatic    #58  <Method byte[] com.aofei.nfc.utils.NfcUtils.getBytesFromStream(int, java.io.ByteArrayInputStream)>
    //   19   36:invokespecial   #62  <Method void String(byte[])>
    //   20   39:astore          6
        byte textData[] = ndefRecord.getPayload();
    //   21   41:aload_1         
    //   22   42:invokevirtual   #46  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getPayload()>
    //   23   45:astore          7
        Charset textEncoding = (status & 0x80) == 0 ? TextRecord.UTF8 : TextRecord.UTF16;
    //   24   47:iload           4
    //   25   49:sipush          128
    //   26   52:iand            
    //   27   53:ifeq            62
    //   28   56:getstatic       #63  <Field java.nio.charset.Charset com.aofei.nfc.ndef.records.TextRecord.UTF16>
    //   29   59:goto            65
    //   30   62:getstatic       #69  <Field java.nio.charset.Charset com.aofei.nfc.ndef.records.TextRecord.UTF8>
    //   31   65:astore          8
        String text = null;
    //   32   67:aconst_null     
    //   33   68:astore          9
        try
        {
            text = new String(textData, textEncoding.toString());
    //   34   70:new             #56  <Class java.lang.String>
    //   35   73:dup             
    //   36   74:aload           7
    //   37   76:aload           8
    //   38   78:invokevirtual   #72  <Method java.lang.String java.nio.charset.Charset.toString()>
    //   39   81:invokespecial   #78  <Method void String(byte[], java.lang.String)>
    //   40   84:astore          9
        }
    //*  41   86:goto            96
        catch(UnsupportedEncodingException e1)
    //*  42   89:astore          10
        {
            e1.printStackTrace();
    //   43   91:aload           10
    //   44   93:invokevirtual   #81  <Method void java.io.UnsupportedEncodingException.printStackTrace()>
        }
        String vcardString = text;
    //   45   96:aload           9
    //   46   98:astore          10
        System.out.println(vcardString);
    //   47  100:getstatic       #86  <Field java.io.PrintStream java.lang.System.out>
    //   48  103:aload           10
    //   49  105:invokevirtual   #92  <Method void java.io.PrintStream.println(java.lang.String)>
        VCardEngine engine = new VCardEngine();
    //   50  108:new             #98  <Class info.ineighborhood.cardme.engine.VCardEngine>
    //   51  111:dup             
    //   52  112:invokespecial   #100 <Method void VCardEngine()>
    //   53  115:astore          11
        VCard vCard = null;
    //   54  117:aconst_null     
    //   55  118:astore          12
        try
        {
            vCard = engine.parse(vcardString);
    //   56  120:aload           11
    //   57  122:aload           10
    //   58  124:invokevirtual   #101 <Method info.ineighborhood.cardme.vcard.VCard info.ineighborhood.cardme.engine.VCardEngine.parse(java.lang.String)>
    //   59  127:astore          12
        }
    //*  60  129:goto            134
        catch(IOException ioexception) { }
    //   61  132:astore          13
        return new VCardRecord(vCard);
    //   62  134:new             #25  <Class com.aofei.nfc.ndef.records.VCardRecord>
    //   63  137:dup             
    //   64  138:aload           12
    //   65  140:invokespecial   #105 <Method void VCardRecord(info.ineighborhood.cardme.vcard.VCard)>
    //   66  143:areturn         
    }

//    public volatile Record decodeRecord(NdefRecord ndefrecord, NdefMessageDecoder ndefmessagedecoder)
//    {
//        return decodeRecord(ndefrecord, ndefmessagedecoder);
//    //    0    0:aload_0         
//    //    1    1:aload_1         
//    //    2    2:aload_2         
//    //    3    3:invokevirtual   #136 <Method com.aofei.nfc.ndef.records.VCardRecord com.aofei.nfc.ndef.decorder.VCardRecordDecoder.decodeRecord(com.aofei.nfc.ndef.NdefRecord, com.aofei.nfc.ndef.NdefMessageDecoder)>
//    //    4    6:areturn         
//    }
}