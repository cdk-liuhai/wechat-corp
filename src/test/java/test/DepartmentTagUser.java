package test;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.cgi.Department;
import com.wechat.corp.cgi.User;
import com.wechat.corp.common.WXCorpConstants;
import com.wechat.corp.connect.HttpClientFactory;
import com.wechat.corp.connect.WechatClientHCE;
import org.apache.http.client.HttpClient;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;


public class DepartmentTagUser {

	public static void main(String[] args) {
		try {
			WechatClientHCE wc = new WechatClientHCE(WXCorpConstants.getCorpElement(WXCorpConstants.CORP2, WXCorpConstants.CORP_ID),
					WXCorpConstants.getCorpElement(WXCorpConstants.CORP2, WXCorpConstants.MEMEBERS_SECRET));
			HttpClient httpClient = HttpClientFactory.createHttpClient();
			wc.setHttpClient(httpClient);

			System.out.println("获取部门：" + Department.list("1", wc).toString());// 获取部门
//			System.out.println("获取标签：" + Tag.list(wc).toString());// 获取标签
//			JSONObject createTagJson = CgiUtils.getTagCreate("标签100", "100");
//			System.out.println("创建标签：" + Tag.create(createTagJson, wc));// 创建标签
//			JSONObject createDepartmentJson = CgiUtils.getDepartmentCreate("一级系统", "1", "953", "953");
//			System.out.println("创建部门：" + Department.create(createDepartmentJson, wc).toString());// 创建部门
//			HashMap<String, String> map0 = new HashMap<String, String>();
//			map0.put("name", "爱好");
//			map0.put("value", "旅游");
//			HashMap<String, String> map1 = new HashMap<String, String>();
//			map1.put("name", "卡号");
//			map1.put("value", "1234567234");
//			ArrayList<Map<String, String>> extattr = new ArrayList<Map<String, String>>();
//			extattr.add(map0);
//			extattr.add(map1);
//			JSONObject createUserJson = CgiUtils.getUserCreate("malushun", "马路顺", new Integer[] { 2 }, "测试",
//					"13021708885", "1", "mlucas@163.com", "mlucaswx", null, null);
//			JSONObject createUserJson = CgiUtils.getUserCreate("zhangsan", "张三", new Integer[] { 200 }, "产品经理",
//					"15913215421", "1", "zhangsan@gzdev.com", "zhangsan4dev", null, extattr);
//			System.out.println("创建成员：" + User.create(createUserJson, wc));// 创建成员
//			JSONObject addtagusersJson = CgiUtils.getTagAddtagusers("100", new String[] { "zhangsan" },
//					new Integer[] { 200 });
//			System.out.println("添加成员标签：" + Tag.addtagusers(addtagusersJson, wc));// 添加成员标签
//			System.out.println("获取标签：" + Tag.list(wc).toString());// 获取标签
//			System.out.println("获取标签成员：" + Tag.get("100", wc).toString());// 获取标签成员
//			System.out.println("获取部门200：" + Department.list("2", wc).toString());// 获取部门200
//			System.out.println("获取成员张三：" + User.get("zhangsan", wc).toString());// 获取成员张三
			System.out.println("获取部门成员详情：" + User.list("1", "1", wc).toString());// 获取部门成员详情
//			System.out.println("删除成员张三：" + User.delete("zhangsan", wc).toString());// 删除成员张三
//			System.out.println("删除部门：" + Department.delete("200", wc).toString());// 删除部门
//			System.out.println("删除标签：" + Tag.delete("100", wc).toString());// 删除标签
		} catch (WechatException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
