const firstName = document.getElementById("first_name");
const lastName = document.getElementById("last_name");
const email = document.getElementById("email");
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

    // Validate Email
    if (!email.value.trim()) {
        setError("email", "Please enter your email.");
        hasErrors = true;
    } else if (!/^\S+@\S+\.\S+$/.test(email.value)) {
        setError("email", "Please enter a valid email address.");
        hasErrors = true;
    }

    if (hasErrors) {
        e.preventDefault(); // Prevent form submission if there are errors
    }
});
