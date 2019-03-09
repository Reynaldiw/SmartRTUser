package com.reynaldiwijaya.smartrt.model.Agenda;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseGetAgenda{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("hasil")
	private String hasil;

	@SerializedName("agenda")
	private List<AgendaItem> agenda;

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

	public void setAgenda(List<AgendaItem> agenda){
		this.agenda = agenda;
	}

	public List<AgendaItem> getAgenda(){
		return agenda;
	}
}