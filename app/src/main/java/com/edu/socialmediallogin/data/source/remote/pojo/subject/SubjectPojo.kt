package com.edu.socialmediallogin.data.source.remote.pojo.subject

data class SubjectPojo(
	val result: List<SubjectItem?>? = null,
	val success: Boolean? = null,
	val abp: Boolean? = null,
	val error: Any? = null,
	val targetUrl: Any? = null,
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

data class SubjectItem(
	val yearlyPrice: Any? = null,
	val studentSubject: StudentSubject? = null,
	val validityStartDate: String? = null,
	val level: Level? = null,
	val packageId: Int? = null,
	val packageTag: Any? = null,
	val monthlyPrice: Any? = null,
	val validityEndDate: String? = null,
	val subjectId: Int? = null,
	val halfYearlyPrice: Any? = null,
	val assetType: AssetType? = null,
	val photoUrl: String? = null,
	val isComingSoon: Boolean? = null,
	val name: String? = null,
	val planEndDate: String? = null,
	val packageGrade: Any? = null,
	val isStudentPremium: Boolean? = null,
	val order: Int? = null
)

data class StudentSubject(
	val subjectId: Int? = null,
	val subjectName: String? = null,
	val order: Any? = null
)

