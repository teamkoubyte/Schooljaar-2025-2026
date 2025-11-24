<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Illuminate\Database\Eloquent\Relations\HasMany;

// Map model - voor mappen/directories
class Map extends Model
{
    // Database tabel naam
    protected $table = 'mappen';
    
    // Velden die massaal toegewezen kunnen worden
    protected $fillable = [
        'gebruiker_id',
        'bovenliggende_map_id',
        'naam',
    ];

    // Relatie: map behoort tot een gebruiker
    public function gebruiker(): BelongsTo
    {
        return $this->belongsTo(User::class, 'gebruiker_id');
    }

    // Relatie: map kan in een bovenliggende map zitten
    public function bovenliggendeMap(): BelongsTo
    {
        return $this->belongsTo(Map::class, 'bovenliggende_map_id');
    }

    // Relatie: map heeft meerdere submappen
    public function submappen(): HasMany
    {
        return $this->hasMany(Map::class, 'bovenliggende_map_id');
    }

    // Relatie: map heeft meerdere bestanden
    public function bestanden(): HasMany
    {
        return $this->hasMany(Bestand::class, 'map_id');
    }
}
