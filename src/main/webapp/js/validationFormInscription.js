document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById('inscriptionForm');

    form.addEventListener('submit', function (e) {
        let hasError = false;

        // Réinitialiser les messages d'erreur
        document.querySelectorAll('.error-message').forEach(msg => {
            msg.textContent = '';
            msg.classList.remove('active');
        });

        // Récupérer les champs
        const firstName = document.getElementById('firstName').value.trim();
        const lastName = document.getElementById('lastName').value.trim();
        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value;
        const telephone = document.getElementById('telephone').value.trim();
        const codePostal = document.getElementById('code-postal').value.trim();
        const rue = document.getElementById('rue').value.trim();
        const numero = document.getElementById('numero').value.trim();
        const lettre = document.getElementById('lettre').value.trim();
        const ville = document.getElementById('ville').value.trim();
        const pays = document.getElementById('pays').value.trim();

        // Vérification des champs obligatoires
        if (!firstName) {
            const error = document.getElementById('error-firstName');
            error.textContent = "Le prénom est obligatoire.";
            error.classList.add('active');
            hasError = true;
        }

        if (!lastName) {
            const error = document.getElementById('error-lastName');
            error.textContent = "Le nom est obligatoire.";
            error.classList.add('active');
            hasError = true;
        }

        if (!rue) {
            const error = document.getElementById('error-rue');
            error.textContent = "La rue est obligatoire.";
            error.classList.add('active');
            hasError = true;
        }

        if (!numero) {
            const error = document.getElementById('error-numero');
            error.textContent = "Le numéro est obligatoire.";
            error.classList.add('active');
            hasError = true;
        }

        if (!ville) {
            const error = document.getElementById('error-ville');
            error.textContent = "La ville est obligatoire.";
            error.classList.add('active');
            hasError = true;
        }

        if (!pays) {
            const error = document.getElementById('error-pays');
            error.textContent = "Le pays est obligatoire.";
            error.classList.add('active');
            hasError = true;
        }

        // Vérification du format de l'email
        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        if (!emailPattern.test(email)) {
            const error = document.getElementById('error-email');
            error.textContent = "L'adresse email n'est pas valide.";
            error.classList.add('active');
            hasError = true;
        }

        // Vérification de la complexité du mot de passe
        const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
        if (!passwordPattern.test(password)) {
            const error = document.getElementById('error-password');
            error.textContent = "Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule et un chiffre.";
            error.classList.add('active');
            hasError = true;
        }

        // Vérification du numéro de téléphone (facultatif, mais format correct si présent)
        const phonePattern = /^[0-9]{9,11}$/;
        if (telephone && !phonePattern.test(telephone)) {
            const error = document.getElementById('error-telephone');
            error.textContent = "Le numéro de téléphone doit être composé de 9 à 11 chiffres.";
            error.classList.add('active');
            hasError = true;
        }

        // Vérification du code postal (4 à 6 chiffres)
        const postalCodePattern = /^[0-9]{4,6}$/;
        if (!postalCodePattern.test(codePostal)) {
            const error = document.getElementById('error-code-postal');
            error.textContent = "Le code postal doit être composé de 4 à 6 chiffres.";
            error.classList.add('active');
            hasError = true;
        }

        // Vérification du format du numéro et de la lettre (lettre facultative, format correct si présente)
        const numberPattern = /^[0-9]+$/;
        const letterPattern = /^[a-zA-Z]?$/;

        if (!numberPattern.test(numero)) {
            const error = document.getElementById('error-numero');
            error.textContent = "Le numéro doit être uniquement composé de chiffres.";
            error.classList.add('active');
            hasError = true;
        }

        if (lettre && !letterPattern.test(lettre)) {
            const error = document.getElementById('error-lettre');
            error.textContent = "La lettre doit être une seule lettre alphabétique (facultatif).";
            error.classList.add('active');
            hasError = true;
        }

        // Bloquer l'envoi si des erreurs sont détectées
        if (hasError) {
            e.preventDefault();
        }
    });
});