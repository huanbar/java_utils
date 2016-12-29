package com.yunxiaotian.common.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 基础工具类
 * 
 * @ClassName: ApplicationContextProvider
 * @Description: 获取配置文件配置,spring bean,以及主机信息
 * @author zhou
 * @date 2016-12-18 14:44:35
 *
 */
public class ApplicationContextProvider implements ApplicationContextAware {
	private static Logger logger = LoggerFactory.getLogger(ApplicationContextProvider.class);
	private static ApplicationContext ctx;
	private static Properties props = null;
	private static Long modifiedTime = null;
	private static long lastCheckTime;
	private static String hostName;
	private static String hostIps = "";

	static {
		try {
			InetAddress ip = InetAddress.getLocalHost();
			hostName = ip.getHostName();
		} catch (Exception eh) {
			logger.warn("can't get host name", eh);
		}
	}

	static {
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
				while (inetAddresses.hasMoreElements()) {
					InetAddress ip = inetAddresses.nextElement();
					if (ip instanceof Inet4Address) {
						if (hostIps.length() == 0) {
							hostIps = ip.getHostAddress();
							continue;
						}
						hostIps += "," + ip.getHostAddress();
					}
				}
			}
		} catch (Exception ep) {
			logger.warn("can't get host ip", ep);
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		if (ApplicationContextProvider.ctx == null) {
			ApplicationContextProvider.ctx = ctx;
		}

	}

	public static String getHostName() {
		return hostName;
	}

	public static String getlocalIps() {
		return hostIps;
	}

}
