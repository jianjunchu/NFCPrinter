package com.aofei.nfcprinter;

/**
 * 
 * @author
 * 2013-10
 * 
 */
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;



//纵向打印测试
//SIZE 86 mm,70 mm
//GAP 11
//DENSITY 6
//SPEED 5
//DIRECTION 0f
//REFERENCE 0,0
//CLS
//BAR 5,16,1600,5
//BAR 5,220,1600,5
//TEXT 5,40 ,"1",0,2,2,"NO:4872"
//TEXT 240,40,"TSS24.BF2",0,1,1,"打印机"
//BARCODE 5,100,"128M",80,1,0,2,1,"69123888"
//PRINT  1,1

//横向打印测试
//SIZE 35 mm,25 mm
//GAP 3
//DENSITY 6
//SPEED 5
//DIRECTION 0
//REFERENCE 0,0
//CLS
//BAR 20,16,1600,5
//BAR 20,180,1600,5
//TEXT 20,40 ,"1",90,2,2,"NO:4872"
//TEXT 240,40,"TSS24.BF2",0,1,1,""
//BARCODE 20,100,"128M",40,1,0,2,1,"69123888"
//PRINT 1
public class PrinterUtil {
	  String TAG = "xxl";
	  private String ip = null;
	  private int port = 9100;
	  OutputStream socketOut = null;
	  InputStream socketIn = null;
	  OutputStreamWriter writer = null;
	  byte[] SIZE;
	  byte[] GAP;
	  byte[] BLINE;
	  byte[] LINE;
	  byte[] CLS;
	  byte[] OFFSET;
	  byte[] FORMFEED;
	  byte[] SET_RIBBON_ON;
	  byte[] HOME;
	  byte[] REFERENCE;
	  byte[] DIRECTION_0;
	  
	  public static int EDGE_LEFE =120; //dots
	  public static int EDGE_RIGHT =480; //dots
	  public static int CHARACTER_HEIGHT =50; //dots
	  
	  
	  private Socket  socket=null;
	  
	  {
		  //SIZE 60 mm,40 mm  
		  //53 49 5A 45 20 36 30 20 6D 6D 2C 34 30 20 6D 6D 
		  SIZE = new byte[16];
		  SIZE[0] = 0x53;
		  SIZE[1] = 0x49;
		  SIZE[2] = 0x5A;
		  SIZE[3] = 0x45;
		  SIZE[4] = 0x20;
		  SIZE[5] = 0x36;
		  SIZE[6] = 0x30;
		  SIZE[7] = 0x20;
		  SIZE[8] = 0x6D;
		  SIZE[9] = 0x6D;
		  SIZE[10] = 0x2C;
		  SIZE[11] = 0x34;
		  SIZE[12] = 0x30;
		  SIZE[13] = 0x20;
		  SIZE[14] = 0x6D;
		  SIZE[15] = 0x6D;		  
	
		  //标签间距，用于无黑标的标签
		  //GAP 11
		  //47 41 50 20 33 
		  GAP = new byte[5];
		  GAP[0] = 0x47;
		  GAP[1] = 0x41;
		  GAP[2] = 0x50;
		  GAP[3] = 0x20;
		  GAP[4] = 0x33;
		  
		  
		  //黑标高度和额外送出的长度，用于有黑标的标签
		  //BLINE 3 mm,4 mm  
		  //42 4C 49 4E 45 20 33 20 6D 6D 2C 34 20 6D 6D 
		  BLINE = new byte[15];
		  BLINE[0] = 0x42;
		  BLINE[1] = 0x4C;
		  BLINE[2] = 0x49;
		  BLINE[3] = 0x4E;
		  BLINE[4] = 0x45;
		  BLINE[5] = 0x20;
		  BLINE[6] = 0x33;
		  BLINE[7] = 0x20;
		  BLINE[8] = 0x6D;
		  BLINE[9] = 0x6D;
		  BLINE[10] = 0x2C;
		  BLINE[11] = 0x34;
		  BLINE[12] = 0x20;
		  BLINE[13] = 0x6D;
		  BLINE[14] = 0x6D;

		  //换行命令
		  LINE = new byte[2];
		  LINE[0] = 0x0D;
		  LINE[1] = 0x0A;
		  
		  //参考坐标
		  //REFERENCE 0,0
		  //52 45 46 45 52 45 4E 43 45 20 30 2C 30
		  REFERENCE = new byte[13];
		  REFERENCE[0] = 0x52;
		  REFERENCE[1] = 0x45;
		  REFERENCE[2] = 0x46;
		  REFERENCE[3] = 0x45;
		  REFERENCE[4] = 0x52;
		  REFERENCE[5] = 0x45;
		  REFERENCE[6] = 0x4E;
		  REFERENCE[7] = 0x43;
		  REFERENCE[8] = 0x45;
		  REFERENCE[9] = 0x20;
		  REFERENCE[10] = 0x30;
		  REFERENCE[11] = 0x2C;
		  REFERENCE[12] = 0x30;
		  
		  //清除数据前缓存数据命令
		  //CLS
		  //43 4C 53 
		  CLS = new byte[3];
		  CLS[0] = 0x43;
		  CLS[1] = 0x4C;
		  CLS[2] = 0x53;

		  //打印前“后退”一段距离，打印后“前进”一段距离 。
		  //OFFSET 12 mm
		  //4F 46 46 53 45 54 20 31 30 20 6D 6D 
		  OFFSET = new byte[12];
		  OFFSET[0] = 0x4F;
		  OFFSET[1] = 0x46;
		  OFFSET[2] = 0x46;
		  OFFSET[3] = 0x53;
		  OFFSET[4] = 0x45;
		  OFFSET[5] = 0x54;
		  OFFSET[6] = 0x20;
		  OFFSET[7] = 0x31;
		  OFFSET[8] = 0x30;
		  OFFSET[9] = 0x20;
		  OFFSET[10] = 0x6D;
		  OFFSET[11] = 0x6D;
		  
		  //向前推送至下一张标签纸的起点开始打印
		  //HOME
		  //48 4F 4D 45 
		  HOME = new byte[4];
		  HOME[0] = 0x48;
		  HOME[1] = 0x4F;
		  HOME[2] = 0x4D;
		  HOME[3] = 0x45;
		  

		  // 到下一个标签的开始位置
		  FORMFEED = new byte[8];
	  	  FORMFEED[0] = 0x46;
	  	  FORMFEED[1] = 0x4F;
	  	  FORMFEED[2] = 0x52;
	  	  FORMFEED[3] = 0x4D;
	  	  FORMFEED[4] = 0x46;
	  	  FORMFEED[5] = 0x45;
	  	  FORMFEED[6] = 0x45;
	  	  FORMFEED[7] = 0x44;
	  	  
	  	  //SET RIBBON ON
	  	  SET_RIBBON_ON = new byte[11];
	  	  SET_RIBBON_ON[0] = 0x53;
	  	  SET_RIBBON_ON[1] = 0x45;
	  	  SET_RIBBON_ON[2] = 0x54;
	  	  SET_RIBBON_ON[3] = 0x20;
	  	  SET_RIBBON_ON[4] = 0x52;
	  	  SET_RIBBON_ON[5] = 0x49;
	  	  SET_RIBBON_ON[6] = 0x42;
	  	  SET_RIBBON_ON[7] = 0x42;
	  	  SET_RIBBON_ON[5] = 0x4F;
	  	  SET_RIBBON_ON[6] = 0x4E;
	  	  SET_RIBBON_ON[7] = 0x20;
	  	  SET_RIBBON_ON[8] = 0x4F;
	  	  SET_RIBBON_ON[9] = 0x46;
	  	  SET_RIBBON_ON[10] = 0x46;
	  	  
	  	  //DIRECTION 0
		  	DIRECTION_0= new byte[11];
		  	DIRECTION_0[0] = 0x44;
		  	DIRECTION_0[1] = 0x49;
		  	DIRECTION_0[2] = 0x52;
		  	DIRECTION_0[3] = 0x45;
		  	DIRECTION_0[4] = 0x43;
		  	DIRECTION_0[5] = 0x54;
		  	DIRECTION_0[6] = 0x49;
		  	DIRECTION_0[7] = 0x4F;
		  	DIRECTION_0[8] = 0x4E;
		  	DIRECTION_0[9] = 0x20;
		  	DIRECTION_0[10] = 0x30; 
	  	  
	  }
	  
	  /**
	    * 打印工具类
	    */
	  public PrinterUtil()  {
	}
	  
	/**
	 * 
	 * @param height  标签高度
	 * @param width   标签宽度
	 * @param gap     标签间距
	 * @param refX    打印参考坐标x
	 * @param refY    打印参考坐标y
	 * @throws Exception
	 */
	  public void initPrinter(int height,int width,int gap,int refX,int refY) throws Exception
	  {
			socket = new Socket( ip, port);
		    socketOut = socket.getOutputStream();
		    socketIn = socket.getInputStream();		  
		    this.SIZE = this.getSizeCommand(height, width);
		    this.GAP = this.getGapCommand(gap);
		    this.REFERENCE = this.getReferenceCommand(refX,refY);
		    
			socket = new Socket( ip, port);
		    socketOut = socket.getOutputStream();
		    socketIn = socket.getInputStream();
		    
			socketOut.write(this.CLS);
			socketOut.write(this.LINE);
			socketOut.flush();
			 
		    socketOut.write(this.SIZE);
			socketOut.write(this.LINE);
			socketOut.flush();
			
		    socketOut.write(this.GAP);
			socketOut.write(this.LINE);
			socketOut.flush();
			
			socketOut.write(this.DIRECTION_0);
			socketOut.write(this.LINE);
			socketOut.flush();
			
			socketOut.write(this.REFERENCE);
			socketOut.write(this.LINE);
			socketOut.flush();
			
		    writer = new OutputStreamWriter(socketOut, "GBK");
	  }
	  
	  /**
	   * 使用配置文件初始化打印机，参数在配置文件里
	   * @param propUtil
	   * @throws Exception
	   */
	  public void initPrinter(PropUtil propUtil) throws Exception
	  {
		  int height,width,gap,refX,refY;
		try{
		  height  = new Integer(propUtil.get("label_height")).intValue();
		}catch(Exception ex)
		{
			throw new Exception("pls check parameter: label_height");
		}
		
		 width  = new Integer(propUtil.get("label_width")).intValue();
		 gap  = new Integer(propUtil.get("label_gap")).intValue();
		 refX  = new Integer(propUtil.get("reference_x")).intValue();
		 refY  = new Integer(propUtil.get("reference_y")).intValue();
		
	    this.SIZE = this.getSizeCommand(height, width);
	    this.GAP = this.getGapCommand(gap);
	    this.REFERENCE = this.getReferenceCommand(refX,refY);
	    
		socket = new Socket( ip, port);
	    socketOut = socket.getOutputStream();
	    socketIn = socket.getInputStream();
	    
		socketOut.write(this.CLS);
		socketOut.write(this.LINE);
		socketOut.flush();
		 
	    socketOut.write(this.SIZE);
		socketOut.write(this.LINE);
		socketOut.flush();
		
	    socketOut.write(this.GAP);
		socketOut.write(this.LINE);
		socketOut.flush();
		
		socketOut.write(this.DIRECTION_0);
		socketOut.write(this.LINE);
		socketOut.flush();
		
		socketOut.write(this.REFERENCE);
		socketOut.write(this.LINE);
		socketOut.flush();
		 
	    writer = new OutputStreamWriter(socketOut, "GBK");	    	  
	  }
	  
	  /**
	   * 设置要打印的文本内容和文本格式
	   * @param printData
	   * @param x  position x
	   * @param y  position x
	   * @param font one of   1,2,3,4,5,6,7,8, TST24.BF2, TSS24.BF2
	   * @param rotation one of  0,90,180,270
	   * @param i  x-multiplication
	   * @param j  y-multiplication
	   */
	  public void setText(String printData,int x,int y,String font,int rotation, int i, int j) {
		    try {
		     writer.write("TEXT "+x+","+y+","+"\""+font+"\""+ ","+rotation+ "," +i+","+j+",");
		     writer.write("\""+printData+"\""+"\r\n");
		    } catch (IOException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		  }
	  
	  /**
	   * 开始打印
	   */
	  public void print()
	  {
		  try{
		  writer.write("PRINT 1"+"\r\n");
		  writer.flush();	     
		    } catch (IOException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
	  }
	  
	 
	 
	 private boolean checkStatus() {
	     try {
			socketOut.write(27);
			socketOut.write(33);
			socketOut.write(63);
			socketOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		int c=-1;
		try {
			c = (int)socketIn.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(c==0)
			return true;
		else
			return false;
	}


		/**
		 * 断开打印机连接
		 */
		public void release() {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public String getIp() {
			return ip;
		}
		
		/**
		 * 设置打印机IP
		 * @param ip
		 */
		public void setIp(String ip) {
			this.ip = ip;
		}


		public int getPort() {
			return port;
		}

		/**
		 * 设置打印机端口号
		 * @param ip
		 */
		public void setPort(int port) {
			this.port = port;
		}
		
//	public static void main(String[] args)
//	 {
//		if(args!=null && args.length>0&& args[0].equalsIgnoreCase("-v"))
//		{
//			System.out.println("0.4");
//			System.exit(-1);
//		}
//		PropUtil propUtil = new PropUtil(new File("./content.properties"));
//		if(propUtil!=null && propUtil.get("write_nfc")!=null)
//		{
//			debug("load content.properties succesfully");
//		}else
//			debug("load content.properties failed");
//		PrinterUtil printer = new PrinterUtil();
//		printer.executeWithoutPrint(propUtil);
//	 }
	
//	private static void debug(Object obj) {
//		if(DEBUG && obj!=null )
//		{
//			System.out.println("nfcprinter info: "+obj.toString());
//		}else
//			System.out.println("nfcprinter info: obj is null");		
//	}
	
	

	//SIZE 60 mm,40 mm
	private byte[] getSizeCommand(int height,int width)
	{
		StringBuffer str = new StringBuffer("SIZE ");
		str.append(width);
		str.append(" mm,");
		str.append(height);
		str.append(" mm");
		return str.toString().getBytes();
	}
	
	//GAP 3
	private byte[] getGapCommand(int gap)
	{
		StringBuffer str = new StringBuffer("GAP ");
		str.append(gap);
		str.append(" mm,");
		str.append("0");
		return str.toString().getBytes();
	}
	
	
	  //REFERENCE 0,0
	private byte[] getReferenceCommand(int x,int y)
	{
		StringBuffer str = new StringBuffer("REFERENCE ");
		str.append(x);
		str.append(",");
		str.append(y);
		return str.toString().getBytes();
	}
}

