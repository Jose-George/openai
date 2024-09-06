package com.openai.common.api.infrastructure;

import com.openai.common.api.domain.model.User;
import com.openai.common.api.domain.repository.filter.UserFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;


public class UserSpecs {

    public static Specification<User> filter(UserFilter filter){
        return (root, criteriaQuery, criteriaBuilder) -> {

            var predicates = new ArrayList<Predicate>();

            predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.isActive()));

            if(filter.getName() != null){
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + filter.getName().toUpperCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
