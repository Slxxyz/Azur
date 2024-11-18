document.addEventListener('DOMContentLoaded', () => {
    const input = document.getElementById('quantity');

    document.querySelectorAll('.decrement').forEach(btn => {
        btn.addEventListener('click', () => {
            input.value = Math.max(1, parseInt(input.value) - 1);
        });
    });

    document.querySelectorAll('.increment').forEach(btn => {
        btn.addEventListener('click', () => {
            input.value = parseInt(input.value) + 1;
        });
    });
});
