"严格模式";

var h = Packages.间;
var ha = h.安卓;

var 导入包 = importPackage;
var 导入类 = importClass;
var 扩类 = JavaImporter;

导入类(ha.工具.线程,ha.工具.文件);

导入包(java.lang,java.io,java.util,
android.os,android.content,android.util,
android.view,android.widget);

导入包(h.工具,h.收集,h.接口);
导入包(ha.资源,ha.资源.布局);
导入包(ha.网络,ha.插件,ha.工具,ha.弹窗,ha.组件,ha.绘画);
导入包(ha.视图,ha.视图.扩展,ha.视图.适配器,ha.视图.实现);

// 不手动导入就会报混淆错

var 导入文件 = function(_文件) {
if (文件.是文件(_文件))
return 当前环境.运行文件(_文件);
}

var 自动布局 = function(_类型) {
let _界面 = 当前界面 || null;
if (_界面 == null) {
throw "没有界面上下文 ~";
}
_类型 = _类型 == null ? "基本" : _类型.toString();
let _类 = ha["资源.布局.布局_" + _类型 + "界面"];
if (typeof(_类) != "function") {
throw "没有那样的布局类型 : " + _类型 + " ~";
}
let _布局 = new _类(_界面);
当前界面.打开布局(_布局);
return _布局;
}
