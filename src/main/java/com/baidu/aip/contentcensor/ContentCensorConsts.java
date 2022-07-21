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

import java.util.Arrays;
import java.util.HashSet;

public class ContentCensorConsts {


    static final String REPORT_URL =
            "https://aip.baidubce.com/rpc/2.0/feedback/v1/report";

    static final String USER_DEFINED_IMAGE_URL =
            "https://aip.baidubce.com/rest/2.0/solution/v1/img_censor/v2/user_defined";

    static final String USER_DEFINED_TEXT_URL =
            "https://aip.baidubce.com/rest/2.0/solution/v1/text_censor/v2/user_defined";

    static final String USER_DEFINED_VOICE_URL =
            "https://aip.baidubce.com/rest/2.0/solution/v1/voice_censor/v3/user_defined";

    static final String USER_DEFINED_VIDEO_URL =
            "https://aip.baidubce.com/rest/2.0/solution/v1/video_censor/v2/user_defined";

    static final String LONG_VIDEO_SUBMIT_URL =
            "https://aip.baidubce.com/rest/2.0/solution/v1/video_censor/v1/video/submit";

    static final String LONG_VIDEO_PULL_URL =
            "https://aip.baidubce.com/rest/2.0/solution/v1/video_censor/v1/video/pull";

    static final String ASYNC_VOICE_SUBMIT_URL =
            "https://aip.baidubce.com/rest/2.0/solution/v1/async_voice/submit";

    static final String ASYNC_VOICE_PULL_URL =
            "https://aip.baidubce.com/rest/2.0/solution/v1/async_voice/pull";

    static final String LIVE_SAVE_URL =
            "https://aip.baidubce.com/rest/2.0/solution/v1/live/v1/config/save";

    static final String LIVE_STOP_URL =
            "https://aip.baidubce.com/rest/2.0/solution/v1/live/v1/config/stop";

    static final String LIVE_VIEW_URL =
            "https://aip.baidubce.com/rest/2.0/solution/v1/live/v1/config/view";

    static final String LIVE_PULL_URL =
            "https://aip.baidubce.com/rest/2.0/solution/v1/live/v1/audit/pull";

    public static final Long ANTIPORN_MAX_IMAGE_SIZE = 4194304L; // 4 * 1024 * 1024

    public static final Integer ANTIPORN_MIN_IMAGE_SIDE_LENGTH = 10;
    public static final Integer ANTIPORN_MAX_IMAGE_SIDE_LENGTH = 2048;

    public static final HashSet<String> ANTIPORN_SUPPORT_IMAGE_FORMAT =
            new HashSet<String>(Arrays.asList("JPEG", "png", "bmp", "gif"));
}
