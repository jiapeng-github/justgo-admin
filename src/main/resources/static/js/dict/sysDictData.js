$(function(){

    var $table = $("#table");
    initTable();
    $('#searchU').click(function () {
        $table.bootstrapTable('refresh');
    });

    querySysDictSelect();

    //初始化
    function initTable() {
        $table.bootstrapTable({
            method: 'get',
//            contentType: "application/json",
            url: '/sysDict/listSysDictData',
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
                },*/ {
                    field: "id",
                    visible: false,
                }, {
                    field: "dictValue",
                    title: "字典值",
                    align: 'center',
                }, {
                    field: "dictDataName",
                    title: "字典数据名",
                    align: 'center',
//                    valign: 'middle'
                }, {
                    field: "dictDataValue",
                    title: "字典数据值",
                    align: 'center',
                }, {
                    field: "dictDataDesc",
                    title: "字典数据描述",
                    align: 'center',
                },{
                    field: "valid",
                    title: "状态",
                    align: 'center',
                    formatter:
                        function (value) { if (value=="0"){
                            return "失效";
                        }if (value=="1"){
                            return "生效";
                        }else {
                            return "未知状态";
                        }
                        }

                }, {
                    field: 'dictValue',
                    title: '操作',
                    formatter: function (value,row) {
                        var html =  "<a  class='text-primary margin_rg15 sysDictDataEdit' sysDictDataRow = '"+JSON.stringify(row)+"'>编辑数据</a>&nbsp;&nbsp;"
                        html += "<a  class='text-primary margin_rg15 sysDictCheck' sysDictDataRow = '"+JSON.stringify(row)+"'>检查redis值</a>&nbsp;&nbsp;"
                        html += "<a  class='text-primary margin_rg15 sysDictDelete' sysDictDataRow = '"+JSON.stringify(row)+"'>删除数据</a>";
                        return html;
                    }
                }
            ]
        });
    }

    $("#clearBtn").on('click',function() {
        $('#dictValue').val('');
        $('#dictDataName').val('');
        $('#dictDataDesc').val('');
        $('#valid').val('');
    });

    function queryParams(params) {
        var param = {
            pageNum: params.pageNum,  //当前页面
            pageSize: params.pageSize,	  //每页显示条数
            sortName: params.sortName,
            order: params.sortOrder,
            dictValue: $('#dictValue').val(),
            dictDataName: $('#dictDataName').val(),
            dictDataDesc: $('#dictDataDesc').val(),
            valid: $('#valid').val()
        };
        return param;
    };

    $("#add").on('click',function(){
        $(".modal-title").html("新建字典数据");
        $('#addFrom select[name="dictValue"]').removeAttr("disabled");
        $("#mymodal").modal("toggle");
        $("#addFrom")[0].reset();
        $('#addFrom input').val('');
        $("#addFrom").attr("action","/sysDict/addSysDictData");
    });

    //添加、编辑按钮提交
    $("#submit").on('click',function(){

        if ($('#addFrom select[name="dictValue"]').val()=='请选择'){
            layer.alert("请选择字典值！", {time:2000});
            return;
        }

        if($('#addFrom input[name="dictDataName"]').val()=='' || $('#addFrom input[name="dictDataValue"]').val()=='' ){
            //layer.alert("带星标号行不允许为空！", {time:2000});
            return;
        }

        //字典无效，无法新增字典数据
        if($("#addFrom").find("option:selected").attr('valid')=="0"){
            layer.alert("当前字典为失效状态，无法新增字典数据或者修改字典数据的状态！", {time:3000});
            return;
        }

        $('#addFrom select[name="dictValue"]').removeAttr("disabled");
        var url = $("#addFrom").attr("action");
        var dataJson =  $('#addFrom').serialize();
        addOrEdit(url,dataJson);

    });


    //编辑数据
    $("#table").on('click','.sysDictDataEdit',function(){
        var data= $('.sysDictDataEdit').attr('sysDictDataRow');
        if (data == ''||data == null||typeof(data)=="undefined") {
            layer.alert('请选择你要查看的信息！', {
                skin: 'layui-layer-molv' //样式类名
                ,closeBtn: 0
            })
        } else {
            $(".modal-title").html("编辑字典数据");
            $("#addFrom")[0].reset();
            $("#addFrom").attr("action","/sysDict/editSysDictData");
            $("#mymodal").modal("toggle");
            var obj = JSON.parse(data);
            $('#addFrom input[name="id"]').val(obj.id);
            $('#addFrom select[name="dictValue"]').val(obj.dictValue).prop("disabled", true);
            $('#addFrom input[name="dictDataName"]').val(obj.dictDataName);
            $('#addFrom input[name="dictDataValue"]').val(obj.dictDataValue);
            $('#addFrom input[name="dictDataDesc"]').val(obj.dictDataDesc);
            $('#addFrom select[name="valid"]').val(obj.valid);
        }
    });

    function getIdSelections() {
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }

    //检查redis值
    $("#table").on('click','.sysDictCheck',function(){
        var data= $('.sysDictDataEdit').attr('sysDictDataRow');
        var obj = JSON.parse(data);
        var dictValue = obj.dictValue;
        var dictDataName = obj.dictDataName;

        $.ajax({
            type: "POST",
            url: '/sysDict/findRedis',
            data: {
                'dictValue': dictValue,
                'dictDataName': dictDataName
            },
            dataType:"json",
            async:false,
            success: function(dataBack){ // 服务器返回的 json 结果
                layer.msg("dictDataValue:"+dataBack.dictDataValue, {time:2000});
            }
        });
    });


    //删除字典数据
    //$(".sysDictDelete").click(function(){
    $("#table").on('click','.sysDictDelete',function(){
        var data= $('.sysDictCheck').attr('sysDictDataRow');
        var obj = JSON.parse(data);

        var dictValue = obj.dictValue;
        var dictDataName = obj.dictDataName;
        var dictDataId = obj.id;

        layer.confirm('是否确定删除该字典数据？', {btn: ['确认','取消'] //按钮
            },function(){
            $.ajax({
                type: "POST",
                url: '/sysDict/delSysDictData',
                data: {
                    'id': dictDataId,
                    'dictValue': dictValue,
                    'dictDataName': dictDataName
                },
                dataType:"json",
                async:false,
                success: function(dataBack){ // 服务器返回的 json 结果
                    if (dataBack.count=='1'){
                        //layer.msg("删除成功", {time:1500});
                        window.location.href='/sysDict/manageSysDictData';
                    }else{
                        layer.alert("删除失败", {time:1500});
                    }
                }
            });
            //alert("yes");
        }, function(){
            //alert("no");
        })
    });

});


function querySysDictSelect(){
    var cbSelect = $('.channelBelong');
    cbSelect.empty();
    cbSelect.append($('<option></option>').attr({'valid':''}).text('请选择'));
    $.ajax({
        type: "POST",
        url: '/sysDict/listSysDict',
        data: {
            'valid': 1,//只查询有效的
            'page': 1,
            'rows' :10000//写死个行数
        },
        dataType:"json",
        async:false,
        success: function(res){ // 服务器返回的 json 结果
            $.each(res.rows,function(){
                var soption = $('<option></option>').attr({'valid':this.valid}).text(this.dictValue);
                cbSelect.append(soption);
            });
        }
    });
}

//新建或者修改字典数据
function  addOrEdit(url,data){
    $.ajax({
        type: "POST",
        url:url ,
        data:data,
        dataType:"json",
        success: function(data){ // 服务器返回的 json 结果
            if (data && data.count==1) {
                window.location.href='/sysDict/manageSysDictData';
            }else{
                layer.msg(data.errorMsg, {time:2000});
            }
        }
    });
}

function returnValid(validText){
    if (validText == '生效') {
        return '1';
    }
    return '0';
};
