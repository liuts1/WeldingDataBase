package com.gy.login;

public class PublicStaticSta {
	private String status ;
	private volatile static PublicStaticSta publicStaticSta = null;
	private PublicStaticSta(){
		
	}
	public static PublicStaticSta getinstance(){
		if(publicStaticSta==null){
			synchronized (PublicStaticSta.class) {
				if(publicStaticSta==null){
					publicStaticSta=new PublicStaticSta();
				}
			}
		}
		return publicStaticSta;
	}
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
