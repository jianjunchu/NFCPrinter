// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:42:23
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   VCardRecordEncoder.java

package com.aofei.nfc.ndef.encorder;

import com.aofei.nfc.ndef.NdefMessageEncoder;
import com.aofei.nfc.ndef.NdefRecord;
import com.aofei.nfc.ndef.records.Record;
import com.aofei.nfc.ndef.records.VCardRecord;
import com.aofei.utils.Hex;
import info.ineighborhood.cardme.io.*;
import info.ineighborhood.cardme.vcard.VCardVersion;
import java.io.PrintStream;

// Referenced classes of package com.aofei.nfc.ndef.encorder:
//            RecordEncoder

public class VCardRecordEncoder
    implements RecordEncoder
{

    public VCardRecordEncoder()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #10  <Method void Object()>
    //    2    4:return          
    }

    public boolean canEncode(Record record)
    {
        return record instanceof VCardRecord;
    //    0    0:aload_1         
    //    1    1:instanceof      #18  <Class VCardRecord>
    //    2    4:ireturn         
    }

    public NdefRecord encodeRecord(Record record, NdefMessageEncoder messageEncoder)
    {
        VCardRecord myRecord = (VCardRecord)record;
    //    0    0:aload_1         
    //    1    1:checkcast       #18  <Class VCardRecord>
    //    2    4:astore_3        
        info.ineighborhood.cardme.vcard.VCard vCard = myRecord.getvCard();
    //    3    5:aload_3         
    //    4    6:invokevirtual   #24  <Method info.ineighborhood.cardme.vcard.VCard VCardRecord.getvCard()>
    //    5    9:astore          4
        VCardWriter vcardWriter = new VCardWriter();
    //    6   11:new             #28  <Class VCardWriter>
    //    7   14:dup             
    //    8   15:invokespecial   #30  <Method void VCardWriter()>
    //    9   18:astore          5
        vcardWriter.setOutputVersion(VCardVersion.V3_0);
    //   10   20:aload           5
    //   11   22:getstatic       #31  <Field VCardVersion VCardVersion.V3_0>
    //   12   25:invokevirtual   #37  <Method void VCardWriter.setOutputVersion(VCardVersion)>
        vcardWriter.setFoldingScheme(FoldingScheme.NONE);
    //   13   28:aload           5
    //   14   30:getstatic       #41  <Field FoldingScheme FoldingScheme.NONE>
    //   15   33:invokevirtual   #47  <Method void VCardWriter.setFoldingScheme(FoldingScheme)>
        vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
    //   16   36:aload           5
    //   17   38:getstatic       #51  <Field CompatibilityMode CompatibilityMode.RFC2426>
    //   18   41:invokevirtual   #57  <Method void VCardWriter.setCompatibilityMode(CompatibilityMode)>
        vcardWriter.setVCard(vCard);
    //   19   44:aload           5
    //   20   46:aload           4
    //   21   48:invokevirtual   #61  <Method void VCardWriter.setVCard(info.ineighborhood.cardme.vcard.VCard)>
        String vcardString = vcardWriter.buildVCardString();
    //   22   51:aload           5
    //   23   53:invokevirtual   #65  <Method String VCardWriter.buildVCardString()>
    //   24   56:astore          6
        System.out.println(vcardString);
    //   25   58:getstatic       #69  <Field PrintStream System.out>
    //   26   61:aload           6
    //   27   63:invokevirtual   #75  <Method void PrintStream.println(String)>
        System.out.println(Hex.bytesToHexString(vcardString.getBytes()));
    //   28   66:getstatic       #69  <Field PrintStream System.out>
    //   29   69:aload           6
    //   30   71:invokevirtual   #81  <Method byte[] String.getBytes()>
    //   31   74:invokestatic    #87  <Method String Hex.bytesToHexString(byte[])>
    //   32   77:invokevirtual   #75  <Method void PrintStream.println(String)>
        byte payload[] = vcardString.getBytes();
    //   33   80:aload           6
    //   34   82:invokevirtual   #81  <Method byte[] String.getBytes()>
    //   35   85:astore          7
        return new NdefRecord((short)2, VCardRecord.TYPE, record.getId(), payload);
    //   36   87:new             #93  <Class NdefRecord>
    //   37   90:dup             
    //   38   91:iconst_2        
    //   39   92:getstatic       #95  <Field byte[] VCardRecord.TYPE>
    //   40   95:aload_1         
    //   41   96:invokevirtual   #99  <Method byte[] Record.getId()>
    //   42   99:aload           7
    //   43  101:invokespecial   #104 <Method void NdefRecord(short, byte[], byte[], byte[])>
    //   44  104:areturn         
    }
}