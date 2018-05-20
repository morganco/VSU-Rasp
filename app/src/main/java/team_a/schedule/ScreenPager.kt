package team_a.schedule

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

class ScreenPager:ViewPager {
    private val adapter = ScreenPagerAdapter()
    private val viewList = ArrayList<View>()

    constructor(context:Context) : super(context) {
        init()
    }

    constructor(context:Context, attrs:AttributeSet) : super(context, attrs) {
        init()
    }

    override fun generateLayoutParams(attrs:AttributeSet):ViewGroup.LayoutParams {
        return ViewGroup.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT)
    }

    private fun init() {
        setAdapter(adapter)
    }

    override fun addView(child:View, params:ViewGroup.LayoutParams) {
        addScreen(child)
    }

    fun addScreen(screen:View) {
        viewList.add(screen)
        adapter.notifyDataSetChanged()
    }

    private inner class ScreenPagerAdapter:PagerAdapter() {
        override fun getCount():Int {
            return viewList.size
        }

        override fun isViewFromObject(view:View, o:Any):Boolean {
            return view == o
        }

        override fun instantiateItem(container:ViewGroup, position:Int):Any {
            val view = viewList[position]
            container.addView(view)
            return view
        }

    override fun destroyItem(container:ViewGroup, position:Int, `object`:Any) {
            container.removeView(`object` as View)
        }
    }
}