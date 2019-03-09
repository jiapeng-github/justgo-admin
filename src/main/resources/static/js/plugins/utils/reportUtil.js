var ReportUtils = {
    column:function(reportData,containerId,titleText,subtitleText,yAxisText,tooltipJson){
        if(containerId==null)containerId = 'container';
        if(titleText==null)titleText = '报表图';
        $('#'+containerId).highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: titleText
            },
            subtitle: {
                align: "right",
                style: {
                    fontSize: '15px',
                    color:"#ff0000"
                },
                text: subtitleText
            },
            xAxis: {
                type: 'category',
                labels: {
                    rotation: -45,
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: yAxisText
                }
            },
            legend: {
                enabled: false
            },

            tooltip: {
                pointFormat: tooltipJson==null?'':tooltipJson.title+': <b>{point.y:.'+tooltipJson.decimal+'f} '+tooltipJson.unit+'</b>'
            },
            series: [{
                name: 'Population',
                data:reportData,
                dataLabels: {
                    enabled: true,
                    rotation: -90,
                    color: '#FFFFFF',
                    align: 'right',
                    format: tooltipJson==null?'':'{point.y:.'+tooltipJson.decimal+'f}', // one decimal
                    y: 10, // 10 pixels down from the top
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            }]
        });
    },
    column_Multiple:function(reportData,containerId,titleText,subtitleText,yAxisText,tooltipJson){
        if(containerId==null)containerId = 'container';
        if(titleText==null)titleText = '报表图';
        $('#'+containerId).highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: titleText
            },
            subtitle: {
                align: "right",
                style: {
                    fontSize: '15px',
                    color:"#ff0000"
                },
                text: subtitleText
            },
            xAxis: {
                categories: reportData.categories,
                crosshair: true
            },
            yAxis: {
                min: 0,
                title: {
                    text: yAxisText
                }
            },
            legend: {
                enabled: false
            },

            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.'+tooltipJson.decimal+'f}</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: reportData.series
        });
    },
    line:function(reportData,containerId,titleText,subtitleText,yAxisText,tooltipJson){
        if(containerId==null)containerId = 'container';
        if(titleText==null)titleText = '报表图';
        $('#'+containerId).highcharts({
            chart: {
                type: 'line'
            },
            title: {
                text: titleText
            },
            subtitle: {
                align: "right",
                style: {
                    fontSize: '15px',
                    color:"#ff0000"
                },
                text: subtitleText
            },

            xAxis: {
                type: 'category',
                labels: {
                    rotation: -45,
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: yAxisText
                }
            },
            legend: {
                enabled: false
            },

            tooltip: {
                pointFormat: tooltipJson==null?'':tooltipJson.title+': <b>{point.y:.'+tooltipJson.decimal+'f} '+tooltipJson.unit+'</b>'
            },
            series: [{
                name: 'Population',
                data:reportData,
                dataLabels: {
                    enabled: true,
                    // rotation: -90,
                    color: '#FFFFFF',
                    align: 'right',
                    format: tooltipJson==null?'':'{point.y:.'+tooltipJson.decimal+'f}', // one decimal
                    y: -10, // 10 pixels down from the top
                    x:30,
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            }]
        });
    },
    funnel:function(reportData,containerId,titleText,subtitleText,yAxisText,tooltipJson){
        if(containerId==null)containerId = 'container';
        if(titleText==null)titleText = '报表图';
        $('#'+containerId).highcharts({
            chart: {
                type: 'funnel',
                marginRight: 100
            },
            title: {
                text: titleText,
                x: -50
            },
            subtitle: {
                align: "right",
                style: {
                    fontSize: '15px',
                    color:"#ff0000"
                },
                text: subtitleText
            },

            plotOptions: {
                series: {
                    dataLabels: {
                        enabled: true,
                        crop : false,
                        overflow: 'none',
                        format: '<b>{point.name}</b> ({point.y:,.0f})',
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black',
                        softConnector: true
                    },
                    neckWidth: '30%',
                    neckHeight: '25%'
                    //-- Other available options
                    // height: pixels or percent
                    // width: pixels or percent
                }
            },
            legend: {
                enabled: false
            },
            tooltip: {
                pointFormat: tooltipJson==null?'':tooltipJson.title+': <b>{point.y:.'+tooltipJson.decimal+'f} '+tooltipJson.unit+'</b>'
            },
            series: [{
                name: 'Population',
                data:reportData
            }]
        });
    }
};
