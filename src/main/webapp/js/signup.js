function validate(){

    let name = document.getElementById("user_name").value;
    let password = document.getElementById("password").value;
    let number = document.getElementById("phone").value;
    let email = document.getElementById("email").value;
    if(name.length < 3){ alert("Please enter minimum 3 characters in name");return false;}
    if(email.length < 5){ alert("Please enter minimum 5 characters in email");return false;}
    if(password.length < 6){ alert("Please enter minimum 6 characters in password");return false;}
}

function createNewUser(){
    if(!validate()) return;
    let name = document.getElementById("user_name").value;
    let password = document.getElementById("password").value;
    let number = document.getElementById("phone").value;
    let email = document.getElementById("email").value;


}