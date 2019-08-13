package com.example.async_task

import android.content.Context
import android.os.AsyncTask
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class myTask(context: Context,button: Button,progressBar: ProgressBar,textView: TextView):AsyncTask<Unit,Int,String>(){ //arg(,progress,returnData)

    lateinit var context: Context
    lateinit var button: Button
    lateinit var progressBar: ProgressBar
    lateinit var textView: TextView

    init {
        this.context = context
        this.button = button
        this.progressBar = progressBar
        this.textView = textView
    }

    override fun doInBackground(vararg p0: Unit?): String {
        for(i in 1..100){
            Thread.sleep(100)
            publishProgress(i)
        }
        return "Download Completed"
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        val progress =values[0]
        textView.text = "$progress% Downloaded..."
        progressBar.progress = progress!! // !! promise never null value
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        textView.text = result
        button.isEnabled = true
    }

    override fun onPreExecute() {
        super.onPreExecute()
        textView.text = "Preparing"
        button.isEnabled = false
        Thread.sleep(1000)
    }
}