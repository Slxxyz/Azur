function updateQuantity(productId, quantity) {
    fetch('/firstSpring/panier/update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({productId: productId, quantity: quantity})
    })
        .then(response => response.text()) // Read the response as text
        .then(text => {
            try {
                const data = JSON.parse(text); // Try to parse the text as JSON
                if (data.success) {
                    const newSubTotal = parseFloat(data.newSubTotal).toFixed(2);
                    const totalAmount = parseFloat(data.totalAmount).toFixed(2);
                    // Mettre à jour le sous-total et le montant total
                    document.querySelector(`#subtotal-${productId}`).textContent = newSubTotal + ' €';
                    console.log('subtotal updated successfully');
                    document.querySelector('#totalAmountMen').textContent = totalAmount + ' €';
                    console.log('Quantity updated successfully');
                } else {
                    console.error('Failed to update quantity');
                }
            } catch (error) {
                console.error('Error parsing JSON:', error);
                console.error('Response text:', text); // Log the response text
            }
        });

}

document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.quantity-input').forEach(input => {
        input.addEventListener('change', (event) => {
            const productId = event.target.dataset.productId;
            const quantity = event.target.value;
            updateQuantity(productId, quantity);
        });
    });
});


function removeOrderLine(productId) {
    console.log('Removing order line:', productId);
    fetch('/firstSpring/panier/remove', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({productId: productId})
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                // Supprimer la ligne de commande de l'interface utilisateur
                document.querySelector(`#order-line-${productId}`).remove();
                if (data.isOrderCustomerDeleted) {
                    // S'il n'y a plus de lignes de commande, afficher un message
                    document.querySelector('#totalAmountMen').textContent = '0.00 €';
                }else {
                    // Mettre à jour le montant total
                    document.querySelector('#totalAmountMen').textContent = data.totalAmount + ' €';
                }
            } else {
                console.error('Failed to remove order line:', data.error);
            }
        })
        .catch(error => console.error('Error:', error));
}

document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.remove-button').forEach(button => {
        button.addEventListener('click', (event) => {
            event.preventDefault();
            const productId = button.dataset.productId;
            removeOrderLine(productId);
        });
    });
});