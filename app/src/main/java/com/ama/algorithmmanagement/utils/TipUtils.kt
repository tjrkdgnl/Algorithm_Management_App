package com.ama.algorithmmanagement.utils

import com.ama.algorithmmanagement.model.Tag

object TipUtils {
    fun tagsConvertToString(tags:List<Tag>):String{
        var result = ""
        tags.map{tag->
            tag.displayNames.map{ dp->
                if(dp.language=="ko"){
                    result+="${dp.name},"
                }
            }
        }
        return result.slice(IntRange(0,result.length-1))
    }
}