/*
 * MIT License
 *
 * Copyright (c) 2017 Maia Grotepass
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.maiatoday.levelbest.view.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import net.maiatoday.levelbest.R
import net.maiatoday.levelbest.databinding.ItemEntryBinding
import net.maiatoday.levelbest.model.Entry


class RealmEntryRecyclerAdapter(context: Context, private val handler: RealmEntryRecyclerAdapter.OnEntryClick, data: OrderedRealmCollection<Entry>) : RealmRecyclerViewAdapter<Entry, RealmEntryRecyclerAdapter.EntryViewHolder>(context, data, true) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemEntryBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_entry, parent, false)
        return EntryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val obj = data!![position]
        holder.bind(obj)
    }

    inner class EntryViewHolder(private val binding: ItemEntryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(obj: Entry): Unit {
            binding.obj = obj
            binding.executePendingBindings()

        }
    }

    interface OnEntryClick {
        fun entryClick(data: Entry)
    }
}