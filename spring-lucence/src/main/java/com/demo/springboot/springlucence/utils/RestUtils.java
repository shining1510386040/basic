package com.demo.springboot.springlucence.utils;

import com.demo.springboot.springlucence.vo.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description RestTemplate 工具类
 * @date 2021/3/9 20:13
 * @see
 */
@Component
public class RestUtils {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @param url    目的url
     * @param params 请求参数,(表单参数)
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 向目的URL发送post请求
     * @date 2021/3/9 20:14
     */
    public ServiceResult sendPostRequest(String url, MultiValueMap<String, String> params, String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("accessToken", accessToken);
        HttpMethod method = HttpMethod.POST;
        // 以表单的方式提交（媒体类型mime-type）
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，
        ResponseEntity<ServiceResult> response = restTemplate.exchange(url, method, requestEntity, ServiceResult.class);

        return response.getBody();
    }

    /**
     * @param url    目的url
     * @param params json参数,例如："{\"code\":\"testCode\",\"order\":1}"
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 向目的url发送post请求，json参数
     * @date 2021/3/25 15:31
     */
    public ServiceResult sendPostRequestWithJsonParams(String url, String params) {
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        // 以表单的方式提交（媒体类型mime-type）
        headers.setContentType(MediaType.APPLICATION_JSON);
        //将请求头部和参数合成一个请求
        HttpEntity<String> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，
        ResponseEntity<ServiceResult> response = restTemplate.exchange(url, method, requestEntity, ServiceResult.class);

        return response.getBody();
    }

    /**
     * @param url    目的url
     * @param params 参数（表单参数）
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 向目的Url发送get请求
     * @date 2021/3/25 15:26
     */
    public ServiceResult sendGetRequest(String url, MultiValueMap<String, String> params) {

        ResponseEntity<ServiceResult> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, ServiceResult.class, params);

        return responseEntity.getBody();
    }
}
