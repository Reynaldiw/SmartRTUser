package com.reynaldiwijaya.smartrt.model.Laporan;

import com.google.gson.annotations.SerializedName;

public class LaporanItem{

	@SerializedName("tgl_lapor")
	private String tglLapor;

	@SerializedName("no_tlp")
	private String noTlp;

	@SerializedName("level")
	private String level;

	@SerializedName("nama_lengkap")
	private String namaLengkap;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("profesi")
	private String profesi;

	@SerializedName("laporan")
	private String laporan;

	@SerializedName("konfirmasi")
	private String konfirmasi;

	@SerializedName("tgl_lahir")
	private String tglLahir;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("password")
	private String password;

	@SerializedName("foto")
	private String foto;

	@SerializedName("no_ktp")
	private String noKtp;

	@SerializedName("jenkel")
	private String jenkel;

	@SerializedName("judul")
	private String judul;

	@SerializedName("reply")
	private String reply;

	@SerializedName("id_kritik")
	private String idKritik;

	@SerializedName("email")
	private String email;

	@SerializedName("status")
	private String status;

	@SerializedName("username")
	private String username;

	public void setTglLapor(String tglLapor){
		this.tglLapor = tglLapor;
	}

	public String getTglLapor(){
		return tglLapor;
	}

	public void setNoTlp(String noTlp){
		this.noTlp = noTlp;
	}

	public String getNoTlp(){
		return noTlp;
	}

	public void setLevel(String level){
		this.level = level;
	}

	public String getLevel(){
		return level;
	}

	public void setNamaLengkap(String namaLengkap){
		this.namaLengkap = namaLengkap;
	}

	public String getNamaLengkap(){
		return namaLengkap;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setProfesi(String profesi){
		this.profesi = profesi;
	}

	public String getProfesi(){
		return profesi;
	}

	public void setLaporan(String laporan){
		this.laporan = laporan;
	}

	public String getLaporan(){
		return laporan;
	}

	public void setKonfirmasi(String konfirmasi){
		this.konfirmasi = konfirmasi;
	}

	public String getKonfirmasi(){
		return konfirmasi;
	}

	public void setTglLahir(String tglLahir){
		this.tglLahir = tglLahir;
	}

	public String getTglLahir(){
		return tglLahir;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setNoKtp(String noKtp){
		this.noKtp = noKtp;
	}

	public String getNoKtp(){
		return noKtp;
	}

	public void setJenkel(String jenkel){
		this.jenkel = jenkel;
	}

	public String getJenkel(){
		return jenkel;
	}

	public void setJudul(String judul){
		this.judul = judul;
	}

	public String getJudul(){
		return judul;
	}

	public void setReply(String reply){
		this.reply = reply;
	}

	public String getReply(){
		return reply;
	}

	public void setIdKritik(String idKritik){
		this.idKritik = idKritik;
	}

	public String getIdKritik(){
		return idKritik;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}