// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:38:21
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   SmartPosterRecord.java

package com.aofei.nfc.ndef.records;


// Referenced classes of package com.aofei.nfc.ndef.records:
//            Record, TextRecord, UriRecord, ActionRecord

public class SmartPosterRecord extends Record
{

    public SmartPosterRecord()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #21  <Method void Record()>
    //    2    4:return          
    }

    public SmartPosterRecord(TextRecord title, UriRecord uri, ActionRecord action)
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #21  <Method void Record()>
        this.title = title;
    //    2    4:aload_0         
    //    3    5:aload_1         
    //    4    6:putfield        #26  <Field TextRecord title>
        this.uri = uri;
    //    5    9:aload_0         
    //    6   10:aload_2         
    //    7   11:putfield        #28  <Field UriRecord uri>
        this.action = action;
    //    8   14:aload_0         
    //    9   15:aload_3         
    //   10   16:putfield        #30  <Field ActionRecord action>
    //   11   19:return          
    }

    public TextRecord getTitle()
    {
        return title;
    //    0    0:aload_0         
    //    1    1:getfield        #26  <Field TextRecord title>
    //    2    4:areturn         
    }

    public void setTitle(TextRecord title)
    {
        this.title = title;
    //    0    0:aload_0         
    //    1    1:aload_1         
    //    2    2:putfield        #26  <Field TextRecord title>
    //    3    5:return          
    }

    public UriRecord getUri()
    {
        return uri;
    //    0    0:aload_0         
    //    1    1:getfield        #28  <Field UriRecord uri>
    //    2    4:areturn         
    }

    public void setUri(UriRecord uri)
    {
        this.uri = uri;
    //    0    0:aload_0         
    //    1    1:aload_1         
    //    2    2:putfield        #28  <Field UriRecord uri>
    //    3    5:return          
    }

    public ActionRecord getAction()
    {
        return action;
    //    0    0:aload_0         
    //    1    1:getfield        #30  <Field ActionRecord action>
    //    2    4:areturn         
    }

    public void setAction(ActionRecord action)
    {
        this.action = action;
    //    0    0:aload_0         
    //    1    1:aload_1         
    //    2    2:putfield        #30  <Field ActionRecord action>
    //    3    5:return          
    }

    public String toString()
    {
        return (new StringBuilder("SmartPoster: [")).append(getTitle()).append(", ").append(getUri()).append(", ").append(getAction()).append("]").toString();
    //    0    0:new             #46  <Class StringBuilder>
    //    1    3:dup             
    //    2    4:ldc1            #48  <String "SmartPoster: [">
    //    3    6:invokespecial   #50  <Method void StringBuilder(String)>
    //    4    9:aload_0         
    //    5   10:invokevirtual   #53  <Method TextRecord getTitle()>
    //    6   13:invokevirtual   #55  <Method StringBuilder StringBuilder.append(Object)>
    //    7   16:ldc1            #59  <String ", ">
    //    8   18:invokevirtual   #61  <Method StringBuilder StringBuilder.append(String)>
    //    9   21:aload_0         
    //   10   22:invokevirtual   #64  <Method UriRecord getUri()>
    //   11   25:invokevirtual   #55  <Method StringBuilder StringBuilder.append(Object)>
    //   12   28:ldc1            #59  <String ", ">
    //   13   30:invokevirtual   #61  <Method StringBuilder StringBuilder.append(String)>
    //   14   33:aload_0         
    //   15   34:invokevirtual   #66  <Method ActionRecord getAction()>
    //   16   37:invokevirtual   #55  <Method StringBuilder StringBuilder.append(Object)>
    //   17   40:ldc1            #68  <String "]">
    //   18   42:invokevirtual   #61  <Method StringBuilder StringBuilder.append(String)>
    //   19   45:invokevirtual   #70  <Method String StringBuilder.toString()>
    //   20   48:areturn         
    }

    public static final byte TYPE[] = {
        83, 112
    };
    private TextRecord title;
    private UriRecord uri;
    private ActionRecord action;

    static 
    {
    //    0    0:iconst_2        
    //    1    1:newarray        byte[]
    //    2    3:dup             
    //    3    4:iconst_0        
    //    4    5:bipush          83
    //    5    7:bastore         
    //    6    8:dup             
    //    7    9:iconst_1        
    //    8   10:bipush          112
    //    9   12:bastore         
    //   10   13:putstatic       #16  <Field byte[] TYPE>
    //*  11   16:return          
    }
}