const Vue = require('vue');
const server = require('express')();

const template = require('fs').readFileSync('./index.template.html', 'utf-8');
console.log(template);
const renderer = require('vue-server-renderer').createRenderer({
  template,
});

const context = {
    title: 'vue ssr',
    meta: `
		<meta charset='utf-8'>
        <meta name="keyword" content="vue,ssr">
        <meta name="description" content="vue srr demo">
    `,
};

server.get('*', (req, res) => {
	console.log('1111');
  const app = new Vue({
    data: {
      url: req.url
    },
    template: `<div>访问的 URL 是： {{ url }}</div>`,
  });

  renderer.renderToString(app, context, (err, html) => {
    console.log(html);
    if (err) {
		console.log(err);
      res.status(500).end('Internal Server Error')
      return;
    }
    res.end(html);
  });
})
console.log("start..");
server.listen(8080);
