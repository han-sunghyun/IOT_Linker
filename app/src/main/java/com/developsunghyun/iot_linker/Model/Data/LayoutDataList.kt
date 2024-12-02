package com.developsunghyun.iot_linker.Model.Data

import com.developsunghyun.iot_linker.R

class InterfaceLayoutData(
    var layoutType: String,
    var slotNumber: Int,
    val image: Int
)

object LayoutDataList {
    val layoutListData: List<List<InterfaceLayoutData>> = listOf(
        listOf(
            InterfaceLayoutData(
                layoutType = "Layout_2Slot_1",
                slotNumber = 2,
                image = R.drawable.layout_2solt1
            ),
            InterfaceLayoutData(
                layoutType = "Layout_2Slot_2",
                slotNumber = 2,
                image = R.drawable.layout_2solt2
            ),
            InterfaceLayoutData(
                layoutType = "Layout_2Slot_3",
                slotNumber = 2,
                image = R.drawable.layout_2solt3
            )
        ),
        listOf(
            InterfaceLayoutData(
                layoutType = "Layout_3Slot_1",
                slotNumber = 3,
                image = R.drawable.layout_3solt1
            ),
            InterfaceLayoutData(
                layoutType = "Layout_3Slot_2",
                slotNumber = 3,
                image = R.drawable.layout_3solt2
            )
        )

    )
}