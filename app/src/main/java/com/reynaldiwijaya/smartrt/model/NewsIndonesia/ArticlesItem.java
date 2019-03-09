package com.reynaldiwijaya.smartrt.model.NewsIndonesia;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ArticlesItem implements Parcelable {

	@SerializedName("publishedAt")
	private String publishedAt;

	@SerializedName("author")
	private String author;

	@SerializedName("urlToImage")
	private String urlToImage;

	@SerializedName("description")
	private String description;

	@SerializedName("source")
	private Source source;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	@SerializedName("content")
	private String content;

	public void setPublishedAt(String publishedAt){
		this.publishedAt = publishedAt;
	}

	public String getPublishedAt(){
		return publishedAt;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return author;
	}

	public void setUrlToImage(String urlToImage){
		this.urlToImage = urlToImage;
	}

	public String getUrlToImage(){
		return urlToImage;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setSource(Source source){
		this.source = source;
	}

	public Source getSource(){
		return source;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.publishedAt);
		dest.writeString(this.author);
		dest.writeString(this.urlToImage);
		dest.writeString(this.description);
		dest.writeParcelable((Parcelable) this.source, flags);
		dest.writeString(this.title);
		dest.writeString(this.url);
		dest.writeString(this.content);
	}

	public ArticlesItem() {
	}

	protected ArticlesItem(Parcel in) {
		this.publishedAt = in.readString();
		this.author = in.readString();
		this.urlToImage = in.readString();
		this.description = in.readString();
		this.source = in.readParcelable(Source.class.getClassLoader());
		this.title = in.readString();
		this.url = in.readString();
		this.content = in.readString();
	}

	public static final Creator<ArticlesItem> CREATOR = new Creator<ArticlesItem>() {
		@Override
		public ArticlesItem createFromParcel(Parcel source) {
			return new ArticlesItem(source);
		}

		@Override
		public ArticlesItem[] newArray(int size) {
			return new ArticlesItem[size];
		}
	};
}