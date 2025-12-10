#!/usr/bin/env bash

# Start Octane met FrankenPHP
php artisan octane:start \
    --server=frankenphp \
    --host=0.0.0.0 \
    --port=80 \
    --workers=4 \
    --max-requests=500 \
    --watch%