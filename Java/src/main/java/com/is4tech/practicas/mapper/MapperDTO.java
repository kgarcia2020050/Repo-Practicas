package com.is4tech.practicas.mapper;

public interface MapperDTO<I,O> {
    public O mapeo(I objeto);
}
