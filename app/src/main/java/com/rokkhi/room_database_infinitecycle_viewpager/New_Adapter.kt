package com.rokkhi.room_database_infinitecycle_viewpager

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

class New_Adapter:PagerAdapter {

    var all_words:Array<String>
    var context:Context

    lateinit var inflater:LayoutInflater

    constructor(context: Context, all_words:Array<String>):super(){

        this.context=context
        this.all_words=all_words
    }


    override fun isViewFromObject(p0: View, p1: Any): Boolean =p0==p1 as CardView

    override fun getCount(): Int =all_words.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var wordText: TextView
        inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var view:View=inflater.inflate(R.layout.word_with_meaning,container,false)
        wordText=view.findViewById(R.id.word_text)
        //image.setBackgroundResource(all_words[position])
        wordText.setText(all_words[position])
        container!!.addView(view)

        return view

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container!!.removeView(`object` as CardView)
    }

}