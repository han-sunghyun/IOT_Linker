package com.developsunghyun.iot_linker.Model.Repository

import com.developsunghyun.iot_linker.Model.Data.ModuleData

class ModuleRepository {
    fun moduleStrJoin(moduleList: List<ModuleData>): String{
        return moduleList.joinToString(",")
    }
}