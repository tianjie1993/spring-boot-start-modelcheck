package com.jettian.pojocheck.config;

import com.jettian.pojocheck.checkintf.ResultDataContainerIntf;

/**
 * @author tianjie
 * @version V1.0
 * @description 长度校验配置类;
 * @date 2018/8/20 10:09
 * @email tj_work@yeah.net
 */
public class PojoCheckConfig {

    private ResultDataContainerIntf resultDataContainerIntf;

    private String scanPackage;

    public PojoCheckConfig(ResultDataContainerIntf resultDataContainerIntf, String scanPackage) {
        this.resultDataContainerIntf = resultDataContainerIntf;
        this.scanPackage = scanPackage;
    }

    public String getScanPackage() {
        return scanPackage;
    }

    public void setScanPackage(String scanPackage) {
        this.scanPackage = scanPackage;
    }

    public ResultDataContainerIntf getResultDataContainerIntf() {
        return resultDataContainerIntf;
    }

    public void setResultDataContainerIntf(ResultDataContainerIntf resultDataContainerIntf) {
        this.resultDataContainerIntf = resultDataContainerIntf;
    }

    public Object pack(String message){
       return  this.resultDataContainerIntf.pack(message);
    }
}
