package com.erkuai.annotation_processor

import com.erkuai.annotation.ARouter
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
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

//        mModuleName = p0?.options?.get(ProcessorConfig.OPTIONS)

        messager?.printMessage(Diagnostic.Kind.NOTE, "processor 初始化完成.....")
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
            messager?.printMessage(Diagnostic.Kind.NOTE, "被ARouter注解的类 className = $className")

            val aRouter = ele.getAnnotation(ARouter::class.java)

            // 生成一段代码
            generateHelloWorld()
        }
        return true
    }

    private fun generateHelloWorld() {

        // 创建一个类类型
        val greeterClass = ClassName("myGenerate", "Greeter")
// 创建名为HelloWorld的文件
        val file = FileSpec.builder("myGenerate", "HelloWorld")
            // 文件中添加一个Greeter类
            .addType(
                TypeSpec.classBuilder("Greeter")
                    // 类中的构造方法中，增加一个name属性
                    .primaryConstructor(
                        FunSpec.constructorBuilder()
                            .addParameter("name", String::class)
                            .build()
                    )
                    // 类中增加一个方法
                    .addFunction(
                        FunSpec.builder("greet")
                            // 方法中的语句，%P通配符代表了字符串模板
                            .addStatement("println(%P)", "Hello, \$name")
                            .build()
                    )
                    .build()
            )
            // 在HelloWorld的文件中增加一个main方法
            .addFunction(
                FunSpec.builder("main")
                    // main方法中增加一个args的可变参数
                    .addParameter("args", String::class, KModifier.VARARG)
                    // main方法中调用Greeter类中的greet方法
                    .addStatement("%T(args[0]).greet()", greeterClass)
                    .build()
            )
            .build()
// 将文件写入输出流。
        filer?.let {
            file.writeTo(it)
        }
    }

}