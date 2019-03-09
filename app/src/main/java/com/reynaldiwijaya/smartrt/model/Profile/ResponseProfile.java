package com.reynaldiwijaya.smartrt.model.Profile;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseProfile{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("hasil")
	private String hasil;

	@SerializedName("user")
	private List<UserItem> user;

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

	public void setUser(List<UserItem> user){
		this.user = user;
	}

	public List<UserItem> getUser(){
		return user;
	}
}