package tech.takenoko.androidmvvm.page_sample2

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.databinding.ViewDataBinding
import android.support.annotation.NonNull
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import tech.takenoko.androidmvvm.GetViewCallbackBlock


/**
 * Created by takenoko on 2018/02/17.
 *
 * @param context
 * @param list
 * @param layoutId
 * @param callbackBlock
 */
class Sample2_CustomAdapter<T, in U: ViewDataBinding>(context: Context, list: ObservableArrayList<T>?, val layoutId: Int, val callbackBlock: GetViewCallbackBlock<T, U>) : ArrayAdapter<T>(context, 0, list) {

    class SampleList constructor(var text1: String, var text2: String, var text3: Int)

    override fun getView(position: Int, convertView: View?, @NonNull parent: ViewGroup): View {
        val binding: U
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, parent, false)
            binding.getRoot().setTag(binding)
        } else {
            binding = convertView.getTag() as U
        }
        callbackBlock(getItem(position), binding)
        return binding.root
    }
}
