// Get elements
const continueButton = document.querySelector('.custom-button');
const formPopup = document.getElementById('form-popup');
const amountInput = document.getElementById('amount-input');
const popupForm = document.getElementById('popup-form');
const closeButton = document.getElementById('close-popup');

// Helper functions
function showPopup() {
    formPopup.style.display = 'flex';
}

function hidePopup() {
    formPopup.style.display = 'none';
}

function validateFormData(firstName, lastName, email, card, amount) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const errors = [];

    if (!firstName) errors.push('First name is required!');
    if (!lastName) errors.push('Last name is required!');
    if (!email || !emailRegex.test(email)) errors.push('Please enter a valid email address!');
    if (!card || !/^\d{13,19}$/.test(card)) errors.push('Card number must be 13-19 digits!');

    // Validate Amount
    if (!amount || isNaN(amount) || amount <= 0) {
        errors.push('Amount must be a positive number!');
    } else if (amount < 5) {
        errors.push('Amount must be at least $5!');
    }

    return errors;
}

// Show the popup form only if an amount is entered
continueButton.addEventListener('click', () => {
    const amountValue = parseFloat(amountInput.value.trim());

    if (isNaN(amountValue) || amountValue <= 0) {
        alert('Please enter a valid amount!');
        return;
    }

    if (amountValue < 5) {
        alert('Amount must be at least $5!');
        return;
    }

    showPopup();
});

// Handle form submission with validation
popupForm.addEventListener('submit', (e) => {
    e.preventDefault();

    // Retrieve form data
    const firstName = document.getElementById('Firstname').value.trim();
    const lastName = document.getElementById('Lastname').value.trim();
    const email = document.getElementById('email').value.trim();
    const card = document.getElementById('card_number').value.trim();
    const amount = parseFloat(amountInput.value.trim());

    // Validate form data
    const errors = validateFormData(firstName, lastName, email, card, amount);

    if (errors.length > 0) {
        alert(errors.join('\n'));
        return;
    }

    console.log('First Name:', firstName);
    console.log('Last Name:', lastName);
    console.log('Email:', email);
    console.log('Card:', card);
    console.log('Amount:', amount);

    hidePopup();
});

// Close popup on button click
closeButton.addEventListener('click', hidePopup);
