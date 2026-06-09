# Projectplan: Documentenkluis Webapplicatie

## Projectinformatie

**Projectnaam:** Veilige Documentenkluis  
**Student:** Mohamed Koubaa  
**Klas:** 6A&D  
**Vak:** Projectmanagement  
**Leerkracht:** Gerrit Wijns  
**Startdatum:** 18/10/2025  
**Einddatum:** [Te bepalen]

## Klantgegevens

**Naam:** Yassine Bibi  
**Email:** 
- yassine.b2007@hotmail.com
- sitesolutions.contact@gmail.com

**Telefoonnummer:** +32 499 91 21 81

## 1. Projectomschrijving

### 1.1 Projectdoel
Ontwikkelen van een webapplicatie voor het veilig opslaan en beheren van documenten met gebruikersauthenticatie, bestandsversleuteling, versiebeheer en uitgebreide logging functionaliteiten.

### 1.2 Reden van Projectkeuze
Veilige documentenkluis nodig - De klant heeft een behoefte aan een veilige manier om gevoelige documenten op te slaan en te beheren.

### 1.3 Doelgroep
- Particuliere gebruikers die documenten veilig willen opslaan
- Kleine bedrijven die documentenbeheer nodig hebben
- Admin-gebruikers voor systeembeheer

### 1.4 Kernfunctionaliteiten
- Gebruikersauthenticatie met registratie en login
- Veilige documentopslag met versleuteling
- Mappenstructuur voor organisatie
- Bestandsversiebeheer
- Upload en download functionaliteit
- Zoekfunctionaliteit
- Activiteitenlogboek
- Two-factor authenticatie (2FA)
- Responsive design
- Admin dashboard met logging

## 2. Scope

### 2.1 In Scope
- Gebruikersbeheer (registratie, login, profiel)
- Documentbeheer (upload, download, verwijderen)
- Mappenstructuur
- Automatische versiebeheer
- Zoekfunctionaliteit
- Wachtwoord reset functionaliteit
- Two-factor authenticatie
- Activiteitenlogboek
- Admin panel
- Responsive interface
- Wachtwoord hashing
- Bestandsversleuteling

### 2.2 Out of Scope
- Mobile app ontwikkeling
- Integratie met externe cloud services (fase 1)
- Geavanceerde documentbewerking
- Real-time samenwerking
- API voor externe applicaties

## 3. Sprint Planning

### Sprint 1: Basis Functionaliteit & Authenticatie
**Duur:** [Te bepalen]

**User Stories:**
- US001: Gebruikersregistratie met email en wachtwoord
- US002: Gebruikersinlog
- US003: Gegevens wijzigen
- US004: Wachtwoord wijzigen
- US005: Account volledig verwijderen
- US006: Admin login
- US007: Responsive interface

**Deliverables:**
- Werkende authenticatie systeem
- Gebruikersbeheer
- Admin toegang
- Responsive design basis

### Sprint 2: Documentbeheer & Organisatie
**Duur:** [Te bepalen]


**User Stories:**
- US008: Mappen aanmaken
- US009: Mappen verwijderen
- US010: Lege bestanden aanmaken
- US011: Bestanden verwijderen
- US012: Bestanden uploaden
- US013: Bestanden downloaden
- US014: Automatisch bestandsversleuteling
- US015: Wachtwoorden gehashed opslaan

**Deliverables:**
- Complete documentbeheer functionaliteit
- Mappenstructuur
- Veilige opslag met versleuteling
- Upload/download functionaliteit

### Sprint 3: Geavanceerde Functies & Beveiliging
**Duur:** [Te bepalen]

**User Stories:**
- US016: Automatisch versiebeheer
- US017: Geschiedenis van bestand bekijken
- US018: Eerdere versies downloaden
- US019: Complete log van gebruikersactiviteiten (admin)
- US020: Zoeken naar bestanden op naam of inhoud
- US021: Wachtwoord resetten via e-mail
- US022: Two-factor authenticatie instellen
- US023: Inloggen met 2FA-code
- US024: Activiteitenlog van eigen acties

**Deliverables:**
- Versiebeheer systeem
- Zoekfunctionaliteit
- 2FA implementatie
- Complete logging
- Wachtwoord reset functionaliteit

## 4. Technische Architectuur

### 4.1 Technology Stack (Voorgesteld)
**Frontend:**
- HTML5, CSS3, JavaScript
- Framework: React of Vue.js (te bepalen)
- Responsive: Bootstrap of Tailwind CSS

**Backend:**
- Node.js met Express, of
- Python met Flask/Django, of
- PHP met Laravel
- Te bepalen in overleg

**Database:**
- MySQL of PostgreSQL voor gebruikersdata
- Bestandssysteem met encryptie voor documenten

**Beveiliging:**
- bcrypt voor password hashing
- AES-256 voor bestandsversleuteling
- JWT voor session management
- TOTP voor 2FA

### 4.2 Architectuur Diagram
```
┌─────────────┐
│   Client    │
│  (Browser)  │
└──────┬──────┘
       │
       │ HTTPS
       ▼
┌─────────────────┐
│   Web Server    │
│  (Frontend)     │
└────────┬────────┘
         │
         │ REST API
         ▼
┌─────────────────┐
│  Application    │
│    Server       │
│  (Backend)      │
└────────┬────────┘
         │
    ┌────┴────┐
    ▼         ▼
┌────────┐ ┌──────────┐
│Database│ │  File    │
│        │ │ Storage  │
│(MySQL) │ │(Encrypted)│
└────────┘ └──────────┘
```

## 5. Database Schema (Concept)

### Tabellen:
1. **users**
   - user_id (PK)
   - email
   - password_hash
   - created_at
   - updated_at
   - is_admin
   - two_factor_enabled
   - two_factor_secret

2. **folders**
   - folder_id (PK)
   - user_id (FK)
   - parent_folder_id (FK, nullable)
   - folder_name
   - created_at

3. **documents**
   - document_id (PK)
   - folder_id (FK)
   - user_id (FK)
   - file_name
   - file_path_encrypted
   - file_size
   - mime_type
   - created_at
   - updated_at

4. **document_versions**
   - version_id (PK)
   - document_id (FK)
   - version_number
   - file_path_encrypted
   - created_at

5. **activity_logs**
   - log_id (PK)
   - user_id (FK)
   - action_type
   - description
   - ip_address
   - timestamp

6. **password_resets**
   - reset_id (PK)
   - user_id (FK)
   - token
   - expires_at
   - created_at

## 6. Beveiligingsmaatregelen

1. **Authenticatie:**
   - Wachtwoorden gehashed met bcrypt (salt rounds: 12+)
   - Two-factor authenticatie optie
   - Session timeout na inactiviteit

2. **Bestandsbeveiliging:**
   - Alle bestanden versleuteld met AES-256
   - Unieke encryptie keys per gebruiker
   - Bestanden opgeslagen buiten web root

3. **Transport Security:**
   - HTTPS verplicht
   - Secure cookies
   - CORS policy

4. **Input Validatie:**
   - Server-side validatie
   - XSS preventie
   - SQL injection preventie via prepared statements

5. **Logging:**
   - Alle gebruikersacties gelogd
   - IP adressen bijhouden
   - Admin monitoring dashboard

## 7. Testing Plan

### 7.1 Unit Testing
- Authenticatie functies
- CRUD operaties
- Encryptie/decryptie functies

### 7.2 Integration Testing
- Complete user flows
- API endpoints
- Database interacties

### 7.3 Security Testing
- Penetration testing
- SQL injection tests
- XSS vulnerability tests
- Authentication bypass tests

### 7.4 User Acceptance Testing
- Test met eindgebruiker (Yassine Bibi)
- Feedback verzamelen
- Bugs en verbeteringen documenteren

## 8. Risico's & Mitigatie

| Risico | Impact | Waarschijnlijkheid | Mitigatie |
|--------|--------|-------------------|-----------|
| Datalek door beveiligingsfout | Hoog | Laag | Code review, security testing, encryptie |
| Prestatieproblemen bij grote bestanden | Middel | Middel | Bestandsgrootte limieten, chunked uploads |
| Complexiteit van versiebeheer | Middel | Middel | Goede planning, stapsgewijze implementatie |
| Tijdsgebrek voor alle features | Middel | Middel | Prioritering features, MVP eerst |
| Browser compatibiliteit | Laag | Laag | Gebruik moderne frameworks, testing |

## 9. Communicatieplan

### 9.1 Met Klant
- **Frequentie:** Wekelijks
- **Methode:** Email, telefoon voor urgente zaken
- **Inhoud:** Sprint reviews, demo's, feedback sessies

### 9.2 Met Leerkracht
- **Frequentie:** Per sprint
- **Methode:** Schooluren, email
- **Inhoud:** Voortgangsrapportage, begeleiding

### 9.3 Documentatie
- Logboek bijhouden per sprint
- Code documentatie
- Gebruikershandleiding
- Technische documentatie

## 10. Deliverables

### Eindproducten:
1. Werkende webapplicatie
2. Gedocumenteerde source code
3. Database schema en setup scripts
4. Gebruikershandleiding
5. Technische documentatie
6. Test rapporten
7. Sprint logboeken
8. Presentatie

## 11. Success Criteria

Het project is succesvol als:
- [ ] Alle user stories uit Sprint 1-3 zijn geïmplementeerd
- [ ] De applicatie is volledig functioneel en stabiel
- [ ] Alle beveiligingsmaatregelen zijn geïmplementeerd
- [ ] De klant is tevreden met het eindresultaat
- [ ] Alle documentatie is compleet
- [ ] Code is clean en gedocumenteerd
- [ ] Applicatie is responsive en gebruiksvriendelijk

## 12. Planning & Deadlines

| Sprint | Start | Eind | Oplevering |
|--------|-------|------|------------|
| Sprint 1 | [Datum] | [Datum] | Basis authenticatie & UI |
| Sprint 2 | [Datum] | [Datum] | Documentbeheer |
| Sprint 3 | [Datum] | [Datum] | Geavanceerde features |
| Testing & Bugfixes | [Datum] | [Datum] | Stabiele versie |
| Documentatie | [Datum] | [Datum] | Complete documentatie |
| Eindoplevering | [Datum] | [Datum] | Presentatie & demo |

## 13. Volgende Stappen

1. **Week 1-2:**
   - [ ] Technologie stack definitief kiezen
   - [ ] Development omgeving opzetten
   - [ ] Database schema finaliseren
   - [ ] Wireframes maken voor UI
   - [ ] Git repository opzetten

2. **Week 3-4:**
   - [ ] Sprint 1 starten
   - [ ] Basis authenticatie implementeren
   - [ ] Database setup
   - [ ] UI framework implementeren

3. **Doorlopend:**
   - [ ] Dagelijks logboek bijhouden
   - [ ] Code commits met duidelijke messages
   - [ ] Wekelijks klant update
   - [ ] Testing tijdens ontwikkeling

---

**Goedkeuring:**

Student: Mohamed Koubaa  
Datum: 23/10/2025

Leerkracht: Gerrit Wijns  
Datum: ___________

Klant: Yassine Bibi  
Datum: ___________
