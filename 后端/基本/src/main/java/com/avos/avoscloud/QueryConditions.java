package com.avos.avoscloud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lbt05 on 3/31/15.
 */
public class QueryConditions {
  Map<String, List<QueryOperation>> where;
  private List<String> include;
  private Set<String> selectedKeys;
  private int limit;
  private boolean trace;
  private int skip = -1;
  private String order;
  private Map<String, String> parameters;

  public QueryConditions() {
    where = new HashMap<String, List<QueryOperation>>();
    include = new LinkedList<String>();
    parameters = new HashMap<String, String>();
  }

  /**
   * clone a same object.
   *
   * @return a new QueryConditions object.
   */
  public QueryConditions clone() {
    QueryConditions condition = new QueryConditions();
    condition.where.putAll(this.where);
    condition.include.addAll(this.include);
    condition.parameters.putAll(this.parameters);
    condition.selectKeys(this.selectedKeys);
    condition.setLimit(this.limit);
    condition.setTrace(this.trace);
    condition.setSkip(this.skip);
    condition.setOrder(this.order);
    return condition;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public int getSkip() {
    return skip;
  }

  public void setSkip(int skip) {
    this.skip = skip;
  }

  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  public List<String> getInclude() {
    return include;
  }

  public void setInclude(List<String> include) {
    this.include = include;
  }

  public Set<String> getSelectedKeys() {
    return selectedKeys;
  }

  public void setSelectedKeys(Set<String> selectedKeys) {
    this.selectedKeys = selectedKeys;
  }

  public Map<String, List<QueryOperation>> getWhere() {
    return where;
  }

  public void setWhere(Map<String, List<QueryOperation>> where) {
    this.where = where;
  }

  public Map<String, String> getParameters() {
    return parameters;
  }

  public void setParameters(Map<String, String> parameters) {
    this.parameters = parameters;
  }

  public boolean isTrace() {
    return trace;
  }

  public void setTrace(boolean trace) {
    this.trace = trace;
  }

  public void addAscendingOrder(String key) {
    if (AVUtils.isBlankString(order)) {
      this.orderByAscending(key);
      return;
    }

    order = String.format("%s,%s", order, key);
  }

  public void orderByAscending(String key) {
    order = String.format("%s", key);
  }

  public void addDescendingOrder(String key) {
    if (AVUtils.isBlankString(order)) {
      orderByDescending(key);
      return;
    }

    order = String.format("%s,-%s", order, key);
  }

  public void orderByDescending(String key) {
    order = String.format("-%s", key);
  }

  public void include(String key) {
    include.add(key);
  }

  public void selectKeys(Collection<String> keys) {
    if (selectedKeys == null) {
      selectedKeys = new HashSet<String>();
    }
    if (null != keys) {
      selectedKeys.addAll(keys);
    }
  }

  public Map<String, Object> compileWhereOperationMap() {
    Map<String, Object> result = new HashMap<String, Object>();
    for (Map.Entry<String, List<QueryOperation>> entry : where.entrySet()) {
      List<QueryOperation> ops = entry.getValue();
      final String key = entry.getKey();
      if (key.equals(QueryOperation.OR_OP)) {
        List<Object> opList = new ArrayList<Object>();
        for (QueryOperation op : ops) {
          opList.add(op.toResult());
        }
        List<Object> existsOr = (List<Object>) result.get(QueryOperation.OR_OP);
        if (existsOr != null) {
          existsOr.addAll(opList);
        } else {
          result.put(QueryOperation.OR_OP, opList);
        }
      } else if (key.equals(QueryOperation.AND_OP)) {
        List<Object> opList = new ArrayList<Object>();
        for (QueryOperation op : ops) {
          opList.add(op.getValue());
        }
        List<Object> existsAnd = (List<Object>) result.get(QueryOperation.AND_OP);
        if (existsAnd != null) {
          existsAnd.addAll(opList);
        } else {
          result.put(QueryOperation.AND_OP, opList);
        }
      } else {
        switch (ops.size()) {
          case 0:
            break;
          case 1:
            Iterator<QueryOperation> iterator = ops.iterator();
            while (iterator.hasNext()) {
              QueryOperation op = iterator.next();
              result.put(key, op.toResult());
            }
            break;
          default:
            List<Object> opList = new ArrayList<Object>();
            Map<String, Object> opMap = new HashMap<String, Object>();
            boolean hasEqual = false;
            for (QueryOperation op : ops) {
              opList.add(op.toResult(key));
              if (QueryOperation.EQUAL_OP.equals(op.op)) {
                hasEqual = true;
              }
              if (!hasEqual) {
                opMap.putAll((Map) op.toResult());
              }
            }
            if (hasEqual) {
              List<Object> existsAnd = (List<Object>) result.get("$and");
              if (existsAnd != null) {
                existsAnd.addAll(opList);
              } else {
                result.put("$and", opList);
              }
            } else {
              result.put(key, opMap);
            }
            break;
        }
      }

    }
    return result;
  }

  public Map<String, String> assembleParameters() {
    if (where.keySet().size() > 0) {
      parameters.put("where", AVUtils.restfulServerData(compileWhereOperationMap()));
    }
    if (limit > 0) {
      parameters.put("limit", Integer.toString(limit));
    }
    if (skip >= 0) {
      parameters.put("skip", Integer.toString(skip));
    }
    if (!AVUtils.isBlankString(order)) {
      parameters.put("order", order);
    }
    if (!AVUtils.isEmptyList(include)) {
      String value = AVUtils.joinCollection(include, ",");
      parameters.put("include", value);
    }
    if (selectedKeys != null && selectedKeys.size() > 0) {
      String keys = AVUtils.joinCollection(selectedKeys, ",");
      parameters.put("keys", keys);
    }
    return parameters;
  }

  public void addWhereItem(QueryOperation op) {
    List<QueryOperation> ops = where.get(op.key);
    if (ops == null) {
      ops = new LinkedList<QueryOperation>();
      where.put(op.key, ops);
    }
    removeDuplications(op, ops);
    ops.add(op);
  }

  public void addWhereItem(String key, String op, Object value) {
    addWhereItem(new QueryOperation(key, op, value));
  }

  private void removeDuplications(QueryOperation op, List<QueryOperation> ops) {
    Iterator<QueryOperation> it = ops.iterator();
    while (it.hasNext()) {
      QueryOperation o = it.next();
      if (o.sameOp(op)) {
        it.remove();
      }
    }
  }

  public void addOrItems(QueryOperation op) {
    List<QueryOperation> ops = where.get(QueryOperation.OR_OP);
    if (ops == null) {
      ops = new LinkedList<QueryOperation>();
      where.put(QueryOperation.OR_OP, ops);
    }

    Iterator<QueryOperation> it = ops.iterator();
    while (it.hasNext()) {
      QueryOperation o = it.next();
      if (o.equals(op)) {
        it.remove();
      }
    }

    ops.add(op);
  }

  public void addAndItems(QueryConditions conditions) {
    Map<String, Object> queryOperationMap = conditions
        .compileWhereOperationMap();
    QueryOperation op = new QueryOperation("$and", "$and", queryOperationMap);

    List<QueryOperation> ops = where.get(QueryOperation.AND_OP);
    if (ops == null) {
      ops = new LinkedList<QueryOperation>();
      where.put(QueryOperation.AND_OP, ops);
    }

    Iterator<QueryOperation> it = ops.iterator();
    while (it.hasNext()) {
      QueryOperation o = it.next();
      if (o.equals(op)) {
        it.remove();
      }
    }
    ops.add(op);
  }

  public void whereWithinRadians(String key, AVGeoPoint point, double maxDistance) {
    this.whereWithinRadians(key, point, maxDistance, -1);
  }

  public void whereWithinRadians(String key, AVGeoPoint point, double maxDistance,
      double minDistance) {
    Map<String, Object> map = AVUtils.createMap("$nearSphere", AVUtils.mapFromGeoPoint(point));
    if (maxDistance >= 0) {
      map.put("$maxDistanceInRadians", maxDistance);
    }
    if (minDistance >= 0) {
      map.put("$minDistanceInRadians", minDistance);
    }
    addWhereItem(new QueryOperation(key, null, map));
  }

  public void whereGreaterThanOrEqualTo(String key, Object value) {
    addWhereItem(new QueryOperation(key, "$gte", value));
  }

  public void whereContainedIn(String key, Collection<? extends Object> values) {
    this.addWhereItem(key, "$in", values);
  }

  public void whereExists(String key) {
    addWhereItem(key, "$exists", true);
  }

  public void whereGreaterThan(String key, Object value) {
    addWhereItem(key, "$gt", value);
  }

  public void whereLessThan(String key, Object value) {
    addWhereItem(key, "$lt", value);
  }

  public void whereLessThanOrEqualTo(String key, Object value) {
    addWhereItem(key, "$lte", value);
  }

  public void whereMatches(String key, String regex) {
    addWhereItem(key, "$regex", regex);
  }

  public void whereMatches(String key, String regex, String modifiers) {
    addWhereItem(key, "$regex", regex);
    addWhereItem(key, "$options", modifiers);
  }

  public void whereNear(String key, AVGeoPoint point) {
    this.addWhereItem(key, "$nearSphere", AVUtils.mapFromGeoPoint(point));
  }

  public void whereNotContainedIn(String key, Collection<? extends Object> values) {
    this.addWhereItem(key, "$nin", values);
  }

  public void whereNotEqualTo(String key, Object value) {
    this.addWhereItem(key, "$ne", value);
  }

  public void whereEqualTo(String key, Object value) {
    if (value instanceof AVObject) {
      addWhereItem(key, QueryOperation.EQUAL_OP, AVUtils.mapFromPointerObject((AVObject) value));
    } else {
      addWhereItem(key, QueryOperation.EQUAL_OP, value);
    }
  }

  public void whereStartsWith(String key, String prefix) {
    this.whereMatches(key, String.format("^%s.*", prefix));
  }

  public void whereWithinGeoBox(String key, AVGeoPoint southwest, AVGeoPoint northeast) {
    List<Map<String, Object>> box = new LinkedList<Map<String, Object>>();
    box.add(AVUtils.mapFromGeoPoint(southwest));
    box.add(AVUtils.mapFromGeoPoint(northeast));
    Map<String, Object> map = AVUtils.createMap("$box", box);
    this.addWhereItem(key, "$within", map);
  }

  public void whereWithinKilometers(String key, AVGeoPoint point, double maxDistance) {
    this.whereWithinKilometers(key, point, maxDistance, -1);
  }

  public void whereWithinKilometers(String key, AVGeoPoint point, double maxDistance,
      double minDistance) {
    Map<String, Object> map = AVUtils.createMap("$nearSphere", AVUtils.mapFromGeoPoint(point));
    if (maxDistance >= 0) {
      map.put("$maxDistanceInKilometers", maxDistance);
    }
    if (minDistance >= 0) {
      map.put("$minDistanceInKilometers", minDistance);
    }
    addWhereItem(key, null, map);
  }

  public void whereWithinMiles(String key, AVGeoPoint point, double maxDistance) {
    this.whereWithinMiles(key, point, maxDistance, -1);
  }


  public void whereWithinMiles(String key, AVGeoPoint point, double maxDistance,
      double minDistance) {
    Map<String, Object> map = AVUtils.createMap("$nearSphere", AVUtils.mapFromGeoPoint(point));
    if (maxDistance >= 0) {
      map.put("$maxDistanceInMiles", maxDistance);
    }
    if (minDistance >= 0) {
      map.put("$minDistanceInMiles", minDistance);
    }
    addWhereItem(key, null, map);
  }

  public void whereEndsWith(String key, String suffix) {
    this.whereMatches(key, String.format(".*%s$", suffix));
  }

  public void whereContains(String key, String substring) {
    String regex = String.format(".*%s.*", substring);
    whereMatches(key, regex);
  }

  public void whereSizeEqual(String key, int size) {
    this.addWhereItem(key, "$size", size);
  }

  public void whereContainsAll(String key, Collection<?> values) {
    addWhereItem(key, "$all", values);
  }

  public void whereDoesNotExist(String key) {
    addWhereItem(key, "$exists", false);
  }


}
