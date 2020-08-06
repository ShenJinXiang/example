const path = require('path');
const express = require('express');

const app = express();

app.use(express.static(path.join(__dirname, 'public')));

app.listen(4004, function() {
    console.log('Server running at 4004!');
    console.log('http://localhost:4004');
});
