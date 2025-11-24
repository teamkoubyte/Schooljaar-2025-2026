<section>
    <header>
        <h2 class="text-lg font-semibold text-gray-900 mb-2">
            Wachtwoord Wijzigen
        </h2>

        <p class="text-sm text-gray-600 mb-4">
            Zorg ervoor dat je account een lang, willekeurig wachtwoord gebruikt om veilig te blijven.
        </p>
    </header>

    <form method="post" action="{{ route('password.update') }}" class="space-y-4">
        @csrf
        @method('put')

        <div class="form-group">
            <label for="update_password_current_password" class="form-label">Huidig Wachtwoord</label>
            <input id="update_password_current_password" name="current_password" type="password" class="form-input" autocomplete="current-password">
            @error('current_password', 'updatePassword')
                <div class="error-message">{{ $message }}</div>
            @enderror
        </div>

        <div class="form-group">
            <label for="update_password_password" class="form-label">Nieuw Wachtwoord</label>
            <input id="update_password_password" name="password" type="password" class="form-input" autocomplete="new-password">
            @error('password', 'updatePassword')
                <div class="error-message">{{ $message }}</div>
            @enderror
        </div>

        <div class="form-group">
            <label for="update_password_password_confirmation" class="form-label">Bevestig Wachtwoord</label>
            <input id="update_password_password_confirmation" name="password_confirmation" type="password" class="form-input" autocomplete="new-password">
            @error('password_confirmation', 'updatePassword')
                <div class="error-message">{{ $message }}</div>
            @enderror
        </div>

        <div class="flex items-center gap-4 mt-4">
            <button type="submit" class="btn-primary">Opslaan</button>

            @if (session('status') === 'password-updated')
                <p class="text-sm text-green-600">Opgeslagen.</p>
            @endif
        </div>
    </form>
</section>
