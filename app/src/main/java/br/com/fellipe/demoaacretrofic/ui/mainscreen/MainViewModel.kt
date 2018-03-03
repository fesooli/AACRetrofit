package br.com.fellipe.demoaacretrofic.ui.mainscreen

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.fellipe.demoaacretrofic.entities.EnderecoResponse
import br.com.fellipe.demoaacretrofic.repositories.EnderecoRepository
import br.com.fellipe.demoaacretrofic.repositories.EnderecoRepositoryImpl

/**
 * Created by logonrm on 03/03/2018.
 */
class MainViewModel : ViewModel() {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val enderecoRepository: EnderecoRepository
    private val mApiResponse: MediatorLiveData<EnderecoResponse> = MediatorLiveData()
    val apiResponse: LiveData<EnderecoResponse> get() = mApiResponse

    init {
        enderecoRepository = EnderecoRepositoryImpl()
    }

    fun pesquisarEndereco(cep: String): LiveData<EnderecoResponse> {
        isLoading.postValue(true)
        mApiResponse.addSource(enderecoRepository.buscarEndereco(cep)) {
            apiResponse -> mApiResponse.value = apiResponse
            isLoading.postValue(false)
        }
        return mApiResponse
    }
}