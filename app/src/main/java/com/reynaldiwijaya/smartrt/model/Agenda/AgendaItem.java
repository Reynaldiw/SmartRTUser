package com.reynaldiwijaya.smartrt.model.Agenda;

import com.google.gson.annotations.SerializedName;

public class AgendaItem{

	@SerializedName("tempat")
	private String tempat;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("judul")
	private String judul;

	@SerializedName("content")
	private String content;

	@SerializedName("konfirmasi")
	private String konfirmasi;

	@SerializedName("id_judul")
	private String idJudul;

	public void setTempat(String tempat){
		this.tempat = tempat;
	}

	public String getTempat(){
		return tempat;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	public void setJudul(String judul){
		this.judul = judul;
	}

	public String getJudul(){
		return judul;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	public void setKonfirmasi(String konfirmasi){
		this.konfirmasi = konfirmasi;
	}

	public String getKonfirmasi(){
		return konfirmasi;
	}

	public void setIdJudul(String idJudul){
		this.idJudul = idJudul;
	}

	public String getIdJudul(){
		return idJudul;
	}
}