// layui方法
layui.use(['tree', 'table', 'vip_table', 'layer'], function () {

    // 操作对象
    var table = layui.table,
        vipTable = layui.vip_table,
        layer = layui.layer,
        tree = layui.tree;
        $ = layui.jquery;

    // 初始化表格数据
    load();

    //查询数据
    function load() {
        let tableIns = table.render({
            elem: '#dateTable',//指定原始表格元素选择器（推荐id选择器）
            response: {
                statusName: 'code',//数据状态的字段名称，默认：code
                statusCode: 200, //成功的状态码，默认：0
                msgName: 'msg', //状态信息的字段名称，默认：msg
                countName: 'total', //数据总数的字段名称，默认：count
                dataName: 'data' //数据列表的字段名称，默认：data
            },
            request: {
                pageName: 'page', //页码的参数名称，默认：page
                limitName: 'pageSize' //每页数据量的参数名，默认：limit
            },
            cols: [
                [ //标题栏
                    {type: 'numbers', title: '序号', width: 60, align: 'center'},
                    {field: 'name', title: '角色代号', width: 200, align: 'center'},
                    {field: 'title', title: '角色名称', width: 200, align: 'center'},
                    {field: 'type', title: '角色类型', width: 200, align: 'center'},
                    {field: 'status', title: '状态', width: 200, align: 'center'},
                    {field: 'addTime', title: '创建时间', width: 300, align: 'center'},
                    {fixed: 'right', title: '操作', width: 450, align: 'center', toolbar: '#barOption'} //这里的toolbar值是模板元素的选择器
                ]
            ],
            id: 'dataCheck',
            url: basePath + '/role/list',
            method: 'get',
            page: true,
            limits: [30, 60, 90, 150, 300],
            limit: 30, //默认采用30
            loading: false,
            where: {
                name: $.trim($("#roleName").val()),
                type: $.trim($("#roleType").val()),
                title: $.trim($("#roleTitle").val())
            },
            done: function (res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                //角色类型转换
                $("[data-field='type']").children().each(function () {
                    if ($(this).text() === '0') {
                        $(this).text("普通用户");
                    } else if ($(this).text() === '1') {
                        $(this).text("管理员");
                    }
                });
                //状态类型转换
                $("[data-field='status']").children().each(function () {
                    if ($(this).text() === '0') {
                        $(this).text("停用");
                    } else if ($(this).text() === '1') {
                        $(this).text("启用");
                    }
                });
            }
        });
    }

    function loadRole() {
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
                    if (role[i].type === 1) {
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

    //查询按钮事件
    $('#load').click(function (e) {
        load();
    });

    /**
     * 清空控件数据
     */
    function clearContents() {
        $('#roleNameAdd').val("");
        $('#roleTitleAdd').val("");
        $('#roleTypeAdd').val("");
        $('#roleStatusAdd').val("");
    }

    /**
     * 新增
     */
    $('#add').click(function (e) {
        clearContents();
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
                $.ajax({
                    type: "post",
                    dataType: 'json',
                    url: basePath + '/role/insertOne',
                    data: {
                        "name": $('#roleNameAdd').val(),
                        "title": $('#roleTitleAdd').val(),
                        "type": parseInt($("#roleTypeAdd").val()),
                        "status": parseInt($("#roleStatusAdd").val())
                    },
                    success: function (data) {
                        if (data.code === 200) {
                            layer.msg('添加成功!', {icon: 6});
                            load();
                        }
                    }
                });
                layer.closeAll();
            }
        });
    });

    //监听工具条按钮事件
    table.on('tool(dateTable)', function (obj) {
        let data = obj.data, //获得当前行数据
            layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'edit') {
            edit(data);
        } else if (layEvent === 'del') {
            del(data);
        } else if (layEvent === 'setPermission') {
            setPermission();
        }

    });

    /**
     * 初始化控件数据
     *
     * @param data
     */
    function initContents(data) {
        $('#roleNameAdd').val(data.name);
        $('#roleTitleAdd').val(data.title);
        $('#roleTypeAdd').val(data.type);
        $('#roleStatusAdd').val(data.status);
    }

    /**
     * 编辑
     *
     * @param data
     */
    function edit(data) {
        layer.open({
            type: 1,
            skin: 'layui-layer-lan',
            title: '修改角色',
            area: ['600px', '420px'],
            offset: '100px', //只定义top坐标，水平保持居中
            shade: ['0.3', '#000'],
            maxmin: true,
            content: $('#addRolePage'),
            btn: ['确认', '取消'],
            success: function (index, layero) {
                initContents(data);
                layui.form.render('select');
            },
            yes: function (index, layero) {
                //获取修改后的值
                $.ajax({
                    type: "POST",
                    dataType: 'json',
                    url: basePath + '/role/update',
                    data: {
                        'roleId': data.id,
                        "name": $('#roleNameAdd').val(),
                        'title': $('#roleTitleAdd').val(),
                        "type": $('#roleTypeAdd').val(),
                        "status": $('#roleStatusAdd').val()
                    },
                    success: function (data) {
                        if (data.code === 200) {
                            layer.msg('修改成功!', {icon: 6});
                            load();
                        }
                    }
                });
                layer.closeAll();
            }
        });
    }

    /**
     * 删除
     *
     * @param data
     */
    function del(data) {
        layer.confirm('确定要删除?删除后数据不可恢复！', {
            icon: 3,
            title: '提示'
        }, function (index) {
            $.ajax({
                type: "POST",
                dataType: 'json',
                url: basePath + '/role/delete/' + data.id,
                success: function (data) {
                    if (data.code === 200) {
                        layer.msg('删除成功!!!', {icon: 6});
                        load();
                    }
                },
            });
            layer.close(index);
        });
    }

    /**
     * 菜单设置
     * @type {{data: {simpleData: {enable: boolean}}, check: {enable: boolean}}}
     */
    let settings = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    }

    /**
     * 初始化菜单
     */
    function initMenu() {
        $.ajax({
            type: 'get',
            url: basePath + '/menu/list',
            dataTable: 'json',
            success: function (result) {
                if (result.code === 200) {
                    let nodes = [];
                    for (let i = 0; i < result.data.length; i++) {
                        let data = {};
                        data.id = result.data[i].menuId;
                        data.pId = result.data[i].parentId;
                        data.name = result.data[i].name;
                        nodes[i] = data;
                    }
                    jQuery.fn.zTree.init($("#menuTree"), settings, nodes);// 为了避免与layui的$冲突，此处用jQuery，不要用$
                }
            }
        })
    }

    /**
     * 设置权限
     */
    function setPermission() {
        layer.open({
            type: 1,
            skin: 'layui-layer-lan',
            title: '设置权限',
            area: ['800px', '600px'],
            offset: '100px', //只定义top坐标，水平保持居中
            shade: ['0.3', '#000'],
            maxmin: true,
            content: $('#selectMenuPage'),
            btn: ['确认', '取消'],
            success: function (index, layero) {
                initMenu();
            },
            yes: function (index, layero) {
                layer.closeAll();
            }

        });
    }

    // 获取选中行
    table.on('checkbox(dataCheck)', function (obj) {
    });

});
