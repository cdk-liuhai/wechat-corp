package test;


import com.wechat.corp.bean.WechatException;
import com.wechat.corp.cgi.Media;
import com.wechat.corp.common.WXCorpConstants;
import com.wechat.corp.connect.DownLoadHandler;
import com.wechat.corp.connect.HttpClientFactory;
import com.wechat.corp.connect.WechatClientHCE;
import net.sf.json.JSONObject;
import org.apache.http.client.HttpClient;

import java.io.*;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;


public class MediaMaterial {

	public static void main(String[] args) throws UnsupportedEncodingException {
		try {
			WechatClientHCE wc = new WechatClientHCE(WXCorpConstants.getCorpElement(Constants.CORP1, Constants.CORP_ID),
					WXCorpConstants.getCorpElement(Constants.CORP1, Constants.MEMEBERS_SECRET));
			HttpClient httpClient = HttpClientFactory.createHttpClient();
			wc.setHttpClient(httpClient);

			DownLoadHandler handler = new DownLoadHandler() {

				@Override
				public void handler(String contentType, String fileName, InputStream is) throws WechatException {
					try {
						fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
						FileOutputStream fos = new FileOutputStream("D:\\wechat_file\\resources\\" + fileName);
						byte[] buffer = new byte[2048];
						int ch = 0;
						while ((ch = is.read(buffer)) != -1) {
							fos.write(buffer, 0, ch);
						}
						is.close();
					} catch (FileNotFoundException e) {
						throw new WechatException(e);
					} catch (IOException e) {
						throw new WechatException(e);
					}
				}
			};
			System.out.println("开始上传临时文件。");

			File f0 = new File(MediaMaterial.class.getClassLoader().getResource("demo.mp4").getFile());
			JSONObject json0 = Media.upload("video", f0, wc);
			System.out.println("视频：" + json0.toString());
			String mediaId0 = json0.getString("media_id");
			System.out.println("视频下载:" + String.valueOf(Media.get(mediaId0, handler, wc)));

			File f1 = new File(MediaMaterial.class.getClassLoader().getResource("qrcode_430.png").getFile());
			JSONObject json1 = Media.upload("image", f1, wc);
			System.out.println("图片：" + json1.toString());
			String mediaId1 = json1.getString("media_id");
			System.out.println("图片下载:" + String.valueOf(Media.get(mediaId1, handler, wc)));

			File f2 = new File(URLDecoder.decode(MediaMaterial.class.getClassLoader().getResource("消防灭火救援指挥调度系统-火警受理台用户手册.doc").getFile(),
					"utf-8"));
			JSONObject json2 = Media.upload("file", f2, wc);
			System.out.println("文件：" + json2.toString());
			String mediaId2 = json2.getString("media_id");
			System.out.println("文件下载:" + String.valueOf(Media.get(mediaId2, handler, wc)));

//			String path3 = URLDecoder.decode(MediaMaterial.class.getResource("/test/DOMA - 翱翔.mp3")
//					.getPath(), "UTF-8");
//			File f3 = new File(path3);
//			JSONObject json3 = Media.upload("voice", f3, wc);
//			System.out.println("语音：" + json3.toString());
//			String mediaId3 = json3.getString("media_id");
//			System.out.println("语音下载:" + String.valueOf(Media.get(mediaId3, handler, wc)));
//
//			System.out.println("开始上传永久素材。");
//			JSONObject json = Material.addMaterial("0", "voice", f3, wc);
//			System.out.println("上传：" + json.toString());
//			String mediaId = json.getString("media_id");
//			json = Material.get(mediaId, "0", handler, wc);
//			System.out.println("获取：" + String.valueOf(json));
//			System.out.println("获取素材总数：" + Material.getCount("0", wc).toString());
//			JSONObject msgJson = CgiUtils.getMaterialBatchget("voice", "0", 0, 10);
//			System.out.println("获取素材列表：" + Material.batchget(msgJson, wc).toString());
//			System.out.println("删除永久素材：" + Material.del(mediaId, "0", wc).toString());
		} catch (WechatException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
