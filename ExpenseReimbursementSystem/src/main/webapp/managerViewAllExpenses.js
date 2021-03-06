let cardBody = document.querySelector("#insert-cards"); 
window.onload = function(){
    fetchAllPendingExpenses();
    //createPendingCards([1,2,3])
}

function fetchAllPendingExpenses(){
    fetch("http://localhost:8080/ExpenseReimbursementSystem/ers/fetchAllExpenses")
    .then(response => response.json())
    .then(data => createPendingCards(data))
    .catch(error => console.log(error)); 
}

function createPendingCards(data){
	console.log(data); 
    for(let i = 0; i < data.length; i++){
       let date = new Date(data[i].dateRequested);
		//let stringDate = date.toString(); 
		
        let divCard = document.createElement("div"); 
        divCard.innerHTML =`
            <div class="card">
                        <div class="card-body">
                          
                         
                              
                               <div class="form-group">
								<p>Expense Request Number: <span id="expense_id">${data[i].expense_id}</span></p>
                                <p>Name: <span id="fName">${data[i].f_name}</span> <span id="lName">${data[i].l_name}</span></p> 
                         		<p>Employee ID: <span id="user_id">${data[i].user_id}</span></p>
                                <p>Category: <span id="category">${data[i].category}</span></p>
                                <p> 
                                    Date Requested: <span id="dateRequested">${date}</span>
                                </p> 
                                <p>
                                    Time frame of the expense: <span id="dateFrom">${data[i].dateFrom}</span> to <span id="dateTo">${data[i].dateTo}</span>
                                </p>
                                <p>
                                    Description: <span id="description"> ${data[i].description}</span>
                                </p>

                                <p>
                                    Status: <span id="status">${data[i].status}</span>
                                </p>
                                <p>
                                    Total: <span id="total">$${data[i].total}</span>
                                </p>
                              </div>
                                <br>
                              
                              
                            
            </div>   
        `;
        cardBody.appendChild(divCard); 
    }
}

function handlePending(event){
    event.preventDefault(); 
    
	fetch("http://localhost:8080/ExpenseReimbursementSystem/ers/view/pending/Expense")
    .then(response => response.json())
    .then(data => {
		removeChildNodes(); 
		createPendingCards(data)
	})
    .catch(error => console.log(error)); 
    return false; 
}

function handleApproved(event){
    event.preventDefault(); 
    
	fetch("http://localhost:8080/ExpenseReimbursementSystem/ers/view/accepted/Expense")
    .then(response => response.json())
    .then(data => {
	removeChildNodes(); 
	createPendingCards(data)
	})
    .catch(error => console.log(error)); 
    return false; 
}

function handleDenied(event){
    event.preventDefault(); 
    console.log("I am in the denied");
	fetch("http://localhost:8080/ExpenseReimbursementSystem/ers/view/Denied/Expense")
    .then(response => response.json())
    .then(data => {
	removeChildNodes();
	createPendingCards(data) 
	})
    .catch(error => console.log(error));  
    return false; 
}

/*
function handleApprove(event){
    event.preventDefault(); 
	
	let updateExpense = {
		total: parseFloat(event.target.parentElement.querySelector("#total").innerText),
		category: event.target.parentElement.querySelector("#category").innerText,
		dateFrom: event.target.parentElement.querySelector("#dateFrom").innerText,
		dateTo: event.target.parentElement.querySelector("#dateTo").innerText,
		dateRequested: event.target.parentElement.querySelector("#dateRequested").innerText,
		description: event.target.parentElement.querySelector("#description").innerText,
		expense_id: event.target.parentElement.querySelector("#expense_id").innerText,
		user_id: event.target.parentElement.querySelector("#user_id").innerText,
		denied: false, 
		status: "Accepted"
		
	}
	
	
	fetch("http://localhost:8080/ExpenseReimbursementSystem/ers/Expense", {
		method: "PUT", 
		body: JSON.stringify(updateExpense)	
	})
	.then(response => {
		if(response.redirected){
			window.location.href = response.url; 
		}	
		
	})
    .then(data => console.log(data))
	.catch(error => console.log(error))



}

function handleDeny(event){
    event.preventDefault(); 
    console.log(event.target.parentElement.querySelector("#expense_id").innerText); 
    let updateExpense = {
		total: parseFloat(event.target.parentElement.querySelector("#total").innerText),
		category: event.target.parentElement.querySelector("#category").innerText,
		dateFrom: event.target.parentElement.querySelector("#dateFrom").innerText,
		dateTo: event.target.parentElement.querySelector("#dateTo").innerText,
		dateRequested: event.target.parentElement.querySelector("#dateRequested").innerText,
		description: event.target.parentElement.querySelector("#description").innerText,
		expense_id: event.target.parentElement.querySelector("#expense_id").innerText,
		user_id: event.target.parentElement.querySelector("#user_id").innerText,
		denied: true, 
		status: "Denied"
		
	}
	
	
	fetch("http://localhost:8080/ExpenseReimbursementSystem/ers/Expense", {
		method: "PUT", 
		body: JSON.stringify(updateExpense)	
	})
	.then(response => {
		if(response.redirected){
			window.location.href = response.url; 
		}	
		
	})
    .then(data => console.log(data))
	.catch(error => console.log(error))

} */

function removeChildNodes(){
    while (cardBody.hasChildNodes()) {
        cardBody.removeChild(cardBody.lastChild);
    }
}