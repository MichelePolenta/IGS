// Funzione per caricare i punti d'interesse dal backend
function caricaPuntiInteresse() {
    axios.get("/api/punti-interesse")
        .then(function (response) {
            // Gestisci la risposta con i dati dei punti d'interesse
            // ...
        })
        .catch(function (error) {
            // Gestisci l'errore
            // ...
        });
}

// Funzione per aggiungere un nuovo punto d'interesse (opzionale)
function aggiungiPuntoInteresse() {
    // ...
}

// Inizial
