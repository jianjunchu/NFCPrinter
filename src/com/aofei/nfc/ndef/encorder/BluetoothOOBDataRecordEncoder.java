// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:42:23
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   BluetoothOOBDataRecordEncoder.java

package com.aofei.nfc.ndef.encorder;

import com.aofei.nfc.ndef.NdefMessageEncoder;
import com.aofei.nfc.ndef.NdefRecord;
import com.aofei.nfc.ndef.records.BluetoothOOBDataRecord;
import com.aofei.nfc.ndef.records.Record;
import com.aofei.utils.ByteUtils;
import com.aofei.utils.Hex;
import java.io.*;

// Referenced classes of package com.aofei.nfc.ndef.encorder:
//            RecordEncoder

public class BluetoothOOBDataRecordEncoder
    implements RecordEncoder
{

    public BluetoothOOBDataRecordEncoder()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #10  <Method void Object()>
    //    2    4:return          
    }

    public boolean canEncode(Record record)
    {
        return record instanceof BluetoothOOBDataRecord;
    //    0    0:aload_1         
    //    1    1:instanceof      #18  <Class BluetoothOOBDataRecord>
    //    2    4:ireturn         
    }

    public NdefRecord encodeRecord(Record record, NdefMessageEncoder messageEncoder)
    {
    	try{
        BluetoothOOBDataRecord btRecord = (BluetoothOOBDataRecord)record;
    //    0    0:aload_1         
    //    1    1:checkcast       #18  <Class BluetoothOOBDataRecord>
    //    2    4:astore_3        
        byte payload[];
        byte oopData[] = btRecord.encode();
    //    3    5:aload_3         
    //    4    6:invokevirtual   #24  <Method byte[] BluetoothOOBDataRecord.encode()>
    //    5    9:astore          4
        ByteArrayOutputStream out = new ByteArrayOutputStream();
    //    6   11:new             #28  <Class ByteArrayOutputStream>
    //    7   14:dup             
    //    8   15:invokespecial   #30  <Method void ByteArrayOutputStream()>
    //    9   18:astore          5
        int oopDataLength = oopData.length + 2;
    //   10   20:aload           4
    //   11   22:arraylength     
    //   12   23:iconst_2        
    //   13   24:iadd            
    //   14   25:istore          6
        byte len[] = ByteUtils.intTo4Bytes(oopDataLength);
    //   15   27:iload           6
    //   16   29:invokestatic    #31  <Method byte[] ByteUtils.intTo4Bytes(int)>
    //   17   32:astore          7
        System.out.println((new StringBuilder("oopDataLength")).append(Hex.bytesToHexString(len)).toString());
    //   18   34:getstatic       #37  <Field PrintStream System.out>
    //   19   37:new             #43  <Class StringBuilder>
    //   20   40:dup             
    //   21   41:ldc1            #45  <String "oopDataLength">
    //   22   43:invokespecial   #47  <Method void StringBuilder(String)>
    //   23   46:aload           7
    //   24   48:invokestatic    #50  <Method String Hex.bytesToHexString(byte[])>
    //   25   51:invokevirtual   #56  <Method StringBuilder StringBuilder.append(String)>
    //   26   54:invokevirtual   #60  <Method String StringBuilder.toString()>
    //   27   57:invokevirtual   #64  <Method void PrintStream.println(String)>
        out.write(len[3]);
    //   28   60:aload           5
    //   29   62:aload           7
    //   30   64:iconst_3        
    //   31   65:baload          
    //   32   66:invokevirtual   #69  <Method void ByteArrayOutputStream.write(int)>
        out.write(len[2]);
    //   33   69:aload           5
    //   34   71:aload           7
    //   35   73:iconst_2        
    //   36   74:baload          
    //   37   75:invokevirtual   #69  <Method void ByteArrayOutputStream.write(int)>
        out.write(oopData);
    //   38   78:aload           5
    //   39   80:aload           4
    //   40   82:invokevirtual   #73  <Method void ByteArrayOutputStream.write(byte[])>
        payload = out.toByteArray();
    //   41   85:aload           5
    //   42   87:invokevirtual   #76  <Method byte[] ByteArrayOutputStream.toByteArray()>
    //   43   90:astore          8
        return new NdefRecord((short)2, "application/vnd.bluetooth.ep.oob".getBytes(), record.getId(), payload);
    //   44   92:new             #79  <Class NdefRecord>
    //   45   95:dup             
    //   46   96:iconst_2        
    //   47   97:ldc1            #81  <String "application/vnd.bluetooth.ep.oob">
    //   48   99:invokevirtual   #83  <Method byte[] String.getBytes()>
    //   49  102:aload_1         
    //   50  103:invokevirtual   #88  <Method byte[] Record.getId()>
    //   51  106:aload           8
    //   52  108:invokespecial   #93  <Method void NdefRecord(short, byte[], byte[], byte[])>
    //   53  111:areturn         

    //   54  112:astore          4
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    		return new NdefRecord((short)2, "application/vnd.bluetooth.ep.oob".getBytes(), record.getId(), new byte[1]);	
    	}
        
    //   55  114:new             #79  <Class NdefRecord>
    //   56  117:dup             
    //   57  118:iconst_2        
    //   58  119:ldc1            #81  <String "application/vnd.bluetooth.ep.oob">
    //   59  121:invokevirtual   #83  <Method byte[] String.getBytes()>
    //   60  124:aload_1         
    //   61  125:invokevirtual   #88  <Method byte[] Record.getId()>
    //   62  128:iconst_1        
    //   63  129:newarray        byte[]
    //   64  131:invokespecial   #93  <Method void NdefRecord(short, byte[], byte[], byte[])>
    //   65  134:areturn         
    }
}