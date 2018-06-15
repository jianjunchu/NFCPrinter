// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 19:33:07
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   NdefUtil.java

package com.aofei.nfc.tag.utils;

import com.aofei.nfc.utils.NfcUtils;
import com.aofei.utils.Hex;
import java.io.ByteArrayInputStream;
import org.apache.log4j.Logger;

public class NdefUtil
{

    public NdefUtil()
    {
    }

    public static byte[] analysisNdefTlv(byte readAll[])
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(readAll);
        int size = 0;
        while(bais.available() > 0) 
        {
            int tlv = bais.read();
            switch(tlv)
            {
            case 0: // '\0'
            default:
                break;

            case 1: // '\001'
                size = bais.read();
                NfcUtils.getBytesFromStream(size, bais);
                break;

            case 2: // '\002'
                size = bais.read();
                NfcUtils.getBytesFromStream(size, bais);
                break;

            case 3: // '\003'
                size = bais.read();
                if(size == 255)
                {
                    byte sizeTwoByte[] = new byte[2];
                    sizeTwoByte[0] = (byte)bais.read();
                    sizeTwoByte[1] = (byte)bais.read();
                    LOGGER.info((new StringBuilder("[TLV] size: ")).append(Hex.bytesToHexString(sizeTwoByte)).append("(h)").toString());
                    byte sizeFourByte[] = new byte[4];
                    sizeFourByte[0] = 0;
                    sizeFourByte[1] = 0;
                    sizeFourByte[2] = sizeTwoByte[0];
                    sizeFourByte[3] = sizeTwoByte[1];
                    size = NfcUtils.bytesToInt(sizeFourByte, 0);
                }
                return NfcUtils.getBytesFromStream(size, bais);
            }
        }
        return null;
    }

    public static int analysisNdefSize(byte readAll[])
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(readAll);
        int size = 0;
        while(bais.available() > 0) 
        {
            int tlv = bais.read();
            switch(tlv)
            {
            case 0: // '\0'
            default:
                break;

            case 1: // '\001'
                size = bais.read();
                NfcUtils.getBytesFromStream(size, bais);
                break;

            case 2: // '\002'
                size = bais.read();
                NfcUtils.getBytesFromStream(size, bais);
                break;

            case 3: // '\003'
                size = bais.read();
                if(size == 255)
                {
                    byte sizeTwoByte[] = new byte[2];
                    sizeTwoByte[0] = (byte)bais.read();
                    sizeTwoByte[1] = (byte)bais.read();
                    LOGGER.info((new StringBuilder("[TLV] size: ")).append(Hex.bytesToHexString(sizeTwoByte)).append("(h)").toString());
                    byte sizeFourByte[] = new byte[4];
                    sizeFourByte[0] = 0;
                    sizeFourByte[1] = 0;
                    sizeFourByte[2] = sizeTwoByte[0];
                    sizeFourByte[3] = sizeTwoByte[1];
                    size = NfcUtils.bytesToInt(sizeFourByte, 0);
                }
                return size;
            }
        }
        return -1;
    }

    private static byte[] getRoundedByteArray(int value, int roundToBytes)
    {
        String hexString = Integer.toHexString(value);
        StringBuffer buff = new StringBuffer();
        for(int i = hexString.length(); i < roundToBytes * 2; i++)
            buff.append("0");

        buff.append(hexString);
        return Hex.hexStringToBytes(buff.toString());
    }

    public static byte[] attachPadding(byte ndef[], int type)
    {
        byte paddedNdef[] = null;
        switch(type)
        {
        case -16: 
        case -15: 
        case 0: // '\0'
            paddedNdef = new byte[ndef.length + 2];
            System.arraycopy(getRoundedByteArray(ndef.length, 2), 0, paddedNdef, 0, 2);
            System.arraycopy(ndef, 0, paddedNdef, 2, ndef.length);
            if(ndef.length > 255)
            {
                byte responseByte2[] = new byte[paddedNdef.length + 3];
                responseByte2[0] = 3;
                responseByte2[1] = -1;
                System.arraycopy(paddedNdef, 0, responseByte2, 2, paddedNdef.length);
                byte responseTail[] = {
                    -2
                };
                System.arraycopy(responseTail, 0, responseByte2, paddedNdef.length + 2, 1);
                return responseByte2;
            } else
            {
                byte responseByte2[] = new byte[paddedNdef.length + 1];
                responseByte2[0] = 3;
                System.arraycopy(paddedNdef, 1, responseByte2, 1, paddedNdef.length - 1);
                byte responseTail[] = {
                    -2
                };
                System.arraycopy(responseTail, 0, responseByte2, paddedNdef.length, 1);
                return responseByte2;
            }

        case 10: // '\n'
            paddedNdef = new byte[ndef.length + 2];
            byte length[] = new byte[4];
            length[3] = (byte)(ndef.length & 0xff);
            length[2] = (byte)(ndef.length >> 8 & 0xff);
            length[1] = (byte)(ndef.length >> 16 & 0xff);
            length[0] = (byte)(ndef.length >> 24 & 0xff);
            paddedNdef[0] = length[2];
            paddedNdef[1] = length[3];
            System.arraycopy(ndef, 0, paddedNdef, 2, ndef.length);
            break;
        }
        return paddedNdef;
    }

    private static byte[] generateAttributeBlock(byte block0[], int length)
    {
        byte len[] = NfcUtils.intTo4Bytes(length);
        LOGGER.debug((new StringBuilder("NDEF len: ")).append(Hex.bytesToHexString(len)).toString());
        block0[11] = len[1];
        block0[12] = len[2];
        block0[13] = len[3];
        int crc = cacluateCRC(block0);
        byte cRc[] = NfcUtils.intTo4Bytes(crc);
        LOGGER.debug((new StringBuilder("CRC: ")).append(Hex.bytesToHexString(cRc)).toString());
        block0[14] = cRc[2];
        block0[15] = cRc[3];
        LOGGER.debug((new StringBuilder("Attribute Block: ")).append(Hex.bytesToHexString(block0)).toString());
        return block0;
    }

    private static int cacluateCRC(byte block0[])
    {
        int sum = 0;
        for(int i = 0; i < 14; i++)
            sum += block0[i] & 0xff;

        return sum;
    }

    private static final Logger LOGGER = Logger.getLogger("com.aofei.nfc.tag.utils.NdefUtil");
    public static final byte NDEF_RECORD_HEAD = 3;
    public static final byte NDEF_RECORD_TAIL = -2;
    public static final byte TRAILER_CONTENT_UNLOAKED[] = {
        -45, -9, -45, -9, -45, -9, 127, 7, -120, 64, 
        -45, -9, -45, -9, -45, -9
    };
    public static final byte TRAILER_CONTENT_LOAKED[] = {
        -45, -9, -45, -9, -45, -9, 120, 119, -120, 67, 
        106, 97, 121, 100, 101, 110
    };

}
