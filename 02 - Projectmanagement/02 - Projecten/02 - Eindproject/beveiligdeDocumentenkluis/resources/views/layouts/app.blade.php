<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="csrf-token" content="{{ csrf_token() }}">

        <title>Documentenkluis - {{ config('app.name', 'SS') }}</title>

        <!-- Favicon -->
        <link rel="icon" type="image/x-icon" href="{{ asset('favicon.ico') }}">

        <!-- Preload + Prefetch kritieke assets -->
        <link rel="preload" href="{{ asset('css/app.css') }}" as="style">
        <link rel="preload" href="{{ asset('css/navigation.css') }}" as="style">
        <link rel="preload" href="{{ asset('js/navigation.js') }}" as="script">
        <link rel="prefetch" href="{{ asset('css/dashboard.css') }}">
        
        <!-- Stylesheets -->
        <link rel="stylesheet" href="{{ asset('css/app.css') }}">
        <link rel="stylesheet" href="{{ asset('css/navigation.css') }}">
        <link rel="stylesheet" href="{{ asset('css/dashboard.css') }}">
        <link rel="stylesheet" href="{{ asset('css/welcome.css') }}">
        
        <!-- Scripts -->
        <script src="{{ asset('js/navigation.js') }}" defer></script>
    </head>
    <body>
        <div>
            @include('layouts.navigation')

            <!-- Page Heading -->
            @isset($header)
                <header>
                    <div class="container">
                        {{ $header }}
                    </div>
                </header>
            @endisset

            <!-- Page Content -->
            <main>
                {{ $slot }}
            </main>
        </div>
    </body>
</html>
