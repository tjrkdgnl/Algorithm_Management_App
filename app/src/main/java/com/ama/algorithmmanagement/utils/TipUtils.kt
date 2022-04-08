package com.ama.algorithmmanagement.utils

import com.ama.algorithmmanagement.domain.entity.Tag

object TipUtils {
    /**
     * @param tags Problem 에서 태그리스트
     * @return 태그리스트에서 한국어로된 태그만 문자열로 리턴
     * 팁 작성화면에서 문제정보를 가져올때 List< Tags > 에서
     * 한국어로 된 유형만 가져와서 문자열로 뿌려줌
     */
    fun tagsConvertToString(tags:List<Tag>?):String{
        var result = ""
        tags?.map{tag->
            tag.displayNames.map{ dp->
                if(dp.language=="ko"){
                    result+="${dp.name},"
                }
            }
        }
        return result.slice(IntRange(0,result.length-1))
    }
}