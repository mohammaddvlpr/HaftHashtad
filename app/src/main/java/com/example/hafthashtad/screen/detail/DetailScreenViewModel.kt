package com.example.hafthashtad.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.cat.useCase.GetCatDetailByIDUseCase
import com.example.hafthashtad.navigation.ID
import com.example.hafthashtad.screen.detail.models.DetailScreenMapper
import com.example.hafthashtad.screen.detail.models.DetailScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCatDetailByIDUseCase: GetCatDetailByIDUseCase,
    private val detailScreenMapper: DetailScreenMapper
) :
    ViewModel() {
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
                            newsDetailUiModel = detailScreenMapper.mapNewModelToNewsDetailUiModel(
                                resultValue
                            )
                        )
                    }
            }


        }
    }


}