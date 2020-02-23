package io.renren.app.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * auther: zzxka
 * date: 2020/2/23
 * description:
 */
@Api(tags = "比赛")
@RestController
@RequestMapping("/app/match")
public class MatchController {
    private static final Logger log= LoggerFactory.getLogger(MatchController.class);

}
