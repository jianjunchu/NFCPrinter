// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:38:21
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   Record.java

package com.aofei.nfc.ndef.records;

import com.aofei.nfc.ndef.NdefConstants;

public abstract class Record
{

    public Record()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #10  <Method void Object()>
        id = NdefConstants.EMPTY_BYTE_ARRAY;
    //    2    4:aload_0         
    //    3    5:getstatic       #12  <Field byte[] NdefConstants.EMPTY_BYTE_ARRAY>
    //    4    8:putfield        #17  <Field byte[] id>
    //    5   11:return          
    }

    public byte[] getId()
    {
        return id;
    //    0    0:aload_0         
    //    1    1:getfield        #17  <Field byte[] id>
    //    2    4:areturn         
    }

    public void setId(byte id[])
    {
        this.id = id;
    //    0    0:aload_0         
    //    1    1:aload_1         
    //    2    2:putfield        #17  <Field byte[] id>
    //    3    5:return          
    }

    protected byte id[];
}