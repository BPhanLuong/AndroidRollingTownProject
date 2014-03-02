package com.centralemarseille.bachrollingtown;


public class Post {
	private String title;
	private String date;
	private String slug;
    
    
    public Post() {
		super();
		this.title = "";
		this.date = "";
		this.slug = "";
		
		

	}
    
    public Post(String title, String date, String slug) {
		super();
		this.title = title;
		this.date = date;
		this.slug = slug;
		
	}


    
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
    

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
    

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

    

}

