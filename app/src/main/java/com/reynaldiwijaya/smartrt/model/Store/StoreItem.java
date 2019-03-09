package com.reynaldiwijaya.smartrt.model.Store;

import com.google.gson.annotations.SerializedName;

public class StoreItem{

	@SerializedName("no_tlp")
	private String noTlp;

	@SerializedName("foto")
	private String foto;

	@SerializedName("alamat_toko")
	private String alamatToko;

	@SerializedName("id_toko")
	private String idToko;

	@SerializedName("nama_lengkap")
	private String namaLengkap;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("foto_toko")
	private String fotoToko;

	@SerializedName("no_tlp_store")
	private String noTlpStore;

	@SerializedName("nama_toko")
	private String namaToko;

	public void setNoTlp(String noTlp){
		this.noTlp = noTlp;
	}

	public String getNoTlp(){
		return noTlp;
	}

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setAlamatToko(String alamatToko){
		this.alamatToko = alamatToko;
	}

	public String getAlamatToko(){
		return alamatToko;
	}

	public void setIdToko(String idToko){
		this.idToko = idToko;
	}

	public String getIdToko(){
		return idToko;
	}

	public void setNamaLengkap(String namaLengkap){
		this.namaLengkap = namaLengkap;
	}

	public String getNamaLengkap(){
		return namaLengkap;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public void setFotoToko(String fotoToko){
		this.fotoToko = fotoToko;
	}

	public String getFotoToko(){
		return fotoToko;
	}

	public void setNoTlpStore(String noTlpStore){
		this.noTlpStore = noTlpStore;
	}

	public String getNoTlpStore(){
		return noTlpStore;
	}

	public void setNamaToko(String namaToko){
		this.namaToko = namaToko;
	}

	public String getNamaToko(){
		return namaToko;
	}
}