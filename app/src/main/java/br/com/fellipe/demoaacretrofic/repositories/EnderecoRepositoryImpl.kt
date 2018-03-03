package br.com.fellipe.demoaacretrofic.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.fellipe.demoaacretrofic.api.EnderecoAPI
import br.com.fellipe.demoaacretrofic.entities.Endereco
import br.com.fellipe.demoaacretrofic.entities.EnderecoResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.HttpsURLConnection

/**
 * Created by logonrm on 03/03/2018.
 */
class EnderecoRepositoryImpl : EnderecoRepository {

    private val enderecoAPI: EnderecoAPI

    init {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://viacep.com.br")
                .client(OkHttpClient())
                .build()

        enderecoAPI = retrofit.create(EnderecoAPI::class.java)
    }

    override fun buscarEndereco(cep: String): LiveData<EnderecoResponse> {
        val liveData = MutableLiveData<EnderecoResponse>()
        enderecoAPI.pesquisar(cep).enqueue(object : Callback<Endereco> {
            override fun onFailure(call: Call<Endereco>?, t: Throwable?) {
                liveData.value = EnderecoResponse(t!!)
            }

            override fun onResponse(call: Call<Endereco>?, response: Response<Endereco>?) {
                liveData.value = EnderecoResponse(response?.body()!!)
            }
        })
        return liveData
    }
}