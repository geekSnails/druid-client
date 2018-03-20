package com.qiyi.search.druidclient.entity;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class LogicalFilter {

    private String type;

    private List<Filter> fields;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Filter> getFields() {
        return fields;
    }

    public void setFields(List<Filter> fields) {
        this.fields = fields;
    }


    public String parse() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"type\":\"").append(type).append("\"");
        if (!CollectionUtils.isEmpty(fields)) {
            json.append(",");
            json.append("\"fields\":");
            json.append("[");
            for (Filter filter : fields) {
                json.append(filter).append(",");
            }
            json.deleteCharAt(json.lastIndexOf(","));
            json.append("]");
        }
        json.append("}");
        return json.toString();
    }

    @Override
    public String toString() {
        return parse();
    }
}
