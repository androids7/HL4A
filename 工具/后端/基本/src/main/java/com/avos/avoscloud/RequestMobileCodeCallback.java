package com.avos.avoscloud;

/**
 * <p>
 * RequestMobilePhoneVerify 用来验证用户的手机号码
 * </p>
 * <p>
 * 　调用的范例如下
 * </p>
 * 
 * <pre>
 * AVUser.requestMobilePhoneVerifyInBackgroud(&quot;12345678901&quot;,
 *     new RequestMobileCodeCallback() {
 *       public void done(AVException e) {
 *         if (e == null) {
 *           requestedSuccessfully();
 *         } else {
 *           requestDidNotSucceed();
 *         }
 *       }
 *     });
 * </pre>
 */
public abstract class RequestMobileCodeCallback extends AVCallback<Void> {

  public abstract void done(后端错误 e);

  @Override
  protected final void internalDone0(Void t, 后端错误 avException) {
    this.done(avException);
  }
}
