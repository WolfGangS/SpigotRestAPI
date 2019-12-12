<?php
    $path_only = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);
    $body = file_get_contents('php://input');
    echo $path_only . "\n" . $body;