<section>
    <header>
        <h2 class="text-lg font-semibold text-gray-900 mb-2">
            Account Verwijderen
        </h2>

        <p class="text-sm text-gray-600 mb-4">
            Zodra je account is verwijderd, worden al zijn gegevens permanent gewist. Download voordat je je account verwijdert alle gegevens die je wilt bewaren.
        </p>
    </header>

    <button type="button" class="btn-red" onclick="if(confirm('Weet je zeker dat je je account wilt verwijderen? Deze actie kan niet ongedaan gemaakt worden.')) { document.getElementById('delete-account-form').submit(); }">
        Account Verwijderen
    </button>
    
    <form id="delete-account-form" method="post" action="{{ route('profile.destroy') }}" style="display: none;">
        @csrf
        @method('delete')
    </form>
</section>
