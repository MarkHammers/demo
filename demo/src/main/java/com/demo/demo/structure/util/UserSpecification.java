package com.demo.demo.structure.util;

import com.demo.demo.structure.model.domain.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSpecification {

    public static Specification<User> hasName(String name) {
        return (root, query, cb) -> (name == null || name.isEmpty()) ? null : cb.equal(cb.lower(root.get("name")), name.toLowerCase());
    }

    public static Specification<User> hasLastname(String lastname) {
        return (root, query, cb) -> (lastname == null || lastname.isEmpty()) ? null : cb.equal(cb.lower(root.get("lastname")), lastname.toLowerCase());
    }

    public static Specification<User> hasEmail(String email) {
        return (root, query, cb) -> (email == null || email.isEmpty()) ? null : cb.equal(cb.lower(root.get("email")), email.toLowerCase());
    }
}
