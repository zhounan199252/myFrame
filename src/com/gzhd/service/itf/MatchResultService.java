package com.gzhd.service.itf;

import java.util.Map;

import com.gzhd.model.MatchResultModel;

public interface MatchResultService {

	public static final String BEAN_NAME = "com.gzhd.service.itf.MatchResultService";
	
	public String addMatchResult(MatchResultModel mnodel);
	
	public boolean checkResultIsExistByMatchId(String matchId);
	
	public Map<String, String> getMatchResultByDate();
}
