package com.aofei.nfcprinter;

import com.aofei.nfc.NFCReaderUtil;

import java.io.File;

public class Example {
	

	  public static boolean DEBUG = true;

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
		Example example = new Example();
		String executeByCommandflag = propUtil.get("command_mode");
		if(executeByCommandflag.equalsIgnoreCase("true"))
			example.executeByCommand(propUtil);
		else
			example.executeWithoutPrint(propUtil);
	 }

		private void executeByCommand(PropUtil propUtil) {

			PrinterUtil printer = new PrinterUtil();	
			printer.setIp(propUtil.get("printer_ip"));
			printer.setPort(new Integer(propUtil.get("printer_port")).intValue());
			int copies = new Integer(propUtil.get("print_copy")).intValue();
			
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
				com.aofei.nfc.NFCReaderUtil nfcReader = new com.aofei.nfc.NFCReaderUtil(com.aofei.nfc.NFCReaderUtil.READER_TYPE_SOCKET);
				String printerIp  = propUtil.get("printer_ip");
				int printerPort= new Integer(propUtil.get("printer_port")).intValue();
				int nfcPort = new Integer(propUtil.get("nfc_port")).intValue();				
				int retryTimes = new Integer(propUtil.get("micro_adjust_time_before_select")).intValue();
				int feedCountAfterSearchSuccess= new Integer(propUtil.get("micro_adjust_time_after_select")).intValue();
				nfcReader.setIp(printerIp);
				nfcReader.setPort(nfcPort);			
				try {
					nfcReader.init(retryTimes,1,printerIp,printerPort,feedCountAfterSearchSuccess);
				} catch (Exception e) {
					e.printStackTrace();
				}					
				String[] commands = new String[1000];
				String[] commandParameter1 = new String[1000];
				String[] commandParameter2 = new String[1000];
				String[] commandExpects = new String[1000];
				int commandCount = 0;
				for(int i=0;i<1000;i++)
				{
					String command  = propUtil.get("command"+i+1);
					if(command==null || command.equalsIgnoreCase("NULL"))
						break;
					else
					{
						commandCount++;
						commands[i]=command;
						commandParameter1[i]=propUtil.get("command"+(i+1)+"_para1");
						commandParameter2[i]=propUtil.get("command"+(i+1)+"_para2");
						commandExpects[i]=propUtil.get("command"+(i+1)+"_expected");
					}
				}
				
				for(int i=0;i<commandCount;i++)
				{
					String command = commands[i];
					String para1 = commandParameter1[i];
					String para2 = commandParameter2[i];
					String commandExpect = commandExpects[i];
					if (command==null || command.equalsIgnoreCase("NULL"))
						break;
					else{
						try {
							execute(nfcReader,command,para1,para2,commandExpect);
						} catch (Exception e) {
							try {
								System.out.println("write retry once");
								execute(nfcReader,command,para1,para2,commandExpect);
							} catch (Exception e1) {
								try {
									System.out.println("write retry twice");
									execute(nfcReader,command,para1,para2,commandExpect);
								} catch (Exception e2) {
									e2.printStackTrace();
									if("true".equalsIgnoreCase(badFlag))
									{
										this.debug("the "+count+" tag write failed!");
										try{
											printer.initPrinter(propUtil);
											} catch (Exception ex) {
												ex.printStackTrace();
											}
											printer.setText("BAD",120,100,"3",0,1,1);
											printer.print();
											printer.release();
									}
									if(existOnErrors )
									{
										System.out.print("Execute Failed for 3 times, System exist");
										System.exit(-1);
									}
								}
							}
						}//写nfc 内容		
					}
				}
				nfcReader.release();//释放连接
		}
	}

		private boolean existOnErrors =true;	

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
				String UID = nfcReader.readUID();
				System.out.println("UID："+UID);
				
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
				System.out.println("reading result："+result);
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
				System.out.println("============Counter："+count+"================");
				int startPageNo = new Integer(propUtil.get("read_start_page")).intValue();
				com.aofei.nfc.NFCReaderUtil nfcReader = new com.aofei.nfc.NFCReaderUtil(com.aofei.nfc.NFCReaderUtil.READER_TYPE_SOCKET);
				String printerIp  = propUtil.get("printer_ip");
				int printerPort= new Integer(propUtil.get("printer_port")).intValue();
				int nfcPort = new Integer(propUtil.get("nfc_port")).intValue();				
				int retryTimes = new Integer(propUtil.get("micro_adjust_time_before_select")).intValue();
				int feedCountAfterSearchSuccess= new Integer(propUtil.get("micro_adjust_time_after_select")).intValue();
				nfcReader.setIp(printerIp);
				nfcReader.setPort(nfcPort);			
				try {
					nfcReader.init(retryTimes,1,printerIp,printerPort,feedCountAfterSearchSuccess);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				String UID=null;
				UID = nfcReader.getUID();
				System.out.println("UID："+UID);
				
				String result = null;
				try {
					result = nfcReader.readByteAsHexStr(startPageNo);
				} catch (Exception e) {
					try {
						System.out.println("read page retry once more");
						result = nfcReader.readByteAsHexStrFromStart(startPageNo);//retry once
					} catch (Exception e1) {
						try {
							System.out.println("read page retry twice more");
							result = nfcReader.readByteAsHexStrFromStart(startPageNo);//retry twice
						} catch (Exception e2) {
							e2.printStackTrace();
							if(existOnErrors)
							{
							System.out.print("READ FAILD FOR 3 TIME, System exist");
							System.exit(-1);
							}
						}
					}
				}
				System.out.println("reading result："+result);
				
				
				try {
					writeToNFC(nfcReader,propUtil);
				} catch (Exception e) {
					try {
						System.out.println("write retry once");
						writeToNFC(nfcReader,propUtil);//retry once
					} catch (Exception e1) {
						try {
							System.out.println("write retry twice");
							writeToNFC(nfcReader,propUtil);//retry twice
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
							if(existOnErrors )
							{
								System.out.print("WRITE FAILD FOR 3 TIME, System exist");
								System.exit(-1);
							}
						}
					}
				}//写nfc 内容		
				
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
				
				if("true".equalsIgnoreCase(badFlag))
				{
					try{
						printer.initPrinter(propUtil);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						printer.setText("BAD",120,100,"3",0,1,1);
						printer.print();
						printer.release();
				}
			}
		}
	}
	
	private void writeToNFC(com.aofei.nfc.NFCReaderUtil NFCReader, PropUtil propUtil) throws Exception {
		String str = propUtil.get("nfc_content");
		int startPageNo = new Integer(propUtil.get("write_start_page")).intValue();
		byte[] byteArray = com.aofei.nfc.NFCReaderUtil.hexStringToBytes(str);
		byte[] newByteArray = com.aofei.nfc.NFCReaderUtil.appendByteArray(byteArray);
		for(int i =0;i<newByteArray.length/4;i++)
		{
			if(!NFCReader.write(startPageNo+i, newByteArray[0+4*i],newByteArray[1+4*i],newByteArray[2+4*i],newByteArray[3+4*i]))
				throw new Exception("write page "+(startPageNo+i)+" failed");
		}
	}
	
	private void execute(com.aofei.nfc.NFCReaderUtil NFCReader, String command, String para1, String para2, String commandExpect) throws Exception {
		byte[] byteArrayCommand = com.aofei.nfc.NFCReaderUtil.hexStringToBytes(command);
		byte[] byteArrayPara1 = com.aofei.nfc.NFCReaderUtil.hexStringToBytes(para1);
		byte[] byteArrayPara2 = com.aofei.nfc.NFCReaderUtil.hexStringToBytes(para2);
		byte[] result = NFCReader.execute(byteArrayCommand,byteArrayPara1,byteArrayPara2);
		if (commandExpect!=null && !commandExpect.equalsIgnoreCase("true"))
		{
			if(commandExpect!= NFCReaderUtil.byteArrayToHexString(result))
				throw new Exception(" execute failed! expected result is "+commandExpect+","+" real result is："+result);
		}
				
		
	}
	
	private static void debug(Object obj) {
		if(DEBUG && obj!=null )
		{
			System.out.println("nfcprinter info: "+obj.toString());
		}else
			System.out.println("nfcprinter info: obj is null");		
	}
}
