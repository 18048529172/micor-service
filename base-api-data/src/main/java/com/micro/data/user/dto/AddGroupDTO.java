package com.micro.data.user.dto;

import com.micro.exception.ExceptionChecks;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/17 9:54
 */
public class AddGroupDTO implements Serializable {

    /**
     * 名字
     */
    private String name;
    /**
     * 创建人
     */
    private Long createUserId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public void check(){
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(this.getName()),"群名称不能为空");
        ExceptionChecks.checkArgument(this.getName().length()<=30,"群名字不能超过30字符");
        ExceptionChecks.checkArgument(this.getCreateUserId() != null,"创建人不能为空");
    }

}
