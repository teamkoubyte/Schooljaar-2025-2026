# User Stories Breakdown - Documentenkluis

## Sprint 1: Authenticatie & Basis Setup

### US001: Gebruikersregistratie
**Als** gebruiker  
**Wil ik** kunnen registreren met email en wachtwoord  
**Zodat ik** toegang krijg tot de documentenkluis

**Acceptatiecriteria:**
- [ ] Registratieformulier met email en wachtwoord velden
- [ ] Email validatie (correct formaat)
- [ ] Wachtwoord minimaal 8 karakters, met hoofdletter, cijfer en speciaal teken
- [ ] Wachtwoord bevestiging veld
- [ ] Controle of email al bestaat
- [ ] Wachtwoord wordt gehashed opgeslagen (bcrypt)
- [ ] Succesbericht na registratie
- [ ] Automatisch inloggen na registratie

**Technische taken:**
- Database tabel `users` aanmaken
- Backend endpoint POST /api/auth/register
- Input validatie implementeren
- Bcrypt integreren voor password hashing
- Frontend formulier met validatie
- Error handling implementeren

**Geschatte tijd:** 8 uur

---

### US002: Gebruikersinlog
**Als** gebruiker  
**Wil ik** kunnen inloggen met mijn gegevens  
**Zodat ik** bij mijn documenten kan

**Acceptatiecriteria:**
- [ ] Login formulier met email en wachtwoord
- [ ] Verificatie van credentials
- [ ] Session/JWT token aanmaken
- [ ] Redirect naar dashboard na succesvolle login
- [ ] Foutmelding bij incorrecte credentials
- [ ] "Onthoud mij" optie
- [ ] Rate limiting tegen brute force

**Technische taken:**
- Backend endpoint POST /api/auth/login
- JWT token generatie implementeren
- Session management opzetten
- Frontend login formulier
- Protected routes implementeren
- Rate limiting middleware

**Geschatte tijd:** 6 uur

---

### US003: Gegevens wijzigen
**Als** gebruiker  
**Wil ik** mijn gegevens kunnen wijzigen  
**Zodat ik** mijn account up-to-date kan houden

**Acceptatiecriteria:**
- [ ] Profiel pagina met huidige gegevens
- [ ] Mogelijkheid email te wijzigen
- [ ] Mogelijkheid naam/profiel info toe te voegen
- [ ] Bevestiging vereist bij email wijziging
- [ ] Validatie van nieuwe gegevens
- [ ] Succesbericht na wijziging
- [ ] Logging van wijzigingen

**Technische taken:**
- Backend endpoint PUT /api/users/profile
- Database update queries
- Frontend profiel formulier
- Email verificatie bij wijziging
- Activity log entry

**Geschatte tijd:** 5 uur

---

### US004: Wachtwoord wijzigen
**Als** gebruiker  
**Wil ik** mijn wachtwoord kunnen wijzigen  
**Zodat ik** mijn account veilig kan houden

**Acceptatiecriteria:**
- [ ] Wachtwoord wijzig formulier
- [ ] Oud wachtwoord vereist
- [ ] Nieuw wachtwoord met bevestiging
- [ ] Wachtwoord sterkte indicator
- [ ] Validatie van wachtwoord regels
- [ ] Nieuwe hash opslaan in database
- [ ] Email notificatie van wijziging
- [ ] Alle andere sessies uitloggen

**Technische taken:**
- Backend endpoint PUT /api/users/password
- Oude wachtwoord verificatie
- Nieuwe wachtwoord hashing
- Email notificatie systeem
- Session invalidation
- Frontend formulier met sterkte indicator

**Geschatte tijd:** 6 uur

---

### US005: Account verwijderen
**Als** gebruiker  
**Wil ik** mijn account volledig kunnen verwijderen  
**Zodat ik** mijn gegevens kan wissen wanneer ik de documentenkluis niet meer wil gebruiken

**Acceptatiecriteria:**
- [ ] Account verwijder optie in instellingen
- [ ] Bevestigingsscherm met waarschuwing
- [ ] Wachtwoord bevestiging vereist
- [ ] Alle gebruikersdata wordt verwijderd
- [ ] Alle bestanden worden verwijderd
- [ ] Alle mappen worden verwijderd
- [ ] Activity logs blijven behouden (geanonimiseerd)
- [ ] Email bevestiging van verwijdering

**Technische taken:**
- Backend endpoint DELETE /api/users/account
- Cascade delete implementeren
- Bestand cleanup functie
- Database cleanup
- Geanonimiseerde logs behouden
- Frontend bevestigings modal
- Email notificatie

**Geschatte tijd:** 8 uur

---

### US006: Admin login
**Als** admin  
**Wil ik** kunnen inloggen met admin-gegevens  
**Zodat ik** het systeem kan beheren

**Acceptatiecriteria:**
- [ ] Admin role in database
- [ ] Admin login via zelfde interface
- [ ] Admin dashboard na login
- [ ] Toegang tot admin functies
- [ ] Niet zichtbaar voor normale gebruikers
- [ ] Extra beveiliging (evt. IP whitelist)

**Technische taken:**
- is_admin veld in users tabel
- Role-based access control (RBAC)
- Admin middleware voor protected routes
- Admin dashboard pagina
- Admin navigatie menu

**Geschatte tijd:** 5 uur

---

### US007: Responsive Interface
**Als** gebruiker  
**Wil ik** een responsive en gebruiksvriendelijke interface zien  
**Zodat ik** de webapplicatie op verschillende apparaten kan gebruiken

**Acceptatiecriteria:**
- [ ] Werkt op desktop (1920x1080+)
- [ ] Werkt op tablet (768px - 1024px)
- [ ] Werkt op mobile (320px - 767px)
- [ ] Touch-friendly op mobile
- [ ] Leesbare fonts en kleuren
- [ ] Consistente UI componenten
- [ ] Loading states
- [ ] Error states

**Technische taken:**
- CSS framework kiezen (Bootstrap/Tailwind)
- Responsive grid systeem
- Mobile-first design
- Breakpoints definiëren
- Component library opzetten
- Cross-browser testing

**Geschatte tijd:** 12 uur

---

## Sprint 2: Documentbeheer

### US008: Mappen aanmaken
**Als** gebruiker  
**Wil ik** mappen kunnen aanmaken  
**Zodat ik** mijn documenten georganiseerd kan opslaan

**Acceptatiecriteria:**
- [ ] "Nieuwe map" knop in interface
- [ ] Modal/formulier voor mapnaam
- [ ] Mapnaam validatie (geen speciale tekens)
- [ ] Mappen kunnen submappen bevatten
- [ ] Visuele mappenstructuur (tree view)
- [ ] Breadcrumb navigatie
- [ ] Succesbericht na aanmaken

**Technische taken:**
- Database tabel `folders` aanmaken
- Backend endpoint POST /api/folders
- Hierarchische structuur implementeren
- Frontend tree component
- Breadcrumb component
- Drag & drop (optioneel)

**Geschatte tijd:** 8 uur

---

### US009: Mappen verwijderen
**Als** gebruiker  
**Wil ik** mappen kunnen verwijderen  
**Zodat ik** mijn documentenstructuur kan onderhouden

**Acceptatiecriteria:**
- [ ] Verwijder knop bij elke map
- [ ] Bevestiging vereist
- [ ] Waarschuwing als map niet leeg is
- [ ] Recursief verwijderen van submappen
- [ ] Alle bestanden in map ook verwijderen
- [ ] Naar prullenbak (optioneel voor later)
- [ ] Activity log entry

**Technische taken:**
- Backend endpoint DELETE /api/folders/:id
- Recursieve delete functie
- Bestand cleanup
- Frontend bevestiging modal
- Tree view update

**Geschatte tijd:** 6 uur

---

### US010: Lege bestanden aanmaken
**Als** gebruiker  
**Wil ik** lege bestanden kunnen aanmaken  
**Zodat ik** mijn documenten kan voorbereiden

**Acceptatiecriteria:**
- [ ] "Nieuw bestand" optie
- [ ] Bestandsnaam invoeren
- [ ] Bestandstype kiezen (.txt, .md, etc.)
- [ ] Leeg bestand wordt aangemaakt
- [ ] Zichtbaar in bestandenlijst
- [ ] Kan later bewerkt/gevuld worden

**Technische taken:**
- Backend endpoint POST /api/documents/create
- Lege bestand genereren
- Database entry aanmaken
- Frontend formulier

**Geschatte tijd:** 4 uur

---

### US011: Bestanden verwijderen
**Als** gebruiker  
**Wil ik** bestanden kunnen verwijderen  
**Zodat ik** onnodige documenten in de kluis kan opruimen

**Acceptatiecriteria:**
- [ ] Verwijder knop bij elk bestand
- [ ] Bevestiging vereist
- [ ] Bestand wordt van schijf verwijderd
- [ ] Database entry verwijderd
- [ ] Alle versies ook verwijderd
- [ ] Activity log entry
- [ ] Succesbericht

**Technische taken:**
- Backend endpoint DELETE /api/documents/:id
- Fysiek bestand verwijderen
- Versies cleanup
- Database cleanup
- Frontend bevestiging

**Geschatte tijd:** 5 uur

---

### US012: Bestanden uploaden
**Als** gebruiker  
**Wil ik** bestanden kunnen uploaden  
**Zodat ik** mijn documenten in de kluis kan opslaan

**Acceptatiecriteria:**
- [ ] Upload knop/drag & drop gebied
- [ ] Multiple files upload
- [ ] Progress bar tijdens upload
- [ ] Bestandsgrootte limiet (bijv. 50MB)
- [ ] Toegestane bestandstypen configureerbaar
- [ ] Preview van geüploade bestanden
- [ ] Automatische versleuteling
- [ ] Succesbericht met lijst

**Technische taken:**
- Backend endpoint POST /api/documents/upload
- Multipart form data handling
- File size validation
- Encryptie implementeren (AES-256)
- Chunk upload voor grote bestanden
- Frontend upload component
- Progress tracking

**Geschatte tijd:** 12 uur

---

### US013: Bestanden downloaden
**Als** gebruiker  
**Wil ik** bestanden kunnen downloaden  
**Zodat ik** mijn documenten lokaal kan bekijken

**Acceptatiecriteria:**
- [ ] Download knop bij elk bestand
- [ ] Automatische decryptie
- [ ] Originele bestandsnaam behouden
- [ ] Download start automatisch
- [ ] Activity log entry
- [ ] Werkt voor alle bestandstypen

**Technische taken:**
- Backend endpoint GET /api/documents/:id/download
- Decryptie functie
- Stream response voor grote bestanden
- Frontend download handler
- Activity logging

**Geschatte tijd:** 6 uur

---

### US014: Automatische versleuteling
**Als** gebruiker  
**Wil ik** dat mijn bestanden automatisch versleuteld worden opgeslagen  
**Zodat ik** ze kan bekijken en niemand anders

**Acceptatiecriteria:**
- [ ] Alle bestanden encrypted at rest
- [ ] AES-256 encryptie
- [ ] Unieke key per gebruiker
- [ ] Transparant voor gebruiker
- [ ] Automatisch bij upload
- [ ] Automatisch decryptie bij download
- [ ] Keys veilig opgeslagen

**Technische taken:**
- Crypto library integreren (Node: crypto, Python: cryptography)
- Key management systeem
- Encrypt functie bij upload
- Decrypt functie bij download
- Key storage in database (encrypted)
- Testing van encryptie/decryptie

**Geschatte tijd:** 10 uur

---

### US015: Wachtwoorden gehashed
**Als** admin  
**Wil ik** dat alle wachtwoorden gehashed worden opgeslagen  
**Zodat ik** mijn gebruikersdata veilig hou

**Acceptatiecriteria:**
- [ ] Bcrypt gebruikt voor hashing
- [ ] Salt rounds: minimaal 12
- [ ] Geen plain-text wachtwoorden in database
- [ ] Hash verificatie bij login
- [ ] Bestaande wachtwoorden gemigreerd
- [ ] Admin kan wachtwoorden NIET zien

**Technische taken:**
- Bcrypt library implementeren
- Hash functie bij registratie
- Hash verificatie bij login
- Migration script voor bestaande data
- Security audit

**Geschatte tijd:** 4 uur (als niet al gedaan in Sprint 1)

---

## Sprint 3: Geavanceerde Features

### US016: Automatisch versiebeheer
**Als** gebruiker  
**Wil ik** dat er automatisch een nieuwe versie wordt aangemaakt wanneer ik een bestand opnieuw upload  
**Zodat ik** geen data verlies

**Acceptatiecriteria:**
- [ ] Bij re-upload wordt nieuwe versie aangemaakt
- [ ] Oude versie blijft behouden
- [ ] Versienummer automatisch incrementeren
- [ ] Timestamp bij elke versie
- [ ] Limiet aan aantal versies (bijv. 10)
- [ ] Oude versies automatisch opruimen
- [ ] Gebruiker ziet welke versie actueel is

**Technische taken:**
- Database tabel `document_versions`
- Backend logic voor versie creatie
- Versie cleanup functie
- Storage management
- Frontend versie indicator

**Geschatte tijd:** 10 uur

---

### US017: Geschiedenis bekijken
**Als** gebruiker  
**Wil ik** de geschiedenis van een bestand kunnen bekijken  
**Zodat ik** weet welke wijzigingen er zijn gemaakt

**Acceptatiecriteria:**
- [ ] Versiegeschiedenis per bestand
- [ ] Lijst met versienummers en timestamps
- [ ] Bestandsgrootte per versie
- [ ] Huidige versie gemarkeerd
- [ ] Sorteerbaar op datum
- [ ] Optie om versie te restoren

**Technische taken:**
- Backend endpoint GET /api/documents/:id/versions
- Frontend versie geschiedenis component
- Tijdlijn weergave
- Version comparison (optioneel)

**Geschatte tijd:** 6 uur

---

### US018: Eerdere versies downloaden
**Als** gebruiker  
**Wil ik** eerdere versies van een bestand kunnen downloaden  
**Zodat ik** kan terugkeren naar een vorige versie

**Acceptatiecriteria:**
- [ ] Download knop bij elke oude versie
- [ ] Bestand met versienummer in naam
- [ ] Automatische decryptie
- [ ] Activity log entry
- [ ] Optie om te restoren als huidige versie

**Technische taken:**
- Backend endpoint GET /api/documents/:id/versions/:versionId/download
- Versie restore functie
- Frontend download handlers
- Activity logging

**Geschatte tijd:** 5 uur

---

### US019: Complete gebruikerslog (Admin)
**Als** admin  
**Wil ik** een complete log kunnen zien van alle gebruikersactiviteiten  
**Zodat ik** het systeem kan monitoren

**Acceptatiecriteria:**
- [ ] Admin dashboard met logs
- [ ] Alle gebruikersacties gelogd
- [ ] Timestamp, gebruiker, actie, details
- [ ] IP adres bijhouden
- [ ] Filterbaar op gebruiker, actie, datum
- [ ] Zoekfunctie
- [ ] Export functie (CSV/PDF)
- [ ] Paginering voor performance

**Technische taken:**
- Database tabel `activity_logs`
- Logging middleware
- Backend endpoint GET /api/admin/logs
- Admin dashboard pagina
- Filter & zoek functionaliteit
- Export functionaliteit

**Geschatte tijd:** 12 uur

---

### US020: Zoeken naar bestanden
**Als** gebruiker  
**Wil ik** kunnen zoeken naar bestanden op naam of inhoud  
**Zodat ik** snel documenten kan vinden

**Acceptatiecriteria:**
- [ ] Zoekbalk in interface
- [ ] Zoeken op bestandsnaam
- [ ] Zoeken in mapnamen
- [ ] Real-time zoekresultaten
- [ ] Highlight van zoekterm
- [ ] Filter opties (datum, type, grootte)
- [ ] Zoek ook in oude versies (optioneel)

**Technische taken:**
- Backend endpoint GET /api/search?q=term
- Database full-text search
- Search indexing
- Frontend search component
- Autocomplete (optioneel)
- Search filters

**Geschatte tijd:** 10 uur

---

### US021: Wachtwoord resetten
**Als** gebruiker  
**Wil ik** mijn wachtwoord kunnen resetten via e-mail  
**Zodat ik** toegang behoud als ik mijn wachtwoord vergeet

**Acceptatiecriteria:**
- [ ] "Wachtwoord vergeten" link op login
- [ ] Email invoeren voor reset
- [ ] Reset link via email
- [ ] Token geldig voor 1 uur
- [ ] Nieuw wachtwoord instellen
- [ ] Token wordt eenmalig gebruikt
- [ ] Email bevestiging van reset
- [ ] Oude sessies uitloggen

**Technische taken:**
- Database tabel `password_resets`
- Backend endpoints voor reset flow
- Email service integratie
- Token generatie en verificatie
- Frontend reset pagina's
- Email templates

**Geschatte tijd:** 8 uur

---

### US022: Two-factor authenticatie instellen
**Als** gebruiker  
**Wil ik** twee-stappen verificatie kunnen instellen  
**Zodat ik** mijn account extra kan beveiligen

**Acceptatiecriteria:**
- [ ] 2FA optie in account instellingen
- [ ] QR code voor authenticator app
- [ ] Backup codes genereren
- [ ] Test code vereist bij activeren
- [ ] 2FA kan uitgeschakeld worden
- [ ] Email notificatie bij wijziging

**Technische taken:**
- TOTP library integreren
- Backend endpoints voor 2FA setup
- QR code generatie
- Backup codes systeem
- Frontend setup wizard
- Database velden voor 2FA

**Geschatte tijd:** 10 uur

---

### US023: Inloggen met 2FA
**Als** gebruiker  
**Wil ik** kunnen inloggen met een 2FA-code  
**Zodat ik** alleen toegang heb tot mijn account

**Acceptatiecriteria:**
- [ ] Extra stap na wachtwoord invoer
- [ ] 6-cijferige code invoeren
- [ ] Code validatie
- [ ] Backup code optie
- [ ] "Vertrouw dit apparaat" optie
- [ ] Foutmelding bij incorrecte code
- [ ] Rate limiting

**Technische taken:**
- Login flow aanpassen
- TOTP verificatie
- Trusted devices systeem
- Backend verificatie logic
- Frontend 2FA input pagina

**Geschatte tijd:** 6 uur

---

### US024: Activiteitenlog (eigen acties)
**Als** gebruiker  
**Wil ik** een activiteitenlog van mijn eigen acties kunnen bekijken  
**Zodat ik** kan zien wat er met mijn bestanden is gebeurd

**Acceptatiecriteria:**
- [ ] Activiteiten pagina in profiel
- [ ] Lijst van eigen acties
- [ ] Timestamp, actie, bestand
- [ ] Filterbaar op actie type
- [ ] Filterbaar op datum
- [ ] Paginering
- [ ] IP adres weergave (optioneel)

**Technische taken:**
- Backend endpoint GET /api/users/activity
- Filter functionaliteit
- Frontend activity log pagina
- Timeline weergave

**Geschatte tijd:** 6 uur

---

## Totale Schatting

**Sprint 1:** ~50 uur  
**Sprint 2:** ~55 uur  
**Sprint 3:** ~73 uur  

**Totaal:** ~178 uur (~22-23 werkdagen bij 8 uur/dag)

## Prioriteiten

**Must Have (MVP):**
- US001-US007 (Sprint 1 compleet)
- US008-US013 (Basis documentbeheer)
- US014-US015 (Beveiliging)

**Should Have:**
- US016-US018 (Versiebeheer)
- US020 (Zoeken)
- US021 (Wachtwoord reset)

**Could Have:**
- US019 (Admin logging)
- US022-US023 (2FA)
- US024 (Activiteitenlog gebruiker)

**Won't Have (deze fase):**
- Real-time collaboration
- Mobile app
- External API
