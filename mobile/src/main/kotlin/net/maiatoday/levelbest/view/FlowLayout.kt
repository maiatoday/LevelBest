package net.maiatoday.levelbest.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

import net.maiatoday.levelbest.R

/**
 * Flow Layout View Group class, converted to Kotlin from java code which I can't find again to attribute, :( sorry
 * Created by maia on 2017/02/22.
 */
class FlowLayout(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {
    var horizontalSpacing: Int = 0
        private set
    private var mVerticalSpacing: Int = 0

    init {
        initAttributes(context, attrs)
    }

    private fun initAttributes(context: Context, attrs: AttributeSet) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout)
        try {
            horizontalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_fl_horizontalSpacing, 0)
            mVerticalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_fl_verticalSpacing, 0)
        } finally {
            a.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthLimit = View.MeasureSpec.getSize(widthMeasureSpec) - paddingRight
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)

        val growHeight = widthMode != View.MeasureSpec.UNSPECIFIED

        var width = 0

        var currentWidth = paddingLeft
        var currentHeight = paddingTop

        var maxChildHeight = 0

        var breakLine = false
        var newLine = false
        var spacing = 0

        val count = childCount
        for (i in 0..count - 1) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)

            val lp = child.layoutParams as LayoutParams
            spacing = horizontalSpacing

            if (lp.horizontalSpacing >= 0) {
                spacing = lp.horizontalSpacing
            }

            if (growHeight && (breakLine || currentWidth + child.measuredWidth > widthLimit)) {
                newLine = true
                currentHeight += maxChildHeight + mVerticalSpacing

                width = Math.max(width, currentWidth - spacing)

                currentWidth = paddingLeft
                maxChildHeight = 0
            } else {
                newLine = false
            }

            maxChildHeight = Math.max(maxChildHeight, child.measuredHeight)

            lp.x = currentWidth
            lp.y = currentHeight

            currentWidth += child.measuredWidth + spacing

            breakLine = lp.breakLine
        }

        if (newLine == false) {
            width = Math.max(width, currentWidth - spacing)
        }

        width += paddingRight
        val height = currentHeight + maxChildHeight + paddingBottom

        setMeasuredDimension(View.resolveSize(width, widthMeasureSpec),
                View.resolveSize(height, heightMeasureSpec))
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val count = childCount
        for (i in 0..count - 1) {
            val child = getChildAt(i)
            val lp = child.layoutParams as LayoutParams
            child.layout(lp.x, lp.y, lp.x + child.measuredWidth, lp.y + child.measuredHeight)
        }
    }

    override fun checkLayoutParams(p: ViewGroup.LayoutParams): Boolean {
        return p is LayoutParams
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun generateLayoutParams(attrs: AttributeSet): LayoutParams {
        return LayoutParams(context, attrs)
    }

    override fun generateLayoutParams(p: ViewGroup.LayoutParams): LayoutParams {
        return LayoutParams(p.width, p.height)
    }

    class LayoutParams : ViewGroup.LayoutParams {
        var horizontalSpacing: Int = 0
        var breakLine: Boolean = false
        internal var x: Int = 0
        internal var y: Int = 0

        constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout_LayoutParams)
            try {
                horizontalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_LayoutParams_fl_layout_horizontalSpacing, -1)
                breakLine = a.getBoolean(R.styleable.FlowLayout_LayoutParams_fl_layout_breakLine, false)
            } finally {
                a.recycle()
            }
        }

        constructor(w: Int, h: Int) : super(w, h) {}

        constructor(w: Int, h: Int, horizontalSpacingDp: Int) : super(w, h) {
            horizontalSpacing = horizontalSpacingDp
        }
    }
}