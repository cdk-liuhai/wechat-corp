package com.wechat.corp.connect;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * @Author: liulh
 * @Description: 创建http请求连接
 * @Date: 2018/3/14 16:00
 * @Modified By:
 */
public abstract class HttpClientFactory {

	public static Integer maxTotal = 10;

	public static Integer socketTimeout = 10000;

	public static Integer connectTimeout = 10000;

	public static Integer connectionRequestTimeout = 10000;

	public static HttpClient createHttpClient() throws KeyManagementException, NoSuchAlgorithmException {
		SSLContext sslcontext = SSLContexts.custom().build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext);
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(maxTotal);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
				.setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionRequestTimeout).build();
		ArrayList<Header> headers = new ArrayList<>();
		Header header = new BasicHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		headers.add(header);
		HttpClientBuilder builder = HttpClients.custom();
		builder.setSSLSocketFactory(sslsf);
		builder.setConnectionManager(connectionManager);
		builder.setDefaultRequestConfig(requestConfig);
		builder.setDefaultHeaders(headers);
		HttpClient httpClient = builder.build();
		return httpClient;
	}

}
