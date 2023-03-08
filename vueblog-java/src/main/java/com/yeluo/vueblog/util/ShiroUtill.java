package com.yeluo.vueblog.util;

import com.yeluo.vueblog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

public class ShiroUtill {
    public static AccountProfile getProfile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
