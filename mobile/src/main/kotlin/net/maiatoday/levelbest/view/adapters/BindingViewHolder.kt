package net.maiatoday.levelbest.view.adapters

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import net.maiatoday.levelbest.BR

/**
 * Created by maia on 2017/02/27.
 */
class BindingViewHolder(private val binding: ViewDataBinding, private val handler: Any) : RecyclerView.ViewHolder(binding.root) {

    fun bind(obj: Any): Unit {
        binding.setVariable(BR.obj, obj)
        binding.setVariable(BR.handler, handler)
        binding.executePendingBindings()

    }
}
