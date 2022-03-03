package com.wwinfo.util;

import java.util.HashMap;

import lombok.extern.slf4j.Slf4j;
import com.uhf.api.cls.Reader;
import com.uhf.api.cls.Reader.AntPortsVSWR;
import com.uhf.api.cls.Reader.Mtr_Param;
import com.uhf.api.cls.Reader.READER_ERR;
import com.uhf.api.cls.Reader.Region_Conf;
import com.uhf.api.cls.Reader.TAGINFO;

/*
 * 中科院无源RFID读取
 */
@Slf4j
public class ZKYAsynReader {
	private HashMap m_readerInfo = null;
	private String m_ip="";
	private int m_antennaNum = 0;
	private Reader devReader = null;
	//private Logger logger = Logger.getLogger(ZKYAsynReader.class.getName());
	private short m_antCount = 0;
	private int[] m_ants = null;
	private Thread m_readThread = null;
	private static boolean m_stopThread = false;

	public boolean init(HashMap readerInfo,String ip,int antennaNum){
		m_readerInfo = readerInfo;
		m_ip = ip;
		m_antennaNum = antennaNum;
		devReader = null;
		return true;
	}

	public boolean StartReading(){
		if (null == m_ip || m_ip.isEmpty() || m_antennaNum < 1){
			return false;
		}
		StopReading();
		ReadRun readRun = new ReadRun();
		m_stopThread = false;
		m_readThread = new Thread(readRun,"RFIDreader"+m_ip);
		m_readThread.start();
		return true;
	}

	public void StopReading(){
		if (null != m_readThread){
			m_stopThread = true;
			if (m_readThread.isAlive()){
				try{
					System.out.println("start wait for join");
					m_readThread.join();
					System.out.println("end wait for join");
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			m_readThread = null;
		}
		if (null != devReader){
			devReader.CloseReader();
		}
	}
	
	private class ReadRun implements Runnable {
		@Override
		public void run(){
			//启动线程异步读取
			devReader = new Reader();
			READER_ERR err;
			int[] antsTemp = new int[4];
			while(true){
				if (m_stopThread){
					return;
				}
				try{
					if (devReader.InitReader_Notype(m_ip, m_antennaNum) != READER_ERR.MT_OK_ERR) {
			        	log.error("RFID InitReader failed：" + m_ip);
						Thread.sleep(3000);	//与设置连接不成功，3秒后重试
						continue;
					}
				}catch(Exception e){
					e.printStackTrace();
					return;
				}
				m_antCount = 0;
				//新的天线测试方法
			    for (int i = 1; i <= 4; i++) {
			    	AntPortsVSWR apvr=devReader.new AntPortsVSWR();
			    	apvr.andid=i;
			    	apvr.power=(short) 3000;
			    	apvr.region=Region_Conf.RG_NA;
			    	//定频
			    	apvr.frecount = 1;
			    	apvr.vswrs[0].frequency = 915250;
			    	err=devReader.ParamGet(Mtr_Param.MTR_PARAM_RF_ANTPORTS_VSWR, apvr);
			    	//antID1234即对应物理天线口1234，当获取的vswr小于10时即可认为天线口有天线连接
			    	//System.out.println(" antID = "+i+" vswr= "+apvr.vswrs[0].vswr+" frequency= "+apvr.vswrs[0].frequency);
					if (err != READER_ERR.MT_OK_ERR){
			        	log.error("RFID ParamGet for andid="+Integer.toString(i)+" failed：" + err.toString()+"("+m_ip+")");
					}else if (apvr.vswrs[0].vswr < 10){
						antsTemp[m_antCount] = i;
						m_antCount++;
					}
			    }
				if (m_antCount < 1){
		        	log.error("RFID(ip="+m_ip+") ParamGet antCount is zero!");
		        	try{
		        		Thread.sleep(30000);	//与设置连接不成功，30秒后重试
		        	}catch(Exception e){
		        		e.printStackTrace();
		        	}
				}else{
					break;
				}
			}
			m_ants = new int[m_antCount];
			for(int i=0;i<m_antCount;i++){
				m_ants[i] = antsTemp[i];
			}
        	log.debug("start RFID read：ip=" + m_ip+";天线数："+Integer.toString(m_antCount));
			while(true){
				TAGINFO[] tags=new TAGINFO[200];
				int[] tagnum=new int[1];
				short timeout = (short)(200*m_antCount);
				err = devReader.TagInventory(m_ants,m_antCount, timeout, tags, tagnum);
				if (err != READER_ERR.MT_OK_ERR){
		        	log.error("RFID TagInventory failed：" + err.toString()+"("+m_ip+")");
					break;
				}
				if (tagnum[0] > 0){
					log.debug("RFID ip: "+m_ip+";天线数："+Integer.toString(m_antCount)
							+";读到标签数："+Integer.toString(tagnum[0]));
					for(int i=0;i<tagnum[0];i++){
						try{
							String cardNo = Reader.bytes_Hexstr(tags[i].EpcId); 
							log.debug(m_ip+"读到标签："+cardNo);
							RFIDReader.settleReadCard(cardNo,false,m_readerInfo);
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
				if (m_stopThread){
					break;
				}
				try{
					Thread.sleep(500);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
