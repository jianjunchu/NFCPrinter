// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:38:21
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   ActionRecord.java

package com.aofei.nfc.ndef.records;


// Referenced classes of package com.aofei.nfc.ndef.records:
//            Record

public class ActionRecord extends Record
{
    public enum Action
    {
    	DO_THE_ACTION(0),SAVE_FOR_LATER(1),OPEN_FOR_EDITING(2);
    	private int value;
    	
    	private Action(int value) {
            this.value = value;
        }
        public int getValue()
        {
            return value;   
        }
    }


    public ActionRecord(Action action)
    { 
    //    0    0:aload_0         
    //    1    1:invokespecial   #18  <Method void Record()>
        this.action = action;
    //    2    4:aload_0         
    //    3    5:aload_1         
    //    4    6:putfield        #20  <Field ActionRecord$Action action>
    //    5    9:return          
    }

    public Action getAction()
    {
        return action;
    //    0    0:aload_0         
    //    1    1:getfield        #20  <Field ActionRecord$Action action>
    //    2    4:areturn         
    }

    public String toString()
    {
        return (new StringBuilder()).append(action).toString();
    //    0    0:new             #28  <Class StringBuilder>
    //    1    3:dup             
    //    2    4:invokespecial   #30  <Method void StringBuilder()>
    //    3    7:aload_0         
    //    4    8:getfield        #20  <Field ActionRecord$Action action>
    //    5   11:invokevirtual   #31  <Method StringBuilder StringBuilder.append(Object)>
    //    6   14:invokevirtual   #35  <Method String StringBuilder.toString()>
    //    7   17:areturn         
    }

    public static final byte TYPE[] = {
        97, 99, 116
    };
    private Action action;

    static 
    {
    //    0    0:iconst_3        
    //    1    1:newarray        byte[]
    //    2    3:dup             
    //    3    4:iconst_0        
    //    4    5:bipush          97
    //    5    7:bastore         
    //    6    8:dup             
    //    7    9:iconst_1        
    //    8   10:bipush          99
    //    9   12:bastore         
    //   10   13:dup             
    //   11   14:iconst_2        
    //   12   15:bipush          116
    //   13   17:bastore         
    //   14   18:putstatic       #12  <Field byte[] TYPE>
    //*  15   21:return          
    }
}