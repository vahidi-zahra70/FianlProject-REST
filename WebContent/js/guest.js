//The root URL for the RESTful services
//$('#URL').val();
var rootURL = "http://localhost:8088/ProjectFinal/prj";
//var rootURL = $('#URL').val()+"/ProjectFinal/prj";

$('#btnshow').click(function() {
	window.location.href=("http://localhost:8088/ProjectFinal/SeeContactGuestMember.html");
});

$('#btnshow2').click(function() {
	SeeAllContactManager()
});

$('#btnshow3').click(function() {
	SeeAllContactGuestMember()
});

$("#login").submit(function(){
	validateUser()
	return false;
});


$("#register").submit(function(){
	if($('#pass1').val()!=$('#pass2').val()){
		$("#duplicateUser").text("The tow passowrds which you entered are not same," +
		" please write your passwords properly.");
		$("#duplicateUser").css('color', 'red');
		$('#nameRegister').val("");
		$('#pass1').val("");
		$('#pass2').val("");

	}
	else{
		registerUser()
	}
	return false;
});

//for Member
$("#formAddContact").submit(function(){
	addContact()
	return false;
});


//for manager
$("#addContact").click(function(){
	if($('#nameContact').val()=="" ||
			$('#familyContact').val()=="" ||
			$('#homePhone').val()=="" ||
			$('#cellPhone').val()=="" ||
			$('#email').val()==""){
		$("#AddNewContact").text("Please insert all the fields.");
		$("#AddNewContact").css('color', 'red');

	}
	else{
		addContact()
	}

});
//for manager
$("#editContact").click(function(){
	if($('#nameContact').val()=="" ||
			$('#familyContact').val()=="" ||
			$('#homePhone').val()=="" ||
			$('#cellPhone').val()=="" ||
			$('#email').val()=="" || $('#contactID').val()==""){
		$("#AddNewContact").text("Please insert all the fields.");
		$("#AddNewContact").css('color', 'red');

	}	

	else{
		editContact()
	}
});



function find() {
	console.log('addWine');
	$.ajax({
		type: 'GET',
		url: rootURL + '/users/va',
		success: function(){
			window.location.href=("http://localhost:8088/ProjectFinal/UserHomePage.html");


		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addWine error: ' + textStatus);
		}
	});
}



//validate a user
function validateUser() {
	console.log('validate');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		async: false,
		cache: false,
		url: rootURL + '/users/validate',
		data: formToJSON(),
	})
	.done( function (responseText) {
		if(responseText=="not exist"){
			$("#unvalidUser").text("Unfortunately your username or passowrd is wrong, please try again");
			$("#unvalidUser").css('color', 'red');
			$('#name').val("");
			$('#pass').val("");

		}
		else if(responseText=="OK 3"){
			console.log(' entering a Member');
			window.location.href=("http://localhost:8088/ProjectFinal/UserHomePage.html");
			$('#name').val("");
			$('#pass').val("");
			$('#URL').val("");
		}
		else if(responseText=="OK 2"){
			console.log(' entering a Manager');
			window.location.href=("http://localhost:8088/ProjectFinal/ManagerHomePage.html");
			$('#name').val("");
			$('#pass').val("");
			$('#URL').val("");
		}
	})
	.fail( function (jqXHR, status, error) {
		alert(jqXHR.status);
	})
}
//Register a new user
function registerUser() {
	console.log('register');
	console.log(rootURL);
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		async: false,
		cache: false,
		url: rootURL + '/users/adduser',
		data: formToJsonRegister(),
	})
	.done( function (responseText) {
		if(responseText=="Saved successfully"){
			$("#duplicateUser").text("You successfully registered, the login page will dispaly for you in a few seconds!");
			$("#duplicateUser").css('color', 'blue');
			$('#nameRegister').val("");
			$('#pass1').val("");
			$('#pass2').val("");
			setTimeout("window.location.href='http://localhost:8088/ProjectFinal/Login.html';",4000);


		}
		else if(responseText=="NOT Saved") {
			$("#duplicateUser").text("The username which you chose already exists, " +
			"please change your username.");
			$("#duplicateUser").css('color', 'red');
			$('#nameRegister').val("");
			$('#pass1').val("");
			$('#pass2').val("");
		}
	})
	.fail( function (jqXHR, status, error) {
		alert(jqXHR.status);
	})

}
//Add a new contact
function addContact() {
	console.log('addContact');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		async: false,
		cache: false,
		url: rootURL + '/contacts',
		data: formToJsonContact(),
		success: function(responseText){
			$("#AddNewContact").text("The Contact successfully inserted to the phone book");
			$("#AddNewContact").css('color', 'blue');
			$('#nameContact').val("");
			$('#familyContact').val("");
			$('#homePhone').val("");
			$('#cellPhone').val("");
			$('#email').val("");


		},
		error: function(jqXHR, textStatus, errorThrown){
			alert(jqXHR.status);
		}
	});
}


//Edit a contact
function editContact() {
	console.log('editContact');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		async: false,
		cache: false,
		url: rootURL + '/contacts/' +$('#contactID').val(),
		data: formToJsonContact(),
		success: function(responseText){
			$("#AddNewContact").text(responseText);
			$("#AddNewContact").css('color', 'blue');
			$('#nameContact').val("");
			$('#familyContact').val("");
			$('#homePhone').val("");
			$('#cellPhone').val("");
			$('#email').val("");
			$('#contactID').val("");


		},
		error: function(jqXHR, textStatus, errorThrown){
			alert(jqXHR.status);
		}
	});
}


//See All contacts/ Manager
function SeeAllContactManager() {
	console.log('seeAllContacts');
	$.ajax({
		type: 'GET',
		url: rootURL+ '/contacts',
		dataType: "json", // data type of response
		success: function(data){
			$("#AllContactManager  td").parent().remove();
			var trHTML = '';
			$.each(data, function (i, item) {
				trHTML += '<tr><td>' + item.id + '</td><td>' + item.name + '</td><td>' + item.family + '</td><td>'+ item.homephone + '</td><td>'+ item.cellphone + '</td><td>'+ item.email +'</td><td><a  href="#" onclick="DeleteContact('+item.id+');">'+"Delete"+'</a></td></tr>';
			});
			$('#AllContactManager').append(trHTML);
		}
	});
};

//See All contacts/ Guest and Member
function SeeAllContactGuestMember() {
	console.log('seeAllContacts');
	$.ajax({
		type: 'GET',
		url: rootURL+ '/contacts',
		dataType: "json", // data type of response
		success: function(data){
			$("#AllContactGuestMember  td").parent().remove();
			var trHTML = '';
			$.each(data, function (i, item) {
				trHTML += '<tr><td>' + item.id + '</td><td>' + item.name + '</td><td>' + item.family + '</td><td>'+ item.homephone + '</td><td>'+ item.cellphone + '</td><td>'+ item.email +'</td></tr>';
			});
			$('#AllContactGuestMember').append(trHTML);
		}
	});
};


//Delete a contact
function DeleteContact(id) {
	console.log('deleteContact');
	$.ajax({
		type: 'DELETE',
		async: false,
		cache: false,
		url: rootURL + '/contacts/' + id,
		success: function(data, textStatus, jqXHR){
			SeeAllContactManager()();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert(jqXHR.status);
		}
	});

}
//Helper function to serialize all the form fields into a JSON string

//For validating
function formToJSON() {
	return JSON.stringify({
		"username": $('#name').val(),
		"password": $('#pass').val()
	});
}
//For registering
function formToJsonRegister() {
	return JSON.stringify({
		"username": $('#nameRegister').val(),
		"password": $('#pass2').val()
	});
}

//For adding and editing contact
function formToJsonContact() {
	return JSON.stringify({
		"name": $('#nameContact').val(),
		"family": $('#familyContact').val(),
		"homephone": $('#homePhone').val(),
		"cellphone": $('#cellPhone').val(),
		"email": $('#email').val()
	});
}

function formToJSON2() {
	var x="rrrrrrrrrrrrrrrr"
		return x

		;
}