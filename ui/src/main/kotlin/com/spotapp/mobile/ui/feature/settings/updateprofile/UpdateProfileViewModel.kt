package com.spotapp.mobile.ui.feature.settings.updateprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spotapp.mobile.data.models.User
import com.spotapp.mobile.data.repository.UsersRepository
import com.spotapp.mobile.domain.usecases.UpdateUserName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UiState(
    val errorMessage: String? = null,
    val user: User? = null,
    val showConfirmationModal: Boolean = false,
    val showEditPasswordDialog: Boolean = false,
    val showAnonymousDialog: Boolean = false,
)

class UpdateProfileViewModel(
    private val usersRepository: UsersRepository,
    private val updateUserNameUseCase: UpdateUserName,
) : ViewModel() {

    private val viewModelState: MutableStateFlow<UiState> =
        MutableStateFlow(UiState())

    val uiState = viewModelState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            viewModelState.update {
                it.copy(
                    user = usersRepository.getUserInformation()
                )
            }
        }
    }

    fun updateUserName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                updateUserNameUseCase(name)
            }.onSuccess {
                viewModelState.update {
                    it.copy(
                        showConfirmationModal = true
                    )
                }
            }.onFailure { throwable ->
                viewModelState.update {
                    it.copy(
                        errorMessage = throwable.message
                    )
                }
            }
        }
    }

    fun showUpdatePasswordDialog() {
        viewModelState.value.user?.let { user ->
            if (user.isAnonymous) {
                viewModelState.update {
                    it.copy(
                        showAnonymousDialog = true,
                    )
                }
            } else {
                viewModelState.update {
                    it.copy(
                        showEditPasswordDialog = true,
                    )
                }
            }
        }
    }

    fun hideUpdatePasswordDialog() {
        viewModelState.update {
            it.copy(
                showEditPasswordDialog = false,
            )
        }
    }

    fun updateUserPassword(oldPassword: String, newPassword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                usersRepository.updateUserPassword(oldPassword, newPassword)
            }.onSuccess {
                viewModelState.update {
                    it.copy(
                        showConfirmationModal = true,
                        showEditPasswordDialog = false,
                    )
                }
            }.onFailure { throwable ->
                viewModelState.update {
                    it.copy(
                        errorMessage = throwable.message,
                        showEditPasswordDialog = false,
                    )
                }
            }
        }
    }

    fun hideConfirmationModal() {
        viewModelState.update { it.copy(showConfirmationModal = false) }
    }

    fun hideAnonymousDialog() {
        viewModelState.update {
            it.copy(
                showAnonymousDialog = false,
            )
        }
    }

    fun cleanError() {
        viewModelState.update { it.copy(errorMessage = null) }
    }
}