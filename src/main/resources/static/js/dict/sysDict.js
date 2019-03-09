$(function(){

    var $table = $("#table");
    initTable();
    $('#searchU').click(function () {
        $table.bootstrapTable('refresh');
    });

    //初始化
    function initTable() {
        $table.bootstrapTable({
            method: 'get',
            contentType: "application/json",
            url: '/sysDict/listSysDict',
            //设置为 true 会有隔行变色效果
            striped: true,
            //设置为 true 会在表格底部显示分页条
            pagination: true,
            //设置在哪里进行分页，可选值为 'client' 或者 'server'。设置 'server'时，必须设置 服务器数据地址（url）或者重写ajax方法
            sidePagination: "server",
            //singleSelect: true,	//单选
            //如果设置了分页，页面数据条数
            pageSize: 10,
            //如果设置了分页，设置可供选择的页面数据条数。设置为All 则显示所有记录。
            pageList: [10],
            queryParams: queryParams,
            pageNum: 1,
            queryParamsType: "other",
            columns: [
                /*{
                    field: "state",
                    checkbox: true
                }, */{
                    field: "id",
                    visible: false,
                }, {
                    title: '序号',
                    width: '5%',
                    align: 'center',
                    formatter: function (value, row, index) {
                        return index+1;
                    }
                }, {
                    field: "dictName",
                    title: "字典名称",
                    align: 'center',
                }, {
                    field: "dictValue",
                    title: "字典值",
                    align: 'center',
                },{
                    field: "dictDesc",
                    title: "字典描述",
                    align: 'center',
                },{
                    field: "valid",
                    title: "状态",
                    align: 'center',
                    formatter:
                        function (value) { if (value==="0"){
                            return "失效";
                        }if (value==="1"){
                            return "生效";
                        }else {
                            return "未知状态";
                        }
                    }

                }, {
                    field: 'dictValue',
                    title: '操作',
                    formatter: function (value,row) {
                        return "<a  class='text-primary margin_rg15 sysDictEdit' sysDictRow = '" + JSON.stringify(row) + "'>编辑字典</a>&nbsp;&nbsp;";
                    }
                }
            ]
        });
    }

    function queryParams(params) {
        return {
            pageNum: params.pageNum,  //当前页面
            pageSize: params.pageSize,	  //每页显示条数
            sortName: params.sortName,
            order: params.sortOrder,
            dictName: $('#dictName').val(),
            dictValue: $('#dictValue').val(),
            dictDesc: $('#dictDesc').val(),
            valid: $('#valid').val()
        };
    }

    $("#add").on('click',function(){
        $(".modal-title").html("新建字典");
        $("#mymodal").modal("toggle");
        $("#addFrom")[0].reset();
        $('#addFrom input').val('');
        $("#addFrom").attr("action","/sysDict/addSysDict");
    });

    //导出报表
    $("#reportDict").on('click',function(){
        var body = $(document.body),
            form = $("<form method='post'></form>"),
            input;
        form.attr("action","/sysDict/report");
        form.attr("method","post");
        form.appendTo(document.body);
        form.submit();
        document.body.removeChild(form[0]);
    });

    //添加、编辑按钮提交
    $("#submit").on('click',function(){
        if($('#addFrom input[name="dictName"]').val()==='' || $('#addFrom input[name="dictValue"]').val()==='' || $('#addFrom input[name="dictDesc"]').val()==='' ){
            //layer.alert("带星标号行不允许为空！", {time:2000});
            return;
        }

        var url = $("#addFrom").attr("action");
        var dataJson =  $('#addFrom').serialize();

        addOrEdit(url,dataJson);

    });

    //查看数据
    /*$("#tbody").on('click','.sysDictDataList',function(){
        var url = "/sysDict/manageSysDictData?dictValue="+$(this).attr('dictValue')+'&valid='+returnValid($(this).parents("tr").find("td").eq(4).text());
        window.location.href = url;
    });*/

    //查看数据
    $("#manageDictData").on('click',function(){
        var url = "/sysDict/manageSysDictData";
        window.location.href = url;
    });

    //编辑字典
    $("#table").on('click','.sysDictEdit',function(){
        let data = $('.sysDictEdit').attr('sysDictRow');
        if (data === ''||data == null||typeof(data)==="undefined") {
            layer.alert('请选择你要查看的信息！', {
                skin: 'layui-layer-molv' //样式类名
                ,closeBtn: 0
            })
        } else {
            $("#mymodal").modal("toggle");
            $(".modal-title").html("编辑字典");
            data = JSON.parse(data);
            $('#addFrom input[name="id"]').val(data.id);
            $('#addFrom input[name="dictName"]').val(data.dictName);
            $('#addFrom input[name="dictValue"]').val(data.dictValue);
            $('#addFrom input[name="dictDesc"]').val(data.dictDesc);
            $('#addFrom select[name="valid"]').val(data.valid);
            $("#addFrom").attr("action","/sysDict/editSysDict");
        }
    });

    function getIdSelections() {
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }

    $("#clearbtn").on('click',function() {
        $('#dictName').val('');
        $('#dictValue').val('');
        $('#dictDesc').val('');
        $('#valid').val('');
    });

    $("#flushRedisData").on('click',function() {
        layer.confirm('该操作会把全部redis缓存数据重新从数据库字典中导入，您确定要执行吗？', {btn: ['确认','取消'] //按钮
            }, function(){
                var url = '/sysDict/flushRedisData';
                $.ajax({
                    type: "POST",
                    url:url ,
                    dataType:"json",
                    success: function(data){ // 服务器返回的 json 结果
                        if (data) {
                            layer.msg("重导缓存记录共 "+data.count+" 条", {time:2000});
                        }else{
                            layer.msg(data.errorMsg, {time:2000});
                        }
                    }
                });
            }, function(){
                //alert("no");
            }
        );
    });

});

//新建或者修改字典
function  addOrEdit(url,data){
    $.ajax({
        type: "POST",
        url:url ,
        data:data,
        dataType:"json",
        success: function(data){ // 服务器返回的 json 结果
            if (data && data.count===1) {
                window.location.href='/sysDict/manageSysDict';
            }else{
                layer.msg(data.errorMsg, {time:2000});
            }
        }
    });
}

function returnValid(validText){
    if (validText === '生效') {
        return '1';
    }
    return '0';
};
