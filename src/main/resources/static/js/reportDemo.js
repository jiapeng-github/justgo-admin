$(function(){
    iniDatePicket();
    $('#reportRangeType').unbind("change.reportRangeType").bind('change.reportRangeType',function(){
       var divKey = "."+$(this).val()+"Div";
       $(".reportSwitchDiv").hide();
       $(divKey).show();
    });


    var containerId='barDemo';
    var titleText = '12312312312';
    var subtitleText = '按时打发第三方: <a href="http://www.baidu.com">百度链接</a>';
    var yAxisText = '登录人数（人）';
    // var tooltipJson = {'title':'登录人数','decimal':2,'unit':'人'};
    var tooltipJson = {'title':'登录人数','decimal':0,'unit':'人'};

    $('#genReport').unbind("click.genReport").bind('click.genReport',function(){
        ReportUtils.column(getData(),containerId,titleText,subtitleText,yAxisText,tooltipJson);
    });
    $('#genReport3').unbind("click.genReport3").bind('click.genReport3',function(){
        ReportUtils.line(getData(),containerId,titleText,subtitleText,yAxisText,tooltipJson);
    });
    $('#genReport4').unbind("click.genReport4").bind('click.genReport4',function(){
        ReportUtils.funnel(getData(),containerId,titleText,subtitleText,yAxisText,tooltipJson);
    });

/*    $('#genReport2').unbind("click.genReport2").bind('click.genReport2',function(){
        ReportUtils.column_Multiple(getData2(),containerId,titleText,subtitleText,yAxisText,tooltipJson);
    });*/

    $('.btn-group :button').unbind("click.btnswitch").bind('click.btnswitch',function(){
        $(this).parent().children(':button').removeClass('active');
        $(this).addClass("active");
        $('.workinput').val('');
        $('.rangeDiv').hide();
        if($(this).hasClass('day')){
            $('.daydiv').show();
        }else if($(this).hasClass('month')){
            $('.monthdiv').show();
        }else if($(this).hasClass('year')){
            $('.yeardiv').show();
        }
    });
    function getData(){
        var fixdata = new Array();
        var sourcedata;
        var data={};

        data.begin = $('.dayDiv').find(':input[name="begin"]').val();
        data.end = $('.dayDiv').find(':input[name="end"]').val();
        console.info(data);
        if(data.begin=='' || data.end == ''){
            layer.alert('报表需要时间范围，请选择开始结束时间！', {icon: 5});
            return;
        }
        $.ajax({
            type: "POST",
            url: '/rd/reportData',
            data: data,
            dataType:"json",
            async:false,
            success: function(res){ // 服务器返回的 json 结果
                sourcedata=res;
            }
        });
        $.each(sourcedata,function(index,item){
            var tmp = new Array();
            tmp[0]=item.title;
            tmp[1]=parseInt(item.count);
            fixdata[index]=tmp;
        });
        return fixdata;
    }
    function getData2(){
        var fixRespData={categories:new Array(),
            series:new Array()};
        var sourcedata;
        var data={};
        data.begin = $('.dayDiv').find(':input[name="begin"]').val();
        data.end = $('.dayDiv').find(':input[name="end"]').val();
        if(data.begin=='' || data.end == ''){
            layer.alert('报表需要时间范围，请选择开始结束时间！', {icon: 5});
            return;
        }
        $.ajax({
            type: "POST",
            url: '/rd/reportData2',
            data: data,
            dataType:"json",
            async:false,
            success: function(res){ // 服务器返回的 json 结果
                sourcedata=res;
            }
        });
        var categories = new Array();
        var peopleCount={name:'人数',data:new Array()};
        var amountSum={name:'金额',data:new Array()};

        $.each(sourcedata,function(index,item){
            categories.push(item.title);
            peopleCount.data.push(parseInt(item.count));
            amountSum.data.push(parseInt(item.sumAmount));
        });
        fixRespData.categories = categories;
        fixRespData.series.push(peopleCount);
        fixRespData.series.push(amountSum);
        return fixRespData;
    }
    function iniDatePicket() {
        $('.input-daterange_day input').each(function() {
            $(this).datepicker({
                format: 'yyyy-mm-dd',
                autoclose:true,
                language:'cn',
                calendarWeeks:true,
                weekStart:1
            });
        });
        $('.input-daterange_week input').each(function(index,item) {
            if(index==0){
                $(item).datepicker({
                    format: 'yyyy-mm-dd',
                    autoclose:true,
                    language:'cn',
                    daysOfWeekDisabled:[0,2,3,4,5,6],
                    calendarWeeks:true,
                    weekStart:1
                });
            }else {
                $(item).datepicker({
                    format: 'yyyy-mm-dd',
                    autoclose:true,
                    language:'cn',
                    daysOfWeekDisabled:[1,2,3,4,5,6],
                    calendarWeeks:true,
                    weekStart:1
                });
            }

        });
        $('.input-daterange_month input').each(function() {
            $(this).datepicker({
                format: 'yyyy-mm',
                autoclose:true,
                language:'cn',
                minViewMode:'months',
                calendarWeeks:true,
                weekStart:1
            });
        });
        $('.input-daterange_year input').each(function() {
            $(this).datepicker({
                format: 'yyyy',
                autoclose:true,
                language:'cn',
                minViewMode:'years',
                calendarWeeks:true,
                weekStart:1
            });
        });

    }
});
