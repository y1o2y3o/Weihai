package web.formbean;

import java.util.HashMap;
import java.util.Map;

public class LoginFormBean {
	private String inputId;
	private String inputPassword;
	private Map<String, String> errors = new HashMap<String, String>();
	/*getters and setters */
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
	public Map<String, String> getErrors(){
		return errors;
	}
	public boolean validate(){
		boolean ok = true;
		if(inputId == null || inputId.trim().equals("")){
			ok = false;
			errors.put("inputId", "名字为空🐎？");
		}
		if(inputPassword == null || inputPassword.trim().equals("")){
			ok = false;
			errors.put("inputPassword", "密码为空🐎？");
		}
		return ok;
	}
}
