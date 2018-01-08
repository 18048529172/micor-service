package com.micro.api.authority.api.authority.dao.jpaspecification;

import com.micro.api.authority.api.authority.entity.Permission;
import com.micro.api.authority.api.authority.entity.Role;
import com.micro.api.authority.api.authority.entity.RolePermission;
import com.micro.api.authority.api.authority.entity.UserRole;
import com.micro.data.authority.dto.QueryPremissionDTO;
import com.micro.exception.ExceptionChecks;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/2 16:01
 */
public class PermissionSpecification implements Specification<Permission> {

    private QueryPremissionDTO queryPremission;

    public PermissionSpecification(QueryPremissionDTO queryPremission){
        this.queryPremission = queryPremission;
    }

    @Override
    public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        String tenementId = this.queryPremission.getTenementId();
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(tenementId),"租户id不能为空");
        predicates.add(criteriaBuilder.equal(root.get("tenementId"),tenementId));
        Long roleId = this.queryPremission.getRoleId();
        if(roleId != null){
            //查询这个角色下的权限
            SetJoin<Permission,RolePermission> rolePermissionSetJoin = root.joinSet("rolePermissions",JoinType.LEFT);
            Join<RolePermission,Role> roleJoin = rolePermissionSetJoin.join("role",JoinType.LEFT);
            predicates.add(criteriaBuilder.equal(roleJoin.get("id"),roleId));
        }
        String name = this.queryPremission.getName();
        if(StringUtils.isNotBlank(name)){
            predicates.add(criteriaBuilder.like(root.get("name"),"%"+name+"%"));
        }
        String code = this.queryPremission.getCode();
        if(StringUtils.isNotBlank(code)){
            predicates.add(criteriaBuilder.equal(root.get("code"),code));
        }
        String userId = this.queryPremission.getUserId();
        if(StringUtils.isNotBlank(userId)){
            SetJoin<Permission,RolePermission> rolePermissionSetJoin = root.joinSet("rolePermissions",JoinType.LEFT);
            Join<RolePermission,Role> roleJoin = rolePermissionSetJoin.join("role",JoinType.LEFT);
            SetJoin<Role,UserRole> userRoleSet = roleJoin.joinSet("userRoles", JoinType.LEFT);
            predicates.add(criteriaBuilder.equal(userRoleSet.get("userId"),userId));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
