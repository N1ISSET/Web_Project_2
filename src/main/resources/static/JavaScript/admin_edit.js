document.getElementById('adminEditForm').addEventListener('submit', function (event) {
    const adminName = document.getElementById('admin_name');
    const password = document.getElementById('password');

    // Clear previous error messages
    document.querySelectorAll('.error-message').forEach(span => span.textContent = '');

    let hasError = false;

    // Validate Admin Name
    if (adminName.value.trim().length < 3) {
        document.getElementById('error-admin-name').textContent = 'Admin Name must be at least 3 characters long.';
        hasError = true;
    }

    // Validate Password
    if (password.value.trim().length < 6) {
        document.getElementById('error-password').textContent = 'Password must be at least 6 characters long.';
        hasError = true;
    } else if (!/^[a-zA-Z0-9]+$/.test(password.value.trim())) {
        document.getElementById('error-password').textContent = 'Password must contain only alphanumeric characters.';
        hasError = true;
    }

    // Prevent form submission if there are errors
    if (hasError) {
        event.preventDefault();
    }
});
