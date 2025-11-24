<?php

use App\Http\Controllers\ProfileController;
use App\Http\Controllers\MapController;
use App\Http\Controllers\BestandController;
use Illuminate\Support\Facades\Route;
use Illuminate\Support\Facades\Auth;

// Homepagina - redirect naar mappen als ingelogd
Route::get('/', function () {
    if (Auth::check()) {
        return redirect()->route('mappen.index');
    }
    return view('welcome');
});

// Dashboard pagina (alleen voor ingelogde gebruikers)
Route::get('/dashboard', function () {
    return view('dashboard');
})->middleware(['auth', 'verified'])->name('dashboard');

// Alle routes die authenticatie vereisen
Route::middleware('auth')->group(function () {
    // Profiel routes
    Route::get('/profile', [ProfileController::class, 'edit'])->name('profile.edit');
    Route::patch('/profile', [ProfileController::class, 'update'])->name('profile.update');
    Route::delete('/profile', [ProfileController::class, 'destroy'])->name('profile.destroy');
    
    // Mappen routes (CRUD operaties)
    Route::resource('mappen', MapController::class)->parameters(['mappen' => 'map']);
    
    // Bestanden routes
    Route::post('/bestanden/leeg', [BestandController::class, 'createLeeg'])->name('bestanden.leeg');
    Route::get('/bestanden/{bestand}/download', [BestandController::class, 'download'])->name('bestanden.download');
    Route::resource('bestanden', BestandController::class)->parameters(['bestanden' => 'bestand']);
});

// Laad authenticatie routes (login, register, etc.)
require __DIR__.'/auth.php';
