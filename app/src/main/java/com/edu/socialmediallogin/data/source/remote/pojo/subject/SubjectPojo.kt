package com.edu.socialmediallogin.data.source.remote.pojo.subject

data class SubjectPojo(
	val result: List<SubjectResult>? = null,
	val success: Boolean? = null,
	val abp: Boolean? = null,
	val error: String? = null,
	val targetUrl: String? = null,
	val unAuthorizedRequest: Boolean? = null
)

data class AssetType(
	val assetId: Int? = null,
	val assetName: String? = null
)

data class Level(
	val level: String? = null,
	val levelId: Int? = null,
	val order: Int? = null
)

data class SubjectResult(
	val yearlyPrice: String? = null,
	val studentSubject: StudentSubject? = null,
	val validityStartDate: String? = null,
	val level: Level? = null,
	val packageId: Int? = null,
	val packageTag: String? = null,
	val monthlyPrice: String? = null,
	val validityEndDate: String? = null,
	val subjectId: Int? = null,
	val halfYearlyPrice: String? = null,
	val assetType: AssetType? = null,
	val photoUrl: String? = null,
	val isComingSoon: Boolean? = null,
	val name: String? = null,
	val planEndDate: String? = null,
	val packageGrade: String? = null,
	val isStudentPremium: Boolean? = null,
	val order: Int? = null
)

data class StudentSubject(
	val subjectId: Int? = null,
	val subjectName: String? = null,
	val order: String? = null
)

