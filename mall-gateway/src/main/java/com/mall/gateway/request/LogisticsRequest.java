package com.mall.gateway.request;

import lombok.Data;

import java.io.Serializable;

/**
 * PROJECT: gateway
 * DESCRIPTION: note
 *
 * @author Daoyuan
 * @date 2018/12/7
 */
@Data
public class LogisticsRequest implements Serializable {

    private static final long serialVersionUID = 6978721740298294790L;

    private String userId;

    private String phoneNo;

    private String orderNo;

    private String logisticsNo;
}
