package com.ylw.net.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "baidu_fans")
public class BaiduFans {
	@DatabaseField(id = true, unique = true, columnName = "fans_uk")
	private long fansUk; // 4127651315
	@DatabaseField(columnName = "album_count")
	private int albumCount; // 0
	@DatabaseField(columnName = "follow_time")
	private int followTime; // 1449020984
	@DatabaseField(columnName = "follow_count")
	private int followCount; // 4
	@DatabaseField(columnName = "avatar_url")
	private String avatarUrl; // http://himg.bdimg.com/sys/portrait/item/bbf62c02.jpg
	@DatabaseField(columnName = "fans_uname")
	private String fansUname; // xjpsh
	@DatabaseField(columnName = "is_vip")
	private int isVip; // 0
	@DatabaseField(columnName = "user_type")
	private int userType; // 0
	@DatabaseField(columnName = "fans_count")
	private int fansCount; // 1
	@DatabaseField(columnName = "type")
	private int type; // -1
	@DatabaseField(columnName = "pubshare_count")
	private int pubshareCount; // 0
	@DatabaseField(columnName = "intro")
	private String intro;

	public long getFansUk() {
		return fansUk;
	}

	public void setFansUk(long fansUk) {
		this.fansUk = fansUk;
	}

	public int getAlbumCount() {
		return albumCount;
	}

	public void setAlbumCount(int albumCount) {
		this.albumCount = albumCount;
	}

	public int getFollowTime() {
		return followTime;
	}

	public void setFollowTime(int followTime) {
		this.followTime = followTime;
	}

	public int getFollowCount() {
		return followCount;
	}

	public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getFansUname() {
		return fansUname;
	}

	public void setFansUname(String fansUname) {
		this.fansUname = fansUname;
	}

	public int getIsVip() {
		return isVip;
	}

	public void setIsVip(int isVip) {
		this.isVip = isVip;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getFansCount() {
		return fansCount;
	}

	public void setFansCount(int fansCount) {
		this.fansCount = fansCount;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPubshareCount() {
		return pubshareCount;
	}

	public void setPubshareCount(int pubshareCount) {
		this.pubshareCount = pubshareCount;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
}
