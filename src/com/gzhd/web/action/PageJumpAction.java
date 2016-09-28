
package com.gzhd.web.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;



@Action(value = "pageJump", results = { 
		@Result(name = "toGd11x5", location = "/WEB-INF/pages/highFrequency/gd11x5.jsp"),
		@Result(name = "toSsq", location = "/WEB-INF/pages/highFrequency/ssq.jsp"),
		@Result(name = "toDlt", location = "/WEB-INF/pages/highFrequency/dlt.jsp"),
		@Result(name = "toCqssc", location = "/WEB-INF/pages/highFrequency/cqssc.jsp"),
		@Result(name = "toYlsfc", location = "/WEB-INF/pages/highFrequency/ylsfc.jsp"),
		@Result(name = "toBjpk10", location = "/WEB-INF/pages/highFrequency/bjpk10.jsp"),
		@Result(name = "toTtxy", location = "/WEB-INF/pages/highFrequency/ttxy.jsp"),
		@Result(name = "toJpzy", location = "/WEB-INF/pages/highFrequency/jpzy.jsp"),
		@Result(name = "toAqzd", location = "/WEB-INF/pages/highFrequency/aqzd.jsp"),
		@Result(name = "toLmhz", location = "/WEB-INF/pages/highFrequency/lmhz.jsp")
		
	
	
})


/**
 * 用于页面间的跳转
 */
@Scope("prototype")
public class PageJumpAction  extends BaseAction<Object>{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toGd11x5() {
		return "toGd11x5";
	}

	public String toSsq() {
		return "toSsq";
	}
	
	public String toDlt() {
		return "toDlt";
	}
	
	public String toCqssc() {
		return "toCqssc";
	}

	public String toYlsfc() {
		return "toYlsfc";
	}

	public String toBjpk10() {
		return "toBjpk10";
	}
	
	public String toTtxy() {
		return "toTtxy";
	}

	public String toJpzy() {
		return "toJpzy";
	}

	public String toAqzd() {
		return "toAqzd";
	}
	
	public String toLmhz() {
		return "toLmhz";
	}
	
	
}
