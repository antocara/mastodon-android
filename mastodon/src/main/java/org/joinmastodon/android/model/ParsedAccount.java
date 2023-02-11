package org.joinmastodon.android.model;

import android.text.SpannableStringBuilder;

import org.joinmastodon.android.MusktodonApp;
import org.joinmastodon.android.data.GlobalUserPreferences;
import org.joinmastodon.android.data.GlobalUserPreferencesDataSource;
import org.joinmastodon.android.ui.text.HtmlParser;
import org.joinmastodon.android.ui.utils.CustomEmojiHelper;

import java.util.Collections;

import me.grishka.appkit.imageloader.requests.ImageLoaderRequest;
import me.grishka.appkit.imageloader.requests.UrlImageLoaderRequest;
import me.grishka.appkit.utils.V;

public class ParsedAccount{
	public Account account;
	public CharSequence parsedName, parsedBio;
	public CustomEmojiHelper emojiHelper;
	public ImageLoaderRequest avatarRequest;

	private GlobalUserPreferencesDataSource globalUserPreferencesDataSource;

	public ParsedAccount(Account account, String accountID){
		this.globalUserPreferencesDataSource = new GlobalUserPreferences(MusktodonApp.context);
		this.account=account;
		parsedName=HtmlParser.parseCustomEmoji(account.displayName, account.emojis);
		parsedBio=HtmlParser.parse(account.note, account.emojis, Collections.emptyList(), Collections.emptyList(), accountID);

		emojiHelper=new CustomEmojiHelper();
		SpannableStringBuilder ssb=new SpannableStringBuilder(parsedName);
		ssb.append(parsedBio);
		emojiHelper.setText(ssb);

		avatarRequest=new UrlImageLoaderRequest(globalUserPreferencesDataSource.getPlayGifs() ? account.avatar : account.avatarStatic, V.dp(40), V.dp(40));
	}
}
