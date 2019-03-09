package com.reynaldiwijaya.smartrt.model.Store;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDataStore{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("hasil")
	private String hasil;

	@SerializedName("store")
	private List<StoreItem> store;

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

	public void setStore(List<StoreItem> store){
		this.store = store;
	}

	public List<StoreItem> getStore(){
		return store;
	}
}