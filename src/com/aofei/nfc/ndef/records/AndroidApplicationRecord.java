// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:38:21
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   AndroidApplicationRecord.java

package com.aofei.nfc.ndef.records;


// Referenced classes of package com.aofei.nfc.ndef.records:
//            Record

public class AndroidApplicationRecord extends Record
{

    public AndroidApplicationRecord(String packageName)
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #18  <Method void Record()>
        this.packageName = packageName;
    //    2    4:aload_0         
    //    3    5:aload_1         
    //    4    6:putfield        #20  <Field String packageName>
    //    5    9:return          
    }

    public AndroidApplicationRecord()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #18  <Method void Record()>
    //    2    4:return          
    }

    public String getPackageName()
    {
        return packageName;
    //    0    0:aload_0         
    //    1    1:getfield        #20  <Field String packageName>
    //    2    4:areturn         
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    //    0    0:aload_0         
    //    1    1:aload_1         
    //    2    2:putfield        #20  <Field String packageName>
    //    3    5:return          
    }

    public static final byte TYPE[] = {
        97, 110, 100, 114, 111, 105, 100, 46, 99, 111, 
        109, 58, 112, 107, 103
    };
    public String packageName;

    static 
    {
    //    0    0:bipush          15
    //    1    2:newarray        byte[]
    //    2    4:dup             
    //    3    5:iconst_0        
    //    4    6:bipush          97
    //    5    8:bastore         
    //    6    9:dup             
    //    7   10:iconst_1        
    //    8   11:bipush          110
    //    9   13:bastore         
    //   10   14:dup             
    //   11   15:iconst_2        
    //   12   16:bipush          100
    //   13   18:bastore         
    //   14   19:dup             
    //   15   20:iconst_3        
    //   16   21:bipush          114
    //   17   23:bastore         
    //   18   24:dup             
    //   19   25:iconst_4        
    //   20   26:bipush          111
    //   21   28:bastore         
    //   22   29:dup             
    //   23   30:iconst_5        
    //   24   31:bipush          105
    //   25   33:bastore         
    //   26   34:dup             
    //   27   35:bipush          6
    //   28   37:bipush          100
    //   29   39:bastore         
    //   30   40:dup             
    //   31   41:bipush          7
    //   32   43:bipush          46
    //   33   45:bastore         
    //   34   46:dup             
    //   35   47:bipush          8
    //   36   49:bipush          99
    //   37   51:bastore         
    //   38   52:dup             
    //   39   53:bipush          9
    //   40   55:bipush          111
    //   41   57:bastore         
    //   42   58:dup             
    //   43   59:bipush          10
    //   44   61:bipush          109
    //   45   63:bastore         
    //   46   64:dup             
    //   47   65:bipush          11
    //   48   67:bipush          58
    //   49   69:bastore         
    //   50   70:dup             
    //   51   71:bipush          12
    //   52   73:bipush          112
    //   53   75:bastore         
    //   54   76:dup             
    //   55   77:bipush          13
    //   56   79:bipush          107
    //   57   81:bastore         
    //   58   82:dup             
    //   59   83:bipush          14
    //   60   85:bipush          103
    //   61   87:bastore         
    //   62   88:putstatic       #12  <Field byte[] TYPE>
    //*  63   91:return          
    }
}