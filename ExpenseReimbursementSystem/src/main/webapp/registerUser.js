console.log("register user AJAX request")




function handleSubmit(event){
    event.preventDefault(); 
    let newUser = {
        firstName: document.querySelector("#fName").value, 
        lastName:  document.querySelector("#lName").value,  
        username: document.querySelector("#email").value, 
        password:  document.querySelector("#password").value, 
        streetAddress:  document.querySelector("#streetAddress").value, 
        city: document.querySelector("#city").value, 
        state: document.querySelector("#state").value, 
        zipcode: document.querySelector("#zipcode").value, 
        title: document.querySelector("#positionForm").value 
    }; 
    
    console.log(newUser); 

    fetch("http://localhost:8080/ExpenseReimbursementSystem/ers/newuser", {
        method: 'post', 
		headers: {
		    'Content-Type': 'application/json',
		  },
        body: JSON.stringify(newUser)
    }).then(response => {
		console.log(response.status); 
		if (response.redirected) {
			
            window.location.href = response.url;
        }
		
	})
    .then(data => console.log(data))
	.catch(error => alert(error + "Sorry that Username has already been taken, you will be redirected to the Landing Page."))
    
   
}

