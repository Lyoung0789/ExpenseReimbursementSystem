console.log("I am in here"); 

/*
window.onload = function(){
    document.querySelector("#emp-submit-btn").addEventListener("click", empLogin); 
}

function empLogin(event){
    event.preventDefault(); 
    let employeeEmail = document.querySelector("#emp-email").value; 
	let employeePassword = document.querySelector("#login-password").value; 
	
	let xhttp = new XMLHttpRequest(); 
	
	console.log("Submitting the post request")
	xhttp.open("POST", "http://localhost:8080/ExpenseReimbursementSystem/ers/login")
	xhttp.send(`email=${employeeEmail}&password=${employeePassword}`); 
		
	
	
   
}

*/