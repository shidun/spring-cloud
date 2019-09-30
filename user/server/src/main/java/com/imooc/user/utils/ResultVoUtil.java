package com.imooc.user.utils;

import com.imooc.user.vo.ResultVO;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 10:36
 */
public class ResultVoUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVo = new ResultVO();
        resultVo.setCode(200);
        resultVo.setMsg("成功");
        resultVo.setData(object);
        return resultVo;
    }
    public static ResultVO success() {
        ResultVO resultVo = new ResultVO();
        resultVo.setCode(200);
        resultVo.setMsg("成功");
        return resultVo;
    }
    public static ResultVO error(String message) {
        ResultVO resultVo = new ResultVO();
        resultVo.setCode(400);
        resultVo.setMsg(message);
        return resultVo;
    }
}
