# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein käyttäjätason testein.

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

Automatisoitujen testien ytimen moudostavat sovelluslogiikkaa.

### DAO-luokat

Dao-luokan toiminnallisuus on testattu luomalla testeissä tilapäinen tiedosto hyödyntäen JUnitin [TemporaryFolder](https://junit.org/junit4/javadoc/4.12/org/junit/rules/TemporaryFolder.html)-ruleja.

### Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 68% ja haarautumakattavuus 53%

![alt text](https://github.com/mvarilo/ot-harjoitustyo/blob/master/dokumentaatio/testauskattavuus.jpg)

## Pelin testaus

Sovelluksen pelillisyyden on suoritettu manuaalisesti.

### Asennus ja konfigurointi

Sovellus on haettu ja sitä on testattu [käyttöohjeen](https://github.com/mvarilo/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md) kuvaamalla tavalla sekä Windows- että Linux-ympäristöön.

Sovellusta on testattu sekä tilanteissa, joissa tallennettava tiedosto on ollut olemassa ja tilanteissa joissa näin ei ole.

### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/mvarilo/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md) ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. Kaikkien toiminnallisuuksien yhteydessä on yritetty tehdä kaikki millä pystyttäisiin rikkomaan ohjelma.

## Sovellukseen jääneet laatuongelmat

Sovellus ei anna hyviä virheilmoituksia jos lataus tai tallennus ei onnistu. Tornien laittaminen nappulasta laittaa ne aina samaan kohtaan. Viholliset kulkevat visuaalisesti väärästä kohdasta mennessään vasemmalle ja alas. Kierroksen viimeinen laukaus ei näy kovin hyvin.