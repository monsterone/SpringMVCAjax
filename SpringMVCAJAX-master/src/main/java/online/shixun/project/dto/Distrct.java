package online.shixun.project.dto;

public class Distrct {
	private String code;
	private String name;
	
	
	public Distrct() {
		super();
	}
	public Distrct(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
