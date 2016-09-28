package com.gzhd.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:系统所有常量
 * @time:2015年7月24日 下午4:34:14
 */
public class ConstantValues {
	
	/**
	 * 超级管理员的用户名
	 */
	public static final String ADMINISTRATOR_USERNAME = "admin";

	 
	/**
	 * 前台已登录用户
	 */
	public static final String FRONT_CURRENT_USER_LOGIN = "frontCurrentLoginUser";
	
	/**
	 * 后台已登录用户
	 */
	public static final String BACK_CURRENT_USER_LOGIN = "backCurrentLoginUser";
	
	
	/**
	 * 后台当前验证码 
	 */
	public static final String BACK_SESSION_CODE = "backSessionCode";
	
	/**
	 * 前台当前验证码
	 */
	public static final String FRONT_SESSION_CODE = "frontSessionCode";
	
	
	/**
	 * 分页大小
	 */
	public static final int PAGE_SIZE = 10;
	
	/**
	 * 中奖金额
	 */
	
	public static  Map<String, String> getAwardMoney(){
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("r1", "2");
		map.put("r2", "6");
		map.put("r3", "19");
		map.put("r4", "78");
		map.put("r5", "540");
		map.put("r6", "90");
		map.put("r7", "26");
		map.put("r8", "9");
		map.put("q2", "130");
		map.put("q3", "1170");
		map.put("q2z", "65");
		map.put("q3z", "195");
		
		
		map.put("dd", "19.3");	
		map.put("x2q2", "193");
		map.put("x2h2", "193");
		map.put("x2r2", "193");
		map.put("x3q3", "1930");
		map.put("x3z3", "1930");
		map.put("x3h3", "1930");
		map.put("x3r3", "1930");	
		map.put("x4q4", "19300");
		map.put("x4h4", "19300");
		map.put("x4r4", "5570");
		map.put("x5", "193000");	
		map.put("q2z2", "193");
		map.put("h2z2", "193");
		map.put("r2z2", "193");
		map.put("q3z3", "730");
		map.put("z3z3", "730");
		map.put("h3z3", "730");
		map.put("r3z3", "730");		
		map.put("q3z6", "430");
		map.put("z3z6", "430");
		map.put("h3z6", "430");
		map.put("r3z6", "430");		
		map.put("q3bdd", "4.6");
		map.put("z3bdd", "4.6");	
		map.put("h3bdd", "4.6");	
		map.put("r3bdd", "4.6");	
		map.put("q3em", "46");
		map.put("h3em", "46");		
		map.put("q2dxd", "3.86");	
		map.put("h2dxd", "3.86");	
		map.put("r2dxd", "3.86");
		map.put("wwdxd", "3.86");	
		map.put("qwdxd", "3.86");	
		map.put("bwdxd", "3.86");	
		map.put("swdxd", "3.86");	
		map.put("gwdxd", "3.86");	
		
		map.put("ylsfc-dd", "19.3");	
		map.put("ylsfc-x2q2", "193");
		map.put("ylsfc-x2h2", "193");
		map.put("ylsfc-x2r2", "193");
		map.put("ylsfc-x3q3", "1930");
		map.put("ylsfc-x3z3", "1930");
		map.put("ylsfc-x3h3", "1930");
		map.put("ylsfc-x3r3", "1930");	
		map.put("ylsfc-x4q4", "19300");
		map.put("ylsfc-x4h4", "19300");
		map.put("ylsfc-x4r4", "5570");
		map.put("ylsfc-x5", "193000");	
		map.put("ylsfc-q2z2", "193");
		map.put("ylsfc-h2z2", "193");
		map.put("ylsfc-r2z2", "193");
		map.put("ylsfc-q3z3", "730");
		map.put("ylsfc-z3z3", "730");
		map.put("ylsfc-h3z3", "730");
		map.put("ylsfc-r3z3", "730");		
		map.put("ylsfc-q3z6", "430");
		map.put("ylsfc-z3z6", "430");
		map.put("ylsfc-h3z6", "430");
		map.put("ylsfc-r3z6", "430");		
		map.put("ylsfc-q3bdd", "4.6");
		map.put("ylsfc-z3bdd", "4.6");	
		map.put("ylsfc-h3bdd", "4.6");	
		map.put("ylsfc-r3bdd", "4.6");	
		map.put("ylsfc-q3em", "46");
		map.put("ylsfc-h3em", "46");		
		map.put("ylsfc-q2dxd", "3.86");	
		map.put("ylsfc-h2dxd", "3.86");	
		map.put("ylsfc-r2dxd", "3.86");
		map.put("ylsfc-wwdxd", "3.86");	
		map.put("ylsfc-qwdxd", "3.86");	
		map.put("ylsfc-bwdxd", "3.86");	
		map.put("ylsfc-swdxd", "3.86");	
		map.put("ylsfc-gwdxd", "3.86");
		
		map.put("pk10q2", "160");
		map.put("pk10q3", "55");
		map.put("pk10dd", "10");
		map.put("pk1dxd", "2");
		map.put("pk2dxd", "2");
		map.put("pk3dxd", "2");
		map.put("pk4dxd", "2");
		map.put("pk5dxd", "2");
		map.put("pk6dxd", "2");
		map.put("pk7dxd", "2");
		map.put("pk8dxd", "2");
		map.put("pk9dxd", "2");
		map.put("pk10dxd", "2");
		
		map.put("s0", "0");
		map.put("s1", "0");
		map.put("s2", "0");
		map.put("s3", "3000");
		map.put("s4", "200");
		map.put("s5", "10");
		map.put("s6", "5");
		
		
		map.put("d0", "0");
		map.put("d1", "0");
		map.put("d2", "0");
		map.put("d3", "0");
		map.put("d4", "200");
		map.put("d5", "10");
		map.put("d6", "5");
		
		
		
	
//		map.put("p10", "0");
//		map.put("p11", "10");
//		map.put("p20", "0");
//		map.put("p21", "2");
//		map.put("p22", "55");
//		map.put("p30", "0");
//		map.put("p31", "2");
//		map.put("p32", "10");
//		map.put("p33", "160");
//		map.put("p40", "0");
//		map.put("p41", "2");
//		map.put("p42", "5");
//		map.put("p43", "20");
//		map.put("p44", "350");
//		map.put("p50", "0");
//		map.put("p51", "2");
//		map.put("p52", "3");
//		map.put("p53", "8");
//		map.put("p54", "30");
//		map.put("p55", "500");
//		map.put("p60", "0");
//		map.put("p61", "0");
//		map.put("p62", "2");
//		map.put("p63", "10");
//		map.put("p64", "100");
//		map.put("p65", "2000");
//		map.put("p66", "10000");
//		map.put("p70", "0");
//		map.put("p71", "0");
//		map.put("p72", "2");
//		map.put("p73", "10");
//		map.put("p74", "25");
//		map.put("p75", "400");
//		map.put("p76", "4500");
//		map.put("p77", "20000");
//		map.put("p80", "0");
//		map.put("p81", "0");
//		map.put("p82", "2");
//		map.put("p83", "10");
//		map.put("p84", "20");
//		map.put("p85", "100");
//		map.put("p86", "500");
//		map.put("p87", "2000");
//		map.put("p88", "40000");
//		map.put("p90", "0");
//		map.put("p91", "0");
//		map.put("p92", "2");
//		map.put("p93", "5");
//		map.put("p94", "10");
//		map.put("p95", "50");
//		map.put("p96", "250");
//		map.put("p97", "5000");
//		map.put("p98", "10000");
//		map.put("p99", "80000");
//		map.put("p100", "2");
//		map.put("p101", "0");
//		map.put("p102", "0");
//		map.put("p103", "0");
//		map.put("p104", "0");
//		map.put("p105", "0");
//		map.put("p106", "0");
//		map.put("p107", "0");
//		map.put("p108", "0");
//		map.put("p109", "0");
//		map.put("p1010", "888888");
				
		return map;
		
		
		
	}
	
	
	/**
	 * 中奖金额
	 */
	
	public static  Map<String, String> getAwardType(){
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("r1", "任选一");
		map.put("r2", "任选二");
		map.put("r3", "任选三");
		map.put("r4", "任选四");
		map.put("r5", "任选五");
		map.put("r6", "任选六");
		map.put("r7", "任选七");
		map.put("r8", "任选八");
		map.put("q2", "前二直选");
		map.put("q3", "前三直选");
		map.put("q2z", "前二组选");
		map.put("q3z", "前三组选");
		
		
		map.put("dd", "定位胆");	
		map.put("x2q2", "二星直选前二");
		map.put("x2h2", "二星直选后二");
		map.put("x2r2", "二星直选任选二");
		map.put("x3q3", "三星直选前三");
		map.put("x3z3", "三星直选中三");
		map.put("x3h3", "三星直选后三");
		map.put("x3r3", "三星直选任选三");	
		map.put("x4q4", "四星直选前四");
		map.put("x4h4", "四星直选后四");
		map.put("x4r4", "四星直选任选四");
		map.put("x5", "五星直选");	
		map.put("q2z2", "二星组选前二");
		map.put("h2z2", "二星组选后二");
		map.put("r2z2", "二星组选任选二");
		map.put("q3z3", "三星组选前三组三");
		map.put("z3z3", "三星组选中三组三");
		map.put("h3z3", "三星组选后三组三");
		map.put("r3z3", "三星组选任选三组三");		
		map.put("q3z6", "三星组选前三组六");
		map.put("z3z6", "三星组选中三组六");
		map.put("h3z6", "三星组选后三组六");
		map.put("r3z6", "三星组选任选三组六");		
		map.put("q3bdd", "前三不定胆");
		map.put("z3bdd", "中三不定胆");	
		map.put("h3bdd", "后三不定胆");	
		map.put("r3bdd", "任三不定胆");	
		map.put("q3em", "前三二码");
		map.put("h3em", "后三二码");		
		map.put("q2dxd", "前二大小单");	
		map.put("h2dxd", "后二大小单");	
		map.put("r2dxd", "任二大小单");
		map.put("wwdxd", "万位大小单");	
		map.put("qwdxd", "千位大小单");	
		map.put("bwdxd", "百位大小单");	
		map.put("swdxd", "十位大小单");	
		map.put("gwdxd", "个位大小单");	
		

		map.put("ylsfc-dd", "定位胆");	
		map.put("ylsfc-x2q2", "二星直选前二");
		map.put("ylsfc-x2h2", "二星直选后二");
		map.put("ylsfc-x2r2", "二星直选任选二");
		map.put("ylsfc-x3q3", "三星直选前三");
		map.put("ylsfc-x3z3", "三星直选中三");
		map.put("ylsfc-x3h3", "三星直选后三");
		map.put("ylsfc-x3r3", "三星直选任选三");	
		map.put("ylsfc-x4q4", "四星直选前四");
		map.put("ylsfc-x4h4", "四星直选后四");
		map.put("ylsfc-x4r4", "四星直选任选四");
		map.put("ylsfc-x5", "五星直选");	
		map.put("ylsfc-q2z2", "二星组选前二");
		map.put("ylsfc-h2z2", "二星组选后二");
		map.put("ylsfc-r2z2", "二星组选任选二");
		map.put("ylsfc-q3z3", "三星组选前三组三");
		map.put("ylsfc-z3z3", "三星组选中三组三");
		map.put("ylsfc-h3z3", "三星组选后三组三");
		map.put("ylsfc-r3z3", "三星组选任选三组三");		
		map.put("ylsfc-q3z6", "三星组选前三组六");
		map.put("ylsfc-z3z6", "三星组选中三组六");
		map.put("ylsfc-h3z6", "三星组选后三组六");
		map.put("ylsfc-r3z6", "三星组选任选三组六");		
		map.put("ylsfc-q3bdd", "前三不定胆");
		map.put("ylsfc-z3bdd", "中三不定胆");	
		map.put("ylsfc-h3bdd", "后三不定胆");	
		map.put("ylsfc-r3bdd", "任三不定胆");	
		map.put("ylsfc-q3em", "前三二码");
		map.put("ylsfc-h3em", "后三二码");		
		map.put("ylsfc-q2dxd", "前二大小单");	
		map.put("ylsfc-h2dxd", "后二大小单");	
		map.put("ylsfc-r2dxd", "任二大小单");
		map.put("ylsfc-wwdxd", "万位大小单");	
		map.put("ylsfc-qwdxd", "千位大小单");	
		map.put("ylsfc-bwdxd", "百位大小单");	
		map.put("ylsfc-swdxd", "十位大小单");	
		map.put("ylsfc-gwdxd", "个位大小单");	
		
		map.put("pk10q2", "冠亚军");
		map.put("pk10q3", "前三名");
		map.put("pk10dd", "定位胆选");
		map.put("pk1dxd", "两面冠军");
		map.put("pk2dxd", "两面亚军");
		map.put("pk3dxd", "两面季军");
		map.put("pk4dxd", "两面第四名");
		map.put("pk5dxd", "两面第五名");
		map.put("pk6dxd", "两面第六名");
		map.put("pk7dxd", "两面第七名");
		map.put("pk8dxd", "两面第八名");
		map.put("pk9dxd", "两面第九名");
		map.put("pk10dxd", "两面第十名");
		
		map.put("s0", "0");
		map.put("s1", "0");
		map.put("s2", "0");
		map.put("s3", "3000");
		map.put("s4", "200");
		map.put("s5", "10");
		map.put("s6", "5");
		
		
		map.put("d0", "0");
		map.put("d1", "0");
		map.put("d2", "0");
		map.put("d3", "0");
		map.put("d4", "200");
		map.put("d5", "10");
		map.put("d6", "5");				
		return map;
				
	}
	
}
