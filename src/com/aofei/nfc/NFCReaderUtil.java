package com.aofei.nfc;

import com.aofei.IReader;
import com.aofei.nfcprinter.PrinterUtil;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Enumeration;

import com.aofei.nfc.utils.NfcUtils;


/**
 * 
 * @author
 * 2013-10
 *
 */
public class NFCReaderUtil implements IReader {
	  static String ip = null;
	  static int port = 11011;
	  OutputStream outputStream = null;
	  InputStream inputStream = null;
	  OutputStreamWriter writer = null;
	  CommPortIdentifier serialPortId = null;
	  SerialPort  serialPort = null;
	  private Socket  socket=null;
	 
	  public static int READER_TYPE_SOCKET = 1;
	  public static int READER_TYPE_SERIAL_PORT = 2;
	  
	  private int readerType;
	  private static String prevUID;
	  private String UID;
	  private boolean isDebug =true ;
	  private boolean checkPrevUID =true ;
	  private int TIME_OUT = 2000;
	  private static boolean RFOpened = false;
	  
	  /**
	   * 构造NFC 读卡器工具类
	   * @param type：两种 NFC 读卡器
	   * 1. 网口 READER_TYPE_SOCKET  
	   * 2. 串口 READER_TYPE_SERIAL_PORT
	   */
	  public NFCReaderUtil(int type)  {
		  readerType = type;
	 }
	  
	  private void initWithoutPort() throws Exception
	  {
		  boolean found = false;
			if(readerType==NFCReaderUtil.READER_TYPE_SOCKET)
			{
				if(port== 0 )
					throw new Exception("port is 0!");
				socket = new Socket( ip, port);
			    outputStream = socket.getOutputStream();
			    inputStream = socket.getInputStream();
			    this.openRF();//打开场
			}else 
			{
				Enumeration enu = CommPortIdentifier.getPortIdentifiers();
				while(enu.hasMoreElements())
				{
				  Object obj = enu.nextElement();
				  String portName =((CommPortIdentifier)obj).getName(); 
				  System.out.print(portName);
				  serialPortId = CommPortIdentifier.getPortIdentifier(portName);
				  try{
				  serialPort = (SerialPort) serialPortId.open("READ", 30);  //打开串口，其中30是打开串口的超时时间
				  }catch(Exception ex)
				  {
					  continue;
				  }
				  serialPort.setSerialPortParams(115200, 8, 1, 0); //设置波特率，数据位，停止位，校验方式
				  //从串口中得到输入输出流了
				  outputStream = serialPort.getOutputStream();   
				  inputStream = serialPort.getInputStream();
				  byte[] result=null;
				  try{
					  result = this.openRF();//打开场
				  }catch(Exception ex)
				  {
					  continue;
				  }
				  if(result!=null && result.length==3 && result[1]==2)
				  {
					  found=true;
					  break;
				  }
				}
			}
			if(found)
			{
			this.reset();//场重置
		    byte [] searchResult = this.searchCardResponse();
		    if(searchResult== null || searchResult.length!=4 || searchResult[2]!=0X00)
		    {
		    	if(searchResult==null)
		    		throw new Exception("Search Card Failed, search return bytes  is null");
		    	else
		    	{
		    		String s="";
		    		s = NfcUtils. convertBinToASCII(searchResult);
//		    		for (int i=0;i<searchResult.length;i++)
//		    		{
//		    			s = s+new Integer(searchResult[i]).toString()+" ";
//		    		}
		    		throw new Exception("Search Card Failed, search return bytes are "+s);
		    	}
		    }	    
		    selectCard();
			}else
			{
				throw new Exception("not found serial port");
			}
		  }
		  

	  /**
	   * 读卡器初始化，打印机直接寻卡
	   * @throws Exception
	   */
	  public void init() throws Exception
	  {
		  if(port==0)
		  {
			  this.initWithoutPort();
		  }
		  else
		  {
			  this.initWithPort();
		  }
	  }
	  /**
	   * 读卡器初始化，打印机直接寻卡
	   * @throws Exception
	   */
	  public void initWithPort() throws Exception
	  {
		if(readerType==NFCReaderUtil.READER_TYPE_SOCKET)
		{
			socket = new Socket( ip, port);
		    outputStream = socket.getOutputStream();
		    inputStream = socket.getInputStream();
		}else 
		{
			serialPortId = CommPortIdentifier.getPortIdentifier("COM"+port);
			serialPort = (SerialPort) serialPortId.open("READ", 30);  //打开串口，其中30是打开串口的超时时间
			serialPort.setSerialPortParams(115200, 8, 1, 0); //设置波特率，数据位，停止位，校验方式
			//从串口中得到输入输出流了
			outputStream = serialPort.getOutputStream();   
			inputStream = serialPort.getInputStream();
		}
		this.openRF();//打开场
		this.reset();//场重置
	    byte [] searchResult = this.searchCardResponse();
	    if(searchResult== null || searchResult.length!=4 || searchResult[2]!=0X00)
	    {
	    	if(searchResult==null)
	    		throw new Exception("Search Card Failed, search return bytes is null");
	    	else
	    	{
	    		String s="";
	    		s = NfcUtils. convertBinToASCII(searchResult);
//	    		for (int i=0;i<searchResult.length;i++)
//	    		{
//	    			s = s+new Integer(searchResult[i]).toString()+" ";
//	    		}
	    		throw new Exception("Search Card Failed, search return bytes are "+s);
	    	}
	    }	    
	    selectCard();
	  }
	

	  private String getComPort() {
		  Enumeration enu = CommPortIdentifier.getPortIdentifiers();
		  while(enu.hasMoreElements())
		  {
			  Object obj = enu.nextElement();
			  System.out.print(((CommPortIdentifier)obj).getName());
			  System.out.print(((CommPortIdentifier)obj).getCurrentOwner());
			  System.out.print(((CommPortIdentifier)obj).getPortType());
		  }
		  return "";
	}

	/**
	   * 读卡器初始化，打印机边进纸边寻卡
	   * @param feedCount, 尝试微进纸次数
	   * @param feedLength，每次进纸长度 mm
	   * @param printerIP, 打印机IP
	   * @param printerPort，打印机的打印端口
	   * @throws Exception
	   */
	  public void init(int feedCount,int feedLength,String printerIP,int printerPort,int feedCountAfterSearchSuccess) throws Exception
	  {
		if(readerType==NFCReaderUtil.READER_TYPE_SOCKET)
		{
			this.debug("step0 start connect");
			socket = new Socket( ip, port);
			this.debug("step0 start connect successful");
			outputStream = socket.getOutputStream();
		    inputStream = socket.getInputStream();
		}else 
		{
			serialPortId = CommPortIdentifier.getPortIdentifier("COM31");
			serialPort = (SerialPort) serialPortId.open("READ", 30);  //打开COM2串口，其中30是打开串口的超时时间
			serialPort.setSerialPortParams(115200, 8, 1, 0); //设置COM2的波特率，数据位，停止位，校验方式
			//从串口中得到输入输出流了
			outputStream = serialPort.getOutputStream();   
			inputStream = serialPort.getInputStream();
		}
		this.debug("step1. open RF");
		if(!RFOpened )
		{
			this.openRF();//打开场
			RFOpened = true;
		}
		this.debug("step2. RF Rest");
		this.reset();//场重置
		this.debug("step3. Search Card");
		if(!searchCardByFeed(feedCount,feedLength,printerIP,printerPort,feedCountAfterSearchSuccess))
	    {
	    	throw new Exception("Search Card Faild");
	    }
		this.debug("step3. Select Card");
	    if(!selectCardByFeed(feedCount,feedLength,printerIP,printerPort))
	    {
	    	throw new Exception("Select Card Faild");
	    }
	  }
	
	    /**
		 * 打开场
		 * @return
	     * @throws Exception 
		 */
		  public byte[] openRF() throws Exception
		  {
			    byte[] result = null;
			    this.debug("step1.1 write command start!");
			    outputStream.write(0xFE);
		    	outputStream.write(0x02);
		    	outputStream.write(0x01);// 
		    	outputStream.write(0xEF);
		    	outputStream.flush();
		    	this.debug("step1.2 write command finished!");
		    	this.debug("step1.3 start to read response");
		    	byte c =(byte)inputStream.read();
		    	int timeConsume = 0;
		    	while(c==-1)
		    	{
		    		this.debug("step1.4 sleep 0.5s");
			    	try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					timeConsume+=500;
					if(timeConsume>TIME_OUT )
					{
						throw new Exception("open RF time out,max time is "+TIME_OUT+"ms");
					}
					c =(byte)inputStream.read();
		    	}
		    	result = new byte[3];
		    	
		    	result[0] = (byte)c;//0xFE
		    	this.debug("step1.6 byte1:"+c);
		    	result[1] = (byte)inputStream.read();//0x02
		    	this.debug("step1.7 byte2:"+result[1]);
		    	result[2] = (byte)inputStream.read();//0xEF
		    	this.debug("step1.8 byte3:"+result[2]);
	    	    return result;
		  }
		  
	/**
	 * 场重置
	 * @return
	 * @throws IOException
	 */
	  public byte[] reset() throws IOException
	  {
		    byte[] result = null;
		    outputStream.write(0xFE);
	    	outputStream.write(0x03);
	    	outputStream.write(0x32);// 32 millisecond.
	    	outputStream.write(0xEF);
	    	outputStream.flush();
	    	debug("==== NFCReader SEND： "+"FE0332EF");
	    	byte c =(byte)inputStream.read();
	    	while(c==-1)
	    	{
		    	try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				c =(byte)inputStream.read();
	    	}
	    	result = new byte[3];
	    	result[0] = (byte)c;//0xFE
	    	result[1] = (byte)inputStream.read();//0x03	    	
	    	result[2] = (byte)inputStream.read();//0xEF
	    	debug("==== NFCReader RECEIVE： "+this.byteArrayToHexString(result));
	    	return result;
	  }
	  
	  private void debug(Object obj) {
		  if(isDebug )
		  {
		  if(obj !=null)
			  System.out.println(obj.toString());
		  }
		
	}

	  /**
	   * 微进纸，每进纸一次寻卡一次。直到寻到卡，或到最大尝试次数。
	   * @param feedCount, 最大尝试次数
	   * @param feedLength, 每次进纸的长度
	   * @param ip, 打印机IP
	   * @param port, 打印机的打印端口
	   * @return
	   * @throws Exception
	   */
	  public boolean searchCardByFeed(int feedCount,int feedLength,String ip,int port,int feedCountAfterSearchSuccess) throws Exception
	  {
		  int count=0;
		  PrinterUtil printerUtil = new PrinterUtil();
		  printerUtil.setIp(ip);
		  printerUtil.setPort(port);
		  printerUtil.initPrinter(2,2,1,0,0);//height 5mm, width 5mm, gap 1mm,refx 0 ,refy 0.
		  while (!searchCard() && count<feedCount)
		  {
			  debug("try search card: "+count+" time ");
			  printerUtil.setText("",1,1,new Integer(feedLength).toString(),0,1,1);
			  printerUtil.print();
			  count++;
			  Thread.sleep(500);
		  }
		  if(count==feedCount)
			  throw new Exception("reached max select card times:"+count);
		  else
		  {			  
			  debug("already moved "+count+" times, and move "+feedCountAfterSearchSuccess+" time");
			  for (int i=0;i<feedCountAfterSearchSuccess;i++)
			  {
				  printerUtil.setText("",1,1,new Integer(feedLength).toString(),0,1,1);
				  printerUtil.print();
			  }
			  
		  }
		  return true;
	  }
	  
	  /**
	   * 寻卡，返回true 或 false
	   * @return true or false
	   * @throws IOException 
	   */
	  public boolean searchCard() throws IOException
	  {
		    byte[] result = null;
		    outputStream.write(0xFE);
		    outputStream.write(0xA0);// command
	    	outputStream.write(0x01);// 1 time
	    	outputStream.write(0xEF);
	    	outputStream.flush();
	    	debug("==== NFCReader SEND： "+"FEA001EF");
	    	byte c =(byte)inputStream.read();
	    	while(c==-1)
	    	{
		    	try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				c =(byte)inputStream.read();
	    	}
	    	result = new byte[4];
    	    result[0] = c;//0xFE
    	    result[1] = (byte)inputStream.read();//0xA0
	    	result[2] = (byte)inputStream.read();//0x00
	    	result[3] = (byte)inputStream.read();//0xEF
	    	debug("==== NFCReader RECEIVED： "+this.byteArrayToHexString(result));
	    	
	    	if(result[2]!=0X00)
		    {
		    	return false;
		    }
    	    return true;
	  }
	  
	  /**
	   * 寻卡，返回寻卡响应码
	   * @return 2 bytes result
	 * @throws IOException 
	   */
	  private byte[] searchCardResponse() throws IOException
	  {
		    byte[] result = null;
		    outputStream.write(0xFE);
		    outputStream.write(0xA0);// command
	    	outputStream.write(0x01);// 1 time
	    	outputStream.write(0xEF);
	    	outputStream.flush();
	    	byte c =(byte)inputStream.read();
	    	while(c==-1)
	    	{
		    	try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				c =(byte)inputStream.read();
	    	}
	    	result = new byte[4];
    	    result[0] = c;//0xFE
    	    result[1] = (byte)inputStream.read();//0xA0
	    	result[2] = (byte)inputStream.read();//0x00
	    	result[3] = (byte)inputStream.read();//0xEF
    	    return result;
	  }

	   /**
	   * 微进纸，每进纸一次选卡一次。直到选卡成功，或到最大尝试次数。
	   * @param feedCount, 最大尝试次数
	   * @param feedLength, 每次进纸的长度
	   * @param ip, 打印机IP
	   * @param port, 打印机的打印端口
	   * @return
	   * @throws Exception
	   */
	  public boolean selectCardByFeed(int feedCount,int feedLength,String ip,int port) throws Exception
	  {
		  int count=0;
		  PrinterUtil printerUtil = new PrinterUtil();
		  printerUtil.setIp(ip);
		  printerUtil.setPort(port);
		  printerUtil.initPrinter(2,2,1,0,0);//height 5mm, width 5mm, gap 1mm,refx 0 ,refy 0.
		  while (!selectCard() && count<feedCount)
		  {
			  debug("try select card: "+count+" time ");
			  printerUtil.setText("",1,1,new Integer(feedLength).toString(),0,1,1);
			  printerUtil.print();
			  count++;
			  Thread.sleep(50);
		  }
		  if(count==feedCount)
			  throw new Exception("select card reached max times:"+count);
		  else
		  {
			  if(count>0)
			  {
				  debug("select card already moved "+count+" times, and move one more time");
				  printerUtil.setText("",1,1,new Integer(feedLength).toString(),0,1,1);
				  printerUtil.print();
			  }
		  }
		  return true;
	  }
	  
	  /**
	   * 选卡，获得选中标签的UID.
	   * @return response byte result
	 * @throws Exception 
	   */
	  public boolean selectCard() throws Exception
	  {
		    byte[] originalPackage = null;
		    byte[] result = null;
		    outputStream.write(0xFE);
		    outputStream.write(0XA1);//command
		    outputStream.write(0xEF);
	    	outputStream.flush();	   
	    	debug("==== NFCReader SEND： "+"FEA1EF");
	    	byte c =(byte)inputStream.read();
	    	while(c==-1)
	    	{
		    	try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				c =(byte)inputStream.read();
	    	}
    	    ByteBuffer bf = ByteBuffer.allocate(100);
    	    bf.put(c);
    	    int length=0;
    	    while(c!=-17)//EF
    	    {
    	    	c  = (byte)inputStream.read();
    	    	bf.put(c);
    	    	length++;
    	    }
    	    originalPackage = bf.array();
    	    debug("==== NFCReader RECEIVED RAW： "+this.byteArrayToHexString(originalPackage));
    	    result = decode(originalPackage);
    	    debug("==== NFCReader RECEIVED DECODED： "+this.byteArrayToHexString(result));
    	    if(result[2]==0X0)//correct
	    	{
    	    	byte[] UIDByteArray = new byte[7];
	   			 for(int i=0;i<7;i++)
	   			 {
	   				 UIDByteArray[i]=result[4+i];
	   			 }
	   			 UID = byteArrayToHexString(UIDByteArray);
	   			checkUID(UID);
	   			 if(checkPrevUID)//check Prev UID, for continue NFC reading and writing
	   			 {
		   			 if(prevUID!=null)
		   				 debug("prevUID="+prevUID+"  "+" UID="+UID+ "  COMPARE RESULT ="+prevUID.equals(UID));
		   			 else
		   				debug("prevUID is null");
		   			 
		   			 if(prevUID==null||prevUID.length()==0)
		   				prevUID = UID;
		   			 else if(prevUID.equals(UID))
		   			 {
		   				 return false;
		   			 }
		   			 else
		   				prevUID = UID;
	   			 }
	    	}else
	    	{
	    		debug("ERROR："+" select card error,select result is:"+byteArrayToHexString(result)+"EF");
	    		return false;
	    	}
	    	
		    return true;
	  }
	  
	  /**
	   * 报文编码
	   *      0xFE  -->  0xFD 0x00
       *      0xFD  -->  0xFD 0x01
       *      0xEF  -->  0xFD 0x02
	   * @param originalPackage  -->  
	   * @return
	   */
	  private byte[] encode(byte[] bytes) {
		    ByteBuffer buffer = ByteBuffer.allocate(100);
		    int count= 0;
		    for(int i=0;i<bytes.length;i++)
			{
		    	if(bytes[i]==-3)//FD
		    	{
		    		buffer.put((byte)-3);
		    		buffer.put((byte)1);
		    		count = count+2;
		    	}
		    	else if(bytes[i]==-17)//EF
		    	{
		    		buffer.put((byte)-3);
		    		buffer.put((byte)2);
		    		count = count+2;
		    	}
		    	else if (bytes[i]==-2)//FE
		    	{
		    		buffer.put((byte)-3);
		    		buffer.put((byte)0);
		    		count = count+2;
		    	}else
		    	{
		    		buffer.put(bytes[i]);
		    		count++;
		    	}
			}
		    byte[] result = new byte[count];
		    System.arraycopy(buffer.array(), 0, result, 0, count);
		    return result;
	  }
	  
	  
	  /**
	   * 报文解码
	   *     0xFD 0x00 --> 0xFE  
       *     0xFD 0x01 --> 0xFD
       *     0xFD 0x02 --> 0xEF
	   * @param originalPackage
	   * @return
	   */
	  private byte[] decode(byte[] bytes) {
	    ByteBuffer result = ByteBuffer.allocate(100);
		for(int i=0;i<bytes.length;i++)
		{
			if(bytes[i]==-3 && i!=bytes.length-1)//FD
			{
				if(bytes[i+1]==0 )
				{
					result.put((byte)-2);//FE
					i++;
				}
				else if (bytes[i+1]==1)
				{
					result.put((byte)-3);//FD
					i++;
				}
				else if ( bytes[i+1]==2)
				{
					result.put((byte)-17);//EF
					i++;
				}
				else
					result.put(bytes[i]);
			}
			else
				result.put(bytes[i]);
		}
	   return result.array();
	}

   public static String byteArrayToHexString(byte[] src) {
	   StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

   /**
    * 写入一个字符串
    * @param startPageNo：写入的开始页号
    * @param content：要写入的字符串
    * @param encoding：字符串的编码
    * @return
    * @throws Exception
    */
   public int writeStr(int startPageNo,String content,String encoding) throws Exception
   {
	   if(encoding == null)
		   encoding = System.getProperty("file.encoding");
	    byte[] byteArray = content.getBytes(encoding);				
		return writeBytes(startPageNo,byteArray);
   }
   
   /**
    * 写入一个字节数组
    * @param startPageNo：写入的开始页号
    * @param content：要写入的字符串
    * @param encoding：字符串的编码
    * @return
    * @throws Exception
    */
   public int writeBytes(int startPageNo,byte[] byteArray) throws Exception
   {
		byte[] newByteArray = appendByteArray(byteArray);
		int pageNum =newByteArray.length/4;
		for(int i =0;i<pageNum;i++)
		{
			if(!this.write((byte)startPageNo+i, newByteArray[0+4*i],newByteArray[1+4*i],newByteArray[2+4*i],newByteArray[3+4*i]))
				throw new Exception("write page "+(startPageNo+i)+" failed");
		}		
		return pageNum;
   }
   
   /**
    * 将页面内容作为字符串读取
    * @param startPageNo  开始页号
    * @param encoding   编码
    * @return
    * @throws Exception
    */
   public String readStr(int startPageNo,String encoding) throws Exception
   {
	   if(encoding == null)
		   encoding = System.getProperty("file.encoding");
	   byte[] result = readByte(startPageNo);
	   return new String(result,encoding);
   }
   
   /**
     * 重新重置场，再写入。用于发生读写错误后的重新操作。
	 * @throws Exception 
	 */
	 public boolean writeByteArrayFromStart(int page,byte[] content) throws Exception {
		 this.init();
		 return this.writeByteArray(page,content);
	 }
    /**
	 * 写卡
	 * @param address 要写入的地址
	 * @param content 要写入的内容，四个字节的字节数组
	 * @return 写入是否成功标识，true or false
     * @throws Exception 
	 */
	 public boolean writeByteArray(int page,byte[] content) throws Exception {
		  byte[] result = null;
		  ByteBuffer buffer = ByteBuffer.allocate(100);
		  int count=0;
		  int times =0;
		  if(content.length!=4)
			  throw new Exception("the content length must be 4!");
	    try {
	    	byte[] encodedContent = encode(content);	    	
	    	debug("begin to write content: "+this.byteArrayToHexString(encodedContent));
	    	
	    	outputStream.write(0xFE);
	    	outputStream.write(0xA5);
	    	outputStream.write(0xA2);
	    	outputStream.write(page);
	    	outputStream.write(encodedContent);
	    	outputStream.write(0xEF);
	    	outputStream.flush();
	    	
	    	
			Thread.sleep(5);//waite for 5 ms.
			
	    	byte c =(byte)inputStream.read();	    	
	    	while(c==-1)
	    	{
	    		times++;
	    		if(times>100)
	    		{
	    			debug("time=100");
	    			return false;
	    		}
		    	try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				c =(byte)inputStream.read();  //90378691
	    	}
	    	
	    	
	    	while(c!=-1)
	    	{
	    		count++;
		    	buffer.put((byte)c);
		    	if(c==-17)//EF
		    		break;
	    		c= (byte)inputStream.read();
	    	}
	    	
		    result = new byte[count];
		    System.arraycopy(buffer.array(), 0, result, 0, count);
		   
    	    if(isDebug)
    	    {
    	    	debug("write content: "+this.byteArrayToHexString(content));
    	    	debug("write response code:"+ this.byteArrayToHexString(result));
    	    }
	     } catch (IOException e) {
	    	e.printStackTrace();
	    }	
	     if(result[2]==0 && result[3]==-96)//[-2, -91, 0, -96, -17]
	    	 return true;
	     else
	     {
	    	 debug("====ERROR Response： "+this.byteArrayToHexString(result));
	    	 return false;
	     }
	  }  
	  
	 /**
	  * 重新获得UID
	  * @return
	  * @throws Exception
	  */
	 public String readUID() throws Exception
	 {
		 byte[] UID = new byte[7];
		 byte[] content = readByte(0);
		 for(int i=0;i<7;i++)
		 {
			 UID[i]=content[i];
		 }
		 return byteArrayToHexString(UID);
	 }
	 
	 /**
	  * 返回初始化，选卡时获得的UID
	  * @return
	  */
	 public String getUID()
	 {
		 return this.UID;
	 }
	 
	 /**
	 * 重新微调方式初始化（打开场，场重置，寻卡，选卡），然后读页面
	 * @param address 要读取的地址
	 * @return 以16进制字符串表示的字节数组，四个页面的内容（16字节）
	 * @throws Exception 
	 */
	 public String readByteAsHexStrFromStart(int feedCount,int feedLength,String printerIP,int printerPort,int page,int feedCountAfterSearchSuccess) throws Exception {
		 this.init(feedCount, feedLength, printerIP, printerPort,feedCountAfterSearchSuccess);
		 return this.readByteAsHexStr(page);
	 }
	 
	 /**
	 * 重新非微调方式初始化（打开场，场重置，寻卡，选卡），然后读页面
	 * @param address 要读取的地址
	 * @return 以16进制字符串表示的字节数组，四个页面的内容（16字节）
	 * @throws Exception 
	 */
	 public String readByteAsHexStrFromStart(int page) throws Exception {
		 this.init();
		 return this.readByteAsHexStr(page);
	 }
	 
	 /**
	 * 读卡
	 * @param address 要读取的地址
	 * @return 以16进制字符串表示的字节数组，四个页面的内容（16字节）
	 * @throws Exception 
	 */
	 public String readByteAsHexStr(int page) throws Exception {
		 byte[] content = readByte(page);
		 return this.byteArrayToHexString(content);
	 }
	 
	 /**
	  * 重新重置场，再读取。用于发生读写错误后的重新操作。
	  * @param page
	  * @return
	  * @throws Exception
	  */
	 public byte[] readByteFromStart(int page) throws Exception {
		 this.reset();
		 this.searchCard();
		 this.selectCard();
		 return readByte(page);
	 }
	 
	/**
	 * 读卡
	 * @param address 要读取的地址
	 * @return 字节数组，四个页面的内容（16字节）
	 * @throws Exception 
	 */
	 public byte[] readByte(int page) throws Exception {
		byte[] result = null;
	    try {
	    	outputStream.write(0xFE);
	    	outputStream.write(0xA5);
	    	outputStream.write(0x30);
	    	outputStream.write(page);
	    	outputStream.write(0xEF);
	    	outputStream.flush();
	    	debug("==== NFCReader SEND： "+"FEA530"+this.byteArrayToHexString(new byte[]{(byte)page})+"EF");
	    	byte c =(byte)inputStream.read();
	    	while(c==-1)
	    	{
		    	try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				c =(byte)inputStream.read();
	    	}
	    	result = new byte[16];
	    	int packageHead = c;//0xFE  -2
    	    
	    	
	    	 ByteBuffer bf = ByteBuffer.allocate(100);
	    	 bf.put(c);
    	    int length=0;
    	    while(c!=-17)//EF
    	    {
    	    	c  = (byte)inputStream.read();
    	    	bf.put(c);
    	    	length++;
    	    }
    	    byte[] originalPackage = bf.array();
    	    debug("==== NFCReader RECEIVED RAW： "+this.byteArrayToHexString(originalPackage));
    	    byte[] decodeResult = decode(originalPackage);
    	    debug("==== NFCReader RECEIVED DECODED： "+this.byteArrayToHexString(decodeResult));
    	    	
    	    if(decodeResult[2]==0)
    	    {
    	    	for(int i =0;i<16;i++)
    	    	{
    	    		result[i] = decodeResult[3+i];//
    	    	}
    	    	debug("==== NFCReader read page： "+byteArrayToHexString(result));    	    	
    	    }else
    	    {    throw new Exception("read failed, packageHeadByte="+packageHead+" responseCodeByte: "+this.byteArrayToHexString(decodeResult));
    	    }
	     } catch (IOException e) {
	    	e.printStackTrace();
	    }	
	     
	    return result;
	}
		 
	public static void main(String[] args)
	 {
		 NFCReaderUtil util = null;
		try {
			util = new NFCReaderUtil(NFCReaderUtil.READER_TYPE_SERIAL_PORT);
			util.setPort(0);
			util.init();
			byte[] content = new byte[]{0x50,0x50,0x50,0x50};
			util.writeByteArray((byte)5, content);
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			util.release();
		}

	 }

	/**
	 * 释放连接
	 */
	public void release() {
		try {
			if(socket!=null)
			{
				socket.close();
				this.debug("connect closed!");
			}
				if(serialPort!=null)
				serialPort.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		NFCReaderUtil.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		NFCReaderUtil.port = port;
	}
	

	/**
	 * 写一页，四个字节
	 * @param pageNo
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @throws Exception 
	 */
	public boolean write(int pageNo, byte b, byte c, byte d, byte e) throws Exception {
		byte[] array = new byte[4];
		array[0]= b;
		array[1]= c;
		array[2]= d;
		array[3]= e;
		return writeByteArray(pageNo, array);
	}
	
	public boolean writeByteArrayFromStart(int pageNo, byte b, byte c, byte d, byte e) throws Exception {
		byte[] array = new byte[4];
		array[0]= b;
		array[1]= c;
		array[2]= d;
		array[3]= e;
		return writeByteArrayFromStart(pageNo, array);
	}
	//数组大小到一个页面 （4字节） 的整数倍 
	public static byte[] appendByteArray(byte[] byteArray) {
		int length = byteArray.length;
		int m = length % 4;		
		byte[] newByteArray;
		if(m==0)
			newByteArray = new byte[length];
		else			
			newByteArray = new byte[length+(4-m)];
		System.arraycopy(byteArray, 0, newByteArray, 0, length);
		return newByteArray;
	}
	
	
    /**
	 * 16进制字符串转化为字节数组
	 */
	public static byte[] hexStringToBytes(String hexString) {
	    if (hexString == null || hexString.equals("")) {  
	        return null;  
	    }  
	    hexString = hexString.toUpperCase();  
	    int length = hexString.length() / 2;  
	    char[] hexChars = hexString.toCharArray();  
	    byte[] d = new byte[length];  
	    for (int i = 0; i < length; i++) {  
	        int pos = i * 2;  
	        d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));  
	    }  
	    return d;  
	}
	
	public static byte charToByte(char c) {  
		int i = "0123456789ABCDEF".indexOf(c);
		if(i == -1){
			throw new NumberFormatException();
		}else{
			return (byte)i;  
		}   
	}

	public boolean isCheckPrevUID() {
		return checkPrevUID;
	}

	public void setCheckPrevUID(boolean checkPrevUID) {
		this.checkPrevUID = checkPrevUID;
	}

	public byte[] execute(byte[] byteArrayCommand, byte[] byteArrayPara1,
			byte[] byteArrayPara2) {
		byte[] result = null;
	    try {
	    	outputStream.write(0xFE);
	    	outputStream.write(byteArrayCommand);
	    	if(byteArrayPara1!=null)
	    		outputStream.write(byteArrayPara1);
	    	if(byteArrayPara2!=null)
	    		outputStream.write(byteArrayPara2);
	    	outputStream.write(0xEF);
	    	outputStream.flush();
	    	String str1= this.byteArrayToHexString(byteArrayCommand);
	    	String str2= this.byteArrayToHexString(byteArrayPara1);
	    	String str3= this.byteArrayToHexString(byteArrayPara2);
	    	debug("==== NFCReader SEND： "+"FE"+byteArrayCommand+str1+str2==null?"":str2+"EF");
	    	byte c =(byte)inputStream.read();
	    	int timeout = 1000;
	    	int time=0;
	    	while(c==-1)
	    	{
		    	try {
					Thread.sleep(50);
					time=time+50;
					if(time> timeout)
						return new byte[]{(byte)0XFF,(byte)0XFF,(byte)0XFF,(byte)0XFF};// when timeout return 4bytes FF,
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				c =(byte)inputStream.read();
	    	}
	    	result = new byte[16];
	    	int packageHead = c;//0xFE  -2
    	    
	    	
	    	 ByteBuffer bf = ByteBuffer.allocate(100);
	    	 bf.put(c);
    	    int length=0;
    	    while(c!=-17)//EF
    	    {
    	    	c  = (byte)inputStream.read();
    	    	bf.put(c);
    	    	length++;
    	    }
    	    byte[] originalPackage = bf.array();
    	    debug("==== NFCReader RECEIVED RAW： "+this.byteArrayToHexString(originalPackage));
    	    byte[] decodeResult = decode(originalPackage);
    	    debug("==== NFCReader RECEIVED DECODED： "+this.byteArrayToHexString(decodeResult));
    	    	
//    	    if(decodeResult[2]==0)
//    	    {
//    	    	for(int i =0;i<16;i++)
//    	    	{
//    	    		result[i] = decodeResult[3+i];//
//    	    	}
//    	    	debug("==== NFCReader read page： "+byteArrayToHexString(result));    	    	
//    	    }else
//    	    {    throw new Exception("read failed, packageHeadByte="+packageHead+" responseCodeByte: "+this.byteArrayToHexString(decodeResult));
//    	    }
    	    return decodeResult;
	     } catch (IOException e) {
	    	e.printStackTrace();
	    }
	 	return null;
	}

	public int getTIME_OUT() {
		return TIME_OUT;
	}

	public void setTIME_OUT(int time_out) {
		TIME_OUT = time_out;
	}
	
	private static void checkUID(String uid) throws Exception
	{
		if(uid!=null && uid.length()>0)
		{
			if("80".equals(uid.substring(0,2)) || "a2".equals(uid.substring(0,2)) || "53".equals(uid.substring(0,2)))
			{
			}
			else
			{
				throw new Exception ("uid is not correct");
			}
		}else
			throw new Exception("uid is null");
	
	}

}

