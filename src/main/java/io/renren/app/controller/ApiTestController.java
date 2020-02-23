package io.renren.app.controller;

import io.renren.app.form.TestForm;
import io.renren.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/app/test")
@RestController
@Api(tags = "通用接口")
public class ApiTestController {
    private static final Logger log = LoggerFactory.getLogger(ApiTestController.class);

    @PostMapping("/apiTest")
    @ResponseBody
    @ApiOperation("测试接口")
    public R test(@RequestBody TestForm testForm){
        return R.ok().put("html","<body><form id = \"sform\" action=\"https://wap.lianlianpay.com/payment.htm\" method=\"post\"><input type=\"hidden\" name=\"req_data\" value='{\"acct_name\":\"张磊\",\"app_request\":\"3\",\"busi_partner\":\"101001\",\"card_no\":\"6216610100016554137\",\"dt_order\":\"20181116214310\",\"id_no\":\"370921199503303018\",\"id_type\":\"0\",\"money_order\":\"1.1\",\"name_goods\":\"测试\",\"no_order\":\"1542375790592\",\"notify_url\":\"http://test.lianlianpay.com.cn/help/notify.php\",\"oid_partner\":\"201408071000001546\",\"risk_item\":\"{\\\"goods_name\\\":\\\"测试\\\",\\\"user_info_full_name\\\":\\\"张三\\\",\\\"user_info_id_no\\\":\\\"370921199503303018\\\",\\\"user_info_bind_phone\\\":\\\"17090403304\\\",\\\"user_info_identify_state\\\":\\\"0\\\",\\\"user_info_mercht_userno\\\":\\\"17090403304\\\",\\\"frms_ware_category\\\":\\\"2010\\\",\\\"user_info_dt_register\\\":\\\"20181116214310\\\"}\",\"sign\":\"LHoRv7vYOm0HtSM/5PjRyWboEwrKy2Ue7G66pD3QajCLgpQm7T370MIm1JyvEGTyskxm/vPuJcm7yiAqg4NMhR4iddWd+BQIqjVRz0i61tzmyoMlP+EDp8/Ot3FHwyQC5c2n9vuqwT11HIrMwNL2KN9P7T1+qUBbvHKS16ITXx4=\",\"sign_type\":\"RSA\",\"user_id\":\"17090403304\",\"version\":\"1.1\"}'/></form></body><script type=\"text/javascript\">document.getElementById(\"sform\").submit();</script>");
    }
}
