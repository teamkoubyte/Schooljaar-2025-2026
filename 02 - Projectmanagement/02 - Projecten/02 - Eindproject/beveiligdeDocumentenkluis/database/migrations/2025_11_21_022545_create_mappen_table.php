<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('mappen', function (Blueprint $table) {
            $table->id();
            $table->foreignId('gebruiker_id')->constrained('users')->onDelete('cascade');
            $table->foreignId('bovenliggende_map_id')->nullable()->constrained('mappen')->onDelete('cascade');
            $table->string('naam');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('mappen');
    }
};
