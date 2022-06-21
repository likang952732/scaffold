package com.wwinfo.util;

import java.util.HashMap;

import com.uhf.api.cls.Reader;
import lombok.extern.slf4j.Slf4j;
import com.uhf.api.cls.Reader.AntPortsVSWR;
import com.uhf.api.cls.Reader.Mtr_Param;
import com.uhf.api.cls.Reader.READER_ERR;
import com.uhf.api.cls.Reader.Region_Conf;
import com.uhf.api.cls.Reader.TAGINFO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/*
 * 中科院无源RFID读取
 */
@Slf4j
@Component
public class ZKYAsynReader {

	@Autowired
	private RFIDReaderUtil rfidReaderUtil;

	private HashMap m_readerInfo = null;
	private String m_ip="";
	private int m_antennaNum = 0;
	private Reader devReader = null;
	private short m_antCount = 0;
	private int[] m_ants = null;
	private Thread m_readThread = null;
	private static boolean m_stopThread = false;
	private static ZKYAsynReader zkyAsynReader;

	@PostConstruct
	public void myInit(){
		zkyAsynReader = this;
		zkyAsynReader.rfidReaderUtil = this.rfidReaderUtil;
	}

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
					log.info("start wait for join");
					m_readThread.join();
					log.info("end wait for join");
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
			/*devReader = new Reader();
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
					log.info("与设置连接成功");
					break;
				}
			}
			m_ants = new int[m_antCount];
			for(int i=0;i<m_antCount;i++){
				m_ants[i] = antsTemp[i];
			}
        	//log.debug("start RFID read：ip=" + m_ip+";天线数："+Integer.toString(m_antCount));
        	log.info("start RFID read：ip=" + m_ip+";天线数："+Integer.toString(m_antCount));
			while(true){
				*//*TAGINFO[] tags=new TAGINFO[200];
				int[] tagnum=new int[1];
				short timeout = (short)(200*m_antCount);
				err = devReader.TagInventory(m_ants,m_antCount, timeout, tags, tagnum);
				if (err != READER_ERR.MT_OK_ERR){
		        	log.error("RFID TagInventory failed：" + err.toString()+"("+m_ip+")");
					break;
				}
				if (tagnum[0] > 0){
					log.info("RFID ip: "+m_ip+";天线数："+Integer.toString(m_antCount)
							+";读到标签数："+Integer.toString(tagnum[0]));
					for(int i=0;i<tagnum[0];i++){
						try{
							String cardNo = Reader.bytes_Hexstr(tags[i].EpcId);
							//log.debug(m_ip+"读到标签："+cardNo);
							log.info(m_ip+"读到标签："+cardNo);
							zkyAsynReader.rfidReaderUtil.settleReadCard(cardNo,false,m_readerInfo);
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
				}*//*

				//6/19
				TAGINFO[] tags = new TAGINFO[200];
				int[] tagnum = new int[1];
				short timeout = 1000; // 1秒
				// err = devReader.TagInventory(cast.connectedants, antCount, timeout, tags,
				// tagnum);
				// err = devReader.TagInventory(ants,antCount, timeout, tags, tagnum);
				// 2022年6月17日,使用新的盘点接口
				err = devReader.TagInventory_Raw(m_ants, m_antCount, timeout, tagnum);
				if (err != READER_ERR.MT_OK_ERR) {
					System.out.println("RFID TagInventory failed：" + err.toString() + "(" + m_ip + ")");
					return;
				}
			*//*if (tagnum[0] < 1)
				return;*//*
				if(tagnum[0] > 0){
					System.out.println("RFID ip: " + m_ip + ";天线数：" + Integer.toString(m_antCount) + ";读到标签数：" + tagnum[0]);
					for (int i = 0; i < tagnum[0]; i++) {
						try {
							TAGINFO tagInfo = devReader.new TAGINFO();
							devReader.GetNextTag(tagInfo);
							String cardNo = hex(tagInfo.EpcId);
							System.out.println(m_ip + "读到无源标签：" + cardNo);
							zkyAsynReader.rfidReaderUtil.settleReadCard(cardNo,false,m_readerInfo);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				try{
					Thread.sleep(500);
				}catch(Exception e){
					e.printStackTrace();
				}
			}*/

			readPassive(m_ip, m_antennaNum);
		}
	}

	public static String hex(byte[] bytes) {
		StringBuilder result = new StringBuilder();
		for (byte aByte : bytes) {
			result.append(String.format("%02x", aByte));
			// upper case
			// result.append(String.format("%02X", aByte));
		}
		return result.toString();
	}

	private void readPassive(String ip, int antennaNum) {
		Reader devReader = new Reader();
		try {
			if (devReader.InitReader_Notype(ip, antennaNum) != READER_ERR.MT_OK_ERR) {
				System.out.println("RFID InitReader failed：" + ip);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		int antCount = 0;
		READER_ERR err;
		/*
		 * //检测连接的天线 ConnAnts_ST cast=devReader.new ConnAnts_ST();
		 * err=devReader.ParamGet(Mtr_Param.MTR_PARAM_READER_CONN_ANTS, cast); if (err
		 * != READER_ERR.MT_OK_ERR){ System.out.println("RFID ParamGet failed：" +
		 * err.toString()+"("+ip+")"); return; } if (cast.antcnt < 1){
		 * System.out.println("RFID ParamGet antcnt is zero!"); return; } antCount =
		 * cast.antcnt;
		 */
		// 改成新的天线测试方法
		int[] antsTemp = new int[4];
		for (int i = 1; i <= 4; i++) {
			AntPortsVSWR apvr = devReader.new AntPortsVSWR();
			apvr.andid = i;
			apvr.power = (short) 3000;
			apvr.region = Region_Conf.RG_NA;
			// 定频
			apvr.frecount = 1;
			apvr.vswrs[0].frequency = 915250;
			err = devReader.ParamGet(Mtr_Param.MTR_PARAM_RF_ANTPORTS_VSWR, apvr);
			// antID1234即对应物理天线口1234，当获取的vswr小于10时即可认为天线口有天线连接

			System.out.println(
					" antID = " + i + " vswr= " + apvr.vswrs[0].vswr + " frequency= " + apvr.vswrs[0].frequency);
			if (err != READER_ERR.MT_OK_ERR) {
				System.out.println("RFID ParamGet for andid=" + Integer.toString(i) + " failed：" + err.toString() + "("
						+ ip + ")");
			} else {
				if(apvr.vswrs[0].vswr <10) {
					antsTemp[antCount] = i;
					antCount++;
				}

			}
		}
		if (antCount < 1) {
			System.out.println("RFID ParamGet antCount is zero!");
			return;
		}
		int[] ants = new int[antCount];
		for (int i = 0; i < antCount; i++) {
			ants[i] = antsTemp[i];
		}

		// 不检测天线
		/*
		 * int[] val=new int[1]; val[0]=0;
		 * err=devReader.ParamSet(Mtr_Param.MTR_PARAM_READER_IS_CHK_ANT, val); if (err
		 * != READER_ERR.MT_OK_ERR){ System.out.println("RFID ParamSet failed：" +
		 * err.toString()+"("+ip+")"); return; }
		 *
		 *
		 * int[] ants=new int[antennaNum]; for(int i=0;i<antennaNum;i++){ ants[i] = i+1;
		 * }
		 */
		while (true){
			TAGINFO[] tags = new TAGINFO[200];
			int[] tagnum = new int[1];
			short timeout = 1000; // 1秒
			// err = devReader.TagInventory(cast.connectedants, antCount, timeout, tags,
			// tagnum);
			// err = devReader.TagInventory(ants,antCount, timeout, tags, tagnum);
			// 2022年6月17日,使用新的盘点接口
			err = devReader.TagInventory_Raw(ants, antCount, timeout, tagnum);
			if (err != READER_ERR.MT_OK_ERR) {
				System.out.println("RFID TagInventory failed：" + err.toString() + "(" + ip + ")");
				return;
			}
			if(tagnum[0] > 0){
				System.out.println("RFID ip: " + ip + ";天线数：" + Integer.toString(antCount) + ";读到标签数：" + tagnum[0]);
				for (int i = 0; i < tagnum[0]; i++) {
					try {
						TAGINFO tagInfo = devReader.new TAGINFO();
						devReader.GetNextTag(tagInfo);
						String cardNo = hex(tagInfo.EpcId);
						System.out.println(ip + "读到无源标签：" + cardNo);
						zkyAsynReader.rfidReaderUtil.settleReadCard(cardNo,false ,m_readerInfo);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			try{
				Thread.sleep(500);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//devReader.CloseReader();
	}

}
