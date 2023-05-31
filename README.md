# Registro Elettronico Universitatio RESTAPI

Registro Elettronico Universitatio RestAPI basato su Java Spring-Boot

## PRIMA DI INIZIARE

Configurare il file application.properties con i relativi dati personalizzati (dati dell'amministratore, dati di connessione al DB)

Effettuare successivamente una chiamata GET all'endpoint https://nomehost:porta/api/auth/firstboot

Se si ottengono indietro un'entità User con StatusCode 201, si possono utilizzare i dati forniti per il login

## ENDPOINTs

Gli endpoint sono tutti documentati tramite OpenAPI e Swagger.

Possono essere consultati in formato JSON tramite una richiesta GET a https://nomehost:porta/api/docs

oppure

consultando https://nomehost:porta/api/swagger-ui.html da browser web.

## AUTHENTICATION

Tutti gli endpoint sono protetti da Autenticazione JWT Based, e Ruoli (Admin, Docente, Studente).

Le documentazioni sono consultabili senza autenticazione, in caso di utilizzo in Produzione vanno preventivamente disabilitati o filtrati.

## USER INTERFACE

E' disponibile un'interfaccia FrontEnd, progettata in concomitanza con questo API in occasione del Capstone Project di EPICODE.

Consulta il mio Git --> https://github.com/maioranav/registroelettronicofe

## SSL

Il Software gestisce SOLO chiamate HTTPs (potrebbe essere necessario autorizzare i certificati self-signed sul tuo sistema operativo/browser).

All'interno della cartella [resources] è presente un certificato pre-installato di prova.

- keytool -genkeypair -alias springboot -keyalg RSA -keysize 4096 -storetype JKS -keystore springboot.jks -validity 3650 -storepass password

- keytool -genkeypair -alias springboot -keyalg RSA -keysize 4096 -storetype PKCS12 -keystore springboot.p12 -validity 3650 -storepass password

Fonte: https://www.thomasvitale.com/https-spring-boot-ssl-certificate/

## NOTE

Alcune unità essenziali sono state testate con la suite di JUnit 5, potrebbe quindi essere necessario, in base al vostro IDE, importare nel classpath le suddette unità di test come libreria aggiuntiva.
