package com.gzhd.web.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gzhd.common.ConstantValues;
import com.gzhd.domain.OpenMessage;
import com.gzhd.model.BetMessageModel;
import com.gzhd.model.FrontUserModel;
import com.gzhd.model.OpenMessageModel;
import com.gzhd.service.itf.BetMessageService;
import com.gzhd.service.itf.FrontUserService;
import com.gzhd.service.itf.OpenMessageService;
import com.gzhd.util.StringUtil;


public class LowFrequencyDataListener implements ServletContextListener {

	private static final Logger logger = Logger.getLogger(LowFrequencyDataListener.class);
	

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		final ServletContext application = sce.getServletContext();

		Timer timer = new Timer();

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				getHighFrequencyData(application);
			}
		}, 2000, 1000*60*60);
	}

	/**
	 * @param 获取十一选5等数据
	 *         
	 */
	private void getHighFrequencyData(ServletContext application) {
		ApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(application);
		final OpenMessageService openMessageService = (OpenMessageService) ac.getBean("com.gzhd.service.itf.OpenMessageService");
		final BetMessageService  betMessageService = (BetMessageService) ac.getBean("com.gzhd.service.itf.BetMessageService");
		final FrontUserService  frontUserService = (FrontUserService) ac.getBean("com.gzhd.service.itf.FrontUserService");
		String dataUrl = "http://f.apiplus.cn/ssq-02.json";

		BufferedReader br = null;
		URL url = null;
		HttpURLConnection con = null;
		StringBuilder response = null;
		String inputLine = "";  
		String  data= null;
		JSONArray jsonArray=null;
		JSONObject jsonObject = null;
		

		try {
			url = new URL(dataUrl);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json;charset=utf-8");

			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			response = new StringBuilder();
			while ((inputLine = br.readLine()) != null) {
				response.append(StringUtil.convert(inputLine));
			}
			if (response.toString().length() > 100) {
				   data= response.toString();
				    jsonObject = JSONObject.parseObject(data);
				    jsonArray=   jsonObject.getJSONArray("data"); 	 
				    for (int i = 0; i < jsonArray.size(); i++) {  
				             JSONObject object = (JSONObject)jsonArray.get(i); 
				             OpenMessageModel model = (OpenMessageModel)JSONObject.toJavaObject(object,OpenMessageModel.class);
				             if(model != null){
	                             model.setType("双色球");
				            	if(openMessageService.getOpenMessage(model)==null){		
				            		openMessageService.addOpenMessage(model);	
				            		openAward(betMessageService,frontUserService,model);
				            	}
				            	 			      
				             } 
				      }
			}
			Thread.sleep(5000); // 先睡眠5秒，否则接口网站会认为恶意操作
			dataUrl = "http://f.apiplus.cn/dlt-02.json";
			url = new URL(dataUrl);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json;charset=utf-8");

			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			response = new StringBuilder();
			while ((inputLine = br.readLine()) != null) {
				response.append(StringUtil.convert(inputLine));
			}
			if (response.toString().length() > 100) {
                 data= response.toString();
			    jsonObject = JSONObject.parseObject(data);
			    jsonArray=   jsonObject.getJSONArray("data");
			    for (int i = 0; i < jsonArray.size(); i++) {  
			             JSONObject object = (JSONObject)jsonArray.get(i); 
			             OpenMessageModel model = (OpenMessageModel)JSONObject.toJavaObject(object,OpenMessageModel.class);
			             if(model != null){
                             model.setType("大乐透");
			            	if(openMessageService.getOpenMessage(model)==null){		
			            		openMessageService.addOpenMessage(model);	
			            		openAward(betMessageService,frontUserService,model);
			            	}
			            	 			      
			             } 
			      }
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != br) {
					br.close();
				}
			} catch (Exception e1) {
				logger.error(e1);
				e1.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	private void  openAward(BetMessageService  betMessageService,FrontUserService  frontUserService,OpenMessageModel model){
		BetMessageModel  betMessageModel= new BetMessageModel();
		betMessageModel.setBetType(model.getType());
		betMessageModel.setExchangeFlag("待开奖");
		betMessageModel.setBetPeriod(model.getExpect());
		List<BetMessageModel> list=betMessageService.getAll(betMessageModel);
		 Map<String, String> map= ConstantValues.getAwardMoney();
		 String ids="";
		 String ids2="";
		 String ids3="";
		for(BetMessageModel bet:list){	
			String[] open=   model.getOpencode().split("[+]");
			String[] betn =  bet.getBetNum().split("[+]");
			String[] openq=   open[0].split(",");
			String[] openh=   open[1].split(",");
			String[] betnq =  betn[0].split(",");
			String[] betnh =  betn[1].split(",");			
			List<String> list1= Arrays.asList(openq);
			List<String> list2= Arrays.asList(openh);
			
			List<String> list3= Arrays.asList(betnq);
			List<String> list4= Arrays.asList(betnh);
			int num1=0;
			int num2=0;
			String level="";
			for( int i=0;i< list3.size();i++){
				if(list1.contains(list3.get(i))){
					num1+=1;
				}
			}
			for( int i=0;i< list4.size();i++){
				if(list2.contains(list4.get(i))){
					num2+=1;
				}
			}
			
			
			
			if(bet.getBetType().equals("双色球")){
				if(num1<3&&num2==1){
					ids +=bet.getId()+",";
					level="s6";
				}else if((num1==4&&num2==0) ||(num1==3&&num2==1)){
					ids +=bet.getId()+",";
					level="s5";
				}else if((num1==5&&num2==0) ||(num1==4&&num2==1)){
					ids +=bet.getId()+",";
					level="s4";
				}else if(num1==5&&num2==1){
					ids +=bet.getId()+",";
					level="s3";
				}else if(num1==6&&num2==0){
					ids3 +=bet.getId()+",";
					level="s2";
				}else if(num1==6&&num2==1){
					ids3 +=bet.getId()+",";
					level="s1";
				}else {
					ids2 +=bet.getId()+",";
					level="s0";
				}
				
							
			} else if(bet.getBetType().equals("大乐透")){
				if((num1<2&&num2==2)||(num1==3&&num2==0) ||(num1==2&&num2==1)){
					ids +=bet.getId()+",";
					level="d6";
				}else if((num1==4&&num2==0) ||(num1==3&&num2==1)||(num1==2&&num2==2)){
					ids +=bet.getId()+",";
					level="d5";
				}else if((num1==4&&num2==1) ||(num1==3&&num2==2)){
					ids +=bet.getId()+",";
					level="d4";
				}else if((num1==5&&num2==0) ||(num1==4&&num2==2)){
					ids3 +=bet.getId()+",";
					level="d3";
				}else if(num1==5&&num2==1){
					ids3 +=bet.getId()+",";
					level="d2";
				}else if(num1==5&&num2==2){
					ids3 +=bet.getId()+",";
					level="d1";
				}else {
					ids2 +=bet.getId()+",";
					level="d0";
				}
					
			} 
			
			
			FrontUserModel frontUserModel = frontUserService.getUserById(bet.getBetPerson());
			if (frontUserModel.getId()!=null) {
				BigDecimal   b   =   new   BigDecimal(Double.valueOf(bet.getBetQuan())*Double.valueOf(map.get(level)));
				 double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
					frontUserModel.setBalance(f1);
					frontUserService.updateUserBalanceById(frontUserModel);	
            }
		
		}
		      if(ids.length()>1){
			  betMessageModel.setId(ids.substring(0, ids.length()-1));
			  betMessageModel.setExchangeFlag("1");
			  betMessageService.updateBetMessage(betMessageModel);	
		       }
			  
			   if(ids2.length()>1){
					  betMessageModel.setId(ids2.substring(0, ids2.length()-1));
					  betMessageModel.setExchangeFlag("2");
					  betMessageService.updateBetMessage(betMessageModel);	
			   }
			   if(ids3.length()>1){
					  betMessageModel.setId(ids3.substring(0, ids3.length()-1));
					  betMessageModel.setExchangeFlag("3");
					  betMessageService.updateBetMessage(betMessageModel);	
			   }
		
	}
	
	


	
}
