package com.wars;

import io.quarkus.panache.common.Sort;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;

import java.util.Objects;


public class QueryParams {

    @QueryParam("page")
    @DefaultValue("0")
    @Min(0)
    private int page;

    @QueryParam("size")
    @DefaultValue("5")
    @Min(1)
    @Max(100)
    private int size;

    @QueryParam("sort")
    @DefaultValue("id")
    private String sort;

    @QueryParam("direction")
    @DefaultValue("Ascending")
    private Sort.Direction direction;

    public QueryParams() {
    }

    public QueryParams(int page, int size, String sort, Sort.Direction direction) {
        this.page = page;
        this.size = size;
        this.sort = sort;
        this.direction = direction;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryParams that = (QueryParams) o;
        return page == that.page && size == that.size && Objects.equals(sort, that.sort) && direction == that.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, size, sort, direction);
    }
}
