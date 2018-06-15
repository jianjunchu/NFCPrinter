// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:38:21
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   IssueExcelRecord.java

package com.aofei.nfc.ndef.records;


// Referenced classes of package com.aofei.nfc.ndef.records:
//            Record

public class IssueExcelRecord extends Record
{

    public IssueExcelRecord(Record record, int count, boolean isLock)
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #14  <Method void Record()>
        this.record = record;
    //    2    4:aload_0         
    //    3    5:aload_1         
    //    4    6:putfield        #17  <Field Record record>
        this.count = count;
    //    5    9:aload_0         
    //    6   10:iload_2         
    //    7   11:putfield        #19  <Field int count>
        this.isLock = isLock;
    //    8   14:aload_0         
    //    9   15:iload_3         
    //   10   16:putfield        #21  <Field boolean isLock>
    //   11   19:return          
    }

    public Record getRecord()
    {
        return record;
    //    0    0:aload_0         
    //    1    1:getfield        #17  <Field Record record>
    //    2    4:areturn         
    }

    public void setRecord(Record record)
    {
        this.record = record;
    //    0    0:aload_0         
    //    1    1:aload_1         
    //    2    2:putfield        #17  <Field Record record>
    //    3    5:return          
    }

    public int getCount()
    {
        return count;
    //    0    0:aload_0         
    //    1    1:getfield        #19  <Field int count>
    //    2    4:ireturn         
    }

    public void setCount(int count)
    {
        this.count = count;
    //    0    0:aload_0         
    //    1    1:iload_1         
    //    2    2:putfield        #19  <Field int count>
    //    3    5:return          
    }

    public boolean isLock()
    {
        return isLock;
    //    0    0:aload_0         
    //    1    1:getfield        #21  <Field boolean isLock>
    //    2    4:ireturn         
    }

    public void setLock(boolean isLock)
    {
        this.isLock = isLock;
    //    0    0:aload_0         
    //    1    1:iload_1         
    //    2    2:putfield        #21  <Field boolean isLock>
    //    3    5:return          
    }

    private Record record;
    private int count;
    private boolean isLock;
}