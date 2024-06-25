# "SoPra"
Basic web application that allows registered users to communicate with each other to trade second-hand plants. Based on the idea of "Kleinanzeigen".

## Abgabehinweise
Abgabe Version auf main branch.

  Login u.a. mit folgenden Accounts möglich:

- Benutzername: User1 , Passwort:1234
- Benutzername: User2 , Passwort:5678
- Benutzername: Hans , Passwort:0987

 Sonstiges:

Und wie abgesprochen ist aus Gründen der Zugriffsrechte auf den PC der Endanwender (z.B. wir) die Möglichkeit Bilder 
hochzuladen reduziert auf Bilder einfügen per Drag&Drop in 'resources/static/images', 
um ein Bild für eine neue Anzeige einzustellen.

## Details
Developer of "SoPra" (GitHub-Username, full name):
- nijade, Nils Denk
- PhilippBurowStuttgart, Philipp Burow
- lino-antonino, Lino Mühlhäuser
- jplutz, Jonas Lutz

## Build
- Ensure gradle is installed

## Run
- Build project
- For Run the project following this procedure:
  - click on the gradle symbol on the top right
  - click "sopra [bootRun]" in the folder "Run Configuration"
  - open "localhost:8080/" in your browser (good option is firefox or chrome)

 ## Branches
 
 - Main Branch:
   - latest stable Version.
     
 - BackUpOfMain_hh/dd/mm_Index:
   - BEFORE MERGING with Main: create a BackupBranch of the latest stable Version (Main) with versions date (time , date, month) + Index (incremental).
     
 - Development Branch:
   - Branch for implementing new features on top of the currently stable version (same as Main). PUSHED ON MAIN when milestone is reached successfully.
     
 - BackUpOfDev_hh/dd/mm-Version_Index:
   - BEFORE PUSHING your Changes, create a BackupBranch of the currently latest Version of the DevelopementBranch (time, date, month) + Index (incremental).

 ## Developement

     3.3: 
    - 1. Sortieren nach Preis (auf/absteigend) und Filtern, gemäß "anderen Online-Shops" {Nils}
    - 2. Merkliste {Lino}
    - 3. Pflegetipps für gekaufte Ware {Phillip}
    - 4. Eines der zusätzlichen Features. Zurzeit haben wir die gamifizierten Elemente. Dazu wiefolgt

        1. Prozentanzeige der Profilvollständigkeit {Lino}  
        2. Kauf/Verkaufstatistiken im Leaderboard o.Ä. {Jonas}
        3. Quiz des Tages/WOche/Monat whatever, nur zur Unterhaltung {Nils}
        4. Levelanzeige {Jonas}


        3.2:
    - Nils: Auf der eingeloggten Startseite können die User Pflanzen über ein Suchfeld suchen und
      gefundene Pflanzen tabellarisch ansehen.
    - Jonas: User können Pflanzen einstellen, bearbeiten und löschen.
    - Lino: User können ihr Userprofil einsehen (bearbeiten ist optional).
    - Philipp: Kaufinteressenten können mit dem Käufer Nachrichten austauschen.



