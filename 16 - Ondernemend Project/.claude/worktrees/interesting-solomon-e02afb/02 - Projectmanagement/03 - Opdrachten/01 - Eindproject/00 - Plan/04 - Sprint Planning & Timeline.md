# Sprint Planning & Timeline

## Project Timeline

**Projectstart:** 18 oktober 2025  
**Verwachte oplevering:** [Te bepalen - ca. 12-16 weken]

---

## Sprint 1: Authenticatie & Basis Setup
**Duur:** 2-3 weken  
**Focus:** Gebruikersbeheer, authenticatie, basis UI

### Week 1-2: Setup & Authenticatie

#### Dag 1-2: Project Setup
- [ ] Git repository opzetten
- [ ] Backend project initialiseren (Node.js + Express + TypeScript)
- [ ] Frontend project initialiseren (React + TypeScript + Vite)
- [ ] Database opzetten (MySQL installatie + configuratie)
- [ ] Development environment configureren
- [ ] .gitignore en environment files
- [ ] README.md met setup instructies

**Deliverable:** Werkende development environment

#### Dag 3-5: Database & User Model
- [ ] Database schema ontwerpen
- [ ] Users tabel aanmaken
- [ ] Prisma/Sequelize setup
- [ ] Database migrations
- [ ] User model implementeren
- [ ] Basic CRUD operaties testen

**Deliverable:** Werkende database met user model

#### Dag 6-8: Registratie & Login
- [ ] US001: Registratie backend endpoint
- [ ] US002: Login backend endpoint
- [ ] Password hashing met bcrypt implementeren
- [ ] JWT token generatie
- [ ] Registratie frontend formulier
- [ ] Login frontend formulier
- [ ] Form validatie
- [ ] Error handling

**Deliverable:** Werkende registratie en login

#### Dag 9-10: Authenticatie Middleware
- [ ] JWT verificatie middleware
- [ ] Protected routes implementeren
- [ ] Auth context in frontend
- [ ] Automatic token refresh
- [ ] Logout functionaliteit
- [ ] Session management

**Deliverable:** Complete authenticatie systeem

### Week 3: Gebruikersbeheer & UI

#### Dag 11-12: Profiel Management
- [ ] US003: Profiel wijzigen (backend + frontend)
- [ ] US004: Wachtwoord wijzigen (backend + frontend)
- [ ] US005: Account verwijderen (backend + frontend)
- [ ] Profiel pagina UI
- [ ] Bevestigingsmodals
- [ ] Success/error notifications

**Deliverable:** Complete profiel management

#### Dag 13-14: Admin Functionaliteit
- [ ] US006: Admin login implementeren
- [ ] Role-based access control (RBAC)
- [ ] Admin middleware
- [ ] Admin dashboard basis UI
- [ ] Admin navigatie
- [ ] Admin-only routes

**Deliverable:** Admin toegang en basis dashboard

#### Dag 15-17: Responsive Design
- [ ] US007: Responsive layout implementeren
- [ ] Tailwind CSS setup en configuratie
- [ ] Layout componenten (Header, Sidebar, Footer)
- [ ] Mobile menu
- [ ] Responsive forms
- [ ] Breakpoint testing
- [ ] Cross-browser testing

**Deliverable:** Volledig responsive interface

#### Dag 18-19: Testing & Bug Fixes Sprint 1
- [ ] Unit tests voor auth functies
- [ ] Integration tests voor API endpoints
- [ ] E2E tests voor user flows
- [ ] Bug fixes
- [ ] Code review
- [ ] Documentatie bijwerken

#### Dag 20: Sprint Review & Demo
- [ ] Demo aan leerkracht
- [ ] Feedback verzamelen
- [ ] Sprint retrospective
- [ ] Logboek bijwerken
- [ ] Planning Sprint 2

**Sprint 1 Deliverables:**
- ✅ Werkende authenticatie (register, login, logout)
- ✅ Gebruikersbeheer (profiel, wachtwoord, account verwijderen)
- ✅ Admin toegang
- ✅ Responsive design
- ✅ Unit en integration tests
- ✅ Documentatie

---

## Sprint 2: Documentbeheer & Organisatie
**Duur:** 3-4 weken  
**Focus:** Mappen, bestanden, upload/download, encryptie

### Week 4: Mappenstructuur

#### Dag 21-22: Database & Models
- [ ] Folders tabel aanmaken
- [ ] Folder model implementeren
- [ ] Hierarchische structuur (parent-child relatie)
- [ ] Documents tabel aanmaken
- [ ] Document model implementeren
- [ ] Database relaties configureren

**Deliverable:** Database schema voor documenten en mappen

#### Dag 23-25: Mappen Functionaliteit
- [ ] US008: Mappen aanmaken (backend + frontend)
- [ ] US009: Mappen verwijderen (backend + frontend)
- [ ] Folder tree component
- [ ] Breadcrumb navigatie
- [ ] Folder navigatie UI
- [ ] Recursieve delete implementeren

**Deliverable:** Complete mappenstructuur

#### Dag 26-27: Lege Bestanden
- [ ] US010: Lege bestanden aanmaken
- [ ] US011: Bestanden verwijderen
- [ ] Document list component
- [ ] Document card component
- [ ] Delete confirmation modal
- [ ] UI voor bestandenbeheer

**Deliverable:** Basis documentbeheer

### Week 5: File Upload & Storage

#### Dag 28-30: Upload Functionaliteit
- [ ] Multer configuratie voor file uploads
- [ ] Storage directory structuur opzetten
- [ ] US012: Upload backend endpoint
- [ ] Multiple files upload support
- [ ] File validation (size, type)
- [ ] Progress tracking
- [ ] Upload UI met drag & drop
- [ ] Progress bar component

**Deliverable:** Werkende upload functionaliteit

#### Dag 31-32: Encryptie Implementatie
- [ ] US014: Encryption service opzetten
- [ ] AES-256 encryptie implementeren
- [ ] User encryption key generatie
- [ ] Encrypt functie bij upload
- [ ] Key management systeem
- [ ] Encryptie testen

**Deliverable:** Automatische bestandsversleuteling

#### Dag 33-34: Download Functionaliteit
- [ ] US013: Download backend endpoint
- [ ] Decryptie implementeren
- [ ] Stream-based download
- [ ] Download UI
- [ ] File preview (optioneel)
- [ ] Download tracking

**Deliverable:** Veilige download met decryptie

### Week 6: Password Security & Testing

#### Dag 35-36: Password Hashing
- [ ] US015: Bcrypt implementatie verifiëren
- [ ] Password policies implementeren
- [ ] Password strength meter
- [ ] Security audit
- [ ] Penetration testing

**Deliverable:** Veilige password implementatie

#### Dag 37-38: Activity Logging Basis
- [ ] Activity logs tabel aanmaken
- [ ] Logging middleware
- [ ] Log belangrijke acties (upload, download, delete)
- [ ] IP tracking
- [ ] Basis log viewer (admin)

**Deliverable:** Basis activity logging

#### Dag 39-40: Testing & Refinement
- [ ] Unit tests voor document/folder services
- [ ] Integration tests voor upload/download
- [ ] Security testing (encryptie/decryptie)
- [ ] Performance testing
- [ ] Bug fixes
- [ ] UI/UX improvements

### Week 7: Sprint Review & Polish

#### Dag 41-42: Code Quality
- [ ] Code review
- [ ] Refactoring
- [ ] Documentation
- [ ] Comments toevoegen
- [ ] API documentatie (Swagger)

#### Dag 43-44: Sprint Review
- [ ] Demo aan leerkracht
- [ ] Demo aan klant (Yassine Bibi)
- [ ] Feedback verzamelen
- [ ] Sprint retrospective
- [ ] Logboek bijwerken
- [ ] Planning Sprint 3

**Sprint 2 Deliverables:**
- ✅ Mappenstructuur met hierarchie
- ✅ Bestand upload met encryptie
- ✅ Bestand download met decryptie
- ✅ Document en map CRUD operaties
- ✅ Basis activity logging
- ✅ Tests en documentatie

---

## Sprint 3: Geavanceerde Features & Beveiliging
**Duur:** 3-4 weken  
**Focus:** Versiebeheer, zoeken, 2FA, logging

### Week 8: Versiebeheer

#### Dag 45-47: Version Control System
- [ ] Document_versions tabel aanmaken
- [ ] US016: Automatisch versiebeheer implementeren
- [ ] Version service opzetten
- [ ] Versie creatie bij re-upload
- [ ] Version cleanup (max 10 versies)
- [ ] Storage voor oude versies

**Deliverable:** Automatisch versiebeheer

#### Dag 48-50: Version History UI
- [ ] US017: Versie geschiedenis bekijken
- [ ] US018: Oude versies downloaden
- [ ] Version history component
- [ ] Timeline weergave
- [ ] Version restore functionaliteit
- [ ] Version comparison (optioneel)

**Deliverable:** Complete version control

### Week 9: Search & Password Reset

#### Dag 51-53: Zoekfunctionaliteit
- [ ] US020: Search backend implementeren
- [ ] Full-text search in database
- [ ] Search indexing
- [ ] Search frontend component
- [ ] Autocomplete/suggestions
- [ ] Search filters (date, type, size)
- [ ] Search highlighting

**Deliverable:** Complete zoekfunctionaliteit

#### Dag 54-56: Password Reset
- [ ] US021: Password reset flow implementeren
- [ ] Password_resets tabel aanmaken
- [ ] Email service setup (Nodemailer)
- [ ] Token generatie en verificatie
- [ ] Email templates ontwerpen
- [ ] Reset password frontend pagina's
- [ ] Token expiration (1 uur)

**Deliverable:** Wachtwoord reset via email

### Week 10: Two-Factor Authentication

#### Dag 57-59: 2FA Setup
- [ ] US022: 2FA setup implementeren
- [ ] TOTP library integreren (speakeasy)
- [ ] QR code generatie
- [ ] Backup codes systeem
- [ ] 2FA database velden
- [ ] 2FA setup wizard UI
- [ ] Test en activate flow

**Deliverable:** 2FA setup functionaliteit

#### Dag 60-61: 2FA Login
- [ ] US023: 2FA login implementeren
- [ ] Login flow aanpassen
- [ ] TOTP verificatie
- [ ] Backup code verificatie
- [ ] Trusted devices systeem
- [ ] 2FA input UI
- [ ] "Remember device" optie

**Deliverable:** Complete 2FA implementatie

#### Dag 62: 2FA Testing
- [ ] 2FA flow testen
- [ ] Security audit
- [ ] Edge cases testen
- [ ] Bug fixes

### Week 11: Logging & Activity

#### Dag 63-65: Complete Logging System
- [ ] US019: Complete admin logging implementeren
- [ ] Alle acties loggen
- [ ] Log filtering en searching
- [ ] Export functionaliteit (CSV)
- [ ] Admin logs dashboard
- [ ] Log viewer met pagination
- [ ] Log statistics

**Deliverable:** Complete admin logging

#### Dag 66-67: User Activity Log
- [ ] US024: User activity log implementeren
- [ ] User activity pagina
- [ ] Filter op actie type
- [ ] Filter op datum
- [ ] Activity timeline UI
- [ ] IP address weergave

**Deliverable:** User activity log

### Week 12: Testing, Bugfixes & Polish

#### Dag 68-70: Comprehensive Testing
- [ ] Complete E2E test suite
- [ ] Security penetration testing
- [ ] Performance testing
- [ ] Load testing
- [ ] Cross-browser testing
- [ ] Mobile device testing
- [ ] Bug tracking en fixes

#### Dag 71-72: UI/UX Polish
- [ ] UI consistency check
- [ ] Loading states verbeteren
- [ ] Error messages verbeteren
- [ ] Animations toevoegen
- [ ] Accessibility improvements
- [ ] User feedback implementeren

#### Dag 73-74: Documentation
- [ ] Gebruikershandleiding schrijven
- [ ] Technische documentatie voltooien
- [ ] API documentatie
- [ ] Deployment guide
- [ ] README files
- [ ] Code comments

#### Dag 75-76: Sprint Review
- [ ] Final demo aan leerkracht
- [ ] Final demo aan klant
- [ ] Feedback verwerken
- [ ] Sprint retrospective
- [ ] Logboek voltooien
- [ ] Acceptance criteria checken

**Sprint 3 Deliverables:**
- ✅ Automatisch versiebeheer
- ✅ Zoekfunctionaliteit
- ✅ Two-factor authenticatie
- ✅ Password reset via email
- ✅ Complete admin logging
- ✅ User activity log
- ✅ Tests en documentatie

---

## Week 13-14: Final Phase

### Week 13: Deployment & Final Testing

#### Dag 77-78: Deployment Preparation
- [ ] Production environment opzetten
- [ ] Database migreren naar production
- [ ] Environment variables configureren
- [ ] HTTPS certificaat (Let's Encrypt)
- [ ] Domain configuratie
- [ ] Performance optimization

#### Dag 79-80: Deployment
- [ ] Backend deployen
- [ ] Frontend deployen
- [ ] Database backup systeem
- [ ] Monitoring setup
- [ ] Error tracking setup
- [ ] Production testing

#### Dag 81-82: User Acceptance Testing
- [ ] Klant test sessie
- [ ] Gebruikers feedback
- [ ] Bug fixes
- [ ] Minor improvements
- [ ] Performance tuning

### Week 14: Finale Oplevering

#### Dag 83-84: Documentation Finalization
- [ ] Complete gebruikershandleiding
- [ ] Video tutorial (optioneel)
- [ ] Admin handleiding
- [ ] Troubleshooting guide
- [ ] FAQ sectie

#### Dag 85-86: Presentation Preparation
- [ ] PowerPoint presentatie maken
- [ ] Demo scenario voorbereiden
- [ ] Test data voorbereiden
- [ ] Backup plan voor demo
- [ ] Presentatie oefenen

#### Dag 87-88: Final Review
- [ ] Alle deliverables checken
- [ ] Success criteria verifiëren
- [ ] Final code review
- [ ] Documentation review
- [ ] Archive project files

#### Dag 89-90: Presentation & Handover
- [ ] **Finale presentatie**
- [ ] Live demo
- [ ] Q&A sessie
- [ ] Project handover aan klant
- [ ] Source code overdracht
- [ ] Database overdracht
- [ ] Onderhoudsinstructies

---

## Milestones

| Milestone | Datum | Deliverables |
|-----------|-------|--------------|
| M1: Sprint 1 Complete | Week 3 | Authenticatie & Basis UI |
| M2: Sprint 2 Complete | Week 7 | Documentbeheer & Encryptie |
| M3: Sprint 3 Complete | Week 12 | Geavanceerde Features |
| M4: Testing Complete | Week 13 | Alle tests groen |
| M5: Deployment | Week 13 | Live productie omgeving |
| M6: Final Delivery | Week 14 | Presentatie & Handover |

---

## Daily Routine

### Dagelijkse Workflow
- **9:00 - 9:15:** Planning van de dag, taken prioriteren
- **9:15 - 12:00:** Development work
- **12:00 - 13:00:** Lunch break
- **13:00 - 16:00:** Development work continued
- **16:00 - 16:30:** Testing van nieuwe features
- **16:30 - 17:00:** Logboek bijwerken, git commits, daily review

### Weekly Routine
- **Maandag:** Sprint planning / week planning
- **Woensdag:** Mid-week check-in, blockers bespreken
- **Vrijdag:** Weekly review, demo voorbereiding

---

## Risk Management Planning

### Weekly Risk Assessment
Elk einde van de week:
- [ ] Voortgang vs. planning checken
- [ ] Blockers identificeren
- [ ] Risico's evalueren
- [ ] Mitigatie acties plannen
- [ ] Communicatie met leerkracht/klant indien nodig

### Contingency Planning
- **Als achterstand ontstaat:** Features prioriteren (MVP eerst)
- **Als technische problemen:** Hulp vragen, alternatieve oplossingen
- **Als scope creep:** Overleg met klant, features naar toekomstige fase

---

## Communication Schedule

### Met Leerkracht (Gerrit Wijns)
- **Frequency:** Einde van elke sprint
- **Format:** Demo + voortgangsbespreking
- **Duration:** 30-60 minuten

### Met Klant (Yassine Bibi)
- **Frequency:** Einde Sprint 2 & Sprint 3
- **Format:** Demo + feedback sessie
- **Duration:** 60 minuten
- **Channel:** Teams/Zoom meeting

### Email Updates
- **Frequency:** Wekelijks (vrijdag)
- **To:** Leerkracht en klant
- **Content:** Korte samenvatting voortgang, komende week planning

---

## Success Metrics

### Technical Metrics
- [ ] 100% user stories Sprint 1-3 geïmplementeerd
- [ ] Test coverage > 80%
- [ ] 0 critical bugs
- [ ] < 5 minor bugs
- [ ] Page load time < 3 seconden
- [ ] API response time < 500ms

### Project Metrics
- [ ] Binnen tijdsplanning
- [ ] Alle documentatie compleet
- [ ] Client satisfaction score > 8/10
- [ ] Code quality score > B (SonarQube)

---

**Deze planning is flexibel en kan aangepast worden op basis van voortgang en feedback.**
