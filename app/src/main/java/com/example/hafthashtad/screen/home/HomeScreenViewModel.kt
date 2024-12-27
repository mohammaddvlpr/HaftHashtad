package com.example.hafthashtad.screen.home

import androidx.lifecycle.ViewModel
import androidx.paging.map
import com.example.domain.cat.useCase.GetAllCatsPagingFlowUseCase
import com.example.hafthashtad.screen.home.models.HomeScreenUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    getAllCatsPagingFlowUseCase: GetAllCatsPagingFlowUseCase,
    private val homeScreenUiMapper: HomeScreenUiMapper,
) :
    ViewModel() {

    val pagingFlow = getAllCatsPagingFlowUseCase().map { pagingData ->
        pagingData.map { newsModel ->
            homeScreenUiMapper.mapDomainToUi(newsModel)
        }

    }



}