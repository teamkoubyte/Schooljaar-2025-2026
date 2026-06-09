# Technische Specificaties - Documentenkluis

## 1. Technology Stack

### Full Stack (Laravel Monolith)
- **Framework:** Laravel 11.x
- **Language:** PHP 8.2+
- **Template Engine:** Blade Templates
- **Styling:** Custom CSS (geen frameworks)
- **CSS Preprocessor:** Laravel Mix / Vite (optioneel SCSS/SASS)
- **JavaScript:** Vanilla JavaScript + Alpine.js (optioneel voor interactiviteit)
- **Icons:** Font Awesome / Heroicons (CDN)
- **Authentication:** Laravel Breeze / Laravel Fortify
- **Validation:** Laravel Validation
- **File Upload:** Laravel Storage
- **Email:** Laravel Mail + Mailtrap (dev) / SMTP (prod)
- **Crypto:** Laravel Encryption (AES-256)
- **2FA:** Laravel Fortify Two-Factor Authentication
- **Password Hashing:** Laravel Hash (bcrypt)
- **Queue:** Laravel Queue (Redis)
- **Cache:** Redis
- **Asset Building:** Laravel Vite / Laravel Mix

### Database
- **Database:** MySQL 8.0
- **ORM:** Eloquent ORM
- **Migrations:** Laravel Migrations
- **Seeding:** Laravel Seeders

### Storage
- **File Storage:** Laravel Storage (local disk - encrypted)
- **Storage Path:** `/storage/app/users/{userId}/documents/`
- **Versioning:** Separate versioned folder per document
- **Public Assets:** `/storage/app/public` (symlinked)

### Containerization (Docker)
- **Docker:** Docker 24+
- **Docker Compose:** v2.20+
- **Containers:**
  - **app:** PHP 8.2-FPM + Laravel
  - **nginx:** Nginx 1.25 (web server)
  - **mysql:** MySQL 8.0
  - **redis:** Redis 7.2 (cache & queue)
  - **mailhog:** MailHog (email testing - dev only)

### DevOps & Tools
- **Version Control:** Git + GitHub
- **Package Manager:** Composer 2.x
- **Asset Building:** Vite (via Laravel Vite)
- **Linting:** Laravel Pint / PHP_CodeSniffer
- **Testing:** PHPUnit + Pest
- **Environment:** Docker Compose + .env files

---

## 2. Folder Structure

### Project Root
```
documentenkluis/
├── docker/
│   ├── nginx/
│   │   └── default.conf
│   ├── php/
│   │   ├── Dockerfile
│   │   └── php.ini
│   └── mysql/
│       └── my.cnf
├── laravel/                    # Laravel applicatie (alles in één)
├── docker-compose.yml
├── docker-compose.dev.yml
├── .env.example
├── Makefile                    # Helper commands
└── README.md
```

### Laravel Application (Full Stack)
```
laravel/
├── app/
│   ├── Console/
│   ├── Exceptions/
│   ├── Http/
│   │   ├── Controllers/
│   │   │   ├── Auth/
│   │   │   │   ├── RegisterController.php
│   │   │   │   ├── LoginController.php
│   │   │   │   ├── PasswordResetController.php
│   │   │   │   └── TwoFactorController.php
│   │   │   ├── DashboardController.php
│   │   │   ├── DocumentController.php
│   │   │   ├── FolderController.php
│   │   │   ├── ProfileController.php
│   │   │   ├── SearchController.php
│   │   │   └── Admin/
│   │   │       ├── UserController.php
│   │   │       └── LogController.php
│   │   ├── Middleware/
│   │   │   ├── Authenticate.php
│   │   │   ├── AdminMiddleware.php
│   │   │   ├── LogActivity.php
│   │   │   └── EnsureEmailIsVerified.php
│   │   └── Requests/
│   │       ├── Auth/
│   │       │   ├── RegisterRequest.php
│   │       │   └── LoginRequest.php
│   │       ├── DocumentUploadRequest.php
│   │       ├── FolderRequest.php
│   │       └── ProfileUpdateRequest.php
│   ├── Models/
│   │   ├── User.php
│   │   ├── Document.php
│   │   ├── Folder.php
│   │   ├── DocumentVersion.php
│   │   ├── ActivityLog.php
│   │   └── TrustedDevice.php
│   ├── Services/
│   │   ├── EncryptionService.php
│   │   ├── DocumentService.php
│   │   ├── VersionService.php
│   │   ├── StorageService.php
│   │   ├── SearchService.php
│   │   └── ActivityLogService.php
│   └── Providers/
│       ├── AppServiceProvider.php
│       ├── AuthServiceProvider.php
│       └── RouteServiceProvider.php
├── bootstrap/
├── config/
│   ├── app.php
│   ├── auth.php
│   ├── database.php
│   ├── filesystems.php
│   └── fortify.php
├── database/
│   ├── factories/
│   │   └── UserFactory.php
│   ├── migrations/
│   │   ├── 2025_10_01_create_users_table.php
│   │   ├── 2025_10_01_create_folders_table.php
│   │   ├── 2025_10_01_create_documents_table.php
│   │   ├── 2025_10_01_create_document_versions_table.php
│   │   ├── 2025_10_01_create_activity_logs_table.php
│   │   └── 2025_10_01_create_trusted_devices_table.php
│   └── seeders/
│       ├── DatabaseSeeder.php
│       └── AdminSeeder.php
├── public/
│   ├── index.php
│   ├── css/
│   │   ├── app.css           # Main stylesheet
│   │   ├── layout.css        # Layout styles
│   │   ├── components.css    # Reusable components
│   │   └── responsive.css    # Media queries
│   ├── js/
│   │   ├── app.js            # Main JavaScript
│   │   ├── upload.js         # File upload functionality
│   │   └── modal.js          # Modal functionality
│   └── images/
├── resources/
│   ├── css/
│   │   ├── app.css           # Source CSS (compiled to public/css)
│   │   ├── variables.css     # CSS custom properties
│   │   ├── layout.css
│   │   ├── components.css
│   │   ├── forms.css
│   │   └── responsive.css
│   ├── js/
│   │   └── app.js            # Source JS (compiled to public/js)
│   └── views/
│       ├── layouts/
│       │   ├── app.blade.php       # Main layout
│       │   ├── guest.blade.php     # Guest layout (login/register)
│       │   └── admin.blade.php     # Admin layout
│       ├── components/
│       │   ├── button.blade.php
│       │   ├── input.blade.php
│       │   ├── modal.blade.php
│       │   ├── alert.blade.php
│       │   └── breadcrumb.blade.php
│       ├── auth/
│       │   ├── login.blade.php
│       │   ├── register.blade.php
│       │   ├── forgot-password.blade.php
│       │   ├── reset-password.blade.php
│       │   └── two-factor-challenge.blade.php
│       ├── dashboard/
│       │   └── index.blade.php
│       ├── documents/
│       │   ├── index.blade.php
│       │   ├── show.blade.php
│       │   ├── upload.blade.php
│       │   └── partials/
│       │       ├── document-card.blade.php
│       │       └── version-history.blade.php
│       ├── folders/
│       │   ├── index.blade.php
│       │   └── partials/
│       │       ├── folder-tree.blade.php
│       │       └── folder-item.blade.php
│       ├── profile/
│       │   ├── edit.blade.php
│       │   ├── security.blade.php
│       │   ├── activity.blade.php
│       │   └── two-factor.blade.php
│       ├── admin/
│       │   ├── dashboard.blade.php
│       │   ├── users.blade.php
│       │   └── logs.blade.php
│       └── search/
│           └── results.blade.php
├── routes/
│   ├── web.php       # All routes (geen API routes nodig)
│   └── console.php
├── storage/
│   ├── app/
│   │   ├── public/
│   │   └── users/
│   ├── framework/
│   └── logs/
├── tests/
│   ├── Feature/
│   │   ├── Auth/
│   │   ├── Document/
│   │   └── Folder/
│   └── Unit/
│       ├── Services/
│       └── Models/
├── .env.example
├── artisan
├── composer.json
├── package.json      # Voor Vite + Tailwind
├── vite.config.js
├── tailwind.config.js
├── phpunit.xml
└── README.md
```

---

## 3. Database Schema (Detailed)

### Laravel Migrations

### users
```php
// database/migrations/2025_10_01_create_users_table.php
Schema::create('users', function (Blueprint $table) {
    $table->id();
    $table->string('email')->unique();
    $table->string('password');
    $table->string('full_name')->nullable();
    $table->boolean('is_admin')->default(false);
    $table->boolean('is_active')->default(true);
    $table->text('encryption_key'); // Encrypted user-specific key
    $table->timestamp('email_verified_at')->nullable();
    $table->rememberToken();
    $table->timestamps();
    $table->timestamp('last_login_at')->nullable();
    
    $table->index('email');
    $table->index('is_admin');
});

// Two Factor Authentication (Laravel Fortify)
Schema::create('two_factor_authentication', function (Blueprint $table) {
    $table->id();
    $table->foreignId('user_id')->constrained()->onDelete('cascade');
    $table->text('two_factor_secret')->nullable();
    $table->text('two_factor_recovery_codes')->nullable();
    $table->timestamp('two_factor_confirmed_at')->nullable();
    $table->timestamps();
});
```

### folders
```php
// database/migrations/2025_10_01_create_folders_table.php
Schema::create('folders', function (Blueprint $table) {
    $table->id();
    $table->foreignId('user_id')->constrained()->onDelete('cascade');
    $table->foreignId('parent_folder_id')->nullable()
          ->constrained('folders')->onDelete('cascade');
    $table->string('folder_name');
    $table->text('folder_path');
    $table->timestamps();
    
    $table->index('user_id');
    $table->index('parent_folder_id');
});
```

### documents
```php
// database/migrations/2025_10_01_create_documents_table.php
Schema::create('documents', function (Blueprint $table) {
    $table->id();
    $table->foreignId('folder_id')->nullable()
          ->constrained()->onDelete('set null');
    $table->foreignId('user_id')->constrained()->onDelete('cascade');
    $table->string('file_name');
    $table->bigInteger('file_size');
    $table->string('mime_type', 100)->nullable();
    $table->text('file_path_encrypted');
    $table->integer('current_version')->default(1);
    $table->boolean('is_encrypted')->default(true);
    $table->timestamps();
    $table->softDeletes(); // Voor soft delete functionaliteit
    
    $table->index('user_id');
    $table->index('folder_id');
    $table->index('file_name');
});
```

### document_versions
```php
// database/migrations/2025_10_01_create_document_versions_table.php
Schema::create('document_versions', function (Blueprint $table) {
    $table->id();
    $table->foreignId('document_id')->constrained()->onDelete('cascade');
    $table->integer('version_number');
    $table->text('file_path_encrypted');
    $table->bigInteger('file_size');
    $table->foreignId('created_by')->constrained('users');
    $table->timestamps();
    
    $table->index('document_id');
    $table->unique(['document_id', 'version_number']);
});
```

### activity_logs
```php
// database/migrations/2025_10_01_create_activity_logs_table.php
Schema::create('activity_logs', function (Blueprint $table) {
    $table->id();
    $table->foreignId('user_id')->nullable()
          ->constrained()->onDelete('set null');
    $table->enum('action_type', [
        'login', 'logout', 'register', 'upload', 'download',
        'delete', 'create_folder', 'delete_folder', 'update_profile',
        'change_password', 'enable_2fa', 'disable_2fa', 'view_document'
    ]);
    $table->string('resource_type', 50)->nullable();
    $table->unsignedBigInteger('resource_id')->nullable();
    $table->text('description')->nullable();
    $table->string('ip_address', 45)->nullable();
    $table->text('user_agent')->nullable();
    $table->timestamps();
    
    $table->index('user_id');
    $table->index('action_type');
    $table->index('created_at');
});
```

### password_resets
```php
// database/migrations/2025_10_01_create_password_resets_table.php
Schema::create('password_resets', function (Blueprint $table) {
    $table->id();
    $table->string('email')->index();
    $table->string('token');
    $table->timestamp('created_at')->nullable();
    $table->timestamp('expires_at');
    $table->timestamp('used_at')->nullable();
    
    $table->index('token');
    $table->index('expires_at');
});
```

### trusted_devices
```php
// database/migrations/2025_10_01_create_trusted_devices_table.php
Schema::create('trusted_devices', function (Blueprint $table) {
    $table->id();
    $table->foreignId('user_id')->constrained()->onDelete('cascade');
    $table->string('device_token')->unique();
    $table->string('device_name')->nullable();
    $table->string('ip_address', 45)->nullable();
    $table->text('user_agent')->nullable();
    $table->timestamp('last_used_at')->useCurrent();
    $table->timestamp('expires_at');
    $table->timestamps();
    
    $table->index('user_id');
    $table->index('device_token');
});
```

---

## 4. Routes (Laravel Web Routes)

### routes/web.php
```php
<?php

use App\Http\Controllers\Auth\RegisterController;
use App\Http\Controllers\Auth\LoginController;
use App\Http\Controllers\Auth\PasswordResetController;
use App\Http\Controllers\Auth\TwoFactorController;
use App\Http\Controllers\DashboardController;
use App\Http\Controllers\DocumentController;
use App\Http\Controllers\FolderController;
use App\Http\Controllers\ProfileController;
use App\Http\Controllers\SearchController;
use App\Http\Controllers\Admin\UserController as AdminUserController;
use App\Http\Controllers\Admin\LogController;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Guest Routes (Authentication)
|--------------------------------------------------------------------------
*/

Route::middleware('guest')->group(function () {
    // Registration
    Route::get('/register', [RegisterController::class, 'show'])->name('register');
    Route::post('/register', [RegisterController::class, 'store']);
    
    // Login
    Route::get('/login', [LoginController::class, 'show'])->name('login');
    Route::post('/login', [LoginController::class, 'store']);
    
    // Password Reset
    Route::get('/forgot-password', [PasswordResetController::class, 'request'])->name('password.request');
    Route::post('/forgot-password', [PasswordResetController::class, 'email'])->name('password.email');
    Route::get('/reset-password/{token}', [PasswordResetController::class, 'reset'])->name('password.reset');
    Route::post('/reset-password', [PasswordResetController::class, 'update'])->name('password.update');
});

/*
|--------------------------------------------------------------------------
| Authenticated Routes
|--------------------------------------------------------------------------
*/

Route::middleware(['auth'])->group(function () {
    // Logout
    Route::post('/logout', [LoginController::class, 'destroy'])->name('logout');
    
    // Dashboard
    Route::get('/dashboard', [DashboardController::class, 'index'])->name('dashboard');
    Route::redirect('/', '/dashboard');
    
    // Two Factor Authentication
    Route::get('/two-factor-challenge', [TwoFactorController::class, 'show'])->name('two-factor.login');
    Route::post('/two-factor-challenge', [TwoFactorController::class, 'store']);
    
    /*
    |--------------------------------------------------------------------------
    | Documents
    |--------------------------------------------------------------------------
    */
    Route::prefix('documents')->name('documents.')->group(function () {
        Route::get('/', [DocumentController::class, 'index'])->name('index');
        Route::get('/create', [DocumentController::class, 'create'])->name('create');
        Route::post('/', [DocumentController::class, 'store'])->name('store');
        Route::post('/upload', [DocumentController::class, 'upload'])->name('upload');
        Route::get('/{document}', [DocumentController::class, 'show'])->name('show');
        Route::get('/{document}/edit', [DocumentController::class, 'edit'])->name('edit');
        Route::put('/{document}', [DocumentController::class, 'update'])->name('update');
        Route::delete('/{document}', [DocumentController::class, 'destroy'])->name('destroy');
        Route::get('/{document}/download', [DocumentController::class, 'download'])->name('download');
        
        // Versions
        Route::get('/{document}/versions', [DocumentController::class, 'versions'])->name('versions');
        Route::get('/{document}/versions/{version}/download', [DocumentController::class, 'downloadVersion'])->name('versions.download');
        Route::post('/{document}/versions/{version}/restore', [DocumentController::class, 'restoreVersion'])->name('versions.restore');
    });
    
    /*
    |--------------------------------------------------------------------------
    | Folders
    |--------------------------------------------------------------------------
    */
    Route::prefix('folders')->name('folders.')->group(function () {
        Route::get('/', [FolderController::class, 'index'])->name('index');
        Route::post('/', [FolderController::class, 'store'])->name('store');
        Route::get('/{folder}', [FolderController::class, 'show'])->name('show');
        Route::put('/{folder}', [FolderController::class, 'update'])->name('update');
        Route::delete('/{folder}', [FolderController::class, 'destroy'])->name('destroy');
    });
    
    /*
    |--------------------------------------------------------------------------
    | Search
    |--------------------------------------------------------------------------
    */
    Route::get('/search', [SearchController::class, 'index'])->name('search');
    
    /*
    |--------------------------------------------------------------------------
    | Profile
    |--------------------------------------------------------------------------
    */
    Route::prefix('profile')->name('profile.')->group(function () {
        Route::get('/', [ProfileController::class, 'edit'])->name('edit');
        Route::patch('/', [ProfileController::class, 'update'])->name('update');
        Route::delete('/', [ProfileController::class, 'destroy'])->name('destroy');
        
        // Password
        Route::get('/password', [ProfileController::class, 'password'])->name('password');
        Route::put('/password', [ProfileController::class, 'updatePassword'])->name('password.update');
        
        // Security (2FA)
        Route::get('/security', [ProfileController::class, 'security'])->name('security');
        Route::post('/two-factor-authentication', [TwoFactorController::class, 'enable'])->name('two-factor.enable');
        Route::delete('/two-factor-authentication', [TwoFactorController::class, 'disable'])->name('two-factor.disable');
        Route::get('/two-factor-qr-code', [TwoFactorController::class, 'qrCode'])->name('two-factor.qr-code');
        Route::get('/two-factor-recovery-codes', [TwoFactorController::class, 'recoveryCodes'])->name('two-factor.recovery-codes');
        
        // Activity Log
        Route::get('/activity', [ProfileController::class, 'activity'])->name('activity');
    });
});

/*
|--------------------------------------------------------------------------
| Admin Routes
|--------------------------------------------------------------------------
*/

Route::middleware(['auth', 'admin'])->prefix('admin')->name('admin.')->group(function () {
    Route::get('/dashboard', [AdminUserController::class, 'dashboard'])->name('dashboard');
    
    // User Management
    Route::get('/users', [AdminUserController::class, 'index'])->name('users.index');
    Route::get('/users/{user}', [AdminUserController::class, 'show'])->name('users.show');
    Route::get('/users/{user}/edit', [AdminUserController::class, 'edit'])->name('users.edit');
    Route::put('/users/{user}', [AdminUserController::class, 'update'])->name('users.update');
    Route::delete('/users/{user}', [AdminUserController::class, 'destroy'])->name('users.destroy');
    
    // Activity Logs
    Route::get('/logs', [LogController::class, 'index'])->name('logs.index');
    Route::get('/logs/export', [LogController::class, 'export'])->name('logs.export');
    
    // Statistics
    Route::get('/stats', [AdminUserController::class, 'stats'])->name('stats');
});
```

---

## 5. Security Measures

### Password Security
- **Hashing:** bcrypt met salt rounds 12
- **Minimum eisen:** 8+ karakters, hoofdletter, cijfer, speciaal teken
- **Wachtwoord sterkte:** Zxcvbn library voor feedback
- **Password reset:** Token geldig 1 uur, eenmalig gebruik

### File Encryption
- **Algoritme:** AES-256-CBC
- **Key generatie:** Per gebruiker unieke key
- **Key storage:** Encrypted key in database
- **IV:** Unique initialization vector per bestand

### Authentication
- **JWT:** Access token (15 min) + Refresh token (7 dagen)
- **Token storage:** HTTP-only cookies
- **CSRF:** CSRF tokens bij state-changing requests
- **2FA:** TOTP met 6-cijferige codes, 30s interval

### Rate Limiting
```typescript
// Login attempts
const loginLimiter = rateLimit({
  windowMs: 15 * 60 * 1000, // 15 minuten
  max: 5, // 5 pogingen
  message: 'Te veel inlogpogingen, probeer later opnieuw'
});

// API calls
const apiLimiter = rateLimit({
  windowMs: 15 * 60 * 1000,
  max: 100 // 100 requests per 15 min
});
```

### Input Validation
- **Email:** RFC 5322 compliant
- **Filenames:** Sanitized, geen path traversal
- **File size:** Max 50MB per bestand
- **File types:** Whitelist van toegestane types
- **SQL Injection:** Parameterized queries via ORM

### Headers & CORS
```typescript
// Security headers
app.use(helmet({
  contentSecurityPolicy: true,
  hsts: true,
  noSniff: true,
  xssFilter: true
}));

// CORS
app.use(cors({
  origin: process.env.FRONTEND_URL,
  credentials: true,
  methods: ['GET', 'POST', 'PUT', 'DELETE'],
  allowedHeaders: ['Content-Type', 'Authorization']
}));
```

---

## 6. Encryption Implementation (Laravel)

### EncryptionService (app/Services/EncryptionService.php)

```php
<?php

namespace App\Services;

use Illuminate\Support\Facades\Crypt;
use Illuminate\Support\Str;

class EncryptionService
{
    /**
     * Generate a unique encryption key for a new user
     */
    public function generateUserKey(): string
    {
        return bin2hex(random_bytes(32)); // 256-bit key
    }

    /**
     * Encrypt user key with application master key
     */
    public function encryptUserKey(string $userKey): string
    {
        return Crypt::encryptString($userKey);
    }

    /**
     * Decrypt user key
     */
    public function decryptUserKey(string $encryptedKey): string
    {
        return Crypt::decryptString($encryptedKey);
    }

    /**
     * Encrypt a file using user's encryption key
     * 
     * @param string $sourcePath Path to original file
     * @param string $destinationPath Path where encrypted file will be saved
     * @param string $userKey User's encryption key (decrypted)
     * @return bool Success status
     */
    public function encryptFile(string $sourcePath, string $destinationPath, string $userKey): bool
    {
        if (!file_exists($sourcePath)) {
            throw new \Exception("Source file not found");
        }

        $algorithm = 'aes-256-cbc';
        $key = hex2bin($userKey);
        $iv = random_bytes(16); // Initialization vector

        // Read source file
        $data = file_get_contents($sourcePath);
        
        // Encrypt data
        $encrypted = openssl_encrypt($data, $algorithm, $key, OPENSSL_RAW_DATA, $iv);
        
        if ($encrypted === false) {
            throw new \Exception("Encryption failed");
        }

        // Write IV + encrypted data to destination
        // IV is prepended so we can read it during decryption
        $result = file_put_contents($destinationPath, $iv . $encrypted);
        
        return $result !== false;
    }

    /**
     * Decrypt a file using user's encryption key
     * 
     * @param string $encryptedPath Path to encrypted file
     * @param string $destinationPath Path where decrypted file will be saved
     * @param string $userKey User's encryption key (decrypted)
     * @return bool Success status
     */
    public function decryptFile(string $encryptedPath, string $destinationPath, string $userKey): bool
    {
        if (!file_exists($encryptedPath)) {
            throw new \Exception("Encrypted file not found");
        }

        $algorithm = 'aes-256-cbc';
        $key = hex2bin($userKey);

        // Read encrypted file
        $data = file_get_contents($encryptedPath);
        
        // Extract IV from beginning of file (first 16 bytes)
        $iv = substr($data, 0, 16);
        $encrypted = substr($data, 16);

        // Decrypt data
        $decrypted = openssl_decrypt($encrypted, $algorithm, $key, OPENSSL_RAW_DATA, $iv);
        
        if ($decrypted === false) {
            throw new \Exception("Decryption failed");
        }

        // Write decrypted data to destination
        $result = file_put_contents($destinationPath, $decrypted);
        
        return $result !== false;
    }

    /**
     * Decrypt file and stream it (for downloads)
     * 
     * @param string $encryptedPath Path to encrypted file
     * @param string $userKey User's encryption key (decrypted)
     * @return resource Stream resource
     */
    public function streamDecryptedFile(string $encryptedPath, string $userKey)
    {
        $tempPath = storage_path('app/temp/' . Str::random(40));
        
        $this->decryptFile($encryptedPath, $tempPath, $userKey);
        
        $stream = fopen($tempPath, 'r');
        
        // Schedule temp file deletion after response
        register_shutdown_function(function () use ($tempPath) {
            if (file_exists($tempPath)) {
                unlink($tempPath);
            }
        });
        
        return $stream;
    }
}
```

### Usage in DocumentController

```php
<?php

namespace App\Http\Controllers\Api;

use App\Models\Document;
use App\Services\EncryptionService;
use App\Services\StorageService;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Storage;

class DocumentController extends Controller
{
    protected $encryptionService;
    protected $storageService;

    public function __construct(
        EncryptionService $encryptionService,
        StorageService $storageService
    ) {
        $this->encryptionService = $encryptionService;
        $this->storageService = $storageService;
    }

    /**
     * Upload and encrypt document
     */
    public function upload(Request $request)
    {
        $request->validate([
            'file' => 'required|file|max:51200', // 50MB
            'folder_id' => 'nullable|exists:folders,id',
        ]);

        $user = $request->user();
        $file = $request->file('file');

        // Get user's decrypted encryption key
        $userKey = $this->encryptionService->decryptUserKey($user->encryption_key);

        // Generate unique filename
        $filename = $file->getClientOriginalName();
        $encryptedFilename = uniqid() . '.enc';
        
        // Temporary storage
        $tempPath = $file->store('temp');
        $tempFullPath = storage_path('app/' . $tempPath);
        
        // Encrypted storage path
        $encryptedPath = "users/{$user->id}/documents/{$encryptedFilename}";
        $encryptedFullPath = storage_path('app/' . $encryptedPath);
        
        // Ensure directory exists
        Storage::makeDirectory("users/{$user->id}/documents");
        
        // Encrypt file
        $this->encryptionService->encryptFile($tempFullPath, $encryptedFullPath, $userKey);
        
        // Delete temp file
        Storage::delete($tempPath);
        
        // Create database record
        $document = Document::create([
            'user_id' => $user->id,
            'folder_id' => $request->folder_id,
            'file_name' => $filename,
            'file_size' => $file->getSize(),
            'mime_type' => $file->getMimeType(),
            'file_path_encrypted' => $encryptedPath,
            'is_encrypted' => true,
            'current_version' => 1,
        ]);

        // Log activity
        activity()
            ->causedBy($user)
            ->performedOn($document)
            ->withProperties(['file_name' => $filename])
            ->log('upload');

        return response()->json([
            'message' => 'Document uploaded successfully',
            'document' => $document,
        ], 201);
    }

    /**
     * Download and decrypt document
     */
    public function download(Document $document)
    {
        $user = request()->user();
        
        // Authorization check
        if ($document->user_id !== $user->id) {
            abort(403, 'Unauthorized');
        }

        // Get user's decrypted encryption key
        $userKey = $this->encryptionService->decryptUserKey($user->encryption_key);
        
        // Get encrypted file path
        $encryptedPath = storage_path('app/' . $document->file_path_encrypted);
        
        // Stream decrypted file
        $stream = $this->encryptionService->streamDecryptedFile($encryptedPath, $userKey);
        
        // Log activity
        activity()
            ->causedBy($user)
            ->performedOn($document)
            ->log('download');
        
        return response()->stream(function () use ($stream) {
            fpassthru($stream);
            fclose($stream);
        }, 200, [
            'Content-Type' => $document->mime_type,
            'Content-Disposition' => 'attachment; filename="' . $document->file_name . '"',
        ]);
    }
}
```

---

## 7. File Upload Flow

```
1. Client selecteert bestand(en)
2. Frontend validatie (size, type)
3. POST /api/documents/upload met multipart/form-data
4. Backend validatie
5. Temporary opslag in /tmp
6. Virus scan (optioneel - ClamAV)
7. Encryptie van bestand
8. Verplaats naar /storage/users/{userId}/
9. Database entry aanmaken
10. Cleanup temporary files
11. Response naar client met document info
```

---

## 8. Environment Variables

### Laravel (.env)
```bash
# Application
APP_NAME="Documentenkluis"
APP_ENV=local
APP_KEY=base64:generated_by_php_artisan_key_generate
APP_DEBUG=true
APP_URL=http://localhost

# Database
DB_CONNECTION=mysql
DB_HOST=mysql
DB_PORT=3306
DB_DATABASE=documentenkluis
DB_USERNAME=laravel
DB_PASSWORD=secret

# Cache & Session
CACHE_DRIVER=redis
SESSION_DRIVER=redis
SESSION_LIFETIME=120
QUEUE_CONNECTION=redis

# Redis
REDIS_HOST=redis
REDIS_PASSWORD=null
REDIS_PORT=6379

# Mail
MAIL_MAILER=smtp
MAIL_HOST=mailhog
MAIL_PORT=1025
MAIL_USERNAME=null
MAIL_PASSWORD=null
MAIL_ENCRYPTION=null
MAIL_FROM_ADDRESS="noreply@documentkluis.local"
MAIL_FROM_NAME="${APP_NAME}"

# Storage
FILESYSTEM_DISK=local
MAX_FILE_SIZE=52428800
ALLOWED_FILE_TYPES=pdf,doc,docx,txt,jpg,png,xls,xlsx

# Security
BCRYPT_ROUNDS=12

# Laravel Fortify (2FA)
FORTIFY_TWO_FACTOR_APP_NAME="${APP_NAME}"
```

---

## 9. Testing Strategy

### Unit Tests
- Auth service (login, register, password hashing)
- Encryption/decryption functions
- Validation functions
- Helper utilities

### Integration Tests
- API endpoints
- Database operations
- File upload/download flow
- Authentication flow

### E2E Tests (Cypress)
- Complete user registration flow
- Login en logout
- Document upload en download
- Mappenstructuur navigatie
- 2FA setup
- Wachtwoord reset flow

### Security Tests
- SQL injection
- XSS attacks
- CSRF protection
- Rate limiting
- Encryption verification

---

## 10. Performance Optimization

### Database
- Indexing op frequently queried columns
- Connection pooling
- Query optimization
- Pagination voor grote resultsets

### File Handling
- Chunked upload voor grote bestanden
- Stream processing i.p.v. buffer
- Lazy loading van bestandslijsten
- Thumbnail generatie voor images

### Caching
- Redis voor session storage (optioneel)
- Browser caching voor static assets
- API response caching waar mogelijk

### Frontend
- Code splitting
- Lazy loading van routes
- Image optimization
- Debouncing voor search

---

## 11. Deployment

### Docker Setup

#### docker-compose.yml
```yaml
version: '3.8'

services:
  # Nginx Web Server
  nginx:
    image: nginx:1.25-alpine
    container_name: documentkluis_nginx
    ports:
      - "80:80"
      - "443:443"
    volumes:
      - ./backend:/var/www/html
      - ./docker/nginx/default.conf:/etc/nginx/conf.d/default.conf
      - ./frontend/dist:/var/www/frontend
    depends_on:
      - app
    networks:
      - documentkluis_network

  # PHP-FPM + Laravel
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

  # MySQL Database
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
    command: --default-authentication-plugin=mysql_native_password

  # Redis for Cache & Queue
  redis:
    image: redis:7.2-alpine
    container_name: documentkluis_redis
    ports:
      - "6379:6379"
    volumes:
      - redis_data:/data
    networks:
      - documentkluis_network

  # MailHog for Email Testing (Development only)
  mailhog:
    image: mailhog/mailhog:latest
    container_name: documentkluis_mailhog
    ports:
      - "1025:1025"
      - "8025:8025"
    networks:
      - documentkluis_network

  # Node for Frontend Build
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
    driver: local
  redis_data:
    driver: local
```

#### docker/php/Dockerfile
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

# Set working directory
WORKDIR /var/www/html

# Copy existing application directory permissions
COPY --chown=www-data:www-data . /var/www/html

# Change current user to www-data
USER www-data

# Expose port 9000 and start php-fpm server
EXPOSE 9000
CMD ["php-fpm"]
```

#### docker/nginx/default.conf
```nginx
server {
    listen 80;
    server_name localhost;
    root /var/www/html/public;
    
    index index.php index.html index.htm;

    # Laravel API routes
    location /api {
        try_files $uri $uri/ /index.php?$query_string;
    }

    # PHP-FPM configuration
    location ~ \.php$ {
        try_files $uri =404;
        fastcgi_split_path_info ^(.+\.php)(/.+)$;
        fastcgi_pass app:9000;
        fastcgi_index index.php;
        include fastcgi_params;
        fastcgi_param SCRIPT_FILENAME $document_root$fastcgi_script_name;
        fastcgi_param PATH_INFO $fastcgi_path_info;
    }

    # Frontend (React) - serve from dist folder
    location / {
        root /var/www/frontend;
        try_files $uri $uri/ /index.html;
    }

    # Static files
    location ~* \.(jpg|jpeg|png|gif|ico|css|js|svg|woff|woff2|ttf|eot)$ {
        expires 1y;
        add_header Cache-Control "public, immutable";
    }

    # Security headers
    add_header X-Frame-Options "SAMEORIGIN" always;
    add_header X-Content-Type-Options "nosniff" always;
    add_header X-XSS-Protection "1; mode=block" always;

    # Deny access to hidden files
    location ~ /\. {
        deny all;
    }

    # File upload size limit
    client_max_body_size 50M;
}
```

#### docker/php/php.ini
```ini
[PHP]
; Maximum execution time
max_execution_time = 300

; Maximum upload file size
upload_max_filesize = 50M
post_max_size = 50M

; Memory limit
memory_limit = 256M

; Error reporting (disable in production)
display_errors = On
error_reporting = E_ALL

; Timezone
date.timezone = Europe/Brussels
```

#### docker/mysql/my.cnf
```ini
[mysqld]
# Character set
character-set-server = utf8mb4
collation-server = utf8mb4_unicode_ci

# InnoDB settings
innodb_buffer_pool_size = 256M
innodb_log_file_size = 64M
innodb_flush_log_at_trx_commit = 2
innodb_flush_method = O_DIRECT

# Query cache (disabled in MySQL 8.0+)
# query_cache_type = 1
# query_cache_size = 32M

# Connection settings
max_connections = 100
```

#### Makefile (Helper Commands)
```makefile
.PHONY: help up down build restart logs shell composer npm migrate seed fresh test

help: ## Show this help
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-20s\033[0m %s\n", $$1, $$2}'

up: ## Start all containers
	docker-compose up -d

down: ## Stop all containers
	docker-compose down

build: ## Build all containers
	docker-compose build

restart: ## Restart all containers
	docker-compose restart

logs: ## Show logs
	docker-compose logs -f

shell: ## Access app container shell
	docker-compose exec app sh

composer-install: ## Install composer dependencies
	docker-compose exec app composer install

npm-install: ## Install npm dependencies
	docker-compose exec node npm install

migrate: ## Run database migrations
	docker-compose exec app php artisan migrate

seed: ## Seed database
	docker-compose exec app php artisan db:seed

fresh: ## Fresh migration with seed
	docker-compose exec app php artisan migrate:fresh --seed

test: ## Run tests
	docker-compose exec app php artisan test

key-generate: ## Generate application key
	docker-compose exec app php artisan key:generate

storage-link: ## Create storage link
	docker-compose exec app php artisan storage:link

optimize: ## Optimize application
	docker-compose exec app php artisan optimize

cache-clear: ## Clear all caches
	docker-compose exec app php artisan cache:clear
	docker-compose exec app php artisan config:clear
	docker-compose exec app php artisan route:clear
	docker-compose exec app php artisan view:clear

setup: build up composer-install key-generate migrate seed storage-link ## Initial project setup
```

### Development Workflow

```bash
# 1. Clone repository
git clone https://github.com/teamkoubyte/documentenkluis.git
cd documentenkluis

# 2. Copy environment files
cp backend/.env.example backend/.env
cp frontend/.env.example frontend/.env

# 3. Start Docker containers
make up
# or
docker-compose up -d

# 4. Install dependencies
make composer-install
make npm-install

# 5. Generate application key
make key-generate

# 6. Run migrations
make migrate

# 7. Seed database (optional - creates admin user)
make seed

# 8. Create storage symlink
make storage-link

# 9. Access application
# Frontend: http://localhost:3000
# Backend API: http://localhost/api
# MailHog: http://localhost:8025
```

### Production Deployment

Voor productie deployment:

1. **Update docker-compose.yml:**
   - Verwijder mailhog service
   - Configureer echte SMTP
   - Voeg SSL certificaten toe aan nginx

2. **Environment variabelen:**
   ```bash
   APP_ENV=production
   APP_DEBUG=false
   APP_URL=https://jouw-domein.com
   ```

3. **Optimize Laravel:**
   ```bash
   make optimize
   ```

4. **Build frontend:**
   ```bash
   cd frontend
   npm run build
   ```

### Environment Requirements
- Docker 24+ installed
- Docker Compose v2.20+
- Minimum 2GB RAM
- 10GB disk space (initieel)
- Port 80, 443, 3000, 3306, 6379 beschikbaar

---

## 12. Monitoring & Logging

### Application Logs
- Winston voor structured logging
- Log levels: error, warn, info, debug
- Rotate logs daily
- Keep logs 30 dagen

### Error Tracking
- Sentry voor error monitoring (optioneel)
- Email alerts voor kritieke errors

### Metrics
- Request/response times
- Database query performance
- Storage usage per gebruiker
- Active users
- Failed login attempts

---

Dit document dient als technische blauwdruk voor het ontwikkelen van de documentenkluis applicatie.
