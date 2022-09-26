package com.zkpgeek.gkrpc.example;

/**
 * @description:
 * @author: ZKP
 * @time: 2022/9/25
 */
public class CalcServiceImpl implements CalcService{
    @Override
    public int add(int a, int b) {
        return a +b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }
}
