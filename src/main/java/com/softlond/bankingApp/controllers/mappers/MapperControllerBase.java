package com.softlond.bankingApp.controllers.mappers;

import java.util.List;

public abstract class MapperControllerBase <T1, T2>{
	public abstract T2 mapperT1T2(T1 input);
    public abstract T1 mapperT2T1(T2 input);
    public abstract List<T2> MapperT1T2(List<T1> input);
    public abstract List<T1> MapperT2T1(List<T2> input);
}
