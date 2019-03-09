package com.reynaldiwijaya.smartrt.model.Laporan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseLaporanKonfirmasi{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("hasil")
	private String hasil;

	@SerializedName("laporan")
	private List<LaporanItem> laporan;

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

	public void setLaporan(List<LaporanItem> laporan){
		this.laporan = laporan;
	}

	public List<LaporanItem> getLaporan(){
		return laporan;
	}
}