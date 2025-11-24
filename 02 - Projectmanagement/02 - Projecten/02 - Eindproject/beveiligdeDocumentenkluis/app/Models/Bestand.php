<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

// Bestand model - voor versleutelde bestanden
class Bestand extends Model
{
    // Database tabel naam
    protected $table = 'bestanden';
    
    // Velden die massaal toegewezen kunnen worden
    protected $fillable = [
        'gebruiker_id',
        'map_id',
        'naam',
        'originele_naam',
        'versleuteld_pad',
        'mime_type',
        'grootte',
    ];

    // Relatie: bestand behoort tot een gebruiker
    public function gebruiker(): BelongsTo
    {
        return $this->belongsTo(User::class, 'gebruiker_id');
    }

    // Relatie: bestand behoort tot een map
    public function map(): BelongsTo
    {
        return $this->belongsTo(Map::class, 'map_id');
    }
}
