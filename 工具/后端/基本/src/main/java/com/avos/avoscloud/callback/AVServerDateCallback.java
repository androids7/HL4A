package com.avos.avoscloud.callback;

import com.avos.avoscloud.AVCallback;
import com.avos.avoscloud.后端错误;

import java.util.Date;

/**
 * Created by lbt05 on 8/7/15.
 */
public abstract class AVServerDateCallback extends AVCallback<Date> {

  public abstract void done(Date serverDate, 后端错误 e);

  @Override
  protected void internalDone0(Date date, 后端错误 avException) {
    this.done(date, avException);
  }
}
