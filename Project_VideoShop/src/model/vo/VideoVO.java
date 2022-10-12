package model.vo;

public class VideoVO {
	
	int videoNo;					// 비디오번호
	String genre;				// 장르
	String videoName;			// 비디오명
	String director;				// 감독
	String actor;					// 배우
	String exp;					// 설명
	
	public int getVideoNo() {
		return videoNo;
	}
	public void setVideoNo(int videoNo) {
		this.videoNo = videoNo;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	
	

}
