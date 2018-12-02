package web.formbean;

import java.util.HashMap;
import java.util.Map;

public class RegisterStudentFormBean {
	private String inputName;
	private String inputId;
	private String inputPassword;
	private String inputPassword2;
	private String inputLevel;
	private String inputMajor; // 主修专业
	private String inputId_major; // 外键id_major
	
	private Map<String, String> errors = new HashMap<String, String>();
	public boolean validate(){
		boolean ok = true;
		// 检验 InputName 合法性
		if(inputName == null || inputName.trim().equals("")){
			ok = false;
			errors.put("InputName", "姓名不能为空🐎?");
		}
		else{
			if(inputName.length() > 30){
				ok = false;
				errors.put("InputName", "名字太长了🐎?");
			}
		}
		
		// 检验 InputPasword 合法性
		if(inputPassword == null){
			ok = false;
			errors.put("InputPassword", "密码不能为空🐎?");
		}
		else{
			if(!inputPassword.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$")){
				ok = false;
				errors.put("InputPassword", "密码不合法🐎?");
			}
		}
		
		// 检验 InputPasword2 合法性
		if(inputPassword2 == null){
			ok = false;
			errors.put("InputPassword2", "确认密码不能为空🐎?");
		}
		else{
			if(!inputPassword2.equals(inputPassword)){
				ok = false;
				errors.put("InputPassword2", "密码不一致🐎?");
			}
		}
		
		// 检验 InputLevel 合法性
		if(inputLevel == null||!inputLevel.matches("\\d{4}")){
			ok = false;
			errors.put("InputLevel", "学级不对🐎?");
		}
		// 检验 InputId 合法性
		if(inputId == null||inputId.trim().equals("")){
			ok = false;
			errors.put("InputId", "学号为空🐎?");
		}
		else{
			if(!inputId.matches("\\d{8}")){
				ok = false;
				errors.put("InputId", "学号不合法🐎?");
			}
		}
		// 检验 InputId_major 合法性...
		return ok;
	}
	/* Setters and getters */
	public String getInputName() {
		return inputName;
	}
	public void setInputName(String inputName) {
		this.inputName = inputName;
	}
	public String getInputId() {
		return inputId;
	}
	public void setInputId(String inputId) {
		this.inputId = inputId;
	}
	public String getInputPassword() {
		return inputPassword;
	}
	public void setInputPassword(String inputPassword) {
		this.inputPassword = inputPassword;
	}
	public String getInputPassword2() {
		return inputPassword2;
	}
	public void setInputPassword2(String inputPassword2) {
		this.inputPassword2 = inputPassword2;
	}
	public String getInputLevel() {
		return inputLevel;
	}
	public void setInputLevel(String inputLevel) {
		this.inputLevel = inputLevel;
	}
	public String getInputMajor() {
		return inputMajor;
	}
	public void setInputMajor(String inputMajor) {
		this.inputMajor = inputMajor;
	}
	public String getInputId_major() {
		return inputId_major;
	}
	public void setInputId_major(String inputId_major) {
		this.inputId_major = inputId_major;
	}
	public Map<String, String> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
	
	
}
