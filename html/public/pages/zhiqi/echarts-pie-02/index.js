{
    pie({
        id: 'tbContent',
        title: '填表情况统计',
        data: [
            {name: '已填写', khs: 50, value: 235, color: 'hsla(20, 60%, 60%, 1)'},
            {name: '增值税', khs: 273, value: 273, color: 'hsla(80, 60%, 60%, 1)'},
            {name: '所得税', khs: 315, value: 315, color: 'hsla(120, 60%, 60%, 1)'},
            {name: '财务报表', khs: 215, value: 215, color: 'hsla(160, 60%, 60%, 1)'},
            {name: '其他', khs: 249, value: 249, color: 'hsla(220, 60%, 60%, 1)'},
        ],
        click: function (params) {
            console.log(params);
        }
    })
}