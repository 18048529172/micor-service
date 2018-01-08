package com.micro.api.authority.api.authority.dao.jpaspecification;

import com.micro.api.authority.api.authority.entity.Role;
import com.micro.api.authority.api.authority.entity.UserRole;
import com.micro.base.api.data.authority.dto.QueryRoleDTO;
import com.micro.base.exception.ExceptionChecks;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/2 11:16
 */
public class RoleSpecification implements Specification<Role> {

    private QueryRoleDTO queryRoleDTO;

    public RoleSpecification(QueryRoleDTO queryRoleDTO){
        this.queryRoleDTO = queryRoleDTO;
    }

    @Override
    public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        String tenementId = this.queryRoleDTO.getTenementId();
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(tenementId),"租户id不能为空");
        Predicate tenementIdPredicate = criteriaBuilder.equal(root.get("tenementId"),tenementId);
        predicates.add(tenementIdPredicate);
        String name = queryRoleDTO.getName();
        if(StringUtils.isNotBlank(name)){
            predicates.add(criteriaBuilder.like(root.get("name"),"%"+name+"%"));
        }
        String code = queryRoleDTO.getCode();
        if(StringUtils.isNotBlank(code)){
            predicates.add(criteriaBuilder.equal(root.get("code"),code));
        }
        String userId = queryRoleDTO.getUserId();
        if(StringUtils.isNotBlank(userId)){
           SetJoin<Role,UserRole> userRoleSet = root.joinSet("userRoles", JoinType.LEFT);
            predicates.add(criteriaBuilder.equal(userRoleSet.get("userId"),userId));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
