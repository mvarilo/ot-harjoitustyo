# Tower Defense

Tower Defense -peli.

## Dokumentaatio

Käyttöohje

[Vaatimusmäärittely](https://github.com/mvarilo/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/mvarilo/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

Testausdokumentti

[Työaikakirjanpito](https://github.com/mvarilo/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _TowerDef 1.0-SNAPSHOT_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_