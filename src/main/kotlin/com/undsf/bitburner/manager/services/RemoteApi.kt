package com.undsf.bitburner.manager.services

interface RemoteApi {
    /**
     * 推送文件
     */
    fun pushFile(
        filename: String,
        content: String,
        server: String
    )

    /**
     * 获取文件内容
     */
    fun getFile(
        filename: String,
        server: String
    )

    /**
     * 删除文件
     */
    fun deleteFile(
        filename: String,
        server: String
    )

    /**
     * 获取文件名列表
     */
    fun getFileNames(server: String)

    /**
     * 获取所有文件及内容
     */
    fun getAllFiles(server: String)

    /**
     * 计算内存消耗
     */
    fun calculateRam(
        filename: String,
        server: String
    )

    /**
     * 获取TS类定义
     */
    fun getDefinitionFile()
}