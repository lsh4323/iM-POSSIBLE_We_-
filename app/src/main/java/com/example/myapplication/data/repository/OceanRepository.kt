package com.example.myapplication.data.repository


import com.example.myapplication.data.model.DonationEffect
import com.example.myapplication.data.model.DonationTarget
import com.example.myapplication.data.model.GradeRate
import com.example.myapplication.data.model.OceanSummary
import com.example.myapplication.data.model.RateComponent
import com.example.myapplication.data.model.RewardSummary
import com.example.myapplication.data.model.RewardType
import com.example.myapplication.data.model.SpendingItem

/**
 * 데이터 소스 추상화. 지금은 디자인 목업 데이터를 in-memory 로 제공한다.
 * 실제 연동 시 이 인터페이스 구현을 마이데이터/서버 API 로 교체하면 된다.
 */
interface OceanRepository {
    fun getOceanSummary(): OceanSummary
    fun getWeeklySpending(): List<SpendingItem>
    fun getRewardSummary(): RewardSummary
    fun getGradeRates(): List<GradeRate>
    fun getDonationTargets(): List<DonationTarget>
    fun getDonationEffects(): List<DonationEffect>
    fun getProductRateComponents(): List<RateComponent>
}

/** 디자인 시안 값을 그대로 반환하는 목업 구현 */
class FakeOceanRepository : OceanRepository {

    override fun getOceanSummary() = OceanSummary(
        fishCount = 1240,
        grade = 3,
        petSpecies = "바다거북",
        petLevel = 2,
        recognizedCount = 3
    )

    override fun getWeeklySpending() = listOf(
        SpendingItem(
            id = "1", type = RewardType.ENV_CERTIFIED, badgeLabel = "환경부 인정 · 자동",
            title = "홈플러스 · 전자영수증 발급",
            description = "탄소중립포인트 실천항목 · 환경부가 판정", points = 10
        ),
        SpendingItem(
            id = "2", type = RewardType.ENV_CERTIFIED, badgeLabel = "환경부 인정 · 자동",
            title = "스타벅스 · 텀블러 이용",
            description = "탄소중립포인트 실천항목 · 환경부가 판정", points = 30
        ),
        SpendingItem(
            id = "3", type = RewardType.AI_MATCHED, badgeLabel = "AI 매칭 · 자동",
            title = "북대구농협 로컬푸드직매장 · 23,400원",
            description = "공개 직매장 목록과 일치 · 보완 적립(공인 아님)", points = 20
        ),
        SpendingItem(
            id = "4", type = RewardType.NOT_ELIGIBLE, badgeLabel = "인정 기준 미해당",
            title = "OO카페 · 4,500원",
            description = "인정 기준에 연결되는 항목 없음", points = 0
        ),
    )

    override fun getRewardSummary() = RewardSummary(
        totalFish = 60,
        envCertified = 40,
        aiMatched = 20,
        grade = 3,
        fishToNextGrade = 240,
        gradeProgress = 0.6f
    )

    override fun getGradeRates() = listOf(
        GradeRate(1, "연 +0.5%p (최대)"),
        GradeRate(2, "연 +0.3%p"),
        GradeRate(3, "연 +0.2%p", isCurrent = true),
        GradeRate(4, "연 +0.15%p"),
        GradeRate(5, "연 +0.1%p"),
    )

    override fun getDonationTargets() = listOf(
        DonationTarget("1", "OO 해양동물전문구조·치료기관", "해양생태계법 제18조 지정기관"),
        DonationTarget("2", "OO 갯벌복원사업", "갯벌복원사업 지침 제5조"),
    )

    override fun getDonationEffects() = listOf(
        DonationEffect("성장 +200P · Lv.2 → Lv.3", "물고기 1마리당 성장 1P", achieved = true),
        DonationEffect("산호 장식 획득", "500마리 이상 보낼 때 · 300마리 더 필요", achieved = false),
    )

    override fun getProductRateComponents() = listOf(
        RateComponent("기본금리", null, "연 3.0%"),
        RateComponent("신규 고객 우대", "iM 계좌가 없어도 마이데이터로 시작한 고객", "+0.1%p"),
        RateComponent("바다지킴이 3등급", "가입 시점 등급으로 확정 · 이후 하락해도 유지", "+0.2%p"),
        RateComponent("만기 추가 우대", "가입 기간 중 적립 실적으로 만기에 산정", "최대 +0.3%p"),
    )
}
