{{-- Mappen Index View - Hoofdpagina documentenkluis --}}
{{-- Toont: mappen, bestanden, upload formulieren --}}

<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-gray-800 leading-tight">
            Documentenkluis
        </h2>
    </x-slot>

    <div class="py-12">
        <div class="max-w-7xl mx-auto sm:px-6 lg:px-8">
            <div class="bg-white overflow-hidden shadow-sm sm:rounded-lg">
                <div class="p-6 text-gray-900">
                    {{-- Breadcrumb navigatie --}}
                    <div class="mb-4">
                        <a href="{{ route('mappen.index') }}" class="text-gray-600 hover:underline hover:text-gray-900">Home</a>
                        @if($huidigemap)
                            / {{ $huidigemap->naam }}
                        @endif
                    </div>

                    {{-- Success melding --}}
                    @if(session('success'))
                        <div class="mb-4 p-4 bg-green-100 text-green-700 rounded">
                            {{ session('success') }}
                        </div>
                    @endif

                    {{-- Formulier: Nieuwe map aanmaken --}}
                    <div class="mb-6 p-4 bg-gray-50 rounded">
                        <h3 class="font-semibold mb-2">Nieuwe Map Aanmaken</h3>
                        <form method="POST" action="{{ route('mappen.store') }}" class="form-row">
                            @csrf
                            <input type="hidden" name="bovenliggende_map_id" value="{{ $huidigemap?->id }}">
                            <input type="text" name="naam" placeholder="Mapnaam" required class="form-input-field">
                            <button type="submit" class="form-button">
                                Aanmaken
                            </button>
                        </form>
                    </div>

                    <!-- Bestand uploaden (US012) -->
                    <div class="mb-6 p-4 bg-gray-50 rounded">
                        <h3 class="font-semibold mb-2">Bestand Uploaden</h3>
                        <p class="text-sm text-gray-600 mb-2">Maximale bestandsgrootte: 100 MB</p>
                        <form method="POST" action="{{ route('bestanden.store') }}" enctype="multipart/form-data" class="form-row" id="upload-form">
                            @csrf
                            <input type="hidden" name="map_id" value="{{ $huidigemap?->id }}">
                            <label class="form-file-label">
                                <span class="file-upload-text" id="file-name">Kies bestand...</span>
                                <input type="file" name="bestand" required class="hidden" id="file-input" onchange="document.getElementById('file-name').textContent = this.files[0]?.name || 'Kies bestand...'">
                            </label>
                            <button type="submit" class="form-button" id="upload-btn">
                                Uploaden
                            </button>
                        </form>
                        <div id="upload-progress" class="hidden mt-3">
                            <div class="w-full bg-gray-200 rounded-full h-2.5">
                                <div class="bg-blue-600 h-2.5 rounded-full transition-all duration-300" style="width: 0%" id="progress-bar"></div>
                            </div>
                            <p class="text-sm text-gray-600 mt-2" id="upload-status">Bestand wordt versleuteld en geüpload...</p>
                        </div>
                    </div>

                    <!-- Leeg bestand aanmaken (US010) -->
                    <div class="mb-6 p-4 bg-gray-50 rounded">
                        <h3 class="font-semibold mb-2">Leeg Bestand Aanmaken</h3>
                        <form method="POST" action="{{ route('bestanden.leeg') }}" class="form-row">
                            @csrf
                            <input type="hidden" name="map_id" value="{{ $huidigemap?->id }}">
                            <input type="text" name="naam" placeholder="Bestandsnaam" required class="form-input-field">
                            <button type="submit" class="form-button">
                                Aanmaken
                            </button>
                        </form>
                    </div>

                    <!-- Mappen weergeven -->
                    <div class="mb-6">
                        <h3 class="font-semibold text-lg mb-3">Mappen</h3>
                        @if($mappen->isEmpty())
                            <p class="text-gray-500">Geen mappen gevonden</p>
                        @else
                            <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
                                @foreach($mappen as $map)
                                    <div class="p-4 border rounded hover:bg-gray-50">
                                        <div class="flex items-center justify-between">
                                            <a href="{{ route('mappen.index', ['map_id' => $map->id]) }}" 
                                               class="flex items-center gap-2 flex-1">
                                                <svg class="w-6 h-6 text-yellow-500" fill="currentColor" viewBox="0 0 20 20">
                                                    <path d="M2 6a2 2 0 012-2h5l2 2h5a2 2 0 012 2v6a2 2 0 01-2 2H4a2 2 0 01-2-2V6z"/>
                                                </svg>
                                                <span class="font-medium">{{ $map->naam }}</span>
                                            </a>
                                            <!-- Verwijder map (US009) -->
                                            <form method="POST" action="{{ route('mappen.destroy', $map) }}" 
                                                  onsubmit="return confirm('Weet je zeker dat je deze map wilt verwijderen?')">
                                                @csrf
                                                @method('DELETE')
                                                <button type="submit" class="delete-map-btn">
                                                    <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"/>
                                                    </svg>
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                @endforeach
                            </div>
                        @endif
                    </div>

                    <!-- Bestanden weergeven -->
                    <div>
                        <h3 class="font-semibold text-lg mb-3">Bestanden</h3>
                        @if(!isset($bestanden) || $bestanden->isEmpty())
                            <p class="text-gray-500">Geen bestanden gevonden</p>
                        @else
                            <div class="space-y-2">
                                @foreach($bestanden as $bestand)
                                    <div class="p-4 border rounded hover:bg-gray-50 flex items-center justify-between">
                                        <div class="flex items-center gap-2">
                                            <svg class="w-6 h-6 text-gray-500" fill="currentColor" viewBox="0 0 20 20">
                                                <path fill-rule="evenodd" d="M4 4a2 2 0 012-2h4.586A2 2 0 0112 2.586L15.414 6A2 2 0 0116 7.414V16a2 2 0 01-2 2H6a2 2 0 01-2-2V4z" clip-rule="evenodd"/>
                                            </svg>
                                            <div>
                                                <span class="font-medium">{{ $bestand->naam }}</span>
                                                <span class="text-sm text-gray-500 ml-2">({{ number_format($bestand->grootte / 1024, 2) }} KB)</span>
                                            </div>
                                        </div>
                                        <div class="flex gap-2">
                                            <!-- Download (US013) -->
                                            <a href="{{ route('bestanden.download', $bestand) }}" 
                                               class="px-4 py-2 bg-gray-800 text-white rounded hover:bg-gray-900 text-sm font-medium">
                                                Download
                                            </a>
                                            <!-- Bekijken -->
                                            <a href="{{ route('bestanden.show', $bestand) }}" 
                                               class="px-4 py-2 bg-gray-600 text-white rounded hover:bg-gray-700 text-sm font-medium">
                                                Bekijken
                                            </a>
                                            <!-- Verwijder bestand (US011) -->
                                            <form method="POST" action="{{ route('bestanden.destroy', $bestand) }}" 
                                                  onsubmit="return confirm('Weet je zeker dat je dit bestand wilt verwijderen?')">
                                                @csrf
                                                @method('DELETE')
                                                <button type="submit" class="px-4 py-2 bg-red-600 text-white rounded hover:bg-red-700 text-sm font-medium">
                                                    Verwijder
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                @endforeach
                            </div>
                        @endif
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        document.getElementById('upload-form').addEventListener('submit', function(e) {
            const fileInput = document.getElementById('file-input');
            const file = fileInput.files[0];
            
            if (file && file.size > 10 * 1024 * 1024) { // Als bestand > 10MB
                // Toon progress indicator
                document.getElementById('upload-progress').classList.remove('hidden');
                document.getElementById('upload-btn').disabled = true;
                document.getElementById('upload-btn').textContent = 'Bezig met uploaden...';
                
                // Simuleer progress voor visuele feedback
                let progress = 0;
                const progressBar = document.getElementById('progress-bar');
                const interval = setInterval(() => {
                    progress += 1;
                    if (progress <= 95) {
                        progressBar.style.width = progress + '%';
                    }
                }, 500); // Update elke 500ms
                
                // Stop simulatie na 5 minuten (als het nog steeds bezig is)
                setTimeout(() => clearInterval(interval), 300000);
            }
        });
    </script>
</x-app-layout>
