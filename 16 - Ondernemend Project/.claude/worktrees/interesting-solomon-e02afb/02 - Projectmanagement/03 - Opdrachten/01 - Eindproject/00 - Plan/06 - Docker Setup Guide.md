# Docker Setup Guide - Documentenkluis

## Overzicht

Dit project gebruikt Docker om een complete development environment op te zetten met:
- **Nginx** - Web server en reverse proxy
- **PHP-FPM** - Laravel backend
- **MySQL** - Database
- **Redis** - Caching en queue management
- **MailHog** - Email testing (development only)
- **Node** - React frontend development

---

## Prerequisites

### Vereiste Software
- **Docker Desktop** 24+ ([Download](https://www.docker.com/products/docker-desktop))
- **Git** ([Download](https://git-scm.com/downloads))
- **Code editor** (VS Code aanbevolen)

### System Requirements
- **OS:** Windows 10/11, macOS, of Linux
- **RAM:** Minimaal 4GB (8GB aanbevolen)
- **Disk space:** Minimaal 10GB vrij
- **CPU:** 64-bit processor met virtualisatie ondersteuning

---

## Installatie Stappen

### 1. Docker Desktop Installeren

**Windows:**
```powershell
# Download Docker Desktop voor Windows
# https://www.docker.com/products/docker-desktop

# Na installatie, controleer versie:
docker --version
docker-compose --version
```

**macOS:**
```bash
# Via Homebrew:
brew install --cask docker

# Of download van website:
# https://www.docker.com/products/docker-desktop

# Controleer installatie:
docker --version
docker-compose --version
```

**Linux (Ubuntu/Debian):**
```bash
# Update package index
sudo apt-get update

# Install dependencies
sudo apt-get install ca-certificates curl gnupg lsb-release

# Add Docker's official GPG key
sudo mkdir -p /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg

# Setup repository
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

# Install Docker Engine
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-compose-plugin

# Verify installation
docker --version
docker compose version
```

### 2. Project Setup

```bash
# Clone repository
git clone https://github.com/teamkoubyte/documentenkluis.git
cd documentenkluis

# Project structuur aanmaken
mkdir -p backend frontend docker/nginx docker/php docker/mysql

# Environment files kopieren
cp backend/.env.example backend/.env
cp frontend/.env.example frontend/.env
```

### 3. Docker Configuration Files Aanmaken

**docker-compose.yml** (in root van project):
```yaml
version: '3.8'

services:
  nginx:
    image: nginx:1.25-alpine
    container_name: documentkluis_nginx
    ports:
      - "80:80"
    volumes:
      - ./backend:/var/www/html
      - ./docker/nginx/default.conf:/etc/nginx/conf.d/default.conf
      - ./frontend/dist:/var/www/frontend
    depends_on:
      - app
    networks:
      - documentkluis_network

  app:
    build:
      context: ./docker/php
      dockerfile: Dockerfile
    container_name: documentkluis_app
    working_dir: /var/www/html
    volumes:
      - ./backend:/var/www/html
      - ./docker/php/php.ini:/usr/local/etc/php/conf.d/custom.ini
    environment:
      - DB_HOST=mysql
      - DB_DATABASE=documentkluis
      - DB_USERNAME=laravel
      - DB_PASSWORD=secret
      - REDIS_HOST=redis
    depends_on:
      - mysql
      - redis
    networks:
      - documentkluis_network

  mysql:
    image: mysql:8.0
    container_name: documentkluis_mysql
    ports:
      - "3306:3306"
    environment:
      MYSQL_DATABASE: documentkluis
      MYSQL_USER: laravel
      MYSQL_PASSWORD: secret
      MYSQL_ROOT_PASSWORD: root_secret
    volumes:
      - mysql_data:/var/lib/mysql
      - ./docker/mysql/my.cnf:/etc/mysql/conf.d/my.cnf
    networks:
      - documentkluis_network

  redis:
    image: redis:7.2-alpine
    container_name: documentkluis_redis
    ports:
      - "6379:6379"
    volumes:
      - redis_data:/data
    networks:
      - documentkluis_network

  mailhog:
    image: mailhog/mailhog:latest
    container_name: documentkluis_mailhog
    ports:
      - "1025:1025"
      - "8025:8025"
    networks:
      - documentkluis_network

  node:
    image: node:18-alpine
    container_name: documentkluis_node
    working_dir: /app
    volumes:
      - ./frontend:/app
    command: sh -c "npm install && npm run dev"
    ports:
      - "3000:3000"
    networks:
      - documentkluis_network

networks:
  documentkluis_network:
    driver: bridge

volumes:
  mysql_data:
  redis_data:
```

**docker/php/Dockerfile**:
```dockerfile
FROM php:8.2-fpm-alpine

# Install system dependencies
RUN apk add --no-cache \
    git \
    curl \
    libpng-dev \
    libzip-dev \
    oniguruma-dev \
    zip \
    unzip \
    mysql-client

# Install PHP extensions
RUN docker-php-ext-install \
    pdo_mysql \
    mbstring \
    exif \
    pcntl \
    bcmath \
    gd \
    zip

# Install Redis extension
RUN apk add --no-cache $PHPIZE_DEPS \
    && pecl install redis \
    && docker-php-ext-enable redis

# Install Composer
COPY --from=composer:latest /usr/bin/composer /usr/bin/composer

WORKDIR /var/www/html

EXPOSE 9000
CMD ["php-fpm"]
```

**docker/nginx/default.conf**:
```nginx
server {
    listen 80;
    server_name localhost;
    root /var/www/html/public;
    
    index index.php index.html;

    # Laravel API
    location /api {
        try_files $uri $uri/ /index.php?$query_string;
    }

    location ~ \.php$ {
        try_files $uri =404;
        fastcgi_split_path_info ^(.+\.php)(/.+)$;
        fastcgi_pass app:9000;
        fastcgi_index index.php;
        include fastcgi_params;
        fastcgi_param SCRIPT_FILENAME $document_root$fastcgi_script_name;
        fastcgi_param PATH_INFO $fastcgi_path_info;
    }

    # React Frontend
    location / {
        root /var/www/frontend;
        try_files $uri $uri/ /index.html;
    }

    location ~ /\. {
        deny all;
    }

    client_max_body_size 50M;
}
```

**docker/php/php.ini**:
```ini
[PHP]
max_execution_time = 300
upload_max_filesize = 50M
post_max_size = 50M
memory_limit = 256M
display_errors = On
error_reporting = E_ALL
date.timezone = Europe/Brussels
```

**docker/mysql/my.cnf**:
```ini
[mysqld]
character-set-server = utf8mb4
collation-server = utf8mb4_unicode_ci
innodb_buffer_pool_size = 256M
max_connections = 100
```

### 4. Makefile Aanmaken (Optioneel maar Handig)

**Makefile** (in root van project):
```makefile
.PHONY: help up down build restart logs shell composer npm migrate seed fresh test

help:
	@echo "Available commands:"
	@echo "  make up              - Start all containers"
	@echo "  make down            - Stop all containers"
	@echo "  make build           - Build containers"
	@echo "  make restart         - Restart containers"
	@echo "  make logs            - Show container logs"
	@echo "  make shell           - Access app container"
	@echo "  make composer-install - Install composer dependencies"
	@echo "  make npm-install     - Install npm dependencies"
	@echo "  make migrate         - Run migrations"
	@echo "  make seed            - Seed database"
	@echo "  make fresh           - Fresh migration with seed"
	@echo "  make test            - Run tests"
	@echo "  make setup           - Complete initial setup"

up:
	docker-compose up -d

down:
	docker-compose down

build:
	docker-compose build

restart:
	docker-compose restart

logs:
	docker-compose logs -f

shell:
	docker-compose exec app sh

composer-install:
	docker-compose exec app composer install

npm-install:
	docker-compose exec node npm install

migrate:
	docker-compose exec app php artisan migrate

seed:
	docker-compose exec app php artisan db:seed

fresh:
	docker-compose exec app php artisan migrate:fresh --seed

test:
	docker-compose exec app php artisan test

key-generate:
	docker-compose exec app php artisan key:generate

storage-link:
	docker-compose exec app php artisan storage:link

optimize:
	docker-compose exec app php artisan optimize

cache-clear:
	docker-compose exec app php artisan cache:clear
	docker-compose exec app php artisan config:clear
	docker-compose exec app php artisan route:clear
	docker-compose exec app php artisan view:clear

setup: build up composer-install key-generate migrate storage-link
	@echo "Setup complete! Access your application at:"
	@echo "  Frontend: http://localhost:3000"
	@echo "  Backend API: http://localhost/api"
	@echo "  MailHog: http://localhost:8025"
```

---

## Development Workflow

### Eerste Keer Setup

```bash
# 1. Start containers en bouw images
make build
make up

# 2. Installeer Laravel dependencies
make composer-install

# 3. Installeer frontend dependencies
make npm-install

# 4. Genereer application key
make key-generate

# 5. Run database migrations
make migrate

# 6. (Optioneel) Seed database met test data
make seed

# 7. Create storage symlink
make storage-link

# 8. Access de applicatie
# Frontend: http://localhost:3000
# Backend: http://localhost/api
# MailHog: http://localhost:8025
# phpMyAdmin: http://localhost:8080 (optioneel toe te voegen)
```

**Of gebruik de shortcut:**
```bash
make setup
```

### Dagelijks Gebruik

```bash
# Start containers
make up
# of
docker-compose up -d

# Check logs
make logs

# Stop containers
make down

# Restart een specifieke service
docker-compose restart nginx
docker-compose restart app
```

### Database Management

```bash
# Run nieuwe migrations
make migrate

# Rollback laatste migration
docker-compose exec app php artisan migrate:rollback

# Fresh install met seed
make fresh

# Database backup (manual)
docker-compose exec mysql mysqldump -u laravel -psecret documentkluis > backup.sql

# Database restore
docker-compose exec -T mysql mysql -u laravel -psecret documentkluis < backup.sql
```

### Laravel Artisan Commands

```bash
# Run artisan commands
docker-compose exec app php artisan [command]

# Voorbeelden:
docker-compose exec app php artisan route:list
docker-compose exec app php artisan make:controller DocumentController
docker-compose exec app php artisan make:model Document -m
docker-compose exec app php artisan make:migration create_documents_table
docker-compose exec app php artisan tinker
```

### Composer Commands

```bash
# Install package
docker-compose exec app composer require package/name

# Update dependencies
docker-compose exec app composer update

# Dump autoload
docker-compose exec app composer dump-autoload
```

### NPM Commands (Frontend)

```bash
# Install package
docker-compose exec node npm install package-name

# Build for production
docker-compose exec node npm run build

# Run dev server
docker-compose exec node npm run dev
```

---

## Troubleshooting

### Containers starten niet

```bash
# Check of ports al in gebruik zijn
# Windows PowerShell:
netstat -ano | findstr :80
netstat -ano | findstr :3306

# Stop andere services die de ports gebruiken
# Of wijzig de ports in docker-compose.yml

# Containers stoppen en volumes verwijderen
docker-compose down -v

# Opnieuw bouwen en starten
docker-compose build --no-cache
docker-compose up -d
```

### Permission Errors (Linux/macOS)

```bash
# Fix file permissions
sudo chown -R $USER:$USER backend/
sudo chmod -R 755 backend/storage
sudo chmod -R 755 backend/bootstrap/cache

# In container:
docker-compose exec app chown -R www-data:www-data /var/www/html/storage
docker-compose exec app chown -R www-data:www-data /var/www/html/bootstrap/cache
```

### Database Connection Error

```bash
# Check of MySQL container draait
docker-compose ps

# Check MySQL logs
docker-compose logs mysql

# Test connection
docker-compose exec app php artisan tinker
>>> DB::connection()->getPdo();

# Reset database
make fresh
```

### Clear All Caches

```bash
make cache-clear

# Of handmatig:
docker-compose exec app php artisan cache:clear
docker-compose exec app php artisan config:clear
docker-compose exec app php artisan route:clear
docker-compose exec app php artisan view:clear
```

### Composer Memory Issues

```bash
# Increase memory limit
docker-compose exec app php -d memory_limit=512M /usr/bin/composer install
```

### Node Module Issues

```bash
# Remove node_modules en reinstall
docker-compose exec node rm -rf node_modules package-lock.json
docker-compose exec node npm install
```

---

## Useful Docker Commands

### Container Management

```bash
# List running containers
docker-compose ps

# Stop all containers
docker-compose down

# Stop and remove volumes
docker-compose down -v

# Restart specific container
docker-compose restart nginx

# View logs
docker-compose logs -f app

# Access container shell
docker-compose exec app sh
docker-compose exec mysql bash
```

### Images Management

```bash
# List images
docker images

# Remove unused images
docker image prune

# Rebuild specific service
docker-compose build app

# Build without cache
docker-compose build --no-cache
```

### Volume Management

```bash
# List volumes
docker volume ls

# Remove unused volumes
docker volume prune

# Inspect volume
docker volume inspect documentkluis_mysql_data
```

### Network Management

```bash
# List networks
docker network ls

# Inspect network
docker network inspect documentkluis_documentkluis_network
```

---

## Production Considerations

### Voor productie deployment:

1. **Update docker-compose.yml:**
   - Verwijder MailHog service
   - Gebruik environment-specifieke .env files
   - Voeg SSL certificaten toe

2. **Security:**
   ```bash
   # Wijzig database credentials
   MYSQL_ROOT_PASSWORD=sterke_random_wachtwoord
   MYSQL_PASSWORD=ander_sterk_wachtwoord
   
   # Zet APP_DEBUG=false
   APP_DEBUG=false
   APP_ENV=production
   ```

3. **Optimize Laravel:**
   ```bash
   docker-compose exec app php artisan optimize
   docker-compose exec app php artisan config:cache
   docker-compose exec app php artisan route:cache
   docker-compose exec app php artisan view:cache
   ```

4. **Build Frontend:**
   ```bash
   docker-compose exec node npm run build
   ```

5. **Setup SSL (Let's Encrypt):**
   ```bash
   # Voeg certbot service toe aan docker-compose.yml
   # Of gebruik reverse proxy zoals Traefik
   ```

---

## Monitoring & Debugging

### View Real-time Logs

```bash
# All services
docker-compose logs -f

# Specific service
docker-compose logs -f app
docker-compose logs -f nginx
docker-compose logs -f mysql
```

### Check Resource Usage

```bash
docker stats
```

### Access MySQL

```bash
# Via container
docker-compose exec mysql mysql -u laravel -psecret documentkluis

# Via host (if port mapped)
mysql -h 127.0.0.1 -P 3306 -u laravel -psecret documentkluis
```

### Access Redis CLI

```bash
docker-compose exec redis redis-cli

# Test connection
> PING
> SET test "Hello"
> GET test
```

---

## Helpful Resources

- [Docker Documentation](https://docs.docker.com/)
- [Docker Compose Documentation](https://docs.docker.com/compose/)
- [Laravel Documentation](https://laravel.com/docs)
- [PHP Docker Images](https://hub.docker.com/_/php)
- [Nginx Configuration](https://nginx.org/en/docs/)
- [MySQL Docker](https://hub.docker.com/_/mysql)

---

**Happy Coding! 🚀**
