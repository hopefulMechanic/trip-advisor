# trip-advisor

Harmonogram prac:

Aktualnie zrobione:
- Konfiguracja frontendu (reactjs)
- Konfiguracja backendu (gradle, spring)
- Konfiguracja bazy H2
- Konfiguracja swaggera
- Frontend, Backend - CRUD Miejsc
- Frontend, Backend Autentykacja i Autoryzacja
- Frontend, Backend - CRUD Komentarzy
- Frontend, Backend - Wyszukiwarka miejsc
- Frontend, Backend - Notyfikacje

Wprowadzenie:

TripAdvisor który chcemy stworzyć jest portalem wykorzystywanym do wyszukiwania miejsc turystycznych (atrakcji, noclegów, restauracji) oraz dzielenia się opiniami o nich. Portal udostępnia możliwość wyszukiwania obiektów według różnych kryteriów. Użytkownik może dodać swoją opinię o obiekcie, zostawić ocenę a także zdobyć dane kontaktowe.


Stack Technologiczny:
- Backend
Java
Spring
- Frontend
HTML
SCSS
JS
React
- Baza danych
SQL
H2

Użytkownicy
Użytkownicy mogą mieć dostęp read only gdy nie posiadają konta. Aby mieć pełen dostęp trzeba zarejestrować konto i się na nie zalogować. Są dwa typy konta użytkownika, konto:
- komercyjne
- niekomercyjne
Użytkownicy komercyjni mogą dodawać miejsca które są ich prywatna własnością do aplikacji. Ten rodzaj miejsca może być płatną atrakcją.
Użytkownicy niekomercyjny mogą dodać tylko atrakcję dostępną publicznie.
Miejsca(Atrakcje) 
Miejsca są wprowadzane przez zalogowanego użytkownika i w zależności od typu konta mogą być atrakcjami komercyjnymi lub publicznymi. Miejsca komercyjne mogą być modyfikowane tylko przez osobę która je stworzyła w przeciwieństwie do miejsc publicznych które raz wprowadzone nie mogą być modyfikowane. Użytkownik zalogowany może subskrybować miejsca komercyjne, a to pozwala na otrzymywanie powiadomień od właściciela.
Komentarze i oceny
Każdy zalogowany użytkownik może wystawić komentarz oraz ocenę do dowolnego miejsca.

Wyszukiwarka Miejsc

Wyszukiwarka pozwala na łatwe filtrowanie listy miejsc po polach:
- nazwa
- opis
- miejscowość
- kraj


Powiadomienia
Ownerzy obiektów mogą stworzyć promocję (lub inne powiadomienie) dotycząca konkretkego obiektu komercyjnego. W efekcie użytkownicy (Users) którzy są wpisani na listę newslettera zostaną o tym fakcie poinformowani.

Use Case
- Rejestracja
Klikamy przycisk zaloguj.
Przechodzimy do zakładki załóż konto.
Wypełniamy formularz.
Klikamy zapisz.
Konto zostaje zapisane w bazie danych i możliwe jest zalogowanie na nie.

- Logowanie
Klikamy przycisk zaloguj.
Wpisujemy swój login i hasło.
klikamy zaloguj.
Gdy dane są poprawne, użytkownik zostaje zalogowany.

- Dodawanie miejsca
Logujemy się do systemu.
Klikamy dodaj miejsce.
Uzupełniamy formularz.
Klikamy zapisz.
System zapisuje obiekt.

- Edycja miejsca komercyjnego
Logujemy się do systemu.
Przechodzimy do miejsca podwójnym kliknięciem na liście.
Jeżeli użytkownik jest osobą która obiekt założyła, klika na przycisk edytuj.
Użytkownik nie zalogowany lub nie będący właścicielem nie widzi przycisku edytuj.
Wprowadzamy zmiany.
Klikamy zapisz.
System uaktualnia obiekt w bazie danych.

- Dodawanie komentarza
Logujemy się.
Wybiera miejsce z listy i klikamy na nie dwukrotnie.
Klikamy przycisk dodaj komentarz.
Wpisujemy treść komentarza.
Wybieramy ocenę.
Zatwierdzamy komentarz.
System zapisuje komentarz.
System wyświetla komentarz przypisany do miejsca.

- Usuwanie komentarzy
Logujemy się.
Wybiera miejsce z listy i klikamy na nie dwukrotnie.
W detalach obiektu komentarze dodane przez nas mają dostępny przycisk usuń.
Klikamy przycisk usuń.
System usuwa komentarz z listy komentarzy konkretnego obiektu

- Wyszukiwanie miejsca
Przechodzimy do ekranu głównego aplikacji.
Wpisujemy dowolny string w miejsce wyszukiwania obiektu.
System dopasowuje string do parametrów(nazwa, opis, miejscowość, kraj) każdego obiektu.
System wyszukuje obiektu według podanych kryteriów.
System wyświetla znalezione obiekty.

- Subskrybowanie obiektu
Logujemy się do systemu.
Wyszukuje obiekt i przechodzimy dwuklikiem do jego detali.
Użytkownik klika przycisk subskrypcji.
System zapisuje użytkownika przypisanego do obiektu.

- Dodanie notyfikacji do miejsca
Logujemy się do systemu.
Wyszukuje obiekt i przechodzimy dwuklikiem do jego detali.
Jeżeli użytkownik jest właścicielem miejsca ma widoczną dodatkową sekcję z obsługą powiadomień.
Wpisujemy treść powiadomienia
Klikamy zapisz
System zapisuje powiadomienie.
Notyfikacja zostanie wyświetlona dla każdego użytkownika który jest subskrybentem

- Anulowanie subskrypcji obiektu
Logujemy się do systemu.
Wyszukuje obiekt i przechodzimy dwuklikiem do jego detali.
Użytkownik klika przycisk anuluj subskrypcję.
System usuwa użytkownika z listy subskrybentów.

## documentation

[Dokumentacja projektu](https://docs.google.com/document/d/1D8wIUmMs7kQ3UNHuGCAnZvQh7-DAUwTEI3hzsNaRPGk/edit?usp=sharing)

## diagram

![alt text](https://github.com/hopefulMechanic/trip-advisor/raw/master/docs/CRC.png)

![alt text](https://github.com/hopefulMechanic/trip-advisor/raw/master/docs/places.png)
