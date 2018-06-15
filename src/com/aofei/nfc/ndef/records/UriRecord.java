// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:38:22
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   UriRecord.java

package com.aofei.nfc.ndef.records;

import java.nio.charset.Charset;

// Referenced classes of package com.aofei.nfc.ndef.records:
//            Record

public class UriRecord extends Record
{

    public UriRecord(String uri)
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #108 <Method void Record()>
        this.uri = uri;
    //    2    4:aload_0         
    //    3    5:aload_1         
    //    4    6:putfield        #110 <Field String uri>
    //    5    9:return          
    }

    public String getUri()
    {
        return uri;
    //    0    0:aload_0         
    //    1    1:getfield        #110 <Field String uri>
    //    2    4:areturn         
    }

    public String toString()
    {
        return (new StringBuilder("Uri: [")).append(uri).append("]").toString();
    //    0    0:new             #117 <Class StringBuilder>
    //    1    3:dup             
    //    2    4:ldc1            #119 <String "Uri: [">
    //    3    6:invokespecial   #121 <Method void StringBuilder(String)>
    //    4    9:aload_0         
    //    5   10:getfield        #110 <Field String uri>
    //    6   13:invokevirtual   #123 <Method StringBuilder StringBuilder.append(String)>
    //    7   16:ldc1            #127 <String "]">
    //    8   18:invokevirtual   #123 <Method StringBuilder StringBuilder.append(String)>
    //    9   21:invokevirtual   #129 <Method String StringBuilder.toString()>
    //   10   24:areturn         
    }

    public static final byte TYPE[] = {
        85
    };
    public static final Charset DEFAULT_URI_CHARSET = Charset.forName("UTF-8");
    public static final String abbreviableUris[] = {
        "", "http://www.", "https://www.", "http://", "https://", "tel:", "mailto:", "ftp://anonymous:anonymous@", "ftp://ftp.", "ftps://", 
        "sftp://", "smb://", "nfs://", "ftp://", "dav://", "news:", "telnet://", "imap:", "rtsp://", "urn:", 
        "pop:", "sip:", "sips:", "tftp:", "btspp://", "btl2cap://", "btgoep://", "tcpobex://", "irdaobex://", "file://", 
        "urn:epc:id:", "urn:epc:tag:", "urn:epc:pat:", "urn:epc:raw:", "urn:epc:", "urn:nfc:"
    };
    private String uri;

    static 
    {
    //    0    0:iconst_1        
    //    1    1:newarray        byte[]
    //    2    3:dup             
    //    3    4:iconst_0        
    //    4    5:bipush          85
    //    5    7:bastore         
    //    6    8:putstatic       #16  <Field byte[] TYPE>
    //    7   11:ldc1            #18  <String "UTF-8">
    //    8   13:invokestatic    #20  <Method Charset Charset.forName(String)>
    //    9   16:putstatic       #26  <Field Charset DEFAULT_URI_CHARSET>
    //   10   19:bipush          36
    //   11   21:anewarray       String[]
    //   12   24:dup             
    //   13   25:iconst_0        
    //   14   26:ldc1            #30  <String "">
    //   15   28:aastore         
    //   16   29:dup             
    //   17   30:iconst_1        
    //   18   31:ldc1            #32  <String "http://www.">
    //   19   33:aastore         
    //   20   34:dup             
    //   21   35:iconst_2        
    //   22   36:ldc1            #34  <String "https://www.">
    //   23   38:aastore         
    //   24   39:dup             
    //   25   40:iconst_3        
    //   26   41:ldc1            #36  <String "http://">
    //   27   43:aastore         
    //   28   44:dup             
    //   29   45:iconst_4        
    //   30   46:ldc1            #38  <String "https://">
    //   31   48:aastore         
    //   32   49:dup             
    //   33   50:iconst_5        
    //   34   51:ldc1            #40  <String "tel:">
    //   35   53:aastore         
    //   36   54:dup             
    //   37   55:bipush          6
    //   38   57:ldc1            #42  <String "mailto:">
    //   39   59:aastore         
    //   40   60:dup             
    //   41   61:bipush          7
    //   42   63:ldc1            #44  <String "ftp://anonymous:anonymous@">
    //   43   65:aastore         
    //   44   66:dup             
    //   45   67:bipush          8
    //   46   69:ldc1            #46  <String "ftp://ftp.">
    //   47   71:aastore         
    //   48   72:dup             
    //   49   73:bipush          9
    //   50   75:ldc1            #48  <String "ftps://">
    //   51   77:aastore         
    //   52   78:dup             
    //   53   79:bipush          10
    //   54   81:ldc1            #50  <String "sftp://">
    //   55   83:aastore         
    //   56   84:dup             
    //   57   85:bipush          11
    //   58   87:ldc1            #52  <String "smb://">
    //   59   89:aastore         
    //   60   90:dup             
    //   61   91:bipush          12
    //   62   93:ldc1            #54  <String "nfs://">
    //   63   95:aastore         
    //   64   96:dup             
    //   65   97:bipush          13
    //   66   99:ldc1            #56  <String "ftp://">
    //   67  101:aastore         
    //   68  102:dup             
    //   69  103:bipush          14
    //   70  105:ldc1            #58  <String "dav://">
    //   71  107:aastore         
    //   72  108:dup             
    //   73  109:bipush          15
    //   74  111:ldc1            #60  <String "news:">
    //   75  113:aastore         
    //   76  114:dup             
    //   77  115:bipush          16
    //   78  117:ldc1            #62  <String "telnet://">
    //   79  119:aastore         
    //   80  120:dup             
    //   81  121:bipush          17
    //   82  123:ldc1            #64  <String "imap:">
    //   83  125:aastore         
    //   84  126:dup             
    //   85  127:bipush          18
    //   86  129:ldc1            #66  <String "rtsp://">
    //   87  131:aastore         
    //   88  132:dup             
    //   89  133:bipush          19
    //   90  135:ldc1            #68  <String "urn:">
    //   91  137:aastore         
    //   92  138:dup             
    //   93  139:bipush          20
    //   94  141:ldc1            #70  <String "pop:">
    //   95  143:aastore         
    //   96  144:dup             
    //   97  145:bipush          21
    //   98  147:ldc1            #72  <String "sip:">
    //   99  149:aastore         
    //  100  150:dup             
    //  101  151:bipush          22
    //  102  153:ldc1            #74  <String "sips:">
    //  103  155:aastore         
    //  104  156:dup             
    //  105  157:bipush          23
    //  106  159:ldc1            #76  <String "tftp:">
    //  107  161:aastore         
    //  108  162:dup             
    //  109  163:bipush          24
    //  110  165:ldc1            #78  <String "btspp://">
    //  111  167:aastore         
    //  112  168:dup             
    //  113  169:bipush          25
    //  114  171:ldc1            #80  <String "btl2cap://">
    //  115  173:aastore         
    //  116  174:dup             
    //  117  175:bipush          26
    //  118  177:ldc1            #82  <String "btgoep://">
    //  119  179:aastore         
    //  120  180:dup             
    //  121  181:bipush          27
    //  122  183:ldc1            #84  <String "tcpobex://">
    //  123  185:aastore         
    //  124  186:dup             
    //  125  187:bipush          28
    //  126  189:ldc1            #86  <String "irdaobex://">
    //  127  191:aastore         
    //  128  192:dup             
    //  129  193:bipush          29
    //  130  195:ldc1            #88  <String "file://">
    //  131  197:aastore         
    //  132  198:dup             
    //  133  199:bipush          30
    //  134  201:ldc1            #90  <String "urn:epc:id:">
    //  135  203:aastore         
    //  136  204:dup             
    //  137  205:bipush          31
    //  138  207:ldc1            #92  <String "urn:epc:tag:">
    //  139  209:aastore         
    //  140  210:dup             
    //  141  211:bipush          32
    //  142  213:ldc1            #94  <String "urn:epc:pat:">
    //  143  215:aastore         
    //  144  216:dup             
    //  145  217:bipush          33
    //  146  219:ldc1            #96  <String "urn:epc:raw:">
    //  147  221:aastore         
    //  148  222:dup             
    //  149  223:bipush          34
    //  150  225:ldc1            #98  <String "urn:epc:">
    //  151  227:aastore         
    //  152  228:dup             
    //  153  229:bipush          35
    //  154  231:ldc1            #100 <String "urn:nfc:">
    //  155  233:aastore         
    //  156  234:putstatic       #102 <Field String[] abbreviableUris>
    //* 157  237:return          
    }
}