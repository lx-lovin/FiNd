package com.example.find

import ai.fritz.core.Fritz
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ai.fritz.vision.FritzVision
import ai.fritz.vision.objectdetection.FritzVisionObjectPredictor
import ai.fritz.fritzvisionobjectmodel.ObjectDetectionOnDeviceModel
import ai.fritz.core.FritzOnDeviceModel
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.R.attr.bitmap
import ai.fritz.vision.FritzVisionImage
import ai.fritz.vision.FritzVisionLabel
import ai.fritz.vision.FritzVisionObject
import ai.fritz.vision.imagelabeling.FritzVisionLabelResult
import ai.fritz.vision.objectdetection.FritzVisionObjectResult
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.AsyncTask
import android.service.notification.Condition
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.util.Log
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.result_showing.*
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.file.Files.find
import java.nio.file.Files.size
import java.sql.Connection


class MainActivity : AppCompatActivity() {

    lateinit var prediction:String
    lateinit var dialog: Dialog
  //  lateinit var recyclerView:RecyclerView
    lateinit var predictor:FritzVisionObjectPredictor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Fritz.configure(this, "0ee7b087b95d4d949f4f885666f5df29")

        dialog = Dialog(this)

      //  recyclerView = findViewById(R.id.recyclerView)

        val onDeviceModel = ObjectDetectionOnDeviceModel()
        predictor = FritzVision.ObjectDetection.getPredictor(onDeviceModel)
        var intent:Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,1)
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode== Activity.RESULT_OK && requestCode==1 && data!=null){
            var bitamp:Bitmap = data.extras.get("data")  as Bitmap


            //dialog.setContentView(R.layout.result_showing)
            //dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            //dialog.window.setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT)
            //dialog.window.attributes.gravity = Gravity.CENTER





            val visionImage = FritzVisionImage.fromBitmap(bitamp)

            val objectResult = predictor.predict(visionImage)

            var results:ArrayList<Data> = arrayListOf()

            var list:ArrayList<FritzVisionObject> = objectResult.visionObjects as ArrayList<FritzVisionObject>

            if (list != null && list.size > 0) {

                var labell = objectResult.visionObjects.get(0)
                prediction = labell.visionLabel.text.toString()
                label.text = labell.visionLabel.text.toString()
                wiki.setImageResource(R.drawable.browse)
                //var adapterr:RecyclerViewAdapter = RecyclerViewAdapter(this,results)
                //recyclerView.layoutManager = LinearLayoutManager(this)
                //recyclerView.adapter = adapterr

            }

            wiki.setOnClickListener {
                var intent1 = Intent(this,InfoAboutObject::class.java)
                intent1.putExtra("result",prediction)
                startActivity(intent1)
            }

            tryAgain.setOnClickListener {
                var intent:Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent,1)
            }

            imageView.setImageBitmap(objectResult.getResultBitmap())


        }
    }
}

