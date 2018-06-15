// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:31:38
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   NdefRecord.java

package com.aofei.nfc.ndef;


public class NdefRecord
{

    public NdefRecord(short tnf, byte type[], byte id[], byte payload[])
    {
        this.tnf = tnf;
        this.type = type;
        this.id = id;
        this.payload = payload;
    }

    public short getTnf()
    {
        return tnf;
    }

    public byte[] getType()
    {
        return type;
    }

    public byte[] getId()
    {
        return id;
    }

    public byte[] getPayload()
    {
        return payload;
    }

    private short tnf;
    private byte type[];
    private byte id[];
    private byte payload[];
}