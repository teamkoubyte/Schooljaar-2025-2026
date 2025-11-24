<?php

namespace App\Http\Controllers;

use App\Models\Map;
use App\Models\Bestand;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class MapController extends Controller
{
    // Toon overzicht van mappen en bestanden
    public function index(Request $request)
    {
        // Haal huidige map ID op uit request
        $mapId = $request->get('map_id');
        
        // Haal mappen op van ingelogde gebruiker in huidige map
        $mappen = Map::where('gebruiker_id', Auth::id())
            ->where('bovenliggende_map_id', $mapId)
            ->orderBy('naam')
            ->get();
        
        // Haal huidige map op (als we in een submap zitten)
        $huidigemap = $mapId ? Map::find($mapId) : null;
        
        // Haal bestanden op in huidige map
        $bestanden = Bestand::where('gebruiker_id', Auth::id())
            ->where('map_id', $mapId)
            ->orderBy('naam')
            ->get();
        
        return view('mappen.index', compact('mappen', 'huidigemap', 'bestanden'));
    }

    // Maak nieuwe map aan
    public function store(Request $request)
    {
        // Valideer invoer
        $data = $request->validate([
            'naam' => 'required|string|max:255',
            'bovenliggende_map_id' => 'nullable|exists:mappen,id'
        ]);

        // Maak map aan voor ingelogde gebruiker
        Map::create([
            'gebruiker_id' => Auth::id(),
            'bovenliggende_map_id' => $data['bovenliggende_map_id'],
            'naam' => $data['naam'],
        ]);

        return redirect()->back()->with('success', 'Map aangemaakt');
    }

    // Toon specifieke map met inhoud
    public function show(Map $map)
    {
        // Check of gebruiker eigenaar is
        if ($map->gebruiker_id !== Auth::id()) {
            abort(403);
        }

        // Haal submappen en bestanden op
        $submappen = $map->submappen()->orderBy('naam')->get();
        $bestanden = $map->bestanden()->orderBy('naam')->get();
        
        return view('mappen.show', compact('map', 'submappen', 'bestanden'));
    }

    // Hernoem map
    public function update(Request $request, Map $map)
    {
        // Check of gebruiker eigenaar is
        if ($map->gebruiker_id !== Auth::id()) {
            abort(403);
        }

        // Valideer nieuwe naam
        $data = $request->validate([
            'naam' => 'required|string|max:255'
        ]);

        // Update map
        $map->update($data);

        return redirect()->back()->with('success', 'Map hernoemd');
    }

    // Verwijder map
    public function destroy(Map $map)
    {
        // Check of gebruiker eigenaar is
        if ($map->gebruiker_id !== Auth::id()) {
            abort(403);
        }

        // Verwijder map
        $map->delete();

        return redirect()->back()->with('success', 'Map verwijderd');
    }
}
