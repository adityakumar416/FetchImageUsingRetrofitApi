package com.example.callapiretrofit

//import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.callapiretrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

        binding.buttonNewImage.setOnClickListener {
            getData()
        }

    }

    private fun getData() {

      /*  val progressDialog=ProgressDialog(this)
        progressDialog.setMessage("Please wait Image is Load")
        progressDialog.show()*/


        binding.halloweenLottie.visibility = View.VISIBLE


        RetrofitInstance.apiInterface.getData().enqueue(object : Callback<responseDataClass?> {
            override fun onResponse(
                call: Call<responseDataClass?>,
                response: Response<responseDataClass?>
            ) {
               // progressDialog.dismiss()
                binding.halloweenLottie.visibility = View.GONE
                binding.dogTitle.text= response.body()?.status
                Glide.with(this@MainActivity).load(response.body()?.message).into(binding.dogImage);

            }

            override fun onFailure(call: Call<responseDataClass?>, t: Throwable) {
                    Toast.makeText(this@MainActivity,"${t.localizedMessage}",Toast.LENGTH_SHORT).show()
                binding.halloweenLottie.visibility = View.GONE


            }
        })
    }
}