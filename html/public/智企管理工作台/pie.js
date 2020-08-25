{
    function pie(setting) {
        if (!setting.data || setting.data.length <= 0 ) {
            return;
        }
        const drawer = {
            start() {
                drawer.id = setting.id;
                drawer.title = setting.title;
                drawer.width = setting.width;
                drawer.height = setting.height;
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
                            // selected: true,
                            itemStyle: { color: item.color, borderColor: item.color },
                            label: {
                                show: true,
                                position: 'outer',
                                color: '#444'
                            },
                            labelLine: {
                                show: true,
                                lineStyle: {
                                    color: '#999'
                                }
                            },
                            emphasis: {
                                label: {
                                    show: true,
                                    position: 'outer',
                                    color: '#444'
                                },
                                labelLine: {
                                    show: true,
                                    lineStyle: {
                                        color: '#999'
                                    }
                                },
                            },
                            tooltip: {
                                // trigger: 'item',
                                formatter: '{b} <br/>' + item.khs + ' 户: {c} ({d}%)'
                            },
                        });
                    } else {
                        data.push({
                            name: item.name,
                            value: item.value,
                            itemStyle: { color: drawer.defaultFillColor, borderColor: drawer.defaultColor },
                            label: {
                                show: false,
                            },
                            labelLine: {
                                show: false,
                            },
                            tooltip: {
                                // trigger: 'item',
                                formatter: '{b}未填写 <br/>{c} 户 ({d}%)'
                            },
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
                    title: {
                        show: true,
                        text: drawer.title,
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        // formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    series : [
                        {
                            name: drawer.title,
                            type: 'pie',
                            width: drawer.width,
                            height: drawer.height,
                            radius: '60%',
                            selectedMode: 'single',
                            label: {
                                show: false,
                            },
                            labelLine: {
                                show: false
                            },
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
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