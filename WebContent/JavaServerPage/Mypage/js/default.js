function passwordCheck() {
	if (form.InputPassword2 == form.InputPassword) {
		return true;
	}
	showModal("비밀번호가 같지 않거나 비밀번호를 입력하지 않으셨습니다");
	return false;
}