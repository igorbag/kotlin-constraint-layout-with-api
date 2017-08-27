package com.example.igor_zs.kotlin_constraintlayout_with_api

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import org.jetbrains.anko.async
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL

object Constantes {
    val URL_MERCADO_BITCOIN = "https://www.mercadobitcoin.com.br/api/ticker/"
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadComponets()

    }

    /**
     * Metodo responsavel por carregar componentes
     *
     * @author Igor Rotondo Bagliotti - igor.bagliotti@gmail.com
     * @since 27/08/2017
     */
    private fun loadComponets() {
        val btnBuscar = findViewById<Button>(R.id.btnBuscar)
        btnBuscar.onClick {
            this.getAllBitcoinsInformations()
        }
    }

    /**
     * Metodo por buscar todas as informacoes de valores do mercado bitcoin
     * @author Igor Rotondo Bagliotti - igor.bagliotti@gmail.com
     * @since 27/08/2017
     */
    private fun getAllBitcoinsInformations() {
        if (!isNetworkConnected()) {
            AlertDialog.Builder(this).setTitle("Sem conexão com a internet")
                    .setMessage("Por favor, verifique sua conexão e tente novemente")
                    .setPositiveButton(android.R.string.ok) { dialog, which -> }

        }
        this.doAsync()
    }

    /**
     * Metodo responsavel por verificar se a internet esta conectada
     *
     * @author Igor Rotondo Bagliotti - igor.bagliotti@gmail.com
     * @since 27/08/2017
     */
    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager// 1
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    /**
     * Metodo responsavel por fazer requisicao assincrona
     *
     * @author Igor Rotondo Bagliotti - igor.bagliotti@gmail.com
     * @since 27/08/2017
     */
    private fun doAsync() {
        async {
            val result = URL(Constantes.URL_MERCADO_BITCOIN).readText()
            uiThread {
                toast(result)
            }
        }
    }
}
