package org.joinmastodon.android.api.requests.oauth;

import com.google.gson.annotations.SerializedName;

import org.joinmastodon.android.api.MastodonAPIRequest;
import org.joinmastodon.android.api.session.AccountSessionManager;
import org.joinmastodon.android.data.mastodonapi.request.OauthTokenRequest;
import org.joinmastodon.android.data.oauth.model.GrantType;
import org.joinmastodon.android.model.Token;

public class GetOauthToken extends MastodonAPIRequest<Token>{
	public GetOauthToken(String clientID, String clientSecret, String code, GrantType grantType){
		super(HttpMethod.POST, "/oauth/token", Token.class);
		//setRequestBody(new OauthTokenRequest(clientID, clientSecret, code, grantType.getValue()));
	}

	@Override
	protected String getPathPrefix(){
		return "";
	}

//	private static class Request{
//		public GrantType grantType;
//		public String clientId;
//		public String clientSecret;
//		public String redirectUri=AccountSessionManager.REDIRECT_URI;
//		public String scope=AccountSessionManager.SCOPE;
//		public String code;
//
//		public Request(String clientId, String clientSecret, String code, GrantType grantType){
//			this.clientId=clientId;
//			this.clientSecret=clientSecret;
//			this.code=code;
//			this.grantType=grantType;
//		}
//	}

}
