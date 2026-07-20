package com.example.myapplication.data.model

/** 소비 내역 항목의 인정 유형 */
enum class RewardType {
    ENV_CERTIFIED,   // 환경부 인정 · 자동 (다크 배지)
    AI_MATCHED,      // AI 매칭 · 자동 (아웃라인 배지)
    NOT_ELIGIBLE     // 인정 기준 미해당 (회색 배지)
}

/** 이번 주 소비 분석 리스트의 한 항목 */
data class SpendingItem(
    val id: String,
    val type: RewardType,
    val badgeLabel: String,   // "환경부 인정 · 자동"
    val title: String,        // "홈플러스 · 전자영수증 발급"
    val description: String,  // "탄소중립포인트 실천항목 · 환경부가 판정"
    val points: Int           // 적립 물고기 수 (0 = 적립 없음)
)

/** 내 바다 홈 요약 */
data class OceanSummary(
    val fishCount: Int,          // 보유 물고기
    val grade: Int,              // 바다지킴이 등급 (1이 최상위)
    val petSpecies: String,      // "바다거북"
    val petLevel: Int,           // 2
    val recognizedCount: Int     // 이번 주 인정된 소비 건수
)

/** 적립 요약 */
data class RewardSummary(
    val totalFish: Int,          // 이번 주 자동 적립 (60)
    val envCertified: Int,       // 환경부 인정 40
    val aiMatched: Int,          // AI 매칭 20
    val grade: Int,
    val fishToNextGrade: Int,    // 2등급까지 240마리
    val gradeProgress: Float     // 0f..1f
)

/** 등급별 우대금리 행 */
data class GradeRate(
    val grade: Int,
    val rate: String,            // "연 +0.2%p"
    val isCurrent: Boolean = false
)

/** 기부 대상 기관/사업 */
data class DonationTarget(
    val id: String,
    val name: String,            // "OO 해양동물전문구조·치료기관"
    val basis: String            // "해양생태계법 제18조 지정기관"
)

/** 기부 시 예상 효과 */
data class DonationEffect(
    val title: String,           // "성장 +200P · Lv.2 → Lv.3"
    val description: String,     // "물고기 1마리당 성장 1P"
    val achieved: Boolean        // 달성 / 미달
)

/** 전용상품 금리 구성 행 */
data class RateComponent(
    val label: String,           // "기본금리"
    val sublabel: String?,       // "iM 계좌가 없어도 마이데이터로 시작한 고객"
    val value: String            // "연 3.0%" / "+0.2%p"
)
