package com.reynaldiwijaya.smartrt.model.Informasi;

import com.google.gson.annotations.SerializedName;

public class NewsItem{

	@SerializedName("fotoInformasi")
	private String fotoInformasi;

	@SerializedName("tgl_nulis")
	private String tglNulis;

	@SerializedName("foto")
	private String foto;

	@SerializedName("no_tlp")
	private String noTlp;

	@SerializedName("nama_lengkap")
	private String namaLengkap;

	@SerializedName("judul")
	private String judul;

	@SerializedName("content")
	private String content;

	@SerializedName("konfirmasi")
	private String konfirmasi;

	@SerializedName("id_informasi")
	private String idInformasi;

	public void setFotoInformasi(String fotoInformasi){
		this.fotoInformasi = fotoInformasi;
	}

	public String getFotoInformasi(){
		return fotoInformasi;
	}

	public void setTglNulis(String tglNulis){
		this.tglNulis = tglNulis;
	}

	public String getTglNulis(){
		return tglNulis;
	}

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setNoTlp(String noTlp){
		this.noTlp = noTlp;
	}

	public String getNoTlp(){
		return noTlp;
	}

	public void setNamaLengkap(String namaLengkap){
		this.namaLengkap = namaLengkap;
	}

	public String getNamaLengkap(){
		return namaLengkap;
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

	public void setIdInformasi(String idInformasi){
		this.idInformasi = idInformasi;
	}

	public String getIdInformasi(){
		return idInformasi;
	}
}