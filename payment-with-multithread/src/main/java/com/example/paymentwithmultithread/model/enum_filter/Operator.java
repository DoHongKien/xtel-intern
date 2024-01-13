package com.example.paymentwithmultithread.model.enum_filter;

import com.example.paymentwithmultithread.model.dto.FilterRequest;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public enum Operator {

    EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb,
                                   FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            Expression<?> key = this.getPath(root, request);
            return cb.and(cb.equal(key, value), predicate);
        }
    },
    NOT_EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb,
                                   FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            Expression<?> key = this.getPath(root, request);
            return cb.and(cb.notEqual(key, value), predicate);
        }
    },
    LIKE {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb,
                                   FilterRequest request, Predicate predicate) {
            Expression<String> key = this.getPath(root, request);
            return cb.and(cb.like(cb.upper(key), "%" + request.getValue().toString().toUpperCase() + "%"), predicate);
        }
    },
    IN {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb,
                                   FilterRequest request, Predicate predicate) {
            List<Object> values = request.getValues();
            CriteriaBuilder.In<Object> in = cb.in(this.getPath(root, request));
            for(Object value : values) {
                in.value(request.getFieldType().parse(value.toString()));
            }
            return cb.and(in, predicate);
        }
    },
    BETWEEN {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb,
                                   FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            Object valueTo = request.getFieldType().parse(request.getValueTo().toString());
            if(request.getFieldType() == FilterType.DATE) {
                LocalDateTime startDate = (LocalDateTime) value;
                LocalDateTime endDate = (LocalDateTime) valueTo;
                Expression<LocalDateTime> key = this.getPath(root, request);
                return cb.and(cb.and(cb.greaterThanOrEqualTo(key, startDate), cb.lessThanOrEqualTo(key, endDate)), predicate);
            }

            if(request.getFieldType() != FilterType.CHAR && request.getFieldType() != FilterType.BOOLEAN) {
                Number start = (Number) value;
                Number end = (Number) valueTo;
                Expression<Number> key = this.getPath(root, request);
                return cb.and(cb.and(cb.ge(key, start), cb.le(key, end)), predicate);
            }
            log.error("Cannot use between for {} field type", request.getFieldType());
            return predicate;
        }
    };

    public abstract <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate);

    public <T, V> Path<V> getPath(Root<T> root, FilterRequest request) {
        String[] keys = request.getKey().split("\\.");
        Path<V> path = root.get(keys[0]);
        for (int i = 1; i < keys.length; i++) {
            path = path.get(keys[i]);
        }
        return path;
    }
}
