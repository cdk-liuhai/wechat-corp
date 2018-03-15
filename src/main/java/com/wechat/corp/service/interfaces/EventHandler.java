package com.wechat.corp.service.interfaces;

import org.w3c.dom.Element;

public interface EventHandler {

	public String subscribe(Element root);

	public String location(Element root);

	public String click(Element root);

	public String view(Element root);

	public String scancodePush(Element root);

	public String scancodeWaitmsg(Element root);

	public String picSysphoto(Element root);

	public String picPhotoOrAlbum(Element root);

	public String picWeixin(Element root);

	public String locationSelect(Element root);

	public String enterAgent(Element root);

	public String batchJobResult(Element root);

}
