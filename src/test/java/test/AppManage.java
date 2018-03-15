/*
 * Company：      Asiainfo Technology Co., Ltd.
 * 
 * @author:   Liulh   
 * 
 * @Createdate:2017年9月1日 下午2:26:02  
 *
 * Copyright: Copyright(C) 2016,2999  All rights Reserved, Designed By Asiainfo 
 * License   
 * The original version of this source code and documentation is copyrighted
 * and owned by Asiainfo Technology Co., Ltd., a wholly-owned subsidiary of Asiainfo. 
 * These materials are provided under terms of a License Agreement Asiainfo. 
 * This notice and attribution to Asiainfo  may not be removed.
 * Asiainfo is a registered trademark of Asiainfo Technology Co., Ltd. 
 */
package test;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.cgi.Agent;
import com.wechat.corp.cgi.CgiUtils;
import com.wechat.corp.common.WXCorpConstants;
import com.wechat.corp.connect.HttpClientFactory;
import com.wechat.corp.connect.WechatClientHCE;
import net.sf.json.JSONObject;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class AppManage {
	
	public static void main(String[] args) {
		String corpid1 = WXCorpConstants.getCorpElement(WXCorpConstants.CORP1, WXCorpConstants.CORP_ID);
		String corpid2 = WXCorpConstants.getCorpElement(WXCorpConstants.CORP2, WXCorpConstants.CORP_ID);

		String testAssistantSecret = WXCorpConstants.getCorpElement(WXCorpConstants.CORP1, WXCorpConstants.TEST_SECRET);
		String testAssistantAgentId = WXCorpConstants.getCorpElement(WXCorpConstants.CORP1, WXCorpConstants.TEST_AGENTID);
		
		String testSecret = WXCorpConstants.getCorpElement(WXCorpConstants.CORP2, WXCorpConstants.TEST_SECRET);
		String testAgentId = WXCorpConstants.getCorpElement(WXCorpConstants.CORP2, WXCorpConstants.TEST_AGENTID);
		try {
			WechatClientHCE wc = new WechatClientHCE(corpid2, testSecret);

			wc.setHttpClient(HttpClientFactory.createHttpClient());

			//获取企业应用
			JSONObject jsonObject1 = Agent.get(testAgentId, wc);
			System.out.println(jsonObject1);
			System.out.println();

			//获取企业应用列表
			JSONObject jsonObject11 = Agent.list(wc);
			System.out.println(jsonObject11);
			System.out.println();
			
			/*//获取企业应用菜单
			JSONObject jsonObject2 = Menu.get(testAssistantAgentId, wc);
			System.out.println(jsonObject2);
			System.out.println();
			
			//创建企业应用菜单
			wc.setCorpId(corpid2);
			wc.setCorpSecret(testSecret);
			JSONObject json2 = Menu.create(jsonObject2, testAgentId, wc);
			System.out.println(json2);
			System.out.println(Menu.get(testAgentId, wc));*/
			
			//设置企业应用
			JSONObject json1 = CgiUtils.getAgentSet(testAgentId, "0", "", "测试应用", "测试用的一个应用", "", "", "");
			System.out.println(Agent.set(json1, wc));
			System.out.println();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (WechatException e) {
			e.printStackTrace();
		}
	}
}


