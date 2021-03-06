$(() => {
    initGrid();

    function initGrid() {
        let data =getData();
        $("#tableGrid").jqGrid({
            height: 200,
            width: $("#tableGrid").parent().width(),
            // width: 800,
            datatype: 'local',
            colNames: ['客户名称', '社会信用代码', '未填表', '未申报', '未缴款'],
            colModel: [
                {name: 'KHMC', index: 'KHMC', width: 200, sortable: false},
                {name: 'SHXYDM', index: 'SHXYDM', width: 200, sortable: false},
                {name: 'WTX', index: 'WTX', width: 100, sortable: false, align: 'center',
                    formatter: function (val, option, row){
                        let html = '<div class="zt-box">' ;
                        if (val > 0) {
                            html += '<span class="val-span red">' + val + '</span> ' +
                                '<i class="iconfont icon-List"></i>';
                        } else {
                            html += '<span class="val-span green">' + val + '</span> ';
                        }
                        html += '</div>';
                        return html;
                    }},
                {name: 'WSB', index: 'WSB', width: 100, sortable: false, align: 'center',
                    formatter: function (val, option, row){
                        let html = '<div class="zt-box">' ;
                        if (val > 0) {
                            html += '<span class="val-span red">' + val + '</span> ' +
                                '<i class="iconfont icon-List"></i>';
                        } else {
                            html += '<span class="val-span green">' + val + '</span> ';
                        }
                        html += '</div>';
                        return html;
                    }},
                {name: 'WJK', index: 'WJK', width: 100, sortable: false, align: 'center',
                    formatter: function (val, option, row){
                        let html = '<div class="zt-box">' ;
                        if (val > 0) {
                            html += '<span class="val-span red">' + val + '</span> ' +
                                '<i class="iconfont icon-List"></i>';
                        } else {
                            html += '<span class="val-span green">' + val + '</span> ';
                        }
                        html += '</div>';
                        return html;
                    }},
            ],
            prmNames: {
                rows: "limit"
            },
            loadComplete: function () {
                data.forEach((item, index) => {
                    $("#tableGrid").jqGrid('addRowData', (index + 1), item);
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
            {KHMC: "山西亿美天成文化传媒有限公司", SHXYDM: "91140100MAOJR3YW8Q", "YTX": 5, "WTX": 3, "YSB": 5, "WSB": 3, "YJK": 6, "WJK": 2},
            {KHMC: "山西亿美天成文化传媒有限公司", SHXYDM: "91140100MAOJR3YW8Q", "YTX": 5, "WTX": 0, "YSB": 5, "WSB": 3, "YJK": 6, "WJK": 2},
            {KHMC: "山西亿美天成文化传媒有限公司", SHXYDM: "91140100MAOJR3YW8Q", "YTX": 5, "WTX": 3, "YSB": 5, "WSB": 3, "YJK": 6, "WJK": 2},
            {KHMC: "山西亿美天成文化传媒有限公司", SHXYDM: "91140100MAOJR3YW8Q", "YTX": 5, "WTX": 0, "YSB": 5, "WSB": 0, "YJK": 6, "WJK": 2},
            {KHMC: "山西亿美天成文化传媒有限公司", SHXYDM: "91140100MAOJR3YW8Q", "YTX": 5, "WTX": 3, "YSB": 5, "WSB": 3, "YJK": 6, "WJK": 2},
            {KHMC: "山西亿美天成文化传媒有限公司", SHXYDM: "91140100MAOJR3YW8Q", "YTX": 5, "WTX": 3, "YSB": 5, "WSB": 3, "YJK": 6, "WJK": 2},
            {KHMC: "山西亿美天成文化传媒有限公司", SHXYDM: "91140100MAOJR3YW8Q", "YTX": 5, "WTX": 3, "YSB": 5, "WSB": 3, "YJK": 6, "WJK": 2},
            {KHMC: "山西亿美天成文化传媒有限公司", SHXYDM: "91140100MAOJR3YW8Q", "YTX": 5, "WTX": 3, "YSB": 5, "WSB": 3, "YJK": 6, "WJK": 2},
            {KHMC: "山西亿美天成文化传媒有限公司", SHXYDM: "91140100MAOJR3YW8Q", "YTX": 5, "WTX": 3, "YSB": 5, "WSB": 3, "YJK": 6, "WJK": 0},
        ]
        return data;
    }
});
