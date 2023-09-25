package com.edu.socialmediallogin.data.source.remote.pojo.video

data class VideoPojo(
	val result: VideoResult? = null,
	val success: Boolean? = null,
	val abp: Boolean? = null,
	val error: String? = null,
	val targetUrl: String? = null,
	val unAuthorizedRequest: Boolean? = null
)

data class VideoResult(
	val completion: String? = null,
	val photoUrl: String? = null,
	val chapters: List<ChaptersItem?>? = null,
	val subjectDescription: String? = null,
	val totalVideoWatchedTimeInSeconds: String? = null,
	val className: String? = null,
	val id: Int? = null,
	val subjectName: String? = null,
	val mastery: String? = null
)

data class ChaptersItem(
	val completion: String? = null,
	val photoUrl: String? = null,
	val chapterId: Int? = null,
	val topics: List<TopicsItem?>? = null,
	val chapterName: String? = null,
	val isWorkedExample: Boolean? = null,
	val isChapterActive: Boolean? = null,
	val mastery: String? = null
)

data class TopicsItem(
	val completion: String? = null,
	val topicName: String? = null,
	val videos: List<VideosItem?>? = null,
	val id: Int? = null,
	val mastery: String? = null
)

data class VideosItem(
	val description: String? = null,
	val videoId: String? = null,
	val className: String? = null,
	val isActive: Boolean? = null,
	val subjectId: Int? = null,
	val masteryStatus: String? = null,
	val percentageCompleted: String? = null,
	val hasEndOfLessonQuiz: Boolean? = null,
	val chapterId: Int? = null,
	val isWorkedExample: Boolean? = null,
	val viewCount: Int? = null,
	val subjectName: String? = null,
	val firstViewedDate: String? = null,
	val chapterName: String? = null,
	val videoTitle: String? = null,
	val masteryLevel: String? = null,
	val videoAssignment: String? = null,
	val topicId: Int? = null,
	val videoDuration: String? = null,
	val thumbNailUrl: String? = null,
	val isRecommended: Boolean? = null,
	val topicName: String? = null,
	val completionStatus: String? = null,
	val videoInteraction: List<Any?>? = null,
	val elapsedTime: String? = null
)

// un-used
data class ReportsItem(
	val interactionDate: String? = null,
	val interactionId: String? = null,
	val videoId: String? = null,
	val quizTime: String? = null,
	val diagnosticRemarks: String? = null,
	val mastery: String? = null
)

data class VideoInteractionItem(
	val completionPercentage: String? = null,
	val reports: List<ReportsItem?>? = null,
	val reportStatus: String? = null,
	val videoId: String? = null,
	val finished: Boolean? = null,
	val time: String? = null,
	val userId: Int? = null,
	val endOfLessonQuizCount: Int? = null,
	val updatedAt: String? = null
)

