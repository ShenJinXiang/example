{
    const option = {
        series : [
            {
                name: '访问来源',
                type: 'pie',
                radius: '55%',
                selectedMode: 'single',
                label: {
                    show: true,
                    position: 'outer',
                    color: '#444'
                },
                labelLine: {
                    lineStyle: {
                        color: '#999'
                    }
                },
                emphasis: {
                    itemStyle: {
                        shadowBlur: 50,
                        shadowColor: 'rgba(40, 40, 40, 0.5)',
                        // shadowOffsetX: 10,
                        // shadowOffsetY: 10,
                    }
                },
                data:[
                    {value:235, name:'视频广告',
                        itemStyle: {
                            color: 'red'
                        }
                    },
                    {
                        value:274,
                        name:'联盟广告',
                        itemStyle: { color: '#f1f1f1', borderColor: 'red' },
                        emphasis: {
                            itemStyle: {
                                color: '#039',
                                borderColor: '#039' ,
                                opacity: 1
                            }
                        }
                    },
                    {value:310, name:'邮件营销',
                        itemStyle: { color: '#f1f1f1', borderColor: 'red' },
                        emphasis: { itemStyle: { color: '#f84', borderColor: '#f84' } }
                    },
                    {value:335, name:'直接访问',
                        itemStyle: { color: '#f1f1f1', borderColor: 'red' },
                        emphasis: { itemStyle: { color: '#f30', borderColor: '#f30' } }
                    },
                    {value:400, name:'搜索引擎',
                        itemStyle: { color: '#f1f1f1', borderColor: 'red' },
                        emphasis: { itemStyle: { color: '#a4f', borderColor: '#a4f' } }
                    },
                    {value:10, name:'极速访达',
                        itemStyle: { color: '#f1f1f1', borderColor: 'red' },
                        emphasis: { itemStyle: { color: '#04f', borderColor: '#04f' } }
                    },
                ]
            }
        ]
    },
        myChart = echarts.init(document.getElementById('main'));
    myChart.setOption(option);

    myChart.on('click', function (params) {
        // console.log(params);
        // params.color = 'green';
        // params.borderColor = 'green';
        // myChart.setOption(params)
        option.series[params.seriesIndex].data[params.dataIndex].itemStyle.color =
            option.series[params.seriesIndex].data[params.dataIndex].emphasis.itemStyle.color;
        option.series[params.seriesIndex].data[params.dataIndex].itemStyle.borderColor =
            option.series[params.seriesIndex].data[params.dataIndex].emphasis.itemStyle.borderColor;
        myChart.setOption(option);

    });
}