package com.minions.huffmancoding.Algorithm;

import java.io.Serializable;

/**
 * Created by Charito on 9/12/2017.
 */
public class Code implements Serializable{
    public Character symbol;
    public byte[] code;
    public Integer frequency;

    public Code(Character symbol, byte[] code){
        this.symbol= symbol;
        this.code = code;
    }
}
