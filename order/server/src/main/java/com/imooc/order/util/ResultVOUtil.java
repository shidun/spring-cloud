package com.imooc.order.util;

import com.imooc.order.VO.ResultVO;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 14:44
 */
public class ResultVOUtil {
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(200);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
}
