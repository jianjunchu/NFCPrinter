// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:38:22
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   VCardRecord.java

package com.aofei.nfc.ndef.records;

import info.ineighborhood.cardme.vcard.VCard;

// Referenced classes of package com.aofei.nfc.ndef.records:
//            Record

public class VCardRecord extends Record
{

    public VCardRecord(VCard vCard)
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #21  <Method void Record()>
        this.vCard = vCard;
    //    2    4:aload_0         
    //    3    5:aload_1         
    //    4    6:putfield        #23  <Field VCard vCard>
    //    5    9:return          
    }

    public VCardRecord()
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #21  <Method void Record()>
    //    2    4:return          
    }

    public VCard getvCard()
    {
        return vCard;
    //    0    0:aload_0         
    //    1    1:getfield        #23  <Field VCard vCard>
    //    2    4:areturn         
    }

    public void setvCard(VCard vCard)
    {
        this.vCard = vCard;
    //    0    0:aload_0         
    //    1    1:aload_1         
    //    2    2:putfield        #23  <Field VCard vCard>
    //    3    5:return          
    }

    public static final byte TYPE[] = {
        116, 101, 120, 116, 47, 120, 45, 118, 67, 97, 
        114, 100
    };
    public static final byte TYPE_P2P[] = {
        116, 101, 120, 116, 47, 120, 45, 118, 99, 97, 
        114, 100
    };
    private VCard vCard;

    static 
    {
    //    0    0:bipush          12
    //    1    2:newarray        byte[]
    //    2    4:dup             
    //    3    5:iconst_0        
    //    4    6:bipush          116
    //    5    8:bastore         
    //    6    9:dup             
    //    7   10:iconst_1        
    //    8   11:bipush          101
    //    9   13:bastore         
    //   10   14:dup             
    //   11   15:iconst_2        
    //   12   16:bipush          120
    //   13   18:bastore         
    //   14   19:dup             
    //   15   20:iconst_3        
    //   16   21:bipush          116
    //   17   23:bastore         
    //   18   24:dup             
    //   19   25:iconst_4        
    //   20   26:bipush          47
    //   21   28:bastore         
    //   22   29:dup             
    //   23   30:iconst_5        
    //   24   31:bipush          120
    //   25   33:bastore         
    //   26   34:dup             
    //   27   35:bipush          6
    //   28   37:bipush          45
    //   29   39:bastore         
    //   30   40:dup             
    //   31   41:bipush          7
    //   32   43:bipush          118
    //   33   45:bastore         
    //   34   46:dup             
    //   35   47:bipush          8
    //   36   49:bipush          67
    //   37   51:bastore         
    //   38   52:dup             
    //   39   53:bipush          9
    //   40   55:bipush          97
    //   41   57:bastore         
    //   42   58:dup             
    //   43   59:bipush          10
    //   44   61:bipush          114
    //   45   63:bastore         
    //   46   64:dup             
    //   47   65:bipush          11
    //   48   67:bipush          100
    //   49   69:bastore         
    //   50   70:putstatic       #13  <Field byte[] TYPE>
    //   51   73:bipush          12
    //   52   75:newarray        byte[]
    //   53   77:dup             
    //   54   78:iconst_0        
    //   55   79:bipush          116
    //   56   81:bastore         
    //   57   82:dup             
    //   58   83:iconst_1        
    //   59   84:bipush          101
    //   60   86:bastore         
    //   61   87:dup             
    //   62   88:iconst_2        
    //   63   89:bipush          120
    //   64   91:bastore         
    //   65   92:dup             
    //   66   93:iconst_3        
    //   67   94:bipush          116
    //   68   96:bastore         
    //   69   97:dup             
    //   70   98:iconst_4        
    //   71   99:bipush          47
    //   72  101:bastore         
    //   73  102:dup             
    //   74  103:iconst_5        
    //   75  104:bipush          120
    //   76  106:bastore         
    //   77  107:dup             
    //   78  108:bipush          6
    //   79  110:bipush          45
    //   80  112:bastore         
    //   81  113:dup             
    //   82  114:bipush          7
    //   83  116:bipush          118
    //   84  118:bastore         
    //   85  119:dup             
    //   86  120:bipush          8
    //   87  122:bipush          99
    //   88  124:bastore         
    //   89  125:dup             
    //   90  126:bipush          9
    //   91  128:bipush          97
    //   92  130:bastore         
    //   93  131:dup             
    //   94  132:bipush          10
    //   95  134:bipush          114
    //   96  136:bastore         
    //   97  137:dup             
    //   98  138:bipush          11
    //   99  140:bipush          100
    //  100  142:bastore         
    //  101  143:putstatic       #15  <Field byte[] TYPE_P2P>
    //* 102  146:return          
    }
}