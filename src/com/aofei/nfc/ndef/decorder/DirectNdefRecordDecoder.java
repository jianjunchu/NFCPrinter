// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:52:15
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate_fullnames 
// Source File Name:   DirectNdefRecordDecoder.java

package com.aofei.nfc.ndef.decorder;

import com.aofei.nfc.ndef.NdefMessageDecoder;
import com.aofei.nfc.ndef.NdefRecord;
import com.aofei.nfc.ndef.records.DirectNDEFRecord;
import com.aofei.nfc.ndef.records.Record;
import com.aofei.nfc.utils.NfcUtils;
import com.aofei.utils.Hex;
import java.io.ByteArrayInputStream;

// Referenced classes of package com.aofei.nfc.ndef.decorder:
//            RecordDecoder

public class DirectNdefRecordDecoder
    implements RecordDecoder
{

    public DirectNdefRecordDecoder()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #10  <Method void Object()>
    //    2    4:return          
    }

    public boolean canDecode(NdefRecord ndefRecord)
    {
        return true;
    //    0    0:iconst_1        
    //    1    1:ireturn         
    }

    public DirectNDEFRecord decodeRecord(NdefRecord ndefRecord, NdefMessageDecoder messageDecoder)
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(ndefRecord.getPayload());
    //    0    0:new             #22  <Class java.io.ByteArrayInputStream>
    //    1    3:dup             
    //    2    4:aload_1         
    //    3    5:invokevirtual   #24  <Method byte[] com.aofei.nfc.ndef.NdefRecord.getPayload()>
    //    4    8:invokespecial   #30  <Method void ByteArrayInputStream(byte[])>
    //    5   11:astore_3        
        int header = bais.read();
    //    6   12:aload_3         
    //    7   13:invokevirtual   #33  <Method int java.io.ByteArrayInputStream.read()>
    //    8   16:istore          4
        short tnf = (short)(header & 7);
    //    9   18:iload           4
    //   10   20:bipush          7
    //   11   22:iand            
    //   12   23:int2short       
    //   13   24:istore          5
        int typeLength = bais.read();
    //   14   26:aload_3         
    //   15   27:invokevirtual   #33  <Method int java.io.ByteArrayInputStream.read()>
    //   16   30:istore          6
        int payloadLength = getPayloadLength((header & 0x10) != 0, bais);
    //   17   32:aload_0         
    //   18   33:iload           4
    //   19   35:bipush          16
    //   20   37:iand            
    //   21   38:ifeq            45
    //   22   41:iconst_1        
    //   23   42:goto            46
    //   24   45:iconst_0        
    //   25   46:aload_3         
    //   26   47:invokespecial   #37  <Method int com.aofei.nfc.ndef.decorder.DirectNdefRecordDecoder.getPayloadLength(boolean, java.io.ByteArrayInputStream)>
    //   27   50:istore          7
        int idLength = getIdLength((header & 8) != 0, bais);
    //   28   52:aload_0         
    //   29   53:iload           4
    //   30   55:bipush          8
    //   31   57:iand            
    //   32   58:ifeq            65
    //   33   61:iconst_1        
    //   34   62:goto            66
    //   35   65:iconst_0        
    //   36   66:aload_3         
    //   37   67:invokespecial   #41  <Method int com.aofei.nfc.ndef.decorder.DirectNdefRecordDecoder.getIdLength(boolean, java.io.ByteArrayInputStream)>
    //   38   70:istore          8
        byte type[] = NfcUtils.getBytesFromStream(typeLength, bais);
    //   39   72:iload           6
    //   40   74:aload_3         
    //   41   75:invokestatic    #44  <Method byte[] com.aofei.nfc.utils.NfcUtils.getBytesFromStream(int, java.io.ByteArrayInputStream)>
    //   42   78:astore          9
        byte id[] = NfcUtils.getBytesFromStream(idLength, bais);
    //   43   80:iload           8
    //   44   82:aload_3         
    //   45   83:invokestatic    #44  <Method byte[] com.aofei.nfc.utils.NfcUtils.getBytesFromStream(int, java.io.ByteArrayInputStream)>
    //   46   86:astore          10
        byte payload[] = NfcUtils.getBytesFromStream(payloadLength, bais);
    //   47   88:iload           7
    //   48   90:aload_3         
    //   49   91:invokestatic    #44  <Method byte[] com.aofei.nfc.utils.NfcUtils.getBytesFromStream(int, java.io.ByteArrayInputStream)>
    //   50   94:astore          11
        return new DirectNDEFRecord(Hex.bytesToHexString(payload));
    //   51   96:new             #50  <Class com.aofei.nfc.ndef.records.DirectNDEFRecord>
    //   52   99:dup             
    //   53  100:aload           11
    //   54  102:invokestatic    #52  <Method java.lang.String com.aofei.utils.Hex.bytesToHexString(byte[])>
    //   55  105:invokespecial   #58  <Method void DirectNDEFRecord(java.lang.String)>
    //   56  108:areturn         
    }

    private int getIdLength(boolean idLengthPresent, ByteArrayInputStream bais)
    {
        if(idLengthPresent)
    //*   0    0:iload_1         
    //*   1    1:ifeq            9
            return bais.read();
    //    2    4:aload_2         
    //    3    5:invokevirtual   #33  <Method int java.io.ByteArrayInputStream.read()>
    //    4    8:ireturn         
        else
            return 0;
    //    5    9:iconst_0        
    //    6   10:ireturn         
    }

    private int getPayloadLength(boolean shortRecord, ByteArrayInputStream bais)
    {
        if(shortRecord)
    //*   0    0:iload_1         
    //*   1    1:ifeq            9
        {
            return bais.read();
    //    2    4:aload_2         
    //    3    5:invokevirtual   #33  <Method int java.io.ByteArrayInputStream.read()>
    //    4    8:ireturn         
        } else
        {
            byte buffer[] = NfcUtils.getBytesFromStream(4, bais);
    //    5    9:iconst_4        
    //    6   10:aload_2         
    //    7   11:invokestatic    #44  <Method byte[] com.aofei.nfc.utils.NfcUtils.getBytesFromStream(int, java.io.ByteArrayInputStream)>
    //    8   14:astore_3        
            return (buffer[0] << 24) + (buffer[1] << 16) + (buffer[2] << 8) + (buffer[3] & 0xff);
    //    9   15:aload_3         
    //   10   16:iconst_0        
    //   11   17:baload          
    //   12   18:bipush          24
    //   13   20:ishl            
    //   14   21:aload_3         
    //   15   22:iconst_1        
    //   16   23:baload          
    //   17   24:bipush          16
    //   18   26:ishl            
    //   19   27:iadd            
    //   20   28:aload_3         
    //   21   29:iconst_2        
    //   22   30:baload          
    //   23   31:bipush          8
    //   24   33:ishl            
    //   25   34:iadd            
    //   26   35:aload_3         
    //   27   36:iconst_3        
    //   28   37:baload          
    //   29   38:sipush          255
    //   30   41:iand            
    //   31   42:iadd            
    //   32   43:ireturn         
        }
    }

//    public volatile Record decodeRecord(NdefRecord ndefrecord, NdefMessageDecoder ndefmessagedecoder)
//    {
//        return decodeRecord(ndefrecord, ndefmessagedecoder);
//    //    0    0:aload_0         
//    //    1    1:aload_1         
//    //    2    2:aload_2         
//    //    3    3:invokevirtual   #84  <Method com.aofei.nfc.ndef.records.DirectNDEFRecord com.aofei.nfc.ndef.decorder.DirectNdefRecordDecoder.decodeRecord(com.aofei.nfc.ndef.NdefRecord, com.aofei.nfc.ndef.NdefMessageDecoder)>
//    //    4    6:areturn         
//    }
}