package com.justgo.admin.enums;

public enum RedisEnum {

	MEMBER_ACTIVE("0", "memberActive", "用户每日活跃"),
	SMS_KEY_REGISTER("redisSmsRegisterKey", "", "注册短信短信redis"),
	NJCC_BIND_CARD_INFO("njccBindCardInfo", "", "缓存绑卡信息"),
	ACCOUNT_LOGIN_SERVICE("accountLoginService", "", "登录token缓存名字"),
	QUICK_PAY_BANK_INFO("quickPayBankInfo", "", "银行卡信息缓存名字"),
	QUICK_PAY_BANK_CARD_NO("quickPayBankCardNo", "", "银行卡卡号缓存名字"),
	RED_PACKET_SHARE("redPacketShare", "", "分享红包redis的key"),
	SHARE_RED_PACKET_RECEIVE("receiveShareRedPacket", "", "接收分享红包redis的key"),
	RECEIVE_RED_PACKET("receiveRedPacket", "", "接收分享红包redis的key"),
	OBLIGATE_MOBILE_RED_PACKET("obligateMobileRedPacket", "", "预留手机号红包"),
	QUICK_CHECK_RED_PACKET_SHARE("quickCheckRedPacketShare", "", "快速查看分享红包实体"),
	ACT_DRAW_ACTIVE("actDrawActive", "", "抽奖执行次数"),
	ACT_EVENT_WHEEL_AWARDS("actEventWheelAwards", "", "根据活动获取奖品集合"),
	WARNING_GAP_AWARD_ALARM("warningGapAwardAlarm", "", "活动奖品警告间隔时间"),
	ACT_EVENT_MAIN_MAP_KEY("actEventMainMapKey", "", "活动map主key"),
	ACT_EVENT_DRAW_WHEEL("actEventDrawWheel", "", "这一期活动及其奖品同步到缓存"),
	WARNING_GAP_STORE_HOUSE_ALARM("warningGapStoreHouseAlarm", "", "库存警告间隔时间"),
	ACT_SIGNIN_RULE_KEY("actSignInRuleKey", "", "活动签到规则的key"),
	ACT_REGISTER_RULE_KEY("actRegisterRuleKey", "", "活动签到规则的key"),
	REDIS_SYS_DICT_KEY("redisSysDictKey", "", "系统字典的redis缓存的key"),
	REDIS_SER_CODE_KEY("redisSerCodeKey", "", "接口拦截的redis缓存的key"),
	VERSION_KEY("versionKey", "", "版本控制的redis缓存的key"),
	REDIS_KAPTCHA_KEY("redisKaptchaKey", "", "图形验证码"),
	RESPONSE_EXCEPTION_ENUM("ResponseExceptionEnum", "", "支付平台返回异常码与异常描述枚举"),
	TOKEN_MANAGE("tokenManage", "", "token管理"),
	TOKEN_MEMBERCODE_CACHE("tokenMembercode_Cache", "", "token和memberCode关联管理"),
	CORE_PROJECT_INF_INFO("coreProjectInfInfo", "", "项目接口使用信息管理"),
	CORE_BIZ_INF_INFO_KEY("coreProjectInfInfoKey", "", "业务接口的redis缓存的key"),
	SHARE_RED_PACKET_RECEIVE_OF_MEMBERCODE("receiveShareRedPacketOfMembercode", "", "通过会员号查询用户接收分享红包redis的key"),
	AMOUNT_MANAGE_KEY("UC_AMOUNT_MANAGE_KEY","","获取业务金额redis的key"),
	HUMANBODY_COUNT_SEX_KEY("HUMANBODY_COUNT_SEX_KEY","","用户画像的男女比例"),
	HUMANBODY_COUNT_SEXTOTAL_KEY("HUMANBODY_COUNT_SEXTOTAL_KEY","","用户画像的男女总数"),
	HUMANBODY_COUNT_REGION_KEY("HUMANBODY_COUNT_REGION_KEY","","用户画像的区域比例"),
	HUMANBODY_COUNT_AGE_KEY("HUMANBODY_COUNT_AGE_KEY","","用户画像的年龄比例"),
	LOGIN_SEND_MESSAGE_LIMITS_NUM("LOGIN_SEND_MESSAGE_LIMITS_NUM", "", "（短信登录）发送短信每天限制的次数"),
    PATTERN_SEND_MESSAGE_LIMITS_NUM("PATTERN_SEND_MESSAGE_LIMITS_NUM", "", "（忘记手势密码）发送短信每天限制的次数"),
	;
	private RedisEnum(String code, String flag, String description) {
		this.code = code;
		this.flag = flag;
		this.description = description;
	}

	/**
	 * seqences id
	 */
	private String code;
	
	/**
	 * 起始标志
	 */
	private String flag;
	
	/**
	 * 描述
	 */
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static RedisEnum getEnumByCode(String code){
		for(RedisEnum e:values()){
			if(e.getCode().equals(code))
				return e;
		}
		return null;
	}

}
