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
	        var menuName = $.trim($("#menuNameInput").val());
	        var tableIns = table.render({
	            elem: '#dataTable' //指定原始表格元素选择器（推荐id选择器）
	                ,
	            response: {
	                statusName: 'code' //数据状态的字段名称，默认：code
	                    ,
	                statusCode: 200 //成功的状态码，默认：0
	                    ,
	                msgName: 'msg' //状态信息的字段名称，默认：msg
	                    ,
	                countName: 'total' //数据总数的字段名称，默认：count
	                    ,
	                dataName: 'data' //数据列表的字段名称，默认：data
	            },
	            method: 'get',
	            request: { //分页   设置分页名称
	                pageName: 'page' //页码的参数名称，默认：page
	                    ,
	                limitName: 'pageSize' //每页数据量的参数名，默认：limit
	            },
	            page: true,
	            limits: [10, 20, 30, 60],
	            limit: 10 //默认采用30
	                ,
	            loading: false,
	            where: {
	                name: menuName
	            },
	            url: '/menu/list',
	            cols: [
	                [ //标题栏
	                    {
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
	                        field: 'parentName',
	                        title: '联系人',
	                        width: 120,
	                        align: 'center'
	                    }, {
	                        field: 'path',
	                        title: '联系方式',
	                        width: 100,
	                        align: 'center'
	                    }, {
	                        field: 'createTime',
	                        title: '供应商地址',
	                        width: 120,
	                        sort: true,
	                        align: 'center'
	                    },{
	                        field: 'createTime',
	                        title: '配送费',
	                        width: 120,
	                        sort: true,
	                        align: 'center'
	                    },{
	                        field: 'createTime',
	                        title: '起送金额',
	                        width: 120,
	                        sort: true,
	                        align: 'center'
	                    },{
	                        field: 'createTime',
	                        title: '配送服务',
	                        width: 120,
	                        sort: true,
	                        align: 'center'
	                    },{
	                        field: 'createTime',
	                        title: '配送范围',
	                        width: 120,
	                        sort: true,
	                        align: 'center'
	                    },{
	                        field: 'createTime',
	                        title: '状态',
	                        width: 120,
	                        sort: true,
	                        align: 'center'
	                    },{
	                        field: 'createTime',
	                        title: '提交时间',
	                        width: 120,
	                        sort: true,
	                        align: 'center'
	                    }, {
	                        fixed: 'right',
	                        title: '操作',
	                        align: 'center',
	                        toolbar: '#barOption'
	                    } //这里的toolbar值是模板元素的选择器
	                ]
	            ],
	            id: 'dataCheck',
	            done: function (res, curr, count) {
	                //如果是异步请求数据方式，res即为你接口返回的信息。
	                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
	                console.log(res);
	                //得到当前页码
	                console.log(curr);
	                //得到数据总量
	                console.log(count);
	            }
	        });
	    }

	    // 初始化表格数据
	    queryDynamic();
	    //查询按钮事件
	    $('#query').click(function (e) {
	        queryDynamic();
	    });

	    // 获取选中行
	    table.on('checkbox(dataCheck)', function (obj) {
	        console.log(obj.checked); //当前是否选中状态
	        console.log(obj.data); //选中行的相关数据
	        console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
	    });

	    // you code ...
	    //新增
	    $('#add').click(function (e) {
	        //先清空
	        $('#menuNameAdd').val("");
	        $('#parentMenuNameAdd').val("");
	        //从后台加载数据到控件
	        loadData();
	        //打开新增菜单对话框
	        layer.open({
	            type: 1,
	            skin: 'layui-layer-lan' //样式类名
	                ,
	            title: '新增菜单',
	            area: ['800px', '400px'] // 宽高
	                ,
	            offset: '100px' //只定义top坐标，水平保持居中
	                ,
	            shade: ['0.3', '#000'],
	            maxmin: true //最大最小化。
	                ,
	            content: $('#addMenuPage') // 内容，content可传入的值是灵活多变的，不仅可以传入普通的html内容，还可以指定DOM，更可以随着type的不同而不同。
	                ,
	            btn: ['确认', '取消'] //弹出框里的确认，取消
	                ,
	            success: function (index, layero) {
	                $.ajax({
	                    type: 'get',
	                    url: accountBackPath + '/menu/list',
	                    data: {
	                        page: 1,
	                        pageSize: 100,
	                        name: ""
	                    },
	                    dataTable: 'json',
	                    success: function (result) {
	                        var menu = result.data;
	                        for (var i = 0; i < menu.length; i++) {
	                            $("#parentNameAdd").append("<option value='" +
	                                menu[i].menuId + "'>" + menu[i].name +
	                                "</option>");
	                        }
	                        layui.form.render('select');
	                    }
	                })
	            },
	            yes: function (index, layero) { //该回调携带两个参数，分别为当前层索引、当前层DOM对象。如：
	                $.ajax({
	                    type: "POST",
	                    dataType: 'json',
	                    url: accountBackPath + '/menu/addMenu',
	                    data: {
	                        "name": $('#menuNameAdd').val(),
	                        "parentName": $("#parentNameAdd").find("option:selected").text(),
	                        "pId": parseInt($("#parentNameAdd").val()),
	                        "path": $('#pathAdd').val()
	                    },
	                    success: function (data) {
	                        debugger;
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
	    //从后台加载菜单数据
	    function loadData() {
	        $.ajax({
	            type: 'get',
	            url: accountBackPath + '/menu/list',
	            data: {
	                page: 1,
	                pageSize: 100,
	                name: ""
	            },
	            dataTable: 'json',
	            success: function (result) {
	                var menuInfo = result.data;
	                for (var i = 0; i < menuInfo.length; i++) {
	                    $("#parentName").append("<option value='" + menuInfo[i].menuId + "'>" +
	                        menuInfo[i].name + "</option>");
	                }
	            }
	        })
	    }
	    //监听工具条
	    //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	    table.on('tool(dataTable)', function (obj) {
	        var data = obj.data //获得当前行数据
	            ,
	            layEvent = obj.event; //获得 lay-event 对应的值
	        //修改
	        if (layEvent === 'edit') {

	            layer.open({
	                type: 1,
	                skin: 'layui-layer-lan',
	                title: '修改菜单',
	                area: ['800px', '400px'],
	                offset: '100px' //只定义top坐标，水平保持居中
	                    ,
	                shade: ['0.3', '#000'],
	                maxmin: true,
	                content: $('#addMenuPage'),
	                btn: ['确认', '取消'],
	                success: function () {
	                    //设置当前的值
	                    $('#menuNameAdd').val(data.name);
	                    $('#pathAdd').val(data.path);
	                    $.ajax({
	                        type: 'get',
	                        url: accountBackPath + '/menu/list',
	                        data: {
	                            page: 1,
	                            pageSize: 100,
	                            name: ""
	                        },
	                        dataTable: 'json',
	                        success: function (result) {
	                            var menu = result.data;
	                            for (var i = 0; i < menu.length; i++) {
									var option = $("<option>").val(menu[i].menuId).text(menu[i].name);
	                                $("#parentNameAdd").append(option);
	                            }
								
	                            layui.form.render('select');
	                        }
	                    })
						//设置选中的父菜单
						if(data.parentId!=null){
							$("#parentNameAdd").val(data.parentId);
						}
	                },
	                yes: function (index, layero) {
	                    //获取修改后的值
	                    var name = $('#menuNameAdd').val();
	                    //获取修改后的parentId
	                    var parentId = parseInt($('#parentNameAdd').val());
	                    var parentName = $("#parentNameAdd").find("option:selected").text();
	                    var path = $('#pathAdd').val();
	                    $.ajax({
	                        type: "POST",
	                        dataType: 'json',
	                        url: accountBackPath + '/menu/updateMenu',
	                        data: {
	                            'id': data.menuId,
	                            "pId": parentId,
	                            "name": name,
	                            "parentName": parentName,
	                            "path": path
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
	                    url: accountBackPath + '/menu/deleteMenu',
	                    data: {
	                        'id': data.menuId
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
	        } else if (layEvent == '') {

	        }
	    });
	});
