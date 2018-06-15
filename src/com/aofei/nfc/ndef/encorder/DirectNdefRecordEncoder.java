// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:42:23
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   DirectNdefRecordEncoder.java

package com.aofei.nfc.ndef.encorder;

import com.aofei.nfc.ndef.NdefMessageEncoder;
import com.aofei.nfc.ndef.NdefRecord;
import com.aofei.nfc.ndef.records.DirectNDEFRecord;
import com.aofei.nfc.ndef.records.Record;
import com.aofei.nfc.utils.NfcUtils;
import com.aofei.utils.Hex;
import java.io.ByteArrayInputStream;

// Referenced classes of package com.aofei.nfc.ndef.encorder:
//            RecordEncoder

public class DirectNdefRecordEncoder
    implements RecordEncoder
{

    public DirectNdefRecordEncoder()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #10  <Method void Object()>
    //    2    4:return          
    }

    public boolean canEncode(Record record)
    {
        return record instanceof DirectNDEFRecord;
    //    0    0:aload_1         
    //    1    1:instanceof      #18  <Class DirectNDEFRecord>
    //    2    4:ireturn         
    }

    public NdefRecord encodeRecord(Record record, NdefMessageEncoder messageEncoder)
    {
    	try{
        ByteArrayInputStream bais;
        String strNDEF = ((DirectNDEFRecord)record).getNdef();
    //    0    0:aload_1         
    //    1    1:checkcast       #18  <Class DirectNDEFRecord>
    //    2    4:invokevirtual   #24  <Method String DirectNDEFRecord.getNdef()>
    //    3    7:astore_3        
        byte ndef[] = null;
    //    4    8:aconst_null     
    //    5    9:astore          4
        if(strNDEF != null)
    //*   6   11:aload_3         
    //*   7   12:ifnull          24
            ndef = Hex.hexStringToBytes(strNDEF);
    //    8   15:aload_3         
    //    9   16:invokestatic    #28  <Method byte[] Hex.hexStringToBytes(String)>
    //   10   19:astore          4
        else
    //*  11   21:goto            31
            ndef = "D00000".getBytes();
    //   12   24:ldc1            #34  <String "D00000">
    //   13   26:invokevirtual   #36  <Method byte[] String.getBytes()>
    //   14   29:astore          4
        bais = new ByteArrayInputStream(ndef);
    //   15   31:new             #42  <Class ByteArrayInputStream>
    //   16   34:dup             
    //   17   35:aload           4
    //   18   37:invokespecial   #44  <Method void ByteArrayInputStream(byte[])>
    //   19   40:astore          5
        short tnf;
        byte type[];
        byte id[];
        byte payload[];
        int header = bais.read();
    //   20   42:aload           5
    //   21   44:invokevirtual   #47  <Method int ByteArrayInputStream.read()>
    //   22   47:istore          6
        tnf = (short)(header & 7);
    //   23   49:iload           6
    //   24   51:bipush          7
    //   25   53:iand            
    //   26   54:int2short       
    //   27   55:istore          7
        int typeLength = bais.read();
    //   28   57:aload           5
    //   29   59:invokevirtual   #47  <Method int ByteArrayInputStream.read()>
    //   30   62:istore          8
        int payloadLength = getPayloadLength((header & 0x10) != 0, bais);
    //   31   64:aload_0         
    //   32   65:iload           6
    //   33   67:bipush          16
    //   34   69:iand            
    //   35   70:ifeq            77
    //   36   73:iconst_1        
    //   37   74:goto            78
    //   38   77:iconst_0        
    //   39   78:aload           5
    //   40   80:invokespecial   #51  <Method int getPayloadLength(boolean, ByteArrayInputStream)>
    //   41   83:istore          9
        int idLength = getIdLength((header & 8) != 0, bais);
    //   42   85:aload_0         
    //   43   86:iload           6
    //   44   88:bipush          8
    //   45   90:iand            
    //   46   91:ifeq            98
    //   47   94:iconst_1        
    //   48   95:goto            99
    //   49   98:iconst_0        
    //   50   99:aload           5
    //   51  101:invokespecial   #55  <Method int getIdLength(boolean, ByteArrayInputStream)>
    //   52  104:istore          10
        type = NfcUtils.getBytesFromStream(typeLength, bais);
    //   53  106:iload           8
    //   54  108:aload           5
    //   55  110:invokestatic    #58  <Method byte[] NfcUtils.getBytesFromStream(int, ByteArrayInputStream)>
    //   56  113:astore          11
        id = NfcUtils.getBytesFromStream(idLength, bais);
    //   57  115:iload           10
    //   58  117:aload           5
    //   59  119:invokestatic    #58  <Method byte[] NfcUtils.getBytesFromStream(int, ByteArrayInputStream)>
    //   60  122:astore          12
        payload = NfcUtils.getBytesFromStream(payloadLength, bais);
    //   61  124:iload           9
    //   62  126:aload           5
    //   63  128:invokestatic    #58  <Method byte[] NfcUtils.getBytesFromStream(int, ByteArrayInputStream)>
    //   64  131:astore          13
    	
        return new NdefRecord(tnf, type, id, payload);
    //   65  133:new             #64  <Class NdefRecord>
    //   66  136:dup             
    //   67  137:iload           7
    //   68  139:aload           11
    //   69  141:aload           12
    //   70  143:aload           13
    //   71  145:invokespecial   #66  <Method void NdefRecord(short, byte[], byte[], byte[])>
    //   72  148:areturn         
    //   73  149:astore          6

    //   74  151:aload           6
    //   75  153:invokevirtual   #69  <Method void Exception.printStackTrace()>
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    		return null;
    	}
        
    //   76  156:aconst_null     
    //   77  157:areturn         
    }

    private int getIdLength(boolean idLengthPresent, ByteArrayInputStream bais)
    {
        if(idLengthPresent)
    //*   0    0:iload_1         
    //*   1    1:ifeq            9
            return bais.read();
    //    2    4:aload_2         
    //    3    5:invokevirtual   #47  <Method int ByteArrayInputStream.read()>
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
    //    3    5:invokevirtual   #47  <Method int ByteArrayInputStream.read()>
    //    4    8:ireturn         
        } else
        {
            byte buffer[] = NfcUtils.getBytesFromStream(4, bais);
    //    5    9:iconst_4        
    //    6   10:aload_2         
    //    7   11:invokestatic    #58  <Method byte[] NfcUtils.getBytesFromStream(int, ByteArrayInputStream)>
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
}