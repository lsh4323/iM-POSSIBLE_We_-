package com.example.myapplication.data

import com.example.myapplication.data.repository.FakeOceanRepository
import com.example.myapplication.data.repository.OceanRepository

/**
 * 경량 의존성 컨테이너. Hilt/Koin 도입 전 단계의 수동 DI.
 * 실제 앱에서는 DI 프레임워크로 교체하는 것을 권장.
 */
object ServiceLocator {
    val oceanRepository: OceanRepository by lazy { FakeOceanRepository() }
}
