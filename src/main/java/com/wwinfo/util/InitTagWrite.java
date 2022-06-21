package com.wwinfo.util;

import com.uhf.api.cls.Reader;
import com.uhf.api.cls.Reader.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 新读写器接口 2022/6/17
 */

@Slf4j
public class InitTagWrite {

	/*public InitTagWrite(int h, int w) {
		super();

		Container coter = this.getContentPane();
		this.setLayout(null);

		*//*
		 * 完成初始化访问密码、销毁密码、EPC码并锁定标签的访问密码、销毁密码 和EPC区 注：在实际的应用场景中，从安全性角度出发初始化了标签的EPC区或者
		 * USER区后有可能需要将这些存储区域进行锁定，防止非授权的改写。如果 是这类应用则必须要初始化标签的访问密码为非0才可以锁定标签的其它存
		 * 储区域，如果考虑到以后还有可能需要销毁标签，则还必须初始化标签的 销毁密码。访问密码和销毁密码也需要被锁定，这些密码才不能被非授权 地读出和改写。
		 *//*
		JButton jbt1 = new JButton("初始化标签");
		jbt1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (!PreTagOp())
					return;

				String acpwd = "12345678";
				byte[] acpwdb = new byte[4];
				rdr.Str2Hex(acpwd, acpwd.length(), acpwdb);

				String klpwd = "87654321";
				byte[] klpwdb = new byte[4];
				rdr.Str2Hex(klpwd, klpwd.length(), klpwdb);

				// 初始化将要写入的EPC码数据
				byte[] wepc = new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01 };

				// 参数accesspwd设置为NULL等同于传入全0的访问密码
				READER_ERR err = rdr.WriteTagEpcEx(1, wepc, 12, null, (short) 1000);
				if (err != READER_ERR.MT_OK_ERR) {
					MessageBox("操作失败,错误码:" + err.toString());

					return;
				}

				// 参数accesspasswd设置为NULL等同于传入全0的访问密码
				// err = rdr.WriteTagData(1, (char)0, 0, klpwdb, 4, null,
				// (short) 1000);
				if (err != READER_ERR.MT_OK_ERR) {
					MessageBox("操作失败,错误码:" + err.toString());

					return;
				}
				err = rdr.WriteTagData(1, (char) 0, 2, acpwdb, 4, null, (short) 1000);
				if (err != READER_ERR.MT_OK_ERR) {
					MessageBox("操作失败,错误码:" + err.toString());

					return;
				}
				// 锁定销毁密码、访问密码和EPC区
				byte bt = (byte) (Lock_Obj.LOCK_OBJECT_KILL_PASSWORD.value()
						| Lock_Obj.LOCK_OBJECT_ACCESS_PASSWD.value() | Lock_Obj.LOCK_OBJECT_BANK1.value());
				short st = (short) (Lock_Type.KILL_PASSWORD_LOCK.value() | Lock_Type.ACCESS_PASSWD_LOCK.value()
						| Lock_Type.BANK1_LOCK.value());
				err = rdr.LockTag(1,
						(byte) (Lock_Obj.LOCK_OBJECT_KILL_PASSWORD.value() | Lock_Obj.LOCK_OBJECT_ACCESS_PASSWD.value()
								| Lock_Obj.LOCK_OBJECT_BANK1.value()),
						(short) (Lock_Type.KILL_PASSWORD_LOCK.value() | Lock_Type.ACCESS_PASSWD_LOCK.value()
								| Lock_Type.BANK1_LOCK.value()),
						acpwdb, (short) 1000);
				if (err != READER_ERR.MT_OK_ERR) {
					MessageBox("操作失败,错误码:" + err.toString());
					return;
				} else {
					// 因为在操作前设置了以当前标签为过滤条件，当操作成功后就应该将
					// 过滤条件去掉，否则会影响下次标签操作前检测标签在天线场内的数量
					rdr.ParamSet(Mtr_Param.MTR_PARAM_TAG_FILTER, null);
					MessageBox("操作成功");
				}
			}

		});

		*//*
		 * 向标签写入EPC码，此例将以16进制字符串000000000000000000000001表示 的数据写入EPC区。
		 * 但请注意：WriteTagEpcEx和WriteTagData方法都可以实现修改标签EPC区数
		 * 据，EPC区的PC段中有几个bit位是用于指明当进行盘存操作（对应Read方法） 时返回给读写器的EPC码的长度，也就是说返回的EPC码的长度是可以小于等
		 * 于实际EPC区容量的，WriteTagEpcEx方法会从块地址2开始将数据写入EPC区，
		 * 同时将PC段中指示盘存操作的EPC码长度的bit位设置为写入数据的长度，也 即WriteTagEpcEx方法写入的数据会和Read方法读出的数据完全一致。
		 * WriteTagData方法则可以修改EPC区任何可写的存储区域。
		 *//*
		JButton jbt2 = new JButton("写EPC");
		jbt2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (!PreTagOp())
					return;

				// 标签出厂时，访问密码都是全0,如果标签已经被锁定则根据实际情况设
				// 置访问密码
				String pwd = "00000000";
				byte[] pwdb = new byte[4];
				rdr.Str2Hex(pwd, pwd.length(), pwdb);

				// 初始化将要写入的EPC码数据
				byte[] wepc = new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01 };

				// 写EPC码操作，超时时间设置为1000ms，也即操作函数最长会阻塞
				// 1000ms左右，如果在超时时间以内完成操作或出现错误则会提前返回
				READER_ERR err = rdr.WriteTagEpcEx(1, wepc, 12, pwdb, (short) 1000);
				if (err != READER_ERR.MT_OK_ERR) {
					MessageBox("盘存失败,错误码:" + err.toString());

				} else {
					// 因为在操作前设置了以当前标签为过滤条件，当操作成功后就应该将
					// 过滤条件去掉，否则会影响下次标签操作前检测标签在天线场内的数量
					rdr.ParamSet(Mtr_Param.MTR_PARAM_TAG_FILTER, null);
					MessageBox("操作成功");
				}
			}

		});

		*//*
		 * 向标签存储区中写入数据，本例向USER区写入两个块的数据
		 *//*
		JButton jbt3 = new JButton("写存储区");
		jbt3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (!PreTagOp())
					return;

				// 访问密码的设置分为两种情况：
				// 1，当所操作区域被锁定时，必须设置和标签一致的访问密码操作才能成功
				// 2，当所操作区域未被锁定时，可以设置方面密码为0，或则不设置访问密码，
				// 读写器默认采用0作为访问密码
				// 标签出厂时，访问密码都是全0,如果标签已经被锁定则根据实际情况设
				// 置访问密码
				String pwd = "00000000";
				byte[] accesspwd = new byte[4];
				rdr.Str2Hex(pwd, pwd.length(), accesspwd);

				byte[] wdata = new byte[] { 0x12, 0x34, 0x56, 0x78 };
				// 写标签存储区，超时时间设置为1000ms，也即操作函数最长会阻塞
				// 1000ms左右，如果在超时时间以内完成操作或出现错误则会提前返回
				READER_ERR err = rdr.WriteTagData(1, (char) 3, 0, wdata, 4, accesspwd, (short) 1000);
				if (err != READER_ERR.MT_OK_ERR) {
					MessageBox("操作失败,错误码:" + err.toString());
					return;
				} else {
					// 因为在操作前设置了以当前标签为过滤条件，当操作成功后就应该将
					// 过滤条件去掉，否则会影响下次标签操作前检测标签在天线场内的数量
					rdr.ParamSet(Mtr_Param.MTR_PARAM_TAG_FILTER, null);
					MessageBox("操作成功");
				}
			}

		});

		*//*
		 * 读取标签存储区中的数据，本例读取USER区从地址0开始的2个块数据
		 *//*
		JButton jbt4 = new JButton("读存储区");
		jbt4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (!PreTagOp())
					return;

				String pwd = "00000000";
				byte[] accesspwd = new byte[4];
				rdr.Str2Hex(pwd, pwd.length(), accesspwd);

				// 标签出厂时，访问密码都是全0,如果标签已经被锁定则根据实际情况设
				// 置访问密码

				// 读标签存储区，超时时间设置为1000ms，也即操作函数最长会阻塞
				// 1000ms左右，如果在超时时间以内完成操作或出现错误则会提前返回
				byte[] rdata = new byte[4];
				READER_ERR err = rdr.GetTagData(1, (char) 3, 0, 2, rdata, accesspwd, (short) 1000);
				if (err != READER_ERR.MT_OK_ERR) {
					MessageBox("操作失败,错误码:" + err.toString());
					return;
				} else {
					System.out.println(rdr.bytes_Hexstr(rdata));
					// 因为在操作前设置了以当前标签为过滤条件，当操作成功后就应该将
					// 过滤条件去掉，否则会影响下次标签操作前检测标签在天线场内的数量
					rdr.ParamSet(Mtr_Param.MTR_PARAM_TAG_FILTER, null);
					MessageBox("操作成功");
				}
			}

		});

		*//*
		 * 锁定标签的存储区域，本例暂时锁定标签的EPC区 需要注意的是锁定标签存储区域前一般都需要先初始化标签的访问密码（本
		 * 例假定标签的访问密码为0x12345678）为非0,否则操作会失败。初始化标签 的访问密码可以通过WriteTagData方法进行操作。
		 *//*
		JButton jbt5 = new JButton("锁标签");
		jbt5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!PreTagOp())
					return;

				String pwd = "12345678";
				byte[] accesspwd = new byte[4];
				rdr.Str2Hex(pwd, pwd.length(), accesspwd);

				// 锁定标签的存储区，超时时间设置为1000ms，也即操作函数最长会阻塞
				// 1000ms左右，如果在超时时间以内完成操作或出现错误则会提前返回
				READER_ERR err = rdr.LockTag(1, (byte) (Lock_Obj.LOCK_OBJECT_BANK1.value()),
						(byte) (Lock_Type.BANK1_LOCK.value()), accesspwd, (short) 1000);
				if (err != READER_ERR.MT_OK_ERR) {
					MessageBox("操作失败,错误码:" + err.toString());
					return;
				} else {
					// 因为在操作前设置了以当前标签为过滤条件，当操作成功后就应该将
					// 过滤条件去掉，否则会影响下次标签操作前检测标签在天线场内的数量
					rdr.ParamSet(Mtr_Param.MTR_PARAM_TAG_FILTER, null);
					MessageBox("操作成功");
				}
			}

		});

		*//*
		 * 销毁标签，标签被销毁后就不再能使用了 需要注意的是标签只有初始化了非零的销毁密码才能执行销毁操作
		 *//*
		JButton jbt6 = new JButton("销毁标签");
		jbt6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!PreTagOp())
					return;

				String pwd = "87654321";
				byte[] killpwd = new byte[4];
				rdr.Str2Hex(pwd, pwd.length(), killpwd);
				// 销毁标签，超时时间设置为1000ms，也即操作函数最长会阻塞1000ms
				// 左右，如果在超时时间以内完成操作或出现错误则会提前返回
				READER_ERR err = rdr.KillTag(1, killpwd, (short) 1000);
				if (err != READER_ERR.MT_OK_ERR) {
					MessageBox("操作失败,错误码:" + err.toString());
					return;
				} else {
					// 因为在操作前设置了以当前标签为过滤条件，当操作成功后就应该将
					// 过滤条件去掉，否则会影响下次标签操作前检测标签在天线场内的数量
					rdr.ParamSet(Mtr_Param.MTR_PARAM_TAG_FILTER, null);
					MessageBox("操作成功");
				}
			}

		});

		coter.add(jbt1);
		coter.add(jbt2);
		coter.add(jbt3);
		coter.add(jbt4);
		coter.add(jbt5);
		coter.add(jbt6);
		int stx = 50, sty = 20, ey = 20, bx = 100, by = 25;
		jbt1.setBounds(165, 65, 100, 65);
		jbt2.setBounds(stx, sty, bx, by);
		jbt3.setBounds(stx, sty + by + ey, bx, by);
		jbt4.setBounds(stx, sty + by * 2 + ey * 2, bx, by);
		jbt5.setBounds(stx, sty + by * 3 + ey * 3, bx, by);
		jbt6.setBounds(stx, sty + by * 4 + ey * 4, bx, by);

		// this.setContentPane(coter);

		this.setBounds(0, 0, w, h);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);

		if (OpenReader() != READER_ERR.MT_OK_ERR) {
			MessageBox("初始化读写器失败,请检查设备地址以及类型，重新打开");
			return;
		}

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (rdr != null)
					rdr.CloseReader();
				System.exit(0);
			}
		});
	}*/

	static Reader rdr = null;

	static READER_ERR OpenReader() {
		// 初始化读写器实例************************************************************
		// 当使用设备的网口进行连接的时候，InitReader_Notype函数的第二个参数也可 //*
		// 能是IP地址
		int antnum = 4;
		rdr = new Reader();
		READER_ERR er = rdr.InitReader_Notype("com6", antnum);
		if (er != READER_ERR.MT_OK_ERR)
			return er;

		/* 必须要设置的参数 */

		/* 设置读写器的发射功率 */
		// 设置读写器发射功率为20dbm，对于初始化标签类应用不宜将发射功率
		// 设置太大,一般来说发射功率越大读写距离越远
		AntPowerConf apcf = rdr.new AntPowerConf();
		apcf.antcnt = antnum;
		for (int i = 0; i < apcf.antcnt; i++) {
			AntPower jaap = rdr.new AntPower();
			jaap.antid = i + 1;
			jaap.readPower = 2000;
			jaap.writePower = 2000;
			apcf.Powers[i] = jaap;
		}

		// 设置读写器发射功率,本例设置为最大发射功率，可根据实际情况调整,
		// 一般来说，功率越大则读写距离越远
		er = rdr.ParamSet(Mtr_Param.MTR_PARAM_RF_ANTPOWER, apcf);
		if (er != READER_ERR.MT_OK_ERR)
			return er;

		// 设置读写器执行GEN2空中接口协议
		Inv_Potls_ST ipst = rdr.new Inv_Potls_ST();
		ipst.potlcnt = 1;
		ipst.potls = new Inv_Potl[1];
		for (int i = 0; i < ipst.potlcnt; i++) {
			Inv_Potl ipl = rdr.new Inv_Potl();
			ipl.weight = 30;
			ipl.potl = SL_TagProtocol.SL_TAG_PROTOCOL_GEN2;
			ipst.potls[0] = ipl;
		}
		// 不用检查返回值，不会失败
		er = rdr.ParamSet(Mtr_Param.MTR_PARAM_TAG_INVPOTL, ipst);
		return er;
	}

	static void MessageBox(String mes) {
		JOptionPane.showMessageDialog(null, mes, "消息提示", JOptionPane.INFORMATION_MESSAGE);

	}

	// 用于在具体标签操作前检测是否只有一个标签在天线场内,并设置以标签的EPC码
	// 为过滤条件进行以后的标签操作
	boolean PreTagOp() {

		int[] tagcnt = new int[1];
		TAGINFO taginfo = rdr.new TAGINFO();
		READER_ERR err = rdr.TagInventory_Raw(new int[] { 1 }, 1, (short) 300, tagcnt);
		if (err != READER_ERR.MT_OK_ERR) {
			MessageBox("盘存失败,错误码:" + err.toString());
			return false;
		}
		if (tagcnt[0] != 1) {
			MessageBox("请确保天线场内有且只有一个标签");
			return false;
		}
		// 获取当前天线场内存在的唯一标签的EPC码
		err = rdr.GetNextTag(taginfo);
		if (err != READER_ERR.MT_OK_ERR) {
			MessageBox("获取标签失败,错误码:" + err.toString());
			return false;
		}
		// 设置以标签的EPC码为过滤条件进行以后的标签操作
		TagFilter_ST tfst = rdr.new TagFilter_ST();
		tfst.bank = 1;
		tfst.fdata = taginfo.EpcId;
		tfst.flen = taginfo.Epclen * 8;
		tfst.isInvert = 0;
		tfst.startaddr = 32;

		err = rdr.ParamSet(Mtr_Param.MTR_PARAM_TAG_FILTER, tfst);
		return true;
	}

	public static void readPassive(String ip, int antennaNum) {
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
			System.out.println("RFID ip: " + ip + ";天线数：" + Integer.toString(antCount) + ";读到标签数：" + tagnum[0]);
			/*if (tagnum[0] < 1)
				return;*/
			if(tagnum[0] > 0){
				for (int i = 0; i < tagnum[0]; i++) {
					try {
						TAGINFO tagInfo = devReader.new TAGINFO();
						devReader.GetNextTag(tagInfo);
						System.out.println(ip + "读到无源标签：" + hex(tagInfo.EpcId));
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

	public static String hex(byte[] bytes) {
		StringBuilder result = new StringBuilder();
		for (byte aByte : bytes) {
			result.append(String.format("%02x", aByte));
			// upper case
			// result.append(String.format("%02X", aByte));
		}
		return result.toString();
	}

	/*public static void main(String[] args) {
		System.out.println(System.getProperty("java.library.path"));
		//第一个参数填入读写器地址,如"192.168.1.100"
		readPassive("192.168.1.100", 4);
	}*/
}

