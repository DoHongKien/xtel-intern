package com.example.paymentwithmultithread.model.dto;

import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
public class SearchSpecification<T> implements Specification<T> {

    private final SearchRequest request;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        // Khởi tạo Predicate với điều kiện luôn đúng
        Predicate predicate = cb.equal(cb.literal(Boolean.TRUE), Boolean.TRUE);

        for (FilterRequest filter : request.getFilters()) {
            log.info("Filter: field({}) - operator({}) - value({})",
                    filter.getKey(),
                    filter.getOperator().toString(),
                    filter.getValueTo() != null ? filter.getValue() + " and " + filter.getValueTo() : filter.getValue());

            predicate = filter.getOperator().build(root, cb, filter, predicate);
        }
        List<Order> orders = new ArrayList<>();
        for (SortRequest sort : request.getSorts()) {
            orders.add(sort.getSort().build(root, cb, sort));
        }

        query.orderBy(orders);
        return predicate;
    }

    public static Pageable getPageable(Integer page, Integer size) {
        return PageRequest.of(
                Objects.requireNonNullElse(page, 0),
                Objects.requireNonNullElse(size, 10)
        );
    }
}
