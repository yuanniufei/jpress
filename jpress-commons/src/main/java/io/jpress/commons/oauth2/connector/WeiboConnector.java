/**
 * Copyright (c) 2016-2020, Michael Yang 杨福海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jpress.commons.oauth2.connector;

import com.alibaba.fastjson.JSONObject;
import io.jboot.utils.StrUtil;
import io.jpress.commons.oauth2.OauthConnector;
import io.jpress.commons.oauth2.OauthUser;

import java.util.HashMap;
import java.util.Map;

public class WeiboConnector extends OauthConnector {

	public WeiboConnector(String name, String appkey, String appSecret) {
		super(name, appkey, appSecret);
	}

	@Override
	public String createAuthorizeUrl(String state) {

		StringBuilder urlBuilder = new StringBuilder("https://api.weibo.com/oauth2/authorize?");
		urlBuilder.append("scope=email");
		urlBuilder.append("&client_id=" + getClientId());
		urlBuilder.append("&redirect_uri=" + getRedirectUri());
		urlBuilder.append("&state=" + state);

		return urlBuilder.toString();
	}

	@Override
	protected OauthUser getOauthUser(String code) {

		Map<String, Object> params = new HashMap<>();
		params.put("grant_type", "authorization_code");
		params.put("client_id", getClientId());
		params.put("client_secret", getClientSecret());
		params.put("redirect_uri", getRedirectUri());
		params.put("code", code);

		String url = "https://api.weibo.com/oauth2/access_token";
		String httpString = httpPost(url, params);

		if (StrUtil.isBlank(httpString)) {
			return null;
		}

		JSONObject json = JSONObject.parseObject(httpString);
		String accessToken = json.getString("access_token");
		String uid = json.getString("uid");

		url = "https://api.weibo.com/2/users/show.json?" + "access_token=" + accessToken + "&uid=" + uid;

		httpString = httpGet(url);
		json = JSONObject.parseObject(httpString);

		OauthUser user = new OauthUser();
		user.setAvatar(json.getString("avatar_large"));
		user.setNickname(json.getString("screen_name"));
		user.setOpenId(json.getString("id"));
		user.setGender(genders.get(json.getString("gender")));
		user.setSource(getName());

		return user;
	}

	static Map<String, String> genders = new HashMap<String, String>();

	static {
		genders.put("m", "male");
		genders.put("f", "female");
		genders.put("n", "unkown");
	}

}
