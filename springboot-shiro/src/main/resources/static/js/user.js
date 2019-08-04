// layui方法
layui.use(['tree', 'table', 'vip_table', 'layer'], function () {
    // 操作对象
    var table = layui.table,
        vipTable = layui.vip_table,
        layer = layui.layer,
        form = layui.form,
        $ = layui.jquery;

    function dateToStr(date) {
        var time = new Date(date.time);
        var y = time.getFullYear();
        var M = time.getMonth() + 1;
        M = M < 10 ? ("0" + M) : M;
        var d = time.getDate();
        d = d < 10 ? ("0" + d) : d;
        var h = time.getHours();
        h = h < 10 ? ("0" + h) : h;
        var m = time.getMinutes();
        m = m < 10 ? ("0" + m) : m;
        var s = time.getSeconds();
        s = s < 10 ? ("0" + s) : s;
        var str = y + "-" + M + "-" + d + " " + h + ":" + m + ":" + s;
        console.log(str);
        return str;
    }

    function queryDynamic() {
        var username = $.trim($("#username").val());
        var realname = $.trim($("#realname").val());
        // 表格渲染
        var tableIns = table.render({
            elem: '#dateTable' //指定原始表格元素选择器（推荐id选择器）
            ,
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
                    if ($(this).text() == '1') {
                        $(this).text("正常");
                    } else if ($(this).text() == '2') {
                        $(this).text("已锁");
                    } else if ($(this).text() == '3') {
                        $(this).text("屏蔽");
                    } else if ($(this).text() == '9') {
                        $(this).text("删除");
                    }
                });
            }
        })

    };

    // 获取选中行
    table.on('checkbox(dataCheck)', function (obj) {
        console.log(obj.checked); //当前是否选中状态
        console.log(obj.data); //选中行的相关数据
        console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
    });

    //封装ajax，获取后台数据
    function getDataFromAjax(type, url, paras) {
        $.ajax({
            type: type,
            url: url,
            data: paras,
            dataTable: 'json',
            success: function (result) {
                return result;
            }
        })
    }

    // you code ...
    queryDynamic();
    //初始化控件数据
    //loadData();
    //从后台加载数据
    function loadData() {
        $.ajax({
            type: 'post',
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
                    $("#roleType").append("<option value='" + role[i].id + "'>" + role[i].type +
                        "</option>");
                    $("#roleTypeAdd").append("<option value='" + role[i].id + "'>" + role[i].type +
                        "</option>");
                    layui.form.render('select');
                }
            }
        })
    }

    //查询按钮事件
    $('#query').click(function (e) {
        queryDynamic();
    });

    //新增平台用户
    $('#add').click(function (e) {
        //清空重置
        $('#usernameAdd').removeAttr("disabled");
        $('#realnameAdd').val("");
        $('#usernameAdd').val("");
        $("#roleTypeAdd").val("-1");
        $("#roleTitleAdd").empty();
        $('#passwordAdd').val("");
        //显示密码框
        $("#passwordPane").show();
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
                var name = $('#usernameAdd').val();
                var password = $('#passwordAdd').val();
                let realname = $('#realnameAdd').val();
                $.ajax({
                    type: "POST",
                    dataType: 'json',
                    url: accountBackPath + '/user/insert',
                    data: {
                        "username": name,
                        "password": password,
                        "realname": realname
                    },
                    success: function (data) {
                        if (data.code == 200) {
                            layer.msg('添加成功!', {
                                icon: 6
                            });
                            queryDynamic();
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
    //监听角色变更选择器
    form.on('select(roleTypeAdd)', function (data) {
        var selectedType = parseInt(data.value);
        $.ajax({
            type: "post",
            dataType: 'json',
            url: accountBackPath + '/role/list',
            data: {
                page: 1,
                pageSize: 100,
                "name": "",
                "title": "",
                "type": selectedType
            },
            dataTable: 'json',
            success: function (result) {
                var role = result.data;
                //先清除原有的
                $('#roleTitleAdd').empty();
                for (var i = 0; i < role.length; i++) {
                    $("#roleTitleAdd").append("<option value='" + role[i].id + "'>" + role[
                        i].title + "</option>");
                    layui.form.render('select');
                }
            }
        });
    });
    //监听工具条事件
    table.on('tool(dateTable)', function (obj) {
        var data = obj.data, //获得当前行数据
            layEvent = obj.event; //获得 lay-event 对应的值
        //设置当前显示的内容
        $('#usernameAdd').val(data.username).attr("disabled", "disabled"); //账户名不可以修改
        $('#roleTypeAdd').val(data.roleType);
        $('#userStatusAdd').val(data.userStatus);
        $('#passwordAdd').val(data.password);
        $('#realnameAdd').val(data.realname);
        //必须添加下面这句,才能渲染
        layui.form.render('select');
        //隐藏密码框
        $("#passwordPane").hide();
        if (layEvent === 'edit') {
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
                    //获取修改后的值
                    var name = $('#usernameAdd').val();
                    var realname = $('#realnameAdd').val();
                    var title = $('#roleTitleAdd').find("option:selected").text();
                    var type = $('#roleTypeAdd').val();
                    var status = $('#userStatusAdd').val();
                    var password = $('#passwordAdd').val();
                    var roleId;
                    $.ajax({
                        type: "get",
                        dataType: 'json',
                        url: accountBackPath + '/role/list',
                        data: {
                            page: 1,
                            pageSize: 100,
                            "name": "",
                            "title": title,
                            "type": type
                        },
                        success: function (result) {
                            var role = result.data;
                            //获取第一个的roleid
                            roleId = role[0].id;

                            //提交修改
                            $.ajax({
                                type: "POST",
                                dataType: 'json',
                                url: accountBackPath + '/user/update',
                                data: {
                                    'id': data.id,
                                    'roleId': roleId,
                                    'realname': realname,
                                    'username': name,
                                    'password': password,
                                    "roleType": type,
                                    'roleTitle': title,
                                    "userStatus": status
                                },
                                success: function (data) {
                                    if (data.code == 200) {
                                        layer.msg('修改成功!', {
                                            icon: 6
                                        });
                                        queryDynamic();
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
                    type: "delete",
                    dataType: 'json',
                    url: accountBackPath + '/user/delete/' + data.id,
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
        } else if (layEvent == 'updatePassword') {
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
                    var originalPassword = $('#originalPassword').val();
                    var newPassword = $('#newPassword').val();
                    var confirmPassword = $('#confirmPassword').val();
                    if (newPassword != confirmPassword) {
                        alert("两次输入密码不一致，请重试！");
                        return;
                    }
                    $.ajax({
                        type: "POST",
                        dataType: 'json',
                        url: accountBackPath + '/user/updatePassword',
                        data: {
                            'userId': data.userId,
                            'password': originalPassword,
                            "newPassword": newPassword
                        },
                        success: function (data) {
                            if (data.code == 200) {
                                layer.msg('修改成功!', {
                                    icon: 6
                                });
                                queryDynamic();
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
    });
});
