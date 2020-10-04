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
