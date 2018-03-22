package com.avos.avoscloud;

/**
 * Created with IntelliJ IDEA. User: dennis (xzhuang@avos.com) Date: 13-7-22 Time: 上午11:37
 */
public final class AVExceptionHolder {

  private static final ThreadLocal<后端错误> local = new ThreadLocal<后端错误>() {
    @Override
    protected 后端错误 initialValue() {
      return null;
    }
  };

  public final static void add(后端错误 e) {
    local.set(e);
  }

  public final static boolean exists() {
    return local.get() != null;
  }

  public final static 后端错误 remove() {
    后端错误 e = local.get();
    local.remove();
    return e;
  }
}
