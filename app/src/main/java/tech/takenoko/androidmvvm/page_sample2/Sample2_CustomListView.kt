package tech.takenoko.androidmvvm.page_sample2

import android.content.Context
import android.util.AttributeSet
import android.widget.ListView
import tech.takenoko.androidmvvm.R
import tech.takenoko.androidmvvm.common.BaseCustomAdapter
import tech.takenoko.androidmvvm.databinding.CommonComponentViewRowBinding


/**
 * Created by takenoko on 2018/02/17.
 */
class Sample2_CustomListView(context: Context, attrs: AttributeSet) : ListView(context, attrs) {

    fun setList(list: MutableList<BaseCustomAdapter.SampleList>?) {
        super.setAdapter(
                BaseCustomAdapter<BaseCustomAdapter.SampleList, CommonComponentViewRowBinding>(
                getContext(),
                list,
                R.layout.common_component_view_row,
                { item, binding -> binding.sampleList = item }))
    }
}

