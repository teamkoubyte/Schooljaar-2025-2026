<x-app-layout>
    <x-slot name="header">
        <h2>
            Dashboard
        </h2>
    </x-slot>

    <div class="main-content">
        <div class="container">
            <div class="content-box">
                <h3>Welkom in je Beveiligde Documentenkluis!</h3>
                <p class="intro-text">Al je documenten worden automatisch versleuteld opgeslagen. Alleen jij kan ze bekijken.</p>
                
                <a href="{{ route('mappen.index') }}" class="btn btn-black btn-full">
                    Ga naar Mijn Documenten
                </a>

                <div class="features">
                    <div class="feature-card">
                        <h4>Versleuteling</h4>
                        <p>Al je bestanden worden automatisch versleuteld</p>
                    </div>
                    <div class="feature-card">
                        <h4>Mappen</h4>
                        <p>Organiseer je documenten in mappen</p>
                    </div>
                    <div class="feature-card">
                        <h4>Veilig Opslaan</h4>
                        <p>Upload en download je documenten veilig</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</x-app-layout>
