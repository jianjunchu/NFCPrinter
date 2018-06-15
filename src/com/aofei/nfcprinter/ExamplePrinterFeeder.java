package com.aofei.nfcprinter;

public class ExamplePrinterFeeder {

	public static void main(String[] args)
	{
		if(args.length!=4)
		{
			System.out.println("usage: ExamplePrinterFeeder ip port width height");
			System.exit(-1);
		}

		String ip  = args[0];
		int port = new Integer(args[1]).intValue();
		int width  = new Integer(args[2]).intValue();
		int height = new Integer(args[3]).intValue();
		ExamplePrinterFeeder feeder = new ExamplePrinterFeeder();
		try {
			feeder.move(ip,port,width, height);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void move(String ip,int port,int width,int height) throws Exception
	{
		  int count=0;
		  PrinterUtil printerUtil = new PrinterUtil();
		  printerUtil.setIp(ip);
		  printerUtil.setPort(port);
		  printerUtil.initPrinter(width,height,1,0,0);//height 5mm, width 5mm, gap 1mm,refx 0 ,refy 0.
		  printerUtil.setText("",1,1,"TST24.BF2",0,1,1);
		  printerUtil.print();
		
	}
}
