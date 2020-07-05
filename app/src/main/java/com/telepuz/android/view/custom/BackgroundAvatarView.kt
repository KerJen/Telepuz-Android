package com.telepuz.android.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import com.telepuz.android.R
import kotlin.math.min

class BackgroundAvatarView : View {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint().apply {
        color = context.getColor(R.color.white)
        //TODO: Implement typeface cache
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }
    private val textBound = Rect()
    private var letter: String? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun setAvatar(letter: String, @ColorInt color: Int) {
        this.letter = letter
        paint.color = color
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        textPaint.textSize = min(width, height) * 0.4F
        textPaint.getTextBounds(letter, 0, letter!!.length, textBound)
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(
            measuredWidth / 2F,
            measuredHeight / 2F,
            min(measuredHeight, measuredWidth) / 2f,
            paint
        )

        val textX = measuredWidth / 2F - textBound.exactCenterX()
        val textY = measuredHeight / 2F - textBound.exactCenterY()

        canvas.drawText(letter!!, textX, textY, textPaint)
    }
}