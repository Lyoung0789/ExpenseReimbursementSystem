console.log("JS for newRequest")




function handleSubmit(event){
    event.preventDefault(); 
    let newRequest = {

        category: document.querySelector("#categoryForm").value, 
        dateFrom: document.querySelector("#dateFrom").value, 
        dateTo: document.querySelector("#dateTo").value, 
        total: document.querySelector("#amountRequested").value, 
        description: document.querySelector("#description").value

    }; 
    


     fetch("http://localhost:8080/ExpenseReimbursementSystem/ers/Expense", {
         method: 'post', 
         body: JSON.stringify(newRequest)
     })
     .then(response =>{ 
     	if(response.redirected){
     		window.location.href=response.url;
     	}
     	})
     
     .then(data => console.log(data))
     .catch(alert => console.log(alert))
   
}