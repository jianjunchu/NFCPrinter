// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:41:12
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   OOBData.java

package com.aofei.nfc.ndef.records.handover;


public class OOBData
{

    public OOBData(byte type, byte data[])
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #12  <Method void Object()>
        this.type = type;
    //    2    4:aload_0         
    //    3    5:iload_1         
    //    4    6:putfield        #15  <Field byte type>
        this.data = data;
    //    5    9:aload_0         
    //    6   10:aload_2         
    //    7   11:putfield        #17  <Field byte[] data>
    //    8   14:return          
    }

    public byte getType()
    {
        return type;
    //    0    0:aload_0         
    //    1    1:getfield        #15  <Field byte type>
    //    2    4:ireturn         
    }

    public void setType(byte type)
    {
        this.type = type;
    //    0    0:aload_0         
    //    1    1:iload_1         
    //    2    2:putfield        #15  <Field byte type>
    //    3    5:return          
    }

    public byte[] getData()
    {
        return data;
    //    0    0:aload_0         
    //    1    1:getfield        #17  <Field byte[] data>
    //    2    4:areturn         
    }

    public void setData(byte data[])
    {
        this.data = data;
    //    0    0:aload_0         
    //    1    1:aload_1         
    //    2    2:putfield        #17  <Field byte[] data>
    //    3    5:return          
    }

    private byte type;
    private byte data[];
}