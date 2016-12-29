package com.yunxiaotian.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**

* http请求封装类,支持http、https协议,get、post方法

* @author huanbar

* @Date 2016-12-29

*

*/
public class HttpUtil {
	public static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	public final static String ENCODING_DEFAULT = "UTF-8";
	public final static String METHOD_POST = "POST";
	public final static String METHOD_GET = "GET";

	/**
	 * 发起http请求并获取结果
	 * 
	 * @param url
	 *            请求地址
	 * @return String
	 * @throws MalformedURLException
	 */
	public static String request(String url) throws MalformedURLException {
		return request(new URL(url), "METHOD_GET", null);
	}

	/**
	 * 发起http请求并获取结果
	 * 
	 * @param url
	 *            请求地址
	 * @param outputStr
	 *            提交的数据
	 * @return String
	 * @throws MalformedURLException
	 */
	public static String request(String url, String outputStr) throws MalformedURLException {
		return request(new URL(url), "METHOD_POST", outputStr);
	}

	/**
	 * 发起http请求并获取结果
	 * 
	 * @param reqUrl
	 *            请求地址
	 * @param method
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return String
	 */
	public static String request(URL url, String method, String outputStr) {
		final StringBuilder stringBuffer = new StringBuilder(255);
		URLConnection conn = null;
		try {
			conn = url.openConnection();

			if (conn instanceof HttpsURLConnection) {
				// 不验证ip
				((HttpsURLConnection) conn).setHostnameVerifier(new HostnameVerifier() {
					@Override
					public boolean verify(String arg0, SSLSession arg1) {
						return true;
					}
				});
				// 信任证书
				TrustManager[] tm = { new X509TrustManager() {
					public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					}

					public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					}

					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}

				} };
				SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
				sslContext.init(null, tm, new java.security.SecureRandom());
				// 从SSLContext对象中得到SSLSocketFactory对象
				SSLSocketFactory ssf = sslContext.getSocketFactory();

				((HttpsURLConnection) conn).setSSLSocketFactory(ssf);

			}
			if (method != null && METHOD_POST.equals(method.toUpperCase())) {
				((HttpURLConnection) conn).setRequestMethod("POST");
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.setUseCaches(false);
				conn.setRequestProperty("Connection: ", "Keep-Alive");
				if (outputStr != null) {
					conn.setRequestProperty("Content-Length", String.valueOf(outputStr.getBytes(ENCODING_DEFAULT).length));
					OutputStream outputStream = ((HttpURLConnection) conn).getOutputStream();
					outputStream.write(outputStr.getBytes(ENCODING_DEFAULT));
					outputStream.close();
				}

				throw new RuntimeException();
			}

			conn.setRequestProperty("User-Agent: ", "Huanbar/1.0");

			final BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), ENCODING_DEFAULT));

			String line;

			while ((line = in.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}

		} catch (ConnectException ce) {
			logger.error("The Web Service  connection timed out.");
		} catch (Exception e) {
			logger.error("The Web Service http  request error: " + e.getMessage());
		} finally {
			if (conn != null && conn instanceof HttpURLConnection) {
				((HttpURLConnection) conn).disconnect();
			}
		}
		return stringBuffer.toString();
	}

	
}
