package com.meow.himmel.domain.use_case.user

import android.net.Uri
import com.meow.himmel.domain.repository.UserRepository

class UpdateAvatar(
     private val repository: UserRepository
) {
    operator fun invoke(uri: Uri) = repository.updateAvatar(uri)
}