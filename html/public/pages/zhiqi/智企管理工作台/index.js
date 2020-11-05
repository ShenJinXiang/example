$(() => {
    initGrids();

    function initCanvas() {
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
        });
        pie({
            id: 'sbContent',
            title: '申报情况统计',
            data: [
                {name: '已申报', khs: 50, value: 235, color: 'hsla(20, 60%, 60%, 1)'},
                {name: '增值税', khs: 273, value: 273, color: 'hsla(80, 60%, 60%, 1)'},
                {name: '所得税', khs: 315, value: 315, color: 'hsla(120, 60%, 60%, 1)'},
                {name: '财务报表', khs: 215, value: 215, color: 'hsla(160, 60%, 60%, 1)'},
                {name: '其他', khs: 249, value: 249, color: 'hsla(220, 60%, 60%, 1)'},
            ],
            click: function (params) {
                console.log(params);
            }
        });
        pie({
            id: 'jkContent',
            title: '缴款情况统计',
            data: [
                {name: '已缴款', khs: 50, value: 235, color: 'hsla(20, 60%, 60%, 1)'},
                {name: '增值税', khs: 273, value: 273, color: 'hsla(80, 60%, 60%, 1)'},
                {name: '所得税', khs: 315, value: 315, color: 'hsla(120, 60%, 60%, 1)'},
                {name: '财务报表', khs: 215, value: 215, color: 'hsla(160, 60%, 60%, 1)'},
                {name: '其他', khs: 249, value: 249, color: 'hsla(220, 60%, 60%, 1)'},
            ],
            click: function (params) {
                console.log(params);
            }
        });
    }

    initCanvas();
    function initGrids() {
        let data = getData();
        initGrid("#tbTable", data);
        initGrid("#sbTable", data);
        initGrid("#jkTable", data);
    }

    function initGrid(el, data) {
        $(el).jqGrid({
            height:200,
            width: $(el).parent().width() * 0.99,
            datatype: 'local',
            colNames: ['客户名称', '社会信用代码'],
            colModel: [
                {name: 'KHMC', index: 'KHMC', width: 307, sortable: false},
                {name: 'SHXYDM', index: 'SHXYDM', width: 307, sortable: false},
            ],
            prmNames: {
                rows: "limit"
            },
            loadComplete: function () {
                data.forEach((item, index) => {
                    $(el).jqGrid('addRowData', (index + 1), item);
                });
            },
            jsonReader: {
                root: "message.list",
                page: "message.pageNumber",
                total: "message.totalPage",
                records: "message.totalRow"
            },
            mtype: "POST",
            rownumbers:true, //显示行顺序号
            viewrecords: false,
            sortable: false
        });
    }

    function getData() {
        var data = [
            {KHMC: "山西亿美天成文化传媒有限公司", SHXYDM: "91140100MAOJR3YW8Q"},
            {KHMC: "山西亿美天成文化传媒有限公司", SHXYDM: "91140100MAOJR3YW8Q"},
            {KHMC: "山西亿美天成文化传媒有限公司", SHXYDM: "91140100MAOJR3YW8Q"},
            {KHMC: "山西亿美天成文化传媒有限公司", SHXYDM: "91140100MAOJR3YW8Q"},
            {KHMC: "山西亿美天成文化传媒有限公司", SHXYDM: "91140100MAOJR3YW8Q"},
            {KHMC: "山西亿美天成文化传媒有限公司", SHXYDM: "91140100MAOJR3YW8Q"},
            {KHMC: "山西亿美天成文化传媒有限公司", SHXYDM: "91140100MAOJR3YW8Q"},
            {KHMC: "山西亿美天成文化传媒有限公司", SHXYDM: "91140100MAOJR3YW8Q"},
            {KHMC: "山西亿美天成文化传媒有限公司", SHXYDM: "91140100MAOJR3YW8Q"},
        ]
        return data;
    }
});