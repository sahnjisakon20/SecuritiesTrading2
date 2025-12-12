package com.cathaybk.passkey.di

import com.example.securitiestrading.KSPKoinModule
import com.example.securitiestrading.module.RetrofitApi.RetrofitModule
import org.koin.ksp.generated.module


fun getKoinModuleList(): List<org.koin.core.module.Module> = listOf(
    RetrofitModule.module,
    KSPKoinModule().module
)