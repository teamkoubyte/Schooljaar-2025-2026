<?php

namespace App\Http\Controllers;

use App\Models\Bestand;
use App\Services\VersleutelingService;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class BestandController extends Controller
{
    private $versleuteling;

    // Constructor: injecteer versleuteling service
    public function __construct(VersleutelingService $versleuteling)
    {
        $this->versleuteling = $versleuteling;
    }

    // Maak leeg bestand aan
    public function createLeeg(Request $request)
    {
        // Valideer invoer
        $data = $request->validate([
            'naam' => 'required|string|max:255',
            'map_id' => 'nullable|exists:mappen,id'
        ]);

        // Versleutel leeg bestand
        $versleuteld = $this->versleuteling->versleutelLeegBestand(Auth::id(), $data['naam']);

        // Sla bestand op in database
        Bestand::create([
            'gebruiker_id' => Auth::id(),
            'map_id' => $data['map_id'],
            'naam' => $data['naam'],
            'originele_naam' => $data['naam'],
            'versleuteld_pad' => $versleuteld['pad'],
            'mime_type' => 'text/plain',
            'grootte' => 0,
        ]);

        return redirect()->back()->with('success', 'Leeg bestand aangemaakt');
    }

    // Upload en versleutel bestand
    public function store(Request $request)
    {
        // Valideer upload (max 10MB)
        $data = $request->validate([
            'bestand' => 'required|file|max:10240',
            'map_id' => 'nullable|exists:mappen,id'
        ]);

        // Versleutel geüpload bestand
        $file = $request->file('bestand');
        $versleuteld = $this->versleuteling->versleutelEnSlaOp($file, Auth::id());

        // Sla bestand op in database
        Bestand::create([
            'gebruiker_id' => Auth::id(),
            'map_id' => $data['map_id'],
            'naam' => $versleuteld['originele_naam'],
            'originele_naam' => $versleuteld['originele_naam'],
            'versleuteld_pad' => $versleuteld['pad'],
            'mime_type' => $versleuteld['mime_type'],
            'grootte' => $versleuteld['grootte'],
        ]);

        return redirect()->back()->with('success', 'Bestand geüpload');
    }

    // Download bestand (ontsleuteld)
    public function download(Bestand $bestand)
    {
        // Check of gebruiker eigenaar is
        if ($bestand->gebruiker_id !== Auth::id()) {
            abort(403);
        }

        // Ontsleutel bestand
        $inhoud = $this->versleuteling->ontsleutel($bestand->versleuteld_pad);

        // Stuur bestand naar browser
        return response($inhoud)
            ->header('Content-Type', $bestand->mime_type)
            ->header('Content-Disposition', 'attachment; filename="' . $bestand->originele_naam . '"');
    }

    // Bekijk bestand in browser
    public function show(Bestand $bestand)
    {
        // Check of gebruiker eigenaar is
        if ($bestand->gebruiker_id !== Auth::id()) {
            abort(403);
        }

        // Ontsleutel bestand
        $inhoud = $this->versleuteling->ontsleutel($bestand->versleuteld_pad);

        return view('bestanden.show', compact('bestand', 'inhoud'));
    }

    // Verwijder bestand
    public function destroy(Bestand $bestand)
    {
        // Check of gebruiker eigenaar is
        if ($bestand->gebruiker_id !== Auth::id()) {
            abort(403);
        }

        // Verwijder versleuteld bestand van schijf
        $this->versleuteling->verwijder($bestand->versleuteld_pad);
        
        // Verwijder uit database
        $bestand->delete();

        return redirect()->back()->with('success', 'Bestand verwijderd');
    }
}
