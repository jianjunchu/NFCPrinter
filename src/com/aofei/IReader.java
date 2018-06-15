package com.aofei;

import java.io.IOException;

public interface IReader {

    /**
     * 场重置
     * @return
     * @throws IOException
     */
    public byte[] reset() throws IOException;

    /**
     * 寻卡
     * @return
     * @throws IOException
     */
    public boolean searchCard() throws IOException;

    /**
     * 选卡
     * @return
     * @throws Exception
     */
    public boolean selectCard() throws Exception;


    /**
     * 写卡
     * @param page 要写入的地址
     * @param content 要写入的内容，四个字节的字节数组
     * @return 写入是否成功标识，true or false
     * @throws Exception
     */
    public boolean writeByteArray(int page,byte[] content) throws Exception;


    /**
     * 读卡
     * @param page 要读取的地址
     * @return 字节数组，四个页面的内容（16字节）
     * @throws Exception
     */
    public byte[] readByte(int page) throws Exception;

}
