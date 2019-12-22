# Käyttöohje

Lataa tiedosto [TowerDef-1.0-SNAPSHOT.jar](https://github.com/mvarilo/ot-harjoitustyo/releases/tag/loppupalautus)

## Konfigurointi

Ei konfiguroitavaa.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar TowerDef-1.0-SNAPSHOT.jar
```

Mikäli homma jostain syystä temppuilee (kuten allekirjoitetulla laitoksen koneella) käynnistä ohjelma oikealla Javan versiolla seuraavasti: 

```
/usr/lib/jvm/java-8-openjdk-amd64/bin/java -jar target/app.jar
```

## Pelaaminen

Sovellus käynnistyy pelinäkymään, jossa pelaaja voi asettaa pelikentälle torneja, jotka ampuvat vihollisia.

Pelaaja voi asettaa torneja kentälle klikkaamalla hiirellä kohtaa kentässä, jossa ei ole esteitä.

Vihollisten tulon voi aloittaa painamalla 'Next Wave' -nappia jolloin viholliset pyrkivät päästä tason läpi maaliin. Jos vihollinen pääsee maaliruutuun ottaa pelaaja vahinkoa. Jos tukikohdalta loppuu elämä niin peli loppuu.

## Tallentaminen ja lataaminen

Peli tallentaa pelin tilan autosave.sav -nimiseen tiedostoon pelin kansioon kierrosten välissä. Peli lataa tallennuksen mikäli sellainen on kansiossa.