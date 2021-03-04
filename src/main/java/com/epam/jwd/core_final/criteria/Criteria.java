package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {
    private Long id;
    private String name;

    public Criteria(Criteria.Builder builder) {
        name = builder.name;
        id = builder.id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    static class Builder<T extends Builder<T>> {
        private Long id;
        private String name;

        public Builder() {
        }

        public T name(String name) {
            this.name = name;
            return (T) this;
        }

        public T id(Long id) {
            this.id = id;
            return (T) this;
        }
    }


}
