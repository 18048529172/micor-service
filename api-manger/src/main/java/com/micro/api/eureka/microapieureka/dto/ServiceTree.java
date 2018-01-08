package com.micro.api.eureka.microapieureka.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 *
 * id：节点ID，对加载远程数据很重要。
 * text：显示节点文本。
 * state：节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
 * checked：表示该节点是否被选中。
 * attributes: 被添加到节点的自定义属性。
 * children: 一个节点数组声明了若干节点。
 *
 * @author liw@suncd.com
 * @date 2017/12/27 11:54
 */
public class ServiceTree {

    private String id;

    private String text;

    private String state;

    private boolean checked = false;

    private String attributes;

    public void addChildren(ServiceTree serviceTree){
        if(this.children == null){
            this.children = new ArrayList<>();
        }
        this.children.add(serviceTree);
    }

    private List<ServiceTree> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public List<ServiceTree> getChildren() {
        return children;
    }

    public void setChildren(List<ServiceTree> children) {
        this.children = children;
    }

}
