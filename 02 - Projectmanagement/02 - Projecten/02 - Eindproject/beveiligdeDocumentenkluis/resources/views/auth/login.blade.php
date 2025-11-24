<x-guest-layout>
    <!-- Session Status -->
    @if (session('status'))
        <div class="success-message">
            {{ session('status') }}
        </div>
    @endif

    <form method="POST" action="{{ route('login') }}">
        @csrf

        <!-- Email Address -->
        <div class="form-group">
            <label for="email" class="form-label">E-mailadres</label>
            <input id="email" class="form-input" type="email" name="email" value="{{ old('email') }}" required autofocus autocomplete="username">
            @error('email')
                <div class="error-message">{{ $message }}</div>
            @enderror
        </div>

        <!-- Password -->
        <div class="form-group">
            <label for="password" class="form-label">Wachtwoord</label>
            <input id="password" class="form-input" type="password" name="password" required autocomplete="current-password">
            @error('password')
                <div class="error-message">{{ $message }}</div>
            @enderror
        </div>

        <!-- Remember Me -->
        <div class="form-group">
            <label for="remember_me" class="checkbox-label">
                <input id="remember_me" type="checkbox" class="form-checkbox" name="remember">
                <span>Onthoud mij</span>
            </label>
        </div>

        <div class="form-actions">
            @if (Route::has('password.request'))
                <a class="link" href="{{ route('password.request') }}">
                    Wachtwoord vergeten?
                </a>
            @endif

            <button type="submit" class="btn-primary">
                Inloggen
            </button>
        </div>
    </form>
</x-guest-layout>
