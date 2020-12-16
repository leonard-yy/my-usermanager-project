package com.lgsoftware.supportbuild.myusermanagerproject.login.controller;

import com.lgsoftware.supportbuild.myusermanagerproject.common.ResponseVO;
import com.lgsoftware.supportbuild.myusermanagerproject.util.ResponseVOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * CREATE BY LiuG ON 2020/12/16.
 * INFO --
 */
@RestController
public class SysUserController {

    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    public ResponseVO getData () {
        List<String> list = new ArrayList<>();
        return ResponseVOUtils.buildSuccess(list);
    }
}
