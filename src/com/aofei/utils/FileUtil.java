// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 19:43:23
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   FileUtil.java

package com.aofei.utils;

import java.io.*;

public class FileUtil
{

    public FileUtil()
    {
    }

    public static byte[] getBytesFromFile(File file)
        throws IOException
    {
        InputStream is = new FileInputStream(file);
        long length = file.length();
        byte bytes[] = new byte[(int)length];
        int offset = 0;
        for(int numRead = 0; offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0; offset += numRead);
        if(offset < bytes.length)
        {
            throw new IOException((new StringBuilder("Could not completely read file ")).append(file.getName()).toString());
        } else
        {
            is.close();
            return bytes;
        }
    }
}