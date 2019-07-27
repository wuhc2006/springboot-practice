/**
 * Created by Administrator on 2017/8/25.
 */
// 配置扩展方法路径
layui.config({
    base: '../../frame/static/js/' // 模块目录
}).extend({ // 模块别名
    vip_nav: 'vip_nav',
    vip_tab: 'vip_tab',
    vip_table: 'vip_table'
});

//var basePath = 'http://127.0.0.1/sk/admin/um';
//供应商
var supplierFrontPath = 'http://localhost:' + window.location.port//7001
var supplierBackPath = 'http://localhost:' + window.location.port//7002
//订单
var orderFrontPath = 'http://localhost:' + window.location.port//9001
var orderBackPath = 'http://localhost:' + window.location.port//9002
//账户管理
var accountFrontPath = 'http://localhost:' + window.location.port//8001
var accountBackPath = "http://localhost:"+ window.location.port//8002
var basePath = 'http://localhost:' + window.location.port

/* isLogin();
//判断是否登录，如果没有登录，直接跳转到登录界面
function isLogin() {
    $.ajax({
        type: 'get',
        url: basePath + '/isLogin',
        data: {
            page: 1,
            pageSize: 100,
            name: ""
        },
        dataType: 'json',
        success: function (result) {
            if (result.code != 200) {
                layer.msg("请先登录!", {
                    icon: 5,
                    time: 2000
                }, function (index) {
                    layer.close(index);
                    window.location.href = 'login.html?';
                });
            }
        },
        error: function () {
            alert("请重新登录！");
        }
    })
} */
