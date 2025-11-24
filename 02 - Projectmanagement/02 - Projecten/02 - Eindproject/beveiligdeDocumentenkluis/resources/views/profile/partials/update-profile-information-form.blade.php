<section>
    <header>
        <h2 class="text-lg font-semibold text-gray-900 mb-2">
            Profiel Informatie
        </h2>

        <p class="text-sm text-gray-600 mb-4">
            Update de profielinformatie en het e-mailadres van je account.
        </p>
    </header>

    <form id="send-verification" method="post" action="{{ route('verification.send') }}">
        @csrf
    </form>

    <form method="post" action="{{ route('profile.update') }}" class="space-y-4">
        @csrf
        @method('patch')

        <div class="form-group">
            <label for="name" class="form-label">Naam</label>
            <input id="name" name="name" type="text" class="form-input" value="{{ old('name', $user->name) }}" required autofocus autocomplete="name">
            @error('name')
                <div class="error-message">{{ $message }}</div>
            @enderror
        </div>

        <div class="form-group">
            <label for="email" class="form-label">E-mailadres</label>
            <input id="email" name="email" type="email" class="form-input" value="{{ old('email', $user->email) }}" required autocomplete="username">
            @error('email')
                <div class="error-message">{{ $message }}</div>
            @enderror

        </div>

        <div class="flex items-center gap-4 mt-4">
            <button type="submit" class="btn-primary">Opslaan</button>

            @if (session('status') === 'profile-updated')
                <p class="text-sm text-green-600">Opgeslagen.</p>
            @endif
        </div>
    </form>
</section>
