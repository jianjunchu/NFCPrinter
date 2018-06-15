// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 19:43:23
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   BcdUtils.java

package com.aofei.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class BcdUtils
{

    public BcdUtils()
    {
    }

    public static String binArrayToString(byte bin[])
    {
        StringBuilder sb = new StringBuilder();
        byte abyte0[];
        int j = (abyte0 = bin).length;
        for(int i = 0; i < j; i++)
        {
            Byte b = Byte.valueOf(abyte0[i]);
            int low = b.byteValue() & 0xf;
            int high = b.byteValue() >> 4 & 0xf;
            sb.append(byteToChar(high));
            sb.append(byteToChar(low));
        }

        return sb.toString();
    }

    public static byte[] stringToByteArray(String str)
    {
        String standStr = standardizationHexString((new StringBuilder(String.valueOf(str))).append(" ").toString());
        byte bin[] = new byte[standStr.length() / 2];
        for(int i = 0; i < bin.length; i++)
            bin[i] = (byte)((charToByte(standStr.charAt(i * 2)) << 4) + charToByte(standStr.charAt(i * 2 + 1)));

        return bin;
    }

    public static String byteToString(byte b)
    {
        StringBuilder buff = new StringBuilder(2);
        buff.append(byteToChar(b >> 4 & 0xf)).append(byteToChar(b & 0xf));
        return buff.toString();
    }

    public static byte stringToByte(String str)
    {
        return (byte)(charToByte(str.charAt(0)) * 16 + charToByte(str.charAt(1)));
    }

    public static boolean isBcdByte(int bcd)
    {
        return (bcd & 0xf) <= 9 && (bcd & 0xf0) >> 4 <= 9;
    }

    public static int bcdToInt(int bcd)
    {
        if(isBcdByte(bcd))
            return ((bcd & 0xf0) >> 4) * 10 + (bcd & 0xf);
        else
            throw new IllegalArgumentException("BCD..");
    }

    public static long bcdToInt(byte bcds[], int beginIndex, int length)
    {
        long result = 0L;
        long base = 1L;
        for(int i = 0; i < length; i++)
        {
            int b = byteToUnsigned(bcds[beginIndex + i]);
            result += (long)bcdToInt(b) * base;
            base *= 100L;
        }

        return result;
    }

    public static byte[] intTobcd(long value, int length)
    {
        byte result[] = new byte[length];
        for(int i = 0; i < length; i++)
        {
            result[i] = intToBcd((int)(value % 100L));
            value /= 100L;
        }

        return result;
    }

    public static byte intToBcd(int decimal)
    {
        return (byte)((decimal / 10) * 16 + decimal % 10);
    }

    public static Date bcdToDate(byte bcdArray[], String format, int beginPosition)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(11, 0);
        String uformat = format.toUpperCase();
        int yyyyidx = uformat.indexOf("YYYY");
        if(yyyyidx != -1)
        {
            calendar.set(1, (int)bcdToInt(bcdArray, beginPosition + yyyyidx / 2, 2));
        } else
        {
            int yyindex = uformat.indexOf("YY");
            if(yyindex != -1)
                calendar.set(1, (calendar.get(1) / 100) * 100 + bcdToInt(bcdArray[beginPosition + yyindex / 2]));
        }
        int mmindex = uformat.indexOf("MM");
        if(mmindex != -1)
            calendar.set(2, bcdToInt(bcdArray[beginPosition + mmindex / 2]) - 1);
        int ddindex = uformat.indexOf("DD");
        if(ddindex != -1)
            calendar.set(5, bcdToInt(bcdArray[beginPosition + ddindex / 2]));
        int hhindex = uformat.indexOf("HH");
        if(hhindex != -1)
            calendar.set(11, bcdToInt(bcdArray[beginPosition + hhindex / 2]));
        int miindex = uformat.indexOf("MI");
        if(miindex != -1)
            calendar.set(12, bcdToInt(bcdArray[beginPosition + miindex / 2]));
        int ssindex = uformat.indexOf("SS");
        if(ssindex != -1)
            calendar.set(13, bcdToInt(bcdArray[beginPosition + ssindex / 2]));
        return calendar.getTime();
    }

    public static byte[] dateToBcd(Date date, String format)
    {
        byte result[] = new byte[format.length() / 2];
        String uformat = format.toUpperCase();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int yyyyidx = uformat.indexOf("YYYY");
        if(yyyyidx != -1)
        {
            byte years[] = intTobcd(calendar.get(1), 2);
            result[yyyyidx / 2] = years[0];
            result[yyyyidx / 2 + 1] = years[1];
        } else
        {
            int yyindex = uformat.indexOf("YY");
            if(yyindex != -1)
                result[yyindex / 2] = intToBcd((byte)(calendar.get(1) % 100));
        }
        int mmindex = uformat.indexOf("MM");
        if(mmindex != -1)
            result[mmindex / 2] = intToBcd((byte)(calendar.get(2) + 1));
        int ddindex = uformat.indexOf("DD");
        if(ddindex != -1)
            result[ddindex / 2] = intToBcd((byte)calendar.get(5));
        int hhindex = uformat.indexOf("HH");
        if(hhindex != -1)
            result[hhindex / 2] = intToBcd((byte)calendar.get(11));
        int miindex = uformat.indexOf("MI");
        if(miindex != -1)
            result[miindex / 2] = intToBcd((byte)calendar.get(12));
        int ssindex = uformat.indexOf("SS");
        if(ssindex != -1)
            result[ssindex / 2] = intToBcd((byte)calendar.get(13));
        return result;
    }

    public static int bitSetOfByte(byte b)
    {
        if(b == 1)
            return 1;
        if(b == 2)
            return 2;
        if(b == 4)
            return 3;
        if(b == 8)
            return 4;
        if(b == 16)
            return 5;
        if(b == 32)
            return 6;
        if(b == 64)
            return 7;
        if(b == -128)
            return 8;
        else
            throw new IllegalArgumentException();
    }

    public static byte[] bitSetStringToBytes(String bitString)
    {
        int len = bitString.length() / 8;
        byte bitArray[] = new byte[len];
        for(int i = 0; i < len; i++)
        {
            int ch = 0;
            for(int j = 0; j < 8; j++)
                if(bitString.charAt(i * 8 + j) == '1')
                    ch |= 1 << j;

            bitArray[i] = (byte)ch;
        }

        return bitArray;
    }

    public static byte[] IPStringToBytes(String ipString)
    {
        String strs[] = ipString.split("[.]");
        byte b[] = new byte[strs.length];
        int index = 0;
        String as[];
        int j = (as = strs).length;
        for(int i = 0; i < j; i++)
        {
            String str = as[i];
            b[index++] = (byte)Integer.parseInt(str);
        }

        return b;
    }

    public static byte[] IpPortStringToBytes(String ipportString)
    {
        String strs[] = ipportString.substring(0, ipportString.indexOf(":")).split("[.]");
        byte b[] = new byte[strs.length + 2];
        int index = 0;
        String as[];
        int j = (as = strs).length;
        for(int i = 0; i < j; i++)
        {
            String str = as[i];
            b[index++] = (byte)Integer.parseInt(str);
        }

        String port[] = ipportString.split("[:]");
        b[index++] = (byte)(Integer.parseInt(port[1]) & 0xff);
        b[index++] = (byte)((Integer.parseInt(port[1]) & 0xff00) >> 8);
        return b;
    }

    public static byte[] TelStringToBytes(String telString)
    {
        String StrValue = telString.replace(",", "A");
        StrValue = StrValue.replace("#", "B");
        if(StrValue.length() > 16)
            StrValue = StrValue.substring(0, 15);
        else
        if(StrValue.length() < 16)
            StrValue = (new StringBuilder(String.valueOf(StrValue))).append(dupeString("F", 16 - StrValue.length())).toString();
        return stringToByteArray(StrValue);
    }

    public static String bytesToTelString(byte bytes[])
    {
        StringBuilder sb = new StringBuilder();
        int valueLower = 0;
        for(int i = 0; i <= bytes.length - 1; i++)
        {
            int valueHigh = (bytes[i] & 0xf0) >> 4;
            valueLower = bytes[i] & 0xf;
            if(valueHigh < 10)
                sb.append(valueHigh);
            else
            if(valueHigh == 10)
                sb.append(",");
            else
            if(valueHigh == 11)
                sb.append("#");
            if(valueLower < 10)
                sb.append(valueLower);
            else
            if(valueLower == 10)
                sb.append(",");
            else
            if(valueLower == 11)
                sb.append("#");
        }

        return sb.toString();
    }

    public static String bytesToIpPortString(byte bytes[])
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 4; i++)
        {
            int Ip = 0;
            if(bytes[i] < 0)
                Ip = bytes[i] + 256;
            else
                Ip = bytes[i];
            sb.append(String.valueOf(Ip));
            if(i < 3)
                sb.append(".");
            else
                sb.append(":");
        }

        int portHigh = bytes[5] < 0 ? bytes[5] + 256 : ((int) (bytes[5]));
        int portLow = bytes[4] < 0 ? bytes[4] + 256 : ((int) (bytes[4]));
        int port = portHigh * 256 + portLow;
        sb.append(String.valueOf(port));
        return sb.toString();
    }

    public static String bytesToIpString(byte bytes[])
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 4; i++)
        {
            int Ip = 0;
            if(bytes[i] < 0)
                Ip = bytes[i] + 256;
            else
                Ip = bytes[i];
            sb.append(String.valueOf(Ip));
            if(i < 3)
                sb.append(".");
            else
                sb.append("");
        }

        return sb.toString();
    }

    public static String bytesToBitSetString(byte bytes[])
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bytes.length; i++)
        {
            int b = bytes[i];
            for(int j = 0; j < 8; j++)
                if((b & 1 << j) == 1 << j)
                    sb.append('1');
                else
                    sb.append('0');

        }

        return sb.toString();
    }

    public static String IntToBitSetString(int value)
    {
        StringBuilder sb = new StringBuilder();
        for(; value > 0; value >>= 1)
            sb.append(value % 2);

        return sb.toString();
    }

    private static char byteToChar(int b)
    {
        char ch = b >= 10 ? (char)((65 + b) - 10) : (char)(48 + b);
        return ch;
    }

    private static byte charToByte(char ch)
    {
        return ch < '0' || ch > '9' ? (byte)((Character.toUpperCase(ch) - 65) + 10) : (byte)(ch - 48);
    }

    private static String standardizationHexString(String str)
    {
        StringBuilder buff = new StringBuilder();
        int endTokenPosition;
        for(int firstTokenPosition = findFirstTokenPosition(str, 0); firstTokenPosition != -1; firstTokenPosition = findFirstTokenPosition(str, endTokenPosition + 1))
        {
            endTokenPosition = findEndTokenPosition(str, firstTokenPosition);
            if(endTokenPosition == firstTokenPosition)
            {
                buff.append('0');
                buff.append(Character.toUpperCase(str.charAt(endTokenPosition)));
            } else
            {
                buff.append(str.charAt(firstTokenPosition));
                buff.append(str.charAt(endTokenPosition));
            }
        }

        return buff.toString();
    }

    private static int findFirstTokenPosition(String str, int initPos)
    {
        for(int i = initPos; i < str.length(); i++)
            if(isHexChar(str.charAt(i)))
                return i;

        return -1;
    }

    private static int findEndTokenPosition(String str, int initPos)
    {
        for(int i = initPos; i < initPos + 2 && i < str.length(); i++)
        {
            if(!isHexChar(str.charAt(i)))
                return i - 1;
            if(i == initPos + 1)
                return i;
        }

        return initPos;
    }

    private static boolean isHexChar(char ch)
    {
        return ch >= '0' && ch <= '9' || Character.toUpperCase(ch) >= 'A' && Character.toUpperCase(ch) <= 'F';
    }

    public static byte[] reverseBytes(byte bytes[])
    {
        int len = bytes.length;
        byte reverseBytes[] = new byte[len];
        for(int i = 0; i < len; i++)
            reverseBytes[i] = bytes[len - i - 1];

        return reverseBytes;
    }

    public static String dupeString(String str, int repeats)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < repeats; i++)
            sb.append(str);

        return sb.toString();
    }

    public static int byteToUnsigned(byte b)
    {
        return (256 + b) % 256;
    }

    public static String dateToString(Date date, String format)
    {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(format);
        return sDateFormat.format(date);
    }
}