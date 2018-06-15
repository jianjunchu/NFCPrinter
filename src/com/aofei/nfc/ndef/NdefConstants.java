// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:31:37
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   NdefConstants.java

package com.aofei.nfc.ndef;


public class NdefConstants
{

    public NdefConstants()
    {
    }

    public static final int FE = 254;
    public static final int MB = 128;
    public static final int ME = 64;
    public static final int CF = 32;
    public static final int SR = 16;
    public static final int IL = 8;
    public static final int TNF_MASK = 7;
    public static final byte EMPTY_BYTE_ARRAY[] = new byte[0];
    public static final short TNF_EMPTY = 0;
    public static final short TNF_WELL_KNOWN = 1;
    public static final short TNF_MIME_MEDIA = 2;
    public static final short TNF_ABSOLUTE_URI = 3;
    public static final short TNF_EXTERNAL_TYPE = 4;
    public static final short TNF_UNKNOWN = 5;
    public static final short TNF_UNCHANGED = 6;

}