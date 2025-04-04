const firstName = document.getElementById("first_name");
const lastName = document.getElementById("last_name");
const age = document.getElementById("age");
const mobile = document.getElementById("mobile");
const email = document.getElementById("email");
const city = document.getElementById("city");
const country = document.getElementById("country");
const form = document.querySelector("form");

// Helper function to set error messages
function setError(id, message) {
    const errorElement = document.getElementById(`error-${id}`);
    if (errorElement) {
        errorElement.innerText = message;
        errorElement.style.color = "red";
    }
}

// Helper function to clear all error messages
function clearErrors() {
    document.querySelectorAll(".error-message").forEach(span => (span.innerText = ""));
}

form.addEventListener("submit", (e) => {
    clearErrors(); // Clear previous error messages

    let hasErrors = false;

    // Validate First Name
    if (!firstName.value.trim()) {
        setError("first-name", "Please enter your first name.");
        hasErrors = true;
    } else if (/\d/.test(firstName.value)) {
        setError("first-name", "First name cannot contain numbers.");
        hasErrors = true;
    }

    // Validate Last Name
    if (!lastName.value.trim()) {
        setError("last-name", "Please enter your last name.");
        hasErrors = true;
    } else if (/\d/.test(lastName.value)) {
        setError("last-name", "Last name cannot contain numbers.");
        hasErrors = true;
    }

    // Validate Age
    if (!age.value.trim()) {
        setError("age", "Please enter your age.");
        hasErrors = true;
    } else if (!/^\d+$/.test(age.value) || parseInt(age.value) < 18 || parseInt(age.value) > 65) {
        setError("age", "Age should be between 18 and 65.");
        hasErrors = true;
    }

    // Validate Mobile
    if (!mobile.value.trim()) {
        setError("mobile", "Please enter your mobile number.");
        hasErrors = true;
    } else if (!/^\d+$/.test(mobile.value)) {
        setError("mobile", "Mobile number should contain only digits.");
        hasErrors = true;
    }

    // Validate Email
    if (!email.value.trim()) {
        setError("email", "Please enter your email.");
        hasErrors = true;
    } else if (!/^\S+@\S+\.\S+$/.test(email.value)) {
        setError("email", "Please enter a valid email address.");
        hasErrors = true;
    }

    // Validate City
    if (!city.value.trim()) {
        setError("city", "Please enter your city.");
        hasErrors = true;
    } else if (!/^[A-Za-z\s]+$/.test(city.value)) {
        setError("city", "City name must contain only letters.");
        hasErrors = true;
    }

    // Validate Country
    if (!country.value.trim()) {
        setError("country", "Please enter your country.");
        hasErrors = true;
    } else if (!/^[A-Za-z\s]+$/.test(country.value)) {
        setError("country", "Country name must contain only letters.");
        hasErrors = true;
    }

    if (hasErrors) {
        e.preventDefault(); // Prevent form submission if there are errors
    }
});
