package test;


import com.wechat.corp.bean.NewsItem;
import com.wechat.corp.bean.WechatException;
import com.wechat.corp.cgi.CgiUtils;
import com.wechat.corp.cgi.Message;
import com.wechat.corp.common.WXCorpConstants;
import com.wechat.corp.common.TimeUtil;
import com.wechat.corp.connect.HttpClientFactory;
import com.wechat.corp.connect.WechatClientHCE;
import net.sf.json.JSONObject;
import org.apache.http.client.HttpClient;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by thisk on 2017/12/28.
 */
public class MessageSendTest {

    public static void main(String[] args) {
        try {
            WechatClientHCE wc = new WechatClientHCE(WXCorpConstants.getCorpElement(WXCorpConstants.CORP1, WXCorpConstants.CORP_ID),
                    WXCorpConstants.getCorpElement(WXCorpConstants.CORP1, WXCorpConstants.TEST_SECRET));
            HttpClient httpClient = HttpClientFactory.createHttpClient();
            wc.setHttpClient(httpClient);

            //获取当前周次
            int weekIndex = TimeUtil.getCurrentWeek();
            //获取当前周第一天
            Calendar firstDay = TimeUtil.getFirstDayOfWeek(
                    TimeUtil.getNoLineYYYYMM(new Date()).substring(0, 4), TimeUtil.getCurrentWeek() + "");
            String startDate = TimeUtil.getTimeYYYYMMDD(firstDay.getTime());
            String endDate = TimeUtil.YYYY_MM_DD.format(TimeUtil.getEndDayOfWeek(firstDay).getTimeInMillis());


            NewsItem replyNewsItem = new NewsItem();
            replyNewsItem.setTitle("【请查看】资源监控周报");
            replyNewsItem.setDescription(startDate + "~" + endDate + " 第" + weekIndex + "周 资源监控周报");
            replyNewsItem.setUrl("http://asiainfo-ch.s1.natapp.cc/wechat/weeklyController/virmonitorweekly.html?" +
                    "startDate=" + startDate + "&endDate=" + endDate + " 23:59:59" + "&weekIndex=" + weekIndex);
            replyNewsItem.setPicurl("http://asiainfo-ch.s1.natapp.cc/wechat/resources/images/report.jpg");

            NewsItem replyNewsItem1 = new NewsItem();
            replyNewsItem1.setTitle("【请查看】资源监控周报");
            replyNewsItem1.setDescription(startDate + "~" + endDate + " 第" + weekIndex + "周 资源监控周报");
            replyNewsItem1.setUrl("http://asiainfo-ch.s1.natapp.cc/wechat/weeklyController/virmonitorweekly.html?" +
                    "startDate=" + startDate + "&endDate=" + endDate + " 23:59:59" + "&weekIndex=" + weekIndex);
            replyNewsItem1.setPicurl("http://asiainfo-ch.s1.natapp.cc/wechat/resources/images/report.jpg");


            List<NewsItem> newsItems = new ArrayList<>();
            newsItems.add(replyNewsItem);
            newsItems.add(replyNewsItem1);

            JSONObject messageNews = CgiUtils.getMessageNews("LIULH", null, null,
                    WXCorpConstants.getCorpElement(WXCorpConstants.CORP1, WXCorpConstants.TEST_AGENTID), newsItems);
            System.out.println("mpnews消息：" + Message.send(messageNews, wc, wc.getAccessToken()));
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
