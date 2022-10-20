
function showAccountForm() {
	if (typeof(storage) != null) {
		localStorage.setItem("selected radio", document.querySelector('input[name="AccountType"]:checked').value);
	}
	
	let text = document.getElementById("savingsForm");
	let text2 = document.getElementById("checkingForm");
	
	if (localStorage.getItem("selected radio") == "savings") {
		if (text2.style.display == "block") {
			text2.style.display = "none";
		}
		
		if (text != null) {
			if (text.style.display == "none") {
				text.style.display = "block";
			} else {
				text.style.display = "none";
			}
		}
	} else if (localStorage.getItem("selected radio") == "checking") {
		
		if (text.style.display == "block") {
			text.style.display = "none";
		}
		
		if (text2 != null) {
			if (text2.style.display == "none") {
				text2.style.display = "block";
			} else {
				text2.style.display = "none";
			}
		}
	}
	
	
	
}

function hideRadioForm(status) {	
	if (status == true) {
		document.getElementById("TypeofAccount").style.display = "none";
		if (localStorage.getItem("selected radio") == "checking") {
			document.getElementById("InitialAmount").style.display = "block";
		} else if (localStorage.getItem("selected radio")  == "savings") {
			document.getElementById("InitialAmountSavings").style.display = "block";
		}
		
	}
	
}

function storeCredentials(firstName, lastName, SSN) {
	if (typeof(Storage) !== "undefined") {
		localStorage.setItem(firstName + " " + lastName, SSN);
	}
	return true;
}

function ToggleOther() {
	let text = document.getElementById("PassportNum");
	let text2 = document.getElementById("StateNum");
	
	if (text.style.display == "none") {
		text.style.display = "block";
	} else {
		text.style.display = "none";
	}
	
	if (text2.style.display == "none") {
		text2.style.display = "block";
	} else {
		text2.style.display = "none";
	}
	
	document.getElementById("SSN").required = false;

}

function checkCredentials(string1, string2) {
	
	let flag1 = checkSSN(string1);
	let flag2 = checkDriverLicense(string2);
	
	if (flag1 == false || flag2 == false) {
		return false;
	}
	
	alert("Valid Credentials");
	return true;
	
	
}

function checkDriverLicense(string) {
	if (string.length != 13) {
		alert("Invalid Driver's License");
		return false;
	}
	if (string.charAt(0).charCodeAt(0) < 65 || string.charAt(0).charCodeAt(0) > 90) {
		alert("Invalid Driver's License");
		return false;
	}
	for (let i = 1; i < string.length; i++) {
		if (string.charAt(i).charCodeAt(i) < 48 || string.charAt(i).charCodeAt(i) > 57) {
			alert("Invalid Driver's License");
			return false;
		}
	}

	return true;
	
	
}
function checkSSN(string) {
	
	if (string != "") {
		if (string.length != 11) {
			alert("Social Security Number Invalid");
			return false;
		}
		let hypenCount = 0;
		let i = 0;
		for (i = 0; i < string.length; i++) {
			if (string.substring(i, i+1) === "-") {
				hypenCount++;
			}
		}
		
		if (hypenCount < 2 || hypenCount > 2) {
			alert("Social Security Number Invalid");
			return false;
		}
		
		let tempStr = string;
		
		
		let firstPart = "";
		let firstPartIdx = tempStr.indexOf("-");
		firstPart = tempStr.substring(0, firstPartIdx);
		if (firstPart.length != 3) {
			alert("Social Security Number Invalid");
			return false;
		}
		
		if (checkIfDigitsEqual(parseInt(firstPart)) == true) {
			alert("Social Security Number Invalid");
			return false;
		}
		
		tempStr = tempStr.substring(firstPartIdx + 1);
		let secondPart = "";
		let secondPartIdx = tempStr.indexOf("-");
		secondPart = tempStr.substring(0, secondPartIdx);
		
		if (secondPart.length != 2) {
			alert("Social Security Number Invalid");
			return false;
		}
			
		tempStr = tempStr.substring(secondPartIdx + 1);
		
		if (tempStr.length != 4) {
			alert("Social Security Number Invalid");
			return false;
		}
	}
	
	return true;
	
}

function checkIfDigitsEqual(int) {
	let num = 0;
	
	let length = int.toString().length;
	
	num = ((Math.pow(10, length) - 1)) / (10 - 1);
	
	num *= int % 10;
	
	if (num == int) {
		return true;
	}
	
	return false;
}

