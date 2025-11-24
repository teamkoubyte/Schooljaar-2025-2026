<nav class="nav">
    <div class="nav-container">
        <div class="nav-inner">
            <div class="nav-left">
                <!-- Logo -->
                <div class="nav-logo">
                    <a href="{{ route('dashboard') }}">
                        <img src="{{ asset('images/logo.png') }}" alt="SS Logo" style="height: 36px; width: auto;">
                    </a>
                </div>

                <!-- Navigation Links -->
                <div class="nav-links">
                    <a href="{{ route('dashboard') }}" class="nav-link {{ request()->routeIs('dashboard') ? 'active' : '' }}">
                        Dashboard
                    </a>
                    <a href="{{ route('mappen.index') }}" class="nav-link {{ request()->routeIs('mappen.*') ? 'active' : '' }}">
                        Mijn Documenten
                    </a>
                </div>
            </div>

            <!-- Settings Dropdown -->
            <div class="nav-right">
                <div class="dropdown" data-dropdown>
                    <button class="dropdown-toggle" data-dropdown-toggle>
                        <span>{{ Auth::user()->name }}</span>
                        <svg class="dropdown-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor">
                            <path fill-rule="evenodd" d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z" clip-rule="evenodd" />
                        </svg>
                    </button>
                    
                    <div class="dropdown-menu hidden" data-dropdown-menu>
                        <a href="{{ route('profile.edit') }}" class="dropdown-item">
                            Profiel
                        </a>
                        
                        <form method="POST" action="{{ route('logout') }}">
                            @csrf
                            <button type="submit" class="dropdown-item">
                                Uitloggen
                            </button>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Mobile Menu Button -->
            <div class="mobile-menu-button">
                <button class="hamburger" data-mobile-menu-toggle>
                    <svg class="hamburger-icon" stroke="currentColor" fill="none" viewBox="0 0 24 24">
                        <path class="hamburger-open" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16" />
                        <path class="hamburger-close hidden" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                    </svg>
                </button>
            </div>
        </div>
    </div>

    <!-- Mobile Menu -->
    <div class="mobile-menu hidden" data-mobile-menu>
        <div class="mobile-menu-links">
            <a href="{{ route('dashboard') }}" class="mobile-menu-link {{ request()->routeIs('dashboard') ? 'active' : '' }}">
                Dashboard
            </a>
            <a href="{{ route('mappen.index') }}" class="mobile-menu-link {{ request()->routeIs('mappen.*') ? 'active' : '' }}">
                Mijn Documenten
            </a>
        </div>

        <!-- Mobile User Info -->
        <div class="mobile-user-info">
            <div class="mobile-user-details">
                <div class="mobile-user-name">{{ Auth::user()->name }}</div>
                <div class="mobile-user-email">{{ Auth::user()->email }}</div>
            </div>

            <div class="mobile-user-actions">
                <a href="{{ route('profile.edit') }}" class="mobile-menu-link">
                    Profiel
                </a>
                
                <form method="POST" action="{{ route('logout') }}">
                    @csrf
                    <button type="submit" class="mobile-menu-link">
                        Uitloggen
                    </button>
                </form>
            </div>
        </div>
    </div>
</nav>
