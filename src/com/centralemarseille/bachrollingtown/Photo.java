package com.centralemarseille.bachrollingtown;

public class Photo {
	private String id;
	private String path;
	private String thumbpath;
	
	
	
	public Photo() {
		super();
		this.id = "";
		this.path = "";
		this.thumbpath = "";
	}



	public Photo(String id, String path, String thumbpath) {
		super();
		this.id = id;
		this.path = path;
		this.thumbpath = thumbpath;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}



	public String getThumbpath() {
		return thumbpath;
	}



	public void setThumbpath(String thumbpath) {
		this.thumbpath = thumbpath;
	}
	
	
	

}
