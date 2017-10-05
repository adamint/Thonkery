/*
 Copyright 2017 CircuitRCAY

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package tv.circuitrcay.thonkery.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public class WebRequester {
    public static WebRequester REQUESTER = new WebRequester();
    private OkHttpClient client = new OkHttpClient();

    private WebRequester() {
    }

    public String get(String url) throws IOException {
        Response response = client.newCall(new Request.Builder().url(url).build()).execute();
        ResponseBody body = response.body();
        response.close();
        return body == null ? null : body.string();
    }
}
