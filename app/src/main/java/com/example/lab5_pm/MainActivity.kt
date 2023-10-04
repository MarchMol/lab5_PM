package com.example.lab5_pm

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lab5_pm.repository.Repository
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var tv : TextView
    private lateinit var container : LinearLayout
    private lateinit var butt : Button
    private lateinit var inText : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById<LinearLayout>(R.id.layoutID)
        tv = findViewById(R.id.t1)
        butt = findViewById(R.id.button)
        inText = findViewById(R.id.inputText)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        butt.setOnClickListener(){
            val name = inText.text.toString()
            viewModel.getPost(name)
            viewModel.myResponse.observe(this, Observer {response ->
                if(response.isSuccessful){

                    if(response.body()?.amiibo ==  null || response.body()?.amiibo!!.size==0 ){
                        tv.text = "Amiibo no encontrado!!"
                    } else{
                        tv.text = "Busca tu Amiibo favorito!!"
                    }

                    vaciarContainer(container)
                    response.body()?.amiibo?.forEach(){

                        val textView = TextView(this)
                        val image = ImageView(this)
                        val txt:String = "\nNombre: "+it.name.toString()+"\n Franquicia: "+it.amiiboSeries.toString()
                        textView.text = txt
                        loadImage(it.image.toString(), image)
                        container.addView(textView)
                        container.addView(image)

                    }

                } else{
                    Log.d("Response",response.errorBody().toString())
                    tv.text = "Amiibo no encontrado!!"
                }
            })
        }
    }
}

fun loadImage(url:String, imageView: ImageView){
    val executor = Executors.newSingleThreadExecutor()
    val handler = Handler(Looper.getMainLooper())
    var image:Bitmap? = null

    executor.execute{
        try{
            val `in` = java.net.URL(url).openStream()
            image = BitmapFactory.decodeStream(`in`)

            handler.post{
                imageView.setImageBitmap(image)
            }
        }catch (e:Exception){

        }
    }
}

fun vaciarContainer(container: ViewGroup) {
    val textViewList = mutableListOf<TextView>()
    val imageViewList = mutableListOf<ImageView>()

    // Find and collect all TextViews within the container
    for (i in 0 until container.childCount) {
        val child = container.getChildAt(i)
        if (child is TextView) {
            textViewList.add(child)
        } else if(child is ImageView){
            imageViewList.add(child)
        }
    }

    // Remove each TextView from the container and clear its text
    for (textView in textViewList) {
        container.removeView(textView)
    }
    for (imageView in imageViewList) {
        container.removeView(imageView)
    }
}