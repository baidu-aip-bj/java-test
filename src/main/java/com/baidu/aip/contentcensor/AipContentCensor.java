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
package com.baidu.aip.contentcensor;

import com.baidu.aip.client.BaseClient;
import com.baidu.aip.error.AipError;
import com.baidu.aip.http.AipRequest;
import com.baidu.aip.http.EBodyFormat;
import com.baidu.aip.http.Headers;
import com.baidu.aip.http.HttpContentType;
import com.baidu.aip.util.Base64Util;
import com.baidu.aip.util.ImageUtil;
import com.baidu.aip.util.Util;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AipContentCensor extends BaseClient {

    public AipContentCensor(String appId, String aipKey, String aipToken) {
        super(appId, aipKey, aipToken);
    }

    /**
     * 反馈接口
     *
     * @param reportData 反馈图片识别结果好坏的json数组
     * @return JSONObject
     */
    public JSONObject report(JSONArray reportData) {
        AipRequest request = new AipRequest();
        preOperation(request);
        request.addBody("feedback", reportData);
        request.setUri(ContentCensorConsts.REPORT_URL);
        request.setBodyFormat(EBodyFormat.RAW_JSON);
        request.addHeader(Headers.CONTENT_TYPE, HttpContentType.JSON_DATA);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 图像审核接口
     * 本接口除了支持自定义配置外，还对返回结果进行了总体的包装，按照用户在控制台中配置的规则直接返回是否合规，如果不合规则指出具体不合规的内容。
     *
     * @param image   本地图片路径或图片url
     * @param type    image参数类型：FILE或URL
     * @param options 可选参数
     * @return JSONObject
     */
    public JSONObject imageCensorUserDefined(String image, EImgType type, HashMap<String, Object> options) {
        if (type == EImgType.FILE) {
            try {
                byte[] imgData = Util.readFileByBytes(image);
                return imageCensorUserDefined(imgData, options);
            } catch (IOException e) {
                return AipError.IMAGE_READ_ERROR.toJsonResult();
            }
        }

        // url
        AipRequest request = new AipRequest();

        request.addBody("imgUrl", image);

        request.setUri(ContentCensorConsts.USER_DEFINED_IMAGE_URL);
        return contentCensorHelper(request, options);
    }

    /**
     * 图像审核接口
     * 本接口除了支持自定义配置外，还对返回结果进行了总体的包装，按照用户在控制台中配置的规则直接返回是否合规，如果不合规则指出具体不合规的内容。
     *
     * @param imgData 图片二进制数据
     * @param options 可选参数
     * @return JSONObject
     */
    public JSONObject imageCensorUserDefined(byte[] imgData, HashMap<String, Object> options) {
        AipRequest request = new AipRequest();

        String base64Content = Base64Util.encode(imgData);
        request.addBody("image", base64Content);

        request.setUri(ContentCensorConsts.USER_DEFINED_IMAGE_URL);
        return contentCensorHelper(request, options);
    }

    /**
     * 文本审核接口
     * 本接口除了支持自定义配置外，还对返回结果进行了总体的包装，按照用户在控制台中配置的规则直接返回是否合规，如果不合规则指出具体不合规的内容。
     *
     * @param text 文本
     * @return JSONObject
     */
    public JSONObject textCensorUserDefined(String text) {
        AipRequest request = new AipRequest();

        request.addBody("text", text);

        request.setUri(ContentCensorConsts.USER_DEFINED_TEXT_URL);
        return contentCensorHelper(request, null);
    }

    private JSONObject checkParam(byte[] imgData) {
        // image format
        String format = ImageUtil.getImageFormatByBytes(imgData);
        if (!ContentCensorConsts.ANTIPORN_SUPPORT_IMAGE_FORMAT.contains(format)) {
            return AipError.UNSUPPORTED_IMAGE_FORMAT_ERROR.toJsonResult();
        }

        return AipError.SUCCESS.toJsonResult();
    }

    private JSONObject checkImgFormat(byte[] imgData, String format) {
        String realFormat = ImageUtil.getImageFormatByBytes(imgData);
        if (realFormat.equals(format)) {
            return AipError.SUCCESS.toJsonResult();
        }
        return AipError.UNSUPPORTED_IMAGE_FORMAT_ERROR.toJsonResult();
    }

    /**
     * 语音审核接口
     * 本接口除了支持自定义配置外，还对返回结果进行了总体的包装，按照用户在控制台中配置的规则直接返回是否合规，如果不合规则指出具体不合规的内容。
     *
     * @param url     语音文件的url地址
     * @param fmt     语音文件类型
     * @param options 可选参数
     * @return JSONObject
     */
    public JSONObject voiceUrlCensorUserDefined(String url, Integer rate, String fmt,
                                                HashMap<String, Object> options) {
        AipRequest request = new AipRequest();

        request.addBody("url", url);
        request.addBody("fmt", fmt);
        request.addBody("rate", rate);

        request.setUri(ContentCensorConsts.USER_DEFINED_VOICE_URL);
        return contentCensorHelper(request, options);
    }

    /**
     * 语音审核接口
     * 本接口除了支持自定义配置外，还对返回结果进行了总体的包装，按照用户在控制台中配置的规则直接返回是否合规，如果不合规则指出具体不合规的内容。
     *
     * @param voiceData 语音二进制数据
     * @param fmt       语音文件类型
     * @param options   可选参数
     * @return JSONObject
     */
    public JSONObject voiceCensorUserDefined(byte[] voiceData, Integer rate, String fmt,
                                             HashMap<String, Object> options) {
        AipRequest request = new AipRequest();

        String base64Content = Base64Util.encode(voiceData);
        request.addBody("base64", base64Content);
        request.addBody("fmt", fmt);
        request.addBody("rate", rate);

        request.setUri(ContentCensorConsts.USER_DEFINED_VOICE_URL);
        return contentCensorHelper(request, options);
    }

    /**
     * 视频审核接口
     * 本接口除了支持自定义配置外，还对返回结果进行了总体的包装，按照用户在控制台中配置的规则直接返回是否合规，如果不合规则指出具体不合规的内容。
     *
     * @param name     视频名字
     * @param videoUrl 视频url
     * @param extId    视频id
     * @param options  可选参数
     * @return JSONObject
     */
    public JSONObject videoCensorUserDefined(String name, String videoUrl, String extId,
                                             HashMap<String, Object> options) {
        AipRequest request = new AipRequest();

        request.addBody("name", name);
        request.addBody("videoUrl", videoUrl);
        request.addBody("extId", extId);

        request.setUri(ContentCensorConsts.USER_DEFINED_VIDEO_URL);
        return contentCensorHelper(request, options);
    }

    /**
     * 长视频审核 - 提交视频接口
     * 本接口除了支持自定义配置外，还对返回结果进行了总体的包装，按照用户在控制台中配置的规则直接返回是否合规，如果不合规则指出具体不合规的内容。
     *
     * @param videoUrl 视频url
     * @param extId    用户侧视频id
     * @param options  可选参数
     * @return JSONObject
     */
    public JSONObject longVideoCensorSubmit(String videoUrl, String extId,
                                            HashMap<String, Object> options) {
        AipRequest request = new AipRequest();

        request.addBody("url", videoUrl);
        request.addBody("extId", extId);

        request.setUri(ContentCensorConsts.LONG_VIDEO_SUBMIT_URL);
        return contentCensorHelper(request, options);
    }

    /**
     * 长视频审核 - 拉取视频结果接口
     * 本接口除了支持自定义配置外，还对返回结果进行了总体的包装，按照用户在控制台中配置的规则直接返回是否合规，如果不合规则指出具体不合规的内容。
     *
     * @param taskId   任务id
     * @param options  可选参数
     * @return JSONObject
     */
    public JSONObject longVideoCensorPull(String taskId, HashMap<String, Object> options) {

        AipRequest request = new AipRequest();

        request.addBody("taskId", taskId);

        request.setUri(ContentCensorConsts.LONG_VIDEO_PULL_URL);
        return contentCensorHelper(request, options);
    }

    /**
     * 长语音审核 - 提交音频接口
     * 本接口除了支持自定义配置外，还对返回结果进行了总体的包装，按照用户在控制台中配置的规则直接返回是否合规，如果不合规则指出具体不合规的内容。
     *
     * @param voiceUrl 音频url
     * @param fmt      音频格式
     * @param rate     音频采样率
     * @param options  可选参数
     * @return JSONObject
     */
    public JSONObject asyncVoiceCensorSubmit(String voiceUrl, String fmt, Integer rate,
                                             HashMap<String, Object> options) {
        AipRequest request = new AipRequest();

        request.addBody("url", voiceUrl);
        request.addBody("fmt", fmt);
        request.addBody("rate", rate);

        request.setUri(ContentCensorConsts.ASYNC_VOICE_SUBMIT_URL);
        return contentCensorHelper(request, options);
    }

    /**
     * 长语音审核 - 轮询结果接口
     * 本接口除了支持自定义配置外，还对返回结果进行了总体的包装，按照用户在控制台中配置的规则直接返回是否合规，如果不合规则指出具体不合规的内容。
     *
     * @param taskId   任务id
     * @param options  可选参数
     * @return JSONObject
     */
    public JSONObject asyncVoiceCensorPullByTaskId(String taskId, HashMap<String, Object> options) {
        return asyncVoiceCensorPull(taskId, null, options);
    }

    public JSONObject asyncVoiceCensorPullByAudioId(String audioId, HashMap<String, Object> options) {
        return asyncVoiceCensorPull(null, audioId, options);
    }

    public JSONObject asyncVoiceCensorPull(String taskId, String audioId,
                                           HashMap<String, Object> options) {
        AipRequest request = new AipRequest();

        request.addBody("taskId", taskId);
        request.addBody("audioId", audioId);

        request.setUri(ContentCensorConsts.ASYNC_VOICE_PULL_URL);
        return contentCensorHelper(request, options);
    }

    /**
     * 直播审核 - 提交任务
     * 本接口除了支持自定义配置外，还对返回结果进行了总体的包装，按照用户在控制台中配置的规则直接返回是否合规，如果不合规则指出具体不合规的内容。
     *
     * @param streamUrl     流地址
     * @param streamType    流地址协议类型
     * @param extId         用户侧流唯一id
     * @param startTime     直播开始时间戳，单位ms
     * @param endTime       直播结束时间戳，单位ms
     * @param streamName    直播名称
     * @param options       可选参数
     * @return JSONObject
     */
    public JSONObject liveCensorSave(String streamUrl, String streamType, String extId,
                                     Long startTime, Long endTime, String streamName,
                                     HashMap<String, Object> options) {
        AipRequest request = new AipRequest();

        request.addBody("streamUrl", streamUrl);
        request.addBody("streamType", streamType);
        request.addBody("extId", extId);
        request.addBody("startTime", startTime);
        request.addBody("endTime", endTime);
        request.addBody("streamName", streamName);

        request.setUri(ContentCensorConsts.LIVE_SAVE_URL);
        return contentCensorHelper(request, options);
    }

    /**
     * 直播审核 - 停止任务
     * 本接口除了支持自定义配置外，还对返回结果进行了总体的包装，按照用户在控制台中配置的规则直接返回是否合规，如果不合规则指出具体不合规的内容。
     *
     * @param taskId        任务id
     * @param options       可选参数
     * @return JSONObject
     */
    public JSONObject liveCensorStop(String taskId, HashMap<String, Object> options) {
        AipRequest request = new AipRequest();

        request.addBody("taskId", taskId);

        request.setUri(ContentCensorConsts.LIVE_STOP_URL);
        return contentCensorHelper(request, options);
    }

    /**
     * 直播审核 - 查看任务
     * 本接口除了支持自定义配置外，还对返回结果进行了总体的包装，按照用户在控制台中配置的规则直接返回是否合规，如果不合规则指出具体不合规的内容。
     *
     * @param taskId        任务id
     * @param options       可选参数
     * @return JSONObject
     */
    public JSONObject liveCensorView(String taskId, HashMap<String, Object> options) {
        AipRequest request = new AipRequest();

        request.addBody("taskId", taskId);

        request.setUri(ContentCensorConsts.LIVE_VIEW_URL);
        return contentCensorHelper(request, options);
    }

    /**
     * 直播审核 - 轮询任务
     * 本接口除了支持自定义配置外，还对返回结果进行了总体的包装，按照用户在控制台中配置的规则直接返回是否合规，如果不合规则指出具体不合规的内容。
     *
     * @param taskId        任务id
     * @param options       可选参数
     * @return JSONObject
     */
    public JSONObject liveCensorPull(String taskId, HashMap<String, Object> options) {
        AipRequest request = new AipRequest();

        request.addBody("taskId", taskId);

        request.setUri(ContentCensorConsts.LIVE_PULL_URL);
        return contentCensorHelper(request, options);
    }

    private JSONObject contentCensorHelper(AipRequest request, HashMap<String, Object> options) {
        preOperation(request);

        if (options != null) {
            for (Map.Entry<String, Object> entry : options.entrySet()) {
                request.addBody(entry.getKey(), entry.getValue());
            }
        }

        postOperation(request);
        return requestServer(request);
    }
}
