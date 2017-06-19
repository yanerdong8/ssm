package com.yed.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 订单号生成器
 * 
 * @author GUOJUN
 * 
 */
public class OrderNoGenerator {

	private static int ID_LEN = 9;
	private static OrderNoGenerator generator = new OrderNoGenerator();
	private static String mac = "";
	
	static {
		mac = getMAC();
	}

	/**
	 * private and only constructor
	 */
	private OrderNoGenerator() {
		mac = getMAC();
	}

	/**
	 * @return Generator
	 */
	public static OrderNoGenerator getInstance() {
		if (generator == null)
			generator = new OrderNoGenerator();
		return generator;
	}

	// 获取mac地址
	private static String getMAC() {
		byte[] mac;
		StringBuffer sb = new StringBuffer();
		try {
			mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
			for (int i = 1; i < mac.length; i++) {
				int temp = mac[i] & 0xff;
				String str = Integer.toHexString(temp);
				if (str.length() == 1) {
					sb.append("0" + str);
				} else {
					sb.append(str);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "ABCDEF";
		}

		return sb.toString().toUpperCase();
	}

	/**
	 * @return the ID （10000以内不重复）
	 */
	public String getID() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
		StringBuilder sb = new StringBuilder();
		int root = (int) Math.pow(10, ID_LEN);
		int id = 0;
		do {
			long tmp = Math.abs(Double.doubleToLongBits(Math.random()));
			id = (int) (tmp % root);
		} while (id < (root / 10));
		sb.append(sdf.format(now));
		sb.append(mac);
		sb.append(id);

		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		OrderNoGenerator generator = OrderNoGenerator.getInstance();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10000; i++) {
			System.out.println(generator.getID());
			if(list.contains(generator.getID())){
				System.out.println("碰撞");
			}
			list.add(generator.getID());
		}

	}
}
