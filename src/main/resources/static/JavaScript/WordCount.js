document.addEventListener('DOMContentLoaded', function () {
    const text_max = 30;
    const countMessage = document.getElementById('count_message');
    const messageInput = document.getElementById('message');

    countMessage.textContent = `0 / ${text_max}`;
    console.log(messageInput)
    messageInput.addEventListener('input', function () {

        const text_length = messageInput.value.length;
        countMessage.textContent = `${text_length} / ${text_max}`;
    });
});
