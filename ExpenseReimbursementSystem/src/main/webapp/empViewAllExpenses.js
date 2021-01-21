
let pendingTableBody = document.querySelector("#pending-table-body"); 
let pastTableBody = document.querySelector("#past-table-body"); 
window.onload = function (){
    getPendingExpenses();  
    getPastExpenses(); 
}



function getPendingExpenses(){
    fetch ("http://localhost:8080/ExpenseReimbursementSystem/ers/pendingExpenses")
     .then(response => response.json())
    .then(data  => DOMManipulation(data))
     .catch(alert); 
}

function DOMManipulation(data){
	console.log(data)
	for(let i = 0; i < data.length; i++){
        let date = new Date(data[i].dateRequested);
		let stringDate = date.toString(); 
		
        let tr = document.createElement("tr"); 
        tr.innerHTML =`
            <th scope="row">${stringDate.slice(0,15)}</th>
            <td>${data[i].category}</td>
            <td>$${data[i].total}</td>
            <button id="${data[i].expense_id}"type="button" onClick=handleClick(event) class="btn btn-warning btn-sm">Details</button>
           
        `;
        pendingTableBody.appendChild(tr); 
    }
}

function getPastExpenses(){
console.log("Did i make it here?"); 
	fetch("http://localhost:8080/ExpenseReimbursementSystem/ers/pastExpenses")
	.then(response => response.json())
	.then(data => pastTableManipulation(data))
	
}

function pastTableManipulation(data){
	console.log(data)
	for(let i = 0; i < data.length; i++){
        let date = new Date(data[i].dateRequested);
		let stringDate = date.toString(); 
		
        let tr = document.createElement("tr"); 
        tr.innerHTML =`
            <th scope="row">${stringDate.slice(0,15)}</th>
            <td>${data[i].category}</td>
            <td>$${data[i].total}</td>
            <td>${data[i].status}</td>
            <button id="${data[i].expense_id}"type="button" onClick=handleClick(event) class="btn btn-warning btn-sm">Details</button>
            
            
        `;
        pastTableBody.appendChild(tr); 
    }
}

function handleClick(event){
	console.log(event.target.id); 	
	fetch(`http://localhost:8080/ExpenseReimbursementSystem/ers/Expense?id=${event.target.id}`)
	.then(response => response.json())
	.then(data => {
		removeChildNodes();
		 createPendingCards(data);
	})
	
	
    
    
}

let cardBody = document.querySelector("#insert-card")
function createPendingCards(data){
	console.log(data)
    
    	let date = new Date(data.dateRequested);
		let stringDate = date.toString(); 
		
        let divCard = document.createElement("div"); 
        divCard.innerHTML =`
            <div class="card">
                        <div class="card-body">
                          
                         
                              <h5 class="card-title">Expense Info:  </h5>
                               <div class="form-group">
								<p>Expense Request Number: <span id="expense_id">${data.expense_id}</span></p>
                         
                                <p>Category: <span id="category">${data.category}</span></p>
                                <p> 
                                    Date Requested: <span id="dateRequested">${stringDate}</span>
                                </p> 
                                <p>
                                    Time frame of the expense: <span id="dateFrom">${data.dateFrom}</span> to <span id="dateTo">${data.dateTo}</span>
                                </p>
                                <p>
                                    Description: <span id="description">${data.description}</span>
                                </p>

                                <p>
                                    Status: <span id="status">${data.status}</span>
                                </p>
                                <p>
                                    Total: <span id="total">$${data.total}</span>
                                </p>
                              </div>
                                <br>
                              
                              
                            
            </div>   
        `;
        cardBody.appendChild(divCard); 
    
}

function removeChildNodes(){
    while (cardBody.hasChildNodes()) {
        cardBody.removeChild(cardBody.lastChild);
    }
}


