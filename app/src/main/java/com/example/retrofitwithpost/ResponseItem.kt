package com.example.retrofitwithpost

@kotlinx.serialization.Serializable
class ResponseItem(
    val agent_id: Int?,
    val datetime: String?,
    val type: String?,
    val duration: String?,
    val comment: String?,
)