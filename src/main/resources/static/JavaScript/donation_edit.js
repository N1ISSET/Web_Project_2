const firstName = document.getElementById("first_name");
const lastName = document.getElementById("last_name");
const email = document.getElementById("email");
const mobile = document.getElementById("mobile");
const amount = document.getElementById("amount");
const cardNumber = document.getElementById("card_number");
const donationDate = document.getElementById("donation_date");
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
    document.querySelectorAll(".error-message").forEach(span => span.innerText = "");
}

form.addEventListener("submit", (e) => {
    clearErrors(); // Clear existing error messages

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

    // Validate Mobile
    if (!mobile.value.trim()) {
        setError("mobile", "Please enter your mobile number.");
        hasErrors = true;
    } else if (!/^\d+$/.test(mobile.value)) {
        setError("mobile", "Mobile number should not include letters.");
        hasErrors = true;
    }

    // Validate Amount
    if (!amount.value.trim()) {
        setError("amount", "Please enter your donation amount.");
        hasErrors = true;
    } else if (!/^\d+$/.test(amount.value) || parseInt(amount.value) < 5) {
        setError("amount", "Donation amount must be greater than 5.");
        hasErrors = true;
    }

    // Validate Card Number
    if (!cardNumber.value.trim()) {
        setError("card-number", "Please enter your card number.");
        hasErrors = true;
    } else if (!/^\d+$/.test(cardNumber.value)) {
        setError("card-number", "Card number should not include letters.");
        hasErrors = true;
    }

    // Validate Donation Date
    if (!donationDate.value.trim()) {
        setError("donation-date", "Please enter your donation date.");
        hasErrors = true;
    } else {
        const dateValue = new Date(donationDate.value);
        const today = new Date();
        if (isNaN(dateValue.getTime())) {
            setError("donation-date", "Please enter a valid date (YYYY-MM-DD).");
            hasErrors = true;
        } else if (dateValue > today) {
            setError("donation-date", "Donation date cannot be in the future.");
            hasErrors = true;
        }
    }

    // Prevent form submission if there are errors
    if (hasErrors) {
        e.preventDefault();
    }
});
