# Registro Elettronico Universitatio RESTAPI
Registro Elettronico Universitatio RestAPI basato su Java Spring-Boot

## Prima di iniziare

Configurare il file application.properties con i relativi dati personalizzati (dati dell'amministratore, dati di connessione al DB)

Effettuare successivamente una chiamata GET all'endpoint nomehost:porta/api/auth/firstboot

Se si ottengono indietro un'entità User con StatusCode 401, si possono utilizzare i dati forniti per il login
