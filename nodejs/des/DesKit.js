const crypto = require('crypto');

// let key = 'abcdefghijklmcopqrstuvwx';
let key = '[#^$c!s`';

let encrypt = function(str) {
    let _key = new Buffer(key);
    let cipher = crypto.createCipheriv('des-ecb', _key, new Buffer(0));
    cipher.setAutoPadding(true);
    let ciph = cipher.update(str, 'utf8', 'base64');
    ciph += cipher.final('base64');
    return ciph;
};

let decrypt = function(str) {
    let _key = new Buffer(key);
    let decipher = crypto.createDecipheriv('des-ecb', _key, new Buffer(0));
    decipher.setAutoPadding(true);
    let txt = decipher.update(str, 'base64', 'utf8');
    txt += decipher.final('utf8');
    return txt;
};

exports.encrypt = encrypt;
exports.decrypt = decrypt;