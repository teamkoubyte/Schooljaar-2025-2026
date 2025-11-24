<?php

namespace App\Services;

use Illuminate\Support\Facades\Crypt;
use Illuminate\Support\Facades\Storage;
use Illuminate\Http\UploadedFile;

// Service voor AES-256 versleuteling van bestanden
class VersleutelingService
{
    // Upload, versleutel en sla bestand op
    public function versleutelEnSlaOp(UploadedFile $bestand, int $gebruikerId): array
    {
        // Lees originele bestand
        $inhoud = file_get_contents($bestand->getRealPath());
        
        // Versleutel met Laravel Crypt (AES-256-CBC)
        $versleuteld = Crypt::encryptString($inhoud);
        
        // Maak unieke bestandsnaam
        $naam = $gebruikerId . '_' . time() . '_' . uniqid() . '.enc';
        $pad = 'versleuteld/' . $naam;
        
        // Sla versleuteld op in storage
        Storage::disk('local')->put($pad, $versleuteld);
        
        // Return bestand info
        return [
            'pad' => $pad,
            'originele_naam' => $bestand->getClientOriginalName(),
            'mime_type' => $bestand->getMimeType(),
            'grootte' => $bestand->getSize(),
        ];
    }
    
    // Maak leeg versleuteld bestand
    public function versleutelLeegBestand(int $gebruikerId, string $naam): array
    {
        // Versleutel lege string
        $versleuteld = Crypt::encryptString('');
        
        // Maak unieke bestandsnaam
        $naam = $gebruikerId . '_' . time() . '_' . uniqid() . '.enc';
        $pad = 'versleuteld/' . $naam;
        
        // Sla op in storage
        Storage::disk('local')->put($pad, $versleuteld);
        
        return ['pad' => $pad];
    }
    
    // Ontsleutel bestand en return inhoud
    public function ontsleutel(string $pad): string
    {
        // Haal versleutelde data op
        $versleuteld = Storage::disk('local')->get($pad);
        
        // Ontsleutel en return
        return Crypt::decryptString($versleuteld);
    }
    
    // Verwijder versleuteld bestand van schijf
    public function verwijder(string $pad): bool
    {
        return Storage::disk('local')->delete($pad);
    }
}
