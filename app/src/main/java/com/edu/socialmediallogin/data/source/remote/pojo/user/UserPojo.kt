package com.edu.socialmediallogin.data.source.remote.pojo.user

data class UserPojo(
	val result: UserDetails? = null,
	val success: Boolean? = null,
	val abp: Boolean? = null,
	val error: Any? = null,
	val targetUrl: Any? = null,
	val unAuthorizedRequest: Boolean? = null
)