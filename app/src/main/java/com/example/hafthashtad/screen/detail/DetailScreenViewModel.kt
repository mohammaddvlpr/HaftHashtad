package com.example.hafthashtad.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.cat.useCase.GetCatDetailByIDUseCase
import com.example.domain.cat.useCase.ToggleFavouriteUseCase
import com.example.hafthashtad.navigation.ID
import com.example.hafthashtad.screen.detail.models.DetailScreenMapper
import com.example.hafthashtad.screen.detail.models.DetailScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCatDetailByIDUseCase: GetCatDetailByIDUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
    private val detailScreenMapper: DetailScreenMapper
) :
    ViewModel() {
    private var job: Job? = null
    fun onFavouriteClick() {
        job?.cancel()
        job = viewModelScope.launch {
            toggleFavouriteUseCase(_state.value.catDetailUiModel.id)
            _state.update { it.copy(catDetailUiModel = it.catDetailUiModel.copy(isFavourite = !it.catDetailUiModel.isFavourite)) }
        }
    }

    private val _state = MutableStateFlow(DetailScreenState())
    val state: StateFlow<DetailScreenState> = _state

    private val id: String? = savedStateHandle[ID]

    init {
        viewModelScope.launch {
            if (id != null) {
                val result = getCatDetailByIDUseCase(id)
                val resultValue = result.getOrNull()

                if (resultValue != null)
                    _state.update {
                        it.copy(
                            catDetailUiModel = detailScreenMapper.mapNewModelToNewsDetailUiModel(
                                resultValue
                            )
                        )
                    }
            }


        }
    }


}