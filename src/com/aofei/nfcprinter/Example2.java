package com.aofei.nfcprinter;

import com.aofei.nfc.NFCReaderUtil;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Example2 {
	  public static boolean DEBUG = true;
	  public static String UID = "";

	public static void main(String[] args)
	 {
		if(args!=null && args.length>0&& args[0].equalsIgnoreCase("-v"))
		{
			System.out.println("0.5");
			System.exit(-1);
		}
		PropUtil propUtil = new PropUtil(new File("./content.properties"));
		if(propUtil!=null && propUtil.get("write_nfc")!=null)
		{
			debug("load content.properties succesfully");
		}else
			debug("load content.properties failed");
		Example2 example2 = new Example2();
		example2.executeWithoutPrint(propUtil);
	 }

	/**
	 * 先写NFC 标签，再打印的快速执行方法，需要有配置文件。
	 * @param propUtil
	 * @throws Exception 
	 */
	public void execute(PropUtil propUtil) throws Exception {
		PrinterUtil printer = new PrinterUtil();	
		printer.setIp(propUtil.get("printer_ip"));
		printer.setPort(new Integer(propUtil.get("printer_port")).intValue());
		int lineCount = new Integer(propUtil.get("line_count")).intValue();
		int copies = new Integer(propUtil.get("print_copy")).intValue();
		
		String[] text = new String[lineCount];
		String[] font = new String[lineCount];
		String[] multi = new String[lineCount];
		String[] leftEdge = new String[lineCount];
		String[] lineHeight = new String[lineCount];
		
		for(int i=1;i<=lineCount;i++)
		{
			text[i-1]=propUtil.get("line"+i);
			font[i-1]=propUtil.get("line"+i+"_font");
			multi[i-1]=propUtil.get("line"+i+"_multiplication");
			lineHeight[i-1]=propUtil.get("line"+i+"_height");	
			leftEdge[i-1]=propUtil.get("line"+i+"_left");
		}	
		
		int rotation = 0;
		for(int count=0;count<copies;count++)// for one copy
		{
			String writeNFC = propUtil.get("write_nfc");
			String printNFC = propUtil.get("print_nfc");
			String badFlag = propUtil.get("bad_flag");
			boolean writeNFCResult=false;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if("true".equalsIgnoreCase(writeNFC))
			{
				System.out.println("NFC Counter："+count);
				int startPageNo = new Integer(propUtil.get("read_start_page")).intValue();
			    com.aofei.nfc.NFCReaderUtil nfcReader = new com.aofei.nfc.NFCReaderUtil(com.aofei.nfc.NFCReaderUtil.READER_TYPE_SOCKET);
				String printerIp  = propUtil.get("printer_ip");
				int printerPort= new Integer(propUtil.get("printer_port")).intValue();
				int nfcPort = new Integer(propUtil.get("nfc_port")).intValue();			
				int retryTimes = new Integer(propUtil.get("micro_adjust_time_before_select")).intValue();
				int feedAfterSelect = new Integer(propUtil.get("micro_adjust_time_after_select")).intValue();
				nfcReader.setIp(printerIp);
				nfcReader.setPort(nfcPort);				
				nfcReader.init(retryTimes,1,printerIp,printerPort,feedAfterSelect);
				UID = nfcReader.readUID();
				System.out.println("│UID: \t"+UID);
				
				String result = null;
				try {
					result = nfcReader.readByteAsHexStr(startPageNo);
				} catch (Exception e) {
					try {
						result = nfcReader.readByteAsHexStr(startPageNo);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				writeToNFC(nfcReader,propUtil);//写nfc 内容				
			}
			
			if(writeNFCResult)
			{
				if( "true".equalsIgnoreCase(printNFC))
				{
					try{
						printer.initPrinter(propUtil);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
					for(int i=0;i<text.length;i++)// for one line
					{
						int x= new Integer(multi[i].substring(0,multi[i].indexOf(","))).intValue();
						int y= new Integer(multi[i].substring(multi[i].indexOf(",")+1,multi[i].length())).intValue();
						int edge = new Integer(leftEdge[i]).intValue();
						int height = new Integer(lineHeight[i]).intValue();
						
						if(new Integer(propUtil.get("print_rotation")).intValue()==0)//纵向打印
							printer.setText(text[i],edge,height*i,font[i],rotation,x,y);
						else//旋转打印
							printer.setText(text[i],height*i,PrinterUtil.EDGE_RIGHT,font[i],rotation,x,y);
						
					}
					printer.print();
					printer.release();
				}
			}
			else
			{
				if("true".equalsIgnoreCase(badFlag))
				{
					try{
						printer.initPrinter(propUtil);
						} catch (Exception e) {
							e.printStackTrace();
						}
						printer.setText("BAD",120,100,"3",0,1,1);
						printer.print();
						printer.release();
				}
			}
		}
	}

	/**
	 * 先写NFC 标签，再打印的快速执行方法，需要有配置文件。
	 * @param propUtil
	 */
	public void executeWithoutPrint(PropUtil propUtil) {
		PrinterUtil printer = new PrinterUtil();	
		printer.setIp(propUtil.get("printer_ip"));
		printer.setPort(new Integer(propUtil.get("printer_port")).intValue());
		int lineCount = new Integer(propUtil.get("line_count")).intValue();
		int copies = new Integer(propUtil.get("print_copy")).intValue();
		
		debug("ip="+propUtil.get("printer_ip"));
		
		String t = "";
		String[] text = new String[lineCount];
		String[] font = new String[lineCount];
		String[] multi = new String[lineCount];
		String[] leftEdge = new String[lineCount];
		String[] lineHeight = new String[lineCount];
		
		for(int i=1;i<=lineCount;i++)
		{
			text[i-1]=propUtil.get("line"+i);
			font[i-1]=propUtil.get("line"+i+"_font");
			multi[i-1]=propUtil.get("line"+i+"_multiplication");
			lineHeight[i-1]=propUtil.get("line"+i+"_height");	
			leftEdge[i-1]=propUtil.get("line"+i+"_left");
		}

		//__-0oO1Ll1234567890qwertyuiopasdfghjkl:zxcvbnm 0
//		System.out.print("\n┌23456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789┐");
		System.out.print("\n┌───────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.print("\n│"+"                                                                       "+now()+"│");
//		__
		int rotation = 0;
//		for(int count=0;count<copies;count++)// for one copy
		for(int count=1;count<copies+1;count++)// for one copy
		{
			String writeNFC = propUtil.get("write_nfc");
			String printNFC = propUtil.get("print_nfc");
			String badFlag = propUtil.get("bad_flag");
			boolean writeNFCResult=false;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if("true".equalsIgnoreCase(writeNFC))
			{
//				System.out.println("\n├──────────────────────────────────────── "+new DecimalFormat("00000").format(count)+" / " + new DecimalFormat("00000").format(copies) + " ────────────────────────────────────────┤");
				System.out.print("\n├──────────────────────────────────────── "+new DecimalFormat("00000").format(count)+" / " + new DecimalFormat("00000").format(copies) + " ────────────────────────────────────────");

				int startPageNo = new Integer(propUtil.get("read_start_page")).intValue();
				com.aofei.nfc.NFCReaderUtil nfcReader = new com.aofei.nfc.NFCReaderUtil(com.aofei.nfc.NFCReaderUtil.READER_TYPE_SOCKET);
				String printerIp  = propUtil.get("printer_ip");
				int printerPort= new Integer(propUtil.get("printer_port")).intValue();
				int nfcPort = new Integer(propUtil.get("nfc_port")).intValue();				
				nfcReader.setIp(printerIp);
				nfcReader.setPort(nfcPort);
				int retryTimes = new Integer(propUtil.get("micro_adjust_time_before_select")).intValue();
				int feedAfterSelect = new Integer(propUtil.get("micro_adjust_time_after_select")).intValue();
//				System.out.println("├0──────────────────────────────────────────────────────────────────────────────────────────────┤");
				try {
					nfcReader.init(retryTimes,1,printerIp,printerPort,feedAfterSelect);
				} catch (Exception e) {
					e.printStackTrace();
				}
//				System.out.println("├1──────────────────────────────────────────────────────────────────────────────────────────────┤");
				System.out.print("┤\n");

				UID=null;
				UID = nfcReader.getUID();
				System.out.println("│UID: \t\t\t"+UID+"                                                          "+"│");

				String result = null;
				try {
					result = nfcReader.readByteAsHexStr(startPageNo);
				} catch (Exception e) {
					try {
						System.out.println("read page retry once");
						result = nfcReader.readByteAsHexStrFromStart(startPageNo);//retry once
					} catch (Exception e1) {
						try {
							System.out.println("read page retry twice");
							result = nfcReader.readByteAsHexStrFromStart(startPageNo);//retry twice
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				}
				System.out.println("│reading result: \t"+result+"                                        "+"│");
//				UID=null;
//				UID = nfcReader.getUID();
//				System.out.println("│UID: \t\t\t"+UID+"                                                          "+"│");

				try {
					writeToNFC(nfcReader,propUtil);
					writeNFCResult=true;
				} catch (Exception e) {		}//写nfc 内容
				nfcReader.release();//释放连接
			}
			if(writeNFCResult)
			{
				if( "true".equalsIgnoreCase(printNFC))
				{
					try{
						printer.initPrinter(propUtil);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						printer.setText("",120,100,"3",0,1,1);
						printer.print();
						printer.release();
				}
			}
			else
			{
				System.out.println("==========the "+count+" tag is bad!!");
				if("true".equalsIgnoreCase(badFlag))
				{
					try{
						printer.initPrinter(propUtil);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						printer.setText("BAD",12,10,"3",0,1,1);
						printer.print();
						printer.release();
				}
			}
		}
		System.out.print("\n├───────────────────────────────────────────────────────────────────────────────────────────────┤");
		System.out.print("\n│"+"                                                                       "+now()+"│");
		System.out.println("\n└───────────────────────────────────────────────────────────────────────────────────────────────┘");
	}

	private static void debug(Object obj) {
		if(DEBUG && obj!=null )
		{
			System.out.println("nfcprinter info: "+obj.toString());
		}else
			System.out.println("nfcprinter info: obj is null");		
	}

	//字符串转16进制字符串
	public static String StringToHex(String s) {
		char[] chars = s.toCharArray();
		StringBuffer hex = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			hex.append(Integer.toHexString((int) chars[i]));
		}
		return hex.toString();
	}

	//截取字符串
	public static String String_Token(String j) {
//		String s[]=j.split(":");
//		String s[]=j.split("{\"token\":\"");
//		String s[]=j.split("\"");
//		String t=s[1];

//		String t=s.substring(int begin，int end);
//		String t=j.substring(10, 74);
		String t=j.substring(10, 74);
//		System.out.println("TokenString: " + t);// .toString());
		return t;
	}

	public static String now(){
//		System.out.println(new Date());
//		new java.sql.Date(new java.util.Date().getTime()).toString();
//        SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss.SSS",Locale.CHINESE);
        SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss.SSS");
//        TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");//获取中国的时区
//        TimeZone tz = TimeZone.getTimeZone("GMT+08:00"); 
//        TimeZone.setDefault(tz); 
//        SimpleDateFormat.setTimeZone(timeZoneChina);
//        String SimpleDateFormatString = SimpleDateFormat.format(Calendar.getInstance().getTime());
		return SimpleDateFormat.format(new java.sql.Date(new java.util.Date().getTime()));
	}

	private void writeToNFC(com.aofei.nfc.NFCReaderUtil NFCReader, PropUtil propUtil) throws Exception {
		
//		String string_ASCII = propUtil.get("nfc_content");
		int writeInteval = new Integer(propUtil.get("write_page_time_interval")).intValue();
		String string_ASCII = "0e783e10da55cd3e57fc891fe669ff481f3a484ac8182dc62a6c83be5af0a2a6";
		String string_HEX = "920a" + StringToHex("@text/plain") + string_ASCII + "540f17" + StringToHex("android.com:pkgcn.tracefoodorigin.wine") +  
				"fe0000" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000" + "00000000" + "a25d2354d700a300";

//		System.out.println("│string_ASCII:\t\t" + string_ASCII +"        "+"│");
////		System.out.println("string_HEX: " + string_HEX);
//		System.out.print("│string_HEX:\t\t" + string_HEX +"        "+"│\n│write finished: \t");

		int startPageNo = new Integer(propUtil.get("write_start_page")).intValue();
		byte[] byteArray = com.aofei.nfc.NFCReaderUtil.hexStringToBytes(string_HEX);
		byte[] newByteArray = com.aofei.nfc.NFCReaderUtil.appendByteArray(byteArray);
		for(int i =0;i<newByteArray.length/4;i++)
		{
			System.out.println("start write page "+(startPageNo+i)+" page content: "+ NFCReaderUtil.byteArrayToHexString(new byte[]{newByteArray[0+4*i],newByteArray[1+4*i],newByteArray[2+4*i],newByteArray[3+4*i]}));
			if(!NFCReader.write(startPageNo+i, newByteArray[0+4*i],newByteArray[1+4*i],newByteArray[2+4*i],newByteArray[3+4*i]))
			{
				if(!NFCReader.writeByteArrayFromStart(startPageNo+i, newByteArray[0+4*i],newByteArray[1+4*i],newByteArray[2+4*i],newByteArray[3+4*i]))
				{
					if(!NFCReader.writeByteArrayFromStart(startPageNo+i, newByteArray[0+4*i],newByteArray[1+4*i],newByteArray[2+4*i],newByteArray[3+4*i]))
					{
					throw new Exception("write page "+(startPageNo+i)+" failed"); 
					}
				}
			}
			
			Thread.sleep(writeInteval);
			System.out.println("end write page "+(startPageNo+i));
		}
		System.out.print("│");
	}
}
