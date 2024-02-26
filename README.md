**PROGETTO IDS 23/24** :world_map:

Autori: Michele Polenta, Matteo Giaccaglia

L’applicativo che abbiamo sviluppato ha l’incarico di poter gestire una piattaforma di contribuzione, il tutto su scala comunale.
Un nuovo utente non registrato che si interfaccia con il nostro progetto potrà vedere una mappa comunale nella quale saranno presenti punti di interesse ed itinerari, i quali saranno stati caricati sulla piattaforma da altri utenti che prendono il nome di contributor e contributor autorizzati.
La differenza sostanziale tra questi ultimi due è la gestione del pending, infatti i contributor autorizzati potranno caricare/eliminare/modificare i contenuti sulla piattaforma, i contributor non autorizzati invece quando vorranno fare queste tre azioni compileranno delle richieste, le quali verranno salvate e potranno essere accettate o meno dai curatori. Quest’ultimi oltre a gestire le richieste potranno anche eseguire tutte le mansioni dei contributor autorizzati.
Per ricoprire un determinato ruolo l’utente dovrà registrarsi sulla piattaforma, successivamente quando eseguirà il login, in base al ruolo con il quale si è registrato, potra avere accesso a determinate funzionalità.
Attualmente il nostro applicativo prende in esame il comune di Ancona, però il database grazie alla table “comuni” è già predisposto per rivolgersi a più comuni di riferimento.



**Code & Database**

SpringBoot :computer:

Per quanto riguarda la stesura del codice java, quest'ultimo è stato scritto sfruttando il framework springboot.

Sprinboot consente non solo di facilitare le interazioni con la base di dati tramite le JPA Repository, permette inoltre di creare un'applicativo web che , tramite i rest controller, elabora e restituisce degli oggetti json.

ELEPHANT SQL :elephant:

La base di dati è stata creata tramite PostgreSQL, successivamente caricata su ElephantSql, in maniera di rendere il db accessibile ovunque al di fuori del proprio dispositivo.

**JWT AUTHENTICATION**

Affinchè la registazione e relativo login dell'utente finale possa essere sicura, il codice sarà in grado di riptare la password nel momento dell'accesso, restituendo un token 
che dopo il login sarà rilasciato all'utente, in maniera anche da permettergli di accedere solo a determinate funzionalità.
Infatti se, ad esempio, l'utente si è registrato come contributor, in questo modo non gli sarà consentito di accedere alle funzionalità del contributor autorizzato o di ruoli 
per i quali non possiede l'account.


**CREDENZIALI ACCESSO ELEPHANT SQL** :unlock:

Le credenziali sono già specificate nelle properties su springboot, quindi lato codice non dovrebbero presentarsi problematiche.
Allo stesso tempo se si desidera accedere al database tramite strumenti esterni, ad esempio DataGrip, quà sotto satanno presenti username e password.

Username: nhaxkrfg

Password: lzDxSDrrGglA-KFt9iYgRTXbmYbwEX2K

Url: postgres://nhaxkrfg:lzDxSDrrGglA-KFt9iYgRTXbmYbwEX2K@dumbo.db.elephantsql.com/nhaxkrfg

Server: dumbo.db.elephantsql.com

**COME AVVIARE L'APPLICATIVO**

Per avviare sarà sufficiente utilizzare il comando predisposto di springboot(mvn spring-boot:run), un volta che il localhost sarà partito bastera sfruttare il rest controller.
Tramite strumenti com postman si potranno sfruttare le diverse tipologie di chiamata http, quindi una volta scelto l'url relativo alla funzionalità che si vuole provare, basterà compilare
i campi richiesti dal metodo del controller.

