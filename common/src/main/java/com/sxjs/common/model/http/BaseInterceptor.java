/*
 *    Copyright (C) 2016 Tamic
 *
 *    link :https://github.com/Tamicer/Novate
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.sxjs.common.model.http;


import android.content.Context;
import android.util.Log;

import com.sxjs.common.CommonConfig;
import com.sxjs.common.util.NetworkUtil;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * BaseInterceptor，use set okhttp call header
 * Created by Tamic on 2016-06-30.
 */
public class BaseInterceptor<T> implements Interceptor {
    private Context context;
    private Map<String, T> headers;

    public BaseInterceptor(Map<String, T> headers, Context context) {
        this.context = context;
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if(!NetworkUtil.isNetworkAvailable(context)){
            throw new NoNetWorkException();
        }
        Request request = chain.request();
        if(CommonConfig.DEBUG){
            Log.d("http_request", "intercept: request header="+request.url() + "/\r body="+request.body() );
        }
        Request.Builder builder = request.newBuilder();
        if (headers != null && headers.size() > 0) {
            Set<String> keys = headers.keySet();
            for (String headerKey : keys) {
                builder.addHeader(headerKey, headers.get(headerKey) == null? "": (String)headers.get(headerKey)).build();
            }
        }
        return chain.proceed(builder.build());

    }
}
