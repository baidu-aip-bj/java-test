/*
 * Copyright 2017 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.baidu.aip.ocr;

import com.baidu.aip.client.BaseClient;
import com.baidu.aip.contentcensor.EImgType;
import com.baidu.aip.error.AipError;
import com.baidu.aip.http.AipRequest;
import com.baidu.aip.util.Base64Util;
import com.baidu.aip.util.Util;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

public class AipOcr extends BaseClient {

    public AipOcr(String appId, String apiKey, String secretKey) {
        super(appId, apiKey, secretKey);
    }

    /**
     * 通用文字识别接口
     * 用户向服务请求识别某张图中的所有文字
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                language_type 识别语言类型，默认为CHN_ENG。可选值包括：<br>- CHN_ENG：中英文混合；<br>- ENG：英文；<br>- POR：葡萄牙语；<br>- FRE：法语；<br>- GER：德语；<br>- ITA：意大利语；<br>- SPA：西班牙语；<br>- RUS：俄语；<br>- JAP：日语；<br>- KOR：韩语；
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     *                detect_language 是否检测语言，默认不检测。当前支持（中文、英语、日语、韩语）
     *                probability 是否返回识别结果中每一行的置信度
     * @return JSONObject
     */
    public JSONObject basicGeneral(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.GENERAL_BASIC);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 通用文字识别接口
     * 用户向服务请求识别某张图中的所有文字
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                language_type 识别语言类型，默认为CHN_ENG。可选值包括：<br>- CHN_ENG：中英文混合；<br>- ENG：英文；<br>- POR：葡萄牙语；<br>- FRE：法语；<br>- GER：德语；<br>- ITA：意大利语；<br>- SPA：西班牙语；<br>- RUS：俄语；<br>- JAP：日语；<br>- KOR：韩语；
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     *                detect_language 是否检测语言，默认不检测。当前支持（中文、英语、日语、韩语）
     *                probability 是否返回识别结果中每一行的置信度
     * @return JSONObject
     */
    public JSONObject basicGeneral(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return basicGeneral(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 通用文字识别接口
     * 用户向服务请求识别某张图中的所有文字
     *
     * @param url     - 图片完整URL，URL长度不超过1024字节，URL对应的图片base64编码后大小不超过4M，最短边至少15px，最长边最大4096px,支持jpg/png/bmp格式，当image字段存在时url字段失效
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                language_type 识别语言类型，默认为CHN_ENG。可选值包括：<br>- CHN_ENG：中英文混合；<br>- ENG：英文；<br>- POR：葡萄牙语；<br>- FRE：法语；<br>- GER：德语；<br>- ITA：意大利语；<br>- SPA：西班牙语；<br>- RUS：俄语；<br>- JAP：日语；<br>- KOR：韩语；
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     *                detect_language 是否检测语言，默认不检测。当前支持（中文、英语、日语、韩语）
     *                probability 是否返回识别结果中每一行的置信度
     * @return JSONObject
     */
    public JSONObject basicGeneralUrl(String url, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.GENERAL_BASIC);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 通用文字识别（高精度版）接口
     * 用户向服务请求识别某张图中的所有文字，相对于通用文字识别该产品精度更高，但是识别耗时会稍长。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     *                probability 是否返回识别结果中每一行的置信度
     * @return JSONObject
     */
    public JSONObject basicAccurateGeneral(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.ACCURATE_BASIC);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 通用文字识别（高精度版）接口
     * 用户向服务请求识别某张图中的所有文字，相对于通用文字识别该产品精度更高，但是识别耗时会稍长。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     *                probability 是否返回识别结果中每一行的置信度
     * @return JSONObject
     */
    public JSONObject basicAccurateGeneral(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return basicAccurateGeneral(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 通用文字识别（含位置信息版）接口
     * 用户向服务请求识别某张图中的所有文字，并返回文字在图中的位置信息。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                recognize_granularity 是否定位单字符位置，big：不定位单字符位置，默认值；small：定位单字符位置
     *                language_type 识别语言类型，默认为CHN_ENG。可选值包括：<br>- CHN_ENG：中英文混合；<br>- ENG：英文；<br>- POR：葡萄牙语；<br>- FRE：法语；<br>- GER：德语；<br>- ITA：意大利语；<br>- SPA：西班牙语；<br>- RUS：俄语；<br>- JAP：日语；<br>- KOR：韩语；
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     *                detect_language 是否检测语言，默认不检测。当前支持（中文、英语、日语、韩语）
     *                vertexes_location 是否返回文字外接多边形顶点位置，不支持单字位置。默认为false
     *                probability 是否返回识别结果中每一行的置信度
     * @return JSONObject
     */
    public JSONObject general(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.GENERAL);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 通用文字识别（含位置信息版）接口
     * 用户向服务请求识别某张图中的所有文字，并返回文字在图中的位置信息。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                recognize_granularity 是否定位单字符位置，big：不定位单字符位置，默认值；small：定位单字符位置
     *                language_type 识别语言类型，默认为CHN_ENG。可选值包括：<br>- CHN_ENG：中英文混合；<br>- ENG：英文；<br>- POR：葡萄牙语；<br>- FRE：法语；<br>- GER：德语；<br>- ITA：意大利语；<br>- SPA：西班牙语；<br>- RUS：俄语；<br>- JAP：日语；<br>- KOR：韩语；
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     *                detect_language 是否检测语言，默认不检测。当前支持（中文、英语、日语、韩语）
     *                vertexes_location 是否返回文字外接多边形顶点位置，不支持单字位置。默认为false
     *                probability 是否返回识别结果中每一行的置信度
     * @return JSONObject
     */
    public JSONObject general(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return general(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 通用文字识别（含位置信息版）接口
     * 用户向服务请求识别某张图中的所有文字，并返回文字在图中的位置信息。
     *
     * @param url     - 图片完整URL，URL长度不超过1024字节，URL对应的图片base64编码后大小不超过4M，最短边至少15px，最长边最大4096px,支持jpg/png/bmp格式，当image字段存在时url字段失效
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                recognize_granularity 是否定位单字符位置，big：不定位单字符位置，默认值；small：定位单字符位置
     *                language_type 识别语言类型，默认为CHN_ENG。可选值包括：<br>- CHN_ENG：中英文混合；<br>- ENG：英文；<br>- POR：葡萄牙语；<br>- FRE：法语；<br>- GER：德语；<br>- ITA：意大利语；<br>- SPA：西班牙语；<br>- RUS：俄语；<br>- JAP：日语；<br>- KOR：韩语；
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     *                detect_language 是否检测语言，默认不检测。当前支持（中文、英语、日语、韩语）
     *                vertexes_location 是否返回文字外接多边形顶点位置，不支持单字位置。默认为false
     *                probability 是否返回识别结果中每一行的置信度
     * @return JSONObject
     */
    public JSONObject generalUrl(String url, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.GENERAL);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 通用文字识别（含位置高精度版）接口
     * 用户向服务请求识别某张图中的所有文字，并返回文字在图片中的坐标信息，相对于通用文字识别（含位置信息版）该产品精度更高，但是识别耗时会稍长。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                recognize_granularity 是否定位单字符位置，big：不定位单字符位置，默认值；small：定位单字符位置
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     *                vertexes_location 是否返回文字外接多边形顶点位置，不支持单字位置。默认为false
     *                probability 是否返回识别结果中每一行的置信度
     * @return JSONObject
     */
    public JSONObject accurateGeneral(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.ACCURATE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 通用文字识别（含位置高精度版）接口
     * 用户向服务请求识别某张图中的所有文字，并返回文字在图片中的坐标信息，相对于通用文字识别（含位置信息版）该产品精度更高，但是识别耗时会稍长。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                recognize_granularity 是否定位单字符位置，big：不定位单字符位置，默认值；small：定位单字符位置
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     *                vertexes_location 是否返回文字外接多边形顶点位置，不支持单字位置。默认为false
     *                probability 是否返回识别结果中每一行的置信度
     * @return JSONObject
     */
    public JSONObject accurateGeneral(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return accurateGeneral(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 通用文字识别（含生僻字版）接口
     * 某些场景中，图片中的中文不光有常用字，还包含了生僻字，这时用户需要对该图进行文字识别，应使用通用文字识别（含生僻字版）。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                language_type 识别语言类型，默认为CHN_ENG。可选值包括：<br>- CHN_ENG：中英文混合；<br>- ENG：英文；<br>- POR：葡萄牙语；<br>- FRE：法语；<br>- GER：德语；<br>- ITA：意大利语；<br>- SPA：西班牙语；<br>- RUS：俄语；<br>- JAP：日语；<br>- KOR：韩语；
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     *                detect_language 是否检测语言，默认不检测。当前支持（中文、英语、日语、韩语）
     *                probability 是否返回识别结果中每一行的置信度
     * @return JSONObject
     */
    public JSONObject enhancedGeneral(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.GENERAL_ENHANCED);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 通用文字识别（含生僻字版）接口
     * 某些场景中，图片中的中文不光有常用字，还包含了生僻字，这时用户需要对该图进行文字识别，应使用通用文字识别（含生僻字版）。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                language_type 识别语言类型，默认为CHN_ENG。可选值包括：<br>- CHN_ENG：中英文混合；<br>- ENG：英文；<br>- POR：葡萄牙语；<br>- FRE：法语；<br>- GER：德语；<br>- ITA：意大利语；<br>- SPA：西班牙语；<br>- RUS：俄语；<br>- JAP：日语；<br>- KOR：韩语；
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     *                detect_language 是否检测语言，默认不检测。当前支持（中文、英语、日语、韩语）
     *                probability 是否返回识别结果中每一行的置信度
     * @return JSONObject
     */
    public JSONObject enhancedGeneral(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return enhancedGeneral(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 通用文字识别（含生僻字版）接口
     * 某些场景中，图片中的中文不光有常用字，还包含了生僻字，这时用户需要对该图进行文字识别，应使用通用文字识别（含生僻字版）。
     *
     * @param url     - 图片完整URL，URL长度不超过1024字节，URL对应的图片base64编码后大小不超过4M，最短边至少15px，最长边最大4096px,支持jpg/png/bmp格式，当image字段存在时url字段失效
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                language_type 识别语言类型，默认为CHN_ENG。可选值包括：<br>- CHN_ENG：中英文混合；<br>- ENG：英文；<br>- POR：葡萄牙语；<br>- FRE：法语；<br>- GER：德语；<br>- ITA：意大利语；<br>- SPA：西班牙语；<br>- RUS：俄语；<br>- JAP：日语；<br>- KOR：韩语；
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     *                detect_language 是否检测语言，默认不检测。当前支持（中文、英语、日语、韩语）
     *                probability 是否返回识别结果中每一行的置信度
     * @return JSONObject
     */
    public JSONObject enhancedGeneralUrl(String url, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.GENERAL_ENHANCED);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 网络图片文字识别接口
     * 用户向服务请求识别一些网络上背景复杂，特殊字体的文字。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     *                detect_language 是否检测语言，默认不检测。当前支持（中文、英语、日语、韩语）
     * @return JSONObject
     */
    public JSONObject webImage(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.WEB_IMAGE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 网络图片文字识别接口
     * 用户向服务请求识别一些网络上背景复杂，特殊字体的文字。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     *                detect_language 是否检测语言，默认不检测。当前支持（中文、英语、日语、韩语）
     * @return JSONObject
     */
    public JSONObject webImage(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return webImage(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 网络图片文字识别接口
     * 用户向服务请求识别一些网络上背景复杂，特殊字体的文字。
     *
     * @param url     - 图片完整URL，URL长度不超过1024字节，URL对应的图片base64编码后大小不超过4M，最短边至少15px，最长边最大4096px,支持jpg/png/bmp格式，当image字段存在时url字段失效
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     *                detect_language 是否检测语言，默认不检测。当前支持（中文、英语、日语、韩语）
     * @return JSONObject
     */
    public JSONObject webImageUrl(String url, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.WEB_IMAGE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 身份证识别接口
     * 用户向服务请求识别身份证，身份证识别包括正面和背面。
     *
     * @param image      - 二进制图像数据
     * @param idCardSide - front：身份证含照片的一面；back：身份证带国徽的一面
     * @param options    - 可选参数对象，key: value都为string类型
     *                   options - options列表:
     *                   detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     *                   detect_risk 是否开启身份证风险类型(身份证复印件、临时身份证、身份证翻拍、修改过的身份证)功能，默认不开启，即：false。可选值:true-开启；false-不开启
     * @return JSONObject
     */
    public JSONObject idcard(byte[] image, String idCardSide, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);

        request.addBody("id_card_side", idCardSide);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.IDCARD);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 身份证识别接口
     * 用户向服务请求识别身份证，身份证识别包括正面和背面。
     *
     * @param image      - 本地图片路径
     * @param idCardSide - front：身份证含照片的一面；back：身份证带国徽的一面
     * @param options    - 可选参数对象，key: value都为string类型
     *                   options - options列表:
     *                   detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     *                   detect_risk 是否开启身份证风险类型(身份证复印件、临时身份证、身份证翻拍、修改过的身份证)功能，默认不开启，即：false。可选值:true-开启；false-不开启
     * @return JSONObject
     */
    public JSONObject idcard(String image, String idCardSide, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return idcard(data, idCardSide, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 银行卡识别接口
     * 识别银行卡并返回卡号和发卡行。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject bankcard(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.BANKCARD);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 银行卡识别接口
     * 识别银行卡并返回卡号和发卡行。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject bankcard(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return bankcard(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 驾驶证识别接口
     * 对机动车驾驶证所有关键字段进行识别
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     * @return JSONObject
     */
    public JSONObject drivingLicense(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.DRIVING_LICENSE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 驾驶证识别接口
     * 对机动车驾驶证所有关键字段进行识别
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     * @return JSONObject
     */
    public JSONObject drivingLicense(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return drivingLicense(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 行驶证识别接口
     * 对机动车行驶证正本所有关键字段进行识别
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     *                accuracy normal 使用快速服务，1200ms左右时延；缺省或其它值使用高精度服务，1600ms左右时延
     * @return JSONObject
     */
    public JSONObject vehicleLicense(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.VEHICLE_LICENSE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 行驶证识别接口
     * 对机动车行驶证正本所有关键字段进行识别
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     *                accuracy normal 使用快速服务，1200ms左右时延；缺省或其它值使用高精度服务，1600ms左右时延
     * @return JSONObject
     */
    public JSONObject vehicleLicense(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return vehicleLicense(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 车牌识别接口
     * 识别机动车车牌，并返回签发地和号牌。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                multi_detect 是否检测多张车牌，默认为false，当置为true的时候可以对一张图片内的多张车牌进行识别
     * @return JSONObject
     */
    public JSONObject plateLicense(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.LICENSE_PLATE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 车牌识别接口
     * 识别机动车车牌，并返回签发地和号牌。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                multi_detect 是否检测多张车牌，默认为false，当置为true的时候可以对一张图片内的多张车牌进行识别
     * @return JSONObject
     */
    public JSONObject plateLicense(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return plateLicense(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 营业执照识别接口
     * 识别营业执照，并返回关键字段的值，包括单位名称、法人、地址、有效期、证件编号、社会信用代码等。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject businessLicense(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.BUSINESS_LICENSE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 营业执照识别接口
     * 识别营业执照，并返回关键字段的值，包括单位名称、法人、地址、有效期、证件编号、社会信用代码等。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject businessLicense(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return businessLicense(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 通用票据识别接口
     * 用户向服务请求识别医疗票据、发票、的士票、保险保单等票据类图片中的所有文字，并返回文字在图中的位置信息。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                recognize_granularity 是否定位单字符位置，big：不定位单字符位置，默认值；small：定位单字符位置
     *                probability 是否返回识别结果中每一行的置信度
     *                accuracy normal 使用快速服务，1200ms左右时延；缺省或其它值使用高精度服务，1600ms左右时延
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     * @return JSONObject
     */
    public JSONObject receipt(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.RECEIPT);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 通用票据识别接口
     * 用户向服务请求识别医疗票据、发票、的士票、保险保单等票据类图片中的所有文字，并返回文字在图中的位置信息。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                recognize_granularity 是否定位单字符位置，big：不定位单字符位置，默认值；small：定位单字符位置
     *                probability 是否返回识别结果中每一行的置信度
     *                accuracy normal 使用快速服务，1200ms左右时延；缺省或其它值使用高精度服务，1600ms左右时延
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     * @return JSONObject
     */
    public JSONObject receipt(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return receipt(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 火车票识别接口
     * 支持对大陆火车票的车票号、始发站、目的站、车次、日期、票价、席别、姓名进行结构化识别
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject trainTicket(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.TRAIN_TICKET);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 火车票识别接口
     * 支持对大陆火车票的车票号、始发站、目的站、车次、日期、票价、席别、姓名进行结构化识别
     *
     * @param image   - 本地图片路径
     * @param type    - 文件类型
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject trainTicket(String image, EImgType type, HashMap<String, String> options) {
        return ocrByUrlOrFile(image, type, OcrConsts.TRAIN_TICKET, options);
    }

    /**
     * 出租车票识别接口
     * 针对出租车票（现支持北京）的发票号码、发票代码、车号、日期、时间、金额进行结构化识别
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject taxiReceipt(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.TAXI_RECEIPT);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 出租车票识别接口
     * 针对出租车票（现支持北京）的发票号码、发票代码、车号、日期、时间、金额进行结构化识别
     *
     * @param image   - 本地图片路径
     * @param type    - image 类型
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject taxiReceipt(String image, EImgType type, HashMap<String, String> options) {
        return ocrByUrlOrFile(image, type, OcrConsts.TAXI_RECEIPT, options);
    }

    /**
     * 表格文字识别同步接口接口
     * 自动识别表格线及表格内容，结构化输出表头、表尾及每个单元格的文字内容。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject form(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.FORM);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 表格文字识别同步接口接口
     * 自动识别表格线及表格内容，结构化输出表头、表尾及每个单元格的文字内容。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject form(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return form(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 表格文字识别接口
     * 自动识别表格线及表格内容，结构化输出表头、表尾及每个单元格的文字内容。表格文字识别接口为异步接口，分为两个API：提交请求接口、获取结果接口。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject tableRecognitionAsync(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.TABLE_RECOGNIZE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 表格文字识别接口
     * 自动识别表格线及表格内容，结构化输出表头、表尾及每个单元格的文字内容。表格文字识别接口为异步接口，分为两个API：提交请求接口、获取结果接口。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject tableRecognitionAsync(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return tableRecognitionAsync(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 表格识别结果接口
     * 获取表格文字识别结果
     *
     * @param requestId - 发送表格文字识别请求时返回的request id
     * @param options   - 可选参数对象，key: value都为string类型
     *                  options - options列表:
     *                  result_type 期望获取结果的类型，取值为“excel”时返回xls文件的地址，取值为“json”时返回json格式的字符串,默认为”excel”
     * @return JSONObject
     */
    public JSONObject tableResultGet(String requestId, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("request_id", requestId);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.TABLE_RESULT_GET);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * VIN码识别接口
     * 对车辆车架上、挡风玻璃上的VIN码进行识别
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject vinCode(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.VIN_CODE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * VIN码识别接口
     * 对车辆车架上、挡风玻璃上的VIN码进行识别
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                *                options - options列表:
     * @return JSONObject
     */
    public JSONObject vinCode(String image, EImgType type, HashMap<String, String> options) {
        return ocrByUrlOrFile(image, type, OcrConsts.VIN_CODE, options);
    }

    /**
     * 定额发票识别接口
     * 对各类定额发票的代码、号码、金额进行识别
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject quotaInvoice(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.QUOTA_INVOICE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 定额发票识别接口
     * 对各类定额发票的代码、号码、金额进行识别
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject quotaInvoice(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return quotaInvoice(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 户口本识别接口
     * 【此接口需要您在[申请页面](https://cloud.baidu.com/survey/AICooperativeConsultingApply.html)中提交合作咨询开通权限】对出生地、出生日期、姓名、民族、与户主关系、性别、身份证号码字段进行识别
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject householdRegister(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.HOUSEHOLD_REGISTER);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 户口本识别接口
     * 【此接口需要您在[申请页面](https://cloud.baidu.com/survey/AICooperativeConsultingApply.html)中提交合作咨询开通权限】对出生地、出生日期、姓名、民族、与户主关系、性别、身份证号码字段进行识别
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject householdRegister(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return householdRegister(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 港澳通行证识别接口
     * 【此接口需要您在[申请页面](https://cloud.baidu.com/survey/AICooperativeConsultingApply.html)中提交合作咨询开通权限】对港澳通行证证号、姓名、姓名拼音、性别、有效期限、签发地点、出生日期字段进行识别
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject HKMacauExitentrypermit(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.HK_MACAU_EXITENTRYPERMIT);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 港澳通行证识别接口
     * 【此接口需要您在[申请页面](https://cloud.baidu.com/survey/AICooperativeConsultingApply.html)中提交合作咨询开通权限】对港澳通行证证号、姓名、姓名拼音、性别、有效期限、签发地点、出生日期字段进行识别
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject HKMacauExitentrypermit(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return HKMacauExitentrypermit(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 台湾通行证识别接口
     * 【此接口需要您在[申请页面](https://cloud.baidu.com/survey/AICooperativeConsultingApply.html)中提交合作咨询开通权限】对台湾通行证证号、签发地、出生日期、姓名、姓名拼音、性别、有效期字段进行识别
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject taiwanExitentrypermit(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.TAIWAN_EXITENTRYPERMIT);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 台湾通行证识别接口
     * 【此接口需要您在[申请页面](https://cloud.baidu.com/survey/AICooperativeConsultingApply.html)中提交合作咨询开通权限】对台湾通行证证号、签发地、出生日期、姓名、姓名拼音、性别、有效期字段进行识别
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject taiwanExitentrypermit(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return taiwanExitentrypermit(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 出生医学证明识别接口
     * 【此接口需要您在[申请页面](https://cloud.baidu.com/survey/AICooperativeConsultingApply.html)中提交合作咨询开通权限】对台湾通行证证号、签发地、出生日期、姓名、姓名拼音、性别、有效期字段进行识别
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject birthCertificate(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.BIRTH_CERTIFICATE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 出生医学证明识别接口
     * 【此接口需要您在[申请页面](https://cloud.baidu.com/survey/AICooperativeConsultingApply.html)中提交合作咨询开通权限】对台湾通行证证号、签发地、出生日期、姓名、姓名拼音、性别、有效期字段进行识别
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject birthCertificate(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return birthCertificate(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 机动车销售发票识别接口
     * 【此接口需要您在[申请页面](https://cloud.baidu.com/survey/AICooperativeConsultingApply.html)中提交合作咨询开通权限】识别机动车销售发票号码、代码、日期、价税合计等14个字段
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject vehicleInvoice(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.VEHICLE_INVOICE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 机动车销售发票识别接口
     * 【此接口需要您在[申请页面](https://cloud.baidu.com/survey/AICooperativeConsultingApply.html)中提交合作咨询开通权限】识别机动车销售发票号码、代码、日期、价税合计等14个字段
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject vehicleInvoice(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return vehicleInvoice(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 车辆合格证识别接口
     * 【此接口需要您在[申请页面](https://cloud.baidu.com/survey/AICooperativeConsultingApply.html)中提交合作咨询开通权限】识别车辆合格证编号、车架号、排放标准、发动机编号等12个字段
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject vehicleCertificate(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.VEHICLE_CERTIFICATE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 车辆合格证识别接口
     * 【此接口需要您在[申请页面](https://cloud.baidu.com/survey/AICooperativeConsultingApply.html)中提交合作咨询开通权限】识别车辆合格证编号、车架号、排放标准、发动机编号等12个字段
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject vehicleCertificate(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return vehicleCertificate(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 税务局通用机打发票识别接口
     * 【此接口需要您在[申请页面](https://cloud.baidu.com/survey/AICooperativeConsultingApply.html)中提交合作咨询开通权限】对国家/地方税务局发行的横/竖版通用机打发票的号码、代码、日期、合计金额、类型、商品名称字段进行结构化识别
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                location 是否输出位置信息，true：输出位置信息，false：不输出位置信息，默认false
     * @return JSONObject
     */
    public JSONObject invoice(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.INVOICE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 税务局通用机打发票识别接口
     * 【此接口需要您在[申请页面](https://cloud.baidu.com/survey/AICooperativeConsultingApply.html)中提交合作咨询开通权限】对国家/地方税务局发行的横/竖版通用机打发票的号码、代码、日期、合计金额、类型、商品名称字段进行结构化识别
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                location 是否输出位置信息，true：输出位置信息，false：不输出位置信息，默认false
     * @return JSONObject
     */
    public JSONObject invoice(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return invoice(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 行程单识别接口
     * 【此接口需要您在[申请页面](https://cloud.baidu.com/survey/AICooperativeConsultingApply.html)中提交合作咨询开通权限】对飞机行程单中的姓名、始发站、目的站、航班号、日期、票价字段进行结构化识别
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                location 是否输出位置信息，true：输出位置信息，false：不输出位置信息，默认false
     * @return JSONObject
     */
    public JSONObject airTicket(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.AIR_TICKET);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 行程单识别接口
     * 【此接口需要您在[申请页面](https://cloud.baidu.com/survey/AICooperativeConsultingApply.html)中提交合作咨询开通权限】对飞机行程单中的姓名、始发站、目的站、航班号、日期、票价字段进行结构化识别
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                location 是否输出位置信息，true：输出位置信息，false：不输出位置信息，默认false
     * @return JSONObject
     */
    public JSONObject airTicket(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return airTicket(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 保单识别接口
     * 【此接口需要您在[申请页面](https://cloud.baidu.com/survey/AICooperativeConsultingApply.html)中提交合作咨询开通权限】对各类保单中投保人、受益人的各项信息、保费、保险名称等字段进行结构化识别
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                rkv_business 是否进行商业逻辑处理，rue：进行商业逻辑处理，false：不进行商业逻辑处理，默认true
     * @return JSONObject
     */
    public JSONObject insuranceDocuments(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.INSURANCE_DOCUMENTS);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 保单识别接口
     * 【此接口需要您在[申请页面](https://cloud.baidu.com/survey/AICooperativeConsultingApply.html)中提交合作咨询开通权限】对各类保单中投保人、受益人的各项信息、保费、保险名称等字段进行结构化识别
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                rkv_business 是否进行商业逻辑处理，rue：进行商业逻辑处理，false：不进行商业逻辑处理，默认true
     * @return JSONObject
     */
    public JSONObject insuranceDocuments(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return insuranceDocuments(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 增值税发票识别接口
     * 此接口需要您在页面中提交合作咨询开通权限】 识别并结构化返回增值税发票的各个字段及其对应值，包含了发票基础信息9项，货物相关信息12项，购买方/销售方的名称、识别号、地址电话、开户行及账号，共29项结构化字段。
     *
     * @param image   - 二进制图像数据
     * @param type    - 文件类型
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject vatInvoice(String image, EImgType type, HashMap<String, String> options) {
        if (type == EImgType.PDF) {
            AipRequest request = new AipRequest();
            preOperation(request);
            if (options != null) {
                request.addBody(options);
            }
            try {
                byte[] data = Util.readFileByBytes(image);
                String base64Content = Base64Util.encode(data);
                request.addBody("pdf_file", base64Content);
            } catch (IOException e) {
                e.printStackTrace();
            }
            request.setUri(OcrConsts.VAT_INVOICE);
            postOperation(request);
            return requestServer(request);
        } else {
            return ocrByUrlOrFile(image, type, OcrConsts.VAT_INVOICE, options);
        }
    }

    /**
     * 增值税发票识别接口
     * 此接口需要您在页面中提交合作咨询开通权限】 识别并结构化返回增值税发票的各个字段及其对应值，包含了发票基础信息9项，货物相关信息12项，购买方/销售方的名称、识别号、地址电话、开户行及账号，共29项结构化字段。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject vatInvoice(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.VAT_INVOICE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 增值税发票识别接口
     * 此接口需要您在页面中提交合作咨询开通权限】 识别并结构化返回增值税发票的各个字段及其对应值，包含了发票基础信息9项，货物相关信息12项，购买方/销售方的名称、识别号、地址电话、开户行及账号，共29项结构化字段。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject vatInvoice(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return vatInvoice(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 二维码识别接口
     * 【此接口需要您在[页面](http://ai.baidu.com/tech/ocr)中提交合作咨询开通权限识别条形码、二维码中包含的URL或其他信息内容
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject qrcode(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.QRCODE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 二维码识别接口
     * 【此接口需要您在[页面](http://ai.baidu.com/tech/ocr)中提交合作咨询开通权限识别条形码、二维码中包含的URL或其他信息内容
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject qrcode(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return qrcode(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 数字识别接口
     * 【此接口需要您在[页面](http://ai.baidu.com/tech/ocr)中提交合作咨询开通权限】对图像中的阿拉伯数字进行识别提取，适用于快递单号、手机号、充值码提取等场景
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                recognize_granularity 是否定位单字符位置，big：不定位单字符位置，默认值；small：定位单字符位置
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     * @return JSONObject
     */
    public JSONObject numbers(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.NUMBERS);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 数字识别接口
     * 【此接口需要您在[页面](http://ai.baidu.com/tech/ocr)中提交合作咨询开通权限】对图像中的阿拉伯数字进行识别提取，适用于快递单号、手机号、充值码提取等场景
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                recognize_granularity 是否定位单字符位置，big：不定位单字符位置，默认值；small：定位单字符位置
     *                detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:<br>- true：检测朝向；<br>- false：不检测朝向。
     * @return JSONObject
     */
    public JSONObject numbers(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return numbers(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 彩票识别接口
     * 【此接口需要您在[页面](http://ai.baidu.com/tech/ocr)中提交合作咨询开通权限】对大乐透、双色球彩票进行识别，并返回识别结果
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                recognize_granularity 是否定位单字符位置，big：不定位单字符位置，默认值；small：定位单字符位置
     * @return JSONObject
     */
    public JSONObject lottery(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.LOTTERY);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 彩票识别接口
     * 【此接口需要您在[页面](http://ai.baidu.com/tech/ocr)中提交合作咨询开通权限】对大乐透、双色球彩票进行识别，并返回识别结果
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                recognize_granularity 是否定位单字符位置，big：不定位单字符位置，默认值；small：定位单字符位置
     * @return JSONObject
     */
    public JSONObject lottery(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return lottery(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 护照识别接口
     * 【此接口需要您在[页面](http://ai.baidu.com/tech/ocr)中提交合作咨询开通权限】支持对中国大陆居民护照的资料页进行结构化识别，包含国家码、姓名、性别、护照号、出生日期、签发日期、有效期至、签发地点。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject passport(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.PASSPORT);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 护照识别接口
     * 【此接口需要您在[页面](http://ai.baidu.com/tech/ocr)中提交合作咨询开通权限】支持对中国大陆居民护照的资料页进行结构化识别，包含国家码、姓名、性别、护照号、出生日期、签发日期、有效期至、签发地点。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject passport(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return passport(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 名片识别接口
     * 【此接口需要您在[页面](http://ai.baidu.com/tech/ocr)中提交合作咨询开通权限】提供对各类名片的结构化识别功能，提取姓名、邮编、邮箱、电话、网址、地址、手机号字段
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject businessCard(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.BUSINESS_CARD);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 名片识别接口
     * 【此接口需要您在[页面](http://ai.baidu.com/tech/ocr)中提交合作咨询开通权限】提供对各类名片的结构化识别功能，提取姓名、邮编、邮箱、电话、网址、地址、手机号字段
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject businessCard(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return businessCard(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 手写文字识别接口
     * 【此接口需要您在[页面](http://ai.baidu.com/tech/ocr)中提交合作咨询开通权限】提供对各类名片的结构化识别功能，提取姓名、邮编、邮箱、电话、网址、地址、手机号字段
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                recognize_granularity 是否定位单字符位置，big：不定位单字符位置，默认值；small：定位单字符位置
     * @return JSONObject
     */
    public JSONObject handwriting(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.HANDWRITING);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 手写文字识别接口
     * 【此接口需要您在[页面](http://ai.baidu.com/tech/ocr)中提交合作咨询开通权限】提供对各类名片的结构化识别功能，提取姓名、邮编、邮箱、电话、网址、地址、手机号字段
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                recognize_granularity 是否定位单字符位置，big：不定位单字符位置，默认值；small：定位单字符位置
     * @return JSONObject
     */
    public JSONObject handwriting(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return handwriting(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 自定义模板文字识别接口
     * 自定义模板文字识别，是针对百度官方没有推出相应的模板，但是当用户需要对某一类卡证/票据（如房产证、军官证、火车票等）进行结构化的提取内容时，可以使用该产品快速制作模板，进行识别。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                templateSign 您在自定义文字识别平台制作的模板的ID
     *                classifierId 分类器Id。这个参数和templateSign至少存在一个，优先使用templateSign。存在templateSign时，表示使用指定模板；如果没有templateSign而有classifierId，表示使用分类器去判断使用哪个模板
     * @return JSONObject
     */
    public JSONObject custom(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.CUSTOM);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 自定义模板文字识别接口
     * 自定义模板文字识别，是针对百度官方没有推出相应的模板，但是当用户需要对某一类卡证/票据（如房产证、军官证、火车票等）进行结构化的提取内容时，可以使用该产品快速制作模板，进行识别。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                templateSign 您在自定义文字识别平台制作的模板的ID
     *                classifierId 分类器Id。这个参数和templateSign至少存在一个，优先使用templateSign。存在templateSign时，表示使用指定模板；如果没有templateSign而有classifierId，表示使用分类器去判断使用哪个模板
     * @return JSONObject
     */
    public JSONObject custom(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return custom(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 获取表格识别结果（异步）.
     *
     * @param requestId 由tableRecognition接口返回的requestId
     * @return 识别状态和结果
     */
    public JSONObject getTableRecognitionJsonResult(String requestId) {
        return getTableResultHelper(requestId, "json");
    }

    /**
     * 获取表格识别结果（异步）.
     *
     * @param requestId 由tableRecognition接口返回的requestId
     * @return 识别状态和excel下载地址
     */
    public JSONObject getTableRecognitionExcelResult(String requestId) {
        return getTableResultHelper(requestId, "excel");
    }

    // 表格识别获取结果接口公共逻辑
    private JSONObject getTableResultHelper(String requestId, String resultType) {
        AipRequest request = new AipRequest();
        preOperation(request);
        request.addBody("request_id", requestId);
        request.addBody("result_type", resultType);
        request.setUri(OcrConsts.TABLE_RESULT_GET);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 表格识别接口，返回json结果
     *
     * @param imgPath            识别图片路径
     * @param timeoutMiliseconds 等待超时(ms)
     * @return json格式识别结果
     */
    public JSONObject tableRecognizeToJson(String imgPath, long timeoutMiliseconds) {
        try {
            byte[] imgData = Util.readFileByBytes(imgPath);
            return tableRecognizeToJson(imgData, timeoutMiliseconds);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 表格识别接口，返回json结果
     *
     * @param imgData            识别图片二进制数据
     * @param timeoutMiliseconds 等待超时(ms)
     * @return json格式识别结果
     */
    public JSONObject tableRecognizeToJson(byte[] imgData, long timeoutMiliseconds) {
        return tableRecSyncHelper(imgData, timeoutMiliseconds, "json");
    }

    /**
     * 表格识别接口，返回生成excel的url地址
     *
     * @param imgPath            识别图片路径
     * @param timeoutMiliseconds 等待超时(ms)
     * @return json result
     */
    public JSONObject tableRecognizeToExcelUrl(String imgPath, long timeoutMiliseconds) {
        try {
            byte[] imgData = Util.readFileByBytes(imgPath);
            return tableRecognizeToExcelUrl(imgData, timeoutMiliseconds);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 表格识别接口，返回生成excel的url地址
     *
     * @param imgData            识别图片二进制数据
     * @param timeoutMiliseconds 等待超时(ms)
     * @return json result
     */
    public JSONObject tableRecognizeToExcelUrl(byte[] imgData, long timeoutMiliseconds) {
        return tableRecSyncHelper(imgData, timeoutMiliseconds, "excel");
    }

    // 表格识别接口包装同步接口，负责发起识别请求并等待结果生成。
    private JSONObject tableRecSyncHelper(byte[] imgData, long timeout, String resultType) {
        JSONObject res = tableRecognitionAsync(imgData, null);
        if (res.has("error_code")) {
            return res;
        }
        String reqId = res.getJSONArray("result").getJSONObject(0).getString("request_id");
        long start = Calendar.getInstance().getTimeInMillis();
        long sleepInterval = 2000;
        JSONObject result;
        while (true) {
            long now = Calendar.getInstance().getTimeInMillis();
            if (now - start > timeout) {
                // 超时
                return AipError.ASYNC_TIMEOUT_ERROR.toJsonResult();
            }
            result = getTableResultHelper(reqId, resultType);
            if (result.has("error_code")) {
                // 返回错误
                return result;
            }
            int retCode = result.getJSONObject("result").getInt("ret_code");
            if (retCode == OcrConsts.ASYNC_TASK_STATUS_FINISHED) {
                return result;
            } else {
                try {
                    Thread.sleep(sleepInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 文档版面分析与识别
     *
     * @param image 识别图片二进制数据
     * @param options 可选参数
     * @return json result
     */
    public JSONObject docAnalysis(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return docAnalysis(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }


    /**
     * 文档版面分析与识别
     *
     * @param image 识别图片二进制数据
     * @param options 可选参数
     * @return json result
     */
    public JSONObject docAnalysis(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);
        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.DOC_ANALYSIS);
        postOperation(request);
        return requestServer(request);

    }


    /**
     * 仪器仪表盘读数识别
     *
     * @param image 识别图片二进制数据
     * @param options 可选参数
     * @return json result
     */
    public JSONObject meter(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return meter(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 仪器仪表盘读数识别
     *
     * @param image 识别图片二进制数据
     * @param options 可选参数
     * @return json result
     */
    public JSONObject meter(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.METER);
        postOperation(request);
        return requestServer(request);

    }


    /**
     * 网络图片文字识别（含位置版）
     *
     * @param image 识别图片二进制数据
     * @param options 可选参数
     * @return json result
     */
    public JSONObject webimageLoc(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return webimageLoc(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }


    /**
     * 网络图片文字识别（含位置版）
     *
     * @param image 识别图片二进制数据
     * @param options 可选参数
     * @return json result
     */
    public JSONObject webimageLoc(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.WEBIMAGE_LOC);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 识别 图片文件或者url
     *
     * @param image     文件或者url
     * @param type      文件类型
     * @param targetUrl 识别接口url
     * @param options  可选参数
     * @return JSONObject
     */
    private JSONObject ocrByUrlOrFile(String image, EImgType type, String targetUrl, HashMap<String, String> options) {
        if (type == EImgType.FILE) {
            return ocrByFile(image, targetUrl, options);
        } else {
            return ocrByUrl(image, targetUrl, options);
        }
    }


    private JSONObject ocrByUrl(String imageUrl, String targetUrl, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);
        request.addBody("url", imageUrl);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(targetUrl);
        postOperation(request);
        return requestServer(request);
    }

    private JSONObject ocrByFile(String image, String targetUrl, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);
        try {
            byte[] data = Util.readFileByBytes(image);
            String base64Content = Base64Util.encode(data);
            request.addBody("image", base64Content);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(targetUrl);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 二维码识别
     * 对图片中的二维码、条形码进行检测和识别，返回存储的文字信息
     *
     * @param url - 图片完整URL
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject qrcodeUrl(String url,
                                HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.QRCODE);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 试卷分析与识别
     * 支持对车辆合格证的23个关键字段进行结构化识别，包括合格证编号、发证日期、车辆制造企业名、车辆品牌、车辆名称、车辆型号、车架号、车身颜色、
     * 发动机型号、发动机号、燃料种类、排量、功率、排放标准、轮胎数、轴距、轴数、转向形式、总质量、整备质量、驾驶室准乘人数、最高设计车速、
     * 车辆制造日期parameter=image:byte[]:二进制图像数据:base64
     *
     * @param url - 图片完整URL
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                multi_detect - 控制是否开启多航班信息识别功能,默认值：false
     * @return JSONObject
     */
    public JSONObject docAnalysisUrl(String url,
                                     HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.DOC_ANALYSIS);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 机动车销售发票
     * 支持对机动车销售发票的26个关键字段进行结构化识别，
     *
     * @param url - 图片完整URL
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject vehicleInvoiceUrl(String url,
                                        HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.VEHICLE_INVOICE);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 车辆合格证
     * 支持对车辆合格证的23个关键字段进行结构化识别，包括合格证编号、发证日期、车辆制造企业名、车辆品牌、车辆名称、车辆型号、车架号、车身颜色、
     * 发动机型号、发动机号、燃料种类、排量、功率、排放标准、轮胎数、轴距、轴数、转向形式、总质量、整备质量、驾驶室准乘人数、最高设计车速、
     * 车辆制造日期parameter=image:byte[]:二进制图像数据:base64
     *
     * @param url - 图片完整URL
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                language_type - 识别语言类型，默认为CHN_ENG
     *                result_type - 返回识别结果是按单行结果返回，还是按单字结果返回，默认为big
     *                detect_direction - 是否检测图像朝向，默认不检测，即：false
     *                line_probability - 是否返回每行识别结果的置信度。默认为false
     *                words_type - 文字类型。
     * @return JSONObject
     */
    public JSONObject vehicleCertificateUrl(String url,
                                            HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.VEHICLE_CERTIFICATE);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 户口本识别
     * 支持对户口本内常住人口登记卡的全部 22 个字段进行结构化识别，
     *
     * @param url - 图片完整URL
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject householdRegisterUrl(String url,
                                           HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.HOUSEHOLD_REGISTER);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 手写文字识别
     * 支持对图片中的手写中文、手写数字进行检测和识别，
     *
     * @param url - 图片完整URL
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                recognize_granularity - 是否定位单字符位置，
     *                probability - 是否返回识别结果中每一行的置信度，默认为false，不返回置信度
     *                detect_direction - 是否检测图像朝向，默认不检测，即：false
     * @return JSONObject
     */
    public JSONObject handwritingUrl(String url,
                                     HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.HANDWRITING);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 飞机行程单识别
     * 支持对飞机行程单的24个字段进行结构化识别，包括电子客票号、印刷序号、姓名、始发站、目的站、航班号、日期、时间、票价、身份证号、承运人、
     * 民航发展基金、保险费、燃油附加费、其他税费、合计金额、填开日期、订票渠道、客票级别、座位等级、销售单位号、签注、免费行李、验证码。 同时，
     * 支持单张行程单上的多航班信息识别。
     *
     * @param url - 图片完整URL
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                multi_detect - 控制是否开启多航班信息识别功能,默认值：false
     * @return JSONObject
     */
    public JSONObject airTicketUrl(String url,
                                   HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.AIR_TICKET);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 通用机打发票
     * 支持对图片中的手写中文、手写数字进行检测和识别，
     *
     * @param url - 图片完整URL
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                location - 是否输出位置信息，true：输出位置信息，false：不输出位置信息，默认false
     * @return JSONObject
     */
    public JSONObject invoiceUrl(String url,
                                 HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.INVOICE);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 护照识别
     * 支持对图片中的手写中文、手写数字进行检测和识别，
     *
     * @param url - 图片完整URL
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject passportUrl(String url,
                                  HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.PASSPORT);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 网约车行程单识别
     * 对各大主要服务商的网约车行程单进行结构化识别，包括滴滴打车、花小猪打车、高德地图、曹操出行、阳光出行，支持识别服务商、
     * 行程开始时间、行程结束时间、车型、总金额等16 个关键字段。
     *
     *
     * @param image - 二进制图像数据
     *
     * @return JSONObject
     */
    public JSONObject onlineTaxiItinerary(byte[] image) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);

        request.setUri(OcrConsts.ONLINE_TAXI_ITINERARY);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 网约车行程单识别
     * 对各大主要服务商的网约车行程单进行结构化识别，包括滴滴打车、花小猪打车、高德地图、曹操出行、阳光出行，支持识别服务商、
     * 行程开始时间、行程结束时间、车型、总金额等16 个关键字段。
     * @param url - 图片完整URL路径
     *
     * @return JSONObject
     */
    public JSONObject onlineTaxiItineraryUrl(String url) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);

        request.setUri(OcrConsts.ONLINE_TAXI_ITINERARY);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 网约车行程单识别
     * 对各大主要服务商的网约车行程单进行结构化识别，包括滴滴打车、花小猪打车、高德地图、曹操出行、阳光出行，支持识别服务商、
     * 行程开始时间、行程结束时间、车型、总金额等16 个关键字段。
     *
     *
     * @param pdfFile - pdf文件二进制数据
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                pdf_file_num - 需要识别的PDF文件的对应页码，当 pdf_file 参数有效时，识别传入页码的对应页面内容，若不传入，则默认识别第 1 页
     * @return JSONObject
     */
    public JSONObject onlineTaxiItineraryPdf(byte[] pdfFile,
                                             HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(pdfFile);
        request.addBody("pdf_file", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.ONLINE_TAXI_ITINERARY);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 磅单识别
     * 结构化识别磅单的车牌号、打印时间、毛重、皮重、净重、发货单位、收货单位、单号8个关键字段，现阶段仅支持识别印刷体磅单
     *
     * @param image - 二进制图像数据
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                pdf_file_num - 需要识别的PDF文件的对应页码，当 pdf_file 参数有效时，识别传入页码的对应页面内容，若不传入，则默认识别第 1 页
     *                probability - 是否返回字段识别结果的置信度，默认为 false，可缺省
     *                - false：不返回字段识别结果的置信度
     *                - true：返回字段识别结果的置信度，包括字段识别结果中各字符置信度的平均值（average）和最小值（min）
     * @return JSONObject
     */
    public JSONObject weightNote(byte[] image,
                                 HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.WEIGHT_NOTE);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 磅单识别
     * 结构化识别磅单的车牌号、打印时间、毛重、皮重、净重、发货单位、收货单位、单号8个关键字段，现阶段仅支持识别印刷体磅单
     *
     * @param url - 图片完整URL路径
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                pdf_file_num - 需要识别的PDF文件的对应页码，当 pdf_file 参数有效时，识别传入页码的对应页面内容，若不传入，则默认识别第 1 页
     *                probability - 是否返回字段识别结果的置信度，默认为 false，可缺省
     *                - false：不返回字段识别结果的置信度
     *                - true：返回字段识别结果的置信度，包括字段识别结果中各字符置信度的平均值（average）和最小值（min）
     * @return JSONObject
     */
    public JSONObject weightNoteUrl(String url,
                                    HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.WEIGHT_NOTE);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 磅单识别
     * 结构化识别磅单的车牌号、打印时间、毛重、皮重、净重、发货单位、收货单位、单号8个关键字段，现阶段仅支持识别印刷体磅单
     *
     * @param pdfFile - 图片完整URL路径
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                pdf_file_num - 需要识别的PDF文件的对应页码，当 pdf_file 参数有效时，识别传入页码的对应页面内容，若不传入，则默认识别第 1 页
     *                probability - 是否返回字段识别结果的置信度，默认为 false，可缺省
     *                - false：不返回字段识别结果的置信度
     *                - true：返回字段识别结果的置信度，包括字段识别结果中各字符置信度的平均值（average）和最小值（min）
     * @return JSONObject
     */
    public JSONObject weightNotePdf(byte[] pdfFile,
                                    HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(pdfFile);
        request.addBody("pdf_file", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.WEIGHT_NOTE);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 医疗费用明细识别
     * 支持识别全国医疗费用明细的姓名、日期、病人ID、总金额等关键字段，支持识别费用明细项目清单，包含项目类型、项目名称、
     * 单价、数量、规格、金额，其中北京地区识别效果最佳。
     *
     *
     * @param image - 二进制图像数据
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                location - 是否返回字段的位置信息，默认为 false，可缺省
     *                - false：不返回字段位置信息
     *                - true：返回字段的位置信息，包括上边距（top）、左边距（left）、宽度（width）、高度（height）
     *                <p>
     *                probability - 是否返回字段识别结果的置信度，默认为 false，可缺省
     *                - false：不返回字段识别结果的置信度
     *                - true：返回字段识别结果的置信度，包括字段识别结果中各字符置信度的平均值（average）和最小值（min）
     * @return JSONObject
     */
    public JSONObject medicalDetail(byte[] image,
                                    HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.MEDICAL_DETAIL);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 医疗费用明细识别
     * 支持识别全国医疗费用明细的姓名、日期、病人ID、总金额等关键字段，支持识别费用明细项目清单，包含项目类型、项目名称、
     * 单价、数量、规格、金额，其中北京地区识别效果最佳。
     *
     *
     * @param url - 图片完整URL路径
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                location - 是否返回字段的位置信息，默认为 false，可缺省
     *                - false：不返回字段位置信息
     *                - true：返回字段的位置信息，包括上边距（top）、左边距（left）、宽度（width）、高度（height）
     *                <p>
     *                probability - 是否返回字段识别结果的置信度，默认为 false，可缺省
     *                - false：不返回字段识别结果的置信度
     *                - true：返回字段识别结果的置信度，包括字段识别结果中各字符置信度的平均值（average）和最小值（min）
     * @return JSONObject
     */
    public JSONObject medicalDetailUrl(String url,
                                       HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.MEDICAL_DETAIL);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 办公文档识别
     * https://ai.baidu.com/ai-doc/OCR/ykg9c09ji
     *
     * @param image   - 图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/ykg9c09ji#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject docAnalysisOffice(String image, HashMap<String, Object> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return docAnalysisOffice(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 办公文档识别
     * https://ai.baidu.com/ai-doc/OCR/ykg9c09ji
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/ykg9c09ji#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject docAnalysisOffice(byte[] image,
                                        HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.DOC_ANALYSIS_OFFICE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 办公文档识别
     * https://ai.baidu.com/ai-doc/OCR/ykg9c09ji
     *
     * @param url     - 图片完整url
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/ykg9c09ji#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject docAnalysisOfficeUrl(String url,
                                           HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.DOC_ANALYSIS_OFFICE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 办公文档识别
     * https://ai.baidu.com/ai-doc/OCR/ykg9c09ji
     *
     * @param pdf     - pdf文件路径
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/ykg9c09ji#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject docAnalysisOfficePdf(String pdf, int pdfFileNum, HashMap<String, Object> options) {
        try {
            byte[] data = Util.readFileByBytes(pdf);
            return docAnalysisOfficePdf(data, pdfFileNum, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 办公文档识别
     * https://ai.baidu.com/ai-doc/OCR/ykg9c09ji
     *
     * @param pdf     - 二进制pdf数据
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/ykg9c09ji#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject docAnalysisOfficePdf(byte[] pdf, int pdfFileNum,
                                           HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(pdf);
        request.addBody("pdf_file", base64Content);
        request.addBody("pdf_file_num", pdfFileNum);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.DOC_ANALYSIS_OFFICE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 印章识别
     * https://ai.baidu.com/ai-doc/OCR/Mk3h7y47a
     *
     * @param image - 图片文件地址
     * @return
     */
    public JSONObject seal(String image) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return seal(data);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 印章识别
     * https://ai.baidu.com/ai-doc/OCR/Mk3h7y47a
     *
     * @param image - 二进制图像数据
     * @return
     */
    public JSONObject seal(byte[] image) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        request.setUri(OcrConsts.SEAL);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 印章识别
     * https://ai.baidu.com/ai-doc/OCR/Mk3h7y47a
     *
     * @param url - 图片完整url
     * @return
     */
    public JSONObject sealUrl(String url) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        request.setUri(OcrConsts.SEAL);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 印章识别
     * https://ai.baidu.com/ai-doc/OCR/Mk3h7y47a
     *
     * @param pdf - pdf路径
     * @return
     */
    public JSONObject sealPdf(String pdf, int pdfFileNum) {
        try {
            byte[] data = Util.readFileByBytes(pdf);
            return sealPdf(data, pdfFileNum);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 印章识别
     * https://ai.baidu.com/ai-doc/OCR/Mk3h7y47a
     *
     * @param pdf - 二进制pdf数据
     * @return
     */
    public JSONObject sealPdf(byte[] pdf, int pdfFileNum) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(pdf);
        request.addBody("pdf_file", base64Content);
        request.addBody("pdf_file_num", pdfFileNum);
        request.setUri(OcrConsts.SEAL);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 身份证混贴识别
     * https://ai.baidu.com/ai-doc/OCR/akp3gfbmc
     *
     * @param image   - 图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/akp3gfbmc#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject multiIdcard(String image,
                                  HashMap<String, Object> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return multiIdcard(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 身份证混贴识别
     * https://ai.baidu.com/ai-doc/OCR/akp3gfbmc
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/akp3gfbmc#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject multiIdcard(byte[] image,
                                  HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.MULTI_IDCARD);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 身份证混贴识别
     * https://ai.baidu.com/ai-doc/OCR/akp3gfbmc
     *
     * @param url     - 图片完整url
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/akp3gfbmc#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject multiIdcardUrl(String url,
                                     HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.MULTI_IDCARD);
        postOperation(request);
        return requestServer(request);
    }

//    /**
//     * 社保卡识别
//     * https://ai.baidu.com/ai-doc/OCR/lkto93055
//     *
//     * @param image - 图片路径
//     * @return
//     */
//    public JSONObject socialSecurityCard(String image) {
//        try {
//            byte[] data = Util.readFileByBytes(image);
//            return socialSecurityCard(data);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return AipError.IMAGE_READ_ERROR.toJsonResult();
//        }
//    }
//
//    /**
//     * 社保卡识别
//     * https://ai.baidu.com/ai-doc/OCR/lkto93055
//     *
//     * @param image - 二进制图像数据
//     * @return
//     */
//    public JSONObject socialSecurityCard(byte[] image) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        String base64Content = Base64Util.encode(image);
//        request.addBody("image", base64Content);
//        request.setUri(OcrConsts.SOCIAL_SECURITY_CARD);
//        postOperation(request);
//        return requestServer(request);
//    }
//
//    /**
//     * 社保卡识别
//     * https://ai.baidu.com/ai-doc/OCR/lkto93055
//     *
//     * @param url - 图片完整url
//     * @return
//     */
//    public JSONObject socialSecurityCardUrl(String url) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        request.addBody("url", url);
//        request.setUri(OcrConsts.SOCIAL_SECURITY_CARD);
//        postOperation(request);
//        return requestServer(request);
//    }

//    /**
//     * 多卡证类别检测
//     * https://ai.baidu.com/ai-doc/OCR/nkbq6wxxy
//     *
//     * @param image - 图片路径
//     * @return
//     */
//    public JSONObject multiCardClassify(String image) {
//        try {
//            byte[] data = Util.readFileByBytes(image);
//            return multiCardClassify(data);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return AipError.IMAGE_READ_ERROR.toJsonResult();
//        }
//    }
//
//    /**
//     * 多卡证类别检测
//     * https://ai.baidu.com/ai-doc/OCR/nkbq6wxxy
//     *
//     * @param image - 二进制图像数据
//     * @return
//     */
//    public JSONObject multiCardClassify(byte[] image) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        String base64Content = Base64Util.encode(image);
//        request.addBody("image", base64Content);
//        request.setUri(OcrConsts.MULTI_CARD_CLASSIFY);
//        postOperation(request);
//        return requestServer(request);
//    }
//
//    /**
//     * 多卡证类别检测
//     * https://ai.baidu.com/ai-doc/OCR/nkbq6wxxy
//     *
//     * @param url - 图片完整url
//     * @return
//     */
//    public JSONObject multiCardClassifyUrl(String url) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        request.addBody("url", url);
//        request.setUri(OcrConsts.MULTI_CARD_CLASSIFY);
//        postOperation(request);
//        return requestServer(request);
//    }

    /**
     * 车辆证照混贴识别
     * https://ai.baidu.com/ai-doc/OCR/Kksfsbngb
     *
     * @param image   - 图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/Kksfsbngb#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject mixedMultiVehicle(String image,
                                        HashMap<String, Object> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return mixedMultiVehicle(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 车辆证照混贴识别
     * https://ai.baidu.com/ai-doc/OCR/Kksfsbngb
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/Kksfsbngb#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject mixedMultiVehicle(byte[] image,
                                        HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.MIXED_MULTI_VEHICLE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 车辆证照混贴识别
     * https://ai.baidu.com/ai-doc/OCR/Kksfsbngb
     *
     * @param url     - 图片完整URL路径
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/Kksfsbngb#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject mixedMultiVehicleUrl(String url,
                                           HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.MIXED_MULTI_VEHICLE);
        postOperation(request);
        return requestServer(request);
    }

//    /**
//     * 二手车销售发票识别
//     * https://ai.baidu.com/ai-doc/OCR/8knr8rrj8
//     *
//     * @param image - 图片路径
//     * @return
//     */
//    public JSONObject usedVehicleInvoice(String image) {
//        try {
//            byte[] data = Util.readFileByBytes(image);
//            return usedVehicleInvoice(data);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return AipError.IMAGE_READ_ERROR.toJsonResult();
//        }
//    }
//
//    /**
//     * 二手车销售发票识别
//     * https://ai.baidu.com/ai-doc/OCR/8knr8rrj8
//     *
//     * @param image - 二进制图像数据
//     * @return
//     */
//    public JSONObject usedVehicleInvoice(byte[] image) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        String base64Content = Base64Util.encode(image);
//        request.addBody("image", base64Content);
//        request.setUri(OcrConsts.USED_VEHICLE_INVOICE);
//        postOperation(request);
//        return requestServer(request);
//    }
//
//    /**
//     * 二手车销售发票识别
//     * https://ai.baidu.com/ai-doc/OCR/8knr8rrj8
//     *
//     * @param url - 图片完整URL路径
//     * @return
//     */
//    public JSONObject usedVehicleInvoiceUrl(String url) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        request.addBody("url", url);
//        request.setUri(OcrConsts.USED_VEHICLE_INVOICE);
//        postOperation(request);
//        return requestServer(request);
//    }

    /**
     * 机动车登记证书识别
     * https://ai.baidu.com/ai-doc/OCR/qknzs5zzo
     *
     * @param image - 图片路径
     * @return
     */
    public JSONObject vehicleRegistrationCertificate(String image) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return vehicleRegistrationCertificate(data);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 机动车登记证书识别
     * https://ai.baidu.com/ai-doc/OCR/qknzs5zzo
     *
     * @param image - 二进制图像数据
     * @return
     */
    public JSONObject vehicleRegistrationCertificate(byte[] image) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        request.setUri(OcrConsts.VEHICLE_REGISTRATION_CERTIFICATE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 机动车登记证书识别
     * https://ai.baidu.com/ai-doc/OCR/qknzs5zzo
     *
     * @param url - 图片完整URL路径
     * @return
     */
    public JSONObject vehicleRegistrationCertificateUrl(String url) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        request.setUri(OcrConsts.VEHICLE_REGISTRATION_CERTIFICATE);
        postOperation(request);
        return requestServer(request);
    }

//    /**
//     * 快递面单识别
//     * https://ai.baidu.com/ai-doc/OCR/Ekwkggqa5
//     *
//     * @param image - 图片路径
//     * @return
//     */
//    public JSONObject waybill(String image) {
//        try {
//            byte[] data = Util.readFileByBytes(image);
//            return waybill(data);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return AipError.IMAGE_READ_ERROR.toJsonResult();
//        }
//    }
//
//    /**
//     * 快递面单识别
//     * https://ai.baidu.com/ai-doc/OCR/Ekwkggqa5
//     *
//     * @param image - 二进制图像数据
//     * @return
//     */
//    public JSONObject waybill(byte[] image) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        String base64Content = Base64Util.encode(image);
//        request.addBody("image", base64Content);
//        request.setUri(OcrConsts.WAYBILL);
//        postOperation(request);
//        return requestServer(request);
//    }
//
//    /**
//     * 快递面单识别
//     * https://ai.baidu.com/ai-doc/OCR/Ekwkggqa5
//     *
//     * @param url - 图片完整URL路径
//     * @return
//     */
//    public JSONObject waybillUrl(String url) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        request.addBody("url", url);
//        request.setUri(OcrConsts.WAYBILL);
//        postOperation(request);
//        return requestServer(request);
//    }

//    /**
//     * 道路运输证识别
//     * https://ai.baidu.com/ai-doc/OCR/ol07rjylw
//     *
//     * @param image - 图片路径
//     * @return
//     */
//    public JSONObject roadTransportCertificate(String image) {
//        try {
//            byte[] data = Util.readFileByBytes(image);
//            return roadTransportCertificate(data);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return AipError.IMAGE_READ_ERROR.toJsonResult();
//        }
//    }
//
//    /**
//     * 道路运输证识别
//     * https://ai.baidu.com/ai-doc/OCR/ol07rjylw
//     *
//     * @param image - 二进制图像数据
//     * @return
//     */
//    public JSONObject roadTransportCertificate(byte[] image) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        String base64Content = Base64Util.encode(image);
//        request.addBody("image", base64Content);
//        request.setUri(OcrConsts.ROAD_TRANSPORT_CERTIFICATE);
//        postOperation(request);
//        return requestServer(request);
//    }
//
//    /**
//     * 道路运输证识别
//     * https://ai.baidu.com/ai-doc/OCR/ol07rjylw
//     *
//     * @param url - 图片完整url
//     * @return
//     */
//    public JSONObject roadTransportCertificateUrl(String url) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        request.addBody("url", url);
//        request.setUri(OcrConsts.ROAD_TRANSPORT_CERTIFICATE);
//        postOperation(request);
//        return requestServer(request);
//    }
//
//    /**
//     * 道路运输证识别
//     * https://ai.baidu.com/ai-doc/OCR/ol07rjylw
//     *
//     * @param pdf - pdf路径
//     * @return
//     */
//    public JSONObject roadTransportCertificatePdf(String pdf, int pdfFileNum) {
//        try {
//            byte[] data = Util.readFileByBytes(pdf);
//            return roadTransportCertificatePdf(data, pdfFileNum);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return AipError.IMAGE_READ_ERROR.toJsonResult();
//        }
//    }
//
//    /**
//     * 道路运输证识别
//     * https://ai.baidu.com/ai-doc/OCR/ol07rjylw
//     *
//     * @param pdf - 二进制pdf数据
//     * @return
//     */
//    public JSONObject roadTransportCertificatePdf(byte[] pdf, int pdfFileNum) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        String base64Content = Base64Util.encode(pdf);
//        request.addBody("pdf_file", base64Content);
//        request.addBody("pdf_file_num", pdfFileNum);
//        request.setUri(OcrConsts.ROAD_TRANSPORT_CERTIFICATE);
//        postOperation(request);
//        return requestServer(request);
//    }

    /**
     * 智能财务票据识别
     * https://ai.baidu.com/ai-doc/OCR/7ktb8md0j
     *
     * @param image   - 图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/7ktb8md0j#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject multipleInvoice(String image,
                                      HashMap<String, Object> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return multipleInvoice(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 智能财务票据识别
     * https://ai.baidu.com/ai-doc/OCR/7ktb8md0j
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/7ktb8md0j#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject multipleInvoice(byte[] image,
                                      HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.MULTIPLE_INVOICE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 智能财务票据识别
     * https://ai.baidu.com/ai-doc/OCR/7ktb8md0j
     *
     * @param url     - 图片完整url
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/7ktb8md0j#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject multipleInvoiceUrl(String url,
                                         HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.MULTIPLE_INVOICE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 智能财务票据识别
     * https://ai.baidu.com/ai-doc/OCR/7ktb8md0j
     *
     * @param pdf     - pdf路径
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/7ktb8md0j#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject multipleInvoicePdf(String pdf, int pdfFileNum,
                                         HashMap<String, Object> options) {
        try {
            byte[] data = Util.readFileByBytes(pdf);
            return multipleInvoicePdf(data, pdfFileNum, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 智能财务票据识别
     * https://ai.baidu.com/ai-doc/OCR/7ktb8md0j
     *
     * @param pdf     - 二进制pdf数据
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/7ktb8md0j#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject multipleInvoicePdf(byte[] pdf, int pdfFileNum,
                                         HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(pdf);
        request.addBody("pdf_file", base64Content);
        request.addBody("pdf_file_num", pdfFileNum);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.MULTIPLE_INVOICE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 增值税发票验真
     * https://ai.baidu.com/ai-doc/OCR/cklbnrnwe
     *
     * @param invoiceCode 发票代码。全电发票（专用发票）、全电发票（普通发票）此参数可为空，其他类型发票均不可为空
     * @param invoiceNum  发票号码
     * @param invoiceDate 开票日期。格式YYYYMMDD，例：20210101
     * @param invoiceType 发票种类
     *                    -增值税专用发票：special_vat_invoice
     *                    -增值税电子专用发票：elec_special_vat_invoice
     *                    -增值税普通发票：normal_invoice
     *                    -增值税普通发票（电子）：elec_normal_invoice
     *                    -增值税普通发票（卷式）：roll_normal_invoice
     *                    -通行费增值税电子普通发票：toll_elec_normal_invoice
     *                    -区块链电子发票（目前仅支持深圳地区）：blockchain_invoice
     *                    -全电发票（专用发票）：elec_invoice_special
     *                    -全电发票（普通发票）：elec_invoice_normal
     *                    -货运运输业增值税专用发票：special_freight_transport_invoice
     *                    -机动车销售发票：motor_vehicle_invoice
     *                    -二手车销售发票：used_vehicle_invoice
     * @param checkCode 校验码。填写发票校验码后6位，增值税电子专票、普票、电子普票、卷票、区块链电子发票、
     *                  通行费增值税电子普通发票此参数不可为空，其他类型发票可为空
     * @param totalAmount 发票金额。增值税专票、电子专票、货运专票、机动车销售发票填写不含税金额；
     *                      二手车销售发票填写车价合计；
     *                      全电发票（专用发票）、全电发票（普通发票）填写价税合计金额，其他类型发票可为空
     * @return
     */
    public JSONObject vatInvoiceVerification(String invoiceCode, String invoiceNum, String invoiceDate,
                                             String invoiceType, String checkCode, String totalAmount) {
        AipRequest request = new AipRequest();
        preOperation(request);
        request.addBody("invoice_code", invoiceCode);
        request.addBody("invoice_num", invoiceNum);
        request.addBody("invoice_date", invoiceDate);
        request.addBody("invoice_type", invoiceType);
        request.addBody("check_code", checkCode);
        request.addBody("total_amount", totalAmount);
        request.setUri(OcrConsts.VAT_INVOICE_VERIFICATION);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 汽车票识别
     * https://ai.baidu.com/ai-doc/OCR/Kkblx01ww
     *
     * @param image - 图片路径
     * @return
     */
    public JSONObject busTicket(String image) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return busTicket(data);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 汽车票识别
     * https://ai.baidu.com/ai-doc/OCR/Kkblx01ww
     *
     * @param image - 二进制图像数据
     * @return
     */
    public JSONObject busTicket(byte[] image) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        request.setUri(OcrConsts.BUS_TICKET);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 汽车票识别
     * https://ai.baidu.com/ai-doc/OCR/Kkblx01ww
     *
     * @param url - 图片完整url
     * @return
     */
    public JSONObject busTicketUrl(String url) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        request.setUri(OcrConsts.BUS_TICKET);
        postOperation(request);
        return requestServer(request);
    }

//    /**
//     * 过路过桥费发票识别
//     * https://ai.baidu.com/ai-doc/OCR/1kbpyx8js
//     *
//     * @param image - 图片路径
//     * @return
//     */
//    public JSONObject tollInvoice(String image) {
//        try {
//            byte[] data = Util.readFileByBytes(image);
//            return tollInvoice(data);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return AipError.IMAGE_READ_ERROR.toJsonResult();
//        }
//    }
//
//    /**
//     * 过路过桥费发票识别
//     * https://ai.baidu.com/ai-doc/OCR/1kbpyx8js
//     *
//     * @param image - 二进制图像数据
//     * @return
//     */
//    public JSONObject tollInvoice(byte[] image) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        String base64Content = Base64Util.encode(image);
//        request.addBody("image", base64Content);
//        request.setUri(OcrConsts.TOLL_INVOICE);
//        postOperation(request);
//        return requestServer(request);
//    }
//
//    /**
//     * 过路过桥费发票识别
//     * https://ai.baidu.com/ai-doc/OCR/1kbpyx8js
//     *
//     * @param url - 图片完整url
//     * @return
//     */
//    public JSONObject tollInvoiceUrl(String url) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        request.addBody("url", url);
//        request.setUri(OcrConsts.TOLL_INVOICE);
//        postOperation(request);
//        return requestServer(request);
//    }

//    /**
//     * 船票识别
//     * https://ai.baidu.com/ai-doc/OCR/nkmcwp3ne
//     *
//     * @param image - 图片路径
//     * @return
//     */
//    public JSONObject ferryTicket(String image) {
//        try {
//            byte[] data = Util.readFileByBytes(image);
//            return ferryTicket(data);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return AipError.IMAGE_READ_ERROR.toJsonResult();
//        }
//    }
//
//    /**
//     * 船票识别
//     * https://ai.baidu.com/ai-doc/OCR/nkmcwp3ne
//     *
//     * @param image - 二进制图像数据
//     * @return
//     */
//    public JSONObject ferryTicket(byte[] image) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        String base64Content = Base64Util.encode(image);
//        request.addBody("image", base64Content);
//        request.setUri(OcrConsts.FERRY_TICKET);
//        postOperation(request);
//        return requestServer(request);
//    }
//
//    /**
//     * 船票识别
//     * https://ai.baidu.com/ai-doc/OCR/nkmcwp3ne
//     *
//     * @param url - 图片完整url
//     * @return
//     */
//    public JSONObject ferryTicketUrl(String url) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        request.addBody("url", url);
//        request.setUri(OcrConsts.FERRY_TICKET);
//        postOperation(request);
//        return requestServer(request);
//    }

//    /**
//     * 购物小票识别
//     * https://ai.baidu.com/ai-doc/OCR/3kwvk8y36
//     *
//     * @param image   - 图片路径
//     * @param options - 可选参数对象，key: value都为string类型
//     *                https://ai.baidu.com/ai-doc/OCR/3kwvk8y36#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
//     * @return
//     */
//    public JSONObject shoppingReceipt(String image,
//                                      HashMap<String, Object> options) {
//        try {
//            byte[] data = Util.readFileByBytes(image);
//            return shoppingReceipt(data, options);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return AipError.IMAGE_READ_ERROR.toJsonResult();
//        }
//    }
//
//    /**
//     * 购物小票识别
//     * https://ai.baidu.com/ai-doc/OCR/3kwvk8y36
//     *
//     * @param image   - 二进制图像数据
//     * @param options - 可选参数对象，key: value都为string类型
//     *                https://ai.baidu.com/ai-doc/OCR/3kwvk8y36#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
//     * @return
//     */
//    public JSONObject shoppingReceipt(byte[] image,
//                                      HashMap<String, Object> options) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        String base64Content = Base64Util.encode(image);
//        request.addBody("image", base64Content);
//        if (options != null) {
//            request.addBody(options);
//        }
//        request.setUri(OcrConsts.SHOPPING_RECEIPT);
//        postOperation(request);
//        return requestServer(request);
//    }
//
//    /**
//     * 购物小票识别
//     * https://ai.baidu.com/ai-doc/OCR/3kwvk8y36
//     *
//     * @param url     - 图片完整url
//     * @param options - 可选参数对象，key: value都为string类型
//     *                https://ai.baidu.com/ai-doc/OCR/3kwvk8y36#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
//     * @return
//     */
//    public JSONObject shoppingReceiptUrl(String url,
//                                         HashMap<String, Object> options) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        request.addBody("url", url);
//        if (options != null) {
//            request.addBody(options);
//        }
//        request.setUri(OcrConsts.SHOPPING_RECEIPT);
//        postOperation(request);
//        return requestServer(request);
//    }
//
//    /**
//     * 购物小票识别
//     * https://ai.baidu.com/ai-doc/OCR/3kwvk8y36
//     *
//     * @param pdf     - pdf路径
//     * @param options - 可选参数对象，key: value都为string类型
//     *                https://ai.baidu.com/ai-doc/OCR/3kwvk8y36#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
//     * @return
//     */
//    public JSONObject shoppingReceiptPdf(String pdf, int pdfFileNum,
//                                         HashMap<String, Object> options) {
//        try {
//            byte[] data = Util.readFileByBytes(pdf);
//            return shoppingReceiptPdf(data, pdfFileNum, options);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return AipError.IMAGE_READ_ERROR.toJsonResult();
//        }
//    }
//
//    /**
//     * 购物小票识别
//     * https://ai.baidu.com/ai-doc/OCR/3kwvk8y36
//     *
//     * @param pdf     - 二进制pdf数据
//     * @param options - 可选参数对象，key: value都为string类型
//     *                https://ai.baidu.com/ai-doc/OCR/3kwvk8y36#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
//     * @return
//     */
//    public JSONObject shoppingReceiptPdf(byte[] pdf, int pdfFileNum,
//                                         HashMap<String, Object> options) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        String base64Content = Base64Util.encode(pdf);
//        request.addBody("pdf_file", base64Content);
//        request.addBody("pdf_file_num", pdfFileNum);
//        if (options != null) {
//            request.addBody(options);
//        }
//        request.setUri(OcrConsts.SHOPPING_RECEIPT);
//        postOperation(request);
//        return requestServer(request);
//    }

    /**
     * 医疗发票识别
     * https://ai.baidu.com/ai-doc/OCR/yke30j1hq
     *
     * @param image   - 图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/yke30j1hq#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject medicalInvoice(String image,
                                     HashMap<String, Object> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return medicalInvoice(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 医疗发票识别
     * https://ai.baidu.com/ai-doc/OCR/yke30j1hq
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/yke30j1hq#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject medicalInvoice(byte[] image,
                                     HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.MEDICAL_INVOICE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 医疗发票识别
     * https://ai.baidu.com/ai-doc/OCR/yke30j1hq
     *
     * @param url     - 图片完整url
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/yke30j1hq#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject medicalInvoiceUrl(String url,
                                        HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.MEDICAL_INVOICE);
        postOperation(request);
        return requestServer(request);
    }

//    /**
//     * 医疗费用结算单识别
//     * https://ai.baidu.com/ai-doc/OCR/Jke30ki7d
//     *
//     * @param image   - 图片路径
//     * @param options - 可选参数对象，key: value都为string类型
//     *                https://ai.baidu.com/ai-doc/OCR/Jke30ki7d#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
//     * @return
//     */
//    public JSONObject medicalStatement(String image,
//                                       HashMap<String, Object> options) {
//        try {
//            byte[] data = Util.readFileByBytes(image);
//            return medicalStatement(data, options);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return AipError.IMAGE_READ_ERROR.toJsonResult();
//        }
//    }
//
//    /**
//     * 医疗费用结算单识别
//     * https://ai.baidu.com/ai-doc/OCR/Jke30ki7d
//     *
//     * @param image   - 二进制图像数据
//     * @param options - 可选参数对象，key: value都为string类型
//     *                https://ai.baidu.com/ai-doc/OCR/Jke30ki7d#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
//     * @return
//     */
//    public JSONObject medicalStatement(byte[] image,
//                                       HashMap<String, Object> options) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        String base64Content = Base64Util.encode(image);
//        request.addBody("image", base64Content);
//        if (options != null) {
//            request.addBody(options);
//        }
//        request.setUri(OcrConsts.MEDICAL_STATEMENT);
//        postOperation(request);
//        return requestServer(request);
//    }
//
//    /**
//     * 医疗费用结算单识别
//     * https://ai.baidu.com/ai-doc/OCR/Jke30ki7d
//     *
//     * @param url     - 图片完整url
//     * @param options - 可选参数对象，key: value都为string类型
//     *                https://ai.baidu.com/ai-doc/OCR/Jke30ki7d#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
//     * @return
//     */
//    public JSONObject medicalStatementUrl(String url,
//                                          HashMap<String, Object> options) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        request.addBody("url", url);
//        if (options != null) {
//            request.addBody(options);
//        }
//        request.setUri(OcrConsts.MEDICAL_STATEMENT);
//        postOperation(request);
//        return requestServer(request);
//    }
//
//    /**
//     * 医疗检验报告单识别
//     * https://ai.baidu.com/ai-doc/OCR/Ekvakju92
//     *
//     * @param image   - 图片路径
//     * @param options - 可选参数对象，key: value都为string类型
//     *                https://ai.baidu.com/ai-doc/OCR/Ekvakju92#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
//     * @return
//     */
//    public JSONObject medicalReportDetection(String image,
//                                             HashMap<String, Object> options) {
//        try {
//            byte[] data = Util.readFileByBytes(image);
//            return medicalReportDetection(data, options);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return AipError.IMAGE_READ_ERROR.toJsonResult();
//        }
//    }
//
//    /**
//     * 医疗检验报告单识别
//     * https://ai.baidu.com/ai-doc/OCR/Ekvakju92
//     *
//     * @param image   - 二进制图像数据
//     * @param options - 可选参数对象，key: value都为string类型
//     *                https://ai.baidu.com/ai-doc/OCR/Ekvakju92#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
//     * @return
//     */
//    public JSONObject medicalReportDetection(byte[] image,
//                                             HashMap<String, Object> options) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        String base64Content = Base64Util.encode(image);
//        request.addBody("image", base64Content);
//        if (options != null) {
//            request.addBody(options);
//        }
//        request.setUri(OcrConsts.MEDICAL_REPORT_DETECTION);
//        postOperation(request);
//        return requestServer(request);
//    }
//
//    /**
//     * 医疗检验报告单识别
//     * https://ai.baidu.com/ai-doc/OCR/Ekvakju92
//     *
//     * @param url     - 图片完整url
//     * @param options - 可选参数对象，key: value都为string类型
//     *                https://ai.baidu.com/ai-doc/OCR/Ekvakju92#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
//     * @return
//     */
//    public JSONObject medicalReportDetectionUrl(String url,
//                                                HashMap<String, Object> options) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        request.addBody("url", url);
//        if (options != null) {
//            request.addBody(options);
//        }
//        request.setUri(OcrConsts.MEDICAL_REPORT_DETECTION);
//        postOperation(request);
//        return requestServer(request);
//    }
//
//    /**
//     * 病案首页识别
//     * https://ai.baidu.com/ai-doc/OCR/1ke30k2s2
//     *
//     * @param image   - 图片路径
//     * @param options - 可选参数对象，key: value都为string类型
//     *                https://ai.baidu.com/ai-doc/OCR/1ke30k2s2#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
//     * @return
//     */
//    public JSONObject medicalRecord(String image,
//                                    HashMap<String, Object> options) {
//        try {
//            byte[] data = Util.readFileByBytes(image);
//            return medicalRecord(data, options);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return AipError.IMAGE_READ_ERROR.toJsonResult();
//        }
//    }
//
//    /**
//     * 病案首页识别
//     * https://ai.baidu.com/ai-doc/OCR/1ke30k2s2
//     *
//     * @param image   - 二进制图像数据
//     * @param options - 可选参数对象，key: value都为string类型
//     *                https://ai.baidu.com/ai-doc/OCR/1ke30k2s2#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
//     * @return
//     */
//    public JSONObject medicalRecord(byte[] image,
//                                    HashMap<String, Object> options) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        String base64Content = Base64Util.encode(image);
//        request.addBody("image", base64Content);
//        if (options != null) {
//            request.addBody(options);
//        }
//        request.setUri(OcrConsts.MEDICAL_RECORD);
//        postOperation(request);
//        return requestServer(request);
//    }
//
//    /**
//     * 病案首页识别
//     * https://ai.baidu.com/ai-doc/OCR/1ke30k2s2
//     *
//     * @param url     - 图片完整url
//     * @param options - 可选参数对象，key: value都为string类型
//     *                https://ai.baidu.com/ai-doc/OCR/1ke30k2s2#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
//     * @return
//     */
//    public JSONObject medicalRecordUrl(String url,
//                                       HashMap<String, Object> options) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        request.addBody("url", url);
//        if (options != null) {
//            request.addBody(options);
//        }
//        request.setUri(OcrConsts.MEDICAL_RECORD);
//        postOperation(request);
//        return requestServer(request);
//    }
//
//    /**
//     * 出院小结识别
//     * https://ai.baidu.com/ai-doc/OCR/Wkwwy4y4q
//     *
//     * @param image   - 图片路径
//     * @param options - 可选参数对象，key: value都为string类型
//     *                https://ai.baidu.com/ai-doc/OCR/Wkwwy4y4q#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
//     * @return
//     */
//    public JSONObject medicalSummary(String image,
//                                     HashMap<String, Object> options) {
//        try {
//            byte[] data = Util.readFileByBytes(image);
//            return medicalSummary(data, options);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return AipError.IMAGE_READ_ERROR.toJsonResult();
//        }
//    }
//
//    /**
//     * 出院小结识别
//     * https://ai.baidu.com/ai-doc/OCR/Wkwwy4y4q
//     *
//     * @param image   - 二进制图像数据
//     * @param options - 可选参数对象，key: value都为string类型
//     *                https://ai.baidu.com/ai-doc/OCR/Wkwwy4y4q#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
//     * @return
//     */
//    public JSONObject medicalSummary(byte[] image,
//                                     HashMap<String, Object> options) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        String base64Content = Base64Util.encode(image);
//        request.addBody("image", base64Content);
//        if (options != null) {
//            request.addBody(options);
//        }
//        request.setUri(OcrConsts.MEDICAL_SUMMARY);
//        postOperation(request);
//        return requestServer(request);
//    }
//
//    /**
//     * 出院小结识别
//     * https://ai.baidu.com/ai-doc/OCR/Wkwwy4y4q
//     *
//     * @param url     - 图片完整url
//     * @param options - 可选参数对象，key: value都为string类型
//     *                https://ai.baidu.com/ai-doc/OCR/Wkwwy4y4q#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
//     * @return
//     */
//    public JSONObject medicalSummaryUrl(String url,
//                                        HashMap<String, Object> options) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        request.addBody("url", url);
//        if (options != null) {
//            request.addBody(options);
//        }
//        request.setUri(OcrConsts.MEDICAL_SUMMARY);
//        postOperation(request);
//        return requestServer(request);
//    }
//
//    /**
//     * 医疗票据类别检测
//     * https://ai.baidu.com/ai-doc/OCR/zkvriu3sh
//     *
//     * @param image - 图片路径
//     * @return
//     */
//    public JSONObject medicalReciptsClassify(String image) {
//        try {
//            byte[] data = Util.readFileByBytes(image);
//            return medicalReciptsClassify(data);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return AipError.IMAGE_READ_ERROR.toJsonResult();
//        }
//    }
//
//    /**
//     * 医疗票据类别检测
//     * https://ai.baidu.com/ai-doc/OCR/zkvriu3sh
//     *
//     * @param image - 二进制图像数据
//     * @return
//     */
//    public JSONObject medicalReciptsClassify(byte[] image) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        String base64Content = Base64Util.encode(image);
//        request.addBody("image", base64Content);
//        request.setUri(OcrConsts.MEDICAL_RECIPTS_CLASSIFY);
//        postOperation(request);
//        return requestServer(request);
//    }
//
//    /**
//     * 医疗票据类别检测
//     * https://ai.baidu.com/ai-doc/OCR/zkvriu3sh
//     *
//     * @param url - 图片完整url
//     * @return
//     */
//    public JSONObject medicalReciptsClassifyUrl(String url) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        request.addBody("url", url);
//        request.setUri(OcrConsts.MEDICAL_RECIPTS_CLASSIFY);
//        postOperation(request);
//        return requestServer(request);
//    }

    /**
     * 公式识别
     * https://ai.baidu.com/ai-doc/OCR/Ok3h7xxva
     *
     * @param image   - 图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/Ok3h7xxva#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject formula(String image,
                              HashMap<String, Object> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return formula(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 公式识别
     * https://ai.baidu.com/ai-doc/OCR/Ok3h7xxva
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/Ok3h7xxva#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject formula(byte[] image,
                              HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.FORMULA);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 公式识别
     * https://ai.baidu.com/ai-doc/OCR/Ok3h7xxva
     *
     * @param url     - 图片完整url
     * @param options - 可选参数对象，key: value都为string类型
     *                https://ai.baidu.com/ai-doc/OCR/Ok3h7xxva#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
     * @return
     */
    public JSONObject formulaUrl(String url,
                                 HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(OcrConsts.FORMULA);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 通信行程卡识别
     * https://ai.baidu.com/ai-doc/OCR/Nksg89dkc
     *
     * @param image - 图片路径
     * @return
     */
    public JSONObject travelCard(String image) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return travelCard(data);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 通信行程卡识别
     * https://ai.baidu.com/ai-doc/OCR/Nksg89dkc
     *
     * @param image - 二进制图像数据
     * @return
     */
    public JSONObject travelCard(byte[] image) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        request.setUri(OcrConsts.TRAVEL_CARD);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 门脸文字识别
     * https://ai.baidu.com/ai-doc/OCR/wk5hw3cvo
     *
     * @param image - 图片路径
     * @return
     */
    public JSONObject facade(String image) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return facade(data);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 门脸文字识别
     * https://ai.baidu.com/ai-doc/OCR/wk5hw3cvo
     *
     * @param image - 二进制图像数据
     * @return
     */
    public JSONObject facade(byte[] image) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        request.setUri(OcrConsts.FACADE);
        postOperation(request);
        return requestServer(request);
    }

//    /**
//     * 智能结构化识别
//     * https://ai.baidu.com/ai-doc/OCR/Qke3nkykj
//     *
//     * @param image   - 图片路径
//     * @param options - 可选参数对象，key: value都为string类型
//     *                https://ai.baidu.com/ai-doc/OCR/Qke3nkykj#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
//     * @return
//     */
//    public JSONObject intelligentOcr(String image,
//                                     HashMap<String, Object> options) {
//        try {
//            byte[] data = Util.readFileByBytes(image);
//            return intelligentOcr(data, options);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return AipError.IMAGE_READ_ERROR.toJsonResult();
//        }
//    }
//
//    /**
//     * 智能结构化识别
//     * https://ai.baidu.com/ai-doc/OCR/Qke3nkykj
//     *
//     * @param image   - 二进制图像数据
//     * @param options - 可选参数对象，key: value都为string类型
//     *                https://ai.baidu.com/ai-doc/OCR/Qke3nkykj#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
//     * @return
//     */
//    public JSONObject intelligentOcr(byte[] image,
//                                     HashMap<String, Object> options) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        String base64Content = Base64Util.encode(image);
//        request.addBody("image", base64Content);
//        if (options != null) {
//            request.addBody(options);
//        }
//        request.setUri(OcrConsts.INTELLIGENT_OCR);
//        postOperation(request);
//        return requestServer(request);
//    }
//
//    /**
//     * 智能结构化识别
//     * https://ai.baidu.com/ai-doc/OCR/Qke3nkykj
//     *
//     * @param url     - 图片完整url
//     * @param options - 可选参数对象，key: value都为string类型
//     *                https://ai.baidu.com/ai-doc/OCR/Qke3nkykj#%E8%AF%B7%E6%B1%82%E8%AF%B4%E6%98%8E
//     * @return
//     */
//    public JSONObject intelligentOcrUrl(String url,
//                                        HashMap<String, Object> options) {
//        AipRequest request = new AipRequest();
//        preOperation(request);
//
//        request.addBody("url", url);
//        if (options != null) {
//            request.addBody(options);
//        }
//        request.setUri(OcrConsts.INTELLIGENT_OCR);
//        postOperation(request);
//        return requestServer(request);
//    }
}
