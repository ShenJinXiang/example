{
    function pie(setting) {
        if (!setting.data || setting.data.length <= 0 ) {
            return;
        }
        const drawer = {
            start() {
                drawer.id = setting.id;
                drawer.title = setting.title;
                drawer.data = setting.data;
                drawer.click = setting.click;
                drawer.defaultColor = setting.data[0].color;
                drawer.defaultFillColor = '#f1f1f1';
                drawer.echartsObj = echarts.init(document.getElementById(drawer.id));
                drawer.initOption();
                drawer.echartsObj.setOption(drawer.option);
                drawer.bindEvent();
                console.log(drawer);
            },
            initOption() {
                let data = [];
                drawer.selectStatus = [];
                drawer.data.forEach((item, index) => {
                    if (index == 0) {
                        data.push({
                            name: item.name,
                            value: item.value,
                            itemStyle: { color: item.color, borderColor: item.color },
                        });
                    } else {
                        data.push({
                            name: item.name,
                            value: item.value,
                            itemStyle: { color: drawer.defaultFillColor, borderColor: drawer.defaultColor },
                            emphasis: {
                                itemStyle: {
                                    color: item.color,
                                    borderColor: item.color ,
                                    // opacity: 1
                                }
                            }
                        });
                    }
                    drawer.selectStatus.push(false);
                });

                drawer.option = {
                    series : [
                        {
                            name: drawer.title,
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
                                }
                            },
                            data: data
                        }
                    ]
                }
            },
            bindEvent() {
                // /*
                drawer.echartsObj.on('click', function (params) {
                    let index = params.dataIndex;
                    let data = drawer.option.series[params.seriesIndex].data;
                    drawer.selectStatus.forEach((item, i) => {
                        if (i === index) {
                            drawer.selectStatus[i] = !drawer.selectStatus[i];
                        } else {
                            drawer.selectStatus[i] = false;
                        }
                    });
                    data.forEach((item, i) => {
                        if (i !== 0) {
                            if (drawer.selectStatus[i]) {
                                item.itemStyle.color = drawer.data[i].color;
                                item.itemStyle.borderColor = drawer.data[i].color;
                            } else {
                                item.itemStyle.color = drawer.defaultFillColor;
                                item.itemStyle.borderColor = drawer.defaultColor;
                            }
                        }
                    });
                    drawer.echartsObj.setOption(drawer.option);

                    drawer.click(params);
                });

                 // */
                // drawer.echartsObj.on("pieselectchanged", function (json) {
                //     console.log(json);
                // });
            }
        };
        drawer.start();
        return drawer;
    }
}