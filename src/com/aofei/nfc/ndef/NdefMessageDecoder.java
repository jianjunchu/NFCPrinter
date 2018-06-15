// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:31:37
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   NdefMessageDecoder.java

package com.aofei.nfc.ndef;

import com.aofei.nfc.utils.NfcUtils;
import com.aofei.utils.Hex;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.aofei.nfc.ndef:
//            NdefMessage, NdefRecordDecoder, NdefRecord

public class NdefMessageDecoder
{

    public NdefMessageDecoder(NdefRecordDecoder ndefRecordDecoder)
    {
        this.ndefRecordDecoder = ndefRecordDecoder;
    }

    public List decodeToRecords(NdefMessage ndefMessage)
    {
        List records = new ArrayList();
        NdefRecord andefrecord[];
        int j = (andefrecord = ndefMessage.getNdefRecords()).length;
        for(int i = 0; i < j; i++)
        {
            NdefRecord ndefRecord = andefrecord[i];
            records.add(ndefRecordDecoder.decode(ndefRecord, this));
        }

        return records;
    }

    public NdefMessage decode(byte ndefMessage[])
    {
        List records = new ArrayList();
        short tnf;
        byte type[];
        byte id[];
        byte payload[];
        for(ByteArrayInputStream bais = new ByteArrayInputStream(ndefMessage); bais.available() > 0; records.add(new NdefRecord(tnf, type, id, payload)))
        {
            int header = bais.read();
            tnf = (short)(header & 7);
            int typeLength = bais.read();
            int payloadLength = getPayloadLength((header & 0x10) != 0, bais);
            int idLength = getIdLength((header & 8) != 0, bais);
            type = NfcUtils.getBytesFromStream(typeLength, bais);
            id = NfcUtils.getBytesFromStream(idLength, bais);
            payload = NfcUtils.getBytesFromStream(payloadLength, bais);
            System.out.println((new StringBuilder("type: ")).append(Hex.bytesToHexString(type)).toString());
            System.out.println((new StringBuilder("id: ")).append(Hex.bytesToHexString(id)).toString());
            System.out.println((new StringBuilder("payload: ")).append(Hex.bytesToHexString(payload)).toString());
            if(records.isEmpty() && (header & 0x80) == 0)
                throw new IllegalArgumentException("no Message Begin record at the begining");
            if(bais.available() == 0 && (header & 0x40) == 0)
                throw new IllegalArgumentException("no Message End record at the end of array");
            if((header & 0xfe) == 254)
                return new NdefMessage((NdefRecord[])records.toArray(new NdefRecord[0]));
        }

        return new NdefMessage((NdefRecord[])records.toArray(new NdefRecord[0]));
    }

    private int getIdLength(boolean idLengthPresent, ByteArrayInputStream bais)
    {
        if(idLengthPresent)
            return bais.read();
        else
            return 0;
    }

    private int getPayloadLength(boolean shortRecord, ByteArrayInputStream bais)
    {
        if(shortRecord)
        {
            return bais.read();
        } else
        {
            byte buffer[] = NfcUtils.getBytesFromStream(4, bais);
            return (buffer[0] << 24) + (buffer[1] << 16) + (buffer[2] << 8) + (buffer[3] & 0xff);
        }
    }

    private NdefRecordDecoder ndefRecordDecoder;
}