package com.citalin.entity;

public class JobParamsRequestEntity {
	
	private String paramKey;
	
	private String paramValue;
	
	public JobParamsRequestEntity()
	{
		
	}

	public JobParamsRequestEntity(String paramKey, String paramValue) {
		super();
		this.paramKey = paramKey;
		this.paramValue = paramValue;
	}



	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	@Override
	public String toString() {
		return "JobParamsRequestEntity [paramKey=" + paramKey + ", paramValue=" + paramValue + "]";
	}
	
	
	

}
