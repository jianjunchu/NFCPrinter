// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:38:21
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) annotate 
// Source File Name:   TextRecord.java

package com.aofei.nfc.ndef.records;

import java.nio.charset.Charset;
import java.util.Locale;

// Referenced classes of package com.aofei.nfc.ndef.records:
//            Record

public class TextRecord extends Record
{

    public TextRecord(String text, Charset encoding, Locale locale)
    {
    //    0    0:aload_0         
    //    1    1:invokespecial   #42  <Method void Record()>
        this.encoding = encoding;
    //    2    4:aload_0         
    //    3    5:aload_2         
    //    4    6:putfield        #44  <Field Charset encoding>
        this.text = text;
    //    5    9:aload_0         
    //    6   10:aload_1         
    //    7   11:putfield        #46  <Field String text>
        this.locale = locale;
    //    8   14:aload_0         
    //    9   15:aload_3         
    //   10   16:putfield        #48  <Field Locale locale>
        if(!encoding.equals(UTF8) && !encoding.equals(UTF16))
    //*  11   19:aload_2         
    //*  12   20:getstatic       #32  <Field Charset UTF8>
    //*  13   23:invokevirtual   #50  <Method boolean Charset.equals(Object)>
    //*  14   26:ifne            49
    //*  15   29:aload_2         
    //*  16   30:getstatic       #36  <Field Charset UTF16>
    //*  17   33:invokevirtual   #50  <Method boolean Charset.equals(Object)>
    //*  18   36:ifne            49
            throw new IllegalArgumentException("unsupported encoding. only utf8 and utf16 are allowed.");
    //   19   39:new             #54  <Class IllegalArgumentException>
    //   20   42:dup             
    //   21   43:ldc1            #56  <String "unsupported encoding. only utf8 and utf16 are allowed.">
    //   22   45:invokespecial   #58  <Method void IllegalArgumentException(String)>
    //   23   48:athrow          
        else
            return;
    //   24   49:return          
    }

    public String getText()
    {
        return text;
    //    0    0:aload_0         
    //    1    1:getfield        #46  <Field String text>
    //    2    4:areturn         
    }

    public Locale getLocale()
    {
        return locale;
    //    0    0:aload_0         
    //    1    1:getfield        #48  <Field Locale locale>
    //    2    4:areturn         
    }

    public Charset getEncoding()
    {
        return encoding;
    //    0    0:aload_0         
    //    1    1:getfield        #44  <Field Charset encoding>
    //    2    4:areturn         
    }

    public String toString()
    {
        return (new StringBuilder("Title: [Text: ")).append(text).append(", Locale: ").append(locale.getLanguage()).append("]").toString();
    //    0    0:new             #75  <Class StringBuilder>
    //    1    3:dup             
    //    2    4:ldc1            #77  <String "Title: [Text: ">
    //    3    6:invokespecial   #79  <Method void StringBuilder(String)>
    //    4    9:aload_0         
    //    5   10:getfield        #46  <Field String text>
    //    6   13:invokevirtual   #80  <Method StringBuilder StringBuilder.append(String)>
    //    7   16:ldc1            #84  <String ", Locale: ">
    //    8   18:invokevirtual   #80  <Method StringBuilder StringBuilder.append(String)>
    //    9   21:aload_0         
    //   10   22:getfield        #48  <Field Locale locale>
    //   11   25:invokevirtual   #86  <Method String Locale.getLanguage()>
    //   12   28:invokevirtual   #80  <Method StringBuilder StringBuilder.append(String)>
    //   13   31:ldc1            #89  <String "]">
    //   14   33:invokevirtual   #80  <Method StringBuilder StringBuilder.append(String)>
    //   15   36:invokevirtual   #91  <Method String StringBuilder.toString()>
    //   16   39:areturn         
    }

    public static final byte TYPE[] = {
        84
    };
    public static final byte LANGUAGE_CODE_MASK = 31;
    public static final Charset UTF8 = Charset.forName("UTF-8");
    public static final Charset UTF16 = Charset.forName("UTF-16BE");
    private String text;
    private Charset encoding;
    private Locale locale;

    static 
    {
    //    0    0:iconst_1        
    //    1    1:newarray        byte[]
    //    2    3:dup             
    //    3    4:iconst_0        
    //    4    5:bipush          84
    //    5    7:bastore         
    //    6    8:putstatic       #22  <Field byte[] TYPE>
    //    7   11:ldc1            #24  <String "UTF-8">
    //    8   13:invokestatic    #26  <Method Charset Charset.forName(String)>
    //    9   16:putstatic       #32  <Field Charset UTF8>
    //   10   19:ldc1            #34  <String "UTF-16BE">
    //   11   21:invokestatic    #26  <Method Charset Charset.forName(String)>
    //   12   24:putstatic       #36  <Field Charset UTF16>
    //*  13   27:return          
    }
}