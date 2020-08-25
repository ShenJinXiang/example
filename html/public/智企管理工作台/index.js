$(() => {
    initGrids();

    function initCanvas() {
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
                debugger
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