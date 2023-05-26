# Registro Elettronico Universitatio RESTAPI
Registro Elettronico Universitatio RestAPI basato su Java Spring-Boot

## PRIMA DI INIZIARE

Configurare il file application.properties con i relativi dati personalizzati (dati dell'amministratore, dati di connessione al DB)

Effettuare successivamente una chiamata GET all'endpoint http://nomehost:porta/api/auth/firstboot

Se si ottengono indietro un'entità User con StatusCode 201, si possono utilizzare i dati forniti per il login

## ENDPOINTs

Gli endpoint sono tutti documentati tramite OpenAPI e Swagger.

Possono essere consultati in formato JSON tramite una richiesta GET a http://nomehost:porta/api/docs 

oppure

consultando http://nomehost:porta/api/swagger-ui.html da browser web.

## AUTHENTICATION

Tutti gli endpoint sono protetti da Autenticazione JWT Based, e Ruoli (Admin, Docente, Studente).

Le documentazioni sono consultabili senza autenticazione, in caso di utilizzo in Produzione vanno preventivamente disabilitati o filtrati.

## USER INTERFACE

E' disponibile un'interfaccia FrontEnd, progettata in concomitanza con questo API in occasione del Capstone Project di EPICODE.

Consulta il mio Git --> https://github.com/maioranav/registroelettronicofe

