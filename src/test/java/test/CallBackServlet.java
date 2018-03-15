package test;


import com.wechat.corp.aes.AesException;
import com.wechat.corp.aes.WXBizMsgCrypt;
import com.wechat.corp.callback.Entrance;
import com.wechat.corp.service.impl.EventHandlerImpl;
import com.wechat.corp.service.impl.MsgHandlerImpl;
import org.xml.sax.SAXException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class CallBackServlet extends HttpServlet {

	private static final long serialVersionUID = -4070890920290030566L;

	private Entrance entrance;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			entrance.doGet(req, resp);
		} catch (AesException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			entrance.doPost(req, resp);
		} catch (AesException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {

	}

	public void init() throws ServletException {
		try {
			ServletConfig servletConfig = getServletConfig();

			String token = servletConfig.getInitParameter("token");
			String corpID = servletConfig.getInitParameter("corpID");
			String encodingAESKey = servletConfig.getInitParameter("encodingAESKey");
			WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, encodingAESKey, corpID);
			Entrance entrance = new Entrance();
			entrance.setWXBizMsgCrypt(wxcpt);
			MsgHandlerImpl msgHandler = new MsgHandlerImpl();
			EventHandlerImpl eventHandler = new EventHandlerImpl();
			entrance.setMsgHandler(msgHandler);
			entrance.setEventHandler(eventHandler);
			this.entrance = entrance;
		} catch (AesException e) {
			e.printStackTrace();
		}
	}

}
