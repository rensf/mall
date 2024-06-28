package com.sys.gateway.util;

import com.alibaba.fastjson.JSONObject;
import com.sys.common.core.enums.ResultCodeEnum;
import com.sys.common.core.result.Result;
import org.apache.http.HttpHeaders;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 响应工具类
 *
 * @author rensf
 * @date 2022/12/15
 */
public class ResponseUtils {

    public static Mono<Void> writeErrorInfo(ServerHttpResponse response, ResultCodeEnum resultCode) {
        switch (resultCode) {
            case ACCESS_UNAUTHORIZED:
            case TOKEN_INVALID_OR_EXPIRED:
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                break;
            case TOKEN_ACCESS_FORBIDDEN:
                response.setStatusCode(HttpStatus.FORBIDDEN);
                break;
            default:
                response.setStatusCode(HttpStatus.BAD_REQUEST);
                break;
        }
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getHeaders().set("Access-Control-Allow-Origin", "*");
        response.getHeaders().set("Cache-Control", "no-cache");
        String body = JSONObject.toJSONString(Result.error(resultCode));
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer)).doOnError(error -> DataBufferUtils.release(buffer));
    }

}
