package edu.hm.cs.cieserver.campus;

public class CampusRequest {

	private Campus campus;

	private String base64Image;

	public CampusRequest() {

	}

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

}
