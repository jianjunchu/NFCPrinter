// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:31:37
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MimeType.java

package com.aofei.nfc.ndef;


public class MimeType 
{

    public MimeType()
    {
    }

    public static boolean isSupported(String t)
    {
        boolean result = false;
        for(int i = 0; i < TYPE_LIST.length; i++)
        {
            if(!TYPE_LIST[i].equals(t))
                continue;
            result = true;
            break;
        }

        return result;
    }

    public static final String APPLICATION_X_CH_ONETOUCH = "application/x-ch-onetouch";
    public static final String APPLICATION_X_OCODE_LGU = "application/x-ocode-lgu";
    public static final String IMAGE_GIF = "image/gif";
    public static final String IMAGE_IEF = "image/ief";
    public static final String IMAGE_JPEG = "image/jpeg";
    public static final String TEXT_CSS = "text/css";
    public static final String TEXT_HTML = "text/html";
    public static final String TEXT_PLAIN = "text/plain";
    public static final String TEXT_RICHTEXT = "text/richtext";
    public static final String TEXT_TAG_SEPARATED = "text/tab-separated- values";
    public static final String TEXT_XML = "text/xml";
    public static final String TEXT_X_SETEXT = "text/x-setext";
    public static final String TEXT_XSL = "text/xsl";
    public static final String VIDEO_MPEG = "video/mpeg";
    public static final String VIDEO_QUICKTIME = "video/quicktime";
    public static final String VIDEO_X_MSVIDEO = "video/x-msvideo";
    public static final String VIDEO_X_SGI_MOVIE = "video/x-sgi-movie";
    public static final String TYPE_LIST[] = {
        "application/x-ch-onetouch", "application/x-ocode-lgu", "image/gif", "image/ief", "image/jpeg", "text/plain"
    };

}