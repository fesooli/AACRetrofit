package br.com.fellipe.demoaacretrofic.repositories

import android.arch.lifecycle.LiveData
import br.com.fellipe.demoaacretrofic.entities.EnderecoResponse

/**
 * Created by logonrm on 03/03/2018.
 */
interface EnderecoRepository {

    fun buscarEndereco(cep: String): LiveData<EnderecoResponse>
}