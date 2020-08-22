const path = require('path'),
    express = require('express'),
    app = express();

app.use(express.static(path.join(__dirname, 'public')));

app.listen(4008, () => {
    console.log('Server running at 4008!')
    console.log('http://localhost:4008')
});

