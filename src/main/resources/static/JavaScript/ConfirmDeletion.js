function confirmDeletion(form) {
    const contactID = form.querySelector('input[name="id"]').value;
    return confirm(`Are you sure you want to delete the contact with ID: ${contactID}?`);
}