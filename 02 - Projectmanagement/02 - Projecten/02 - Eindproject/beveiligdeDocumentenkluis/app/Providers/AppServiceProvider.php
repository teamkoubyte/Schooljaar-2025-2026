<?php

namespace App\Providers;

use Illuminate\Support\ServiceProvider;

class AppServiceProvider extends ServiceProvider
{
    /**
     * Register any application services.
     */
    public function register(): void
    {
        //
    }

    /**
     * Bootstrap any application services.
     */
    public function boot(): void
    {
        // Optimalisatie: Disable lazy loading violations in productie
        \Illuminate\Database\Eloquent\Model::preventLazyLoading(!app()->isProduction());
        
        // Optimalisatie: Strict mode uitschakelen voor snelheid
        \Illuminate\Database\Eloquent\Model::shouldBeStrict(false);
    }
}
