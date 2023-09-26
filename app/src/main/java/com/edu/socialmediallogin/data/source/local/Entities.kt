package com.edu.socialmediallogin.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edu.socialmediallogin.data.source.remote.pojo.subject.AssetType
import com.edu.socialmediallogin.data.source.remote.pojo.subject.Level
import com.edu.socialmediallogin.data.source.remote.pojo.subject.StudentSubject
import com.edu.socialmediallogin.data.source.remote.pojo.video.ChaptersItem

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int? = null,
    @ColumnInfo("pendingBalance")
    val pendingBalance: String? = null,
    @ColumnInfo("isSchoolChatroomEnabled")
    val isSchoolChatroomEnabled: Boolean? = null,
    @ColumnInfo("role")
    val role: String? = null,
    @ColumnInfo("isActive")
    val isActive: Boolean? = null,
    @ColumnInfo("countryId")
    val countryId: Int? = null,
    @ColumnInfo("schoolPhotoUrl")
    val schoolPhotoUrl: String? = null,
    @ColumnInfo("isPasswordEmpty")
    val isPasswordEmpty: Boolean? = null,
    @ColumnInfo("photoUrl")
    val photoUrl: String? = null,
    @ColumnInfo("createdAt")
    val createdAt: String? = null,
    @ColumnInfo("emailAddress")
    val emailAddress: String? = null,
    @ColumnInfo("userMode")
    val userMode: String? = null,
    @ColumnInfo("isB2C")
    val isB2C: Boolean? = null,
    @ColumnInfo("nickname")
    val nickname: String? = null,
    @ColumnInfo("schoolName")
    val schoolName: String? = null,
    @ColumnInfo("isEBookPrintFeatureEnabled")
    val isEBookPrintFeatureEnabled: Boolean? = null,
    @ColumnInfo("isLite")
    val isLite: Boolean? = null,
    @ColumnInfo("gradeId")
    val gradeId: Int? = null,
    @ColumnInfo("tutorCreditBalance")
    val tutorCreditBalance: String? = null,
    @ColumnInfo("heading")
    val heading: String? = null,
    @ColumnInfo("subjects")
    val subjects: String? = null,
    @ColumnInfo("fullName")
    val fullName: String? = null,
    @ColumnInfo("userId")
    val userId: Int? = null,
    @ColumnInfo("isOtpEnabled")
    val isOtpEnabled: Boolean? = null,
    @ColumnInfo("phoneNumber")
    val phoneNumber: String? = null,
    @ColumnInfo("grade")
    val grade: String? = null,
    @ColumnInfo("location")
    val location: String? = null,
    @ColumnInfo("isEmailConfirmed")
    val isEmailConfirmed: Boolean? = null
)

@Entity(tableName = "subjects")
data class SubjectEntity(
    @PrimaryKey
    @ColumnInfo(name = "subjectId")
    val subjectId: Int? = null,
    @ColumnInfo(name = "yearlyPrice")
    val yearlyPrice: String? = null,
    @ColumnInfo(name = "studentSubject")
    val studentSubject: StudentSubject? = null,
    @ColumnInfo(name = "validityStartDate")
    val validityStartDate: String? = null,
    @ColumnInfo(name = "level")
    val level: Level? = null,
    @ColumnInfo(name = "packageId")
    val packageId: Int? = null,
    @ColumnInfo(name = "packageTag")
    val packageTag: String? = null,
    @ColumnInfo(name = "monthlyPrice")
    val monthlyPrice: String? = null,
    @ColumnInfo(name = "validityEndDate")
    val validityEndDate: String? = null,
    @ColumnInfo(name = "halfYearlyPrice")
    val halfYearlyPrice: String? = null,
    @ColumnInfo(name = "assetType")
    val assetType: AssetType? = null,
    @ColumnInfo(name = "photoUrl")
    val photoUrl: String? = null,
    @ColumnInfo(name = "isComingSoon")
    val isComingSoon: Boolean? = null,
    @ColumnInfo(name = "name")
    val name: String? = null,
    @ColumnInfo(name = "planEndDate")
    val planEndDate: String? = null,
    @ColumnInfo(name = "packageGrade")
    val packageGrade: String? = null,
    @ColumnInfo(name = "isStudentPremium")
    val isStudentPremium: Boolean? = null,
    @ColumnInfo(name = "order")
    val order: Int? = null
)

@Entity(tableName = "videos")
data class VideoEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "completion")
    val completion: String? = null,
    @ColumnInfo(name = "photoUrl")
    val photoUrl: String? = null,
    @ColumnInfo(name = "chapters")
    val chapters: List<ChaptersItem?>? = null,
    @ColumnInfo(name = "subjectDescription")
    val subjectDescription: String? = null,
    @ColumnInfo(name = "totalVideoWatchedTimeInSeconds")
    val totalVideoWatchedTimeInSeconds: String? = null,
    @ColumnInfo(name = "className")
    val className: String? = null,
    @ColumnInfo(name = "subjectName")
    val subjectName: String? = null,
    @ColumnInfo(name = "mastery")
    val mastery: String? = null,
)