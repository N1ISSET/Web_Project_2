document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector(".admin-form");
    const passwordField = document.getElementById("password");
    const confirmPasswordField = document.getElementById("confirm-password");
    const errorDiv = document.getElementById("password-error");

    form.addEventListener("submit", function (event) {
        // Clear any existing error message
        errorDiv.textContent = "";

        // Check if passwords match
        if (passwordField.value !== confirmPasswordField.value) {
            // Prevent form submission
            event.preventDefault();

            // Show the error message
            errorDiv.textContent = "Passwords do not match. Please try again.";
            confirmPasswordField.focus();
        }
    });
});

