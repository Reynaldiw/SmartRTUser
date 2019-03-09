package com.reynaldiwijaya.smartrt.model.Informasi;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseGetInformasi{

	@SerializedName("news")
	private List<NewsItem> news;

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("hasil")
	private String hasil;

	public void setNews(List<NewsItem> news){
		this.news = news;
	}

	public List<NewsItem> getNews(){
		return news;
	}

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setHasil(String hasil){
		this.hasil = hasil;
	}

	public String getHasil(){
		return hasil;
	}
}