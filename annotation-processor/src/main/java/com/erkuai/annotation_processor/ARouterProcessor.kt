package com.erkuai.annotation_processor

import com.erkuai.annotation.ARouter
import com.google.auto.service.AutoService
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.lang.model.util.Types
import javax.tools.Diagnostic

/**
 *  注解处理器
 */

@AutoService(Processor::class) // 编译期绑定
@SupportedAnnotationTypes("com.erkuai.annotation.ARouter") // 表示要处理哪个注解
@SupportedSourceVersion(SourceVersion.RELEASE_8)
class ARouterProcessor : AbstractProcessor() {

    // 操作element的工具类，函数、类、属性都是element
    private var elementsTool: Elements? = null

    // 类信息的工具类
    private var typesTools: Types? = null

    // 编译期的日志打印
    private var messager: Messager? = null

    // java文件生成
    private var filer: Filer? = null

    override fun init(p0: ProcessingEnvironment?) {
        super.init(p0)
        elementsTool = p0?.elementUtils
        typesTools = p0?.typeUtils
        messager = p0?.messager
        filer = p0?.filer
    }

    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
        if (0 == p0?.size) { //没有注解需要翻译
            messager?.printMessage(Diagnostic.Kind.NOTE, "没有发现被ARouter注解的类")
            return false // 注解处理器不工作
        }
        // 获取被ARouter注解的类信息
        val elements = p1?.getElementsAnnotatedWith(ARouter::class.java) ?: return false
        for (ele in elements) {
            // 获取类节点、获取包节点
            val elePackageName = elementsTool?.getPackageOf(ele) ?: continue
            val packageName = elePackageName.simpleName.toString()
            messager?.printMessage(Diagnostic.Kind.NOTE, "packageName = $packageName")

            // 获取全类名
            val className = ele?.simpleName.toString()
            messager?.printMessage(Diagnostic.Kind.NOTE, "className = $className")

        }
        return true
    }

}