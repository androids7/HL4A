package 间.安卓.后端;

import com.avos.avoscloud.AVOSCloud;
import 间.安卓.工具.环境;
import com.avos.avoscloud.AVCloud;
import com.avos.avoscloud.后端错误;

public class LeanCloud {

    public static void 初始化(String $ID,String $KEY) {
        AVOSCloud.initialize(环境.取应用(),$ID,$KEY);
    }
    
    public static Object 转换错误(后端错误 $错误) {
        return 转换错误($错误.取错误码());
    }
    
    
    public static String 转换错误(int $错误码) {
        switch ($错误码) {
            case 0:return "本地网络错误";
            case 1:return "服务器错误";
            case 100:return "无法连接服务器";
            case 101:return "查询的 Class 或关联的 Pointer 不存在";
            case 103:return "非法 Class 名称";
            case 104:return "缺少 ObjectId";
            case 105:return "无效的 Key名称 (列名)";
            case 106:return "无效的 Pointer 格式";
            case 107:return "无效的 JSON 对象 解析失败";
            case 108:return "API 仅供内部使用";
            case 109:return "无权限执行此操作";
            case 111:return "存储的值不匹配列的类型";
            case 112:return "推送订阅的频道无效";
            case 113:return "Class 中的某个字段设定成必须，保存的对象缺少该字段。";
            case 116:return "要存储的对象超过了大小限制 (16M)";
            case 117:return "无法操作只读字段 更新失败";
            case 118:return "无法进行禁止的操作";
            case 120:return "查询结果无法从缓存中找到";
            case 121:return "JSON key 的名称不能包含 $ 和 .";
            case 122:return "文件名称只能是英文字母、数字和下划线，长度限制 36。";
            case 124:return "请求超时无响应";
            case 125:return "电子邮箱地址无效";
            case 126:return "ID无效 用户不存在";
            case 127:return "手机号码无效";
            case 128:return "操作的Relation数量为0或超过1000";
            case 137:return "唯一字段重复";
            case 139:return "权限组名称只能以英文字母、数字或下划线组成。";
            case 140:return "服务器超过免费额度 (本月)";
            case 141:return "云引擎调用超时";
            case 142:return "云引擎校验错误";
            case 145:return "本设备没有启用支付功能";
            case 150:return "转换数据到图片失败";
            case 154:return "超过应用可用上限";
            case 160:return "账户余额不足";
            
            case 200:return "用户名为空";
            case 201:return "密码为空";
            case 202:return "用户名被占用";
            case 203:return "邮箱被占用";
            case 204:return "没有提供邮箱";
            case 205:return "邮箱地址没有对应的用户";
            case 206:return "无权操作/未登录";
            case 207:return "第三方登录已禁用";
            case 208:return "第三方帐号已经绑定其他用户";
            case 210:return "密码错误";
            case 211:return "用户不存在";
            case 212:return "需要提供手机号码";
            case 213:return "该号码没有对应用户";
            case 214:return "手机号码已经被注册";
            case 215:return "手机号码未验证";
            case 216:return "邮箱地址未验证";
            case 217:return "用户名不能为空";
            case 218:return "密码不能为空";
            case 219:return "失败次数超限 请稍后再试";
            
            case 250:return "第三方账户没有返回用户唯一标示";
            case 251:return "无效的账户连接 (微博)";
            case 252:return "无效的微信授权信息";
            case 300:return "CQL语法错误 (数据库)";
            case 301:return "新增对象失败";
            case 302:return "无效的 GeoPoint 类型";
            case 303:return "插入数据库失败";
            case 304:return "LeanCloud 内部异常";
            case 305:return "条件不满足 删除/更新失败";
            case 401:return "客户端非法";
            case 403:return "无权操作/未登录";
            case 429:return "超过应用的流控限制";
            case 430:return "超过上传文件流控限制";
            case 431:return "超过云引擎 hook 调用流控限制";
            case 502:return "服务器维护中 (LeanCloud)";
            case 503:return "应用临时维护";
            case 511:return "API 暂时不可用";
            case 524:return "与后端应用服务器通讯失败";
            case 529:return "当前 IP 并发超过限制";
            case 600:return "无效的短信签名";
            case 601:return "发送短信过于频繁";
            case 602:return "号码错误/发送失败";
            case 603:return "短信验证码错误/过期";
            case 604:return "找不到自定义的短信模板";
            case 605:return "短信模板未审核/没有设置默认签名";
            case 606:return "渲染短信模板失败 通常是模板语法问题";
            case 700:return "无效的查询或者排序字段";
            // 以下为通信IM模块错误码 未补全
            case 1006:return "连接非正常关闭";
            case 4100:return "应用不存在/实时通信禁用";
        }
        return "未知错误";
    }
    
    
}
