<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Beveiligde Documentenkluis</title>
    
    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="{{ asset('favicon.ico') }}">
    
    <!-- CSS -->
    <link rel="stylesheet" href="{{ asset('css/welcome.css') }}">
</head>
<body>
    <!-- Navigatie -->
    <nav>
        <div class="nav-container">
            <div>
                <img src="{{ asset('images/logo.png') }}" alt="SS Logo" class="logo">
            </div>
            
            <div class="nav-links">
                @auth
                    <a href="{{ route('dashboard') }}">Dashboard</a>
                    <a href="{{ route('mappen.index') }}">Mijn Documenten</a>
                @else
                    <a href="{{ route('login') }}">Log in</a>
                    <a href="{{ route('register') }}" class="btn-register">Register</a>
                @endauth
            </div>
        </div>
    </nav>

    <!-- Hoofdcontent -->
    <div class="main-content">
        <div class="container">
            <div class="content-box">
                <h3>Welkom in je Beveiligde Documentenkluis!</h3>
                <p class="intro-text">Al je documenten worden automatisch versleuteld opgeslagen. Alleen jij kan ze bekijken.</p>
                
                @auth
                    <a href="{{ route('mappen.index') }}" class="btn btn-blue">
                        Ga naar Mijn Documenten
                    </a>
                @else
                    <a href="{{ route('login') }}" class="btn btn-black btn-full">
                        Log in om te starten
                    </a>
                @endauth

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
</body>
</html>
