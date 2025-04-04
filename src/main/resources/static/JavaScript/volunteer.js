const firstname = document.getElementById("first_name");
const lastname = document.getElementById("last_name");
const age = document.getElementById("age");
const mobile = document.getElementById("mobile");
const email = document.getElementById("email");
const city = document.getElementById("city");
const country = document.getElementById("country");
const form = document.getElementById("form");
const agree = document.getElementById("agree");
const errorElement = document.getElementById("error");


form.addEventListener('submit', (e) => {
    let messages = [];

    // Validate First Name
    if (!firstname.value.trim()) {
        messages.push("Please enter your first name!");
    } else if (/\d/.test(firstname.value)) {
        messages.push("First name cannot contain numbers!");
    }

    // Validate Last Name
    if (!lastname.value.trim()) {
        messages.push("Please enter your last name!");
    } else if (/\d/.test(lastname.value)) {
        messages.push("Last name cannot contain numbers!");
    }

    // Validate Age
    if (!age.value.trim()) {
        messages.push("Please enter your age!");
    } else if (!/^\d+$/.test(age.value)) {
        messages.push("Age must be a number!");
    }

    // Validate Mobile
    if (!mobile.value.trim()) {
        messages.push("Please enter your mobile number!");
    } else if (!/^\d+$/.test(mobile.value)) {
        messages.push("Mobile number must contain only numbers!");
    }

    // Validate Email
    if (!email.value.trim()) {
        messages.push("Please enter your email!");
    } else if (!/^\S+@\S+\.\S+$/.test(email.value)) {
        messages.push("Please enter a valid email address!");
    }

    // Validate City
    if (!city.value.trim()) {
        messages.push("Please enter your city!");
    } else if (!/^[A-Za-z\s]+$/.test(city.value)) {
        messages.push("City name must contain only letters!");
    }

    // Validate Country
    if (!country.value.trim()) {
        messages.push("Please enter your country!");
    } else if (!/^[A-Za-z\s]+$/.test(country.value)) {
        messages.push("Country name must contain only letters!");
    }

    if (!agree.checked) {
        messages.push("You must agree to the Terms and Conditions!");
    }


    // Show Error Messages
    if (messages.length > 0) {
        e.preventDefault();
        errorElement.innerText = messages.join("\n");
        errorElement.style.color = "red";
    }
});
