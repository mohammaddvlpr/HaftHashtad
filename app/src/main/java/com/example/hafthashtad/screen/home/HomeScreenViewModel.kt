package com.example.hafthashtad.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.domain.cat.useCase.GetAllCatsPagingFlowUseCase
import com.example.domain.cat.useCase.GetFavouriteCatIdsFlowUseCase
import com.example.domain.cat.useCase.ToggleFavouriteUseCase
import com.example.hafthashtad.screen.home.models.CatUiModel
import com.example.hafthashtad.screen.home.models.HomeScreenUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    getAllCatsPagingFlowUseCase: GetAllCatsPagingFlowUseCase,
    private val homeScreenUiMapper: HomeScreenUiMapper,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
    private val getFavouriteCatIdsFlowUseCase: GetFavouriteCatIdsFlowUseCase
) :
    ViewModel() {
    fun onFavouriteClick(catUiModel: CatUiModel) {
        viewModelScope.launch {
            toggleFavouriteUseCase(catUiModel.id)
        }
    }

    val pagingFlow = getAllCatsPagingFlowUseCase().map { pagingData ->
        pagingData.map { catModel ->
            homeScreenUiMapper.mapDomainToUi(catModel)
        }

    }.cachedIn(viewModelScope)

    val favouriteCatIdsFlow = getFavouriteCatIdsFlowUseCase().map { it.toHashSet() }

}