package com.hindu.newslettersubscription.controller;

import com.hindu.newslettersubscription.config.RepoConfig;
import com.hindu.newslettersubscription.model.CategoryInfo;
import com.hindu.newslettersubscription.model.UserInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.hindu.newslettersubscription.provider.DeferredResultProvider.createDeferredResult;
import static com.hindu.newslettersubscription.serviceimpl.SubscriberFacade.*;

@RestController
@RequestMapping("/newsletter")
public class SubscribeController {

    @Autowired
    private RepoConfig repoConfig;

    @ApiOperation(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            value = "Subscribe to Category",
            notes = "Subscribe to Category"
    )
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK" ),
            @ApiResponse(code = HttpServletResponse.SC_FOUND, message = "Data Found" ),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Data not found" ),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Invalid request" ),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal server error" )
    })
    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public DeferredResult<ResponseEntity<UserInfo>> subscribe(
            @ApiParam(value = "userCategory", name = "userCategory" )
            @RequestBody final UserInfo userInfo) {
        return createDeferredResult(subscribeCategory(userInfo).with(repoConfig.getUserRepository()), HttpStatus.OK);
    }

    @ApiOperation(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            value = "UnSubscribe to Category",
            notes = "UnSubscribe to Category"
    )
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK" ),
            @ApiResponse(code = HttpServletResponse.SC_FOUND, message = "Data Found" ),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Data not found" ),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Invalid request" ),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal server error" )
    })
    @RequestMapping(value = "/unsubscribe", method = RequestMethod.POST)
    public DeferredResult<ResponseEntity<UserInfo>> unSubscribe(
            @ApiParam(value = "userCategory", name = "userCategory" )
            @RequestBody final UserInfo userInfo) {
        return createDeferredResult(unSubscribeCategory(userInfo).with(repoConfig.getUserRepository()), HttpStatus.OK);
    }

    @ApiOperation(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            value = "Subscription Status for Category",
            notes = "Subscription Status for Category"
    )
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK" ),
            @ApiResponse(code = HttpServletResponse.SC_FOUND, message = "Data Found" ),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Data not found" ),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Invalid request" ),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal server error" )
    })
    @RequestMapping(value = "/listSubscribed", method = RequestMethod.GET)
    public DeferredResult<ResponseEntity<List<UserInfo>>> unSubscribe() {
        return createDeferredResult(fetchSubscribedCategory().with(repoConfig.getUserRepository()), HttpStatus.OK);
    }

}
