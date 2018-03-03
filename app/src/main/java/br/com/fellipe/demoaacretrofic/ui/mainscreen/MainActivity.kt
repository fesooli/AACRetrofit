package br.com.fellipe.demoaacretrofic.ui.mainscreen

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import br.com.fellipe.demoaacretrofic.R
import br.com.fellipe.demoaacretrofic.entities.Endereco
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        btPesquisar.setOnClickListener({
            mainViewModel.pesquisarEndereco(etCEP.text.toString())
        })

        mainViewModel.apiResponse.observe(this, Observer { apiResponse ->
            if(apiResponse?.erro == null){
                val endereco = apiResponse?.endereco
                tvResultado.text = endereco?.logradouro +
                        ", " + endereco?.localidade
            } else {
                Log.i("TAG", "ERRO: " + apiResponse.erro)
            }
        })

        mainViewModel.isLoading.observe(this, Observer { isLoading ->
            if(isLoading!!) {
                loading.visibility = View.VISIBLE
            } else {
                loading.visibility = View.GONE
            }
        })
    }
}
