// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 19:43:23
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Hex.java

package com.aofei.utils;


public final class Hex
{

    private Hex()
    {
    }

    public static String byteToHexString(byte b)
    {
        int n = b & 0xff;
        String result = (new StringBuilder(String.valueOf(n >= 16 ? "" : "0"))).append(Integer.toHexString(n)).toString();
        return result.toUpperCase();
    }

    public static String shortToHexString(short s)
    {
        int n = s & 0xffff;
        String result = (new StringBuilder(String.valueOf(n >= 4096 ? "" : "0"))).append(n >= 256 ? "" : "0").append(n >= 16 ? "" : "0").append(Integer.toHexString(s)).toString();
        if(result.length() > 4)
            result = result.substring(result.length() - 4, result.length());
        return result.toUpperCase();
    }

    public static String intToHexString(int n)
    {
        String result = (new StringBuilder(String.valueOf(n >= 0x10000000 ? "" : "0"))).append(n >= 0x1000000 ? "" : "0").append(n >= 0x100000 ? "" : "0").append(n >= 0x10000 ? "" : "0").append(n >= 4096 ? "" : "0").append(n >= 256 ? "" : "0").append(n >= 16 ? "" : "0").append(Integer.toHexString(n)).toString();
        return result.toUpperCase();
    }

    public static String bytesToHexString(byte text[])
    {
        return bytesToHexString(text, 1000);
    }

    public static String bytesToHexString(byte text[], int numRow)
    {
        if(text == null)
            return "NULL";
        else
            return bytesToHexString(text, 0, text.length, numRow);
    }

    public static String toHexString(byte text[])
    {
        return bytesToHexString(text, 0, text.length, 1000);
    }

    public static String toHexString(byte text[], int numRow)
    {
        return bytesToHexString(text, 0, text.length, numRow);
    }

    public static String bytesToHexString(byte text[], int offset, int length, int numRow)
    {
        if(text == null)
            return "NULL";
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < length; i++)
        {
            if(i != 0 && i % numRow == 0)
                result.append("\n");
            result.append(byteToHexString(text[offset + i]));
        }

        return result.toString();
    }

    public static String bytesToHexString(byte text[], int offset, int length)
    {
        return bytesToHexString(text, offset, length, 1000);
    }

    public static byte hexStringToByte(String text)
        throws NumberFormatException
    {
        byte bytes[] = hexStringToBytes(text);
        if(bytes.length != 1)
            throw new NumberFormatException();
        else
            return bytes[0];
    }

    public static short hexStringToShort(String text)
        throws NumberFormatException
    {
        byte bytes[] = hexStringToBytes(text);
        if(bytes.length != 2)
            throw new NumberFormatException();
        else
            return (short)((bytes[0] & 0xff) << 8 | bytes[1] & 0xff);
    }

    public static int hexStringToInt(String text)
        throws NumberFormatException
    {
        byte bytes[] = hexStringToBytes(text);
        if(bytes.length != 4)
            throw new NumberFormatException();
        else
            return (bytes[0] & 0xff) << 24 | (bytes[1] & 0xff) << 16 | (bytes[2] & 0xff) << 8 | bytes[3] & 0xff;
    }

    public static byte[] hexStringToBytes(String text)
        throws NumberFormatException
    {
        if(text == null)
            return null;
        StringBuffer hexText = new StringBuffer();
        for(int i = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if(!Character.isWhitespace(c))
            {
                if("0123456789abcdefABCDEF".indexOf(c) < 0)
                    throw new NumberFormatException();
                hexText.append(c);
            }
        }

        if(hexText.length() % 2 != 0)
            hexText.insert(0, "0");
        byte result[] = new byte[hexText.length() / 2];
        for(int i = 0; i < hexText.length(); i += 2)
        {
            int hi = hexDigitToInt(hexText.charAt(i));
            int lo = hexDigitToInt(hexText.charAt(i + 1));
            result[i / 2] = (byte)((hi & 0xff) << 4 | lo & 0xff);
        }

        return result;
    }

    static int hexDigitToInt(char c)
        throws NumberFormatException
    {
        switch(c)
        {
        case 48: // '0'
            return 0;

        case 49: // '1'
            return 1;

        case 50: // '2'
            return 2;

        case 51: // '3'
            return 3;

        case 52: // '4'
            return 4;

        case 53: // '5'
            return 5;

        case 54: // '6'
            return 6;

        case 55: // '7'
            return 7;

        case 56: // '8'
            return 8;

        case 57: // '9'
            return 9;

        case 65: // 'A'
        case 97: // 'a'
            return 10;

        case 66: // 'B'
        case 98: // 'b'
            return 11;

        case 67: // 'C'
        case 99: // 'c'
            return 12;

        case 68: // 'D'
        case 100: // 'd'
            return 13;

        case 69: // 'E'
        case 101: // 'e'
            return 14;

        case 70: // 'F'
        case 102: // 'f'
            return 15;

        case 58: // ':'
        case 59: // ';'
        case 60: // '<'
        case 61: // '='
        case 62: // '>'
        case 63: // '?'
        case 64: // '@'
        case 71: // 'G'
        case 72: // 'H'
        case 73: // 'I'
        case 74: // 'J'
        case 75: // 'K'
        case 76: // 'L'
        case 77: // 'M'
        case 78: // 'N'
        case 79: // 'O'
        case 80: // 'P'
        case 81: // 'Q'
        case 82: // 'R'
        case 83: // 'S'
        case 84: // 'T'
        case 85: // 'U'
        case 86: // 'V'
        case 87: // 'W'
        case 88: // 'X'
        case 89: // 'Y'
        case 90: // 'Z'
        case 91: // '['
        case 92: // '\\'
        case 93: // ']'
        case 94: // '^'
        case 95: // '_'
        case 96: // '`'
        default:
            throw new NumberFormatException();
        }
    }

    private static String pad(String txt, int width, char padChar, boolean left)
    {
        String result = txt;
        String padString = Character.toString(padChar);
        for(int i = txt.length(); i < width; i++)
            if(left)
                result = (new StringBuilder(String.valueOf(padString))).append(result).toString();
            else
                result = (new StringBuilder(String.valueOf(result))).append(padString).toString();

        return result;
    }

    public static String bytesToSpacedHexString(byte data[])
    {
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < data.length; i++)
        {
            result.append(byteToHexString(data[i]));
            result.append(i >= data.length - 1 ? "" : " ");
        }

        return result.toString().toUpperCase();
    }

    private static String[] bytesToSpacedHexStrings(byte data[], int columns, int padWidth)
    {
        byte src[][] = split(data, columns);
        String result[] = new String[src.length];
        for(int j = 0; j < src.length; j++)
        {
            result[j] = bytesToSpacedHexString(src[j]);
            result[j] = pad(result[j], padWidth, ' ', false);
        }

        return result;
    }

    public static String bytesToASCIIString(byte data[])
    {
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < data.length; i++)
        {
            char c = (char)data[i];
            result.append(Character.toString(" .,:;'`\"<>()[]{}?/\\!@#$%^&*_-=+|~0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(c) < 0 ? '.' : c));
        }

        return result.toString();
    }

    static String[] bytesToASCIIStrings(byte data[], int columns, int padWidth)
    {
        byte src[][] = split(data, columns);
        String result[] = new String[src.length];
        for(int j = 0; j < src.length; j++)
            result[j] = bytesToASCIIString(src[j]);

        return result;
    }

    public static byte[][] split(byte src[], int width)
    {
        int rows = src.length / width;
        int rest = src.length % width;
        byte dest[][] = new byte[rows + (rest <= 0 ? 0 : 1)][];
        int k = 0;
        for(int j = 0; j < rows; j++)
        {
            dest[j] = new byte[width];
            System.arraycopy(src, k, dest[j], 0, width);
            k += width;
        }

        if(rest > 0)
        {
            dest[rows] = new byte[rest];
            System.arraycopy(src, k, dest[rows], 0, rest);
        }
        return dest;
    }

    public static String bytesToPrettyString(byte data[])
    {
        return bytesToPrettyString(data, 16, true, 4, null, true);
    }

    public static String bytesToPrettyString(byte data[], int columns, boolean useIndex, int indexPadWidth, String altIndex, boolean useASCII)
    {
        StringBuffer result = new StringBuffer();
        String hexStrings[] = bytesToSpacedHexStrings(data, columns, 3 * columns);
        String asciiStrings[] = bytesToASCIIStrings(data, columns, columns);
        for(int j = 0; j < hexStrings.length; j++)
        {
            if(useIndex)
            {
                String prefix = Integer.toHexString(j * columns).toUpperCase();
                result.append((new StringBuilder(String.valueOf(pad(prefix, indexPadWidth, '0', true)))).append(": ").toString());
            } else
            {
                String prefix = j != 0 ? "" : altIndex;
                result.append((new StringBuilder(String.valueOf(pad(prefix, indexPadWidth, ' ', true)))).append(" ").toString());
            }
            result.append(hexStrings[j]);
            if(useASCII)
                result.append((new StringBuilder(" ")).append(asciiStrings[j]).toString());
            result.append("\n");
        }

        return result.toString();
    }

    private static final String HEXCHARS = "0123456789abcdefABCDEF";
    private static final String PRINTABLE = " .,:;'`\"<>()[]{}?/\\!@#$%^&*_-=+|~0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final boolean LEFT = true;
    private static final boolean RIGHT = false;
}