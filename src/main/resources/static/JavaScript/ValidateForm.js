function validateForm() {
    const firstname = document.getElementById("firstname").value.trim();
    const lastname = document.getElementById("lastname").value.trim();
    const email = document.getElementById("email").value.trim();
    const message = document.getElementById("message").value.trim();
    const agree = document.getElementById("agree").checked;


    const firstnameError = document.getElementById("first-name-error");
    const lastnameError = document.getElementById("last-name-error");
    const emailError = document.getElementById("email-error");
    const messageError = document.getElementById("message-error");
    const agreeError = document.getElementById("agree-error");

    const errorFields = [firstnameError, lastnameError, emailError, messageError, agreeError];
    errorFields.forEach(errorField => errorField.textContent = "");

    let isValid = true;

    if (!firstname || /\d/.test(firstname)) {
        firstnameError.textContent = "Please enter valid firstname";
        isValid = false;
    }

    if (!lastname || /\d/.test(lastname)) {
        lastnameError.textContent = "Please enter vaild lastname";
        isValid = false;
    }

    if (!email || !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
        emailError.textContent = "Please enter a valid email address.";
        isValid = false;
    }

    if (!message) {
        messageError.textContent = "Message cannot be empty.";
        isValid = false;
    }

    if (!agree) {
        agreeError.textContent = "You must agree to the terms to proceed.";
        isValid = false;
    }
    return isValid;
}