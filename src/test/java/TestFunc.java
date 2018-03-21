import com.wechat.corp.common.WXCorpConstants;
import org.junit.Test;
import test.Constants;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @decription 单元测试
 *
 * @author liulh
 * @create 2018-03-14 18:17
 **/
public class TestFunc {

    @Test
    public void testXml() {
        System.out.println(WXCorpConstants.getCorpElement("aialmCorp.CorpID"));
        System.out.println(WXCorpConstants.getCorpElement("asiainfoCorp.CorpID"));
        System.out.println(WXCorpConstants.getCorpElement(Constants.CORP1, Constants.CORP_ID));
    }

    @Test
    public void testFormat() {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token={0}&department_id={1}&fetch_child={2}&status={3}";
        String[] aaa = {"1","2","3","4"};
        List bbb = new ArrayList(Arrays.asList(aaa));
        bbb.remove(0);
        bbb.add(0, "0");

        String b = MessageFormat.format(url, bbb.toArray(new String[bbb.size()]));

        System.out.println(b);

//        b = MessageFormat.format(b, aaa);
//
//        System.out.println(b);
    }
}
