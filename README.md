# Színház Jegyfoglaló Felület
## Funkcionális követelmény:
- Elérhető színdarabok listázása
- Termek vizuális megjelenítése ülőhelyekkel
- Jegy vásárlás és helyfoglalás
- Felhasználók módosíthatnak a saját helyükön, foglalásukat visszavonhatják
- Adminisztrátorok lekérhetik a foglalások listáját és módosíthatják azokat
- Adminisztrátorok eltávolíthatnak/hozzáadhatnak színdarabokat
## Nem funkcionális követelmény:
- Felhasználóbarát felület
- Termek közötti egyszerű váltás
- Színkódolt ülések
- A keresés eredménye azonnal, vagy nagyon rövid idő alatt elérhető legyen
- Tokenek kiosztása, amivel a felhasználó és az adminisztrátor módosításokat végezhet a foglalásokon
## Szakterületi fogalomjegyzék:
- Színdarab: tartozik hozzá időpont és terem, amiben játsszák
- Előadóterem: mindegyik előadóteremhez tartozik nézőtér, ahol ülőhelyek vannak
- Nézőtér: ülőhelyek listája egy teremhez
- Ülőhely: lehet szabad, foglalt és lezárt, valamint páholy és földszinti
## Szerepkörök:
- Látogató: Látja az elérhető darabokat, jegyet/helyet tud foglalni
- Felhasználó: Akinek van aktív foglalása, aktuális foglalásán módosítani tud, vagy visszavonhatja azt
- Adminisztrátor: Lekérheti a foglalások listáját, módosíthatja a foglalásokat, hozzáadhat/eltávolíthat színdarabokat, lezárhat termeket vagy üléseket
## Alkalmazott fejlesztői környezetek:
Backend
- IDE: JetBrains IntelliJ IDEA
- MySQL 5+
- Apache Maven 
- Java SE 11+
- Spring Framework 2+

Frontend
- IDE: JetBrains WebStorm
- NodeJS 14+
- NPM 6+
- AngularJS 11+
## Adatbázis felépítése és táblák kapcsolata:
![UML table](https://github.com/geodius/theater-booking/blob/main/UML2.png)
## Szerepkörök és hozzájuk tartozó jogosultságok táblája:
![Roles and privlieges](https://github.com/geodius/theater-booking/blob/main/UserRoles.png)
## Könyvtárstruktúra
Backend
- src
    - main
        - java/hu/elte/fswp/theater-booking
        
			- controller: kontrollerek mappája
			- database: adatbázis elérése CrudRepository segítségével
			- entity: tartalmazza az entitások szintaktikai követelményeit, attribútumait, és egyéb egyedi alkotóelemeket tároló osztályokat
			- model: az egyes alkotóelemeken végrehajtható műveleteket tartalmazó osztályok mappája
			- security: biztonsági konfigurációk mappája, egyes szerepköröknél mik a megengedettek
			- utility: egyéb, a program működéséhez szükséges classok mappája
		- resources: adatbázis elérése
	- test: tartalmazza a tesztelést
	
Frontend
- theater-booking
	- src: tartalmazza a további almappákat, valamint az egész programra vonatkozó css és egyéb beállításokat
		- app
			- admin-main: a főoldal adminként nézve
			- admin-plays: az előadások adminként nézve, így lehet módosítani
			- admin-reservation: a foglalások adminként nézve, így lehet módosítani őket
			- admin-schedule: a műsor adminként nézve, így lehet módosítani
			- admin-users: a felhasználók listája, az admin tudja őket szerkeszteni
			- calendar-play: a főoldalon az előadások rövid leírása
			- calendar-schedule : a főoldalon a műsor napokra bontva
			- home: a főoldal, itt van pár kép a színházról, valamint előadások és műsor egy hétre
			- login: bejelentkezési oldal
			- navbar: a fenti navigációs blokk
			- plays: az előadások szinopszisa, valamint mikor játsszák
			- schedule: a műsor
			- ticket-reservation-overlay: jegyvásárlásra kattintva ez az overlay jelenik meg
		- assets
			- fonts: speciális fontok, amiket a webes felület használ
			- img: egyéb képek, például a carousel képei és a színház ikonja
			- rooms: termek rajzai képformátumban
		- environments: környezetek osztályai Angularhoz
		
##Kliensoldali szolgáltatások
- **Műsor listázása:** naponként kilistázza a játszott előadásokat.
- **Előadások listázása:** az összes előadást kilistázza, valamint megadja, hogy mikor játsszák (ha játsszák).
- **Jegyár listázása:** az összes jelenleg vásárolható jegyfajta árait listázza ki.
- **Jegyfoglalás:** egy adott műsorponthoz foglalhat a felhasználó jegyet.
- **Foglalás törlése:** a felhasználó a foglalását törölheti is.

##Kapcsolat a szerverrel
![com](https://github.com/geodius/theater-booking/blob/main/com.png)

##Helyfoglalás menetének részletes leírása
![asd](https://github.com/geodius/theater-booking/blob/main/asd.png)

##Felhasználói dokumentáció

**Használat**

Az oldal egy színház webes felületét valósítja meg, ami lehetővé teszi a műsorok, előadások, felhasználók listázását, módosítását és törlését, ha a felhasználó admin. Az egyéb regisztrált emberek jegyet vásárolhatnak, valamint megtekinthetik egy héttel előre a műsort.

**Wireframe**

![haziko](https://github.com/geodius/theater-booking/blob/main/haziko.png)
![eloadasok](https://github.com/geodius/theater-booking/blob/main/eloadasok.png)
![jegyek](https://github.com/geodius/theater-booking/blob/main/jegyek.png)
![musor](https://github.com/geodius/theater-booking/blob/main/musor.png)
![belepes](https://github.com/geodius/theater-booking/blob/main/belepes.png)
