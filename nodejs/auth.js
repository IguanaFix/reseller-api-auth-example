const crypto = require('crypto');
const algorithm = 'aes-128-ecb';

const args = process.argv.slice(2);

if (args.length !== 3) {
    console.log('Exactly 3 arguments required: clientId apiKey apiSecret');
    process.exit(1);
}

console.log(generatePassword(args[0], args[1], args[2]));

function generatePassword(clientId, apiKey, apiSecret) {
    let cipher = crypto.createCipheriv(algorithm, apiKey, new Buffer(''));
    let result = cipher.update(new Buffer(clientId + '$' + new Date().getTime() + '$' + apiSecret, 'utf8'),'buffer', 'hex');
    result += cipher.final('hex');
    return result;
}