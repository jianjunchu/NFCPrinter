// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:38:21
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   MimeRecord.java

package com.aofei.nfc.ndef.records;


// Referenced classes of package com.aofei.nfc.ndef.records:
//            Record

public class MimeRecord extends Record
{

    public MimeRecord(String type, byte content[])
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #12  <Method void Record()>
        this.type = type;
    //    2    4:aload_0         
    //    3    5:aload_1         
    //    4    6:putfield        #15  <Field String type>
        this.content = content;
    //    5    9:aload_0         
    //    6   10:aload_2         
    //    7   11:putfield        #17  <Field byte[] content>
    //    8   14:return          
    }

    public MimeRecord()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #12  <Method void Record()>
    //    2    4:return          
    }

    public String getType()
    {
        return type;
    //    0    0:aload_0         
    //    1    1:getfield        #15  <Field String type>
    //    2    4:areturn         
    }

    public void setType(String type)
    {
        this.type = type;
    //    0    0:aload_0         
    //    1    1:aload_1         
    //    2    2:putfield        #15  <Field String type>
    //    3    5:return          
    }

    public byte[] getContent()
    {
        return content;
    //    0    0:aload_0         
    //    1    1:getfield        #17  <Field byte[] content>
    //    2    4:areturn         
    }

    public void setContent(byte content[])
    {
        this.content = content;
    //    0    0:aload_0         
    //    1    1:aload_1         
    //    2    2:putfield        #17  <Field byte[] content>
    //    3    5:return          
    }

    public String type;
    public byte content[];
}