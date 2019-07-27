// layui方法
layui.use(['tree', 'table', 'vip_table', 'layer'], function () {

    // 操作对象
    var table = layui.table,
        vipTable = layui.vip_table,
        layer = layui.layer,
        $ = layui.jquery;

    //查询数据
    function queryDynamic() {
        var roleName = $.trim($("#roleName").val());
        var roleType = $.trim($("#roleType").val());
        var roleTitle = $.trim($("#roleTitle").val());
        // 表格渲染
        var tableIns = table.render({
            elem: '#dateTable' ,//指定原始表格元素选择器（推荐id选择器）
                /*	            , height: vipTable.getFullHeight()    //容器高度*/
            response: {
                statusName: 'code' ,//数据状态的字段名称，默认：code
                statusCode: 200, //成功的状态码，默认：0
                msgName: 'msg', //状态信息的字段名称，默认：msg
                countName: 'total', //数据总数的字段名称，默认：count
                dataName: 'data' //数据列表的字段名称，默认：data
            },
            request: { //分页   设置分页名称
                pageName: 'page', //页码的参数名称，默认：page
                limitName: 'pageSize' //每页数据量的参数名，默认：limit
            },
            cols: [
                [ //标题栏
                    {type: 'numbers',title: '序号',width: 60,align: 'center'},
                    {field: 'name',title: '角色代号',width: 200,align: 'center'},
                    {field: 'title',title: '角色名称',width: 200,align: 'center'},
                    {field: 'type',title: '角色类型',width: 200,align: 'center'},
                    {field: 'status',title: '状态',width: 200,align: 'center'},
                    {field: 'addTime',title: '创建时间',width: 300,align: 'center'},
                    {fixed: 'right',title: '操作',width: 450,align: 'center',toolbar: '#barOption'} //这里的toolbar值是模板元素的选择器
                ]
            ],
            id: 'dataCheck',
            url: basePath + '/role/list',
            method: 'get',
            page: true,
            limits: [30, 60, 90, 150, 300],
            limit: 30 //默认采用30
                ,
            loading: false,
            where: {
                name: roleName,
                type: roleType,
                title: roleTitle
            },
            done: function (res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                //角色类型转换
                $("[data-field='type']").children().each(function () {
                    if ($(this).text() == '0') {
                        $(this).text("普通用户");
                    } else if ($(this).text() == '1') {
                        $(this).text("管理员");
                    }
                });
				//状态类型转换
				$("[data-field='status']").children().each(function () {
					if ($(this).text() == '0') {
						$(this).text("停用");
					} else if ($(this).text() == '1') {
						$(this).text("启用");
					}
				});
            }
        });
    }

    // 初始化表格数据
    queryDynamic();
    //初始化控件数据
    //loadData();
    //从后台加载数据
    function loadData() {
        $.ajax({
            type: 'get',
            url: basePath + '/role/list',
            data: {
                page: 1,
                pageSize: 100,
                name: ""
            },
            dataTable: 'json',
            success: function (result) {
                let role = result.data;
                for (let i = 0; i < role.length; i++) {
                    let roleTypeText;
                    if (role[i].type == 1) {
                        roleTypeText = '管理员'
                    } else {
                        roleTypeText = '普通用户'
                    }
                    $("#roleType").append("<option value='" + role[i].id + "'>" + roleTypeText +
                        "</option>");
                    $("#roleTypeAdd").append("<option value='" + role[i].id + "'>" + roleTypeText +
                        "</option>");
                    layui.form.render('select');
                }
            }
        })
    }
    //初始化菜单数据
    loadMenuInfo();

    function loadMenuInfo() {

    }
    //查询按钮事件
    $('#query').click(function (e) {
        queryDynamic();
    });

    //新增角色
    $('#add').click(function (e) {
        //先清空
        $('#roleNameAdd').val("");
        $('#roleTitleAdd').val("");
        $('#roleTypeAdd').val("");
        $('#roleStatusAdd').val("");
        //打开新增菜单对话框
        layer.open({
            type: 1,
            skin: 'layui-layer-lan', //样式类名
            title: '新增角色',
            area: ['600px', '420px'], // 宽高
            offset: '100px', //只定义top坐标，水平保持居中
            shade: ['0.3', '#000'],
            maxmin: true, //最大最小化。
            content: $('#addRolePage'), // 内容，content可传入的值是灵活多变的，不仅可以传入普通的html内容，还可以指定DOM，更可以随着type的不同而不同。
            btn: ['保存', '取消'], //弹出框里的确认，取 消
            yes: function (index, layero) { //该回调携带两个参数，分别为当前层索引、当前层DOM对象。如：
                var roleNameAdd = $('#roleNameAdd').val();
                var roleTitleAdd = $('#roleTitleAdd').val();
                var roleTypeAdd = parseInt($("#roleTypeAdd").val());
                var roleStatusAdd = parseInt($("#roleStatusAdd").val());
                $.ajax({
                    type: "post",
                    dataType: 'json',
                    url: basePath + '/role/insertOne',
                    data: {
                        "name": roleNameAdd,
                        "title": roleTitleAdd,
                        "type": roleTypeAdd,
                        "status": roleStatusAdd
                    },
                    success: function (data) {
                        console.log(data);
                        if (data.code == 200) {
                            layer.msg('添加成功!', {
                                icon: 6
                            });
                            queryDynamic();
                        }
                    }
                });
                layer.closeAll();
            }
        });
    });
    //监听菜单全选是否勾选
    layui.form.on('checkbox(checkAll)', function (data) {
        if (this.checked == true) {
            this.title = "全不选";
            $(".menu").each(function () {
                this.checked = true;
            });
        } else {
            this.title = "全选";
            $(".menu").each(function () {
                this.checked = false;
            });
        }
        layui.form.render('checkbox');
    });

    //监听工具条事件
    table.on('tool(dateTable)', function (obj) {
        var data = obj.data, //获得当前行数据
            layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'edit') {
            layer.open({
                type: 1,
                skin: 'layui-layer-lan',
                title: '修改角色',
                area: ['600px', '420px'],
                offset: '100px' //只定义top坐标，水平保持居中
                    ,
                shade: ['0.3', '#000'],
                maxmin: true,
                content: $('#addRolePage'),
                btn: ['确认', '取消'],
                success: function (index, layero) {
                    //设置当前的值？如何在dom加载完后，把数据装载进去？
                    $('#roleNameAdd').val(data.name);
                    $('#roleTitleAdd').val(data.title);
                    $('#roleTypeAdd').val(data.type);
                    $('#roleStatusAdd').val(data.status);
                    layui.form.render('select');
                },
                yes: function (index, layero) {
                    //获取修改后的值
                    var name = $('#roleNameAdd').val();
                    var title = $('#roleTitleAdd').val();
                    var type = $('#roleTypeAdd').val();
                    var status = $('#roleStatusAdd').val();
                    $.ajax({
                        type: "POST",
                        dataType: 'json',
                        url: basePath + '/role/update',
                        data: {
                            'roleId': data.id,
                            "name": name,
                            'title': title,
                            "type": type,
                            "status": status
                        },
                        success: function (data) {
                            if (data.code == 200) {
                                layer.msg('修改成功!', {
                                    icon: 6
                                });
                                queryDynamic();
                            }
                        }
                    });
                    layer.closeAll();
                }
            });
        } else if (layEvent == 'del') {
            //打开一个询问框
            layer.confirm('确定要删除?删除后数据不可恢复！', {
                icon: 3,
                title: '提示'
            }, function (index) {
                $.ajax({
                    type: "POST",
                    dataType: 'json',
                    url: basePath + '/role/deleteById',
                    data: {
                        'roleId': data.id
                    },
                    success: function (data) {
                        if (data.code == 200) {
                            layer.msg('删除成功!!!', {
                                icon: 6
                            });
                            queryDynamic();
                        }
                    },
                });
                layer.close(index);
            });
        } else if (layEvent == 'setPermission') {
            // 列出所有的权限
            layer.open({
                type: 1,
                skin: 'layui-layer-lan',
                title: '设置权限',
                area: ['800px', '600px'],
                offset: '100px' //只定义top坐标，水平保持居中
                    ,
                shade: ['0.3', '#000'],
                maxmin: true,
                content: $('#selectMenuPage'),
                btn: ['确认', '取消'],
                success: function (index, layero) {
                    //从数据获得菜单名称，并展示
                    $("#menuCheckList").empty();
                    $("#menuCheckList").append(
                        "<input type='checkbox' name='checkAll' lay-filter='checkAll' lay-skin='primary' title='全选'>"
                    )
                    $.ajax({
                        type: 'get',
                        url: basePath + '/menu/list',
                        data: {
                            page: 1,
                            pageSize: 100,
                            name: ""
                        },
                        dataTable: 'json',
                        success: function (result) {
                            var menuInfo = result.data;
                            for (var i = 0; i < menuInfo.length; i++) {
                                $("#menuCheckList").append(
                                    "<input type='checkbox' class='menu' name='menu' value='" +
                                    menuInfo[i].menuId +
                                    "' lay-skin='primary' title='" +
                                    menuInfo[i].name + "'>");
                            }
                            //根据roleid查找其拥有的menuid,role_menu_id
                            var roleId = data.id;
                            $.ajax({
                                type: "POST",
                                dataType: 'json',
                                url: basePath +
                                    '/rolemenu/selectMenuByRoleId',
                                data: {
                                    page: 1,
                                    pageSize: 100,
                                    "name": "",
                                    'roleId': roleId
                                },
                                success: function (roleMenuResult) {
                                    //循环设置checkbox选中
                                    var roleMenuInfo =
                                        roleMenuResult.data;
                                    for (var i = 0; i <
                                        roleMenuInfo.length; i++) {
                                        $("input[value='" +
                                            roleMenuInfo[i].menuId +
                                            "']").attr(
                                            "checked",
                                            "checked");
                                    }
                                    layui.form.render('checkbox');
                                }
                            });


                        }
                    })
                },
                yes: function (index, layero) {
                    //获取勾选的chekbox的name值
                    var menuIdList = "";
                    var checkboxList = $("input:checkbox[name='menu']:checked");
                    checkboxList.each(function (index, item) { // 遍历name=standard的多选框
                        if (index != (checkboxList.length - 1)) {
                            menuIdList += $(this).val() + ',';
                        } else {
                            menuIdList += $(this).val(); //拼接成用逗号隔开的字符串,里面是id
                        }
                    });
                    $.ajax({
                        type: "POST",
                        dataType: 'json',
                        url: basePath + '/rolemenu/addMenu2Role',
                        data: {
                            'roleId': data.id,
                            "menuIdList": menuIdList
                        },
                        success: function (data) {
                            if (data.code == 200) {
                                layer.msg('修改成功!', {
                                    icon: 6
                                });
                                queryDynamic();
                            }
                        }
                    });
                    layer.closeAll();
                }
            });
        }

    });
    // 获取选中行
    table.on('checkbox(dataCheck)', function (obj) {
        console.log(obj.checked); //当前是否选中状态
        console.log(obj.data); //选中行的相关数据
        console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
    });

});
