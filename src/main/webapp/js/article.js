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

document.addEventListener("DOMContentLoaded", () => {
    const addToCartButtons = document.querySelectorAll(".add-to-cart");

    addToCartButtons.forEach(button => {
        button.addEventListener("click", event => {
            event.preventDefault();

            console.log(document.getElementById('quantity').value)
            const productId = button.dataset.productId; // ID du produit
            const quantity = parseInt(document.getElementById('quantity').value) || 1; // Quantité (par défaut 1)

            // Vérifier si l'utilisateur est connecté
            fetch("/firstSpring/connexion/check", { method: "GET" }) // Créer un endpoint pour vérifier l'authentification
                .then(response => response.json())
                .then(data => {
                    if (data.isAuthenticated) {
                        // Si l'utilisateur est connecté
                        addToCartForAuthenticatedUser(productId, quantity);
                    } else {
                        // Si l'utilisateur n'est pas connecté
                        addToCartForGuest(productId, quantity);
                    }
                })
                .catch(error => console.error("Erreur d'authentification :", error));
        });
    });
});

// Ajouter au panier pour un utilisateur connecté
function addToCartForAuthenticatedUser(productId, quantity) {
    fetch("firstSpring/panier/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ productId: productId, quantity: quantity })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert("Produit ajouté au panier !");
                console.log("Panier mis à jour :", data.shoppingCart);
            } else {
                alert("Erreur lors de l'ajout au panier : " + data.error);
            }
        })
        .catch(error => console.error("Erreur lors de l'ajout au panier :", error));
}

// Ajouter au panier pour un utilisateur non connecté
function addToCartForGuest(productId, quantity) {
    const cart = JSON.parse(localStorage.getItem("guestCart")) || {};
    if (cart[productId]) {
        cart[productId] += quantity; // Ajouter à la quantité existante
    } else {
        cart[productId] = quantity; // Ajouter un nouvel article
    }

    console.log(productId, quantity);
    localStorage.setItem("guestCart", JSON.stringify(cart));
    console.log("Panier temporaire :", cart);
}


