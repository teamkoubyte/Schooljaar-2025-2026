# Logboek Sprint 1 - Documentenkluis Webapplicatie

**Student:** Mohamed Koubaa  
**Klas:** 6A&D  
**Leerkracht:** Gerrit Wijns  
**Klant:** Yassine Bibi  

**GitHub Repository:** https://github.com/teamkoubyte/documentenkluis

---

## Instructies
Bij het gebruik van het logboek typen we steeds het meest recente gegeven bovenaan.
Als de leerkracht commentaar heeft geschreven, laat je die uiteraard staan.

---

## 2025-10-23
**Activiteit:** Projectplan volledig uitgewerkt met Laravel monolith architectuur

**Wat heb ik gedaan:**
- Uitgebreid projectplan opgesteld met alle onderdelen
- User stories breakdown gemaakt voor alle 24 user stories verdeeld over 3 sprints
- Technische specificaties document opgesteld met:
  - **Laravel Monolith Architecture** - Volledige PHP stack
  - Backend: Laravel 11 met PHP 8.2+
  - Frontend: Blade Templates + **Custom CSS** + Vanilla JavaScript
  - Database: MySQL 8.0 met Eloquent ORM
  - Containerization: Docker Compose setup (5 services)
  - Complete Docker configuratie (Nginx, PHP-FPM, MySQL, Redis, MailHog)
  - Beveiliging: AES-256 encryptie via Laravel Encryption, bcrypt via Laravel Hash
  - Authentication: Laravel Breeze/Fortify (inclusief 2FA)
- Database schema ontwerp met Laravel migrations
- Web routes specificatie met Laravel routing (geen API endpoints nodig)
- Security measures (AES-256 encryptie, bcrypt hashing, 2FA via Fortify)
- Folder structure voor Laravel monolith applicatie
- Gedetailleerde sprint planning gemaakt met dag-tot-dag breakdown
- Risico analyse en mitigatie strategie uitgewerkt
- Communicatieplan opgesteld
- Docker configuratie met Makefile voor helper commands

**Technische keuzes (FINAAL):**
- **Full Stack Framework:** Laravel 11 (PHP 8.2+) - Monolith Architectuur
- **Frontend Engine:** Blade Templates (Laravel's template engine)
- **Styling:** **Custom CSS (geen frameworks zoals Tailwind/Bootstrap)**
- **CSS Organisatie:** 
  - Aparte CSS files per concern (layout, components, forms, responsive)
  - CSS Custom Properties voor variabelen (kleuren, spacing, fonts)
  - Mobile-first responsive design met media queries
- **JavaScript:** Vanilla JavaScript + Alpine.js (optioneel, alleen waar echt nodig)
- **Database:** MySQL 8.0 met Eloquent ORM
- **Cache & Queue:** Redis 7.2
- **Beveiliging:** 
  - Laravel Encryption (AES-256) voor file encryptie
  - Laravel Hash (bcrypt) voor passwords
  - Laravel Fortify voor 2FA en authenticatie
- **Containerization:** Docker Compose met 5 services:
  - nginx (web server)
  - app (PHP-FPM + Laravel)
  - mysql (database)
  - redis (cache & queue)
  - mailhog (email testing)

**Docker Services:**
- Nginx 1.25 als web server
- PHP 8.2-FPM voor Laravel applicatie
- MySQL 8.0 voor database
- Redis 7.2 voor caching en queue management
- MailHog voor email testing tijdens development

**Waarom Custom CSS (geen Tailwind):**
- **Meer controle:** Volledige controle over CSS code
- **Leren:** Betere CSS kennis opdoen
- **Kleiner bestand:** Geen ongebruikte utility classes
- **Eenvoudiger:** Geen build process complexity voor CSS
- **Overzichtelijker:** Klassieke CSS structuur met herbruikbare classes
- **Performance:** Minder CSS code in productie

**CSS Structuur:**
```
resources/css/
├── variables.css    # :root variabelen (kleuren, fonts, spacing)
├── layout.css       # Grid, flexbox, containers
├── components.css   # Buttons, cards, modals
├── forms.css        # Inputs, textareas, selects
└── responsive.css   # Media queries
```

**Voordelen van deze stack:**
- Laravel biedt out-of-the-box beveiliging (Fortify, Encryption, CSRF protection)
- Eloquent ORM maakt database operaties eenvoudiger
- Laravel Migrations voor database version control
- Blade Templates met componenten voor herbruikbare UI
- Docker zorgt voor consistente development environment
- Eenvoudig te deployen naar productie
- Redis integratie voor betere performance
- Custom CSS geeft volledige controle en is educatief

**Resultaat:**
- 4 complete planningsdocumenten opgeleverd
- Duidelijk overzicht van alle 24 user stories met acceptatiecriteria
- Planning voor 14 weken met ~178 uur geschatte ontwikkeltijd
- Alle beveiligingsmaatregelen gedocumenteerd
- Complete Docker setup met Makefile voor easy deployment
- Laravel-specifieke code voorbeelden voor encryptie/decryptie
- Blade template structuur uitgewerkt
- CSS structuur gedefinieerd

**Volgende stappen:**
- Projectplan bespreken met leerkracht (Gerrit Wijns)
- Laravel Monolith architectuur laten goedkeuren
- Planning met concrete datums invullen
- Feedback van klant (Yassine Bibi) vragen op het voorstel
- Docker environment opzetten en testen
- Laravel project initialiseren met Breeze
- CSS structuur en design system opzetten

**Tijdsinvestering:** 6 uur (inclusief alle aanpassingen)

**Status:** ✅ Projectplan compleet met Laravel Monolith + Custom CSS + Docker

---

## 2025-10-18
**Activiteit:** Gedetailleerd voorstel ontvangen en geanalyseerd

**Wat heb ik gedaan:**
- Gedetailleerd voorstel van klant ontvangen met alle user stories
- 24 user stories ontvangen verdeeld over 3 sprints:
  - Sprint 1: Authenticatie & basis functionaliteit (7 user stories)
  - Sprint 2: Documentbeheer & organisatie (8 user stories)
  - Sprint 3: Geavanceerde functies & beveiliging (9 user stories)
- Projectvereisten geanalyseerd
- Complexiteit van features beoordeeld
- Beveiligingseisen geïdentificeerd (encryptie, hashing, 2FA)

**Belangrijke features geïdentificeerd:**
1. Gebruikersauthenticatie met registratie en login
2. Veilige documentopslag met AES-256 encryptie
3. Mappenstructuur voor organisatie
4. Automatisch versiebeheer bij re-upload
5. Two-factor authenticatie voor extra beveiliging
6. Uitgebreide logging van gebruikersactiviteiten
7. Admin dashboard voor systeembeheer
8. Wachtwoord reset via email
9. Zoekfunctionaliteit
10. Responsive design voor alle apparaten

**Beveiligingsvereisten:**
- Alle wachtwoorden gehashed met bcrypt
- Alle bestanden encrypted at rest (AES-256)
- Two-factor authenticatie optie
- Complete activity logging
- Secure password reset flow

**Vragen voor klant:**
- Zijn er specifieke bestandstypen die NIET toegestaan mogen zijn?
- Wat is de maximale bestandsgrootte per upload?
- Hoeveel versies van een bestand moeten bewaard blijven?
- Zijn er specifieke compliance eisen (GDPR, etc.)?

**Volgende stappen:**
- Projectplan opstellen
- Technische architectuur uitwerken
- Sprint planning maken
- Meeting plannen met leerkracht

**Tijdsinvestering:** 3 uur

**Status:** ✅ Requirements analysis compleet

---

## 2025-10-15
**Activiteit:** Eerste gesprek met klant en programma-idee besproken

**Wat heb ik gedaan:**
- Kennismaking met klant Yassine Bibi
- Projectidee besproken: Veilige documentenkluis webapplicatie
- Behoeften van de klant geanalyseerd
- Veiligheidsaspecten besproken (encryptie, authenticatie)
- Doelgroep gedefinieerd: particulieren en kleine bedrijven
- Eerste functionele eisen verzameld

**Klant gegevens:**
- Naam: Yassine Bibi
- Email: yassine.b2007@hotmail.com, sitesolutions.contact@gmail.com
- Telefoon: +32 499 91 21 81

**Belangrijkste punten uit gesprek:**
1. Klant heeft behoefte aan veilige documentopslag
2. Moet toegankelijk zijn via webbrowser
3. Gebruikers moeten eigen mappen kunnen aanmaken
4. Bestanden moeten versleuteld opgeslagen worden
5. Versiebeheer is belangrijk voor de klant
6. Gebruiksvriendelijkheid is prioriteit
7. Moet werken op desktop en mobile

**Reden van projectkeuze:**
De klant heeft een duidelijke behoefte aan een veilige manier om gevoelige documenten op te slaan en te beheren. Bestaande oplossingen zijn of te complex, of te duur, of bieden niet genoeg controle over de data. Deze webapplicatie moet een balans bieden tussen beveiliging en gebruiksgemak.

**Afspraken:**
- Tussenoplevering gepland na Sprint 2 (ongeveer week 7)
- Eindoplevering na Sprint 3 (ongeveer week 12-14)
- Wekelijkse updates via email
- Demo's na elke sprint

**Volgende stappen:**
- Wachten op gedetailleerd voorstel van klant met user stories
- Programma-idee document opstellen
- Research doen naar encryptie methodes
- Technologie stack kiezen

**Tijdsinvestering:** 2 uur

**Status:** ✅ Eerste klantgesprek afgerond

**Commentaar leerkracht:**
<!-- Hier komt feedback van Gerrit Wijns -->

---

## 2025-10-14
**Activiteit:** Eindproject opdracht ontvangen

**Wat heb ik gedaan:**
- Eindproject opdracht ontvangen via Google Classroom
- Werkwijze en deadlines besproken in de klas
- Evaluatiecriteria doorgenomen:
  - User stories implementatie
  - Code kwaliteit
  - Documentatie
  - Sprint logboeken
  - Presentatie
- Gezocht naar potentiële projectideeën
- Verschillende opties overwogen (webshop, portfolio, documentbeheer)

**Opdracht details:**
- Werk volgens Scrum methodiek met 3 sprints
- Hou per sprint een logboek bij
- Lever na elke sprint demo
- Werk samen met een echte klant
- Documenteer alles (code, API, gebruikershandleiding)

**Project mogelijkheden overwogen:**
1. E-commerce platform
2. Portfolio website met CMS
3. **Documentenkluis (gekozen)**
4. Task management systeem
5. Booking systeem

**Waarom documentenkluis gekozen:**
- Interessante technische uitdaging (encryptie, beveiliging)
- Duidelijke behoefte bij potentiële klant
- Goede balans tussen frontend en backend werk
- Mogelijkheid om moderne tech stack te gebruiken
- Uitbreidbaar naar toekomstige features

**Volgende stappen:**
- Contact opnemen met potentiële klant
- Programma-idee uitwerken
- Afspraak maken voor intake gesprek
- Research doen naar vergelijkbare applicaties

**Tijdsinvestering:** 1.5 uur

**Status:** ✅ Project opdracht ontvangen en geanalyseerd

**Commentaar leerkracht:**
<!-- Hier komt feedback van Gerrit Wijns -->

---

## Template voor nieuwe entries

```markdown
## YYYY-MM-DD
**Activiteit:** [Korte titel van wat je deed]

**Wat heb ik gedaan:**
- [Lijst van concrete acties]
- [Wat heb je bereikt]
- [Problemen die je tegenkwam]

**Technische details:** (optioneel)
- [Code snippets, libraries gebruikt, etc.]

**Resultaat:**
- [Wat werkt nu]
- [Wat is opgeleverd]

**Problemen/Blockers:** (indien van toepassing)
- [Wat ging niet goed]
- [Waar zit je mee]

**Oplossingen:** (indien van toepassing)
- [Hoe heb je problemen opgelost]

**Geleerd:**
- [Nieuwe kennis of inzichten]

**Volgende stappen:**
- [Wat ga je morgen/volgende keer doen]

**Tijdsinvestering:** X uur

**Status:** 🔄 In progress / ✅ Afgerond / ⚠️ Blocked

**Commentaar leerkracht:**
<!-- Hier komt feedback -->
```

---

## Sprint 1 Overzicht

**Doel Sprint 1:** Authenticatie & Basis Functionaliteit  
**Start:** [Te bepalen]  
**Eind:** [Te bepalen]  
**Verwachte duur:** 2-3 weken

**User Stories Sprint 1:**
- [ ] US001: Gebruikersregistratie
- [ ] US002: Gebruikersinlog
- [ ] US003: Gegevens wijzigen
- [ ] US004: Wachtwoord wijzigen
- [ ] US005: Account verwijderen
- [ ] US006: Admin login
- [ ] US007: Responsive interface

**Deliverables:**
- [ ] Werkende authenticatie systeem
- [ ] Gebruikersbeheer
- [ ] Admin toegang
- [ ] Responsive design basis
- [ ] Unit en integration tests
- [ ] Sprint 1 documentatie

---

## Totaal overzicht tijdsinvestering

| Datum | Activiteit | Uren |
|-------|-----------|------|
| 2025-10-23 | Projectplan + Laravel Monolith + Custom CSS | 6.0 |
| 2025-10-18 | Requirements analysis | 3.0 |
| 2025-10-15 | Eerste klantgesprek | 2.0 |
| 2025-10-14 | Project opdracht ontvangen | 1.5 |
| **Totaal** | | **12.5** |

---

**Einde Sprint 1 Logboek**
