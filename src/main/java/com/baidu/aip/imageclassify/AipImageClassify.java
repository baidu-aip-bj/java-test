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

package com.baidu.aip.imageclassify;

import com.baidu.aip.client.BaseClient;
import com.baidu.aip.error.AipError;
import com.baidu.aip.http.AipRequest;
import com.baidu.aip.http.EBodyFormat;
import com.baidu.aip.http.HttpContentType;
import com.baidu.aip.util.Base64Util;
import com.baidu.aip.util.Util;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AipImageClassify extends BaseClient {

    public AipImageClassify(String appId, String apiKey, String secretKey) {
        super(appId, apiKey, secretKey);
    }

    /**
     * 通用物体识别接口
     * 该请求用于通用物体及场景识别，即对于输入的一张图片（可正常解码，且长宽比适宜），输出图片中的多个物体及场景标签。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                baike_num 返回百科信息的结果数，默认不返回
     * @return JSONObject
     */
    public JSONObject advancedGeneral(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.ADVANCED_GENERAL);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 通用物体识别接口
     * 该请求用于通用物体及场景识别，即对于输入的一张图片（可正常解码，且长宽比适宜），输出图片中的多个物体及场景标签。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                baike_num 返回百科信息的结果数，默认不返回
     * @return JSONObject
     */
    public JSONObject advancedGeneral(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return advancedGeneral(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 菜品识别接口
     * 该请求用于菜品识别。即对于输入的一张图片（可正常解码，且长宽比适宜），输出图片的菜品名称、卡路里信息、置信度。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                top_num 返回预测得分top结果数，默认为5
     *                filter_threshold 默认0.95，可以通过该参数调节识别效果，降低非菜识别率.
     *                baike_num 返回百科信息的结果数，默认不返回
     * @return JSONObject
     */
    public JSONObject dishDetect(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.DISH_DETECT);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 菜品识别接口
     * 该请求用于菜品识别。即对于输入的一张图片（可正常解码，且长宽比适宜），输出图片的菜品名称、卡路里信息、置信度。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                top_num 返回预测得分top结果数，默认为5
     *                filter_threshold 默认0.95，可以通过该参数调节识别效果，降低非菜识别率.
     *                baike_num 返回百科信息的结果数，默认不返回
     * @return JSONObject
     */
    public JSONObject dishDetect(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return dishDetect(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 车辆识别接口
     * 该请求用于检测一张车辆图片的具体车型。即对于输入的一张图片（可正常解码，且长宽比适宜），输出图片的车辆品牌及型号。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                top_num 返回预测得分top结果数，默认为5
     *                baike_num 返回百科信息的结果数，默认不返回
     * @return JSONObject
     */
    public JSONObject carDetect(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.CAR_DETECT);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 车辆识别接口
     * 该请求用于检测一张车辆图片的具体车型。即对于输入的一张图片（可正常解码，且长宽比适宜），输出图片的车辆品牌及型号。
     *
     * @param url     - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                top_num 返回预测得分top结果数，默认为5
     *                baike_num 返回百科信息的结果数，默认不返回
     * @return JSONObject
     */
    public JSONObject carDetectUrl(String url, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.CAR_DETECT);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 车辆识别接口
     * 该请求用于检测一张车辆图片的具体车型。即对于输入的一张图片（可正常解码，且长宽比适宜），输出图片的车辆品牌及型号。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                top_num 返回预测得分top结果数，默认为5
     *                baike_num 返回百科信息的结果数，默认不返回
     * @return JSONObject
     */
    public JSONObject carDetect(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return carDetect(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 车辆检测接口
     * 传入单帧图像，**检测图片中所有机动车辆，返回每辆车的类型和坐标位置**，可识别小汽车、卡车、巴士、摩托车、三轮车5大类车辆，
     * **并对每类车辆分别计数，可返回含有统计值和检测框的渲染结果图**，支持指定不规则区域的车辆统计。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                show 是否返回结果图（含统计值和跟踪框）。选true时返回渲染后的图片(base64)，其它无效值或为空则默认false。
     *                area 只统计该区域内的车辆数，缺省时为全图统计。<br>逗号分隔，如‘x1,y1,x2,y2,x3,y3...xn,yn'，按顺序依次给出每
     *                个顶点的x、y坐标（默认尾点和首点相连），形成闭合多边形区域。<br>服务会做范围（顶点左边需在图像范围内）及个数校验（数组
     *                长度必须为偶数，且大于3个顶点）。只支持单个多边形区域，建议设置矩形框，即4个顶点。**坐标取值不能超过图像宽度和高度，
     *                比如1280的宽度，坐标值最大到1279**。
     * @return JSONObject
     */
    public JSONObject vehicleDetect(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.VEHICLE_DETECT);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 车辆检测接口
     * 传入单帧图像，**检测图片中所有机动车辆，返回每辆车的类型和坐标位置**，可识别小汽车、卡车、巴士、摩托车、三轮车5大类车辆，
     * **并对每类车辆分别计数，可返回含有统计值和检测框的渲染结果图**，支持指定不规则区域的车辆统计。
     *
     * @param url     - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                show 是否返回结果图（含统计值和跟踪框）。选true时返回渲染后的图片(base64)，其它无效值或为空则默认false。
     *                area 只统计该区域内的车辆数，缺省时为全图统计。<br>逗号分隔，如‘x1,y1,x2,y2,x3,y3...xn,yn'，
     *                按顺序依次给出每个顶点的x、y坐标（默认尾点和首点相连），形成闭合多边形区域。<br>服务会做范围（顶点左边需在图像范围内）
     *                及个数校验（数组长度必须为偶数，且大于3个顶点）。只支持单个多边形区域，建议设置矩形框，即4个顶点。**坐标取值不能超过
     *                图像宽度和高度，比如1280的宽度，坐标值最大到1279**。
     * @return JSONObject
     */
    public JSONObject vehicleDetectUrl(String url, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.VEHICLE_DETECT);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 车辆检测接口
     * 传入单帧图像，**检测图片中所有机动车辆，返回每辆车的类型和坐标位置**，可识别小汽车、卡车、巴士、摩托车、三轮车5大类车辆，**并对每类
     * 车辆分别计数，可返回含有统计值和检测框的渲染结果图**，支持指定不规则区域的车辆统计。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                show 是否返回结果图（含统计值和跟踪框）。选true时返回渲染后的图片(base64)，其它无效值或为空则默认false。
     *                area 只统计该区域内的车辆数，缺省时为全图统计。<br>逗号分隔，如‘x1,y1,x2,y2,x3,y3...xn,yn'，按顺序依次给出每
     *                个顶点的x、y坐标（默认尾点和首点相连），形成闭合多边形区域。<br>服务会做范围（顶点左边需在图像范围内）及个数校验（
     *                数组长度必须为偶数，且大于3个顶点）。只支持单个多边形区域，建议设置矩形框，即4个顶点。**坐标取值不能超过图像宽度和高度，
     *                比如1280的宽度，坐标值最大到1279**。
     * @return JSONObject
     */
    public JSONObject vehicleDetect(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return vehicleDetect(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 车辆外观损伤识别接口
     * 针对常见的小汽车车型，传入单帧图像，识别车辆外观受损部件及损伤类型，支持32种车辆部件、5大类外观损伤。
     * 同时可输出损伤的数值化结果（长宽、面积、部件占比），支持单图多种损伤的识别。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject vehicleDamage(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.VEHICLE_DAMAGE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 车辆外观损伤识别接口
     * 针对常见的小汽车车型，传入单帧图像，识别车辆外观受损部件及损伤类型，支持32种车辆部件、5大类外观损伤。同时可输出损伤的数值化结果
     * （长宽、面积、部件占比），支持单图多种损伤的识别。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject vehicleDamage(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return vehicleDamage(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * logo商标识别接口
     * 该请求用于检测和识别图片中的品牌LOGO信息。即对于输入的一张图片（可正常解码，且长宽比适宜），输出图片中LOGO的名称、位置和置信度。
     * 当效果欠佳时，可以建立子库（在[控制台](https://console.bce.baidu.com/ai/#/ai/imagerecognition/overview/index)创建应用并
     * 申请建库）并通过调用logo入口接口完成自定义logo入库，提高识别效果。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                custom_lib 是否只使用自定义logo库的结果，默认false：返回自定义库+默认库的识别结果
     * @return JSONObject
     */
    public JSONObject logoSearch(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.LOGO_SEARCH);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * logo商标识别接口
     * 该请求用于检测和识别图片中的品牌LOGO信息。即对于输入的一张图片（可正常解码，且长宽比适宜），输出图片中LOGO的名称、位置和置信度。
     * 当效果欠佳时，可以建立子库（在[控制台](https://console.bce.baidu.com/ai/#/ai/imagerecognition/overview/index)
     * 创建应用并申请建库）并通过调用logo入口接口完成自定义logo入库，提高识别效果。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                custom_lib 是否只使用自定义logo库的结果，默认false：返回自定义库+默认库的识别结果
     * @return JSONObject
     */
    public JSONObject logoSearch(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return logoSearch(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * logo商标识别—添加接口
     * 使用入库接口请先在[控制台](https://console.bce.baidu.com/ai/#/ai/imagerecognition/overview/index)创建应用并申请建库，
     * 建库成功后方可正常使用。
     *
     * @param image   - 二进制图像数据
     * @param brief   - brief，检索时带回。此处要传对应的name与code字段，name长度小于100B，code长度小于150B
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject logoAdd(byte[] image, String brief, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);

        request.addBody("brief", brief);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.LOGO_ADD);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * logo商标识别—添加接口
     * 使用入库接口请先在[控制台](https://console.bce.baidu.com/ai/#/ai/imagerecognition/overview/index)创建应用并申请建库，
     * 建库成功后方可正常使用。
     *
     * @param image   - 本地图片路径
     * @param brief   - brief，检索时带回。此处要传对应的name与code字段，name长度小于100B，code长度小于150B
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject logoAdd(String image, String brief, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return logoAdd(data, brief, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * logo商标识别—删除接口
     * 使用删除接口请先在[控制台](https://console.bce.baidu.com/ai/#/ai/imagerecognition/overview/index)创建应用并申请建库
     * ，建库成功后先调用入库接口完成logo图片入库，删除接口用户在已入库的logo图片中删除图片。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject logoDeleteByImage(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.LOGO_DELETE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * logo商标识别—删除接口
     * 使用删除接口请先在[控制台](https://console.bce.baidu.com/ai/#/ai/imagerecognition/overview/index)创建应用并申请建库，
     * 建库成功后先调用入库接口完成logo图片入库，删除接口用户在已入库的logo图片中删除图片。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject logoDeleteByImage(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return logoDeleteByImage(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * logo商标识别—删除接口
     * 使用删除接口请先在[控制台](https://console.bce.baidu.com/ai/#/ai/imagerecognition/overview/index)创建应用并申请建库，
     * 建库成功后先调用入库接口完成logo图片入库，删除接口用户在已入库的logo图片中删除图片。
     *
     * @param contSign - 图片签名（和image二选一，image优先级更高）
     * @param options  - 可选参数对象，key: value都为string类型
     *                 options - options列表:
     * @return JSONObject
     */
    public JSONObject logoDeleteBySign(String contSign, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("cont_sign", contSign);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.LOGO_DELETE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 动物识别接口
     * 该请求用于识别一张图片。即对于输入的一张图片（可正常解码，且长宽比适宜），输出动物识别结果
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                top_num 返回预测得分top结果数，默认为6
     *                baike_num 返回百科信息的结果数，默认不返回
     * @return JSONObject
     */
    public JSONObject animalDetect(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.ANIMAL_DETECT);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 动物识别接口
     * 该请求用于识别一张图片。即对于输入的一张图片（可正常解码，且长宽比适宜），输出动物识别结果
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                top_num 返回预测得分top结果数，默认为6
     *                baike_num 返回百科信息的结果数，默认不返回
     * @return JSONObject
     */
    public JSONObject animalDetect(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return animalDetect(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 植物识别接口
     * 该请求用于识别一张图片。即对于输入的一张图片（可正常解码，且长宽比适宜），输出植物识别结果。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                baike_num 返回百科信息的结果数，默认不返回
     * @return JSONObject
     */
    public JSONObject plantDetect(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.PLANT_DETECT);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 植物识别接口
     * 该请求用于识别一张图片。即对于输入的一张图片（可正常解码，且长宽比适宜），输出植物识别结果。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                baike_num 返回百科信息的结果数，默认不返回
     * @return JSONObject
     */
    public JSONObject plantDetect(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return plantDetect(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 图像主体检测接口
     * 用户向服务请求检测图像中的主体位置。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                with_face 如果检测主体是人，主体区域是否带上人脸部分，0-不带人脸区域，其他-带人脸区域，裁剪类需求推荐带人脸，
     *                检索/识别类需求推荐不带人脸。默认取1，带人脸。
     * @return JSONObject
     */
    public JSONObject objectDetect(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.OBJECT_DETECT);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 图像主体检测接口
     * 用户向服务请求检测图像中的主体位置。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                with_face 如果检测主体是人，主体区域是否带上人脸部分，0-不带人脸区域，其他-带人脸区域，裁剪类需求推荐带人脸，
     *                检索/识别类需求推荐不带人脸。默认取1，带人脸。
     * @return JSONObject
     */
    public JSONObject objectDetect(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return objectDetect(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 地标识别接口
     * 该请求用于识别地标，即对于输入的一张图片（可正常解码，且长宽比适宜），输出图片中的地标识别结果。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject landmark(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.LANDMARK);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 地标识别接口
     * 该请求用于识别地标，即对于输入的一张图片（可正常解码，且长宽比适宜），输出图片中的地标识别结果。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject landmark(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return landmark(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 花卉识别接口
     * 检测用户上传的花卉图片，输出图片的花卉识别结果名称及对应的概率打分。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                top_num 返回预测得分top结果数，默认为5
     *                baike_num 返回百科信息的结果数，默认不返回
     * @return JSONObject
     */
    public JSONObject flower(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.FLOWER);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 花卉识别接口
     * 检测用户上传的花卉图片，输出图片的花卉识别结果名称及对应的概率打分。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                top_num 返回预测得分top结果数，默认为5
     *                baike_num 返回百科信息的结果数，默认不返回
     * @return JSONObject
     */
    public JSONObject flower(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return flower(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 食材识别接口
     * 该请求用于识别果蔬类食材，即对于输入的一张图片（可正常解码，且长宽比适宜），输出图片中的果蔬食材结果。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                top_num 返回预测得分top结果数，如果为空或小于等于0默认为5；如果大于20默认20
     * @return JSONObject
     */
    public JSONObject ingredient(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.INGREDIENT);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 食材识别接口
     * 该请求用于识别果蔬类食材，即对于输入的一张图片（可正常解码，且长宽比适宜），输出图片中的果蔬食材结果。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                top_num 返回预测得分top结果数，如果为空或小于等于0默认为5；如果大于20默认20
     * @return JSONObject
     */
    public JSONObject ingredient(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return ingredient(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 红酒识别接口
     * 该服务用于识别红酒标签，即对于输入的一张图片（可正常解码，长宽比适宜，且酒标清晰可见），输出图片中的红酒名称、国家、产区、酒庄、类型
     * 、糖分、葡萄品种、酒品描述等信息。可识别数十万中外常见红酒。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject redwine(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.REDWINE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 红酒识别接口
     * 该服务用于识别红酒标签，即对于输入的一张图片（可正常解码，长宽比适宜，且酒标清晰可见），输出图片中的红酒名称、国家、产区、酒庄、类型、
     * 糖分、葡萄品种、酒品描述等信息。可识别数十万中外常见红酒。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject redwine(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return redwine(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 货币识别接口
     * 识别图像中的货币类型，以纸币为主，正反面均可准确识别，接口返回货币的名称、代码、面值、年份信息；可识别各类近代常见货币，如美元、欧元、
     * 英镑、法郎、澳大利亚元、俄罗斯卢布、日元、韩元、泰铢、印尼卢比等。
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject currency(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.CURRENCY);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 货币识别接口
     * 识别图像中的货币类型，以纸币为主，正反面均可准确识别，接口返回货币的名称、代码、面值、年份信息；可识别各类近代常见货币，如美元
     * 、欧元、英镑、法郎、澳大利亚元、俄罗斯卢布、日元、韩元、泰铢、印尼卢比等。
     *
     * @param image   - 本地图片路径
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject currency(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return currency(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }


    /**
     * 自定义菜品识别—入库
     *
     * @param image   - 图片地址
     * @param brief   - 菜品名称摘要信息
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject customDishesAddImage(String image, String brief, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return customDishesAddImage(data, brief, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 自定义菜品识别—入库
     *
     * @param image   - 二进制图像数据
     * @param brief   - 菜品名称摘要信息
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject customDishesAddImage(byte[] image, String brief, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        request.addBody("brief", brief);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.CUSTOMDISHADD);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 自定义菜品识别—检索
     *
     * @param image   - 图像地址
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject customDishesSearch(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return customDishesSearch(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }


    /**
     * 自定义菜品识别—检索
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject customDishesSearch(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.CUSTOMDISHSEARCH);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 自定义菜品识别—删除-image
     *
     * @param image   - 图像地址
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject customDishesDeleteImage(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return customDishesDeleteImage(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }


    /**
     * 自定义菜品识别—删除-image
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject customDishesDeleteImage(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.CUSTOMDISHDELETE);
        postOperation(request);
        return requestServer(request);
    }

    /**
     * 自定义菜品识别—删除-cont_sign
     *
     * @param contSign - 输入图片签名
     * @param options  - 可选参数对象，key: value都为string类型
     *                 options - options列表:
     * @return JSONObject
     */
    public JSONObject customDishesDeleteContSign(String contSign, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);
        request.addBody("cont_sign", contSign);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.CUSTOMDISHDELETE);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 图像多主体检测
     *
     * @param image   - 图像地址
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */

    public JSONObject multiObjectDetect(String image, HashMap<String, String> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return multiObjectDetect(data, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 图像多主体检测
     *
     * @param image   - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject multiObjectDetect(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);
        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.MULTIOBJECTDETECT);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 组合接口API-image
     *
     * @param image   - 二进制图像数据
     * @param scenes  - 场景
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject combinationByImage(String image, List<String> scenes, HashMap<String, Object> options) {
        try {
            byte[] data = Util.readFileByBytes(image);
            return combinationByImage(data, scenes, options);
        } catch (IOException e) {
            e.printStackTrace();
            return AipError.IMAGE_READ_ERROR.toJsonResult();
        }
    }

    /**
     * 组合接口API-image
     *
     * @param image   - 二进制图像数据
     * @param scenes  - 场景
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject combinationByImage(byte[] image, List<String> scenes, HashMap<String, Object> options) {
        AipRequest request = new AipRequest();
        preOperation(request, HttpContentType.JSON_DATA);
        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        request.addBody("scenes", scenes);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.COMBINATION);
        request.setBodyFormat(EBodyFormat.RAW_JSON);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 组合接口API-imgUrl
     *
     * @param imgUrl  - 图片url地址
     * @param scenes  - 场景
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     * @return JSONObject
     */
    public JSONObject combinationByImageUrl(String imgUrl, List<String> scenes, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request, HttpContentType.JSON_DATA);
        request.addBody("imgUrl", imgUrl);
        request.addBody("scenes", scenes);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.COMBINATION);
        request.setBodyFormat(EBodyFormat.RAW_JSON);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 车流属性识别
     * 传入单帧图像，检测图片中所有车辆，返回每辆车的类型和坐标位置，可识别小汽车、卡车、巴士、摩托车、三轮车、自行车6大类车辆，
     * <p>
     * @param image - 二进制图像数据
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                type - 是否选定某些属性输出对应的信息，可从12种输出属性中任选若干，用英文逗号分隔（例如vehicle_type,roof_rack,
     *                skylight）。默认输出全部属性
     * @return JSONObject
     */
    public JSONObject vehicleAttr(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.VEHICLE_ATTR);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 车流属性识别
     * 传入单帧图像，检测图片中所有车辆，返回每辆车的类型和坐标位置，可识别小汽车、卡车、巴士、摩托车、三轮车、自行车6大类车辆，
     * <p>
     * @param  url - 图片完整URL
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                type - 是否选定某些属性输出对应的信息，可从12种输出属性中任选若干，用英文逗号分隔（例如vehicle_type,roof_rack,
     *                skylight）。默认输出全部属性
     * @return JSONObject
     */
    public JSONObject vehicleAttrUrl(String url, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.VEHICLE_ATTR);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 车辆检测
     * 面向高空拍摄视角（30米以上），传入单帧图像，检测图片中所有车辆，返回每辆车的坐标位置（不区分车辆类型），并进行车辆计数，支持指定矩形区域
     * 的车辆检测与数量统计。
     * <p>
     * @param image - 二进制图像数据
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                area - 只统计该矩形区域内的车辆数，缺省时为全图统计。逗号分隔，如‘x1,y1,x2,y2,x3,y3...xn,yn'，按顺序依次给出
     *                每个顶点的x、y坐标（默认尾点和首点相连），形成闭合矩形区域。
     * @return JSONObject
     */
    public JSONObject vehicleDetectHigh(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.VEHICLE_DETECT_HIGH);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 车辆检测-高空版
     * 面向高空拍摄视角（30米以上），传入单帧图像，检测图片中所有车辆，返回每辆车的坐标位置（不区分车辆类型），并进行车辆计数，
     * 支持指定矩形区域的车辆检测与数量统计。
     * <p>
     * @param  url - 图片完整URL
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                area - 只统计该矩形区域内的车辆数，缺省时为全图统计。逗号分隔，如‘x1,y1,x2,y2,x3,y3...xn,yn'，
     *                按顺序依次给出每个顶点的x、y坐标（默认尾点和首点相连），形成闭合矩形区域。
     * @return JSONObject
     */
    public JSONObject vehicleDetectHighUrl(String url, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.VEHICLE_DETECT_HIGH);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 车辆分割
     * 传入单帧图像，检测图像中的车辆，以小汽车为主，识别车辆的轮廓范围，与背景进行分离，返回分割后的二值图、灰度图，支持多个车辆、车门打开、
     * 后备箱打开、机盖打开、正面、侧面、背面等各种拍摄场景。
     * <p>
     * @param  image - 二进制图像数据
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                type - 可以通过设置type参数，自主设置返回哪些结果图，避免造成带宽的浪费。
     * @return JSONObject
     */
    public JSONObject vehicleSeg(byte[] image, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        request.addBody("image", image);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.VEHICLE_SEG);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 车流统计
     * 根据传入的连续视频图片序列，进行车辆检测和追踪，返回每个车辆的坐标位置、车辆类型（包括小汽车、卡车、巴士、摩托车、三轮车5类）。
     * 在原图中指定区域，根据车辆轨迹判断驶入/驶出区域的行为，统计各类车辆的区域进出车流量，可返回含统计值和跟踪框的渲染图。
     * @param image - 二进制图像数据
     * @param  caseId - 任务ID（通过case_id区分不同视频流，自拟，不同序列间不可重复）
     * @param  caseInit - 每个case的初始化信号，为true时对该case下的跟踪算法进行初始化，为false时重载该case的跟踪状态。当为false且读取不到相应
     *  case 的信息时，直接重新初始化
     * @param  area - 只统计进出该区域的车辆。逗号分隔，如‘x1,y1,x2,y2,x3,y3...xn,yn'，按顺序依次给出每个顶点的x、y坐标（默认尾点和首点相连），
     * 形成闭合多边形区域。
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                show - 是否返回结果图（含统计值和跟踪框）。选true时返回渲染后的图片(base64)，其它无效值或为空则默认false。
     * @return JSONObject
     */
    public JSONObject trafficFlow(byte[] image, int caseId, String caseInit, String area,
                                  HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        request.addBody("case_id", caseId);
        request.addBody("case_init", caseInit);
        request.addBody("area", area);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.TRAFFIC_FLOW);
        postOperation(request);
        return requestServer(request);
    }


    /**
     * 车流统计
     * 根据传入的连续视频图片序列，进行车辆检测和追踪，返回每个车辆的坐标位置、车辆类型（包括小汽车、卡车、巴士、摩托车、三轮车5类）。在原图中
     * 指定区域，根据车辆轨迹判断驶入/驶出区域的行为，统计各类车辆的区域进出车流量，可返回含统计值和跟踪框的渲染图。
     * @param  url - 图片完整URL
     * @param  caseId - 任务ID（通过case_id区分不同视频流，自拟，不同序列间不可重复）
     * @param caseInit - 每个case的初始化信号，为true时对该case下的跟踪算法进行初始化，为false时重载该case的跟踪状态。当为false且读取不到相应
     * case的信息时，直接重新初始化
     * @param area - 只统计进出该区域的车辆。逗号分隔，如‘x1,y1,x2,y2,x3,y3...xn,yn'，按顺序依次给出每个顶点的x、y坐标（默认尾点和首点相连），
     * 形成闭合多边形区域。
     *
     * @param options - 可选参数对象，key: value都为string类型
     *                options - options列表:
     *                show - 是否返回结果图（含统计值和跟踪框）。选true时返回渲染后的图片(base64)，其它无效值或为空则默认false。
     * @return JSONObject
     */
    public JSONObject trafficFlowUrl(String url, int caseId, String caseInit, String area,
                                     HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        preOperation(request);

        request.addBody("url", url);
        request.addBody("case_id", caseId);
        request.addBody("case_init", caseInit);
        request.addBody("area", area);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(ImageClassifyConsts.TRAFFIC_FLOW);
        postOperation(request);
        return requestServer(request);
    }


}
