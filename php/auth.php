<?php

if (count($argv) < 4) {
    echo "Exactly 3 arguments required: clientId apiKey apiSecret";
    exit(1);
}
echo generate_password($argv[1], $argv[2], $argv[3]);

function generate_password($client_id, $api_key, $api_secret)
{
    $plain_password = $client_id . "$" . round(microtime(true) * 1000) . "$" . $api_secret;
    $ciphertext_raw = openssl_encrypt($plain_password, "AES-128-ECB", $api_key, OPENSSL_RAW_DATA);
    return bin2hex($ciphertext_raw);
}

?>