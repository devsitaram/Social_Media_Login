package com.edu.socialmediallogin.data.source.remote.pojo.video

data class VideoPojo(
	val result: Result? = null,
	val success: Boolean? = null,
	val abp: Boolean? = null,
	val error: Any? = null,
	val targetUrl: Any? = null,
	val unAuthorizedRequest: Boolean? = null
)

data class VideoInteractionItem(
	val completionPercentage: Any? = null,
	val reports: List<ReportsItem?>? = null,
	val reportStatus: String? = null,
	val videoId: String? = null,
	val finished: Boolean? = null,
	val time: Any? = null,
	val userId: Int? = null,
	val endOfLessonQuizCount: Int? = null,
	val updatedAt: String? = null
)

data class TopicsItem(
	val completion: Any? = null,
	val topicName: String? = null,
	val videos: List<VideosItem?>? = null,
	val id: Int? = null,
	val mastery: Any? = null
)

data class Result(
	val completion: Any? = null,
	val photoUrl: String? = null,
	val chapters: List<ChaptersItem?>? = null,
	val subjectDescription: String? = null,
	val totalVideoWatchedTimeInSeconds: Any? = null,
	val className: String? = null,
	val id: Int? = null,
	val subjectName: String? = null,
	val mastery: Any? = null
)

data class VideosItem(
	val description: String? = null,
	val videoId: String? = null,
	val className: String? = null,
	val isActive: Boolean? = null,
	val subjectId: Int? = null,
	val masteryStatus: String? = null,
	val percentageCompleted: Any? = null,
	val hasEndOfLessonQuiz: Boolean? = null,
	val chapterId: Int? = null,
	val isWorkedExample: Boolean? = null,
	val viewCount: Int? = null,
	val subjectName: String? = null,
	val firstViewedDate: Any? = null,
	val chapterName: String? = null,
	val videoTitle: String? = null,
	val masteryLevel: Any? = null,
	val videoAssignment: Any? = null,
	val topicId: Int? = null,
	val videoDuration: Any? = null,
	val thumbNailUrl: String? = null,
	val isRecommended: Boolean? = null,
	val topicName: String? = null,
	val completionStatus: String? = null,
	val videoInteraction: List<Any?>? = null,
	val elapsedTime: Any? = null
)

data class ChaptersItem(
	val completion: Any? = null,
	val photoUrl: String? = null,
	val chapterId: Int? = null,
	val topics: List<TopicsItem?>? = null,
	val chapterName: String? = null,
	val isWorkedExample: Boolean? = null,
	val isChapterActive: Boolean? = null,
	val mastery: Any? = null
)

data class ReportsItem(
	val interactionDate: String? = null,
	val interactionId: String? = null,
	val videoId: String? = null,
	val quizTime: Any? = null,
	val diagnosticRemarks: Any? = null,
	val mastery: Any? = null
)

