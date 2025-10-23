# 🔐 Beveiligde Documentenkluis

Een veilige webapplicatie voor het opslaan en beheren van vertrouwelijke documenten met end-to-end encryptie.

## 📋 Project Info

- **Naam:** Beveiligde Documentenkluis
- **Maker:** Mohamed Koubaa
- **Vak:** Projectmanagement
- **Schooljaar:** 2025-2026
- **Leerkracht:** Gerrit Wijns
- **Klant:** Yassine Bibi

## 🎯 Doel

Een veilige en gebruiksvriendelijke webapplicatie waarbij gebruikers documenten kunnen uploaden, opslaan en beheren met:
- **AES-256 encryptie** voor maximale beveiliging
- **Two-Factor Authentication (2FA)** voor extra bescherming
- **Versie beheer** voor documentgeschiedenis
- **Activity logs** voor audit trail
- **Trusted devices** voor veilige toegang

## 🚀 Features

### ✅ Sprint 1 (Compleet)
- [x] User registratie en authenticatie
- [x] Login/logout functionaliteit
- [x] Profile management (gegevens, wachtwoord wijzigen)
- [x] Account verwijderen
- [x] Admin rol systeem
- [x] Responsive design (mobile/tablet/desktop)

### 🔄 Sprint 2 (In ontwikkeling)
- [ ] Document upload met encryptie
- [ ] Folder structuur
- [ ] Document delen tussen gebruikers
- [ ] Versie beheer
- [ ] Download functionaliteit

### 📅 Sprint 3 (Gepland)
- [ ] Two-Factor Authentication (2FA)
- [ ] Activity logs
- [ ] Trusted devices
- [ ] Email notificaties
- [ ] Admin dashboard
- [ ] User management

## 🛠️ Tech Stack

- **Framework:** Laravel
- **Programmeertaal:** PHP
- **Database:** MySQL
- **Cache/Queue:** Laravel Redis
- **Email Testing:** Mailpit
- **Database Management:** phpMyAdmin
- **Containerisatie:** Docker + Laravel Sail

## 📦 Installatie

### Vereisten
- Docker Desktop
- Git

### Installatie

1. **Clone repository**
```bash
git clone https://github.com/teamkoubyte/Schooljaar-2025-2026.git
cd "02 - Projectmanagement/02 - Projecten/02 - Eindproject/beveiligdeDocumentenkluis"
```

2. **Environment configuratie**
```bash
cp .env.example .env
```

3. **Docker containers starten**
```bash
docker compose up -d
```

4. **Dependencies installeren**
```bash
docker compose exec laravel.test composer install
docker compose exec laravel.test npm install
```

5. **Application key genereren**
```bash
docker compose exec laravel.test php artisan key:generate
```

6. **Database migreren**
```bash
docker compose exec laravel.test php artisan migrate
```

7. **Assets builden**
```bash
docker compose exec laravel.test npm run build
```

## 🌐 Toegang

- **Webapplicatie:** http://localhost:8000
- **phpMyAdmin:** http://localhost:8080
- **Mailpit:** http://localhost:8025

## 👤 Login Gegevens

### Admin Account
- **Email:** admin@documentenkluis.be
- **Password:** admin123

### Test Account
Registreer een nieuwe gebruiker via http://localhost:8000/register

## 🗄️ Database

**Connectie gegevens:**
- Host: `localhost`
- Port: `3306`
- Database: `laravel`
- Username: `sail`
- Password: `password`

## 📝 Development

### Artisan Commando's
```bash
# Migrations
docker compose exec laravel.test php artisan migrate

# Cache clearen
docker compose exec laravel.test php artisan cache:clear

# Logs bekijken
docker compose logs -f laravel.test

# Tinker (interactieve shell)
docker compose exec laravel.test php artisan tinker
```

### Docker Commando's
```bash
# Start containers
docker compose up -d

# Stop containers
docker compose down

# Restart containers
docker compose restart

# Shell toegang
docker compose exec laravel.test bash
```
---
