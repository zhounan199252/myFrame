package com.gzhd.web.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
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
import com.gzhd.model.PageModel;
import com.gzhd.service.itf.BetMessageService;
import com.gzhd.service.itf.FrontUserService;
import com.gzhd.service.itf.OpenMessageService;
import com.gzhd.util.StringUtil;
import com.gzhd.util.TimeUtil;


public class HighFrequencyDataListener implements ServletContextListener {

	private static final Logger logger = Logger.getLogger(HighFrequencyDataListener.class);
	

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
		}, 2000, 1000*60);
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
		String dataUrl = "http://f.apiplus.cn/gd11x5-04.json";

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
	                             model.setType("广东11选5");
				            	if(openMessageService.getOpenMessage(model)==null){		
				            		//openMessageService.addOpenMessage(model);
				            		openAward(betMessageService,frontUserService,model);
				            	
				            	}
				            	 			      
				             } 
				      }
			}
			Thread.sleep(2000); // 先睡眠2秒，否则接口网站会认为恶意操作
			dataUrl = "http://f.apiplus.cn/cqssc-04.json";
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
                             model.setType("重庆时时彩");
			            	if(openMessageService.getOpenMessage(model)==null){		
			            		//openMessageService.addOpenMessage(model);		
			            		openAward(betMessageService,frontUserService,model);
			            	}
			            	 			      
			             } 
			      }
			}
			Thread.sleep(2000); // 先睡眠5秒，否则接口网站会认为恶意操作
			
			dataUrl = "http://f.apiplus.cn/bjpk10-04.json";
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
	                             model.setType("北京pk10");
				            	if(openMessageService.getOpenMessage(model)==null){		
				            		//openMessageService.addOpenMessage(model);	
				            		openAward(betMessageService,frontUserService,model);
				            	}
				            	 			      
				             } 
				      }
			}
			
			OpenMessageModel  model = new OpenMessageModel(); 
			model.setType("永乐十分彩");
			model.setOpentime(TimeUtil.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
			PageModel pageModel = openMessageService.getForPageModel(
					model.getPageNum(), 4, model);
			 for (int i = 0; i < pageModel.getRecordList().size(); i++) {  
				        model=(OpenMessageModel) pageModel.getRecordList().get(i); 
	            		openAward(betMessageService,frontUserService,model);
	             			      
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
		 List<String> list1= new  ArrayList<String>();
		 List<String> list2= new  ArrayList<String>();
		for(BetMessageModel bet:list){	
		
		
//				int num=0;
//			    for(int i=0;i<betn.length/2;i++){
//			    	if(open[Integer.valueOf(betn[2*i])].equals(betn[2*i+1])){
//			    		num++;
//			    	}	
//			    }
//			    
//			    if(betn.length/2<=5&&num>=1){	
//			    	ids +=bet.getId()+",";
//			    }else if(betn.length/2>5&&betn.length/2<=9&&num>=2){	
//			    	ids +=bet.getId()+",";
//			    }else if(betn.length/2==10&&(num==10||num==10)){	
//			    	ids +=bet.getId()+",";
//			    }else{
//			    	ids2 +=bet.getId()+",";
//			    }
//			    
//			    FrontUserModel frontUserModel = frontUserService.getUserById(bet.getBetPerson());
//				if (frontUserModel.getId()!=null) {
//					BigDecimal   b   =   new   BigDecimal(Double.valueOf(bet.getBetQuan())*Double.valueOf(map.get("p"+betn.length/2+num)));
//					 double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
//						frontUserModel.setBalance(f1);
//						frontUserService.updateUserBalanceById(frontUserModel);	
//	            }
		
				
				 String childType= bet.getBetChildType();
				 list1= Arrays.asList(model.getOpencode().split(","));
				 list2= Arrays.asList(bet.getBetNum().split(","));	
				 String[] open=model.getOpencode().split(",");
				 String[] betn= bet.getBetNum().split(",");
				boolean  flag =false;
				if(childType.equals("r1")||childType.equals("r2")||childType.equals("r3")||childType.equals("r4")||childType.equals("r5")){
					flag =list1.containsAll(list2) ;   
				}else if(childType.equals("r6")||childType.equals("r7")||childType.equals("r8")){
					flag =list2.containsAll(list1) ;
				}else if(childType.equals("q2")||childType.equals("q3")){
					flag =model.getOpencode().startsWith(bet.getBetNum()) ;
				}else if(childType.equals("q2z")){
					flag =list1.subList(0, 2).containsAll(list2);
				}else if(childType.equals("q3z")){
					flag =list1.subList(0, 3).containsAll(list2);			
				}else if(childType.equals("ylsfc-dd")||childType.equals("dd")||childType.equals("pk10dd")){
					int num =0;
					 for(int i=0;i<betn.length/2;i++){
						 System.out.print(Integer.valueOf(betn[2*i])-1);
					    	if(open[Integer.valueOf(betn[2*i])-1].equals(betn[2*i+1])){
					    		  flag = true;	
					    		  num++;
					    	}
					    }
					bet.setBetQuan(String.valueOf(Integer.valueOf(bet.getBetQuan())*num));    			
				}else if(childType.equals("ylsfc-x5")||childType.equals("ylsfc-x4q4")||childType.equals("ylsfc-x4h4")||childType.equals("ylsfc-x4r4")||
						childType.equals("ylsfc-x3q3")||childType.equals("ylsfc-x3z3")||childType.equals("ylsfc-x3h3")||childType.equals("ylsfc-x3r3")||
						childType.equals("ylsfc-x2q2")||childType.equals("ylsfc-x2h2")||childType.equals("ylsfc-x2r2")||
						childType.equals("x5")||childType.equals("x4q4")||childType.equals("x4h4")||childType.equals("x4r4")||
						childType.equals("x3q3")||childType.equals("x3z3")||childType.equals("x3h3")||childType.equals("x3r3")||
						childType.equals("x2q2")||childType.equals("x2h2")||childType.equals("x2r2")||
						childType.equals("pk10q2")||childType.equals("pk10q3")){
				    for(int i=0;i<betn.length/2;i++){
				    	if(open[Integer.valueOf(betn[2*i])-1].equals(betn[2*i+1])){
				    		  flag = true;	
				    	}else{
				    		 flag =false;
				    		  break;
				    	}
				    }
				    
				}else if(childType.equals("ylsfc-q2z2")||childType.equals("q2z2")){
					flag =list1.subList(0, 2).containsAll(list2);
				}else if(childType.equals("ylsfc-h2z2")||childType.equals("h2z2")){
					flag =list1.subList(3, 5).containsAll(list2);
				}else if(childType.equals("ylsfc-q3z3")||childType.equals("q3z3")){
					flag =list1.subList(0, 3).containsAll(list2)&&(new HashSet<String>(list1.subList(0, 3)).size()!=list1.subList(0, 3).size()) ;
				}else if(childType.equals("ylsfc-q3z6")||childType.equals("ylsfc-q3bdd")||childType.equals("ylsfc-q3em")||
						childType.equals("q3z6")||childType.equals("q3bdd")||childType.equals("q3em")){
					flag =list1.subList(0, 3).containsAll(list2);
				}else if(childType.equals("ylsfc-z3z3")||childType.equals("z3z3")){
					flag =list1.subList(1, 4).containsAll(list2)&&(new HashSet<String>(list1.subList(1, 4)).size()!=list1.subList(1, 4).size()) ;
				}else if(childType.equals("ylsfc-z3z6")||childType.equals("ylsfc-z3bdd")||
						childType.equals("z3z6")||childType.equals("z3bdd")){
					flag =list1.subList(1, 4).containsAll(list2);
				}else if(childType.equals("ylsfc-h3z3")||childType.equals("h3z3")){
					flag =list1.subList(2, 5).containsAll(list2)&&(new HashSet<String>(list1.subList(2, 5)).size()!=list1.subList(2, 5).size()) ;
				}else if(childType.equals("ylsfc-h3z6")||childType.equals("ylsfc-h3bdd")||childType.equals("ylsfc-h3em")||
						childType.equals("h3z6")||childType.equals("h3bdd")||childType.equals("h3em")){
					flag =list1.subList(2, 5).containsAll(list2);
				}else if(childType.equals("ylsfc-r2z2")||childType.equals("ylsfc-r3z3")||childType.equals("ylsfc-r3z6")||childType.equals("ylsfc-r3bdd")||
						childType.equals("r2z2")||childType.equals("r3z3")||childType.equals("r3z6")||childType.equals("r3bdd")){
				    list1 = new  ArrayList<String>();
				    list2= new  ArrayList<String>();
				    if(childType.equals("ylsfc-r2z2")||childType.equals("r2z2")){
				    	list1.add(open[Integer.valueOf(betn[0])-1]);
					    list1.add(open[Integer.valueOf(betn[1])-1]);
						 for(int i=2;i<betn.length;i++){
							  list2.add(betn[i]);
						    }
					  
				    }else{
				    	list1.add(open[Integer.valueOf(betn[0])-1]);
					    list1.add(open[Integer.valueOf(betn[1])-1]); 
					    list1.add(open[Integer.valueOf(betn[2])-1]);  
					    for(int i=3;i<betn.length;i++){
							  list2.add(betn[i]);
						    }
					   
				    }
				    
				    if(childType.equals("ylsfc-r3z3")||childType.equals("r3z3")){
				    	  flag =list1.containsAll(list2)&&(new HashSet<String>(list1).size()+1==list1.size()) ;  
				    }else{
				    	  flag =list1.containsAll(list2) ;  	
				    }
				}else if(childType.equals("ylsfc-q2dxd")||childType.equals("ylsfc-h2dxd")||childType.equals("ylsfc-r2dxd")||
						childType.equals("q2dxd")||childType.equals("h2dxd")||childType.equals("r2dxd")){		  
				    for(int i=0;i<betn.length/2;i++){
				    	if(Integer.valueOf(open[Integer.valueOf(betn[2*i])-1])>5&&"大".equals(betn[2*i+1])){
				    		  flag = true;	
				    	}else if(Integer.valueOf(open[Integer.valueOf(betn[2*i])-1])<5&&"小".equals(betn[2*i+1])){
				    		  flag = true;	
				    	}else if(Integer.valueOf(open[Integer.valueOf(betn[2*i])-1])%2!=0&&"单".equals(betn[2*i+1])){
				    		  flag = true;	
				    	}else if(Integer.valueOf(open[Integer.valueOf(betn[2*i])-1])%2==0&&"双".equals(betn[2*i+1])){
				    		  flag = true;	
				    	}else{
				    		 flag =false;
				    		  break;
				    	}
				    }
				}else if(childType.equals("ylsfc-wwdxd")||childType.equals("ylsfc-qwdxd")||childType.equals("ylsfc-bwdxd")
						||childType.equals("ylsfc-bwdxd")||childType.equals("ylsfc-gwdxd")||
						childType.equals("wwdxd")||childType.equals("qwdxd")||childType.equals("bwdxd")
						||childType.equals("bwdxd")||childType.equals("gwdxd")||childType.equals("pk1dxd")
						||childType.equals("pk2dxd")||childType.equals("pk3dxd")||childType.equals("pk4dxd")
						||childType.equals("pk5dxd")||childType.equals("pk6dxd")||childType.equals("pk7dxd")
						||childType.equals("pk8dxd")||childType.equals("pk9dxd")||childType.equals("pk10dxd")){		
					int num =0;
				    for(int i=0;i<betn.length/2;i++){
				    	if(Integer.valueOf(open[Integer.valueOf(betn[2*i])-1])>5&&"大".equals(betn[2*i+1])){
				    		  flag = true;	
				    		  num++;
				    	}else if(Integer.valueOf(open[Integer.valueOf(betn[2*i])-1])<5&&"小".equals(betn[2*i+1])){
				    		  flag = true;	
				    		  num++;
				    	}else if(Integer.valueOf(open[Integer.valueOf(betn[2*i])-1])%2!=0&&"单".equals(betn[2*i+1])){
				    		  flag = true;
				    		  num++;
				    	}else if(Integer.valueOf(open[Integer.valueOf(betn[2*i])-1])%2==0&&"双".equals(betn[2*i+1])){
				    		  flag = true;	
				    		  num++;
				    	}
				    }
		    
					bet.setBetQuan(String.valueOf(Integer.valueOf(bet.getBetQuan())*num));   
				}
				 if(flag){
				    	ids +=bet.getId()+",";
				    	FrontUserModel frontUserModel = frontUserService.getUserById(bet.getBetPerson());
						if (frontUserModel.getId()!=null) {
							BigDecimal   b   =   new   BigDecimal(Double.valueOf(bet.getBetQuan())*Double.valueOf(map.get(childType)));
							 double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
								frontUserModel.setBalance(f1);
								frontUserService.updateUserBalanceById(frontUserModel);	
			            }
				    }else{
				    	ids2 +=bet.getId()+",";
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
		
	}
	
	

}
