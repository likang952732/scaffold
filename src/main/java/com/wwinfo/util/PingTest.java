package com.wwinfo.util;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.wwinfo.model.Alarm;

public class PingTest {
	//private Logger logger = Logger.getLogger(PingTest.class.getName());
	//private DBBusiness db = new DBBusiness();
	private static boolean isWindows = true;
	
	static{
		String strOS = System.getProperty("os.name");
		if (strOS.indexOf("Windows")>=0){
			isWindows = true;
		}else
			isWindows = false;				
	}
	/*
	 * 定时器执行函数
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	public void funTimer(String name){
		/*
		boolean ret = Global.sendEmail("在线报警","测试内容",
				"smtp.exmail.qq.com","465","luzhongwei@wwinfo.com.cn","support@wwinfo.com.cn",
				"luzhongwei@wwinfo.com.cn","6yaxBUBSAVJBVFZk");
		return;
		*/
		//读取所有RFID探测器
		/*List<HashMap<String,Object>> readerList = db.getTableData("rfidreader", null, null);
		//读取所有摄像头
		List<HashMap<String,Object>> cameraList = db.getTableData("camera", null, null);*/

		List<HashMap<String,Object>> readerList = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if (null != readerList){
			for(HashMap<String,Object> item: readerList){
				String ip = (String)item.get("readerIP");
				if (null != ip && !ip.isEmpty()){
					boolean pingResult = ping(ip);
					if (!pingResult){
						for(int i=0;i<3;i++){
							try{
								Thread.sleep(3000);
							}catch(Exception e){
								e.printStackTrace();
							}
							pingResult = ping(ip);
							if (pingResult)
								break;
						}
					}
					int lastStatus = Integer.parseInt(item.get("lastStatus").toString());
					HashMap hmUpdate = new HashMap();
					hmUpdate.put("readerName", item.get("readerName"));
					hmUpdate.put("checkTime", sdf.format(new Date()));
					hmUpdate.put("lastStatus", pingResult?"0":"1");
					//db.updateTableData("rfidreader", hmUpdate);
					if (!pingResult && lastStatus==0 && Integer.valueOf(item.get("isAlarm").toString())==1){
						//断线报警
						StringBuilder content = new StringBuilder();
						content.append("RFID探测器");
						content.append(item.get("readerName"));
						content.append("(IP地址为");
						content.append(ip);
						content.append(")在线检测断开");
						//Alarm.addAlarm(content.toString(), 0, "RFID探测器断线", null, null);
					}
				}
			}
		}
		/*if (null != cameraList){
			for(HashMap<String,Object> item: cameraList){
				String ip = (String)item.get("cameraIP");
				if (null != ip && !ip.isEmpty()){
					boolean pingResult = ping(ip);
					int lastStatus = Integer.parseInt(item.get("lastStatus").toString());
					HashMap hmUpdate = new HashMap();
					hmUpdate.put("cameraName", item.get("cameraName"));
					hmUpdate.put("checkTime", sdf.format(new Date()));
					hmUpdate.put("lastStatus", pingResult?"0":"1");
					db.updateTableData("camera", hmUpdate);
					if (!pingResult && lastStatus==0 && Integer.valueOf(item.get("isAlarm").toString())==1){
						//断线报警
						StringBuilder content = new StringBuilder();
						content.append("摄像头");
						content.append(item.get("cameraName"));
						content.append("(IP地址为");
						content.append(ip);
						content.append(")在线检测断开");
						Alarm.addAlarm(content.toString(), 0, "摄像头断线", null, null);
					}
				}
			}
		}*/
	}
	
	/*
	 * 通过PING检测IP地址是否通
	 */
	private boolean ping(String ip){
		String cmd = "ping -n 1 ";
		if (!isWindows){
			cmd = "ping -c 1 ";
		}
		cmd += ip;
		boolean ret = false;
		try{
			Process p1 = Runtime.getRuntime().exec(cmd);
		    int returnVal = p1.waitFor();
		    if(returnVal==0){
		    	InputStream is = p1.getInputStream();
		    	byte[] bufferTemp=new byte[2048];
		    	is.read(bufferTemp);
		    	String str = new String(bufferTemp,"GBK");
		    	if (str.indexOf("请求超时")>=0 || str.indexOf("无法访问目标主机")>=0 ||
		    			str.indexOf("Request timeout")>=0 ||
		    			str.indexOf("could not find host")>=0){
		    		ret = false;
		    	}else{
		    		ret = true;
		    	}
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
	    return ret;
	}
}
