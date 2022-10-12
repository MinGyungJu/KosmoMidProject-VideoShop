package model.vo;

public class VideoVO {
	
	int vno;			// 비디오번호 (★ prime key)
	String genre;		// 장르
	String title;		// 제목
	String director;	// 감독
	String actor;		// 배우
	String v_desc;		// 설명
	
	
	public int getVno() {
		return vno;
	}
	public void setVno(int vno) {
		this.vno = vno;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getV_desc() {
		return v_desc;
	}
	public void setV_desc(String v_desc) {
		this.v_desc = v_desc;
	}
	
	
	
	

}
