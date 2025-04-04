document.addEventListener('DOMContentLoaded', () => {
    const options = document.querySelectorAll('.option');
    const inputField = document.getElementById('amount-input');

    if (options.length === 0 || !inputField) {
        console.error('Required elements not found');
        return;
    }

    options.forEach(option => {
        option.addEventListener('click', () => {
            // Remove 'selected' class from all buttons
            options.forEach(opt => opt.classList.remove('selected'));

            // Add 'selected' class to the clicked button
            option.classList.add('selected');

            // Update the input field with the selected button's value
            const value = option.textContent.replace('USD', '').trim();
            inputField.value = value;
        });
    });

    // Prevent non-numeric input in the input field
    inputField.addEventListener('input', () => {
        inputField.value = inputField.value.replace(/[^0-9]/g, ''); // Allow only numbers
    });

    // Optionally prevent invalid key presses (for extra UX improvement)
    inputField.addEventListener('keydown', (event) => {
        // Allow special keys: backspace, delete, arrows, etc.
        const allowedKeys = ['Backspace', 'Delete', 'ArrowLeft', 'ArrowRight', 'Tab'];
        if (
            !allowedKeys.includes(event.key) && // Allow special keys
            !/^\d$/.test(event.key) // Allow digits (0-9)
        ) {
            event.preventDefault();
        }
    });
});
