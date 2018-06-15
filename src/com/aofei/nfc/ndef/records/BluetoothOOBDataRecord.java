// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:38:21
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   BluetoothOOBDataRecord.java

package com.aofei.nfc.ndef.records;

import com.aofei.nfc.ndef.records.handover.OOBData;
import com.aofei.utils.*;
import java.io.*;
import java.util.ArrayList;

// Referenced classes of package com.aofei.nfc.ndef.records:
//            Record

public class BluetoothOOBDataRecord extends Record
{
    public static interface EIR
    {

        public static final byte COMPLETE_LOCAL_NAME = 9;
        public static final byte COMPLETE_CLASS_OF_DEVICE = 13;
        public static final byte COMPLETE_16BIT_SERVICE_CLASS_UUID_LIST = 3;
    }

    public static interface ServiceClassUUIDList
    {

        public static final byte HSP[] = {
            8, 17
        };
        public static final byte A2DP[] = {
            11, 17
        };

        /* static  */
        /* { */
        //    0    0:iconst_2        
        //    1    1:newarray        byte[]
        //    2    3:dup             
        //    3    4:iconst_0        
        //    4    5:bipush          8
        //    5    7:bastore         
        //    6    8:dup             
        //    7    9:iconst_1        
        //    8   10:bipush          17
        //    9   12:bastore         
        //   10   13:putstatic       #11  <Field byte[] HSP>
        //   11   16:iconst_2        
        //   12   17:newarray        byte[]
        //   13   19:dup             
        //   14   20:iconst_0        
        //   15   21:bipush          11
        //   16   23:bastore         
        //   17   24:dup             
        //   18   25:iconst_1        
        //   19   26:bipush          17
        //   20   28:bastore         
        //   21   29:putstatic       #13  <Field byte[] A2DP>
        //*  22   32:return          
        /* } */
    }

    public static interface ServiceOfDevice
    {

        public static final byte AUDIO = 32;
        public static final byte AUDIO_VIDEO = 4;
        public static final byte WEARABLE_HEADSET_DEVICE = 4;
    }


    public static void main(String args[])
    {
        System.out.println(Hex.bytesToHexString("LG Portable_0051".getBytes()));
    //    0    0:getstatic       #17  <Field PrintStream System.out>
    //    1    3:ldc1            #23  <String "LG Portable_0051">
    //    2    5:invokevirtual   #25  <Method byte[] String.getBytes()>
    //    3    8:invokestatic    #31  <Method String Hex.bytesToHexString(byte[])>
    //    4   11:invokevirtual   #37  <Method void PrintStream.println(String)>
    //    5   14:return          
    }

    public BluetoothOOBDataRecord(byte macAddress[], OOBData options[])
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #49  <Method void Record()>
        this.macAddress = macAddress;
    //    2    4:aload_0         
    //    3    5:aload_1         
    //    4    6:putfield        #52  <Field byte[] macAddress>
        this.options = options;
    //    5    9:aload_0         
    //    6   10:aload_2         
    //    7   11:putfield        #54  <Field OOBData[] options>
    //    8   14:return          
    }

    public BluetoothOOBDataRecord()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #49  <Method void Record()>
    //    2    4:return          
    }

    public byte[] getMacAddress()
    {
        return macAddress;
    //    0    0:aload_0         
    //    1    1:getfield        #52  <Field byte[] macAddress>
    //    2    4:areturn         
    }

    public void setMacAddress(byte macAddress[])
    {
        this.macAddress = macAddress;
    //    0    0:aload_0         
    //    1    1:aload_1         
    //    2    2:putfield        #52  <Field byte[] macAddress>
    //    3    5:return          
    }

    public OOBData[] getOptions()
    {
        return options;
    //    0    0:aload_0         
    //    1    1:getfield        #54  <Field OOBData[] options>
    //    2    4:areturn         
    }

    public void setOptions(OOBData options[])
    {
        this.options = options;
    //    0    0:aload_0         
    //    1    1:aload_1         
    //    2    2:putfield        #54  <Field OOBData[] options>
    //    3    5:return          
    }

    public byte[] encode()
    {
        ByteArrayOutputStream payload = new ByteArrayOutputStream();
    //    0    0:new             #66  <Class ByteArrayOutputStream>
    //    1    3:dup             
    //    2    4:invokespecial   #68  <Method void ByteArrayOutputStream()>
    //    3    7:astore_1        
        byte abyte0[];
        byte abyte1[]= null;
        try{
        if(macAddress == null || macAddress.length != 6)
    //*   4    8:aload_0         
    //*   5    9:getfield        #52  <Field byte[] macAddress>
    //*   6   12:ifnull          25
    //*   7   15:aload_0         
    //*   8   16:getfield        #52  <Field byte[] macAddress>
    //*   9   19:arraylength     
    //*  10   20:bipush          6
    //*  11   22:icmpeq          36
            payload.write(new byte[6]);
    //   12   25:aload_1         
    //   13   26:bipush          6
    //   14   28:newarray        byte[]
    //   15   30:invokevirtual   #69  <Method void ByteArrayOutputStream.write(byte[])>
        else
    //*  16   33:goto            47
            payload.write(BcdUtils.reverseBytes(macAddress));
        }catch(Exception ex){}
    //   17   36:aload_1         
    //   18   37:aload_0         
    //   19   38:getfield        #52  <Field byte[] macAddress>
    //   20   41:invokestatic    #72  <Method byte[] BcdUtils.reverseBytes(byte[])>
    //   21   44:invokevirtual   #69  <Method void ByteArrayOutputStream.write(byte[])>
        if(options != null)
            //break MISSING_BLOCK_LABEL_76;
        {
            
            try
            {
                OOBData aoobdata[];
                int j = (aoobdata = options).length;
    //   36   76:aload_0         
    //   37   77:getfield        #54  <Field OOBData[] options>
    //   38   80:dup             
    //   39   81:astore          5
    //   40   83:arraylength     
    //   41   84:istore          4
                for(int i = 0; i < j; i++)
    //*  42   86:iconst_0        
    //*  43   87:istore_3        
    //*  44   88:goto            151
                {
                    OOBData option = aoobdata[i];
    //   45   91:aload           5
    //   46   93:iload_3         
    //   47   94:aaload          
    //   48   95:astore_2        
                    byte type = option.getType();
    //   49   96:aload_2         
    //   50   97:invokevirtual   #84  <Method byte OOBData.getType()>
    //   51  100:istore          6
                    byte data[] = option.getData();
    //   52  102:aload_2         
    //   53  103:invokevirtual   #90  <Method byte[] OOBData.getData()>
    //   54  106:astore          7
                    if(data == null)
    //*  55  108:aload           7
    //*  56  110:ifnonnull       127
                    {
                        payload.write(1);
    //   57  113:aload_1         
    //   58  114:iconst_1        
    //   59  115:invokevirtual   #93  <Method void ByteArrayOutputStream.write(int)>
                        payload.write(type);
    //   60  118:aload_1         
    //   61  119:iload           6
    //   62  121:invokevirtual   #93  <Method void ByteArrayOutputStream.write(int)>
                    } else
    //*  63  124:goto            148
                    {
                        payload.write(data.length + 1);
    //   64  127:aload_1         
    //   65  128:aload           7
    //   66  130:arraylength     
    //   67  131:iconst_1        
    //   68  132:iadd            
    //   69  133:invokevirtual   #93  <Method void ByteArrayOutputStream.write(int)>
                        payload.write(type);
    //   70  136:aload_1         
    //   71  137:iload           6
    //   72  139:invokevirtual   #93  <Method void ByteArrayOutputStream.write(int)>
                        payload.write(data);
    //   73  142:aload_1         
    //   74  143:aload           7
    //   75  145:invokevirtual   #69  <Method void ByteArrayOutputStream.write(byte[])>
                    }
                }

    //   76  148:iinc            3  1
    //   77  151:iload_3         
    //   78  152:iload           4
    //   79  154:icmplt          91
                abyte1 = payload.toByteArray();
    //   80  157:aload_1         
    //   81  158:invokevirtual   #78  <Method byte[] ByteArrayOutputStream.toByteArray()>
    //   82  161:astore          9
            }
    //*  83  163:aload_1         
    //*  84  164:ifnull          176
    //*  85  167:aload_1         
    //*  86  168:invokevirtual   #81  <Method void ByteArrayOutputStream.close()>
    //*  87  171:goto            176
    //*  88  174:astore          10
    //*  89  176:aload           9
    //*  90  178:areturn         
            catch(IOException ioexception)
    //*  91  179:astore_2        
            {
                try
                {
                    if(payload != null)
    //*  92  180:aload_1         
    //*  93  181:ifnull          214
                        payload.close();
    //   94  184:aload_1         
    //   95  185:invokevirtual   #81  <Method void ByteArrayOutputStream.close()>
                }
    //*  96  188:goto            214
                catch(IOException ioexception3) { }
    //   97  191:astore          10
                
    //   98  193:goto            214
            }
            finally
    //*  99  196:astore          8
            {
                try
                {
                    if(payload != null)
    //* 100  198:aload_1         
    //* 101  199:ifnull          211
                        payload.close();
    //  102  202:aload_1         
    //  103  203:invokevirtual   #81  <Method void ByteArrayOutputStream.close()>
                }
    //* 104  206:goto            211
                catch(IOException ioexception4) {
                	
                }
    //  105  209:astore          10
               
    //  106  211:aload           8
    //  107  213:athrow          
            }
            try
            {
                if(payload != null)
                    payload.close();
            }
            catch(IOException ioexception2) { }
            return abyte1;
        }
    //   22   47:aload_0         
    //   23   48:getfield        #54  <Field OOBData[] options>
    //   24   51:ifnonnull       76
        abyte0 = payload.toByteArray();
    //   25   54:aload_1         
    //   26   55:invokevirtual   #78  <Method byte[] ByteArrayOutputStream.toByteArray()>
    //   27   58:astore          9
        try
        {
            if(payload != null)
    //*  28   60:aload_1         
    //*  29   61:ifnull          73
                payload.close();
    //   30   64:aload_1         
    //   31   65:invokevirtual   #81  <Method void ByteArrayOutputStream.close()>
        }
    //*  32   68:goto            73
        catch(IOException ioexception1) { 
        	return null;
        }
    //   33   71:astore          10
        return abyte0;
    //   34   73:aload           9
    //   35   75:areturn         

       
        
    //  108  214:aconst_null     
    //  109  215:areturn         
    }

    public static BluetoothOOBDataRecord decode(byte payload[])
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(payload);
    //    0    0:new             #112 <Class ByteArrayInputStream>
    //    1    3:dup             
    //    2    4:aload_0         
    //    3    5:invokespecial   #114 <Method void ByteArrayInputStream(byte[])>
    //    4    8:astore_1        
        byte macAddress[];
        if(bais.available() > 2)
    //*   5    9:aload_1         
    //*   6   10:invokevirtual   #116 <Method int ByteArrayInputStream.available()>
    //*   7   13:iconst_2        
    //*   8   14:icmple          23
            macAddress = ByteUtils.getBytesFromStream(2, bais);
    //    9   17:iconst_2        
    //   10   18:aload_1         
    //   11   19:invokestatic    #120 <Method byte[] ByteUtils.getBytesFromStream(int, ByteArrayInputStream)>
    //   12   22:astore_2        
        macAddress = null;
    //   13   23:aconst_null     
    //   14   24:astore_2        
        if(bais.available() >= 6)
    //*  15   25:aload_1         
    //*  16   26:invokevirtual   #116 <Method int ByteArrayInputStream.available()>
    //*  17   29:bipush          6
    //*  18   31:icmplt          46
        {
            macAddress = ByteUtils.getBytesFromStream(6, bais);
    //   19   34:bipush          6
    //   20   36:aload_1         
    //   21   37:invokestatic    #120 <Method byte[] ByteUtils.getBytesFromStream(int, ByteArrayInputStream)>
    //   22   40:astore_2        
            macAddress = BcdUtils.reverseBytes(macAddress);
    //   23   41:aload_2         
    //   24   42:invokestatic    #72  <Method byte[] BcdUtils.reverseBytes(byte[])>
    //   25   45:astore_2        
        }
        ArrayList oobDataList = new ArrayList();
    //   26   46:new             #126 <Class ArrayList>
    //   27   49:dup             
    //   28   50:invokespecial   #128 <Method void ArrayList()>
    //   29   53:astore_3        
        OOBData oobData = null;
    //   30   54:aconst_null     
    //   31   55:astore          4
    //*  32   57:goto            104
        for(; bais.available() > 0; oobDataList.add(oobData))
        {
            byte eirLength = (byte)bais.read();
    //   33   60:aload_1         
    //   34   61:invokevirtual   #129 <Method int ByteArrayInputStream.read()>
    //   35   64:int2byte        
    //   36   65:istore          5
            byte eirType = (byte)bais.read();
    //   37   67:aload_1         
    //   38   68:invokevirtual   #129 <Method int ByteArrayInputStream.read()>
    //   39   71:int2byte        
    //   40   72:istore          6
            byte eirData[] = ByteUtils.getBytesFromStream(eirLength - 1, bais);
    //   41   74:iload           5
    //   42   76:iconst_1        
    //   43   77:isub            
    //   44   78:aload_1         
    //   45   79:invokestatic    #120 <Method byte[] ByteUtils.getBytesFromStream(int, ByteArrayInputStream)>
    //   46   82:astore          7
            oobData = new OOBData(eirType, eirData);
    //   47   84:new             #85  <Class OOBData>
    //   48   87:dup             
    //   49   88:iload           6
    //   50   90:aload           7
    //   51   92:invokespecial   #132 <Method void OOBData(byte, byte[])>
    //   52   95:astore          4
        }

    //   53   97:aload_3         
    //   54   98:aload           4
    //   55  100:invokevirtual   #135 <Method boolean ArrayList.add(Object)>
    //   56  103:pop             
    //   57  104:aload_1         
    //   58  105:invokevirtual   #116 <Method int ByteArrayInputStream.available()>
    //   59  108:ifgt            60
        OOBData a[] = new OOBData[oobDataList.size()];
    //   60  111:aload_3         
    //   61  112:invokevirtual   #139 <Method int ArrayList.size()>
    //   62  115:anewarray       OOBData[]
    //   63  118:astore          5
        OOBData options[] = (OOBData[])oobDataList.toArray(a);
    //   64  120:aload_3         
    //   65  121:aload           5
    //   66  123:invokevirtual   #142 <Method Object[] ArrayList.toArray(Object[])>
    //   67  126:checkcast       #107 <Class OOBData[]>
    //   68  129:astore          6
        return new BluetoothOOBDataRecord(macAddress, options);
    //   69  131:new             #1   <Class BluetoothOOBDataRecord>
    //   70  134:dup             
    //   71  135:aload_2         
    //   72  136:aload           6
    //   73  138:invokespecial   #146 <Method void BluetoothOOBDataRecord(byte[], OOBData[])>
    //   74  141:areturn         
    }

    public static final String TYPE = "application/vnd.bluetooth.ep.oob";
    private byte macAddress[];
    private OOBData options[];
}