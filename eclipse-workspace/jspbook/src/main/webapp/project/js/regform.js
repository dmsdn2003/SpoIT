function validateForm() {
	var password = document.forms["form1"]["password"].value;
	var rpassword = document.forms["form1"]["rpassword"].value;
	
	if (password !== rpassword) {
		document.getElementById("passwordError").style.display = "block";
		return false;
	}
	return true;
} 