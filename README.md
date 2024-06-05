# "SoPra"
Basic web application that allows registered users to communicate with each other to trade second-hand plants. Based on the idea of "Kleinanzeigen".

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

     3.2:
    - Nils: Auf der eingeloggten Startseite können die User Pflanzen über ein Suchfeld suchen und
      gefundene Pflanzen tabellarisch ansehen.
    - Jonas: User können Pflanzen einstellen, bearbeiten und löschen.
    - Lino: User können ihr Userprofil einsehen (bearbeiten ist optional).
    - Philipp: Kaufinteressenten können mit dem Käufer Nachrichten austauschen.



