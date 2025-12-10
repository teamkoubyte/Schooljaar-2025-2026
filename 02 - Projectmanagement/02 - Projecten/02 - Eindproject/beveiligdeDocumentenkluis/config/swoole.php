<?php

return [
    // HTTP Server
    'http' => [
        'host' => '0.0.0.0',
        'port' => 8000,
        'options' => [
            'worker_num' => 4,
            'enable_coroutine' => true,
            'max_request' => 5000,
            'package_max_length' => 104857600, // 100MB in bytes
            'upload_tmp_dir' => '/tmp',
            'buffer_output_size' => 104857600, // 100MB
        ],
    ],
];
