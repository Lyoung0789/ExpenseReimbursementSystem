let pendingTableBody = document.querySelector("#pending-table-body")

window.onload = function(){
	getManagerInfo(); 
	getPendingExpenses(); 
		
}

function getManagerInfo(){
    fetch("http://localhost:8080/ExpenseReimbursementSystem/ers/manager")
    .then(response => response.json())
    .then(data => updateManagerCard(data))
    .catch(error => console.log(error)); 
}

function updateManagerCard(data){
	console.log(data); 
    let firstName = document.querySelector("#first_name"); 
    let lastName = document.querySelector("#last_name"); 
	let city = document.querySelector("#city"); 
	let state = document.querySelector("#state"); 

    firstName.append(data.firstName); 
    lastName.append(data.lastName); 
   
	city.append(data.city); 
	state.append(data.state);
	
}

function getPendingExpenses(){
	fetch ("http://localhost:8080/ExpenseReimbursementSystem/ers/allPendingExpenses")
    .then(response => response.json())
    .then(data  => updatePendingCard(data))
    .catch(error => console.log(error));
}

function updatePendingCard(data){
	console.log(data); 
	for(let i = 0; i < data.length; i++){
        let date = new Date(data[i].dateRequested);
		let stringDate = date.toString(); 
		
        let tr = document.createElement("tr"); 
        tr.innerHTML =`
			<td>${data[i].expense_id}</td>
            <th scope="row">${stringDate.slice(0,15)}</th>
            <td>${data[i].category}</td>
            <td>$${data[i].total}</td>
        `;
        pendingTableBody.appendChild(tr); 
    }
}
