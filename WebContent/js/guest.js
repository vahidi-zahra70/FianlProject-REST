//The root URL for the RESTful services
var rootURL = "http://localhost:8088/ProjectFinal/prj";

$('#btnshow').click(function() {
	find()
});

$("#login").submit(function(){
	validateUser()
});

$("#register").submit(function(){
	registerUser()
});

function find() {
	console.log('addWine');
	$.ajax({
		type: 'GET',
		url: rootURL + '/users/va',
		success: function(){
			alert('Wine created successfully');


		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addWine error: ' + textStatus);
		}
	});
}



//validate a user
function validateUser() {
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
			alert("Sorry your username or password is wrong!");
		}
		else{
			alert("kkkkkkk");
		}
	})
	.fail( function (jqXHR, status, error) {
		alert(jqXHR.status);
	})
}
//Register a new user
function registerUser() {
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		async: false,
		cache: false,
		url: rootURL + '/users/adduser',
		data: formToJsonRegister(),
	})
	.done( function (responseText) {
		if(responseText=="not exist"){
			alert("Sorry your username or password is wrong!");
		}
		else{
			alert("kkkkkkk");
		}
	})
	.fail( function (jqXHR, status, error) {
		alert(jqXHR.status);
	})
}



//Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	return JSON.stringify({
		"username": $('#name').val(),
		"password": $('#pass').val()
	});
}
function formToJsonRegister() {
	return JSON.stringify({
		"username": $('#nameRegister').val(),
		"password": $('#pass2').val()
	});
}

function formToJSON2() {
	var x="rrrrrrrrrrrrrrrr"
		return x

		;
}