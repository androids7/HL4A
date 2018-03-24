package com.avos.avoscloud;

import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA. User: zhuzeng Date: 4/10/13 Time: 9:54 AM To change this template use
 * File | Settings | File Templates.
 */
public class AVErrorUtils {
  public static final int CIRCLE_REFERENCE = 100001;
  public static final int MISSING_OBJECTID = 104;

  // {"error":"The user cannot be altered by a client without the session.","code":206}
  public static 后端错误 createException(String content) {
    try {
      JSONObject object = new JSONObject(content);
      String errorMessage = object.getString("error");
      int code = object.getInt("code");
      return new 后端错误(code, errorMessage);
    } catch (Exception exception) {
      return new 后端错误(后端错误.UNKNOWN, content);
    }
  }

  public static 后端错误 createException(Throwable t, String content) {
    if (content != null) {
      return createException(content);
    } else if (t != null) {
      if (t instanceof 后端错误) {
        return (后端错误) t;
      }
      return new 后端错误(t);
    } else {
      return new 后端错误(后端错误.UNKNOWN, "unknown reason");
    }

  }

  static int errorCode(String content) {
    int code = 0;
    try {
      JSONObject object = new JSONObject(content);
      code = object.getInt("code");
      return code;
    } catch (Exception exception) {}
    return code;
  }

  static public 后端错误 createException(int code, String content) {
    return new 后端错误(code, content);
  }

  static public 后端错误 invalidObjectIdException() {
    return AVErrorUtils.createException(后端错误.MISSING_OBJECT_ID, "Invalid object id.");
  }

  static public 后端错误 invalidQueryException() {
    return AVErrorUtils.createException(后端错误.INVALID_QUERY, "Invalid query.");
  }

  static public 后端错误 sessionMissingException() {
    return AVErrorUtils.createException(后端错误.SESSION_MISSING,
        "No valid session token, make sure signUp or login has been called.");
  }

  static public 后端错误 fileDownloadInConsistentFailureException() {
    return AVErrorUtils.createException(后端错误.FILE_DOWNLOAD_INCONSISTENT_FAILURE,
        "Downloaded file is inconsistent with original file");
  }


  // ================================================================================
  // Some Special Exception
  // ================================================================================

  static 后端错误 circleException() {
    return new 后端错误(CIRCLE_REFERENCE, "Found a circular dependency when saving.");
  }

}
