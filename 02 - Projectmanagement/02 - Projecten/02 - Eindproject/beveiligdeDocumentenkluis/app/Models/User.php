<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;

// Gebruiker model - voor authenticatie en autorisatie
class User extends Authenticatable
{
    use HasFactory, Notifiable;

    // Velden die massaal toegewezen kunnen worden
    protected $fillable = [
        'name',
        'email',
        'password',
    ];

    // Velden die verborgen moeten blijven in JSON responses
    protected $hidden = [
        'password',
        'remember_token',
    ];

    // Type conversies voor velden
    protected function casts(): array
    {
        return [
            'email_verified_at' => 'datetime',
            'password' => 'hashed',
        ];
    }

    // Relatie: gebruiker heeft meerdere mappen
    public function mappen(): HasMany
    {
        return $this->hasMany(Map::class, 'gebruiker_id');
    }

    // Relatie: gebruiker heeft meerdere bestanden
    public function bestanden(): HasMany
    {
        return $this->hasMany(Bestand::class, 'gebruiker_id');
    }
}
