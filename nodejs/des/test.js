const des = require('./DesKit');

let str1 = "中华人民共和国";
console.log(str1);
let str2 = des.encrypt(str1);
console.log(str2);
let str3 = des.decrypt(str2);
console.log(str3);
