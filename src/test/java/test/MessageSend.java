package test;


import com.wechat.corp.bean.MpnewsItem;
import com.wechat.corp.bean.NewsItem;
import com.wechat.corp.bean.WechatException;
import com.wechat.corp.cgi.CgiUtils;
import com.wechat.corp.cgi.Media;
import com.wechat.corp.cgi.Message;
import com.wechat.corp.common.Constants;
import com.wechat.corp.connect.HttpClientFactory;
import com.wechat.corp.connect.WechatClientHCE;
import net.sf.json.JSONObject;
import org.apache.http.client.HttpClient;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class MessageSend {

	public static void main(String[] args) {
		try {
			/*
			 * 第一个应用
			 */
			WechatClientHCE wc = new WechatClientHCE(Constants.getCorpElement(Constants.CORP1, Constants.CORP_ID),
					Constants.getCorpElement(Constants.CORP1, Constants.TEST_SECRET));
			HttpClient httpClient = HttpClientFactory.createHttpClient();
			wc.setHttpClient(httpClient);

			File f2 = new File(MediaMaterial.class.getClassLoader().getResource("demo.mp4").getFile());
			JSONObject videoJson2 = Media.upload("video", f2, wc);
			System.out.println("视频2：" + videoJson2.toString());
			String mediaId2 = videoJson2.getString("media_id");

			File f3 = new File(URLDecoder.decode(MediaMaterial.class.getClassLoader().getResource("基于BS架构的服务器主动推送技术.doc").getFile(), "utf-8"));
			JSONObject fileJson3 = Media.upload("file", f3, wc);
			System.out.println("文件3：" + fileJson3.toString());
			String mediaId3 = fileJson3.getString("media_id");

//---------------------------------------------------------------------------------------------------------

			System.out.println("发送video消息。");
			JSONObject json3 = CgiUtils.getMessageVideo("LIULH", null, null,
					Constants.getCorpElement(Constants.CORP1, Constants.TEST_AGENTID), mediaId2, "video消息", "这是一条测试视频消息！", 0);
			System.out.println("video消息：" + Message.send(json3, wc, wc.getAccessToken()));

//---------------------------------------------------------------------------------------------------------

			System.out.println("发送file消息。");
			JSONObject json4 = CgiUtils.getMessageFile("LIULH", null, null,
					Constants.getCorpElement(Constants.CORP1, Constants.TEST_AGENTID), mediaId3, 0);
			System.out.println("file消息：" + Message.send(json4, wc, wc.getAccessToken()));

//---------------------------------------------------------------------------------------------------------

			System.out.println("发送text消息。");
			JSONObject json0 = CgiUtils.getMessageText("LIULH", null, null,
					Constants.getCorpElement(Constants.CORP1, Constants.TEST_AGENTID), "text消息，这是一条测试消息！", 0);
			System.out.println("text消息：" + Message.send(json0, wc, wc.getAccessToken()));

////////////////////////////////////////////////////////////////////////////////////////////////////////////

			/*
			 * 第二个应用
			 */
			wc.setCorpId(Constants.getCorpElement(Constants.CORP2, Constants.CORP_ID));
			wc.setCorpSecret(Constants.getCorpElement(Constants.CORP2, Constants.TEST_SECRET));

			System.out.println("上传临时素材。");
			File f0 = new File(MediaMaterial.class.getClassLoader().getResource("1.jpg").getFile());
			JSONObject imgJson0 = Media.upload("image", f0, wc);
			System.out.println("图片0：" + imgJson0.toString());
			String mediaId0 = imgJson0.getString("media_id");

//---------------------------------------------------------------------------------------------------------

			System.out.println("发送image消息。");
			JSONObject json1 = CgiUtils.getMessageImage("llh", null, null,
					Constants.getCorpElement(Constants.CORP2, Constants.TEST_AGENTID), mediaId0, 0);
			System.out.println("image消息：" + Message.send(json1, wc, wc.getAccessToken()));

//---------------------------------------------------------------------------------------------------------

			System.out.println("发送news消息。");
			NewsItem newsItem0 = new NewsItem();
			newsItem0.setTitle("小秘书感谢移动君的大力支持");
			newsItem0
					.setDescription("#阅兵保通信#阅兵现场观礼的小伙伴抓拍阅兵式共享文字、照片和视频十分踊跃，截至11时，观礼台核心区域4G流量中微信流量较日常增长18倍，微博流量较日常增长22倍。群众的力量果然是无穷的，咱们的网络保障也是杠杠的啊……");
			newsItem0.setUrl("http://weibo.com/1642909335/Cz70TyxWp");
			newsItem0.setPicurl("http://ww2.sinaimg.cn/bmiddle/774e69f9jw1evp6a7dx5mj20zk0qo776.jpg");

			NewsItem newsItem1 = new NewsItem();
			newsItem1.setTitle("微博看阅兵！同样的阅兵，不同的视角，为祖国点赞");
			newsItem1
					.setDescription("同样的阅兵，不同的视角！@央视新闻 的#9.3胜利日大阅兵#，@解放军报-天安门阅兵 的#9.3天安门阅兵#，@中国之声 的#微看阅兵#等媒体微博多角度独家直播胜利日大阅兵。身临其境的零距离不容错过。微博看阅兵，为祖国点赞！");
			newsItem1.setUrl("http://weibo.com/1642909335/CyMJ1wKsq");
			newsItem1.setPicurl("http://ww3.sinaimg.cn/bmiddle/90eb2137jw1evoyhcky0fj20hs0vk418.jpg");

			NewsItem newsItem2 = new NewsItem();
			newsItem2.setTitle("解读：“新经济”首次写入政府工作报告");
			newsItem2
					.setDescription("新京报快讯(记者沙璐)今天上午，国务院总理李克强向大会作政府工作报告。李克强提到，当前我国发展正处于这样一个关键时期，必须培育壮大新动能，加快发展新经济。新京报记者发现，这是“新经济”首次写入政府工作报告。");
			newsItem2.setUrl("http://news.qq.com/a/20160305/023900.htm");
			newsItem2.setPicurl("http://vpic.video.qq.com/4210358460/a0019i7a92d_ori_1.jpg");

			ArrayList<NewsItem> newsItems = new ArrayList<NewsItem>();
			newsItems.add(newsItem0);
			newsItems.add(newsItem1);
			newsItems.add(newsItem2);

			JSONObject json5 = CgiUtils.getMessageNews("llh", null, null,
					Constants.getCorpElement(Constants.CORP2, Constants.TEST_AGENTID), newsItems);
			System.out.println("news消息：" + Message.send(json5, wc, wc.getAccessToken()));

//---------------------------------------------------------------------------------------------------------
			
			System.out.println("发送mpnews消息。");
			MpnewsItem mpnewsItem0 = new MpnewsItem();
			mpnewsItem0.setTitle("测试mpnews消息1");
			mpnewsItem0.setThumbMediaId(mediaId0);
			mpnewsItem0.setAuthor("刘龙海");
			mpnewsItem0.setContentSourceUrl("http://weibo.com/1642909335/CyMJ1wKsq");
			mpnewsItem0
					.setContent("<p style=\"TEXT-INDENT: 2em;color:blue;\">【习近平：主人翁的地位要体现出来】习近平参加上海团审议时，上海电气液压气动公司总工艺师李斌向总书记汇报了加强技工队伍建设的思考和建议。习近平讲话中强调说，我们要想办法调动一线工人、制造业工人、农民工的积极性，这也是社会主义的本质要求。工人阶级是主人翁，主人翁的地位要体现出来。</p>");
			mpnewsItem0.setDigest("这是图文消息测试1。");
			mpnewsItem0.setShowCoverPic("1");

			MpnewsItem mpnewsItem1 = new MpnewsItem();
			mpnewsItem1.setTitle("测试mpnews消息2");
			mpnewsItem1.setThumbMediaId(mediaId0);
			mpnewsItem1.setAuthor("刘龙海");
			mpnewsItem1.setContentSourceUrl("http://weibo.com/1642909335/CyMJ1wKsq");
			mpnewsItem1
					.setContent("<p style=\"TEXT-INDENT: 2em;color:yellow;\">【习近平在上海团谈引进和集聚科技人才】金东寒代表是中国工程院院士，现任上海大学校长。主持人介绍说，他去年出席大会时还是中船重工711所所长，后作为人才引进到了上海大学。习近平说，要以更加开放的视野引进和集聚人才，加快集聚一批站在行业科技前沿、具有国际视野的领军人才。</p>");
			mpnewsItem1.setDigest("这是图文消息测试2。");
			mpnewsItem1.setShowCoverPic("1");

			MpnewsItem mpnewsItem2 = new MpnewsItem();
			mpnewsItem2.setTitle("测试mpnews消息3");
			mpnewsItem2.setThumbMediaId(mediaId0);
			mpnewsItem2.setAuthor("刘龙海");
			mpnewsItem2.setContentSourceUrl("http://weibo.com/1642909335/CyMJ1wKsq");
			mpnewsItem2
					.setContent("<p style=\"TEXT-INDENT: 2em;color:red;\">【习近平在上海团谈引进和集聚科技人才】金东寒代表是中国工程院院士，现任上海大学校长。主持人介绍说，他去年出席大会时还是中船重工711所所长，后作为人才引进到了上海大学。习近平说，要以更加开放的视野引进和集聚人才，加快集聚一批站在行业科技前沿、具有国际视野的领军人才。</p>");
			mpnewsItem2.setDigest("这是图文消息测试3。");
			mpnewsItem2.setShowCoverPic("1");

			ArrayList<MpnewsItem> mpnewsItems = new ArrayList<MpnewsItem>();
			mpnewsItems.add(mpnewsItem0);
			mpnewsItems.add(mpnewsItem1);
			mpnewsItems.add(mpnewsItem2);

			JSONObject json6 = CgiUtils.getMessageMpnews("llh", null, null,
					Constants.getCorpElement(Constants.CORP2, Constants.TEST_AGENTID), mpnewsItems, 0);
			System.out.println("mpnews消息：" + Message.send(json6, wc, wc.getAccessToken()));
		} catch (WechatException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

}
