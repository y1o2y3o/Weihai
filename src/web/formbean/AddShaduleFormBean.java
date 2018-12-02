package web.formbean;

public class AddShaduleFormBean {
	private String inputId;
	private String inputName;
	private String inputTeacher="None";
	private String inputPlace="None";
	private String inputRemark;
	private String inputId_major;
	private String inputScope;
	private String inputDay;
	private String inputOrder;
	
	public boolean validate(){
		boolean ok = true;
		// 校验inputId
		if(inputId == null || inputId.trim().equals("") || inputId.length() > 30){
			ok = false;
			System.out.println("inputId不合法:"+inputId);
		}
					
		// 校验inputName
		if(inputName == null || inputName.trim().equals("") || inputName.length() > 60){
			ok = false;
			System.out.println("inputName不合法:"+inputName);
		}		
		
		// 校验inputTeacher
		if(inputTeacher == null || inputTeacher.length() > 30){
			ok = false;
			System.out.println("inputTeacher不合法:"+inputTeacher);
		}
				
		// 校验inputPlace
		if(inputPlace == null || inputPlace.length() > 30){
			ok = false;
			System.out.println("inputPlace不合法:"+inputPlace);
		}
		
		// 校验inputRemark
		if(inputRemark == null || inputRemark.length() > 60){
			ok = false;
			System.out.println("inputRemark不合法:"+inputRemark);
		}
		
		// 校验inputId_major
		if(inputId_major == null || !inputId_major.matches("^\\d{1,4}$")){
			ok = false;
			System.out.println("inputId_major不合法:"+inputId_major);
		}
		
		// 校验 inputScope
		if(inputScope == null || !inputScope.matches("^\\d{4}-\\d{4}-\\d{1}$")){
			ok = false;
			System.out.println("inputScope不合法:"+inputScope);
		}		
		
		// 校验 inputDay
		if(inputDay == null || !inputDay.matches("^[1-7]$")){
			ok = false;
			System.out.println("inputDay不合法:"+inputDay);
		}
		
		// 校验inputOrder
		if(inputOrder == null || !inputOrder.matches("^[1-5]$")){
			ok = false;
			System.out.println("inputOrder不合法:"+inputOrder);
		}	

		return ok;
	}

	public String getInputId() {
		return inputId;
	}

	public void setInputId(String inputId) {
		this.inputId = inputId;
	}

	public String getInputName() {
		return inputName;
	}

	public void setInputName(String inputName) {
		this.inputName = inputName;
	}

	public String getInputTeacher() {
		return inputTeacher;
	}

	public void setInputTeacher(String inputTeacher) {
		this.inputTeacher = inputTeacher;
	}

	public String getInputPlace() {
		return inputPlace;
	}

	public void setInputPlace(String inputPlace) {
		this.inputPlace = inputPlace;
	}

	public String getInputRemark() {
		return inputRemark;
	}

	public void setInputRemark(String inputRemark) {
		this.inputRemark = inputRemark;
	}

	public String getInputId_major() {
		return inputId_major;
	}

	public void setInputId_major(String inputId_major) {
		this.inputId_major = inputId_major;
	}

	public String getInputScope() {
		return inputScope;
	}

	public void setInputScope(String inputScope) {
		this.inputScope = inputScope;
	}

	public String getInputDay() {
		return inputDay;
	}

	public void setInputDay(String inputDay) {
		this.inputDay = inputDay;
	}

	public String getInputOrder() {
		return inputOrder;
	}

	public void setInputOrder(String inputOrder) {
		this.inputOrder = inputOrder;
	}
	
	
	
}
