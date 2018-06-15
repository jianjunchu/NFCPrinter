// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:38:21
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   DirectNDEFRecord.java

package com.aofei.nfc.ndef.records;


// Referenced classes of package com.aofei.nfc.ndef.records:
//            Record

public class DirectNDEFRecord extends Record
{

    public DirectNDEFRecord(String ndef)
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #10  <Method void Record()>
        this.ndef = ndef;
    //    2    4:aload_0         
    //    3    5:aload_1         
    //    4    6:putfield        #13  <Field String ndef>
    //    5    9:return          
    }

    public String getNdef()
    {
        return ndef;
    //    0    0:aload_0         
    //    1    1:getfield        #13  <Field String ndef>
    //    2    4:areturn         
    }

    public void setNdef(String ndef)
    {
        this.ndef = ndef;
    //    0    0:aload_0         
    //    1    1:aload_1         
    //    2    2:putfield        #13  <Field String ndef>
    //    3    5:return          
    }

    public String ndef;
}