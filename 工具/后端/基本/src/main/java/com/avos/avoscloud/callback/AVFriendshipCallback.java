package com.avos.avoscloud.callback;

import com.avos.avoscloud.AVCallback;
import com.avos.avoscloud.后端错误;
import com.avos.avoscloud.AVFriendship;

import java.util.List;

/**
 * AVFriendshipQuery的getInBackground中得回调类
 */
public abstract class AVFriendshipCallback extends AVCallback<AVFriendship> {

  public abstract void done(AVFriendship friendship, 后端错误 e);

  @Override
  protected final void internalDone0(AVFriendship returnValue, 后端错误 e) {
    done(returnValue, e);
  }
}
