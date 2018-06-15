// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 19:43:23
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ByteUtils.java

package com.aofei.utils;

import java.io.*;
import java.net.URLEncoder;

public class ByteUtils
{

    public ByteUtils()
    {
    }

    public static byte[] convertHexAsciiToByteArray(byte bytes[])
    {
        return convertHexAsciiToByteArray(bytes, 0, bytes.length);
    }

    public static byte[] convertHexAsciiToByteArray(byte bytes[], int offset, int length)
    {
        byte bin[] = new byte[length / 2];
        for(int x = 0; x < length / 2; x++)
            bin[x] = (byte)Integer.parseInt(new String(bytes, offset + x * 2, 2), 16);

        return bin;
    }

    public static byte[] convertASCIIToBin(String ascii)
    {
        byte bin[] = new byte[ascii.length() / 2];
        for(int x = 0; x < bin.length; x++)
            bin[x] = (byte)Integer.parseInt(ascii.substring(x * 2, x * 2 + 2), 16);

        return bin;
    }

    public static String convertBinToASCII(byte bin[])
    {
        return convertBinToASCII(bin, 0, bin.length);
    }

    public static String convertBinToASCII(byte bin[], int offset, int length)
    {
        StringBuilder sb = new StringBuilder();
        for(int x = offset; x < offset + length; x++)
        {
            String s = Integer.toHexString(bin[x]);
            if(s.length() == 1)
                sb.append('0');
            else
                s = s.substring(s.length() - 2);
            sb.append(s);
        }

        return sb.toString().toUpperCase();
    }

    public static byte[] toByteArray(Number data)
    {
        Class dataType = data.getClass();
        int length;
        long value;
        if(dataType==java.lang.Byte.class)
        {
            length = 1;
            value = ((Byte)data).byteValue();
        } else
        if(java.lang.Short.class == dataType)
        {
            length = 2;
            value = ((Short)data).shortValue();
        } else
        if(java.lang.Integer.class == dataType)
        {
            length = 4;
            value = ((Integer)data).intValue();
        } else
        if(java.lang.Long.class == dataType)
        {
            length = 8;
            value = ((Long)data).longValue();
        } else
        {
            throw new IllegalArgumentException("Parameter must be one of the following types:\n Byte, Short, Integer, Long");
        }
        byte byteArray[] = new byte[length];
        for(int i = 0; i < length; i++)
            byteArray[i] = (byte)(int)(value >> 8 * (length - i - 1) & 255L);

        return byteArray;
    }

    public static byte[] intTo4Bytes(int i)
    {
        return (new byte[] {
            (byte)(i >> 24 & 0xff), (byte)(i >> 16 & 0xff), (byte)(i >> 8 & 0xff), (byte)(i & 0xff)
        });
    }

    public static int bytesToInt(byte b[], int offset)
    {
        return (b[offset + 0] & 0xff) << 24 | (b[offset + 1] & 0xff) << 16 | (b[offset + 2] & 0xff) << 8 | b[offset + 3] & 0xff;
    }

    public static int bytesAsciiToByte(byte b[], int offset)
    {
        return (b[offset] << 4 | b[offset + 1]) << 24 | (b[offset + 2] << 4 | b[offset + 3]) << 16 | (b[offset + 4] << 4 | b[offset + 5]) << 8 | (b[offset + 6] << 4 | b[offset + 7]);
    }

    public static byte bytesToByte(byte b[], int offset)
    {
        return (byte)(b[offset] << 4 | b[offset + 1]);
    }

    public static String encodeUrl(String url)
    {
        try {
			return URLEncoder.encode(url, "utf8");
		} catch (UnsupportedEncodingException e) {
	        throw new RuntimeException(e);	    
		}
        }

    public static boolean isEqualArray(byte b1[], byte b2[])
    {
        if(b1.length != b2.length)
            return false;
        for(int i = 0; i < b1.length; i++)
            if(b1[i] != b2[i])
                return false;

        return true;
    }

    public static byte[] getBytesFromStream(int length, ByteArrayInputStream bais)
    {
        byte bytes[] = new byte[length];
        bais.read(bytes, 0, bytes.length);
        return bytes;
    }

    public static byte[] getBytesFromStream(int length, InputStream bais)
        throws IOException
    {
        byte bytes[] = new byte[length];
        bais.read(bytes, 0, bytes.length);
        return bytes;
    }

    public static String fromUTF8ByteArray(byte bytes[])
    {
        int i = 0;
        int length = 0;
        while(i < bytes.length) 
        {
            length++;
            if((bytes[i] & 0xf0) == 240)
            {
                length++;
                i += 4;
            } else
            if((bytes[i] & 0xe0) == 224)
                i += 3;
            else
            if((bytes[i] & 0xc0) == 192)
                i += 2;
            else
                i++;
        }
        char cs[] = new char[length];
        i = 0;
        length = 0;
        while(i < bytes.length) 
        {
            char ch;
            if((bytes[i] & 0xf0) == 240)
            {
                int codePoint = (bytes[i] & 3) << 18 | (bytes[i + 1] & 0x3f) << 12 | (bytes[i + 2] & 0x3f) << 6 | bytes[i + 3] & 0x3f;
                int U = codePoint - 0x10000;
                char W1 = (char)(0xd800 | U >> 10);
                char W2 = (char)(0xdc00 | U & 0x3ff);
                cs[length++] = W1;
                ch = W2;
                i += 4;
            } else
            if((bytes[i] & 0xe0) == 224)
            {
                ch = (char)((bytes[i] & 0xf) << 12 | (bytes[i + 1] & 0x3f) << 6 | bytes[i + 2] & 0x3f);
                i += 3;
            } else
            if((bytes[i] & 0xd0) == 208)
            {
                ch = (char)((bytes[i] & 0x1f) << 6 | bytes[i + 1] & 0x3f);
                i += 2;
            } else
            if((bytes[i] & 0xc0) == 192)
            {
                ch = (char)((bytes[i] & 0x1f) << 6 | bytes[i + 1] & 0x3f);
                i += 2;
            } else
            {
                ch = (char)(bytes[i] & 0xff);
                i++;
            }
            cs[length++] = ch;
        }
        return new String(cs);
    }
}