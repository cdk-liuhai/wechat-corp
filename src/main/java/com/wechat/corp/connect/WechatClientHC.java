package com.wechat.corp.connect;

import com.wechat.corp.bean.WechatException;
import net.sf.json.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicResponseHandler;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @Author: liulh
 * @Description: 用org.apache.http实现的WechatClient。
 * @Date: 2018/3/14 16:01
 * @Modified By:
 */
public abstract class WechatClientHC implements WechatClient {

	protected HttpClient httpClient;

	public HttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public String get(String uri) throws WechatException {
		try {
			HttpGet get = new HttpGet(uri);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			return httpClient.execute(get, responseHandler);
		} catch (ClientProtocolException e) {
			throw new WechatException(e);
		} catch (IOException e) {
			throw new WechatException(e);
		}
	}

	public String post(String uri, String msg) throws WechatException {
		try {
			HttpPost post = new HttpPost(uri);
			StringEntity entity = new StringEntity(msg, "UTF-8");
			post.setEntity(entity);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			return httpClient.execute(post, responseHandler);
		} catch (UnsupportedEncodingException e) {
			throw new WechatException(e);
		} catch (ClientProtocolException e) {
			throw new WechatException(e);
		} catch (IOException e) {
			throw new WechatException(e);
		}
	}

	public String upload(String uri, File file) throws WechatException {
		try {
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.setCharset(Charset.forName("UTF-8"));
			builder.addBinaryBody("media", file);
			HttpEntity entity = builder.build();
			HttpPost post = new HttpPost(uri);
			post.addHeader("Content-Type", "multipart/form-data;");
			post.setEntity(entity);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			return httpClient.execute(post, responseHandler);
		} catch (ClientProtocolException e) {
			throw new WechatException(e);
		} catch (IOException e) {
			throw new WechatException(e);
		}
	}
	
	public String upLoad(String uri, File file) throws WechatException {
		if(!file.exists() || !file.isFile()){ 
			throw new WechatException("文件不存在！");
		}
		URL urlObj = null;
		HttpURLConnection con = null;
		JSONObject jsonObj = null;
		try {
			urlObj = new URL(uri);
			con = (HttpURLConnection) urlObj.openConnection();
			con.setRequestMethod("POST");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			//设置边界
			String BOUNDARY = "----------" + System.currentTimeMillis();
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			
			StringBuilder sb = new StringBuilder();
			sb.append("--");
			sb.append(BOUNDARY);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"media\";filename=\"" + file.getName() + "\"\r\n");
			sb.append("Content-Type:application/octet-stream\r\n\r\n");
			
			byte[] head = sb.toString().getBytes("utf-8");
			OutputStream out = new DataOutputStream(con.getOutputStream());
			out.write(head);
			
			InputStream in = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while((bytes = in.read(bufferOut)) != -1){
				out.write(bufferOut, 0, bytes);
			}
			in.close();
			
			byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("UTF-8");
			
			out.write(foot);
			out.flush();
			out.close();
			
			StringBuffer sbf = new StringBuffer();
			BufferedReader reader = null;
			String result = null;
			
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while((line = reader.readLine()) != null){
				sbf.append(line);
			}
			
			if(result == null){
				result = sbf.toString();
			}
			if(reader != null){
				reader.close();
			}
			jsonObj = JSONObject.fromObject(result);
			//System.out.println(result);
			con = (HttpURLConnection) urlObj.openConnection();
		} catch (MalformedURLException e) {
			throw new WechatException(e.getMessage());
		} catch (IOException e) {
			throw new WechatException(e.getMessage());
		}
		return jsonObj.toString();
	}

	public String download(String uri, OutputStream os) throws WechatException {
		try {
			HttpGet get = new HttpGet(uri);
			HttpResponse response = httpClient.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				String contentType = entity.getContentType().getValue();
				if (contentType.indexOf("application/json") >= 0) {
					ResponseHandler<String> responseHandler = new BasicResponseHandler();
					return responseHandler.handleResponse(response);
				}
				InputStream is = entity.getContent();
				byte[] buffer = new byte[2048];
				int ch = 0;
				while ((ch = is.read(buffer)) != -1) {
					os.write(buffer, 0, ch);
				}
				is.close();
				return null;
			} else {
				throw new WechatException(response.getStatusLine().toString());
			}
		} catch (ClientProtocolException e) {
			throw new WechatException(e);
		} catch (IOException e) {
			throw new WechatException(e);
		}
	}

	public String download(String uri, DownLoadHandler handler) throws WechatException {
		try {
			HttpGet get = new HttpGet(uri);
			HttpResponse response = httpClient.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				String contentType = entity.getContentType().getValue();
				if (contentType.indexOf("application/json") >= 0) {
					ResponseHandler<String> responseHandler = new BasicResponseHandler();
					return responseHandler.handleResponse(response);
				}
				String fileName = null;
				Header header = response.getLastHeader("Content-disposition");
				if (header != null) {
					String value = header.getValue();
					int index = value.indexOf("filename");
					if (index >= 0) {
						fileName = value.substring(index + 8);
						if (fileName.startsWith("=\"")) {
							fileName = fileName.substring(2);
						}
						index = fileName.indexOf('"');
						fileName = fileName.substring(0, index);
					}
				}
				InputStream is = entity.getContent();
				handler.handler(contentType, fileName, is);
				return null;
			} else {
				throw new WechatException(response.getStatusLine().toString());
			}
		} catch (ClientProtocolException e) {
			throw new WechatException(e);
		} catch (IOException e) {
			throw new WechatException(e);
		}
	}

}
