var CommonUtils = {
    uuid:function(){
        var s = [];
        var hexDigits = "0123456789abcdef";
        for (var i = 0; i < 36; i++) {
            s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
        }
        s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
        s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
        s[8] = s[13] = s[18] = s[23] = "-";

        var uuid = s.join("");
        return uuid;
    },

    formatTime:function (value){
        if (value == null || value == '') {
            return '';
        }
        if(value.length==14){
            //20160618163611
            return value.substr(0,4)+'-'+value.substr(4,2)+'-'+value.substr(6,2)+' '+value.substr(8,2)+':'+value.substr(10,2)+':'+value.substr(12,2);
        }else if(value.length==6){
            return value.substr(0,4)+'-'+value.substr(4,2);
        }else if(value.length==8){
            return value.substr(0,4)+'-'+value.substr(4,2)+'-'+value.substr(6,2);
        }
        return value;
    },
    formatDatebox:function(value){
        if (value == null || value == '') {
            return '';
        }
        var dt;
        if (value instanceof Date) {
            dt = value;
        } else {

            dt = new Date(value);

        }
        return dt.format("yyyy-MM-dd HH:mm:ss"); //扩展的Date的format方法(上述插件实现)
    },

    formatToDate:function(value){
        if (value == null || value == '') {
            return '';
        }
        var dt;
        if (value instanceof Date) {
            dt = value;
        } else {

            dt = new Date(value);

        }
        return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)
    },
    compareDate:function(startDate,endDate){
        var startTime = new Date(startDate);
        var endTime = new Date(endDate);
        if(startTime.getTime() > endTime.getTime()){
            return false;
        }else{
            return true;
        }
    },
    CheckPhone:{
        /**校验手机号码*/
        CHECK_MOBILE : function(str){
            var r = /^1[3|4|5|7|8][0-9]{9}$/;
            return r.test(str);
        },
        /**校验固话*/
        CHECK_TEL : function(str){
            var r = /^0\d{2,3}-\d{7,8}(-\d{1,6})?$/;
            return r.test(str);
        }
    },
    MathCheck:{
        FLOAT_NOT_NEGATIVE:function(str){
            //非负浮点数（正浮点数 + 0）
            var r = /^\+{0,1}\d+(\.\d{1,2})?$/;
            return r.test(str);
        },
        INTEGER:function(str){
            //校验正整数
            var r = /^([+]?)(\d+)$/;
            return r.test(str);
        },
        PERCENT:function(str){
            //校验百分数
            var r = /^(((\d|[1-9]\d)(\.\d{1,2})?)|100|100.0|100.00)$/;
            return r.test(str);
        }
    },

    ExportExcel:function (url,data) {
        var body = $(document.body),
            form = $("<form method='post'></form>"),
            input;
        form.attr("action",url);
        form.attr("method","post");
        $.each(data,function(key,value){
            input = $("<input type='hidden'>");
            input.attr({"name":key});
            input.val(value);
            form.append(input);
        });
        form.appendTo(document.body);
        form.submit();
        document.body.removeChild(form[0]);
    },
    ajaxReq: function(ajaxurl,param,callback) {
        $.ajax({
            type: "POST",
            url: ajaxurl,
            data: param,
            dataType: "json",
            async: false,
            success: function (res) { // 服务器返回的 json 结果
                callback(res);
            }
        })
    },
    /**获取当前时间*/
    getNowFormatDate: function() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
        return currentdate;
    },
};


Date.prototype.format = function(format) {
    var o = {
        "M+" : this.getMonth() + 1,
        "d+" : this.getDate(),
        "H+" : this.getHours(),
        "m+" : this.getMinutes(),
        "s+" : this.getSeconds(),
        "q+" : Math.floor((this.getMonth() + 3) / 3),
        "S" : this.getMilliseconds()
    };
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }

    for ( var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};

$(function(){
    $.ajaxSetup({
        contentType : "application/x-www-form-urlencoded;charset=utf-8",
        complete : function(XMLHttpRequest, textStatus) {
            if(typeof(XMLHttpRequest.responseText) != 'undefined'||XMLHttpRequest.responseText!=''){
                try {
                    var json = JSON.parse(XMLHttpRequest.responseText);
                    if(json.responseCode=='9997'){
                        layer.alert(json.responseDesc, {
                            skin: 'layui-layer-molv', //样式类名
                            closeBtn: 0  // 是否显示关闭按钮
                        })
                    }
                } catch (e) {
                    console.log("ajax-skip-page.")
                }
            }
        }
    });
    $(document).ajaxStart(function(){layer.load();});
    $(document).ajaxStop(function(){layer.closeAll('loading');});
});