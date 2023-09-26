package com.edu.socialmediallogin.data.source.remote.pojo.video

data class VideoStreamUrlPojo(
	val result: VideoStreamUrlResult? = null,
	val success: Boolean? = null,
	val error: String? = null
)

data class VideoStreamUrlResult(
	val prevAnswers: List<String?>? = null,
	val secondaryProvider: String? = null,
	val thumbnail: String? = null,
	val controlBars: ControlBars? = null,
	val annotations: List<AnnotationsItem?>? = null,
	val streamingUrl: String? = null,
	val timeLine: List<TimeLineItem?>? = null,
	val canSeek: Boolean? = null,
	val title: String? = null,
	val playbackRate: Int? = null,
	val prevInteractions: Boolean? = null,
	val provider: String? = null,
	val time: String? = null,
	val parentTheme: String? = null,
	val isWorkedOut: Boolean? = null
)

data class TimeLineItem(
	val endTimeLine: String? = null,
	val id: String? = null,
	val startTimeLine: String? = null
)

data class Position(
	val top: String? = null,
	val left: String? = null
)

data class AnnotationsItem(
	val owner: String? = null,
	val updatedBy: String? = null,
	val label: String? = null,
	val video: String? = null,
	val pause: Boolean? = null,
	val createdAt: String? = null,
	val deleted: Boolean? = null,
	val createdBy: String? = null,
	val v: Int? = null,
	val startTime: Any? = null,
	val id: String? = null,
	val position: Position? = null,
	val dimension: Dimension? = null,
	val items: List<ItemsItem?>? = null,
	val updatedAt: String? = null
)

data class Action(
	val groupId: String? = null,
	val time: Any? = null,
	val type: String? = null,
	val value: String? = null
)

data class Dimension(
	val width: String? = null,
	val height: String? = null
)

data class OptionsItem(
	val createdAt: String? = null,
	val answerOptionCodes: List<Any?>? = null,
	val deleted: Boolean? = null,
	val action: Action? = null,
	val id: String? = null,
	val content: String? = null,
	val updatedAt: String? = null
)

data class ItemsItem(
	val createdAt: String? = null,
	val deleted: Boolean? = null,
	val options: List<OptionsItem?>? = null,
	val id: String? = null,
	val type: String? = null,
	val content: String? = null,
	val updatedAt: String? = null
)

data class ControlBars(
	val hasEndOfLessonQuiz: Boolean? = null,
	val seekBar: Boolean? = null
)

