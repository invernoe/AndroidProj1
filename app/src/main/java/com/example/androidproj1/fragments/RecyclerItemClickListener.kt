package com.example.androidproj1.fragments

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

  class RecyclerItemClickListener(context: Context, recyclerView: RecyclerView,
                                  private val mListener: OnItemClickListener?) : RecyclerView.OnItemTouchListener {

    private val mGestureDetector: GestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            return true
        }
    })

      interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

      override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
        val childView = view.findChildViewUnder(e.x, e.y)

        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView))
        }

        return false
    }

      override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
          TODO("Not yet implemented")
      }

      override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
          TODO("Not yet implemented")
      }

  }

