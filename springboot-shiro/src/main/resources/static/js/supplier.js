var currentPage = 1;
// layui方法
layui.use(['tree', 'table', 'vip_table', 'layer'], function () {
    // 操作对象
    var table = layui.table,
        vipTable = layui.vip_table,
        layer = layui.layer,
        form = layui.form,
        $ = layui.jquery;

    function queryDynamic() {
        // 表格渲染
        let name = $.trim($("#query_name").val());
        let tableIns = table.render({
            elem: '#dataTable', //指定原始表格元素选择器（推荐id选择器）
            response: {
                statusName: 'code', //数据状态的字段名称，默认：code
                statusCode: 200, //成功的状态码，默认：0
                msgName: 'msg', //状态信息的字段名称，默认：msg
                countName: 'total', //数据总数的字段名称，默认：count
                dataName: 'data' //数据列表的字段名称，默认：data
            },
            method: 'get',
            request: { //分页   设置分页名称
                pageName: 'page' ,//页码的参数名称，默认：page
                limitName: 'pageSize' //每页数据量的参数名，默认：limit
            },
            page: true,
            limits: [10, 20, 30, 60],
            limit: 10 ,//默认采用30
            loading: false,
            where: {
                name: name
            },
            url: '/supplier/list',
            cols: [
                [{
                    type: 'numbers',
                    title: '序号',
                    width: 60
                    }, {
                    field: 'name',
                    title: '供应商名称',
                    width: 120,
                    align: 'center',
                    sort: true
                }, {
                    field: 'contactPerson',
                    title: '联系人',
                    width: 120,
                    align: 'center'
                }, {
                    field: 'phone',
                    title: '联系方式',
                    width: 100,
                    align: 'center'
                }, {
                    field: 'address',
                    title: '供应商地址',
                    width: 120,
                    sort: true,
                    align: 'center'
                },{
                    field: 'deliveryFee',
                    title: '配送费',
                    width: 120,
                    sort: true,
                    align: 'center'
                },{
                    field: 'deliveryAmount',
                    title: '起送金额',
                    width: 120,
                    sort: true,
                    align: 'center'
                },{
                    field: 'deliveryService',
                    title: '配送服务',
                    width: 120,
                    sort: true,
                    align: 'center'
                },{
                    field: 'deliveryScale',
                    title: '配送范围',
                    width: 120,
                    sort: true,
                    align: 'center'
                },{
                    field: 'status',
                    title: '状态',
                    width: 120,
                    sort: true,
                    align: 'center'
                },{
                    field: 'addTime',
                    title: '提交时间',
                    width: 120,
                    sort: true,
                    align: 'center'
                },{
                    field: 'updateTime',
                    title: '修改时间',
                    width: 120,
                    sort: true,
                    align: 'center'
                }, {
                    fixed: 'right',
                    title: '操作',
                    align: 'center',
                    toolbar: '#barOption'
                }
                ]
            ],
            id: 'dataCheck',
            done: function (res, curr, count) {
                //状态类型转换
                $("[data-field='status']").children().each(function () {
                    if ($(this).text() === '0') {
                        $(this).text("不确定");
                    } else if ($(this).text() === '1') {
                        $(this).text("审核中");
                    }else if ($(this).text() === '2') {
                        $(this).text("正常服务");
                    }else if ($(this).text() === '3') {
                        $(this).text("服务暂停");
                    }else if ($(this).text() === '4') {
                        $(this).text("服务终止");
                    }
                });
            }
        });
    }

    // 初始化表格数据
    queryDynamic();
    //查询按钮事件
    $('#query').click(function (e) {
        queryDynamic();
    });

    $('#add').click(function (e) {
        layer.open({
            type: 1,
            skin: 'layui-layer-lan', //样式类名
            title: '新增菜单',
            area: ['900px', '700px'] ,// 宽高
            offset: '100px', //只定义top坐标，水平保持居中
            shade: ['0.3', '#000'],
            maxmin: false ,//最大最小化。
            content: $('#addSupplierPage'),
            btn: ['确认', '取消'],
            yes: function (index, layero) {
                $.ajax({
                    type: "POST",
                    dataType: 'json',
                    url: accountBackPath + '/supplier/insert',
                    data: {
                        "name": $('#name').val(),
                        "contactPerson": $('#contactPerson').val(),
                        "phone": $('#phone').val(),
                        'address':$('#address').val(),
                        'deliveryFee':$('#deliveryFee').val(),
                        'deliveryAmount':$('#deliveryAmount').val(),
                        'deliveryService':$('#deliveryService').val(),
                        'deliveryScale':$('#deliveryScale').val(),
                        'status':$('#status').val()
                    },
                    success: function (data) {
                        if (data.code === 200) {
                            layer.msg('添加成功!', {icon: 6});
                            queryDynamic();
                        }
                    }
                });
                layer.closeAll();
            }
        });
    });

    /**
     * 初始化控件的值
     * @param data
     */
    function initControlValues(data){
        if (data != null){
            $('#name').val(data.name);
            $('#contactPerson').val(data.contactPerson);
            $('#phone').val(data.phone);
            $('#address').val(data.address);
            $('#deliveryFee').val(data.deliveryFee);
            $('#deliveryAmount').val(data.deliveryAmount);
            $('#deliveryService').val(data.deliveryService);
            $('#deliveryScale').val(data.deliveryScale);
            $('#status').val(data.status);
            layui.form.render('select');
        }
    }

    let supplier = {
        data:{},
        edit: function (data) {
            layer.open({
                type: 1,
                skin: 'layui-layer-lan',
                title: '修改菜单',
                area: ['900px', '700px'] ,// 宽高
                offset: '100px', //只定义top坐标，水平保持居中
                shade: ['0.3', '#000'],
                maxmin: true,
                content: $('#addSupplierPage'),
                btn: ['确认', '取消'],
                success: function () {
                    initControlValues(data);
                },
                yes: function (index, layero) {
                    $.ajax({
                        type: "POST",
                        dataType: 'json',
                        url: accountBackPath + '/supplier/update',
                        data: {
                            'id': data.id,
                            "name": $('#name').val(),
                            "contactPerson": $('#contactPerson').val(),
                            "phone": $('#phone').val(),
                            'address':$('#address').val(),
                            'deliveryFee':$('#deliveryFee').val(),
                            'deliveryAmount':$('#deliveryAmount').val(),
                            'deliveryService':$('#deliveryService').val(),
                            'deliveryScale':$('#deliveryScale').val(),
                            'status':$('#status').val()

                        },
                        success: function (data) {
                            if (data.code === 200) {
                                layer.msg('修改成功!', {icon: 6});
                                queryDynamic();
                            }
                        }
                    });
                    layer.closeAll();
                }
            });
        },
        del:function (data) {
            layer.confirm('确定要删除?删除后数据不可恢复！', {
                icon: 3,
                title: '提示'
            }, function (index) {
                $.ajax({
                    type: "delete",
                    dataType: 'json',
                    url: accountBackPath + '/supplier/delete/' + data.id,
                    success: function (data) {
                        if (data.code === 200) {
                            layer.msg('删除成功!!!', {
                                icon: 6
                            });
                            queryDynamic();
                        }
                    },
                });
                layer.close(index);
            });
        }
    }

    //监听工具条
    //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    table.on('tool(dataTable)', function (obj) {
        var data = obj.data, //获得当前行数据
            layEvent = obj.event; //获得 lay-event 对应的值
        supplier.data = data;
        if (layEvent === 'edit') {
            supplier.edit(data);
        } else if (layEvent === 'del') {
            supplier.del(data);
        }
    });
});
