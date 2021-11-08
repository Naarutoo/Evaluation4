package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var responselist: DTO

    val i:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for(i in 0..50) {
            nextBtn.setOnClickListener {
                callAPI()

            }
        }
    }

    private fun callAPI() {
        val apiServices =Network.getRetrofitInstance().create(ApiServices::class.java)
        apiServices.getUserDetails().enqueue(
            object : Callback<DTO>{
                override fun onResponse(call: Call<DTO>, response: Response<DTO>) {
                    response.body()?.results?.get(i)?.apply {
                        nameshowing.text=name?.first
                        genderShowing.text=gender
                        cityShowing.text=location?.city
                        countryShowing.text=location?.country
                        dobShowing.text=dob?.date
                        phoneShowing.text=phone
                        emailShowing.text=email
                        Glide.with(imageView).load(picture?.thumbnail).into(imageView)

                    }
                }

                override fun onFailure(call: Call<DTO>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            }
        )
    }
}