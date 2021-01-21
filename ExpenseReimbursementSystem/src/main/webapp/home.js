let pendingTableBody = document.querySelector("#pending-table-body"); 

window.onload = function(){
	getUserInfo(); 
	
	getPendingExpenses(); 
}

function getUserInfo(){
    fetch("http://localhost:8080/ExpenseReimbursementSystem/ers/user")
    .then(response => response.json())
    .then(data => updateUserCard(data))
    .catch(alert); 
}

function updateUserCard(data){
	console.log(data); 
    let firstName = document.querySelector("#first_name"); 
    let lastName = document.querySelector("#last_name"); 

    let position = document.querySelector("#position"); 
	let streetAddress = document.querySelector("#street-address")
	let city = document.querySelector("#city")
	let state = document.querySelector("#state")
	let zipcode = document.querySelector("#zipcode")
	
    firstName.append(data.firstName); 
    lastName.append(data.lastName); 
    position.append(data.title); 
    streetAddress.append(data.streetAddress);
    city.append(data.city); 
    state.append(data.state); 
    zipcode.append(data.zipcode);  
}




function getPendingExpenses(){
	console.log("Calling this"); 
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
		console.log("In here")
        let tr = document.createElement("tr"); 
        tr.innerHTML =`
            <th style="color:orange" scope="row">${stringDate.slice(0,15)}</th>
            <td>${data[i].category}</td>
            <td>$ ${data[i].total}</td>
        `;
        pendingTableBody.appendChild(tr); 
    }
	}