// layui方法
layui.use(['tree', 'table', 'vip_table', 'layer'], function () {
    // 操作对象
    var table = layui.table,
        vipTable = layui.vip_table,
        layer = layui.layer,
        form = layui.form,
        $ = layui.jquery;

    function refresh() {
        var username = $.trim($("#username").val());
        var realname = $.trim($("#realname").val());
        // 表格渲染
        var tableIns = table.render({
            elem: '#dateTable', //指定原始表格元素选择器（推荐id选择器）
            cols: [
                [ //标题栏
                    {
                        type: 'numbers',
                        title: '序号',
                        width: '60'
                    }, {
                    field: 'username',
                    title: '用户名',
                    width: 150,
                    align: 'center',
                    sort: true
                }, {
                    field: 'realname',
                    title: '真实姓名',
                    width: 150,
                    align: 'center',
                    sort: true
                }, {
                    field: 'userStatus',
                    title: '状态',
                    width: 150,
                    align: 'center'
                }, {
                    field: 'addTime',
                    title: '创建时间',
                    width: 300,
                    sort: true,
                    align: 'center'
                }, {
                    field: 'updateTime',
                    title: '修改时间',
                    width: 300,
                    sort: true,
                    align: 'center'
                },{
                    fixed: 'right',
                    title: '操作',
                    align: 'center',
                    toolbar: '#barOption'
                } //这里的toolbar值是模板元素的选择器
                ]
            ],
            id: 'dataCheck',
            url: accountBackPath + '/user/list',
            method: 'get',
            page: true,
            limits: [30, 60, 90, 150, 300],
            limit: 30, //默认采用30
            request: { //分页   设置分页名称
                pageName: 'page', //页码的参数名称，默认：page,
                limitName: 'pageSize' //每页数据量的参数名，默认：limit
            },
            response: {
                statusName: 'code', //数据状态的字段名称，默认：code
                statusCode: 200, //成功的状态码，默认：0
                msgName: 'msg', //状态信息的字段名称，默认：msg
                countName: 'total', //数据总数的字段名称，默认：count
                dataName: 'data' //数据列表的字段名称，默认：data
            },
            where: {
                username: username,
                realname: realname
            },
            loading: false,
            done: function (res, curr, count) {
                $("[data-field='userStatus']").children().each(function () {
                    if ($(this).text() === '1') {
                        $(this).text("正常");
                    } else if ($(this).text() === '2') {
                        $(this).text("已锁");
                    } else if ($(this).text() === '3') {
                        $(this).text("屏蔽");
                    } else if ($(this).text() === '9') {
                        $(this).text("删除");
                    }
                });
                initRoleList();
            }
        })

    };

    // you code ...
    refresh();

    /**
     * 初始化角色列表
     */
    function initRoleList() {
        $.ajax({
            type: 'get',
            url: accountBackPath + '/role/list',
            data: {
                page: 1,
                pageSize: 100,
                name: ""
            },
            dataTable: 'json',
            success: function (result) {
                var role = result.data;
                for (var i = 0; i < role.length; i++) {
                    $("#roleTitleAdd").append("<option value='" + role[i].id + "'>" + role[i].title +
                        "</option>");
                    //layui.form.render('select');
                }
            }
        })
    }

    //查询按钮事件
    $('#query').click(function (e) {
        refresh();
    });

    /**
     * 清空
     */
    function clearData() {
        $('#realnameAdd').val("");
        $('#usernameAdd').val("");
        $("#roleTypeAdd").val("-1");
        $("#roleTitleAdd").empty();
        $('#passwordAdd').val("");
    }

    //新增平台用户
    $('#add').click(function (e) {
        clearData();
        //显示密码框
        $("#passwordPane").show();
        $('#usernameAdd').removeAttr("disabled"); //账户名不可以修改
        //打开新增菜单对话框
        layer.open({
            type: 1,
            skin: 'layui-layer-lan', //样式类名
            title: '添加用户',
            area: ['800px', '600px'], // 宽高
            offset: '100px', //只定义top坐标，水平保持居中
            shade: ['0.3', '#000'],
            maxmin: true, //最大最小化。
            response: {
                statusName: 'code', //数据状态的字段名称，默认：code
                statusCode: 200, //成功的状态码，默认：0
                msgName: 'msg', //状态信息的字段名称，默认：msg
                countName: 'total', //数据总数的字段名称，默认：count
                dataName: 'data' //数据列表的字段名称，默认：data
            },
            content: $('#addUserPage'), // 内容，content可传入的值是灵活多变的，不仅可以传入普通的html内容，还可以指定DOM，更可以随着type的不同而不同。
            btn: ['保存', '取消'], //弹出框里的确认，取 消
            yes: function (index, layero) { //该回调携带两个参数，分别为当前层索引、当前层DOM对象。如：
                $.ajax({
                    type: "POST",
                    dataType: 'json',
                    url: accountBackPath + '/user/insert',
                    data: {
                        "username": $('#usernameAdd').val(),
                        "password": $('#passwordAdd').val(),
                        "realname": $('#realnameAdd').val()
                    },
                    success: function (data) {
                        if (data.code === 200) {
                            layer.msg('添加成功!', {
                                icon: 6
                            });
                            refresh();
                        } else {
                            layer.msg(data.msg, {
                                icon: 5,
                                time: 2000
                            }, function (index) {
                                layer.close(index);
                            });
                        }
                    }
                });
                layer.closeAll();
            }
        });
    });

    /**
     * 初始化选中的角色
     * @param userId
     */
    function initSelectedRole(userId){
        $.ajax({
            type: "get",
            dataType: 'json',
            url: accountBackPath + '/userRole/getRoleByUser',
            data:{
                'userId' :userId
            },
            success: function (result) {
                if (result.data != null && result.data.length > 0){
                    $("#roleTitleAdd").val(result.data[0].roleId);
                    layui.form.render('select');
                }
            }
        });
    }

    //监听工具条事件
    table.on('tool(dateTable)', function (obj) {
        var data = obj.data, //获得当前行数据
            layEvent = obj.event; //获得 lay-event 对应的值
        //设置当前显示的内容
        $('#usernameAdd').val(data.username).attr("aabled", "disabled"); //账户名不可以修改
        $('#userStatusAdd').val(data.userStatus);
        $('#passwordAdd').val(data.password);
        $('#realnameAdd').val(data.realname);
        //必须添加下面这句,才能渲染
        layui.form.render('select');
        //隐藏密码框
        $("#passwordPane").hide();
        role.data = data;
        if (layEvent === 'edit') {
            role.edit(data);
        } else if (layEvent === 'del') {
            role.del(data);
        } else if (layEvent === 'updatePassword') {
            role.updatePassword(data);
        } else if (layEvent === 'assignRole'){
            role.assignRole(data);
        }
    });

    // 角色对象的相关属性及操作
    let role = {
        data: {},
        // 删除
        del: function(data){
            layer.confirm('确定要删除?删除后数据不可恢复！', {
                icon: 3,
                title: '提示'
            }, function (index) {
                $.ajax({
                    type: "delete",
                    dataType: 'json',
                    url: accountBackPath + '/user/delete/' + data.id,
                    success: function (data) {
                        if (data.code === 200) {
                            layer.msg('删除成功!!!', {
                                icon: 6
                            });
                            refresh();
                        }
                    },
                });
                layer.close(index);
            });
        },
        // 分配角色
        assignRole: function (data) {
            layer.open({
                type: 1,
                skin: 'layui-layer-lan',
                title: '分配角色',
                area: ['800px', '600px'],
                offset: '100px', //只定义top坐标，水平保持居中
                shade: ['0.3', '#000'],
                maxmin: true,
                content: $('#assignRolePage'),
                btn: ['确认', '取消'],
                success:function(index, layero){
                    initSelectedRole(data.id);
                },
                yes: function (index, layero) {
                    let roleId = $("#roleTitleAdd option:selected").val();
                    if (roleId === "") return;
                    $.ajax({
                        type: "POST",
                        dataType: 'json',
                        url: accountBackPath + '/userRole/assignRole',
                        data: {
                            'userId': data.id,
                            'roleId': roleId
                        },
                        success: function (data) {
                            layer.msg('分配角色成功!!!', {icon: 6});
                        }
                    });
                    layer.closeAll();
                }

            });
        },
        // 编辑
        edit: function (data) {
            layer.open({
                type: 1,
                skin: 'layui-layer-lan',
                title: '编辑用户',
                area: ['800px', '600px'],
                offset: '100px', //只定义top坐标，水平保持居中
                shade: ['0.3', '#000'],
                maxmin: true,
                content: $('#addUserPage'),
                btn: ['确认', '取消'],
                yes: function (index, layero) {
                    $.ajax({
                        type: "POST",
                        dataType: 'json',
                        url: accountBackPath + '/user/update',
                        data: {
                            'id': data.id,
                            'realname': $('#realnameAdd').val(),
                            'username': $('#usernameAdd').val(),
                            "userStatus": $('#userStatusAdd').val()
                        },
                        success: function (data) {
                            if (data.code === 200) {
                                layer.msg('修改成功!', {icon: 6});
                                refresh();
                            } else {
                                layer.msg(data.msg, {
                                    icon: 5,
                                    time: 2000
                                }, function (index) {
                                    layer.close(
                                        index);
                                });
                            }
                        }
                    });
                    layer.closeAll();
                }
            });
        },
        // 修改密码
        updatePassword: function (data) {
            $("#passwordPane").show();
            layer.open({
                type: 1,
                skin: 'layui-layer-lan',
                title: '修改密码',
                area: ['800px', '400px'],
                offset: '100px', //只定义top坐标，水平保持居中
                shade: ['0.3', '#000'],
                maxmin: true,
                content: $('#updatePasswordPage'),
                btn: ['确认', '取消'],
                yes: function (index, layero) {
                    //获取修改后的值
                    let newPassword = $('#newPassword').val();
                    if (newPassword !== $('#confirmPassword').val()) {
                        alert("两次输入密码不一致，请重试！");
                        return;
                    }
                    $.ajax({
                        type: "POST",
                        dataType: 'json',
                        url: accountBackPath + '/user/update/password',
                        data: {
                            'id': data.id,
                            'oldPassword': $('#originalPassword').val(),
                            "newPassword": newPassword
                        },
                        success: function (data) {
                            if (data.code === 200) {
                                layer.msg('修改成功!', {
                                    icon: 6
                                });
                                refresh();
                            } else {
                                layer.msg(data.msg, {
                                    icon: 5,
                                    time: 2000
                                }, function (index) {
                                    layer.close(index);
                                });
                            }
                        }
                    });
                    layer.closeAll();
                }
            });
        }
    }

});
