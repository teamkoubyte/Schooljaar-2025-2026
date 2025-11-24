<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-gray-800 leading-tight">
            Bestand: {{ $bestand->naam }}
        </h2>
    </x-slot>

    <div class="py-12">
        <div class="max-w-7xl mx-auto sm:px-6 lg:px-8">
            <div class="bg-white overflow-hidden shadow-sm sm:rounded-lg">
                <div class="p-6 text-gray-900">
                    <div class="mb-4">
                        <a href="{{ route('mappen.index', ['map_id' => $bestand->map_id]) }}" 
                           class="text-blue-600 hover:underline">← Terug naar map</a>
                    </div>

                    <div class="mb-6">
                        <h3 class="font-semibold text-lg mb-2">Bestandsinformatie</h3>
                        <dl class="grid grid-cols-1 gap-2">
                            <div>
                                <dt class="font-medium text-gray-500">Naam:</dt>
                                <dd>{{ $bestand->originele_naam }}</dd>
                            </div>
                            <div>
                                <dt class="font-medium text-gray-500">Type:</dt>
                                <dd>{{ $bestand->mime_type }}</dd>
                            </div>
                            <div>
                                <dt class="font-medium text-gray-500">Grootte:</dt>
                                <dd>{{ number_format($bestand->grootte / 1024, 2) }} KB</dd>
                            </div>
                            <div>
                                <dt class="font-medium text-gray-500">Aangemaakt:</dt>
                                <dd>{{ $bestand->created_at->format('d-m-Y H:i') }}</dd>
                            </div>
                        </dl>
                    </div>

                    <div class="mb-4">
                        <h3 class="font-semibold text-lg mb-2">Inhoud (Ontsleuteld)</h3>
                        <div class="p-4 bg-gray-100 rounded border overflow-auto max-h-96">
                            <pre class="whitespace-pre-wrap">{{ $inhoud }}</pre>
                        </div>
                    </div>

                    <div class="flex gap-2">
                        <a href="{{ route('bestanden.download', $bestand) }}" 
                           class="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700">
                            Download
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</x-app-layout>
