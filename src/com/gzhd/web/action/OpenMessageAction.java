package com.gzhd.web.action;

import java.util.Date;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import com.gzhd.common.ConstantValues;
import com.gzhd.model.OpenMessageModel;
import com.gzhd.model.PageModel;
import com.gzhd.service.itf.OpenMessageService;
import com.gzhd.util.TimeUtil;
import com.opensymphony.xwork2.ActionContext;


@Action(value = "openMessage", results = { 
		@Result(name = "openMessage", location = "/WEB-INF/pages/highFrequency/openMessage.jsp"),
		@Result(name = "ylsfcAddCode", location = "/WEB-INF/pages/highFrequency/ylsfcAddCode.jsp"),
		@Result(name = "toList", location = "openMessage!openMessage.action", type = "redirectAction")
		
	
	
})


/**
 * 管理永乐十分彩
 */
@Scope("prototype")
public class OpenMessageAction  extends BaseAction<OpenMessageModel>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = OpenMessageService.BEAN_NAME)
	private OpenMessageService openMessageService;
	
	
	/**
	 * 后台查看开奖信息
	 */
	public String openMessage() {
		PageModel pageModel = openMessageService.getForPageModel(
				model.getPageNum(), ConstantValues.PAGE_SIZE, model);

		ActionContext.getContext().put("pageModel", pageModel);
		return "openMessage";
	}
	
	
	/**
	 * 后台添加永乐十分彩开奖号码
	 */
	public String ylsfcAddCode() {
		return "ylsfcAddCode";
	}
	
	
	/**
	 * 后台查看开奖信息
	 */
	public void lookOpenMessage() {
		model.setType("永乐十分彩");
		model.setOpentime(TimeUtil.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
		PageModel pageModel = openMessageService.getForPageModel(
				model.getPageNum(), 6, model);
		writeJsonToJsp(pageModel.getRecordList());
	}
	
	/**
	 * 后台添加永乐十分彩开奖号码
	 */
	public String saveYlsfcCode() {
	    String  opencode=model.getOpencode();
		  int total=(opencode.length()+1)/6;
		  String  time=model.getOpentime();
		  String time2=time.substring(0, 4)+"-"+time.substring(4, 6)+"-"+time.substring(6, 8)+" ";
		  for(int i=1;i<=total;i++){
			 String a=opencode.substring(6*(i-1), 6*i-1);
			  model.setOpencode(a.substring(0, 1)+","+a.substring(1, 2)+","+a.substring(2, 3)+","+a.substring(3, 4)+","+a.substring(4, 5));
			  String expect=time;
			     if(i<10){
			    	 expect= expect+"00"+i;
			     }else{
			    	 expect= expect+"0"+i; 
			     }
			   
			  model.setExpect(expect);
			  String  opentime=time2;
			       int hour= i*10/60;
			       int minute= i*10%60;
			       
			if(i>11){
				hour=hour+8;
			}
			
			  if(hour<10){
				  if(minute==0){
					  opentime=opentime+"0"+hour+":00:00";  
				  }else{
					  opentime=opentime+"0"+hour+":"+minute+":00";  
				  }
				  
			  }else if(hour==24){
				  opentime=opentime+"23:59:59";  
			  }else{
				  if(minute==0){
					  opentime=opentime+hour+":00:00";  
				  }else{
					  opentime=opentime+hour+":"+minute+":00";  
				  }
			  }
			       
			  model.setOpentime(opentime);
			  if(openMessageService.getOpenMessage(model)==null){
				  openMessageService.addOpenMessage(model); 
			  }
		
		  }
		return "toList";
	}
	
	
	
	/**
	 * 删除
	 */
	public String deleteById() {

		openMessageService.deleteById(model.getId());

		return "toList";
	}

}
