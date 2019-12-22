# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenteen näkee luokka/pakkauskaaviosta:

![alt text](https://github.com/mvarilo/ot-harjoitustyo/blob/master/dokumentaatio/arkki.jpg)

Sekvenssikaavio:

![alt text](https://github.com/mvarilo/ot-harjoitustyo/blob/master/dokumentaatio/sekvenssikaavio.png)

## Tietojen pysyväistallennus

Pakkauksen _towerdef.dao_ luokka _Dao_ huolehtii tilanteen tallettamisesta tiedostoon.

Luokat noudattavat [Data Access Object](https://en.wikipedia.org/wiki/Data_access_object) -suunnittelumallia ja ne on tarvittaessa mahdollista korvata uusilla toteutuksilla, jos sovelluksen datan talletustapaa päätetään vaihtaa. Luokat onkin eristetty sovelluslogiikasta.

'autosav.sav' -niminen tiedosto tallennetaan sovelluksen juureen.

