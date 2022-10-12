package model.vo;

public class CustomerVO {
	String custName;		// 고객명
	String custTel1;			// 전화번호
	String custTel2;			// 보조 전화번호
	String custAddr;		// 주소
	String custEmail;		// 이메일

public String getCustName() {
	return custName;
}

public void setCustName(String custName) {
	this.custName = custName;
}

public String getCustTel1() {
	return custTel1;
}

public void setCustTel1(String custTel1) {
	this.custTel1 = custTel1;
}

public String getCustTel2() {
	return custTel2;
}

public void setCustTel2(String custTel2) {
	this.custTel2 = custTel2;
}

public String getCustAddr() {
	return custAddr;
}

public void setCustAddr(String custAddr) {
	this.custAddr = custAddr;
}

public String getCustEmail() {
	return custEmail;
}

public void setCustEmail(String custEmail) {
	this.custEmail = custEmail;
}


}
