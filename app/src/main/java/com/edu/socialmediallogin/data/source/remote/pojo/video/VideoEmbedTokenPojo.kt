package com.edu.socialmediallogin.data.source.remote.pojo.video

data class VideoEmbedTokenPojo(
	val result: VideoEmbedTokenResult? = null,
	val success: Boolean? = null,
	val abp: Boolean? = null,
	val error: String? = null,
	val targetUrl: String? = null,
	val unAuthorizedRequest: Boolean? = null
)

data class VideoEmbedTokenResult(
	val message: String? = null,
	val embedToken: String? = null,
	val url: String? = null
)

