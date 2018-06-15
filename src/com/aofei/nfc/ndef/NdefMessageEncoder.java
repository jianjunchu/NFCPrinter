// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:31:38
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   NdefMessageEncoder.java

package com.aofei.nfc.ndef;

import com.aofei.nfc.ndef.records.Record;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.ListIterator;

// Referenced classes of package com.aofei.nfc.ndef:
//            NdefRecordEncoder, NdefRecord

public class NdefMessageEncoder
{

    public NdefMessageEncoder(NdefRecordEncoder ndefRecordEncoder)
    {
        this.ndefRecordEncoder = ndefRecordEncoder;
    }

    public byte[] encodeSingle(Record record)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte header = -64;
        NdefRecord ndefRecord = ndefRecordEncoder.encode(record, this);
        writeNdefRecord(baos, header, ndefRecord);
        return baos.toByteArray();
    }

    public byte[] encode(List records)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte header;
        NdefRecord ndefRecord;
        for(ListIterator it = records.listIterator(); it.hasNext(); writeNdefRecord(baos, header, ndefRecord))
        {
            header = 0;
            header = setMessageBeginIfFirstRecord(it, header);
            Record record = (Record)it.next();
            header = setMessageEndIfLastRecord(it, header);
            ndefRecord = ndefRecordEncoder.encode(record, this);
        }

        return baos.toByteArray();
    }

    private byte setMessageBeginIfFirstRecord(ListIterator it, byte header)
    {
        if(!it.hasPrevious())
            header |= 0x80;
        return header;
    }

    private byte setMessageEndIfLastRecord(ListIterator it, byte header)
    {
        if(!it.hasNext())
            header |= 0x40;
        return header;
    }

    private void writeNdefRecord(ByteArrayOutputStream baos, byte header, NdefRecord ndefRecord)
    {
        writeHeader(baos, header, ndefRecord);
        baos.write(ndefRecord.getType().length);
        writePayloadLength(baos, ndefRecord.getPayload().length);
        writeIdLength(baos, ndefRecord.getId().length);
        writeBytes(baos, ndefRecord.getType());
        writeBytes(baos, ndefRecord.getId());
        writeBytes(baos, ndefRecord.getPayload());
    }

    private void writeHeader(ByteArrayOutputStream baos, byte header, NdefRecord ndefRecord)
    {
        header = setShortRecord(header, ndefRecord);
        header = setIdLength(header, ndefRecord);
        header = setTypeNameFormat(header, ndefRecord);
        baos.write(header);
    }

    private byte setShortRecord(byte header, NdefRecord ndefRecord)
    {
        if(ndefRecord.getPayload().length <= 255)
            header |= 0x10;
        return header;
    }

    private byte setIdLength(byte header, NdefRecord ndefRecord)
    {
        if(ndefRecord.getId().length > 0)
            header |= 8;
        return header;
    }

    private byte setTypeNameFormat(byte header, NdefRecord ndefRecord)
    {
        header |= ndefRecord.getTnf();
        return header;
    }

    private void writeBytes(ByteArrayOutputStream baos, byte bytes[])
    {
        baos.write(bytes, 0, bytes.length);
    }

    private void writeIdLength(ByteArrayOutputStream baos, int length)
    {
        if(length > 0)
            baos.write(length);
    }

    private void writePayloadLength(ByteArrayOutputStream baos, int length)
    {
        if(length <= 255)
        {
            baos.write(length);
        } else
        {
            byte payloadLengthArray[] = new byte[4];
            payloadLengthArray[0] = (byte)(length >>> 24);
            payloadLengthArray[1] = (byte)(length >>> 16);
            payloadLengthArray[2] = (byte)(length >>> 8);
            payloadLengthArray[3] = (byte)(length & 0xff);
            baos.write(payloadLengthArray, 0, payloadLengthArray.length);
        }
    }

    private static final int MAX_LENGTH_FOR_SHORT_RECORD = 255;
    private NdefRecordEncoder ndefRecordEncoder;
}